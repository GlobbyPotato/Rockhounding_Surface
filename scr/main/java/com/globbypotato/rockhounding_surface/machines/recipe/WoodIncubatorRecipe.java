package com.globbypotato.rockhounding_surface.machines.recipe;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class WoodIncubatorRecipe {

	private final ItemStack input;
	private final ItemStack solute;
	private final ItemStack output;
	private final FluidStack solvent;
	private final boolean oredict;

	public WoodIncubatorRecipe(ItemStack input, ItemStack solute, boolean oredict, FluidStack solvent, ItemStack output){
		this.input = input;
		this.solute = solute;
		this.oredict = oredict;
		this.solvent = solvent;
		this.output = output;
	}

	public ItemStack getInput(){
		return this.input.copy();
	}

	public ItemStack getSolute(){
		return this.solute.copy();
	}

	public FluidStack getSolvent(){
		return this.solvent;
	}

	public ItemStack getOutput(){
		return this.output.copy();
	}
	
	public boolean canOredict(){
		return this.oredict;
	}
}