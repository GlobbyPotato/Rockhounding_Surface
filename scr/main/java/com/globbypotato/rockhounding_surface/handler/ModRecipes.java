package com.globbypotato.rockhounding_surface.handler;

import com.globbypotato.rockhounding_surface.ModBlocks;
import com.globbypotato.rockhounding_surface.ModItems;
import com.globbypotato.rockhounding_surface.enums.EnumFossilPlanks;
import com.globbypotato.rockhounding_surface.enums.EnumGypsumBlocks;
import com.globbypotato.rockhounding_surface.integration.SupportUtils;
import com.globbypotato.rockhounding_surface.utils.BaseRecipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class ModRecipes extends BaseRecipes {

	public static void init() {
		ModRecipes.deviceRecipes();
		ModRecipes.gypsumRecipes();
		ModRecipes.woodRecipes();
		ModRecipes.agingRecipes();
		ModRecipes.truffleRecipes();
	}

	private static void deviceRecipes() {
		//compost bin
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.compostBin), new Object[] { "SPS", "PCP", "SPS", 'P', "slabWood", 'S', "plankWood", 'C', "chest" }));
		//tryffle auction table
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.truffleAuction), new Object[] {"WWW", " P ", "PPP", 'W', new ItemStack(Blocks.WOOL), 'P', "plankWood" }));
		//wood incubator
		if(SupportUtils.rhChemistryLoaded()){
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.woodIncubator), new Object[] { "IcI", "ICI", "IGI", 'c', SupportUtils.logicChip(), 'I', SupportUtils.ironCasing(), 'C', SupportUtils.cabinet(), 'G', "blockGlass" }));
		}
	}

	private static void gypsumRecipes() {
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.gypsum, 8, 0), new Object[] { "GGG", "GWG", "GGG", 'G', new ItemStack(ModBlocks.whiteSand), 'W', Items.WATER_BUCKET }));
 		GameRegistry.addSmelting(new ItemStack(ModBlocks.whiteSand), new ItemStack(ModBlocks.gypsum, 1, 10), 2.0F);
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.gypsum, 4, 1), new Object[] { "GG", "GG", 'G', new ItemStack(ModBlocks.gypsum, 1, 0) }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.gypsum, 4, 2), new Object[] { "GG", "GG", 'G', new ItemStack(ModBlocks.gypsum, 1, 1) }));
 		GameRegistry.addSmelting(new ItemStack(ModBlocks.gypsum, 1, 2), new ItemStack(ModBlocks.gypsum, 1, 3), 2.0F);
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.gypsum, 1, 4), new Object[] { "G", "G", 'G', new ItemStack(ModBlocks.gypsumSlabsLo, 1, 2) }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.gypsum, 1, 5), new Object[] {new ItemStack(ModBlocks.gypsum, 1, 2), "vine"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.gypsum, 4, 7), new Object[] { "GG", "GG", 'G', new ItemStack(ModBlocks.gypsum, 1, 3) }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.gypsum, 2, 8), new Object[] { "GG", "GG", 'G', new ItemStack(ModBlocks.gypsumSlabsLo, 1, 1) }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.gypsum, 2, 9), new Object[] { "GG", "GG", 'G', new ItemStack(ModBlocks.gypsumSlabsLo, 1, 8) }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.gypsum, 1, 11), new Object[] {new ItemStack(ModBlocks.gypsum, 1, 1), "dyeBlack"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.gypsumPillar, 4), new Object[] { "GG", "GG", 'G', new ItemStack(ModBlocks.gypsum, 1, 8) }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.gypsumColumn, 2), new Object[] { "G", "G", 'G', new ItemStack(ModBlocks.gypsum, 1, 1) }));

		//slabs
	    for(int x = 0; x < EnumGypsumBlocks.size(); x++){
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.gypsumSlabsLo, 6, x), new Object[] { "PPP", 'P', new ItemStack(ModBlocks.gypsum, 1, x) }));
	    }

	    //fences
		if(ModConfig.allowFences){
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.gypsumWall, 6), new Object[] { "PPP", "PPP", 'P', new ItemStack(ModBlocks.gypsum, 1, 0) }));
		}

		//stairs
		if(ModConfig.allowStairs){
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.gypsumPlainStairs,4), new Object[] { "G  ", "GG ", "GGG", 'G', new ItemStack(ModBlocks.gypsum, 1, 0) }));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.gypsumBlockStairs,4), new Object[] { "G  ", "GG ", "GGG", 'G', new ItemStack(ModBlocks.gypsum, 1, 1) }));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.gypsumBrickStairs,4), new Object[] { "G  ", "GG ", "GGG", 'G', new ItemStack(ModBlocks.gypsum, 1, 2) }));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.gypsumTileStairs,4), new Object[]  { "G  ", "GG ", "GGG", 'G', new ItemStack(ModBlocks.gypsum, 1, 8) }));
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.gypsumCobbleStairs,4), new Object[] { "G  ", "GG ", "GGG", 'G', new ItemStack(ModBlocks.gypsum, 1, 10) }));
		}

	//gypsum dust
		GameRegistry.addRecipe(new ShapelessOreRecipe(gypsumDusts, new Object[] {new ItemStack(ModBlocks.whiteSand)}));
	//enriched bonemeal
		GameRegistry.addRecipe(new ShapelessOreRecipe(gypsumMeals, new Object[] {bonemeal, gypsumDust, gypsumDust, gypsumDust, gypsumDust, gypsumDust, gypsumDust, gypsumDust, gypsumDust}));
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

	private static void agingRecipes() {
		if(SupportUtils.rhChemistryLoaded()){
		//composts
			GameRegistry.addRecipe(new ShapelessOreRecipe(aging(8, 0), new Object[] {SupportUtils.chemFlask(), compost, compost, compost, compost, compost, compost, compost, compost}));
			GameRegistry.addRecipe(new ShapelessOreRecipe(aging(8, 1), new Object[] {SupportUtils.chemFlask(), "dustIron", "dustIron", "dustCopper", "dustCopper", "gemQuartz", "gemQuartz", "gemQuartz", "gemQuartz"}));
			GameRegistry.addRecipe(new ShapelessOreRecipe(aging(8, 2), new Object[] {SupportUtils.chemFlask(), "dustIron", "dustIron", "dustIron", "dustIron", "gemQuartz", "gemQuartz", "gemQuartz", "gemQuartz"}));
			GameRegistry.addRecipe(new ShapelessOreRecipe(aging(8, 3), new Object[] {SupportUtils.chemFlask(), "dustIron", "dustCopper", "dustCopper", "dustCopper", "gemQuartz", "gemQuartz", "gemQuartz", "gemQuartz"}));
			GameRegistry.addRecipe(new ShapelessOreRecipe(aging(8, 4), new Object[] {SupportUtils.chemFlask(), "dustIron", "dustChromium", "dustChromium", "dustChromium", "gemQuartz", "gemQuartz", "gemQuartz", "gemQuartz"}));
			GameRegistry.addRecipe(new ShapelessOreRecipe(aging(8, 5), new Object[] {SupportUtils.chemFlask(), "dustIron", "dustManganese", "dustManganese", "dustManganese", "gemQuartz", "gemQuartz", "gemQuartz", "gemQuartz"}));
			GameRegistry.addRecipe(new ShapelessOreRecipe(aging(8, 6), new Object[] {SupportUtils.chemFlask(), "dustIron", "dustCobalt", "dustManganese", "dustCopper", "gemQuartz", "gemQuartz", "gemQuartz", "gemQuartz"}));
			GameRegistry.addRecipe(new ShapelessOreRecipe(aging(8, 7), new Object[] {SupportUtils.chemFlask(), "gemLapis", "gemLapis", "gemLapis", "gemLapis", "gemLapis", "gemLapis", "gemLapis", "gemLapis"}));
			GameRegistry.addRecipe(new ShapelessOreRecipe(aging(8, 8), new Object[] {SupportUtils.chemFlask(), "dustIron", "dustIron", "dustIron", "dustIron", "dustSilicon", "dustSilicon", "dustSilicon", "dustSilicon"}));
			GameRegistry.addRecipe(new ShapelessOreRecipe(aging(8, 8), new Object[] {SupportUtils.chemFlask(), "gemCarnelian", "gemCarnelian", "gemCarnelian", "gemCarnelian", "gemCarnelian", "gemCarnelian", "gemCarnelian", "gemCarnelian"}));
			GameRegistry.addRecipe(new ShapelessOreRecipe(aging(8, 10),new Object[] {SupportUtils.chemFlask(), teredo, teredo, teredo, teredo, teredo, teredo, teredo, teredo}));
		}
	}

	private static void woodRecipes() {
	    //wood planks
	    for(int x = 0; x < 4; x++){
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.fossilPlanks, 4, x), 	 new Object[] { new ItemStack(ModBlocks.bogLogs,1,x) }));
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.fossilPlanks, 4, x + 4), new Object[] { new ItemStack(ModBlocks.fossilLogs,1,x) }));
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.fossilPlanks, 4, x + 8), new Object[] { new ItemStack(ModBlocks.petrifiedLogs,1,x) }));
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModBlocks.fossilPlanks, 4, x + 12), new Object[] { new ItemStack(ModBlocks.coldLogs,1,x) }));
	    }

	    //Slabs
	    for(int x = 0; x < EnumFossilPlanks.size(); x++){
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.fossilSlabsLo, 6, x), new Object[] { "PPP", 'P', new ItemStack(ModBlocks.fossilPlanks, 1, x) }));
	    }

	    //sticks
	    for(int x = 0; x < EnumFossilPlanks.size(); x++){
			GameRegistry.addRecipe(new ItemStack(ModItems.fossilSticks, 4, x), new Object[] { "P", "P", 'P', new ItemStack(ModBlocks.fossilPlanks,1,x) });
	    }

	    //fences
		if(ModConfig.allowFences){
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
		}

		//gates
		if(ModConfig.allowGates){
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
		}

	    //stairs
		if(ModConfig.allowStairs){
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
		}
	}

	private static void truffleRecipes() {
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