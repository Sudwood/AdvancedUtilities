package com.sudwood.advancedutilities.items;

import java.util.List;

import com.sudwood.advancedutilities.AdvancedUtilities;
import com.sudwood.advancedutilities.client.SoundHandler;
import com.sudwood.advancedutilities.container.InventoryItem;
import com.sudwood.advancedutilities.entity.EntityBullet;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemMagazine extends Item
{
	public ItemMagazine()
	{
		super();
		this.setMaxStackSize(1);
	}
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) 
	{
		if(par1ItemStack.getTagCompound() == null)
		{
			par1ItemStack.setTagCompound(new NBTTagCompound());
		}
		NBTTagCompound tag = par1ItemStack.getTagCompound();
		InventoryItem inv = new InventoryItem(par1ItemStack);
		par3List.add("Bullets: "+inv.getTotalItems(AdvancedUtilitiesItems.bronzeBullet));
	}
	/**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
    	par3EntityPlayer.openGui(AdvancedUtilities.instance, AdvancedUtilities.bulletMagazineGui, par2World, (int) par3EntityPlayer.posX, (int) par3EntityPlayer.posY, (int) par3EntityPlayer.posZ);
     return par1ItemStack;
    }
	@Override
	public int getMaxItemUseDuration(ItemStack stack) 
	{
		return 68000; // return any value greater than zero
	}
	
	  
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister)
    {
    	this.itemIcon = par1IconRegister.registerIcon(AdvancedUtilities.MODID+":"+"bulletmagazine");
    }
}
