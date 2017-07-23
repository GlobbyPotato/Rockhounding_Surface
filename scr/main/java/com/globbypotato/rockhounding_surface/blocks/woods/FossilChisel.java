package com.globbypotato.rockhounding_surface.blocks.woods;

import com.globbypotato.rockhounding_surface.blocks.BlockIO;
import com.globbypotato.rockhounding_surface.blocks.itemblocks.FossilChiselIB;
import com.globbypotato.rockhounding_surface.enums.EnumChiseled;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class FossilChisel extends BlockIO {
	public static final PropertyEnum VARIANT = PropertyEnum.create("variant", EnumChiseled.class);

    public FossilChisel(Material material, String[] array, float hardness, float resistance, String name, SoundType stepSound) {
		super(material, array, hardness, resistance, name, stepSound);
        GameRegistry.register(new FossilChiselIB(this, EnumChiseled.getNames()).setRegistryName(name));
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