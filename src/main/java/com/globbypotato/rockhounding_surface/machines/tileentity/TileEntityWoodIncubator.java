package com.globbypotato.rockhounding_surface.machines.tileentity;

import java.util.ArrayList;

import com.globbypotato.rockhounding_core.machines.tileentity.MachineStackHandler;
import com.globbypotato.rockhounding_core.machines.tileentity.TemplateStackHandler;
import com.globbypotato.rockhounding_core.machines.tileentity.TileEntityTank;
import com.globbypotato.rockhounding_core.machines.tileentity.WrappedItemHandler;
import com.globbypotato.rockhounding_core.machines.tileentity.WrappedItemHandler.WriteMode;
import com.globbypotato.rockhounding_core.utils.CoreUtils;
import com.globbypotato.rockhounding_surface.handler.ModConfig;
import com.globbypotato.rockhounding_surface.machines.recipe.MachineRecipes;
import com.globbypotato.rockhounding_surface.machines.recipe.WoodIncubatorRecipe;
import com.globbypotato.rockhounding_surface.utils.BaseRecipes;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.templates.FluidHandlerConcatenate;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.oredict.OreDictionary;

public class TileEntityWoodIncubator extends TileEntityTank{

	public static int inputSlots = 5;
	public static int outputSlots = 1;
	public static int templateSlots = 1;

	private ItemStackHandler template = new TemplateStackHandler(3);

	public static final int WOOD_SLOT = 1;
	public static final int SOLVENT_SLOT = 2;

	public FluidTank inputTank;

	public TileEntityWoodIncubator() {
		super(inputSlots, outputSlots, templateSlots, 0);

		this.inputTank = new FluidTank(ModConfig.machineTank < 1000 ? 1000 : ModConfig.machineTank){
			@Override
			public boolean canFillFluidType(FluidStack fluid){
				return isActive() && solventHasRecipe(fluid) && isValidInterval() && isCorrectSolvent(fluid);
			}

			@Override
		    public boolean canDrain(){
		        return !isValidInterval() || (isValidInterval() && isWrongSolvent(TileEntityWoodIncubator.this.inputTank));
		    }
		};
		this.inputTank.setTileEntity(this);
		this.inputTank.setCanFill(true);

		this.input =  new MachineStackHandler(inputSlots,this){
			@Override
			public ItemStack insertItem(int slot, ItemStack insertingStack, boolean simulate){
				if(slot == WOOD_SLOT && isActive() && isValidInterval() && inputHasRecipe(insertingStack) ){
					return super.insertItem(slot, insertingStack, simulate);
				}
				if(slot == INPUT_SLOT && isActive() && isValidSolute(insertingStack)){
					return super.insertItem(slot, insertingStack, simulate);
				}
				if(slot == SOLVENT_SLOT && isActive() && isValidInterval() && solventHasRecipe(FluidUtil.getFluidContained(insertingStack)) && FluidUtil.getFluidContained(insertingStack).isFluidEqual(getCurrentRecipe().getSolvent())){
					return super.insertItem(slot, insertingStack, simulate);
				}
				return insertingStack;
			}
		};
		this.automationInput = new WrappedItemHandler(this.input, WriteMode.IN);
		this.markDirtyClient();
	}



	//----------------------- HANDLER -----------------------
	public ItemStackHandler getTemplate(){
		return this.template;
	}

	public int getCookTimeMax(){
		return ModConfig.speedWoodIncubator;
	}

	@Override
	public int getGUIHeight() {
		return BaseRecipes.HEIGHT;
	}

	public String getName() {
		return "wood_incubator";
	}



	//----------------------- RECIPE -----------------------
	public ArrayList<WoodIncubatorRecipe> recipeList(){
		return MachineRecipes.woodIncubatorRecipes;
	}

	public WoodIncubatorRecipe getRecipeList(int x){
		return recipeList().get(x);
	}

	public boolean isValidInterval() {
		return getRecipeIndex() > -1 && getRecipeIndex() < recipeList().size();
	}

	public WoodIncubatorRecipe getCurrentRecipe(){
		if(isValidInterval()){
			return getRecipeList(getRecipeIndex());
		}
		return null;
	}

