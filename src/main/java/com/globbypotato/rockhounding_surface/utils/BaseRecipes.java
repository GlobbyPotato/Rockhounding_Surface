package com.globbypotato.rockhounding_surface.utils;

import com.globbypotato.rockhounding_surface.ModItems;
import com.globbypotato.rockhounding_surface.enums.EnumAgingItems;
import com.globbypotato.rockhounding_surface.enums.EnumGypsumItems;

import net.minecraft.item.ItemStack;

public class BaseRecipes {
	public static int HEIGHT = 200;
	public static ItemStack teredo_grub = aging(1, EnumAgingItems.TEREDO_GRUB.ordinal());
	public static ItemStack compost = gypsum(1, EnumGypsumItems.COMPOST.ordinal());
	public static ItemStack bonemeal = gypsum(1, EnumGypsumItems.BONEMEAL.ordinal());
	public static ItemStack conditioner = gypsum(1, EnumGypsumItems.CONDITIONER.ordinal());
	public static ItemStack amendment = gypsum(1, EnumGypsumItems.AMENDMENT.ordinal());

	public static ItemStack gypsum(int num, int meta) {return new ItemStack(ModItems.GYPSUM_ITEMS, num, meta);}
	public static ItemStack aging(int num, int meta) {return new ItemStack(ModItems.AGING_ITEMS, num, meta);}

}