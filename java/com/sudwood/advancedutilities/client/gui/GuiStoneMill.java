package com.sudwood.advancedutilities.client.gui;

import java.util.Arrays;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.sudwood.advancedutilities.container.ContainerStoneMill;
import com.sudwood.advancedutilities.tileentity.TileEntityStoneMill;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class GuiStoneMill extends GuiContainer
{
    private static final ResourceLocation furnaceGuiTextures = new ResourceLocation("advancedutilities","textures/gui/stonemillgui.png");
    private TileEntityStoneMill tile;
    private static final String __OBFID = "CL_00000758";

    public GuiStoneMill(InventoryPlayer par1InventoryPlayer, TileEntityStoneMill par2TileEntityStoneMill)
    {
        super(new ContainerStoneMill(par1InventoryPlayer, par2TileEntityStoneMill));
        this.tile = par2TileEntityStoneMill;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_)
    {
        this.fontRendererObj.drawString("Stone Mill", this.xSize / 2 - this.fontRendererObj.getStringWidth("Stone Mill") / 2, 6, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 96 + 2, 4210752);
    }

    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(furnaceGuiTextures);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        int i1;

        i1 = this.tile.getGrindProgressScaled(54);
        this.drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 + 1, 16);
    }
    
    @Override
    public void drawScreen(int par1, int par2, float par3)
    {
    	super.drawScreen(par1, par2, par3);
    	int var5 = (this.width - this.xSize) / 2;
        int var6 = (this.height - this.ySize) / 2;
        if(par1 >= 80+var5 && par2 >= 35+var6 && par1 <= 101+var5 && par2 <= 50+var6)
		{
			String[] text = {"Progress: " + tile.grindTime + " / 400"};
			List temp = Arrays.asList(text);
			drawHoveringText(temp, par1, par2, fontRendererObj);
		}
        if(par1 >= 56+var5 && par2 >= 17+var6 && par1 <= 72+var5 && par2 <= 33+var6)
		{
			String[] text = {"Cobblestone goes here."};
			List temp = Arrays.asList(text);
			drawHoveringText(temp, par1, par2, fontRendererObj);
		}
        
        if(par1 >= 56+var5 && par2 >= 53+var6 && par1 <= 72+var5 && par2 <= 69+var6)
		{
			String[] text = {"Ore goes here."};
			List temp = Arrays.asList(text);
			drawHoveringText(temp, par1, par2, fontRendererObj);
		}
        
    }
}