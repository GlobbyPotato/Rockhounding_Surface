package com.globbypotato.rockhounding_surface.machines;

import com.globbypotato.rockhounding_surface.handler.GuiHandler;
import com.globbypotato.rockhounding_surface.machines.tileentity.TileEntityCompostBin;

import net.minecraft.block.material.Material;
import net.minecraft.util.EnumFacing;

public class CompostBin extends BaseMachine {
    public CompostBin(float hardness, float resistance, String name){
        super(name, Material.WOOD, TileEntityCompostBin.class, GuiHandler.compostBinID);
		setHardness(hardness); setResistance(resistance);	
		setHarvestLevel("axe", 0);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
    }
}