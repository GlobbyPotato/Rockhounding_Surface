package com.globbypotato.rockhounding_surface.compat.crafttweaker;

import com.globbypotato.rockhounding_surface.compat.jei.incubator.WoodIncubatorRecipeWrapper;
import com.globbypotato.rockhounding_surface.machines.recipe.MachineRecipes;
import com.globbypotato.rockhounding_surface.machines.recipe.WoodIncubatorRecipe;

import minetweaker.IUndoableAction;
import minetweaker.MineTweakerAPI;
import minetweaker.api.item.IItemStack;
import minetweaker.api.liquid.ILiquidStack;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.rockhounding_surface.WoodIncubator")
public class IncubatorRecipes extends CTSupport{
	private static String name = "Wood Incubator Recipe";

    @ZenMethod
    public static void add(IItemStack input, IItemStack solute, boolean oredict, ILiquidStack solvent, int solventAmount, IItemStack output) {
        if(solute == null || solvent == null || output == null) {MineTweakerAPI.logError(name + ": Invalid recipe."); return;}
        FluidStack solventStack = new FluidStack(toFluid(solvent).getFluid(), solventAmount);
        MineTweakerAPI.apply(new AddToIncubator(new WoodIncubatorRecipe(toStack(input), toStack(solute), oredict, solventStack, toStack(output))));
    }
		    private static class AddToIncubator implements IUndoableAction {
		    	private WoodIncubatorRecipe recipe;
		    	public AddToIncubator(WoodIncubatorRecipe recipe){
		          super();
		          this.recipe = recipe;
		    	}
		    	@Override
		    	public void apply() {
		    		MachineRecipes.woodIncubatorRecipes.add(this.recipe);
		    		MineTweakerAPI.getIjeiRecipeRegistry().addRecipe(new WoodIncubatorRecipeWrapper(this.recipe));
		    	}
		    	@Override
		    	public void undo() {
		    		for(WoodIncubatorRecipe recipe : MachineRecipes.woodIncubatorRecipes){
		    			if(recipe.equals(this.recipe)){
		    				MineTweakerAPI.getIjeiRecipeRegistry().removeRecipe(new WoodIncubatorRecipeWrapper(recipe));
		    				MachineRecipes.woodIncubatorRecipes.remove(recipe);	
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


    @ZenMethod
    public static void remove(IItemStack output) {
        if(output == null) {MineTweakerAPI.logError(name + ": Invalid recipe."); return;}
        MineTweakerAPI.apply(new RemoveFromIncubator(toStack(output)));    
    }
		    private static class RemoveFromIncubator implements IUndoableAction {
		    	private ItemStack output;
		    	private WoodIncubatorRecipe undoRecipe;
		    	public RemoveFromIncubator(ItemStack output) {
		    		super();
		    		this.output = output;
		    	}
		    	@Override
		    	public void apply() {
		    		for(WoodIncubatorRecipe recipe : MachineRecipes.woodIncubatorRecipes){
		    			if(this.output != null && recipe.getOutput().isItemEqual(this.output)){
				    		this.undoRecipe = recipe;
		    				MineTweakerAPI.getIjeiRecipeRegistry().removeRecipe(new WoodIncubatorRecipeWrapper(recipe));
		    				MachineRecipes.woodIncubatorRecipes.remove(recipe);	
	                        break;
		    			}
		    		}
		    	}
		    	@Override
		    	public void undo() {
		    		if(this.undoRecipe != null){
		    			MachineRecipes.woodIncubatorRecipes.add(this.undoRecipe);
			    		MineTweakerAPI.getIjeiRecipeRegistry().addRecipe(new WoodIncubatorRecipeWrapper(this.undoRecipe));
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