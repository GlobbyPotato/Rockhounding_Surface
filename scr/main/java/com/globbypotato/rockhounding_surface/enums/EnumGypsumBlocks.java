package com.globbypotato.rockhounding_surface.enums;

public enum EnumGypsumBlocks implements BaseEnum {
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
		for(int i=0;i<size();i++){temp[i] = getName(i);}
		return temp;
	}
	
	public static String getName(int index){
		return name(index);
	}
}