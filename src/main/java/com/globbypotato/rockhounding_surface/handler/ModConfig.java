package com.globbypotato.rockhounding_surface.handler;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ModConfig {
	public static final String CATEGORY_BUILDING = "Building";
	public static final String CATEGORY_FOSSILWOOD = "FossilWood";
	public static final String CATEGORY_TRUFFLES = "Truffles";
	public static final String CATEGORY_TOOLS = "Tools";
	public static final String CATEGORY_GYPSUM = "Gypsum";

	public static int fossilRarity;
	public static int fossilSize;
	public static boolean ENABLE_OAK; //
	public static boolean ENABLE_SPRUCE; //
	public static boolean ENABLE_KAURI; //
	public static boolean ENABLE_MOPHANE; //
	public static boolean ENABLE_PALM; //
	public static boolean ENABLE_PINE; //
	public static boolean ENABLE_DRIFTWOOD; //
	public static boolean ENABLE_ARAUCARIA; //
	public static boolean ENABLE_BETULA; //
	public static boolean ENABLE_CASTANO; //
	public static boolean ENABLE_OPALIZED; //
	public static boolean ENABLE_RAINBOW; //
	public static boolean ENABLE_AZURITE; //
	public static boolean ENABLE_TEREDO; //
	public static boolean ENABLE_BEECH; //
	public static boolean ENABLE_REDWOOD; //
	public static boolean allowStairs;
	public static boolean allowFences;
	public static boolean allowGates;
	public static boolean allowChisel;
	public static boolean woodOredict;

	public static boolean ENABLE_TRUFFLES;
	public static int truffleRarity;
	public static boolean pigGriefing;
	public static boolean dirtSubstrate;
	
	public static int speedWoodIncubator;
	public static int speedCompost;
	public static int machineTank;

	public static boolean ENABLE_SANDS;
	public static boolean allowGypsumDeco;
	public static boolean enableFertilizers;
	public static boolean droppedGypsum;

	public static void loadConfig(FMLPreInitializationEvent event) {
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();

	//BUILDING
		config.addCustomCategoryComment(CATEGORY_BUILDING, "These settings handle the allowed building parts.");
		allowStairs = config.get(						CATEGORY_BUILDING, 	"ALLOW_STAIRS", 			true,	"Include the mod stairs to the game").getBoolean();
		allowFences = config.get(						CATEGORY_BUILDING, 	"ALLOW_FENCES", 			true,	"Include the mod fences to the game").getBoolean();
		allowGates = config.get(						CATEGORY_BUILDING, 	"ALLOW_GATES", 				true,	"Include the mod fence gates to the game").getBoolean();
		allowChisel = config.get(						CATEGORY_BUILDING, 	"ALLOW_CHISEL", 			true,	"Include the mod chiseled blocks to the game").getBoolean();

	//FOSSIL WOOD
		config.addCustomCategoryComment(CATEGORY_FOSSILWOOD, "These settings handle the fossil wood parameters.");
		fossilRarity = config.get(						CATEGORY_FOSSILWOOD, "WOOD_FREQUENCY", 			20, 	"Frequency of the fossil wood generation").getInt();
		fossilSize = config.get(						CATEGORY_FOSSILWOOD, "WOOD_SIZE", 				8, 		"Size of the wood formation").getInt();
        woodOredict = config.get(						CATEGORY_FOSSILWOOD, "WOOD_OREDICT", 			false,	"Wheter the wood parts will be oredicted for common uses").getBoolean();
        ENABLE_OAK = config.get(						CATEGORY_FOSSILWOOD, "ENABLE_BOG_OAK", 			true,	"Enable the spawn of the specific fossil wood").getBoolean();
        ENABLE_SPRUCE = config.get(						CATEGORY_FOSSILWOOD, "ENABLE_BOG_SPRUCE", 		true,	"Enable the spawn of the specific fossil wood").getBoolean();
        ENABLE_KAURI = config.get(						CATEGORY_FOSSILWOOD, "ENABLE_KAURI", 			true,	"Enable the spawn of the specific fossil wood").getBoolean();
        ENABLE_MOPHANE = config.get(					CATEGORY_FOSSILWOOD, "ENABLE_MOPHANE", 			true,	"Enable the spawn of the specific fossil wood").getBoolean();
        ENABLE_PALM = config.get(						CATEGORY_FOSSILWOOD, "ENABLE_PALM", 			true,	"Enable the spawn of the specific fossil wood").getBoolean();
        ENABLE_PINE = config.get(						CATEGORY_FOSSILWOOD, "ENABLE_PINE", 			true,	"Enable the spawn of the specific fossil wood").getBoolean();
        ENABLE_DRIFTWOOD = config.get(					CATEGORY_FOSSILWOOD, "ENABLE_DRIFTWOOD",		true,	"Enable the spawn of the specific fossil wood").getBoolean();
        ENABLE_ARAUCARIA = config.get(					CATEGORY_FOSSILWOOD, "ENABLE_ARAUCARIA",		true,	"Enable the spawn of the specific fossil wood").getBoolean();
        ENABLE_BETULA = config.get(						CATEGORY_FOSSILWOOD, "ENABLE_BETULA", 			true,	"Enable the spawn of the specific fossil wood").getBoolean();
        ENABLE_CASTANO = config.get(					CATEGORY_FOSSILWOOD, "ENABLE_CASTANO", 			true,	"Enable the spawn of the specific fossil wood").getBoolean();
        ENABLE_OPALIZED = config.get(					CATEGORY_FOSSILWOOD, "ENABLE_OPALIZED", 		true,	"Enable the spawn of the specific fossil wood").getBoolean();
        ENABLE_RAINBOW = config.get(					CATEGORY_FOSSILWOOD, "ENABLE_RAINBOW", 			true,	"Enable the spawn of the specific fossil wood").getBoolean();
        ENABLE_AZURITE = config.get(					CATEGORY_FOSSILWOOD, "ENABLE_AZURITE", 			true,	"Enable the spawn of the specific fossil wood").getBoolean();
        ENABLE_TEREDO = config.get(						CATEGORY_FOSSILWOOD, "ENABLE_TEREDO", 			true,	"Enable the spawn of the specific fossil wood").getBoolean();
        ENABLE_BEECH = config.get(						CATEGORY_FOSSILWOOD, "ENABLE_BEECH", 			true,	"Enable the spawn of the specific fossil wood").getBoolean();
        ENABLE_REDWOOD = config.get(					CATEGORY_FOSSILWOOD, "ENABLE_REDWOOD", 			true,	"Enable the spawn of the specific fossil wood").getBoolean();

    // TRUFFLES
		config.addCustomCategoryComment(CATEGORY_TRUFFLES, "These settings handle the truffle parameters.");
		ENABLE_TRUFFLES = config.get(					CATEGORY_TRUFFLES, 	 "ENABLE_TRUFFLES", 		true,	"Enable the truffles finding and its features (food, auction)").getBoolean();
		truffleRarity = config.get(						CATEGORY_TRUFFLES, 	 "TRUFFLE_RARITY", 			16000,	"Rarity at which truffles could be discovered by pigs. The higher the rarer").getInt();
		pigGriefing = config.get(						CATEGORY_TRUFFLES, 	 "PIG_GRIEFING", 			false,	"Allow pigs to grief the block where truffles are discovered").getBoolean();

	// TOOLS
		config.addCustomCategoryComment(CATEGORY_TOOLS, "These settings handle the settings of machines and tools.");
		speedWoodIncubator = config.get(				CATEGORY_TOOLS, 	 "SPEED_INCUBATOR", 		400,	"Ticks required to process in the Wood Incubator").getInt();
		speedCompost = config.get(						CATEGORY_TOOLS, 	 "SPEED_COMPOST", 			1600,	"Ticks required to process in the Compost Bin").getInt();
		machineTank = config.get(						CATEGORY_TOOLS, 	 "TANK_CAPACITY", 			10000,	"Capacity for machine fluid tanks").getInt();

	//GYPSUM
		config.addCustomCategoryComment(CATEGORY_GYPSUM, "These settings handle the settings of the gypsum feature.");
		ENABLE_SANDS = config.get(						CATEGORY_GYPSUM, 	 "ENABLE_BIOME", 			true,	"Enable the White Sands Biome and its features (biome, food) before creating a world").getBoolean();
		allowGypsumDeco = config.get(					CATEGORY_GYPSUM, 	 "ALLOW_GYPSUM_DECO", 		true,	"Enable the gypsum blocks").getBoolean();
		enableFertilizers = config.get(					CATEGORY_GYPSUM, 	 "ALLOW_FERTILIZERS", 		true,	"Enable the gypsum fertilizers").getBoolean();
		droppedGypsum = config.get(						CATEGORY_GYPSUM, 	 "ALLOW_GYPSUM_TOSS", 		true,	"Enable the gypsum making by tossing White Sand into water").getBoolean();
		dirtSubstrate = config.get(						CATEGORY_GYPSUM, 	 "ALLOW_DIRT_SUBSTRATE", 	false,	"Add a dirt substrate under the gypsum top layer").getBoolean();

        if (config.hasChanged()) {
        	config.save();
        }
	}
}