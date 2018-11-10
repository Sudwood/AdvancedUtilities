package com.sudwood.advancedutilities.container;

import com.sudwood.advancedutilities.HelperLibrary;
import com.sudwood.advancedutilities.items.ItemVoidRing;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

public class InventoryVoidRing implements IInventory
{
	private String name = "Inventory Void Ring";
	
	/** Defining your inventory size this way is handy */
	public static final int INV_SIZE = 5;
	private int[] stacks = new int[INV_SIZE];
	/** Inventory's size must be same as number of slots you add to the Container class */
	private ItemStack[] inventory = new ItemStack[INV_SIZE];
	
	/** Provides NBT Tag Compound to reference */
	private final ItemStack invItem;
	
	public int checkItem(Item item)
    {
        for (int i = 0; i < this.INV_SIZE; ++i)
        {
            if (this.inventory[i] != null && this.inventory[i].getItem() == item)
            {
                return i;
            }
        }

        return -1;
    }
	
	public int checkItem(ItemStack stack)
    {
        for (int i = 0; i < this.INV_SIZE; ++i)
        {
            if (this.inventory[i] != null && HelperLibrary.areItemStacksSameItemAndDamageAndNBT(stack, inventory[i]))
            {
                return i;
            }
        }

        return -1;
    }
	
	public int getMaxSizeInventory(Item invItem)
	{
		int total = this.INV_SIZE;
		if(invItem == null)
		{
			return total;
		}
		else
		{
			for (int i = 0; i < this.INV_SIZE; ++i)
	        {
	        	if (this.inventory[i] != null && this.inventory[i].getItem() == invItem)
	            {
	        		total--;
	        		InventoryVoidRing temp = new InventoryVoidRing(inventory[i]);
	        		total+=temp.getSizeInventory();
	        		
	            }
	        }
		}
		return total;
	}
	
	public void decreaseStackedItem(Item item, Item invItem)
	{
		for (int i = 0; i < this.INV_SIZE; ++i)
        {
        	if (this.inventory[i] != null && this.inventory[i].getItem() == invItem)
            {
        		InventoryVoidRing temp = new InventoryVoidRing(inventory[i]);
                for(int ix = 0; ix < temp.getSizeInventory(); ix++)
                {
             	   if(temp.getStackInSlot(ix)!=null && temp.getStackInSlot(ix).getItem() == item)
             	   {
             		   temp.decrStackSize(ix, 1);
             		   return;
             	   }
                }
            }
            if (this.inventory[i] != null && this.inventory[i].getItem() == item)
            {
                this.decrStackSize(i, 1);
                return;
            }
        }
	}
	
	public int checkItemWithInvItems(Item item, Item invItem)
    {
        for (int i = 0; i < this.INV_SIZE; ++i)
        {
        	if (this.inventory[i] != null && this.inventory[i].getItem() == invItem)
            {
        		InventoryVoidRing temp = new InventoryVoidRing(inventory[i]);
                for(int ix = 0; ix < temp.getSizeInventory(); ix++)
                {
             	   if(temp.getStackInSlot(ix)!=null && temp.getStackInSlot(ix).getItem() == item)
             	   {
             		   return i;
             	   }
                }
            }
            if (this.inventory[i] != null && this.inventory[i].getItem() == item)
            {
                return i;
            }
        }

        return -1;
    }
	
	public int getTotalItems(Item item)
	{
		int total = 0;
		for (int i = 0; i < this.INV_SIZE; ++i)
        {
            if (this.inventory[i] != null && this.inventory[i].getItem() == item)
            {
               total+=inventory[i].stackSize;
            }
        }
		return total;
	}
	
	public int getTotalItemsWithInventoryItems(Item item, Item invItem)
	{
		int total = 0;
		for (int i = 0; i < this.INV_SIZE; ++i)
        {
            if (this.inventory[i] != null && this.inventory[i].getItem() == invItem)
            {
               InventoryVoidRing temp = new InventoryVoidRing(inventory[i]);
               for(int ix = 0; ix < temp.getSizeInventory(); ix++)
               {
            	   if(temp.getStackInSlot(ix)!=null && temp.getStackInSlot(ix).getItem() == invItem)
            	   {
            		total+= temp.getTotalItemsWithInventoryItems(item, invItem);   
            	   }
            	   else if(temp.getStackInSlot(ix)!=null && temp.getStackInSlot(ix).getItem() == item)
            	   {
            		   total+= temp.getStackInSlot(ix).stackSize;
            	   }
               }
            }
            if (this.inventory[i] != null && this.inventory[i].getItem() == item)
            {
            	total+=inventory[i].stackSize;
            }
        }
		return total;
	}
	
	public boolean hasItem(Item item)
    {
        int i = this.checkItem(item);
        return i >= 0;
    }
	
	public boolean hasItem(ItemStack item)
    {
        int i = this.checkItem(item);
        return i >= 0;
    }
	
