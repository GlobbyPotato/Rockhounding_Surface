package com.globbypotato.rockhounding_surface.blocks.rocks;

import java.util.List;

import com.globbypotato.rockhounding_surface.ModBlocks;
import com.globbypotato.rockhounding_surface.handler.Reference;

import net.minecraft.block.BlockWall;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BaseWall extends BlockWall {
    public BaseWall(String name, SoundType soundtype, float hardness, float resistance) {
		super(ModBlocks.gypsum);
		setRegistryName(name);
		setUnlocalizedName(getRegistryName().toString());
		GameRegistry.register(this);
		GameRegistry.register(new ItemBlock(this).setRegistryName(name));
		setHardness(hardness); setResistance(resistance); setSoundType(soundtype);
		setCreativeTab(Reference.RockhoundingSurface);
	}

    @SideOnly(Side.CLIENT)
    @Override
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list){
    	list.add(new ItemStack(itemIn, 1, 0));
    }

    @Override
    public BlockStateContainer createBlockState(){
        return new BlockStateContainer(this, new IProperty[] {UP, NORTH, EAST, WEST, SOUTH, VARIANT});
    }
}