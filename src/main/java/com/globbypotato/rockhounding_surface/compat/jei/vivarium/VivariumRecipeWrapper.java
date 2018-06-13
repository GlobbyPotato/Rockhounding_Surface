package com.globbypotato.rockhounding_surface.compat.jei.vivarium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;

import com.globbypotato.rockhounding_surface.compat.jei.RHRecipeWrapper;
import com.globbypotato.rockhounding_surface.machines.recipe.MachineRecipes;
import com.globbypotato.rockhounding_surface.machines.recipe.VivariumRecipe;
import com.globbypotato.rockhounding_surface.utils.BaseRecipes;

import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.item.ItemStack;

public class VivariumRecipeWrapper extends RHRecipeWrapper<VivariumRecipe> {
	
	public VivariumRecipeWrapper(@Nonnull VivariumRecipe recipe) {
		super(recipe);
	}

	public static List<VivariumRecipeWrapper> getRecipes() {
		List<VivariumRecipeWrapper> recipes = new ArrayList<>();
		for (VivariumRecipe recipe : MachineRecipes.vivariumRecipes) {
			if(!recipe.getInput().isEmpty()){
				recipes.add(new VivariumRecipeWrapper(recipe));
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