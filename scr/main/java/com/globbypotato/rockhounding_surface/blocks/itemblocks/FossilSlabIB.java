package com.globbypotato.rockhounding_surface.blocks.itemblocks;

import com.globbypotato.rockhounding_core.blocks.itemblocks.BaseMetaIB;
import com.globbypotato.rockhounding_surface.ModBlocks;
import com.globbypotato.rockhounding_surface.blocks.woods.FossilSlabs;
import com.globbypotato.rockhounding_surface.enums.EnumFossilPlanks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FossilSlabIB extends BaseMetaIB {
    IBlockState placingState;

    public FossilSlabIB(Block block, String[] names) {
        super(block, names);
	}

    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ){
        IBlockState iblockstate = worldIn.getBlockState(pos);
        Block block = iblockstate.getBlock();

        if (!block.isReplaceable(worldIn, pos)){
            pos = pos.offset(facing);
        }

        if (stack.stackSize != 0 && playerIn.canPlayerEdit(pos, facing, stack) && worldIn.canBlockBePlaced(this.block, pos, false, facing, (Entity)null, stack)){
            int i = this.getMetadata(stack.getMetadata());
	            if((facing.getIndex() > 1 && hitY > 0.5F) || (facing.getIndex() == 0)){
	            	placingState = ModBlocks.fossilSlabsHi.getDefaultState().withProperty(FossilSlabs.VARIANT, EnumFossilPlanks.values()[stack.getItemDamage()]);
	            }else{
	               	placingState = ModBlocks.fossilSlabsLo.getDefaultState().withProperty(FossilSlabs.VARIANT, EnumFossilPlanks.values()[stack.getItemDamage()]);
	            }
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
}