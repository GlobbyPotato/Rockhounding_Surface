package com.globbypotato.rockhounding_surface;

import com.globbypotato.rockhounding_surface.handler.ModConfig;
import com.globbypotato.rockhounding_surface.handler.Reference;
import com.globbypotato.rockhounding_surface.world.BiomeWhiteSands;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.BiomeProperties;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.registries.IForgeRegistry;

@ObjectHolder(Reference.MODID)
public class ModBiomes {

	public static final BiomeWhiteSands WHITE_SANDS = new BiomeWhiteSands(new BiomeProperties("White Sands").setBaseHeight(0.125F).setHeightVariation(0.05F).setTemperature(1.3F).setRainfall(0.3F).setWaterColor(13161691));

	@Mod.EventBusSubscriber(modid = Reference.MODID)
	public static class registerBiomes{

		@SubscribeEvent
		public static void registerBiome(final RegistryEvent.Register<Biome> event) {
			final IForgeRegistry<Biome> registry = event.getRegistry();		
			if(ModConfig.ENABLE_SANDS && WHITE_SANDS != null){
				registry.register(WHITE_SANDS);
				BiomeManager.addBiome(BiomeType.DESERT, new BiomeEntry(WHITE_SANDS, 10));
				BiomeManager.addSpawnBiome(WHITE_SANDS);
				BiomeManager.addVillageBiome(WHITE_SANDS, true);
				BiomeManager.addStrongholdBiome(WHITE_SANDS);
		        BiomeProvider.allowedBiomes.add(WHITE_SANDS);
		        BiomeDictionary.addTypes(WHITE_SANDS, BiomeDictionary.Type.SANDY, BiomeDictionary.Type.DRY, BiomeDictionary.Type.DEAD);
			}
		}
	}
}