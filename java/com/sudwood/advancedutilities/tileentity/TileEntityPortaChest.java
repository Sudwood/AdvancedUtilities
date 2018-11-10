package com.sudwood.advancedutilities.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityPortaChest extends TileEntity implements IInventory
{
	private int chestType;
	public static final int WOOD = 0;
	public static final int BRONZE = 1;
	public static final int GOLD = 2;
	public static final int DIAMOND = 3;
	public static final int PLATINUM = 4;
	private String[] names = {"Wood", "Bronze", "Gold","Diamond","Platinum"};
	private ItemStack[] inventory;
	public boolean isOpen = false;

	public TileEntityPortaChest()
	{
		
	}
	public TileEntityPortaChest(int type)
	{
		chestType = type;
		this.InitializeInvnetory();
	}
	
	public void InitializeInvnetory()
	{
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
				break;
		}
	}
	
	public int getType()
	{
		return chestType;
	}
	public void setType(int ty)
	{
		chestType = ty;
	}
	
	public void changeOpenState(boolean bool)
	{
		isOpen = bool;
		chestType++;
		if(chestType > PLATINUM)
			chestType = WOOD;
		this.markDirty();
	}
	@Override
	public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);
        chestType=tag.getInteger("chestType");
        isOpen = tag.getBoolean("isOpen");
        this.InitializeInvnetory();
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
	
	public void readFromNBTWithoutCoords(NBTTagCompound tag)
	{
		chestType=tag.getInteger("chestType");
		isOpen = tag.getBoolean("isOpen");
		this.InitializeInvnetory();
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

    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
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
    public void writeToNBTWithoutCoords(NBTTagCompound tag)
    {
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

	@Override
	public int getSizeInventory() 
	{
		if(inventory!=null)
		return inventory.length;
		if(inventory == null)
		{
			this.InitializeInvnetory();
		}
		return 0;
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
	}

	@Override
	public String getInventoryName() 
	{
		return names[chestType]+" Porta Chest";
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
	public boolean isUseableByPlayer(EntityPlayer p_70300_1_) 
	{
		return true;
	}

	@Override
	public void openInventory() 
	{
		worldObj.playSound(xCoord, yCoord, zCoord, "random.chestopen", 15, 2, true);
		this.isOpen = true;
	}

	@Override
	public void closeInventory() 
	{
		this.isOpen = false;
		//worldObj.playSound(xCoord, yCoord, zCoord, "random.chestclosed", 15, 2, true);
		//worldObj.playSoundAtEntity(player, "random.chestclosed", 15, 2);
	}

	@Override
	public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) 
	{
		return true;
	}

}
