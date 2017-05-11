package com.globbypotato.rockhounding_surface.world;

import java.util.Random;

import com.globbypotato.rockhounding_surface.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fml.common.IWorldGenerator;

public class SandGenerator implements IWorldGenerator {
	public static int sandFrequency;
	Random rand = new Random();

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		switch (world.provider.getDimension()) {
			case 0:
				generateOverworld(world, random, new BlockPos(chunkX * 16, 64, chunkZ * 16)); break;
		}
	}

	private void generateOverworld(World world, Random random, BlockPos pos) {
		Biome biome = world.getBiome(pos);
		if(isBiome(biome, Type.BEACH) && sandFrequency > 0){
			if(rand.nextInt(3) == 0){
				addNewBeach(ModBlocks.whiteSand, 0, world, random, pos, 16, 16, 30, 32, sandFrequency, world.getSeaLevel() - 2, world.getSeaLevel() + 1, Blocks.SAND);
			}
			if(rand.nextInt(3) == 0){
				addNewBeach(ModBlocks.whiteSand, 0, world, random, pos, 16, 16, 30, 32, sandFrequency, world.getSeaLevel() - 2, world.getSeaLevel() + 1, Blocks.GRAVEL);
			}
		}
	}

	private void addNewBeach(Block block, int metadata, World world, Random random, BlockPos pos, int maxX, int maxZ, int minVeinSize, int maxVeinSize, int chanceToSpawn, int minY, int maxY, Block generateIn) {
		if (minY < 0 || maxY > 256 || minY > maxY) throw new IllegalArgumentException("Illegal Height Arguments for WorldGenerator");
		int coalVeinSize = minVeinSize + random.nextInt(1 + (maxVeinSize - minVeinSize));
		for (int i = 0; i < chanceToSpawn + 1; i++) {
			int x = pos.getX() + random.nextInt(maxX);
			int y = minY + random.nextInt(1 + (maxY - minY));
			int z = pos.getZ() + random.nextInt(maxZ);
			BlockPos blockpos = new BlockPos(x, y, z);
            IBlockState state = block.getStateFromMeta(metadata);
			WorldGenMinable mine = new WorldGenMinable(state, coalVeinSize + 1, BlockMatcher.forBlock(generateIn));
			mine.generate(world, random, new BlockPos(x, y, z));
		}
	}

	private boolean isBiome(Biome biome, Type type) {
		return BiomeDictionary.isBiomeOfType(biome, type);
	}
}