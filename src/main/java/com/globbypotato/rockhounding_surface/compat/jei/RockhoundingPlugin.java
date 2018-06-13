package com.globbypotato.rockhounding_surface.compat.jei;

import com.globbypotato.rockhounding_surface.ModBlocks;
import com.globbypotato.rockhounding_surface.ModItems;
import com.globbypotato.rockhounding_surface.compat.jei.auction.AuctionRecipeCategory;
import com.globbypotato.rockhounding_surface.compat.jei.auction.AuctionRecipeWrapper;
import com.globbypotato.rockhounding_surface.compat.jei.composter.ComposterRecipeCategory;
import com.globbypotato.rockhounding_surface.compat.jei.composter.ComposterRecipeWrapper;
import com.globbypotato.rockhounding_surface.compat.jei.incubator.WoodIncubatorRecipeCategory;
import com.globbypotato.rockhounding_surface.compat.jei.incubator.WoodIncubatorRecipeWrapper;
import com.globbypotato.rockhounding_surface.compat.jei.vivarium.VivariumRecipeCategory;
import com.globbypotato.rockhounding_surface.compat.jei.vivarium.VivariumRecipeWrapper;
import com.globbypotato.rockhounding_surface.enums.EnumGypsumBlocks;
import com.globbypotato.rockhounding_surface.handler.ModConfig;
import com.globbypotato.rockhounding_surface.machines.gui.GuiCompostBin;
import com.globbypotato.rockhounding_surface.machines.gui.GuiTruffleAuction;
import com.globbypotato.rockhounding_surface.machines.gui.GuiVivarium;
import com.globbypotato.rockhounding_surface.machines.gui.GuiWoodIncubator;
import com.globbypotato.rockhounding_surface.utils.BaseRecipes;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IIngredientBlacklist;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

@JEIPlugin
@SideOnly(Side.CLIENT)
public class RockhoundingPlugin implements IModPlugin {

	public static IJeiHelpers jeiHelpers;

	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		jeiHelpers = registry.getJeiHelpers();
		IGuiHelper guiHelper = jeiHelpers.getGuiHelper();
		if(ModConfig.ENABLE_TRUFFLES){
			registry.addRecipeCategories(	
				new AuctionRecipeCategory(guiHelper, RHRecipeUID.AUCTION)
			);
		}

