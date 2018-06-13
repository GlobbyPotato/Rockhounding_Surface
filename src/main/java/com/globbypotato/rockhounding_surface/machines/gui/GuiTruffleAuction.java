package com.globbypotato.rockhounding_surface.machines.gui;

import java.util.List;

import com.globbypotato.rockhounding_core.machines.gui.GuiUtils;
import com.globbypotato.rockhounding_surface.handler.Reference;
import com.globbypotato.rockhounding_surface.machines.container.ContainerTruffleAuction;
import com.globbypotato.rockhounding_surface.machines.tileentity.TileEntityTruffleAuction;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiTruffleAuction extends GuiBase {
	
	public static final ResourceLocation TEXTURE_REF = new ResourceLocation(Reference.MODID + ":textures/gui/guitruffleauction.png");
	public static final ResourceLocation TEXTURE_JEI = new ResourceLocation(Reference.MODID + ":textures/gui/jei/jeitruffleauction.png");
    private final TileEntityTruffleAuction tile;

    public GuiTruffleAuction(InventoryPlayer playerInv, TileEntityTruffleAuction tile){
        super(new ContainerTruffleAuction(playerInv,tile));
        TEXTURE = TEXTURE_REF;
        this.tile = tile;
		this.containerName =  "container." + this.tile.getName();
    }

	@Override
	public void drawScreen(int mouseX, int mouseY, float f) {
		super.drawScreen(mouseX, mouseY, f);
		int x = (this.width - this.xSize) / 2;
		int y = (this.height - this.ySize) / 2;

		//truffles
		if(this.tile.inputSlot().isEmpty() && GuiUtils.hoveringArea(79, 28, 18, 18, mouseX, mouseY, x, y)){
			List<String> tooltip = GuiUtils.drawLabel("Put truffles here", mouseX, mouseY);
			drawHoveringText(tooltip, mouseX, mouseY, this.fontRenderer);
		}
		
		//wishlist
		if(this.tile.toolSlot().isEmpty() && GuiUtils.hoveringArea(123, 62, 18, 18, mouseX, mouseY, x, y)){
			List<String> tooltip = GuiUtils.drawLabel("Put the wishlist item here", mouseX, mouseY);
			drawHoveringText(tooltip, mouseX, mouseY, this.fontRenderer);
		}

		//nugget
		if(GuiUtils.hoveringArea(36, 55, 18, 18, mouseX, mouseY, x, y)){
			List<String> tooltip = GuiUtils.drawLabel("Default reward", mouseX, mouseY);
			drawHoveringText(tooltip, mouseX, mouseY, this.fontRenderer);
		}

	}
}