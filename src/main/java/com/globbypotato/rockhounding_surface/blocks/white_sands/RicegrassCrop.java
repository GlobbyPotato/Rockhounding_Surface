package com.globbypotato.rockhounding_surface.blocks.white_sands;

import com.globbypotato.rockhounding_surface.ModItems;
import com.globbypotato.rockhounding_surface.blocks.io.CropIO;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.item.Item;

public class RicegrassCrop extends CropIO {

    public RicegrassCrop(String name){
		super(name);
		setCreativeTab(null);
        this.setDefaultState(this.blockState.getBaseState().withProperty(AGE, Integer.valueOf(0)));
	}

    @Override
    public Item getSeed(){
        return ModItems.RICEGRASS_SEEDS;
    }

    @Override
    public Item getCrop(){
        return ModItems.RICEGRASS_ITEM;
    }

    @Override
    public BlockStateContainer createBlockState(){
        return new BlockStateContainer(this, new IProperty[] {AGE});
    }

}