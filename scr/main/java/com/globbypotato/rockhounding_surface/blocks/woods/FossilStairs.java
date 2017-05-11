package com.globbypotato.rockhounding_surface.blocks.woods;

import com.globbypotato.rockhounding_surface.ModBlocks;
import com.globbypotato.rockhounding_surface.blocks.BaseStairs;

import net.minecraft.block.SoundType;

public class FossilStairs extends BaseStairs {

	public FossilStairs(String name, int meta, SoundType soundtype, float hardness, float resistance) {
		super(ModBlocks.fossilPlanks.getStateFromMeta(meta), name, soundtype, hardness, resistance);
	}
}