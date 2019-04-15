package com.globbypotato.rockhounding_surface.enums;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public enum EnumFluid {

//GENERIC
	ACIDIC_WATER("Acidic Water", "acidic_water"),
	AGING_BATH("Aging Bath", "aging_bath"),
	CASTING_BATH("Casting Bath", "casting_bath");

	private String name;
	private String fluidName;

	EnumFluid(String name, String fluidName){
		this.name = name;
		this.fluidName = fluidName;
	}

	public String getName(){
		return this.name;
	}

	public String getFluidName(){
		return this.fluidName;
	}

	public static Fluid pickFluid(EnumFluid fluid){
		return FluidRegistry.getFluid(fluid.getFluidName());
	}
}