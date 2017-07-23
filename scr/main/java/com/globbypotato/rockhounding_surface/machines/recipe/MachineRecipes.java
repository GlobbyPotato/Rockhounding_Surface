package com.globbypotato.rockhounding_surface.machines.recipe;

import java.util.ArrayList;

import com.globbypotato.rockhounding_surface.ModBlocks;
import com.globbypotato.rockhounding_surface.ModItems;
import com.globbypotato.rockhounding_surface.enums.EnumTruffles;
import com.globbypotato.rockhounding_surface.fluids.ModFluids;
import com.globbypotato.rockhounding_surface.integration.SupportUtils;
import com.globbypotato.rockhounding_surface.utils.BaseRecipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class MachineRecipes extends BaseRecipes {
	public static ArrayList<WoodIncubatorRecipe> woodIncubatorRecipes = new ArrayList<WoodIncubatorRecipe>();
	public static ArrayList<CompostBinRecipe> compostRecipes = new ArrayList<CompostBinRecipe>();

	public static void machineRecipes() {
		if(SupportUtils.rhChemistryLoaded()){
			woodIncubatorRecipes.add(new WoodIncubatorRecipe(new ItemStack(Blocks.LOG2,1, 1), new ItemStack(ModItems.agingItems, 1, 1), false, new FluidStack(ModFluids.AGING_BATH,1000), new ItemStack(ModBlocks.bogLogs, 1, 0)));//bog oak
			woodIncubatorRecipes.add(new WoodIncubatorRecipe(new ItemStack(Blocks.LOG, 1, 1), new ItemStack(ModItems.agingItems, 1, 1), false, new FluidStack(ModFluids.AGING_BATH,1000), new ItemStack(ModBlocks.bogLogs, 1, 1)));//bog spruce
			woodIncubatorRecipes.add(new WoodIncubatorRecipe(new ItemStack(Blocks.LOG2,1, 0), new ItemStack(ModItems.agingItems, 1, 1), false, new FluidStack(ModFluids.AGING_BATH,1000), new ItemStack(ModBlocks.bogLogs, 1, 3)));//mophane
			woodIncubatorRecipes.add(new WoodIncubatorRecipe(new ItemStack(Blocks.LOG, 1, 2), new ItemStack(ModItems.agingItems, 1, 1), false, new FluidStack(ModFluids.AGING_BATH,1000), new ItemStack(ModBlocks.fossilLogs, 1, 1)));//pine
			woodIncubatorRecipes.add(new WoodIncubatorRecipe(new ItemStack(Blocks.LOG, 1, 0), new ItemStack(ModItems.agingItems, 1, 1), false, new FluidStack(ModFluids.AGING_BATH,1000), new ItemStack(ModBlocks.bogLogs, 1, 2)));//kauri
			woodIncubatorRecipes.add(new WoodIncubatorRecipe(new ItemStack(Blocks.LOG, 1, 3), new ItemStack(ModItems.agingItems, 1, 1), false, new FluidStack(ModFluids.AGING_BATH,1000), new ItemStack(ModBlocks.fossilLogs, 1, 0)));//palmosylon
			woodIncubatorRecipes.add(new WoodIncubatorRecipe(new ItemStack(Blocks.LOG, 1, 0), new ItemStack(ModItems.agingItems, 1, 1), false, new FluidStack(FluidRegistry.WATER,1000),  new ItemStack(ModBlocks.fossilLogs, 1, 2)));//driftwood
			woodIncubatorRecipes.add(new WoodIncubatorRecipe(new ItemStack(Blocks.LOG2,1, 1), new ItemStack(ModItems.agingItems, 1, 3), false, new FluidStack(ModFluids.CASTING_BATH,1000),  new ItemStack(ModBlocks.fossilLogs, 1, 3)));//araucaria
			woodIncubatorRecipes.add(new WoodIncubatorRecipe(new ItemStack(Blocks.LOG, 1, 1), new ItemStack(ModItems.agingItems, 1, 2), false, new FluidStack(ModFluids.CASTING_BATH,1000),  new ItemStack(ModBlocks.petrifiedLogs, 1, 1)));//castano
			woodIncubatorRecipes.add(new WoodIncubatorRecipe(new ItemStack(Blocks.LOG, 1, 2), new ItemStack(ModItems.agingItems, 1, 5), false, new FluidStack(ModFluids.CASTING_BATH,1000),  new ItemStack(ModBlocks.petrifiedLogs, 1, 0)));//betula
			woodIncubatorRecipes.add(new WoodIncubatorRecipe(new ItemStack(Blocks.LOG2,1, 0), new ItemStack(ModItems.agingItems, 1, 4), false, new FluidStack(ModFluids.CASTING_BATH,1000),  new ItemStack(ModBlocks.petrifiedLogs, 1, 2)));//opalized
			woodIncubatorRecipes.add(new WoodIncubatorRecipe(new ItemStack(Blocks.LOG, 1, 0), new ItemStack(ModItems.agingItems, 1, 6), false, new FluidStack(ModFluids.CASTING_BATH,1000),  new ItemStack(ModBlocks.petrifiedLogs, 1, 3)));//rainbow
			woodIncubatorRecipes.add(new WoodIncubatorRecipe(new ItemStack(Blocks.LOG, 1, 0), new ItemStack(ModItems.agingItems, 1, 7), false, new FluidStack(ModFluids.CASTING_BATH,1000), new ItemStack(ModBlocks.coldLogs, 1, 0)));//azurite
			woodIncubatorRecipes.add(new WoodIncubatorRecipe(new ItemStack(Blocks.LOG, 1, 2), new ItemStack(ModItems.agingItems, 1, 10),false, new FluidStack(FluidRegistry.WATER,1000), new ItemStack(ModBlocks.coldLogs, 1, 1)));//teredo
			woodIncubatorRecipes.add(new WoodIncubatorRecipe(new ItemStack(Blocks.LOG, 1, 1), new ItemStack(ModItems.agingItems, 1, 7), false, new FluidStack(ModFluids.AGING_BATH,1000), new ItemStack(ModBlocks.coldLogs, 1, 2)));//beech
			woodIncubatorRecipes.add(new WoodIncubatorRecipe(new ItemStack(Blocks.LOG, 1, 3), new ItemStack(ModItems.agingItems, 1, 8), false, new FluidStack(ModFluids.CASTING_BATH,1000), new ItemStack(ModBlocks.coldLogs, 1, 3)));//redwood
		}

		compostRecipes.add(new CompostBinRecipe(new ItemStack(Blocks.PUMPKIN), true));
		compostRecipes.add(new CompostBinRecipe(new ItemStack(Blocks.MELON_BLOCK), false));
		compostRecipes.add(new CompostBinRecipe(new ItemStack(Items.ROTTEN_FLESH), false));
		compostRecipes.add(new CompostBinRecipe(new ItemStack(Items.WHEAT), false));
		compostRecipes.add(new CompostBinRecipe(new ItemStack(Blocks.VINE), true));
		compostRecipes.add(new CompostBinRecipe(new ItemStack(Blocks.HAY_BLOCK), true));
		compostRecipes.add(new CompostBinRecipe(new ItemStack(Blocks.BROWN_MUSHROOM), true));
		compostRecipes.add(new CompostBinRecipe(new ItemStack(Blocks.RED_MUSHROOM), true));
		compostRecipes.add(new CompostBinRecipe(new ItemStack(Blocks.BROWN_MUSHROOM_BLOCK), true));
		compostRecipes.add(new CompostBinRecipe(new ItemStack(Blocks.RED_MUSHROOM_BLOCK), true));
		compostRecipes.add(new CompostBinRecipe(new ItemStack(Items.REEDS), true));
		compostRecipes.add(new CompostBinRecipe(new ItemStack(Items.EGG), true));
		compostRecipes.add(new CompostBinRecipe(new ItemStack(Items.POISONOUS_POTATO), false));
		compostRecipes.add(new CompostBinRecipe(new ItemStack(Items.FEATHER), true));
		compostRecipes.add(new CompostBinRecipe(new ItemStack(ModItems.ricegrassItem), true));
		for(int x = 0; x < EnumTruffles.size(); x++){
			compostRecipes.add(new CompostBinRecipe(new ItemStack(ModItems.truffles,1,x), true));
		}
		if(SupportUtils.rhTiersLoaded()){
			compostRecipes.add(new CompostBinRecipe(SupportUtils.itemPeat(), false));
			compostRecipes.add(new CompostBinRecipe(SupportUtils.driedPeat(), false));
		}
	}

}