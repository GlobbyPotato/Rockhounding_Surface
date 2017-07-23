package com.globbypotato.rockhounding_surface.blocks.woods;

import com.globbypotato.rockhounding_core.blocks.itemblocks.BaseMetaIB;
import com.globbypotato.rockhounding_surface.blocks.BlockIO;
import com.globbypotato.rockhounding_surface.enums.EnumFossilPlanks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class FossilPlanks extends BlockIO {
	public static final PropertyEnum VARIANT = PropertyEnum.create("variant", EnumFossilPlanks.class);

    public FossilPlanks(Material material, String[] array, float hardness, float resistance, String name, SoundType stepSound) {
		super(material, array, hardness, resistance, name, stepSound);
        GameRegistry.register(new BaseMetaIB(this, EnumFossilPlanks.getNames()).setRegistryName(name));
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