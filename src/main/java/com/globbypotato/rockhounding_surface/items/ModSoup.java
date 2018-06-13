package com.globbypotato.rockhounding_surface.items;

import javax.annotation.Nullable;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ModSoup extends ModFood {
	
	public ModSoup(int heal, float saturation, boolean isWolfFood, EnumAction action, int duration, String name) {
		super(heal, saturation, isWolfFood, action, duration, name);
		setMaxStackSize(1);
	}

    @Nullable
	@Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving){
        super.onItemUseFinish(stack, worldIn, entityLiving);
        return new ItemStack(Items.BOWL);
    }

}