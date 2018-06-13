package com.globbypotato.rockhounding_surface.items.io;

import com.globbypotato.rockhounding_surface.handler.Reference;

import net.minecraft.block.Block;
import net.minecraft.item.ItemSeeds;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.EnumPlantType;

public class SeedsIO extends ItemSeeds{

	public SeedsIO(String name, Block crops, Block soil) {
		super(crops, soil);
	    setRegistryName(name);
        setUnlocalizedName(this.getRegistryName().toString());
		setCreativeTab(Reference.RockhoundingSurface);

	}

    @Override
    public EnumPlantType getPlantType(net.minecraft.world.IBlockAccess world, BlockPos pos){
        return EnumPlantType.Crop;
    }

}