		registry.addRecipeCategories(	
			new ComposterRecipeCategory(guiHelper, RHRecipeUID.COMPOSTER),
			new WoodIncubatorRecipeCategory(guiHelper, RHRecipeUID.WOOD_INCUBATOR),
			new VivariumRecipeCategory(guiHelper, RHRecipeUID.VIVARIUM)
		);
	}

	@Override
	public void register(IModRegistry registry) {
		IIngredientBlacklist itemBlacklist = registry.getJeiHelpers().getIngredientBlacklist();
		jeiHelpers = registry.getJeiHelpers();
		int rX = 156; int rY = 4; int rW = 16; int rH = 14; 

		if(ModConfig.ENABLE_TRUFFLES){
			registry.addRecipes(AuctionRecipeWrapper.getRecipes(), RHRecipeUID.AUCTION);
			registry.addRecipeClickArea(GuiTruffleAuction.class, rX, rY, rW, rH, RHRecipeUID.AUCTION);
			registry.addRecipeCatalyst(new ItemStack(ModBlocks.TRUFFLE_AUCTION), RHRecipeUID.AUCTION);
			String truffleString = "Truffles can be rarely found by pigs under the grass or mycelium in FOREST, CONIFEROUS and MUSHROOM biomes only. Each variant of truffle has its own rarity as indicated on the item tooltip. With them it's possible to prepare additional food, also as a companion of Pam's Harvestcraft, or set an auction through villagers to trade them for gold or wishlist items. The auction happens through an Auction Table in which to place the truffle and eventually a wishlist item. If around the table, in a radius of 10 blocks there are villagers, they will start rating the truffle and eventually place a bid. Rarer and precious truffles have a better base value with a chance for better income. The session will last 1 minute. When it is over, the truffle will be sold and gold will appear on the table. If the truffle received a very good review at the end of the auction, instead of nuggets, a copy of the wishlist object will be returned. Better truffles may require higher reviews to unlock the wishlist item. The number of villagers around the table will influence the auction.";
			registry.addIngredientInfo(new ItemStack(ModItems.TRUFFLES, 1, OreDictionary.WILDCARD_VALUE), ItemStack.class, truffleString);
			registry.addIngredientInfo(new ItemStack(ModBlocks.TRUFFLE_AUCTION), ItemStack.class, truffleString);
		}

		registry.addRecipes(WoodIncubatorRecipeWrapper.getRecipes(), RHRecipeUID.WOOD_INCUBATOR);
		registry.addRecipes(ComposterRecipeWrapper.getRecipes(), RHRecipeUID.COMPOSTER);
		registry.addRecipes(VivariumRecipeWrapper.getRecipes(), RHRecipeUID.VIVARIUM);

		registry.addRecipeClickArea(GuiWoodIncubator.class, rX, rY, rW, rH, RHRecipeUID.WOOD_INCUBATOR);
		registry.addRecipeClickArea(GuiCompostBin.class, rX, rY, rW, rH, RHRecipeUID.COMPOSTER);
		registry.addRecipeClickArea(GuiVivarium.class, rX, rY, rW, rH, RHRecipeUID.VIVARIUM);

		registry.addRecipeCatalyst(new ItemStack(ModBlocks.WOOD_INCUBATOR), RHRecipeUID.WOOD_INCUBATOR);
		registry.addRecipeCatalyst(new ItemStack(ModBlocks.COMPOST_BIN), RHRecipeUID.COMPOSTER);
		registry.addRecipeCatalyst(new ItemStack(ModBlocks.VIVARIUM), RHRecipeUID.VIVARIUM);

		registry.addIngredientInfo(BaseRecipes.compost, ItemStack.class, "Compost can be produced inside the Compost Bin. It accepts any organic object like seeds, saplings, leaves, food, vegetation and customized items. Every type of ingredient has a different composting factor depending on their complexity or type: where seeds have the lowest value, food will have an higher one. The highest value is given by the peat chunks available from Ore Tiers, due to the already advanced maceration. Composting is also extended to any oredicted object related to a given registered compostable input. The produced Organic Compost is used as base for several internal features. It can be also used to revitalize the gravel. It has a chance to enrich gravel blocks into coarse dirt. It applies on a 5x2x5 grid and has a 25% success rate to fertilize.");
		registry.addIngredientInfo(BaseRecipes.bonemeal, ItemStack.class, "Ths is the result of the bonemeal doubling recipe. It can be used as a vanilla bonemeal");
		registry.addIngredientInfo(BaseRecipes.conditioner, ItemStack.class, "Adding Gypsum Dust and Organic compost to bonemeal, it is possible to alter the function of the bonemeal into a Soil Conditioner. This fertilizer will improve the quality of the soil from coarse dirt to regular dirt and then to grass. Modded soil from BiomesOPlenty is made compatible. It applies on a 5x2x5 grid and has a 25% success rate to fertilize.");
		registry.addIngredientInfo(BaseRecipes.amendment, ItemStack.class, "This is the final alteration of the fertilizer. Adding some quartz substance and more Organic compost to the Soil Conditioner, the Soil Amendment will mutate the grass block into a Podzol block. It applies on a 5x2x5 grid and has a 25% success rate to fertilize.");

		itemBlacklist.addIngredientToBlacklist(new ItemStack(ModBlocks.FOSSIL_SLABS_HI, 1, OreDictionary.WILDCARD_VALUE));
		if(ModConfig.allowGypsumDeco){
			itemBlacklist.addIngredientToBlacklist(new ItemStack(ModBlocks.GYPSUM_SLABS_LO, 1, EnumGypsumBlocks.DOUBLE.ordinal()));
			itemBlacklist.addIngredientToBlacklist(new ItemStack(ModBlocks.GYPSUM_SLABS_HI, 1, OreDictionary.WILDCARD_VALUE));
		}
		if(ModConfig.ENABLE_SANDS){
			itemBlacklist.addIngredientToBlacklist(new ItemStack(ModBlocks.GYPSUM_BUSH_HI, 1, OreDictionary.WILDCARD_VALUE));
			itemBlacklist.addIngredientToBlacklist(new ItemStack(ModBlocks.GYPSUM_CROPS, 1, 1));
			itemBlacklist.addIngredientToBlacklist(new ItemStack(ModBlocks.GYPSUM_CROPS, 1, 4));
			itemBlacklist.addIngredientToBlacklist(new ItemStack(ModBlocks.GYPSUM_CROPS, 1, 5));
			itemBlacklist.addIngredientToBlacklist(new ItemStack(ModBlocks.RICEGRASS_CROP));
		}else{
			itemBlacklist.addIngredientToBlacklist(new ItemStack(ModItems.GYPSUM_ITEMS, 1, 5));
		}
	}
}