package com.globbypotato.rockhounding_surface.compat.waila;

import net.minecraftforge.fml.common.event.FMLInterModComms;

public class WailaCompat {
	private static String part1 = "com.globbypotato.rockhounding_surface.compat.waila.";
	private static String part2 = ".callbackRegister";
	private static String wailaID = "Waila";

	public static void init() {
		FMLInterModComms.sendMessage(wailaID, "register", part1 + "WailaIncubator" + part2);
		FMLInterModComms.sendMessage(wailaID, "register", part1 + "WailaComposter" + part2);
	}

}