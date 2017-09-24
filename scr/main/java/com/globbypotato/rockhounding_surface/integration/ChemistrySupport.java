package com.globbypotato.rockhounding_surface.integration;

import java.util.Arrays;

import com.globbypotato.rockhounding_surface.enums.EnumAgingItems;
import com.globbypotato.rockhounding_surface.fluids.ModFluids;
import com.globbypotato.rockhounding_surface.utils.BaseRecipes;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import rockhounding.api.machines.IReciper;

public class ChemistrySupport extends IReciper {
	public static void init(){
		if(SupportUtils.rhChemistryLoaded()){
			sendToOven(SupportUtils.saltCompound(), false, new FluidStack(FluidRegistry.WATER, 1000), null, new FluidStack(ModFluids.ACIDIC_WATER, 1000));
			sendToOven(BaseRecipes.aging(1, EnumAgingItems.ORGANIC_COMPOST.ordinal()), false, new FluidStack(ModFluids.ACIDIC_WATER, 1000), new FluidStack(ModFluids.AGING_BATH, 1000));
			sendToOven(BaseRecipes.aging(1, EnumAgingItems.CONTAMINATING_COMPOST.ordinal()), false, new FluidStack(ModFluids.AGING_BATH, 1000), new FluidStack(ModFluids.CASTING_BATH, 1000));
			sendToExtractor("Sulfate", BaseRecipes.gypsumDust, Arrays.asList("calcium", "sulfur"), Arrays.asList(23,19));
			sendToBlender(Arrays.asList(BaseRecipes.gypsum(9,4)), BaseRecipes.aging(8, 0));
			sendToBlender(Arrays.asList("dustIron", "dustCalcium", "dustCarbon", "gemQuartz"), Arrays.asList(1,4,3,3), BaseRecipes.aging(8, 1));
			sendToBlender(Arrays.asList("dustIron", "gemQuartz"), Arrays.asList(4,5), BaseRecipes.aging(8, 2));
			sendToBlender(Arrays.asList("dustIron", "dustCopper", "gemQuartz", "dustMagnesium"), Arrays.asList(1,3,4,1), BaseRecipes.aging(8, 3));
			sendToBlender(Arrays.asList("dustIron", "dustChromium", "gemQuartz"), Arrays.asList(1,3,5), BaseRecipes.aging(8, 4));
			sendToBlender(Arrays.asList("dustIron", "dustManganese", "gemQuartz"), Arrays.asList(1,3,5), BaseRecipes.aging(8, 5));
			sendToBlender(Arrays.asList("dustIron", "dustCobalt", "dustManganese", "dustCopper", "gemQuartz"), Arrays.asList(1,1,1,2,4), BaseRecipes.aging(8, 6));
			sendToBlender(Arrays.asList("gemLapis", "gemQuartz"), Arrays.asList(7,2), BaseRecipes.aging(8, 7));
			sendToBlender(Arrays.asList("dustIron", "dustSilicon"), Arrays.asList(4,5), BaseRecipes.aging(8, 8));
			sendToBlender(Arrays.asList("gemCarnelian"), Arrays.asList(9), BaseRecipes.aging(8, 8));
			sendToBlender(Arrays.asList(BaseRecipes.bonemeal, BaseRecipes. gypsum(9, 0)), BaseRecipes.gypsumMeals);
			sendToBlender(Arrays.asList(BaseRecipes.gypsumMeal, BaseRecipes. gypsum(4, 0), BaseRecipes. gypsum(6, 4), getElements(1, 8)), BaseRecipes.gypsumConds);
			sendToBlender(Arrays.asList(BaseRecipes.gypsumCond, BaseRecipes.quartzs, BaseRecipes. gypsum(5, 0), getElements(2, 8)), BaseRecipes.gypsumAmends);
		}
	}

	public static Item elements(){
		if(SupportUtils.rhChemistryLoaded()){
			Item block = Item.REGISTRY.getObject(new ResourceLocation(SupportUtils.rhChemistryID + ":" + "chemicalDusts"));
			return block;
		}
		return null;
	}
	
	private static ItemStack getElements(int num, int meta) { return new ItemStack (elements(), num, meta);}

}