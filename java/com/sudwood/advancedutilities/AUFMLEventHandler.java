package com.sudwood.advancedutilities;

import org.lwjgl.input.Keyboard;

import com.sudwood.advancedutilities.client.SoundHandler;
import com.sudwood.advancedutilities.items.AdvancedUtilitiesItems;
import com.sudwood.advancedutilities.items.ItemArmorSteamJetpack;
import com.sudwood.advancedutilities.packets.PacketJetpack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AUFMLEventHandler 
{
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void ClientEvent(TickEvent.ClientTickEvent event)
	{
		if(Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindJump.getKeyCode()) && Minecraft.getMinecraft().thePlayer != null && !ExtendedPlayer.get(Minecraft.getMinecraft().thePlayer).isJetpack  && Minecraft.getMinecraft().currentScreen == null)
		{
			AdvancedUtilities.packetPipeline.sendToServer(new PacketJetpack(true));
		}
		else if(Minecraft.getMinecraft().thePlayer != null && (ExtendedPlayer.get(Minecraft.getMinecraft().thePlayer).isJetpack || Minecraft.getMinecraft().currentScreen != null))
		{
			AdvancedUtilities.packetPipeline.sendToServer(new PacketJetpack(false));
		}
	}
	
	@SubscribeEvent
	public void PlayerEvent(TickEvent.PlayerTickEvent event)
	{
		if(event.player != null)
		{
			EntityPlayer player = event.player;
			ExtendedPlayer props = ExtendedPlayer.get(player);
			if(player.getCurrentArmor(2) != null && player.getCurrentArmor(2).getItem() == AdvancedUtilitiesItems.steamJetpack && props.isJetpack)
			{
				ItemStack jetpack = player.getCurrentArmor(2).copy();
				if(jetpack.getTagCompound() == null)
				{
					jetpack.setTagCompound(new NBTTagCompound());
				}
				NBTTagCompound tag = jetpack.getTagCompound();
				if(tag.getInteger("tankAmount") >= ItemArmorSteamJetpack.steamUse)
				{
					player.addVelocity(0,0.1,0);
					player.fallDistance = 0F;
					SoundHandler.playAtEntity(player.worldObj, player, "steamStream", 0.1F, 0.6F);
					tag.setInteger("tankAmount", tag.getInteger("tankAmount") - ItemArmorSteamJetpack.steamUse);
				}
				player.setCurrentItemOrArmor(3, jetpack);
				
			}
		}
	}
}
