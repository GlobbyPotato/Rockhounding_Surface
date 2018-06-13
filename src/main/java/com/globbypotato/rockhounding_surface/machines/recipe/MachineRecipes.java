package com.globbypotato.rockhounding_surface.machines.recipe;

import java.util.ArrayList;

import com.globbypotato.rockhounding_surface.ModBlocks;
import com.globbypotato.rockhounding_surface.ModItems;
import com.globbypotato.rockhounding_surface.enums.EnumTruffles;
import com.globbypotato.rockhounding_surface.fluids.ModFluids;
import com.globbypotato.rockhounding_surface.handler.ModConfig;
import com.globbypotato.rockhounding_surface.utils.BaseRecipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import rockhounding.api.IReciperBase;

public class MachineRecipes extends BaseRecipes{
	public static ArrayList<WoodIncubatorRecipe> woodIncubatorRecipes = new ArrayList<WoodIncubatorRecipe>();
	public static ArrayList<CompostBinRecipe> compostRecipes = new ArrayList<CompostBinRecipe>();
	public static ArrayList<TruffleAuctionRecipe> auctionRecipes = new ArrayList<TruffleAuctionRecipe>();
	public static ArrayList<VivariumRecipe> vivariumRecipes = new ArrayList<VivariumRecipe>();

