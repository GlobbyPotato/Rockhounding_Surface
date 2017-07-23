package com.globbypotato.rockhounding_surface.machines;

import javax.annotation.Nullable;

import com.globbypotato.rockhounding_surface.handler.GuiHandler;
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

public class WoodIncubator extends BaseMachine{
    public WoodIncubator(float hardness, float resistance, String name){
        super(name, Material.IRON, TileEntityWoodIncubator.class, GuiHandler.woodIncubatorID);
		setHardness(hardness); setResistance(resistance);	
		setHarvestLevel("pickaxe", 0);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack){
    	super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing()), 2);
		if(stack.hasTagCompound()){
        	TileEntityWoodIncubator te = (TileEntityWoodIncubator) worldIn.getTileEntity(pos);
			if(te != null){
        		if(stack.getTagCompound().hasKey("Solvent")){
        			te.inputTank.setFluid(FluidStack.loadFluidStackFromNBT(stack.getTagCompound().getCompoundTag("Solvent")));
        		}
			}
		}
    }

    @Override
    public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, @Nullable ItemStack stack){
        player.addStat(StatList.getBlockStats(this));
        player.addExhaustion(0.025F);
        java.util.List<ItemStack> items = new java.util.ArrayList<ItemStack>();
        ItemStack itemstack = new ItemStack(Item.getItemFromBlock(this));
        if(te != null && te instanceof TileEntityWoodIncubator){
  			addNbt(itemstack, te);
        }
        if (itemstack != null){ items.add(itemstack); }
        net.minecraftforge.event.ForgeEventFactory.fireBlockHarvesting(items, worldIn, pos, state, 0, 1.0f, true, player);
        for (ItemStack item : items){ spawnAsEntity(worldIn, pos, item); }
    }

	private void addNbt(ItemStack itemstack, TileEntity tileentity) {
		TileEntityWoodIncubator incubator = ((TileEntityWoodIncubator)tileentity);
		itemstack.setTagCompound(new NBTTagCompound());
		addPowerNbt(itemstack, tileentity);
		NBTTagCompound solvent = new NBTTagCompound(); 
		if(incubator.inputTank.getFluid() != null){
			incubator.inputTank.getFluid().writeToNBT(solvent);
			itemstack.getTagCompound().setTag("Solvent", solvent);
		}
	}
}