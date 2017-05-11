package com.globbypotato.rockhounding_surface.compat.jei;

import java.util.List;

import javax.annotation.Nonnull;

import mezz.jei.api.recipe.BlankRecipeWrapper;
import net.minecraft.item.ItemStack;

public abstract class RHRecipeWrapper<R> extends BlankRecipeWrapper {

	@Nonnull
	private final R recipe;

	public RHRecipeWrapper(@Nonnull R recipe) {
		this.recipe = recipe;
	}

	@Nonnull
	public R getRecipe() {
		return recipe;
	}

	public List<ItemStack> getSolutes() {
		return null;
	}
}