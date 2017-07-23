package com.globbypotato.rockhounding_surface.items.food;

import com.globbypotato.rockhounding_surface.handler.Reference;

import net.minecraft.block.Block;
import net.minecraft.item.ItemSeeds;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BaseSeeds extends ItemSeeds{

	public BaseSeeds(String name, Block crops, Block soil) {
		super(crops, soil);
	    setRegistryName(name);
        setUnlocalizedName(this.getRegistryName().toString());
        GameRegistry.register(this);
		setCreativeTab(Reference.RockhoundingSurface);

	}

    @Override
    public EnumPlantType getPlantType(net.minecraft.world.IBlockAccess world, BlockPos pos){
        return EnumPlantType.Crop;
    }

}