package com.globbypotato.rockhounding_surface.compat.jei.composter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;

import com.globbypotato.rockhounding_surface.compat.jei.RHRecipeWrapper;
import com.globbypotato.rockhounding_surface.machines.recipe.CompostBinRecipe;
import com.globbypotato.rockhounding_surface.machines.recipe.MachineRecipes;
import com.globbypotato.rockhounding_surface.utils.BaseRecipes;

import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.item.ItemStack;

public class ComposterRecipeWrapper extends RHRecipeWrapper<CompostBinRecipe> {
	
	public ComposterRecipeWrapper(@Nonnull CompostBinRecipe recipe) {
		super(recipe);
	}

	public static List<ComposterRecipeWrapper> getRecipes() {
		List<ComposterRecipeWrapper> recipes = new ArrayList<>();
		for (CompostBinRecipe recipe : MachineRecipes.compostRecipes) {
			if(!recipe.getInput().isEmpty()){
				recipes.add(new ComposterRecipeWrapper(recipe));
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
		return Collections.singletonList(BaseRecipes.compost.copy());
	}

	@Override
	public void getIngredients(IIngredients ingredients) {
        ingredients.setInputs(ItemStack.class, getInputs());
        ingredients.setOutputs(ItemStack.class, getOutputs());
	}
}