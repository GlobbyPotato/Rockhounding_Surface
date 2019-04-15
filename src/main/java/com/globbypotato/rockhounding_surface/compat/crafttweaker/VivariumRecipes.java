package com.globbypotato.rockhounding_surface.compat.crafttweaker;

import java.util.ArrayList;

import com.globbypotato.rockhounding_surface.machines.recipe.MachineRecipes;
import com.globbypotato.rockhounding_surface.machines.recipe.VivariumRecipe;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.api.item.IItemStack;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.rockhounding_surface.Vivarium")
public class VivariumRecipes extends CTSupport{
	public static String name = "Vivarium";
	public static ArrayList<VivariumRecipe> recipeList = MachineRecipes.vivariumRecipes;

    @ZenMethod
    public static void add(IItemStack input, IItemStack output, int produceRate, int consumeRate) {
        if(input == null || output == null) {error(name); return;}
        CraftTweakerAPI.apply(new AddToVivarium(new VivariumRecipe(toStack(input), toStack(output), produceRate, consumeRate)));
    }
    @ZenMethod
    public static void add(String oredict, IItemStack output, int produceRate, int consumeRate) {
        if(output == null) {error(name); return;}
        CraftTweakerAPI.apply(new AddToVivarium(new VivariumRecipe(oredict, toStack(output), produceRate, consumeRate)));
    }
		    private static class AddToVivarium implements IAction {
		    	private VivariumRecipe recipe;
		    	public AddToVivarium(VivariumRecipe recipe){
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


    @ZenMethod
    public static void remove(IItemStack output) {
        if(output == null) {error(name); return;}
        CraftTweakerAPI.apply(new RemoveFromVivarium(toStack(output)));    
    }
		    private static class RemoveFromVivarium implements IAction {
		    	private ItemStack output;
		    	public RemoveFromVivarium(ItemStack output) {
		    		super();
		    		this.output = output;
		    	}
		    	@Override
		    	public void apply() {
		    		for(VivariumRecipe recipe : recipeList){
		    			if(!this.output.isEmpty() && recipe.getOutput().isItemEqual(this.output)){
		    				recipeList.remove(recipe);	
	                        break;
		    			}
		    		}
		    	}
		    	@Override
		    	public String describe() {
		    		return removeCaption(name);
		    	}
		    }
}