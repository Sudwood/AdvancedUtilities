package com.sudwood.advancedutilities;

import java.util.Random;
import java.util.UUID;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;

import com.sudwood.advancedutilities.client.KeyHandler;
import com.sudwood.advancedutilities.client.SoundHandler;
import com.sudwood.advancedutilities.items.AdvancedUtilitiesItems;
import com.sudwood.advancedutilities.items.ItemArmorSteamJetpack;
import com.sudwood.advancedutilities.packets.PacketDrinkQuickPotion;
import com.sudwood.advancedutilities.packets.PacketJetpack;
import com.sudwood.advancedutilities.packets.PacketRunningShoes;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AUFMLEventHandler 
{
	 public static final UUID MovementSpeed = UUID.fromString("36EFE1D0-C964-11E3-9C1A-0800200C9A66");
	Random random = new Random();
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void KeyInputEvent(cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent event) 
	{
		if(KeyHandler.jetpackToggle.getIsKeyPressed()) 
		{
			if(Minecraft.getMinecraft().currentScreen == null && Minecraft.getMinecraft().thePlayer != null) 
			{
				AdvancedUtilities.network.sendToServer(new PacketJetpack(!ExtendedPlayer.get(Minecraft.getMinecraft().thePlayer).toggleJetpack, 1));
			}
		}
		if(KeyHandler.drinkQuickPotion.getIsKeyPressed()) 
		{
			if(Minecraft.getMinecraft().currentScreen == null && Minecraft.getMinecraft().thePlayer != null) 
			{
				AdvancedUtilities.network.sendToServer(new PacketDrinkQuickPotion(true));
			}
		}
		if(KeyHandler.skills.getIsKeyPressed()) 
		{
			if(Minecraft.getMinecraft().currentScreen == null && Minecraft.getMinecraft().thePlayer != null) 
			{
				Minecraft.getMinecraft().thePlayer.openGui(AdvancedUtilities.instance, AdvancedUtilities.skillsGui, Minecraft.getMinecraft().theWorld, (int)Minecraft.getMinecraft().thePlayer.posX, (int)Minecraft.getMinecraft().thePlayer.posX, (int)Minecraft.getMinecraft().thePlayer.posX);
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void ClientEvent(TickEvent.ClientTickEvent event)
	{
		if(Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindJump.getKeyCode()) && Minecraft.getMinecraft().thePlayer != null && !ExtendedPlayer.get(Minecraft.getMinecraft().thePlayer).isJetpack && ExtendedPlayer.get(Minecraft.getMinecraft().thePlayer).toggleJetpack && Minecraft.getMinecraft().currentScreen == null)
		{
			AdvancedUtilities.network.sendToServer(new PacketJetpack(true, 0));

		}
		if(Keyboard.isKeyDown(KeyHandler.run.getKeyCode()) && Minecraft.getMinecraft().thePlayer != null && !ExtendedPlayer.get(Minecraft.getMinecraft().thePlayer).isRunning  && Minecraft.getMinecraft().currentScreen == null)
		{
			AdvancedUtilities.network.sendToServer(new PacketRunningShoes(true));

		}
		if(Minecraft.getMinecraft().thePlayer != null && Minecraft.getMinecraft().thePlayer.getCurrentArmor(2) !=null && Minecraft.getMinecraft().thePlayer.getCurrentArmor(2).getItem() == AdvancedUtilitiesItems.steamJetpack && (ExtendedPlayer.get(Minecraft.getMinecraft().thePlayer).isJetpack ||  !ExtendedPlayer.get(Minecraft.getMinecraft().thePlayer).toggleJetpack) && ( Minecraft.getMinecraft().currentScreen != null) || !Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindJump.getKeyCode()) )
		{
			AdvancedUtilities.network.sendToServer(new PacketJetpack(false, 0));

		}
		if(Minecraft.getMinecraft().thePlayer != null && Minecraft.getMinecraft().thePlayer.getCurrentArmor(0) !=null && Minecraft.getMinecraft().thePlayer.getCurrentArmor(0).getItem() == AdvancedUtilitiesItems.runningShoes && ExtendedPlayer.get(Minecraft.getMinecraft().thePlayer).isRunning && ( Minecraft.getMinecraft().currentScreen != null) || !Keyboard.isKeyDown(KeyHandler.run.getKeyCode()) )
		{
			AdvancedUtilities.network.sendToServer(new PacketRunningShoes(false));

		}
	}
	@SubscribeEvent
	public void PlayerEvent(TickEvent.PlayerTickEvent event)
	{
		if(event.player != null)
		{
			EntityPlayer player = event.player;
			ExtendedPlayer props = ExtendedPlayer.get(player);
			IAttributeInstance atinst = player.getEntityAttribute(SharedMonsterAttributes.movementSpeed);//enter the attribute speed without triggering any calculation
			AttributeModifier mod = new AttributeModifier(MovementSpeed, "AdvancedUtilities:MovementSpeed", atinst.getBaseValue()*8, 2);
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
					if(FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER && !MinecraftServer.getServer().isFlightAllowed())
					{
						MinecraftServer.getServer().setAllowFlight(true);
					}
					player.addVelocity(0,0.05,0);
					player.fallDistance = 0F;
					player.jumpMovementFactor = 0.08F;
					SoundHandler.playAtEntity(player.worldObj, player, "steamStream", 0.1F, 0.6F);
					if(!player.capabilities.isCreativeMode)
						tag.setInteger("tankAmount", tag.getInteger("tankAmount") - ItemArmorSteamJetpack.steamUse);
				}
				player.setCurrentItemOrArmor(3, jetpack);
				
			}
			if(player.getCurrentArmor(0) !=null && player.getCurrentArmor(0).getItem() == AdvancedUtilitiesItems.runningShoes && props.isRunning)
			{
				if(atinst.getModifier(MovementSpeed) == null)
				atinst.applyModifier(mod);
				player.jumpMovementFactor = 0.08F;
				if(player.fallDistance > player.getHealth()+3 && EnchantmentHelper.getEnchantmentLevel(Enchantment.featherFalling.effectId, player.getCurrentArmor(0))> 0)
				{
					player.fallDistance = player.getHealth()+2;
				}
				player.stepHeight = 1F;
				if(random.nextInt(20) <= 0 && player.onGround)
					player.addExhaustion(1);
				return;
			}
			if(player.getCurrentArmor(0) !=null && player.getCurrentArmor(0).getItem() == AdvancedUtilitiesItems.runningShoes && !props.isRunning)
			{
				if(atinst.getModifier(MovementSpeed) != null)
				atinst.removeModifier(mod);
				player.stepHeight = 1F;
				if(player.fallDistance > player.getHealth()+3 && EnchantmentHelper.getEnchantmentLevel(Enchantment.featherFalling.effectId, player.getCurrentArmor(0))> 0)
				{
					player.fallDistance = player.getHealth()+2;
				}
				player.jumpMovementFactor = 0.02F;
				return;
			}
			if(player.getCurrentArmor(0) == null)
			{
				if(atinst.getModifier(MovementSpeed) != null)
				atinst.removeModifier(mod);
				player.jumpMovementFactor = 0.02F;
				player.stepHeight = 0.5F;
				return;
			}
		}
	}
}
