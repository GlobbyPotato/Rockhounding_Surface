package com.globbypotato.rockhounding_surface.blocks.woods;

import java.util.Random;

import javax.annotation.Nullable;

import com.globbypotato.rockhounding_surface.ModBlocks;
import com.globbypotato.rockhounding_surface.blocks.BaseSlab;
import com.globbypotato.rockhounding_surface.blocks.itemblocks.FossilSlabIB;
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
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class FossilSlabs extends BaseSlab {
    public static final PropertyEnum<EnumFossilPlanks> VARIANT = PropertyEnum.<EnumFossilPlanks>create("variant", EnumFossilPlanks.class);

	public FossilSlabs(String name, Material material, SoundType soundtype, String[] array, float hardness, float resistance, boolean tab) {
		super(name, material, soundtype, array, hardness, resistance, tab);
		GameRegistry.register(new FossilSlabIB(this, EnumFossilPlanks.getNames()).setRegistryName(name));
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumFossilPlanks.values()[0]));
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(VARIANT, EnumFossilPlanks.values()[meta]);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumFossilPlanks)state.getValue(VARIANT)).ordinal();
	}

	@Override
	public BlockStateContainer createBlockState(){
        return new BlockStateContainer(this, new IProperty[] {VARIANT});
    }

	@Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ){
		if(holdingSlab(heldItem) && isDoublingSlab(world, pos, heldItem, side)){
			world.setBlockState(pos, ModBlocks.fossilPlanks.getDefaultState().withProperty(FossilSlabs.VARIANT, EnumFossilPlanks.values()[heldItem.getItemDamage()]));
			world.playSound(player, pos, SoundEvents.BLOCK_WOOD_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);
            if(!player.capabilities.isCreativeMode){
            	--heldItem.stackSize;
    			if(heldItem.stackSize <= 0){heldItem = null;}
            }
			return true;
		}
		return false;
    }

	private boolean isDoublingSlab(World world, BlockPos pos, ItemStack heldItem, EnumFacing side) {
		IBlockState state = world.getBlockState(pos);
		return (state.getBlock() == ModBlocks.fossilSlabsLo && state.getBlock().getMetaFromState(state) == heldItem.getItemDamage() && side == EnumFacing.UP) ||
 			   (state.getBlock() == ModBlocks.fossilSlabsHi && state.getBlock().getMetaFromState(state) == heldItem.getItemDamage() && side == EnumFacing.DOWN);
	}

	private boolean holdingSlab(ItemStack heldItem){
		return heldItem != null && heldItem.getItem() == Item.getItemFromBlock(ModBlocks.fossilSlabsLo);
	}

	@Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player){
        Item item = Item.getItemFromBlock(ModBlocks.fossilSlabsLo);
        return item == null ? null : new ItemStack(item, 1, this.damageDropped(state));
	}

    @Nullable
	@Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune){
        return Item.getItemFromBlock(ModBlocks.fossilSlabsLo);
    }

}