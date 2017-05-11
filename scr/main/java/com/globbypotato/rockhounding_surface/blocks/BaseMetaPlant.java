package com.globbypotato.rockhounding_surface.blocks;

import java.util.List;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BaseMetaPlant extends BasePlant {
	
	protected BaseMetaPlant(String name, String[] array, Material material, SoundType soundtype, float hardness, float resistance){
		super(name, material, array, soundtype, hardness, resistance);
	}

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list){
    	for(int x = 0; x < this.array.length; x++){
            list.add(new ItemStack(itemIn, 1, x));
    	}
    }

	@Override
    public int damageDropped(IBlockState state){
    	return getMetaFromState(state);
    }

}