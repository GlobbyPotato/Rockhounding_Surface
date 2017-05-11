package com.globbypotato.rockhounding_surface;

import java.util.Collections;

import com.globbypotato.rockhounding_surface.handler.ModConfig;
import com.globbypotato.rockhounding_surface.world.BiomeWhiteSands;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.BiomeProperties;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;

public class ModBiomes {

	public static Biome WHITE_SANDS;

	public static void init(){
		initBiome();
		registerBiome();
	}

	public static void initBiome(){
		WHITE_SANDS = new BiomeWhiteSands(new BiomeProperties("White Sands").setBaseHeight(0.125F).setHeightVariation(0.05F).setTemperature(2.0F).setRainfall(0.3F).setWaterColor(13161691));
        Collections.addAll(Biome.EXPLORATION_BIOMES_LIST, new Biome[] {WHITE_SANDS});
	}

	public static void registerBiome(){
		Biome.registerBiome(ModConfig.whiteSandsID, "White Sands", WHITE_SANDS);
		BiomeManager.addBiome(BiomeType.DESERT, new BiomeEntry(WHITE_SANDS, 10));
		BiomeDictionary.registerBiomeType(WHITE_SANDS, Type.SANDY, Type.HOT, Type.DRY);
		BiomeManager.addSpawnBiome(WHITE_SANDS);
	}

}