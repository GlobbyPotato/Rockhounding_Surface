package com.globbypotato.rockhounding_surface;

import com.globbypotato.rockhounding_core.handlers.RegistryHandler;
import com.globbypotato.rockhounding_surface.enums.EnumAgingItems;
import com.globbypotato.rockhounding_surface.enums.EnumFood;
import com.globbypotato.rockhounding_surface.enums.EnumFossilPlanks;
import com.globbypotato.rockhounding_surface.enums.EnumGypsumItems;
import com.globbypotato.rockhounding_surface.enums.EnumTruffles;
import com.globbypotato.rockhounding_surface.handler.ModConfig;
import com.globbypotato.rockhounding_surface.handler.Reference;
import com.globbypotato.rockhounding_surface.integration.SupportUtils;
import com.globbypotato.rockhounding_surface.items.AgingItems;
import com.globbypotato.rockhounding_surface.items.FossilSticks;
import com.globbypotato.rockhounding_surface.items.GypsumItems;
import com.globbypotato.rockhounding_surface.items.ModFood;
import com.globbypotato.rockhounding_surface.items.ModSoup;
import com.globbypotato.rockhounding_surface.items.Truffles;
import com.globbypotato.rockhounding_surface.items.io.ItemIO;
import com.globbypotato.rockhounding_surface.items.io.SeedsIO;

