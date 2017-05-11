package com.globbypotato.rockhounding_surface.proxy;

import com.globbypotato.rockhounding_surface.ModBiomes;
import com.globbypotato.rockhounding_surface.ModBlocks;
import com.globbypotato.rockhounding_surface.ModItems;
import com.globbypotato.rockhounding_surface.fluids.BucketHandler;
import com.globbypotato.rockhounding_surface.fluids.ModFluids;
import com.globbypotato.rockhounding_surface.handler.GlobbyEventHandler;
import com.globbypotato.rockhounding_surface.handler.GuiHandler;
import com.globbypotato.rockhounding_surface.handler.ModConfig;
import com.globbypotato.rockhounding_surface.handler.ModDictionary;
import com.globbypotato.rockhounding_surface.handler.ModRecipes;
import com.globbypotato.rockhounding_surface.handler.Reference;
import com.globbypotato.rockhounding_surface.integration.ChemistrySupport;
import com.globbypotato.rockhounding_surface.integration.ChiselSupport;
import com.globbypotato.rockhounding_surface.integration.crafttweaker.CTSupport;
import com.globbypotato.rockhounding_surface.machines.recipe.ExtraRecipes;
import com.globbypotato.rockhounding_surface.utils.IMCUtils;
import com.globbypotato.rockhounding_surface.world.FossilGenerator;
import com.globbypotato.rockhounding_surface.world.SandGenerator;

import net.minecraft.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms.IMCEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent e){
		// Load Config
		ModConfig.loadConfig(e);

		// Register Fluids
		if( !FluidRegistry.isUniversalBucketEnabled() ){
		   	MinecraftForge.EVENT_BUS.register(BucketHandler.INSTANCE);
		   	ModFluids.loadBuckets();
			ModFluids.registerFluidBuckets();
		}
		ModFluids.registerFluidContainers();

		// Register Contents
		ModBlocks.init();
		ModItems.init();
		ModBiomes.init();

		// Register events
		MinecraftForge.EVENT_BUS.register(new GlobbyEventHandler());	

		// Register worldgen 
		GameRegistry.registerWorldGenerator(new FossilGenerator(), 1);
		GameRegistry.registerWorldGenerator(new SandGenerator(), 2);

		// Register oreDictionary
		ModDictionary.loadDictionary();
	}

	public void init(FMLInitializationEvent e){
		// Register Craft Tweaker Support
		CTSupport.init();

		// Register Recipes
		ModRecipes.init();

		//Register Guis
		NetworkRegistry.INSTANCE.registerGuiHandler(Reference.MODID, new GuiHandler());

		//IMC support
		ChiselSupport.loadChisel();
		ChemistrySupport.init();
		ExtraRecipes.init();
	}

	public void imcInit(IMCEvent event) {
		// Add custom recipes
		IMCUtils.extraRecipes(event.getMessages());
	}

	public void postInit(FMLPostInitializationEvent e){}

	public void registerTileEntitySpecialRenderer() {}

	public void registerRenderInformation() {}

	public void initFluidModel(Block block, Fluid fluid) {}

}