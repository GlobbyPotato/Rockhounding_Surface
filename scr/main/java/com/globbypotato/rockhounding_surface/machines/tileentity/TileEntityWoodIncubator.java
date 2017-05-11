package com.globbypotato.rockhounding_surface.machines.tileentity;

import com.globbypotato.rockhounding_surface.handler.ModConfig;
import com.globbypotato.rockhounding_surface.handler.ModRecipes;
import com.globbypotato.rockhounding_surface.integration.SupportUtils;
import com.globbypotato.rockhounding_surface.machines.gui.GuiWoodIncubator;
import com.globbypotato.rockhounding_surface.machines.recipe.WoodIncubatorRecipe;
import com.globbypotato.rockhounding_surface.machines.tileentity.WrappedItemHandler.WriteMode;
import com.globbypotato.rockhounding_surface.utils.FuelUtils;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidHandlerConcatenate;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.oredict.OreDictionary;

public class TileEntityWoodIncubator extends TileEntityMachineEnergy implements IFluidHandlingTile{

	private ItemStackHandler template = new TemplateStackHandler(3);

	//Input handler slots
	public static final int SOLUTE_SLOT = 0;
	public static final int SOLVENT_SLOT = 4;
	public static final int INPUT_SLOT = 2;
	public static final int REDSTONE_SLOT = 3;
	public static final int OUTPUT_SLOT = 0;

	public int recipeIndex = -1;
	public boolean activation;

	public FluidTank inputTank;

	public TileEntityWoodIncubator() {
		super(5, 1, 1);

		inputTank = new FluidTank(1000 + ModConfig.machineTank){
			@Override
			public boolean canFillFluidType(FluidStack fluid){
				return activation && solventHasRecipe(fluid) && isValidInterval() && isCorrectSolvent(fluid);
			}

			@Override
		    public boolean canDrain(){
		        return !isValidInterval() || (isValidInterval() && isWrongSolvent(inputTank));
		    }
		};
		inputTank.setTileEntity(this);
		inputTank.setCanFill(true);

		input =  new MachineStackHandler(INPUT_SLOTS,this){
			@Override
			public ItemStack insertItem(int slot, ItemStack insertingStack, boolean simulate){
				if(slot == FUEL_SLOT && (FuelUtils.isItemFuel(insertingStack) || ItemStack.areItemsEqual(insertingStack, SupportUtils.inductor())) && !isWood(insertingStack)){
					return super.insertItem(slot, insertingStack, simulate);
				}
				if(slot == REDSTONE_SLOT && hasRedstone(insertingStack)){
					return super.insertItem(slot, insertingStack, simulate);
				}
				if(slot == INPUT_SLOT && isValidInterval() && inputHasRecipe(insertingStack) && insertingStack.isItemEqual(getRecipe().getInput()) ){
					return super.insertItem(slot, insertingStack, simulate);
				}
				if(slot == SOLUTE_SLOT && isValidInterval() && (soluteHasRecipe(insertingStack) && insertingStack.isItemEqual(getRecipe().getSolute()) || canSoluteOredict(insertingStack, slot))){
					return super.insertItem(slot, insertingStack, simulate);
				}
				if(slot == SOLVENT_SLOT && activation && isValidInterval() && solventHasRecipe(FluidUtil.getFluidContained(insertingStack)) && FluidUtil.getFluidContained(insertingStack).isFluidEqual(getRecipe().getSolvent())){
					return super.insertItem(slot, insertingStack, simulate);
				}
				return insertingStack;
			}
		};
		automationInput = new WrappedItemHandler(input,WriteMode.IN_OUT);
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



	//----------------------- CUSTOM -----------------------
	public boolean isValidInterval() {
		return recipeIndex >= 0 && recipeIndex <= ModRecipes.woodIncubatorRecipes.size() - 1;
	}

	public WoodIncubatorRecipe getRecipe(){
		return isValidInterval() ? ModRecipes.woodIncubatorRecipes.get(recipeIndex) : null;
	}

	public boolean soluteHasRecipe(ItemStack insertingStack){
		return isValidInterval() && ModRecipes.woodIncubatorRecipes.stream().anyMatch(
				recipe -> insertingStack != null && recipe.getSolute() != null && insertingStack.isItemEqual(getRecipe().getSolute()));
	}

	public boolean inputHasRecipe(ItemStack insertingStack){
		return isValidInterval() && ModRecipes.woodIncubatorRecipes.stream().anyMatch(
				recipe -> insertingStack != null && recipe.getInput() != null && insertingStack.isItemEqual(getRecipe().getInput()));
	}

	public boolean solventHasRecipe(FluidStack insertingStack){
		return isValidInterval() && ModRecipes.woodIncubatorRecipes.stream().anyMatch(
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
		this.activation = compound.getBoolean("Activation");
		this.cookTime = compound.getInteger("CookTime");
		this.redstoneCount = compound.getInteger("RedstoneCount");
		this.inputTank.readFromNBT(compound.getCompoundTag("InputTank"));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound){
		super.writeToNBT(compound);
		compound.setInteger("RecipeScan", this.recipeIndex);
		compound.setBoolean("Activation", this.activation);
		compound.setInteger("CookTime", this.cookTime);
		compound.setInteger("RedstoneCount", this.redstoneCount);

		NBTTagCompound inputTankNBT = new NBTTagCompound();
		this.inputTank.writeToNBT(inputTankNBT);
		compound.setTag("InputTank", inputTankNBT);

		return compound;
	}

	public boolean interactWithBucket(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		boolean didFill = FluidUtil.interactWithFluidHandler(heldItem, this.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, side), player);
		this.markDirtyClient();
		return didFill;
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) return true;
		else return super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
			return (T) getCombinedTank();
		return super.getCapability(capability, facing);
	}

