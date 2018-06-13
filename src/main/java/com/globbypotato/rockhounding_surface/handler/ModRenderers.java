package com.globbypotato.rockhounding_surface.handler;

import com.globbypotato.rockhounding_surface.machines.renders.RendererAuction;
import com.globbypotato.rockhounding_surface.machines.renders.RendererVivarium;
import com.globbypotato.rockhounding_surface.machines.tileentity.TileEntityTruffleAuction;
import com.globbypotato.rockhounding_surface.machines.tileentity.TileEntityVivarium;

import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ModRenderers {

	public static void specialRenders() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTruffleAuction.class, new RendererAuction());	
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityVivarium.class, new RendererVivarium());
	}
}