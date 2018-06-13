package com.globbypotato.rockhounding_surface.blocks.io;

import com.globbypotato.rockhounding_surface.handler.Reference;

import net.minecraft.block.BlockFalling;
import net.minecraft.block.SoundType;

public class SandIO extends BlockFalling {
	public SandIO(String name, float hardness, float resistance){
		setRegistryName(name);
		setUnlocalizedName(getRegistryName().toString());
		setHardness(hardness); setResistance(resistance); setSoundType(SoundType.SAND);
		setHarvestLevel("shovel",0);
		setCreativeTab(Reference.RockhoundingSurface);
        this.setDefaultState(this.blockState.getBaseState());
	}

}