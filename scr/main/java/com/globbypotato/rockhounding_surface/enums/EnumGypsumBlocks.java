package com.globbypotato.rockhounding_surface.enums;

import net.minecraft.util.IStringSerializable;

public enum EnumGypsumBlocks implements IStringSerializable {
	PLAIN, 
	BLOCK, 
	BRICK,
	CRACKED, 
	CARVED, 
	MOSSY,
	DOUBLE,
	FRACTAL,
	TILE,
	NETHER,
	COBBLE,
	CREEPER;

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
		return index > -1 && index < size() ? EnumGypsumBlocks.values()[index].getName() : EnumGypsumBlocks.values()[0].getName();
	}
}