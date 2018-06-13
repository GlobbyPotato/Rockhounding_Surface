package com.globbypotato.rockhounding_surface.blocks.io;

import net.minecraft.block.BlockCrops;

public class CropIO extends BlockCrops {
    public CropIO(String name){
		super();
		setRegistryName(name);
		setUnlocalizedName(getRegistryName().toString());
		setCreativeTab(null);
    }
}