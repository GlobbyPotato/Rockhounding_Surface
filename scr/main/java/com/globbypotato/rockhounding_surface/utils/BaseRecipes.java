package com.globbypotato.rockhounding_surface.utils;

import com.globbypotato.rockhounding_surface.ModBlocks;
import com.globbypotato.rockhounding_surface.ModItems;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class BaseRecipes {
	public static ItemStack gypsumDust = gypsum(1, 0);
	public static ItemStack gypsumDusts = gypsum(4, 0);
	public static ItemStack gypsumMeal = gypsum(1, 1);
	public static ItemStack gypsumMeals = gypsum(2, 1);
	public static ItemStack gypsumCond = gypsum(1, 2);
	public static ItemStack gypsumConds = gypsum(4, 2);
	public static ItemStack gypsumAmend = gypsum(1, 3);
	public static ItemStack gypsumAmends = gypsum(4, 3);
	public static ItemStack compost = gypsum(1, 4);
	public static ItemStack mesquiteFlour = gypsum(1, 5);
	public static ItemStack mesquiteFlower = gypsum(1, 6);

	public static ItemStack teredo = aging(1, 9);

	public static ItemStack bonemeal = new ItemStack(Items.DYE, 1, 15);
	public static ItemStack bread = new ItemStack(Items.BREAD);
	
	public static ItemStack sourberryFuit = new ItemStack(ModItems.sourberry);
	public static ItemStack sourberryJuice = new ItemStack(ModItems.sourberryJuice);
	public static ItemStack sourberryPie = new ItemStack(ModItems.sourberryPie);
	public static ItemStack sourberryTea = new ItemStack(ModItems.sourberryTea);
	public static ItemStack sourberry = new ItemStack(ModItems.sourberry);
	public static ItemStack purplePear = new ItemStack(ModItems.purplePear);
	public static ItemStack purplePearJuice = new ItemStack(ModItems.purplePearJuice);
	public static ItemStack purplePearSmoothie = new ItemStack(ModItems.purplePearSmoothie);
	public static ItemStack purplePearJam = new ItemStack(ModItems.purplePearJam);
	public static ItemStack ricegrassItem = new ItemStack(ModItems.ricegrassItem);
	public static ItemStack desertSpoon = new ItemStack(ModBlocks.gypsumBushLo, 1, 2);
	public static ItemStack sotol = new ItemStack(ModItems.sotol);
	public static ItemStack mesquitePie = new ItemStack(ModItems.mesquitePie);
	public static ItemStack mesquiteLiquor = new ItemStack(ModItems.mesquiteLiquor);
	public static ItemStack mesquiteTea = new ItemStack(ModItems.mesquiteTea);

	protected static ItemStack gypsum(int num, int meta) {return new ItemStack(ModItems.gypsumItems, num, meta);}
	public static ItemStack aging(int num, int meta) {return new ItemStack(ModItems.agingItems, num, meta);}

}