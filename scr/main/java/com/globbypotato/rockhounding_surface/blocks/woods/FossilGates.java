package com.globbypotato.rockhounding_surface.blocks.woods;

import com.globbypotato.rockhounding_surface.blocks.BaseGate;
import com.globbypotato.rockhounding_surface.enums.EnumFossilPlanks;

import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class FossilGates extends BaseGate {

	public FossilGates(String name, Material material, EnumType plank, SoundType soundtype, float hardness, float resistance) {
		super(name, material, plank, soundtype, resistance, resistance);
	}
}