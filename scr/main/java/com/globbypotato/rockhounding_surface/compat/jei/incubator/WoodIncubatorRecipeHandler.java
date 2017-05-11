package com.globbypotato.rockhounding_surface.compat.jei.incubator;

import javax.annotation.Nonnull;

import com.globbypotato.rockhounding_surface.compat.jei.RHRecipeUID;
import com.globbypotato.rockhounding_surface.machines.recipe.WoodIncubatorRecipe;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;

public class WoodIncubatorRecipeHandler implements IRecipeHandler<WoodIncubatorRecipeWrapper> {

	@Nonnull
	@Override
	public Class<WoodIncubatorRecipeWrapper> getRecipeClass() {
		return WoodIncubatorRecipeWrapper.class;
	}

	@Nonnull
	@Override
	public String getRecipeCategoryUid() {
		return RHRecipeUID.WOOD_INCUBATOR;
	}

	@Nonnull
	@Override
	public String getRecipeCategoryUid(@Nonnull WoodIncubatorRecipeWrapper recipe) {
		return RHRecipeUID.WOOD_INCUBATOR;
	}

	@Nonnull
	@Override
	public IRecipeWrapper getRecipeWrapper(@Nonnull WoodIncubatorRecipeWrapper recipe) {
		return recipe;
	}

	@Override
	public boolean isRecipeValid(@Nonnull WoodIncubatorRecipeWrapper wrapper) {
		WoodIncubatorRecipe recipe = wrapper.getRecipe();
		if (recipe.getSolute() == null 
		|| recipe.getInput() == null 
		|| recipe.getSolvent() == null 
		|| recipe.getOutput() == null) {
			return false;
		}
		return true;
	}
}