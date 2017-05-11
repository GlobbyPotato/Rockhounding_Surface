package com.globbypotato.rockhounding_surface;

import com.globbypotato.rockhounding_surface.blocks.BaseOriented;
import com.globbypotato.rockhounding_surface.blocks.BaseSand;
import com.globbypotato.rockhounding_surface.blocks.BaseWall;
import com.globbypotato.rockhounding_surface.blocks.plants.GypsumBushHi;
import com.globbypotato.rockhounding_surface.blocks.plants.GypsumBushLo;
import com.globbypotato.rockhounding_surface.blocks.plants.GypsumShrubs;
import com.globbypotato.rockhounding_surface.blocks.plants.RicegrassCrop;
import com.globbypotato.rockhounding_surface.blocks.rocks.Gypsum;
import com.globbypotato.rockhounding_surface.blocks.rocks.GypsumSlabs;
import com.globbypotato.rockhounding_surface.blocks.rocks.GypsumStairs;
import com.globbypotato.rockhounding_surface.blocks.woods.BogLogs;
import com.globbypotato.rockhounding_surface.blocks.woods.ColdLogs;
import com.globbypotato.rockhounding_surface.blocks.woods.FossilChisel;
import com.globbypotato.rockhounding_surface.blocks.woods.FossilFences;
import com.globbypotato.rockhounding_surface.blocks.woods.FossilGates;
import com.globbypotato.rockhounding_surface.blocks.woods.FossilLogs;
import com.globbypotato.rockhounding_surface.blocks.woods.FossilPlanks;
import com.globbypotato.rockhounding_surface.blocks.woods.FossilSlabs;
import com.globbypotato.rockhounding_surface.blocks.woods.FossilStairs;
import com.globbypotato.rockhounding_surface.blocks.woods.PetrifiedLogs;
import com.globbypotato.rockhounding_surface.enums.EnumBogLogs;
import com.globbypotato.rockhounding_surface.enums.EnumBushes;
import com.globbypotato.rockhounding_surface.enums.EnumChiseled;
import com.globbypotato.rockhounding_surface.enums.EnumColdLogs;
import com.globbypotato.rockhounding_surface.enums.EnumFossilLogs;
import com.globbypotato.rockhounding_surface.enums.EnumFossilPlanks;
import com.globbypotato.rockhounding_surface.enums.EnumGypsumBlocks;
import com.globbypotato.rockhounding_surface.enums.EnumPetrifiedLogs;
import com.globbypotato.rockhounding_surface.enums.EnumShrubs;
import com.globbypotato.rockhounding_surface.integration.SupportUtils;
import com.globbypotato.rockhounding_surface.machines.CompostBin;
import com.globbypotato.rockhounding_surface.machines.TruffleAuction;
import com.globbypotato.rockhounding_surface.machines.WoodIncubator;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class ModBlocks {
// Fossil woods
	public static Block bogLogs;
	public static Block fossilLogs;
	public static Block petrifiedLogs;
	public static Block coldLogs;
	public static Block fossilPlanks;
	public static Block oakChiseled;
	public static Block spruceChiseled;
	public static Block betulaChiseled;
	public static Block castanoChiseled;
	public static Block palmChiseled;
	public static Block araucariaChiseled;
	public static Block kauriChiseled;
	public static Block pineChiseled;
	public static Block mophaneChiseled;
	public static Block driftwoodChiseled;
	public static Block azuriteChiseled;
	public static Block beechChiseled;
	public static Block teredoChiseled;
	public static Block redwoodChiseled;
	public static Block fossilSlabsLo;
	public static Block fossilSlabsHi;
	public static Block oakFences;
	public static Block spruceFences;
	public static Block kauriFences;
	public static Block mophaneFences;
	public static Block palmFences;
	public static Block pineFences;
	public static Block driftwoodFences;
	public static Block araucariaFences;
	public static Block betulaFences;
	public static Block castanoFences;
	public static Block opalizedFences;
	public static Block rainbowFences;
	public static Block azuriteFences;
	public static Block beechFences;
	public static Block teredoFences;
	public static Block redwoodFences;
	public static Block oakGate;
	public static Block spruceGate;
	public static Block kauriGate;
	public static Block mophaneGate;
	public static Block palmGate;
	public static Block pineGate;
	public static Block driftwoodGate;
	public static Block araucariaGate;
	public static Block betulaGate;
	public static Block castanoGate;
	public static Block opalizedGate;
	public static Block rainbowGate;
	public static Block azuriteGate;
	public static Block beechGate;
	public static Block teredoGate;
	public static Block redwoodGate;
	public static Block oakStairs;
	public static Block spruceStairs;
	public static Block kauriStairs;
	public static Block mophaneStairs;
	public static Block palmStairs;
	public static Block pineStairs;
	public static Block driftwoodStairs;
	public static Block araucariaStairs;
	public static Block betulaStairs;
	public static Block castanoStairs;
	public static Block opalizedStairs;
	public static Block rainbowStairs;
	public static Block azuriteStairs;
	public static Block beechStairs;
	public static Block teredoStairs;
	public static Block redwoodStairs;
// Wood Aging Tech
	public static Block woodIncubator;
// Truffles
	public static Block truffleAuction;
// Sands
	public static Block whiteSand;
	public static Block gypsum;
	public static Block gypsumSlabsHi;
	public static Block gypsumSlabsLo;
	public static Block gypsumPlainStairs;
	public static Block gypsumBlockStairs;
	public static Block gypsumBrickStairs;
	public static Block gypsumTileStairs;
	public static Block gypsumCobbleStairs;
	public static Block gypsumWall;
	public static Block gypsumPillar;
	public static Block gypsumColumn;
	public static Block gypsumBushLo;
	public static Block gypsumBushHi;
	public static Block gypsumCrops;
	public static Block ricegrassCrop;
//generic
	public static Block compostBin;

	public static void init() {
// Fossil woods
		bogLogs = new BogLogs("bogLogs", EnumBogLogs.getNames());
		fossilLogs = new FossilLogs("fossilLogs", EnumFossilLogs.getNames());
		petrifiedLogs = new PetrifiedLogs("petrifiedLogs", EnumPetrifiedLogs.getNames());
		fossilPlanks = new FossilPlanks("fossilPlanks", Material.WOOD, SoundType.WOOD, EnumFossilPlanks.getNames(), 3.0F, 5.0F);
		coldLogs = new ColdLogs("coldLogs", EnumColdLogs.getNames());
		if(SupportUtils.chiselLoaded()){
			oakChiseled = new FossilChisel("oakChiseled", Material.WOOD, SoundType.WOOD, EnumChiseled.getNames(), 3.0F, 5.0F);
			spruceChiseled = new FossilChisel("spruceChiseled", Material.WOOD, SoundType.WOOD, EnumChiseled.getNames(), 3.0F, 5.0F);
			betulaChiseled = new FossilChisel("betulaChiseled", Material.WOOD, SoundType.WOOD, EnumChiseled.getNames(), 3.0F, 5.0F);
			castanoChiseled = new FossilChisel("castanoChiseled", Material.WOOD, SoundType.WOOD, EnumChiseled.getNames(), 3.0F, 5.0F);
			palmChiseled = new FossilChisel("palmChiseled", Material.WOOD, SoundType.WOOD, EnumChiseled.getNames(), 3.0F, 5.0F);
			araucariaChiseled = new FossilChisel("araucariaChiseled", Material.WOOD, SoundType.WOOD, EnumChiseled.getNames(), 3.0F, 5.0F);
			kauriChiseled = new FossilChisel("kauriChiseled", Material.WOOD, SoundType.WOOD, EnumChiseled.getNames(), 3.0F, 5.0F);
			pineChiseled = new FossilChisel("pineChiseled", Material.WOOD, SoundType.WOOD, EnumChiseled.getNames(), 3.0F, 5.0F);
			mophaneChiseled = new FossilChisel("mophaneChiseled", Material.WOOD, SoundType.WOOD, EnumChiseled.getNames(), 3.0F, 5.0F);
			driftwoodChiseled = new FossilChisel("driftwoodChiseled", Material.WOOD, SoundType.WOOD, EnumChiseled.getNames(), 3.0F, 5.0F);
			azuriteChiseled = new FossilChisel("azuriteChiseled", Material.WOOD, SoundType.WOOD, EnumChiseled.getNames(), 3.0F, 5.0F);
			beechChiseled = new FossilChisel("beechChiseled", Material.WOOD, SoundType.WOOD, EnumChiseled.getNames(), 3.0F, 5.0F);
			teredoChiseled = new FossilChisel("teredoChiseled", Material.WOOD, SoundType.WOOD, EnumChiseled.getNames(), 3.0F, 5.0F);
			redwoodChiseled = new FossilChisel("redwoodChiseled", Material.WOOD, SoundType.WOOD, EnumChiseled.getNames(), 3.0F, 5.0F);
		}
		fossilSlabsLo = new FossilSlabs("fossilSlabsLo", Material.WOOD, SoundType.WOOD, EnumFossilPlanks.getNames(), 3.0F, 5.0F, true);
		fossilSlabsHi = new FossilSlabs("fossilSlabsHi", Material.WOOD, SoundType.WOOD, EnumFossilPlanks.getNames(), 3.0F, 5.0F, false);
		oakFences = new FossilFences("oakFences", Material.WOOD, EnumFossilPlanks.OAK.getMapColor(), SoundType.WOOD, 2.0F, 5.0F);
		spruceFences = new FossilFences("spruceFences", Material.WOOD, EnumFossilPlanks.SPRUCE.getMapColor(), SoundType.WOOD, 2.0F, 5.0F);
		kauriFences = new FossilFences("kauriFences", Material.WOOD, EnumFossilPlanks.KAURI.getMapColor(), SoundType.WOOD, 2.0F, 5.0F);
		mophaneFences = new FossilFences("mophaneFences", Material.WOOD, EnumFossilPlanks.MOPHANE.getMapColor(), SoundType.WOOD, 2.0F, 5.0F);
		palmFences = new FossilFences("palmFences", Material.WOOD, EnumFossilPlanks.PALM.getMapColor(), SoundType.WOOD, 2.0F, 5.0F);
		pineFences = new FossilFences("pineFences", Material.WOOD, EnumFossilPlanks.PINE.getMapColor(), SoundType.WOOD, 2.0F, 5.0F);
		driftwoodFences = new FossilFences("driftwoodFences", Material.WOOD, EnumFossilPlanks.DRIFTWOOD.getMapColor(), SoundType.WOOD, 2.0F, 5.0F);
		araucariaFences = new FossilFences("araucariaFences", Material.WOOD, EnumFossilPlanks.ARAUCARIA.getMapColor(), SoundType.WOOD, 2.0F, 5.0F);
		betulaFences = new FossilFences("betulaFences", Material.WOOD, EnumFossilPlanks.BETULA.getMapColor(), SoundType.WOOD, 2.0F, 5.0F);
		castanoFences = new FossilFences("castanoFences", Material.WOOD, EnumFossilPlanks.CASTANO.getMapColor(), SoundType.WOOD, 2.0F, 5.0F);
		opalizedFences = new FossilFences("opalizedFences", Material.WOOD, EnumFossilPlanks.OPALIZED.getMapColor(), SoundType.WOOD, 2.0F, 5.0F);
		rainbowFences = new FossilFences("rainbowFences", Material.WOOD, EnumFossilPlanks.RAINBOW.getMapColor(), SoundType.WOOD, 2.0F, 5.0F);
		azuriteFences = new FossilFences("azuriteFences", Material.WOOD, EnumFossilPlanks.AZURITE.getMapColor(), SoundType.WOOD, 2.0F, 5.0F);
		beechFences = new FossilFences("beechFences", Material.WOOD, EnumFossilPlanks.BEECH.getMapColor(), SoundType.WOOD, 2.0F, 5.0F);
		teredoFences = new FossilFences("teredoFences", Material.WOOD, EnumFossilPlanks.TEREDO.getMapColor(), SoundType.WOOD, 2.0F, 5.0F);
		redwoodFences = new FossilFences("redwoodFences", Material.WOOD, EnumFossilPlanks.REDWOOD.getMapColor(), SoundType.WOOD, 2.0F, 5.0F);
		oakGate = new FossilGates("oakGate", Material.WOOD, EnumType.OAK , SoundType.WOOD, 2.0F, 5.0F);
		spruceGate = new FossilGates("spruceGate", Material.WOOD, EnumType.OAK, SoundType.WOOD, 2.0F, 5.0F);
		kauriGate = new FossilGates("kauriGate", Material.WOOD, EnumType.OAK, SoundType.WOOD, 2.0F, 5.0F);
		mophaneGate = new FossilGates("mophaneGate", Material.WOOD, EnumType.OAK, SoundType.WOOD, 2.0F, 5.0F);
		palmGate = new FossilGates("palmGate", Material.WOOD, EnumType.OAK, SoundType.WOOD, 2.0F, 5.0F);
		pineGate = new FossilGates("pineGate", Material.WOOD, EnumType.OAK, SoundType.WOOD, 2.0F, 5.0F);
		driftwoodGate = new FossilGates("driftwoodGate", Material.WOOD, EnumType.OAK, SoundType.WOOD, 2.0F, 5.0F);
		araucariaGate = new FossilGates("araucariaGate", Material.WOOD, EnumType.OAK, SoundType.WOOD, 2.0F, 5.0F);
		betulaGate = new FossilGates("betulaGate", Material.WOOD, EnumType.OAK, SoundType.WOOD, 2.0F, 5.0F);
		castanoGate = new FossilGates("castanoGate", Material.WOOD, EnumType.OAK, SoundType.WOOD, 2.0F, 5.0F);
		opalizedGate = new FossilGates("opalizedGate", Material.WOOD, EnumType.OAK, SoundType.WOOD, 2.0F, 5.0F);
		rainbowGate = new FossilGates("rainbowGate", Material.WOOD, EnumType.OAK, SoundType.WOOD, 2.0F, 5.0F);
		azuriteGate = new FossilGates("azuriteGate", Material.WOOD, EnumType.OAK , SoundType.WOOD, 2.0F, 5.0F);
		beechGate = new FossilGates("beechGate", Material.WOOD, EnumType.OAK , SoundType.WOOD, 2.0F, 5.0F);
		teredoGate = new FossilGates("teredoGate", Material.WOOD, EnumType.OAK , SoundType.WOOD, 2.0F, 5.0F);
		redwoodGate = new FossilGates("redwoodGate", Material.WOOD, EnumType.OAK , SoundType.WOOD, 2.0F, 5.0F);
		oakStairs = new FossilStairs("oakStairs", EnumFossilPlanks.OAK.ordinal(), SoundType.WOOD, 2.0F, 5.0F);
		spruceStairs = new FossilStairs("spruceStairs", EnumFossilPlanks.SPRUCE.ordinal(), SoundType.WOOD, 2.0F, 5.0F);
		kauriStairs = new FossilStairs("kauriStairs", EnumFossilPlanks.KAURI.ordinal(), SoundType.WOOD, 2.0F, 5.0F);
		mophaneStairs = new FossilStairs("mophaneStairs", EnumFossilPlanks.MOPHANE.ordinal(), SoundType.WOOD, 2.0F, 5.0F);
		palmStairs = new FossilStairs("palmStairs", EnumFossilPlanks.PALM.ordinal(), SoundType.WOOD, 2.0F, 5.0F);
		pineStairs = new FossilStairs("pineStairs", EnumFossilPlanks.PINE.ordinal(), SoundType.WOOD, 2.0F, 5.0F);
		driftwoodStairs = new FossilStairs("driftwoodStairs", EnumFossilPlanks.DRIFTWOOD.ordinal(), SoundType.WOOD, 2.0F, 5.0F);
		araucariaStairs = new FossilStairs("araucariaStairs", EnumFossilPlanks.ARAUCARIA.ordinal(), SoundType.WOOD, 2.0F, 5.0F);
		betulaStairs = new FossilStairs("betulaStairs", EnumFossilPlanks.BETULA.ordinal(), SoundType.WOOD, 2.0F, 5.0F);
		castanoStairs = new FossilStairs("castanoStairs", EnumFossilPlanks.CASTANO.ordinal(), SoundType.WOOD, 2.0F, 5.0F);
		opalizedStairs = new FossilStairs("opalizedStairs", EnumFossilPlanks.OPALIZED.ordinal(), SoundType.WOOD, 2.0F, 5.0F);
		rainbowStairs = new FossilStairs("rainbowStairs", EnumFossilPlanks.RAINBOW.ordinal(), SoundType.WOOD, 2.0F, 5.0F);
		azuriteStairs = new FossilStairs("azuriteStairs", EnumFossilPlanks.AZURITE.ordinal(), SoundType.WOOD, 2.0F, 5.0F);
		beechStairs = new FossilStairs("beechStairs", EnumFossilPlanks.BEECH.ordinal(), SoundType.WOOD, 2.0F, 5.0F);
		teredoStairs = new FossilStairs("teredoStairs", EnumFossilPlanks.TEREDO.ordinal(), SoundType.WOOD, 2.0F, 5.0F);
		redwoodStairs = new FossilStairs("redwoodStairs", EnumFossilPlanks.REDWOOD.ordinal(), SoundType.WOOD, 2.0F, 5.0F);
// Wood Aging Tech
		if(SupportUtils.rhChemistryLoaded()){
			woodIncubator = new WoodIncubator(3.0F, 5.0F, "woodIncubator");
		}
// Truffles
		truffleAuction = new TruffleAuction(3.0F, 5.0F, "truffleAuction");
// Sands
		whiteSand = new BaseSand("whiteSand", 1.0F, 1.0F);
		gypsum = new Gypsum("gypsum", Material.ROCK, SoundType.STONE, EnumGypsumBlocks.getNames(), 1.5F, 2.0F);
		gypsumPillar = new BaseOriented("gypsumPillar", Material.ROCK, SoundType.STONE, 1.5F, 2.0F);
		gypsumColumn = new BaseOriented("gypsumColumn", Material.ROCK, SoundType.STONE, 1.5F, 2.0F);
		gypsumSlabsLo = new GypsumSlabs("gypsumSlabsLo", Material.ROCK, SoundType.STONE, EnumGypsumBlocks.getNames(), 1.5F, 2.0F, true);
		gypsumSlabsHi = new GypsumSlabs("gypsumSlabsHi", Material.ROCK, SoundType.STONE, EnumGypsumBlocks.getNames(), 1.5F, 2.0F, false);
		gypsumPlainStairs = new GypsumStairs("gypsumPlainStairs", EnumGypsumBlocks.PLAIN.ordinal(), SoundType.STONE, 1.5F, 2.0F);
		gypsumBlockStairs = new GypsumStairs("gypsumBlockStairs", EnumGypsumBlocks.BLOCK.ordinal(), SoundType.STONE, 1.5F, 2.0F);
		gypsumBrickStairs = new GypsumStairs("gypsumBrickStairs", EnumGypsumBlocks.BRICK.ordinal(), SoundType.STONE, 1.5F, 2.0F);
		gypsumTileStairs = new GypsumStairs("gypsumTileStairs", EnumGypsumBlocks.TILE.ordinal(), SoundType.STONE, 1.5F, 2.0F);
		gypsumCobbleStairs = new GypsumStairs("gypsumCobbleStairs", EnumGypsumBlocks.COBBLE.ordinal(), SoundType.STONE, 1.5F, 2.0F);
		gypsumWall = new BaseWall("gypsumWall", SoundType.STONE, 1.5F, 2.0F);
		gypsumBushLo = new GypsumBushLo("gypsumBushLo");
		gypsumBushHi = new GypsumBushHi("gypsumBushHi");
		gypsumCrops = new GypsumShrubs("gypsumCrops", Material.GRASS, SoundType.PLANT, EnumShrubs.getNames(), 0.5F, 0.5F);
		ricegrassCrop = new RicegrassCrop("ricegrassCrop");
//generic
		compostBin = new CompostBin(3.0F, 5.0F, "compostBin");
	}

	public static void initClient(){
		for(int i = 0; i < EnumBogLogs.size(); i++){			registerMetaBlockRender(bogLogs, i, EnumBogLogs.getName(i));}
		for(int i = 0; i < EnumFossilLogs.size(); i++){			registerMetaBlockRender(fossilLogs, i, EnumFossilLogs.getName(i));}
		for(int i = 0; i < EnumPetrifiedLogs.size(); i++){		registerMetaBlockRender(petrifiedLogs, i, EnumPetrifiedLogs.getName(i));}
		for(int i = 0; i < EnumColdLogs.size(); i++){			registerMetaBlockRender(coldLogs, i, EnumColdLogs.getName(i));}
		for(int i = 0; i < EnumFossilPlanks.size(); i++){		registerMetaBlockRender(fossilPlanks, i, EnumFossilPlanks.getName(i));}
		if(SupportUtils.chiselLoaded()){
			for(int i = 0; i < EnumChiseled.size(); i++){		registerMetaBlockRender(oakChiseled, i, EnumChiseled.getName(i));}
			for(int i = 0; i < EnumChiseled.size(); i++){		registerMetaBlockRender(spruceChiseled, i, EnumChiseled.getName(i));}
			for(int i = 0; i < EnumChiseled.size(); i++){		registerMetaBlockRender(betulaChiseled, i, EnumChiseled.getName(i));}
			for(int i = 0; i < EnumChiseled.size(); i++){		registerMetaBlockRender(castanoChiseled, i, EnumChiseled.getName(i));}
			for(int i = 0; i < EnumChiseled.size(); i++){		registerMetaBlockRender(palmChiseled, i, EnumChiseled.getName(i));}
			for(int i = 0; i < EnumChiseled.size(); i++){		registerMetaBlockRender(araucariaChiseled, i, EnumChiseled.getName(i));}
			for(int i = 0; i < EnumChiseled.size(); i++){		registerMetaBlockRender(kauriChiseled, i, EnumChiseled.getName(i));}
			for(int i = 0; i < EnumChiseled.size(); i++){		registerMetaBlockRender(pineChiseled, i, EnumChiseled.getName(i));}
			for(int i = 0; i < EnumChiseled.size(); i++){		registerMetaBlockRender(mophaneChiseled, i, EnumChiseled.getName(i));}
			for(int i = 0; i < EnumChiseled.size(); i++){		registerMetaBlockRender(driftwoodChiseled, i, EnumChiseled.getName(i));}
			for(int i = 0; i < EnumChiseled.size(); i++){		registerMetaBlockRender(azuriteChiseled, i, EnumChiseled.getName(i));}
			for(int i = 0; i < EnumChiseled.size(); i++){		registerMetaBlockRender(beechChiseled, i, EnumChiseled.getName(i));}
			for(int i = 0; i < EnumChiseled.size(); i++){		registerMetaBlockRender(teredoChiseled, i, EnumChiseled.getName(i));}
			for(int i = 0; i < EnumChiseled.size(); i++){		registerMetaBlockRender(redwoodChiseled, i, EnumChiseled.getName(i));}
		}
		for(int i = 0; i < EnumFossilPlanks.size(); i++){		registerMetaBlockRender(fossilSlabsLo, i, EnumFossilPlanks.getName(i));}
		for(int i = 0; i < EnumFossilPlanks.size(); i++){		registerMetaBlockRender(fossilSlabsHi, i, EnumFossilPlanks.getName(i));}
		registerItemBlockRender(oakFences, 0, "oakFences");
		registerItemBlockRender(spruceFences, 0, "spruceFences");
		registerItemBlockRender(kauriFences, 0, "kauriFences");
		registerItemBlockRender(mophaneFences, 0, "mophaneFences");
		registerItemBlockRender(palmFences, 0, "palmFences");
		registerItemBlockRender(pineFences, 0, "pineFences");
		registerItemBlockRender(driftwoodFences, 0, "driftwoodFences");
		registerItemBlockRender(araucariaFences, 0, "araucariaFences");
		registerItemBlockRender(betulaFences, 0, "betulaFences");
		registerItemBlockRender(castanoFences, 0, "castanoFences");
		registerItemBlockRender(opalizedFences, 0, "opalizedFences");
		registerItemBlockRender(rainbowFences, 0, "rainbowFences");
		registerItemBlockRender(azuriteFences, 0, "azuriteFences");
		registerItemBlockRender(beechFences, 0, "beechFences");
		registerItemBlockRender(teredoFences, 0, "teredoFences");
		registerItemBlockRender(redwoodFences, 0, "redwoodFences");
		registerItemBlockRender(oakGate, 0, "oakGate");
		registerItemBlockRender(spruceGate, 0, "spruceGate");
		registerItemBlockRender(kauriGate, 0, "kauriGate");
		registerItemBlockRender(mophaneGate, 0, "mophaneGate");
		registerItemBlockRender(palmGate, 0, "palmGate");
		registerItemBlockRender(pineGate, 0, "pineGate");
		registerItemBlockRender(driftwoodGate, 0, "driftwoodGate");
		registerItemBlockRender(araucariaGate, 0, "araucariaGate");
		registerItemBlockRender(betulaGate, 0, "betulaGate");
		registerItemBlockRender(castanoGate, 0, "castanoGate");
		registerItemBlockRender(opalizedGate, 0, "opalizedGate");
		registerItemBlockRender(rainbowGate, 0, "rainbowGate");
		registerItemBlockRender(azuriteGate, 0, "azuriteGate");
		registerItemBlockRender(beechGate, 0, "beechGate");
		registerItemBlockRender(teredoGate, 0, "teredoGate");
		registerItemBlockRender(redwoodGate, 0, "redwoodGate");
		registerItemBlockRender(oakStairs, 0, "oakStairs");
		registerItemBlockRender(spruceStairs, 0, "spruceStairs");
		registerItemBlockRender(kauriStairs, 0, "kauriStairs");
		registerItemBlockRender(mophaneStairs, 0, "mophaneStairs");
		registerItemBlockRender(palmStairs, 0, "palmStairs");
		registerItemBlockRender(pineStairs, 0, "pineStairs");
		registerItemBlockRender(driftwoodStairs, 0, "driftwoodStairs");
		registerItemBlockRender(araucariaStairs, 0, "araucariaStairs");
		registerItemBlockRender(betulaStairs, 0, "betulaStairs");
		registerItemBlockRender(castanoStairs, 0, "castanoStairs");
		registerItemBlockRender(opalizedStairs, 0, "opalizedStairs");
		registerItemBlockRender(rainbowStairs, 0, "rainbowStairs");
		registerItemBlockRender(azuriteStairs, 0, "azuriteStairs");
		registerItemBlockRender(beechStairs, 0, "beechStairs");
		registerItemBlockRender(teredoStairs, 0, "teredoStairs");
		registerItemBlockRender(redwoodStairs, 0, "redwoodStairs");
	//Truffle
		registerItemBlockRender(truffleAuction, 0, "truffleAuction");
	//Wood aging
		if(SupportUtils.rhChemistryLoaded()){
			registerItemBlockRender(woodIncubator, 0, "woodIncubator");
		}
	//Sands
		registerItemBlockRender(whiteSand, 0, "whiteSand");
		for(int i = 0; i < EnumGypsumBlocks.size(); i++){		registerMetaBlockRender(gypsum, i, EnumGypsumBlocks.getName(i));}
		for(int i = 0; i < EnumGypsumBlocks.size(); i++){		registerMetaBlockRender(gypsumSlabsLo, i, EnumGypsumBlocks.getName(i));}
		for(int i = 0; i < EnumGypsumBlocks.size(); i++){		registerMetaBlockRender(gypsumSlabsHi, i, EnumGypsumBlocks.getName(i));}
		registerItemBlockRender(gypsumPillar, 0, "gypsumPillar");
		registerItemBlockRender(gypsumColumn, 0, "gypsumColumn");
		registerItemBlockRender(gypsumPlainStairs, 0, "gypsumPlainStairs");
		registerItemBlockRender(gypsumBlockStairs, 0, "gypsumBlockStairs");
		registerItemBlockRender(gypsumBrickStairs, 0, "gypsumBrickStairs");
		registerItemBlockRender(gypsumTileStairs, 0, "gypsumTileStairs");
		registerItemBlockRender(gypsumCobbleStairs, 0, "gypsumCobbleStairs");
		registerItemBlockRender(gypsumWall, 0, "gypsumWall");
		for(int i = 0; i < EnumBushes.size(); i++){				registerMetaBlockRender(gypsumBushLo, i, EnumBushes.getName(i));}
		for(int i = 0; i < EnumBushes.size(); i++){				registerMetaBlockRender(gypsumBushHi, i, EnumBushes.getName(i));}
		for(int i = 0; i < EnumShrubs.size(); i++){				registerMetaBlockRender(gypsumCrops, i, EnumShrubs.getName(i));}
		registerItemBlockRender(ricegrassCrop, 0, "ricegrassCrop");
	//generic
		registerItemBlockRender(compostBin, 0, "compostBin");
	}

	public static void registerMetaBlockRender(Block block, int meta, String fileName){
		Item item = Item.getItemFromBlock(block);
		ModelResourceLocation model = new ModelResourceLocation(block.getRegistryName() + "_" + fileName, "inventory");
		ModelLoader.setCustomModelResourceLocation(item, meta, model );
	}
	public static void registerItemBlockRender(Block block, int meta, String fileName){
		Item item = Item.getItemFromBlock(block);
		ModelResourceLocation model = new ModelResourceLocation(block.getRegistryName(), "inventory");
		ModelLoader.setCustomModelResourceLocation(item, meta, model );
	}

}