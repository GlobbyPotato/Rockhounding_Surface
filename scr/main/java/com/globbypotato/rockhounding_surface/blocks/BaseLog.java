package com.globbypotato.rockhounding_surface.blocks;

import java.util.List;
import java.util.Random;

import com.globbypotato.rockhounding_surface.handler.Reference;

import net.minecraft.block.BlockLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BaseLog extends BlockLog {
	public String[] array;
	
	protected BaseLog(String name, String[] array){
		super();
		setRegistryName(name);
		setUnlocalizedName(getRegistryName().toString());
		GameRegistry.register(this);
		setHarvestLevel("axe", 0);
		setCreativeTab(Reference.RockhoundingSurface);
		this.array = array;
	}

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list){
    	for(int x = 0; x < 4; x++){
            list.add(new ItemStack(itemIn, 1, x));
    	}
    }

	@Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune){
		return Item.getItemFromBlock(this);
	}

    public int damageDropped(IBlockState state){
    	return getMetaFromState(state);
    }

	public int quantityDropped(Random rand) {
		return 1;
	}

}