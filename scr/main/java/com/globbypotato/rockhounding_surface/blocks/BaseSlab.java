package com.globbypotato.rockhounding_surface.blocks;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BaseSlab extends BaseMetaBlock {
    public static final AxisAlignedBB LO_SLAB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);
    public static final AxisAlignedBB HI_SLAB = new AxisAlignedBB(0.0D, 0.5D, 0.0D, 1.0D, 1.0D, 1.0D);
    public String[] array;
    public boolean tab;
    
	public BaseSlab(String name, Material material, SoundType soundtype, String[] array, float hardness, float resistance, boolean tab) {
		super(name, array, material, soundtype, hardness, resistance);
		this.tab = tab;
		this.array = array;
	}

	@Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos){
        return this.tab ? LO_SLAB : HI_SLAB;
    }

    @Nullable
	@Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, World worldIn, BlockPos pos){
        return this.tab ? LO_SLAB : HI_SLAB;
    }

    @SideOnly(Side.CLIENT)
	@Override
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list){
        if (this.tab){
        	for(int x = 0; x < this.array.length; x++){
                list.add(new ItemStack(itemIn, 1, x));
        	}
        }
    }

	@Override
    public EnumBlockRenderType getRenderType(IBlockState state){
        return EnumBlockRenderType.MODEL;
    }

	@Override
    public boolean isFullyOpaque(IBlockState state){
        return false;
    }

	@Override
    public boolean isOpaqueCube(IBlockState state){
        return false;
    }

	@Override
	public boolean isFullBlock(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
}