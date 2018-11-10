package com.sudwood.advancedutilities.tileentity;
import com.sudwood.advancedutilities.TransferHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityItemTube extends TileEntity implements IInventory
{
    protected ItemStack[] inventory = new ItemStack[5];
    private String inventoryName;
    private int transferCooldown = 0;
    public final int CooldownTime = 8;
    public int numTransfered = 1;
    public boolean checkPower = true;
    
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        checkPower = par1NBTTagCompound.getBoolean("checkpower");
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
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
        tag.setBoolean("checkpower", checkPower);
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
    public void updateEntity()
    {
    	if(checkPower)
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
    	if(!checkPower)
    	{
    		if(!worldObj.isRemote)
	    	{
		    	if(inventory[0]!=null || inventory[1]!=null || inventory[2]!=null || inventory[3]!=null || inventory[4]!=null)
		    		pushItem();
		    	
		    	if((inventory[0] ==null || inventory[0].stackSize<64) || (inventory[1] ==null || inventory[1].stackSize<64) || (inventory[2] ==null || inventory[2].stackSize<64) || (inventory[3] ==null || inventory[3].stackSize<64) || (inventory[4] ==null || inventory[4].stackSize<64))
		    	{
		    		pullItem();
		    	}
	    	}
    	}
    }
    
    public boolean putItemInThisInventory(ItemStack stack)
    {
    	int[] slot = null;
    	if(stack!=null)
    	{
    		slot = TransferHelper.checkSpace(xCoord, yCoord, zCoord, stack, worldObj, 0);
    		if(slot!=null)
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
    
    public void pushItemOld()
    {
    	int sideAttached = worldObj.getBlockMetadata(xCoord, yCoord, zCoord) ^ 1;
    	int[] slot = null;
    	for(int i = 0; i < inventory.length; i++)
    	{
	    	if(inventory[i]!=null)
	    	{
	    		
	    		IInventory transfer = null;
	    		switch(worldObj.getBlockMetadata(xCoord, yCoord, zCoord))
	    		{
	    		case 1: //down
	    			slot = TransferHelper.checkSpace(xCoord+ForgeDirection.DOWN.offsetX, yCoord+ForgeDirection.DOWN.offsetY, zCoord+ForgeDirection.DOWN.offsetZ, new ItemStack(inventory[i].getItem(), numTransfered, inventory[i].getItemDamage()), worldObj, sideAttached);
	    			try
	    			{
	    				transfer = (IInventory) worldObj.getTileEntity(xCoord+ForgeDirection.DOWN.offsetX, yCoord+ForgeDirection.DOWN.offsetY, zCoord+ForgeDirection.DOWN.offsetZ);
	    			}
	    			catch(Exception e)
	    			{
	    				
	    			}
	    			break;
	    		case 0: //up
	    			slot = TransferHelper.checkSpace(xCoord+ForgeDirection.UP.offsetX, yCoord+ForgeDirection.UP.offsetY, zCoord+ForgeDirection.UP.offsetZ, new ItemStack(inventory[i].getItem(), numTransfered, inventory[i].getItemDamage()), worldObj, sideAttached);
	    			try
	    			{
	    				transfer = (IInventory) worldObj.getTileEntity(xCoord+ForgeDirection.UP.offsetX, yCoord+ForgeDirection.UP.offsetY, zCoord+ForgeDirection.UP.offsetZ);
	    			}
	    			catch(Exception e)
	    			{
	    				
	    			}
	    			break;
	    		case 3: //North
	    			slot = TransferHelper.checkSpace(xCoord+ForgeDirection.NORTH.offsetX, yCoord+ForgeDirection.NORTH.offsetY, zCoord+ForgeDirection.NORTH.offsetZ, new ItemStack(inventory[i].getItem(), numTransfered, inventory[i].getItemDamage()), worldObj, sideAttached);
	    			
	    			try
	    			{
	    				transfer = (IInventory) worldObj.getTileEntity(xCoord+ForgeDirection.NORTH.offsetX, yCoord+ForgeDirection.NORTH.offsetY, zCoord+ForgeDirection.NORTH.offsetZ);
	    			}
	    			catch(Exception e)
	    			{
	    				
	    			}break;
	    		case 2: //south
	    			slot = TransferHelper.checkSpace(xCoord+ForgeDirection.SOUTH.offsetX, yCoord+ForgeDirection.SOUTH.offsetY, zCoord+ForgeDirection.SOUTH.offsetZ, new ItemStack(inventory[i].getItem(), numTransfered, inventory[i].getItemDamage()), worldObj, sideAttached);
	    			try
	    			{
	    				transfer = (IInventory) worldObj.getTileEntity(xCoord+ForgeDirection.SOUTH.offsetX, yCoord+ForgeDirection.SOUTH.offsetY, zCoord+ForgeDirection.SOUTH.offsetZ);
	    			}
	    			catch(Exception e)
	    			{
	    				
	    			}
	    			break;
	    		case 5: //west
	    			slot = TransferHelper.checkSpace(xCoord+ForgeDirection.WEST.offsetX, yCoord+ForgeDirection.WEST.offsetY, zCoord+ForgeDirection.WEST.offsetZ, new ItemStack(inventory[i].getItem(), numTransfered, inventory[i].getItemDamage()), worldObj, sideAttached);
	    			try
	    			{
	    				transfer = (IInventory) worldObj.getTileEntity(xCoord+ForgeDirection.WEST.offsetX, yCoord+ForgeDirection.WEST.offsetY, zCoord+ForgeDirection.WEST.offsetZ);
	    			}
	    			catch(Exception e)
	    			{
	    				
	    			}
	    			break;
	    		case 4: //east
	    			slot = TransferHelper.checkSpace(xCoord+ForgeDirection.EAST.offsetX, yCoord+ForgeDirection.EAST.offsetY, zCoord+ForgeDirection.EAST.offsetZ, new ItemStack(inventory[i].getItem(), numTransfered, inventory[i].getItemDamage()), worldObj, sideAttached);
	    			try
	    			{
	    				transfer = (IInventory) worldObj.getTileEntity(xCoord+ForgeDirection.EAST.offsetX, yCoord+ForgeDirection.EAST.offsetY, zCoord+ForgeDirection.EAST.offsetZ);
	    			}
	    			catch(Exception e)
	    			{
	    				
	    			}
	    			break;
	    		}
	    		if(slot!=null && transfer!= null)
	    		{	
	    			if(slot[1] == 0)
	    			{
	    				ItemStack temp = inventory[i].copy();
	    				temp.stackSize = this.numTransfered;
	    				transfer.setInventorySlotContents(slot[0], temp);
	    				transfer.markDirty();
	    				if(inventory[i].stackSize <= numTransfered)
	    				{
	    					inventory[i] = null;
	    					this.markDirty();
	    					return;
	    				}
	    				inventory[i].stackSize-=numTransfered;
	    				this.markDirty();
	    				return;
	    			}
	    			if(slot[1] == 1 && transfer.getStackInSlot(slot[0]).stackSize < transfer.getInventoryStackLimit())
	    			{
	    				ItemStack stack = inventory[i].copy();
	    				if(slot[2]!=0)
	    					stack.stackSize = slot[2]+transfer.getStackInSlot(slot[0]).stackSize;
	    				else
	    					stack.stackSize = numTransfered+transfer.getStackInSlot(slot[0]).stackSize;
	    				
	    				transfer.setInventorySlotContents(slot[0], stack);
	    				transfer.markDirty();
	    				if(inventory[i].stackSize <= numTransfered)
	    				{
	    					inventory[i] = null;
	    					this.markDirty();
	    					return;
	    				}
	    				inventory[i].stackSize-=numTransfered;
	    				this.markDirty();
	    				return;
	    				
	    			}
	    			
	    		}
	    		
	    	}
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
		return inventory[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		if (this.inventory[i] != null)
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
        else
        {
            return null;
        }
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		if (this.inventory[i] != null)
        {
            ItemStack itemstack = this.inventory[i];
            this.inventory[i] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		this.inventory[i] = itemstack;

        if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit())
        {
        	itemstack.stackSize = this.getInventoryStackLimit();
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
		// TODO Auto-generated method stub
		return true;
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
