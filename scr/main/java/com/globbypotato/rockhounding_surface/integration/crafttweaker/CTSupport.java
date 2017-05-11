package com.globbypotato.rockhounding_surface.integration.crafttweaker;

import minetweaker.MineTweakerAPI;
import minetweaker.api.item.IItemStack;
import minetweaker.api.liquid.ILiquidStack;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Loader;

public class CTSupport {

	public static void init() {
		if (Loader.isModLoaded("MineTweaker3")) {
			CTSupport.loadSupport();
	    }
	}

    public static void loadSupport() {
        MineTweakerAPI.registerClass(IncubatorRecipes.class);
        MineTweakerAPI.registerClass(CompostRecipes.class);
    }

    public static ItemStack toStack(IItemStack iStack){
    	if (iStack == null) {
    		return null;
    	}
    	Object internal = iStack.getInternal();
    	if (!(internal instanceof ItemStack)) {
    		MineTweakerAPI.logError("Not a valid item stack: " + iStack);
    	}    	
    	return (ItemStack)internal;
    }

    public static FluidStack toFluid(ILiquidStack iStack){
    	if (iStack == null) {
    		return null;
    	}
    	Object internal = iStack.getInternal();
    	if (!(internal instanceof FluidStack)) {
    		MineTweakerAPI.logError("Not a valid item fluidstack: " + iStack);
    	}    	
    	return FluidRegistry.getFluidStack(iStack.getName(), iStack.getAmount());
    }

}