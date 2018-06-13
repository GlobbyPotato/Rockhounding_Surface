package com.globbypotato.rockhounding_surface.machines;

import com.globbypotato.rockhounding_surface.handler.GuiHandler;
import com.globbypotato.rockhounding_surface.handler.Reference;
import com.globbypotato.rockhounding_surface.machines.tileentity.TileEntityVivarium;

import net.minecraft.block.material.Material;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Vivarium extends MachineIO {
    public Vivarium(String name){
    	super(Reference.MODID, name, Material.GLASS, TileEntityVivarium.class, GuiHandler.vivariumID, 1.0F);
    	setHardness(3.0F); setResistance(5.0F);	
		setHarvestLevel("axe", 0);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
    }
    
	@SideOnly(Side.CLIENT)
    @Override
    public BlockRenderLayer getBlockLayer(){
        return BlockRenderLayer.TRANSLUCENT;
    }

}