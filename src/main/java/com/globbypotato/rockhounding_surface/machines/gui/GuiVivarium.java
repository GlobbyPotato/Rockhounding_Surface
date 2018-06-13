package com.globbypotato.rockhounding_surface.machines.gui;

import com.globbypotato.rockhounding_core.machines.gui.GuiUtils;
import com.globbypotato.rockhounding_surface.handler.Reference;
import com.globbypotato.rockhounding_surface.machines.container.ContainerVivarium;
import com.globbypotato.rockhounding_surface.machines.tileentity.TileEntityVivarium;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiVivarium extends GuiBase {

	public static final ResourceLocation TEXTURE_REF = new ResourceLocation(Reference.MODID + ":textures/gui/guivivarium.png");
	public static final ResourceLocation TEXTURE_JEI = new ResourceLocation(Reference.MODID + ":textures/gui/jei/jeivivarium.png");
    private final TileEntityVivarium tile;

    public GuiVivarium(InventoryPlayer playerInv, TileEntityVivarium tile){
        super(new ContainerVivarium(playerInv,tile));
		TEXTURE = TEXTURE_REF;
        this.tile = tile;
		this.containerName =  "container." + this.tile.getName();
    }

	@Override
    public void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY){
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    	super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);

			GuiUtils.renderFluidBar(new FluidStack(FluidRegistry.WATER, 1000), 1000, 1000, i + 63, j + 57, 50, 26);
    }

}