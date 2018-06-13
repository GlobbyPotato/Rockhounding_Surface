package com.globbypotato.rockhounding_surface.utils;

import java.util.List;

import com.globbypotato.rockhounding_surface.machines.recipe.CompostBinRecipe;
import com.globbypotato.rockhounding_surface.machines.recipe.MachineRecipes;
import com.globbypotato.rockhounding_surface.machines.recipe.VivariumRecipe;
import com.globbypotato.rockhounding_surface.machines.recipe.WoodIncubatorRecipe;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.event.FMLInterModComms.IMCMessage;
import rockhounding.api.IReciperBase;

public class IMCUtils extends IReciperBase{
	static ItemStack input;
	static ItemStack solute;
	static ItemStack output;
	static FluidStack solvent;

	public static void extraRecipes(List<IMCMessage> messages) {
		for(IMCMessage message : messages) {
    		if(message.isNBTMessage()){
				try {
		    		NBTTagCompound tag = message.getNBTValue();
		    		//WOOD INCUBATOR
	    			if(message.key.equalsIgnoreCase(add_wood_incubator_key)){
		        		if(tag.hasKey(tagInput)){
		        			input = new ItemStack(tag.getCompoundTag(tagInput));
		        		}
		        		if(tag.hasKey(tagPattern)){
		        			solute = new ItemStack(tag.getCompoundTag(tagPattern));
		        		}
		        		if(tag.hasKey(tagSolvent)){
		        			solvent = FluidStack.loadFluidStackFromNBT(tag.getCompoundTag(tagSolvent));
		        		}
		        		if(tag.hasKey(tagOutput)){
		        			output = new ItemStack(tag.getCompoundTag(tagOutput));
		        		}
	        			MachineRecipes.woodIncubatorRecipes.add(new WoodIncubatorRecipe(input, solute, solvent, output));
					}
	    			//COMPOST BIN
	    			if(message.key.equalsIgnoreCase(add_compost_bin_key)){
		        		if(tag.hasKey(tagInput)){
		        			input = new ItemStack(tag.getCompoundTag(tagInput));
		        		}
		        		int type = 0;
		        		if(tag.hasKey(tagPattern)){
		        			type = tag.getInteger(tagPattern);
		        		}
		        		int value = 0;
		        		if(tag.hasKey(tagWeights)){
		        			value = tag.getInteger(tagWeights);
		        		}
		        		
		        		if(!input.isEmpty()){
		        			MachineRecipes.compostRecipes.add(new CompostBinRecipe(input, type, value));
		        		}
					}
		    		//VIVARIUM
	    			if(message.key.equalsIgnoreCase(add_vivarium_key)){
		        		if(tag.hasKey(tagInput)){
		        			input = new ItemStack(tag.getCompoundTag(tagInput));
		        		}
		        		if(tag.hasKey(tagOutput)){
		        			output = new ItemStack(tag.getCompoundTag(tagOutput));
		        		}
		        		int produceRate = 2000;
		        		if(tag.hasKey(tagWeights)){
		        			produceRate = tag.getInteger(tagWeights);
		        		}
		        		int consumeRate = 20;
		        		if(tag.hasKey(tagWaste)){
		        			consumeRate = tag.getInteger(tagWaste);
		        		}

	        			MachineRecipes.vivariumRecipes.add(new VivariumRecipe(input, output, produceRate, consumeRate));
					}
				}catch (Exception e){
					e.printStackTrace();
				}
    		}
		}
	}
}