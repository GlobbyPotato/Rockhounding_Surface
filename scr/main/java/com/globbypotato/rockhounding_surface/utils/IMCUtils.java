package com.globbypotato.rockhounding_surface.utils;

import java.util.List;

import com.globbypotato.rockhounding_surface.handler.ModRecipes;
import com.globbypotato.rockhounding_surface.machines.recipe.CompostBinRecipe;
import com.globbypotato.rockhounding_surface.machines.recipe.WoodIncubatorRecipe;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.event.FMLInterModComms.IMCMessage;

public class IMCUtils {
	public static String INCUBATOR_KEY = "addToIncubator";
	public static String INCUBATOR_KEY_REMOVER = "removeFromIncubator";
	public static String COMPOST_KEY = "addToCompost";
	public static String COMPOST_KEY_REMOVER = "removeFromCompost";
	static ItemStack input;
	static ItemStack solute;
	static boolean canOredict;
	static FluidStack solvent;
	static ItemStack output;

	public static void extraRecipes(List<IMCMessage> messages) {
		for(IMCMessage message : messages) {
			try {
	    		NBTTagCompound tag = message.getNBTValue();
		    		/**
		    		 * REMOVE RECIPES
		    		 */
	    		if(message.isNBTMessage()){
					if(message.key.equalsIgnoreCase(INCUBATOR_KEY_REMOVER)){
		    			ItemStack wood = null;
		        		if(tag.hasKey("Output")){
		        			wood = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("Output"));
		        		}
		        		if(wood != null){
		        			for(int x = 0; x < ModRecipes.woodIncubatorRecipes.size(); x++){
		        				if(ModRecipes.woodIncubatorRecipes.get(x).getOutput().isItemEqual(wood)){
		        					ModRecipes.woodIncubatorRecipes.remove(x);
		        				}
		        			}
		        		}
					}else if(message.key.equalsIgnoreCase(COMPOST_KEY_REMOVER)){
		    			ItemStack bin = null;
		        		if(tag.hasKey("Input")){
		        			bin = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("Input"));
		        		}
		        		if(bin != null){
		        			for(int x = 0; x < ModRecipes.compostRecipes.size(); x++){
		        				if(ModRecipes.compostRecipes.get(x).getInput().isItemEqual(bin)){
		        					ModRecipes.compostRecipes.remove(x);
		        				}
		        			}
		        		}

		    		/**
		    		 * REMOVE RECIPES
		    		 */
					}else if(message.key.equalsIgnoreCase(INCUBATOR_KEY)){
		        		if(tag.hasKey("Input")){
		        			input = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("Input"));
		        		}
		        		if(tag.hasKey("Solute")){
		        			solute = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("Solute"));
		        		}
		        		if(tag.hasKey("Oredict")){
		        			canOredict = tag.getBoolean("Oredict");
		        		}
		        		if(tag.hasKey("Solvent")){
		        			solvent = FluidStack.loadFluidStackFromNBT(tag.getCompoundTag("Solvent"));
		        		}
		        		if(tag.hasKey("Output")){
		        			output = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("Output"));
		        		}
		        		if(input != null && solute != null && solvent.getFluid() != null && output != null){
		        			ModRecipes.woodIncubatorRecipes.add(new WoodIncubatorRecipe(input, solute, canOredict, solvent, output));
		        		}
					}else if(message.key.equalsIgnoreCase(COMPOST_KEY)){
		        		if(tag.hasKey("Input")){
		        			input = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("Input"));
		        		}
		        		if(tag.hasKey("Oredict")){
		        			canOredict = tag.getBoolean("Oredict");
		        		}
		        		if(input != null){
		        			ModRecipes.compostRecipes.add(new CompostBinRecipe(input, canOredict));
		        		}
					}
	    		}
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	}
}