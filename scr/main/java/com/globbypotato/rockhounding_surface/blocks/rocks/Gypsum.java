package com.globbypotato.rockhounding_surface.blocks.rocks;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.globbypotato.rockhounding_core.blocks.itemblocks.BaseMetaIB;
import com.globbypotato.rockhounding_surface.ModBlocks;
import com.globbypotato.rockhounding_surface.blocks.BlockIO;
import com.globbypotato.rockhounding_surface.enums.EnumGypsumBlocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Gypsum extends BlockIO {
	public static final PropertyEnum VARIANT = PropertyEnum.create("variant", EnumGypsumBlocks.class);

    public Gypsum(Material material, String[] array, float hardness, float resistance, String name, SoundType stepSound) {
		super(material, array, hardness, resistance, name, stepSound);
        GameRegistry.register(new BaseMetaIB(this, EnumGypsumBlocks.getNames()).setRegistryName(name));
        setHarvestLevel("pickaxe", 0);
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumGypsumBlocks.values()[0]));
    }

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(VARIANT, EnumGypsumBlocks.values()[meta]);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumGypsumBlocks)state.getValue(VARIANT)).ordinal();
	}

	@Override
	public BlockStateContainer createBlockState(){
		return new BlockStateContainer(this, new IProperty[] { VARIANT });
	}

    @Override
    public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, @Nullable ItemStack stack){
        player.addStat(StatList.getBlockStats(this));
        player.addExhaustion(0.025F);
        java.util.List<ItemStack> items = new java.util.ArrayList<ItemStack>();
        ItemStack itemstack;
        if(getMetaFromState(state) == EnumGypsumBlocks.DOUBLE.ordinal()){
        	itemstack = new ItemStack(ModBlocks.gypsumSlabsLo,2,1);
        }else{
        	itemstack = this.createStackedBlock(state);
        }
        if (itemstack != null){ items.add(itemstack); }
        net.minecraftforge.event.ForgeEventFactory.fireBlockHarvesting(items, worldIn, pos, state, 0, 1.0f, true, player);
        for (ItemStack item : items){ spawnAsEntity(worldIn, pos, item); }
    }

}