	public FluidHandlerConcatenate getCombinedTank(){
		return new FluidHandlerConcatenate(inputTank);
	}



	//----------------------- PROCESS -----------------------
	@Override
	public void update(){
		if(!isValidInterval()){ recipeIndex = -1; }
		fuelHandler(input.getStackInSlot(FUEL_SLOT));
		redstoneHandler(REDSTONE_SLOT, this.getCookTimeMax());

		if(!worldObj.isRemote){
			emptyContainer(SOLVENT_SLOT, inputTank);

			if(isValidInterval()){
				if(canSynthesize()){execute();}
			}
			this.markDirtyClient();
		}
	}

	public boolean canSynthesize(){
		return activation
			&& this.getPower() >= this.getCookTimeMax()
			&& this.getRedstone() >= this.getCookTimeMax()
			&& ItemStack.areItemsEqual(getRecipe().getInput(), input.getStackInSlot(INPUT_SLOT))
			&& (ItemStack.areItemsEqual(getRecipe().getSolute(), input.getStackInSlot(SOLUTE_SLOT)) || canSoluteOredict(input.getStackInSlot(SOLUTE_SLOT), SOLUTE_SLOT))
			&& (inputTank.getFluid() != null && inputTank.getFluid().containsFluid(getRecipe().getSolvent()) && inputTank.getFluidAmount() >= getRecipe().getSolvent().amount)
			&& ((output.getStackInSlot(OUTPUT_SLOT) == null) || (output.getStackInSlot(OUTPUT_SLOT) != null && ItemStack.areItemsEqual(getRecipe().getOutput(), output.getStackInSlot(OUTPUT_SLOT)) && output.getStackInSlot(OUTPUT_SLOT).stackSize < output.getStackInSlot(OUTPUT_SLOT).getMaxStackSize() ));
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
		inputTank.getFluid().amount -= getRecipe().getSolvent().amount;
		if(inputTank.getFluid().amount <= 0){inputTank.setFluid(null);}
		ItemStack recipeOutput = getRecipe().getOutput();
		output.setOrStack(OUTPUT_SLOT, recipeOutput);
		input.decrementSlot(INPUT_SLOT);
		input.decrementSlot(SOLUTE_SLOT);
	}

}