package com.globbypotato.rockhounding_surface.blocks.io;

import com.globbypotato.rockhounding_surface.handler.Reference;

import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class OrientedIO extends BlockRotatedPillar {

	public OrientedIO(String name, Material material, SoundType soundtype, float hardness, float resistance) {
		super(material);
		setRegistryName(name);
		setUnlocalizedName(getRegistryName().toString());
		setHardness(hardness); setResistance(resistance); setSoundType(soundtype);
		setCreativeTab(Reference.RockhoundingSurface);
	}
}