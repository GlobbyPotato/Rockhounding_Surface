package com.globbypotato.rockhounding_surface.blocks.itemblocks;

import com.globbypotato.rockhounding_surface.ModBlocks;
import com.globbypotato.rockhounding_surface.blocks.plants.GypsumShrubs;
import com.globbypotato.rockhounding_surface.enums.EnumShrubs;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockSand;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ShrubsIB extends MetaIB {
    IBlockState placingState;

    public ShrubsIB(Block block, String[] names) {
        super(block, names);
	}

    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ){
        IBlockState state = worldIn.getBlockState(pos);
        Block block = state.getBlock();

        if (!block.isReplaceable(worldIn, pos)){
            pos = pos.offset(facing);
        }

        if(stack.getItem() == Item.getItemFromBlock(ModBlocks.gypsumCrops)){
	        if(isValidSoil(state)){
            	placingState = ModBlocks.gypsumCrops.getDefaultState().withProperty(GypsumShrubs.VARIANT, EnumShrubs.values()[stack.getItemDamage()]);
	            if (placeBlockAt(stack, playerIn, worldIn, pos, facing, hitX, hitY, hitZ, placingState)){
	                SoundType soundtype = worldIn.getBlockState(pos).getBlock().getSoundType(worldIn.getBlockState(pos), worldIn, pos, playerIn);
	                worldIn.playSound(playerIn, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
	                if(!playerIn.capabilities.isCreativeMode){--stack.stackSize;}
		            return EnumActionResult.SUCCESS;
	            }
	        }
        }
        return EnumActionResult.FAIL;
    }

	private boolean isValidSoil(IBlockState state) {
		return state.getBlock() instanceof BlockGrass || state.getBlock() instanceof BlockSand || state.getBlock() instanceof BlockDirt || state.getBlock() instanceof BlockFarmland || state.getBlock() == ModBlocks.whiteSand;
	}

}