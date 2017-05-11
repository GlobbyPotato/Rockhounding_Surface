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
		IItemHandler input = tile.getInput();
        this.addSlotToContainer(new SlotItemHandler(input, 0, 123, 39));//tool
	}
}