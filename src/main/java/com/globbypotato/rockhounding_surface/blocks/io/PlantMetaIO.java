package com.globbypotato.rockhounding_surface.blocks.io;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PlantMetaIO extends PlantIO {
	
	protected PlantMetaIO(String name, String[] array, Material material){
		super(name, material, array);
	}

    @Override
	@SideOnly(Side.CLIENT)
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items){
    	for(int x = 0; x < this.array.length; x++){
    		items.add(new ItemStack(this, 1, x));
    	}
    }

	@Override
    public int damageDropped(IBlockState state){
    	return getMetaFromState(state);
    }

}