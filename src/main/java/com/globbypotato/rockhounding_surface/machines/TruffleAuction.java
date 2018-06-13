package com.globbypotato.rockhounding_surface.machines;

import com.globbypotato.rockhounding_surface.handler.GuiHandler;
import com.globbypotato.rockhounding_surface.handler.Reference;
import com.globbypotato.rockhounding_surface.machines.tileentity.TileEntityTruffleAuction;

import net.minecraft.block.material.Material;
import net.minecraft.util.EnumFacing;

public class TruffleAuction extends MachineIO{
    public TruffleAuction(String name){
        super(Reference.MODID, name, Material.WOOD, TileEntityTruffleAuction.class,GuiHandler.truffleAuctionID, 1.0F);
    	setHardness(3.0F); setResistance(5.0F);	
		setHarvestLevel("axe", 0);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
    }
}