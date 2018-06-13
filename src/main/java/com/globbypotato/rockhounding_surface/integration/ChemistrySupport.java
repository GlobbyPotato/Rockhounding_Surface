package com.globbypotato.rockhounding_surface.integration;

import java.util.Arrays;

import com.globbypotato.rockhounding_core.utils.CoreBasics;
import com.globbypotato.rockhounding_core.utils.CoreUtils;
import com.globbypotato.rockhounding_surface.enums.EnumAgingItems;
import com.globbypotato.rockhounding_surface.enums.EnumGypsumItems;
import com.globbypotato.rockhounding_surface.fluids.ModFluids;
import com.globbypotato.rockhounding_surface.utils.BaseRecipes;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import rockhounding.api.IReciper;
import rockhounding.api.IReciperBase;

public class ChemistrySupport extends IReciper {
	public static void init(){
		if(IReciperBase.chemistryLoaded()){
			sendToLabOven(	null,
							CoreUtils.getModdedStack(chemicals(), 1, 6), 
							ItemStack.EMPTY, 
							CoreBasics.waterStack(1000), 
							null, 
							new FluidStack(ModFluids.ACIDIC_WATER, 1000),
							null,
							ItemStack.EMPTY);

			sendToLabOven(	null, 	
							BaseRecipes.aging(1, EnumAgingItems.ORGANIC_COMPOST.ordinal()), 		
							ItemStack.EMPTY, 		
							new FluidStack(ModFluids.ACIDIC_WATER, 1000), 
							null,			
							new FluidStack(ModFluids.AGING_BATH, 1000),
							null,
							ItemStack.EMPTY);

			sendToLabOven(	null,
							BaseRecipes.aging(1, EnumAgingItems.CONTAMINATING_COMPOST.ordinal()), 
							ItemStack.EMPTY, 
							new FluidStack(ModFluids.AGING_BATH, 1000), 
							null,
							new FluidStack(ModFluids.CASTING_BATH, 1000),
							null,
							ItemStack.EMPTY);

			sendToSlurryPond(	BaseRecipes.gypsum(1, EnumGypsumItems.COMPOST.ordinal()), 
								CoreBasics.waterStack(200), 
								CoreUtils.getFluid("organic_slurry", 200));

			sendToChemicalExtractor("Sulfate", 
									BaseRecipes.gypsum(1, EnumGypsumItems.GYPSUM.ordinal()), 
									Arrays.asList("dustCalcium", "dustSulfur"),
									Arrays.asList(23, 19));

			sendToLabBlender(	Arrays.asList(	BaseRecipes.gypsum(9, EnumGypsumItems.COMPOST.ordinal())), 
								BaseRecipes.aging(8, EnumAgingItems.ORGANIC_COMPOST.ordinal()));

			sendToLabBlender(	Arrays.asList(	CoreUtils.getModdedStack(elements(), 1, 30),
												CoreUtils.getModdedStack(elements(), 4, 23),
												CoreUtils.getModdedStack(elements(), 3, 24), 
												new ItemStack(Items.QUARTZ, 3)), 
								BaseRecipes.aging(8, EnumAgingItems.CONTAMINATING_COMPOST.ordinal()));

			sendToLabBlender(	Arrays.asList(	CoreUtils.getModdedStack(elements(), 4, 30),
												CoreUtils.getModdedStack(elements(), 2, 24),
												new ItemStack(Items.QUARTZ, 5)),
								BaseRecipes.aging(8, EnumAgingItems.IRON_COMPOST.ordinal()));

			sendToLabBlender(	Arrays.asList(	CoreUtils.getModdedStack(elements(), 1, 30),
												CoreUtils.getModdedStack(elements(), 5, 26),
												CoreUtils.getModdedStack(elements(), 1, 33),
												new ItemStack(Items.QUARTZ, 4)), 
								BaseRecipes.aging(8, EnumAgingItems.COPPER_COMPOST.ordinal()));

			sendToLabBlender(	Arrays.asList(	CoreUtils.getModdedStack(elements(), 1, 30),
												CoreUtils.getModdedStack(elements(), 3, 27),
												new ItemStack(Items.QUARTZ, 5)), 
								BaseRecipes.aging(8, EnumAgingItems.CHROMIUM_COMPOST.ordinal()));

			sendToLabBlender(	Arrays.asList(	CoreUtils.getModdedStack(elements(), 1, 30), 
												CoreUtils.getModdedStack(elements(), 3, 34), 
												new ItemStack(Items.QUARTZ, 5)), 
								BaseRecipes.aging(8, EnumAgingItems.MANGANESE_COMPOST.ordinal()));

			sendToLabBlender(	Arrays.asList(	CoreUtils.getModdedStack(elements(), 2, 30),  
												CoreUtils.getModdedStack(elements(), 2, 25),  
												CoreUtils.getModdedStack(elements(), 2, 34), 
												CoreUtils.getModdedStack(elements(), 2, 26),
												new ItemStack(Items.QUARTZ, 4)), 
								BaseRecipes.aging(8, EnumAgingItems.RAINBOW_COMPOST.ordinal()));

			sendToLabBlender(	Arrays.asList(	new ItemStack(Items.DYE, 7, 4), 
												new ItemStack(Items.QUARTZ, 4)), 
								BaseRecipes.aging(8, EnumAgingItems.LAPIS_COMPOST.ordinal()));

			sendToLabBlender(	Arrays.asList(	CoreUtils.getModdedStack(elements(), 4, 30),  
												CoreUtils.getModdedStack(elements(), 5, 42)), 
								BaseRecipes.aging(8, EnumAgingItems.CHALCEDONY_COMPOST.ordinal()));

			sendToLabBlender(	Arrays.asList(	new ItemStack(Items.BONE), 
												BaseRecipes. gypsum(9, EnumGypsumItems.GYPSUM.ordinal())), 
								BaseRecipes.gypsum(8, EnumGypsumItems.BONEMEAL.ordinal()));

			sendToLabBlender(	Arrays.asList(	BaseRecipes.gypsum(1, EnumGypsumItems.BONEMEAL.ordinal()), 
												BaseRecipes.gypsum(4, EnumGypsumItems.GYPSUM.ordinal()),
												BaseRecipes.gypsum(4, EnumGypsumItems.COMPOST.ordinal()), 
												CoreUtils.getModdedStack(elements(), 1, 8)), 
								BaseRecipes.gypsum(8, EnumGypsumItems.CONDITIONER.ordinal()));

			sendToLabBlender(	Arrays.asList(	BaseRecipes.gypsum(1, EnumGypsumItems.CONDITIONER.ordinal()), 
												new ItemStack(Items.QUARTZ, 4), 
												BaseRecipes.gypsum(5, EnumGypsumItems.GYPSUM.ordinal()),
												CoreUtils.getModdedStack(elements(), 2, 8)), 
								BaseRecipes.gypsum(8, EnumGypsumItems.AMENDMENT.ordinal()));
		}
	}

	public static Item elements(){
		Item block = Item.REGISTRY.getObject(new ResourceLocation(IReciperBase.rh_chemistry_id + ":" + "chemical_dusts"));
		return block;
	}

	public static Item chemicals(){
		Item block = Item.REGISTRY.getObject(new ResourceLocation(IReciperBase.rh_chemistry_id + ":" + "chemical_items"));
		return block;
	}

}