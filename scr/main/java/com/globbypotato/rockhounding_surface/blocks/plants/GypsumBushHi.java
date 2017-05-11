package com.globbypotato.rockhounding_surface.blocks.plants;

import com.globbypotato.rockhounding_surface.ModBlocks;
import com.globbypotato.rockhounding_surface.blocks.BaseBush;
import com.globbypotato.rockhounding_surface.blocks.itemblocks.BushIB;
import com.globbypotato.rockhounding_surface.enums.EnumBushes;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class GypsumBushHi extends BaseBush {
	public GypsumBushHi(String name){
        super(name);
		GameRegistry.register(new BushIB(this, EnumBushes.getNames()).setRegistryName(name));
		setCreativeTab(null);
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumBushes.values()[0]));
    }

    @Override
    public boolean canSustainBush(IBlockState state){
        return state.getBlock() instanceof GypsumBushLo;
    }

    @Override
    public void checkAndDropBlock(World worldIn, BlockPos pos, IBlockState state){
        if (!this.canBlockStay(worldIn, pos, state)){
            if(!worldIn.isRemote){
            	worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
            }
        }
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player){
    	return new ItemStack(ModBlocks.gypsumBushLo, 1, state.getBlock().getMetaFromState(state));
    }

    @Override
    public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player){
    	return false;
    }

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(VARIANT, EnumBushes.values()[meta]);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumBushes)state.getValue(VARIANT)).ordinal();
	}

	@Override
	public BlockStateContainer createBlockState(){
		return new BlockStateContainer(this, new IProperty[] { VARIANT });
	}

}