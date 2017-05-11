package com.globbypotato.rockhounding_surface.machines.tileentity;

import java.util.ArrayList;
import java.util.Random;

import com.globbypotato.rockhounding_surface.ModItems;
import com.globbypotato.rockhounding_surface.handler.ModConfig;
import com.globbypotato.rockhounding_surface.handler.ModRecipes;
import com.globbypotato.rockhounding_surface.integration.SupportUtils;
import com.globbypotato.rockhounding_surface.machines.gui.GuiCompostBin;
import com.globbypotato.rockhounding_surface.machines.recipe.CompostBinRecipe;
import com.globbypotato.rockhounding_surface.utils.Utils;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.IGrowable;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.oredict.OreDictionary;

public class TileEntityCompostBin extends TileEntityMachineEnergy {

	public static final int INPUT_SLOT = 0;
	public static final int OUTPUT_SLOT = 0;
	Random rand = new Random();
	public int compostFactor = 100;
	public int capacity = 1000 + ModConfig.machineTank;
	public int amount;
	public ItemStack compostStack = new ItemStack(ModItems.gypsumItems,1,4);

	public TileEntityCompostBin(){
		super(1,1,0);
		input =  new MachineStackHandler(INPUT_SLOTS,this){
			@Override
			public ItemStack insertItem(int slot, ItemStack insertingStack, boolean simulate){
				if(slot == INPUT_SLOT && (hasRecipe(insertingStack) || canCompost(insertingStack) || isValidOredict(insertingStack))){
					return super.insertItem(slot, insertingStack, simulate);
				}
				return insertingStack;
			}
		};
		this.automationInput = new WrappedItemHandler(input, WrappedItemHandler.WriteMode.IN_OUT);
	}

	public static boolean hasRecipe(ItemStack insertingStack){
		return ModRecipes.compostRecipes.stream().anyMatch(
				recipe -> ItemStack.areItemsEqual(recipe.getInput(), insertingStack));
	}

	private boolean canCompost(ItemStack stack) {
		Item composting = stack.getItem();
		return composting instanceof ItemFood
			|| composting instanceof ItemSeeds
			|| (composting instanceof ItemBlock && Block.getBlockFromItem(composting) instanceof IPlantable)
			|| (composting instanceof ItemBlock && Block.getBlockFromItem(composting) instanceof BlockLeaves)
			|| (composting instanceof ItemBlock && Block.getBlockFromItem(composting) instanceof IGrowable && !(Block.getBlockFromItem(composting) instanceof BlockGrass));
	}

	public static boolean isValidOredict(ItemStack stack) {
		if(stack != null){
			ArrayList<Integer> inputIDs = Utils.intArrayToList(OreDictionary.getOreIDs(stack));
			for(CompostBinRecipe recipe: ModRecipes.compostRecipes){
				ArrayList<Integer> recipeIDs = Utils.intArrayToList(OreDictionary.getOreIDs(recipe.getInput()));
				for(Integer ores: recipeIDs){
					if(inputIDs.contains(ores)) return true;
				}
			}
		}
		return false;
	}



	//----------------------- CUSTOM -----------------------
	@Override
	public int getGUIHeight() {
		return GuiCompostBin.HEIGHT;
	}

	public int getMaxCookTime(){
		return ModConfig.speedCompost;
	}

	//----------------------- I/O -----------------------
	@Override
	public void readFromNBT(NBTTagCompound compound){
		super.readFromNBT(compound);
		this.amount = compound.getInteger("Amount");
		this.cookTime = compound.getInteger("CookTime");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound){
		super.writeToNBT(compound);
		compound.setInteger("Amount", this.amount);
		compound.setInteger("CookTime", this.cookTime);
		return compound;
	}

	//-------------------------------------------------
	@Override
	public void update() {
		if(!worldObj.isRemote){
			if(canAcquire()){acquire();}
			if(canProcess(output.getStackInSlot(OUTPUT_SLOT))){
				cookTime++;
				if(cookTime >= getMaxCookTime()){
					cookTime = 0;
					compost();
				}
			}
			this.markDirtyClient();
		}
	}

	private boolean canProcess(ItemStack stack) {
		return this.amount >= this.compostFactor
			&& canOutput(stack);
	}

	private boolean canOutput(ItemStack stack) {
		return (stack == null || (stack != null && stack.isItemEqual(compostStack) && stack.stackSize < stack.getMaxStackSize()));
	}

	private void compost() {
		this.amount -= this.compostFactor;
		output.setOrIncrement(OUTPUT_SLOT, compostStack.copy());
	}

	private boolean canAcquire() {
		return input.getStackInSlot(INPUT_SLOT) != null 
			&& this.amount <= (this.capacity - getCompostFactor());
	}

	private void acquire() {
		this.amount += getCompostFactor(); 
 		input.decrementSlot(INPUT_SLOT);
	}

	public int getCompostFactor() {
		ItemStack stack = input.getStackInSlot(INPUT_SLOT);
		if(stack != null){
			Item composting = stack.getItem();
			if(composting instanceof ItemSeeds || composting == Items.FEATHER){
				return 1;
			}else if(composting instanceof ItemBlock && Block.getBlockFromItem(composting) instanceof BlockSapling){
				return 2;
			}else if(composting instanceof ItemBlock && Block.getBlockFromItem(composting) instanceof BlockLeaves){
				return 3;
			}else if(composting instanceof ItemBlock && Block.getBlockFromItem(composting) instanceof BlockCrops){
				return 4;
			}else if(composting instanceof ItemFood){
				return 8;
			}else if(SupportUtils.rhTiersLoaded() && stack.isItemEqual(SupportUtils.itemPeat())){
				return 50;
			}else if(SupportUtils.rhTiersLoaded() && stack.isItemEqual(SupportUtils.driedPeat())){
				return 30;
			}
		}
		return 4;
	}
}