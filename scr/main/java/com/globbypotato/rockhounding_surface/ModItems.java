package com.globbypotato.rockhounding_surface;

import com.globbypotato.rockhounding_surface.enums.EnumAgingItems;
import com.globbypotato.rockhounding_surface.enums.EnumFood;
import com.globbypotato.rockhounding_surface.enums.EnumFossilPlanks;
import com.globbypotato.rockhounding_surface.enums.EnumGypsumItems;
import com.globbypotato.rockhounding_surface.enums.EnumTruffles;
import com.globbypotato.rockhounding_surface.fluids.ModFluids;
import com.globbypotato.rockhounding_surface.integration.SupportUtils;
import com.globbypotato.rockhounding_surface.items.AgingItems;
import com.globbypotato.rockhounding_surface.items.BaseItems;
import com.globbypotato.rockhounding_surface.items.BaseSeeds;
import com.globbypotato.rockhounding_surface.items.FossilSticks;
import com.globbypotato.rockhounding_surface.items.GypsumItems;
import com.globbypotato.rockhounding_surface.items.ModFood;
import com.globbypotato.rockhounding_surface.items.ModSoup;
import com.globbypotato.rockhounding_surface.items.Truffles;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.FluidRegistry;

public class ModItems {
//Fossil woods
	public static Item fossilSticks;
	
//Wood Aging Tech
	public static Item agingItems;

// Truffles
	public static Item truffles;
	public static Item truffle_slices;
	public static Item truffle_oil;
	public static Item truffle_canape;
	public static Item truffle_rice;
	public static Item truffle_fillet;
	public static Item truffle_vodka;
	public static Item truffle_toast;
	public static Item truffle_tomato;
	public static Item truffle_pumpkin;
	public static Item truffle_omelette;
// gypsum
	public static Item gypsumItems;
	public static Item sourberry;
	public static Item sourberryJuice;
	public static Item sourberryPie;
	public static Item sourberryTea;
	public static Item purplePear;
	public static Item purplePearJuice;
	public static Item purplePearSmoothie;
	public static Item purplePearJam;
	public static Item sotol;
	public static Item ricegrassSeeds;
	public static Item ricegrassItem;
	public static Item mesquite;
	public static Item mesquitePie;
	public static Item mesquiteLiquor;
	public static Item mesquiteTea;

	public static void init() {
//Fossil woods
		fossilSticks = new FossilSticks("fossilSticks", EnumFossilPlanks.getNames());
//Wood Aging Tech
		if(SupportUtils.rhChemistryLoaded()){
			agingItems = new AgingItems("agingItems", EnumAgingItems.getNames());
		}
// Truffles
		truffles = new Truffles("truffles", EnumTruffles.getNames());		
		truffle_slices = new ModFood(1, 0.2F, false, EnumAction.EAT, 16, EnumFood.getName(0));
		truffle_oil = new ModFood(0, 0.1F, false, EnumAction.DRINK, 16, EnumFood.getName(1));
		truffle_canape = new ModFood(3, 0.5F, false, EnumAction.EAT, 24, EnumFood.getName(2));
		truffle_rice = new ModFood(5, 0.7F, false, EnumAction.EAT, 32, EnumFood.getName(3));
		truffle_fillet = new ModFood(9, 1.2F, false, EnumAction.EAT, 64, EnumFood.getName(4));
		truffle_vodka = new ModFood(4, 0.6F, false, EnumAction.DRINK, 32, EnumFood.getName(5));
		truffle_toast = new ModFood(4, 0.7F, false, EnumAction.EAT, 24, EnumFood.getName(6));
		truffle_tomato = new ModFood(7, 1.0F, false, EnumAction.EAT, 64, EnumFood.getName(7));
		truffle_pumpkin = new ModFood(9, 0.9F, false, EnumAction.EAT, 64, EnumFood.getName(8));
		truffle_omelette = new ModFood(3, 0.8F, false, EnumAction.EAT, 32, EnumFood.getName(9));
// gypsum items
		gypsumItems = new GypsumItems("gypsumItems", EnumGypsumItems.getNames());
		sourberry = new ModFood(1, 0.2F, false, EnumAction.EAT, 16, EnumFood.getName(10));
		purplePear = new ModFood(1, 0.2F, false, EnumAction.EAT, 16, EnumFood.getName(14));
		sourberryTea = new ModFood(3, 0.8F, false, EnumAction.DRINK, 24, EnumFood.getName(13));
		sourberryJuice = new ModFood(5, 1.0F, false, EnumAction.DRINK, 32, EnumFood.getName(11));
		purplePearJuice = new ModFood(5, 1.0F, false, EnumAction.DRINK, 32, EnumFood.getName(15));
		purplePearSmoothie = new ModFood(5, 1.6F, false, EnumAction.DRINK, 32, EnumFood.getName(16));
		purplePearJam = new ModSoup(8, 1.2F, false, EnumAction.EAT, 32, EnumFood.getName(17));
		sourberryPie = new ModFood(6, 0.7F, false, EnumAction.EAT, 24, EnumFood.getName(12));
		sotol = new ModFood(4, 1.6F, false, EnumAction.DRINK, 32, EnumFood.getName(18));
		mesquite = new ModFood(1, 0.2F, false, EnumAction.EAT, 16, EnumFood.getName(19));
		mesquitePie = new ModFood(6, 0.7F, false, EnumAction.EAT, 24, EnumFood.getName(20));
		mesquiteLiquor = new ModFood(4, 1.6F, false, EnumAction.DRINK, 32, EnumFood.getName(21));
		mesquiteTea = new ModFood(3, 0.8F, false, EnumAction.DRINK, 24, EnumFood.getName(22));
		ricegrassSeeds = new BaseSeeds("ricegrassSeeds", ModBlocks.ricegrassCrop, Blocks.FARMLAND);		
		ricegrassItem = new BaseItems("ricegrassItem");		
	}

