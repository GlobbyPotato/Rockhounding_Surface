package com.globbypotato.rockhounding_surface.machines.container;

import com.globbypotato.rockhounding_surface.machines.tileentity.TileEntityCompostBin;

import net.minecraft.inventory.IInventory;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerCompostBin extends ContainerBase<TileEntityCompostBin>{
	public ContainerCompostBin(IInventory playerInventory, TileEntityCompostBin te) {
		super(playerInventory,te);
	}

	@Override
	protected void addOwnSlots() {
		IItemHandler input = this.tile.getInput();
		IItemHandler output = this.tile.getOutput();
        this.addSlotToContainer(new SlotItemHandler(input, 0, 30, 39));//input
        this.addSlotToContainer(new SlotItemHandler(output, 0, 130, 73));//output
	}
}