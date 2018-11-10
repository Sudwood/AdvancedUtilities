package com.sudwood.advancedutilities.items;

import java.util.List;

import org.lwjgl.input.Keyboard;

import com.sudwood.advancedutilities.AdvancedUtilities;
import com.sudwood.advancedutilities.container.InventoryVoidRing;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class ItemVoidRing  extends Item implements IBauble
{
	public ItemVoidRing()
	{
		this.maxStackSize =1;
	}
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) 
    {
		NBTTagCompound tag = stack.getTagCompound();
		if(tag==null)
		{
			tag = new NBTTagCompound();
		}
		list.add("Void: "+tag.getBoolean("Void"));
		if(!Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindSneak.getKeyCode()))
		{
			list.add("<press "+Keyboard.getKeyName(Minecraft.getMinecraft().gameSettings.keyBindSneak.getKeyCode())+" for more information>");
		}
		else
		{
			list.add("Void determines if items collected should be stored in the ring");
			list.add("until full <false> or deleted <true> Shift right click to change");
			InventoryVoidRing inv = new InventoryVoidRing(stack);
			if(inv!=null)
			{
				for(int i = 0;i<inv.getSizeInventory(); i++)
				{
					if(inv.getStackInSlot(i)!=null)
					{
						list.add(inv.getStackInSlot(i).getDisplayName()+" : "+inv.getStackInSlot(i).stackSize);
					}
				}
			}
		}
    }

	@SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister icon)
    {
		this.itemIcon = icon.registerIcon("advancedutilities:voidring");
    }
	@Override
	public BaubleType getBaubleType(ItemStack itemstack) {
		// TODO Auto-generated method stub
		return BaubleType.RING;
	}

	@Override
	public void onWornTick(ItemStack itemstack, EntityLivingBase player)
	{
		
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
	 @Override
		public int getMaxItemUseDuration(ItemStack stack) 
		{
			return 68000; // return any value greater than zero
		}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
		NBTTagCompound tag = stack.getTagCompound();
		if(tag == null)
		{
			tag = new NBTTagCompound();
			stack.setTagCompound(tag);
		}
		if(tag!=null)
		{
			if(player.isSneaking())
			{
				tag.setBoolean("Void", !tag.getBoolean("Void"));
				if(!world.isRemote)
				player.addChatMessage(new ChatComponentText("Swapped Void Ring to: "+tag.getBoolean("Void")));
				stack.setTagCompound(tag);
				return stack;
			}
			
		}
			player.openGui(AdvancedUtilities.instance, AdvancedUtilities.voidRingGui, world, (int)player.posX, (int)player.posY, (int)player.posZ);
		
		return stack;
    }

}
