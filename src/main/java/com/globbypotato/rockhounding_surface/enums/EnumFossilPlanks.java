package com.globbypotato.rockhounding_surface.enums;

import net.minecraft.block.material.MapColor;

public enum EnumFossilPlanks implements BaseEnum {
	OAK			(MapColor.BLACK), 
	SPRUCE		(MapColor.GREEN),
	KAURI		(MapColor.SAND),
	MOPHANE		(MapColor.RED),
	PALM		(MapColor.YELLOW),
	PINE		(MapColor.LIME),
	DRIFTWOOD	(MapColor.GRAY),
	ARAUCARIA	(MapColor.FOLIAGE),
	BETULA		(MapColor.QUARTZ),
	CASTANO		(MapColor.NETHERRACK),
	OPALIZED	(MapColor.ADOBE),
	RAINBOW		(MapColor.BROWN),
	AZURITE		(MapColor.DIAMOND),
	TEREDO		(MapColor.BROWN),
	BEECH		(MapColor.LAPIS),
	REDWOOD		(MapColor.RED);

    private final MapColor mapColor;
	private EnumFossilPlanks(MapColor mapColor) {
		this.mapColor = mapColor; 
	}

	//---------CUSTOM----------------
	public static int size(){
		return values().length;
	}

	public static String name(int index) {
		return values()[index].getName();
	}

	//---------ENUM----------------
	public static String[] getNames(){
		String[] temp = new String[size()];
		for(int i = 0; i < size(); i++){
			temp[i] = getName(i);
		}
		return temp;
	}

	public static String getName(int index){
		return name(index);
	}

    public MapColor getMapColor(){ 
    	return this.mapColor; 
    }

}
