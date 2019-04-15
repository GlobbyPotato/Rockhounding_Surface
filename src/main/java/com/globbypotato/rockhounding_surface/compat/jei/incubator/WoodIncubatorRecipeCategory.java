package com.globbypotato.rockhounding_surface.compat.jei.incubator;

import javax.annotation.Nonnull;

import com.globbypotato.rockhounding_surface.compat.jei.RHRecipeCategory;
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
	private String uid;

	private final static ResourceLocation guiTexture = GuiWoodIncubator.TEXTURE_REF;

	public WoodIncubatorRecipeCategory(IGuiHelper guiHelper, String uid) {
		super(guiHelper.createDrawable(guiTexture, 34, 51, 108, 44), "jei." + uid + ".name");
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
		IGuiFluidStackGroup guiFluidStacks = recipeLayout.getFluidStacks();

		WoodIncubatorRecipeWrapper wrapper = (WoodIncubatorRecipeWrapper) recipeWrapper;
		
		guiFluidStacks.init(TANK_SLOT, true,  21, 21, 66, 22, 500, false, null);
		guiFluidStacks.set(TANK_SLOT, wrapper.getFluidInputs().get(0));
		
		guiItemStacks.init(INPUT_SLOT, true, 0, 23);
		guiItemStacks.set(INPUT_SLOT, wrapper.getInputs().get(0));
	
		guiItemStacks.init(SOLUTE_SLOT, true, 20, 0);
		guiItemStacks.set(SOLUTE_SLOT, wrapper.getSolutes().get(0));

		guiItemStacks.init(OUTPUT_SLOT, false, 90, 23);
		guiItemStacks.set(OUTPUT_SLOT, wrapper.getOutputs().get(0));
	}

}