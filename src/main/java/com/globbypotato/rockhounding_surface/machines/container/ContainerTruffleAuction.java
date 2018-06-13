package com.globbypotato.rockhounding_surface.machines.container;

import com.globbypotato.rockhounding_surface.machines.tileentity.TileEntityTruffleAuction;

import net.minecraft.inventory.IInventory;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerTruffleAuction extends ContainerBase<TileEntityTruffleAuction>{
	public ContainerTruffleAuction(IInventory playerInventory, TileEntityTruffleAuction te) {
		super(playerInventory,te);
	}

	@Override
	protected void addOwnSlots() {
		IItemHandler input = this.tile.getInput();
        this.addSlotToContainer(new SlotItemHandler(input, 0, 80, 29));//input
        this.addSlotToContainer(new SlotItemHandler(input, 1, 124, 63));//tool
	}
}