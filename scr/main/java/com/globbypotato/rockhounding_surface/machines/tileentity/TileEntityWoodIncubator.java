package com.globbypotato.rockhounding_surface.machines.tileentity;

import com.globbypotato.rockhounding_core.machines.tileentity.MachineStackHandler;
import com.globbypotato.rockhounding_core.machines.tileentity.TemplateStackHandler;
import com.globbypotato.rockhounding_core.machines.tileentity.TileEntityMachineTank;
import com.globbypotato.rockhounding_core.machines.tileentity.WrappedItemHandler;
import com.globbypotato.rockhounding_core.machines.tileentity.WrappedItemHandler.WriteMode;
import com.globbypotato.rockhounding_surface.handler.ModConfig;
import com.globbypotato.rockhounding_surface.machines.gui.GuiWoodIncubator;
import com.globbypotato.rockhounding_surface.machines.recipe.MachineRecipes;
import com.globbypotato.rockhounding_surface.machines.recipe.WoodIncubatorRecipe;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.templates.FluidHandlerConcatenate;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.oredict.OreDictionary;

public class TileEntityWoodIncubator extends TileEntityMachineTank{

	private ItemStackHandler template = new TemplateStackHandler(3);

	//Input handler slots
	public static final int WOOD_SLOT = 2;
	public static final int REDSTONE_SLOT = 3;
	public static final int SOLVENT_SLOT = 4;

	public FluidTank inputTank;

	public static int totInput = 5;
	public static int totOutput = 1;

	public TileEntityWoodIncubator() {
		super(totInput, totOutput, 1);

		inputTank = new FluidTank(1000 + ModConfig.machineTank){
			@Override
			public boolean canFillFluidType(FluidStack fluid){
				return isActive() && solventHasRecipe(fluid) && isValidInterval() && isCorrectSolvent(fluid);
			}

			@Override
		    public boolean canDrain(){
		        return !isValidInterval() || (isValidInterval() && isWrongSolvent(inputTank));
		    }
		};
		inputTank.setTileEntity(this);
		inputTank.setCanFill(true);

		input =  new MachineStackHandler(totInput,this){
			@Override
			public ItemStack insertItem(int slot, ItemStack insertingStack, boolean simulate){
				if(slot == FUEL_SLOT && isGatedPowerSource(insertingStack) && !isWood(insertingStack)){
					return super.insertItem(slot, insertingStack, simulate);
				}
				if(slot == REDSTONE_SLOT && hasRedstone(insertingStack)){
					return super.insertItem(slot, insertingStack, simulate);
				}
				if(slot == WOOD_SLOT && isActive() && isValidInterval() && inputHasRecipe(insertingStack) && insertingStack.isItemEqual(getRecipe().getInput()) ){
					return super.insertItem(slot, insertingStack, simulate);
				}
				if(slot == INPUT_SLOT && isActive() && isValidInterval() && (soluteHasRecipe(insertingStack) && insertingStack.isItemEqual(getRecipe().getSolute()) || canSoluteOredict(insertingStack, slot))){
					return super.insertItem(slot, insertingStack, simulate);
				}
				if(slot == SOLVENT_SLOT && isActive() && isValidInterval() && solventHasRecipe(FluidUtil.getFluidContained(insertingStack)) && FluidUtil.getFluidContained(insertingStack).isFluidEqual(getRecipe().getSolvent())){
					return super.insertItem(slot, insertingStack, simulate);
				}
				return insertingStack;
			}
		};
		automationInput = new WrappedItemHandler(input, WriteMode.IN);
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
		return GuiWoodIncubator.HEIGHT;
	}

	@Override
	public boolean hasRF(){
		return true;	
	}

	@Override
	public boolean isRFGatedByBlend(){
		return true;
	}



	//----------------------- CUSTOM -----------------------
	public boolean isValidInterval() {
		return recipeIndex >= 0 && recipeIndex <= MachineRecipes.woodIncubatorRecipes.size() - 1;
	}

	public WoodIncubatorRecipe getRecipe(){
		return isValidInterval() ? MachineRecipes.woodIncubatorRecipes.get(recipeIndex) : null;
	}

