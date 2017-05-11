package com.globbypotato.rockhounding_surface.machines.gui;

import java.util.Arrays;
import java.util.List;

import com.globbypotato.rockhounding_surface.handler.Reference;
import com.globbypotato.rockhounding_surface.machines.container.ContainerCompostBin;
import com.globbypotato.rockhounding_surface.machines.tileentity.TileEntityCompostBin;
import com.globbypotato.rockhounding_surface.utils.Translator;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiCompostBin extends GuiBase {
	
	public static final ResourceLocation TEXTURE_REF = new ResourceLocation(Reference.MODID + ":textures/gui/guicompostbin.png");
    private final InventoryPlayer playerInventory;
    private final TileEntityCompostBin composter;
	public static final int WIDTH = 176;
	public static final int HEIGHT = 168;

    public GuiCompostBin(InventoryPlayer playerInv, TileEntityCompostBin tile){
        super(tile, new ContainerCompostBin(playerInv,tile));
        this.TEXTURE = TEXTURE_REF;
        this.composter = tile;
        this.playerInventory = playerInv;
		this.xSize = WIDTH;
		this.ySize = HEIGHT;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float f) {
       super.drawScreen(mouseX, mouseY, f);
	   int x = (this.width - this.xSize) / 2;
	   int y = (this.height - this.ySize) / 2;

		//tank
		if(mouseX >= 68+x && mouseX <= 108+x && mouseY >= 19+y && mouseY <= 69+y){
			String text = this.composter.amount + "/" + this.composter.capacity + " units";
			int processed = (this.composter.cookTime * 100) / this.composter.getMaxCookTime();
			String process = processed + "% compost ready";

			String multiString[] = new String[]{text, process};

			List<String> tooltip = Arrays.asList(multiString);
			drawHoveringText(tooltip, mouseX, mouseY, fontRendererObj);
		}
    }

    @Override
    public void drawGuiContainerForegroundLayer(int mouseX, int mouseY){
    	super.drawGuiContainerForegroundLayer(mouseX, mouseY);
    	String device = Translator.translateToLocal("container.compostBin");
        this.fontRendererObj.drawString(device, this.xSize / 2 - this.fontRendererObj.getStringWidth(device) / 2, 6, 4210752);
        this.fontRendererObj.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
    }

    public void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY){
		super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
		int i = (this.width - this.xSize) / 2;
		int j = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
		//tank
        if (this.composter.amount > 0){
            int k = this.getBarScaled(50, this.composter.amount, this.composter.capacity);
            this.drawTexturedModalRect(i + 68, j + 19 + (50 - k), 176, 0, 40, k);
        }
        //smelt bar
        if (this.composter.cookTime > 0){
            int k = this.getBarScaled(14, this.composter.cookTime, this.composter.getMaxCookTime());
            this.drawTexturedModalRect(i + 111, j + 53, 176, 52, k, 16);
        }

    }
}