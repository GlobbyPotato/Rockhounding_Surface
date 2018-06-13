package com.globbypotato.rockhounding_surface.enums;

public enum EnumComposting implements BaseEnum{
	SEEDS(1), 
	PLANTS(3),
	SAPLING(5),
	VEGETATION(10),
	FOOD(20),
	FOSSIL(50);

	public int compost;
	
	EnumComposting(int compost){
		this.compost = compost;
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
		for(int i=0;i<size();i++){
			temp[i] = getName(i);
		}
		return temp;
	}

	public static String getName(int index){
		return name(index);
	}

	//---------CUSTOM----------------
	public int[] getFactors(){
		int[] temp = new int[size()];
		for(int i=0;i<size();i++){
			temp[i] = getValue(i);
		}
		return temp;
	}

	public static int getValue(int index){
		return values()[index].getValue();
	}

	public int getValue(){
		return this.compost;
	}

}