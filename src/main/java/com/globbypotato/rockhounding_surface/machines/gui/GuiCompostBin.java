package com.globbypotato.rockhounding_surface.machines.gui;

import java.util.List;

import com.globbypotato.rockhounding_core.machines.gui.GuiUtils;
import com.globbypotato.rockhounding_surface.handler.Reference;
import com.globbypotato.rockhounding_surface.machines.container.ContainerCompostBin;
import com.globbypotato.rockhounding_surface.machines.tileentity.TileEntityCompostBin;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiCompostBin extends GuiBase {

	public static final ResourceLocation TEXTURE_REF = new ResourceLocation(Reference.MODID + ":textures/gui/guicompostbin.png");
	public static final ResourceLocation TEXTURE_JEI = new ResourceLocation(Reference.MODID + ":textures/gui/jei/jeicompostbin.png");
    private final TileEntityCompostBin tile;

    public GuiCompostBin(InventoryPlayer playerInv, TileEntityCompostBin tile){
        super(new ContainerCompostBin(playerInv,tile));
		TEXTURE = TEXTURE_REF;
        this.tile = tile;
		this.containerName =  "container." + this.tile.getName();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float f) {
       super.drawScreen(mouseX, mouseY, f);
	   int x = (this.width - this.xSize) / 2;
	   int y = (this.height - this.ySize) / 2;

	   if(GuiUtils.hoveringArea(67, 38, 42, 52, mouseX, mouseY, x, y)){
		   String text = this.tile.amount + "/" + this.tile.capacity + " units";
		   int processed = (this.tile.getCooktime() * 100) / this.tile.getMaxCookTime();
		   String process = processed + "% compost ready";
		   String multiString[] = new String[]{text, process};
		   List<String> tooltip = GuiUtils.drawMultiLabel(multiString, mouseX, mouseY);
		   drawHoveringText(tooltip, mouseX, mouseY, this.fontRenderer);
	   }
    }

    @Override
	public void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY){
		super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
		int i = (this.width - this.xSize) / 2;
		int j = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);

		//tank
        if (this.tile.amount > 0){
            int k = GuiUtils.getScaledValue(50, this.tile.amount, this.tile.capacity);
            this.drawTexturedModalRect(i + 68, j + 39 + (50 - k), 176, 0, 40, k);
        }

        //smelt bar
        if (this.tile.getCooktime() > 0){
            int k = GuiUtils.getScaledValue(14, this.tile.getCooktime(), this.tile.getMaxCookTime());
            this.drawTexturedModalRect(i + 111, j + 73, 176, 52, k, 16);
        }

    }
}