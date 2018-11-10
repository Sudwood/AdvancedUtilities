package com.sudwood.advancedutilities.client.gui;


import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.sudwood.advancedutilities.container.ContainerGrowerBlock;
import com.sudwood.advancedutilities.tileentity.TileEntityBlockPlacer;
import com.sudwood.advancedutilities.tileentity.TileEntityGrowerBlock;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiGrowerBlock extends GuiContainer
{
	private static final ResourceLocation hopperGuiTextures = new ResourceLocation("advancedutilities","textures/gui/growergui.png");
    private TileEntityGrowerBlock tile;
    private static final String __OBFID = "CL_00000758";

    public GuiGrowerBlock(InventoryPlayer par1InventoryPlayer, TileEntityGrowerBlock par2TileEntityKiln)
    {
        super(new ContainerGrowerBlock(par1InventoryPlayer, par2TileEntityKiln));
        this.tile = par2TileEntityKiln;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_)
    {
        this.fontRendererObj.drawString("Grower Block", this.xSize / 2 - this.fontRendererObj.getStringWidth("Grower Block") / 2, 6, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 95 + 2, 4210752);
        this.fontRendererObj.drawString("Bonemeal: ", 104, this.ySize -126, 4210752);//+tile.storedBonemeal
        this.fontRendererObj.drawString(tile.storedBonemeal+"", 104, this.ySize -110, 4210752);
    }

    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(hopperGuiTextures);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        int i1;

    }
    
    @Override
    public void drawScreen(int par1, int par2, float par3)
    {
    	super.drawScreen(par1, par2, par3);
        
    }
}