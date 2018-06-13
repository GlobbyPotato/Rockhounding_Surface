package com.globbypotato.rockhounding_surface.machines.container;

import com.globbypotato.rockhounding_surface.machines.tileentity.TileEntityVivarium;

import net.minecraft.inventory.IInventory;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerVivarium extends ContainerBase<TileEntityVivarium>{
	public ContainerVivarium(IInventory playerInventory, TileEntityVivarium te) {
		super(playerInventory,te);
	}

	@Override
	protected void addOwnSlots() {
		IItemHandler input = this.tile.getInput();
		IItemHandler output = this.tile.getOutput();
        this.addSlotToContainer(new SlotItemHandler(input, 0, 80, 40));//input
        this.addSlotToContainer(new SlotItemHandler(output, 0, 80, 61));//output
	}
}