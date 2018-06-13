package com.globbypotato.rockhounding_surface.compat.jei.vivarium;

import javax.annotation.Nonnull;

import com.globbypotato.rockhounding_surface.compat.jei.RHRecipeCategory;
import com.globbypotato.rockhounding_surface.machines.gui.GuiVivarium;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IGuiFluidStackGroup;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class VivariumRecipeCategory extends RHRecipeCategory {

	private static final int INPUT_SLOT = 0;
	private static final int OUTPUT_SLOT = 1;
	private static final int WATER_SLOT = 2;
	private String uid;
	public VivariumRecipeCategory(IGuiHelper guiHelper, String uid) {
		super(guiHelper.createDrawable(GuiVivarium.TEXTURE_JEI, 0, 0, 54, 51), "jei." + uid + ".name");
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

		VivariumRecipeWrapper wrapper = (VivariumRecipeWrapper) recipeWrapper;

		guiFluidStacks.init(WATER_SLOT, true,  2, 39, 50, 6, 1000, false, null);
		guiFluidStacks.set(WATER_SLOT, new FluidStack(FluidRegistry.WATER, 1000));

		guiItemStacks.init(INPUT_SLOT, true, 18, 0);
		guiItemStacks.set(INPUT_SLOT, wrapper.getInputs());
		
		guiItemStacks.init(OUTPUT_SLOT, true, 18, 21);
		guiItemStacks.set(OUTPUT_SLOT, wrapper.getOutputs());

	}

}