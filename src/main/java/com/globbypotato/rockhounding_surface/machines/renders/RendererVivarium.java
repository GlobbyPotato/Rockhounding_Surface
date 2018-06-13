package com.globbypotato.rockhounding_surface.machines.renders;

import com.globbypotato.rockhounding_core.machines.tileentity.TileEntityInv;
import com.globbypotato.rockhounding_surface.machines.tileentity.TileEntityVivarium;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class RendererVivarium extends TileEntitySpecialRenderer<TileEntityVivarium>{
	private static EntityItem shard;

	@Override
	public void render(TileEntityVivarium vivarium, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		super.render(vivarium, x, y, z, partialTicks, destroyStage, alpha);

		if(vivarium != null){
			if(!vivarium.getInput().getStackInSlot(TileEntityInv.INPUT_SLOT).isEmpty()){
				ItemStack inputStack = new ItemStack(vivarium.getInput().getStackInSlot(TileEntityInv.INPUT_SLOT).getItem(), 1, vivarium.getInput().getStackInSlot(TileEntityInv.INPUT_SLOT).getItemDamage());
				World world = Minecraft.getMinecraft().world;
				shard = new EntityItem(world, 0, 0, 0, inputStack);
				shard.hoverStart = 0;
				GlStateManager.pushMatrix();
				{
					GlStateManager.translate(x, y, z);
					GlStateManager.rotate(90F, 1, 0, 0);
					GlStateManager.translate(0.5, 0.0, -0.70);
					GlStateManager.scale(1.0, 1.0, 1.0);
					Minecraft.getMinecraft().getRenderManager().renderEntity(shard, 0, 0, 0, 0F, 0F, false);
				}
				GlStateManager.popMatrix();
			}
			if(!vivarium.getOutput().getStackInSlot(TileEntityInv.OUTPUT_SLOT).isEmpty()){
				ItemStack inputStack = new ItemStack(vivarium.getOutput().getStackInSlot(TileEntityInv.OUTPUT_SLOT).getItem(), 1, vivarium.getOutput().getStackInSlot(TileEntityInv.OUTPUT_SLOT).getItemDamage());
				World world = Minecraft.getMinecraft().world;
				shard = new EntityItem(world, 0, 0, 0, inputStack);
				shard.hoverStart = 0;
				GlStateManager.pushMatrix();
				{
					GlStateManager.translate(x, y, z);
					GlStateManager.rotate(90F, 1, 0, 0);
					GlStateManager.translate(0.5, 0.0, -0.60);
					GlStateManager.scale(1.0, 1.0, 1.0);
					Minecraft.getMinecraft().getRenderManager().renderEntity(shard, 0, 0, 0, 0F, 0F, false);
				}
				GlStateManager.popMatrix();
			}
		}
	}
}