package com.globbypotato.rockhounding_surface.blocks;

import java.util.Random;

import com.globbypotato.rockhounding_surface.handler.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BaseBlock extends Block{
	public String[] array;

	protected BaseBlock(String name, Material materialIn, String[] array, SoundType soundtype, float hardness, float resistance) {
		super(materialIn);
		setRegistryName(name);
		setUnlocalizedName(getRegistryName().toString());
		GameRegistry.register(this);
		setHardness(hardness); setResistance(resistance); setSoundType(soundtype);
		setCreativeTab(Reference.RockhoundingSurface);
		this.array = array;
	}

	@Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune){
		return Item.getItemFromBlock(this);
	}

	@Override
	public int quantityDropped(Random rand) {
		return 1;
	}

}