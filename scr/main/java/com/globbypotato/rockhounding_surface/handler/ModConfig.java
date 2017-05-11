package com.globbypotato.rockhounding_surface.handler;

import com.globbypotato.rockhounding_surface.machines.tileentity.TileEntityMachineEnergy;
import com.globbypotato.rockhounding_surface.world.FossilGenerator;
import com.globbypotato.rockhounding_surface.world.SandGenerator;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ModConfig {
	public static final String CATEGORY_FOSSILWOOD = "FossilWood";
	public static final String CATEGORY_TRUFFLES = "Truffles";
	public static final String CATEGORY_TOOLS = "Tools";
	public static final String CATEGORY_GYPSUM = "Gypsum";

	public static boolean woodOredict;
	public static boolean enableTruffles;
	public static int truffleRarity;
	public static boolean pigGriefing;
	public static boolean allowTab;

	public static int speedWoodIncubator;
	public static int speedCompost;
	public static int whiteSandsID;
	public static int machineTank;
	
	public static void loadConfig(FMLPreInitializationEvent event) {
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();

	//FOSSIL WOOD
		config.addCustomCategoryComment("FossilWood", "These settings handle the fossil wood parameters.");
		FossilGenerator.fossilRarity = config.get(		CATEGORY_FOSSILWOOD, "WOOD_FREQUENCY", 			20, 	"Frequency of the fossil wood spawning").getInt();
		FossilGenerator.fossilSize = config.get(		CATEGORY_FOSSILWOOD, "WOOD_SIZE", 				8, 		"Size of the wood formation").getInt();
        woodOredict = config.get(						CATEGORY_FOSSILWOOD, "WOOD_OREDICT", 			false,	"Wheter the wood parts will be oredicted for common uses").getBoolean();
        FossilGenerator.ENABLE_OAK = config.get(		CATEGORY_FOSSILWOOD, "ENABLE_BOG_OAK", 			true,	"Enable the spawn of the specific fossil wood").getBoolean();
        FossilGenerator.ENABLE_SPRUCE = config.get(		CATEGORY_FOSSILWOOD, "ENABLE_BOG_SPRUCE", 		true,	"Enable the spawn of the specific fossil wood").getBoolean();
        FossilGenerator.ENABLE_KAURI = config.get(		CATEGORY_FOSSILWOOD, "ENABLE_KAURI", 			true,	"Enable the spawn of the specific fossil wood").getBoolean();
        FossilGenerator.ENABLE_MOPHANE = config.get(	CATEGORY_FOSSILWOOD, "ENABLE_MOPHANE", 			true,	"Enable the spawn of the specific fossil wood").getBoolean();
        FossilGenerator.ENABLE_PALM = config.get(		CATEGORY_FOSSILWOOD, "ENABLE_PALM", 			true,	"Enable the spawn of the specific fossil wood").getBoolean();
        FossilGenerator.ENABLE_PINE = config.get(		CATEGORY_FOSSILWOOD, "ENABLE_PINE", 			true,	"Enable the spawn of the specific fossil wood").getBoolean();
        FossilGenerator.ENABLE_DRIFTWOOD = config.get(	CATEGORY_FOSSILWOOD, "ENABLE_DRIFTWOOD",		true,	"Enable the spawn of the specific fossil wood").getBoolean();
        FossilGenerator.ENABLE_ARAUCARIA = config.get(	CATEGORY_FOSSILWOOD, "ENABLE_ARAUCARIA",		true,	"Enable the spawn of the specific fossil wood").getBoolean();
        FossilGenerator.ENABLE_BETULA = config.get(		CATEGORY_FOSSILWOOD, "ENABLE_BETULA", 			true,	"Enable the spawn of the specific fossil wood").getBoolean();
        FossilGenerator.ENABLE_CASTANO = config.get(	CATEGORY_FOSSILWOOD, "ENABLE_CASTANO", 			true,	"Enable the spawn of the specific fossil wood").getBoolean();
        FossilGenerator.ENABLE_OPALIZED = config.get(	CATEGORY_FOSSILWOOD, "ENABLE_OPALIZED", 		true,	"Enable the spawn of the specific fossil wood").getBoolean();
        FossilGenerator.ENABLE_RAINBOW = config.get(	CATEGORY_FOSSILWOOD, "ENABLE_RAINBOW", 			true,	"Enable the spawn of the specific fossil wood").getBoolean();
        FossilGenerator.ENABLE_AZURITE = config.get(	CATEGORY_FOSSILWOOD, "ENABLE_AZURITE", 			true,	"Enable the spawn of the specific fossil wood").getBoolean();
        FossilGenerator.ENABLE_TEREDO = config.get(		CATEGORY_FOSSILWOOD, "ENABLE_TEREDO", 			true,	"Enable the spawn of the specific fossil wood").getBoolean();
        FossilGenerator.ENABLE_BEECH = config.get(		CATEGORY_FOSSILWOOD, "ENABLE_BEECH", 			true,	"Enable the spawn of the specific fossil wood").getBoolean();
        FossilGenerator.ENABLE_REDWOOD = config.get(	CATEGORY_FOSSILWOOD, "ENABLE_REDWOOD", 			true,	"Enable the spawn of the specific fossil wood").getBoolean();

    // TRUFFLES
		config.addCustomCategoryComment("Truffles", "These settings handle the truffle parameters.");
		enableTruffles = config.get(					CATEGORY_TRUFFLES, 	 "ENABLE_TRUFFLES", 		true,	"Enable the truffle discovery").getBoolean();
		truffleRarity = config.get(						CATEGORY_TRUFFLES, 	 "TRUFFLE_RARITY", 			16000,	"Rarity at which truffles could be discovered by pigs. The higher the rarer").getInt();
		pigGriefing = config.get(						CATEGORY_TRUFFLES, 	 "PIG_GRIEFING", 			false,	"Allow pigs to grief the block where truffles are discovered").getBoolean();

	// TOOLS
		config.addCustomCategoryComment("Tools", "These settings handle the settings of machines and tools.");
		speedWoodIncubator = config.get(				CATEGORY_TOOLS, 	 "SPEED_INCUBATOR", 		400,	"Ticks required to process in the Wood Incubator").getInt();
		speedCompost = config.get(						CATEGORY_TOOLS, 	 "SPEED_COMPOST", 			1600,	"Ticks required to process in the Compost Bin").getInt();
		machineTank = config.get(						CATEGORY_TOOLS, 	 "TANK_CAPACITY", 			9000,	"Capacity for machine fluid tanks. This value is increased by 1000 by default").getInt();
		TileEntityMachineEnergy.allowInductor = config.get(CATEGORY_TOOLS,   "PERMANENT_INDUCTION",		false,	"Make the Induction Heating Interface a machine permanent upgrade").getBoolean();

	//GYPSUM
		config.addCustomCategoryComment("Gypsum", "These settings handle the settings of the gypsum feature.");
		SandGenerator.sandFrequency = config.get(		CATEGORY_GYPSUM, 	 "WHITE_BEACH_FREQUENCY",	0,		"Frequency of the white sand beaches in world").getInt();
		whiteSandsID = config.get(						CATEGORY_GYPSUM, 	 "WHITE_SANDS_ID",		 	110, 	"ID of the White Sands biome").getInt();

        if (config.hasChanged()) {
        	config.save();
        }
	}
}