package com.globbypotato.rockhounding_surface.blocks.white_sands;

import java.util.Random;

import javax.annotation.Nullable;

import com.globbypotato.rockhounding_surface.ModBlocks;
import com.globbypotato.rockhounding_surface.blocks.io.SlabsIO;
import com.globbypotato.rockhounding_surface.enums.EnumGypsumBlocks;

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
import net.minecraft.world.World;

public class GypsumSlabs extends SlabsIO {
    public static final PropertyEnum<EnumGypsumBlocks> VARIANT = PropertyEnum.<EnumGypsumBlocks>create("variant", EnumGypsumBlocks.class);

	public GypsumSlabs(String name, String[] array, boolean tab) {
		super(name, Material.ROCK, array, 1.0F, 2.0F, tab, SoundType.STONE);
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumGypsumBlocks.values()[0]));
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(VARIANT, EnumGypsumBlocks.values()[meta]);
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
			world.setBlockState(pos, ModBlocks.GYPSUM.getDefaultState().withProperty(GypsumSlabs.VARIANT, EnumGypsumBlocks.values()[heldItem.getItemDamage()]));
			world.playSound(player, pos, SoundEvents.BLOCK_STONE_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);
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
		return (state.getBlock() == ModBlocks.GYPSUM_SLABS_LO && state.getBlock().getMetaFromState(state) == heldItem.getItemDamage() && side == EnumFacing.UP) ||
 			   (state.getBlock() == ModBlocks.GYPSUM_SLABS_HI && state.getBlock().getMetaFromState(state) == heldItem.getItemDamage() && side == EnumFacing.DOWN);
	}

	private static boolean holdingSlabLo(ItemStack heldItem){
		return !heldItem.isEmpty() && heldItem.getItem() == Item.getItemFromBlock(ModBlocks.GYPSUM_SLABS_LO);
	}

	@Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player){
        return new ItemStack(ModBlocks.GYPSUM_SLABS_LO, 1, this.damageDropped(state));
	}

    @Nullable
	@Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune){
        return Item.getItemFromBlock(ModBlocks.GYPSUM_SLABS_LO);
    }

}