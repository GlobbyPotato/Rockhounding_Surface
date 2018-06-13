package com.globbypotato.rockhounding_surface.compat.top;

import javax.annotation.Nullable;

import com.globbypotato.rockhounding_surface.handler.Reference;
import com.globbypotato.rockhounding_surface.machines.tileentity.TileEntityCompostBin;
import com.globbypotato.rockhounding_surface.machines.tileentity.TileEntityTruffleAuction;
import com.globbypotato.rockhounding_surface.machines.tileentity.TileEntityWoodIncubator;
import com.google.common.base.Function;

import mcjty.theoneprobe.api.IProbeConfig;
import mcjty.theoneprobe.api.IProbeConfigProvider;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeHitEntityData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.IProbeInfoProvider;
import mcjty.theoneprobe.api.ITheOneProbe;
import mcjty.theoneprobe.api.NumberFormat;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TopMachines implements IProbeInfoProvider{

	@Override
	public String getID() {
        return Reference.MODID + ":" + "incubator";
	}

	public static class EnergyInfo implements IProbeConfigProvider{
		@Override
		public void getProbeConfig(IProbeConfig config, EntityPlayer player, World world, Entity entity, IProbeHitEntityData data) {}

		@Override
		public void getProbeConfig(IProbeConfig config, EntityPlayer player, World world, IBlockState blockState, IProbeHitData data) {
	        final TileEntity te = world.getTileEntity(data.getPos());
			if( te instanceof TileEntityWoodIncubator || 
				te instanceof TileEntityTruffleAuction || 
				te instanceof TileEntityCompostBin
			){
				config.setRFMode(0);
			}			
		}
	}

	@Override
	public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world, IBlockState blockState, IProbeHitData data) {
        final TileEntity te = world.getTileEntity(data.getPos());
		if(te != null){
			if(te instanceof TileEntityCompostBin){
				TileEntityCompostBin tank = (TileEntityCompostBin)te;
				probeInfo.progress(tank.getCompostAmount(), tank.getCompostMax(), probeInfo.defaultProgressStyle()
																	.suffix(" unit/s")
																	.filledColor(0xFF457343)
																	.alternateFilledColor(0xFF667343)
																	.borderColor(0x000000)
																	.numberFormat(NumberFormat.FULL));
			}
			if(te instanceof TileEntityWoodIncubator){
				TileEntityWoodIncubator tank = (TileEntityWoodIncubator)te;
				probeInfo.progress(tank.inputTank.getFluidAmount(), tank.inputTank.getCapacity(), probeInfo.defaultProgressStyle()
																				.suffix(" mB")
																				.filledColor(0xFFB1FF08)
																				.alternateFilledColor(0xFFF0FF08)
																				.borderColor(0x000000)
																				.numberFormat(NumberFormat.FULL));
			}
		}
	}

    public static class getTOP implements Function<ITheOneProbe, Void> {
        public static ITheOneProbe top;

        @Nullable
        @Override
        public Void apply (ITheOneProbe theOneProbe) {

        	top = theOneProbe;
            top.registerProvider(new TopMachines());
            top.registerProbeConfigProvider(new EnergyInfo());
            return null;
        }
    }

}