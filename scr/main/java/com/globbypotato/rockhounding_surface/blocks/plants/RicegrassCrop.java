package com.globbypotato.rockhounding_surface.blocks.plants;

import com.globbypotato.rockhounding_surface.ModItems;
import com.globbypotato.rockhounding_surface.blocks.BaseCrop;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RicegrassCrop extends BaseCrop {

    public RicegrassCrop(String name){
		super(name);
		GameRegistry.register(new ItemBlock(this).setRegistryName(name));
        this.setDefaultState(this.blockState.getBaseState().withProperty(AGE, Integer.valueOf(0)));
	}

    @Override
    public Item getSeed(){
        return ModItems.ricegrassSeeds;
    }

    @Override
    public Item getCrop(){
        return ModItems.ricegrassItem;
    }

    @Override
    public BlockStateContainer createBlockState(){
        return new BlockStateContainer(this, new IProperty[] {AGE});
    }

}