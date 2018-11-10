package com.sudwood.advancedutilities.tileentity;

import com.sudwood.advancedutilities.blocks.AdvancedUtilitiesBlocks;
import com.sudwood.advancedutilities.config.ServerOptions;
import com.sudwood.advancedutilities.fluids.AdvancedUtilitiesFluids;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
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
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class TileEntityBoiler extends TileEntity implements ISidedInventory, IFluidHandler, ISteamTank
{

	protected static final int[] slotsTop = new int[] {};
	protected static final int[] slotsBottom = new int[] {};
	protected static final int[] slotsSides = new int[] {0};
    /**
     * The ItemStacks that hold the items currently being used in the furnace
     */
    protected ItemStack[] inventory = new ItemStack[1];
    protected int fuelUseage = 1;
    public FluidTank tank = new FluidTank(64*FluidContainerRegistry.BUCKET_VOLUME);
    public FluidTank waterTank = new FluidTank(64*FluidContainerRegistry.BUCKET_VOLUME);
    
    /**
     * The number of ticks that the furnace will keep burning
     */
    public int furnaceBurnTime;
    /**
     * The number of ticks that a fresh copy of the currently-burning item would keep the furnace burning for
     */
    public int currentItemBurnTime;
    
    protected int steamMulti = 1;
    /**
     * The number of ticks that the current item has been cooking for
     */
    public int furnaceCookTime;
    protected String field_145958_o;
    protected static final String __OBFID = "CL_00000357";

    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory()
    {
        return this.inventory.length;
    }
    
    public int getTankAmount()
    {
    	return tank.getFluidAmount();
    }
    public int getWaterTankAmount()
    {
    	return waterTank.getFluidAmount();
    }

    /**
     * Returns the stack in slot i
     */
    public ItemStack getStackInSlot(int par1)
    {
        return this.inventory[par1];
    }
    
    public String getFluidName()
    {
    	if(this.tank.getFluidAmount() > 0)
    		return this.tank.getFluid().getFluid().getLocalizedName();
    	else
    		return "Empty";
    }
    
    public String getWaterName()
    {
    	if(this.waterTank.getFluidAmount() > 0)
    		return this.waterTank.getFluid().getFluid().getLocalizedName();
    	else
    		return "Empty";
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
    
    public void pushSteam(int amount)
    {
    	if(worldObj.getBlock(xCoord, yCoord+1, zCoord)!=null)
    	{
	    	try
	    	{
	    		TileEntity tile = worldObj.getTileEntity(xCoord, yCoord+1, zCoord);
	    		if(tile instanceof ISteamTank && this.tank.getFluidAmount() >= amount)
	    		{
	    			if(((IFluidHandler)tile).canFill(ForgeDirection.DOWN, AdvancedUtilitiesFluids.fluidSteam))
	    			{
		    			((IFluidHandler)tile).fill(ForgeDirection.DOWN, new FluidStack(AdvancedUtilitiesFluids.fluidSteam, amount), true);
		    			this.drain(ForgeDirection.UNKNOWN, new FluidStack(AdvancedUtilitiesFluids.fluidSteam, amount), true);
		    			worldObj.markBlockForUpdate(xCoord, yCoord+1, zCoord);
	    			}
	    		}
	    	}
	    	catch(Exception e)
	    	{
	    		
	    	}
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

    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);
        NBTTagList nbttaglist = tag.getTagList("Items", 10);
        this.inventory = new ItemStack[this.getSizeInventory()];
        this.waterTank.readFromNBT((NBTTagCompound) tag.getTag("waterTank"));

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.inventory.length)
            {
                this.inventory[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }

        this.furnaceBurnTime = tag.getShort("BurnTime");
        this.currentItemBurnTime = getItemBurnTime(this.inventory[0]);
        this.steamMulti = tag.getInteger("steamMulti");
        this.tank.readFromNBT(tag);
        if (tag.hasKey("CustomName", 8))
        {
            this.field_145958_o = tag.getString("CustomName");
        }
    }

    public void writeToNBT(NBTTagCompound p_145841_1_)
    {
        super.writeToNBT(p_145841_1_);
        p_145841_1_.setShort("BurnTime", (short)this.furnaceBurnTime);
        p_145841_1_.setInteger("steamMulti", this.steamMulti);
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
        this.tank.writeToNBT(p_145841_1_);
        NBTTagCompound tagish = new NBTTagCompound();
        this.waterTank.writeToNBT(tagish);
        p_145841_1_.setTag("waterTank", tagish);
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
     * Returns an integer between 0 and the passed value representing how much burn time is left on the current fuel
     * item, where 0 means that the item is exhausted and the passed value means that the item is fresh
     */
    @SideOnly(Side.CLIENT)
    public int getBurnTimeRemainingScaled(int p_145955_1_)
    {
        if (this.currentItemBurnTime == 0)
        {
            this.currentItemBurnTime = 200;
        }

        return this.furnaceBurnTime * p_145955_1_ / this.currentItemBurnTime;
    }
    
    public int getFuelUseage()
    {
    	return this.fuelUseage;
    }
    
    @SideOnly(Side.CLIENT)
    public int getFluidScaled(int p_145955_1_)
    {
        return this.tank.getFluidAmount() * p_145955_1_ / this.tank.getCapacity();
    }
    
    @SideOnly(Side.CLIENT)
    public int getWaterScaled(int p_145955_1_)
    {
        return this.waterTank.getFluidAmount() * p_145955_1_ / this.waterTank.getCapacity();
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

        if (this.furnaceBurnTime > 0 && this.canSmelt())
        {
            this.furnaceBurnTime-=steamMulti*this.fuelUseage;
        }

        if (!this.worldObj.isRemote)
        {
            if (this.furnaceBurnTime <= 0 && this.canSmelt())
            {
                this.currentItemBurnTime = this.furnaceBurnTime = getItemBurnTime(this.inventory[0]);

                if (this.furnaceBurnTime > 0)
                {
                    flag1 = true;

                    if (this.inventory[0] != null)
                    {
                        --this.inventory[0].stackSize;

                        if (this.inventory[0].stackSize == 0)
                        {
                            this.inventory[0] = inventory[0].getItem().getContainerItem(inventory[0]);
                        }
                    }
                }
            }

            if (this.isBurning() && this.canSmelt())
            {
                this.makeSteam();
            }
            else
            {
               
            }

            if (flag != this.furnaceBurnTime > 0)
            {
                flag1 = true;
                
            }
            getWater();
            pushSteam(20*steamMulti*ServerOptions.steamCreationRate);
        }

        if (flag1)
        {
            this.markDirty();
        }
    }
    
    public int getScaledBurnTime()
    {
    	return (this.furnaceBurnTime*steamMulti) / (20);
    }

    public void makeSteam()
    {
    	this.fill(ForgeDirection.UNKNOWN, new FluidStack(AdvancedUtilitiesFluids.fluidSteam, ServerOptions.steamCreationRate*steamMulti), true);
    	this.waterTank.drain(ServerOptions.steamCreationRate*steamMulti, true);
    	worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }
    
    public void checkBellows()
    {
    	steamMulti = 1;
    	if(worldObj.getBlock(xCoord+ForgeDirection.NORTH.offsetX, yCoord, zCoord+ForgeDirection.NORTH.offsetZ) == AdvancedUtilitiesBlocks.bellows && worldObj.getBlockMetadata(xCoord+ForgeDirection.NORTH.offsetX, yCoord, zCoord+ForgeDirection.NORTH.offsetZ) == 3)// looking north for a bellows pointing south
    	{
    		steamMulti+=1;
    	}
    	if(worldObj.getBlock(xCoord+ForgeDirection.SOUTH.offsetX, yCoord, zCoord+ForgeDirection.SOUTH.offsetZ) == AdvancedUtilitiesBlocks.bellows && worldObj.getBlockMetadata(xCoord+ForgeDirection.SOUTH.offsetX, yCoord, zCoord+ForgeDirection.SOUTH.offsetZ) == 2)// looking south for a bellows pointing north
    	{
    		steamMulti+=1;
    	}
    	if(worldObj.getBlock(xCoord+ForgeDirection.WEST.offsetX, yCoord, zCoord+ForgeDirection.WEST.offsetZ) == AdvancedUtilitiesBlocks.bellows && worldObj.getBlockMetadata(xCoord+ForgeDirection.WEST.offsetX, yCoord, zCoord+ForgeDirection.WEST.offsetZ) == 4)// looking west for a bellows pointing east
    	{
    		steamMulti+=1;
    	}
    	if(worldObj.getBlock(xCoord+ForgeDirection.EAST.offsetX, yCoord, zCoord+ForgeDirection.EAST.offsetZ) == AdvancedUtilitiesBlocks.bellows && worldObj.getBlockMetadata(xCoord+ForgeDirection.EAST.offsetX, yCoord, zCoord+ForgeDirection.EAST.offsetZ) == 5)// looking east for a bellows pointing west
    	{
    		steamMulti+=1;
    	}
    }
    
    /**
     * Returns true if the furnace can smelt an item, i.e. has a source item, destination stack isn't full, etc.
     */
    protected boolean canSmelt()
    {
    	if(tank.getFluidAmount() < tank.getCapacity() && waterTank.getFluidAmount() >= 5*steamMulti)
    		return true;
    	else 
    		return false;
    }
    
    protected void getWater()
    {
    	if(this.waterTank.getFluidAmount() + 1000 < this.waterTank.getCapacity())
    	{
    		if(worldObj.getBlock(xCoord, yCoord-1, zCoord) == Blocks.water && worldObj.getBlock(xCoord, yCoord-1, zCoord) != Blocks.flowing_water)
    		{
    			this.fill(ForgeDirection.DOWN, new FluidStack(FluidRegistry.WATER, 1000), true);
    			worldObj.setBlockToAir(xCoord, yCoord-1, zCoord);
    		}
    	}
    }

    /**
     * Turn one item from the furnace source stack into the appropriate smelted item in the furnace result stack
     */
    public void smeltItem()
    {
        if (this.canSmelt())
        {
            ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.inventory[0]);

            if (this.inventory[2] == null)
            {
                this.inventory[2] = itemstack.copy();
            }
            else if (this.inventory[2].getItem() == itemstack.getItem())
            {
                this.inventory[2].stackSize += itemstack.stackSize; // Forge BugFix: Results may have multiple items
            }

            --this.inventory[0].stackSize;

            if (this.inventory[0].stackSize <= 0)
            {
                this.inventory[0] = null;
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
                    return 150/5;
                }

                if (block.getMaterial() == Material.wood)
                {
                    return 300/5;
                }

                if (block == Blocks.coal_block)
                {
                    return 16000/5;
                }
            }

            if (item instanceof ItemTool && ((ItemTool)item).getToolMaterialName().equals("WOOD")) return 200/5;
            if (item instanceof ItemSword && ((ItemSword)item).getToolMaterialName().equals("WOOD")) return 200/5;
            if (item instanceof ItemHoe && ((ItemHoe)item).getToolMaterialName().equals("WOOD")) return 200/5;
            if (item == Items.stick) return 100/5;
            if (item == Items.coal) return 1600/5;
            if (item == Item.getItemFromBlock(Blocks.sapling)) return 100/5;
            if (item == Items.blaze_rod) return 2400/5;
            return GameRegistry.getFuelValue(p_145952_0_)/5;
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

    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill)
    {
    	 if(resource.getFluid() == AdvancedUtilitiesFluids.fluidSteam)
         	return tank.fill(resource, doFill);
         if(resource.getFluid() == FluidRegistry.WATER)
         	return waterTank.fill(resource, doFill);
		return 0;
    }

    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain)
    {
        if (resource == null || (!resource.isFluidEqual(tank.getFluid()) && !resource.isFluidEqual(waterTank.getFluid())))
        {
            return null;
        }
        if(resource.getFluid() == AdvancedUtilitiesFluids.fluidSteam)
        	return tank.drain(resource.amount, doDrain);
		return null;
    }

    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain)
    {
        return tank.drain(maxDrain, doDrain);
    }

    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid)
    {
        if(fluid == FluidRegistry.WATER || fluid == AdvancedUtilitiesFluids.fluidSteam)
        	return true;
        else
        	return false;
    }

    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid)
    {
        if(fluid == AdvancedUtilitiesFluids.fluidSteam)
        {
        	return true;
        }
        else
        {
        	return false;
        }
    }

    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from)
    {
        return new FluidTankInfo[] { tank.getInfo() };
    }
	
}
