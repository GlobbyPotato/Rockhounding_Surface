package com.globbypotato.rockhounding_surface.blocks.white_sands;

import com.globbypotato.rockhounding_surface.blocks.io.OrientedIO;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class GypsumPillars extends OrientedIO {

	public GypsumPillars(String name) {
		super(name, Material.ROCK, SoundType.STONE, 2.0F, 2.0F);
	}
}