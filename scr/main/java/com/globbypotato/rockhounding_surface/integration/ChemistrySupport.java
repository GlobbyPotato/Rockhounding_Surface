package com.globbypotato.rockhounding_surface.integration;

import java.util.Arrays;

import com.globbypotato.rockhounding_surface.ModItems;
import com.globbypotato.rockhounding_surface.enums.EnumAgingItems;
import com.globbypotato.rockhounding_surface.fluids.ModFluids;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import rockhounding.api.machines.IReciper;

public class ChemistrySupport extends IReciper {
	public static void init(){
		if(SupportUtils.rhChemistryLoaded()){
			sendToOven(SupportUtils.saltCompound(), false, new FluidStack(FluidRegistry.WATER, 1000), null, new FluidStack(ModFluids.ACIDIC_WATER, 1000));
			sendToOven(new ItemStack(ModItems.agingItems, 1, EnumAgingItems.ORGANIC_COMPOST.ordinal()), false, new FluidStack(ModFluids.ACIDIC_WATER, 1000), null, new FluidStack(ModFluids.AGING_BATH, 1000));
			sendToOven(new ItemStack(ModItems.agingItems, 1, EnumAgingItems.CONTAMINATING_COMPOST.ordinal()), false, new FluidStack(ModFluids.AGING_BATH, 1000), null, new FluidStack(ModFluids.CASTING_BATH, 1000));
			sendToExtractor("Sulfate", new ItemStack(ModItems.gypsumItems, 1, 0), Arrays.asList("calcium", "sulfur"), Arrays.asList(23,19));
		}
	}
}