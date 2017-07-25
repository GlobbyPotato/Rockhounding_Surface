package com.globbypotato.rockhounding_surface.machines.renders;

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
	public void renderTileEntityAt(TileEntityTruffleAuction te, double x, double y, double z, float partialTicks, int destroyStage) {
		super.renderTileEntityAt(te, x, y, z, partialTicks, destroyStage);

		TileEntityTruffleAuction auction = (TileEntityTruffleAuction)te;
		if(auction != null){
			if(auction.getInput().getStackInSlot(auction.INPUT_SLOT) != null){
				ItemStack inputStack = new ItemStack(auction.getInput().getStackInSlot(auction.INPUT_SLOT).getItem(), 1, auction.getInput().getStackInSlot(auction.INPUT_SLOT).getItemDamage());
				World world = Minecraft.getMinecraft().theWorld;
				shard = new EntityItem(world, 0, 0, 0, inputStack);
				shard.hoverStart = 0;
				GlStateManager.pushMatrix();
				{
					GlStateManager.translate(x, y, z);
					GlStateManager.rotate(90F, 1, 0, 0);
					GlStateManager.translate(0.50, 0.05, -1.03);
					GlStateManager.scale(1.0, 1.0, 2.0);
					Minecraft.getMinecraft().getRenderManager().doRenderEntity(shard, 0, 0, 0, 0F, 0F, false);
				}
				GlStateManager.popMatrix();
			}
		}
	}
}