package com.globbypotato.rockhounding_surface.compat.jei.camposter;

import javax.annotation.Nonnull;

import com.globbypotato.rockhounding_surface.compat.jei.RHRecipeUID;
import com.globbypotato.rockhounding_surface.machines.recipe.CompostBinRecipe;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;

public class ComposterRecipeHandler implements IRecipeHandler<ComposterRecipeWrapper> {

	@Nonnull
	@Override
	public Class<ComposterRecipeWrapper> getRecipeClass() {
		return ComposterRecipeWrapper.class;
	}

	@Nonnull
	@Override
	public String getRecipeCategoryUid() {
		return RHRecipeUID.COMPOSTER;
	}

	@Nonnull
	@Override
	public String getRecipeCategoryUid(@Nonnull ComposterRecipeWrapper recipe) {
		return RHRecipeUID.COMPOSTER;
	}

	@Nonnull
	@Override
	public IRecipeWrapper getRecipeWrapper(@Nonnull ComposterRecipeWrapper recipe) {
		return recipe;
	}

	@Override
	public boolean isRecipeValid(@Nonnull ComposterRecipeWrapper wrapper) {
		CompostBinRecipe recipe = wrapper.getRecipe();
		if (recipe.getInput() == null) {
			return false;
		}
		return true;
	}
}