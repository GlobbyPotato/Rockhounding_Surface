package com.globbypotato.rockhounding_surface.machines.tileentity;

import java.util.ArrayList;

import com.globbypotato.rockhounding_core.machines.tileentity.MachineStackHandler;
import com.globbypotato.rockhounding_core.machines.tileentity.TileEntityInv;
import com.globbypotato.rockhounding_core.machines.tileentity.WrappedItemHandler;
import com.globbypotato.rockhounding_core.machines.tileentity.WrappedItemHandler.WriteMode;
import com.globbypotato.rockhounding_core.utils.CoreUtils;
import com.globbypotato.rockhounding_surface.enums.EnumComposting;
import com.globbypotato.rockhounding_surface.handler.ModConfig;
import com.globbypotato.rockhounding_surface.machines.recipe.CompostBinRecipe;
import com.globbypotato.rockhounding_surface.machines.recipe.MachineRecipes;
import com.globbypotato.rockhounding_surface.utils.BaseRecipes;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.IGrowable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.oredict.OreDictionary;

public class TileEntityCompostBin extends TileEntityInv {

	public int capacity = 1000 + ModConfig.machineTank;
	public int amount;

	public static int inputSlots = 1;
	public static int outputSlots = 1;

	public TileEntityCompostBin(){
		super(inputSlots, outputSlots, 0, 0);
		this.input =  new MachineStackHandler(inputSlots,this){
			@Override
			public ItemStack insertItem(int slot, ItemStack insertingStack, boolean simulate){
				if(slot == INPUT_SLOT && (isCompostableRecipe(insertingStack) || isCompostableDefault(insertingStack))){
					return super.insertItem(slot, insertingStack, simulate);
				}
				return insertingStack;
			}
		};
		this.automationInput = new WrappedItemHandler(this.input, WriteMode.IN);
	}



	//----------------------- SLOT -----------------------
	private ItemStack inputSlot(){
		return this.input.getStackInSlot(INPUT_SLOT);
	}

	private ItemStack outputSlot(){
		return this.output.getStackInSlot(OUTPUT_SLOT);
	}



	//----------------------- I/O -----------------------
	@Override
	public void readFromNBT(NBTTagCompound compound){
		super.readFromNBT(compound);
		this.amount = compound.getInteger("Amount");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound){
		super.writeToNBT(compound);
		compound.setInteger("Amount", getCompostAmount());
		return compound;
	}



	//----------------------- HANDLER -----------------------
	@Override
	public int getGUIHeight() {
		return BaseRecipes.HEIGHT;
	}

	public int getMaxCookTime(){
		return ModConfig.speedCompost;
	}

	public String getName() {
		return "compost_bin";
	}



	//----------------------- RECIPE -----------------------
	public ArrayList<CompostBinRecipe> recipeList(){
		return MachineRecipes.compostRecipes;
	}

	public CompostBinRecipe getRecipeList(int x){
		return recipeList().get(x);
	}

	public boolean isCompostableRecipe(ItemStack insertingStack) {
		if(!insertingStack.isEmpty()){
			ArrayList<Integer> inputIDs = CoreUtils.intArrayToList(OreDictionary.getOreIDs(insertingStack));
			if(!inputIDs.isEmpty()){
				for(CompostBinRecipe recipe: recipeList()){
					ArrayList<Integer> recipeIDs = CoreUtils.intArrayToList(OreDictionary.getOreIDs(recipe.getInput()));
					if(!recipeIDs.isEmpty()){
						for(Integer ores: recipeIDs){
							if(inputIDs.contains(ores)){
								return true;
							}
						}
					}
				}
			}
			return recipeList().stream().anyMatch(recipe -> ItemStack.areItemsEqual(recipe.getInput(), insertingStack));
		}
		return false;
	}

	public CompostBinRecipe getCurrentRecipe(){
		if(!inputSlot().isEmpty()){
			for(int x = 0; x < recipeList().size(); x++){
				if(!getRecipeList(x).getInput().isEmpty() && CoreUtils.isMatchingIngredient(inputSlot(), getRecipeList(x).getInput())){
					return getRecipeList(x);
				}
			}
		}
		return null;
	}

