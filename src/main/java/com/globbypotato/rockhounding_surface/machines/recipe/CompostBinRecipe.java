package com.globbypotato.rockhounding_surface.machines.recipe;

import net.minecraft.item.ItemStack;

public class CompostBinRecipe {

	private ItemStack input;
	private int type, value;

	public CompostBinRecipe(ItemStack input, int type, int value){
		this.input = input;
		this.type = type;
		this.value = value;
	}

	public ItemStack getInput(){
		if(!this.input.isEmpty()) return this.input.copy();
		return ItemStack.EMPTY;
	}

	public int getType(){
		return this.type;
	}

	public int getValue(){
		return this.value;
	}

}