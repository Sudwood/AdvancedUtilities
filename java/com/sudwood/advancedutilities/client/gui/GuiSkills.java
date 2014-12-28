package com.sudwood.advancedutilities.client.gui;

import java.util.Arrays;
import java.util.List;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiKeyBindingList;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.gui.GuiOptionSlider;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ResourceLocation;

import com.sudwood.advancedutilities.AdvancedUtilities;
import com.sudwood.advancedutilities.client.ClientRegistering;

//
// GuiBuffBar implements a simple status bar at the top of the screen which 
// shows the current buffs/debuffs applied to the character.
//
public class GuiSkills extends GuiScreen
{
  private Minecraft mc;
  private static final ResourceLocation texture = new ResourceLocation(AdvancedUtilities.MODID, "textures/gui/skillsgui.png");
  protected int xSize = 176;
  /** The Y size of the inventory window in pixels. */
  protected int ySize = 166;
  public GuiSkills(Minecraft mc)
  {
    super();
    
    // We need this to invoke the render engine.
    this.mc = mc;
    res = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
  }

  private ScaledResolution res = null;
  
  protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_)
  {
      this.fontRendererObj.drawString("Skills", this.xSize / 2 - this.fontRendererObj.getStringWidth("Skills") / 2, 6, 4210752);
      this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 96 + 2, 4210752);
  }
  
  public boolean doesGuiPauseGame()
  {
      return false;
  }
  
  public void initGui()
  {
      this.buttonList.add(new GuiButton(0, 140, 60, 20, 20, "Haste"));
      this.buttonList.add(new GuiButton(1, 170, 60, 20, 20, "Speed"));
      this.buttonList.add(new GuiButton(2, 200, 60, 20, 20, "Health"));
      this.buttonList.add(new GuiButton(3, 140, 90, 20, 20, "Haste"));
      this.buttonList.add(new GuiButton(4, 170, 90, 20, 20, "Speed"));
      this.buttonList.add(new GuiButton(5, 200, 90, 20, 20, "Health"));
      this.buttonList.add(new GuiButton(6, 140, 120, 20, 20, "Haste"));
      this.buttonList.add(new GuiButton(7, 170, 120, 20, 20, "Speed"));
      this.buttonList.add(new GuiButton(8, 200, 120, 20, 20, "Health"));
  }
  
  @Override
  protected void keyTyped(char c, int keyCode) 
  {
	    // make sure you call super!!!
	    super.keyTyped(c, keyCode);
	    // 1 is the Esc key, and we made our keybinding array public and static so we can access it here
	    if (c == 1 || keyCode == ClientRegistering.keyhandle.skills.getKeyCode())
	    {
	    	mc.thePlayer.closeScreen();
	    }
  }


  
  @Override
  public void drawScreen(int par1, int par2, float par3)
  {
	  GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      this.mc.getTextureManager().bindTexture(texture);
      int k = (this.width - this.xSize) / 2;
      int l = (this.height - this.ySize) / 2;
      this.drawTexturedModalRect(k-30, l, 0, 0, this.xSize+60, this.ySize);
  	super.drawScreen(par1, par2, par3);
  	int var5 = (this.width - this.xSize) / 2;
      int var6 = (this.height - this.ySize) / 2;
      if(par1 >= 17+var5-30 && par2 >= 22+var6 && par1 <= 36+var5-30 && par2 <= 41+var6)
		{
    	  String[] text = {"Level 20 Skills"};
			List temp = Arrays.asList(text);
			drawHoveringText(temp, par1, par2, fontRendererObj);
		}
      if(par1 >= 18+var5-30 && par2 >= 47+var6 && par1 <= 36+var5-30 && par2 <= 66+var6)
		{
    	  String[] text = {"Level 30 Skills"};
			List temp = Arrays.asList(text);
			drawHoveringText(temp, par1, par2, fontRendererObj);
		}
      if(par1 >= 18+var5-30 && par2 >= 72+var6 && par1 <= 36+var5-30 && par2 <= 91+var6)
		{
    	  String[] text = {"Level 50 Skills"};
			List temp = Arrays.asList(text);
			drawHoveringText(temp, par1, par2, fontRendererObj);
		}
      if(par1 >= 158+var5-30 && par2 >= 22+var6 && par1 <= 176+var5-30 && par2 <= 41+var6)
		{
  	  String[] text = {"Level 100 Skills"};
			List temp = Arrays.asList(text);
			drawHoveringText(temp, par1, par2, fontRendererObj);
		}
      
      

      
  }

}