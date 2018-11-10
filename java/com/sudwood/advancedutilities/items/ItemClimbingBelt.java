package com.sudwood.advancedutilities.items;

import com.sudwood.advancedutilities.ExtendedPlayer;
import com.sudwood.advancedutilities.config.HudOptions;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemClimbingBelt extends Item implements IBauble
{
	private int soundTimer = 0;
	public ItemClimbingBelt()
	{
		this.maxStackSize =1;
	}
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister icon)
    {
		this.itemIcon = icon.registerIcon("advancedutilities:climbingbelt");
    }

	@Override
	public BaubleType getBaubleType(ItemStack itemstack) {
		// TODO Auto-generated method stub
		return BaubleType.BELT;
	}

	@Override
	public void onWornTick(ItemStack itemstack, EntityLivingBase player) 
	{
		if(player instanceof EntityPlayer)
		{
			ExtendedPlayer props = ExtendedPlayer.get((EntityPlayer) player);
			if(player.isSneaking() && props.toggleBaubles)
			{
				boolean canFloat = false;
				if(player.worldObj.getBlock((int)Math.floor(player.posX+1), (int)Math.floor(player.posY), (int)Math.floor(player.posZ)) !=Blocks.air)
				{
					canFloat = true;
				}
				if(player.worldObj.getBlock((int)Math.floor(player.posX-1), (int)Math.floor(player.posY), (int)Math.floor(player.posZ)) !=Blocks.air)
				{
					canFloat = true;
				}
				if(player.worldObj.getBlock((int)Math.floor(player.posX), (int)Math.floor(player.posY), (int)Math.floor(player.posZ+1)) !=Blocks.air)
				{
					canFloat = true;
				}
				if(player.worldObj.getBlock((int)Math.floor(player.posX), (int)Math.floor(player.posY), (int)Math.floor(player.posZ-1)) !=Blocks.air)
				{
					canFloat = true;
				}
				if(player.worldObj.getBlock((int)Math.floor(player.posX+1), (int)Math.floor(player.posY-1), (int)Math.floor(player.posZ)) !=Blocks.air)
				{
					canFloat = true;
				}
				if(player.worldObj.getBlock((int)Math.floor(player.posX-1), (int)Math.floor(player.posY-1), (int)Math.floor(player.posZ)) !=Blocks.air)
				{
					canFloat = true;
				}
				if(player.worldObj.getBlock((int)Math.floor(player.posX), (int)Math.floor(player.posY-1), (int)Math.floor(player.posZ+1)) !=Blocks.air)
				{
					canFloat = true;
				}
				if(player.worldObj.getBlock((int)Math.floor(player.posX), (int)Math.floor(player.posY-1), (int)Math.floor(player.posZ-1)) !=Blocks.air)
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
						if(soundTimer<15)
						{
							soundTimer++;
						}
						if(soundTimer>= 15)
						{
							player.playSound("step.ladder", 0.3F, 1F);
							soundTimer = 0;
						}
					}
					return;
				}
			}
			if(player.isCollidedHorizontally && props.toggleBaubles)
			{
			
				if(!player.isSneaking())
				player.motionY = 0.2;
				if(player.isSneaking())
					player.motionY =0;
				player.fallDistance = 0F;
				if(HudOptions.playBeltSounds)
				{
					if(soundTimer<15)
					{
						soundTimer++;
					}
					if(soundTimer>= 15)
					{
						player.playSound("step.ladder", 0.3F, 1F);
						soundTimer = 0;
					}
				}
				return;
			}
		
		}
		
	}

	@Override
	public void onEquipped(ItemStack itemstack, EntityLivingBase player)
	{
		
		
	}

	@Override
	public void onUnequipped(ItemStack itemstack, EntityLivingBase player) 
	{
		
	}

	@Override
	public boolean canEquip(ItemStack itemstack, EntityLivingBase player) 
	{
		return true;
	}

	@Override
	public boolean canUnequip(ItemStack itemstack, EntityLivingBase player) 
	{
		return true;
	}
	 

}
