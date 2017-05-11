package com.globbypotato.rockhounding_surface.enums;

import net.minecraft.util.IStringSerializable;

public enum EnumBushes implements IStringSerializable{
	RICEGRASS(), 
	SOAPTREE(),
	SPOON(),
	PEAR(),
	MESQUITE(),
	GRASS();

	@Override
	public String getName() {
		return toString().toLowerCase();
	}

	public static int size(){
		return values().length;
	}

	public static String[] getNames(){
		String[] temp = new String[size()];
		for(int i=0;i<size();i++){
			temp[i] = getName(i);
		}
		return temp;
	}

	public static String getName(int index){
		return index > -1 && index < size() ? EnumBushes.values()[index].getName() : EnumBushes.values()[0].getName();
	}

}