package com.globbypotato.rockhounding_surface.handler;

import com.globbypotato.rockhounding_surface.ModBlocks;
import com.globbypotato.rockhounding_surface.enums.EnumColdLogs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Reference {
	// Create Mod Reference 
	public static final String MODID = "rockhounding_surface";
    public static final String NAME = "Rockhounding Mod: Surface";
	public static final String VERSION = "${version_mod}";
	public static final String CLIENT_PROXY_CLASS = "com.globbypotato.rockhounding_surface.proxy.ClientProxy";
	public static final String SERVER_PROXY_CLASS = "com.globbypotato.rockhounding_surface.proxy.CommonProxy";

	//Create new Creative Tab with Icon
	public static CreativeTabs RockhoundingSurface = new CreativeTabs("rockhoundingSurface") {
        @Override
		@SideOnly(Side.CLIENT)
		public ItemStack getTabIconItem() { 
			return new ItemStack(ModBlocks.COLD_LOGS, 1, EnumColdLogs.AZURITE.ordinal()); 
		}
	};
}