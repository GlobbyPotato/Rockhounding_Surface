package com.globbypotato.rockhounding_surface.blocks.fossil_wood;

import com.globbypotato.rockhounding_surface.blocks.io.MetaIO;
import com.globbypotato.rockhounding_surface.enums.EnumChiseled;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;

public class FossilChiseled extends MetaIO {
	public static final PropertyEnum VARIANT = PropertyEnum.create("variant", EnumChiseled.class);

    public FossilChiseled(String name, String[] array) {
		super(name, Material.WOOD, array, 1.0F, 2.0F, SoundType.WOOD);
        setHarvestLevel("axe", 0);
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumChiseled.values()[0]));
    }

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(VARIANT, EnumChiseled.values()[meta]);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumChiseled)state.getValue(VARIANT)).ordinal();
	}

	@Override
	public BlockStateContainer createBlockState(){
		return new BlockStateContainer(this, new IProperty[] { VARIANT });
	}

}