package com.globbypotato.rockhounding_surface.blocks.white_sands;

import java.util.Random;

import javax.annotation.Nullable;

import com.globbypotato.rockhounding_surface.ModBlocks;
import com.globbypotato.rockhounding_surface.ModItems;
import com.globbypotato.rockhounding_surface.blocks.io.ShrubsIO;
import com.globbypotato.rockhounding_surface.enums.EnumGypsumItems;
import com.globbypotato.rockhounding_surface.enums.EnumShrubs;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GypsumShrubs extends ShrubsIO{
	public static final PropertyEnum VARIANT = PropertyEnum.create("variant", EnumShrubs.class);
	Random rand = new Random();
	public static final AxisAlignedBB BUSH_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
	public static final AxisAlignedBB SHRUB_AABB = new AxisAlignedBB(0.2D, 0.0D, 0.2D, 0.8D, 0.6D, 0.8D);
	public static final AxisAlignedBB SHORT_AABB = new AxisAlignedBB(0.2D, 0.0D, 0.2D, 0.8D, 0.2D, 0.8D);
	public static final AxisAlignedBB SEED_AABB = new AxisAlignedBB(0.28D, 0.0D, 0.28D, 0.72D, 0.24D, 0.72D);

    public GypsumShrubs(String name, Material material, String[] array ){
        super(name, material,  array);
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumShrubs.values()[0]));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items){
    	for(int x = 0; x < this.array.length; x++){
    		if(x != 1 && x != 4 && x != 5 && x != 7 && x != 8){
    			items.add(new ItemStack(this, 1, x));
    		}
    	}
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos){
    	int meta = state.getBlock().getMetaFromState(state);
    	if(meta == 0 || meta == 1){
    		return BUSH_AABB;
    	}else if(meta == 2){
    		return SHORT_AABB;
    	}else if(meta == 3 || meta == 6){
    		return SEED_AABB;
    	}
        return SHRUB_AABB;
    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos){
    	int meta = state.getBlock().getMetaFromState(state);
        return meta == 2 || meta == 3 || meta == 4 || meta == 5 || meta == 6 || meta == 7 || meta == 8 ? NULL_AABB : BUSH_AABB;
    }

    @Override
    public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn){
    	IBlockState state = worldIn.getBlockState(pos);
    	int meta = state.getBlock().getMetaFromState(state);
        if(!worldIn.isRemote){
        	if(isMatureBush(meta)){
        		harvestBerry(worldIn, pos, playerIn, meta);
        	}
        }
    }

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ){
		ItemStack heldItem = playerIn.getHeldItemMainhand();
    	int meta = state.getBlock().getMetaFromState(state);
        if(!worldIn.isRemote){
        	if(isGrowingBush(meta)){
	        	if(!playerIn.getHeldItem(hand).isEmpty() && isFertilizer(heldItem)){
	        		if(this.rand.nextInt(5) == 0){
		        		worldIn.setBlockState(pos, ModBlocks.GYPSUM_CROPS.getStateFromMeta(meta + 1), 2);
		        		playerIn.getHeldItem(hand).shrink(1);
		                return true;
	        		}
	        		playerIn.getHeldItem(hand).shrink(1);
	        	}
        	}
        	if(isMatureBush(meta)){
        		harvestBerry(worldIn, pos, playerIn, meta);
                return true;
        	}
        }
        return false;
    }

    private static boolean isFertilizer(ItemStack heldItem) {
		return heldItem.isItemEqual(new ItemStack(Items.DYE,1,15))
			|| heldItem.isItemEqual(new ItemStack(ModItems.GYPSUM_ITEMS, 1, EnumGypsumItems.BONEMEAL.ordinal()));
	}

	private void harvestBerry(World worldIn, BlockPos pos, EntityPlayer playerIn, int meta) {
        EntityItem entityitem = new EntityItem(worldIn, playerIn.posX, playerIn.posY - 1.0D, playerIn.posZ, getItemDrop(meta));
		worldIn.setBlockState(pos, ModBlocks.GYPSUM_CROPS.getStateFromMeta(meta - 1), 2);
        worldIn.spawnEntity(entityitem);
        if(meta == 8){
        	ItemStack flowerStack = new ItemStack(ModItems.MESQUITE_FLOWER);
            EntityItem entityflower = new EntityItem(worldIn, playerIn.posX, playerIn.posY - 1.0D, playerIn.posZ, flowerStack);
            worldIn.spawnEntity(entityflower);
        }
	}

    private ItemStack getItemDrop(int meta) {
        if(meta == 1){
        	return new ItemStack(ModItems.SOURBERRY, this.rand.nextInt(3) + 1);
        }else if(meta == 5){
        	return new ItemStack(ModItems.PURPLEPEAR, this.rand.nextInt(3) + 1);
        }else if(meta == 8){
        	return new ItemStack(ModItems.MESQUITE, this.rand.nextInt(3) + 1);
        }
		return null;
	}

	@Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand){
        super.updateTick(worldIn, pos, state, rand);
        this.checkAndDropBlock(worldIn, pos, state);
    	int meta = state.getBlock().getMetaFromState(state);
    	//grow seed
    	if (rand.nextInt(16) == 0 && isSeedingBush(state.getBlock().getMetaFromState(state))){
           	worldIn.setBlockState(pos, ModBlocks.GYPSUM_CROPS.getStateFromMeta(meta + 1), 2);
        }
    	//apply fruits
        if (rand.nextInt(16) == 0 && isGrowingBush(state.getBlock().getMetaFromState(state))){
           	worldIn.setBlockState(pos, ModBlocks.GYPSUM_CROPS.getStateFromMeta(meta + 1), 2);
        }
    }

	private static boolean isGrowingBush(int i) {
		return i == 0 || i == 4 || i == 7;
	}

	private static boolean isMatureBush(int i) {
		return i == 1 || i == 5 || i == 8;
	}

	private static boolean isSeedingBush(int i) {
		return i == 3 || i == 6;
	}

	@Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune){
		int meta = state.getBlock().getMetaFromState(state);
		if(meta == 2){
			return null;
		}
		return Item.getItemFromBlock(this);
	}

	@Override
    public int damageDropped(IBlockState state){
		int meta = state.getBlock().getMetaFromState(state);
		if(meta == 1){
			return 0;
		}else if(meta == 4 || meta == 5){
			return 3;
		}else if(meta == 7 || meta == 8){
			return 6;
		}
    	return getMetaFromState(state);
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player){
    	int meta = state.getBlock().getMetaFromState(state);
        if(meta == 1){
        	return new ItemStack(state.getBlock(), 1, 0);
        }else if(meta == 4 || meta == 5){
        	return new ItemStack(state.getBlock(), 1, 3);
        }else if(meta == 7 || meta == 8){
        	return new ItemStack(state.getBlock(), 1, 6);
        }
    	return new ItemStack(state.getBlock(), 1, state.getBlock().getMetaFromState(state));
    }

    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn){
    	int meta = state.getBlock().getMetaFromState(state);
    	if(meta == 3 || meta == 4 || meta == 5){
    		entityIn.attackEntityFrom(DamageSource.CACTUS, 1.5F);
    	}
    }

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(VARIANT, EnumShrubs.values()[meta]);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumShrubs)state.getValue(VARIANT)).ordinal();
	}

	@Override
	public BlockStateContainer createBlockState(){
		return new BlockStateContainer(this, new IProperty[] { VARIANT });
	}
}