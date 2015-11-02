package com.sudwood.advancedutilities.client.gui;

import java.util.Arrays;
import java.util.List;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.sudwood.advancedutilities.container.ContainerSteamCrusher;
import com.sudwood.advancedutilities.tileentity.TileEntitySteamCrusher;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiSteamCrusher extends GuiContainer
{
    private static final ResourceLocation furnaceGuiTextures = new ResourceLocation("advancedutilities","textures/gui/steamcrushergui.png");
    private TileEntitySteamCrusher tile;
    private static final String __OBFID = "CL_00000758";

    public GuiSteamCrusher(InventoryPlayer par1InventoryPlayer, TileEntitySteamCrusher par2TileEntitySteamCrusher)
    {
        super(new ContainerSteamCrusher(par1InventoryPlayer, par2TileEntitySteamCrusher));
        this.tile = par2TileEntitySteamCrusher;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_)
    {
        this.fontRendererObj.drawString("Steam Crusher", this.xSize / 3 - this.fontRendererObj.getStringWidth("Steam Powered") / 2, 6, 4210752);
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

        i1 = this.tile.getProgressScaled(22);
        this.drawTexturedModalRect(k + 39, l + 35, 176, 14, i1 + 1, 16);
        
        if(tile.getTankAmount() > 0)
        {
        	i1 = this.tile.getFluidScaled(66);
        	this.drawTexturedModalRect(k + 109, l + 61 + 12 - i1, 177, 99 - i1, 38, i1 + 2);
        }
        
        
    }
    
    @Override
    public void drawScreen(int par1, int par2, float par3)
    {
    	super.drawScreen(par1, par2, par3);
    	int var5 = (this.width - this.xSize) / 2;
        int var6 = (this.height - this.ySize) / 2;
        if(par1 >= 39+var5 && par2 >= 35+var6 && par1 <= 60+var5 && par2 <= 49+var6)
		{
			String[] text = {"Progress: " + tile.progressTime + " / "+tile.getMaxProcessTime()};
			List temp = Arrays.asList(text);
			drawHoveringText(temp, par1, par2, fontRendererObj);
		}
        if(par1 >= 110+var5 && par2 >= 8+var6 && par1 <= 143+var5 && par2 <= 74+var6)
		{
			String[] text = {"Amount: "+tile.getTankAmount()+" mB "+tile.getFluidName()};
			List temp = Arrays.asList(text);
			drawHoveringText(temp, par1, par2, fontRendererObj);
		}
        
    }
}