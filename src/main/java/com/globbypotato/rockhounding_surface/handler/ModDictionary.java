package com.globbypotato.rockhounding_surface.handler;

import com.globbypotato.rockhounding_surface.ModBlocks;
import com.globbypotato.rockhounding_surface.ModItems;
import com.globbypotato.rockhounding_surface.enums.EnumFossilPlanks;
import com.globbypotato.rockhounding_surface.enums.EnumGypsumBlocks;
import com.globbypotato.rockhounding_surface.enums.EnumGypsumItems;
import com.globbypotato.rockhounding_surface.enums.EnumTruffles;
import com.globbypotato.rockhounding_surface.integration.SupportUtils;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;

public class ModDictionary {

	@Mod.EventBusSubscriber(modid = Reference.MODID)
	public static class RegistrationHandler {

		/**
		 * @param event  
		 */
		@SubscribeEvent(priority = EventPriority.LOWEST)
		public static void registerOreDictEntries(final RegistryEvent.Register<Item> event) {
			
			if(ModConfig.ENABLE_TRUFFLES){
				for(int x = 0; x < EnumTruffles.size(); x++){
					OreDictionary.registerOre("cropTruffle", 	new ItemStack(ModItems.TRUFFLES,1,x));
				}
				OreDictionary.registerOre("listAllspice", 		new ItemStack(ModItems.TRUFFLE_SLICES));
				OreDictionary.registerOre("foodTruffle", 		new ItemStack(ModItems.TRUFFLE_SLICES));
				if(SupportUtils.pamsLoaded()){
					OreDictionary.registerOre("foodOliveoil", 		new ItemStack(ModItems.TRUFFLE_OIL));
				}
			}	

			OreDictionary.registerOre("dustGypsum", 			new ItemStack(ModItems.GYPSUM_ITEMS, 1, EnumGypsumItems.GYPSUM.ordinal()));
			OreDictionary.registerOre("gypsum", 				new ItemStack(ModItems.GYPSUM_ITEMS, 1, EnumGypsumItems.GYPSUM.ordinal()));
			if(ModConfig.allowGypsumDeco){
				OreDictionary.registerOre("blockGypsum", 		new ItemStack(ModBlocks.GYPSUM, 1, EnumGypsumBlocks.BLOCK.ordinal()));
			}

			if(ModConfig.ENABLE_SANDS){
				OreDictionary.registerOre("cropRicegrass", 		new ItemStack(ModItems.RICEGRASS_ITEM));
				OreDictionary.registerOre("listAllgrain", 		new ItemStack(ModItems.RICEGRASS_ITEM));
				OreDictionary.registerOre("listAllseeds", 		new ItemStack(ModItems.RICEGRASS_SEEDS));
	
				OreDictionary.registerOre("cropSourberry", 		new ItemStack(ModItems.SOURBERRY));
				OreDictionary.registerOre("listAllfruit", 		new ItemStack(ModItems.SOURBERRY));
				OreDictionary.registerOre("listAllberry", 		new ItemStack(ModItems.SOURBERRY));
	
				OreDictionary.registerOre("listAllfruit", 		new ItemStack(ModItems.PURPLEPEAR));
				OreDictionary.registerOre("cropPurplepear", 	new ItemStack(ModItems.PURPLEPEAR));
				
				OreDictionary.registerOre("cropMesquite", 		new ItemStack(ModItems.MESQUITE));
				OreDictionary.registerOre("flourMesquite", 		new ItemStack(ModItems.GYPSUM_ITEMS, 1, EnumGypsumItems.MESQUITE_FLOUR.ordinal()));
				OreDictionary.registerOre("foodFlour", 			new ItemStack(ModItems.GYPSUM_ITEMS, 1, EnumGypsumItems.MESQUITE_FLOUR.ordinal()));
				OreDictionary.registerOre("flourEqualsWheat", 	new ItemStack(ModItems.GYPSUM_ITEMS, 1, EnumGypsumItems.MESQUITE_FLOUR.ordinal()));
			}

			if(ModConfig.woodOredict){
				for(int x = 0; x < EnumFossilPlanks.size(); x++){
					OreDictionary.registerOre("plankWood", 		new ItemStack(ModBlocks.FOSSIL_PLANKS, 1, x));
					OreDictionary.registerOre("stickWood", 		new ItemStack(ModItems.FOSSIL_STICKS, 1, x));
					OreDictionary.registerOre("slabWood", 		new ItemStack(ModBlocks.FOSSIL_SLABS_LO, 1, x));
				}
				for(int x = 0; x < 4; x++){
					OreDictionary.registerOre("logWood", 		new ItemStack(ModBlocks.BOG_LOGS, 1, x));
					OreDictionary.registerOre("logWood", 		new ItemStack(ModBlocks.FOSSIL_LOGS, 1, x));
					OreDictionary.registerOre("logWood", 		new ItemStack(ModBlocks.PETRIFIED_LOGS, 1, x));
					OreDictionary.registerOre("logWood", 		new ItemStack(ModBlocks.COLD_LOGS, 1, x));
				}
				if(ModConfig.allowStairs){
					OreDictionary.registerOre("stairsWood", 	new ItemStack(ModBlocks.OAK_STAIRS));
					OreDictionary.registerOre("stairsWood", 	new ItemStack(ModBlocks.SPRUCE_STAIRS));
					OreDictionary.registerOre("stairsWood", 	new ItemStack(ModBlocks.KAURI_STAIRS));
					OreDictionary.registerOre("stairsWood", 	new ItemStack(ModBlocks.MOPHANE_STAIRS));
					OreDictionary.registerOre("stairsWood", 	new ItemStack(ModBlocks.PALM_STAIRS));
					OreDictionary.registerOre("stairsWood", 	new ItemStack(ModBlocks.PINE_STAIRS));
					OreDictionary.registerOre("stairsWood", 	new ItemStack(ModBlocks.ARAUCARIA_STAIRS));
					OreDictionary.registerOre("stairsWood", 	new ItemStack(ModBlocks.DRIFTWOOD_STAIRS));
					OreDictionary.registerOre("stairsWood", 	new ItemStack(ModBlocks.BETULA_STAIRS));
					OreDictionary.registerOre("stairsWood", 	new ItemStack(ModBlocks.CASTANO_STAIRS));
					OreDictionary.registerOre("stairsWood", 	new ItemStack(ModBlocks.RAINBOW_STAIRS));
					OreDictionary.registerOre("stairsWood", 	new ItemStack(ModBlocks.OPALIZED_STAIRS));
					OreDictionary.registerOre("stairsWood", 	new ItemStack(ModBlocks.AZURITE_STAIRS));
					OreDictionary.registerOre("stairsWood", 	new ItemStack(ModBlocks.BEECH_STAIRS));
					OreDictionary.registerOre("stairsWood", 	new ItemStack(ModBlocks.TEREDO_STAIRS));
					OreDictionary.registerOre("stairsWood", 	new ItemStack(ModBlocks.REDWOOD_STAIRS));
				}
			}

		}
	}

}