package com.globbypotato.rockhounding_surface.blocks.io;

import com.globbypotato.rockhounding_surface.handler.Reference;

import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class GateIO extends BlockFenceGate {

	public GateIO(String name, EnumType plank, SoundType soundtype, float hardness, float resistance) {
		super(plank);
		setRegistryName(name);
		setUnlocalizedName(getRegistryName().toString());
		setCreativeTab(Reference.RockhoundingSurface);
		setHardness(hardness); setResistance(resistance); setSoundType(soundtype);
        this.setDefaultState(this.blockState.getBaseState().withProperty(OPEN, Boolean.valueOf(false)).withProperty(POWERED, Boolean.valueOf(false)).withProperty(IN_WALL, Boolean.valueOf(false)));
	}

    @Override
    public BlockStateContainer createBlockState(){
        return new BlockStateContainer(this, new IProperty[] {FACING, OPEN, POWERED, IN_WALL});
    }

    @Override
	public boolean isWood(IBlockAccess world, BlockPos pos){
        return false;
    }

}