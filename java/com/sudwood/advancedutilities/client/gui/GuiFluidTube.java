package com.sudwood.advancedutilities.client.gui;

import java.util.Arrays;
import java.util.List;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.sudwood.advancedutilities.AdvancedUtilities;
import com.sudwood.advancedutilities.container.ContainerFluidTube;
import com.sudwood.advancedutilities.tileentity.TileEntityFluidTube;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiFluidTube extends GuiContainer
{
	private static final ResourceLocation hopperGuiTextures = new ResourceLocation(AdvancedUtilities.MODID, "textures/gui/fluidtubegui.png");
    private TileEntityFluidTube tile;
    private static final String __OBFID = "CL_00000758";

    public GuiFluidTube(InventoryPlayer par1InventoryPlayer, TileEntityFluidTube par2TileEntityKiln)
    {
        super(new ContainerFluidTube(par1InventoryPlayer, par2TileEntityKiln));
        this.tile = par2TileEntityKiln;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_)
    {
        this.fontRendererObj.drawString("Fluid Tube", this.xSize / 8 - this.fontRendererObj.getStringWidth("Fluid Tube") / 8, 6, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 96 + 2, 4210752);
    }

    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(hopperGuiTextures);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        int i1;
        
        if(tile.getTankAmount() > 0)
        {
        	i1 = this.tile.getFluidScaled(66);
        	this.drawTexturedModalRect(k + 69, l + 61 + 12 - i1, 177, 99 - i1, 38, i1 + 2);
        }

    }
    
    @Override
    public void drawScreen(int par1, int par2, float par3)
    {
    	super.drawScreen(par1, par2, par3);
    	int var5 = (this.width - this.xSize) / 2;
        int var6 = (this.height - this.ySize) / 2;
    	 if(par1 >= 69+var5 && par2 >= 8+var6 && par1 <= 104+var5 && par2 <= 74+var6)
 		{
 			String[] text = {"Amount: "+tile.getTankAmount()+" mB "+tile.getFluidName()};
 			List temp = Arrays.asList(text);
 			drawHoveringText(temp, par1, par2, fontRendererObj);
 		}
    }
}