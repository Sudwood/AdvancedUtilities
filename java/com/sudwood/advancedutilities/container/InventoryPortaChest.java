package com.sudwood.advancedutilities.container;

import com.sudwood.advancedutilities.HelperLibrary;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class InventoryPortaChest implements IInventory
{
	private ItemStack chest;
	private ItemStack[] inventory;
	public int chestType;
	public static final int WOOD = 0;
	public static final int BRONZE = 1;
	public static final int GOLD = 2;
	public static final int DIAMOND = 3;
	public static final int PLATINUM = 4;
	public boolean isOpen =false;
	public InventoryPortaChest(ItemStack held)
	{
		chest = held;
			chestType = held.getItemDamage();
			switch(chestType)
			{
			case WOOD:
				inventory = new ItemStack[27];
				break;
			case BRONZE:
				inventory = new ItemStack[54];
				break;
			case GOLD:
				inventory = new ItemStack[81];
				break;
			case DIAMOND:
				inventory = new ItemStack[108];
				break;
			case PLATINUM:
				inventory = new ItemStack[135];
				break;
			default:
				inventory = new ItemStack[27];
					break;
			}
		this.readFromNBT(held.getTagCompound());
	}
	@Override
	public int getSizeInventory() 
	{
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) 
	{
		if(slot>=inventory.length)
		{
			return null;
		}
		return inventory[slot];
	}

	@Override
	public ItemStack decrStackSize(int par1, int par2) 
	{
		if (inventory[par1] != null)
        {
            ItemStack itemstack;

            if (inventory[par1].stackSize <= par2)
            {
                itemstack = inventory[par1];
                inventory[par1] = null;
                return itemstack;
            }
            else
            {
                itemstack = inventory[par1].splitStack(par2);

                if (inventory[par1].stackSize == 0)
                {
                    inventory[par1] = null;
                }
                return itemstack;
            }
        }
        else
        {
            return null;
        }
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) 
	{
		return this.getStackInSlot(slot);
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) 
	{
		if(slot>=this.getSizeInventory())
		{
			return;
		}
		inventory[slot]=stack;
		this.markDirty();
	}

	@Override
	public String getInventoryName() 
	{
		return "Porta Chest";
	}

	@Override
	public boolean hasCustomInventoryName() 
	{
		return true;
	}

	@Override
	public int getInventoryStackLimit() 
	{
		return 64;
	}

	@Override
	public void markDirty() 
	{
		for (int i = 0; i < getSizeInventory(); ++i)
		{
			if (getStackInSlot(i) != null && getStackInSlot(i).stackSize == 0)
			inventory[i] = null;
		}
		// be sure to write to NBT when the inventory changes!
		if(chest!=null)
		writeToNBT(chest.getTagCompound());
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) 
	{
		return HelperLibrary.areItemStacksSameItemAndDamage(player.getHeldItem(), chest);
	}

	@Override
	public void openInventory() 
	{
		isOpen = true;
	}

	@Override
	public void closeInventory() 
	{
		isOpen = false;
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) 
	{
		return true;
	}
	
	
	public void writeToNBT(NBTTagCompound tag)
    {
		if(tag == null)
		{
			tag = new NBTTagCompound();
		}
    	tag.setInteger("chestType", chestType);
    	tag.setBoolean("isOpen", isOpen);
    	NBTTagList nbttaglist = new NBTTagList();
    	
        for (int i = 0; i < this.inventory.length; ++i)
        {
            if (this.inventory[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.inventory[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        tag.setTag("Items", nbttaglist);
    }
	
	public void readFromNBT(NBTTagCompound tag)
	{
		if(tag == null)
		{
			tag = new NBTTagCompound();
			tag.setInteger("chestType", chestType);
			return;
		}
		chestType=tag.getInteger("chestType");
		isOpen = tag.getBoolean("isOpen");
		NBTTagList nbttaglist = tag.getTagList("Items", 10);
		for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.inventory.length)
            {
                this.inventory[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
	}

}
