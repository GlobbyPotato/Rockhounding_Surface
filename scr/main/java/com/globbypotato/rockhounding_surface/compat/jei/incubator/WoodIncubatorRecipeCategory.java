package com.globbypotato.rockhounding_surface.compat.jei.incubator;

import javax.annotation.Nonnull;

import com.globbypotato.rockhounding_surface.compat.jei.RHRecipeCategory;
import com.globbypotato.rockhounding_surface.compat.jei.RHRecipeUID;
import com.globbypotato.rockhounding_surface.machines.gui.GuiWoodIncubator;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IGuiFluidStackGroup;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.util.ResourceLocation;

public class WoodIncubatorRecipeCategory extends RHRecipeCategory {

	private static final int TANK_SLOT = 0;
	private static final int INPUT_SLOT = 1;
	private static final int SOLUTE_SLOT = 2;
	private static final int OUTPUT_SLOT = 3;

	private final static ResourceLocation guiTexture = GuiWoodIncubator.TEXTURE_REF;

	public WoodIncubatorRecipeCategory(IGuiHelper guiHelper) {
		super(guiHelper.createDrawable(guiTexture, 60, 32, 70, 70), "jei.wood_incubator.name");
	}

	@Nonnull
	@Override
	public String getUid() {
		return RHRecipeUID.WOOD_INCUBATOR;
	}

	@Override
	public void setRecipe(@Nonnull IRecipeLayout recipeLayout, @Nonnull IRecipeWrapper recipeWrapper) {
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
		IGuiFluidStackGroup guiFluidStacks = recipeLayout.getFluidStacks();

		WoodIncubatorRecipeWrapper wrapper = (WoodIncubatorRecipeWrapper) recipeWrapper;
		
		guiFluidStacks.init(TANK_SLOT, true,  24, 2, 20, 65, 2000, false, null);
		guiFluidStacks.set(TANK_SLOT, wrapper.getFluidInputs().get(0));
		
		guiItemStacks.init(INPUT_SLOT, true, 49, 1);
		guiItemStacks.set(INPUT_SLOT, wrapper.getInputs().get(0));
	
		guiItemStacks.init(SOLUTE_SLOT, true, 1, 1);
		guiItemStacks.set(SOLUTE_SLOT, wrapper.getSolutes().get(0));

		guiItemStacks.init(OUTPUT_SLOT, true, 49, 50);
		guiItemStacks.set(OUTPUT_SLOT, wrapper.getOutputs().get(0));
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients) {
		setRecipe(recipeLayout,recipeWrapper);
	}
}