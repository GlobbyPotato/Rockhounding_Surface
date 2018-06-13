package com.globbypotato.rockhounding_surface.compat.jei.auction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;

import com.globbypotato.rockhounding_surface.compat.jei.RHRecipeWrapper;
import com.globbypotato.rockhounding_surface.machines.recipe.MachineRecipes;
import com.globbypotato.rockhounding_surface.machines.recipe.TruffleAuctionRecipe;

import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.item.ItemStack;

public class AuctionRecipeWrapper extends RHRecipeWrapper<TruffleAuctionRecipe> {
	
	public AuctionRecipeWrapper(@Nonnull TruffleAuctionRecipe recipe) {
		super(recipe);
	}

	public static List<AuctionRecipeWrapper> getRecipes() {
		List<AuctionRecipeWrapper> recipes = new ArrayList<>();
		for (TruffleAuctionRecipe recipe : MachineRecipes.auctionRecipes) {
			if(!recipe.getInput().isEmpty()){
				recipes.add(new AuctionRecipeWrapper(recipe));
			}
		}
		return recipes;
	}

	@Nonnull
	public List<ItemStack> getInputs(){
		return Collections.singletonList(getRecipe().getInput());
	}

	@Nonnull
	public List<ItemStack> getOutputs(){
		return Collections.singletonList(getRecipe().getOutput());
	}

	@Override
	public void getIngredients(IIngredients ingredients) {
        ingredients.setInputs(ItemStack.class, getInputs());
        ingredients.setOutputs(ItemStack.class, getOutputs());
	}
}