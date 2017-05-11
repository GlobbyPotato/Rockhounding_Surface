package com.globbypotato.rockhounding_surface.items;

import javax.annotation.Nullable;

import com.globbypotato.rockhounding_surface.ModItems;
import com.globbypotato.rockhounding_surface.handler.Reference;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModSoup extends ItemFood {
	private EnumAction action;
	private int duration;
	private int heal;
	float saturation;
	
	public ModSoup(int heal, float saturation, boolean isWolfFood, EnumAction action, int duration, String name) {
		super(heal, saturation, isWolfFood);
	    setRegistryName(name);
        setUnlocalizedName(this.getRegistryName().toString());
        GameRegistry.register(this);
		setCreativeTab(Reference.RockhoundingSurface);
		this.action = action;
		this.duration = duration;
		this.saturation = saturation;
		this.heal = heal;
	}

	@Override
    public EnumAction getItemUseAction(ItemStack stack){
        return this.action;
    }

	@Override
    public int getMaxItemUseDuration(ItemStack stack){
        return this.duration;
    }

	@Override
    public int getHealAmount(ItemStack stack){
        return this.heal;
    }

	@Override
    public float getSaturationModifier(ItemStack stack){
        return this.saturation;
    }

    @Nullable
	@Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving){
        super.onItemUseFinish(stack, worldIn, entityLiving);
        return new ItemStack(Items.BOWL);
    }

}