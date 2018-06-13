package com.globbypotato.rockhounding_surface.compat.jei.incubator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;

import com.globbypotato.rockhounding_surface.compat.jei.RHRecipeWrapper;
import com.globbypotato.rockhounding_surface.machines.recipe.MachineRecipes;
import com.globbypotato.rockhounding_surface.machines.recipe.WoodIncubatorRecipe;

import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class WoodIncubatorRecipeWrapper extends RHRecipeWrapper<WoodIncubatorRecipe> {
	
	public WoodIncubatorRecipeWrapper(@Nonnull WoodIncubatorRecipe recipe) {
		super(recipe);
	}

	public static List<WoodIncubatorRecipeWrapper> getRecipes() {
		List<WoodIncubatorRecipeWrapper> recipes = new ArrayList<>();
		for (WoodIncubatorRecipe recipe : MachineRecipes.woodIncubatorRecipes) {
			if(!recipe.getInput().isEmpty() && !recipe.getSolute().isEmpty() && !recipe.getOutput().isEmpty() && recipe.getSolvent() != null){
				recipes.add(new WoodIncubatorRecipeWrapper(recipe));
			}
		}
		return recipes;
	}

	@Nonnull
	public List<ItemStack> getInputs(){
		return Collections.singletonList(getRecipe().getInput());
	}

	@Nonnull
	public List<ItemStack> getSolutes(){
		return Collections.singletonList(getRecipe().getSolute());
	}

	@Nonnull
	public List<FluidStack> getFluidInputs(){
		return Collections.singletonList(getRecipe().getSolvent());
	}

	@Nonnull
	public List<ItemStack> getOutputs(){
		return Collections.singletonList(getRecipe().getOutput());
	}

	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInputLists(ItemStack.class, Arrays.asList(getSolutes(), getInputs()));
		ingredients.setInputs(FluidStack.class, getFluidInputs());
		ingredients.setOutputs(ItemStack.class, getOutputs());
	}
}