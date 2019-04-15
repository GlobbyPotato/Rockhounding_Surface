package com.globbypotato.rockhounding_surface.blocks.itemblocks;

import com.globbypotato.rockhounding_core.blocks.itemblocks.BaseMetaIB;
import com.globbypotato.rockhounding_surface.ModBlocks;
import com.globbypotato.rockhounding_surface.blocks.white_sands.GypsumSlabs;
import com.globbypotato.rockhounding_surface.enums.EnumGypsumBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GypsumSlabIB extends BaseMetaIB {
    IBlockState placingState;

    public GypsumSlabIB(Block block, String[] names) {
        super(block, names);
	}

    @Override
    public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ){
		ItemStack stack = playerIn.getHeldItem(EnumHand.MAIN_HAND);
        IBlockState iblockstate = worldIn.getBlockState(pos);
        Block block = iblockstate.getBlock();

        if (!block.isReplaceable(worldIn, pos)){
            pos = pos.offset(facing);
        }

        if (stack.getCount() != 0 && playerIn.canPlayerEdit(pos, facing, stack) && canBlockBePlaced(iblockstate, this.block, worldIn, pos, facing)){
            if((facing.getIndex() > 1 && hitY > 0.5F) || (facing.getIndex() == 0)){
            	this.placingState = ModBlocks.GYPSUM_SLABS_HI.getDefaultState().withProperty(GypsumSlabs.VARIANT, EnumGypsumBlocks.values()[stack.getItemDamage()]);
            }else{
               	this.placingState = ModBlocks.GYPSUM_SLABS_LO.getDefaultState().withProperty(GypsumSlabs.VARIANT, EnumGypsumBlocks.values()[stack.getItemDamage()]);
            }
            if (placeBlockAt(stack, playerIn, worldIn, pos, facing, hitX, hitY, hitZ, this.placingState)){
                SoundType soundtype = worldIn.getBlockState(pos).getBlock().getSoundType(worldIn.getBlockState(pos), worldIn, pos, playerIn);
                worldIn.playSound(playerIn, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
                if(!playerIn.capabilities.isCreativeMode){
                	stack.shrink(1);
                }
            }
            return EnumActionResult.SUCCESS;
        }
		return EnumActionResult.FAIL;
    }

	private static boolean canBlockBePlaced(IBlockState iblockstate, Block block, World world, BlockPos pos, EnumFacing side){
		AxisAlignedBB axisalignedbb = block.getCollisionBoundingBox(iblockstate, world, pos);
		if (axisalignedbb != null && !world.checkNoEntityCollision(axisalignedbb.offset(pos), null)) return false;
		return block.isReplaceable(world, pos) && block.canPlaceBlockOnSide(world, pos, side);
	}

}