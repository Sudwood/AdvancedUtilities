package com.sudwood.advancedutilities.client.gui;

import java.util.Arrays;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.sudwood.advancedutilities.container.ContainerWoodenCrate;
import com.sudwood.advancedutilities.tileentity.TileEntityWoodenCrate;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class GuiWoodenCrate extends GuiContainer
{
    private static final ResourceLocation furnaceGuiTextures = new ResourceLocation("advancedutilities","textures/gui/woodcrategui.png");
    private TileEntityWoodenCrate tile;
    private static final String __OBFID = "CL_00000758";

    public GuiWoodenCrate(InventoryPlayer par1InventoryPlayer, TileEntityWoodenCrate par2TileEntityWoodenCrate)
    {
        super(new ContainerWoodenCrate(par1InventoryPlayer, par2TileEntityWoodenCrate));
        this.tile = par2TileEntityWoodenCrate;
        this.xSize = 255;
        this.ySize = 166;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_)
    {
        this.fontRendererObj.drawString("Wooden Crate", this.xSize / 2 - this.fontRendererObj.getStringWidth("Wooden Crate") / 2, -26, 16777215);
        this.fontRendererObj.drawString("Amount: "+tile.stackSize, this.xSize / 2 - this.fontRendererObj.getStringWidth("Wooden Crate") / 2, -15, 16777215);
        this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), -1, this.ySize - 86 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(furnaceGuiTextures);
        int k = this.guiLeft;
        int l = this.guiTop;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        int i1;
    }
    
    @Override
    public void drawScreen(int par1, int par2, float par3)
    {
    	super.drawScreen(par1, par2, par3);
    	int var5 = (this.width - this.xSize) / 2;
        int var6 = (this.height - this.ySize) / 2;
       
        
    }
}