	public boolean hasItemWithInvItem(Item item, Item invItem)
    {
		int i = this.checkItemWithInvItems(item, invItem);
		return i>=0;
    }
	
	/**
	* @param itemstack - the ItemStack to which this inventory belongs
	*/
	public InventoryVoidRing(ItemStack stack)
	{
		this.invItem = stack;
		if (!stack.hasTagCompound())
		{
			stack.setTagCompound(new NBTTagCompound());
		}
		readFromNBT(stack.getTagCompound());
	}
	
	@Override
	public int getSizeInventory()
	{
		return inventory.length;
	}
	
	@Override
	public ItemStack getStackInSlot(int slot)
	{
		return inventory[slot];
	}
	
	@Override
	public ItemStack decrStackSize(int slot, int amount)
	{
		ItemStack stack = getStackInSlot(slot);
		if(stack != null)
		{
			if(stack.stackSize > amount)
			{
				stack = stack.splitStack(amount);
				markDirty();
			}
			else
			{
				setInventorySlotContents(slot, null);
			}
		}
		return stack;
	}
	
	@Override
	public ItemStack getStackInSlotOnClosing(int slot)
	{
		ItemStack stack = getStackInSlot(slot);
		if(stack != null)
		{
			setInventorySlotContents(slot, null);
		}
		return stack;
	}
	
	@Override
	public void setInventorySlotContents(int slot, ItemStack itemstack)
	{
		this.inventory[slot] = itemstack;
		
		if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit())
		{
			itemstack.stackSize = this.getInventoryStackLimit();
		}
	
		markDirty();
	}
	
	@Override
	public String getInventoryName()
	{
		return name;
	}
	
	@Override
	public boolean hasCustomInventoryName()
	{
		return name.length() > 0;
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
		writeToNBT(invItem.getTagCompound());
	}
	
	/**
	 * Does no checks! Do checks before calling!!!!
	 * @param stack
	 * @return did it get put in inventory
	 */
	public boolean addItemStackToInventory(ItemStack stack 	)
	{
		for(int i = 0; i<this.getSizeInventory(); i++)
		{
			if(inventory[i] != null)
			{
				if(inventory[i].stackSize + stack.stackSize <= this.getInventoryStackLimit())
				{
					ItemStack temp = inventory[i];
					temp.stackSize+=stack.stackSize;
					this.setInventorySlotContents(i, temp);
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
	{
	// this will close the inventory if the player tries to move
	// the item that opened it, but you need to return this method
	// from the Container's canInteractWith method
	// an alternative would be to override the slotClick method and
	// prevent the current item slot from being clicked
		return true;
	}
	
	@Override
	public void openInventory() {}
	
	@Override
	public void closeInventory() 
	{
		
	}
	
	/**
	* This method doesn't seem to do what it claims to do, as
	* items can still be left-clicked and placed in the inventory
	* even when this returns false
	*/
	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemstack)
	{
	// Don't want to be able to store the inventory item within itself
	// Bad things will happen, like losing your inventory
	// Actually, this needs a custom Slot to work
		return !(itemstack.getItem() instanceof ItemVoidRing);
	}
	
	/**
	* A custom method to read our inventory from an ItemStack's NBT compound
	*/
	public void readFromNBT(NBTTagCompound tagcompound)
	{
		// Gets the custom taglist we wrote to this compound, if any
		// 1.7.2 change to compound.getTagList("ItemInventory", Constants.NBT.TAG_COMPOUND);
		NBTTagList items = tagcompound.getTagList("ItemInventory", Constants.NBT.TAG_COMPOUND);
		
		for (int i = 0; i < items.tagCount(); ++i)
		{
			// 1.7.2 change to items.getCompoundTagAt(i)
			NBTTagCompound item = (NBTTagCompound) items.getCompoundTagAt(i);
			byte slot = item.getByte("Slot");
			
			// Just double-checking that the saved slot index is within our inventory array bounds
			if (slot >= 0 && slot < getSizeInventory()) 
			{
				inventory[slot] = ItemStack.loadItemStackFromNBT(item);
			}
		}
	}
	
	/**
	* A custom method to write our inventory to an ItemStack's NBT compound
	*/
	public void writeToNBT(NBTTagCompound tagcompound)
	{
		// Create a new NBT Tag List to store itemstacks as NBT Tags
		NBTTagList items = new NBTTagList();
		
		for (int i = 0; i < getSizeInventory(); ++i)
		{
			// Only write stacks that contain items
			if (getStackInSlot(i) != null)
			{
				// Make a new NBT Tag Compound to write the itemstack and slot index to
				NBTTagCompound item = new NBTTagCompound();
				item.setInteger("Slot", i);
				// Writes the itemstack in slot(i) to the Tag Compound we just made
				getStackInSlot(i).writeToNBT(item);
				
				// add the tag compound to our tag list
				items.appendTag(item);
			}
		}
		// Add the TagList to the ItemStack's Tag Compound with the name "ItemInventory"
		tagcompound.setTag("ItemInventory", items);
	}
}