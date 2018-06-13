package com.globbypotato.rockhounding_surface.machines.recipe;

import net.minecraft.item.ItemStack;

public class TruffleAuctionRecipe {

	private ItemStack input;
	private ItemStack output;

	public TruffleAuctionRecipe(ItemStack input, ItemStack output){
		this.input = input;
		this.output = output;
	}

	public ItemStack getInput(){
		if(!this.input.isEmpty()) return this.input.copy();
		return ItemStack.EMPTY;
	}

	public ItemStack getOutput(){
		if(!this.output.isEmpty()) return this.output.copy();
		return ItemStack.EMPTY;
	}
}