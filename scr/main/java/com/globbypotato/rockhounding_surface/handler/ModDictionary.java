package com.globbypotato.rockhounding_surface.handler;

import com.globbypotato.rockhounding_surface.ModBlocks;
import com.globbypotato.rockhounding_surface.ModItems;
import com.globbypotato.rockhounding_surface.enums.EnumFossilPlanks;
import com.globbypotato.rockhounding_surface.enums.EnumTruffles;
import com.globbypotato.rockhounding_surface.utils.BaseRecipes;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ModDictionary {

	public static void loadDictionary()  {
		if(ModConfig.woodOredict){
			for(int x = 0; x < EnumFossilPlanks.size(); x++){
				OreDictionary.registerOre("plankWood", 		new ItemStack(ModBlocks.fossilPlanks, 1, x));
				OreDictionary.registerOre("stickWood", 		new ItemStack(ModItems.fossilSticks, 1, x));
				OreDictionary.registerOre("slabWood", 		new ItemStack(ModBlocks.fossilSlabsLo, 1, x));
			}
			for(int x = 0; x < 4; x++){
				OreDictionary.registerOre("logWood", 		new ItemStack(ModBlocks.bogLogs, 1, x));
				OreDictionary.registerOre("logWood", 		new ItemStack(ModBlocks.fossilLogs, 1, x));
				OreDictionary.registerOre("logWood", 		new ItemStack(ModBlocks.petrifiedLogs, 1, x));
				OreDictionary.registerOre("logWood", 		new ItemStack(ModBlocks.coldLogs, 1, x));
			}
			if(ModConfig.allowStairs){
				OreDictionary.registerOre("stairsWood", 		new ItemStack(ModBlocks.oakStairs));
				OreDictionary.registerOre("stairsWood", 		new ItemStack(ModBlocks.spruceStairs));
				OreDictionary.registerOre("stairsWood", 		new ItemStack(ModBlocks.kauriStairs));
				OreDictionary.registerOre("stairsWood", 		new ItemStack(ModBlocks.mophaneStairs));
				OreDictionary.registerOre("stairsWood", 		new ItemStack(ModBlocks.palmStairs));
				OreDictionary.registerOre("stairsWood", 		new ItemStack(ModBlocks.pineStairs));
				OreDictionary.registerOre("stairsWood", 		new ItemStack(ModBlocks.araucariaStairs));
				OreDictionary.registerOre("stairsWood", 		new ItemStack(ModBlocks.driftwoodStairs));
				OreDictionary.registerOre("stairsWood", 		new ItemStack(ModBlocks.betulaStairs));
				OreDictionary.registerOre("stairsWood", 		new ItemStack(ModBlocks.castanoStairs));
				OreDictionary.registerOre("stairsWood", 		new ItemStack(ModBlocks.rainbowStairs));
				OreDictionary.registerOre("stairsWood", 		new ItemStack(ModBlocks.opalizedStairs));
				OreDictionary.registerOre("stairsWood", 		new ItemStack(ModBlocks.azuriteStairs));
				OreDictionary.registerOre("stairsWood", 		new ItemStack(ModBlocks.beechStairs));
				OreDictionary.registerOre("stairsWood", 		new ItemStack(ModBlocks.teredoStairs));
				OreDictionary.registerOre("stairsWood", 		new ItemStack(ModBlocks.redwoodStairs));
			}
		}

		OreDictionary.registerOre("dustGypsum", 			BaseRecipes.gypsumDust);
		OreDictionary.registerOre("gypsum", 				BaseRecipes.gypsumDust);
		OreDictionary.registerOre("blockGypsum", 			new ItemStack(ModBlocks.gypsum,1,1));

		for(int x = 0; x < EnumTruffles.size(); x++){
			OreDictionary.registerOre("cropTruffle", 		new ItemStack(ModItems.truffles,1,x));
		}
		OreDictionary.registerOre("listAllspice", 			new ItemStack(ModItems.truffle_slices));
		OreDictionary.registerOre("foodOliveoil", 			new ItemStack(ModItems.truffle_oil));
		OreDictionary.registerOre("foodTruffle", 			new ItemStack(ModItems.truffle_slices));

		OreDictionary.registerOre("cropRicegrass", 			new ItemStack(ModItems.ricegrassItem));
		OreDictionary.registerOre("listAllgrain", 			new ItemStack(ModItems.ricegrassItem));
		OreDictionary.registerOre("listAllseeds", 			new ItemStack(ModItems.ricegrassSeeds));

		OreDictionary.registerOre("cropSourberry", 			new ItemStack(ModItems.sourberry));
		OreDictionary.registerOre("listAllfruit", 			new ItemStack(ModItems.sourberry));
		OreDictionary.registerOre("listAllberry", 			new ItemStack(ModItems.sourberry));

		OreDictionary.registerOre("listAllfruit", 			new ItemStack(ModItems.purplePear));
		OreDictionary.registerOre("cropPurplepear", 		new ItemStack(ModItems.purplePear));
		
		OreDictionary.registerOre("cropMesquite", 			new ItemStack(ModItems.mesquite));
		OreDictionary.registerOre("foodFlour", 				BaseRecipes.mesquiteFlour);
		OreDictionary.registerOre("flourEqualsWheat", 		BaseRecipes.mesquiteFlour);
	}
}