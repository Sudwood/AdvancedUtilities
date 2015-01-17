package com.sudwood.advancedutilities.client.gui;

import java.util.ArrayList;
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
import com.sudwood.advancedutilities.ExtendedPlayer;
import com.sudwood.advancedutilities.client.ClientRegistering;
import com.sudwood.advancedutilities.packets.PacketForge;
import com.sudwood.advancedutilities.packets.PacketSkillMenu;

//
// GuiBuffBar implements a simple status bar at the top of the screen which 
// shows the current buffs/debuffs applied to the character.
//
public class GuiSkills extends GuiScreen
{
	
  private ArrayList textBoxes = new ArrayList();
  private Minecraft mc;
  private static final ResourceLocation texture = new ResourceLocation(AdvancedUtilities.MODID, "textures/gui/skillsgui.png");
  protected int xSize = 176;
  /** The Y size of the inventory window in pixels. */
  protected int ySize = 166;
  boolean canSend = true;
  private String speedText = "";
  private String hasteText = "";
  private String healthText = "";
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
      this.fontRendererObj.drawString(speedText, 200, 150, 4210752);
      this.fontRendererObj.drawString(hasteText, 200, 150, 4210752);
      this.fontRendererObj.drawString(healthText, 200, 150, 4210752);
  }
  
  public boolean doesGuiPauseGame()
  {
      return false;
  }
  @Override
  public void actionPerformed(GuiButton button)
  {
	  ExtendedPlayer player = ExtendedPlayer.get(Minecraft.getMinecraft().thePlayer);
	  if(player!= null && player.skills != null && canSend)
	  {
		  byte[] skills = {0, 0, 0};
		    switch(button.id)
		    {
		    case 0:
		    	if(player.skills[0] == 0 && player.skills[1] == 0 && player.skills[2] == 0)
		    	{
			    	skills[0] = 1;
			    	AdvancedUtilities.network.sendToServer(new PacketSkillMenu(skills, (byte)20));
			    	Minecraft.getMinecraft().thePlayer.experienceLevel -= 20;
			    	this.resetUI();
			    	canSend = false;
		    	}
		    	break;
		    case 1:
		    	if(player.skills[0] == 1 && player.skills[1] == 0 && player.skills[2] == 0)
		    	{
			    	skills[0] = 2;
			    	AdvancedUtilities.network.sendToServer(new PacketSkillMenu(skills, (byte)30));
			    	Minecraft.getMinecraft().thePlayer.experienceLevel -= 30;
			    	this.resetUI();
			    	((GuiButton)this.buttonList.get(1)).enabled = false;
			    	((GuiButton)this.buttonList.get(4)).enabled = false;
			    	((GuiButton)this.buttonList.get(7)).enabled = false;
			    	canSend = false;
		    	}
		    	else if(player.skills[1] == 1 || player.skills[2] == 1)
		    	{
		    		skills[0] = 1;
		    		skills[1] = player.skills[1];
		    		skills[2] = player.skills[2];
			    	AdvancedUtilities.network.sendToServer(new PacketSkillMenu(skills, (byte)30));
			    	Minecraft.getMinecraft().thePlayer.experienceLevel -= 30;
			    	this.resetUI();
			    	((GuiButton)this.buttonList.get(1)).enabled = false;
			    	((GuiButton)this.buttonList.get(4)).enabled = false;
			    	((GuiButton)this.buttonList.get(7)).enabled = false;
			    	canSend = false;
		    	}
		    	break;
		    case 2:
		    	System.out.println(player.skills[0]+" , "+player.skills[1]+" , "+player.skills[2]);
		    	if(player.skills[0] == 2 && player.skills[1] == 0 && player.skills[2] == 0)
		    	{
			    	skills[0] = 3;
			    	AdvancedUtilities.network.sendToServer(new PacketSkillMenu(skills, (byte)500));
			    	System.out.println("Didsometing");
			    	Minecraft.getMinecraft().thePlayer.experienceLevel -= 50;
			    	this.resetUI();
			    	((GuiButton)this.buttonList.get(2)).enabled = false;
			    	((GuiButton)this.buttonList.get(5)).enabled = false;
			    	((GuiButton)this.buttonList.get(8)).enabled = false;
			    	canSend = false;
		    	}
		    	else if((player.skills[1] == 1 || player.skills[2] == 1) && player.skills[0] == 1)
		    	{
		    		skills[0] = 2;
		    		skills[1] = player.skills[1];
		    		skills[2] = player.skills[2];
			    	AdvancedUtilities.network.sendToServer(new PacketSkillMenu(skills, (byte)50));
			    	Minecraft.getMinecraft().thePlayer.experienceLevel -= 50;
			    	this.resetUI();
			    	((GuiButton)this.buttonList.get(2)).enabled = false;
			    	((GuiButton)this.buttonList.get(5)).enabled = false;
			    	((GuiButton)this.buttonList.get(8)).enabled = false;
			    	canSend = false;
		    	}
		    	else if(player.skills[1] == 2 || player.skills[2] == 2)
		    	{
		    		skills[0] = 1;
		    		skills[1] = player.skills[1];
		    		skills[2] = player.skills[2];
			    	AdvancedUtilities.network.sendToServer(new PacketSkillMenu(skills, (byte)50));
			    	Minecraft.getMinecraft().thePlayer.experienceLevel -= 50;
			    	this.resetUI();
			    	((GuiButton)this.buttonList.get(2)).enabled = false;
			    	((GuiButton)this.buttonList.get(5)).enabled = false;
			    	((GuiButton)this.buttonList.get(8)).enabled = false;
			    	canSend = false;
		    	}
		    	else if(player.skills[1] == 1 && player.skills[2] == 1)
		    	{
		    		skills[0] = 1;
		    		skills[2] = player.skills[2];
		    		skills[1] = player.skills[1];
			    	AdvancedUtilities.network.sendToServer(new PacketSkillMenu(skills, (byte)50));
			    	Minecraft.getMinecraft().thePlayer.experienceLevel -= 50;
			    	this.resetUI();
			    	((GuiButton)this.buttonList.get(2)).enabled = false;
			    	((GuiButton)this.buttonList.get(5)).enabled = false;
			    	((GuiButton)this.buttonList.get(8)).enabled = false;
			    	canSend = false;
		    	}
		    	break;
		    case 3:
		    	if(player.skills[1] == 0 && player.skills[0] == 0 && player.skills[2] == 0)
		    	{
			    	skills[1] = 1;
			    	AdvancedUtilities.network.sendToServer(new PacketSkillMenu(skills, (byte)20));
			    	Minecraft.getMinecraft().thePlayer.experienceLevel -= 20;
			    	this.resetUI();
			    	canSend = false;
		    	}
		    	break;
		    case 4:
		    	if(player.skills[1] == 1 && player.skills[0] == 0 && player.skills[2] == 0)
		    	{
			    	skills[1] = 2;
			    	AdvancedUtilities.network.sendToServer(new PacketSkillMenu(skills, (byte)30));
			    	Minecraft.getMinecraft().thePlayer.experienceLevel -= 30;
			    	this.resetUI();
			    	((GuiButton)this.buttonList.get(1)).enabled = false;
			    	((GuiButton)this.buttonList.get(4)).enabled = false;
			    	((GuiButton)this.buttonList.get(7)).enabled = false;
			    	canSend = false;
		    	}
		    	else if(player.skills[0] == 1 || player.skills[2] == 1)
		    	{
		    		skills[1] = 1;
		    		skills[0] = player.skills[0];
		    		skills[2] = player.skills[2];
			    	AdvancedUtilities.network.sendToServer(new PacketSkillMenu(skills, (byte)30));
			    	Minecraft.getMinecraft().thePlayer.experienceLevel -= 30;
			    	this.resetUI();
			    	((GuiButton)this.buttonList.get(1)).enabled = false;
			    	((GuiButton)this.buttonList.get(4)).enabled = false;
			    	((GuiButton)this.buttonList.get(7)).enabled = false;
			    	canSend = false;
		    	}
		    	break;
		    case 5:
		    	if(player.skills[1] == 2 && player.skills[0] == 0 && player.skills[2] == 0)
		    	{
			    	skills[1] = 3;
			    	AdvancedUtilities.network.sendToServer(new PacketSkillMenu(skills, (byte)500));
			    	Minecraft.getMinecraft().thePlayer.experienceLevel -= 50;
			    	this.resetUI();
			    	((GuiButton)this.buttonList.get(2)).enabled = false;
			    	((GuiButton)this.buttonList.get(5)).enabled = false;
			    	((GuiButton)this.buttonList.get(8)).enabled = false;
			    	canSend = false;
		    	}
		    	else if((player.skills[0] == 1 || player.skills[2] == 1) && player.skills[1] == 1)
		    	{
		    		skills[1] = 2;
		    		skills[0] = player.skills[0];
		    		skills[2] = player.skills[2];
			    	AdvancedUtilities.network.sendToServer(new PacketSkillMenu(skills, (byte)50));
			    	Minecraft.getMinecraft().thePlayer.experienceLevel -= 50;
			    	this.resetUI();
			    	((GuiButton)this.buttonList.get(2)).enabled = false;
			    	((GuiButton)this.buttonList.get(5)).enabled = false;
			    	((GuiButton)this.buttonList.get(8)).enabled = false;
			    	canSend = false;
		    	}
		    	else if(player.skills[0] == 2 || player.skills[2] == 2)
		    	{
		    		skills[1] = 1;
		    		skills[0] = player.skills[0];
		    		skills[2] = player.skills[2];
			    	AdvancedUtilities.network.sendToServer(new PacketSkillMenu(skills, (byte)50));
			    	Minecraft.getMinecraft().thePlayer.experienceLevel -= 50;
			    	this.resetUI();
			    	((GuiButton)this.buttonList.get(2)).enabled = false;
			    	((GuiButton)this.buttonList.get(5)).enabled = false;
			    	((GuiButton)this.buttonList.get(8)).enabled = false;
			    	canSend = false;
		    	}
		    	else if(player.skills[0] == 1 && player.skills[2] == 1)
		    	{
		    		skills[1] = 1;
		    		skills[0] = player.skills[0];
		    		skills[2] = player.skills[2];
			    	AdvancedUtilities.network.sendToServer(new PacketSkillMenu(skills, (byte)50));
			    	Minecraft.getMinecraft().thePlayer.experienceLevel -= 50;
			    	this.resetUI();
			    	((GuiButton)this.buttonList.get(2)).enabled = false;
			    	((GuiButton)this.buttonList.get(5)).enabled = false;
			    	((GuiButton)this.buttonList.get(8)).enabled = false;
			    	canSend = false;
		    	}
		    	break;
		    case 6:
		    	if(player.skills[2] == 0 && player.skills[0] == 0 && player.skills[1] == 0)
		    	{
			    	skills[2] = 1;
			    	AdvancedUtilities.network.sendToServer(new PacketSkillMenu(skills, (byte)20));
			    	Minecraft.getMinecraft().thePlayer.experienceLevel -= 20;
			    	this.resetUI();
			    	canSend = false;
		    	}
		    	break;
		    case 7:
		    	if(player.skills[2] == 1 && player.skills[0] == 0 && player.skills[1] == 0)
		    	{
			    	skills[2] = 2;
			    	AdvancedUtilities.network.sendToServer(new PacketSkillMenu(skills, (byte)30));
			    	Minecraft.getMinecraft().thePlayer.experienceLevel -= 30;
			    	this.resetUI();
			    	((GuiButton)this.buttonList.get(1)).enabled = false;
			    	((GuiButton)this.buttonList.get(4)).enabled = false;
			    	((GuiButton)this.buttonList.get(7)).enabled = false;
			    	canSend = false;
		    	}
		    	else if(player.skills[0] == 1 || player.skills[1] == 1)
		    	{
		    		skills[2] = 1;
		    		skills[0] = player.skills[0];
		    		skills[1] = player.skills[1];
			    	AdvancedUtilities.network.sendToServer(new PacketSkillMenu(skills, (byte)30));
			    	Minecraft.getMinecraft().thePlayer.experienceLevel -= 30;
			    	this.resetUI();
			    	((GuiButton)this.buttonList.get(1)).enabled = false;
			    	((GuiButton)this.buttonList.get(4)).enabled = false;
			    	((GuiButton)this.buttonList.get(7)).enabled = false;
			    	canSend = false;
		    	}
		    	break;
		    case 8:
		    	if(player.skills[2] == 2 && player.skills[0] == 0 && player.skills[1] == 0)
		    	{
			    	skills[2] = 3;
			    	AdvancedUtilities.network.sendToServer(new PacketSkillMenu(skills, (byte)500));
			    	Minecraft.getMinecraft().thePlayer.experienceLevel -= 50;
			    	this.resetUI();
			    	((GuiButton)this.buttonList.get(2)).enabled = false;
			    	((GuiButton)this.buttonList.get(5)).enabled = false;
			    	((GuiButton)this.buttonList.get(8)).enabled = false;
			    	canSend = false;
		    	}
		    	else if((player.skills[0] == 1 || player.skills[1] == 1) && player.skills[2] == 1)
		    	{
		    		skills[2] = 2;
		    		skills[0] = player.skills[0];
		    		skills[1] = player.skills[1];
			    	AdvancedUtilities.network.sendToServer(new PacketSkillMenu(skills, (byte)50));
			    	Minecraft.getMinecraft().thePlayer.experienceLevel -= 50;
			    	this.resetUI();
			    	((GuiButton)this.buttonList.get(2)).enabled = false;
			    	((GuiButton)this.buttonList.get(5)).enabled = false;
			    	((GuiButton)this.buttonList.get(8)).enabled = false;
			    	canSend = false;
		    	}
		    	else if(player.skills[0] == 2 || player.skills[1] == 2)
		    	{
		    		skills[2] = 1;
		    		skills[0] = player.skills[0];
		    		skills[1] = player.skills[1];
			    	AdvancedUtilities.network.sendToServer(new PacketSkillMenu(skills, (byte)50));
			    	Minecraft.getMinecraft().thePlayer.experienceLevel -= 50;
			    	this.resetUI();
			    	((GuiButton)this.buttonList.get(2)).enabled = false;
			    	((GuiButton)this.buttonList.get(5)).enabled = false;
			    	((GuiButton)this.buttonList.get(8)).enabled = false;
			    	canSend = false;
		    	}
		    	else if(player.skills[0] == 1 && player.skills[1] == 1)
		    	{
		    		skills[2] = 1;
		    		skills[0] = player.skills[0];
		    		skills[1] = player.skills[1];
			    	AdvancedUtilities.network.sendToServer(new PacketSkillMenu(skills, (byte)50));
			    	Minecraft.getMinecraft().thePlayer.experienceLevel -= 50;
			    	this.resetUI();
			    	((GuiButton)this.buttonList.get(2)).enabled = false;
			    	((GuiButton)this.buttonList.get(5)).enabled = false;
			    	((GuiButton)this.buttonList.get(8)).enabled = false;
			    	canSend = false;
		    	}
		    	break;
		    /*case 9:
		    	skills[0] = 0;
		    	skills[1] = 0;
		    	skills[2] = 0;
		    	AdvancedUtilities.network.sendToServer(new PacketSkillMenu(skills, (byte)0));
		    	this.resetUI();
		    	break;*/
		    }
	  }
  }
  
  
  public void resetUI()
  {
	  ExtendedPlayer player = ExtendedPlayer.get(Minecraft.getMinecraft().thePlayer);
	  for(int i = 0; i < 9; i++)
      {
    	  textBoxes.add(i, null);
      }
      for(int i = 3; i < 9; i++)
      {
    	  ((GuiButton)this.buttonList.get(i)).enabled = false;
      }
      textBoxes.add(0, new TextSquare(140, 60, 20, 20, "Speed Level 1"));
      textBoxes.add(3, new TextSquare(170, 60, 20, 20, "Haste Level 1"));
      textBoxes.add(6, new TextSquare(200, 60, 20, 20, "Health Level 1"));
      textBoxes.add(1, new TextSquare(140, 85, 20, 20, "Speed Level 2"));
      textBoxes.add(4, new TextSquare(170, 85, 20, 20, "Haste Level 2"));
      textBoxes.add(7, new TextSquare(200, 85, 20, 20, "Health Level 2"));
      textBoxes.add(2, new TextSquare(140, 110, 20, 20, "Speed Level 3"));
      textBoxes.add(5, new TextSquare(170, 110, 20, 20, "Haste Level 3"));
      textBoxes.add(8, new TextSquare(200, 110, 20, 20, "Health Level 3"));
      textBoxes.add(9, new TextSquare(255, 210, 20, 20, "RESET"));

      speedText = "Speed Level 0";
      hasteText = "Haste Level 0";
      healthText = "Health Level 0";
      int totals = 0;
      if(player!=null && player.skills!= null && player.skills.length >= 3)
      {
    	  textBoxes.add(0, new TextSquare(140, 60, 20, 20, "Speed Level 1"));
    	  textBoxes.add(1, new TextSquare(140, 85, 20, 20, "Speed Level 2"));
    	  textBoxes.add(2, new TextSquare(140, 110, 20, 20, "Speed Level 3"));
    	  textBoxes.add(3, new TextSquare(170, 60, 20, 20, "Haste Level 1"));
    	  textBoxes.add(4, new TextSquare(170, 85, 20, 20, "Haste Level 2"));
    	  textBoxes.add(5, new TextSquare(170, 110, 20, 20, "Haste Level 3"));
          textBoxes.add(6, new TextSquare(200, 60, 20, 20, "Health Level 1"));
          textBoxes.add(7, new TextSquare(200, 85, 20, 20, "Health Level 2"));
          textBoxes.add(8, new TextSquare(200, 110, 20, 20, "Health Level 3"));
    	  speedText = "Speed Level: "+player.skills[0];
          hasteText = "Haste Level: "+player.skills[1];
          healthText = "Health Level: "+player.skills[2];
          totals = player.skills[0]+player.skills[1]+player.skills[2];
          if(totals == 0)
          {
        	  ((GuiButton)this.buttonList.get(0)).enabled = true;
	    	  ((GuiButton)this.buttonList.get(1)).enabled = false;
	    	  ((GuiButton)this.buttonList.get(2)).enabled = false;
	    	  ((GuiButton)this.buttonList.get(3)).enabled = true;
    		  ((GuiButton)this.buttonList.get(4)).enabled = false;
    		  ((GuiButton)this.buttonList.get(5)).enabled = false;
    		  ((GuiButton)this.buttonList.get(6)).enabled = true;
			  ((GuiButton)this.buttonList.get(7)).enabled = false;
			  ((GuiButton)this.buttonList.get(8)).enabled = false;
          }
	      if(totals >= 1)
	      {
	    	  ((GuiButton)this.buttonList.get(0)).enabled = false;
	    	  ((GuiButton)this.buttonList.get(1)).enabled = true;
	    	  ((GuiButton)this.buttonList.get(2)).enabled = false;
	    	  ((GuiButton)this.buttonList.get(3)).enabled = false;
    		  ((GuiButton)this.buttonList.get(4)).enabled = true;
    		  ((GuiButton)this.buttonList.get(5)).enabled = false;
    		  ((GuiButton)this.buttonList.get(6)).enabled = false;
			  ((GuiButton)this.buttonList.get(7)).enabled = true;
			  ((GuiButton)this.buttonList.get(8)).enabled = false;
	    	  if(totals >= 2)
	    	  {
		    	  ((GuiButton)this.buttonList.get(1)).enabled = false;
		    	  ((GuiButton)this.buttonList.get(2)).enabled = true;
	    		  ((GuiButton)this.buttonList.get(4)).enabled = false;
	    		  ((GuiButton)this.buttonList.get(5)).enabled = true;
	    		  ((GuiButton)this.buttonList.get(7)).enabled = false;
				  ((GuiButton)this.buttonList.get(8)).enabled = true;
	    		  if(totals >= 3)
	        	  {
	    			  ((GuiButton)this.buttonList.get(0)).enabled = false;
	    	    	  ((GuiButton)this.buttonList.get(1)).enabled = false;
	    	    	  ((GuiButton)this.buttonList.get(2)).enabled = false;
	    	    	  ((GuiButton)this.buttonList.get(3)).enabled = false;
	        		  ((GuiButton)this.buttonList.get(4)).enabled = false;
	        		  ((GuiButton)this.buttonList.get(5)).enabled = false;
	        		  ((GuiButton)this.buttonList.get(6)).enabled = false;
	    			  ((GuiButton)this.buttonList.get(7)).enabled = false;
	    			  ((GuiButton)this.buttonList.get(8)).enabled = false;
	    			  

	        	  }
	    	  }
	      }
	    
      }
  }
  
  public void initGui()
  {
      this.buttonList.add(new GuiButton(0, 140, 60, 20, 20, ""));
      this.buttonList.add(new GuiButton(1, 140, 85, 20, 20, ""));
      this.buttonList.add(new GuiButton(2, 140, 110, 20, 20, ""));
      this.buttonList.add(new GuiButton(3, 170, 60, 20, 20, ""));
      this.buttonList.add(new GuiButton(4, 170, 85, 20, 20, ""));
      this.buttonList.add(new GuiButton(5, 170, 110, 20, 20, ""));
      this.buttonList.add(new GuiButton(6, 200, 60, 20, 20, ""));
      this.buttonList.add(new GuiButton(7, 200, 85, 20, 20, ""));
      this.buttonList.add(new GuiButton(8, 200, 110, 20, 20, ""));
      //this.buttonList.add(new GuiButton(9, 255, 210, 20, 20, "RESET"));
      resetUI();
      
      
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
      
      for(int i = 0; i < textBoxes.size(); i++)
      {
    	  TextSquare temp = (TextSquare) textBoxes.get(i);
    	  if(temp!= null && par1 >= temp.x && par2 >= temp.y && par1 <= temp.x+temp.xSize && par2 <= temp.y+temp.ySize)
    	  {
    		String[] text = {temp.hoverText};
  			List temp2 = Arrays.asList(text);
  			drawHoveringText(temp2, par1, par2, fontRendererObj);
    	  }
      }
      
      this.fontRendererObj.drawString("Skills", this.xSize / 2 - this.fontRendererObj.getStringWidth("Skills") / 2, 6, 4210752);
      this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 96 + 2, 4210752);
      this.fontRendererObj.drawString(speedText, 220, 90, 16777215);
      this.fontRendererObj.drawString(hasteText, 220, 100, 16777215);
      this.fontRendererObj.drawString(healthText, 220, 110, 16777215);
      
      

      
  }

}

