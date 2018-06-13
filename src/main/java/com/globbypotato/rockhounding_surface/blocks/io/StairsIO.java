package com.globbypotato.rockhounding_surface.blocks.io;

import java.util.Random;

import com.globbypotato.rockhounding_surface.handler.Reference;

import net.minecraft.block.BlockStairs;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class StairsIO extends BlockStairs{

	protected StairsIO(IBlockState state, String name, SoundType soundtype, float hardness, float resistance) {
		super(state);
		setRegistryName(name);
		setUnlocalizedName(getRegistryName().toString());
		setHardness(hardness); setResistance(resistance); setSoundType(soundtype);
		setCreativeTab(Reference.RockhoundingSurface);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(HALF, BlockStairs.EnumHalf.BOTTOM).withProperty(SHAPE, BlockStairs.EnumShape.STRAIGHT));
		this.useNeighborBrightness=true;
	}

	@Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune){
		return Item.getItemFromBlock(this);
	}

	@Override
	public int quantityDropped(Random rand) {
		return 1;
	}

	@Override
	public BlockStateContainer createBlockState(){
        return new BlockStateContainer(this, new IProperty[] {FACING, HALF, SHAPE});
    }

    @Override
	public boolean isWood(IBlockAccess world, BlockPos pos){
        return false;
    }

}