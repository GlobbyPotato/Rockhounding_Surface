package com.globbypotato.rockhounding_surface.machines.gui;

import java.util.Arrays;
import java.util.List;

import com.globbypotato.rockhounding_core.utils.RenderUtils;
import com.globbypotato.rockhounding_core.utils.Translator;
import com.globbypotato.rockhounding_surface.handler.Reference;
import com.globbypotato.rockhounding_surface.machines.container.ContainerWoodIncubator;
import com.globbypotato.rockhounding_surface.machines.tileentity.TileEntityWoodIncubator;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiWoodIncubator extends GuiBase {
	private final InventoryPlayer playerInventory;
	private final TileEntityWoodIncubator woodIncubator;
	public static final int WIDTH = 176;
	public static final int HEIGHT = 225;
	public static final ResourceLocation TEXTURE_REF =  new ResourceLocation(Reference.MODID + ":textures/gui/guiwoodincubator.png");

	public GuiWoodIncubator(InventoryPlayer playerInv, TileEntityWoodIncubator tile){
		super(tile, new ContainerWoodIncubator(playerInv, tile));
		this.playerInventory = playerInv;
		TEXTURE = TEXTURE_REF;
		this.woodIncubator = tile;
		this.xSize = WIDTH;
		this.ySize = HEIGHT;
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float f) {
		super.drawScreen(mouseX, mouseY, f);
		int x = (this.width - this.xSize) / 2;
		int y = (this.height - this.ySize) / 2;

		//fuel
		if(mouseX >= 10+x && mouseX <= 21+x && mouseY >= 48+y && mouseY <= 99+y){
			String text = this.woodIncubator.getPower() + "/" + this.woodIncubator.getPowerMax() + " ticks";
			List<String> tooltip = Arrays.asList(text);
			drawHoveringText(tooltip, mouseX, mouseY, fontRendererObj);
		}
		//redstone
		if(mouseX >= 31+x && mouseX <= 42+x && mouseY >= 33+y && mouseY <= 84+y){
			String text = this.woodIncubator.getRedstone() + "/" + this.woodIncubator.getRedstoneMax() + " RF";
			List<String> tooltip = Arrays.asList(text);
			drawHoveringText(tooltip, mouseX, mouseY, fontRendererObj);
		}
		//input tank
		if(mouseX>= 84+x && mouseX <= 104+x && mouseY >= 33+y && mouseY <= 99+y){
			int fluidAmount = 0;
			if(woodIncubator.inputTank.getFluid() != null){
				fluidAmount = this.woodIncubator.inputTank.getFluidAmount();
			}
			String text = fluidAmount + "/" + this.woodIncubator.inputTank.getCapacity() + " mb ";
			String liquid = "";
			if(woodIncubator.inputTank.getFluid() != null) liquid = woodIncubator.inputTank.getFluid().getLocalizedName();
			List<String> tooltip = Arrays.asList(text, liquid);
			drawHoveringText(tooltip, mouseX, mouseY, fontRendererObj);
		}
		//prev
		if(mouseX >= 137+x && mouseX <= 153+x && mouseY >= 122+y && mouseY <= 138+y){
			List<String> tooltip = Arrays.asList("Previous Recipe");
			drawHoveringText(tooltip, mouseX, mouseY, fontRendererObj);
		}
		//next
		if(mouseX >= 154+x && mouseX <= 168+x && mouseY >= 122+y && mouseY <= 138+y){
			List<String> tooltip = Arrays.asList("Next Recipe");
			drawHoveringText(tooltip, mouseX, mouseY, fontRendererObj);
		}
		//activation
		if(mouseX >= 7+x && mouseX <= 23+x && mouseY >= 122+y && mouseY <= 138+y){
			List<String> tooltip = Arrays.asList("Activation");
			drawHoveringText(tooltip, mouseX, mouseY, fontRendererObj);
		}
	}

	 @Override
	public void drawGuiContainerForegroundLayer(int mouseX, int mouseY){
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);

    	String device = Translator.translateToLocal("container.woodIncubator");
		this.fontRendererObj.drawString(device, this.xSize / 2 - this.fontRendererObj.getStringWidth(device) / 2, 4, 4210752);
		String recipeLabel = "";
		if(this.woodIncubator.isValidInterval()){
			recipeLabel = this.woodIncubator.getRecipe().getOutput().getDisplayName();
		}else{
			recipeLabel = "No Recipe";
		}
		this.fontRendererObj.drawString(recipeLabel, 26, 127, 4210752);
	}

	 @Override
	public void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY){
		super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
		int i = (this.width - this.xSize) / 2;
		int j = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
		//power bar
		if (this.woodIncubator.powerCount > 0){
            int k = this.getBarScaled(50, this.woodIncubator.powerCount, this.woodIncubator.powerMax);
            this.drawTexturedModalRect(i + 11, j + 49 + (50 - k), 176, 27, 10, k);
		}
		//redstone
		if (this.woodIncubator.redstoneCount > 0){
			int k = this.getBarScaled(50, this.woodIncubator.redstoneCount, this.woodIncubator.redstoneMax);
			this.drawTexturedModalRect(i + 32, j + 34 + (50 - k), 176, 81, 10, k);
		}
		//smelt bar
		int k = this.getBarScaled(14, this.woodIncubator.cookTime, this.woodIncubator.getCookTimeMax());
		this.drawTexturedModalRect(i + 109, j + 57, 176, 0, 15, k); //dust

		//process icons
		if(this.woodIncubator.canSynthesize()){
			this.drawTexturedModalRect(i + 88, j + 102, 176, 131, 12, 14); //fire
			this.drawTexturedModalRect(i + 62, j + 61, 176, 145, 15, 9); //fluid drop
		}
		//induction icons
		if(this.woodIncubator.hasPermanentInduction()){
			this.drawTexturedModalRect(i + 7, j + 30, 176, 154, 18, 18); //inductor
		}
        //activation
        if(this.woodIncubator.activation){
            this.drawTexturedModalRect(i + 7, j + 122, 176, 172, 16, 16);
        }

		//input fluid
		if(woodIncubator.inputTank.getFluid() != null){
			FluidStack temp = woodIncubator.inputTank.getFluid();
			int capacity = woodIncubator.inputTank.getCapacity();
			if(temp.amount > 5){
				RenderUtils.bindBlockTexture();
				RenderUtils.renderGuiTank(temp,capacity, temp.amount, i + 84, j + 34, zLevel, 20, 65);
			}
		}
	}

}