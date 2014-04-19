package com.sudwood.advancedutilities.tileentity;

import com.sudwood.advancedutilities.items.AdvancedUtilitiesItems;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
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

public class TileEntitySmeltry extends TileEntity implements ISidedInventory
{
    private static final int[] slotsTop = new int[] {0};
    private static final int[] slotsBottom = new int[] {2, 1};
    private static final int[] slotsSides = new int[] {1};
    /**
     * The ItemStacks that hold the items currently being used in the furnace
     */
    private ItemStack[] inventory = new ItemStack[4];
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
        this.currentItemBurnTime = getItemBurnTime(this.inventory[1]);

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
        return this.furnaceCookTime * p_145953_1_ / 1200;
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
            this.currentItemBurnTime = 1200;
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
       /**/
        if (this.furnaceBurnTime > 0 && this.canSmelt())
        {
            --this.furnaceBurnTime;
        }

        if (!this.worldObj.isRemote)
        {
            if (this.furnaceBurnTime == 0 && this.canSmelt())
            {
                this.currentItemBurnTime = this.furnaceBurnTime = getItemBurnTime(this.inventory[2]);

                if (this.furnaceBurnTime > 0)
                {
                    flag1 = true;

                    if (this.inventory[2] != null)
                    {
                        --this.inventory[2].stackSize;

                        if (this.inventory[2].stackSize == 0)
                        {
                            this.inventory[2] = inventory[2].getItem().getContainerItem(inventory[2]);
                        }
                    }
                }
            }

            if (this.isBurning() && this.canSmelt())
            {
                ++this.furnaceCookTime;

                if (this.furnaceCookTime == 1200)
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
        if (this.inventory[0] == null || this.inventory[1] == null)
        {
            return false;
        }
        else
        {
            ItemStack itemstack = this.getSmeltryResult(inventory[0], inventory[1]);
            if (itemstack == null) return false;
            if (this.inventory[3] == null) return true;
            if (!this.inventory[3].isItemEqual(itemstack)) return false;
            int result = inventory[3].stackSize + itemstack.stackSize;
            return result <= getInventoryStackLimit() && result <= this.inventory[3].getMaxStackSize(); //Forge BugFix: Make it respect stack sizes properly.
        }
    }

    public ItemStack getSmeltryResult(ItemStack melt, ItemStack mould)
    {
    	if(melt == null || mould == null)
    		return null;
    	
    	if(melt.getItem() ==Item.getItemFromBlock(Blocks.clay))
    	{
    		if(mould.getItem() == Items.stick)
    		{
    			return new ItemStack(AdvancedUtilitiesItems.cast, 1, 0);
    		}
    		if(mould.getItem() == Items.stone_pickaxe)
    		{
    			return new ItemStack(AdvancedUtilitiesItems.cast, 1, 1);
    		}
    		if(mould.getItem() == Items.stone_sword)
    		{
    			return new ItemStack(AdvancedUtilitiesItems.cast, 1, 2);
    		}
    		if(mould.getItem() == Items.stone_shovel)
    		{
    			return new ItemStack(AdvancedUtilitiesItems.cast, 1, 3);
    		}
    		if(mould.getItem() == Items.stone_axe)
    		{
    			return new ItemStack(AdvancedUtilitiesItems.cast, 1, 4);
    		}
    		if(mould.isItemEqual(new ItemStack(AdvancedUtilitiesItems.plate, 1, 3)))
    		{
    			return new ItemStack(AdvancedUtilitiesItems.cast, 1, 5);
    		}
    		if(mould.isItemEqual(new ItemStack(AdvancedUtilitiesItems.stoneRivets, 1)))
    		{
    			return new ItemStack(AdvancedUtilitiesItems.cast, 1, 6);
    		}
    		if(mould.isItemEqual(new ItemStack(AdvancedUtilitiesItems.itemCasing, 1, 0)))
    		{
    			return new ItemStack(AdvancedUtilitiesItems.cast, 1, 7);
    		}
    		if(mould.isItemEqual(new ItemStack(AdvancedUtilitiesItems.itemBulletHead, 1, 0)))
    		{
    			return new ItemStack(AdvancedUtilitiesItems.cast, 1, 8);
    		}
    	}
    	if(melt.getItem() == Items.iron_ingot)
    	{
    		if(mould.isItemEqual(new ItemStack(AdvancedUtilitiesItems.cast, 1, 0)))
    		{
    			return new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 1);
    		}
    		if(mould.isItemEqual(new ItemStack(AdvancedUtilitiesItems.cast, 1, 1)))
    		{
    			return new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 2);
    		}
    		if(mould.isItemEqual(new ItemStack(AdvancedUtilitiesItems.cast, 1, 2)))
    		{
    			return new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 3);
    		}
    		if(mould.isItemEqual(new ItemStack(AdvancedUtilitiesItems.cast, 1, 3)))
    		{
    			return new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 4);
    		}
    		if(mould.isItemEqual(new ItemStack(AdvancedUtilitiesItems.cast, 1, 4)))
    		{
    			return new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 5);
    		}
    		if(mould.isItemEqual(new ItemStack(AdvancedUtilitiesItems.cast, 1, 5)))
    		{
    			return new ItemStack(AdvancedUtilitiesItems.plate, 1, 0);
    		}
    	}
    	if(melt.getItem() == AdvancedUtilitiesItems.ingotBronze)
    	{
    		if(mould.isItemEqual(new ItemStack(AdvancedUtilitiesItems.cast, 1, 0)))
    		{
    			return new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 0);
    		}
    		if(mould.isItemEqual(new ItemStack(AdvancedUtilitiesItems.cast, 1, 1)))
    		{
    			return new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 6);
    		}
    		if(mould.isItemEqual(new ItemStack(AdvancedUtilitiesItems.cast, 1, 2)))
    		{
    			return new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 7);
    		}
    		if(mould.isItemEqual(new ItemStack(AdvancedUtilitiesItems.cast, 1, 3)))
    		{
    			return new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 8);
    		}
    		if(mould.isItemEqual(new ItemStack(AdvancedUtilitiesItems.cast, 1, 4)))
    		{
    			return new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 9);
    		}
    		if(mould.isItemEqual(new ItemStack(AdvancedUtilitiesItems.cast, 1, 5)))
    		{
    			return new ItemStack(AdvancedUtilitiesItems.plate, 1, 1);
    		}
    		if(mould.isItemEqual(new ItemStack(AdvancedUtilitiesItems.cast, 1, 7)))
    		{
    			return new ItemStack(AdvancedUtilitiesItems.itemCasing, 16, 1);
    		}
    	}
    	if(melt.getItem() == AdvancedUtilitiesItems.ingotBrass)
    	{
    		if(mould.isItemEqual(new ItemStack(AdvancedUtilitiesItems.cast, 1, 5)))
    		{
    			return new ItemStack(AdvancedUtilitiesItems.plate, 1, 2);
    		}
    		if(mould.isItemEqual(new ItemStack(AdvancedUtilitiesItems.cast, 1, 6)))
    		{
    			return new ItemStack(AdvancedUtilitiesItems.brassRivets, 1);
    		}
    	}
    	if(melt.getItem() == AdvancedUtilitiesItems.ingotLead)
    	{
    		if(mould.isItemEqual(new ItemStack(AdvancedUtilitiesItems.cast, 1, 8)))
    		{
    			return new ItemStack(AdvancedUtilitiesItems.itemBulletHead, 16, 1);
    		}
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
            ItemStack itemstack = this.getSmeltryResult(inventory[0], inventory[1]);

            if (this.inventory[3] == null)
            {
                this.inventory[3] = itemstack.copy();
            }
            else if (this.inventory[3].getItem() == itemstack.getItem())
            {
                this.inventory[3].stackSize += itemstack.stackSize; // Forge BugFix: Results may have multiple items
            }

            --this.inventory[0].stackSize;

            if (this.inventory[0].stackSize <= 0)
            {
                this.inventory[0] = null;
            }
            if(this.inventory[1].getItem() != AdvancedUtilitiesItems.cast)
            {
            	--this.inventory[1].stackSize;

                if (this.inventory[1].stackSize <= 0)
                {
                    this.inventory[1] = null;
                }
            }
        }
    }

    /**
     * Returns the number of ticks that the supplied fuel item will keep the furnace burning, or 0 if the item isn't
     * fuel
     */
    public static int getItemBurnTime(ItemStack p_145952_0_)
    {
        if (p_145952_0_ == null)
        {
            return 0;
        }
        else
        {
            Item item = p_145952_0_.getItem();

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
            return GameRegistry.getFuelValue(p_145952_0_);
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
        return par1 == 2 ? false : (par1 == 1 ? isItemFuel(par2ItemStack) : true);
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