package com.globbypotato.rockhounding_surface.blocks;

import com.globbypotato.rockhounding_surface.handler.Reference;

import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BaseOriented extends BlockRotatedPillar {

	public BaseOriented(String name, Material material, SoundType soundtype, float hardness, float resistance) {
		super(material);
		setRegistryName(name);
		setUnlocalizedName(getRegistryName().toString());
		GameRegistry.register(this);
		GameRegistry.register(new ItemBlock(this).setRegistryName(name));
		setHardness(hardness); setResistance(resistance); setSoundType(soundtype);
		setCreativeTab(Reference.RockhoundingSurface);
	}
}