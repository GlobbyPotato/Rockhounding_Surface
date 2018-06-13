package com.globbypotato.rockhounding_surface.blocks.white_sands;

import com.globbypotato.rockhounding_surface.blocks.io.SandIO;

import net.minecraft.block.state.IBlockState;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WhiteSand extends SandIO {
	public WhiteSand(String name){
		super(name, 1.0F, 1.0F);
	}

    @SideOnly(Side.CLIENT)
    @Override
    public int getDustColor(IBlockState state){
        return 13355979;
    }

}