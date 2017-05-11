package com.globbypotato.rockhounding_surface.enums;

public enum EnumGypsumItems {
	GYPSUM,
	BONEMEAL,
	CONDITIONER,
	AMENDMENT,
	COMPOST,
	MESQUITE_FLOUR,
	MESQUITE_FLOWER;

	private EnumGypsumItems() {}

	public static String[] getNames(){
		String[] temp = new String[size()];
		for(int i=0;i<size();i++){
			temp[i] = getName(i);
		}
		return temp;
	}
	public static String getName(int index){return EnumGypsumItems.values()[index].toString().toLowerCase();}
	
	public static int size(){return values().length;}

}