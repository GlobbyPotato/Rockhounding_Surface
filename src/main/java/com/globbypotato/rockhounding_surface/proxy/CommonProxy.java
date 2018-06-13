package com.globbypotato.rockhounding_surface.proxy;

import com.globbypotato.rockhounding_surface.compat.crafttweaker.CTSupport;
import com.globbypotato.rockhounding_surface.compat.top.TopCompat;
import com.globbypotato.rockhounding_surface.compat.waila.WailaCompat;
import com.globbypotato.rockhounding_surface.fluids.ModFluids;
import com.globbypotato.rockhounding_surface.handler.GlobbyEventHandler;
import com.globbypotato.rockhounding_surface.handler.GuiHandler;
import com.globbypotato.rockhounding_surface.handler.ModConfig;
import com.globbypotato.rockhounding_surface.handler.Reference;
import com.globbypotato.rockhounding_surface.integration.ChemistrySupport;
import com.globbypotato.rockhounding_surface.integration.ChiselSupport;
import com.globbypotato.rockhounding_surface.utils.IMCUtils;
import com.globbypotato.rockhounding_surface.world.FossilGenerator;

import net.minecraftforge.common.MinecraftForge;
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
		ModFluids.registerFluidContainers();

		// Register events
		MinecraftForge.EVENT_BUS.register(new GlobbyEventHandler());	

		// Register worldgen 
		GameRegistry.registerWorldGenerator(new FossilGenerator(), 0);

		// Waila compatilbility
        WailaCompat.init();
        TopCompat.init();
		CTSupport.init();
	}

	/**
	 * @param e  
	 */
	public void init(FMLInitializationEvent e){

		//Register Guis
		NetworkRegistry.INSTANCE.registerGuiHandler(Reference.MODID, new GuiHandler());

		//IMC support
		ChiselSupport.loadChisel();
		ChemistrySupport.init();
	}

	/**
	 * @param e  
	 */
	public void postInit(FMLPostInitializationEvent e){}

	/**
	 * @param event  
	 */
	public void imcInit(IMCEvent event) {
		// Add custom recipes
		IMCUtils.extraRecipes(event.getMessages());
	}

}