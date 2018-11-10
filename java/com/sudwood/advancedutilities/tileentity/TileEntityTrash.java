package com.sudwood.advancedutilities.tileentity;

import java.util.Random;

import com.sudwood.advancedutilities.HelperLibrary;
import com.sudwood.advancedutilities.items.AdvancedUtilitiesItems;
import com.sudwood.advancedutilities.items.ItemIngot;
import com.sudwood.advancedutilities.recipes.CompressRecipes;
import com.sudwood.advancedutilities.recipes.CrushRecipes;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityTrash extends TileEntity implements IInventory
{
	 private ItemStack[] inventory = new ItemStack[1];
    public int stackSize = 0;

    private String field_145958_o;

    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory()
    {
        return inventory.length;
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
    
    public void updateEntity()
    {
    	inventory[0]=null;
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
    	if(par2ItemStack!= null)
    	{
    		ItemStack temp = par2ItemStack.copy();
    		temp.stackSize = 1;
    		if(inventory[par1]== null || par2ItemStack.stackSize - inventory[par1].stackSize >= 0)
    		{
	    		if(this.isItemValidForSlot(par1, temp))
	    		{
	    			inventory[par1] = par2ItemStack;
	    		}
    		}
    		else if(par2ItemStack.stackSize - inventory[par1].stackSize < 0)
    		{
    				inventory[par1] = par2ItemStack;
    		}
    	}
    	else
    	{
    		inventory[par1] = par2ItemStack;
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
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
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
     * Returns true if automation can insert the given item in the given slot from the given side. Args: Slot, item,
     * side
     */
    public boolean canInsertItem(int par1, ItemStack par2ItemStack, int par3)
    {
    	if(stackSize>=(this.getSizeInventory()*this.getInventoryStackLimit()))
    		return false;
        return this.isItemValidForSlot(par1, par2ItemStack);
    }

    /**
     * Returns true if automation can extract the given item in the given slot from the given side. Args: Slot, item,
     * side
     */
    public boolean canExtractItem(int par1, ItemStack par2ItemStack, int par3)
    {
    	if(inventory[par1]!=null)
    	{
    		return true;
    	}
        return false;
    }
}