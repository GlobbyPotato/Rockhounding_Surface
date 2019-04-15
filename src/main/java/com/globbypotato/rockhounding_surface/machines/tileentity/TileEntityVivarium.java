package com.globbypotato.rockhounding_surface.machines.tileentity;

import java.util.ArrayList;

import com.globbypotato.rockhounding_core.machines.tileentity.MachineStackHandler;
import com.globbypotato.rockhounding_core.machines.tileentity.TileEntityInv;
import com.globbypotato.rockhounding_core.machines.tileentity.WrappedItemHandler;
import com.globbypotato.rockhounding_core.machines.tileentity.WrappedItemHandler.WriteMode;
import com.globbypotato.rockhounding_core.utils.CoreUtils;
import com.globbypotato.rockhounding_surface.machines.recipe.MachineRecipes;
import com.globbypotato.rockhounding_surface.machines.recipe.VivariumRecipe;
import com.globbypotato.rockhounding_surface.utils.BaseRecipes;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class TileEntityVivarium extends TileEntityInv {

	public static int inputSlots = 1;
	public static int outputSlots = 1;

	public TileEntityVivarium(){
		super(inputSlots, outputSlots, 0, 0);
		this.input =  new MachineStackHandler(inputSlots,this){
			@Override
			public ItemStack insertItem(int slot, ItemStack insertingStack, boolean simulate){
				if(slot == INPUT_SLOT && isValidInput(insertingStack)){
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



	//----------------------- HANDLER -----------------------
	@Override
	public int getGUIHeight() {
		return BaseRecipes.HEIGHT;
	}

	public String getName() {
		return "vivarium";
	}



	//----------------------- CUSTOM -----------------------
	public boolean produceChance(){
		return rand.nextInt(getProduce()) == 0;
	}

	public boolean consumeChance(){
		return rand.nextInt(getConsume()) == 0;
	}



	//----------------------- RECIPE -----------------------
	public ArrayList<VivariumRecipe> recipeList(){
		return MachineRecipes.vivariumRecipes;
	}

	public VivariumRecipe getRecipeList(int x){
		return recipeList().get(x);
	}

	public boolean isValidInput(ItemStack stack) {
		if(!stack.isEmpty()){
			for(VivariumRecipe recipe: recipeList()){
				if(recipe.getType()){
					ArrayList<Integer> inputOreIDs = CoreUtils.intArrayToList(OreDictionary.getOreIDs(stack));
					if(!inputOreIDs.isEmpty()){
						if(inputOreIDs.contains(OreDictionary.getOreID(recipe.getOredict()))){
							return true;
						}
					}
				}else{
					if(recipe.getInput().isItemEqual(stack)){
						return true;
					}
				}
			}
		}
		return false;
	}

	public VivariumRecipe getCurrentRecipe(){
		if(!inputSlot().isEmpty()){
			for(int x = 0; x < recipeList().size(); x++){
				if(getRecipeList(x).getType()){
					ArrayList<Integer> inputOreIDs = CoreUtils.intArrayToList(OreDictionary.getOreIDs(inputSlot()));
					if(inputOreIDs.contains(OreDictionary.getOreID(getRecipeList(x).getOredict()))){
						return getRecipeList(x);
					}
				}else{
					if(getRecipeList(x).getInput().isItemEqual(inputSlot())){
						return getRecipeList(x);
					}
				}
			}
		}
		return null;
	}

	public boolean isValidRecipe(){
		return getCurrentRecipe() != null;
	}

	public ItemStack recipeOutput(){
		return isValidRecipe() ? getCurrentRecipe().getOutput() : ItemStack.EMPTY;
	}

	public int getConsume(){
		return isValidRecipe() ? getCurrentRecipe().getConsumeRate() : 1;
	}

	public int getProduce(){
		return isValidRecipe() ? getCurrentRecipe().getProduceRate() : 1;
	}



	//--------------------- PROCESS ---------------------
	@Override
	public void update() {
		if(!this.world.isRemote){
			if(canBreed()){
				breed();
			}
		}
	}

	private boolean canBreed() {
		return isValidRecipe()
			&& produceChance()
			&& this.output.canSetOrStack(this.output.getStackInSlot(OUTPUT_SLOT), recipeOutput());
	}

	private void breed() {
		this.output.setOrStack(OUTPUT_SLOT, recipeOutput());
		if(consumeChance()){
			this.input.decrementSlot(INPUT_SLOT);
		}
		this.markDirtyClient();
	}

}