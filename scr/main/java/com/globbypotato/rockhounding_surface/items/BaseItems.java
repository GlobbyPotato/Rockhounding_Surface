package com.globbypotato.rockhounding_surface.items;

import java.util.List;

import com.globbypotato.rockhounding_surface.handler.Reference;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BaseItems extends Item {
	public String[] array;

	public BaseItems(String name) {
		super();
	    setRegistryName(name);
        setUnlocalizedName(this.getRegistryName().toString());
        GameRegistry.register(this);
		setCreativeTab(Reference.RockhoundingSurface);
	}
	
	@SideOnly(Side.CLIENT)
	public void initModel() {
    	ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}

}