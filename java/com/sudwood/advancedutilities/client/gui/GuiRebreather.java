package com.sudwood.advancedutilities.client.gui;

import java.util.Arrays;
import java.util.List;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.sudwood.advancedutilities.container.ContainerRebreather;
import com.sudwood.advancedutilities.container.RebreatherInv;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiRebreather extends GuiContainer
{
    private static final ResourceLocation furnaceGuiTextures = new ResourceLocation("advancedutilities","textures/gui/rebreathergui.png");
    private static final String __OBFID = "CL_00000758";
    private final RebreatherInv inventory;
    private NBTTagCompound tag;

    public GuiRebreather(ContainerRebreather containerItem, ItemStack gun)
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
        this.fontRendererObj.drawString("Rebreather", this.xSize / 3 - this.fontRendererObj.getStringWidth("Bullet Magazine") / 2, 6, 4210752);
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
    	int var5 = (this.width - this.xSize) / 2;
        int var6 = (this.height - this.ySize) / 2;
    	if(par1 >= 17+var5 && par2 >= 4+var6 && par1 <= 34+var5 && par2 <= 81+var6)
		{
			String[] text = {"Food"};
			List temp = Arrays.asList(text);
			drawHoveringText(temp, par1-40, par2-15, fontRendererObj);
		}
    	if(par1 >= 141+var5 && par2 >= 4+var6 && par1 <= 158+var5 && par2 <= 81+var6)
		{
			String[] text = {"Air Tank"};
			List temp = Arrays.asList(text);
			drawHoveringText(temp, par1-40, par2-15, fontRendererObj);
		}
        
    }
}