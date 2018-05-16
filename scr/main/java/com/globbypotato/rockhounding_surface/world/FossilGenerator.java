package com.globbypotato.rockhounding_surface.world;

import java.util.Random;

import com.globbypotato.rockhounding_surface.ModBiomes;
import com.globbypotato.rockhounding_surface.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fml.common.IWorldGenerator;

public class FossilGenerator implements IWorldGenerator {
	public static int fossilRarity;
	public static int fossilSize;
	public static boolean ENABLE_OAK; //
	public static boolean ENABLE_SPRUCE; //
	public static boolean ENABLE_KAURI; //
	public static boolean ENABLE_MOPHANE; //
	public static boolean ENABLE_PALM; //
	public static boolean ENABLE_PINE; //
	public static boolean ENABLE_DRIFTWOOD; //
	public static boolean ENABLE_ARAUCARIA; //
	public static boolean ENABLE_BETULA; //
	public static boolean ENABLE_CASTANO; //
	public static boolean ENABLE_OPALIZED; //
	public static boolean ENABLE_RAINBOW; //
	public static boolean ENABLE_AZURITE; //
	public static boolean ENABLE_TEREDO; //
	public static boolean ENABLE_BEECH; //
	public static boolean ENABLE_REDWOOD; //

	Random rand = new Random();
	IBlockState state;
	private int tweakedRarity;
	private int oneCheck;

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		switch (world.provider.getDimension()) {
			case 0:
				if(world.getWorldInfo().getTerrainType() != WorldType.FLAT){
					generateOverworld(world, random, new BlockPos(chunkX * 16, 64, chunkZ * 16)); 
					if(ENABLE_DRIFTWOOD){
						generateBeach(world, random, new BlockPos(chunkX * 16, 64, chunkZ * 16));
					}
					if(ENABLE_PINE){
						generateDead(world, random, new BlockPos(chunkX * 16, 64, chunkZ * 16));
					}
					if(ENABLE_KAURI){
						generateBuried(world, random, new BlockPos(chunkX * 16, 64, chunkZ * 16));
					}
					if(ENABLE_SPRUCE){
						generateLog(world, random, new BlockPos(chunkX * 16, 64, chunkZ * 16));
					}
					if(ENABLE_TEREDO){
						generateDeep(world, random, new BlockPos(chunkX * 16, 64, chunkZ * 16));
					}
					if(ENABLE_REDWOOD){
						generatePole(world, random, new BlockPos(chunkX * 16, 64, chunkZ * 16));
					}
				}
				break;
		}
	}

	private void generatePole(World world, Random random, BlockPos pos) {
		Biome biome = world.getBiome(pos);
		if(isValidTaiga(biome)){
	    	for(int tries = 0; tries < getDivisionOf(4); tries++){
		    	int x = pos.getX() + random.nextInt(6) + 4; int y = random.nextInt(50) + 50; int z = pos.getZ() + random.nextInt(6) + 4;
		        BlockPos.MutableBlockPos blockPos = new BlockPos.MutableBlockPos();
		        blockPos.setPos(x, y, z);
		        if(world.getBlockState(blockPos).equals(biome.topBlock) ){
		        	for(int size = 0; size < 2 + rand.nextInt(fossilSize); size++){
				        blockPos.setPos(x, y - size, z);
		  		    	state = ModBlocks.coldLogs.getStateFromMeta(3);
			        	world.setBlockState(blockPos, state);
		        	}
		        	for(int size = 2; size < 4 + rand.nextInt(fossilSize); size++){
				        blockPos.setPos(x-1, y - size, z-1);
		  		    	state = ModBlocks.coldLogs.getStateFromMeta(3);
			        	world.setBlockState(blockPos, state);
		        	}
		        	for(int size = 2; size < 4 + rand.nextInt(fossilSize); size++){
				        blockPos.setPos(x+1, y - size, z+1);
		  		    	state = ModBlocks.coldLogs.getStateFromMeta(3);
			        	world.setBlockState(blockPos, state);
		        	}
		        	for(int size = 2; size < 4 + rand.nextInt(fossilSize); size++){
				        blockPos.setPos(x+1, y - size, z-1);
		  		    	state = ModBlocks.coldLogs.getStateFromMeta(3);
			        	world.setBlockState(blockPos, state);
		        	}
		        	for(int size = 2; size < 4 + rand.nextInt(fossilSize); size++){
				        blockPos.setPos(x-1, y - size, z+1);
		  		    	state = ModBlocks.coldLogs.getStateFromMeta(3);
			        	world.setBlockState(blockPos, state);
		        	}
		        }
	    	}
		}
	}

	private void generateDeep(World world, Random random, BlockPos pos) {
		Biome biome = world.getBiome(pos);
		if(isBiome(biome, Type.BEACH)){
	    	for(int tries = 0; tries < getDivisionOf(1); tries++){
		    	int x = pos.getX() + random.nextInt(6) + 4; int y = random.nextInt(30) + 30; int z = pos.getZ() + random.nextInt(6) + 4;
		        BlockPos.MutableBlockPos blockPos = new BlockPos.MutableBlockPos();
		        blockPos.setPos(x, y, z);
		        if(world.getBlockState(blockPos).isOpaqueCube() ){
		        	for(int size = 1; size < 1 + rand.nextInt(fossilSize); size++){
				        blockPos.setPos(x, y + size, z);
				        if(world.getBlockState(blockPos).getMaterial() == Material.WATER ){
			  		    	state = ModBlocks.coldLogs.getStateFromMeta(1);
				        	world.setBlockState(blockPos, state);
				        }
		        	}
		        }
	    	}
		}
		if(isBiome(biome, Type.OCEAN)){
	    	for(int tries = 0; tries < getDivisionOf(1); tries++){
		    	int x = pos.getX() + random.nextInt(6) + 4; int y = random.nextInt(30) + 30; int z = pos.getZ() + random.nextInt(6) + 4;
		        BlockPos.MutableBlockPos blockPos = new BlockPos.MutableBlockPos();
		        blockPos.setPos(x, y, z);
		        if(y > 50 && world.getBlockState(blockPos).isOpaqueCube() ){
		        	for(int size = 1; size < 1 + rand.nextInt(fossilSize); size++){
				        blockPos.setPos(x, y + size, z);
				        if(world.getBlockState(blockPos).getMaterial() == Material.WATER ){
			  		    	state = ModBlocks.coldLogs.getStateFromMeta(1);
				        	world.setBlockState(blockPos, state);
				        }
		        	}
		        }
	    	}
		}
	}

	private void generateLog(World world, Random random, BlockPos pos) {
		//spruce
		Biome biome = world.getBiome(pos);
		if(isValidTaiga(biome)){
	    	for(int tries = 0; tries < getDivisionOf(2); tries++){
		    	int x = pos.getX() + random.nextInt(6) + 4; int y = random.nextInt(50) + 50; int z = pos.getZ() + random.nextInt(6) + 4;
		        BlockPos.MutableBlockPos blockPos = new BlockPos.MutableBlockPos();
		        blockPos.setPos(x, y, z);
		        if (world.getBlockState(blockPos).equals(biome.topBlock) ){
					locateLog(world, blockPos, x, y, z);
				}
	    	}
        }
	}
			private void locateLog(World world, MutableBlockPos blockPos, int x, int y, int z) {
				for(int yy = -2; yy <= 2; yy++){
					if(yy == 0){
						for(int xx = -1; xx <= 1; xx++){
							for(int zz = -1; zz <= 1; zz++){
								placeLog(world, blockPos, x, y, z, xx, yy, zz);
							}
						}
					}else if(yy == -1 || yy == 1){
						for(int xx = -1; xx <= 1; xx++){
							for(int zz = -1; zz <= 1; zz++){
								if((xx == 1 && zz == 0) || (xx == -1 && zz == 0) || (xx == 0 && zz == 1) || (xx == 0 && zz == -1)){
									placeLog(world, blockPos, x, y, z, xx, yy, zz);
								}
							}
						}
					}else if(yy == -2 || yy == 2){
						placeLog(world, blockPos, x, y, z, 0, yy, 0);
					}
				}
			}

			private void placeLog(World world, MutableBlockPos blockPos, int x, int y, int z, int xx, int yy, int zz) {
				blockPos.setPos(x + xx, y + yy, z + zz);
  		    	state = ModBlocks.bogLogs.getStateFromMeta(1);
  		    	world.setBlockState(blockPos, state);
			}

	private void generateBuried(World world, Random random, BlockPos pos) {
		Biome biome = world.getBiome(pos);
	    if(isBiome(biome, Type.SWAMP)){
	    	for(int tries = 0; tries < getDivisionOf(10); tries++){
		    	int x = pos.getX() + random.nextInt(6) + 4; int y = random.nextInt(26) + 50; int z = pos.getZ() + random.nextInt(6) + 4;
		        BlockPos.MutableBlockPos blockPos = new BlockPos.MutableBlockPos();
		        blockPos.setPos(x, y, z);
		        if (world.getBlockState(blockPos).equals(biome.topBlock) ){
		        	//spawn the core
					for(int xx = -2; xx <= 2; xx++){
						for(int zz = -2; zz <= 2; zz++){
							placeBuried(world, rand.nextInt(fossilSize) + 3, rand.nextInt(3), blockPos, x, y, z, xx, zz);
						}
					}
					//spawn sides
					for(int xx = -1; xx <= 1; xx++){
						placeBuried(world, rand.nextInt(fossilSize) + 3, rand.nextInt(3), blockPos, x, y, z, xx, 3);
						placeBuried(world, rand.nextInt(fossilSize) + 3, rand.nextInt(3), blockPos, x, y, z, xx, -3);
						placeBuried(world, rand.nextInt(fossilSize) + 3, rand.nextInt(3), blockPos, x, y, z, 3, xx);
						placeBuried(world, rand.nextInt(fossilSize) + 3, rand.nextInt(3), blockPos, x, y, z, -3, xx);
					}
		        }
	    	}
	    }
	}
			private void placeBuried(World world, int randSize, int randGap, MutableBlockPos blockPos, int x, int y, int z, int xx, int zz) {
				for(int yy = randGap; yy <= randSize; yy++){
			        blockPos.setPos(x + xx, y - yy, z + zz);
				    state = ModBlocks.bogLogs.getStateFromMeta(2);
				    world.setBlockState(blockPos, state);
				}
			}

	private void generateDead(World world, Random random, BlockPos pos) {
		Biome biome = world.getBiome(pos);
	    if(isBiome(biome, Type.SWAMP)){
	    	for(int tries = 0; tries < getDivisionOf(4); tries++){
		    	int x = pos.getX() + random.nextInt(6) + 4; int y = random.nextInt(50) + 50; int z = pos.getZ() + random.nextInt(6) + 4;
		        BlockPos.MutableBlockPos blockPos = new BlockPos.MutableBlockPos();
		        blockPos.setPos(x, y, z);
		        if (world.getBlockState(blockPos).equals(biome.topBlock) ){
		        	//centre
		        	placePillar(world, rand.nextInt(fossilSize) + 2, blockPos, x, y, z);
		        	//sides
		        	placePillar(world, rand.nextInt(fossilSize / 2) + 2, blockPos, x + 1, y, z);
		        	placePillar(world, rand.nextInt(fossilSize / 2) + 2, blockPos, x - 1, y, z);
		        	placePillar(world, rand.nextInt(fossilSize / 2) + 2, blockPos, x, y, z + 1);
		        	placePillar(world, rand.nextInt(fossilSize / 2) + 2, blockPos, x, y, z - 1);
		        }
	        }
	    }
	}
			private void placePillar(World world, int randSize, MutableBlockPos blockPos, int x, int y, int z) {
				for(int hi = 0; hi < randSize; hi++){
					blockPos.setPos(x, y + hi, z);
					if(!world.getBlockState(blockPos).isOpaqueCube()){
		  		    	state = ModBlocks.fossilLogs.getStateFromMeta(1);
		  		    	world.setBlockState(blockPos, state);
					}
				}
			}

	public void generateBeach(World world, Random random, BlockPos pos) {
		//if is a beach
		Biome biome = world.getBiome(pos);
		if(isBiome(biome, Type.BEACH)){
	    	for(int tries = 0; tries < getDivisionOf(6); tries++){
		    	//get a point
	    		int x = pos.getX() + random.nextInt(6) + 4; int y = random.nextInt(5) + 61; int z = pos.getZ() + random.nextInt(6) + 4;
		        BlockPos.MutableBlockPos blockPos = new BlockPos.MutableBlockPos();
		        blockPos.setPos(x, y, z);
		        if (world.getBlockState(blockPos).equals(biome.topBlock) || world.getBlockState(blockPos).equals(Blocks.GRASS) ){
					//check surrounding
			        for(int yy = 0; yy <= 1; yy++){
						for(int xx = -2; xx <= 2; xx++){
							for(int zz = -2; zz <= 2; zz++){
						        //check is is sustained
								blockPos.setPos(x + xx, y + yy - 1, z + zz);
						        if(world.getBlockState(blockPos).isOpaqueCube() ){
						        	//decide to spawn
							        blockPos.setPos(x + xx, y + yy, z + zz);
							        if(!world.getBlockState(blockPos).isOpaqueCube() ){
							        	if(rand.nextInt(3) == 0){
							  		    	state = ModBlocks.fossilLogs.getStateFromMeta((rand.nextInt(3) * 4) + 2);
							  		    	world.setBlockState(blockPos, state);
								        }
						        	}
						        }
							}
						}
			        }
		        }
	    	}
		}
	}

	public void generateDune(World world, Random random, BlockPos pos) {
		//if is a white sand dune
		if(ENABLE_DRIFTWOOD){
			Biome biome = world.getBiome(pos);
			if(BiomeDictionary.areBiomesEquivalent(biome, ModBiomes.WHITE_SANDS)){
		    	for(int tries = 0; tries < getDivisionOf(6); tries++){
			    	//get a point
		    		int x = pos.getX() + random.nextInt(6) + 4; int y = random.nextInt(50) + 50; int z = pos.getZ() + random.nextInt(6) + 4;
			        BlockPos.MutableBlockPos blockPos = new BlockPos.MutableBlockPos();
			        blockPos.setPos(x, y, z);
			        if (world.getBlockState(blockPos).equals(biome.topBlock) ){
						//check surrounding
				        for(int yy = 0; yy <= 1; yy++){
							for(int xx = -2; xx <= 2; xx++){
								for(int zz = -2; zz <= 2; zz++){
							        //check is is sustained
									blockPos.setPos(x + xx, y + yy - 1, z + zz);
							        if(world.getBlockState(blockPos).isOpaqueCube() ){
							        	//decide to spawn
								        blockPos.setPos(x + xx, y + yy, z + zz);
								        if(!world.getBlockState(blockPos).isOpaqueCube() ){
								        	if(rand.nextInt(3) == 0){
								  		    	state = ModBlocks.fossilLogs.getStateFromMeta((rand.nextInt(3) * 4) + 2);
								  		    	world.setBlockState(blockPos, state);
									        }
							        	}
							        }
								}
							}
				        }
			        }
		    	}
			}
		}
	}

	private void generateOverworld(World world, Random random, BlockPos pos) {
		Biome biome = world.getBiome(pos);
		//tweak frequency
	    if(isBiome(biome, Type.JUNGLE)){
	    	tweakedRarity = 1 + (getMultiplyOf(4));
	    }else if(isBiome(biome, Type.BEACH)){
	    	tweakedRarity = 1 + (getDivisionOf(1));
	    }else if(isBiome(biome, Type.SANDY)){
	    	tweakedRarity = 1 + (getDivisionOf(2));
	    }else if(isBiome(biome, Type.FOREST)){
	    	tweakedRarity = 1 + (getMultiplyOf(4));
	    }else if(isBiome(biome, Type.RIVER)){
	    	tweakedRarity = 1 + (getMultiplyOf(5));
	    }else if(isBiome(biome, Type.SWAMP)){
	    	tweakedRarity = 1 + (getDivisionOf(1) + (getDivisionOf(2)));
	    }else if(isBiome(biome, Type.CONIFEROUS)){
	    	tweakedRarity = 1 + (getMultiplyOf(2));
	    }else if(isBiome(biome, Type.PLAINS)){
	    	tweakedRarity = 1 + (getDivisionOf(1) - (getDivisionOf(3)));
	    }else if(isValidHill(biome)){
	    	tweakedRarity = 1 + (getMultiplyOf(3));
	    }else if(isValidAlps(biome)){
	    	tweakedRarity = 1 + (getMultiplyOf(2));
	    }else if(isValidOcean(biome)){
	    	tweakedRarity = 1 + (getMultiplyOf(2));
	    }else{
	    	tweakedRarity = 1 + getDivisionOf(1);
	    }

	    for (int i = 0; i < tweakedRarity; ++i){
	    	//get a random block pos
	    	int x = pos.getX() + random.nextInt(6) + 4; int y = random.nextInt(250) + 1; int z = pos.getZ() + random.nextInt(6) + 4;
	    	oneCheck = 0;
	    	BlockPos.MutableBlockPos trunkPos = new BlockPos.MutableBlockPos();
	        trunkPos.setPos(x, y, z);

          //check surface
	        if (world.getBlockState(trunkPos).equals(biome.topBlock) ){
	        	trunkPos.setPos(x, y + 1, z);
	        	if (!world.getBlockState(trunkPos).isOpaqueCube()){
	        		//set size and orientation
	        		Block woodType; int woodVariant = 0;
	        		//palmoxilon
	        		if(ENABLE_PALM && isValidJungle(biome)){
	        			selectTrunk(world, biome, trunkPos, x, y, z, ModBlocks.fossilLogs, 0);
	        		}
	        		//opalized
	        		if(ENABLE_OPALIZED && isValidSavanna(biome)){
	        			selectTrunk(world, biome, trunkPos, x, y, z, ModBlocks.petrifiedLogs, 2);
	        		}
	        		//rainbow
	        		if(ENABLE_RAINBOW && isValidDesert(biome)){
	        			selectTrunk(world, biome, trunkPos, x, y, z, ModBlocks.petrifiedLogs, 3);
	        		}
	        		//araucaria
	        		if(ENABLE_ARAUCARIA && isValidForest(biome)){
	        			selectTrunk(world, biome, trunkPos, x, y, z, ModBlocks.fossilLogs, 3);
	        		}
	        		//bog oak
	        		if(ENABLE_OAK && isValidBog(biome)){
	        			selectTrunk(world, biome, trunkPos, x, y, z, ModBlocks.bogLogs, 0);
	        		}
	    			//betula
	    			if(ENABLE_BETULA && isValidPlain(biome)){
		        		selectTrunk(world, biome, trunkPos, x, y, z, ModBlocks.petrifiedLogs, 0);
	    			}
	    			//castano
	    			if(ENABLE_CASTANO && isValidHill(biome)){
		        		selectTrunk(world, biome, trunkPos, x, y, z, ModBlocks.petrifiedLogs, 1);
	    			}
		        	//mophane
	    			if(ENABLE_MOPHANE && isValidWet(biome)){
		        		selectTrunk(world, biome, trunkPos, x, y, z, ModBlocks.bogLogs, 3);
	    			}
		        	//beech
	    			if(ENABLE_BEECH && isValidPak(biome)){
		        		selectTrunk(world, biome, trunkPos, x, y, z, ModBlocks.coldLogs, 2);
	    			}
		        	//azurite
	    			if(ENABLE_AZURITE && isValidAlps(biome)){
		        		selectTrunk(world, biome, trunkPos, x, y, z, ModBlocks.coldLogs, 0);
	    			}
	        	}
	        }
	    }
	}

			private boolean isValidOcean(Biome biome) {
				return isBiome(biome, Type.OCEAN);
			}
			private boolean isValidAlps(Biome biome) {
				return isBiome(biome, Type.SNOWY) && (isBiome(biome, Type.HILLS) || isBiome(biome, Type.MOUNTAIN));
			}
			private boolean isValidPak(Biome biome) {
				return isBiome(biome, Type.SNOWY) && !isBiome(biome, Type.HILLS) && !isBiome(biome, Type.MOUNTAIN)  && !isBiome(biome, Type.BEACH);
			}
			private boolean isValidPlain(Biome biome) {
				return !isBiome(biome, Type.SNOWY) &&
						isBiome(biome, Type.PLAINS);
			}
			private boolean isValidDesert(Biome biome) {
				return isBiome(biome, Type.SANDY);
			}
			private boolean isValidJungle(Biome biome) {
				return  isBiome(biome, Type.JUNGLE) || 
						isBiome(biome, Type.MUSHROOM);
			}
			private boolean isValidSavanna(Biome biome) {
				return  isBiome(biome, Type.SAVANNA) || 
						isBiome(biome, Type.SPARSE) && 
						!isBiome(biome, Type.HILLS);
			}
			private boolean isValidBog(Biome biome) {
				return  isBiome(biome, Type.SWAMP) || 
						isBiome(biome, Type.RIVER);
			}
			private boolean isValidTaiga(Biome biome) {
				return  (isBiome(biome, Type.CONIFEROUS) || 
						isBiome(biome, Type.DENSE) || 
						isBiome(biome, Type.SPOOKY) || 
						isBiome(biome, Type.LUSH)) &&
						!isBiome(biome, Type.JUNGLE);
			}
			private boolean isValidHill(Biome biome) {
				return  !isBiome(biome, Type.SNOWY) &&
						!isBiome(biome, Type.FOREST) && 
						(isBiome(biome, Type.HILLS) || 
						isBiome(biome, Type.MOUNTAIN));
			}
		    private boolean isValidWet(Biome biome) {
				return  !isBiome(biome, Type.SAVANNA) && 
						!isBiome(biome, Type.SANDY) && 
						!isBiome(biome, Type.JUNGLE) &&
						(isBiome(biome, Type.HOT) || isBiome(biome, Type.WET));
			}
			private boolean isValidForest(Biome biome) {
				return   isBiome(biome, Type.FOREST) && 
						!isBiome(biome, Type.CONIFEROUS) && 
						!isBiome(biome, Type.DENSE) &&
						!isBiome(biome, Type.LUSH);
			}

			private void selectTrunk(World world, Biome biome, MutableBlockPos trunkPos, int x, int y, int z, Block woodType, int woodVariant){
				if(oneCheck == 0){
					spawnTrunk(world, biome, trunkPos, x, y, z, woodType, woodVariant);
				}
			}
			private void spawnTrunk(World world, Biome biome, MutableBlockPos trunkPos, int x, int y, int z, Block woodType, int woodVariant){
				int getSize = (rand.nextInt(fossilSize) / 2) + 2;
				int getDirection = rand.nextInt(4) + 2;
				int side = 0; int sizeX = 0; int sizeZ = 0;
				if(getDirection == 2){
					sizeX = 1; sizeZ = 0; side = 4;
				}else if(getDirection == 4){
					sizeX = -1; sizeZ = 0; side = 4;
				}else if(getDirection == 3){
					sizeX = 0; sizeZ = 1; side = 8;
				}else if(getDirection == 5){
					sizeX = 0; sizeZ = -1; side = 8;
				}
				//place blocks
				for(int size = 0; size < getSize; size++){
					trunkPos.setPos(x + (size * sizeX), y, z + (size * sizeZ));
			        if (world.getBlockState(trunkPos).equals(biome.topBlock) ){
			        	trunkPos.setPos(x + (size * sizeX), y + 1, z + (size * sizeZ));
			        	if (!world.getBlockState(trunkPos).isOpaqueCube()){
			  		    	state = woodType.getStateFromMeta(woodVariant + side);
			  		    	world.setBlockState(trunkPos, state);
			  		    	oneCheck = 1;
			        	}
			        }
					trunkPos.setPos(x - (size * sizeX), y, z - (size * sizeZ));
			        if (world.getBlockState(trunkPos).equals(biome.topBlock) ){
			        	trunkPos.setPos(x - (size * sizeX), y + 1, z - (size * sizeZ));
			        	if (!world.getBlockState(trunkPos).isOpaqueCube()){
			  		    	state = woodType.getStateFromMeta(woodVariant + side);
			  		    	world.setBlockState(trunkPos, state);
			  		    	oneCheck = 1;
			        	}
			        }
				}	
		    }
			
	private boolean isBiome(Biome biome, Type type) {
		return BiomeDictionary.isBiomeOfType(biome, type);
	}

	private int getDivisionOf(int i) {
		int rarity = fossilRarity / i;
		return rarity >= 1 ? rarity : 1;
	}

	private int getMultiplyOf(int i) {
		int rarity = fossilRarity * i;
		return rarity;
	}


}