package com.globbypotato.rockhounding_surface.compat.jei.vivarium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;

import com.globbypotato.rockhounding_surface.compat.jei.RHRecipeWrapper;
import com.globbypotato.rockhounding_surface.machines.recipe.MachineRecipes;
import com.globbypotato.rockhounding_surface.machines.recipe.VivariumRecipe;

import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class VivariumRecipeWrapper extends RHRecipeWrapper<VivariumRecipe> {
	
	public VivariumRecipeWrapper(@Nonnull VivariumRecipe recipe) {
		super(recipe);
	}

	public static List<VivariumRecipeWrapper> getRecipes() {
		List<VivariumRecipeWrapper> recipes = new ArrayList<>();
		for (VivariumRecipe recipe : MachineRecipes.vivariumRecipes) {
			if(isValidRecipe(recipe)){
				recipes.add(new VivariumRecipeWrapper(recipe));
			}
		}
		return recipes;
	}

	private static boolean isValidRecipe(VivariumRecipe recipe){
		return ((!recipe.getType() && !recipe.getInput().isEmpty()) || (recipe.getType() && OreDictionary.getOres(recipe.getOredict()).size() > 0))
			&& recipe.getOutput() != null;
	}

	public List<ItemStack> getInputs(){
		ArrayList<ItemStack> inputs = new ArrayList<ItemStack>();
		if(getRecipe().getType()){
			inputs.addAll(OreDictionary.getOres(getRecipe().getOredict()));
		}else{
			inputs.add(getRecipe().getInput());
		}
		return inputs;
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