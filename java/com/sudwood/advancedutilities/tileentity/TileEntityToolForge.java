package com.sudwood.advancedutilities.tileentity;

import com.sudwood.advancedutilities.items.AdvancedUtilitiesItems;
import com.sudwood.advancedutilities.items.ItemBETool;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
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

public class TileEntityToolForge extends TileEntity implements IInventory
{
    private static final int[] slotsTop = new int[] {0};
    private static final int[] slotsBottom = new int[] {2, 1};
    private static final int[] slotsSides = new int[] {1};
    /**
     * The ItemStacks that hold the items currently being used in the furnace
     */
    private ItemStack[] inventory = new ItemStack[3];
    
    /**
     * The number of ticks that the current item has been cooking for
     */
    public int furnaceCookTime;
    private boolean isCrafting;
    private String field_145958_o;
    private static final String __OBFID = "CL_00000357";

    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory()
    {
        return this.inventory.length;
    }

    /**
     * Returns the stack in slot i
     */
    public ItemStack getStackInSlot(int par1)
    {
        return this.inventory[par1];
    }

    /**
     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
     * new stack.
     */
    public ItemStack decrStackSize(int par1, int par2)
    {
        if (this.inventory[par1] != null)
        {
            ItemStack itemstack;

            if (this.inventory[par1].stackSize <= par2)
            {
                itemstack = this.inventory[par1];
                this.inventory[par1] = null;
                return itemstack;
            }
            else
            {
                itemstack = this.inventory[par1].splitStack(par2);

                if (this.inventory[par1].stackSize == 0)
                {
                    this.inventory[par1] = null;
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
        if (this.inventory[par1] != null)
        {
            ItemStack itemstack = this.inventory[par1];
            this.inventory[par1] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
    {
        this.inventory[par1] = par2ItemStack;

        if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
        {
            par2ItemStack.stackSize = this.getInventoryStackLimit();
        }
    }

    /**
     * Returns the name of the inventory
     */
    public String getInventoryName()
    {
        return "Tool Forge";
    }

    /**
     * Returns if the inventory is named
     */
    public boolean hasCustomInventoryName()
    {
        return true;
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
        this.furnaceCookTime = tag.getShort("CookTime");
        this.isCrafting = tag.getBoolean("isCrafting");
        if (tag.hasKey("CustomName", 8))
        {
            this.field_145958_o = tag.getString("CustomName");
        }
    }

    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
        tag.setShort("CookTime", (short)this.furnaceCookTime);
        tag.setBoolean("isCrafting", isCrafting);
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

    /**
     * Returns the maximum stack size for a inventory slot.
     */
    public int getInventoryStackLimit()
    {
        return 1;
    }

    /**
     * Returns an integer between 0 and the passed value representing how close the current item is to being completely
     * cooked
     */
    @SideOnly(Side.CLIENT)
    public int getCookProgressScaled(int p_145953_1_)
    {
        return this.furnaceCookTime * p_145953_1_ / 1200;
    }




    public void updateEntity()
    {
      if(isCrafting() && this.canSmelt())
      {
    	  this.furnaceCookTime++;
    	  if(!worldObj.isRemote)
    	  {
    		  if(this.furnaceCookTime == 1200)
    		  {
    			  this.smeltItem();
    			  this.isCrafting = false;
    			  this.furnaceCookTime = 0;
    		  }
    	  }
      }
      if(!this.canSmelt())
      {
    	  this.furnaceCookTime = 0;
    	  this.isCrafting = false;
      }
    }

    public boolean isCrafting()
    {
    	return isCrafting;
    }
    
    public void startIsCrafting()
    {
    	if(this.canSmelt())
    		isCrafting = true;
    }
    
    /**
     * Returns true if the furnace can smelt an item, i.e. has a source item, destination stack isn't full, etc.
     */
    private boolean canSmelt()
    {
        if (this.inventory[0] == null || this.inventory[1] == null)
        {
            return false;
        }
        else
        {
            ItemStack itemstack = this.getToolResult(inventory[0], inventory[1]);
            if (itemstack == null) return false;
            if (this.inventory[2] == null) return true;
            if (this.inventory[2] !=null) return false;
            return false;
        }
    }

    public ItemStack getToolResult(ItemStack top, ItemStack rod)
    {
    	if(top == null || rod == null)
    		return null;
    	if(top.isItemEqual(new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 2)))
    	{
    		if(rod.isItemEqual(new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 0)))
    		{
    			return new ItemStack(AdvancedUtilitiesItems.toolBE, 1, 2);
    		}
    		if(rod.isItemEqual(new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 1)))
    		{
    			return new ItemStack(AdvancedUtilitiesItems.toolBE, 1, 1);
    		}
    		if(rod.isItemEqual(new ItemStack(Items.stick, 1, 0)))
    		{
    			return new ItemStack(AdvancedUtilitiesItems.toolBE, 1, 0);
    		}
    	}
    	if(top.isItemEqual(new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 3)))
    	{
    		if(rod.isItemEqual(new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 0)))
    		{
    			return new ItemStack(AdvancedUtilitiesItems.toolBE, 1, 5);
    		}
    		if(rod.isItemEqual(new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 1)))
    		{
    			return new ItemStack(AdvancedUtilitiesItems.toolBE, 1, 4);
    		}
    		if(rod.isItemEqual(new ItemStack(Items.stick, 1, 0)))
    		{
    			return new ItemStack(AdvancedUtilitiesItems.toolBE, 1, 3);
    		}
    	}
    	if(top.isItemEqual(new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 4)))
    	{
    		if(rod.isItemEqual(new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 0)))
    		{
    			return new ItemStack(AdvancedUtilitiesItems.toolBE, 1, 8);
    		}
    		if(rod.isItemEqual(new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 1)))
    		{
    			return new ItemStack(AdvancedUtilitiesItems.toolBE, 1, 7);
    		}
    		if(rod.isItemEqual(new ItemStack(Items.stick, 1, 0)))
    		{
    			return new ItemStack(AdvancedUtilitiesItems.toolBE, 1, 6);
    		}
    	}
    	if(top.isItemEqual(new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 5)))
    	{
    		if(rod.isItemEqual(new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 0)))
    		{
    			return new ItemStack(AdvancedUtilitiesItems.toolBE, 1, 11);
    		}
    		if(rod.isItemEqual(new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 1)))
    		{
    			return new ItemStack(AdvancedUtilitiesItems.toolBE, 1, 10);
    		}
    		if(rod.isItemEqual(new ItemStack(Items.stick, 1, 0)))
    		{
    			return new ItemStack(AdvancedUtilitiesItems.toolBE, 1, 9);
    		}
    	}
    	if(top.isItemEqual(new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 6)))
    	{
    		if(rod.isItemEqual(new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 0)))
    		{
    			return new ItemStack(AdvancedUtilitiesItems.toolBE, 1, 14);
    		}
    		if(rod.isItemEqual(new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 1)))
    		{
    			return new ItemStack(AdvancedUtilitiesItems.toolBE, 1, 13);
    		}
    		if(rod.isItemEqual(new ItemStack(Items.stick, 1, 0)))
    		{
    			return new ItemStack(AdvancedUtilitiesItems.toolBE, 1, 12);
    		}
    	}
    	if(top.isItemEqual(new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 7)))
    	{
    		if(rod.isItemEqual(new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 0)))
    		{
    			return new ItemStack(AdvancedUtilitiesItems.toolBE, 1, 17);
    		}
    		if(rod.isItemEqual(new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 1)))
    		{
    			return new ItemStack(AdvancedUtilitiesItems.toolBE, 1, 16);
    		}
    		if(rod.isItemEqual(new ItemStack(Items.stick, 1, 0)))
    		{
    			return new ItemStack(AdvancedUtilitiesItems.toolBE, 1, 15);
    		}
    	}
    	if(top.isItemEqual(new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 8)))
    	{
    		if(rod.isItemEqual(new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 0)))
    		{
    			return new ItemStack(AdvancedUtilitiesItems.toolBE, 1, 20);
    		}
    		if(rod.isItemEqual(new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 1)))
    		{
    			return new ItemStack(AdvancedUtilitiesItems.toolBE, 1, 19);
    		}
    		if(rod.isItemEqual(new ItemStack(Items.stick, 1, 0)))
    		{
    			return new ItemStack(AdvancedUtilitiesItems.toolBE, 1, 18);
    		}
    	}
    	if(top.isItemEqual(new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 9)))
    	{
    		if(rod.isItemEqual(new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 0)))
    		{
    			return new ItemStack(AdvancedUtilitiesItems.toolBE, 1, 23);
    		}
    		if(rod.isItemEqual(new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 1)))
    		{
    			return new ItemStack(AdvancedUtilitiesItems.toolBE, 1, 22);
    		}
    		if(rod.isItemEqual(new ItemStack(Items.stick, 1, 0)))
    		{
    			return new ItemStack(AdvancedUtilitiesItems.toolBE, 1, 21);
    		}
    	}
    	if(top.getItem() == AdvancedUtilitiesItems.toolBE && (top.getItemDamage() < 12))
    	{
    		if(rod.getItem() == Items.iron_ingot)
    		{
    			return new ItemStack(top.getItem(), 1, top.getItemDamage());
    		}
    	}
    	if(top.getItem() == AdvancedUtilitiesItems.toolBE && (top.getItemDamage() > 11))
    	{
    		if(rod.getItem() == AdvancedUtilitiesItems.ingotBronze)
    		{
    			return new ItemStack(top.getItem(), 1, top.getItemDamage());
    		}
    	}
    	if(top.getItem() == AdvancedUtilitiesItems.jackHammer)
    	{
    		ItemStack result = top.copy();
    		if(EnchantmentHelper.getEnchantmentLevel(Enchantment.efficiency.effectId, rod) > 0)
    		{
    			result.addEnchantment(Enchantment.efficiency, EnchantmentHelper.getEnchantmentLevel(Enchantment.efficiency.effectId, rod));
    		}
    		if(EnchantmentHelper.getEnchantmentLevel(Enchantment.fortune.effectId, rod) > 0 && EnchantmentHelper.getEnchantmentLevel(Enchantment.silkTouch.effectId, rod)<= 0)
    		{
    			result.addEnchantment(Enchantment.fortune, EnchantmentHelper.getEnchantmentLevel(Enchantment.fortune.effectId, rod));
    		}
    		if(EnchantmentHelper.getEnchantmentLevel(Enchantment.silkTouch.effectId, rod) > 0 && EnchantmentHelper.getEnchantmentLevel(Enchantment.fortune.effectId, rod)<= 0)
    		{
    			result.addEnchantment(Enchantment.silkTouch, EnchantmentHelper.getEnchantmentLevel(Enchantment.silkTouch.effectId, rod));
    		}
    		if(EnchantmentHelper.getEnchantmentLevel(Enchantment.fireAspect.effectId, rod) > 0)
    		{
    			result.addEnchantment(Enchantment.fireAspect, EnchantmentHelper.getEnchantmentLevel(Enchantment.fireAspect.effectId, rod));
    		}
    		if(EnchantmentHelper.getEnchantmentLevel(Enchantment.sharpness.effectId, rod) > 0)
    		{
    			result.addEnchantment(Enchantment.sharpness, EnchantmentHelper.getEnchantmentLevel(Enchantment.sharpness.effectId, rod));
    		}
    		if(EnchantmentHelper.getEnchantmentLevel(Enchantment.smite.effectId, rod) > 0)
    		{
    			result.addEnchantment(Enchantment.smite, EnchantmentHelper.getEnchantmentLevel(Enchantment.smite.effectId, rod));
    		}
    		if(EnchantmentHelper.getEnchantmentLevel(Enchantment.baneOfArthropods.effectId, rod) > 0)
    		{
    			result.addEnchantment(Enchantment.baneOfArthropods, EnchantmentHelper.getEnchantmentLevel(Enchantment.baneOfArthropods.effectId, rod));
    		}
    		return result;
    	}
    	
    	return null;
    }
    
    
    /**
     * Turn one item from the furnace source stack into the appropriate smelted item in the furnace result stack
     */
    public void smeltItem()
    {
        if (this.canSmelt())
        {
            ItemStack itemstack = this.getToolResult(inventory[0], inventory[1]);

            if (this.inventory[2] == null)
            {
                this.inventory[2] = itemstack.copy();
            }
            
            ItemBETool.checkCreated(inventory[2]);

            --this.inventory[0].stackSize;

            if (this.inventory[0].stackSize <= 0)
            {
                this.inventory[0] = null;
            }

        	--this.inventory[1].stackSize;

            if (this.inventory[1].stackSize <= 0)
            {
                this.inventory[1] = null;
            }
            
        }
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
    public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack)
    {
        return false;
    }

 
}