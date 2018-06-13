package com.globbypotato.rockhounding_surface.blocks.white_sands;

import com.globbypotato.rockhounding_surface.ModBlocks;
import com.globbypotato.rockhounding_surface.blocks.io.StairsIO;

import net.minecraft.block.SoundType;

public class GypsumStairs extends StairsIO {

	public GypsumStairs(String name, int meta) {
		super(ModBlocks.GYPSUM.getStateFromMeta(meta), name, SoundType.STONE, 2.0F, 2.0F);
	}
}