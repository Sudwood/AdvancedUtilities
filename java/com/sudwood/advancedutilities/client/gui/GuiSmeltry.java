package com.sudwood.advancedutilities.client.gui;

import java.util.Arrays;
import java.util.List;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.sudwood.advancedutilities.container.ContainerSmeltry;
import com.sudwood.advancedutilities.tileentity.TileEntitySmeltry;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiSmeltry extends GuiContainer
{
    private static final ResourceLocation furnaceGuiTextures = new ResourceLocation("advancedutilities","textures/gui/smeltrygui.png");
    private TileEntitySmeltry tile;
    private static final String __OBFID = "CL_00000758";

    public GuiSmeltry(InventoryPlayer par1InventoryPlayer, TileEntitySmeltry par2TileEntitySmeltry)
    {
        super(new ContainerSmeltry(par1InventoryPlayer, par2TileEntitySmeltry));
        this.tile = par2TileEntitySmeltry;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_)
    {
        this.fontRendererObj.drawString("Smeltry", this.xSize / 3 - this.fontRendererObj.getStringWidth("Smeltry") / 2, 6, 4210752);
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

        if (this.tile.isBurning())
        {
            i1 = this.tile.getBurnTimeRemainingScaled(12);
            this.drawTexturedModalRect(k + 117, l + 32 + 12 - i1, 176, 12 - i1, 14, i1 + 2);
        }

        i1 = this.tile.getCookProgressScaled(22);
        this.drawTexturedModalRect(k + 56, l + 27, 201, 11, 16, i1);
        //this.drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 + 1, 16);
    }
    
    @Override
    public void drawScreen(int par1, int par2, float par3)
    {
    	super.drawScreen(par1, par2, par3);
    	int var5 = (this.width - this.xSize) / 2;
        int var6 = (this.height - this.ySize) / 2;
        if(par1 >= 56+var5 && par2 >= 27+var6 && par1 <= 70+var5 && par2 <= 48+var6)
		{
			String[] text = {"Progress: " + tile.furnaceCookTime + " / 1200"};
			List temp = Arrays.asList(text);
			drawHoveringText(temp, par1, par2, fontRendererObj);
		}
        if(par1 >= 116+var5 && par2 >= 31+var6 && par1 <= 131+var5 && par2 <= 46+var6)
		{
			String[] text = {"Burn Time Remaining: " + tile.furnaceBurnTime/20 + " s"};
			List temp = Arrays.asList(text);
			drawHoveringText(temp, par1-40, par2-15, fontRendererObj);
		}
        if(par1 >= 81+var5 && par2 >= 7+var6 && par1 <= 96+var5 && par2 <= 22+var6)
		{
			String[] text = {"Melted Item"};
			List temp = Arrays.asList(text);
			drawHoveringText(temp, par1-40, par2-15, fontRendererObj);
		}
        if(par1 >= 81+var5 && par2 >= 30+var6 && par1 <= 96+var5 && par2 <= 45+var6)
		{
			String[] text = {"Cast Item"};
			List temp = Arrays.asList(text);
			drawHoveringText(temp, par1-40, par2-15, fontRendererObj);
		}
        
    }
}