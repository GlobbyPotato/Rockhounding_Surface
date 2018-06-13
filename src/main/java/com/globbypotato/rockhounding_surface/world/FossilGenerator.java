package com.globbypotato.rockhounding_surface.world;

import java.util.Random;

import com.globbypotato.rockhounding_surface.ModBiomes;
import com.globbypotato.rockhounding_surface.ModBlocks;
import com.globbypotato.rockhounding_surface.handler.ModConfig;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fml.common.IWorldGenerator;

public class FossilGenerator implements IWorldGenerator {
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

					if(ModConfig.ENABLE_DRIFTWOOD){
						generateBeach(world, random, new BlockPos(chunkX * 16, 64, chunkZ * 16));
					}
					if(ModConfig.ENABLE_PINE){
						generateDead(world, random, new BlockPos(chunkX * 16, 64, chunkZ * 16));
					}
					if(ModConfig.ENABLE_KAURI){
						generateBuried(world, random, new BlockPos(chunkX * 16, 64, chunkZ * 16));
					}
					if(ModConfig.ENABLE_SPRUCE){
						generateLog(world, random, new BlockPos(chunkX * 16, 64, chunkZ * 16));
					}
					if(ModConfig.ENABLE_TEREDO){
						generateDeep(world, random, new BlockPos(chunkX * 16, 64, chunkZ * 16));
					}
					if(ModConfig.ENABLE_REDWOOD){
						generatePole(world, random, new BlockPos(chunkX * 16, 64, chunkZ * 16));
					}
				}
				break;
		default:
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
		        	for(int size = 0; size < 2 + this.rand.nextInt(ModConfig.fossilSize); size++){
				        blockPos.setPos(x, y - size, z);
		  		    	this.state = ModBlocks.COLD_LOGS.getStateFromMeta(3);
			        	world.setBlockState(blockPos, this.state);
		        	}
		        	for(int size = 2; size < 4 + this.rand.nextInt(ModConfig.fossilSize); size++){
				        blockPos.setPos(x-1, y - size, z-1);
		  		    	this.state = ModBlocks.COLD_LOGS.getStateFromMeta(3);
			        	world.setBlockState(blockPos, this.state);
		        	}
		        	for(int size = 2; size < 4 + this.rand.nextInt(ModConfig.fossilSize); size++){
				        blockPos.setPos(x+1, y - size, z+1);
		  		    	this.state = ModBlocks.COLD_LOGS.getStateFromMeta(3);
			        	world.setBlockState(blockPos, this.state);
		        	}
		        	for(int size = 2; size < 4 + this.rand.nextInt(ModConfig.fossilSize); size++){
				        blockPos.setPos(x+1, y - size, z-1);
		  		    	this.state = ModBlocks.COLD_LOGS.getStateFromMeta(3);
			        	world.setBlockState(blockPos, this.state);
		        	}
		        	for(int size = 2; size < 4 + this.rand.nextInt(ModConfig.fossilSize); size++){
				        blockPos.setPos(x-1, y - size, z+1);
		  		    	this.state = ModBlocks.COLD_LOGS.getStateFromMeta(3);
			        	world.setBlockState(blockPos, this.state);
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
		        	for(int size = 1; size < 1 + this.rand.nextInt(ModConfig.fossilSize); size++){
				        blockPos.setPos(x, y + size, z);
				        if(world.getBlockState(blockPos).getMaterial() == Material.WATER ){
			  		    	this.state = ModBlocks.COLD_LOGS.getStateFromMeta(1);
				        	world.setBlockState(blockPos, this.state);
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
		        	for(int size = 1; size < 1 + this.rand.nextInt(ModConfig.fossilSize); size++){
				        blockPos.setPos(x, y + size, z);
				        if(world.getBlockState(blockPos).getMaterial() == Material.WATER ){
			  		    	this.state = ModBlocks.COLD_LOGS.getStateFromMeta(1);
				        	world.setBlockState(blockPos, this.state);
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
	    	for(int tries = 0; tries < getDivisionOf(1); tries++){
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
  		    	this.state = ModBlocks.BOG_LOGS.getStateFromMeta(1);
  		    	world.setBlockState(blockPos, this.state);
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
							placeBuried(world, this.rand.nextInt(ModConfig.fossilSize) + 3, this.rand.nextInt(3), blockPos, x, y, z, xx, zz);
						}
					}
					//spawn sides
					for(int xx = -1; xx <= 1; xx++){
						placeBuried(world, this.rand.nextInt(ModConfig.fossilSize) + 3, this.rand.nextInt(3), blockPos, x, y, z, xx, 3);
						placeBuried(world, this.rand.nextInt(ModConfig.fossilSize) + 3, this.rand.nextInt(3), blockPos, x, y, z, xx, -3);
						placeBuried(world, this.rand.nextInt(ModConfig.fossilSize) + 3, this.rand.nextInt(3), blockPos, x, y, z, 3, xx);
						placeBuried(world, this.rand.nextInt(ModConfig.fossilSize) + 3, this.rand.nextInt(3), blockPos, x, y, z, -3, xx);
					}
		        }
	    	}
	    }
	}
			private void placeBuried(World world, int randSize, int randGap, MutableBlockPos blockPos, int x, int y, int z, int xx, int zz) {
				for(int yy = randGap; yy <= randSize; yy++){
			        blockPos.setPos(x + xx, y - yy, z + zz);
				    this.state = ModBlocks.BOG_LOGS.getStateFromMeta(2);
				    world.setBlockState(blockPos, this.state);
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
		        	placePillar(world, this.rand.nextInt(ModConfig.fossilSize) + 2, blockPos, x, y, z);
		        	//sides
		        	placePillar(world, this.rand.nextInt(ModConfig.fossilSize / 2) + 2, blockPos, x + 1, y, z);
		        	placePillar(world, this.rand.nextInt(ModConfig.fossilSize / 2) + 2, blockPos, x - 1, y, z);
		        	placePillar(world, this.rand.nextInt(ModConfig.fossilSize / 2) + 2, blockPos, x, y, z + 1);
		        	placePillar(world, this.rand.nextInt(ModConfig.fossilSize / 2) + 2, blockPos, x, y, z - 1);
		        }
	        }
	    }
	}
			private void placePillar(World world, int randSize, MutableBlockPos blockPos, int x, int y, int z) {
				for(int hi = 0; hi < randSize; hi++){
					blockPos.setPos(x, y + hi, z);
					if(!world.getBlockState(blockPos).isOpaqueCube()){
		  		    	this.state = ModBlocks.FOSSIL_LOGS.getStateFromMeta(1);
		  		    	world.setBlockState(blockPos, this.state);
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
							        	if(this.rand.nextInt(3) == 0){
							  		    	this.state = ModBlocks.FOSSIL_LOGS.getStateFromMeta((this.rand.nextInt(3) * 4) + 2);
							  		    	world.setBlockState(blockPos, this.state);
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
		if(ModConfig.ENABLE_DRIFTWOOD){
			Biome biome = world.getBiome(pos);
			if(BiomeDictionary.areSimilar(biome, ModBiomes.WHITE_SANDS)){
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
								        	if(this.rand.nextInt(3) == 0){
								  		    	this.state = ModBlocks.FOSSIL_LOGS.getStateFromMeta((this.rand.nextInt(3) * 4) + 2);
								  		    	world.setBlockState(blockPos, this.state);
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
	    	this.tweakedRarity = 1 + (getMultiplyOf(4));
	    }else if(isBiome(biome, Type.BEACH)){
	    	this.tweakedRarity = 1 + (getDivisionOf(1));
	    }else if(isBiome(biome, Type.SANDY)){
	    	this.tweakedRarity = 1 + (getDivisionOf(2));
	    }else if(isBiome(biome, Type.FOREST)){
	    	this.tweakedRarity = 1 + (getMultiplyOf(4));
	    }else if(isBiome(biome, Type.RIVER)){
	    	this.tweakedRarity = 1 + (getMultiplyOf(5));
	    }else if(isBiome(biome, Type.SWAMP)){
	    	this.tweakedRarity = 1 + (getDivisionOf(1) + (getDivisionOf(2)));
	    }else if(isBiome(biome, Type.CONIFEROUS)){
	    	this.tweakedRarity = 1 + (getMultiplyOf(2));
	    }else if(isBiome(biome, Type.PLAINS)){
	    	this.tweakedRarity = 1 + (getDivisionOf(1) - (getDivisionOf(3)));
	    }else if(isValidHill(biome)){
	    	this.tweakedRarity = 1 + (getMultiplyOf(3));
	    }else if(isValidAlps(biome)){
	    	this.tweakedRarity = 1 + (getMultiplyOf(2));
	    }else if(isValidOcean(biome)){
	    	this.tweakedRarity = 1 + (getMultiplyOf(2));
	    }else{
	    	this.tweakedRarity = 1 + ModConfig.fossilRarity;
	    }

	    for (int i = 0; i < this.tweakedRarity; ++i){
	    	//get a random block pos
	    	int x = pos.getX() + random.nextInt(6) + 4; int y = random.nextInt(250) + 1; int z = pos.getZ() + random.nextInt(6) + 4;
	    	this.oneCheck = 0;
	    	BlockPos.MutableBlockPos trunkPos = new BlockPos.MutableBlockPos();
	        trunkPos.setPos(x, y, z);

          //check surface
	        if (world.getBlockState(trunkPos).equals(biome.topBlock) ){
	        	trunkPos.setPos(x, y + 1, z);
	        	if (!world.getBlockState(trunkPos).isOpaqueCube()){
	        		//palmoxilon
	        		if(ModConfig.ENABLE_PALM && isValidJungle(biome)){
	        			selectTrunk(world, biome, trunkPos, x, y, z, ModBlocks.FOSSIL_LOGS, 0);
	        		}
	        		//opalized
	        		if(ModConfig.ENABLE_OPALIZED && isValidMesa(biome)){
	        			selectTrunk(world, biome, trunkPos, x, y, z, ModBlocks.PETRIFIED_LOGS, 2);
	        		}
	        		//rainbow
	        		if(ModConfig.ENABLE_RAINBOW && isValidDesert(biome)){
	        			selectTrunk(world, biome, trunkPos, x, y, z, ModBlocks.PETRIFIED_LOGS, 3);
	        		}
	        		//araucaria
	        		if(ModConfig.ENABLE_ARAUCARIA && isValidForest(biome)){
	        			selectTrunk(world, biome, trunkPos, x, y, z, ModBlocks.FOSSIL_LOGS, 3);
	        		}
	        		//bog oak
	        		if(ModConfig.ENABLE_OAK && isValidBog(biome)){
	        			selectTrunk(world, biome, trunkPos, x, y, z, ModBlocks.BOG_LOGS, 0);
	        		}
	    			//betula
	    			if(ModConfig.ENABLE_BETULA && isValidPlain(biome)){
		        		selectTrunk(world, biome, trunkPos, x, y, z, ModBlocks.PETRIFIED_LOGS, 0);
	    			}
	    			//castano
	    			if(ModConfig.ENABLE_CASTANO && isValidHill(biome)){
		        		selectTrunk(world, biome, trunkPos, x, y, z, ModBlocks.PETRIFIED_LOGS, 1);
	    			}
		        	//mophane
	    			if(ModConfig.ENABLE_MOPHANE && isValidSavanna(biome)){
		        		selectTrunk(world, biome, trunkPos, x, y, z, ModBlocks.BOG_LOGS, 3);
	    			}
		        	//beech
	    			if(ModConfig.ENABLE_BEECH && isValidPak(biome)){
		        		selectTrunk(world, biome, trunkPos, x, y, z, ModBlocks.COLD_LOGS, 2);
	    			}
		        	//azurite
	    			if(ModConfig.ENABLE_AZURITE && isValidAlps(biome)){
		        		selectTrunk(world, biome, trunkPos, x, y, z, ModBlocks.COLD_LOGS, 0);
	    			}
	        	}
	        }
	    }
	}

			private static boolean isValidOcean(Biome biome) {
				return 	isBiome(biome, Type.OCEAN);
			}
			private static boolean isValidAlps(Biome biome) {
				return	isBiome(biome, Type.SNOWY) 
					&& 	(isBiome(biome, Type.HILLS) || isBiome(biome, Type.MOUNTAIN));
			}
			private static boolean isValidPak(Biome biome) {
				return 	isBiome(biome, Type.SNOWY) 
					&& 	!isBiome(biome, Type.HILLS) 
					&& 	!isBiome(biome, Type.MOUNTAIN)
					&& 	!isBiome(biome, Type.BEACH);
			}
			private static boolean isValidPlain(Biome biome) {
				return	isBiome(biome, Type.PLAINS)
					&&	!isBiome(biome, Type.SNOWY)
					&&	!isBiome(biome, Type.SAVANNA);
			}
			private static boolean isValidDesert(Biome biome) {
				return 	isBiome(biome, Type.SANDY)
					&&	!isBiome(biome, Type.MESA);
			}
			private static boolean isValidJungle(Biome biome) {
				return  isBiome(biome, Type.JUNGLE) || isBiome(biome, Type.MUSHROOM);
			}
			private static boolean isValidMesa(Biome biome) {
				return  isBiome(biome, Type.MESA);
			}
			private static boolean isValidBog(Biome biome) {
				return 	(isBiome(biome, Type.SWAMP) || isBiome(biome, Type.RIVER))
					&&	!isBiome(biome, Type.COLD);
			}
			private static boolean isValidTaiga(Biome biome) {
				return 	(isBiome(biome, Type.CONIFEROUS) || isBiome(biome, Type.SPOOKY))
					&& 	!isBiome(biome, Type.JUNGLE);
			}
			private static boolean isValidHill(Biome biome) {
				return  !isBiome(biome, Type.SNOWY) 
					&&	!isBiome(biome, Type.SANDY) 
					&&	!isBiome(biome, Type.SAVANNA) 
					&&	!isBiome(biome, Type.FOREST) 
					&&	(isBiome(biome, Type.HILLS) || isBiome(biome, Type.MOUNTAIN));
			}
		    private static boolean isValidSavanna(Biome biome) {
				return  isBiome(biome, Type.SAVANNA);
			}
			private static boolean isValidForest(Biome biome) {
				return  isBiome(biome, Type.FOREST) 
					&&	!isBiome(biome, Type.CONIFEROUS) 
					&&	!isBiome(biome, Type.HILLS) 
					&&	!isBiome(biome, Type.DENSE) 
					&&	!isBiome(biome, Type.LUSH);
			}

			private void selectTrunk(World world, Biome biome, MutableBlockPos trunkPos, int x, int y, int z, Block woodType, int woodVariant){
				if(this.oneCheck == 0){
					spawnTrunk(world, biome, trunkPos, x, y, z, woodType, woodVariant);
				}
			}
			private void spawnTrunk(World world, Biome biome, MutableBlockPos trunkPos, int x, int y, int z, Block woodType, int woodVariant){
				int getSize = (this.rand.nextInt(ModConfig.fossilSize) / 2) + 2;
				int getDirection = this.rand.nextInt(4) + 2;
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
			  		    	this.state = woodType.getStateFromMeta(woodVariant + side);
			  		    	world.setBlockState(trunkPos, this.state);
			  		    	this.oneCheck = 1;
			        	}
			        }
					trunkPos.setPos(x - (size * sizeX), y, z - (size * sizeZ));
			        if (world.getBlockState(trunkPos).equals(biome.topBlock) ){
			        	trunkPos.setPos(x - (size * sizeX), y + 1, z - (size * sizeZ));
			        	if (!world.getBlockState(trunkPos).isOpaqueCube()){
			  		    	this.state = woodType.getStateFromMeta(woodVariant + side);
			  		    	world.setBlockState(trunkPos, this.state);
			  		    	this.oneCheck = 1;
			        	}
			        }
				}	
		    }
			
	private static boolean isBiome(Biome biome, Type type) {
		return BiomeDictionary.hasType(biome, type);
	}

	private static int getDivisionOf(int i) {
		int rarity = ModConfig.fossilRarity / i;
		return rarity >= 1 ? rarity : 1;
	}

	private static int getMultiplyOf(int i) {
		int rarity = ModConfig.fossilRarity * i;
		return rarity;
	}

}