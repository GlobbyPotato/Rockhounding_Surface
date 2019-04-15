package com.globbypotato.rockhounding_surface.blocks.white_sands;

import com.globbypotato.rockhounding_surface.blocks.io.BushIO;
import com.globbypotato.rockhounding_surface.blocks.io.SandIO;
import com.globbypotato.rockhounding_surface.blocks.io.ShrubsIO;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WhiteSand extends SandIO {
	public WhiteSand(String name){
		super(name, 1.0F, 1.0F);
	}

    @SideOnly(Side.CLIENT)
    @Override
    public int getDustColor(IBlockState state){
        return 13355979;
    }

    @Override
    public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, IPlantable plantable){
        IBlockState plant = plantable.getPlant(world, pos.offset(direction));
        EnumPlantType plantType = plantable.getPlantType(world, pos.offset(direction));

        if ((plant.getBlock() == Blocks.REEDS && hasWater(world, pos)) || plant.getBlock() == Blocks.CACTUS || plant.getBlock() == Blocks.DEADBUSH){
            return true;
        }

        if (plantable instanceof BushIO || plantable instanceof ShrubsIO){
            return true;
        }

        return false;
    }

	private boolean hasWater(IBlockAccess world, BlockPos pos) {
        for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL){
            IBlockState iblockstate = world.getBlockState(pos.offset(enumfacing));
            if (iblockstate.getMaterial() == Material.WATER || iblockstate.getBlock() == Blocks.FROSTED_ICE){
                return true;
            }
        }
        return false;
	}
}