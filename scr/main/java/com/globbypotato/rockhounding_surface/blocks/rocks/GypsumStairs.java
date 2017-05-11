package com.globbypotato.rockhounding_surface.blocks.rocks;

import com.globbypotato.rockhounding_surface.ModBlocks;
import com.globbypotato.rockhounding_surface.blocks.BaseStairs;

import net.minecraft.block.SoundType;

public class GypsumStairs extends BaseStairs {

	public GypsumStairs(String name, int meta, SoundType soundtype, float hardness, float resistance) {
		super(ModBlocks.gypsum.getStateFromMeta(meta), name, soundtype, hardness, resistance);
	}
}