	public boolean soluteHasRecipe(ItemStack insertingStack){
		return isValidInterval() && MachineRecipes.woodIncubatorRecipes.stream().anyMatch(
				recipe -> insertingStack != null && recipe.getSolute() != null && insertingStack.isItemEqual(getRecipe().getSolute()));
	}

	public boolean inputHasRecipe(ItemStack insertingStack){
		return isValidInterval() && MachineRecipes.woodIncubatorRecipes.stream().anyMatch(
				recipe -> insertingStack != null && recipe.getInput() != null && insertingStack.isItemEqual(getRecipe().getInput()));
	}

	public boolean solventHasRecipe(FluidStack insertingStack){
		return isValidInterval() && MachineRecipes.woodIncubatorRecipes.stream().anyMatch(
				recipe -> insertingStack != null && recipe.getSolvent()!= null && insertingStack.isFluidEqual(getRecipe().getSolvent()));
	}

	private boolean isWood(ItemStack stack) {
		if(stack.getItem() instanceof ItemBlock){
			Block wood = Block.getBlockFromItem(stack.getItem());
			return wood instanceof BlockLog;
		}
		return false;
	}

	private boolean canSoluteOredict(ItemStack stack, int slot) {
		if(isValidInterval() && getRecipe().canOredict()){
			if(stack != null){
				int[] oreIDs = OreDictionary.getOreIDs(stack);
				if(oreIDs.length > 0) {
					for(int i = 0; i < oreIDs.length; i++) {
						if(oreIDs[i] > -1) {
							String oreName = OreDictionary.getOreName(oreIDs[i]);
							ItemStack soluteStack = getRecipe().getSolute();
							if(soluteStack != null){
								int[] tempIDs = OreDictionary.getOreIDs(soluteStack);
								if(tempIDs.length > 0) {
									for(int j = 0; j < tempIDs.length; j++) {
										if(tempIDs[j] > -1) {
											String tempName = OreDictionary.getOreName(tempIDs[j]);
											if(oreName != null && tempName != null && oreName.matches(tempName)){
												return true;
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return false;
	}

	private boolean isCorrectSolvent(FluidStack fluid) {
		return isValidInterval() && fluid.isFluidEqual(getRecipe().getSolvent());
	}

	private boolean isWrongSolvent(FluidTank tank) {
		return isValidInterval() && tank.getFluid() != null && !tank.getFluid().isFluidEqual(getRecipe().getSolvent()) && tank.getFluidAmount() > 0;
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

	public FluidHandlerConcatenate getCombinedTank(){
		return new FluidHandlerConcatenate(lavaTank, inputTank);
	}



	//----------------------- PROCESS -----------------------
	@Override
	public void update(){
		if(!isValidInterval()){ recipeIndex = -1; }
		acceptEnergy();
		fuelHandler(input.getStackInSlot(FUEL_SLOT));
		redstoneHandler(REDSTONE_SLOT, this.getCookTimeMax());
		lavaHandler();

		if(!worldObj.isRemote){
			emptyContainer(SOLVENT_SLOT, inputTank);

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
			&& this.getPower() >= this.getCookTimeMax()
			&& this.getRedstone() >= this.getCookTimeMax()
			&& ItemStack.areItemsEqual(getRecipe().getInput(), input.getStackInSlot(WOOD_SLOT))
			&& (ItemStack.areItemsEqual(getRecipe().getSolute(), input.getStackInSlot(INPUT_SLOT)) || canSoluteOredict(input.getStackInSlot(INPUT_SLOT), INPUT_SLOT))
			&& input.hasEnoughFluid(inputTank.getFluid(), getRecipe().getSolvent())
			&& output.canSetOrStack(output.getStackInSlot(OUTPUT_SLOT), getRecipe().getOutput());
	}

	private void execute() {
		cookTime++;
		powerCount--;
		redstoneCount--; 
		if(cookTime >= getCookTimeMax()) {
			cookTime = 0; 
			handleOutput();
		}
	}

	private void handleOutput() {
		input.drainOrClean(inputTank, getRecipe().getSolvent().amount, true);
		output.setOrStack(OUTPUT_SLOT, getRecipe().getOutput());
		input.decrementSlot(WOOD_SLOT);
		input.decrementSlot(INPUT_SLOT);
	}

}