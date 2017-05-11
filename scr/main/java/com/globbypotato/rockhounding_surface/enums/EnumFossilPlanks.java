package com.globbypotato.rockhounding_surface.enums;

import net.minecraft.block.material.MapColor;
import net.minecraft.util.IStringSerializable;

public enum EnumFossilPlanks implements IStringSerializable {
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

	@Override
	public String getName() {
        return toString().toLowerCase();
	}

    public MapColor getMapColor(){ 
    	return this.mapColor; 
    }

	public static int size(){
		return values().length;
	}

	public static String[] getNames(){
		String[] temp = new String[size()];
		for(int i=0;i<size();i++){temp[i] = getName(i);}
		return temp;
	}

	public static String getName(int index){
		return index > -1 && index < size() ? EnumFossilPlanks.values()[index].getName() : EnumFossilPlanks.values()[0].getName();
	}

}