package com.sudwood.advancedutilities.client.gui;

import java.util.Arrays;
import java.util.List;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.sudwood.advancedutilities.container.ContainerTrash;
import com.sudwood.advancedutilities.tileentity.TileEntityTrash;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiTrash extends GuiContainer
{
    private static final ResourceLocation furnaceGuiTextures = new ResourceLocation("advancedutilities","textures/gui/trashgui.png");
    private TileEntityTrash tile;
    private static final String __OBFID = "CL_00000758";

    public GuiTrash(InventoryPlayer par1InventoryPlayer, TileEntityTrash par2TileEntityCompressor)
    {
        super(new ContainerTrash(par1InventoryPlayer, par2TileEntityCompressor));
        this.tile = par2TileEntityCompressor;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_)
    {
        this.fontRendererObj.drawString("Warning Trash! Items will be deleted.", this.xSize / 2 - this.fontRendererObj.getStringWidth("Warning Trash! Items will be deleted.") / 2, 6, 4210752);
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

    }
    
    @Override
    public void drawScreen(int par1, int par2, float par3)
    {
    	super.drawScreen(par1, par2, par3);
    	int var5 = (this.width - this.xSize) / 2;
        int var6 = (this.height - this.ySize) / 2;
        if(par1 >= 56+var5 && par2 >= 35+var6 && par1 <= 72+var5 && par2 <= 51+var6)
		{
			String[] text = {"Ingots go here."};
			List temp = Arrays.asList(text);
			drawHoveringText(temp, par1, par2, fontRendererObj);
		}
        
    }
}