	private boolean isMatchingInput(int x) {
		return ItemStack.areItemsEqual(getRecipeList(x).getInput(), inputSlot());
	}

	public boolean isValidRecipe(){
		return getCurrentRecipe() != null;
	}

	public boolean isCompostableDefault(ItemStack insertingStack) {
		return !isValidRecipe()
			&& (isSpecificPlantable(insertingStack) || isGenericPlantable(insertingStack));
	}

	private boolean isGenericPlantable(ItemStack insertingStack) {
		Item composting = insertingStack.getItem();
		return !isSpecificPlantable(insertingStack) 
			&& (Block.getBlockFromItem(composting) instanceof IGrowable 
			|| Block.getBlockFromItem(composting) instanceof IPlantable || composting instanceof IGrowable || composting instanceof IPlantable);
	}

	private boolean isSpecificPlantable(ItemStack insertingStack) {
		Item composting = insertingStack.getItem();
		return composting instanceof ItemFood
			|| composting instanceof ItemSeeds
			|| Block.getBlockFromItem(composting) instanceof BlockSapling
			|| Block.getBlockFromItem(composting) instanceof BlockLeaves
			|| Block.getBlockFromItem(composting) instanceof BlockCrops;
	}



	//----------------------- CUSTOM -----------------------
	public int getCompostAmount(){
		return this.amount;
	}

	public int getCompostMax(){
		return this.capacity;
	}

	public int compostFactor(){
		return 100;
	}

	public int getCompostFactor(ItemStack insertingStack) {
		if(!insertingStack.isEmpty()){ 
			if(!isValidRecipe() && isCompostableDefault(insertingStack)){
				Item composting = inputSlot().getItem();
				if(composting instanceof ItemSeeds){
					return EnumComposting.SEEDS.getValue();
				}else if(Block.getBlockFromItem(composting) instanceof BlockSapling){
					return EnumComposting.SAPLING.getValue();
				}else if(Block.getBlockFromItem(composting) instanceof BlockLeaves){
					return EnumComposting.VEGETATION.getValue();
				}else if(Block.getBlockFromItem(composting) instanceof BlockCrops){
					return EnumComposting.SAPLING.getValue();
				}else if(composting instanceof ItemFood){
					return EnumComposting.FOOD.getValue();
				}else if(isGenericPlantable(insertingStack)){
					return EnumComposting.PLANTS.getValue();
				}
			}
			if(isValidRecipe()){
				if(getCurrentRecipe().getType() > -1){
					return EnumComposting.getValue(getCurrentRecipe().getType());
				}else{
					return getCurrentRecipe().getValue();
				}
			}else{
				
			}
		}
		return 0;
	}



	//--------------------- PROCESS ---------------------
	@Override
	public void update() {
		if(!this.world.isRemote){
			if(canAcquire()){
				acquire();
			}
			if(canProcess(outputSlot())){
				this.cooktime++;
				if(getCooktime() >= getMaxCookTime()){
					this.cooktime = 0;
					compost();
				}
			}
			this.markDirtyClient();
		}
	}

	private boolean canAcquire() {
		return !inputSlot().isEmpty()
			&& (isValidRecipe() || isCompostableDefault(inputSlot()))
			&& getCompostFactor(inputSlot()) > 0
			&& getCompostAmount() <= (this.capacity - getCompostFactor(inputSlot()));
	}

	private void acquire() {
		this.amount += getCompostFactor(inputSlot()); 
 		this.input.decrementSlot(INPUT_SLOT);
	}

	private boolean canProcess(ItemStack stack) {
		return getCompostAmount() >= compostFactor()
			&& this.input.canSetOrStack(stack, BaseRecipes.compost.copy());
	}

	private void compost() {
		this.amount -= compostFactor();
		this.output.setOrIncrement(OUTPUT_SLOT, BaseRecipes.compost.copy());
	}

}