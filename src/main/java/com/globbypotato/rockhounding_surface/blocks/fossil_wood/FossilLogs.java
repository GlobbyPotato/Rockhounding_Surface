package com.globbypotato.rockhounding_surface.blocks.fossil_wood;

import com.globbypotato.rockhounding_surface.blocks.io.LogIO;
import com.globbypotato.rockhounding_surface.enums.EnumFossilLogs;

import net.minecraft.block.BlockLog;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;

public class FossilLogs extends LogIO {
    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", EnumFossilLogs.class);

    public FossilLogs(String name){
        super(name);
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumFossilLogs.values()[0]).withProperty(LOG_AXIS, BlockLog.EnumAxis.Y));
    }

    @Override
    public IBlockState getStateFromMeta(int meta){
        IBlockState iblockstate = this.getDefaultState().withProperty(VARIANT, EnumFossilLogs.values()[(meta & 3) % 4]);
        switch (meta & 12){
            case 0:  iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.Y); break;
            case 4:  iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.X); break;
            case 8:  iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.Z); break;
            default: iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.NONE); 
        }
        return iblockstate;
    }

    @Override
    public int getMetaFromState(IBlockState state){
        int i = 0;
        i = i | ((EnumFossilLogs)state.getValue(VARIANT)).ordinal();
        switch (state.getValue(LOG_AXIS)){
            case X: i |= 4; break;
            case Z: i |= 8; break;
            case NONE: i |= 12;
		default:
			break;
        }
        return i;
    }

    @Override
    public BlockStateContainer createBlockState(){
        return new BlockStateContainer(this, new IProperty[] {VARIANT, LOG_AXIS});
    }

    @Override
    public int damageDropped(IBlockState state){
        return ((EnumFossilLogs)state.getValue(VARIANT)).ordinal();
    }
}