package com.globbypotato.rockhounding_surface.compat.jei.camposter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;

import com.globbypotato.rockhounding_surface.compat.jei.RHRecipeWrapper;
import com.globbypotato.rockhounding_surface.handler.ModRecipes;
import com.globbypotato.rockhounding_surface.machines.recipe.CompostBinRecipe;

import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.item.ItemStack;

public class ComposterRecipeWrapper extends RHRecipeWrapper<CompostBinRecipe> {
	
	public ComposterRecipeWrapper(@Nonnull CompostBinRecipe recipe) {
		super(recipe);
	}

	public static List<ComposterRecipeWrapper> getRecipes() {
		List<ComposterRecipeWrapper> recipes = new ArrayList<>();
		for (CompostBinRecipe recipe : ModRecipes.compostRecipes) {
			recipes.add(new ComposterRecipeWrapper(recipe));
		}
		return recipes;
	}

	@Nonnull
	@Override
	public List<ItemStack> getInputs(){
		return Collections.singletonList(getRecipe().getInput());
	}

	@Override
	public void getIngredients(IIngredients ingredients) {}
}