	public boolean isValidRecipe() {
		return getCurrentRecipe() != null;
	}

	public boolean inputHasRecipe(ItemStack insertingStack){
		return isValidInterval() && recipeList().stream().anyMatch(
				recipe -> !insertingStack.isEmpty() && !recipe.getInput().isEmpty() && insertingStack.isItemEqual(getCurrentRecipe().getInput()));
	}

	public boolean solventHasRecipe(FluidStack insertingStack){
		return isValidInterval() && recipeList().stream().anyMatch(
				recipe -> insertingStack != null && recipe.getSolvent()!= null && insertingStack.isFluidEqual(getCurrentRecipe().getSolvent()));
	}

	public boolean isValidSolute(ItemStack insertingStack) {
		if(isValidInterval()){
			if(!insertingStack.isEmpty()){
				ArrayList<Integer> inputIDs = CoreUtils.intArrayToList(OreDictionary.getOreIDs(insertingStack));
				if(!inputIDs.isEmpty()){
					for(WoodIncubatorRecipe recipe: recipeList()){
						ArrayList<Integer> recipeIDs = CoreUtils.intArrayToList(OreDictionary.getOreIDs(recipe.getSolute()));
						if(!recipeIDs.isEmpty()){
							for(Integer ores: recipeIDs){
								if(inputIDs.contains(ores)){
									return true;
								}
							}
						}
					}
				}
				return recipeList().stream().anyMatch(recipe -> ItemStack.areItemsEqual(recipe.getSolute(), insertingStack));
			}
		}
		return false;
	}



	//----------------------- CUSTOM -----------------------
	public boolean isCorrectSolvent(FluidStack fluid) {
		return isValidInterval() && fluid.isFluidEqual(getCurrentRecipe().getSolvent());
	}

	public boolean isWrongSolvent(FluidTank tank) {
		return isValidInterval() && tank.getFluid() != null && !tank.getFluid().isFluidEqual(getCurrentRecipe().getSolvent()) && tank.getFluidAmount() > 0;
	}



	//----------------------- I/O -----------------------
	@Override
	public void readFromNBT(NBTTagCompound compound){
		super.readFromNBT(compound);
		this.recipeIndex = compound.getInteger("RecipeScan");
		this.inputTank.readFromNBT(compound.getCompoundTag("InputTank"));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound){
		super.writeToNBT(compound);
		compound.setInteger("RecipeScan", this.recipeIndex);

		NBTTagCompound inputTankNBT = new NBTTagCompound();
		this.inputTank.writeToNBT(inputTankNBT);
		compound.setTag("InputTank", inputTankNBT);

		return compound;
	}

	@Override
	public FluidHandlerConcatenate getCombinedTank(){
		return new FluidHandlerConcatenate(this.inputTank);
	}



	//----------------------- PROCESS -----------------------
	@Override
	public void update(){
		if(!isValidInterval()){ this.recipeIndex = -1; }

		if(!this.world.isRemote){
			emptyContainer(SOLVENT_SLOT, this.inputTank);

			if(isValidInterval()){
				if(canSynthesize()){
					execute();
				}
			}
			this.markDirtyClient();
		}
	}

	public boolean canSynthesize(){
		return isActive()
			&& isValidInterval()
			&& ItemStack.areItemsEqual(getCurrentRecipe().getInput(), this.input.getStackInSlot(WOOD_SLOT))
			&& isValidSolute(this.input.getStackInSlot(INPUT_SLOT))
			&& this.input.canDrainFluid(this.inputTank.getFluid(), getCurrentRecipe().getSolvent())
			&& this.output.canSetOrStack(this.output.getStackInSlot(OUTPUT_SLOT), getCurrentRecipe().getOutput());
	}

	private void execute() {
		this.cooktime++;
		if(getCooktime() >= getCookTimeMax()) {
			this.cooktime = 0; 
			handleOutput();
		}
	}

	private void handleOutput() {
		this.input.drainOrCleanFluid(this.inputTank, getCurrentRecipe().getSolvent().amount, true);
		this.output.setOrStack(OUTPUT_SLOT, getCurrentRecipe().getOutput());
		this.input.decrementSlot(WOOD_SLOT);
		this.input.decrementSlot(INPUT_SLOT);
	}

}