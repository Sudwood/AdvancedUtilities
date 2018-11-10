package com.sudwood.advancedutilities.items;

import java.util.List;

import com.sudwood.advancedutilities.ExtendedPlayer;
import com.sudwood.advancedutilities.TransferHelper;
import com.sudwood.advancedutilities.config.HudOptions;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemMagnetAmulet  extends Item implements IBauble
{
	public ItemMagnetAmulet()
	{
		this.maxStackSize =1;
	}
	

	@SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister icon)
    {
		this.itemIcon = icon.registerIcon("advancedutilities:magnetamulet");
    }
	@Override
	public BaubleType getBaubleType(ItemStack itemstack) {
		// TODO Auto-generated method stub
		return BaubleType.AMULET;
	}

	@Override
	public void onWornTick(ItemStack itemstack, EntityLivingBase player)
	{
		
		float distancexz = 16;
        float distancey = 8;
        if(player instanceof EntityPlayer)
        {
        	ExtendedPlayer props = ExtendedPlayer.get((EntityPlayer) player);
	        List<EntityItem> items = player.worldObj.getEntitiesWithinAABB(EntityItem.class, player.boundingBox.expand(distancexz, distancey, distancexz));
	        if(props.toggleBaubles)
	        {
		        for (EntityItem item : items) {
		            if (item.delayBeforeCanPickup > 0) continue;
		            if(!TransferHelper.canFitInInventory(item.getEntityItem(), ((EntityPlayer)player).inventory)) continue;
		            if (item.delayBeforeCanPickup == 0) 
		            {
		            	if(HudOptions.magnetAmuletParticles)
		            	{
		            		player.worldObj.spawnParticle("portal", item.posX, item.posY, item.posZ, 0D, 0.1D, 0D);
		            	}
		               item.onCollideWithPlayer((EntityPlayer) player);
		            }
		        }
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
