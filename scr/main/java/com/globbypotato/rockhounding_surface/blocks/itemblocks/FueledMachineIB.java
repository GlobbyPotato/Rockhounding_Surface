package com.globbypotato.rockhounding_surface.blocks.itemblocks;

import java.util.List;

import com.globbypotato.rockhounding_surface.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class FueledMachineIB extends ItemBlock {
	public FueledMachineIB(Block block) {
        super(block);
	}

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName(stack);
    }

    @Override
	public int getMetadata(int meta){
		return meta;
	}

	@Override
    public void onCreated(ItemStack itemstack, World world, EntityPlayer player) {
		if(itemstack.getItem() == Item.getItemFromBlock(ModBlocks.woodIncubator)){
			setItemNbt(itemstack);
		}
    }

    private static void setItemNbt(ItemStack itemstack) {
    	itemstack.setTagCompound(new NBTTagCompound());
    	itemstack.getTagCompound().setInteger("Fuel", 0);
    	itemstack.getTagCompound().setInteger("Energy", 0);
	}

	@Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemstack, EntityPlayer player, List<String> tooltip, boolean held) {
		if(itemstack.getItem() == Item.getItemFromBlock(ModBlocks.woodIncubator)){
	        if(itemstack.hasTagCompound()) {
	        	int fuel = itemstack.getTagCompound().getInteger("Fuel");
	        	int energy = itemstack.getTagCompound().getInteger("Energy");
	        	if(fuel > 0){
	        		tooltip.add(TextFormatting.DARK_GRAY + "Stored Fuel: " + TextFormatting.YELLOW + fuel + " ticks");
	        	}
	        	if(energy > 0){
	        		tooltip.add(TextFormatting.DARK_GRAY + "Stored Energy: " + TextFormatting.RED + energy + " RF");
	        	}
	    		if(itemstack.getTagCompound().hasKey("Solvent")){
	    			FluidStack fluid = FluidStack.loadFluidStackFromNBT(itemstack.getTagCompound().getCompoundTag("Solvent"));
	    			if(fluid != null && fluid.amount > 0){
	            		tooltip.add(TextFormatting.DARK_GRAY + "Stored Fluid: " + TextFormatting.GOLD + fluid.getLocalizedName() + " (" + fluid.amount + " mB)");
	    			}
	    		}
	        }else{
	        	setItemNbt(itemstack);
	        }
		}
    }

}