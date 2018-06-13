package com.globbypotato.rockhounding_surface.blocks.fossil_wood;

import com.globbypotato.rockhounding_surface.ModBlocks;
import com.globbypotato.rockhounding_surface.blocks.io.StairsIO;

import net.minecraft.block.SoundType;

public class FossilStairs extends StairsIO {

	public FossilStairs(String name, int meta) {
		super(ModBlocks.FOSSIL_PLANKS.getStateFromMeta(meta), name, SoundType.WOOD, 1.0F, 2.0F);
	}
}