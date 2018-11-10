package com.sudwood.advancedutilities;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.lwjgl.input.Keyboard;

import com.sudwood.advancedutilities.client.KeyHandler;
import com.sudwood.advancedutilities.client.SoundHandler;
import com.sudwood.advancedutilities.config.HudOptions;
import com.sudwood.advancedutilities.items.AdvancedUtilitiesItems;
import com.sudwood.advancedutilities.items.ItemArmorSteamJetpack;
import com.sudwood.advancedutilities.packets.PacketDrinkQuickPotion;
import com.sudwood.advancedutilities.packets.PacketJetpack;
import com.sudwood.advancedutilities.packets.PacketRunningShoes;

import codechicken.nei.NEISPH;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class AUFMLEventHandler 
{
	 public static final UUID MovementSpeed = UUID.fromString("36EFE1D0-C964-11E3-9C1A-0800200C9A66");
	 public static final UUID SteamLegs = UUID.fromString("a6dfcd60-2db4-11e5-9184-feff819cdc9f");
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
		if(KeyHandler.hoverToggle.getIsKeyPressed()) 
		{
			if(Minecraft.getMinecraft().currentScreen == null && Minecraft.getMinecraft().thePlayer != null) 
			{
				AdvancedUtilities.network.sendToServer(new PacketJetpack(!ExtendedPlayer.get(Minecraft.getMinecraft().thePlayer).toggleHover, 2));
			}
		}
		if(KeyHandler.buableToggle.getIsKeyPressed()) 
		{
			if(Minecraft.getMinecraft().currentScreen == null && Minecraft.getMinecraft().thePlayer != null) 
			{
				AdvancedUtilities.network.sendToServer(new PacketJetpack(!ExtendedPlayer.get(Minecraft.getMinecraft().thePlayer).toggleBaubles, 3));
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
		if(Minecraft.getMinecraft().thePlayer != null)
		{
			EntityPlayer player = Minecraft.getMinecraft().thePlayer;
			ExtendedPlayer props = ExtendedPlayer.get(player);
		if(Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindJump.getKeyCode()) && Minecraft.getMinecraft().thePlayer != null && !ExtendedPlayer.get(Minecraft.getMinecraft().thePlayer).isJetpack && ExtendedPlayer.get(Minecraft.getMinecraft().thePlayer).toggleJetpack && Minecraft.getMinecraft().currentScreen == null)
		{
			AdvancedUtilities.network.sendToServer(new PacketJetpack(true, 0));

		}
		if(Keyboard.isKeyDown(KeyHandler.run.getKeyCode())&& !props.isRunning  && Minecraft.getMinecraft().currentScreen == null)
		{
			AdvancedUtilities.network.sendToServer(new PacketRunningShoes(true));

		}
		if(props.isJetpack && !Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindJump.getKeyCode()))
		{
			AdvancedUtilities.network.sendToServer(new PacketJetpack(false, 0));
		}
		if( props.isJetpack  && !props.toggleJetpack)
		{
			AdvancedUtilities.network.sendToServer(new PacketJetpack(false, 0));
		}
		
		if( player.getCurrentArmor(0) !=null && player.getCurrentArmor(0).getItem() == AdvancedUtilitiesItems.runningShoes && props.isRunning && ( Minecraft.getMinecraft().currentScreen != null) || !Keyboard.isKeyDown(KeyHandler.run.getKeyCode()) )
		{
			AdvancedUtilities.network.sendToServer(new PacketRunningShoes(false));

		}
		}
	}
	@SubscribeEvent
	public void PlayerEvent(TickEvent.PlayerTickEvent event)
	{
		if(event.player != null)
		{
			EntityPlayer player = event.player;
			World world = player.worldObj;
			ExtendedPlayer props = ExtendedPlayer.get(player);
			IAttributeInstance atinst = player.getEntityAttribute(SharedMonsterAttributes.movementSpeed);//enter the attribute speed without triggering any calculation
			AttributeModifier mod = new AttributeModifier(MovementSpeed, "AdvancedUtilities:MovementSpeed", atinst.getBaseValue()*8, 2);
			AttributeModifier sleg = new AttributeModifier(SteamLegs, "AdvancedUtilities:SteamLegs", atinst.getBaseValue()*4, 1);
			if(props.toggleBaubles)
				doBaublesStuff(player, props, world);
			
			if(player.getCurrentArmor(2) != null && player.getCurrentArmor(2).getItem() == AdvancedUtilitiesItems.steamJetpack && props.toggleJetpack && props.toggleHover && !player.onGround &&!props.isJetpack)
			{
				ItemStack jetpack = player.getCurrentArmor(2).copy();
				if(jetpack.getTagCompound() == null)
				{
					jetpack.setTagCompound(new NBTTagCompound());
				}
				NBTTagCompound tag = jetpack.getTagCompound();
				if(tag.getInteger("tankAmount") >= ItemArmorSteamJetpack.steamHover)
				{
					if(player.motionY < 0)
					{
						player.motionY = 0;
					}
					if(HudOptions.playJetpackSounds)
					SoundHandler.playAtEntity(player.worldObj, player, "steamStream", 0.05F, 0.6F);
					if(HudOptions.displayJetpackParticles)
					{
						int facing = MathHelper.floor_double((double)(player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		 	    		switch(facing)
		 	    		{
		 	    			case 0: // south
		 	    				world.spawnParticle("cloud", player.posX-(0.6*ForgeDirection.SOUTH.offsetX), player.posY-1, player.posZ-(0.6*ForgeDirection.SOUTH.offsetZ), (-1)*player.motionX, -0.5F, (-1)*player.motionZ);
		 	    				break;
		 	    			case 1: // west
		 	    				world.spawnParticle("cloud", player.posX-(0.6*ForgeDirection.WEST.offsetX), player.posY-1, player.posZ-(0.6*ForgeDirection.WEST.offsetZ), (-1)*player.motionX, -0.5F, (-1)*player.motionZ);
		 	    				break;
		 	    			case 2: // north
		 	    				world.spawnParticle("cloud", player.posX-(0.6*ForgeDirection.NORTH.offsetX), player.posY-1, player.posZ-(0.6*ForgeDirection.NORTH.offsetZ), (-1)*player.motionX, -0.5F, (-1)*player.motionZ);
		 	    				break;
		 	    			case 3: // east
		 	    				world.spawnParticle("cloud", player.posX-(0.6*ForgeDirection.EAST.offsetX), player.posY-1, player.posZ-(0.6*ForgeDirection.EAST.offsetZ), (-1)*player.motionX, -0.5F, (-1)*player.motionZ);
		 	    				break;
		 	    		}
					}
					if(!player.capabilities.isCreativeMode)
						tag.setInteger("tankAmount", tag.getInteger("tankAmount") - ItemArmorSteamJetpack.steamHover);
					
				}
				jetpack.setTagCompound(tag);
				player.setCurrentItemOrArmor(3, jetpack);
			}
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
					if(HudOptions.playJetpackSounds)
					SoundHandler.playAtEntity(player.worldObj, player, "steamStream", 0.1F, 0.6F);
					if(HudOptions.displayJetpackParticles)
					{
						int facing = MathHelper.floor_double((double)(player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		 	    		switch(facing)
		 	    		{
		 	    			case 0: // south
		 	    				world.spawnParticle("cloud", player.posX-(0.6*ForgeDirection.SOUTH.offsetX), player.posY-1, player.posZ-(0.6*ForgeDirection.SOUTH.offsetZ), (-1)*player.motionX, -0.5F, (-1)*player.motionZ);
		 	    				break;
		 	    			case 1: // west
		 	    				world.spawnParticle("cloud", player.posX-(0.6*ForgeDirection.WEST.offsetX), player.posY-1, player.posZ-(0.6*ForgeDirection.WEST.offsetZ), (-1)*player.motionX, -0.5F, (-1)*player.motionZ);
		 	    				break;
		 	    			case 2: // north
		 	    				world.spawnParticle("cloud", player.posX-(0.6*ForgeDirection.NORTH.offsetX), player.posY-1, player.posZ-(0.6*ForgeDirection.NORTH.offsetZ), (-1)*player.motionX, -0.5F, (-1)*player.motionZ);
		 	    				break;
		 	    			case 3: // east
		 	    				world.spawnParticle("cloud", player.posX-(0.6*ForgeDirection.EAST.offsetX), player.posY-1, player.posZ-(0.6*ForgeDirection.EAST.offsetZ), (-1)*player.motionX, -0.5F, (-1)*player.motionZ);
		 	    				break;
		 	    		}
					}
					if(!player.capabilities.isCreativeMode)
						tag.setInteger("tankAmount", tag.getInteger("tankAmount") - ItemArmorSteamJetpack.steamUse);
				}
				jetpack.setTagCompound(tag);
				player.setCurrentItemOrArmor(3, jetpack);
				
			}
			if(player.getCurrentArmor(0) !=null && player.getCurrentArmor(0).getItem() == AdvancedUtilitiesItems.runningShoes && props.isRunning)
			{
				if(atinst.getModifier(MovementSpeed) == null)
				atinst.applyModifier(mod);
				player.jumpMovementFactor = 0.10F;
				player.stepHeight = 1F;
				if(player.fallDistance > player.getHealth()+3 && EnchantmentHelper.getEnchantmentLevel(Enchantment.featherFalling.effectId, player.getCurrentArmor(0))> 0)
				{
					player.fallDistance = player.getHealth()+2;
				}
				player.stepHeight = 1F;
				if(random.nextInt(20) <= 0 && player.onGround)
					player.addExhaustion(1);
				//return;
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
				//return;
			}
			if(player.getCurrentArmor(1) !=null && player.getCurrentArmor(1).getItem() == AdvancedUtilitiesItems.steamLegs)
			{
				NBTTagCompound tag = player.getCurrentArmor(1).stackTagCompound;
				if(tag == null)
				{
					tag = new NBTTagCompound();
				}
				if(tag.getInteger("tankAmount") > 0)
				{
					if(atinst.getModifier(SteamLegs) == null)
					atinst.applyModifier(sleg);
					player.jumpMovementFactor = 0.08F;
					player.stepHeight = 1F;
					if(player.motionX != 0 || player.motionZ != 0)
					{
						if(random.nextInt(5) <= 0 && player.onGround && !player.capabilities.isCreativeMode)
						tag.setInteger("tankAmount", tag.getInteger("tankAmount")-1);
					}
				}
				//return;
			}
			if((player.getCurrentArmor(0) == null && player.getCurrentArmor(1) == null))
			{
				if(atinst.getModifier(MovementSpeed) != null)
					atinst.removeModifier(mod);
				if(atinst.getModifier(SteamLegs) != null)
					atinst.removeModifier(sleg);
				player.jumpMovementFactor = 0.02F;
				player.stepHeight = 0.5F;
			}
			if((player.getCurrentArmor(0) != null && player.getCurrentArmor(1) != null) && (player.getCurrentArmor(0).getItem() != AdvancedUtilitiesItems.runningShoes && player.getCurrentArmor(1).getItem() != AdvancedUtilitiesItems.steamLegs))
			{
				if(atinst.getModifier(MovementSpeed) != null)
					atinst.removeModifier(mod);
				if(atinst.getModifier(SteamLegs) != null)
					atinst.removeModifier(sleg);
				player.jumpMovementFactor = 0.02F;
				player.stepHeight = 0.5F;
			}
			if(player.getCurrentArmor(0) == null)
			{
				if(atinst.getModifier(MovementSpeed) != null)
				atinst.removeModifier(mod);
			}
			
			if(player.getCurrentArmor(1) == null)
			{
				if(atinst.getModifier(SteamLegs) != null)
				atinst.removeModifier(sleg);
			}
		}
	}
	
	public static void doBaublesStuff(EntityPlayer player, ExtendedPlayer props, World world)
	{
		boolean hasBelt = false;
		boolean hasAmulet = false;
		int slot = 0;
		if(player.getHeldItem()!=null&&player.getHeldItem().getItem() == AdvancedUtilitiesItems.climbingBelt)
		{
			hasBelt = true;
			slot = player.inventory.currentItem;
		}
		if(player.getHeldItem()!=null&&player.getHeldItem().getItem() == AdvancedUtilitiesItems.magnetAmulet)
		{
			hasAmulet = true;
		}

		if(!hasBelt || !hasAmulet)
		{
			for(int i = 0; i  <9; i++)
			{
				if(player.inventory.mainInventory[i]!=null && player.inventory.mainInventory[i].getItem() == AdvancedUtilitiesItems.climbingBelt)
				{
					hasBelt = true;
					slot = i;
				}
				if(player.inventory.mainInventory[i]!=null && player.inventory.mainInventory[i].getItem() == AdvancedUtilitiesItems.magnetAmulet)
				{
					hasAmulet = true;
				}
			}
		}
		

		if(hasBelt)
		{
			if(player.isSneaking())
			{
				boolean canFloat = false;
				if(world.getBlock((int)Math.floor(player.posX+1), (int)Math.floor(player.posY), (int)Math.floor(player.posZ)) !=Blocks.air)
				{
					canFloat = true;
				}
				if(world.getBlock((int)Math.floor(player.posX-1), (int)Math.floor(player.posY), (int)Math.floor(player.posZ)) !=Blocks.air)
				{
					canFloat = true;
				}
				if(world.getBlock((int)Math.floor(player.posX), (int)Math.floor(player.posY), (int)Math.floor(player.posZ+1)) !=Blocks.air)
				{
					canFloat = true;
				}
				if(world.getBlock((int)Math.floor(player.posX), (int)Math.floor(player.posY), (int)Math.floor(player.posZ-1)) !=Blocks.air)
				{
					canFloat = true;
				}
				if(world.getBlock((int)Math.floor(player.posX+1), (int)Math.floor(player.posY-1), (int)Math.floor(player.posZ)) !=Blocks.air)
				{
					canFloat = true;
				}
				if(world.getBlock((int)Math.floor(player.posX-1), (int)Math.floor(player.posY-1), (int)Math.floor(player.posZ)) !=Blocks.air)
				{
					canFloat = true;
				}
				if(world.getBlock((int)Math.floor(player.posX), (int)Math.floor(player.posY-1), (int)Math.floor(player.posZ+1)) !=Blocks.air)
				{
					canFloat = true;
				}
				if(world.getBlock((int)Math.floor(player.posX), (int)Math.floor(player.posY-1), (int)Math.floor(player.posZ-1)) !=Blocks.air)
				{
					canFloat = true;
				}
				if(player.worldObj.getBlock((int)Math.floor(player.posX), (int)Math.floor(player.posY+1), (int)Math.floor(player.posZ)) !=Blocks.air)
				{
					canFloat = true;
				}
				if(canFloat)
				{
					player.fallDistance = 0F;
					player.motionY = 0;
					if(HudOptions.playBeltSounds)
					{
						ItemStack stack = player.inventory.mainInventory[slot];
						if(stack!=null&& stack.getItem() == AdvancedUtilitiesItems.climbingBelt)
						{
							stack.setItemDamage(stack.getItemDamage()+1);
							if(stack.getItemDamage() >=15)
							{
								player.playSound("step.ladder", 0.3F, 1F);
								stack.setItemDamage(0);
							}
							
						}
						
					}
				}
			}
			
			if(player.isCollidedHorizontally)
			{
				if(!player.isSneaking())
				player.motionY = 0.2;
				if(player.isSneaking())
					player.motionY =0;
				
				player.fallDistance = 0F;
				if(HudOptions.playBeltSounds)
				{
					ItemStack stack = player.inventory.mainInventory[slot];
					if(stack!=null&& stack.getItem() == AdvancedUtilitiesItems.climbingBelt)
					{
						stack.setItemDamage(stack.getItemDamage()+1);
						if(stack.getItemDamage() >=15)
						{
							player.playSound("step.ladder", 0.3F, 1F);
							stack.setItemDamage(0);
						}
						
					}
				}
			}
		}
		if(hasAmulet)
		{


	        float distancexz = 16;
	        float distancey = 8;
	        List<EntityItem> items = player.worldObj.getEntitiesWithinAABB(EntityItem.class, player.boundingBox.expand(distancexz, distancey, distancexz));
	        for (EntityItem item : items) {
	            if (item.delayBeforeCanPickup > 0) continue;
	            if(!TransferHelper.canFitInInventory(item.getEntityItem(), player.inventory)) continue;
	            if (item.delayBeforeCanPickup == 0) 
	            {
	            	if(HudOptions.magnetAmuletParticles)
	            	{
	            		player.worldObj.spawnParticle("portal", item.posX, item.posY, item.posZ, 0.2D, 0.3D, 0.2D);
	            	}
	               item.onCollideWithPlayer(player);
	            }

	           
	}
		}
	}
}
