package com.globbypotato.rockhounding_surface.machines;

import javax.annotation.Nullable;

import com.globbypotato.rockhounding_core.machines.BaseMachine;
import com.globbypotato.rockhounding_core.machines.tileentity.IFluidHandlingTile;
import com.globbypotato.rockhounding_core.utils.CoreUtils;
import com.globbypotato.rockhounding_surface.Rhsurface;
import com.globbypotato.rockhounding_surface.handler.Reference;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class MachineIO extends BaseMachine{
    float top;

	public MachineIO(String modid, String name, Material material, Class<? extends TileEntity> tileClass, int guiID, float top) {
		super(modid, name, material, tileClass, guiID);
		this.top = top;
		setCreativeTab(Reference.RockhoundingSurface);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
	}

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos){
        return new AxisAlignedBB(0.0f, 0.0f, 0.0f, 1.0f, this.top, 1.0f);
    }

	@Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos){
        return new AxisAlignedBB(0.0f, 0.0f, 0.0f, 1.0f, this.top, 1.0f);
    }

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ){
		if (!world.isRemote) {
			if(world.getTileEntity(pos) instanceof IFluidHandlingTile){
				if(!player.getHeldItemMainhand().isEmpty()){
					if (CoreUtils.isBucketType(player.getHeldItemMainhand())){
						((IFluidHandlingTile)world.getTileEntity(pos)).interactWithFluidHandler(player, hand, world, pos, facing);
						return true;
					}
				}
			}
			player.openGui(Rhsurface.instance, this.guiID, world, pos.getX(), pos.getY(), pos.getZ());
		}
		return true;
	}

    @Override
    public BlockStateContainer createBlockState(){
        return new BlockStateContainer(this, new IProperty[] {FACING});
    }

}