package com.globbypotato.rockhounding_surface.enums;

import net.minecraft.util.IStringSerializable;

public enum EnumWalls implements IStringSerializable {
	PLAIN	(0,  "plain"), 
	BRICK	(1,  "brick");

	private static final EnumWalls[] META_LOOKUP = new EnumWalls[values().length];
	private int meta;
	private final String name;

	private EnumWalls(int meta, String name) {this.meta = meta; this.name = name; }

	public String getName() { return this.name; }
    public int getMetadata() { return this.meta; }

	@Override
	public String toString() { return this.getName(); }

    public static EnumWalls byMetadata(int meta) {
        if (meta < 0 || meta >= META_LOOKUP.length) { meta = 0; }
        return META_LOOKUP[meta];
    }

    static {
    	EnumWalls[] metas = values();
        int metaLenght = metas.length;
        for (int x = 0; x < metaLenght; ++x) {
        	EnumWalls metaIn = metas[x];
            META_LOOKUP[metaIn.getMetadata()] = metaIn;
        }
    }

	public static String[] getNames(){
		String[] temp = new String[size()];
		for(int i=0;i<size();i++){temp[i] = getName(i);}
		return temp;
	}
	public static String getName(int index){return EnumWalls.values()[index].toString().toLowerCase();}
	public static int size(){return values().length;}
}