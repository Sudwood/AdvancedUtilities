package com.sudwood.advancedutilities.tileentity;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityWoodenCrate extends TileEntity implements IInventory
{
	 private ItemStack[] inventory = new ItemStack[64];
    private static final int[] slotsTop = new int[] {0};
    private static final int[] slotsBottom = new int[] {5};
    private static final int[] slotsSides = new int[] {4};
    public int stackSize = 0;
    private Random rand = new Random();

    private String field_145958_o;

    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory()
    {
        return 64;
    }

    /**
     * Returns the stack in slot i
     */
    public ItemStack getStackInSlot(int par1)
    {
    	if(par1<this.getSizeInventory())
        return this.inventory[par1];
    	else
    		return null;
    }
    
    public int checkStackSize()
    {
    	int temp = 0;
    	for(int i = 0; i < 64; i++)
        {
        	if(inventory[i]!= null)
        	{
        		temp+=inventory[i].stackSize;
        	}
        }
    	return temp;
    }

    /**
     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
     * new stack.
     */
    public ItemStack decrStackSize(int par1, int par2)
    {
        if (inventory[par1] != null)
        {
            ItemStack itemstack;

            if (inventory[par1].stackSize <= par2)
            {
                itemstack = inventory[par1];
                inventory[par1] = null;
                stackSize = 0;
                for(int i = 0; i < 64; i++)
                {
                	if(inventory[i]!= null)
                	{
                		stackSize+=inventory[i].stackSize;
                	}
                }
                return itemstack;
            }
            else
            {
                itemstack = inventory[par1].splitStack(par2);

                if (inventory[par1].stackSize == 0)
                {
                    inventory[par1] = null;
                }
                stackSize = 0;
                for(int i = 0; i < 64; i++)
                {
                	if(inventory[i]!= null)
                	{
                		stackSize+=inventory[i].stackSize;
                	}
                }
                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }

    /**
     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
     * like when you close a workbench GUI.
     */
    public ItemStack getStackInSlotOnClosing(int par1)
    {
        return this.inventory[par1];
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
    {

    	inventory[par1] = par2ItemStack;
        stackSize = 0;
        for(int i = 0; i < 64; i++)
        {
        	if(inventory[i]!= null)
        	{
        		stackSize+=inventory[i].stackSize;
        	}
        }
    }

    /**
     * Returns the name of the inventory
     */
    public String getInventoryName()
    {
        return this.hasCustomInventoryName() ? this.field_145958_o : "container.furnace";
    }

    /**
     * Returns if the inventory is named
     */
    public boolean hasCustomInventoryName()
    {
        return this.field_145958_o != null && this.field_145958_o.length() > 0;
    }

    public void func_145951_a(String p_145951_1_)
    {
        this.field_145958_o = p_145951_1_;
    }

    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);
        NBTTagList nbttaglist = tag.getTagList("Items", 10);
        this.inventory = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.inventory.length)
            {
                this.inventory[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
        stackSize = tag.getInteger("stackSize");
        if (tag.hasKey("CustomName", 8))
        {
            this.field_145958_o = tag.getString("CustomName");
        }
    }

    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
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
        tag.setInteger("stackSize", stackSize);
        if (this.hasCustomInventoryName())
        {
        	tag.setString("CustomName", this.field_145958_o);
        }
    }
    public void writeInventory(NBTTagCompound tag)
    {
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
    
    public void readInventory(NBTTagCompound tag)
    {
    	NBTTagList nbttaglist = tag.getTagList("Items", 10);
        this.inventory = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.inventory.length)
            {
                this.inventory[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
        stackSize = tag.getInteger("stackSize");
    }

    /**
     * Returns the maximum stack size for a inventory slot.
     */
    public int getInventoryStackLimit()
    {
        return 64;
    }


    /**
     * Do not make give this method the name canInteractWith because it clashes with Container
     */
    public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
    {
        return true;
    }

    public void openInventory() {}

    public void closeInventory() {}

    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
     */
    public boolean isItemValidForSlot(int par1, ItemStack item1)
    {
        return true;
    }
    

    /**
     * Returns an array containing the indices of the slots that can be accessed by automation on the given side of this
     * block.
     */
    public int[] getAccessibleSlotsFromSide(int par1)
    {
        return new int[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63};
    }

    /**
     * Returns true if automation can insert the given item in the given slot from the given side. Args: Slot, item,
     * side
     */
    public boolean canInsertItem(int par1, ItemStack par2ItemStack, int par3)
    {
        return this.isItemValidForSlot(par1, par2ItemStack);
    }

    /**
     * Returns true if automation can extract the given item in the given slot from the given side. Args: Slot, item,
     * side
     */
    public boolean canExtractItem(int par1, ItemStack par2ItemStack, int par3)
    {
        return true;
    }
}