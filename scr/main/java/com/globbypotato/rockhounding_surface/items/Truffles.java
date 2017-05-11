package com.globbypotato.rockhounding_surface.items;

import java.util.List;

import com.globbypotato.rockhounding_surface.enums.EnumTruffles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Truffles extends BaseArray {

	public Truffles(String name, String[] array) {
		super(name, array);
	}

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemstack, EntityPlayer playerIn, List<String> list, boolean advanced){
		if(itemstack.getItemDamage() ==  EnumTruffles.BLACK.ordinal()){list.add(TextFormatting.DARK_GRAY + "Discovery Chance: " + TextFormatting.YELLOW + "54%");}
		if(itemstack.getItemDamage() ==  EnumTruffles.WINTER.ordinal()){list.add(TextFormatting.DARK_GRAY + "Discovery Chance: " + TextFormatting.YELLOW + "30%");}
		if(itemstack.getItemDamage() ==  EnumTruffles.SUMMER.ordinal()){list.add(TextFormatting.DARK_GRAY + "Discovery Chance: " + TextFormatting.YELLOW + "10%");}
		if(itemstack.getItemDamage() ==  EnumTruffles.GIANT.ordinal()){list.add(TextFormatting.DARK_GRAY + "Discovery Chance: " + TextFormatting.YELLOW + "5%");}
		if(itemstack.getItemDamage() ==  EnumTruffles.WHITE.ordinal()){list.add(TextFormatting.DARK_GRAY + "Discovery Chance: " + TextFormatting.YELLOW + "1%");}
    }
}