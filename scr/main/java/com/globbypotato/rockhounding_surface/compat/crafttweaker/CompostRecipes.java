package com.globbypotato.rockhounding_surface.compat.crafttweaker;

import com.globbypotato.rockhounding_surface.compat.jei.camposter.ComposterRecipeWrapper;
import com.globbypotato.rockhounding_surface.machines.recipe.CompostBinRecipe;
import com.globbypotato.rockhounding_surface.machines.recipe.MachineRecipes;

import minetweaker.IUndoableAction;
import minetweaker.MineTweakerAPI;
import minetweaker.api.item.IItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.rockhounding_surface.CompostBin")
public class CompostRecipes {
	private static String name = "Compost Bin Recipe";

    @ZenMethod
    public static void add(IItemStack input, boolean oredict) {
        if(input == null) {MineTweakerAPI.logError(name + ": Invalid recipe."); return;}
        MineTweakerAPI.apply(new AddToCompost(new CompostBinRecipe(CTSupport.toStack(input), oredict)));
    }
		    private static class AddToCompost implements IUndoableAction {
		    	private CompostBinRecipe recipe;
		    	public AddToCompost(CompostBinRecipe recipe){
		          super();
		          this.recipe = recipe;
		    	}
		    	@Override
		    	public void apply() {
		    		MachineRecipes.compostRecipes.add(this.recipe);
		    		MineTweakerAPI.getIjeiRecipeRegistry().addRecipe(new ComposterRecipeWrapper(this.recipe));
		    	}
		    	@Override
		    	public void undo() {
		    		for(CompostBinRecipe recipe : MachineRecipes.compostRecipes){
		    			if(recipe.equals(this.recipe)){
		    				MineTweakerAPI.getIjeiRecipeRegistry().removeRecipe(new ComposterRecipeWrapper(recipe));
		    				MachineRecipes.compostRecipes.remove(recipe);	
	                        break;
		    			}
		    		}
		    	}
		    	@Override
		    	public boolean canUndo() {return true;}
		    	@Override
		    	public String describe() {return null;}
		    	@Override
		    	public String describeUndo() {return null;}
		    	@Override
		    	public Object getOverrideKey() {return null;}
		    }

}