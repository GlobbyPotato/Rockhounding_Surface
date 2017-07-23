package com.globbypotato.rockhounding_surface.blocks.itemblocks;

import com.globbypotato.rockhounding_core.blocks.itemblocks.BaseMetaIB;
import com.globbypotato.rockhounding_surface.ModBlocks;
import com.globbypotato.rockhounding_surface.blocks.plants.GypsumBushLo;
import com.globbypotato.rockhounding_surface.enums.EnumBushes;

import net.minecraft.block.Block;
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

public class BushIB extends BaseMetaIB {
    IBlockState placingState;

    public BushIB(Block block, String[] names) {
        super(block, names);
	}

    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ){
        IBlockState iblockstate = worldIn.getBlockState(pos);
        Block block = iblockstate.getBlock();

        if (!block.isReplaceable(worldIn, pos)){
            pos = pos.offset(facing);
        }

        if(stack.getItem() == Item.getItemFromBlock(ModBlocks.gypsumBushLo)){
	        BlockPos topPos = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ());
	        IBlockState topState = worldIn.getBlockState(topPos);
	        if(topState.getBlock().isAir(topState, worldIn, topPos)){
            	placingState = ModBlocks.gypsumBushLo.getDefaultState().withProperty(GypsumBushLo.VARIANT, EnumBushes.values()[stack.getItemDamage()]);
	            if (placeBlockAt(stack, playerIn, worldIn, pos, facing, hitX, hitY, hitZ, placingState)){
	                SoundType soundtype = worldIn.getBlockState(pos).getBlock().getSoundType(worldIn.getBlockState(pos), worldIn, pos, playerIn);
	                worldIn.playSound(playerIn, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
	                if(!playerIn.capabilities.isCreativeMode){
	                	--stack.stackSize;
	                }
	            }
	            return EnumActionResult.SUCCESS;
	        }else{
	            return EnumActionResult.FAIL;
	        }
        }
        return EnumActionResult.SUCCESS;
    }

}