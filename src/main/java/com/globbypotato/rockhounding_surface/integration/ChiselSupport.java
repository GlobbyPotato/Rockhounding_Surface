package com.globbypotato.rockhounding_surface.integration;

import com.globbypotato.rockhounding_surface.ModBlocks;
import com.globbypotato.rockhounding_surface.enums.EnumChiseled;
import com.globbypotato.rockhounding_surface.enums.EnumFossilPlanks;
import com.globbypotato.rockhounding_surface.enums.EnumGypsumBlocks;

import team.chisel.api.carving.CarvingUtils;
import team.chisel.api.carving.ICarvingRegistry;

public class ChiselSupport  {

	public static void loadChisel() {
		if(SupportUtils.chiselEnabled()){
			ICarvingRegistry chisel = CarvingUtils.getChiselRegistry();
			if(chisel != null) {
			//fossil woods
				for(int x = 0; x < EnumFossilPlanks.size(); x++){
					chisel.addVariation(EnumFossilPlanks.getName(x),  CarvingUtils.variationFor(ModBlocks.FOSSIL_PLANKS.getStateFromMeta(x), 0));
				}
				for(int z = 0; z < EnumChiseled.size(); z++){
					chisel.addVariation(EnumFossilPlanks.getName(0),  CarvingUtils.variationFor(ModBlocks.OAK_CHISELED.getStateFromMeta(z), z));
					chisel.addVariation(EnumFossilPlanks.getName(1),  CarvingUtils.variationFor(ModBlocks.SPRUCE_CHISELED.getStateFromMeta(z), z));
					chisel.addVariation(EnumFossilPlanks.getName(2),  CarvingUtils.variationFor(ModBlocks.KAURI_CHISELED.getStateFromMeta(z), z));
					chisel.addVariation(EnumFossilPlanks.getName(3),  CarvingUtils.variationFor(ModBlocks.MOPHANE_CHISELED.getStateFromMeta(z), z));
					chisel.addVariation(EnumFossilPlanks.getName(4),  CarvingUtils.variationFor(ModBlocks.PALM_CHISELED.getStateFromMeta(z), z));
					chisel.addVariation(EnumFossilPlanks.getName(5),  CarvingUtils.variationFor(ModBlocks.PINE_CHISELED.getStateFromMeta(z), z));
					chisel.addVariation(EnumFossilPlanks.getName(6),  CarvingUtils.variationFor(ModBlocks.DRIFTWOOD_CHISELED.getStateFromMeta(z), z));
					chisel.addVariation(EnumFossilPlanks.getName(7),  CarvingUtils.variationFor(ModBlocks.ARAUCARIA_CHISELED.getStateFromMeta(z), z));
					chisel.addVariation(EnumFossilPlanks.getName(8),  CarvingUtils.variationFor(ModBlocks.BETULA_CHISELED.getStateFromMeta(z), z));
					chisel.addVariation(EnumFossilPlanks.getName(9),  CarvingUtils.variationFor(ModBlocks.CASTANO_CHISELED.getStateFromMeta(z), z));
					chisel.addVariation(EnumFossilPlanks.getName(12), CarvingUtils.variationFor(ModBlocks.AZURITE_CHISELED.getStateFromMeta(z), z));
					chisel.addVariation(EnumFossilPlanks.getName(13), CarvingUtils.variationFor(ModBlocks.TEREDO_CHISELED.getStateFromMeta(z), z));
					chisel.addVariation(EnumFossilPlanks.getName(14), CarvingUtils.variationFor(ModBlocks.BEECH_CHISELED.getStateFromMeta(z), z));
					chisel.addVariation(EnumFossilPlanks.getName(15), CarvingUtils.variationFor(ModBlocks.REDWOOD_CHISELED.getStateFromMeta(z), z));
				}
			//gypsum
				chisel.addVariation("gypsumBlocks", CarvingUtils.variationFor(ModBlocks.GYPSUM_COLUMN.getStateFromMeta(0), 0));
				chisel.addVariation("gypsumBlocks", CarvingUtils.variationFor(ModBlocks.GYPSUM_PILLAR.getStateFromMeta(0), 0));
				for(int x = 0; x < EnumGypsumBlocks.size(); x++){
					chisel.addVariation("gypsumBlocks", CarvingUtils.variationFor(ModBlocks.GYPSUM.getStateFromMeta(x), x));
				}
				for(int x = 0; x < EnumGypsumBlocks.size(); x++){
					if(x != 6){
						chisel.addVariation("gypsumSlabs", CarvingUtils.variationFor(ModBlocks.GYPSUM_SLABS_LO.getStateFromMeta(x), x));
					}
				}

			}
		}
	}
}