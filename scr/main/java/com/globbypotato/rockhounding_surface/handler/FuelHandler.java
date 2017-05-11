package com.globbypotato.rockhounding_surface.handler;

import com.globbypotato.rockhounding_surface.ModBlocks;
import com.globbypotato.rockhounding_surface.ModItems;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.IFuelHandler;

public class FuelHandler implements IFuelHandler {

	@Override
	public int getBurnTime(ItemStack fuel) {
		if(!ModConfig.woodOredict){
			// Fossil log
			if(fuel.getItem() == Item.getItemFromBlock(ModBlocks.bogLogs)) return 10;	
			if(fuel.getItem() == Item.getItemFromBlock(ModBlocks.fossilLogs)) return 10;
			if(fuel.getItem() == Item.getItemFromBlock(ModBlocks.petrifiedLogs)) return 10;
			if(fuel.getItem() == Item.getItemFromBlock(ModBlocks.coldLogs)) return 10;
			// Fossil planks
			if(fuel.getItem() == Item.getItemFromBlock(ModBlocks.fossilPlanks)) return 10;	
			// Fossil sticks
			if(fuel.getItem() == ModItems.fossilSticks) return 10;
		}
		return 0;
	}
}