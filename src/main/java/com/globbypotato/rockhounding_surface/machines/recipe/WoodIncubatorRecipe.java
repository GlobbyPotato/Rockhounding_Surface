package com.globbypotato.rockhounding_surface.machines.recipe;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class WoodIncubatorRecipe {

	private final ItemStack input;
	private final ItemStack solute;
	private final ItemStack output;
	private final FluidStack solvent;

	public WoodIncubatorRecipe(ItemStack input, ItemStack solute, FluidStack solvent, ItemStack output){
		this.input = input;
		this.solute = solute;
		this.solvent = solvent;
		this.output = output;
	}

	public ItemStack getInput(){
		if(!this.input.isEmpty()) return this.input.copy();
		return ItemStack.EMPTY;
	}

	public ItemStack getSolute(){
		if(!this.solute.isEmpty()) return this.solute.copy();
		return ItemStack.EMPTY;
	}

	public FluidStack getSolvent(){
		if(this.solvent != null) return this.solvent.copy();
		return null;
	}

	public ItemStack getOutput(){
		if(!this.output.isEmpty()) return this.output.copy();
		return ItemStack.EMPTY;
	}
	
}