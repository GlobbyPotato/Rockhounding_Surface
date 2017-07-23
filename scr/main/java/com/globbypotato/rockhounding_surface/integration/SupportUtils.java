package com.globbypotato.rockhounding_surface.integration;

import com.globbypotato.rockhounding_surface.handler.ModConfig;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;

public class SupportUtils {
	private static String rhChemistryID = "rockhounding_chemistry";
	private static String rhTiersID = "rockhounding_oretiers";
	private static String bopID = "BiomesOPlenty";
	private static String chiselID = "chisel";
	private static String pamsID = "harvestcraft";
	private static String naturaID = "natura";

	// MOD CHECK
	public static boolean rhChemistryLoaded(){return Loader.isModLoaded(rhChemistryID);}
	public static boolean rhTiersLoaded(){return Loader.isModLoaded(rhTiersID);}
	public static boolean bopLoaded() {return Loader.isModLoaded(bopID);}
	public static boolean chiselLoaded() {return Loader.isModLoaded(chiselID);}
	public static boolean chiselEnabled(){return chiselLoaded() && ModConfig.allowChisel;}
	public static boolean pamsLoaded() {return Loader.isModLoaded(pamsID);}
	public static boolean naturaLoaded() {return Loader.isModLoaded(naturaID);}
	
	public static ItemStack chemFlask(){
		if(rhChemistryLoaded()){
			Item flask = Item.REGISTRY.getObject(new ResourceLocation(rhChemistryID + ":" + "chemFlask"));
			return new ItemStack(flask);
		}
		return null;
	}

	public static ItemStack saltCompound(){
		if(rhChemistryLoaded()){
			Item salt = Item.REGISTRY.getObject(new ResourceLocation(rhChemistryID + ":" + "chemicalItems"));
			return new ItemStack(salt, 1, 3);
		}
		return null;
	}

	public static ItemStack ironCasing(){
		if(rhChemistryLoaded()){
			Item casing = Item.REGISTRY.getObject(new ResourceLocation(rhChemistryID + ":" + "miscItems"));
			return new ItemStack(casing,1,42);
		}
		return null;
	}

	public static ItemStack logicChip(){
		if(rhChemistryLoaded()){
			Item chip = Item.REGISTRY.getObject(new ResourceLocation(rhChemistryID + ":" + "miscItems"));
			return new ItemStack(chip,1,0);
		}
		return null;
	}

	public static ItemStack cabinet(){
		if(rhChemistryLoaded()){
			Item cabinet = Item.REGISTRY.getObject(new ResourceLocation(rhChemistryID + ":" + "miscItems"));
			return new ItemStack(cabinet,1,1);
		}
		return null;
	}

	public static Block dirt(){
		if(bopLoaded()){
			Block bopDirt = Block.REGISTRY.getObject(new ResourceLocation(bopID + ":" + "dirt"));
			return bopDirt;
		}
		return null;
	}

	public static Block grass(){
		if(bopLoaded()){
			Block bopGrass = Block.REGISTRY.getObject(new ResourceLocation(bopID + ":" + "grass"));
			return bopGrass;
		}
		return null;
	}
	
	public static ItemStack itemPeat(){
		if(rhTiersLoaded()){
			Item itemPeat = Item.REGISTRY.getObject(new ResourceLocation(rhTiersID + ":" + "tiersItems"));
			return new ItemStack(itemPeat,1,3);
		}
		return null;
	}

	public static ItemStack driedPeat(){
		if(rhTiersLoaded()){
			Item driedPeat = Item.REGISTRY.getObject(new ResourceLocation(rhTiersID + ":" + "tiersItems"));
			return new ItemStack(driedPeat,1,6);
		}
		return null;
	}

	public static ItemStack naturaSeeds(){
		if(naturaLoaded()){
			Item naturaseed = Item.REGISTRY.getObject(new ResourceLocation(naturaID + ":" + "overworld_seeds"));
			return new ItemStack(naturaseed, 1, 0);
		}
		return null;
	}

}