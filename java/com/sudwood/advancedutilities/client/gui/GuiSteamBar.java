package com.sudwood.advancedutilities.client.gui;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

import org.lwjgl.opengl.GL11;

import com.sudwood.advancedutilities.AdvancedUtilities;
import com.sudwood.advancedutilities.ExtendedPlayer;
import com.sudwood.advancedutilities.config.HudOptions;
import com.sudwood.advancedutilities.container.InventoryItem;
import com.sudwood.advancedutilities.container.RebreatherInv;
import com.sudwood.advancedutilities.entity.minecart.EntityTankCart;
import com.sudwood.advancedutilities.items.AdvancedUtilitiesItems;
import com.sudwood.advancedutilities.items.ItemArmorSteamJetpack;
import com.sudwood.advancedutilities.items.ItemJackHammer;
import com.sudwood.advancedutilities.tileentity.ISteamTank;

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
  static RenderItem ri = new RenderItem();
  public GuiSteamBar(Minecraft mc)
  {
    super();
    
    // We need this to invoke the render engine.
    this.mc = mc;
    res = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
  }

  private ScaledResolution res = null;
  
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
    if(HudOptions.displayFluidHud){
    	ArrayList fluids = new ArrayList();
    	MovingObjectPosition mop = Minecraft.getMinecraft().renderViewEntity.rayTrace(200, 1.0F);
    	if(mop != null)
    	{
	    	int blockHitSide = mop.sideHit;
	    	Block look = Minecraft.getMinecraft().theWorld.getBlock(mop.blockX, mop.blockY, mop.blockZ) ; 
	    	World world = Minecraft.getMinecraft().theWorld;
	    	if(look instanceof BlockContainer)
	    	{
	    		if(world.getTileEntity(mop.blockX, mop.blockY, mop.blockZ) instanceof ISteamTank)
	    		{
	    			FluidTankInfo[] info = ((IFluidHandler)world.getTileEntity(mop.blockX, mop.blockY, mop.blockZ)).getTankInfo(ForgeDirection.UNKNOWN);
	    			for(int i = 0; i < info.length; i++)
	    			{
	    				if(info[i].fluid != null)
	    				{
	    					fluids.add(info[i].fluid.getFluid().getName()+": "+info[i].fluid.amount+" / "+info[i].capacity+" mB");
	    				}
	    			}
	    			
	    		}
	    	}
	    	if(mop.entityHit!= null)
	    	{
	    		if(mop.entityHit instanceof EntityTankCart)
	    		{
	    			EntityTankCart ent = (EntityTankCart) mop.entityHit;
	    			if(ent.tank.getFluidAmount() > 0)
	    				fluids.add(ent.tank.getFluid().getFluid().getName()+": "+ent.tank.getFluidAmount()+" / "+ent.tank.getCapacity()+" mB");
	    		}
	    	}
    		for(int i = 0; i < fluids.size(); i++)
			{
				Minecraft.getMinecraft().fontRenderer.drawStringWithShadow((String)fluids.get(i), HudOptions.fluidX, HudOptions.fluidY+12*i, 0xEEEEEE);
				
			}
    	}
    }
    if(HudOptions.displayBlockHud){
    	ArrayList fluids = new ArrayList();
    	MovingObjectPosition mop = Minecraft.getMinecraft().renderViewEntity.rayTrace(200, 1.0F);
    	if(mop != null)
    	{
	    	int blockHitSide = mop.sideHit;
	    	Block look = Minecraft.getMinecraft().theWorld.getBlock(mop.blockX, mop.blockY, mop.blockZ) ; 
	    	World world = Minecraft.getMinecraft().theWorld;
	    	boolean lower = false;
	    	if(look != null)
	    	{
	    		if(look instanceof BlockContainer && world.getTileEntity(mop.blockX, mop.blockY, mop.blockZ) instanceof ISteamTank)
	    			lower = true;
	    		fluids.add(StatCollector.translateToLocal(look.getUnlocalizedName().substring(5)+":"+world.getBlockMetadata(mop.blockX, mop.blockY, mop.blockZ)));
	    	}
    		for(int i = 0; i < fluids.size(); i++)
			{
    			if(lower == false)
				Minecraft.getMinecraft().fontRenderer.drawStringWithShadow((String)fluids.get(i), HudOptions.fluidX, HudOptions.fluidY+12*i, 0xEEEEEE);
    			else
    			{
    				Minecraft.getMinecraft().fontRenderer.drawStringWithShadow((String)fluids.get(i), HudOptions.fluidX, HudOptions.fluidY+12, 0xEEEEEE);
    			}
			}
    	}
    }
    // Starting position for the buff bar - 2 pixels from the top left corner.
    int xPos = 2;
    int yPos = 2;
    final int width = res.getScaledWidth();
    final int height = res.getScaledHeight();
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    GL11.glDisable(GL11.GL_LIGHTING);   
    this.mc.getTextureManager().bindTexture(texture);   
    if(mc.currentScreen == null && !mc.gameSettings.showDebugInfo && mc.thePlayer != null)
    {
    	int steam = 0;
    	int maxSteam = 0;
    	boolean hasBullets = false;
    	boolean isJetpack = false;
    	boolean isJackHammer = false;
    	boolean isRebreather = false;
    	int jackSteam = 0;
    	int jetSteam = 0;
    	int numBullets = 0;
    	int maxBullets = 0;
    	int food = 0;
    	int numrender = 0;
    	if(HudOptions.displaySteamHud && ((mc.thePlayer.getCurrentEquippedItem() != null&& mc.thePlayer.getCurrentEquippedItem().getItem() == AdvancedUtilitiesItems.pnumaticGun) || (mc.thePlayer.getCurrentEquippedItem() != null&& mc.thePlayer.getCurrentEquippedItem().getItem() == AdvancedUtilitiesItems.jackHammer) || (mc.thePlayer.getCurrentArmor(2)!=null && mc.thePlayer.getCurrentArmor(2).getItem() == AdvancedUtilitiesItems.steamJetpack) || (mc.thePlayer.getCurrentArmor(3)!=null && mc.thePlayer.getCurrentArmor(3).getItem() == AdvancedUtilitiesItems.rebreather)))
    	{
    		this.drawTexturedModalRect(width-(width-HudOptions.steamBarX), height-(height-HudOptions.steamBarY), 1, 1, 9, 30);
	    	if(mc.thePlayer.getCurrentEquippedItem()!=null && mc.thePlayer.getCurrentEquippedItem().getItem() == AdvancedUtilitiesItems.pnumaticGun)
	    	{
	    		if(mc.thePlayer.getCurrentEquippedItem().getTagCompound()!=null)
	    		{
		    		NBTTagCompound tag = mc.thePlayer.getCurrentEquippedItem().getTagCompound();
		    		steam += tag.getInteger("tankAmount");
		    		maxSteam += tag.getInteger("maxTankAmount");
	    		}
	    		InventoryItem inv = new InventoryItem(mc.thePlayer.getCurrentEquippedItem());
	    		if(inv.hasItemWithInvItem(AdvancedUtilitiesItems.bronzeBullet, AdvancedUtilitiesItems.bulletMagazine))
	    		{
	    			hasBullets = true;
	    			numBullets = inv.getTotalItemsWithInventoryItems(AdvancedUtilitiesItems.bronzeBullet, AdvancedUtilitiesItems.bulletMagazine);
	    			maxBullets = inv.getMaxSizeInventory(AdvancedUtilitiesItems.bulletMagazine)*64;
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
	    	if(mc.thePlayer.getCurrentArmor(3)!=null && mc.thePlayer.getCurrentArmor(3).getItem() == AdvancedUtilitiesItems.rebreather)
	    	{
	    		RebreatherInv inv = new RebreatherInv(mc.thePlayer.getCurrentArmor(3));
	    		food = inv.getFoodTotal();
	    		isRebreather = true;
	    	}
	    	if(maxSteam > 0)
	    	{
	    		int scaled = steam * 30/ maxSteam;
	    		this.drawTexturedModalRect(width-(width-HudOptions.steamBarX), height-(height-(HudOptions.steamBarY+30-scaled)), 17, 30-scaled, 9, scaled+1);
	    	}
	    	this.drawString(mc.fontRenderer, "Steam: "+steam+" / "+maxSteam+" mB", width-(width-15), height-(height-2), 0xFFFFFF);
	    	if(isJackHammer && !isJetpack)
	    	{
	    		this.drawString(mc.fontRenderer, "Blocks Remaining: "+jackSteam / ItemJackHammer.steamUse, width-(width-HudOptions.steamToolX), height-(height-HudOptions.steamToolY), 0xFFFFFF);
	    		numrender = 1;
	    	}
	    	if(isJackHammer && isJetpack)
	    	{
	    		this.drawString(mc.fontRenderer, "Blocks Remaining: "+jackSteam / ItemJackHammer.steamUse, width-(width-HudOptions.steamToolX), height-(height-(HudOptions.steamToolY+10)), 0xFFFFFF);
	    		this.drawString(mc.fontRenderer, "Flight Time Remaining: "+jetSteam / (ItemArmorSteamJetpack.steamUse*20)+" s", width-(width-HudOptions.steamJetPackTimeX), height-(height-HudOptions.steamJetPackTimeY), 0xFFFFFF);
	    		numrender = 1;
	    	}
	    	if(hasBullets && !isJetpack)
	    	{
	    		this.drawString(mc.fontRenderer, "Bullets: "+numBullets+" / "+maxBullets, width-(width-HudOptions.steamToolX), height-(height-HudOptions.steamToolY), 0xFFFFFF);
	    		numrender = 1;
	    	}
	    	if(hasBullets && isJetpack)
	    	{
	    		this.drawString(mc.fontRenderer, "Bullets: "+numBullets+" / "+maxBullets, width-(width-HudOptions.steamToolX), height-(height-(HudOptions.steamToolY+10)), 0xFFFFFF);
	    		this.drawString(mc.fontRenderer, "Flight Time Remaining: "+jetSteam / (ItemArmorSteamJetpack.steamUse*20)+" s", width-(width-HudOptions.steamJetPackTimeX), height-(height-HudOptions.steamJetPackTimeY), 0xFFFFFF);
	    		numrender = 1;
	    	}
	    	if(!isJackHammer&&!hasBullets&&isJetpack)
	    	{
	    		this.drawString(mc.fontRenderer, "Flight Time Remaining: "+jetSteam / (ItemArmorSteamJetpack.steamUse*20)+" s", width-(width-HudOptions.steamJetPackTimeX), height-(height-HudOptions.steamJetPackTimeY), 0xFFFFFF);
	    		numrender = 1;
	    	}
	    	if(isRebreather)
	    	{
	    		this.drawString(mc.fontRenderer, "Food Remaining: "+food, width-(width-HudOptions.steamToolX), height-(height-HudOptions.steamToolY-10*numrender), 0xFFFFFF);
	    	}
	    	// 16763955
	    	if(ExtendedPlayer.get(mc.thePlayer).toggleJetpack)
	    	{
	    		this.drawString(mc.fontRenderer, "Jetpack Enabled", width-(width-HudOptions.steamJetPackEnabledX), height-(height-HudOptions.steamJetPackEnabledY), 0xFFCC33);
	    	}
    	}
    	int armorOffset = 15;
    	if(HudOptions.displayArmorHud)
    	{
	    	for(int i = 0; i < 4; i++)
	    	{
	    		if(mc.thePlayer.getCurrentArmor(i)!= null && mc.thePlayer.getCurrentArmor(i).getItem() instanceof ItemArmor && mc.thePlayer.getCurrentArmor(i).getMaxDamage() > 0)
	    		{
	    			ItemStack armor = mc.thePlayer.getCurrentArmor(i);
	    			int dmg = armor.getItemDamageForDisplay();
	                int color = (int) Math.round(255.0D - dmg * 255.0D
	                        / armor.getMaxDamage());
	                int shiftedColor = 255 - color << 16 | color << 8;
	                if(i == 3)
	                	renderSlots(armor, mc.fontRenderer, 0, dmg, width-HudOptions.ArmorHudX,height-HudOptions.ArmorHudY +1, shiftedColor, armor.getMaxDamage());
	                if(i == 2)
	                {
	                	if(mc.thePlayer.getCurrentArmor(3) != null && mc.thePlayer.getCurrentArmor(3).getMaxDamage() > 0)
	                		renderSlots(armor, mc.fontRenderer, 0, dmg, width-HudOptions.ArmorHudX,height-HudOptions.ArmorHudY + 20, shiftedColor, armor.getMaxDamage());
	                	if(mc.thePlayer.getCurrentArmor(3) == null || mc.thePlayer.getCurrentArmor(3).getMaxDamage() <= 0)
	                		renderSlots(armor, mc.fontRenderer, 0, dmg, width-HudOptions.ArmorHudX,height-HudOptions.ArmorHudY +1, shiftedColor, armor.getMaxDamage());
	                }
	                if(i == 1)
	                {
	                	if(mc.thePlayer.getCurrentArmor(3) != null && mc.thePlayer.getCurrentArmor(3).getMaxDamage() > 0 && mc.thePlayer.getCurrentArmor(2) != null && mc.thePlayer.getCurrentArmor(2).getMaxDamage() > 0)
	                	renderSlots(armor, mc.fontRenderer, 0, dmg, width-HudOptions.ArmorHudX,height-HudOptions.ArmorHudY + 40, shiftedColor, armor.getMaxDamage());
	                	if((mc.thePlayer.getCurrentArmor(3) == null || mc.thePlayer.getCurrentArmor(3).getMaxDamage() <= 0) && mc.thePlayer.getCurrentArmor(2) != null && mc.thePlayer.getCurrentArmor(2).getMaxDamage() > 0)
	                		renderSlots(armor, mc.fontRenderer, 0, dmg, width-HudOptions.ArmorHudX,height-HudOptions.ArmorHudY + 20, shiftedColor, armor.getMaxDamage());
	                	if((mc.thePlayer.getCurrentArmor(3) == null || mc.thePlayer.getCurrentArmor(3).getMaxDamage() <= 0) &&( mc.thePlayer.getCurrentArmor(2) == null || mc.thePlayer.getCurrentArmor(2).getMaxDamage() <= 0))
	                		renderSlots(armor, mc.fontRenderer, 0, dmg, width-HudOptions.ArmorHudX,height-HudOptions.ArmorHudY + 5, shiftedColor, armor.getMaxDamage());
	                	if((mc.thePlayer.getCurrentArmor(3) != null && mc.thePlayer.getCurrentArmor(3).getMaxDamage() > 0) &&( mc.thePlayer.getCurrentArmor(2) == null || mc.thePlayer.getCurrentArmor(2).getMaxDamage() <= 0))
	                		renderSlots(armor, mc.fontRenderer, 0, dmg, width-HudOptions.ArmorHudX,height-HudOptions.ArmorHudY + 20, shiftedColor, armor.getMaxDamage());
	                }
	                if(i == 0)
	                {
	                	
	                	if(mc.thePlayer.getCurrentArmor(3) != null && mc.thePlayer.getCurrentArmor(3).getMaxDamage() > 0 && mc.thePlayer.getCurrentArmor(2) != null && mc.thePlayer.getCurrentArmor(2).getMaxDamage() > 0 && mc.thePlayer.getCurrentArmor(1) != null && mc.thePlayer.getCurrentArmor(1).getMaxDamage() > 0)
	                		renderSlots(armor, mc.fontRenderer, 0, dmg, width-HudOptions.ArmorHudX,height-HudOptions.ArmorHudY + 56, shiftedColor, armor.getMaxDamage());
	    				if((mc.thePlayer.getCurrentArmor(3) == null || mc.thePlayer.getCurrentArmor(3).getMaxDamage() <= 0) && mc.thePlayer.getCurrentArmor(2) != null && mc.thePlayer.getCurrentArmor(2).getMaxDamage() > 0 && mc.thePlayer.getCurrentArmor(1) != null && mc.thePlayer.getCurrentArmor(1).getMaxDamage() > 0)
	    					renderSlots(armor, mc.fontRenderer, 0, dmg, width-HudOptions.ArmorHudX,height-HudOptions.ArmorHudY + 36, shiftedColor, armor.getMaxDamage());
	    				if(mc.thePlayer.getCurrentArmor(3) != null && mc.thePlayer.getCurrentArmor(3).getMaxDamage() > 0 && (mc.thePlayer.getCurrentArmor(2) == null || mc.thePlayer.getCurrentArmor(2).getMaxDamage() <= 0 )&& mc.thePlayer.getCurrentArmor(1) != null && mc.thePlayer.getCurrentArmor(1).getMaxDamage() > 0)
	    				{
	    					renderSlots(armor, mc.fontRenderer, 0, dmg, width-HudOptions.ArmorHudX,height-HudOptions.ArmorHudY + 36, shiftedColor, armor.getMaxDamage());
	    				}
	    				if(mc.thePlayer.getCurrentArmor(3) != null && mc.thePlayer.getCurrentArmor(3).getMaxDamage() > 0 && mc.thePlayer.getCurrentArmor(2) != null && mc.thePlayer.getCurrentArmor(2).getMaxDamage() > 0 &&( mc.thePlayer.getCurrentArmor(1) == null || mc.thePlayer.getCurrentArmor(1).getMaxDamage() <= 0))
	    				{
	    					renderSlots(armor, mc.fontRenderer, 0, dmg, width-HudOptions.ArmorHudX,height-HudOptions.ArmorHudY + 36, shiftedColor, armor.getMaxDamage());
	    				}
	    				if((mc.thePlayer.getCurrentArmor(3) == null || mc.thePlayer.getCurrentArmor(3).getMaxDamage() <= 0 ) && (mc.thePlayer.getCurrentArmor(2) == null || mc.thePlayer.getCurrentArmor(2).getMaxDamage() <= 0 )&& mc.thePlayer.getCurrentArmor(1) != null && mc.thePlayer.getCurrentArmor(1).getMaxDamage() > 0)
	    					renderSlots(armor, mc.fontRenderer, 0, dmg, width-HudOptions.ArmorHudX,height-HudOptions.ArmorHudY + 19, shiftedColor, armor.getMaxDamage());
	    				if((mc.thePlayer.getCurrentArmor(3) == null || mc.thePlayer.getCurrentArmor(3).getMaxDamage() <= 0 ) && mc.thePlayer.getCurrentArmor(2) != null && mc.thePlayer.getCurrentArmor(2).getMaxDamage() > 0 && (mc.thePlayer.getCurrentArmor(1) == null || mc.thePlayer.getCurrentArmor(1).getMaxDamage() <= 0))
	    					renderSlots(armor, mc.fontRenderer, 0, dmg, width-HudOptions.ArmorHudX,height-HudOptions.ArmorHudY + 19, shiftedColor, armor.getMaxDamage());
	    				if(mc.thePlayer.getCurrentArmor(3) != null && mc.thePlayer.getCurrentArmor(3).getMaxDamage() > 0  && (mc.thePlayer.getCurrentArmor(2) == null || mc.thePlayer.getCurrentArmor(2).getMaxDamage() <= 0 )&& (mc.thePlayer.getCurrentArmor(1) == null || mc.thePlayer.getCurrentArmor(1).getMaxDamage() <= 0))
	    					renderSlots(armor, mc.fontRenderer, 0, dmg, width-HudOptions.ArmorHudX,height-HudOptions.ArmorHudY + 19, shiftedColor, armor.getMaxDamage());
	    				if((mc.thePlayer.getCurrentArmor(3) == null || mc.thePlayer.getCurrentArmor(3).getMaxDamage() <= 0 )  && (mc.thePlayer.getCurrentArmor(2) == null || mc.thePlayer.getCurrentArmor(2).getMaxDamage() <= 0 )&& (mc.thePlayer.getCurrentArmor(1) == null || mc.thePlayer.getCurrentArmor(1).getMaxDamage() <= 0))
	    					renderSlots(armor, mc.fontRenderer, 0, dmg, width-HudOptions.ArmorHudX,height-HudOptions.ArmorHudY + 2, shiftedColor, armor.getMaxDamage());
	                }
	    			RenderHelper.enableGUIStandardItemLighting();
	    			if(i == 3)
	    				ri.renderItemAndEffectIntoGUI(mc.fontRenderer, mc.renderEngine, armor, width-HudOptions.ArmorHudX+3, res.getScaledHeight()-HudOptions.ArmorHudY);
	    			if(i == 2)
	    			{
	    				if(mc.thePlayer.getCurrentArmor(3) != null && mc.thePlayer.getCurrentArmor(3).getMaxDamage() > 0)
	    					ri.renderItemAndEffectIntoGUI(mc.fontRenderer, mc.renderEngine, armor, width-HudOptions.ArmorHudX+3, res.getScaledHeight()-HudOptions.ArmorHudY + armorOffset);
	    				if(mc.thePlayer.getCurrentArmor(3) == null || mc.thePlayer.getCurrentArmor(3).getMaxDamage() <= 0)
	    					ri.renderItemAndEffectIntoGUI(mc.fontRenderer, mc.renderEngine, armor, width-HudOptions.ArmorHudX+3, res.getScaledHeight()-HudOptions.ArmorHudY-3);
	    			}
	    			if(i == 1)
	    			{
	    				if(mc.thePlayer.getCurrentArmor(3) != null && mc.thePlayer.getCurrentArmor(3).getMaxDamage() > 0 && mc.thePlayer.getCurrentArmor(2) != null && mc.thePlayer.getCurrentArmor(2).getMaxDamage() > 0)
	    					ri.renderItemAndEffectIntoGUI(mc.fontRenderer, mc.renderEngine, armor, width-HudOptions.ArmorHudX+3, res.getScaledHeight()-HudOptions.ArmorHudY + (armorOffset+2)*2);
		    			if((mc.thePlayer.getCurrentArmor(3) == null || mc.thePlayer.getCurrentArmor(3).getMaxDamage() <= 0) && mc.thePlayer.getCurrentArmor(2) != null && mc.thePlayer.getCurrentArmor(2).getMaxDamage() > 0)
	    					ri.renderItemAndEffectIntoGUI(mc.fontRenderer, mc.renderEngine, armor, width-HudOptions.ArmorHudX+3, res.getScaledHeight()-HudOptions.ArmorHudY + (armorOffset));
		    			if((mc.thePlayer.getCurrentArmor(3) == null || mc.thePlayer.getCurrentArmor(3).getMaxDamage() <= 0) &&( mc.thePlayer.getCurrentArmor(2) == null || mc.thePlayer.getCurrentArmor(2).getMaxDamage() <= 0))
		    				ri.renderItemAndEffectIntoGUI(mc.fontRenderer, mc.renderEngine, armor, width-HudOptions.ArmorHudX+3, res.getScaledHeight()-HudOptions.ArmorHudY );
		    			if((mc.thePlayer.getCurrentArmor(3) != null && mc.thePlayer.getCurrentArmor(3).getMaxDamage() > 0) &&( mc.thePlayer.getCurrentArmor(2) == null || mc.thePlayer.getCurrentArmor(2).getMaxDamage() <= 0))
		    				ri.renderItemAndEffectIntoGUI(mc.fontRenderer, mc.renderEngine, armor, width-HudOptions.ArmorHudX+3, res.getScaledHeight()-HudOptions.ArmorHudY + (armorOffset));
	    			}
	    			if(i == 0)
	    			{
	    				if(mc.thePlayer.getCurrentArmor(3) != null && mc.thePlayer.getCurrentArmor(3).getMaxDamage() > 0 && mc.thePlayer.getCurrentArmor(2) != null && mc.thePlayer.getCurrentArmor(2).getMaxDamage() > 0 && mc.thePlayer.getCurrentArmor(1) != null && mc.thePlayer.getCurrentArmor(1).getMaxDamage() > 0)
	    					ri.renderItemAndEffectIntoGUI(mc.fontRenderer, mc.renderEngine, armor, width-HudOptions.ArmorHudX+3, res.getScaledHeight()-HudOptions.ArmorHudY + (armorOffset+38));
	    				if((mc.thePlayer.getCurrentArmor(3) == null || mc.thePlayer.getCurrentArmor(3).getMaxDamage() <= 0) && mc.thePlayer.getCurrentArmor(2) != null && mc.thePlayer.getCurrentArmor(2).getMaxDamage() > 0 && mc.thePlayer.getCurrentArmor(1) != null && mc.thePlayer.getCurrentArmor(1).getMaxDamage() > 0)
	    					ri.renderItemAndEffectIntoGUI(mc.fontRenderer, mc.renderEngine, armor, width-HudOptions.ArmorHudX+3, res.getScaledHeight()-HudOptions.ArmorHudY + (armorOffset+18));
	    				if(mc.thePlayer.getCurrentArmor(3) != null && mc.thePlayer.getCurrentArmor(3).getMaxDamage() > 0 && (mc.thePlayer.getCurrentArmor(2) == null || mc.thePlayer.getCurrentArmor(2).getMaxDamage() <= 0 )&& mc.thePlayer.getCurrentArmor(1) != null && mc.thePlayer.getCurrentArmor(1).getMaxDamage() > 0)
	    				{
	    					ri.renderItemAndEffectIntoGUI(mc.fontRenderer, mc.renderEngine, armor, width-HudOptions.ArmorHudX+3, res.getScaledHeight()-HudOptions.ArmorHudY + (armorOffset+18));
	    				}
	    				if(mc.thePlayer.getCurrentArmor(3) != null && mc.thePlayer.getCurrentArmor(3).getMaxDamage() > 0 && mc.thePlayer.getCurrentArmor(2) != null && mc.thePlayer.getCurrentArmor(2).getMaxDamage() > 0 &&( mc.thePlayer.getCurrentArmor(1) == null || mc.thePlayer.getCurrentArmor(1).getMaxDamage() <= 0))
	    				{
	    					ri.renderItemAndEffectIntoGUI(mc.fontRenderer, mc.renderEngine, armor, width-HudOptions.ArmorHudX+3, res.getScaledHeight()-HudOptions.ArmorHudY + (armorOffset+18));
	    				}
	    				if((mc.thePlayer.getCurrentArmor(3) == null || mc.thePlayer.getCurrentArmor(3).getMaxDamage() <= 0 ) && (mc.thePlayer.getCurrentArmor(2) == null || mc.thePlayer.getCurrentArmor(2).getMaxDamage() <= 0 )&& mc.thePlayer.getCurrentArmor(1) != null && mc.thePlayer.getCurrentArmor(1).getMaxDamage() > 0)
	    					ri.renderItemAndEffectIntoGUI(mc.fontRenderer, mc.renderEngine, armor, width-HudOptions.ArmorHudX+3, res.getScaledHeight()-HudOptions.ArmorHudY + (armorOffset+2));
	    				if((mc.thePlayer.getCurrentArmor(3) == null || mc.thePlayer.getCurrentArmor(3).getMaxDamage() <= 0 ) && mc.thePlayer.getCurrentArmor(2) != null && mc.thePlayer.getCurrentArmor(2).getMaxDamage() > 0 && (mc.thePlayer.getCurrentArmor(1) == null || mc.thePlayer.getCurrentArmor(1).getMaxDamage() <= 0))
	    					ri.renderItemAndEffectIntoGUI(mc.fontRenderer, mc.renderEngine, armor, width-HudOptions.ArmorHudX+3, res.getScaledHeight()-HudOptions.ArmorHudY + (armorOffset+2));
	    				if(mc.thePlayer.getCurrentArmor(3) != null && mc.thePlayer.getCurrentArmor(3).getMaxDamage() > 0  && (mc.thePlayer.getCurrentArmor(2) == null || mc.thePlayer.getCurrentArmor(2).getMaxDamage() <= 0 )&& (mc.thePlayer.getCurrentArmor(1) == null || mc.thePlayer.getCurrentArmor(1).getMaxDamage() <= 0))
	    					ri.renderItemAndEffectIntoGUI(mc.fontRenderer, mc.renderEngine, armor, width-HudOptions.ArmorHudX+3, res.getScaledHeight()-HudOptions.ArmorHudY + (armorOffset));
	    				if((mc.thePlayer.getCurrentArmor(3) == null || mc.thePlayer.getCurrentArmor(3).getMaxDamage() <= 0 )  && (mc.thePlayer.getCurrentArmor(2) == null || mc.thePlayer.getCurrentArmor(2).getMaxDamage() <= 0 )&& (mc.thePlayer.getCurrentArmor(1) == null || mc.thePlayer.getCurrentArmor(1).getMaxDamage() <= 0))
	    					ri.renderItemAndEffectIntoGUI(mc.fontRenderer, mc.renderEngine, armor, width-HudOptions.ArmorHudX+3, res.getScaledHeight()-HudOptions.ArmorHudY);
	    			}
	    			RenderHelper.disableStandardItemLighting();
	    			
	    		}
	    	}
    	}
    	if(HudOptions.displayToolHud && mc.thePlayer.getCurrentEquippedItem() != null)
    	{
    		int slot = mc.thePlayer.inventory.currentItem;
    		int dmg = mc.thePlayer.getCurrentEquippedItem().getItemDamageForDisplay();
    		int maxDamage = mc.thePlayer.getCurrentEquippedItem().getMaxDamage();
    		if(mc.thePlayer.getCurrentEquippedItem().getItem() == AdvancedUtilitiesItems.toolBE)
    		{
    			NBTTagCompound tag = mc.thePlayer.getCurrentEquippedItem().getTagCompound();
    			dmg = tag.getInteger("MaxDamage")-tag.getInteger("CurrentDamage");
    			maxDamage = tag.getInteger("MaxDamage");
    		}
            int color = (int) Math.round(255.0D - dmg * 255.0D
                    / maxDamage);
            int shiftedColor = 255 - color << 16 | color << 8;
            renderSlots(mc.thePlayer.getCurrentEquippedItem(), mc.fontRenderer, 0, dmg, width-HudOptions.ToolHudX+(slot*20),height-HudOptions.ToolHudY, shiftedColor, maxDamage); // 278
    	}
    }
    
    
   
  }
  
  public static void renderSlots(ItemStack stack, FontRenderer font,
          int offset, int dmg, int x, int y, int shiftedColor, int maxDamage) {
      boolean flag = Minecraft.getMinecraft().fontRenderer.getUnicodeFlag();
      Minecraft.getMinecraft().fontRenderer.setUnicodeFlag(false);
      renderNormalSlots(stack, font, offset, dmg, x, y, shiftedColor, maxDamage);
      Minecraft.getMinecraft().fontRenderer.setUnicodeFlag(flag);
  }
  
  private static void renderNormalSlots(ItemStack stack, FontRenderer font,
          int offset, int dmg, int x, int y, int shiftedColor, int maxDamage) {
	  	String dmgStr = "";
	  	  if(stack.isItemStackDamageable() || stack.getItem() == AdvancedUtilitiesItems.toolBE)
	  	  {
          	dmgStr = (maxDamage - dmg)+"";
	  	  }

          GL11.glPushMatrix();
          GL11.glScalef(0.5F, 0.5F, 0.5F);
          font.drawStringWithShadow(dmgStr,
                  (x + 16 - font.getStringWidth(dmgStr) / 2) * 2,
                  (y + 11) * 2, shiftedColor);

          GL11.glScalef(1F, 1F, 1F);
          GL11.glPopMatrix();
	  	  
      
  }
  
 /** @param x Location X
  * @param y Location Y
  * @param w Draw Width
  * @param h Draw Height
  */
 public void drawTexture(int x, int y, int w, int h)
 {
     GL11.glColor4f(1F, 1F, 1F, 1F);
     Tessellator tessellator = Tessellator.instance;
     tessellator.startDrawingQuads();
     tessellator.addVertexWithUV(x + 0, y + h, this.zLevel, 0D, 1D);
     tessellator.addVertexWithUV(x + w, y + h, this.zLevel, 1D, 1D);
     tessellator.addVertexWithUV(x + w, y + 0, this.zLevel, 1D, 0D);
     tessellator.addVertexWithUV(x + 0, y + 0, this.zLevel, 0D, 0D);
     tessellator.draw();
 }
}