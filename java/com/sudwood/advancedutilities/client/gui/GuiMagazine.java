package com.sudwood.advancedutilities.client.gui;


import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.sudwood.advancedutilities.container.ContainerMagazine;
import com.sudwood.advancedutilities.container.InventoryItem;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiMagazine extends GuiContainer
{
    private static final ResourceLocation furnaceGuiTextures = new ResourceLocation("advancedutilities","textures/gui/bulletmagazinegui.png");
    private static final String __OBFID = "CL_00000758";
    private final InventoryItem inventory;
    private NBTTagCompound tag;

    public GuiMagazine(ContainerMagazine containerItem, ItemStack gun)
    {
	    super(containerItem);
	    this.inventory = containerItem.inventory;
	    tag = gun.getTagCompound();
    }


    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_)
    {
        this.fontRendererObj.drawString("Bullet Magazine", this.xSize / 3 - this.fontRendererObj.getStringWidth("Bullet Magazine") / 2, 6, 4210752);
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
    protected void keyTyped(char c, int keyCode) 
    {
	    // make sure you call super!!!
	    super.keyTyped(c, keyCode);
	    // 1 is the Esc key, and we made our keybinding array public and static so we can access it here
	    if (c == 1 )
	    {
	    	mc.thePlayer.closeScreen();
	    }
    }
    
    @Override
    public void drawScreen(int par1, int par2, float par3)
    {
    	super.drawScreen(par1, par2, par3);
    }
}