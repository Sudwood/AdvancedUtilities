package com.sudwood.advancedutilities;

import java.util.UUID;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.minecart.MinecartInteractEvent;

import com.sudwood.advancedutilities.blocks.AdvancedUtilitiesBlocks;
import com.sudwood.advancedutilities.client.SoundHandler;
import com.sudwood.advancedutilities.entity.minecart.EntityChunkLoadingCart;
import com.sudwood.advancedutilities.entity.minecart.IChunkCart;
import com.sudwood.advancedutilities.items.AdvancedUtilitiesItems;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AUEventHandler 
{
	 public static final UUID SpeedSkill1 = UUID.fromString("3a170798-8fc1-11e4-96fa-123b93f75cba");
	 public static final UUID SpeedSkill2 = UUID.fromString("3a170a04-8fc1-11e4-96fa-123b93f75cba");
	 public static final UUID SpeedSkill3 = UUID.fromString("3a170b62-8fc1-11e4-96fa-123b93f75cba");
	 public static final UUID DamageSkill1 = UUID.fromString("3a170cb6-8fc1-11e4-96fa-123b93f75cba");
	 public static final UUID DamageSkill2 = UUID.fromString("3a170df6-8fc1-11e4-96fa-123b93f75cba");
	 public static final UUID DamageSkill3 = UUID.fromString("3a170f36-8fc1-11e4-96fa-123b93f75cba");
	 public static final UUID HealthSkill1 = UUID.fromString("3a171076-8fc1-11e4-96fa-123b93f75cba");
	 public static final UUID HealthSkill2 = UUID.fromString("3a1711ac-8fc1-11e4-96fa-123b93f75cba");
	 public static final UUID HealthSkill3 = UUID.fromString("3a1712e2-8fc1-11e4-96fa-123b93f75cba");
	
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
	
	@SubscribeEvent
	public void onMinecartInteract(MinecartInteractEvent event)
	{
		if(event.player!=null && event.minecart != null && event.player.getCurrentEquippedItem() !=null && event.player.getCurrentEquippedItem().getItem() == AdvancedUtilitiesItems.crowbar)
		{
			EntityPlayer player = event.player;
			ItemStack held = player.getCurrentEquippedItem().copy();
			if(held.getTagCompound() == null)
			{
				held.setTagCompound(new NBTTagCompound());
			}
			NBTTagCompound tag = held.getTagCompound();
			if(!tag.getBoolean("isSet"))
			{
				tag.setInteger("ID", event.minecart.getEntityId());
				tag.setBoolean("isSet", true);
				held.damageItem(1, player);
				player.setCurrentItemOrArmor(0, held);
				event.setCanceled(true);
				return;
			}
			else if(tag.getBoolean("isSet"))
			{
				event.minecart.mountEntity(player.worldObj.getEntityByID(tag.getInteger("ID")));
				tag.setBoolean("isSet", false);
				tag.setInteger("ID", 0);
				held.damageItem(1, player);
				player.setCurrentItemOrArmor(0, held);
				event.setCanceled(true);
				return;
			}
		}
	}
	
	@SubscribeEvent
	public void onEntityMoveChunks(EntityEvent.EnteringChunk event)
	{
		if(event.entity instanceof IChunkCart)
		{
			IChunkCart entity = (IChunkCart) event.entity;
			entity.loadChunk(event.newChunkX, event.newChunkZ);
			entity.unLoadChunk(event.oldChunkX, event.oldChunkZ);
			
		}
	}
	
	@SubscribeEvent
	public void onEntityJump(LivingEvent.LivingJumpEvent event)
	{
		if(event.entityLiving != null && event.entityLiving instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)event.entityLiving;
			if(player.getCurrentArmor(0) != null && player.getCurrentArmor(0).getItem() == AdvancedUtilitiesItems.runningShoes)
			{
				player.motionY+=0.05D;
			}
		}
	}
	
	@SubscribeEvent
	public void onLivingDeath(LivingDeathEvent event)
	{
		if(event.entityLiving instanceof EntitySheep && !event.entityLiving.worldObj.isRemote)
		{
			if(Math.random()>=0.4)
			{
				event.entityLiving.worldObj.spawnEntityInWorld(new EntityItem(event.entityLiving.worldObj, event.entityLiving.posX, event.entityLiving.posY, event.entityLiving.posZ, new ItemStack(Items.leather, 1)));
			}
		}
	}
	
	@SubscribeEvent
	public void PlayerPickupXpEvent(net.minecraftforge.event.entity.player.PlayerPickupXpEvent event)
	{
		if(!event.entityPlayer.worldObj.isRemote)
		{
			EntityPlayer player = event.entityPlayer;
			ExtendedPlayer props = ExtendedPlayer.get(player);
			IAttributeInstance atinst = player.getEntityAttribute(SharedMonsterAttributes.movementSpeed);//enter the attribute speed without triggering any calculation
			IAttributeInstance atinstDamage = player.getEntityAttribute(SharedMonsterAttributes.attackDamage);//enter the attribute speed without triggering any calculation
			IAttributeInstance atinstHealth = player.getEntityAttribute(SharedMonsterAttributes.maxHealth);//enter the attribute speed without triggering any calculation
			
			AttributeModifier ss1 = new AttributeModifier(SpeedSkill1, "AdvancedUtilities:SpeedSkill1", atinst.getBaseValue()*2, 2);
			AttributeModifier ss2 = new AttributeModifier(SpeedSkill2, "AdvancedUtilities:SpeedSkill2", atinst.getBaseValue()*3, 2);
			AttributeModifier ss3 = new AttributeModifier(SpeedSkill3, "AdvancedUtilities:SpeedSkill3", atinst.getBaseValue()*4, 2);
			AttributeModifier ds1 = new AttributeModifier(DamageSkill1, "AdvancedUtilities:DamageSkill1", atinstDamage.getBaseValue()*1.5, 2);
			AttributeModifier ds2 = new AttributeModifier(DamageSkill2, "AdvancedUtilities:DamageSkill2", atinstDamage.getBaseValue()*1.5, 2);
			AttributeModifier ds3 = new AttributeModifier(DamageSkill3, "AdvancedUtilities:DamageSkill3", atinstDamage.getBaseValue()*2, 2);
			AttributeModifier hs1 = new AttributeModifier(HealthSkill1, "AdvancedUtilities:HealthSkill1", atinstHealth.getBaseValue()*0.0135, 2);
			AttributeModifier hs2 = new AttributeModifier(HealthSkill2, "AdvancedUtilities:HealthSkill2", atinstHealth.getBaseValue()*0.025, 2);
			AttributeModifier hs3 = new AttributeModifier(HealthSkill3, "AdvancedUtilities:HealthSkill3", atinstHealth.getBaseValue()*0.02865, 2);
			if(props.skills[0] == 0 && props.skills[1] == 0 && props.skills[2] == 0)
			{
				if(atinst.getModifier(SpeedSkill1) != null)
				{
					atinst.removeModifier(ss1);
				}
				if(atinst.getModifier(SpeedSkill2) != null)
				{
					atinst.removeModifier(ss2);
				}
				if(atinst.getModifier(SpeedSkill3) != null)
				{
					atinst.removeModifier(ss3);
				}
				if(atinst.getModifier(DamageSkill1) != null)
				{
					atinst.removeModifier(ds1);
				}
				if(atinst.getModifier(DamageSkill2) != null)
				{
					atinst.removeModifier(ds2);
				}
				if(atinst.getModifier(DamageSkill3) != null)
				{
					atinst.removeModifier(ds3);
				}
				if(atinst.getModifier(HealthSkill1) != null)
				{
					atinst.removeModifier(hs1);
				}
				if(atinst.getModifier(HealthSkill2) != null)
				{
					atinst.removeModifier(hs2);
				}
				if(atinst.getModifier(HealthSkill3) != null)
				{
					atinst.removeModifier(hs3);
				}
			}
			if(props.skills[0] > 0 && player.experienceLevel >= 20)
			{
				if(atinst.getModifier(SpeedSkill1) == null)
				{
					atinst.applyModifier(ss1);
				}
				if(props.skills[0] > 1 && player.experienceLevel >= 30)
				{
					if(atinst.getModifier(SpeedSkill2) == null)
					{
						atinst.applyModifier(ss2);
					}
					if(props.skills[0] > 2 && player.experienceLevel >= 50)
					{
						if(atinst.getModifier(SpeedSkill3) == null)
						{
							atinst.applyModifier(ss3);
						}
					}
				}
				
			}
			if(props.skills[1] > 0 && player.experienceLevel >= 20)
			{
				if(atinstDamage.getModifier(DamageSkill1) == null)
				{
					atinstDamage.applyModifier(ds1);
				}
				if(props.skills[1] > 1 && player.experienceLevel >= 30)
				{
					if(atinstDamage.getModifier(DamageSkill2) == null)
					{
						atinstDamage.applyModifier(ds2);
					}
					if(props.skills[1] > 2 && player.experienceLevel >= 50)
					{
						if(atinstDamage.getModifier(DamageSkill3) == null)
						{
							atinstDamage.applyModifier(ds3);
						}
					}
				}
				
			}
			if(props.skills[2] > 0 && player.experienceLevel >= 20)
			{
				if(atinstHealth.getModifier(HealthSkill1) == null)
				{
					atinstHealth.applyModifier(hs1);
				}
				if(props.skills[2] > 1 && player.experienceLevel >= 30)
				{
					if(atinstHealth.getModifier(HealthSkill2) == null)
					{
						atinstHealth.applyModifier(hs2);
					}
					if(props.skills[2] > 2 && player.experienceLevel >= 50)
					{
						if(atinstHealth.getModifier(HealthSkill3) == null)
						{
							atinstHealth.applyModifier(hs3);
						}
					}
				}
				
			}
		}
	}
	
}
