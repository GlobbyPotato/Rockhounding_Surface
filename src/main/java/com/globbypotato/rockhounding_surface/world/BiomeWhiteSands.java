package com.globbypotato.rockhounding_surface.world;

import java.util.Random;

import com.globbypotato.rockhounding_surface.ModBlocks;
import com.globbypotato.rockhounding_surface.handler.Reference;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenMinable;

public class BiomeWhiteSands extends Biome {

	public BiomeWhiteSands(BiomeProperties properties) {
		super(properties);
        this.setRegistryName(new ResourceLocation(Reference.MODID, "white_sands"));
		this.topBlock = ModBlocks.WHITE_SAND.getDefaultState();
		this.fillerBlock = ModBlocks.WHITE_SAND.getDefaultState();
		this.decorator.dirtGen = new WorldGenMinable(Blocks.DIRT.getDefaultState(), 16);
		this.decorator.clayPerChunk = 6;
        this.decorator.treesPerChunk = -999;
        this.spawnableCreatureList.clear();
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityRabbit.class, 4, 2, 3));
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntitySilverfish.class, 3, 3, 4));
	}

	@Override
    public void decorate(World worldIn, Random rand, BlockPos pos){
        super.decorate(worldIn, rand, pos);

        // driftwood
        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, pos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.FOSSIL)){
            new FossilGenerator().generateDune(worldIn, rand, pos);
    	}

        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, pos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.FLOWERS)){
        	//dead grass
        	if(rand.nextInt(3) == 0){
	        	for (int bush = 0; bush < 200; ++bush){
		    		int x = pos.getX() + rand.nextInt(14) + 1; 
		    		int y = rand.nextInt(30) + 60; 
		    		int z = pos.getZ() + rand.nextInt(14) + 1;
		    		BlockPos soilPos = new BlockPos(x, y, z);
			        if (worldIn.getBlockState(soilPos).equals(this.topBlock) ){
			    		BlockPos bushPos1 = new BlockPos(x, y + 1, z);
				        if (isBlockAir(worldIn, bushPos1)){
			  		    	IBlockState bushState1 = ModBlocks.GYPSUM_CROPS.getStateFromMeta(2);
			  		    	worldIn.setBlockState(bushPos1, bushState1);
				        }
			        }
		        }
	        }
        	//tall grass
	        if(rand.nextInt(3) == 0){
	        	for (int bush = 0; bush < 80; bush++){
		    		int x = pos.getX() + rand.nextInt(14) + 1; 
		    		int y = rand.nextInt(30) + 60; 
		    		int z = pos.getZ() + rand.nextInt(14) + 1;
		    		BlockPos soilPos = new BlockPos(x, y, z);
			        if (worldIn.getBlockState(soilPos).equals(this.topBlock) ){
			    		BlockPos bushPos1 = new BlockPos(x, y + 1, z);
			    		BlockPos bushPos2 = new BlockPos(x, y + 2, z);
				        if (isBlockAir(worldIn, bushPos1) && isBlockAir(worldIn, bushPos2) ){
			  		    	IBlockState bushState1 = ModBlocks.GYPSUM_BUSH_LO.getStateFromMeta(5);
			  		    	IBlockState bushState2 = ModBlocks.GYPSUM_BUSH_HI.getStateFromMeta(5);
			  		    	worldIn.setBlockState(bushPos1, bushState1);
			  		    	worldIn.setBlockState(bushPos2, bushState2);
				        }
			        }
		        }
	        }
        	//ricegrass
	        if(rand.nextInt(4) == 0){
	        	for (int bush = 0; bush < 30; ++bush){
		    		int x = pos.getX() + rand.nextInt(14) + 1; 
		    		int y = rand.nextInt(50) + 50; 
		    		int z = pos.getZ() + rand.nextInt(14) + 1;
		    		BlockPos soilPos = new BlockPos(x, y, z);
			        if (worldIn.getBlockState(soilPos).equals(this.topBlock) ){
			    		BlockPos bushPos1 = new BlockPos(x, y + 1, z);
			    		BlockPos bushPos2 = new BlockPos(x, y + 2, z);
				        if (isBlockAir(worldIn, bushPos1) && isBlockAir(worldIn, bushPos2) ){
			  		    	IBlockState bushState1 = ModBlocks.GYPSUM_BUSH_LO.getStateFromMeta(0);
			  		    	IBlockState bushState2 = ModBlocks.GYPSUM_BUSH_HI.getStateFromMeta(0);
			  		    	worldIn.setBlockState(bushPos1, bushState1);
			  		    	worldIn.setBlockState(bushPos2, bushState2);
				        }
			        }
		        }
	        }
        	//soaptree
	        if(rand.nextInt(4) == 0){
		        for (int bush = 0; bush < 30; ++bush){
		    		int x = pos.getX() + rand.nextInt(14) + 1; 
		    		int y = rand.nextInt(50) + 50; 
		    		int z = pos.getZ() + rand.nextInt(14) + 1;
		    		BlockPos soilPos = new BlockPos(x, y, z);
			        if (worldIn.getBlockState(soilPos).equals(this.topBlock) ){
			    		BlockPos bushPos1 = new BlockPos(x, y + 1, z);
			    		BlockPos bushPos2 = new BlockPos(x, y + 2, z);
				        if (isBlockAir(worldIn, bushPos1) && isBlockAir(worldIn, bushPos2) ){
			  		    	IBlockState bushState1 = ModBlocks.GYPSUM_BUSH_LO.getStateFromMeta(1);
			  		    	IBlockState bushState2 = ModBlocks.GYPSUM_BUSH_HI.getStateFromMeta(1);
			  		    	worldIn.setBlockState(bushPos1, bushState1);
			  		    	worldIn.setBlockState(bushPos2, bushState2);
				        }
			        }
		        }
	        }
	        //spoon
	        if(rand.nextInt(5) == 0){
		        for (int bush = 0; bush < 30; ++bush){
		    		int x = pos.getX() + rand.nextInt(14) + 1; 
		    		int y = rand.nextInt(50) + 50; 
		    		int z = pos.getZ() + rand.nextInt(14) + 1;
		    		BlockPos soilPos = new BlockPos(x, y, z);
			        if (worldIn.getBlockState(soilPos).equals(this.topBlock) ){
			    		BlockPos bushPos1 = new BlockPos(x, y + 1, z);
			    		BlockPos bushPos2 = new BlockPos(x, y + 2, z);
				        if (isBlockAir(worldIn, bushPos1) && isBlockAir(worldIn, bushPos2) ){
			  		    	IBlockState bushState1 = ModBlocks.GYPSUM_BUSH_LO.getStateFromMeta(2);
			  		    	IBlockState bushState2 = ModBlocks.GYPSUM_BUSH_HI.getStateFromMeta(2);
			  		    	worldIn.setBlockState(bushPos1, bushState1);
			  		    	worldIn.setBlockState(bushPos2, bushState2);
				        }
			        }
		        }
	        }
	        //pear
	        if(rand.nextInt(6) == 0){
		        for (int bush = 0; bush < 10; ++bush){
		    		int x = pos.getX() + rand.nextInt(14) + 1; 
		    		int y = rand.nextInt(50) + 50; 
		    		int z = pos.getZ() + rand.nextInt(14) + 1;
		    		BlockPos soilPos = new BlockPos(x, y, z);
			        if (worldIn.getBlockState(soilPos).equals(this.topBlock) ){
			    		BlockPos bushPos1 = new BlockPos(x, y + 1, z);
			    		BlockPos bushPos2 = new BlockPos(x, y + 2, z);
				        if (isBlockAir(worldIn, bushPos1) && isBlockAir(worldIn, bushPos2) ){
			  		    	IBlockState bushState1 = ModBlocks.GYPSUM_BUSH_LO.getStateFromMeta(3);
			  		    	IBlockState bushState2 = ModBlocks.GYPSUM_BUSH_HI.getStateFromMeta(3);
			  		    	worldIn.setBlockState(bushPos1, bushState1);
			  		    	worldIn.setBlockState(bushPos2, bushState2);
				        }
			        }
		        }
	        }
	        //rose
	        if(rand.nextInt(4) == 0){
		        for (int bush = 0; bush < 15; ++bush){
		    		int x = pos.getX() + rand.nextInt(14) + 1; 
		    		int y = rand.nextInt(50) + 50;
		    		int z = pos.getZ() + rand.nextInt(14) + 1;
		    		BlockPos soilPos = new BlockPos(x, y, z);
			        if (worldIn.getBlockState(soilPos).equals(this.topBlock) ){
			    		BlockPos bushPos1 = new BlockPos(x, y + 1, z);
			    		BlockPos bushPos2 = new BlockPos(x, y + 2, z);
				        if (isBlockAir(worldIn, bushPos1) && isBlockAir(worldIn, bushPos2) ){
			  		    	IBlockState bushState1 = ModBlocks.GYPSUM_BUSH_LO.getStateFromMeta(4);
			  		    	IBlockState bushState2 = ModBlocks.GYPSUM_BUSH_HI.getStateFromMeta(4);
			  		    	worldIn.setBlockState(bushPos1, bushState1);
			  		    	worldIn.setBlockState(bushPos2, bushState2);
				        }
			        }
		        }
	        }
	        //skunkbush
	        if(rand.nextInt(3) == 0){
		        for (int bush = 0; bush < 8; ++bush){
		    		int x = pos.getX() + rand.nextInt(14) + 1; 
		    		int y = rand.nextInt(50) + 50; 
		    		int z = pos.getZ() + rand.nextInt(14) + 1;
		    		BlockPos soilPos = new BlockPos(x, y, z);
			        if (worldIn.getBlockState(soilPos).equals(this.topBlock) ){
			    		BlockPos bushPos1 = new BlockPos(x, y + 1, z);
				        if (isBlockAir(worldIn, bushPos1)){
			  		    	IBlockState bushState1 = ModBlocks.GYPSUM_CROPS.getStateFromMeta(rand.nextInt(2));
			  		    	worldIn.setBlockState(bushPos1, bushState1);
				        }
			        }
		        }
	        }
        }
	}

	private static boolean isBlockAir(World world, BlockPos pos) {
		return world.getBlockState(pos).getBlock().isAir(world.getBlockState(pos), world, pos);
	}

}