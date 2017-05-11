package com.globbypotato.rockhounding_surface.machines;

import com.globbypotato.rockhounding_surface.blocks.itemblocks.FueledMachineIB;
import com.globbypotato.rockhounding_surface.handler.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BaseMachineBlock extends Block {

	public BaseMachineBlock(String name) {
		this(name, Material.ROCK);
	}
	
	public BaseMachineBlock(String name, Material material) {
		super(material);
		setRegistryName(name);
		setUnlocalizedName(getRegistryName().toString());
		GameRegistry.register(this);
		GameRegistry.register(new FueledMachineIB(this), getRegistryName());
		setCreativeTab(Reference.RockhoundingSurface);
	}

	@SideOnly(Side.CLIENT)
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}
}