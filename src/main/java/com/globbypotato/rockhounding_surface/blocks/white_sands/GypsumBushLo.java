package com.globbypotato.rockhounding_surface.blocks.white_sands;

import java.util.Random;

import com.globbypotato.rockhounding_surface.ModBlocks;
import com.globbypotato.rockhounding_surface.ModItems;
import com.globbypotato.rockhounding_surface.blocks.io.BushIO;
import com.globbypotato.rockhounding_surface.enums.EnumBushes;
import com.globbypotato.rockhounding_surface.utils.ModUtils;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GypsumBushLo extends BushIO {
	public GypsumBushLo(String name){
        super(name);
        this.setTickRandomly(true);
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumBushes.values()[0]));
        this.disableStats();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items){
    	for(int x = 0; x < EnumBushes.size(); x++){
    		items.add(new ItemStack(this, 1, x));
    	}
    }

    @Override
    public boolean canSustainBush(IBlockState state){
        return ModUtils.isValidSoil(state);
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos){
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
        if(!hasTop(worldIn, pos)){
            this.dropHalfBlock(worldIn, pos, state);
        }
        IBlockState soilState = worldIn.getBlockState(pos.down());
    	if(!ModUtils.isValidSoil(soilState)){
            this.dropHalfBlock(worldIn, pos, state);
    	}
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand){
        if(!hasTop(worldIn, pos)){
            this.dropHalfBlock(worldIn, pos, state);
        }
        IBlockState soilState = worldIn.getBlockState(pos.down());
    	if(!ModUtils.isValidSoil(soilState)){
            this.dropHalfBlock(worldIn, pos, state);
    	}

    	worldIn.scheduleBlockUpdate(pos, this, this.tickRate(worldIn), 0);
    }

    @Override
    public void checkAndDropBlock(World worldIn, BlockPos pos, IBlockState state){}

    public void dropHalfBlock(World worldIn, BlockPos pos, IBlockState state){
        this.dropBlockAsItem(worldIn, pos, state, 0);
        if(!worldIn.isRemote){
        	worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
        }
    }

    private static boolean hasTop(World worldIn, BlockPos pos){
        IBlockState topState = worldIn.getBlockState(pos.up());
        return topState.getBlock() instanceof GypsumBushHi;
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack){
        BlockPos topPos = pos.up();
        IBlockState topState = worldIn.getBlockState(topPos);
        if(topState.getBlock().isAir(topState, worldIn, topPos)){
        	worldIn.setBlockState(topPos, ModBlocks.GYPSUM_BUSH_HI.getStateFromMeta(stack.getMetadata()));
        }
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

	@Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune){
		int meta = state.getBlock().getMetaFromState(state);
		if(state.getBlock() instanceof GypsumBushHi){
			return null;
		}
		if(meta == 0){
			return ModItems.RICEGRASS_SEEDS;
		}else if(meta == 3){
			return Item.getItemFromBlock(ModBlocks.GYPSUM_CROPS);
		}else if(meta == 4){
			return Item.getItemFromBlock(ModBlocks.GYPSUM_CROPS);
		}
		return Item.getItemFromBlock(this);
	}

	@Override
    public int damageDropped(IBlockState state){
		int meta = state.getBlock().getMetaFromState(state);
		if(meta == 0){
			return 0;
		}else if(meta == 3){
			return 3;
		}else if(meta == 4){
			return 6;
		}else{
	        return ((EnumBushes)state.getValue(VARIANT)).ordinal();
		}
    }

}