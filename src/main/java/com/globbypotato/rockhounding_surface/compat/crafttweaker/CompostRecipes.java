package com.globbypotato.rockhounding_surface.compat.crafttweaker;

import java.util.ArrayList;

import com.globbypotato.rockhounding_surface.enums.EnumComposting;
import com.globbypotato.rockhounding_surface.machines.recipe.CompostBinRecipe;
import com.globbypotato.rockhounding_surface.machines.recipe.MachineRecipes;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.api.item.IItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.rockhounding_surface.CompostBin")
public class CompostRecipes extends CTSupport{
	public static String name = "Compost Bin";
	public static ArrayList<CompostBinRecipe> recipeList = MachineRecipes.compostRecipes;

    @ZenMethod
    public static void add(IItemStack input, int type, int value) {
        if(input == null || type < -1 || type >= EnumComposting.size()) {error(name); return;}
        CraftTweakerAPI.apply(new AddToCompost(new CompostBinRecipe(toStack(input), type, value)));
    }
		    private static class AddToCompost implements IAction {
		    	private CompostBinRecipe recipe;
		    	public AddToCompost(CompostBinRecipe recipe){
		          super();
		          this.recipe = recipe;
		    	}
		    	@Override
		    	public void apply() {
		    		recipeList.add(this.recipe);
		    	}
		    	@Override
		    	public String describe() {
		    		return addCaption(name);
		    	}
		    }

}