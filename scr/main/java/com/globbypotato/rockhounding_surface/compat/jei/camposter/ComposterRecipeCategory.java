package com.globbypotato.rockhounding_surface.compat.jei.camposter;

import javax.annotation.Nonnull;

import com.globbypotato.rockhounding_surface.compat.jei.RHRecipeCategory;
import com.globbypotato.rockhounding_surface.compat.jei.RHRecipeUID;
import com.globbypotato.rockhounding_surface.machines.gui.GuiCompostBin;
import com.globbypotato.rockhounding_surface.utils.BaseRecipes;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IGuiFluidStackGroup;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.util.ResourceLocation;

public class ComposterRecipeCategory extends RHRecipeCategory {

	private static final int INPUT_SLOT = 0;
	private static final int OUTPUT_SLOT = 1;

	private final static ResourceLocation guiTexture = GuiCompostBin.TEXTURE_REF;

	public ComposterRecipeCategory(IGuiHelper guiHelper) {
		super(guiHelper.createDrawable(guiTexture, 29, 18, 117, 52), "jei.composter.name");
	}

	@Nonnull
	@Override
	public String getUid() {
		return RHRecipeUID.COMPOSTER;
	}

	@Override
	public void setRecipe(@Nonnull IRecipeLayout recipeLayout, @Nonnull IRecipeWrapper recipeWrapper) {
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
		IGuiFluidStackGroup guiFluidStacks = recipeLayout.getFluidStacks();

		ComposterRecipeWrapper wrapper = (ComposterRecipeWrapper) recipeWrapper;
		
		guiItemStacks.init(INPUT_SLOT, true, 0, 0);
		guiItemStacks.set(INPUT_SLOT, wrapper.getInputs().get(0));

		guiItemStacks.init(OUTPUT_SLOT, true, 100, 34);
		guiItemStacks.set(OUTPUT_SLOT, BaseRecipes.compost);
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients) {
		setRecipe(recipeLayout,recipeWrapper);
	}
}