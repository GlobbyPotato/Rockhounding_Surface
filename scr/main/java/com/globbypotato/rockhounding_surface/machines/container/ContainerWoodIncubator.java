package com.globbypotato.rockhounding_surface.machines.container;

import com.globbypotato.rockhounding_surface.machines.recipe.MachineRecipes;
import com.globbypotato.rockhounding_surface.machines.tileentity.TileEntityWoodIncubator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerWoodIncubator extends ContainerBase<TileEntityWoodIncubator> {

	public ContainerWoodIncubator(IInventory playerInventory, TileEntityWoodIncubator tile){
		super(playerInventory,tile);
	}

	@Override
	protected void addOwnSlots() {
		IItemHandler input = tile.getInput();
		IItemHandler output = tile.getOutput();
		IItemHandler template = tile.getTemplate();

		this.addSlotToContainer(new SlotItemHandler(input, 0, 62, 34));//input solute
		this.addSlotToContainer(new SlotItemHandler(input, 1, 8, 31));//fuel
		this.addSlotToContainer(new SlotItemHandler(input, 2, 110, 34));//input log
		this.addSlotToContainer(new SlotItemHandler(input, 3, 29, 86));//input redstone
		this.addSlotToContainer(new SlotItemHandler(input, 4, 62, 83));//input solvent

		this.addSlotToContainer(new SlotItemHandler(output, 0, 110, 83));//output

		this.addSlotToContainer(new SlotItemHandler(template, 0, 137,  122));//prev
		this.addSlotToContainer(new SlotItemHandler(template, 1, 153,  122));//next
		this.addSlotToContainer(new SlotItemHandler(template, 2, 7,  122));//activation

	}

	@Override
	public ItemStack slotClick(int slot, int dragType, ClickType clickTypeIn, EntityPlayer player){
		if(slot == 6){
			if(this.tile.recipeIndex >= 0){
	    		this.tile.recipeIndex--; 
    			this.tile.activation = false;
			}else{
				this.tile.recipeIndex = MachineRecipes.woodIncubatorRecipes.size() - 1;
    			this.tile.activation = false;
			}
    		return null;
    	}else if(slot == 7){
    		if(this.tile.recipeIndex < MachineRecipes.woodIncubatorRecipes.size() - 1){
    			this.tile.recipeIndex++; 
    			this.tile.activation = false;
    		}else{
    			this.tile.recipeIndex = -1; 
    			this.tile.activation = false;
    		}
    		return null;
    	}else if(slot == 8){
   			this.tile.activation = !this.tile.activation; 
    		return null;
    	}else{
    		return super.slotClick(slot, dragType, clickTypeIn, player);
    	}
	}

	@Override
	public boolean mergeItemStack(ItemStack stack, int startIndex, int endIndex, boolean reverseDirection){
		if(super.mergeItemStack(stack, startIndex, 6, reverseDirection)){
			return true;
		}else{
			return super.mergeItemStack(stack, 9, endIndex, reverseDirection);
		}
    }
}