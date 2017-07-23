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
					chisel.addVariation(EnumFossilPlanks.getName(x),  ModBlocks.fossilPlanks.getStateFromMeta(x), 0);
				}
				for(int z = 0; z < EnumChiseled.size(); z++){
					chisel.addVariation(EnumFossilPlanks.getName(0),  ModBlocks.oakChiseled.getStateFromMeta(z), z);
					chisel.addVariation(EnumFossilPlanks.getName(1),  ModBlocks.spruceChiseled.getStateFromMeta(z), z);
					chisel.addVariation(EnumFossilPlanks.getName(2),  ModBlocks.kauriChiseled.getStateFromMeta(z), z);
					chisel.addVariation(EnumFossilPlanks.getName(3),  ModBlocks.mophaneChiseled.getStateFromMeta(z), z);
					chisel.addVariation(EnumFossilPlanks.getName(4),  ModBlocks.palmChiseled.getStateFromMeta(z), z);
					chisel.addVariation(EnumFossilPlanks.getName(5),  ModBlocks.pineChiseled.getStateFromMeta(z), z);
					chisel.addVariation(EnumFossilPlanks.getName(6),  ModBlocks.driftwoodChiseled.getStateFromMeta(z), z);
					chisel.addVariation(EnumFossilPlanks.getName(7),  ModBlocks.araucariaChiseled.getStateFromMeta(z), z);
					chisel.addVariation(EnumFossilPlanks.getName(8),  ModBlocks.betulaChiseled.getStateFromMeta(z), z);
					chisel.addVariation(EnumFossilPlanks.getName(9),  ModBlocks.castanoChiseled.getStateFromMeta(z), z);
					chisel.addVariation(EnumFossilPlanks.getName(12), ModBlocks.azuriteChiseled.getStateFromMeta(z), z);
					chisel.addVariation(EnumFossilPlanks.getName(13), ModBlocks.teredoChiseled.getStateFromMeta(z), z);
					chisel.addVariation(EnumFossilPlanks.getName(14), ModBlocks.beechChiseled.getStateFromMeta(z), z);
					chisel.addVariation(EnumFossilPlanks.getName(15), ModBlocks.redwoodChiseled.getStateFromMeta(z), z);
				}
			//gypsum
				chisel.addVariation("gypsumBlocks", ModBlocks.gypsumColumn.getStateFromMeta(0), 0);
				chisel.addVariation("gypsumBlocks", ModBlocks.gypsumPillar.getStateFromMeta(0), 0);
				for(int x = 0; x < EnumGypsumBlocks.size(); x++){
					chisel.addVariation("gypsumBlocks", ModBlocks.gypsum.getStateFromMeta(x), x);
				}
			}
		}
	}
}