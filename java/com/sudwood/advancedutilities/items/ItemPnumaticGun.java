package com.sudwood.advancedutilities.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidContainerRegistry;

import com.sudwood.advancedutilities.AdvancedUtilities;
import com.sudwood.advancedutilities.client.SoundHandler;
import com.sudwood.advancedutilities.container.InventoryItem;
import com.sudwood.advancedutilities.entity.EntityBullet;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemPnumaticGun extends Item
{
	public static final int maxStorage = 16*FluidContainerRegistry.BUCKET_VOLUME;
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) 
	{
		if(par1ItemStack.getTagCompound() == null)
		{
			par1ItemStack.setTagCompound(new NBTTagCompound());
		}
		NBTTagCompound tag = par1ItemStack.getTagCompound();
		if(tag.getInteger("maxTankAmount") == 0)
		{
			tag.setInteger("maxTankAmount", this.maxStorage);
		}
		par3List.add("Steam: "+tag.getInteger("tankAmount")+" / "+tag.getInteger("maxTankAmount")+" mB");
		InventoryItem inv = new InventoryItem(par1ItemStack);
		par3List.add("Bullets: "+inv.getTotalItems(AdvancedUtilitiesItems.bronzeBullet));
	}
	/**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
    	InventoryItem inv = new InventoryItem(par1ItemStack);
    	NBTTagCompound tag = par1ItemStack.getTagCompound();
    	if((!par3EntityPlayer.isSneaking() || !par3EntityPlayer.onGround) && inv.hasItem(AdvancedUtilitiesItems.bronzeBullet) && tag.getInteger("tankAmount") >= 50)
    	{
	     float f = 50F;
	    
		 EntityBullet bullet = new EntityBullet(par2World, par3EntityPlayer, f * 2.0F);
	
	     if (f == 1.0F)
	     {
	         bullet.setIsCritical(true);
	     }
	
	     bullet.setDamage(8);
	
	     SoundHandler.playAtEntity(par2World, par3EntityPlayer, "gunshot", 1.4F, 0.6F); 
	     
	     bullet.canBePickedUp = 1;
	     if(!par2World.isRemote)
	     {
	    	 par2World.spawnEntityInWorld(bullet);
	     }
	     int slot = inv.checkItem(AdvancedUtilitiesItems.bronzeBullet);
	     inv.decrStackSize(slot, 1);
	     tag.setInteger("tankAmount", tag.getInteger("tankAmount")-50);
    	}
    	if(par3EntityPlayer.isSneaking() && !par2World.isRemote && par3EntityPlayer.onGround)
    	{
    		par3EntityPlayer.openGui(AdvancedUtilities.instance, AdvancedUtilities.pnumaticGunGui, par2World, (int) par3EntityPlayer.posX, (int) par3EntityPlayer.posY, (int) par3EntityPlayer.posZ);
    	}
     
     return par1ItemStack;
    }
	@Override
	public int getMaxItemUseDuration(ItemStack stack) 
	{
		return 68000; // return any value greater than zero
	}
}
