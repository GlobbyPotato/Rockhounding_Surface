package com.globbypotato.rockhounding_surface.blocks.plants;

import java.util.Random;

import com.globbypotato.rockhounding_surface.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockSand;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BaseShrubs extends BaseMetaPlant implements IPlantable, IGrowable{

	public BaseShrubs(String name, Material material, SoundType soundtype, String[] array, float hardness, float resistance ){
        super(name, array, material, soundtype, hardness, resistance);
        setTickRandomly(true);
        this.disableStats();
    }

	@Override
    public boolean isFullyOpaque(IBlockState state){
        return false;
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
	
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer(){
        return BlockRenderLayer.CUTOUT;
    }
    
    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn){
        super.neighborChanged(state, worldIn, pos, blockIn);
        checkSoil(state, worldIn, pos);
    }

    private void checkSoil(IBlockState state, World worldIn, BlockPos pos) {
        BlockPos soilPos = new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ());
        IBlockState soilState = worldIn.getBlockState(soilPos);
        if(!isValidSoil(soilState)){
            this.checkAndDropBlock(worldIn, pos, state);
        }
	}

	protected void checkAndDropBlock(World worldIn, BlockPos pos, IBlockState state) {
		if(!canStay(worldIn, pos, state)){
	        this.dropBlockAsItem(worldIn, pos, state, 0);
	        if(!worldIn.isRemote){
	        	worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
	        }
		}
	}

	private boolean canStay(World worldIn, BlockPos pos, IBlockState state) {
        BlockPos soilPos = new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ());
        IBlockState soilState = worldIn.getBlockState(soilPos);
		return isValidSoil(soilState);
	}

	private boolean isValidSoil(IBlockState state) {
		return state.getBlock() instanceof BlockGrass || state.getBlock() instanceof BlockSand || state.getBlock() instanceof BlockDirt || state.getBlock() instanceof BlockFarmland || state.getBlock() == ModBlocks.whiteSand;
	}

}