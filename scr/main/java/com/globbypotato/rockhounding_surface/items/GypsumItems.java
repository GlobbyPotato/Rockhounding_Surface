package com.globbypotato.rockhounding_surface.items;

import java.util.List;
import java.util.Random;

import com.globbypotato.rockhounding_surface.enums.EnumGypsumItems;
import com.globbypotato.rockhounding_surface.integration.SupportUtils;

import net.minecraft.block.BlockGrass;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GypsumItems extends BaseArray {
	public String[] array;
	Random rand = new Random();
	private int successRate = 4;

	public GypsumItems(String name, String[] array) {
		super(name, array);
		this.array = array;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void getSubItems(Item itemIn, CreativeTabs tab, List subItems) {
		for(int i = 0; i < array.length; i++){
			subItems.add(new ItemStack(itemIn, 1, i));
		}
	}

	@Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ){
        if (!playerIn.canPlayerEdit(pos.offset(facing), facing, stack)){
            return EnumActionResult.FAIL;
        }else{
            if (!worldIn.isRemote){
	            if(stack.getItemDamage() == 1){
	                if(applyBonemeal(stack, worldIn, pos, playerIn)){
	                    if (!worldIn.isRemote){
	                        worldIn.playEvent(2005, pos, 0);
	                    }
	                    return EnumActionResult.SUCCESS;
	                }
	            }
	            if(stack.getItemDamage() == 2){
	                improveSoil(worldIn, pos, facing, hitX, hitY, hitZ, 0, playerIn, stack);
	        		decreseStack(playerIn, stack);
	                return EnumActionResult.SUCCESS;
	            }
	            if(stack.getItemDamage() == 3){
	            	amendSoil(worldIn, pos, facing, hitX, hitY, hitZ, 0, playerIn, stack);
	        		decreseStack(playerIn, stack);
	                return EnumActionResult.SUCCESS;
	            }
	            if(stack.getItemDamage() == 4){
	            	compostSoil(worldIn, pos, facing, hitX, hitY, hitZ, 0, playerIn, stack);
	        		decreseStack(playerIn, stack);
	                return EnumActionResult.SUCCESS;
	            }
            }
        }
		return EnumActionResult.PASS;
    }

    private void compostSoil(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int i, EntityPlayer playerIn, ItemStack stack) {
    	for(int y = -1; y <= 1; y++){
        	for(int x = -2; x <= 2; x++){
            	for(int z = -2; z <= 2; z++){
            		BlockPos dirtPos = new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z);
            		IBlockState dirtState = world.getBlockState(dirtPos);
			    	if(getGravelType(dirtState, dirtPos) && rand.nextInt(successRate) == 0){
			    		world.setBlockState(dirtPos, Blocks.DIRT.getStateForPlacement(world, dirtPos, facing, hitX, hitY, hitZ, 1, playerIn, stack) );
			    	}
            	}
        	}
    	}
	}

	private void amendSoil(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int i, EntityPlayer playerIn, ItemStack stack) {
    	for(int y = -1; y <= 1; y++){
        	for(int x = -2; x <= 2; x++){
            	for(int z = -2; z <= 2; z++){
            		BlockPos dirtPos = new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z);
            		IBlockState dirtState = world.getBlockState(dirtPos);
			    	if(getGrassType(dirtState, dirtPos) && rand.nextInt(successRate) == 0){
			    		world.setBlockState(dirtPos, Blocks.DIRT.getStateForPlacement(world, dirtPos, facing, hitX, hitY, hitZ, 2, playerIn, stack) );
			    	}
            	}
        	}
    	}
	}

	private void improveSoil(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int i, EntityPlayer playerIn, ItemStack stack) {
    	for(int y = -1; y <= 1; y++){
        	for(int x = -2; x <= 2; x++){
            	for(int z = -2; z <= 2; z++){
            		BlockPos dirtPos = new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z);
            		IBlockState dirtState = world.getBlockState(dirtPos);
            		//vanilla soil
            		if(getDirtType(dirtState, dirtPos, 1) && rand.nextInt(successRate) == 0){
            			world.setBlockState(dirtPos, Blocks.DIRT.getStateForPlacement(world, dirtPos, facing, hitX, hitY, hitZ, 0, playerIn, stack) );
            		}else if(getDirtType(dirtState, dirtPos, 0) && rand.nextInt(successRate) == 0){
            			world.setBlockState(dirtPos, Blocks.GRASS.getDefaultState());
               		//bop soil
            		}else if(SupportUtils.bopLoaded()){
                		if(getBopGrassType(dirtState, dirtPos) && rand.nextInt(successRate) == 0){
                			world.setBlockState(dirtPos, Blocks.GRASS.getDefaultState());
                		}else{
	                		if(getBopCoarseType(dirtState, dirtPos) && rand.nextInt(successRate) == 0){
	                			int meta = world.getBlockState(dirtPos).getBlock().getMetaFromState(dirtState) - 8;
	                			world.setBlockState(dirtPos, SupportUtils.dirt().getStateForPlacement(world, dirtPos, facing, hitX, hitY, hitZ, meta, playerIn, stack) );
	                		}else if(getBopDirtType(dirtState, dirtPos) && rand.nextInt(successRate) == 0){
	                			int meta = world.getBlockState(dirtPos).getBlock().getMetaFromState(dirtState) + 2;
	                			world.setBlockState(dirtPos, SupportUtils.grass().getStateForPlacement(world, dirtPos, facing, hitX, hitY, hitZ, meta, playerIn, stack) );
	                		}
                		}
            		}
            	}
        	}
    	}
	}

	private void decreseStack(EntityPlayer player, ItemStack stack) {
		if(!player.capabilities.isCreativeMode){
            --stack.stackSize;
			if(stack.stackSize <= 0){stack = null;}
		}
	}

	private boolean getBopGrassType(IBlockState dirtState, BlockPos dirtPos) {
		return dirtState.getBlock() == SupportUtils.grass() && (dirtState.getBlock().getMetaFromState(dirtState) >= 2 && dirtState.getBlock().getMetaFromState(dirtState) <= 4);
	}
	private boolean getGravelType(IBlockState dirtState, BlockPos dirtPos) {
		return dirtState.getBlock() == Blocks.GRAVEL;
	}
	private boolean getGrassType(IBlockState dirtState, BlockPos dirtPos) {
		return dirtState.getBlock() instanceof BlockGrass;
	}
	private boolean getBopCoarseType(IBlockState dirtState, BlockPos dirtPos) {
		return dirtState.getBlock() == SupportUtils.dirt() && dirtState.getBlock().getMetaFromState(dirtState) >= 8;
	}
	private boolean getBopDirtType(IBlockState dirtState, BlockPos dirtPos) {
		return dirtState.getBlock() == SupportUtils.dirt() && dirtState.getBlock().getMetaFromState(dirtState) < 8;
	}
	private boolean getDirtType(IBlockState dirtState, BlockPos dirtPos, int i) {
		return dirtState.getBlock() == Blocks.DIRT && dirtState.getBlock().getMetaFromState(dirtState) == i;
	}

	public static boolean applyBonemeal(ItemStack stack, World worldIn, BlockPos target, EntityPlayer player){
        IBlockState iblockstate = worldIn.getBlockState(target);
        int hook = net.minecraftforge.event.ForgeEventFactory.onApplyBonemeal(player, worldIn, target, iblockstate, stack);
        if (hook != 0) return hook > 0;
        if (iblockstate.getBlock() instanceof IGrowable){
            IGrowable igrowable = (IGrowable)iblockstate.getBlock();
            if (igrowable.canGrow(worldIn, target, iblockstate, worldIn.isRemote)){
                if (!worldIn.isRemote){
                    if (igrowable.canUseBonemeal(worldIn, worldIn.rand, target, iblockstate)){
                        igrowable.grow(worldIn, worldIn.rand, target, iblockstate);
                    }
                    --stack.stackSize;
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
                worldIn.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, (double)((float)pos.getX() + itemRand.nextFloat()), (double)pos.getY() + (double)itemRand.nextFloat() * iblockstate.getBoundingBox(worldIn, pos).maxY, (double)((float)pos.getZ() + itemRand.nextFloat()), d0, d1, d2, new int[0]);
            }
        }else{
            for (int i1 = 0; i1 < amount; ++i1){
                double d0 = itemRand.nextGaussian() * 0.02D;
                double d1 = itemRand.nextGaussian() * 0.02D;
                double d2 = itemRand.nextGaussian() * 0.02D;
                worldIn.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, (double)((float)pos.getX() + itemRand.nextFloat()), (double)pos.getY() + (double)itemRand.nextFloat() * 1.0f, (double)((float)pos.getZ() + itemRand.nextFloat()), d0, d1, d2, new int[0]);
            }
        }
    }

    @SideOnly(Side.CLIENT)
	@Override
    public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean held) {
		if(SupportUtils.rhChemistryLoaded() && itemstack.getItemDamage() == EnumGypsumItems.GYPSUM.ordinal()){
			list.add(TextFormatting.DARK_GRAY + "Category: " + TextFormatting.YELLOW + "Sulfate");
			list.add(TextFormatting.DARK_GRAY + "Calcium: " + TextFormatting.WHITE + "23%");
			list.add(TextFormatting.DARK_GRAY + "Sulfur: " + TextFormatting.WHITE + "19%");
		}
    }
}