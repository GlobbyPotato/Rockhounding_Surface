package com.globbypotato.rockhounding_surface.integration;

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

	// MOD CHECK
	public static boolean rhChemistryLoaded(){return Loader.isModLoaded(rhChemistryID);}
	public static boolean rhTiersLoaded(){return Loader.isModLoaded(rhTiersID);}
	public static boolean bopLoaded() {return Loader.isModLoaded(bopID);}
	public static boolean chiselLoaded() {return Loader.isModLoaded(chiselID);}
	public static boolean pamsLoaded() {return Loader.isModLoaded(pamsID);}

	public static ItemStack chemFlask(){
		if(rhChemistryLoaded()){
			Item flask = Item.REGISTRY.getObject(new ResourceLocation(rhChemistryID + ":" + "chemFlask"));
			return new ItemStack(flask);
		}
		return null;
	}

	//SEARCH
	public static ItemStack saltCompound(){
		if(rhChemistryLoaded()){
			Item salt = Item.REGISTRY.getObject(new ResourceLocation(rhChemistryID + ":" + "chemicalItems"));
			return new ItemStack(salt, 1, 3);
		}
		return null;
	}

	public static ItemStack inductor(){
		if(rhChemistryLoaded()){
			Item inductor = Item.REGISTRY.getObject(new ResourceLocation(rhChemistryID + ":" + "miscItems"));
			return new ItemStack(inductor,1,17);
		}
		return null;
	}

	public static ItemStack labOven(){
		if(rhChemistryLoaded()){
			Item labOven = Item.REGISTRY.getObject(new ResourceLocation(rhChemistryID + ":" + "labOven"));
			return new ItemStack(labOven);
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
			return new ItemStack(driedPeat,1,8);
		}
		return null;
	}

}