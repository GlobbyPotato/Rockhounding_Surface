package com.globbypotato.rockhounding_surface.enums;

public enum EnumFood {
	TRUFFLE_SLICES,
	TRUFFLE_OIL, 
	TRUFFLE_CANAPE,
	TRUFFLE_RICE,
	TRUFFLE_FILLET,
	TRUFFLE_VODKA,
	TRUFFLE_TOAST,
	TRUFFLE_TOMATO,
	TRUFFLE_PUMPKIN,
	TRUFFLE_OMELETTE,
	SOURBERRY_FRUIT,
	SOURBERRY_JUICE,
	SOURBERRY_PIE,
	SOURBERRY_TEA,
	PURPLEPEAR_FRUIT,
	PURPLEPEAR_JUICE,
	PURPLEPEAR_SMOOTHIE,
	PURPLEPEAR_JAM,
	SOTOL,
	MESQUITE_FRUIT,
	MESQUITE_PIE,
	MESQUITE_LIQUOR,
	MESQUITE_TEA;

	private EnumFood() {}

	public static String[] getNames(){
		String[] temp = new String[size()];
		for(int i=0;i<size();i++){temp[i] = getName(i);}
		return temp;
	}
	public static String getName(int index){return EnumFood.values()[index].toString().toLowerCase();}

	public static int size(){return values().length;}
}