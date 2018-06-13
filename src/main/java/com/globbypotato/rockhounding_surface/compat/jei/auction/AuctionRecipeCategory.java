package com.globbypotato.rockhounding_surface.compat.jei.auction;

import javax.annotation.Nonnull;

import com.globbypotato.rockhounding_surface.compat.jei.RHRecipeCategory;
import com.globbypotato.rockhounding_surface.machines.gui.GuiTruffleAuction;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;

public class AuctionRecipeCategory extends RHRecipeCategory {

	private static final int INPUT_SLOT = 0;
	private static final int OUTPUT_SLOT = 1;
	private String uid;

	public AuctionRecipeCategory(IGuiHelper guiHelper, String uid) {
		super(guiHelper.createDrawable(GuiTruffleAuction.TEXTURE_JEI, 0, 0, 64, 57), "jei." + uid + ".name");
		this.uid = uid;
	}

	@Nonnull
	@Override
	public String getUid() {
		return this.uid;
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

		AuctionRecipeWrapper wrapper = (AuctionRecipeWrapper) recipeWrapper;

		guiItemStacks.init(INPUT_SLOT, true, 3, 19);
		guiItemStacks.set(INPUT_SLOT, wrapper.getInputs());

		guiItemStacks.init(OUTPUT_SLOT, true, 44, 26);
		guiItemStacks.set(OUTPUT_SLOT, wrapper.getOutputs());
	}

}