package com.globbypotato.rockhounding_surface.handler;

import java.util.ArrayList;

import com.globbypotato.rockhounding_surface.ModBlocks;
import com.globbypotato.rockhounding_surface.ModItems;
import com.globbypotato.rockhounding_surface.enums.EnumFossilPlanks;
import com.globbypotato.rockhounding_surface.enums.EnumGypsumBlocks;
import com.globbypotato.rockhounding_surface.enums.EnumTruffles;
import com.globbypotato.rockhounding_surface.fluids.ModFluids;
import com.globbypotato.rockhounding_surface.integration.SupportUtils;
import com.globbypotato.rockhounding_surface.machines.recipe.CompostBinRecipe;
import com.globbypotato.rockhounding_surface.machines.recipe.WoodIncubatorRecipe;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class ModRecipes {
	public static ArrayList<WoodIncubatorRecipe> woodIncubatorRecipes = new ArrayList<WoodIncubatorRecipe>();
	public static ArrayList<CompostBinRecipe> compostRecipes = new ArrayList<CompostBinRecipe>();
	static ItemStack gypsumSand = new ItemStack(ModBlocks.whiteSand);

	static ItemStack gypsumPlain = gypsum(0);
	static ItemStack gypsumBlock = gypsum(1);
	static ItemStack gypsumBrick = gypsum(2);
	static ItemStack gypsumCracked = gypsum(3);
	static ItemStack gypsumCarved = gypsum(4);
	static ItemStack gypsumMossy = gypsum(5);
	static ItemStack gypsumTile = gypsum(8);
	static ItemStack gypsumCobble = gypsum(10);
	static ItemStack gypsumCreeper = gypsum(11);
	static ItemStack gypsumBlockSlab = new ItemStack(ModBlocks.gypsumSlabsLo,1,1);
	static ItemStack gypsumBrickSlab = new ItemStack(ModBlocks.gypsumSlabsLo,1,2);
	static ItemStack gypsumTileSlab = new ItemStack(ModBlocks.gypsumSlabsLo,1,8);

	static ItemStack gypsumPlains = gypsums(8,0);
	static ItemStack gypsumBlocks = gypsums(4,1);
	static ItemStack gypsumBricks = gypsums(4,2);
	static ItemStack gypsumFractals = gypsums(4,7);
	static ItemStack gypsumTiles = gypsums(2,8);
	static ItemStack gypsumNethers = gypsums(2,9);
	static ItemStack gypsumPillars = new ItemStack(ModBlocks.gypsumPillar,4);
	static ItemStack gypsumColumns = new ItemStack(ModBlocks.gypsumColumn,2);

	static ItemStack gypsumDust = new ItemStack(ModItems.gypsumItems, 1, 0);
	static ItemStack gypsumDusts = new ItemStack(ModItems.gypsumItems, 4, 0);
	static ItemStack gypsumMeal = new ItemStack(ModItems.gypsumItems, 1, 1);
	static ItemStack gypsumCond = new ItemStack(ModItems.gypsumItems, 1, 2);
	static ItemStack gypsumMeals = new ItemStack(ModItems.gypsumItems, 2, 1);
	static ItemStack gypsumConds = new ItemStack(ModItems.gypsumItems, 4, 2);
	static ItemStack gypsumAmends = new ItemStack(ModItems.gypsumItems, 4, 3);
	static ItemStack compost = new ItemStack(ModItems.gypsumItems, 1, 4);
	static ItemStack bonemeal = new ItemStack(Items.DYE, 1, 15);
	static ItemStack bread = new ItemStack(Items.BREAD);
	static ItemStack sourberryFuit = new ItemStack(ModItems.sourberry);
	static ItemStack sourberryJuice = new ItemStack(ModItems.sourberryJuice);
	static ItemStack sourberryPie = new ItemStack(ModItems.sourberryPie);
	static ItemStack sourberryTea = new ItemStack(ModItems.sourberryTea);
	static ItemStack sourberry = new ItemStack(ModItems.sourberry);
	static ItemStack purplePear = new ItemStack(ModItems.purplePear);
	static ItemStack purplePearJuice = new ItemStack(ModItems.purplePearJuice);
	static ItemStack purplePearSmoothie = new ItemStack(ModItems.purplePearSmoothie);
	static ItemStack purplePearJam = new ItemStack(ModItems.purplePearJam);
	static ItemStack ricegrassItem = new ItemStack(ModItems.ricegrassItem);
	static ItemStack desertSpoon = new ItemStack(ModBlocks.gypsumBushLo, 1, 2);
	static ItemStack sotol = new ItemStack(ModItems.sotol);
	static ItemStack mesquitePie = new ItemStack(ModItems.mesquitePie);
	static ItemStack mesquiteFlour = new ItemStack(ModItems.gypsumItems,1,5);
	static ItemStack mesquiteFlower = new ItemStack(ModItems.gypsumItems,1,6);
	static ItemStack mesquiteLiquor = new ItemStack(ModItems.mesquiteLiquor);
	static ItemStack mesquiteTea = new ItemStack(ModItems.mesquiteTea);

	static ItemStack teredos = new ItemStack(ModItems.agingItems,1,9);

	public static void init() {
		ModRecipes.gypsumRecipes();
		ModRecipes.woodRecipes();
		ModRecipes.agingRecipes();
		ModRecipes.machineRecipes();
		ModRecipes.truffleRecipes();
	}

	private static ItemStack gypsum(int i) {
		return new ItemStack(ModBlocks.gypsum, 1, i);
	}
	private static ItemStack gypsums(int num, int meta) {
		return new ItemStack(ModBlocks.gypsum, num, meta);
	}

	private static void gypsumRecipes() {
	//compost bin
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.compostBin), new Object[] { "SPS", "PCP", "SPS", 'P', "slabWood", 'S', "plankWood", 'C', "chest" }));
	//gypsum plain
		GameRegistry.addRecipe(new ShapedOreRecipe(gypsumPlains, new Object[] { "GGG", "GWG", "GGG", 'G', gypsumSand, 'W', Items.WATER_BUCKET }));
		GameRegistry.addRecipe(new ShapedOreRecipe(gypsumPlains, new Object[] { "GGG", "GWG", "GGG", 'G', gypsumSand, 'W', "listAllwater" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.gypsumPlainStairs,4), new Object[] { "G  ", "GG ", "GGG", 'G', gypsumPlain }));
	//gypsum block
		GameRegistry.addRecipe(new ShapedOreRecipe(gypsumBlocks, new Object[] { "GG", "GG", 'G', gypsumPlain }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.gypsumBlockStairs,4), new Object[] { "G  ", "GG ", "GGG", 'G', gypsumBlock }));
	//gypsum brick
		GameRegistry.addRecipe(new ShapedOreRecipe(gypsumBricks, new Object[] { "GG", "GG", 'G', gypsumBlock }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.gypsumBrickStairs,4), new Object[] { "G  ", "GG ", "GGG", 'G', gypsumBrick }));
	//gypsum cracked
 		GameRegistry.addSmelting(gypsumBrick, gypsumCracked, 2.0F);
	//gypsum carved
		GameRegistry.addRecipe(new ShapedOreRecipe(gypsumCarved, new Object[] { "G", "G", 'G', gypsumBrickSlab }));
	//gypsum mossy
		GameRegistry.addRecipe(new ShapelessOreRecipe(gypsumMossy, new Object[] {gypsumBrick, "vine"}));
	//gypsum fractal
		GameRegistry.addRecipe(new ShapedOreRecipe(gypsumFractals, new Object[] { "GG", "GG", 'G', gypsumCracked }));
	//gypsum tile
		GameRegistry.addRecipe(new ShapedOreRecipe(gypsumTiles, new Object[] { "GG", "GG", 'G', gypsumBlockSlab }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.gypsumTileStairs,4), new Object[] { "G  ", "GG ", "GGG", 'G', gypsumTile }));
	//gypsum nether
		GameRegistry.addRecipe(new ShapedOreRecipe(gypsumNethers, new Object[] { "GG", "GG", 'G', gypsumTileSlab }));
	//gypsum pillar
		GameRegistry.addRecipe(new ShapedOreRecipe(gypsumPillars, new Object[] { "GG", "GG", 'G', gypsumTile }));
	//gypsum column
		GameRegistry.addRecipe(new ShapedOreRecipe(gypsumColumns, new Object[] { "G", "G", 'G', gypsumBlock }));
	//gypsum cobble
 		GameRegistry.addSmelting(gypsumSand, gypsumCobble, 2.0F);
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.gypsumCobbleStairs,4), new Object[] { "G  ", "GG ", "GGG", 'G', gypsumCobble }));
	//creeper
		GameRegistry.addRecipe(new ShapelessOreRecipe(gypsumCreeper, new Object[] {gypsumBlock, "dyeBlack"}));
    //Slabs
	    for(int x = 0; x < EnumGypsumBlocks.size(); x++){
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.gypsumSlabsLo, 6, x), new Object[] { "PPP", 'P', gypsums(1,x) }));
	    }
    //wall
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.gypsumWall, 6), new Object[] { "PPP", "PPP", 'P', gypsum(0) }));
	//gypsum dust
		GameRegistry.addRecipe(new ShapelessOreRecipe(gypsumDusts, new Object[] {gypsumSand}));
	//enriched bonemeal
		GameRegistry.addRecipe(new ShapelessOreRecipe(gypsumMeals, new Object[] {bonemeal, gypsumDust, gypsumDust, gypsumDust}));
	//conditioner
		GameRegistry.addRecipe(new ShapelessOreRecipe(gypsumConds, new Object[] {gypsumMeal, gypsumDust, gypsumDust, gypsumDust, gypsumDust, compost, compost, compost, compost}));
	//amendments
		GameRegistry.addRecipe(new ShapelessOreRecipe(gypsumAmends, new Object[] {gypsumCond, "gemQuartz", "gemQuartz", "gemQuartz", compost, compost, compost, compost, compost}));
	//desert spoon lead
		GameRegistry.addRecipe(new ShapedOreRecipe(Items.LEAD, new Object[] { "PPP", 'P', new ItemStack(ModBlocks.gypsumBushLo, 1, 1)}));
	//soaptree string
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.STRING,2), new Object[] { "PPP", 'P', desertSpoon}));
	//food
		GameRegistry.addRecipe(new ShapedOreRecipe(bread, new Object[] { "PPP", 'P', "cropRicegrass" }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(sourberryJuice, new Object[] {"toolJuicer", "cropSourberry"}));
		GameRegistry.addRecipe(new ShapelessOreRecipe(sourberryPie, new Object[] {"cropSourberry", "dustSalt", "egg"}));
		GameRegistry.addRecipe(new ShapelessOreRecipe(sourberryPie, new Object[] {"cropSourberry", Items.SUGAR, "egg"}));
 		GameRegistry.addSmelting(sourberry, sourberryTea, 2.0F);
		GameRegistry.addRecipe(new ShapelessOreRecipe(purplePearJuice, new Object[] {"toolJuicer", "cropPurplepear"}));
		GameRegistry.addRecipe(new ShapelessOreRecipe(purplePearSmoothie, new Object[] {"toolJuicer", "cropPurplepear", Items.SNOWBALL}));
		GameRegistry.addRecipe(new ShapedOreRecipe(purplePearJam, new Object[] { "PPP", "PSP", " B ", 'P', "cropPurplepear",  'S', Items.SUGAR, 'B', Items.BOWL}));
		GameRegistry.addRecipe(new ShapelessOreRecipe(sotol, new Object[] {"toolPot", "listAllwater", Items.SUGAR, desertSpoon}));
		GameRegistry.addRecipe(new ShapelessOreRecipe(mesquitePie, new Object[] {"toolMixingbowl", mesquiteFlour, "cropAlmond", "egg", Items.SUGAR}));
		GameRegistry.addRecipe(new ShapelessOreRecipe(mesquiteFlour, new Object[] {"toolMortarandpestle", "cropMesquite"}));
   		GameRegistry.addSmelting(mesquiteFlower, mesquiteTea, 1.0F);
		GameRegistry.addRecipe(new ShapelessOreRecipe(mesquiteLiquor, new Object[] {"toolPot", mesquiteFlour, "listAllwater"}));
		GameRegistry.addRecipe(new ShapelessOreRecipe(mesquiteLiquor, new Object[] {"toolPot", mesquiteFlour, Items.WATER_BUCKET}));
	}

	private static void machineRecipes() {
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
			compostRecipes.add(new CompostBinRecipe(SupportUtils.itemPeat(), true));
			compostRecipes.add(new CompostBinRecipe(SupportUtils.driedPeat(), true));
		}
	}

	private static void agingRecipes() {
		if(SupportUtils.rhChemistryLoaded()){
		//wood incubator
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.woodIncubator), new Object[] { "IcI", "ICI", "IGI", 'c', SupportUtils.logicChip(), 'I', SupportUtils.ironCasing(), 'C', SupportUtils.cabinet(), 'G', "blockGlass" }));
		//composts
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.agingItems, 8, 0), new Object[] {SupportUtils.chemFlask(), compost, compost, compost, compost, compost, compost, compost, compost}));
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.agingItems, 8, 1), new Object[] {SupportUtils.chemFlask(), "dustIron", "dustIron", "dustCopper", "dustCopper", "gemQuartz", "gemQuartz", "gemQuartz", "gemQuartz"}));
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.agingItems, 8, 2), new Object[] {SupportUtils.chemFlask(), "dustIron", "dustIron", "dustIron", "dustIron", "gemQuartz", "gemQuartz", "gemQuartz", "gemQuartz"}));
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.agingItems, 8, 3), new Object[] {SupportUtils.chemFlask(), "dustIron", "dustCopper", "dustCopper", "dustCopper", "gemQuartz", "gemQuartz", "gemQuartz", "gemQuartz"}));
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.agingItems, 8, 4), new Object[] {SupportUtils.chemFlask(), "dustIron", "dustChromium", "dustChromium", "dustChromium", "gemQuartz", "gemQuartz", "gemQuartz", "gemQuartz"}));
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.agingItems, 8, 5), new Object[] {SupportUtils.chemFlask(), "dustIron", "dustManganese", "dustManganese", "dustManganese", "gemQuartz", "gemQuartz", "gemQuartz", "gemQuartz"}));
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.agingItems, 8, 6), new Object[] {SupportUtils.chemFlask(), "dustIron", "dustCobalt", "dustManganese", "dustCopper", "gemQuartz", "gemQuartz", "gemQuartz", "gemQuartz"}));
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.agingItems, 8, 7), new Object[] {SupportUtils.chemFlask(), "gemLapis", "gemLapis", "gemLapis", "gemLapis", "gemLapis", "gemLapis", "gemLapis", "gemLapis"}));
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.agingItems, 8, 8), new Object[] {SupportUtils.chemFlask(), "dustIron", "dustIron", "dustIron", "dustIron", "dustSilicon", "dustSilicon", "dustSilicon", "dustSilicon"}));
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.agingItems, 8, 8), new Object[] {SupportUtils.chemFlask(), "gemCarnelian", "gemCarnelian", "gemCarnelian", "gemCarnelian", "gemCarnelian", "gemCarnelian", "gemCarnelian", "gemCarnelian"}));
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.agingItems, 8, 10),new Object[] {SupportUtils.chemFlask(), teredos, teredos, teredos, teredos, teredos, teredos, teredos, teredos}));
		}
	}

	private static void woodRecipes() {
	    for(int x = 0; x < 4; x++){
		    //wood planks
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.fossilPlanks, 4, x), 	 new Object[] { new ItemStack(ModBlocks.bogLogs,1,x) }));
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.fossilPlanks, 4, x + 4), new Object[] { new ItemStack(ModBlocks.fossilLogs,1,x) }));
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.fossilPlanks, 4, x + 8), new Object[] { new ItemStack(ModBlocks.petrifiedLogs,1,x) }));
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.fossilPlanks, 4, x + 12), new Object[] { new ItemStack(ModBlocks.coldLogs,1,x) }));
	    }
	    //Slabs
	    for(int x = 0; x < EnumFossilPlanks.size(); x++){
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.fossilSlabsLo, 6, x), new Object[] { "PPP", 'P', new ItemStack(ModBlocks.fossilPlanks, 1, x) }));
	    }
	    //fences
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.oakFences, 4), new Object[] 		 { "PSP", "PSP", 'P', new ItemStack(ModBlocks.fossilPlanks, 1, 0), 'S', new ItemStack(ModItems.fossilSticks, 1, 0) }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.spruceFences, 4), new Object[] 	 { "PSP", "PSP", 'P', new ItemStack(ModBlocks.fossilPlanks, 1, 1), 'S', new ItemStack(ModItems.fossilSticks, 1, 1) }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.kauriFences, 4), new Object[] 	 { "PSP", "PSP", 'P', new ItemStack(ModBlocks.fossilPlanks, 1, 2), 'S', new ItemStack(ModItems.fossilSticks, 1, 2) }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.mophaneFences, 4), new Object[] 	 { "PSP", "PSP", 'P', new ItemStack(ModBlocks.fossilPlanks, 1, 3), 'S', new ItemStack(ModItems.fossilSticks, 1, 3) }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.palmFences, 4), new Object[] 	 { "PSP", "PSP", 'P', new ItemStack(ModBlocks.fossilPlanks, 1, 4), 'S', new ItemStack(ModItems.fossilSticks, 1, 4) }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.pineFences, 4), new Object[] 	 { "PSP", "PSP", 'P', new ItemStack(ModBlocks.fossilPlanks, 1, 5), 'S', new ItemStack(ModItems.fossilSticks, 1, 5) }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.driftwoodFences, 4), new Object[] { "PSP", "PSP", 'P', new ItemStack(ModBlocks.fossilPlanks, 1, 6), 'S', new ItemStack(ModItems.fossilSticks, 1, 6) }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.araucariaFences, 4), new Object[] { "PSP", "PSP", 'P', new ItemStack(ModBlocks.fossilPlanks, 1, 7), 'S', new ItemStack(ModItems.fossilSticks, 1, 7) }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.betulaFences, 4), new Object[] 	 { "PSP", "PSP", 'P', new ItemStack(ModBlocks.fossilPlanks, 1, 8), 'S', new ItemStack(ModItems.fossilSticks, 1, 8) }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.castanoFences, 4), new Object[] 	 { "PSP", "PSP", 'P', new ItemStack(ModBlocks.fossilPlanks, 1, 9), 'S', new ItemStack(ModItems.fossilSticks, 1, 9) }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.opalizedFences, 4), new Object[]  { "PSP", "PSP", 'P', new ItemStack(ModBlocks.fossilPlanks, 1,10), 'S', new ItemStack(ModItems.fossilSticks, 1, 10) }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.rainbowFences, 4), new Object[] 	 { "PSP", "PSP", 'P', new ItemStack(ModBlocks.fossilPlanks, 1,11), 'S', new ItemStack(ModItems.fossilSticks, 1, 11) }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.azuriteFences, 4), new Object[] 	 { "PSP", "PSP", 'P', new ItemStack(ModBlocks.fossilPlanks, 1,12), 'S', new ItemStack(ModItems.fossilSticks, 1, 12) }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.teredoFences, 4), new Object[] 	 { "PSP", "PSP", 'P', new ItemStack(ModBlocks.fossilPlanks, 1,13), 'S', new ItemStack(ModItems.fossilSticks, 1, 13) }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.beechFences, 4), new Object[] 	 { "PSP", "PSP", 'P', new ItemStack(ModBlocks.fossilPlanks, 1,14), 'S', new ItemStack(ModItems.fossilSticks, 1, 14) }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.redwoodFences, 4), new Object[] 	 { "PSP", "PSP", 'P', new ItemStack(ModBlocks.fossilPlanks, 1,15), 'S', new ItemStack(ModItems.fossilSticks, 1, 15) }));
	    //gates
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.oakGate), new Object[] 		  { "SPS", "SPS", 'P', new ItemStack(ModBlocks.fossilPlanks, 1, 0), 'S', new ItemStack(ModItems.fossilSticks, 1, 0) }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.spruceGate), new Object[] 	  { "SPS", "SPS", 'P', new ItemStack(ModBlocks.fossilPlanks, 1, 1), 'S', new ItemStack(ModItems.fossilSticks, 1, 1) }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.kauriGate), new Object[] 	  { "SPS", "SPS", 'P', new ItemStack(ModBlocks.fossilPlanks, 1, 2), 'S', new ItemStack(ModItems.fossilSticks, 1, 2) }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.mophaneGate), new Object[] 	  { "SPS", "SPS", 'P', new ItemStack(ModBlocks.fossilPlanks, 1, 3), 'S', new ItemStack(ModItems.fossilSticks, 1, 3) }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.palmGate), new Object[] 	  	  { "SPS", "SPS", 'P', new ItemStack(ModBlocks.fossilPlanks, 1, 4), 'S', new ItemStack(ModItems.fossilSticks, 1, 4) }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.pineGate), new Object[]  	  { "SPS", "SPS", 'P', new ItemStack(ModBlocks.fossilPlanks, 1, 5), 'S', new ItemStack(ModItems.fossilSticks, 1, 5) }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.driftwoodGate), new Object[]   { "SPS", "SPS", 'P', new ItemStack(ModBlocks.fossilPlanks, 1, 6), 'S', new ItemStack(ModItems.fossilSticks, 1, 6) }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.araucariaGate), new Object[]   { "SPS", "SPS", 'P', new ItemStack(ModBlocks.fossilPlanks, 1, 7), 'S', new ItemStack(ModItems.fossilSticks, 1, 7) }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.betulaGate), new Object[] 	  { "SPS", "SPS", 'P', new ItemStack(ModBlocks.fossilPlanks, 1, 8), 'S', new ItemStack(ModItems.fossilSticks, 1, 8) }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.castanoGate), new Object[] 	  { "SPS", "SPS", 'P', new ItemStack(ModBlocks.fossilPlanks, 1, 9), 'S', new ItemStack(ModItems.fossilSticks, 1, 9) }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.opalizedGate), new Object[] 	  { "SPS", "SPS", 'P', new ItemStack(ModBlocks.fossilPlanks, 1,10), 'S', new ItemStack(ModItems.fossilSticks, 1, 10) }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.rainbowGate), new Object[] 	  { "SPS", "SPS", 'P', new ItemStack(ModBlocks.fossilPlanks, 1,11), 'S', new ItemStack(ModItems.fossilSticks, 1, 11) }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.azuriteGate), new Object[] 	  { "SPS", "SPS", 'P', new ItemStack(ModBlocks.fossilPlanks, 1,12), 'S', new ItemStack(ModItems.fossilSticks, 1, 12) }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.teredoGate), new Object[] 	  { "SPS", "SPS", 'P', new ItemStack(ModBlocks.fossilPlanks, 1,13), 'S', new ItemStack(ModItems.fossilSticks, 1, 13) }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.beechGate), new Object[] 	  { "SPS", "SPS", 'P', new ItemStack(ModBlocks.fossilPlanks, 1,14), 'S', new ItemStack(ModItems.fossilSticks, 1, 14) }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.redwoodGate), new Object[] 	  { "SPS", "SPS", 'P', new ItemStack(ModBlocks.fossilPlanks, 1,15), 'S', new ItemStack(ModItems.fossilSticks, 1, 15) }));
	    //stairs
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.oakStairs,4), new Object[] 	    { "P  ", "PP ", "PPP", 'P', new ItemStack(ModBlocks.fossilPlanks, 1, 0) }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.spruceStairs,4), new Object[]    { "P  ", "PP ", "PPP", 'P', new ItemStack(ModBlocks.fossilPlanks, 1, 1) }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.kauriStairs,4), new Object[]     { "P  ", "PP ", "PPP", 'P', new ItemStack(ModBlocks.fossilPlanks, 1, 2) }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.mophaneStairs,4), new Object[]   { "P  ", "PP ", "PPP", 'P', new ItemStack(ModBlocks.fossilPlanks, 1, 3) }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.palmStairs,4), new Object[] 	    { "P  ", "PP ", "PPP", 'P', new ItemStack(ModBlocks.fossilPlanks, 1, 4) }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.pineStairs,4), new Object[] 	    { "P  ", "PP ", "PPP", 'P', new ItemStack(ModBlocks.fossilPlanks, 1, 5) }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.driftwoodStairs,4), new Object[] { "P  ", "PP ", "PPP", 'P', new ItemStack(ModBlocks.fossilPlanks, 1, 6) }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.araucariaStairs,4), new Object[] { "P  ", "PP ", "PPP", 'P', new ItemStack(ModBlocks.fossilPlanks, 1, 7) }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.betulaStairs,4), new Object[] 	{ "P  ", "PP ", "PPP", 'P', new ItemStack(ModBlocks.fossilPlanks, 1, 8) }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.castanoStairs,4), new Object[]   { "P  ", "PP ", "PPP", 'P', new ItemStack(ModBlocks.fossilPlanks, 1, 9) }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.opalizedStairs,4), new Object[]  { "P  ", "PP ", "PPP", 'P', new ItemStack(ModBlocks.fossilPlanks, 1,10) }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.rainbowStairs,4), new Object[]   { "P  ", "PP ", "PPP", 'P', new ItemStack(ModBlocks.fossilPlanks, 1,11) }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.azuriteStairs,4), new Object[]   { "P  ", "PP ", "PPP", 'P', new ItemStack(ModBlocks.fossilPlanks, 1,12) }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.teredoStairs,4), new Object[]    { "P  ", "PP ", "PPP", 'P', new ItemStack(ModBlocks.fossilPlanks, 1,13) }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.beechStairs,4), new Object[]     { "P  ", "PP ", "PPP", 'P', new ItemStack(ModBlocks.fossilPlanks, 1,14) }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.redwoodStairs,4), new Object[]   { "P  ", "PP ", "PPP", 'P', new ItemStack(ModBlocks.fossilPlanks, 1,15) }));
	    //sticks
	    for(int x = 0; x < EnumFossilPlanks.size(); x++){
			GameRegistry.addRecipe(new ItemStack(ModItems.fossilSticks, 4, x), new Object[] { "P", "P", 'P', new ItemStack(ModBlocks.fossilPlanks,1,x) });
	    }
	}

	private static void truffleRecipes() {
 		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.truffleAuction), new Object[] {"WWW", " P ", "PPP", 'W', new ItemStack(Blocks.WOOL), 'P', "plankWood" }));
 		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.truffle_slices, 4), new Object[] {"cropTruffle"}));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.truffle_vodka), new Object[] {"listAllwater", "cropTruffle", "cropPotato", "listAllsugar"}));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.truffle_vodka), new Object[] {Items.WATER_BUCKET, "cropTruffle", "cropPotato", Items.SUGAR}));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.truffle_toast, 3), new Object[] {"foodTruffle", "bread"}));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.truffle_toast, 3), new Object[] {"foodTruffle", Items.BREAD}));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.truffle_pumpkin), new Object[] {"cropPumpkin", "cropTruffle", "cropPotato", Items.MILK_BUCKET}));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.truffle_pumpkin), new Object[] {"cropPumpkin", "cropTruffle", "cropPotato", "listAllmilk"}));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.truffle_omelette), new Object[] {"foodTruffle", "foodScrambledegg", "listAllmilk"}));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.truffle_rice), new Object[] {"foodTruffle", "cropOnion", "cropRice", "foodOliveoil"}));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.truffle_rice), new Object[] {"foodTruffle", "cropOnion", "cropRice", "foodButter"}));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.truffle_oil), new Object[] {"toolJuicer", "seedGrape", "cropTruffle"}));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.truffle_canape, 6), new Object[] {"foodTruffle", "foodToast", "foodBoiledegg", "foodOliveoil"}));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.truffle_canape, 6), new Object[] {"foodTruffle", "foodToast", "foodFirmtofu", "foodOliveoil"}));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.truffle_fillet), new Object[] {"foodTruffle", "listAllbeefcooked", "listAllmushroom", "cropSpiceleaf", "foodOliveoil"}));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.truffle_tomato), new Object[] {"foodTruffle", "cropTomato", "listAllmushroom", "foodOliveoil"}));
	}
}