package com.globbypotato.rockhounding_surface.blocks.itemblocks;

import java.util.List;

import javax.annotation.Nullable;

import com.globbypotato.rockhounding_core.blocks.itemblocks.BaseMetaIB;
import com.globbypotato.rockhounding_surface.blocks.fossil_wood.FossilChiseled;

import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class FossilChiselIB extends BaseMetaIB {
	public FossilChiselIB(Block block, String[] names) {
        super(block, names);
	}

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemstack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flag){
		if(Block.getBlockFromItem(itemstack.getItem()) instanceof FossilChiseled){
			if(itemstack.getItemDamage() ==  0){tooltip.add(TextFormatting.DARK_GRAY + itemstack.getDisplayName() + " Blinds");}
			if(itemstack.getItemDamage() ==  1){tooltip.add(TextFormatting.DARK_GRAY + "Horizontal " + itemstack.getDisplayName() + " in Disarray");}
			if(itemstack.getItemDamage() ==  2){tooltip.add(TextFormatting.DARK_GRAY + "Vertical " + itemstack.getDisplayName() + " in Disarray");}
			if(itemstack.getItemDamage() ==  3){tooltip.add(TextFormatting.DARK_GRAY + "Short " + itemstack.getDisplayName());}
			if(itemstack.getItemDamage() ==  4){tooltip.add(TextFormatting.DARK_GRAY + itemstack.getDisplayName() + " Crate");}
			if(itemstack.getItemDamage() ==  5){tooltip.add(TextFormatting.DARK_GRAY + itemstack.getDisplayName() + " Scaffold");}
			if(itemstack.getItemDamage() ==  6){tooltip.add(TextFormatting.DARK_GRAY + "Fancy " + itemstack.getDisplayName() + " Crate");}
			if(itemstack.getItemDamage() ==  7){tooltip.add(TextFormatting.DARK_GRAY + itemstack.getDisplayName() + " Double Slab");}
			if(itemstack.getItemDamage() ==  8){tooltip.add(TextFormatting.DARK_GRAY + "Fancy " + itemstack.getDisplayName() + " Arrangment");}
			if(itemstack.getItemDamage() ==  9){tooltip.add(TextFormatting.DARK_GRAY + "Large Long " + itemstack.getDisplayName());}
			if(itemstack.getItemDamage() == 10){tooltip.add(TextFormatting.DARK_GRAY + itemstack.getDisplayName() + " Panel");}
			if(itemstack.getItemDamage() == 11){tooltip.add(TextFormatting.DARK_GRAY + itemstack.getDisplayName() + " Parquet");}
			if(itemstack.getItemDamage() == 12){tooltip.add(TextFormatting.DARK_GRAY + "Short " + itemstack.getDisplayName());}
			if(itemstack.getItemDamage() == 13){tooltip.add(TextFormatting.DARK_GRAY + "Vertical " + itemstack.getDisplayName());}
			if(itemstack.getItemDamage() == 14){tooltip.add(TextFormatting.DARK_GRAY + "Vertical Uneven " + itemstack.getDisplayName());}
			if(itemstack.getItemDamage() == 15){tooltip.add(TextFormatting.DARK_GRAY + "Large Vertical " + itemstack.getDisplayName());}
		}
    }
}