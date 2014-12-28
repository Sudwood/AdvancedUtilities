package com.sudwood.advancedutilities.client.gui;

import java.util.Arrays;
import java.util.List;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.sudwood.advancedutilities.container.ContainerKiln;
import com.sudwood.advancedutilities.container.ContainerPnumaticGun;
import com.sudwood.advancedutilities.container.ContainerSteamBoiler;
import com.sudwood.advancedutilities.container.ContainerSteamCrusher;
import com.sudwood.advancedutilities.container.InventoryItem;
import com.sudwood.advancedutilities.tileentity.TileEntitySteamCrusher;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiPnumaticGun extends GuiContainer
{
    private static final ResourceLocation furnaceGuiTextures = new ResourceLocation("advancedutilities","textures/gui/pnumaticgungui.png");
    private static final String __OBFID = "CL_00000758";
    private final InventoryItem inventory;
    private NBTTagCompound tag;

    public GuiPnumaticGun(ContainerPnumaticGun containerItem, ItemStack gun)
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
        this.fontRendererObj.drawString("Pnumatic Gun", this.xSize / 3 - this.fontRendererObj.getStringWidth("Pnumatic Gun") / 2, 6, 4210752);
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
        
        
        
        if(tag.getInteger("tankAmount") > 0)
        {
        	i1 = tag.getInteger("tankAmount") * 66 / tag.getInteger("maxTankAmount");
        	this.drawTexturedModalRect(k + 109, l + 61 + 12 - i1, 177, 99 - i1, 38, i1 + 2);
        }
        
        
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
        if(par1 >= 110+var5 && par2 >= 8+var6 && par1 <= 143+var5 && par2 <= 74+var6)
		{
			String[] text = {"Amount: "+tag.getInteger("tankAmount")+" / "+tag.getInteger("maxTankAmount")+" mB Steam"};
			List temp = Arrays.asList(text);
			drawHoveringText(temp, par1, par2, fontRendererObj);
		}
        
    }
}