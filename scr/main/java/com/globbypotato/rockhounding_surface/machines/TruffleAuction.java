package com.globbypotato.rockhounding_surface.machines;

import com.globbypotato.rockhounding_surface.handler.GuiHandler;
import com.globbypotato.rockhounding_surface.machines.tileentity.TileEntityTruffleAuction;

import net.minecraft.block.material.Material;
import net.minecraft.util.EnumFacing;

public class TruffleAuction extends BaseMachine{
    public TruffleAuction(float hardness, float resistance, String name){
        super(name, Material.IRON, TileEntityTruffleAuction.class,GuiHandler.truffleAuctionID);
		setHardness(hardness); setResistance(resistance);	
		setHarvestLevel("pickaxe", 0);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
    }
}