	public static void initClient() {
// Fossil woods
		for(int i = 0; i < EnumFossilPlanks.size(); i++){		registerMetaItemRender(fossilSticks, i, EnumFossilPlanks.getName(i));}
// Wood Aging Tech
		if(SupportUtils.rhChemistryLoaded()){
			for(int i = 0; i < EnumAgingItems.size(); i++){		registerMetaItemRender(agingItems, i, EnumAgingItems.getName(i));}
		}
// Gypsum
		for(int i = 0; i < EnumGypsumItems.size(); i++){		registerMetaItemRender(gypsumItems, i, EnumGypsumItems.getName(i));}
		registerSimpleItemRender(sourberry, 0, EnumFood.getName(10));
		registerSimpleItemRender(sourberryJuice, 0, EnumFood.getName(11));
		registerSimpleItemRender(sourberryPie, 0, EnumFood.getName(12));
		registerSimpleItemRender(sourberryTea, 0, EnumFood.getName(13));
		registerSimpleItemRender(purplePear, 0, EnumFood.getName(14));
		registerSimpleItemRender(purplePearJuice, 0, EnumFood.getName(15));
		registerSimpleItemRender(purplePearSmoothie, 0, EnumFood.getName(16));
		registerSimpleItemRender(purplePearJam, 0, EnumFood.getName(17));
		registerSimpleItemRender(sotol, 0, EnumFood.getName(18));
		registerSimpleItemRender(mesquite, 0, EnumFood.getName(19));
		registerSimpleItemRender(mesquitePie, 0, EnumFood.getName(20));
		registerSimpleItemRender(mesquiteLiquor, 0, EnumFood.getName(21));
		registerSimpleItemRender(mesquiteTea, 0, EnumFood.getName(22));
		registerSimpleItemRender(ricegrassSeeds, 0, "ricegrassSeeds");
		registerSimpleItemRender(ricegrassItem, 0, "ricegrassItem");
// Truffles
		for(int i = 0; i < EnumTruffles.size(); i++){			registerMetaItemRender(truffles, i, EnumTruffles.getName(i));}
		registerSimpleItemRender(truffle_slices, 0, EnumFood.getName(0));
		registerSimpleItemRender(truffle_oil, 0, EnumFood.getName(1));
		registerSimpleItemRender(truffle_canape, 0, EnumFood.getName(2));
		registerSimpleItemRender(truffle_rice, 0, EnumFood.getName(3));
		registerSimpleItemRender(truffle_fillet, 0, EnumFood.getName(4));
		registerSimpleItemRender(truffle_vodka, 0, EnumFood.getName(5));
		registerSimpleItemRender(truffle_toast, 0, EnumFood.getName(6));
		registerSimpleItemRender(truffle_tomato, 0, EnumFood.getName(7));
		registerSimpleItemRender(truffle_pumpkin, 0, EnumFood.getName(8));
		registerSimpleItemRender(truffle_omelette, 0, EnumFood.getName(9));

		if(SupportUtils.rhChemistryLoaded()){
			if( !FluidRegistry.isUniversalBucketEnabled() ){
				registerSimpleItemRender(ModFluids.acidicWaterBucket, 0, "acidicWaterBucket");
				registerSimpleItemRender(ModFluids.agingBathBucket, 0, "agingBathBucket");
				registerSimpleItemRender(ModFluids.castingBathBucket, 0, "castingBathBucket");
			}
		}
	}

	public static void registerMetaItemRender(Item item, int meta, String fileName){
		ModelResourceLocation model = new ModelResourceLocation(item.getRegistryName() + "_" + fileName, "inventory");
		ModelLoader.setCustomModelResourceLocation(item, meta, model );
	}
	public static void registerSimpleItemRender(Item item, int meta, String fileName){
		ModelResourceLocation model = new ModelResourceLocation(item.getRegistryName(), "inventory");
		ModelLoader.setCustomModelResourceLocation(item, meta, model );
	}

}