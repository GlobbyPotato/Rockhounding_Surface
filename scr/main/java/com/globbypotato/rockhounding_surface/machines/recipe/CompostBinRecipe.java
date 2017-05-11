package com.globbypotato.rockhounding_surface.machines.recipe;

import net.minecraft.item.ItemStack;

public class CompostBinRecipe {

	private ItemStack input;
	private boolean canOredict;

	public CompostBinRecipe(ItemStack input, boolean canOredict){
		this.input = input;
		this.canOredict = canOredict;
	}

	public ItemStack getInput(){
		return this.input.copy();
	}

	public boolean getOredicts(){
		return this.canOredict;
	}
}