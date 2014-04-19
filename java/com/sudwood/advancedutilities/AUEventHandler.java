package com.sudwood.advancedutilities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;

import com.sudwood.advancedutilities.blocks.AdvancedUtilitiesBlocks;
import com.sudwood.advancedutilities.client.SoundHandler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AUEventHandler 
{
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void textureHook(TextureStitchEvent.Post event)
	    {
	        if (event.map.getTextureType() == 0)
	        {   
	            AdvancedUtilitiesBlocks.fluidSteam.setIcons(AdvancedUtilitiesBlocks.blockFluidSteam.getIcon(0, 0), AdvancedUtilitiesBlocks.blockFluidSteam.getIcon(1, 0));
	        }
	    }
	
	@SubscribeEvent
	public void serverChat(ServerChatEvent event)
	    {
	        String chatMessage = event.message;
	        if(chatMessage.startsWith("test") && chatMessage.endsWith("sound"))
	        {
	        	SoundHandler.playAtEntity(event.player.worldObj, event.player, "gunshot", 1.4F, 0.6F);
	        }
	    }
	
	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event)
	{
	/*
	Be sure to check if the entity being constructed is the correct type for the extended properties you're about to add! The null check may not be necessary - I only use it to make sure properties are only registered once per entity
	*/
	if (event.entity instanceof EntityPlayer && ExtendedPlayer.get((EntityPlayer) event.entity) == null)
		ExtendedPlayer.register((EntityPlayer) event.entity);
	}
	
	// These are methods in the EventHandler class, in case you don't know that by now

	// we need to add this new event - it is called for every living entity upon death
	@SubscribeEvent
	public void onLivingDeathEvent(LivingDeathEvent event)
	{
		// we only want to save data for players (most likely, anyway)
		if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer)
		{
			// call our handy static one-liner to save custom data to the proxy
			ExtendedPlayer.saveProxyData((EntityPlayer) event.entity);
		}
	}

	// we already have this event, but we need to modify it some
	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent event)
	{
		if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer)
		{
			ExtendedPlayer.loadProxyData((EntityPlayer)event.entity);
			// finally, we sync the data between server and client (we did this earlier in 3.3)
		}
	}
}
