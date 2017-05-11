package com.globbypotato.rockhounding_surface.handler;

import com.globbypotato.rockhounding_surface.machines.container.ContainerCompostBin;
import com.globbypotato.rockhounding_surface.machines.container.ContainerTruffleAuction;
import com.globbypotato.rockhounding_surface.machines.container.ContainerWoodIncubator;
import com.globbypotato.rockhounding_surface.machines.gui.GuiCompostBin;
import com.globbypotato.rockhounding_surface.machines.gui.GuiTruffleAuction;
import com.globbypotato.rockhounding_surface.machines.gui.GuiWoodIncubator;
import com.globbypotato.rockhounding_surface.machines.tileentity.TileEntityCompostBin;
import com.globbypotato.rockhounding_surface.machines.tileentity.TileEntityTruffleAuction;
import com.globbypotato.rockhounding_surface.machines.tileentity.TileEntityWoodIncubator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	public static final int truffleAuctionID = 0;
	public static final int woodIncubatorID = 1;
	public static final int compostBinID = 2;

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity entity = world.getTileEntity(new BlockPos(x,y,z));
		switch(ID) {
			default: return null;
			case truffleAuctionID:
				if (entity != null && entity instanceof TileEntityTruffleAuction){return new ContainerTruffleAuction(player.inventory, (TileEntityTruffleAuction) entity);}
			case woodIncubatorID:
				if (entity != null && entity instanceof TileEntityWoodIncubator){return new ContainerWoodIncubator(player.inventory, (TileEntityWoodIncubator) entity);}
			case compostBinID:
				if (entity != null && entity instanceof TileEntityCompostBin){return new ContainerCompostBin(player.inventory, (TileEntityCompostBin) entity);}
		}
        return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity entity = world.getTileEntity(new BlockPos(x,y,z));
		switch(ID) {
			default: return null;
			case truffleAuctionID:
				if (entity != null && entity instanceof TileEntityTruffleAuction) {return new GuiTruffleAuction(player.inventory, (TileEntityTruffleAuction) entity);}
			case woodIncubatorID:
				if (entity != null && entity instanceof TileEntityWoodIncubator) {return new GuiWoodIncubator(player.inventory, (TileEntityWoodIncubator) entity);}
			case compostBinID:
				if (entity != null && entity instanceof TileEntityCompostBin) {return new GuiCompostBin(player.inventory, (TileEntityCompostBin) entity);}
		}
        return null;
	}

}
