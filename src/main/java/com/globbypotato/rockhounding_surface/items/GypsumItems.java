package com.globbypotato.rockhounding_surface.items;

import java.util.Random;

import com.globbypotato.rockhounding_surface.enums.EnumGypsumItems;
import com.globbypotato.rockhounding_surface.handler.ModConfig;
import com.globbypotato.rockhounding_surface.integration.SupportUtils;
import com.globbypotato.rockhounding_surface.items.io.ArrayIO;

import net.minecraft.block.BlockGrass;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GypsumItems extends ArrayIO {
	public String[] array;
	Random rand = new Random();
	private int successRate = 4;

	public GypsumItems(String name, String[] array) {
		super(name, array);
		this.array = array;
	}

    @Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items){
        if (this.isInCreativeTab(tab)){
        	for(int i = 0; i < this.array.length; i++){
        		if(i == EnumGypsumItems.BONEMEAL.ordinal() || i == EnumGypsumItems.CONDITIONER.ordinal() || i == EnumGypsumItems.AMENDMENT.ordinal()){
            		if(ModConfig.enableFertilizers){
            			items.add(new ItemStack(this, 1, i));
            		}
        		}else{
        			items.add(new ItemStack(this, 1, i));
        		}
    		}
        }
    }

	@Override
    public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ){
		ItemStack stack = playerIn.getHeldItemMainhand();
        if (!playerIn.canPlayerEdit(pos.offset(facing), facing, stack)){
            return EnumActionResult.FAIL;
        }
		if (!worldIn.isRemote){
		    if(stack.getItemDamage() == 1){
		        if(applyBonemeal(stack, worldIn, pos, playerIn)){
		            if (!worldIn.isRemote){
		                worldIn.playEvent(2005, pos, 0);
		            }
		            return EnumActionResult.SUCCESS;
		        }
		    }else if(stack.getItemDamage() == 2){
		        improveSoil(worldIn, pos, facing, hitX, hitY, hitZ, playerIn);
				decreseStack(playerIn, stack);
		        return EnumActionResult.SUCCESS;
		    }else if(stack.getItemDamage() == 3){
		    	amendSoil(worldIn, pos, facing, hitX, hitY, hitZ, playerIn);
				decreseStack(playerIn, stack);
		        return EnumActionResult.SUCCESS;
		    }else if(stack.getItemDamage() == 4){
		    	compostSoil(worldIn, pos, facing, hitX, hitY, hitZ, playerIn);
				decreseStack(playerIn, stack);
		        return EnumActionResult.SUCCESS;
		    }
		}
		return EnumActionResult.FAIL;
    }

    private void compostSoil(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, EntityPlayer playerIn) {
    	for(int y = -1; y <= 1; y++){
        	for(int x = -2; x <= 2; x++){
            	for(int z = -2; z <= 2; z++){
            		BlockPos dirtPos = new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z);
            		IBlockState dirtState = world.getBlockState(dirtPos);
			    	if(getGravelType(dirtState) && this.rand.nextInt(this.successRate) == 0){
			    		world.setBlockState(dirtPos, Blocks.DIRT.getStateForPlacement(world, dirtPos, facing, hitX, hitY, hitZ, 1, playerIn, EnumHand.MAIN_HAND));
			    	}
            	}
        	}
    	}
	}

	private void amendSoil(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, EntityPlayer playerIn) {
    	for(int y = -1; y <= 1; y++){
        	for(int x = -2; x <= 2; x++){
            	for(int z = -2; z <= 2; z++){
            		BlockPos dirtPos = new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z);
            		IBlockState dirtState = world.getBlockState(dirtPos);
			    	if(getGrassType(dirtState) && this.rand.nextInt(this.successRate) == 0){
			    		world.setBlockState(dirtPos, Blocks.DIRT.getStateForPlacement(world, dirtPos, facing, hitX, hitY, hitZ, 2, playerIn, EnumHand.MAIN_HAND) );
			    	}
            	}
        	}
    	}
	}

	private void improveSoil(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, EntityPlayer playerIn) {
    	for(int y = -1; y <= 1; y++){
        	for(int x = -2; x <= 2; x++){
            	for(int z = -2; z <= 2; z++){
            		BlockPos dirtPos = new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z);
            		IBlockState dirtState = world.getBlockState(dirtPos);
            		//vanilla soil
            		if(getDirtType(dirtState, 1) && this.rand.nextInt(this.successRate) == 0){
            			world.setBlockState(dirtPos, Blocks.DIRT.getStateForPlacement(world, dirtPos, facing, hitX, hitY, hitZ, 0, playerIn, EnumHand.MAIN_HAND) );
            		}else if(getDirtType(dirtState, 0) && this.rand.nextInt(this.successRate) == 0){
            			world.setBlockState(dirtPos, Blocks.GRASS.getDefaultState());
               		//bop soil
            		}else if(SupportUtils.bopLoaded()){
                		if(getBopGrassType(dirtState) && this.rand.nextInt(this.successRate) == 0){
                			world.setBlockState(dirtPos, Blocks.GRASS.getDefaultState());
                		}else{
	                		if(getBopCoarseType(dirtState) && this.rand.nextInt(this.successRate) == 0){
	                			int meta = world.getBlockState(dirtPos).getBlock().getMetaFromState(dirtState) - 8;
	                			world.setBlockState(dirtPos, SupportUtils.dirt().getStateForPlacement(world, dirtPos, facing, hitX, hitY, hitZ, meta, playerIn, EnumHand.MAIN_HAND) );
	                		}else if(getBopDirtType(dirtState) && this.rand.nextInt(this.successRate) == 0){
	                			int meta = world.getBlockState(dirtPos).getBlock().getMetaFromState(dirtState) + 2;
	                			world.setBlockState(dirtPos, SupportUtils.grass().getStateForPlacement(world, dirtPos, facing, hitX, hitY, hitZ, meta, playerIn, EnumHand.MAIN_HAND) );
	                		}
                		}
            		}
            	}
        	}
    	}
	}

	private static void decreseStack(EntityPlayer player, ItemStack stack) {
		if(!player.capabilities.isCreativeMode){
            stack.shrink(1);
			if(stack.getCount() <= 0){stack = ItemStack.EMPTY;}
		}
	}

	private static boolean getBopGrassType(IBlockState dirtState) {
		return dirtState.getBlock() == SupportUtils.grass() && (dirtState.getBlock().getMetaFromState(dirtState) >= 2 && dirtState.getBlock().getMetaFromState(dirtState) <= 4);
	}
	private static boolean getGravelType(IBlockState dirtState) {
		return dirtState.getBlock() == Blocks.GRAVEL;
	}
	private static boolean getGrassType(IBlockState dirtState) {
		return dirtState.getBlock() instanceof BlockGrass;
	}
	private static boolean getBopCoarseType(IBlockState dirtState) {
		return dirtState.getBlock() == SupportUtils.dirt() && dirtState.getBlock().getMetaFromState(dirtState) >= 8;
	}
	private static boolean getBopDirtType(IBlockState dirtState) {
		return dirtState.getBlock() == SupportUtils.dirt() && dirtState.getBlock().getMetaFromState(dirtState) < 8;
	}
	private static boolean getDirtType(IBlockState dirtState, int i) {
		return dirtState.getBlock() == Blocks.DIRT && dirtState.getBlock().getMetaFromState(dirtState) == i;
	}

	public static boolean applyBonemeal(ItemStack stack, World worldIn, BlockPos target, EntityPlayer player){
        IBlockState iblockstate = worldIn.getBlockState(target);
        int hook = net.minecraftforge.event.ForgeEventFactory.onApplyBonemeal(player, worldIn, target, iblockstate, stack, EnumHand.MAIN_HAND);
        if (hook != 0) return hook > 0;
        if (iblockstate.getBlock() instanceof IGrowable){
            IGrowable igrowable = (IGrowable)iblockstate.getBlock();
            if (igrowable.canGrow(worldIn, target, iblockstate, worldIn.isRemote)){
                if (!worldIn.isRemote){
                    if (igrowable.canUseBonemeal(worldIn, worldIn.rand, target, iblockstate)){
                        igrowable.grow(worldIn, worldIn.rand, target, iblockstate);
                    }
                    stack.shrink(1);
                }
                return true;
            }
        }
        return false;
    }

    @SideOnly(Side.CLIENT)
    public static void spawnBonemealParticles(World worldIn, BlockPos pos, int amount){
        if (amount == 0){
            amount = 15;
        }
        IBlockState iblockstate = worldIn.getBlockState(pos);
        if (iblockstate.getMaterial() != Material.AIR){
            for (int i = 0; i < amount; ++i){
                double d0 = itemRand.nextGaussian() * 0.02D;
                double d1 = itemRand.nextGaussian() * 0.02D;
                double d2 = itemRand.nextGaussian() * 0.02D;
                worldIn.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, pos.getX() + itemRand.nextFloat(), pos.getY() + itemRand.nextFloat() * iblockstate.getBoundingBox(worldIn, pos).maxY, pos.getZ() + itemRand.nextFloat(), d0, d1, d2, new int[0]);
            }
        }else{
            for (int i1 = 0; i1 < amount; ++i1){
                double d0 = itemRand.nextGaussian() * 0.02D;
                double d1 = itemRand.nextGaussian() * 0.02D;
                double d2 = itemRand.nextGaussian() * 0.02D;
                worldIn.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, pos.getX() + itemRand.nextFloat(), pos.getY() + (double)itemRand.nextFloat() * 1.0f, pos.getZ() + itemRand.nextFloat(), d0, d1, d2, new int[0]);
            }
        }
    }

}