package com.sudwood.advancedutilities.client.gui;

import org.lwjgl.opengl.GL11;

import com.sudwood.advancedutilities.AdvancedUtilities;
import com.sudwood.advancedutilities.container.ContainerPortaChest;
import com.sudwood.advancedutilities.tileentity.TileEntityPortaChest;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GuiPortaChest extends GuiContainer
{
	private int chestType;
	private ResourceLocation wood = new ResourceLocation(AdvancedUtilities.MODID, "textures/gui/woodportachestgui.png");
	private ResourceLocation bronze = new ResourceLocation(AdvancedUtilities.MODID, "textures/gui/bronzeportachestgui.png");
	private ResourceLocation gold = new ResourceLocation(AdvancedUtilities.MODID, "textures/gui/goldportachestgui.png");
	private ResourceLocation diamond = new ResourceLocation(AdvancedUtilities.MODID, "textures/gui/diamondportachestgui.png");
	private ResourceLocation platinum3 = new ResourceLocation(AdvancedUtilities.MODID, "textures/gui/platinumportachestguicomplete.png");
	private ContainerPortaChest inventory;
	public GuiPortaChest(ContainerPortaChest cont)
	{
		super(cont);
		inventory = cont;
		chestType = cont.chestType;
		switch(chestType)
		{
		case TileEntityPortaChest.WOOD:
			break;
		case TileEntityPortaChest.BRONZE:
			this.xSize = 183;
			this.ySize = 201;
			break;
		case TileEntityPortaChest.GOLD:
			this.xSize = 183;
			this.ySize = 255;
			break;
		case TileEntityPortaChest.DIAMOND:
			this.xSize = 237;
			this.ySize = 255;
			break;
		case TileEntityPortaChest.PLATINUM:
			this.xSize = 290;
			this.ySize = 255;
			break;
			
		}
	}
	public GuiPortaChest(ContainerPortaChest cont, ItemStack stack)
	{
		super(cont);
		chestType = cont.chestType;
	}
	
	protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_)
    {
    }

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) 
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		 int k = (this.width - this.xSize) / 2;
	        int l = (this.height - this.ySize) / 2;
		switch(chestType)
		{
		case TileEntityPortaChest.WOOD:
			 this.mc.getTextureManager().bindTexture(wood);
			  this.drawTexturedModalRect(k, l, 0, 0, this.xSize+50, this.ySize);
			break;
		case TileEntityPortaChest.BRONZE:
			this.mc.getTextureManager().bindTexture(bronze);
			  this.drawTexturedModalRect(k, l, 0, 0, this.xSize+50, this.ySize);
			break;
		case TileEntityPortaChest.GOLD:
			this.mc.getTextureManager().bindTexture(gold);
			  this.drawTexturedModalRect(k, l, 0, 0, this.xSize+50, this.ySize);
			break;
		case TileEntityPortaChest.DIAMOND:
			this.mc.getTextureManager().bindTexture(diamond);
			  this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
			break;
		case TileEntityPortaChest.PLATINUM:
			this.mc.getTextureManager().bindTexture(platinum3);
			 this.drawTexturedRect(k, l, 0, 0, xSize, ySize, 512, 256);
			break;
			
		}

        int i1;
		
	}
	
	 public void drawTexturedRect(int x, int y, int u, int v, int width, int height, int textureWidth, int textureHeight)

	    {

	        float f = 1F / (float)textureWidth;

	        float f1 = 1F / (float)textureHeight;

	        Tessellator tessellator = Tessellator.instance;

	        tessellator.startDrawingQuads();

	        tessellator.addVertexWithUV((double)(x), (double)(y + height), 0, (double)((float)(u) * f), (double)((float)(v + height) * f1));

	        tessellator.addVertexWithUV((double)(x + width), (double)(y + height), 0, (double)((float)(u + width) * f), (double)((float)(v + height) * f1));

	        tessellator.addVertexWithUV((double)(x + width), (double)(y), 0, (double)((float)(u + width) * f), (double)((float)(v) * f1));

	        tessellator.addVertexWithUV((double)(x), (double)(y), 0, (double)((float)(u) * f), (double)((float)(v) * f1));

	        tessellator.draw();

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
