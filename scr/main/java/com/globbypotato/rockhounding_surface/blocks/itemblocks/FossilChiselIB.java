package com.globbypotato.rockhounding_surface.blocks.itemblocks;

import java.util.List;

import com.globbypotato.rockhounding_core.blocks.itemblocks.BaseMetaIB;
import com.globbypotato.rockhounding_surface.blocks.woods.FossilChisel;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class FossilChiselIB extends BaseMetaIB {
	public FossilChiselIB(Block block, String[] names) {
        super(block, names);
	}

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemstack, EntityPlayer playerIn, List<String> list, boolean advanced){
		if(Block.getBlockFromItem(itemstack.getItem()) instanceof FossilChisel){
			if(itemstack.getItemDamage() ==  0){list.add(TextFormatting.DARK_GRAY + itemstack.getDisplayName() + " Blinds");}
			if(itemstack.getItemDamage() ==  1){list.add(TextFormatting.DARK_GRAY + "Horizontal " + itemstack.getDisplayName() + " in Disarray");}
			if(itemstack.getItemDamage() ==  2){list.add(TextFormatting.DARK_GRAY + "Vertical " + itemstack.getDisplayName() + " in Disarray");}
			if(itemstack.getItemDamage() ==  3){list.add(TextFormatting.DARK_GRAY + "Short " + itemstack.getDisplayName());}
			if(itemstack.getItemDamage() ==  4){list.add(TextFormatting.DARK_GRAY + itemstack.getDisplayName() + " Crate");}
			if(itemstack.getItemDamage() ==  5){list.add(TextFormatting.DARK_GRAY + itemstack.getDisplayName() + " Scaffold");}
			if(itemstack.getItemDamage() ==  6){list.add(TextFormatting.DARK_GRAY + "Fancy " + itemstack.getDisplayName() + " Crate");}
			if(itemstack.getItemDamage() ==  7){list.add(TextFormatting.DARK_GRAY + itemstack.getDisplayName() + " Double Slab");}
			if(itemstack.getItemDamage() ==  8){list.add(TextFormatting.DARK_GRAY + "Fancy " + itemstack.getDisplayName() + " Arrangment");}
			if(itemstack.getItemDamage() ==  9){list.add(TextFormatting.DARK_GRAY + "Large Long " + itemstack.getDisplayName());}
			if(itemstack.getItemDamage() == 10){list.add(TextFormatting.DARK_GRAY + itemstack.getDisplayName() + " Panel");}
			if(itemstack.getItemDamage() == 11){list.add(TextFormatting.DARK_GRAY + itemstack.getDisplayName() + " Parquet");}
			if(itemstack.getItemDamage() == 12){list.add(TextFormatting.DARK_GRAY + "Short " + itemstack.getDisplayName());}
			if(itemstack.getItemDamage() == 13){list.add(TextFormatting.DARK_GRAY + "Vertical " + itemstack.getDisplayName());}
			if(itemstack.getItemDamage() == 14){list.add(TextFormatting.DARK_GRAY + "Vertical Uneven " + itemstack.getDisplayName());}
			if(itemstack.getItemDamage() == 15){list.add(TextFormatting.DARK_GRAY + "Large Vertical " + itemstack.getDisplayName());}
		}
    }
}