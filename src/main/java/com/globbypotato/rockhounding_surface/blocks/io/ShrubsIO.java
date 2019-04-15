package com.globbypotato.rockhounding_surface.blocks.io;

import java.util.Random;

import com.globbypotato.rockhounding_surface.utils.ModUtils;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ShrubsIO extends PlantMetaIO implements IPlantable, IGrowable{

	public ShrubsIO(String name, Material material, String[] array ){
        super(name, array, material);
        setTickRandomly(true);
        this.disableStats();
    }

	@Override
    public boolean isOpaqueCube(IBlockState state){
        return false;
    }

	@Override
	public boolean isFullBlock(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
    	super.onBlockAdded(worldIn, pos, state);
    }

    @Override
	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
        return false;
	}

	@Override
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		return false;
	}

	@Override
	public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
        return EnumPlantType.Desert;
	}

	@Override
	public IBlockState getPlant(IBlockAccess world, BlockPos pos) {
        return this.getDefaultState();
	}
	
    @Override
	@SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer(){
        return BlockRenderLayer.CUTOUT;
    }
    
    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos){
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
        checkSoil(state, worldIn, pos);
    }

    private void checkSoil(IBlockState state, World worldIn, BlockPos pos) {
        BlockPos soilPos = new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ());
        IBlockState soilState = worldIn.getBlockState(soilPos);
        if(!ModUtils.isValidSoil(soilState)){
            this.checkAndDropBlock(worldIn, pos, state);
        }
	}

	protected void checkAndDropBlock(World worldIn, BlockPos pos, IBlockState state) {
		if(!canStay(worldIn, pos)){
	        this.dropBlockAsItem(worldIn, pos, state, 0);
	        if(!worldIn.isRemote){
	        	worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
	        }
		}
	}

	private static boolean canStay(World worldIn, BlockPos pos) {
        BlockPos soilPos = new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ());
        IBlockState soilState = worldIn.getBlockState(soilPos);
		return ModUtils.isValidSoil(soilState);
	}

	@Override
    public boolean isTopSolid(IBlockState state){
        return false;
    }

	@Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face){
        return BlockFaceShape.UNDEFINED;
    }

}