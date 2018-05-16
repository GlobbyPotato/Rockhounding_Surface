package com.globbypotato.rockhounding_surface.handler;

import com.globbypotato.rockhounding_surface.ModBlocks;
import com.globbypotato.rockhounding_surface.enums.EnumFossilPlanks;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Reference {
	// Create Mod Reference 
	public static final String MODID = "rockhounding_surface";
	public static final String VERSION = "${version_mod}";
	public static final String CLIENT_PROXY_CLASS = "com.globbypotato.rockhounding_surface.proxy.ClientProxy";
	public static final String SERVER_PROXY_CLASS = "com.globbypotato.rockhounding_surface.proxy.CommonProxy";

	//Create new Creative Tab with Icon
	public static CreativeTabs RockhoundingSurface = new CreativeTabs("rockhoundingSurface") {
        @SideOnly(Side.CLIENT)
        @Override
		public Item getTabIconItem() { 
			return Item.getItemFromBlock(ModBlocks.fossilPlanks); 
		}
        @SideOnly(Side.CLIENT)
        @Override
        public int getIconItemDamage(){
            return EnumFossilPlanks.BETULA.ordinal();
        }
	};
}