import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class ModItems {
//Fossil woods
	public static final Item FOSSIL_STICKS = new FossilSticks("fossil_sticks", EnumFossilPlanks.getNames());

	public static final Item GYPSUM_ITEMS = new GypsumItems("gypsum_items", EnumGypsumItems.getNames());
	public static final Item AGING_ITEMS = new AgingItems("aging_items", EnumAgingItems.getNames());

	public static final Item RICEGRASS_SEEDS = new SeedsIO("ricegrass_seeds", ModBlocks.RICEGRASS_CROP, Blocks.FARMLAND); ;
	public static final Item RICEGRASS_ITEM = new ItemIO("ricegrass_item");
	public static final Item SOURBERRY = new ModFood(1, 0.2F, false, EnumAction.EAT, 				16, EnumFood.getName(EnumFood.SOURBERRY_FRUIT.ordinal()));
	public static final Item PURPLEPEAR = new ModFood(1, 0.2F, false, EnumAction.EAT, 				16, EnumFood.getName(EnumFood.PURPLEPEAR_FRUIT.ordinal()));
	public static final Item MESQUITE = new ModFood(1, 0.2F, false, EnumAction.EAT, 				16, EnumFood.getName(EnumFood.MESQUITE_FRUIT.ordinal()));
	public static final Item MESQUITE_FLOWER = new ModFood(1, 0.2F, false, EnumAction.EAT, 			16, EnumFood.getName(EnumFood.MESQUITE_FLOWER.ordinal()));
	public static final Item TRUFFLES = new Truffles("truffles", EnumTruffles.getNames());		

	public static final Item SOURBERRY_PIE = new ModFood(6, 0.7F, false, EnumAction.EAT, 			24, EnumFood.getName(EnumFood.SOURBERRY_PIE.ordinal()));
	public static final Item PURPLEPEAR_JAM = new ModSoup(8, 1.2F, false, EnumAction.EAT, 			32, EnumFood.getName(EnumFood.PURPLEPEAR_JAM.ordinal()));
	public static final Item MESQUITE_LIQUOR = new ModFood(4, 1.6F, false, EnumAction.DRINK, 		32, EnumFood.getName(EnumFood.MESQUITE_LIQUOR.ordinal()));
	public static final Item SOURBERRY_TEA = new ModFood(3, 0.8F, false, EnumAction.DRINK, 			24, EnumFood.getName(EnumFood.SOURBERRY_TEA.ordinal()));
	public static final Item MESQUITE_TEA = new ModFood(3, 0.8F, false, EnumAction.DRINK, 			24, EnumFood.getName(EnumFood.MESQUITE_TEA.ordinal()));
	public static final Item SOTOL = new ModFood(4, 1.6F, false, EnumAction.DRINK, 					32, EnumFood.getName(EnumFood.SOTOL.ordinal()));
	public static final Item TRUFFLE_SLICES = new ModFood(1, 0.2F, false, EnumAction.EAT, 			16, EnumFood.getName(EnumFood.TRUFFLE_SLICES.ordinal()));
	public static final Item TRUFFLE_VODKA = new ModFood(4, 0.6F, false, EnumAction.DRINK, 			32, EnumFood.getName(EnumFood.TRUFFLE_VODKA.ordinal()));
	public static final Item TRUFFLE_TOAST = new ModFood(4, 0.7F, false, EnumAction.EAT, 			24, EnumFood.getName(EnumFood.TRUFFLE_TOAST.ordinal()));
	public static final Item TRUFFLE_PUMPKIN = new ModFood(9, 0.9F, false, EnumAction.EAT, 			64, EnumFood.getName(EnumFood.TRUFFLE_PUMPKIN.ordinal()));
	public static final Item TRUFFLE_OMELETTE = new ModFood(3, 0.8F, false, EnumAction.EAT, 		32, EnumFood.getName(EnumFood.TRUFFLE_OMELETTE.ordinal()));

	public static final Item SOURBERRY_JUICE = new ModFood(5, 1.0F, false, EnumAction.DRINK, 		32, EnumFood.getName(EnumFood.SOURBERRY_JUICE.ordinal()));
	public static final Item SOURBERRY_SMOOTHIE = new ModFood(5, 1.6F, false, EnumAction.DRINK,		32, EnumFood.getName(EnumFood.SOURBERRY_SMOOTHIE.ordinal()));
	public static final Item PURPLEPEAR_JUICE = new ModFood(5, 1.0F, false, EnumAction.DRINK, 		32, EnumFood.getName(EnumFood.PURPLEPEAR_JUICE.ordinal()));
	public static final Item PURPLEPEAR_SMOOTHIE = new ModFood(5, 1.6F, false, EnumAction.DRINK,	32, EnumFood.getName(EnumFood.PURPLEPEAR_SMOOTHIE.ordinal()));
	public static final Item MESQUITE_PIE = new ModFood(6, 0.7F, false, EnumAction.EAT, 			24, EnumFood.getName(EnumFood.MESQUITE_PIE.ordinal()));
	public static final Item MUSH = new ModFood(4, 0.7F, false, EnumAction.EAT, 					24, EnumFood.getName(EnumFood.MUSH.ordinal()));
	public static final Item TRUFFLE_OIL = new ModFood(0, 0.1F, false, EnumAction.DRINK, 			16, EnumFood.getName(EnumFood.TRUFFLE_OIL.ordinal()));
	public static final Item TRUFFLE_CANAPE = new ModFood(3, 0.5F, false, EnumAction.EAT, 			24, EnumFood.getName(EnumFood.TRUFFLE_CANAPE.ordinal()));
	public static final Item TRUFFLE_RICE = new ModFood(5, 0.7F, false, EnumAction.EAT, 			32, EnumFood.getName(EnumFood.TRUFFLE_RICE.ordinal()));
	public static final Item TRUFFLE_FILLET = new ModFood(9, 1.2F, false, EnumAction.EAT, 			64, EnumFood.getName(EnumFood.TRUFFLE_FILLET.ordinal()));
	public static final Item TRUFFLE_TOMATO = new ModFood(7, 1.0F, false, EnumAction.EAT, 			64, EnumFood.getName(EnumFood.TRUFFLE_TOMATO.ordinal()));

	@Mod.EventBusSubscriber(modid = Reference.MODID)
	public static class RegistrationHandler {

		// register the item
		@SubscribeEvent
		public static void registerItems(final RegistryEvent.Register<Item> event) {
			final IForgeRegistry<Item> registry = event.getRegistry();
			registry.register(FOSSIL_STICKS);
			registry.register(GYPSUM_ITEMS);
			registry.register(AGING_ITEMS);

			if(ModConfig.ENABLE_SANDS){
				registry.register(RICEGRASS_SEEDS);
				registry.register(RICEGRASS_ITEM);
				registry.register(SOURBERRY);
				registry.register(PURPLEPEAR);
				registry.register(MESQUITE);
				registry.register(MESQUITE_FLOWER);
				registry.register(SOURBERRY_PIE);
				registry.register(SOURBERRY_TEA);
				registry.register(PURPLEPEAR_JAM);
				registry.register(MESQUITE_LIQUOR);
				registry.register(MESQUITE_TEA);
				registry.register(SOTOL);
			}

			if(ModConfig.ENABLE_TRUFFLES){
				registry.register(TRUFFLES);
				registry.register(TRUFFLE_SLICES);
				registry.register(TRUFFLE_VODKA);
				registry.register(TRUFFLE_TOAST);
				registry.register(TRUFFLE_PUMPKIN);
				registry.register(TRUFFLE_OMELETTE);
			}

			if(SupportUtils.pamsLoaded()){
				if(ModConfig.ENABLE_SANDS){
					registry.register(SOURBERRY_JUICE);
					registry.register(SOURBERRY_SMOOTHIE);
					registry.register(PURPLEPEAR_JUICE);
					registry.register(PURPLEPEAR_SMOOTHIE);
					registry.register(MESQUITE_PIE);
					registry.register(MUSH);
				}
				if(ModConfig.ENABLE_TRUFFLES){
					registry.register(TRUFFLE_OIL);
					registry.register(TRUFFLE_CANAPE);
					registry.register(TRUFFLE_RICE);
					registry.register(TRUFFLE_FILLET);
					registry.register(TRUFFLE_TOMATO);
				}
			}
		}

		/**
		 * @param event  
		 */
		// register the model
		@SubscribeEvent
		public static void registerModels(ModelRegistryEvent event){
			RegistryHandler.registerMetaModel(FOSSIL_STICKS, EnumFossilPlanks.getNames());
			RegistryHandler.registerMetaModel(GYPSUM_ITEMS, EnumGypsumItems.getNames());
			RegistryHandler.registerMetaModel(AGING_ITEMS, EnumAgingItems.getNames());
			if(ModConfig.ENABLE_SANDS){
				RegistryHandler.registerSingleModel(RICEGRASS_SEEDS);
				RegistryHandler.registerSingleModel(RICEGRASS_ITEM);
				RegistryHandler.registerSingleModel(SOURBERRY);
				RegistryHandler.registerSingleModel(PURPLEPEAR);
				RegistryHandler.registerSingleModel(MESQUITE);
				RegistryHandler.registerSingleModel(MESQUITE_FLOWER);
				RegistryHandler.registerSingleModel(SOURBERRY_PIE);
				RegistryHandler.registerSingleModel(SOURBERRY_TEA);
				RegistryHandler.registerSingleModel(PURPLEPEAR_JAM);
				RegistryHandler.registerSingleModel(MESQUITE_LIQUOR);
				RegistryHandler.registerSingleModel(MESQUITE_TEA);
				RegistryHandler.registerSingleModel(SOTOL);
			}
			if(ModConfig.ENABLE_TRUFFLES){
				RegistryHandler.registerMetaModel(TRUFFLES, EnumTruffles.getNames());
				RegistryHandler.registerSingleModel(TRUFFLE_SLICES);
				RegistryHandler.registerSingleModel(TRUFFLE_VODKA);
				RegistryHandler.registerSingleModel(TRUFFLE_TOAST);
				RegistryHandler.registerSingleModel(TRUFFLE_PUMPKIN);
				RegistryHandler.registerSingleModel(TRUFFLE_OMELETTE);
			}
			if(SupportUtils.pamsLoaded()){
				if(ModConfig.ENABLE_SANDS){
					RegistryHandler.registerSingleModel(SOURBERRY_JUICE);
					RegistryHandler.registerSingleModel(PURPLEPEAR_JUICE);
					RegistryHandler.registerSingleModel(SOURBERRY_SMOOTHIE);
					RegistryHandler.registerSingleModel(PURPLEPEAR_SMOOTHIE);
					RegistryHandler.registerSingleModel(MESQUITE_PIE);
					RegistryHandler.registerSingleModel(MUSH);
				}
				if(ModConfig.ENABLE_TRUFFLES){
					RegistryHandler.registerSingleModel(TRUFFLE_OIL);
					RegistryHandler.registerSingleModel(TRUFFLE_CANAPE);
					RegistryHandler.registerSingleModel(TRUFFLE_RICE);
					RegistryHandler.registerSingleModel(TRUFFLE_FILLET);
					RegistryHandler.registerSingleModel(TRUFFLE_TOMATO);
				}
			}
		}
	}

}