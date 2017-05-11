package com.globbypotato.rockhounding_surface.blocks.rocks;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.globbypotato.rockhounding_surface.ModBlocks;
import com.globbypotato.rockhounding_surface.blocks.BaseSlab;
import com.globbypotato.rockhounding_surface.blocks.itemblocks.GypsumSlabIB;
import com.globbypotato.rockhounding_surface.enums.EnumGypsumBlocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GypsumSlabs extends BaseSlab {
    public static final PropertyEnum<EnumGypsumBlocks> VARIANT = PropertyEnum.<EnumGypsumBlocks>create("variant", EnumGypsumBlocks.class);
	public GypsumSlabs(String name, Material material, SoundType soundtype, String[] array, float hardness, float resistance, boolean tab) {
		super(name, material, soundtype, array, hardness, resistance, tab);
		GameRegistry.register(new GypsumSlabIB(this, EnumGypsumBlocks.getNames()).setRegistryName(name));
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumGypsumBlocks.values()[0]));
		if(!tab){setCreativeTab(null);}
	}

    @SideOnly(Side.CLIENT)
	@Override
    public void getSubBlocks(Item itemIn, CreativeTabs ctab, List<ItemStack> list){
    	if(this.tab){
	    	for(int x = 0; x < this.array.length; x++){
	    		if(x != EnumGypsumBlocks.DOUBLE.ordinal()){
	    			list.add(new ItemStack(itemIn, 1, x));
	    		}
	    	}
    	}
    }

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(VARIANT, EnumGypsumBlocks.values()[meta]);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumGypsumBlocks)state.getValue(VARIANT)).ordinal();
	}

	@Override
	public BlockStateContainer createBlockState(){
        return new BlockStateContainer(this, new IProperty[] {VARIANT});
    }

	@Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ){
		if(holdingSlab(heldItem) && isDoublingSlab(world, pos, heldItem, side)){
			if(heldItem.getItemDamage() == 1){
				world.setBlockState(pos, ModBlocks.gypsum.getDefaultState().withProperty(GypsumSlabs.VARIANT, EnumGypsumBlocks.values()[6]));
				world.playSound(player, pos, SoundEvents.BLOCK_STONE_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);
			}else{
				world.setBlockState(pos, ModBlocks.gypsum.getDefaultState().withProperty(GypsumSlabs.VARIANT, EnumGypsumBlocks.values()[heldItem.getItemDamage()]));
				world.playSound(player, pos, SoundEvents.BLOCK_STONE_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);
			}
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
		return (state.getBlock() == ModBlocks.gypsumSlabsLo && state.getBlock().getMetaFromState(state) == heldItem.getItemDamage() && side == EnumFacing.UP) ||
 			   (state.getBlock() == ModBlocks.gypsumSlabsHi && state.getBlock().getMetaFromState(state) == heldItem.getItemDamage() && side == EnumFacing.DOWN);
	}

	private boolean holdingSlab(ItemStack heldItem){
		return heldItem != null && heldItem.getItem() == Item.getItemFromBlock(ModBlocks.gypsumSlabsLo);
	}

	@Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player){
        Item item = Item.getItemFromBlock(ModBlocks.gypsumSlabsLo);
        return item == null ? null : new ItemStack(item, 1, this.damageDropped(state));
	}

    @Nullable
	@Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune){
        return Item.getItemFromBlock(ModBlocks.gypsumSlabsLo);
    }
}