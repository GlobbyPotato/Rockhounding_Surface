package com.globbypotato.rockhounding_surface.blocks.fossil_wood;

import com.globbypotato.rockhounding_surface.blocks.io.MetaIO;
import com.globbypotato.rockhounding_surface.enums.EnumFossilPlanks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;

public class FossilPlanks extends MetaIO {
	public static final PropertyEnum VARIANT = PropertyEnum.create("variant", EnumFossilPlanks.class);

    public FossilPlanks(String name, String[] array) {
		super(name, Material.WOOD, array, 1.0F, 2.0F, SoundType.WOOD);
        setHarvestLevel("axe", 0);
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumFossilPlanks.values()[0]));
    }

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(VARIANT, EnumFossilPlanks.values()[meta]);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumFossilPlanks)state.getValue(VARIANT)).ordinal();
	}

	@Override
	public BlockStateContainer createBlockState(){
		return new BlockStateContainer(this, new IProperty[] { VARIANT });
	}

}