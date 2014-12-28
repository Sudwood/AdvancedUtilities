package com.sudwood.advancedutilities.tileentity;

import com.sudwood.advancedutilities.items.AdvancedUtilitiesItems;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
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
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityKiln extends TileEntity implements ISidedInventory
{
    private static final int[] slotsTop = new int[] {0};
    private static final int[] slotsBottom = new int[] {5};
    private static final int[] slotsSides = new int[] {4};
    /**
     * The ItemStacks that hold the items currently being used in the furnace
     */
    private ItemStack[] inventory = new ItemStack[6];
    /**
     * The number of ticks that the furnace will keep burning
     */
    public int furnaceBurnTime;
    /**
     * The number of ticks that a fresh copy of the currently-burning item would keep the furnace burning for
     */
    public int currentItemBurnTime;
    /**
     * The number of ticks that the current item has been cooking for
     */
    public int furnaceCookTime;
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

    public void readFromNBT(NBTTagCompound p_145839_1_)
    {
        super.readFromNBT(p_145839_1_);
        NBTTagList nbttaglist = p_145839_1_.getTagList("Items", 10);
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

        this.furnaceBurnTime = p_145839_1_.getShort("BurnTime");
        this.furnaceCookTime = p_145839_1_.getShort("CookTime");
        this.currentItemBurnTime = getItemBurnTime(this.inventory[4]);

        if (p_145839_1_.hasKey("CustomName", 8))
        {
            this.field_145958_o = p_145839_1_.getString("CustomName");
        }
    }

    public void writeToNBT(NBTTagCompound p_145841_1_)
    {
        super.writeToNBT(p_145841_1_);
        p_145841_1_.setShort("BurnTime", (short)this.furnaceBurnTime);
        p_145841_1_.setShort("CookTime", (short)this.furnaceCookTime);
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

        p_145841_1_.setTag("Items", nbttaglist);

        if (this.hasCustomInventoryName())
        {
            p_145841_1_.setString("CustomName", this.field_145958_o);
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
     * Returns an integer between 0 and the passed value representing how close the current item is to being completely
     * cooked
     */
    @SideOnly(Side.CLIENT)
    public int getCookProgressScaled(int p_145953_1_)
    {
        return this.furnaceCookTime * p_145953_1_ / 200;
    }

    /**
     * Returns an integer between 0 and the passed value representing how much burn time is left on the current fuel
     * item, where 0 means that the item is exhausted and the passed value means that the item is fresh
     */
    @SideOnly(Side.CLIENT)
    public int getBurnTimeRemainingScaled(int p_145955_1_)
    {
        if (this.currentItemBurnTime == 0)
        {
            this.currentItemBurnTime = 80;
        }

        return this.furnaceBurnTime * p_145955_1_ / this.currentItemBurnTime;
    }

    /**
     * Furnace isBurning
     */
    public boolean isBurning()
    {
        return this.furnaceBurnTime > 0;
    }

    public void updateEntity()
    {
        boolean flag = this.furnaceBurnTime > 0;
        boolean flag1 = false;

        if (this.furnaceBurnTime > 0&&this.canSmelt())
        {
            --this.furnaceBurnTime;
        }

        
        if (!this.worldObj.isRemote)
        {
            if (this.furnaceBurnTime == 0 && this.canSmelt())
            {
                this.currentItemBurnTime = this.furnaceBurnTime = getItemBurnTime(this.inventory[4]);

                if (this.furnaceBurnTime > 0)
                {
                    flag1 = true;

                    if (this.inventory[4] != null)
                    {
                        --this.inventory[4].stackSize;

                        if (this.inventory[4].stackSize == 0)
                        {
                            this.inventory[4] = inventory[4].getItem().getContainerItem(inventory[4]);
                        }
                    }
                }
            }

            if (this.isBurning() && this.canSmelt())
            {
                ++this.furnaceCookTime;
                if (this.furnaceCookTime == 80)
                {
                    this.furnaceCookTime = 0;
                    this.smeltItem();
                    flag1 = true;
                }
            }
            else
            {
                this.furnaceCookTime = 0;
                
            }

            if (flag != this.furnaceBurnTime > 0)
            {
                flag1 = true;
            }
        }
        if(this.isBurning())
        {
        	worldObj.getBlock(xCoord, yCoord, zCoord).setLightLevel(1F);
        	worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 1, 1);
        }
        else
        {
        	worldObj.getBlock(xCoord, yCoord, zCoord).setLightLevel(0F);
        	worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 0, 1);
        	
        }

        if (flag1)
        {
            this.markDirty();
        }
    }

    /**
     * Returns true if the furnace can smelt an item, i.e. has a source item, destination stack isn't full, etc.
     */
    private boolean canSmelt()
    {
        if (this.inventory[0] == null && this.inventory[1] == null && this.inventory[2] == null && this.inventory[3] == null)
        {
            return false;
        }
        else
        {
            if(this.inventory[0] != null && this.inventory[1] != null && this.inventory[2] != null && this.inventory[3] != null)
            {
            	if(OreDictionary.getOreIDs(inventory[0]) != null && OreDictionary.getOreIDs(inventory[1]) != null && OreDictionary.getOreIDs(inventory[2]) != null && OreDictionary.getOreIDs(inventory[3]) != null)
            	{
            		if(OreDictionary.getOreIDs(inventory[0]).length > 0 && OreDictionary.getOreIDs(inventory[1]).length > 0 && OreDictionary.getOreIDs(inventory[2]).length > 0 && OreDictionary.getOreIDs(inventory[3]).length > 0)
            		{
			            if(OreDictionary.getOreIDs(this.inventory[0])[0] == OreDictionary.getOreIDs(new ItemStack( AdvancedUtilitiesItems.ingotTin, 1))[0]  && OreDictionary.getOreIDs(this.inventory[1])[0] == OreDictionary.getOreIDs(new ItemStack( AdvancedUtilitiesItems.ingotCopper, 1))[0]  && OreDictionary.getOreIDs(this.inventory[2])[0] == OreDictionary.getOreIDs(new ItemStack( AdvancedUtilitiesItems.ingotCopper, 1))[0]  && OreDictionary.getOreIDs(this.inventory[3])[0] == OreDictionary.getOreIDs(new ItemStack( AdvancedUtilitiesItems.ingotCopper, 1))[0] && (this.inventory[5] == null || this.inventory[5].stackSize < 64))
			            {
			            	return true;
			            }
			            if(OreDictionary.getOreIDs(this.inventory[1])[0] == OreDictionary.getOreIDs(new ItemStack( AdvancedUtilitiesItems.ingotTin, 1))[0]  && OreDictionary.getOreIDs(this.inventory[0])[0] == OreDictionary.getOreIDs(new ItemStack( AdvancedUtilitiesItems.ingotCopper, 1))[0]  && OreDictionary.getOreIDs(this.inventory[2])[0] == OreDictionary.getOreIDs(new ItemStack( AdvancedUtilitiesItems.ingotCopper, 1))[0]  && OreDictionary.getOreIDs(this.inventory[3])[0] == OreDictionary.getOreIDs(new ItemStack( AdvancedUtilitiesItems.ingotCopper, 1))[0] && (this.inventory[5] == null || this.inventory[5].stackSize < 64))
			            {
			            	return true;
			            }
			            if(OreDictionary.getOreIDs(this.inventory[2])[0] == OreDictionary.getOreIDs(new ItemStack( AdvancedUtilitiesItems.ingotTin, 1))[0]  && OreDictionary.getOreIDs(this.inventory[1])[0] == OreDictionary.getOreIDs(new ItemStack( AdvancedUtilitiesItems.ingotCopper, 1))[0]  && OreDictionary.getOreIDs(this.inventory[0])[0] == OreDictionary.getOreIDs(new ItemStack( AdvancedUtilitiesItems.ingotCopper, 1))[0]  && OreDictionary.getOreIDs(this.inventory[3])[0] == OreDictionary.getOreIDs(new ItemStack( AdvancedUtilitiesItems.ingotCopper, 1))[0] && (this.inventory[5] == null || this.inventory[5].stackSize < 64))
			            {
			            	return true;
			            }
			            if(OreDictionary.getOreIDs(this.inventory[3])[0] == OreDictionary.getOreIDs(new ItemStack( AdvancedUtilitiesItems.ingotTin, 1))[0]  && OreDictionary.getOreIDs(this.inventory[1])[0] == OreDictionary.getOreIDs(new ItemStack( AdvancedUtilitiesItems.ingotCopper, 1))[0]  && OreDictionary.getOreIDs(this.inventory[2])[0] == OreDictionary.getOreIDs(new ItemStack( AdvancedUtilitiesItems.ingotCopper, 1))[0]  && OreDictionary.getOreIDs(this.inventory[0])[0] == OreDictionary.getOreIDs(new ItemStack( AdvancedUtilitiesItems.ingotCopper, 1))[0] && (this.inventory[5] == null || this.inventory[5].stackSize < 64))
			            {
			            	return true;
			            }
			
			            if(OreDictionary.getOreIDs(this.inventory[0])[0] == OreDictionary.getOreIDs(new ItemStack( AdvancedUtilitiesItems.ingotZinc, 1))[0]  && OreDictionary.getOreIDs(this.inventory[1])[0] == OreDictionary.getOreIDs(new ItemStack( AdvancedUtilitiesItems.ingotCopper, 1))[0]  && OreDictionary.getOreIDs(this.inventory[2])[0] == OreDictionary.getOreIDs(new ItemStack( AdvancedUtilitiesItems.ingotCopper, 1))[0]  && OreDictionary.getOreIDs(this.inventory[3])[0] == OreDictionary.getOreIDs(new ItemStack( AdvancedUtilitiesItems.ingotCopper, 1))[0] && (this.inventory[5] == null || this.inventory[5].stackSize < 64))
			            {
			            	return true;
			            }
			            if(OreDictionary.getOreIDs(this.inventory[1])[0] == OreDictionary.getOreIDs(new ItemStack( AdvancedUtilitiesItems.ingotZinc, 1))[0]  && OreDictionary.getOreIDs(this.inventory[0])[0] == OreDictionary.getOreIDs(new ItemStack( AdvancedUtilitiesItems.ingotCopper, 1))[0]  && OreDictionary.getOreIDs(this.inventory[2])[0] == OreDictionary.getOreIDs(new ItemStack( AdvancedUtilitiesItems.ingotCopper, 1))[0]  && OreDictionary.getOreIDs(this.inventory[3])[0] == OreDictionary.getOreIDs(new ItemStack( AdvancedUtilitiesItems.ingotCopper, 1))[0] && (this.inventory[5] == null || this.inventory[5].stackSize < 64))
			            {
			            	return true;
			            }
			            if(OreDictionary.getOreIDs(this.inventory[2])[0] == OreDictionary.getOreIDs(new ItemStack( AdvancedUtilitiesItems.ingotZinc, 1))[0]  && OreDictionary.getOreIDs(this.inventory[1])[0] == OreDictionary.getOreIDs(new ItemStack( AdvancedUtilitiesItems.ingotCopper, 1))[0]  && OreDictionary.getOreIDs(this.inventory[0])[0] == OreDictionary.getOreIDs(new ItemStack( AdvancedUtilitiesItems.ingotCopper, 1))[0]  && OreDictionary.getOreIDs(this.inventory[3])[0] == OreDictionary.getOreIDs(new ItemStack( AdvancedUtilitiesItems.ingotCopper, 1))[0] && (this.inventory[5] == null || this.inventory[5].stackSize < 64))
			            {
			            	return true;
			            }
			            if(OreDictionary.getOreIDs(this.inventory[3])[0] == OreDictionary.getOreIDs(new ItemStack( AdvancedUtilitiesItems.ingotZinc, 1))[0]  && OreDictionary.getOreIDs(this.inventory[1])[0] == OreDictionary.getOreIDs(new ItemStack( AdvancedUtilitiesItems.ingotCopper, 1))[0]  && OreDictionary.getOreIDs(this.inventory[2])[0] == OreDictionary.getOreIDs(new ItemStack( AdvancedUtilitiesItems.ingotCopper, 1))[0]  && OreDictionary.getOreIDs(this.inventory[0])[0] == OreDictionary.getOreIDs(new ItemStack( AdvancedUtilitiesItems.ingotCopper, 1))[0] && (this.inventory[5] == null || this.inventory[5].stackSize < 64))
			            {
			            	return true;
			            }
            		}
            	}
            }
            ItemStack itemstack;
            if(inventory[0] !=null)
            	itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.inventory[0]);
            else{
            	itemstack = null;}
            if(inventory[0] == null)
            {
            	if(inventory[1] == null)
            	{
            		if(inventory[2] == null)
                	{
            			if(inventory[3] == null)
                    	{
            				return false;
                    	}
            			ItemStack stack = FurnaceRecipes.smelting().getSmeltingResult(inventory[3]);
            			if(stack!=null)
            				return true;
                	}
            		if(inventory[2]!=null)
            		{
            		ItemStack stack = FurnaceRecipes.smelting().getSmeltingResult(inventory[2]);
        			if(stack!=null)
        				return true;
            		}
            	}
            	if(inventory[1]!=null)
            	{
            	ItemStack stack = FurnaceRecipes.smelting().getSmeltingResult(inventory[1]);
    			if(stack!=null)
    				return true;
            	}
            }
            if (itemstack == null) return false;
            if (this.inventory[5] == null && this.inventory[0]!=null) return true;
            if (!this.inventory[5].isItemEqual(itemstack) ) return false;
            int result = inventory[5].stackSize + itemstack.stackSize;
            return result <= getInventoryStackLimit() && result <= this.inventory[5].getMaxStackSize(); //Forge BugFix: Make it respect stack sizes properly.
        }
    }

    /**
     * Turn one item from the furnace source stack into the appropriate smelted item in the furnace result stack
     */
    public void smeltItem()
    {
        if (this.canSmelt())
        {
        	
        	if(this.inventory[0] != null && this.inventory[1] != null && this.inventory[2] != null && this.inventory[3] != null)
            {
        		
        		if(OreDictionary.getOreIDs(inventory[0]) != null && OreDictionary.getOreIDs(inventory[1]) != null && OreDictionary.getOreIDs(inventory[2]) != null && OreDictionary.getOreIDs(inventory[3]) != null && OreDictionary.getOreIDs(inventory[0]).length >= 1 && OreDictionary.getOreIDs(inventory[1]).length >= 1 && OreDictionary.getOreIDs(inventory[2]).length >= 1 && OreDictionary.getOreIDs(inventory[3]).length >= 1)
        		{
        		if(OreDictionary.getOreIDs(inventory[0])[0] != -1 && OreDictionary.getOreIDs(inventory[1])[0] != -1 && OreDictionary.getOreIDs(inventory[2])[0] != -1 && OreDictionary.getOreIDs(inventory[3])[0] != -1 && OreDictionary.getOreIDs(new ItemStack( AdvancedUtilitiesItems.ingotTin, 1)).length > 0 && OreDictionary.getOreIDs(new ItemStack( AdvancedUtilitiesItems.ingotCopper, 1)).length > 0)
        		{
        			if(OreDictionary.getOreIDs(this.inventory[0])[0] == OreDictionary.getOreIDs(new ItemStack( AdvancedUtilitiesItems.ingotTin, 1))[0]  && OreDictionary.getOreIDs(this.inventory[1])[0] == OreDictionary.getOreIDs(new ItemStack( AdvancedUtilitiesItems.ingotCopper, 1))[0]  && OreDictionary.getOreIDs(this.inventory[2])[0] == OreDictionary.getOreIDs(new ItemStack( AdvancedUtilitiesItems.ingotCopper, 1))[0]  && OreDictionary.getOreIDs(this.inventory[3])[0] == OreDictionary.getOreIDs(new ItemStack( AdvancedUtilitiesItems.ingotCopper, 1))[0] )
        			{
        				if(this.inventory[5] == null)
		        		{
		        			this.inventory[5] = new ItemStack(AdvancedUtilitiesItems.ingotBronze, 2);
		        			for(int i = 0; i < 4; i++)
		                    {
		                   	 --this.inventory[i].stackSize;
		                        if (this.inventory[i].stackSize <= 0)
		                        {
		                            this.inventory[i] = null;
		                        }
		                    }
		        			return;
		        		}
		        		if(this.inventory[5].stackSize <= 62 && this.inventory[5].getItem() == AdvancedUtilitiesItems.ingotBronze)
		        		{
		        			this.inventory[5].stackSize+=2;
		        			for(int i = 0; i < 4; i++)
		                    {
		                   	 --this.inventory[i].stackSize;
		                        if (this.inventory[i].stackSize <= 0)
		                        {
		                            this.inventory[i] = null;
		                        }
		                    }
		        			return;
		        		}
		        		return;
        			}
            
		            if(OreDictionary.getOreIDs(this.inventory[1])[0] == OreDictionary.getOreIDs(new ItemStack( AdvancedUtilitiesItems.ingotTin, 1))[0]  && OreDictionary.getOreIDs(this.inventory[0])[0] == OreDictionary.getOreIDs(new ItemStack( AdvancedUtilitiesItems.ingotCopper, 1))[0]  && OreDictionary.getOreIDs(this.inventory[2])[0] == OreDictionary.getOreIDs(new ItemStack( AdvancedUtilitiesItems.ingotCopper, 1))[0]  && OreDictionary.getOreIDs(this.inventory[3])[0] == OreDictionary.getOreIDs(new ItemStack( AdvancedUtilitiesItems.ingotCopper, 1))[0] )
		            {
		            	if(this.inventory[5] == null)
		        		{
		        			this.inventory[5] = new ItemStack(AdvancedUtilitiesItems.ingotBronze, 2);
		        			for(int i = 0; i < 4; i++)
		                    {
		                   	 --this.inventory[i].stackSize;
		                        if (this.inventory[i].stackSize <= 0)
		                        {
		                            this.inventory[i] = null;
		                        }
		                    }
		        			return;
		        		}
		        		if(this.inventory[5].stackSize <= 62 && this.inventory[5].getItem() == AdvancedUtilitiesItems.ingotBronze)
		        		{
		        			this.inventory[5].stackSize+=2;
		        			for(int i = 0; i < 4; i++)
		                    {
		                   	 --this.inventory[i].stackSize;
		                        if (this.inventory[i].stackSize <= 0)
		                        {
		                            this.inventory[i] = null;
		                        }
		                    }
		        		}
		            	return;
		            }
		            if(OreDictionary.getOreIDs(this.inventory[2])[0] == OreDictionary.getOreIDs(new ItemStack( AdvancedUtilitiesItems.ingotTin, 1))[0]  && OreDictionary.getOreIDs(this.inventory[1])[0] == OreDictionary.getOreIDs(new ItemStack( AdvancedUtilitiesItems.ingotCopper, 1))[0]  && OreDictionary.getOreIDs(this.inventory[0])[0] == OreDictionary.getOreIDs(new ItemStack( AdvancedUtilitiesItems.ingotCopper, 1))[0]  && OreDictionary.getOreIDs(this.inventory[3])[0] == OreDictionary.getOreIDs(new ItemStack( AdvancedUtilitiesItems.ingotCopper, 1))[0] )
		            {
		            	if(this.inventory[5] == null)
		        		{
		        			this.inventory[5] = new ItemStack(AdvancedUtilitiesItems.ingotBronze, 2);
		        			for(int i = 0; i < 4; i++)
		                    {
		                   	 --this.inventory[i].stackSize;
		                        if (this.inventory[i].stackSize <= 0)
		                        {
		                            this.inventory[i] = null;
		                        }
		                    }
		        			return;
		        		}
		        		if(this.inventory[5].stackSize <= 62 && this.inventory[5].getItem() == AdvancedUtilitiesItems.ingotBronze)
		        		{
		        			this.inventory[5].stackSize+=2;
		        			for(int i = 0; i < 4; i++)
		                    {
		                   	 --this.inventory[i].stackSize;
		                        if (this.inventory[i].stackSize <= 0)
		                        {
		                            this.inventory[i] = null;
		                        }
		                    }
		        		}
		            	return;
		            }
		            if(OreDictionary.getOreIDs(this.inventory[3])[0] == OreDictionary.getOreIDs(new ItemStack( AdvancedUtilitiesItems.ingotTin, 1))[0] && OreDictionary.getOreIDs(this.inventory[1])[0] == OreDictionary.getOreIDs(new ItemStack( AdvancedUtilitiesItems.ingotCopper, 1))[0]  && OreDictionary.getOreIDs(this.inventory[2])[0] == OreDictionary.getOreIDs(new ItemStack( AdvancedUtilitiesItems.ingotCopper, 1))[0]  && OreDictionary.getOreIDs(this.inventory[0])[0] == OreDictionary.getOreIDs(new ItemStack( AdvancedUtilitiesItems.ingotCopper, 1))[0] )
		            {
		            	if(this.inventory[5] == null)
		        		{
		        			this.inventory[5] = new ItemStack(AdvancedUtilitiesItems.ingotBronze, 2);
		        			for(int i = 0; i < 4; i++)
		                    {
		                   	 --this.inventory[i].stackSize;
		                        if (this.inventory[i].stackSize <= 0)
		                        {
		                            this.inventory[i] = null;
		                        }
		                    }
		        			return;
		        		}
		        		if(this.inventory[5].stackSize <= 62 && this.inventory[5].getItem() == AdvancedUtilitiesItems.ingotBronze)
		        		{
		        			this.inventory[5].stackSize+=2;
		        			for(int i = 0; i < 4; i++)
		                    {
		                   	 --this.inventory[i].stackSize;
		                        if (this.inventory[i].stackSize <= 0)
		                        {
		                            this.inventory[i] = null;
		                        }
		                    }
		        		}
		            	return;
		            }
        	}
        	}
            }
            
            
            //brass
            /*
             * 
             * 
             * 
             * 
             */
            if(this.inventory[0] != null && this.inventory[1] != null && this.inventory[2] != null && this.inventory[3] != null && this.inventory[0].getItem() == AdvancedUtilitiesItems.ingotZinc && this.inventory[1].getItem() == AdvancedUtilitiesItems.ingotCopper && this.inventory[2].getItem() == AdvancedUtilitiesItems.ingotCopper && this.inventory[3].getItem() == AdvancedUtilitiesItems.ingotCopper)
            {
            	if(OreDictionary.getOreIDs(inventory[0]) != null && OreDictionary.getOreIDs(inventory[1]) != null && OreDictionary.getOreIDs(inventory[2]) != null && OreDictionary.getOreIDs(inventory[3]) != null && OreDictionary.getOreIDs(inventory[0]).length > 0 && OreDictionary.getOreIDs(inventory[1]).length > 0 && OreDictionary.getOreIDs(inventory[2]).length > 0 && OreDictionary.getOreIDs(inventory[3]).length > 0)
            	{
        		if(OreDictionary.getOreIDs(inventory[0])[0] != -1 && OreDictionary.getOreIDs(inventory[1])[0] != -1 && OreDictionary.getOreIDs(inventory[2])[0] != -1 && OreDictionary.getOreIDs(inventory[3])[0] != -1)
        		{
            		if(OreDictionary.getOreIDs(this.inventory[0])[0] == OreDictionary.getOreIDs(new ItemStack( AdvancedUtilitiesItems.ingotZinc, 1))[0]  && OreDictionary.getOreIDs(this.inventory[1])[0] == OreDictionary.getOreIDs(new ItemStack( AdvancedUtilitiesItems.ingotCopper, 1))[0]  && OreDictionary.getOreIDs(this.inventory[2])[0] == OreDictionary.getOreIDs(new ItemStack( AdvancedUtilitiesItems.ingotCopper, 1))[0]  && OreDictionary.getOreIDs(this.inventory[3])[0] == OreDictionary.getOreIDs(new ItemStack( AdvancedUtilitiesItems.ingotCopper, 1))[0] )
            		{
		        		if(this.inventory[5] == null)
		        		{
		        			this.inventory[5] = new ItemStack(AdvancedUtilitiesItems.ingotBrass, 2);
		        			for(int i = 0; i < 4; i++)
		                    {
		                   	 --this.inventory[i].stackSize;
		                        if (this.inventory[i].stackSize <= 0)
		                        {
		                            this.inventory[i] = null;
		                        }
		                    }
		        			return;
		        		}
		        		if(this.inventory[5].stackSize <= 62 && this.inventory[5].getItem() == AdvancedUtilitiesItems.ingotBrass)
		        		{
		        			this.inventory[5].stackSize+=2;
		        			for(int i = 0; i < 4; i++)
		                    {
		                   	 --this.inventory[i].stackSize;
		                        if (this.inventory[i].stackSize <= 0)
		                        {
		                            this.inventory[i] = null;
		                        }
		                    }
		        		}
		        		return;
            		}
		            
		            if(OreDictionary.getOreIDs(this.inventory[1])[0] == OreDictionary.getOreIDs(new ItemStack( AdvancedUtilitiesItems.ingotZinc, 1))[0]  && OreDictionary.getOreIDs(this.inventory[0])[0] == OreDictionary.getOreIDs(new ItemStack( AdvancedUtilitiesItems.ingotCopper, 1))[0]  && OreDictionary.getOreIDs(this.inventory[2])[0] == OreDictionary.getOreIDs(new ItemStack( AdvancedUtilitiesItems.ingotCopper, 1))[0]  && OreDictionary.getOreIDs(this.inventory[3])[0] == OreDictionary.getOreIDs(new ItemStack( AdvancedUtilitiesItems.ingotCopper, 1))[0] )
		            {
		            	if(this.inventory[5] == null)
		        		{
		        			this.inventory[5] = new ItemStack(AdvancedUtilitiesItems.ingotBrass, 2);
		        			for(int i = 0; i < 4; i++)
		                    {
		                   	 --this.inventory[i].stackSize;
		                        if (this.inventory[i].stackSize <= 0)
		                        {
		                            this.inventory[i] = null;
		                        }
		                    }
		        			return;
		        		}
		        		if(this.inventory[5].stackSize <= 62 && this.inventory[5].getItem() == AdvancedUtilitiesItems.ingotBrass)
		        		{
		        			this.inventory[5].stackSize+=2;
		        			for(int i = 0; i < 4; i++)
		                    {
		                   	 --this.inventory[i].stackSize;
		                        if (this.inventory[i].stackSize <= 0)
		                        {
		                            this.inventory[i] = null;
		                        }
		                    }
		        		}
		            	return;
		            }
		            if(OreDictionary.getOreIDs(this.inventory[2])[0] == OreDictionary.getOreIDs(new ItemStack( AdvancedUtilitiesItems.ingotZinc, 1))[0]  && OreDictionary.getOreIDs(this.inventory[1])[0] == OreDictionary.getOreIDs(new ItemStack( AdvancedUtilitiesItems.ingotCopper, 1))[0]  && OreDictionary.getOreIDs(this.inventory[0])[0] == OreDictionary.getOreIDs(new ItemStack( AdvancedUtilitiesItems.ingotCopper, 1))[0]  && OreDictionary.getOreIDs(this.inventory[3])[0] == OreDictionary.getOreIDs(new ItemStack( AdvancedUtilitiesItems.ingotCopper, 1))[0] )
		            {
		            	if(this.inventory[5] == null)
		        		{
		        			this.inventory[5] = new ItemStack(AdvancedUtilitiesItems.ingotBrass, 2);
		        			for(int i = 0; i < 4; i++)
		                    {
		                   	 --this.inventory[i].stackSize;
		                        if (this.inventory[i].stackSize <= 0)
		                        {
		                            this.inventory[i] = null;
		                        }
		                    }
		        			return;
		        		}
		        		if(this.inventory[5].stackSize <= 62 && this.inventory[5].getItem() == AdvancedUtilitiesItems.ingotBrass)
		        		{
		        			this.inventory[5].stackSize+=2;
		        			for(int i = 0; i < 4; i++)
		                    {
		                   	 --this.inventory[i].stackSize;
		                        if (this.inventory[i].stackSize <= 0)
		                        {
		                            this.inventory[i] = null;
		                        }
		                    }
		        		}
		            	return;
		            }
		            if(OreDictionary.getOreIDs(this.inventory[3])[0] == OreDictionary.getOreIDs(new ItemStack( AdvancedUtilitiesItems.ingotZinc, 1))[0] && OreDictionary.getOreIDs(this.inventory[1])[0] == OreDictionary.getOreIDs(new ItemStack( AdvancedUtilitiesItems.ingotCopper, 1))[0]  && OreDictionary.getOreIDs(this.inventory[2])[0] == OreDictionary.getOreIDs(new ItemStack( AdvancedUtilitiesItems.ingotCopper, 1))[0]  && OreDictionary.getOreIDs(this.inventory[0])[0] == OreDictionary.getOreIDs(new ItemStack( AdvancedUtilitiesItems.ingotCopper, 1))[0] )
		            {
		            	if(this.inventory[5] == null)
		        		{
		        			this.inventory[5] = new ItemStack(AdvancedUtilitiesItems.ingotBrass, 2);
		        			for(int i = 0; i < 4; i++)
		                    {
		                   	 --this.inventory[i].stackSize;
		                        if (this.inventory[i].stackSize <= 0)
		                        {
		                            this.inventory[i] = null;
		                        }
		                    }
		        			return;
		        		}
		        		if(this.inventory[5].stackSize <= 62 && this.inventory[5].getItem() == AdvancedUtilitiesItems.ingotBrass)
		        		{
		        			this.inventory[5].stackSize+=2;
		        			for(int i = 0; i < 4; i++)
		                    {
		                   	 --this.inventory[i].stackSize;
		                        if (this.inventory[i].stackSize <= 0)
		                        {
		                            this.inventory[i] = null;
		                        }
		                    }
		        		}
		            	return;
		            }
            	}
            	}
            }
            
            
            if(inventory[0] == null)
            {
            	if(inventory[1] == null)
            	{
            		if(inventory[2] == null)
                	{
            			if(inventory[3] == null)
                    	{
            				return;
                    	}
            			ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.inventory[3]);

                        if (this.inventory[5] == null)
                        {
                            this.inventory[5] = itemstack.copy();
                        }
                        else if (this.inventory[5].getItem() == itemstack.getItem())
                        {
                            this.inventory[5].stackSize += itemstack.stackSize; // Forge BugFix: Results may have multiple items
                        }

                        --this.inventory[3].stackSize;

                        if (this.inventory[3].stackSize <= 0)
                        {
                            this.inventory[3] = null;
                        }
            			return;
                	}
            		ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.inventory[2]);

                    if (this.inventory[5] == null)
                    {
                        this.inventory[5] = itemstack.copy();
                    }
                    else if (this.inventory[5].getItem() == itemstack.getItem())
                    {
                        this.inventory[5].stackSize += itemstack.stackSize; // Forge BugFix: Results may have multiple items
                    }

                    --this.inventory[2].stackSize;

                    if (this.inventory[2].stackSize <= 0)
                    {
                        this.inventory[2] = null;
                    }
            		return;
            	}
            	ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.inventory[1]);

                if (this.inventory[5] == null)
                {
                    this.inventory[5] = itemstack.copy();
                }
                else if (this.inventory[5].getItem() == itemstack.getItem())
                {
                    this.inventory[5].stackSize += itemstack.stackSize; // Forge BugFix: Results may have multiple items
                }

                --this.inventory[1].stackSize;

                if (this.inventory[1].stackSize <= 0)
                {
                    this.inventory[1] = null;
                }
                return;
            	
            }
            
            if(inventory[1]!=null&&inventory[2]!=null&&inventory[3]!=null&&inventory[0].getItem() == inventory[1].getItem()&& inventory[1].getItem() == inventory[2].getItem() && inventory[2].getItem() == inventory[3].getItem() && (this.inventory[5]== null || this.inventory[5].stackSize <=60))
            {
            	
            	ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.inventory[0]);
            	itemstack.stackSize = 4;
                if (this.inventory[5] == null)
                {
                    this.inventory[5] = itemstack.copy();
                }
                else if (this.inventory[5].getItem() == itemstack.getItem() && this.inventory[5].stackSize <= 60)
                {
                    this.inventory[5].stackSize += itemstack.stackSize; // Forge BugFix: Results may have multiple items
                }

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
                --this.inventory[2].stackSize;

                if (this.inventory[2].stackSize <= 0)
                {
                    this.inventory[2] = null;
                }
                --this.inventory[3].stackSize;

                if (this.inventory[3].stackSize <= 0)
                {
                    this.inventory[3] = null;
                }
                return;
            }
            
            if(inventory[1]!=null&&inventory[2]!=null&& inventory[3] == null&&inventory[0].getItem() == inventory[1].getItem()&& inventory[1].getItem() == inventory[2].getItem() && (this.inventory[5]== null || this.inventory[5].stackSize <=61))
            {
            	ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.inventory[0]);
            	itemstack.stackSize = 3;
                if (this.inventory[5] == null)
                {
                    this.inventory[5] = itemstack.copy();
                }
                else if (this.inventory[5].getItem() == itemstack.getItem() && this.inventory[5].stackSize <= 61)
                {
                    this.inventory[5].stackSize += itemstack.stackSize; // Forge BugFix: Results may have multiple items
                }

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
                --this.inventory[2].stackSize;

                if (this.inventory[2].stackSize <= 0)
                {
                    this.inventory[2] = null;
                }
                return;
            }
            if(inventory[1]!=null&&inventory[0].getItem() == inventory[1].getItem()&& (this.inventory[5]== null || this.inventory[5].stackSize <=62))
            {
            	ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.inventory[0]);
            	itemstack.stackSize = 2;
                if (this.inventory[5] == null)
                {
                    this.inventory[5] = itemstack.copy();
                }
                else if (this.inventory[5].getItem() == itemstack.getItem() && this.inventory[5].stackSize <= 62)
                {
                    this.inventory[5].stackSize += itemstack.stackSize; // Forge BugFix: Results may have multiple items
                }

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
                return;
            }
            
            if(this.inventory[0]!=null && this.inventory[1] == null && this.inventory[2] == null && this.inventory[3] == null)
        	{
        		int copper = OreDictionary.getOreID("oreCopper");
        		int tin = OreDictionary.getOreID("oreTin");
        		if(OreDictionary.getOreIDs(inventory[0])!=null&& OreDictionary.getOreIDs(inventory[0]).length>0 && OreDictionary.getOreIDs(inventory[0])[0] == copper)
        		{
        			if(this.inventory[5] == null)
            		{
            			this.inventory[5] = new ItemStack(AdvancedUtilitiesItems.ingotCopper, 1);
                       	 --this.inventory[0].stackSize;
                            if (this.inventory[0].stackSize <= 0)
                            {
                                this.inventory[0] = null;
                            }
            			return;
            		}
            		if(this.inventory[5].stackSize <= 63 && this.inventory[5].getItem() == AdvancedUtilitiesItems.ingotCopper)
            		{
            			this.inventory[5].stackSize+=1;
                       	 --this.inventory[0].stackSize;
                            if (this.inventory[0].stackSize <= 0)
                            {
                                this.inventory[0] = null;
                            }
                            return;
            		}
        		}
        		if(OreDictionary.getOreIDs(inventory[0])!=null&& OreDictionary.getOreIDs(inventory[0]).length>0 &&OreDictionary.getOreIDs(inventory[0])[0] == tin)
        		{
        			if(this.inventory[5] == null)
            		{
            			this.inventory[5] = new ItemStack(AdvancedUtilitiesItems.ingotTin, 1);
                       	 --this.inventory[0].stackSize;
                            if (this.inventory[0].stackSize <= 0)
                            {
                                this.inventory[0] = null;
                            }
            			return;
            		}
            		if(this.inventory[5].stackSize <= 63 && this.inventory[5].getItem() == AdvancedUtilitiesItems.ingotTin)
            		{
            			this.inventory[5].stackSize+=1;
                       	 --this.inventory[0].stackSize;
                            if (this.inventory[0].stackSize <= 0)
                            {
                                this.inventory[0] = null;
                            }
                            return;
            		}
        		}
        	
            
            ItemStack itemstack2 = FurnaceRecipes.smelting().getSmeltingResult(this.inventory[0]);
            itemstack2.stackSize = 1;
            if (this.inventory[5] == null && itemstack2!=null)
            {
                this.inventory[5] = itemstack2.copy();
            }
            else if (itemstack2!=null && this.inventory[5].getItem() == itemstack2.getItem())
            {
                this.inventory[5].stackSize += itemstack2.stackSize; // Forge BugFix: Results may have multiple items
            }

            --this.inventory[0].stackSize;

            if (this.inventory[0].stackSize <= 0)
            {
                this.inventory[0] = null;
            }
        	//System.out.println(itemstack2);
            return;
        	}
        }
        	
    }

    /**
     * Returns the number of ticks that the supplied fuel item will keep the furnace burning, or 0 if the item isn't
     * fuel
     */
    public static int getItemBurnTime(ItemStack stack)
    {
        if (stack == null)
        {
            return 0;
        }
        else
        {
            Item item = stack.getItem();

            if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.air)
            {
                Block block = Block.getBlockFromItem(item);

                if (block == Blocks.wooden_slab)
                {
                    return 150;
                }

                if (block.getMaterial() == Material.wood)
                {
                    return 300;
                }

                if (block == Blocks.coal_block)
                {
                    return 16000;
                }
            }

            if (item instanceof ItemTool && ((ItemTool)item).getToolMaterialName().equals("WOOD")) return 200;
            if (item instanceof ItemSword && ((ItemSword)item).getToolMaterialName().equals("WOOD")) return 200;
            if (item instanceof ItemHoe && ((ItemHoe)item).getToolMaterialName().equals("WOOD")) return 200;
            if (item == Items.stick) return 100;
            if (item == Items.coal) return 1600;
            if (item == Items.lava_bucket) return 20000;
            if (item == Item.getItemFromBlock(Blocks.sapling)) return 100;
            if (item == Items.blaze_rod) return 2400;
            return GameRegistry.getFuelValue(stack);
        }
    }

    public static boolean isItemFuel(ItemStack p_145954_0_)
    {
        /**
         * Returns the number of ticks that the supplied fuel item will keep the furnace burning, or 0 if the item isn't
         * fuel
         */
        return getItemBurnTime(p_145954_0_) > 0;
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
        return par1 == 5 ? false : (par1 == 4 ? isItemFuel(par2ItemStack) : true);
    }

    /**
     * Returns an array containing the indices of the slots that can be accessed by automation on the given side of this
     * block.
     */
    public int[] getAccessibleSlotsFromSide(int par1)
    {
        return par1 == 0 ? slotsBottom : (par1 == 1 ? slotsTop : slotsSides);
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
        return par3 != 0 || par1 != 1 || par2ItemStack.getItem() == Items.bucket;
    }
}