package com.globbypotato.rockhounding_surface.machines.gui;

import java.util.List;

import com.globbypotato.rockhounding_core.machines.gui.GuiUtils;
import com.globbypotato.rockhounding_surface.handler.Reference;
import com.globbypotato.rockhounding_surface.machines.container.ContainerWoodIncubator;
import com.globbypotato.rockhounding_surface.machines.tileentity.TileEntityWoodIncubator;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiWoodIncubator extends GuiBase {
	private final TileEntityWoodIncubator tile;
	public static final int HEIGHT_INCUBATOR = 225;
	public static final ResourceLocation TEXTURE_REF =  new ResourceLocation(Reference.MODID + ":textures/gui/guiwoodincubator.png");

	public GuiWoodIncubator(InventoryPlayer playerInv, TileEntityWoodIncubator tile){
		super(new ContainerWoodIncubator(playerInv, tile));
		TEXTURE = TEXTURE_REF;
        this.tile = tile;
		this.containerName =  "container." + this.tile.getName();
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float f) {
		super.drawScreen(mouseX, mouseY, f);
		int x = (this.width - this.xSize) / 2;
		int y = (this.height - this.ySize) / 2;

		//input fluid
	    if(GuiUtils.hoveringArea(54, 71, 68, 24, mouseX, mouseY, x, y)){
			List<String> tooltip = GuiUtils.drawFluidTankInfo(this.tile.inputTank, mouseX, mouseY);
			drawHoveringText(tooltip, mouseX, mouseY, this.fontRenderer);
		}

		   //prev
		   if(GuiUtils.hoveringArea(137, 22, 16, 16, mouseX, mouseY, x, y)){
			   List<String> tooltip = GuiUtils.drawLabel("Previous Recipe", mouseX, mouseY);
			   drawHoveringText(tooltip, mouseX, mouseY, this.fontRenderer);
		   }

		   //prev
		   if(GuiUtils.hoveringArea(153, 22, 16, 16, mouseX, mouseY, x, y)){
			   List<String> tooltip = GuiUtils.drawLabel("Next Recipe", mouseX, mouseY);
			   drawHoveringText(tooltip, mouseX, mouseY, this.fontRenderer);
		   }

	   //activation
	   if(GuiUtils.hoveringArea(7, 22, 16, 16, mouseX, mouseY, x, y)){
		   List<String> tooltip = GuiUtils.drawLabel("Activation", mouseX, mouseY);
		   drawHoveringText(tooltip, mouseX, mouseY, this.fontRenderer);
	   }

	}

	 @Override
	public void drawGuiContainerForegroundLayer(int mouseX, int mouseY){
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		String recipeLabel = "No Recipe";
		if(this.tile.isValidInterval()){
			recipeLabel = this.tile.getCurrentRecipe().getOutput().getDisplayName();
		}
		this.fontRenderer.drawString(recipeLabel, 25, 26, 4210752);
	}

	@Override
	public void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY){
		super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
		int i = (this.width - this.xSize) / 2;
		int j = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);

		//smelt bar
		int k = GuiUtils.getScaledValue(18, this.tile.getCooktime(), this.tile.getCookTimeMax());
		this.drawTexturedModalRect(i + 79, j + 51, 176, 9, 18, k); //dust

		//process icons
		if(this.tile.canSynthesize()){
			this.drawTexturedModalRect(i + 82, j + 97, 176, 82, 12, 14); //fire
		}

		//activation
        if(this.tile.activation){
            this.drawTexturedModalRect(i + 8, j + 23, 176, 66, 14, 14);
        }

		//input fluid
		if(this.tile.inputTank.getFluid() != null){
			GuiUtils.renderFluidBar(this.tile.inputTank.getFluid(), this.tile.inputTank.getFluidAmount(), this.tile.inputTank.getCapacity(), i + 55, j + 72, 66, 22);
		}
	}

}