	public static void machineRecipes() {
		if(IReciperBase.chemistryLoaded()){
			woodIncubatorRecipes.add(new WoodIncubatorRecipe(new ItemStack(Blocks.LOG2,1, 1), aging(1, 1), new FluidStack(ModFluids.AGING_BATH,1000), 		new ItemStack(ModBlocks.BOG_LOGS, 1, 0)));//bog oak
			woodIncubatorRecipes.add(new WoodIncubatorRecipe(new ItemStack(Blocks.LOG, 1, 1), aging(1, 1), new FluidStack(ModFluids.AGING_BATH,1000), 		new ItemStack(ModBlocks.BOG_LOGS, 1, 1)));//bog spruce
			woodIncubatorRecipes.add(new WoodIncubatorRecipe(new ItemStack(Blocks.LOG, 1, 0), aging(1, 1), new FluidStack(ModFluids.AGING_BATH,1000), 		new ItemStack(ModBlocks.BOG_LOGS, 1, 2)));//kauri
			woodIncubatorRecipes.add(new WoodIncubatorRecipe(new ItemStack(Blocks.LOG2,1, 0), aging(1, 1), new FluidStack(ModFluids.AGING_BATH,1000), 		new ItemStack(ModBlocks.BOG_LOGS, 1, 3)));//mophane
			woodIncubatorRecipes.add(new WoodIncubatorRecipe(new ItemStack(Blocks.LOG, 1, 3), aging(1, 1), new FluidStack(ModFluids.AGING_BATH,1000), 		new ItemStack(ModBlocks.FOSSIL_LOGS, 1, 0)));//palmosylon
			woodIncubatorRecipes.add(new WoodIncubatorRecipe(new ItemStack(Blocks.LOG, 1, 2), aging(1, 1), new FluidStack(ModFluids.AGING_BATH,1000), 		new ItemStack(ModBlocks.FOSSIL_LOGS, 1, 1)));//pine
			woodIncubatorRecipes.add(new WoodIncubatorRecipe(new ItemStack(Blocks.LOG, 1, 0), aging(1, 1), new FluidStack(FluidRegistry.WATER,1000),  		new ItemStack(ModBlocks.FOSSIL_LOGS, 1, 2)));//driftwood
			woodIncubatorRecipes.add(new WoodIncubatorRecipe(new ItemStack(Blocks.LOG2,1, 1), aging(1, 3), new FluidStack(ModFluids.CASTING_BATH,1000), 	new ItemStack(ModBlocks.FOSSIL_LOGS, 1, 3)));//araucaria
			woodIncubatorRecipes.add(new WoodIncubatorRecipe(new ItemStack(Blocks.LOG, 1, 2), aging(1, 5), new FluidStack(ModFluids.CASTING_BATH,1000),  	new ItemStack(ModBlocks.PETRIFIED_LOGS, 1, 0)));//betula
			woodIncubatorRecipes.add(new WoodIncubatorRecipe(new ItemStack(Blocks.LOG, 1, 1), aging(1, 2), new FluidStack(ModFluids.CASTING_BATH,1000),  	new ItemStack(ModBlocks.PETRIFIED_LOGS, 1, 1)));//castano
			woodIncubatorRecipes.add(new WoodIncubatorRecipe(new ItemStack(Blocks.LOG2,1, 0), aging(1, 4), new FluidStack(ModFluids.CASTING_BATH,1000), 	new ItemStack(ModBlocks.PETRIFIED_LOGS, 1, 2)));//opalized
			woodIncubatorRecipes.add(new WoodIncubatorRecipe(new ItemStack(Blocks.LOG, 1, 0), aging(1, 6), new FluidStack(ModFluids.CASTING_BATH,1000), 	new ItemStack(ModBlocks.PETRIFIED_LOGS, 1, 3)));//rainbow
			woodIncubatorRecipes.add(new WoodIncubatorRecipe(new ItemStack(Blocks.LOG, 1, 0), aging(1, 7), new FluidStack(ModFluids.CASTING_BATH,1000), 	new ItemStack(ModBlocks.COLD_LOGS, 1, 0)));//azurite
			woodIncubatorRecipes.add(new WoodIncubatorRecipe(new ItemStack(Blocks.LOG, 1, 2), aging(1, 10),new FluidStack(FluidRegistry.WATER,1000), 		new ItemStack(ModBlocks.COLD_LOGS, 1, 1)));//teredo
			woodIncubatorRecipes.add(new WoodIncubatorRecipe(new ItemStack(Blocks.LOG, 1, 1), aging(1, 7), new FluidStack(ModFluids.AGING_BATH,1000), 		new ItemStack(ModBlocks.COLD_LOGS, 1, 2)));//beech
			woodIncubatorRecipes.add(new WoodIncubatorRecipe(new ItemStack(Blocks.LOG, 1, 3), aging(1, 8), new FluidStack(ModFluids.CASTING_BATH,1000), 	new ItemStack(ModBlocks.COLD_LOGS, 1, 3)));//redwood
		}

		compostRecipes.add(new CompostBinRecipe(new ItemStack(Blocks.PUMPKIN), 3, 0));
		compostRecipes.add(new CompostBinRecipe(new ItemStack(Blocks.MELON_BLOCK), 3, 0));
		compostRecipes.add(new CompostBinRecipe(new ItemStack(Items.ROTTEN_FLESH), 4, 0));
		compostRecipes.add(new CompostBinRecipe(new ItemStack(Items.WHEAT), 2, 0));
		compostRecipes.add(new CompostBinRecipe(new ItemStack(Blocks.VINE), 2, 0));
		compostRecipes.add(new CompostBinRecipe(new ItemStack(Blocks.HAY_BLOCK), 3, 0));
		compostRecipes.add(new CompostBinRecipe(new ItemStack(Blocks.BROWN_MUSHROOM), 3, 0));
		compostRecipes.add(new CompostBinRecipe(new ItemStack(Blocks.RED_MUSHROOM), 3, 0));
		compostRecipes.add(new CompostBinRecipe(new ItemStack(Blocks.BROWN_MUSHROOM_BLOCK), 3, 0));
		compostRecipes.add(new CompostBinRecipe(new ItemStack(Blocks.RED_MUSHROOM_BLOCK), 3, 0));
		compostRecipes.add(new CompostBinRecipe(new ItemStack(Items.REEDS), 2, 0));
		compostRecipes.add(new CompostBinRecipe(new ItemStack(Items.EGG), 2, 0));
		compostRecipes.add(new CompostBinRecipe(new ItemStack(Items.POISONOUS_POTATO), 4, 0));
		compostRecipes.add(new CompostBinRecipe(new ItemStack(Items.FEATHER), 0, 0));
		compostRecipes.add(new CompostBinRecipe(new ItemStack(ModItems.RICEGRASS_ITEM), 2, 0));
		for(int x = 0; x < EnumTruffles.size(); x++){
			compostRecipes.add(new CompostBinRecipe(new ItemStack(ModItems.TRUFFLES,1,x), 4, 0));
		}

		if(ModConfig.ENABLE_TRUFFLES){
			for(int x = 0; x < EnumTruffles.size(); x++){
				auctionRecipes.add(new TruffleAuctionRecipe(new ItemStack(ModItems.TRUFFLES, 1, x), new ItemStack(Items.GOLD_NUGGET)));
			}
		}

		vivariumRecipes.add(new VivariumRecipe(new ItemStack(Blocks.WATERLILY), BaseRecipes.teredo_grub, 2000, 20));

	}

}