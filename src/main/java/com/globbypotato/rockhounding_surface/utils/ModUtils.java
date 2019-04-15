package com.globbypotato.rockhounding_surface.utils;

import com.globbypotato.rockhounding_surface.blocks.white_sands.WhiteSand;

import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockSand;
import net.minecraft.block.state.IBlockState;

public class ModUtils {

	public static boolean isValidSoil(IBlockState state) {
		return state.getBlock() instanceof BlockGrass 
			|| state.getBlock() instanceof BlockSand 
			|| state.getBlock() instanceof BlockDirt 
			|| state.getBlock() instanceof BlockFarmland 
			|| state.getBlock() instanceof WhiteSand;
	}

}