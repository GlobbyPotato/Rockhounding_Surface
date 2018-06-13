package com.globbypotato.rockhounding_surface.blocks.white_sands;

import com.globbypotato.rockhounding_surface.blocks.io.MetaIO;
import com.globbypotato.rockhounding_surface.enums.EnumGypsumBlocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;

public class Gypsum extends MetaIO {
	public static final PropertyEnum VARIANT = PropertyEnum.create("variant", EnumGypsumBlocks.class);

    public Gypsum(String name, String[] array) {
		super(name, Material.ROCK, array, 2.0F, 2.0F, SoundType.STONE);
        setHarvestLevel("pickaxe", 0);
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumGypsumBlocks.values()[0]));
    }

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(VARIANT, EnumGypsumBlocks.values()[meta]);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumGypsumBlocks)state.getValue(VARIANT)).ordinal();
	}

	@Override
	public BlockStateContainer createBlockState(){
		return new BlockStateContainer(this, new IProperty[] { VARIANT });
	}

}