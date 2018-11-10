package com.sudwood.advancedutilities.tileentity;

import com.sudwood.advancedutilities.HelperLibrary;
import com.sudwood.advancedutilities.TransferHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;


public class TileEntityRestrictedItemTube extends TileEntity implements IInventory
{
	public static final int WHITELIST = 0;
	public static final int BLACKLIST = 1;
	private int mode = WHITELIST;
    private ItemStack[] inventory = new ItemStack[5];
    private ItemStack[] restriction = new ItemStack[9];
    private String inventoryName;
    private int transferCooldown = 0;
    public final int CooldownTime = 8;
    public int numTransfered = 1;
    
    
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        mode = par1NBTTagCompound.getInteger("Mode");
        NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items", 10);
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
        
        NBTTagList nbttaglist1 = par1NBTTagCompound.getTagList("Restriction", 10);
        this.restriction = new ItemStack[9];

        for (int i = 0; i < nbttaglist1.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound2 = nbttaglist1.getCompoundTagAt(i);
            byte b0 = nbttagcompound2.getByte("Slot");

            if (b0 >= 0 && b0 < this.restriction.length)
            {
                this.restriction[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound2);
            }
        }
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
        tag.setInteger("Mode", mode);
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
        
        NBTTagList nbttaglist1 = new NBTTagList();

        for (int i = 0; i < this.restriction.length; ++i)
        {
            if (this.restriction[i] != null)
            {
                NBTTagCompound nbttagcompound2 = new NBTTagCompound();
                nbttagcompound2.setByte("Slot", (byte)i);
                this.restriction[i].writeToNBT(nbttagcompound2);
                nbttaglist1.appendTag(nbttagcompound2);
            }
        }

