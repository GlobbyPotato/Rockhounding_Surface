package com.globbypotato.rockhounding_surface.machines.recipe;

import net.minecraft.item.ItemStack;

public class VivariumRecipe {

	private ItemStack input, output;
	private int produceRate, consumeRate;
	private String oredict;
	private boolean type;

	public VivariumRecipe(ItemStack input, String oredict, boolean type, ItemStack output, int produceRate, int consumeRate){
		this.input = input;
		this.output = output;
		this.produceRate = produceRate;
		this.consumeRate = consumeRate;
		this.oredict = oredict;
		this.type = type;
	}

	public VivariumRecipe(ItemStack input, ItemStack output, int produceRate, int consumeRate){
		this(input, "", false, output, produceRate, consumeRate);
	}

	public VivariumRecipe(String oredict, ItemStack output, int produceRate, int consumeRate){
		this(ItemStack.EMPTY, oredict, true, output, produceRate, consumeRate);
	}

	public ItemStack getInput(){
		if(!this.input.isEmpty()) return this.input.copy();
		return ItemStack.EMPTY;
	}

	public String getOredict(){
		return this.oredict;
	}

	public boolean getType(){
		return this.type;
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