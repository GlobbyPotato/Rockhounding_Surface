package com.globbypotato.rockhounding_surface.blocks.plants;

import java.util.Random;

import javax.annotation.Nullable;

import com.globbypotato.rockhounding_surface.enums.EnumBushes;
import com.globbypotato.rockhounding_surface.handler.Reference;

import net.minecraft.block.BlockBush;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BaseBush extends BlockBush {
	public static final PropertyEnum VARIANT = PropertyEnum.create("variant", EnumBushes.class);
    protected static final AxisAlignedBB BUSH_AABB = new AxisAlignedBB(0.2D, 0.0D, 0.2D, 0.8D, 1.0D, 0.8D);

	public BaseBush(String name){
        super();
		setRegistryName(name);
		setUnlocalizedName(getRegistryName().toString());
		GameRegistry.register(this);
		setCreativeTab(Reference.RockhoundingSurface);
        this.setTickRandomly(true);
        this.disableStats();
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos){
        return BUSH_AABB;
    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos){
        return NULL_AABB;
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos){
        return EnumPlantType.Desert;
    }

	@Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune){
		return state.getBlock() instanceof GypsumBushHi ? null : Item.getItemFromBlock(this);
	}

	@Override
    public int damageDropped(IBlockState state){
        return ((EnumBushes)state.getValue(VARIANT)).ordinal();
    }

    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn){
    	if(state.getBlock().getMetaFromState(state) == 3){
    		entityIn.attackEntityFrom(DamageSource.cactus, 1.5F);
    	}
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player){
        return new ItemStack(state.getBlock(), 1, state.getBlock().getMetaFromState(state));
    }

}