package com.globbypotato.rockhounding_surface.blocks.fossil_wood;

import com.globbypotato.rockhounding_surface.blocks.io.GateIO;

import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.SoundType;

public class FossilGates extends GateIO {

	public FossilGates(String name) {
		super(name, EnumType.OAK, SoundType.WOOD, 1.0F, 2.0F);
	}

}