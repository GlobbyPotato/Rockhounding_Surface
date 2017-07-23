package com.globbypotato.rockhounding_surface.enums;

public enum EnumWalls implements BaseEnum {
	PLAIN	(0,  "plain"), 
	BRICK	(1,  "brick");

	private int meta;
	private final String name;

	private EnumWalls(int meta, String name) {
		this.meta = meta; this.name = name; 
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
		for(int i=0;i<size();i++){temp[i] = getName(i);}
		return temp;
	}

	public static String getName(int index){
		return name(index);
	}

	public String getName() { 
		return this.name; 
	}
    
	public int getMetadata() { 
		return this.meta; 
	}

}