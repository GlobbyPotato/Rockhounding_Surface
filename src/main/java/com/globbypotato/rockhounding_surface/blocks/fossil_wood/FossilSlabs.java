package com.globbypotato.rockhounding_surface.blocks.fossil_wood;

import java.util.Random;

import javax.annotation.Nullable;

import com.globbypotato.rockhounding_surface.ModBlocks;
import com.globbypotato.rockhounding_surface.blocks.io.SlabsIO;
import com.globbypotato.rockhounding_surface.enums.EnumFossilPlanks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class FossilSlabs extends SlabsIO {
    public static final PropertyEnum<EnumFossilPlanks> VARIANT = PropertyEnum.<EnumFossilPlanks>create("variant", EnumFossilPlanks.class);

	public FossilSlabs(String name, String[] array, boolean tab) {
		super(name, Material.WOOD, array, 1.0F, 2.0F, tab, SoundType.WOOD);
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumFossilPlanks.values()[0]));
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(VARIANT, EnumFossilPlanks.values()[meta]);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(VARIANT).ordinal();
	}

	@Override
	public BlockStateContainer createBlockState(){
        return new BlockStateContainer(this, new IProperty[] {VARIANT});
    }

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ){
		ItemStack heldItem = player.getHeldItemMainhand();
		if(holdingSlabLo(heldItem) && isDoublingSlab(world, pos, heldItem, side)){
			world.setBlockState(pos, ModBlocks.FOSSIL_PLANKS.getDefaultState().withProperty(FossilSlabs.VARIANT, EnumFossilPlanks.values()[heldItem.getItemDamage()]));
			world.playSound(player, pos, SoundEvents.BLOCK_WOOD_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);
            if(!player.capabilities.isCreativeMode){
            	heldItem.shrink(1);
    			if(heldItem.getCount() <= 0){heldItem = ItemStack.EMPTY;}
            }
			return true;
		}
		return false;
    }

	private static boolean isDoublingSlab(World world, BlockPos pos, ItemStack heldItem, EnumFacing side) {
		IBlockState state = world.getBlockState(pos);
		return (state.getBlock() == ModBlocks.FOSSIL_SLABS_LO && state.getBlock().getMetaFromState(state) == heldItem.getItemDamage() && side == EnumFacing.UP) ||
 			   (state.getBlock() == ModBlocks.FOSSIL_SLABS_HI && state.getBlock().getMetaFromState(state) == heldItem.getItemDamage() && side == EnumFacing.DOWN);
	}

	private static boolean holdingSlabLo(ItemStack heldItem){
		return !heldItem.isEmpty() && heldItem.getItem() == Item.getItemFromBlock(ModBlocks.FOSSIL_SLABS_LO);
	}

	@Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player){
        return new ItemStack(ModBlocks.FOSSIL_SLABS_LO, 1, this.damageDropped(state));
	}

    @Nullable
	@Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune){
        return Item.getItemFromBlock(ModBlocks.FOSSIL_SLABS_LO);
    }

    @Override
	public boolean isWood(IBlockAccess world, BlockPos pos){
        return false;
    }

}