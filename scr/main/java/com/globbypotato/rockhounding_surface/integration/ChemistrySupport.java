package com.globbypotato.rockhounding_surface.integration;

import java.util.Arrays;

import com.globbypotato.rockhounding_surface.enums.EnumAgingItems;
import com.globbypotato.rockhounding_surface.fluids.ModFluids;
import com.globbypotato.rockhounding_surface.utils.BaseRecipes;

import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import rockhounding.api.machines.IReciper;

public class ChemistrySupport extends IReciper {
	public static void init(){
		if(SupportUtils.rhChemistryLoaded()){
			sendToOven(SupportUtils.saltCompound(), false, new FluidStack(FluidRegistry.WATER, 1000), null, new FluidStack(ModFluids.ACIDIC_WATER, 1000));
			sendToOven(BaseRecipes.aging(1, EnumAgingItems.ORGANIC_COMPOST.ordinal()), false, new FluidStack(ModFluids.ACIDIC_WATER, 1000), null, new FluidStack(ModFluids.AGING_BATH, 1000));
			sendToOven(BaseRecipes.aging(1, EnumAgingItems.CONTAMINATING_COMPOST.ordinal()), false, new FluidStack(ModFluids.AGING_BATH, 1000), null, new FluidStack(ModFluids.CASTING_BATH, 1000));
			sendToExtractor("Sulfate", BaseRecipes.gypsumDust, Arrays.asList("calcium", "sulfur"), Arrays.asList(23,19));
		}
	}
}