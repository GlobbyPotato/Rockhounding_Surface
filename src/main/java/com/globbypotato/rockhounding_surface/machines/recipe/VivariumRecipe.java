package com.globbypotato.rockhounding_surface.machines.recipe;

import net.minecraft.item.ItemStack;

public class VivariumRecipe {

	private ItemStack input, output;
	private int produceRate, consumeRate;

	public VivariumRecipe(ItemStack input, ItemStack output, int produceRate, int consumeRate){
		this.input = input;
		this.output = output;
		this.produceRate = produceRate;
		this.consumeRate = consumeRate;
	}

	public ItemStack getInput(){
		if(!this.input.isEmpty()) return this.input.copy();
		return ItemStack.EMPTY;
	}

	public ItemStack getOutput(){
		if(!this.output.isEmpty()) return this.output.copy();
		return ItemStack.EMPTY;
	}

	public int getConsumeRate(){
		return this.consumeRate;
	}

	public int getProduceRate(){
		return this.produceRate;
	}

}