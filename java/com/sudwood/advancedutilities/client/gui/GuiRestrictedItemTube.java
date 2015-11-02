package com.sudwood.advancedutilities.client.gui;

import java.util.Arrays;
import java.util.List;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.sudwood.advancedutilities.AdvancedUtilities;
import com.sudwood.advancedutilities.container.ContainerRestrictedItemTube;
import com.sudwood.advancedutilities.tileentity.TileEntityRestrictedItemTube;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiRestrictedItemTube extends GuiContainer
{
	private static final ResourceLocation hopperGuiTextures = new ResourceLocation(AdvancedUtilities.MODID, "textures/gui/restricteditemtubegui.png");
    private TileEntityRestrictedItemTube tile;
    private static final String __OBFID = "CL_00000758";

    public GuiRestrictedItemTube(InventoryPlayer par1InventoryPlayer, TileEntityRestrictedItemTube par2TileEntityKiln)
    {
        super(new ContainerRestrictedItemTube(par1InventoryPlayer, par2TileEntityKiln));
        this.tile = par2TileEntityKiln;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_)
    {
        this.fontRendererObj.drawString("Restricted Item Tube", this.xSize / 2 - this.fontRendererObj.getStringWidth("Restricted Item Tube") / 2, 6, 4210752);
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

    }
    
    @Override
    public void drawScreen(int par1, int par2, float par3)
    {
    	super.drawScreen(par1, par2, par3);
    	int var5 = (this.width - this.xSize) / 2;
        int var6 = (this.height - this.ySize) / 2;
    	if(par1 >= 108+var5 && par2 >= 16+var6 && par1 <= 161+var5 && par2 <= 69+var6)
		{
    		String[] text = {""};
    		if(tile.getMode() == TileEntityRestrictedItemTube.WHITELIST)
    			text[0] = "Whitelisted Items";
    		if(tile.getMode() == TileEntityRestrictedItemTube.BLACKLIST)
    			text[0] = "Blacklisted Items";
			List temp = Arrays.asList(text);
			drawHoveringText(temp, par1-40, par2-15, fontRendererObj);
		}
    }
}