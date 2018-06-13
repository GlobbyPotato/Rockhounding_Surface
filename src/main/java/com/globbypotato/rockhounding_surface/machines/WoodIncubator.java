package com.globbypotato.rockhounding_surface.machines;

import javax.annotation.Nullable;

import com.globbypotato.rockhounding_core.enums.EnumFluidNbt;
import com.globbypotato.rockhounding_core.utils.MachinesUtils;
import com.globbypotato.rockhounding_surface.handler.GuiHandler;
import com.globbypotato.rockhounding_surface.handler.Reference;
import com.globbypotato.rockhounding_surface.machines.tileentity.TileEntityWoodIncubator;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

public class WoodIncubator extends MachineIO{
    public WoodIncubator(String name){
        super(Reference.MODID, name, Material.IRON, TileEntityWoodIncubator.class, GuiHandler.woodIncubatorID, 1.0F);
    	setHardness(3.0F); setResistance(5.0F);	
		setHarvestLevel("pickaxe", 0);
		setCreativeTab(null);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack){
    	super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing()), 2);
		if(stack.hasTagCompound()){
        	TileEntity te = worldIn.getTileEntity(pos);;
			if(te != null){
		    	MachinesUtils.restoreMachineNBT(stack, te, -1);

	        	restoreIncubatorNBT(stack, te);
			}
		}
    }

    @Override
    public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, @Nullable ItemStack stack){
        player.addStat(StatList.getBlockStats(this));
        player.addExhaustion(0.025F);
        java.util.List<ItemStack> items = new java.util.ArrayList<ItemStack>();
        ItemStack itemstack = new ItemStack(Item.getItemFromBlock(this));
        if(te != null){
        	MachinesUtils.addMachineNbt(itemstack, te);

        	if(te instanceof TileEntityWoodIncubator){
	        	addIncubatorNbt(itemstack, te);
	        }
        }
        if (!itemstack.isEmpty()){ items.add(itemstack); }
        net.minecraftforge.event.ForgeEventFactory.fireBlockHarvesting(items, worldIn, pos, state, 0, 1.0f, true, player);
        for (ItemStack item : items){ spawnAsEntity(worldIn, pos, item); }
    }

    private static void restoreIncubatorNBT(ItemStack stack, TileEntity te){
		TileEntityWoodIncubator incubator = ((TileEntityWoodIncubator)te);
		if(stack.getTagCompound().hasKey(EnumFluidNbt.SOLVENT.nameTag())){
			incubator.inputTank.setFluid(FluidStack.loadFluidStackFromNBT(stack.getTagCompound().getCompoundTag(EnumFluidNbt.SOLVENT.nameTag())));
		}
    }

	private static void addIncubatorNbt(ItemStack itemstack, TileEntity tileentity) {
		TileEntityWoodIncubator incubator = (TileEntityWoodIncubator) tileentity; 
		NBTTagCompound solvent = new NBTTagCompound(); 
		if(incubator.inputTank.getFluid() != null){
			incubator.inputTank.getFluid().writeToNBT(solvent);
			itemstack.getTagCompound().setTag(EnumFluidNbt.SOLVENT.nameTag(), solvent);
		}
	}
}