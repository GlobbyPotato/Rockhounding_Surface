package com.globbypotato.rockhounding_surface.blocks.fossil_wood;

import com.globbypotato.rockhounding_surface.blocks.io.FenceIO;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class FossilFences extends FenceIO {

	public FossilFences(String name, MapColor mapcolor) {
		super(name, Material.WOOD, mapcolor, SoundType.WOOD, 1.0F, 1.0F);
	}
}