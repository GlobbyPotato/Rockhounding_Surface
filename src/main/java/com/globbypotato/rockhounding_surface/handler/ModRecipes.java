package com.globbypotato.rockhounding_surface.handler;

import com.globbypotato.rockhounding_surface.ModBlocks;
import com.globbypotato.rockhounding_surface.ModItems;
import com.globbypotato.rockhounding_surface.enums.EnumAgingItems;
import com.globbypotato.rockhounding_surface.enums.EnumGypsumBlocks;
import com.globbypotato.rockhounding_surface.enums.EnumGypsumItems;
import com.globbypotato.rockhounding_surface.machines.recipe.MachineRecipes;
import com.globbypotato.rockhounding_surface.utils.BaseRecipes;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreIngredient;
import rockhounding.api.IReciperBase;

@Mod.EventBusSubscriber(modid = Reference.MODID)
public class ModRecipes{

	/**
	 * @param event  
	 */
	@SubscribeEvent
	public static void registerRecipes(final RegistryEvent.Register<IRecipe> event){
		if(ModConfig.allowGypsumDeco){
			smelting(new ItemStack(ModBlocks.WHITE_SAND), 					gypsumBlock(EnumGypsumBlocks.COBBLE.ordinal()));
			smelting(gypsumBlock(EnumGypsumBlocks.BRICKS.ordinal()), 		gypsumBlock(EnumGypsumBlocks.CRACKED.ordinal()));
		}
		if(ModConfig.ENABLE_SANDS){
			smelting(new ItemStack(ModItems.SOURBERRY), 					new ItemStack(ModItems.SOURBERRY_TEA));
			smelting(new ItemStack(ModItems.MESQUITE_FLOWER), 				new ItemStack(ModItems.MESQUITE_TEA));
		}

		if(ModConfig.enableFertilizers){
			GameRegistry.addShapelessRecipe(new ResourceLocation(Reference.MODID, "enriched_bonemeal"), new ResourceLocation(Reference.MODID, "fertilizers"), 	new ItemStack(ModItems.GYPSUM_ITEMS, 2, EnumGypsumItems.BONEMEAL.ordinal()), 				new Ingredient[] { Ingredient.fromStacks(new ItemStack(Items.DYE, 1, 15)), new OreIngredient("dustGypsum"), new OreIngredient("dustGypsum"), new OreIngredient("dustGypsum"), new OreIngredient("dustGypsum"), new OreIngredient("dustGypsum"), new OreIngredient("dustGypsum"), new OreIngredient("dustGypsum"), new OreIngredient("dustGypsum") });
		}
		if(IReciperBase.chemistryLoaded()){
			GameRegistry.addShapelessRecipe(new ResourceLocation(Reference.MODID, "teredo_colture"), 	new ResourceLocation(Reference.MODID, "aging"), 		new ItemStack(ModItems.AGING_ITEMS, 8, EnumAgingItems.TEREDO_COLTURE.ordinal()), 			new Ingredient[] { Ingredient.fromStacks(new ItemStack(Items.WATER_BUCKET)), Ingredient.fromStacks(BaseRecipes.teredo_grub), Ingredient.fromStacks(BaseRecipes.teredo_grub), Ingredient.fromStacks(BaseRecipes.teredo_grub), Ingredient.fromStacks(BaseRecipes.teredo_grub), Ingredient.fromStacks(BaseRecipes.teredo_grub), Ingredient.fromStacks(BaseRecipes.teredo_grub), Ingredient.fromStacks(BaseRecipes.teredo_grub), Ingredient.fromStacks(BaseRecipes.teredo_grub) });
		}

		MachineRecipes.machineRecipes();
	}

	private static void smelting(ItemStack input, ItemStack output) {
		GameRegistry.addSmelting(input, output, 2.0F);
	}

	private static ItemStack gypsumBlock(int i){
		return new ItemStack(ModBlocks.GYPSUM, 1, i);
	}
}