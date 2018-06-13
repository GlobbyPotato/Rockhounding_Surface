package com.globbypotato.rockhounding_surface.integration;

import com.globbypotato.rockhounding_surface.handler.ModConfig;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import rockhounding.api.IReciperBase;

public class SupportUtils {
	public static String bopID = "biomesoplenty";
	public static String chiselID = "chisel";
	public static String pamsID = "harvestcraft";
	public static String naturaID = "natura";

	// MOD CHECK
	public static boolean bopLoaded() {return Loader.isModLoaded(bopID);}
	public static boolean chiselLoaded() {return Loader.isModLoaded(chiselID);}
	public static boolean chiselEnabled(){return chiselLoaded() && ModConfig.allowChisel && ModConfig.allowGypsumDeco;}
	public static boolean pamsLoaded() {return Loader.isModLoaded(pamsID);}
	public static boolean naturaLoaded() {return Loader.isModLoaded(naturaID);}

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

}