package com.sudwood.advancedutilities.client.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.sudwood.advancedutilities.container.ContainerSteelOven;
import com.sudwood.advancedutilities.tileentity.TileEntitySteelController;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiSteelOven extends GuiContainer
{
    private static final ResourceLocation furnaceGuiTextures = new ResourceLocation("advancedutilities","textures/gui/steelovengui.png");
    private TileEntitySteelController tile;
    private static final String __OBFID = "CL_00000758";
    private ArrayList textBoxes = new ArrayList();

    public GuiSteelOven(InventoryPlayer par1InventoryPlayer, TileEntitySteelController par2TileEntitySteamFurnace)
    {
        super(new ContainerSteelOven(par1InventoryPlayer, par2TileEntitySteamFurnace));
        this.tile = par2TileEntitySteamFurnace;
        textBoxes.add(new TextSquare(14, 18, 16, 16, "Add Coal Here"));
        textBoxes.add(new TextSquare(50, 18, 16, 16, "Add Sand Here"));
        textBoxes.add(new TextSquare(32, 36, 16, 16, "Add Iron Dust Here"));
        textBoxes.add(new TextSquare(14, 54, 16, 16, "Add Nickel Dust Here"));
        textBoxes.add(new TextSquare(50, 54, 16, 16, "Add Redstone Here"));
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_)
    {
        this.fontRendererObj.drawString("Steel Oven", this.xSize / 4 - this.fontRendererObj.getStringWidth("Steel Oven") / 2, 3, 4210752);
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
        this.drawTexturedModalRect(k + 73, l + 36, 176, 14, i1 + 1, 16);
        
        if(tile.getTankAmount() > 0)
        {
        	i1 = this.tile.getFluidScaled(66);
        	this.drawTexturedModalRect(k + 133, l + 62 + 12 - i1, 177, 99 - i1, 38, i1 + 2);
        }
        
        
    }
    
    @Override
    public void drawScreen(int par1, int par2, float par3)
    {
    	super.drawScreen(par1, par2, par3);
    	int var5 = (this.width - this.xSize) / 2;
        int var6 = (this.height - this.ySize) / 2;
        if(par1 >= 73+var5 && par2 >= 36+var6 && par1 <= 96+var5 && par2 <= 51+var6)
		{
			String[] text = {"Progress: " + tile.progressTime + " / "+tile.getMaxProgressTime()};
			List temp = Arrays.asList(text);
			drawHoveringText(temp, par1, par2, fontRendererObj);
		}
        if(par1 >= 133+var5 && par2 >= 7+var6 && par1 <= 168+var5 && par2 <= 75+var6)
		{
			String[] text = {"Amount: "+tile.getTankAmount()+" mB "+tile.getFluidName()};
			List temp = Arrays.asList(text);
			drawHoveringText(temp, par1, par2, fontRendererObj);
		}
        
        for(int i = 0; i < textBoxes.size(); i++)
        {
      	  TextSquare temp = (TextSquare) textBoxes.get(i);
      	  if(temp!= null && par1 >= temp.x+var5 && par2 >= temp.y+var6 && par1 <= temp.x+temp.xSize+var5 && par2 <= temp.y+temp.ySize+var6)
      	  {
      		String[] text = {temp.hoverText};
    			List temp2 = Arrays.asList(text);
    			drawHoveringText(temp2, par1, par2, fontRendererObj);
      	  }
        }
        
    }
}