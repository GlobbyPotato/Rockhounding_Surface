package com.globbypotato.rockhounding_surface.blocks.io;

import java.util.Random;

import com.globbypotato.rockhounding_surface.handler.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

public class PlantIO extends Block{
	public String[] array;

	public PlantIO(String name, Material materialIn, String[] array) {
		super(materialIn);
		setRegistryName(name);
		setUnlocalizedName(getRegistryName().toString());
        setSoundType(SoundType.PLANT);
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