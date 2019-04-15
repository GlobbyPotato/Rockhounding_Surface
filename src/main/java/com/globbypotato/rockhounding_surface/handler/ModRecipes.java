package com.globbypotato.rockhounding_surface.handler;

import com.globbypotato.rockhounding_surface.ModBlocks;
import com.globbypotato.rockhounding_surface.ModItems;
import com.globbypotato.rockhounding_surface.enums.EnumAgingItems;
import com.globbypotato.rockhounding_surface.enums.EnumGypsumBlocks;
import com.globbypotato.rockhounding_surface.enums.EnumGypsumItems;
import com.globbypotato.rockhounding_surface.machines.recipe.MachineRecipes;
import com.globbypotato.rockhounding_surface.utils.BaseRecipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
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
			GameRegistry.addShapelessRecipe(new ResourceLocation(Reference.MODID, "enriched_bonemeal"), 			new ResourceLocation(Reference.MODID, "fertilizers"), 	new ItemStack(ModItems.GYPSUM_ITEMS, 2, EnumGypsumItems.BONEMEAL.ordinal()), 				new Ingredient[] { Ingredient.fromStacks(new ItemStack(Items.DYE, 1, 15)), new OreIngredient("dustGypsum"), new OreIngredient("dustGypsum"), new OreIngredient("dustGypsum"), new OreIngredient("dustGypsum"), new OreIngredient("dustGypsum"), new OreIngredient("dustGypsum"), new OreIngredient("dustGypsum"), new OreIngredient("dustGypsum") });
		}
		if(!IReciperBase.chemistryLoaded()){
			GameRegistry.addShapelessRecipe(new ResourceLocation(Reference.MODID, "organic_compost"), 			new ResourceLocation(Reference.MODID, "aging"), 		agingItem(8, EnumAgingItems.ORGANIC_COMPOST), 			new Ingredient[] { Ingredient.fromStacks(new ItemStack(Items.WATER_BUCKET)), Ingredient.fromStacks(BaseRecipes.compost), Ingredient.fromStacks(BaseRecipes.compost), Ingredient.fromStacks(BaseRecipes.compost), Ingredient.fromStacks(BaseRecipes.compost), Ingredient.fromStacks(BaseRecipes.compost), Ingredient.fromStacks(BaseRecipes.compost), Ingredient.fromStacks(BaseRecipes.compost), Ingredient.fromStacks(BaseRecipes.compost) });

			if(OreDictionary.doesOreNameExist("dustIron")){
				GameRegistry.addShapelessRecipe(new ResourceLocation(Reference.MODID, "iron_compost"),			new ResourceLocation(Reference.MODID, "aging"), 		agingItem(8, EnumAgingItems.IRON_COMPOST), 				new Ingredient[] { Ingredient.fromStacks(new ItemStack(Items.WATER_BUCKET)), Ingredient.fromStacks(agingItem(1, EnumAgingItems.ORGANIC_COMPOST)),Ingredient.fromStacks(agingItem(1, EnumAgingItems.ORGANIC_COMPOST)),new OreIngredient("dustIron"),new OreIngredient("dustIron"),new OreIngredient("dustIron"),new OreIngredient("dustIron"),new OreIngredient("dustIron"),new OreIngredient("dustIron") });
			}else{
				GameRegistry.addShapelessRecipe(new ResourceLocation(Reference.MODID, "iron_compost"), 			new ResourceLocation(Reference.MODID, "aging"), 		agingItem(3, EnumAgingItems.IRON_COMPOST), 				new Ingredient[] { Ingredient.fromStacks(new ItemStack(Items.WATER_BUCKET)), Ingredient.fromStacks(agingItem(1, EnumAgingItems.ORGANIC_COMPOST)),Ingredient.fromStacks(agingItem(1, EnumAgingItems.ORGANIC_COMPOST)), new OreIngredient("nuggetIron"), new OreIngredient("nuggetIron"), new OreIngredient("nuggetIron"), new OreIngredient("nuggetIron"), new OreIngredient("nuggetIron"), new OreIngredient("nuggetIron") });
			}

			if(OreDictionary.doesOreNameExist("dustCopper")){
				GameRegistry.addShapelessRecipe(new ResourceLocation(Reference.MODID, "copper_compost"),		new ResourceLocation(Reference.MODID, "aging"), 		agingItem(8, EnumAgingItems.COPPER_COMPOST), 			new Ingredient[] { Ingredient.fromStacks(new ItemStack(Items.WATER_BUCKET)), Ingredient.fromStacks(agingItem(1, EnumAgingItems.ORGANIC_COMPOST)),Ingredient.fromStacks(agingItem(1, EnumAgingItems.ORGANIC_COMPOST)),new OreIngredient("dustCopper"),new OreIngredient("dustCopper"),new OreIngredient("dustCopper"),new OreIngredient("dustCopper"),new OreIngredient("dustCopper"),new OreIngredient("dustCopper") });
			}else{
				GameRegistry.addShapelessRecipe(new ResourceLocation(Reference.MODID, "copper_compost"),		new ResourceLocation(Reference.MODID, "aging"), 		agingItem(2, EnumAgingItems.COPPER_COMPOST), 			new Ingredient[] { Ingredient.fromStacks(new ItemStack(Items.WATER_BUCKET)), Ingredient.fromStacks(agingItem(1, EnumAgingItems.ORGANIC_COMPOST)),Ingredient.fromStacks(agingItem(1, EnumAgingItems.ORGANIC_COMPOST)),Ingredient.fromStacks(new ItemStack(Blocks.STONE,1,5)),Ingredient.fromStacks(new ItemStack(Blocks.STONE,1,5)),Ingredient.fromStacks(new ItemStack(Blocks.STONE,1,5)),Ingredient.fromStacks(new ItemStack(Blocks.STONE,1,5)),Ingredient.fromStacks(new ItemStack(Blocks.STONE,1,5)),Ingredient.fromStacks(new ItemStack(Blocks.STONE,1,5)) });
			}

			if(OreDictionary.doesOreNameExist("dustChromium")){
				GameRegistry.addShapelessRecipe(new ResourceLocation(Reference.MODID, "chromium_compost"),		new ResourceLocation(Reference.MODID, "aging"), 		agingItem(8, EnumAgingItems.CHROMIUM_COMPOST), 			new Ingredient[] { Ingredient.fromStacks(new ItemStack(Items.WATER_BUCKET)), Ingredient.fromStacks(agingItem(1, EnumAgingItems.ORGANIC_COMPOST)),Ingredient.fromStacks(agingItem(1, EnumAgingItems.ORGANIC_COMPOST)),new OreIngredient("dustChromium"),new OreIngredient("dustChromium"),new OreIngredient("dustChromium"),new OreIngredient("dustChromium"),new OreIngredient("dustChromium"),new OreIngredient("dustChromium") });
			}else{
				if(OreDictionary.doesOreNameExist("dustChrome")){
					GameRegistry.addShapelessRecipe(new ResourceLocation(Reference.MODID, "chromium_compost"),	new ResourceLocation(Reference.MODID, "aging"), 		agingItem(8, EnumAgingItems.CHROMIUM_COMPOST), 			new Ingredient[] { Ingredient.fromStacks(new ItemStack(Items.WATER_BUCKET)), Ingredient.fromStacks(agingItem(1, EnumAgingItems.ORGANIC_COMPOST)),Ingredient.fromStacks(agingItem(1, EnumAgingItems.ORGANIC_COMPOST)),new OreIngredient("dustChrome"),new OreIngredient("dustChrome"),new OreIngredient("dustChrome"),new OreIngredient("dustChrome"),new OreIngredient("dustChrome"),new OreIngredient("dustChrome") });
				}else{
					GameRegistry.addShapelessRecipe(new ResourceLocation(Reference.MODID, "chromium_compost"),	new ResourceLocation(Reference.MODID, "aging"), 		agingItem(2, EnumAgingItems.CHROMIUM_COMPOST), 			new Ingredient[] { Ingredient.fromStacks(new ItemStack(Items.WATER_BUCKET)), Ingredient.fromStacks(agingItem(1, EnumAgingItems.ORGANIC_COMPOST)),Ingredient.fromStacks(agingItem(1, EnumAgingItems.ORGANIC_COMPOST)),Ingredient.fromStacks(new ItemStack(Blocks.STONE,1,3)),Ingredient.fromStacks(new ItemStack(Blocks.STONE,1,3)),Ingredient.fromStacks(new ItemStack(Blocks.STONE,1,3)),Ingredient.fromStacks(new ItemStack(Blocks.STONE,1,3)),Ingredient.fromStacks(new ItemStack(Blocks.STONE,1,3)),Ingredient.fromStacks(new ItemStack(Blocks.STONE,1,3)) });
				}
			}

			if(OreDictionary.doesOreNameExist("dustManganese")){
				GameRegistry.addShapelessRecipe(new ResourceLocation(Reference.MODID, "manganese_compost"),		new ResourceLocation(Reference.MODID, "aging"), 		agingItem(8, EnumAgingItems.MANGANESE_COMPOST), 		new Ingredient[] { Ingredient.fromStacks(new ItemStack(Items.WATER_BUCKET)), Ingredient.fromStacks(agingItem(1, EnumAgingItems.ORGANIC_COMPOST)),Ingredient.fromStacks(agingItem(1, EnumAgingItems.ORGANIC_COMPOST)),new OreIngredient("dustManganese"),new OreIngredient("dustManganese"),new OreIngredient("dustManganese"),new OreIngredient("dustManganese"),new OreIngredient("dustManganese"),new OreIngredient("dustManganese") });
			}else{
				GameRegistry.addShapelessRecipe(new ResourceLocation(Reference.MODID, "manganese_compost"),		new ResourceLocation(Reference.MODID, "aging"), 		agingItem(8, EnumAgingItems.MANGANESE_COMPOST), 		new Ingredient[] { Ingredient.fromStacks(new ItemStack(Items.WATER_BUCKET)), Ingredient.fromStacks(agingItem(1, EnumAgingItems.ORGANIC_COMPOST)),Ingredient.fromStacks(agingItem(1, EnumAgingItems.ORGANIC_COMPOST)), new OreIngredient("sandstone"), new OreIngredient("sandstone"), new OreIngredient("sandstone"), new OreIngredient("sandstone"), new OreIngredient("sandstone"), new OreIngredient("sandstone") });
			}

			GameRegistry.addShapelessRecipe(new ResourceLocation(Reference.MODID, "calchedony_compost"),		new ResourceLocation(Reference.MODID, "aging"), 		agingItem(8, EnumAgingItems.CHALCEDONY_COMPOST), 		new Ingredient[] { Ingredient.fromStacks(new ItemStack(Items.WATER_BUCKET)), Ingredient.fromStacks(agingItem(1, EnumAgingItems.ORGANIC_COMPOST)),Ingredient.fromStacks(agingItem(1, EnumAgingItems.ORGANIC_COMPOST)), new OreIngredient("gemQuartz"), new OreIngredient("gemQuartz"), new OreIngredient("gemQuartz"), new OreIngredient("gemQuartz"), new OreIngredient("gemQuartz"), new OreIngredient("gemQuartz") });
			GameRegistry.addShapelessRecipe(new ResourceLocation(Reference.MODID, "lapis_compost"), 			new ResourceLocation(Reference.MODID, "aging"), 		agingItem(8, EnumAgingItems.LAPIS_COMPOST), 			new Ingredient[] { Ingredient.fromStacks(new ItemStack(Items.WATER_BUCKET)), Ingredient.fromStacks(agingItem(1, EnumAgingItems.ORGANIC_COMPOST)),Ingredient.fromStacks(agingItem(1, EnumAgingItems.ORGANIC_COMPOST)), new OreIngredient("gemLapis"), new OreIngredient("gemLapis"), new OreIngredient("gemLapis"), new OreIngredient("gemLapis"), new OreIngredient("gemLapis"), new OreIngredient("gemLapis") });

			GameRegistry.addShapelessRecipe(new ResourceLocation(Reference.MODID, "contaminating_compost"), 	new ResourceLocation(Reference.MODID, "aging"), 		agingItem(16, EnumAgingItems.CONTAMINATING_COMPOST), 	new Ingredient[] { Ingredient.fromStacks(new ItemStack(Items.WATER_BUCKET)), Ingredient.fromStacks(agingItem(1, EnumAgingItems.ORGANIC_COMPOST)),Ingredient.fromStacks(agingItem(1, EnumAgingItems.ORGANIC_COMPOST)), Ingredient.fromStacks(agingItem(1, EnumAgingItems.IRON_COMPOST)), Ingredient.fromStacks(agingItem(1, EnumAgingItems.IRON_COMPOST)), Ingredient.fromStacks(agingItem(1, EnumAgingItems.COPPER_COMPOST)), Ingredient.fromStacks(agingItem(1, EnumAgingItems.COPPER_COMPOST)), Ingredient.fromStacks(agingItem(1, EnumAgingItems.CHALCEDONY_COMPOST)), Ingredient.fromStacks(agingItem(1, EnumAgingItems.CHALCEDONY_COMPOST)) });
			GameRegistry.addShapelessRecipe(new ResourceLocation(Reference.MODID, "rainbow_compost"),			new ResourceLocation(Reference.MODID, "aging"), 		agingItem(16, EnumAgingItems.RAINBOW_COMPOST), 			new Ingredient[] { Ingredient.fromStacks(new ItemStack(Items.WATER_BUCKET)), Ingredient.fromStacks(agingItem(1, EnumAgingItems.ORGANIC_COMPOST)),Ingredient.fromStacks(agingItem(1, EnumAgingItems.ORGANIC_COMPOST)), Ingredient.fromStacks(agingItem(1, EnumAgingItems.IRON_COMPOST)), Ingredient.fromStacks(agingItem(1, EnumAgingItems.IRON_COMPOST)), Ingredient.fromStacks(agingItem(1, EnumAgingItems.CHALCEDONY_COMPOST)), Ingredient.fromStacks(agingItem(1, EnumAgingItems.CHALCEDONY_COMPOST)), Ingredient.fromStacks(agingItem(1, EnumAgingItems.LAPIS_COMPOST)), Ingredient.fromStacks(agingItem(1, EnumAgingItems.LAPIS_COMPOST)) });

			GameRegistry.addShapelessRecipe(new ResourceLocation(Reference.MODID, "teredo_colture"), 			new ResourceLocation(Reference.MODID, "aging"), 		agingItem(8, EnumAgingItems.TEREDO_COLTURE), 			new Ingredient[] { Ingredient.fromStacks(new ItemStack(Items.WATER_BUCKET)), Ingredient.fromStacks(BaseRecipes.teredo_grub), Ingredient.fromStacks(BaseRecipes.teredo_grub), Ingredient.fromStacks(BaseRecipes.teredo_grub), Ingredient.fromStacks(BaseRecipes.teredo_grub), Ingredient.fromStacks(BaseRecipes.teredo_grub), Ingredient.fromStacks(BaseRecipes.teredo_grub), Ingredient.fromStacks(BaseRecipes.teredo_grub), Ingredient.fromStacks(BaseRecipes.teredo_grub) });

		}

		MachineRecipes.machineRecipes();
	}

	private static void smelting(ItemStack input, ItemStack output) {
		GameRegistry.addSmelting(input, output, 2.0F);
	}

	private static ItemStack gypsumBlock(int i){
		return new ItemStack(ModBlocks.GYPSUM, 1, i);
	}
	
	private static ItemStack agingItem(int num, EnumAgingItems i){
		return new ItemStack(ModItems.AGING_ITEMS, num, i.ordinal());
	}

}