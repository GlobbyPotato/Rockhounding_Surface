package com.globbypotato.rockhounding_surface.blocks.woods;

import com.globbypotato.rockhounding_surface.blocks.BaseMetaBlock;
import com.globbypotato.rockhounding_surface.blocks.itemblocks.FossilChiselIB;
import com.globbypotato.rockhounding_surface.enums.EnumChiseled;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class FossilChisel extends BaseMetaBlock {
	public static final PropertyEnum VARIANT = PropertyEnum.create("variant", EnumChiseled.class);

    public FossilChisel(String name, Material material, SoundType soundtype, String[] array, float hardness, float resistance){
        super(name, array, material, soundtype, resistance, resistance);
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