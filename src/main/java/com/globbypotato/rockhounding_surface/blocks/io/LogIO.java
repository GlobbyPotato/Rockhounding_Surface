package com.globbypotato.rockhounding_surface.blocks.io;

import com.globbypotato.rockhounding_surface.handler.Reference;

import net.minecraft.block.BlockLog;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class LogIO extends BlockLog {
	
	protected LogIO(String name){
		super();
		setRegistryName(name);
		setUnlocalizedName(getRegistryName().toString());
		setHarvestLevel("axe", 0);
		setHardness(1.0F);
		setResistance(2.0F);
		setCreativeTab(Reference.RockhoundingSurface);
	}

	@Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items){
    	for(int x = 0; x < 4; x++){
    		items.add(new ItemStack(this, 1, x));
    	}
    }

    @Override
	public boolean isWood(IBlockAccess world, BlockPos pos){
        return false;
    }

}