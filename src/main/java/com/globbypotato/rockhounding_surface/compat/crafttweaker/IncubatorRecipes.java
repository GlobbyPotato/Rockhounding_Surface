package com.globbypotato.rockhounding_surface.compat.crafttweaker;

import java.util.ArrayList;

import com.globbypotato.rockhounding_surface.machines.recipe.MachineRecipes;
import com.globbypotato.rockhounding_surface.machines.recipe.WoodIncubatorRecipe;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.liquid.ILiquidStack;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.rockhounding_surface.WoodIncubator")
public class IncubatorRecipes extends CTSupport{
	public static String name = "Wood Incubator";
	public static ArrayList<WoodIncubatorRecipe> recipeList = MachineRecipes.woodIncubatorRecipes;

    @ZenMethod
    public static void add(IItemStack input, IItemStack solute, ILiquidStack solvent, int solventAmount, IItemStack output) {
        if(solute == null || solvent == null || output == null) {error(name); return;}
        FluidStack solventStack = new FluidStack(toFluid(solvent).getFluid(), solventAmount);
        CraftTweakerAPI.apply(new AddToIncubator(new WoodIncubatorRecipe(toStack(input), toStack(solute), solventStack, toStack(output))));
    }
		    private static class AddToIncubator implements IAction {
		    	private WoodIncubatorRecipe recipe;
		    	public AddToIncubator(WoodIncubatorRecipe recipe){
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
        CraftTweakerAPI.apply(new RemoveFromIncubator(toStack(output)));    
    }
		    private static class RemoveFromIncubator implements IAction {
		    	private ItemStack output;
		    	public RemoveFromIncubator(ItemStack output) {
		    		super();
		    		this.output = output;
		    	}
		    	@Override
		    	public void apply() {
		    		for(WoodIncubatorRecipe recipe : recipeList){
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