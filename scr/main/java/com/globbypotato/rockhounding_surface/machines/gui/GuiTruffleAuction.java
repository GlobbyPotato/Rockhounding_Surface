package com.globbypotato.rockhounding_surface.machines.gui;

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
    private final InventoryPlayer playerInventory;
    private final TileEntityTruffleAuction auctioner;
	public static final int WIDTH = 176;
	public static final int HEIGHT = 178;

    public GuiTruffleAuction(InventoryPlayer playerInv, TileEntityTruffleAuction tile){
        super(tile, new ContainerTruffleAuction(playerInv,tile));
        this.TEXTURE = TEXTURE_REF;
        this.auctioner = tile;
        this.playerInventory = playerInv;
		this.xSize = WIDTH;
		this.ySize = HEIGHT;
		this.containerName = "container.truffleTable";
    }

    public void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY){
		super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
		int i = (this.width - this.xSize) / 2;
		int j = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
    }

}