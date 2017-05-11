package com.globbypotato.rockhounding_surface.enums;

import net.minecraft.util.IStringSerializable;

public enum EnumBogLogs implements IStringSerializable {
	OAK, 
	SPRUCE,
	KAURI,
	MOPHANE;

	@Override
	public String getName() {
        return toString().toLowerCase();
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
		return index > -1 && index < size() ? EnumBogLogs.values()[index].getName() : EnumBogLogs.values()[0].getName();
	}
}