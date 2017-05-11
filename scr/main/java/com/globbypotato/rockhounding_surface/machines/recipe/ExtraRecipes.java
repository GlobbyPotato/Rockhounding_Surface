package com.globbypotato.rockhounding_surface.machines.recipe;

import java.util.Arrays;

import com.globbypotato.rockhounding_surface.ModItems;
import com.globbypotato.rockhounding_surface.integration.SupportUtils;

import net.minecraft.item.ItemStack;
import rockhounding.api.machines.IReciper;

public class ExtraRecipes extends IReciper{
	public static void init(){

		/**
		 * description: Add a custom recipe to the Chemical Extractor
		 * 
		 * @param category 
		 * @param inputStack
		 * @param elements array
		 * @param quantities array
		 */
		if(SupportUtils.rhChemistryLoaded()){
			sendToExtractor("Sulfate", new ItemStack(ModItems.gypsumItems,1,0), Arrays.asList("calcium", "sulfur"), Arrays.asList(23,19));
		}
	}
}