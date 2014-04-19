package com.sudwood.advancedutilities.client.gui;

import java.util.Collection;
import java.util.Iterator;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

import org.lwjgl.opengl.GL11;

import com.sudwood.advancedutilities.AdvancedUtilities;
import com.sudwood.advancedutilities.container.InventoryItem;
import com.sudwood.advancedutilities.items.AdvancedUtilitiesItems;
import com.sudwood.advancedutilities.items.ItemArmorSteamJetpack;
import com.sudwood.advancedutilities.items.ItemJackHammer;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

//
// GuiBuffBar implements a simple status bar at the top of the screen which 
// shows the current buffs/debuffs applied to the character.
//
public class GuiSteamBar extends Gui
{
  private Minecraft mc;
  private static final ResourceLocation texture = new ResourceLocation(AdvancedUtilities.MODID, "textures/gui/steamoverlay.png");

  public GuiSteamBar(Minecraft mc)
  {
    super();
    
    // We need this to invoke the render engine.
    this.mc = mc;
  }

  private static final int BUFF_ICON_SIZE = 18;
  private static final int BUFF_ICON_SPACING = BUFF_ICON_SIZE + 2; // 2 pixels between buff icons
  private static final int BUFF_ICON_BASE_U_OFFSET = 0;
  private static final int BUFF_ICON_BASE_V_OFFSET = 198;
  private static final int BUFF_ICONS_PER_ROW = 8;
  
  //
  // This event is called by GuiIngameForge during each frame by
  // GuiIngameForge.pre() and GuiIngameForce.post().
  //
  @SubscribeEvent(priority = EventPriority.NORMAL)
  public void onRenderExperienceBar(RenderGameOverlayEvent event)
  {
    // 
    // We draw after the ExperienceBar has drawn.  The event raised by GuiIngameForge.pre()
    // will return true from isCancelable.  If you call event.setCanceled(true) in
    // that case, the portion of rendering which this event represents will be canceled.
    // We want to draw *after* the experience bar is drawn, so we make sure isCancelable() returns
    // false and that the eventType represents the ExperienceBar event.
    if(event.isCancelable() || event.type != ElementType.EXPERIENCE)
    {      
      return;
    }

    // Starting position for the buff bar - 2 pixels from the top left corner.
    int xPos = 2;
    int yPos = 2;

    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    GL11.glDisable(GL11.GL_LIGHTING);   
    this.mc.getTextureManager().bindTexture(texture);   
    if(this.mc.currentScreen == null && !mc.gameSettings.showDebugInfo && mc.thePlayer != null && ((mc.thePlayer.getCurrentEquippedItem() != null&& mc.thePlayer.getCurrentEquippedItem().getItem() == AdvancedUtilitiesItems.pnumaticGun) || (mc.thePlayer.getCurrentEquippedItem() != null&& mc.thePlayer.getCurrentEquippedItem().getItem() == AdvancedUtilitiesItems.jackHammer) || (mc.thePlayer.getCurrentArmor(2)!=null && mc.thePlayer.getCurrentArmor(2).getItem() == AdvancedUtilitiesItems.steamJetpack)))
    {
    	this.drawTexturedModalRect(xPos, yPos, 1, 1, 9, 30);
    	int steam = 0;
    	int maxSteam = 0;
    	boolean hasBullets = false;
    	boolean isJetpack = false;
    	boolean isJackHammer = false;
    	int jackSteam = 0;
    	int jetSteam = 0;
    	int numBullets = 0;
    	if(mc.thePlayer.getCurrentEquippedItem()!=null && mc.thePlayer.getCurrentEquippedItem().getItem() == AdvancedUtilitiesItems.pnumaticGun)
    	{
    		if(mc.thePlayer.getCurrentEquippedItem().getTagCompound()!=null)
    		{
	    		NBTTagCompound tag = mc.thePlayer.getCurrentEquippedItem().getTagCompound();
	    		steam += tag.getInteger("tankAmount");
	    		maxSteam += tag.getInteger("maxTankAmount");
    		}
    		InventoryItem inv = new InventoryItem(mc.thePlayer.getCurrentEquippedItem());
    		if(inv.hasItem(AdvancedUtilitiesItems.bronzeBullet))
    		{
    			hasBullets = true;
    			numBullets = inv.getTotalItems(AdvancedUtilitiesItems.bronzeBullet);
    		}
    	}
    	if(mc.thePlayer.getCurrentEquippedItem()!=null && mc.thePlayer.getCurrentEquippedItem().getItem() == AdvancedUtilitiesItems.jackHammer)
    	{
    		if(mc.thePlayer.getCurrentEquippedItem().getTagCompound()!=null)
    		{
	    		NBTTagCompound tag = mc.thePlayer.getCurrentEquippedItem().getTagCompound();
	    		steam += tag.getInteger("tankAmount");
	    		jackSteam = tag.getInteger("tankAmount");
	    		maxSteam += tag.getInteger("maxTankAmount");
    		}
    		isJackHammer = true;
    	}
    	if(mc.thePlayer.getCurrentArmor(2)!=null && mc.thePlayer.getCurrentArmor(2).getItem() == AdvancedUtilitiesItems.steamJetpack)
    	{
    		if(mc.thePlayer.getCurrentArmor(2).getTagCompound()!=null)
    		{
	    		NBTTagCompound tag = mc.thePlayer.getCurrentArmor(2).getTagCompound();
	    		steam += tag.getInteger("tankAmount");
	    		jetSteam = tag.getInteger("tankAmount");
	    		maxSteam += tag.getInteger("maxTankAmount");
    		}
    		isJetpack = true;
    	}
    	if(maxSteam > 0)
    	{
    		int scaled = steam * 30/ maxSteam;
    		this.drawTexturedModalRect(xPos, yPos+30-scaled, 17, 30-scaled, 9, scaled+1);
    	}
    	this.drawString(mc.fontRenderer, "Steam: "+steam+" / "+maxSteam+" mB", 15, 2, 0xFFFFFF);
    	if(isJackHammer && !isJetpack)
    	{
    		this.drawString(mc.fontRenderer, "BlocksRemaining: "+jackSteam / ItemJackHammer.steamUse, 15, 12, 0xFFFFFF);
    	}
    	if(isJackHammer && isJetpack)
    	{
    		this.drawString(mc.fontRenderer, "Blocks Remaining: "+jackSteam / ItemJackHammer.steamUse, 15, 22, 0xFFFFFF);
    		this.drawString(mc.fontRenderer, "Flight Time Remaining: "+jetSteam / (ItemArmorSteamJetpack.steamUse*20)+" s", 15, 12, 0xFFFFFF);
    	}
    	if(hasBullets && !isJetpack)
    	{
    		this.drawString(mc.fontRenderer, "Bullets: "+numBullets+" / 320", 15, 12, 0xFFFFFF);
    	}
    	if(hasBullets && isJetpack)
    	{
    		this.drawString(mc.fontRenderer, "Bullets: "+numBullets+" / 320", 15, 22, 0xFFFFFF);
    		this.drawString(mc.fontRenderer, "Flight Time Remaining: "+jetSteam / (ItemArmorSteamJetpack.steamUse*20)+" s", 15, 12, 0xFFFFFF);
    	}
    	if(!isJackHammer&&!hasBullets&&isJetpack)
    	{
    		this.drawString(mc.fontRenderer, "Flight Time Remaining: "+jetSteam / (ItemArmorSteamJetpack.steamUse*20)+" s", 15, 12, 0xFFFFFF);
    	}
    }
   
  }
}