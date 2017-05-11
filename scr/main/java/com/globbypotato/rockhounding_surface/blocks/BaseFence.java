package com.globbypotato.rockhounding_surface.blocks;

import com.globbypotato.rockhounding_surface.handler.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BaseFence extends BlockFence {
	public BaseFence(String name, Material material, MapColor mapcolor, SoundType soundtype, float hardness, float resistance) {
		super(material, mapcolor);
		setRegistryName(name);
		setUnlocalizedName(getRegistryName().toString());
		GameRegistry.register(this);
		GameRegistry.register(new ItemBlock(this).setRegistryName(name));
		setCreativeTab(Reference.RockhoundingSurface);
		setHardness(hardness); setResistance(resistance); setSoundType(soundtype);
        this.setDefaultState(this.blockState.getBaseState().withProperty(NORTH, Boolean.valueOf(false)).withProperty(EAST, Boolean.valueOf(false)).withProperty(SOUTH, Boolean.valueOf(false)).withProperty(WEST, Boolean.valueOf(false)));
	}

	@Override
	public boolean canConnectTo(IBlockAccess worldIn, BlockPos pos){
		IBlockState iblockstate = worldIn.getBlockState(pos);
		Block block = iblockstate.getBlock();
		return block == Blocks.BARRIER ? false : ((!(block instanceof BlockFence) || iblockstate.getMaterial() != this.blockMaterial) && !(block instanceof BlockFenceGate) ? (iblockstate.isOpaqueCube() && iblockstate.isFullCube() ? iblockstate.getMaterial() != Material.GOURD : false) : true);
    }

    @Override
    public BlockStateContainer createBlockState(){
        return new BlockStateContainer(this, new IProperty[] {NORTH, EAST, WEST, SOUTH});
    }

}