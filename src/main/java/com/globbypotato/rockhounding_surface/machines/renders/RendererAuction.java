package com.globbypotato.rockhounding_surface.machines.renders;

import com.globbypotato.rockhounding_core.machines.tileentity.TileEntityInv;
import com.globbypotato.rockhounding_surface.machines.tileentity.TileEntityTruffleAuction;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class RendererAuction extends TileEntitySpecialRenderer<TileEntityTruffleAuction>{
	private static EntityItem shard;

	@Override
	public void render(TileEntityTruffleAuction auction, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		super.render(auction, x, y, z, partialTicks, destroyStage, alpha);

		if(auction != null){
			if(!auction.getInput().getStackInSlot(TileEntityInv.INPUT_SLOT).isEmpty()){
				ItemStack inputStack = new ItemStack(auction.getInput().getStackInSlot(TileEntityInv.INPUT_SLOT).getItem(), 1, auction.getInput().getStackInSlot(TileEntityInv.INPUT_SLOT).getItemDamage());
				World world = Minecraft.getMinecraft().world;
				shard = new EntityItem(world, 0, 0, 0, inputStack);
				shard.hoverStart = 0;
				GlStateManager.pushMatrix();
				{
					GlStateManager.translate(x, y, z);
					GlStateManager.rotate(90F, 1, 0, 0);
					GlStateManager.translate(0.50, 0.05, -1.03);
					GlStateManager.scale(1.0, 1.0, 2.0);
					Minecraft.getMinecraft().getRenderManager().renderEntity(shard, 0, 0, 0, 0F, 0F, false);
				}
				GlStateManager.popMatrix();
			}
		}
	}
}