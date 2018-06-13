package com.globbypotato.rockhounding_surface;

import com.globbypotato.rockhounding_core.blocks.itemblocks.BaseMetaIB;
import com.globbypotato.rockhounding_core.blocks.itemblocks.PoweredIB;
import com.globbypotato.rockhounding_core.handlers.RegistryHandler;
import com.globbypotato.rockhounding_surface.blocks.fossil_wood.BogLogs;
import com.globbypotato.rockhounding_surface.blocks.fossil_wood.ColdLogs;
import com.globbypotato.rockhounding_surface.blocks.fossil_wood.FossilChiseled;
import com.globbypotato.rockhounding_surface.blocks.fossil_wood.FossilFences;
import com.globbypotato.rockhounding_surface.blocks.fossil_wood.FossilGates;
import com.globbypotato.rockhounding_surface.blocks.fossil_wood.FossilLogs;
import com.globbypotato.rockhounding_surface.blocks.fossil_wood.FossilPlanks;
import com.globbypotato.rockhounding_surface.blocks.fossil_wood.FossilSlabs;
import com.globbypotato.rockhounding_surface.blocks.fossil_wood.FossilStairs;
import com.globbypotato.rockhounding_surface.blocks.fossil_wood.PetrifiedLogs;
import com.globbypotato.rockhounding_surface.blocks.itemblocks.BushIB;
import com.globbypotato.rockhounding_surface.blocks.itemblocks.FossilSlabIB;
import com.globbypotato.rockhounding_surface.blocks.itemblocks.GypsumSlabIB;
import com.globbypotato.rockhounding_surface.blocks.itemblocks.ShrubIB;
import com.globbypotato.rockhounding_surface.blocks.white_sands.Gypsum;
import com.globbypotato.rockhounding_surface.blocks.white_sands.GypsumBushHi;
import com.globbypotato.rockhounding_surface.blocks.white_sands.GypsumBushLo;
import com.globbypotato.rockhounding_surface.blocks.white_sands.GypsumPillars;
import com.globbypotato.rockhounding_surface.blocks.white_sands.GypsumShrubs;
import com.globbypotato.rockhounding_surface.blocks.white_sands.GypsumSlabs;
import com.globbypotato.rockhounding_surface.blocks.white_sands.GypsumStairs;
import com.globbypotato.rockhounding_surface.blocks.white_sands.RicegrassCrop;
import com.globbypotato.rockhounding_surface.blocks.white_sands.WhiteSand;
import com.globbypotato.rockhounding_surface.enums.EnumBogLogs;
import com.globbypotato.rockhounding_surface.enums.EnumBushes;
import com.globbypotato.rockhounding_surface.enums.EnumChiseled;
import com.globbypotato.rockhounding_surface.enums.EnumColdLogs;
import com.globbypotato.rockhounding_surface.enums.EnumFossilLogs;
import com.globbypotato.rockhounding_surface.enums.EnumFossilPlanks;
import com.globbypotato.rockhounding_surface.enums.EnumGypsumBlocks;
import com.globbypotato.rockhounding_surface.enums.EnumPetrifiedLogs;
import com.globbypotato.rockhounding_surface.enums.EnumShrubs;
import com.globbypotato.rockhounding_surface.handler.ModConfig;
import com.globbypotato.rockhounding_surface.handler.Reference;
import com.globbypotato.rockhounding_surface.machines.CompostBin;
import com.globbypotato.rockhounding_surface.machines.TruffleAuction;
import com.globbypotato.rockhounding_surface.machines.Vivarium;
import com.globbypotato.rockhounding_surface.machines.WoodIncubator;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class ModBlocks {

// Fossil woods
	public static final Block BOG_LOGS = new BogLogs("bog_logs");
	public static final Block FOSSIL_LOGS = new FossilLogs("fossil_logs");
	public static final Block PETRIFIED_LOGS = new PetrifiedLogs("petrified_logs");
	public static final Block COLD_LOGS = new ColdLogs("cold_logs");
	public static final Block FOSSIL_PLANKS = new FossilPlanks("fossil_planks", EnumFossilPlanks.getNames());
	public static final Block FOSSIL_SLABS_LO = new FossilSlabs("fossil_slabs_lo", EnumFossilPlanks.getNames(), true);
	public static final Block FOSSIL_SLABS_HI = new FossilSlabs("fossil_slabs_hi", EnumFossilPlanks.getNames(), false);

	public static final Block WHITE_SAND = new WhiteSand("white_sand");
	public static final Block GYPSUM = new Gypsum("gypsum", EnumGypsumBlocks.getNames());
	public static final Block GYPSUM_SLABS_LO = new GypsumSlabs("gypsum_slabs_lo", EnumGypsumBlocks.getNames(), true);
	public static final Block GYPSUM_SLABS_HI = new GypsumSlabs("gypsum_slabs_hi", EnumGypsumBlocks.getNames(), false);
	public static final Block GYPSUM_PILLAR = new GypsumPillars("gypsum_pillar");
	public static final Block GYPSUM_COLUMN = new GypsumPillars("gypsum_column");

	public static final Block OAK_FENCES = new FossilFences("oak_fences", EnumFossilPlanks.OAK.getMapColor());
	public static final Block SPRUCE_FENCES = new FossilFences("spruce_fences", EnumFossilPlanks.SPRUCE.getMapColor());
	public static final Block KAURI_FENCES = new FossilFences("kauri_fences", EnumFossilPlanks.KAURI.getMapColor());
	public static final Block MOPHANE_FENCES = new FossilFences("mophane_fences", EnumFossilPlanks.MOPHANE.getMapColor());
	public static final Block PALM_FENCES = new FossilFences("palm_fences", EnumFossilPlanks.PALM.getMapColor());
	public static final Block PINE_FENCES = new FossilFences("pine_fences", EnumFossilPlanks.PINE.getMapColor());
	public static final Block DRIFTWOOD_FENCES = new FossilFences("driftwood_fences", EnumFossilPlanks.DRIFTWOOD.getMapColor());
	public static final Block ARAUCARIA_FENCES = new FossilFences("araucaria_fences", EnumFossilPlanks.ARAUCARIA.getMapColor());
	public static final Block BETULA_FENCES = new FossilFences("betula_fences", EnumFossilPlanks.BETULA.getMapColor());
	public static final Block CASTANO_FENCES = new FossilFences("castano_fences", EnumFossilPlanks.CASTANO.getMapColor());
	public static final Block OPALIZED_FENCES = new FossilFences("opalized_fences", EnumFossilPlanks.OPALIZED.getMapColor());
	public static final Block RAINBOW_FENCES = new FossilFences("rainbow_fences", EnumFossilPlanks.RAINBOW.getMapColor());
	public static final Block AZURITE_FENCES = new FossilFences("azurite_fences", EnumFossilPlanks.AZURITE.getMapColor());
	public static final Block BEECH_FENCES = new FossilFences("beech_fences", EnumFossilPlanks.BEECH.getMapColor());
	public static final Block TEREDO_FENCES = new FossilFences("teredo_fences", EnumFossilPlanks.TEREDO.getMapColor());
	public static final Block REDWOOD_FENCES = new FossilFences("redwood_fences", EnumFossilPlanks.REDWOOD.getMapColor());

	public static final Block OAK_GATE = new FossilGates("oak_gate");
	public static final Block SPRUCE_GATE = new FossilGates("spruce_gate");
	public static final Block KAURI_GATE = new FossilGates("kauri_gate");
	public static final Block MOPHANE_GATE = new FossilGates("mophane_gate");
	public static final Block PALM_GATE = new FossilGates("palm_gate");
	public static final Block PINE_GATE = new FossilGates("pine_gate");
	public static final Block DRIFTWOOD_GATE = new FossilGates("driftwood_gate");
	public static final Block ARAUCARIA_GATE = new FossilGates("araucaria_gate");
	public static final Block BETULA_GATE = new FossilGates("betula_gate");
	public static final Block CASTANO_GATE = new FossilGates("castano_gate");
	public static final Block OPALIZED_GATE = new FossilGates("opalized_gate");
	public static final Block RAINBOW_GATE = new FossilGates("rainbow_gate");
	public static final Block AZURITE_GATE = new FossilGates("azurite_gate");
	public static final Block BEECH_GATE = new FossilGates("beech_gate");
	public static final Block TEREDO_GATE = new FossilGates("teredo_gate");
	public static final Block REDWOOD_GATE = new FossilGates("redwood_gate");

	public static final Block OAK_STAIRS = new FossilStairs("oak_stairs", 0);
	public static final Block SPRUCE_STAIRS = new FossilStairs("spruce_stairs", 1);
	public static final Block KAURI_STAIRS = new FossilStairs("kauri_stairs", 2);
	public static final Block MOPHANE_STAIRS = new FossilStairs("mophane_stairs", 3);
	public static final Block PALM_STAIRS = new FossilStairs("palm_stairs", 4);
	public static final Block PINE_STAIRS = new FossilStairs("pine_stairs", 5);
	public static final Block DRIFTWOOD_STAIRS = new FossilStairs("driftwood_stairs", 6);
	public static final Block ARAUCARIA_STAIRS = new FossilStairs("araucaria_stairs", 7);
	public static final Block BETULA_STAIRS = new FossilStairs("betula_stairs", 8);
	public static final Block CASTANO_STAIRS = new FossilStairs("castano_stairs", 9);
	public static final Block OPALIZED_STAIRS = new FossilStairs("opalized_stairs", 10);
	public static final Block RAINBOW_STAIRS = new FossilStairs("rainbow_stairs", 11);
	public static final Block AZURITE_STAIRS = new FossilStairs("azurite_stairs", 12);
	public static final Block TEREDO_STAIRS = new FossilStairs("teredo_stairs", 13);
	public static final Block BEECH_STAIRS = new FossilStairs("beech_stairs", 14);
	public static final Block REDWOOD_STAIRS = new FossilStairs("redwood_stairs", 15);
	
	public static final Block GYPSUM_PLAIN_STAIRS = new GypsumStairs("gypsum_plain_stairs", EnumGypsumBlocks.PLAIN.ordinal());
	public static final Block GYPSUM_BLOCK_STAIRS = new GypsumStairs("gypsum_block_stairs", EnumGypsumBlocks.BLOCK.ordinal());
	public static final Block GYPSUM_BRICKS_STAIRS = new GypsumStairs("gypsum_bricks_stairs", EnumGypsumBlocks.BRICKS.ordinal());
	public static final Block GYPSUM_TILE_STAIRS = new GypsumStairs("gypsum_tile_stairs", EnumGypsumBlocks.TILE.ordinal());
	public static final Block GYPSUM_COBBLE_STAIRS = new GypsumStairs("gypsum_cobble_stairs", EnumGypsumBlocks.COBBLE.ordinal());

	public static final Block OAK_CHISELED = new FossilChiseled("oak_chiseled", EnumChiseled.getNames());
	public static final Block SPRUCE_CHISELED = new FossilChiseled("spruce_chiseled", EnumChiseled.getNames());
	public static final Block KAURI_CHISELED = new FossilChiseled("kauri_chiseled", EnumChiseled.getNames());
	public static final Block MOPHANE_CHISELED = new FossilChiseled("mophane_chiseled", EnumChiseled.getNames());
	public static final Block PALM_CHISELED = new FossilChiseled("palm_chiseled", EnumChiseled.getNames());
	public static final Block PINE_CHISELED = new FossilChiseled("pine_chiseled", EnumChiseled.getNames());
	public static final Block DRIFTWOOD_CHISELED = new FossilChiseled("driftwood_chiseled", EnumChiseled.getNames());
	public static final Block ARAUCARIA_CHISELED = new FossilChiseled("araucaria_chiseled", EnumChiseled.getNames());
	public static final Block BETULA_CHISELED = new FossilChiseled("betula_chiseled", EnumChiseled.getNames());
	public static final Block CASTANO_CHISELED = new FossilChiseled("castano_chiseled", EnumChiseled.getNames());
	public static final Block AZURITE_CHISELED = new FossilChiseled("azurite_chiseled", EnumChiseled.getNames());
	public static final Block TEREDO_CHISELED = new FossilChiseled("teredo_chiseled", EnumChiseled.getNames());
	public static final Block BEECH_CHISELED = new FossilChiseled("beech_chiseled", EnumChiseled.getNames());
	public static final Block REDWOOD_CHISELED = new FossilChiseled("redwood_chiseled", EnumChiseled.getNames());

	public static final Block RICEGRASS_CROP = new RicegrassCrop("ricegrass_crop");
	public static final Block GYPSUM_BUSH_LO = new GypsumBushLo("gypsum_bush_lo");
	public static final Block GYPSUM_BUSH_HI = new GypsumBushHi("gypsum_bush_hi");
	public static final Block GYPSUM_CROPS = new GypsumShrubs("gypsum_crops", Material.GRASS, EnumShrubs.getNames());

	public static final Block TRUFFLE_AUCTION = new TruffleAuction("truffle_auction");
	public static final Block COMPOST_BIN = new CompostBin("compost_bin");
	public static final Block VIVARIUM = new Vivarium("vivarium");
	public static final Block WOOD_INCUBATOR = new WoodIncubator("wood_incubator");

	@Mod.EventBusSubscriber(modid = Reference.MODID)
	public static class RegistrationHandler {

		// register the block block
		@SubscribeEvent
		public static void registerBlock(final RegistryEvent.Register<Block> event) {
			final IForgeRegistry<Block> registry = event.getRegistry();		
			registry.register(BOG_LOGS);
			registry.register(FOSSIL_LOGS);
			registry.register(PETRIFIED_LOGS);
			registry.register(COLD_LOGS);
			registry.register(FOSSIL_PLANKS);
			registry.register(FOSSIL_SLABS_LO);
			registry.register(FOSSIL_SLABS_HI);

			registry.register(WHITE_SAND);
			if(ModConfig.allowGypsumDeco){
				registry.register(GYPSUM);
				registry.register(GYPSUM_SLABS_LO);
				registry.register(GYPSUM_SLABS_HI);
				registry.register(GYPSUM_PILLAR);
				registry.register(GYPSUM_COLUMN);
			}
			if(ModConfig.ENABLE_SANDS){
				registry.register(RICEGRASS_CROP);
				registry.register(GYPSUM_BUSH_LO);
				registry.register(GYPSUM_BUSH_HI);
				registry.register(GYPSUM_CROPS);
			}

			if(ModConfig.ENABLE_TRUFFLES){
				registry.register(TRUFFLE_AUCTION);
			}

			registry.register(COMPOST_BIN);
			registry.register(WOOD_INCUBATOR);
			registry.register(VIVARIUM);

			if(ModConfig.allowFences){
				registry.register(OAK_FENCES);
				registry.register(SPRUCE_FENCES);
				registry.register(KAURI_FENCES);
				registry.register(MOPHANE_FENCES);
				registry.register(PALM_FENCES);
				registry.register(PINE_FENCES);
				registry.register(DRIFTWOOD_FENCES);
				registry.register(ARAUCARIA_FENCES);
				registry.register(BETULA_FENCES);
				registry.register(CASTANO_FENCES);
				registry.register(OPALIZED_FENCES);
				registry.register(RAINBOW_FENCES);
				registry.register(AZURITE_FENCES);
				registry.register(BEECH_FENCES);
				registry.register(TEREDO_FENCES);
				registry.register(REDWOOD_FENCES);
			}

			if(ModConfig.allowGates){
				registry.register(OAK_GATE);
				registry.register(SPRUCE_GATE);
				registry.register(KAURI_GATE);
				registry.register(MOPHANE_GATE);
				registry.register(PALM_GATE);
				registry.register(PINE_GATE);
				registry.register(DRIFTWOOD_GATE);
				registry.register(ARAUCARIA_GATE);
				registry.register(BETULA_GATE);
				registry.register(CASTANO_GATE);
				registry.register(OPALIZED_GATE);
				registry.register(RAINBOW_GATE);
				registry.register(AZURITE_GATE);
				registry.register(BEECH_GATE);
				registry.register(TEREDO_GATE);
				registry.register(REDWOOD_GATE);
			}

			if(ModConfig.allowStairs){
				registry.register(OAK_STAIRS);
				registry.register(SPRUCE_STAIRS);
				registry.register(KAURI_STAIRS);
				registry.register(MOPHANE_STAIRS);
				registry.register(PALM_STAIRS);
				registry.register(PINE_STAIRS);
				registry.register(DRIFTWOOD_STAIRS);
				registry.register(ARAUCARIA_STAIRS);
				registry.register(BETULA_STAIRS);
				registry.register(CASTANO_STAIRS);
				registry.register(OPALIZED_STAIRS);
				registry.register(RAINBOW_STAIRS);
				registry.register(AZURITE_STAIRS);
				registry.register(BEECH_STAIRS);
				registry.register(TEREDO_STAIRS);
				registry.register(REDWOOD_STAIRS);
				
				if(ModConfig.allowGypsumDeco){
					registry.register(GYPSUM_PLAIN_STAIRS);
					registry.register(GYPSUM_BLOCK_STAIRS);
					registry.register(GYPSUM_BRICKS_STAIRS);
					registry.register(GYPSUM_TILE_STAIRS);
					registry.register(GYPSUM_COBBLE_STAIRS);
				}
			}

			if(ModConfig.allowChisel){
				registry.register(OAK_CHISELED);
				registry.register(SPRUCE_CHISELED);
				registry.register(KAURI_CHISELED);
				registry.register(MOPHANE_CHISELED);
				registry.register(PALM_CHISELED);
				registry.register(PINE_CHISELED);
				registry.register(DRIFTWOOD_CHISELED);
				registry.register(ARAUCARIA_CHISELED);
				registry.register(BETULA_CHISELED);
				registry.register(CASTANO_CHISELED);
				registry.register(AZURITE_CHISELED);
				registry.register(BEECH_CHISELED);
				registry.register(TEREDO_CHISELED);
				registry.register(REDWOOD_CHISELED);
			}
		}

		// register the itemblock
		@SubscribeEvent
		public static void registerItemBlock(final RegistryEvent.Register<Item> event) {
			final IForgeRegistry<Item> registry = event.getRegistry();

			registry.register(new BaseMetaIB(BOG_LOGS, EnumBogLogs.getNames()).setRegistryName(BOG_LOGS.getRegistryName()));
			registry.register(new BaseMetaIB(FOSSIL_LOGS, EnumFossilLogs.getNames()).setRegistryName(FOSSIL_LOGS.getRegistryName()));
			registry.register(new BaseMetaIB(PETRIFIED_LOGS, EnumPetrifiedLogs.getNames()).setRegistryName(PETRIFIED_LOGS.getRegistryName()));
			registry.register(new BaseMetaIB(COLD_LOGS, EnumColdLogs.getNames()).setRegistryName(COLD_LOGS.getRegistryName()));
			registry.register(new BaseMetaIB(FOSSIL_PLANKS, EnumFossilPlanks.getNames()).setRegistryName(FOSSIL_PLANKS.getRegistryName()));
			registry.register(new FossilSlabIB(FOSSIL_SLABS_LO, EnumFossilPlanks.getNames()).setRegistryName(FOSSIL_SLABS_LO.getRegistryName()));
			registry.register(new FossilSlabIB(FOSSIL_SLABS_HI, EnumFossilPlanks.getNames()).setRegistryName(FOSSIL_SLABS_HI.getRegistryName()));

			registry.register(new ItemBlock(WHITE_SAND).setRegistryName(WHITE_SAND.getRegistryName()));
			if(ModConfig.allowGypsumDeco){
				registry.register(new BaseMetaIB(GYPSUM, EnumGypsumBlocks.getNames()).setRegistryName(GYPSUM.getRegistryName()));
				registry.register(new GypsumSlabIB(GYPSUM_SLABS_LO, EnumGypsumBlocks.getNames()).setRegistryName(GYPSUM_SLABS_LO.getRegistryName()));
				registry.register(new GypsumSlabIB(GYPSUM_SLABS_HI, EnumGypsumBlocks.getNames()).setRegistryName(GYPSUM_SLABS_HI.getRegistryName()));
				registry.register(new ItemBlock(GYPSUM_PILLAR).setRegistryName(GYPSUM_PILLAR.getRegistryName()));
				registry.register(new ItemBlock(GYPSUM_COLUMN).setRegistryName(GYPSUM_COLUMN.getRegistryName()));
			}
			if(ModConfig.ENABLE_SANDS){
				registry.register(new ItemBlock(RICEGRASS_CROP).setRegistryName(RICEGRASS_CROP.getRegistryName()));
				registry.register(new BushIB(GYPSUM_BUSH_LO, EnumBushes.getNames()).setRegistryName(GYPSUM_BUSH_LO.getRegistryName()));
				registry.register(new BushIB(GYPSUM_BUSH_HI, EnumBushes.getNames()).setRegistryName(GYPSUM_BUSH_HI.getRegistryName()));
				registry.register(new ShrubIB(GYPSUM_CROPS, EnumShrubs.getNames()).setRegistryName(GYPSUM_CROPS.getRegistryName()));
			}

			if(ModConfig.ENABLE_TRUFFLES){
				registry.register(new ItemBlock(TRUFFLE_AUCTION).setRegistryName(TRUFFLE_AUCTION.getRegistryName()));
			}
			registry.register(new ItemBlock(COMPOST_BIN).setRegistryName(COMPOST_BIN.getRegistryName()));
			registry.register(new PoweredIB(WOOD_INCUBATOR).setRegistryName(WOOD_INCUBATOR.getRegistryName()));
			registry.register(new PoweredIB(VIVARIUM).setRegistryName(VIVARIUM.getRegistryName()));

			if(ModConfig.allowFences){
				registry.register(new ItemBlock(OAK_FENCES).setRegistryName(OAK_FENCES.getRegistryName()));
				registry.register(new ItemBlock(SPRUCE_FENCES).setRegistryName(SPRUCE_FENCES.getRegistryName()));
				registry.register(new ItemBlock(KAURI_FENCES).setRegistryName(KAURI_FENCES.getRegistryName()));
				registry.register(new ItemBlock(MOPHANE_FENCES).setRegistryName(MOPHANE_FENCES.getRegistryName()));
				registry.register(new ItemBlock(PALM_FENCES).setRegistryName(PALM_FENCES.getRegistryName()));
				registry.register(new ItemBlock(PINE_FENCES).setRegistryName(PINE_FENCES.getRegistryName()));
				registry.register(new ItemBlock(DRIFTWOOD_FENCES).setRegistryName(DRIFTWOOD_FENCES.getRegistryName()));
				registry.register(new ItemBlock(ARAUCARIA_FENCES).setRegistryName(ARAUCARIA_FENCES.getRegistryName()));
				registry.register(new ItemBlock(BETULA_FENCES).setRegistryName(BETULA_FENCES.getRegistryName()));
				registry.register(new ItemBlock(CASTANO_FENCES).setRegistryName(CASTANO_FENCES.getRegistryName()));
				registry.register(new ItemBlock(OPALIZED_FENCES).setRegistryName(OPALIZED_FENCES.getRegistryName()));
				registry.register(new ItemBlock(RAINBOW_FENCES).setRegistryName(RAINBOW_FENCES.getRegistryName()));
				registry.register(new ItemBlock(AZURITE_FENCES).setRegistryName(AZURITE_FENCES.getRegistryName()));
				registry.register(new ItemBlock(BEECH_FENCES).setRegistryName(BEECH_FENCES.getRegistryName()));
				registry.register(new ItemBlock(TEREDO_FENCES).setRegistryName(TEREDO_FENCES.getRegistryName()));
				registry.register(new ItemBlock(REDWOOD_FENCES).setRegistryName(REDWOOD_FENCES.getRegistryName()));
			}

			if(ModConfig.allowGates){
				registry.register(new ItemBlock(OAK_GATE).setRegistryName(OAK_GATE.getRegistryName()));
				registry.register(new ItemBlock(SPRUCE_GATE).setRegistryName(SPRUCE_GATE.getRegistryName()));
				registry.register(new ItemBlock(KAURI_GATE).setRegistryName(KAURI_GATE.getRegistryName()));
				registry.register(new ItemBlock(MOPHANE_GATE).setRegistryName(MOPHANE_GATE.getRegistryName()));
				registry.register(new ItemBlock(PALM_GATE).setRegistryName(PALM_GATE.getRegistryName()));
				registry.register(new ItemBlock(PINE_GATE).setRegistryName(PINE_GATE.getRegistryName()));
				registry.register(new ItemBlock(DRIFTWOOD_GATE).setRegistryName(DRIFTWOOD_GATE.getRegistryName()));
				registry.register(new ItemBlock(ARAUCARIA_GATE).setRegistryName(ARAUCARIA_GATE.getRegistryName()));
				registry.register(new ItemBlock(BETULA_GATE).setRegistryName(BETULA_GATE.getRegistryName()));
				registry.register(new ItemBlock(CASTANO_GATE).setRegistryName(CASTANO_GATE.getRegistryName()));
				registry.register(new ItemBlock(OPALIZED_GATE).setRegistryName(OPALIZED_GATE.getRegistryName()));
				registry.register(new ItemBlock(RAINBOW_GATE).setRegistryName(RAINBOW_GATE.getRegistryName()));
				registry.register(new ItemBlock(AZURITE_GATE).setRegistryName(AZURITE_GATE.getRegistryName()));
				registry.register(new ItemBlock(BEECH_GATE).setRegistryName(BEECH_GATE.getRegistryName()));
				registry.register(new ItemBlock(TEREDO_GATE).setRegistryName(TEREDO_GATE.getRegistryName()));
				registry.register(new ItemBlock(REDWOOD_GATE).setRegistryName(REDWOOD_GATE.getRegistryName()));
			}

			if(ModConfig.allowStairs){
				registry.register(new ItemBlock(OAK_STAIRS).setRegistryName(OAK_STAIRS.getRegistryName()));
				registry.register(new ItemBlock(SPRUCE_STAIRS).setRegistryName(SPRUCE_STAIRS.getRegistryName()));
				registry.register(new ItemBlock(KAURI_STAIRS).setRegistryName(KAURI_STAIRS.getRegistryName()));
				registry.register(new ItemBlock(MOPHANE_STAIRS).setRegistryName(MOPHANE_STAIRS.getRegistryName()));
				registry.register(new ItemBlock(PALM_STAIRS).setRegistryName(PALM_STAIRS.getRegistryName()));
				registry.register(new ItemBlock(PINE_STAIRS).setRegistryName(PINE_STAIRS.getRegistryName()));
				registry.register(new ItemBlock(DRIFTWOOD_STAIRS).setRegistryName(DRIFTWOOD_STAIRS.getRegistryName()));
				registry.register(new ItemBlock(ARAUCARIA_STAIRS).setRegistryName(ARAUCARIA_STAIRS.getRegistryName()));
				registry.register(new ItemBlock(BETULA_STAIRS).setRegistryName(BETULA_STAIRS.getRegistryName()));
				registry.register(new ItemBlock(CASTANO_STAIRS).setRegistryName(CASTANO_STAIRS.getRegistryName()));
				registry.register(new ItemBlock(OPALIZED_STAIRS).setRegistryName(OPALIZED_STAIRS.getRegistryName()));
				registry.register(new ItemBlock(RAINBOW_STAIRS).setRegistryName(RAINBOW_STAIRS.getRegistryName()));
				registry.register(new ItemBlock(AZURITE_STAIRS).setRegistryName(AZURITE_STAIRS.getRegistryName()));
				registry.register(new ItemBlock(BEECH_STAIRS).setRegistryName(BEECH_STAIRS.getRegistryName()));
				registry.register(new ItemBlock(TEREDO_STAIRS).setRegistryName(TEREDO_STAIRS.getRegistryName()));
				registry.register(new ItemBlock(REDWOOD_STAIRS).setRegistryName(REDWOOD_STAIRS.getRegistryName()));

				if(ModConfig.allowGypsumDeco){
					registry.register(new ItemBlock(GYPSUM_PLAIN_STAIRS).setRegistryName(GYPSUM_PLAIN_STAIRS.getRegistryName()));
					registry.register(new ItemBlock(GYPSUM_BLOCK_STAIRS).setRegistryName(GYPSUM_BLOCK_STAIRS.getRegistryName()));
					registry.register(new ItemBlock(GYPSUM_BRICKS_STAIRS).setRegistryName(GYPSUM_BRICKS_STAIRS.getRegistryName()));
					registry.register(new ItemBlock(GYPSUM_TILE_STAIRS).setRegistryName(GYPSUM_TILE_STAIRS.getRegistryName()));
					registry.register(new ItemBlock(GYPSUM_COBBLE_STAIRS).setRegistryName(GYPSUM_COBBLE_STAIRS.getRegistryName()));
				}
			}

			if(ModConfig.allowChisel){
				registry.register(new BaseMetaIB(OAK_CHISELED, EnumChiseled.getNames()).setRegistryName(OAK_CHISELED.getRegistryName()));
				registry.register(new BaseMetaIB(SPRUCE_CHISELED, EnumChiseled.getNames()).setRegistryName(SPRUCE_CHISELED.getRegistryName()));
				registry.register(new BaseMetaIB(KAURI_CHISELED, EnumChiseled.getNames()).setRegistryName(KAURI_CHISELED.getRegistryName()));
				registry.register(new BaseMetaIB(MOPHANE_CHISELED, EnumChiseled.getNames()).setRegistryName(MOPHANE_CHISELED.getRegistryName()));
				registry.register(new BaseMetaIB(PALM_CHISELED, EnumChiseled.getNames()).setRegistryName(PALM_CHISELED.getRegistryName()));
				registry.register(new BaseMetaIB(PINE_CHISELED, EnumChiseled.getNames()).setRegistryName(PINE_CHISELED.getRegistryName()));
				registry.register(new BaseMetaIB(DRIFTWOOD_CHISELED, EnumChiseled.getNames()).setRegistryName(DRIFTWOOD_CHISELED.getRegistryName()));
				registry.register(new BaseMetaIB(ARAUCARIA_CHISELED, EnumChiseled.getNames()).setRegistryName(ARAUCARIA_CHISELED.getRegistryName()));
				registry.register(new BaseMetaIB(BETULA_CHISELED, EnumChiseled.getNames()).setRegistryName(BETULA_CHISELED.getRegistryName()));
				registry.register(new BaseMetaIB(CASTANO_CHISELED, EnumChiseled.getNames()).setRegistryName(CASTANO_CHISELED.getRegistryName()));
				registry.register(new BaseMetaIB(AZURITE_CHISELED, EnumChiseled.getNames()).setRegistryName(AZURITE_CHISELED.getRegistryName()));
				registry.register(new BaseMetaIB(BEECH_CHISELED, EnumChiseled.getNames()).setRegistryName(BEECH_CHISELED.getRegistryName()));
				registry.register(new BaseMetaIB(TEREDO_CHISELED, EnumChiseled.getNames()).setRegistryName(TEREDO_CHISELED.getRegistryName()));
				registry.register(new BaseMetaIB(REDWOOD_CHISELED, EnumChiseled.getNames()).setRegistryName(REDWOOD_CHISELED.getRegistryName()));
			}

		}

		// register the item model
		/**
		 * @param event  
		 */
		@SubscribeEvent
		public static void registerModels(ModelRegistryEvent event){

			RegistryHandler.registerMetaModel(BOG_LOGS, EnumBogLogs.getNames());
			RegistryHandler.registerMetaModel(FOSSIL_LOGS, EnumFossilLogs.getNames());
			RegistryHandler.registerMetaModel(PETRIFIED_LOGS, EnumPetrifiedLogs.getNames());
			RegistryHandler.registerMetaModel(COLD_LOGS, EnumColdLogs.getNames());
			RegistryHandler.registerMetaModel(FOSSIL_PLANKS, EnumFossilPlanks.getNames());
			RegistryHandler.registerMetaModel(FOSSIL_SLABS_LO, EnumFossilPlanks.getNames());
			RegistryHandler.registerMetaModel(FOSSIL_SLABS_HI, EnumFossilPlanks.getNames());

			RegistryHandler.registerSingleModel(WHITE_SAND);
			if(ModConfig.allowGypsumDeco){
				RegistryHandler.registerMetaModel(GYPSUM, EnumGypsumBlocks.getNames());
				RegistryHandler.registerMetaModel(GYPSUM_SLABS_LO, EnumGypsumBlocks.getNames());
				RegistryHandler.registerMetaModel(GYPSUM_SLABS_HI, EnumGypsumBlocks.getNames());
				RegistryHandler.registerSingleModel(GYPSUM_PILLAR);
				RegistryHandler.registerSingleModel(GYPSUM_COLUMN);
			}
			if(ModConfig.ENABLE_SANDS){
				RegistryHandler.registerSingleModel(RICEGRASS_CROP);
				RegistryHandler.registerMetaModel(GYPSUM_BUSH_LO, EnumBushes.getNames());
				RegistryHandler.registerMetaModel(GYPSUM_BUSH_HI, EnumBushes.getNames());
				RegistryHandler.registerMetaModel(GYPSUM_CROPS, EnumShrubs.getNames());
			}

			if(ModConfig.ENABLE_TRUFFLES){
				RegistryHandler.registerSingleModel(TRUFFLE_AUCTION);
			}
			RegistryHandler.registerSingleModel(COMPOST_BIN);
			RegistryHandler.registerSingleModel(WOOD_INCUBATOR);
			RegistryHandler.registerSingleModel(VIVARIUM);

			if(ModConfig.allowFences){
				RegistryHandler.registerSingleModel(OAK_FENCES);
				RegistryHandler.registerSingleModel(SPRUCE_FENCES);
				RegistryHandler.registerSingleModel(KAURI_FENCES);
				RegistryHandler.registerSingleModel(MOPHANE_FENCES);
				RegistryHandler.registerSingleModel(PALM_FENCES);
				RegistryHandler.registerSingleModel(PINE_FENCES);
				RegistryHandler.registerSingleModel(DRIFTWOOD_FENCES);
				RegistryHandler.registerSingleModel(ARAUCARIA_FENCES);
				RegistryHandler.registerSingleModel(BETULA_FENCES);
				RegistryHandler.registerSingleModel(CASTANO_FENCES);
				RegistryHandler.registerSingleModel(OPALIZED_FENCES);
				RegistryHandler.registerSingleModel(RAINBOW_FENCES);
				RegistryHandler.registerSingleModel(AZURITE_FENCES);
				RegistryHandler.registerSingleModel(BEECH_FENCES);
				RegistryHandler.registerSingleModel(TEREDO_FENCES);
				RegistryHandler.registerSingleModel(REDWOOD_FENCES);
			}

			if(ModConfig.allowGates){
				RegistryHandler.registerSingleModel(OAK_GATE);
				RegistryHandler.registerSingleModel(SPRUCE_GATE);
				RegistryHandler.registerSingleModel(KAURI_GATE);
				RegistryHandler.registerSingleModel(MOPHANE_GATE);
				RegistryHandler.registerSingleModel(PALM_GATE);
				RegistryHandler.registerSingleModel(PINE_GATE);
				RegistryHandler.registerSingleModel(DRIFTWOOD_GATE);
				RegistryHandler.registerSingleModel(ARAUCARIA_GATE);
				RegistryHandler.registerSingleModel(BETULA_GATE);
				RegistryHandler.registerSingleModel(CASTANO_GATE);
				RegistryHandler.registerSingleModel(OPALIZED_GATE);
				RegistryHandler.registerSingleModel(RAINBOW_GATE);
				RegistryHandler.registerSingleModel(AZURITE_GATE);
				RegistryHandler.registerSingleModel(BEECH_GATE);
				RegistryHandler.registerSingleModel(TEREDO_GATE);
				RegistryHandler.registerSingleModel(REDWOOD_GATE);
			}

			if(ModConfig.allowStairs){
				RegistryHandler.registerSingleModel(OAK_STAIRS);
				RegistryHandler.registerSingleModel(SPRUCE_STAIRS);
				RegistryHandler.registerSingleModel(KAURI_STAIRS);
				RegistryHandler.registerSingleModel(MOPHANE_STAIRS);
				RegistryHandler.registerSingleModel(PALM_STAIRS);
				RegistryHandler.registerSingleModel(PINE_STAIRS);
				RegistryHandler.registerSingleModel(DRIFTWOOD_STAIRS);
				RegistryHandler.registerSingleModel(ARAUCARIA_STAIRS);
				RegistryHandler.registerSingleModel(BETULA_STAIRS);
				RegistryHandler.registerSingleModel(CASTANO_STAIRS);
				RegistryHandler.registerSingleModel(OPALIZED_STAIRS);
				RegistryHandler.registerSingleModel(RAINBOW_STAIRS);
				RegistryHandler.registerSingleModel(AZURITE_STAIRS);
				RegistryHandler.registerSingleModel(BEECH_STAIRS);
				RegistryHandler.registerSingleModel(TEREDO_STAIRS);
				RegistryHandler.registerSingleModel(REDWOOD_STAIRS);

				if(ModConfig.allowGypsumDeco){
					RegistryHandler.registerSingleModel(GYPSUM_PLAIN_STAIRS);
					RegistryHandler.registerSingleModel(GYPSUM_BLOCK_STAIRS);
					RegistryHandler.registerSingleModel(GYPSUM_BRICKS_STAIRS);
					RegistryHandler.registerSingleModel(GYPSUM_TILE_STAIRS);
					RegistryHandler.registerSingleModel(GYPSUM_COBBLE_STAIRS);
				}
			}

			if(ModConfig.allowChisel){
				RegistryHandler.registerMetaModel(OAK_CHISELED, EnumChiseled.getNames());
				RegistryHandler.registerMetaModel(SPRUCE_CHISELED, EnumChiseled.getNames());
				RegistryHandler.registerMetaModel(KAURI_CHISELED, EnumChiseled.getNames());
				RegistryHandler.registerMetaModel(MOPHANE_CHISELED, EnumChiseled.getNames());
				RegistryHandler.registerMetaModel(PALM_CHISELED, EnumChiseled.getNames());
				RegistryHandler.registerMetaModel(PINE_CHISELED, EnumChiseled.getNames());
				RegistryHandler.registerMetaModel(DRIFTWOOD_CHISELED, EnumChiseled.getNames());
				RegistryHandler.registerMetaModel(ARAUCARIA_CHISELED, EnumChiseled.getNames());
				RegistryHandler.registerMetaModel(BETULA_CHISELED, EnumChiseled.getNames());
				RegistryHandler.registerMetaModel(CASTANO_CHISELED, EnumChiseled.getNames());
				RegistryHandler.registerMetaModel(AZURITE_CHISELED, EnumChiseled.getNames());
				RegistryHandler.registerMetaModel(BEECH_CHISELED, EnumChiseled.getNames());
				RegistryHandler.registerMetaModel(TEREDO_CHISELED, EnumChiseled.getNames());
				RegistryHandler.registerMetaModel(REDWOOD_CHISELED, EnumChiseled.getNames());
			}

		}
	}

}