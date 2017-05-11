package com.globbypotato.rockhounding_surface.compat.jei;

import com.globbypotato.rockhounding_surface.ModBlocks;
import com.globbypotato.rockhounding_surface.ModItems;
import com.globbypotato.rockhounding_surface.compat.jei.camposter.ComposterRecipeCategory;
import com.globbypotato.rockhounding_surface.compat.jei.camposter.ComposterRecipeHandler;
import com.globbypotato.rockhounding_surface.compat.jei.camposter.ComposterRecipeWrapper;
import com.globbypotato.rockhounding_surface.compat.jei.incubator.WoodIncubatorRecipeCategory;
import com.globbypotato.rockhounding_surface.compat.jei.incubator.WoodIncubatorRecipeHandler;
import com.globbypotato.rockhounding_surface.compat.jei.incubator.WoodIncubatorRecipeWrapper;
import com.globbypotato.rockhounding_surface.machines.gui.GuiCompostBin;
import com.globbypotato.rockhounding_surface.machines.gui.GuiWoodIncubator;

import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IIngredientBlacklist;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class RockhoundingPlugin extends BlankModPlugin{

	public static IJeiHelpers jeiHelpers;

	@Override
	public void register(IModRegistry registry) {

		jeiHelpers = registry.getJeiHelpers();
		IGuiHelper guiHelper = jeiHelpers.getGuiHelper();

		registry.addRecipeCategories(
				new WoodIncubatorRecipeCategory(guiHelper),
				new ComposterRecipeCategory(guiHelper)
				);

		registry.addRecipeHandlers(
				new WoodIncubatorRecipeHandler(),
				new ComposterRecipeHandler()
				);
		registry.addRecipes(WoodIncubatorRecipeWrapper.getRecipes());
		registry.addRecipeClickArea(GuiWoodIncubator.class, 110, 58, 15, 14, RHRecipeUID.WOOD_INCUBATOR);
		registry.addRecipeCategoryCraftingItem(new ItemStack(ModBlocks.woodIncubator), RHRecipeUID.WOOD_INCUBATOR);

		registry.addRecipes(ComposterRecipeWrapper.getRecipes());
		registry.addRecipeClickArea(GuiCompostBin.class, 49, 21, 16, 14, RHRecipeUID.COMPOSTER);
		registry.addRecipeClickArea(GuiCompostBin.class, 111, 54, 16, 14, RHRecipeUID.COMPOSTER);
		registry.addRecipeCategoryCraftingItem(new ItemStack(ModBlocks.compostBin), RHRecipeUID.COMPOSTER);

		registry.addDescription(new ItemStack(ModItems.gypsumItems,1,4), "Compost can be produced inside the Compost Bin. It accepts any organic object like seeds, saplings, leaves, food, vegetation and customized items. Every type of ingredient has a different composting factor depending on their complexity or type: where seeds have the lowest value, food will have an higher one. The highest value is given by the peat chunks available from Ore Tiers, due to the already advanced maceration. Composting is also extended to any oredicted object related to a given registered compostable input.");

		registry.addDescription(new ItemStack(ModItems.agingItems,1,9), "Teredo Grubs can be randomly found when breaking Lily Pads. The plant will be eventually lost in the occurrency.");

		registry.addDescription(new ItemStack(ModBlocks.compostBin), "This device turns any food or vegetation item into compost, material that will be used in some of the mod features. It also accepts additional recipes.");

		IIngredientBlacklist itemBlacklist = registry.getJeiHelpers().getIngredientBlacklist();
		itemBlacklist.addIngredientToBlacklist(new ItemStack(ModBlocks.gypsumSlabsLo, 1, 6));
		itemBlacklist.addIngredientToBlacklist(new ItemStack(ModBlocks.gypsum, 1, 6));
		itemBlacklist.addIngredientToBlacklist(new ItemStack(ModBlocks.gypsumBushHi));
		itemBlacklist.addIngredientToBlacklist(new ItemStack(ModBlocks.gypsumCrops, 1, 1));
		itemBlacklist.addIngredientToBlacklist(new ItemStack(ModBlocks.gypsumCrops, 1, 4));
		itemBlacklist.addIngredientToBlacklist(new ItemStack(ModBlocks.gypsumCrops, 1, 5));
		itemBlacklist.addIngredientToBlacklist(new ItemStack(ModBlocks.ricegrassCrop));
	}
}