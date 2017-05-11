package com.globbypotato.rockhounding_surface.enums;

public enum EnumAgingItems {
	ORGANIC_COMPOST, 
	CONTAMINATING_COMPOST,
	IRON_COMPOST,
	COPPER_COMPOST,
	CHROMIUM_COMPOST,
	MANGANESE_COMPOST,
	RAINBOW_COMPOST,
	LAPIS_COMPOST,
	CHALCEDONY_COMPOST,
	TEREDO_GRUB,
	TEREDO_COLTURE;

	public static String[] getNames(){
		String[] temp = new String[size()];
		for(int i=0;i<size();i++){
			temp[i] = getName(i);
		}
		return temp;
	}
	public static String getName(int index){return EnumAgingItems.values()[index].toString().toLowerCase();}
	
	public static int size(){return values().length;}

}