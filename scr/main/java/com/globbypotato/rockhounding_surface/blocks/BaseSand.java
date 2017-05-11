package com.globbypotato.rockhounding_surface.blocks;

import java.util.Random;

import com.globbypotato.rockhounding_surface.handler.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BaseSand extends BlockFalling {
	public BaseSand(String name, float hardness, float resistance){
		setRegistryName(name);
		setUnlocalizedName(getRegistryName().toString());
		GameRegistry.register(this);
		GameRegistry.register(new ItemBlock(this).setRegistryName(name));
		setHardness(hardness); setResistance(resistance); setSoundType(SoundType.SAND);
		setHarvestLevel("shovel",0);
		setCreativeTab(Reference.RockhoundingSurface);
        this.setDefaultState(this.blockState.getBaseState());
	}

    @SideOnly(Side.CLIENT)
    @Override
    public int getDustColor(IBlockState p_189876_1_){
        return 13355979;
    }

}