        tag.setTag("Restriction", nbttaglist1);

    }
    
    public void setWhitelist()
    {
    	mode = WHITELIST;
    	worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		this.markDirty();
    }
    
    public void setBlacklist()
    {
    	mode = BLACKLIST;
    	worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		this.markDirty();
    }
    
    public int getMode()
    {
    	return mode;
    }
    
    @Override
    public void updateEntity()
    {
    	if(!worldObj.isRemote && !worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord))
    	{
	    	if(inventory[0]!=null || inventory[1]!=null || inventory[2]!=null || inventory[3]!=null || inventory[4]!=null)
	    		pushItem();
	    	
	    	if((inventory[0] ==null || inventory[0].stackSize<64) || (inventory[1] ==null || inventory[1].stackSize<64) || (inventory[2] ==null || inventory[2].stackSize<64) || (inventory[3] ==null || inventory[3].stackSize<64) || (inventory[4] ==null || inventory[4].stackSize<64))
	    	{
	    		pullItem();
	    	}
    	}
    }
    
    public boolean putItemInThisInventory(ItemStack stack)
    {
    	int[] slot = null;
    	if(stack!=null)
    	{
    		slot = TransferHelper.checkSpace(xCoord, yCoord, zCoord, stack, worldObj, 0);
    		if(slot!=null && this.isItemValidForSlot(slot[0],stack))
    		{
	    		if(slot[1] == 0)
				{
					inventory[slot[0]] = new ItemStack(stack.getItem(), stack.stackSize, stack.getItemDamage());
					this.markDirty();
					return true;
				}
				if(slot[1] == 1)
				{
					inventory[slot[0]].stackSize += stack.stackSize;
					this.markDirty();
					return true;
				}
    		}
    	}
    	return false;
    	
    }
    public boolean isWrongTile(ForgeDirection dir)
    {
    	if(worldObj.getTileEntity(xCoord+dir.offsetX, yCoord+dir.offsetY, zCoord+dir.offsetZ) instanceof TileEntityRestrictedItemTube)
    	{
    		return true;
    	}
    	if(worldObj.getTileEntity(xCoord+dir.offsetX, yCoord+dir.offsetY, zCoord+dir.offsetZ) instanceof TileEntitySplitterItemTube)
    	{
    		return true;
    	}
    	if(worldObj.getTileEntity(xCoord+dir.offsetX, yCoord+dir.offsetY, zCoord+dir.offsetZ) instanceof TileEntityItemTube)
    	{
    		return true;
    	}
    	return false;
    }
     
    public void pullItem()
    {
    	
    	switch(worldObj.getBlockMetadata(xCoord, yCoord, zCoord))
    	{
    	case 1: // up
    		TransferHelper.pullItem(this, xCoord, yCoord, zCoord, ForgeDirection.UP, worldObj, numTransfered);
	    	break;
    	case 0:// down
    		TransferHelper.pullItem(this, xCoord, yCoord, zCoord, ForgeDirection.DOWN, worldObj, numTransfered);
	    	break;
    	case 3:// south
    		TransferHelper.pullItem(this, xCoord, yCoord, zCoord, ForgeDirection.SOUTH, worldObj, numTransfered);
	    	break;
    	case 2:// north
    		TransferHelper.pullItem(this, xCoord, yCoord, zCoord, ForgeDirection.NORTH, worldObj, numTransfered);
	    	break;
    	case 4:// west
    		TransferHelper.pullItem(this, xCoord, yCoord, zCoord, ForgeDirection.WEST, worldObj, numTransfered);
	    	break;
    	case 5:// east
    		TransferHelper.pullItem(this, xCoord, yCoord, zCoord, ForgeDirection.EAST, worldObj, numTransfered);
	    	break;
    	}
    }
    
    public void pushItem()
    {
    	switch(worldObj.getBlockMetadata(xCoord, yCoord, zCoord))
    	{
    	case 1: // up
    		TransferHelper.pushItem(this, xCoord, yCoord, zCoord, ForgeDirection.UP, worldObj, numTransfered);
	    	break;
    	case 0:// down
    		TransferHelper.pushItem(this, xCoord, yCoord, zCoord, ForgeDirection.DOWN, worldObj, numTransfered);
	    	break;
    	case 3:// south
    		TransferHelper.pushItem(this, xCoord, yCoord, zCoord, ForgeDirection.SOUTH, worldObj, numTransfered);
	    	break;
    	case 2:// north
    		TransferHelper.pushItem(this, xCoord, yCoord, zCoord, ForgeDirection.NORTH, worldObj, numTransfered);
	    	break;
    	case 4:// west
    		TransferHelper.pushItem(this, xCoord, yCoord, zCoord, ForgeDirection.WEST, worldObj, numTransfered);
	    	break;
    	case 5:// east
    		TransferHelper.pushItem(this, xCoord, yCoord, zCoord, ForgeDirection.EAST, worldObj, numTransfered);
	    	break;
    	}
    }
    
	@Override
	public int getSizeInventory() {
		// TODO Auto-generated method stub
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		// TODO Auto-generated method stub
		if(i < 5)
			return inventory[i];
		else
			return restriction[i-5];
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		if (i< 5 && this.inventory[i] != null)
        {
            ItemStack itemstack;

            if (this.inventory[i].stackSize <= j)
            {
                itemstack = this.inventory[i];
                this.inventory[i] = null;
                return itemstack;
            }
            else
            {
                itemstack = this.inventory[i].splitStack(j);

                if (this.inventory[i].stackSize == 0)
                {
                    this.inventory[i] = null;
                }

                return itemstack;
            }
        }
		if (i >= 5 && this.restriction[i-5] != null)
        {
            ItemStack itemstack;

            if (this.restriction[i-5].stackSize <= j)
            {
                itemstack = this.restriction[i-5];
                this.restriction[i-5] = null;
                return itemstack;
            }
            else
            {
                itemstack = this.restriction[i-5].splitStack(j);

                if (this.restriction[i-5].stackSize == 0)
                {
                    this.restriction[i-5] = null;
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
	public ItemStack getStackInSlotOnClosing(int i) {
		if (i < 5 && this.inventory[i] != null)
        {
            ItemStack itemstack = this.inventory[i];
            this.inventory[i] = null;
            return itemstack;
        }
		if (i >= 5 && this.restriction[i-5] != null)
        {
            ItemStack itemstack = restriction[i-5];
            this.restriction[i-5] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		if(i < 5)
		{
			this.inventory[i] = itemstack;
	
	        if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit())
	        {
	        	itemstack.stackSize = this.getInventoryStackLimit();
	        }
		}
		else
		{
			this.restriction[i-5] = itemstack;
			if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit())
		       {
		        	itemstack.stackSize = this.getInventoryStackLimit();
		       }
		}
	}




	@Override
	public int getInventoryStackLimit() {
		// TODO Auto-generated method stub
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
    {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		boolean result = false;
		boolean[] temp = new boolean[9];
		boolean isToolBow = false;
		if(itemstack == null)
			return false;
		if(itemstack.getItem() instanceof ItemTool || itemstack.getItem() instanceof ItemBow)
		{
			isToolBow = true;
		}
		for(int iz = 0; iz < 9; iz++)
		{
			temp[iz] = true;
		}
		for (int ix = 0; ix < 9; ix++)
		{
			if(isToolBow&&mode == WHITELIST && restriction[ix]!=null && (HelperLibrary.areItemStacksSameItem(restriction[ix],itemstack) ))
			{
				result = true;
			}
			if(isToolBow&&mode == BLACKLIST && restriction[ix]!=null && (HelperLibrary.areItemStacksSameItem(restriction[ix],itemstack)))
			{
				result = false;
			}
			if(mode == WHITELIST && restriction[ix]!=null && (HelperLibrary.areItemStacksSameItemAndDamageAndNBT(restriction[ix],itemstack) ))
			{
				result = true;
			}
			if(mode == BLACKLIST && restriction[ix]!=null && (HelperLibrary.areItemStacksSameItemAndDamageAndNBT(restriction[ix],itemstack)))
			{
				result = false;
			}
			
		}

		return result;
	}

	@Override
	public String getInventoryName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasCustomInventoryName() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void openInventory() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeInventory() {
		// TODO Auto-generated method stub
		
	}
	
	public void doPullItem(int x, int y, int z, int side, ForgeDirection dir)
	{
		int[] slot = TransferHelper.getFirstStackThatFits(x,y,z, inventory, worldObj, numTransfered, this, 0);
    	if(slot!= null)
    	{
    		try
    		{
    			IInventory tile = (IInventory) worldObj.getTileEntity(x,y,z);
    			if(!isWrongTile(dir))
    			{
    				ItemStack temp = tile.getStackInSlot(slot[0]).copy();
    				temp.stackSize = numTransfered;
	    			if(slot[1] == 0)
	    			{
	    				inventory[slot[2]] = temp;
	    				this.markDirty();
	    				if(tile.getStackInSlot(slot[0]).stackSize <= numTransfered)
	    				{
	    					tile.setInventorySlotContents(slot[0], null);
	    				}
	    				else
	    				{
	    					temp.stackSize = tile.getStackInSlot(slot[0]).stackSize - numTransfered;
	    					tile.setInventorySlotContents(slot[0], temp);
	    				}
	    				tile.markDirty();
	    			}
	    			if(slot[1] == 1)
	    			{
	    				temp.stackSize = inventory[slot[2]].stackSize + numTransfered;
	    				this.setInventorySlotContents(slot[2],temp);
	    				this.markDirty();
	    				if(tile.getStackInSlot(slot[0]).stackSize <= numTransfered)
	    				{
	    					tile.setInventorySlotContents(slot[0], null);
	    				}
	    				else
	    				{
	    					temp.stackSize = tile.getStackInSlot(slot[0]).stackSize - numTransfered;
	    					tile.setInventorySlotContents(slot[0], temp);
	    				}
	    				tile.markDirty();
	    			}
    			}
    		}
    		
    		catch(Exception e)
    		{
    			
    		}
    	}
	}

}
