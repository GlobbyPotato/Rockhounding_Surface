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
		IItemHandler input = this.tile.getInput();
		IItemHandler output = this.tile.getOutput();
		IItemHandler template = this.tile.getTemplate();

		this.addSlotToContainer(new SlotItemHandler(input, 0, 55, 52));//input solute
		this.addSlotToContainer(new SlotItemHandler(input, 1, 35, 75));//input log
		this.addSlotToContainer(new SlotItemHandler(input, 2, 105, 52));//input solvent

		this.addSlotToContainer(new SlotItemHandler(output, 0, 125, 75));//output

		this.addSlotToContainer(new SlotItemHandler(template, 0, 137,  22));//prev
		this.addSlotToContainer(new SlotItemHandler(template, 1, 154,  22));//next
		this.addSlotToContainer(new SlotItemHandler(template, 2, 7,  22));//activation

	}

	@Override
	public ItemStack slotClick(int slot, int dragType, ClickType clickTypeIn, EntityPlayer player){
		if(slot == 4){
			if(this.tile.recipeIndex >= 0){
	    		this.tile.recipeIndex--; 
    			this.tile.activation = false;
			}else{
				this.tile.recipeIndex = MachineRecipes.woodIncubatorRecipes.size() - 1;
    			this.tile.activation = false;
			}
			doClickSound(player, this.tile.getWorld(), this.tile.getPos());
    		return ItemStack.EMPTY;
    	}else if(slot == 5){
    		if(this.tile.recipeIndex < MachineRecipes.woodIncubatorRecipes.size() - 1){
    			this.tile.recipeIndex++; 
    			this.tile.activation = false;
    		}else{
    			this.tile.recipeIndex = -1; 
    			this.tile.activation = false;
    		}
			doClickSound(player, this.tile.getWorld(), this.tile.getPos());
    		return ItemStack.EMPTY;
    	}else if(slot == 6){
   			this.tile.activation = !this.tile.activation; 
			doClickSound(player, this.tile.getWorld(), this.tile.getPos());
    		return ItemStack.EMPTY;
    	}else{
    		return super.slotClick(slot, dragType, clickTypeIn, player);
    	}
	}

	@Override
	public boolean mergeItemStack(ItemStack stack, int startIndex, int endIndex, boolean reverseDirection){
		if(super.mergeItemStack(stack, startIndex, 4, reverseDirection)){
			return true;
		}
		return super.mergeItemStack(stack, 7, endIndex, reverseDirection);
    }
}