package com.sudwood.advancedutilities.tileentity;

import java.util.List;








import com.sudwood.advancedutilities.TransferHelper;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;


public class TileEntitySplitterItemTube extends TileEntityItemTube implements IInventory
{
    private ItemStack[] inventory = new ItemStack[5];
    private String inventoryName;
    private int transferCooldown = 0;
    public final int CooldownTime = 8;
    public int numTransfered = 1;
    private int[] lastDir = {1, 1, 1, 1, 1, 1};
    
    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);
        this.lastDir = tag.getIntArray("lastDir");
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
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
        tag.setIntArray("lastDir", lastDir);
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
    	case 1:
    		int[] slot = TransferHelper.getFirstStackThatFits(xCoord, yCoord+ForgeDirection.UP.offsetY, zCoord, inventory, worldObj, numTransfered, this, 0);
	    	if(slot!= null)
	    	{
	    		try
	    		{
	    			IInventory tile = (IInventory) worldObj.getTileEntity(xCoord, yCoord+ForgeDirection.UP.offsetY, zCoord);
	    			if(!isWrongTile(ForgeDirection.UP))
	    			{
		    			if(slot[1] == 0)
		    			{
		    				inventory[slot[2]] = new ItemStack(tile.getStackInSlot(slot[0]).getItem(), numTransfered, tile.getStackInSlot(slot[0]).getItemDamage());
		    				this.markDirty();
		    				if(tile.getStackInSlot(slot[0]).stackSize == 1)
		    				{
		    					tile.setInventorySlotContents(slot[0], null);
		    				}
		    				else
		    					tile.setInventorySlotContents(slot[0], new ItemStack(tile.getStackInSlot(slot[0]).getItem(), tile.getStackInSlot(slot[0]).stackSize - numTransfered, tile.getStackInSlot(slot[0]).getItemDamage()));
		    				tile.markDirty();
		    			}
		    			if(slot[1] == 1)
		    			{
		    				this.setInventorySlotContents(slot[2], new ItemStack(inventory[slot[2]].getItem(), inventory[slot[2]].stackSize + numTransfered, inventory[slot[2]].getItemDamage()));
		    				this.markDirty();
		    				if(tile.getStackInSlot(slot[0]).stackSize == 1)
		    				{
		    					tile.setInventorySlotContents(slot[0], null);
		    				}
		    				else
		    					tile.setInventorySlotContents(slot[0], new ItemStack(tile.getStackInSlot(slot[0]).getItem(), tile.getStackInSlot(slot[0]).stackSize - numTransfered, tile.getStackInSlot(slot[0]).getItemDamage()));
		    				tile.markDirty();
		    			}
	    			}
	    		}
	    		
	    		catch(Exception e)
	    		{
	    			
	    		}
	    	}
	    	break;
    	case 0:
    		int[] slot1 = TransferHelper.getFirstStackThatFits(xCoord, yCoord+ForgeDirection.DOWN.offsetY, zCoord, inventory, worldObj, numTransfered, this, 1);
	    	if(slot1!= null)
	    	{
	    		try
	    		{
	    			IInventory tile = (IInventory) worldObj.getTileEntity(xCoord, yCoord+ForgeDirection.DOWN.offsetY, zCoord);
	    			if(!isWrongTile(ForgeDirection.DOWN))
	    			{
		    			if(slot1[1] == 0)
		    			{
		    				inventory[slot1[2]] = new ItemStack(tile.getStackInSlot(slot1[0]).getItem(), numTransfered, tile.getStackInSlot(slot1[0]).getItemDamage());
		    				this.markDirty();
		    				if(tile.getStackInSlot(slot1[0]).stackSize == 1)
		    				{
		    					tile.setInventorySlotContents(slot1[0], null);
		    				}
		    				else
		    					tile.setInventorySlotContents(slot1[0], new ItemStack(tile.getStackInSlot(slot1[0]).getItem(), tile.getStackInSlot(slot1[0]).stackSize - numTransfered, tile.getStackInSlot(slot1[0]).getItemDamage()));
		    				tile.markDirty();
		    			}
		    			if(slot1[1] == 1)
		    			{
		    				this.setInventorySlotContents(slot1[2], new ItemStack(inventory[slot1[2]].getItem(), inventory[slot1[2]].stackSize + numTransfered, inventory[slot1[2]].getItemDamage()));
		    				this.markDirty();
		    				if(tile.getStackInSlot(slot1[0]).stackSize == 1)
		    				{
		    					tile.setInventorySlotContents(slot1[0], null);
		    				}
		    				else
		    					tile.setInventorySlotContents(slot1[0], new ItemStack(tile.getStackInSlot(slot1[0]).getItem(), tile.getStackInSlot(slot1[0]).stackSize - numTransfered, tile.getStackInSlot(slot1[0]).getItemDamage()));
		    				tile.markDirty();
		    			}
	    			}
	    		}
	    		
	    		catch(Exception e)
	    		{
	    			
	    		}
	    	}
	    	break;
    	case 3:
    		int[] slot11 = TransferHelper.getFirstStackThatFits(xCoord+ForgeDirection.SOUTH.offsetX, yCoord+ForgeDirection.SOUTH.offsetY, zCoord+ForgeDirection.SOUTH.offsetZ, inventory, worldObj, numTransfered, this, 2);
	    	if(slot11!= null)
	    	{
	    		try
	    		{
	    			IInventory tile = (IInventory) worldObj.getTileEntity(xCoord+ForgeDirection.SOUTH.offsetX, yCoord+ForgeDirection.SOUTH.offsetY, zCoord+ForgeDirection.SOUTH.offsetZ);
	    			if(!isWrongTile(ForgeDirection.SOUTH))
	    			{
		    			if(slot11[1] == 0)
		    			{
		    				inventory[slot11[2]] = new ItemStack(tile.getStackInSlot(slot11[0]).getItem(), numTransfered, tile.getStackInSlot(slot11[0]).getItemDamage());
		    				this.markDirty();
		    				if(tile.getStackInSlot(slot11[0]).stackSize == 1)
		    				{
		    					tile.setInventorySlotContents(slot11[0], null);
		    				}
		    				else
		    					tile.setInventorySlotContents(slot11[0], new ItemStack(tile.getStackInSlot(slot11[0]).getItem(), tile.getStackInSlot(slot11[0]).stackSize - numTransfered, tile.getStackInSlot(slot11[0]).getItemDamage()));
		    				tile.markDirty();
		    			}
		    			if(slot11[1] == 1)
		    			{
		    				this.setInventorySlotContents(slot11[2], new ItemStack(inventory[slot11[2]].getItem(), inventory[slot11[2]].stackSize + numTransfered, inventory[slot11[2]].getItemDamage()));
		    				this.markDirty();
		    				if(tile.getStackInSlot(slot11[0]).stackSize == 1)
		    				{
		    					tile.setInventorySlotContents(slot11[0], null);
		    				}
		    				else
		    					tile.setInventorySlotContents(slot11[0], new ItemStack(tile.getStackInSlot(slot11[0]).getItem(), tile.getStackInSlot(slot11[0]).stackSize - numTransfered, tile.getStackInSlot(slot11[0]).getItemDamage()));
		    				tile.markDirty();
		    			}
	    			}
	    		}
	    		
	    		catch(Exception e)
	    		{
	    			
	    		}
	    	}
	    	break;
    	case 2:
    		int[] slot111 = TransferHelper.getFirstStackThatFits(xCoord+ForgeDirection.NORTH.offsetX, yCoord+ForgeDirection.NORTH.offsetY, zCoord+ForgeDirection.NORTH.offsetZ, inventory, worldObj, numTransfered, this, 3);
	    	if(slot111!= null)
	    	{
	    		try
	    		{
	    			IInventory tile = (IInventory) worldObj.getTileEntity(xCoord+ForgeDirection.NORTH.offsetX, yCoord+ForgeDirection.NORTH.offsetY, zCoord+ForgeDirection.NORTH.offsetZ);
	    			if(!isWrongTile(ForgeDirection.NORTH))
	    			{
		    			if(slot111[1] == 0)
		    			{
		    				inventory[slot111[2]] = new ItemStack(tile.getStackInSlot(slot111[0]).getItem(), numTransfered, tile.getStackInSlot(slot111[0]).getItemDamage());
		    				this.markDirty();
		    				if(tile.getStackInSlot(slot111[0]).stackSize == 1)
		    				{
		    					tile.setInventorySlotContents(slot111[0], null);
		    				}
		    				else
		    					tile.setInventorySlotContents(slot111[0], new ItemStack(tile.getStackInSlot(slot111[0]).getItem(), tile.getStackInSlot(slot111[0]).stackSize - numTransfered, tile.getStackInSlot(slot111[0]).getItemDamage()));
		    				tile.markDirty();
		    			}
		    			if(slot111[1] == 1)
		    			{
		    				this.setInventorySlotContents(slot111[2], new ItemStack(inventory[slot111[2]].getItem(), inventory[slot111[2]].stackSize + numTransfered, inventory[slot111[2]].getItemDamage()));
		    				this.markDirty();
		    				if(tile.getStackInSlot(slot111[0]).stackSize == 1)
		    				{
		    					tile.setInventorySlotContents(slot111[0], null);
		    				}
		    				else
		    					tile.setInventorySlotContents(slot111[0], new ItemStack(tile.getStackInSlot(slot111[0]).getItem(), tile.getStackInSlot(slot111[0]).stackSize - numTransfered, tile.getStackInSlot(slot111[0]).getItemDamage()));
		    				tile.markDirty();
		    			}
	    			}
	    		}
	    		
	    		catch(Exception e)
	    		{
	    			
	    		}
	    	}
	    	break;
    	case 4:
    		int[] slot1111 = TransferHelper.getFirstStackThatFits(xCoord+ForgeDirection.WEST.offsetX, yCoord+ForgeDirection.WEST.offsetY, zCoord+ForgeDirection.WEST.offsetZ, inventory, worldObj, numTransfered, this, 5);
	    	if(slot1111!= null)
	    	{
	    		try
	    		{
	    			IInventory tile = (IInventory) worldObj.getTileEntity(xCoord+ForgeDirection.WEST.offsetX, yCoord+ForgeDirection.WEST.offsetY, zCoord+ForgeDirection.WEST.offsetZ);
	    			if(!isWrongTile(ForgeDirection.WEST))
	    			{
		    			if(slot1111[1] == 0)
		    			{
		    				inventory[slot1111[2]] = new ItemStack(tile.getStackInSlot(slot1111[0]).getItem(), numTransfered, tile.getStackInSlot(slot1111[0]).getItemDamage());
		    				this.markDirty();
		    				if(tile.getStackInSlot(slot1111[0]).stackSize == 1)
		    				{
		    					tile.setInventorySlotContents(slot1111[0], null);
		    				}
		    				else
		    					tile.setInventorySlotContents(slot1111[0], new ItemStack(tile.getStackInSlot(slot1111[0]).getItem(), tile.getStackInSlot(slot1111[0]).stackSize - numTransfered, tile.getStackInSlot(slot1111[0]).getItemDamage()));
		    				tile.markDirty();
		    			}
		    			if(slot1111[1] == 1)
		    			{
		    				this.setInventorySlotContents(slot1111[2], new ItemStack(inventory[slot1111[2]].getItem(), inventory[slot1111[2]].stackSize + numTransfered, inventory[slot1111[2]].getItemDamage()));
		    				this.markDirty();
		    				if(tile.getStackInSlot(slot1111[0]).stackSize == 1)
		    				{
		    					tile.setInventorySlotContents(slot1111[0], null);
		    				}
		    				else
		    					tile.setInventorySlotContents(slot1111[0], new ItemStack(tile.getStackInSlot(slot1111[0]).getItem(), tile.getStackInSlot(slot1111[0]).stackSize - numTransfered, tile.getStackInSlot(slot1111[0]).getItemDamage()));
		    				tile.markDirty();
		    			}
	    			}
	    		}
	    		
	    		catch(Exception e)
	    		{
	    			
	    		}
	    	}
	    	break;
    	case 5:
    		int[] slot11111 = TransferHelper.getFirstStackThatFits(xCoord+ForgeDirection.EAST.offsetX, yCoord+ForgeDirection.EAST.offsetY, zCoord+ForgeDirection.EAST.offsetZ, inventory, worldObj, numTransfered, this, 4);
	    	if(slot11111!= null)
	    	{
	    		try
	    		{
	    			IInventory tile = (IInventory) worldObj.getTileEntity(xCoord+ForgeDirection.EAST.offsetX, yCoord+ForgeDirection.EAST.offsetY, zCoord+ForgeDirection.EAST.offsetZ);
	    			if(!isWrongTile(ForgeDirection.EAST))
	    			{
		    			if(slot11111[1] == 0)
		    			{
		    				inventory[slot11111[2]] = new ItemStack(tile.getStackInSlot(slot11111[0]).getItem(), numTransfered, tile.getStackInSlot(slot11111[0]).getItemDamage());
		    				this.markDirty();
		    				if(tile.getStackInSlot(slot11111[0]).stackSize == 1)
		    				{
		    					tile.setInventorySlotContents(slot11111[0], null);
		    				}
		    				else
		    					tile.setInventorySlotContents(slot11111[0], new ItemStack(tile.getStackInSlot(slot11111[0]).getItem(), tile.getStackInSlot(slot11111[0]).stackSize - numTransfered, tile.getStackInSlot(slot11111[0]).getItemDamage()));
		    				tile.markDirty();
		    			}
		    			if(slot11111[1] == 1)
		    			{
		    				this.setInventorySlotContents(slot11111[2], new ItemStack(inventory[slot11111[2]].getItem(), inventory[slot11111[2]].stackSize + numTransfered, inventory[slot11111[2]].getItemDamage()));
		    				this.markDirty();
		    				if(tile.getStackInSlot(slot11111[0]).stackSize == 1)
		    				{
		    					tile.setInventorySlotContents(slot11111[0], null);
		    				}
		    				else
		    					tile.setInventorySlotContents(slot11111[0], new ItemStack(tile.getStackInSlot(slot11111[0]).getItem(), tile.getStackInSlot(slot11111[0]).stackSize - numTransfered, tile.getStackInSlot(slot11111[0]).getItemDamage()));
		    				tile.markDirty();
		    			}
	    			}
	    		}
	    		
	    		catch(Exception e)
	    		{
	    			
	    		}
	    	}
	    	break;
    	}
    }
    
    public void pushItem()
    {
    	int sideAttached = worldObj.getBlockMetadata(xCoord, yCoord, zCoord) ^ 1;
    	int[] slot = null;
    	IInventory transfer = null;
    	for(int i = 0; i < inventory.length; i++)
    	{
	    	if(inventory[i]!=null)
	    	{
	    		lastDir[worldObj.getBlockMetadata(xCoord, yCoord, zCoord)^1] = 0;
	    		for(int ix = 0; ix < 6; ix++)
	    		{
	    			if(lastDir[0] == 0 && lastDir[1] == 0 && lastDir[0] == 0 && lastDir[2] == 0 && lastDir[3] == 0 && lastDir[4] == 0 && lastDir[0] == 0)
	    			{
	    				
	    				lastDir[0] = 1;
	    				lastDir[1] = 1;
	    				lastDir[2] = 1;
	    				lastDir[3] = 1;
	    				lastDir[4] = 1;
	    				lastDir[5] = 1;
	    				lastDir[worldObj.getBlockMetadata(xCoord, yCoord, zCoord)^1] = 0;
	    			}
	    			ForgeDirection dir = ForgeDirection.getOrientation(ix);
	    			if(lastDir[ix] == 0 || worldObj.getTileEntity(xCoord+dir.offsetX, yCoord+dir.offsetY, zCoord+dir.offsetZ) == null || !(worldObj.getTileEntity(xCoord+dir.offsetX, yCoord+dir.offsetY, zCoord+dir.offsetZ) instanceof IInventory))
		    			{
		    				lastDir[ix] = 0;
		    				continue;
		    			}
	    			else if(worldObj.getTileEntity(xCoord+dir.offsetX, yCoord+dir.offsetY, zCoord+dir.offsetZ) != null && worldObj.getTileEntity(xCoord+dir.offsetX, yCoord+dir.offsetY, zCoord+dir.offsetZ) instanceof IInventory)
	    			{
	    				slot = TransferHelper.checkSpace(xCoord+dir.offsetX, yCoord+dir.offsetY, zCoord+dir.offsetZ, new ItemStack(inventory[i].getItem(), 1, inventory[i].getItemDamage()), worldObj, ix^1);
		    			try
		    			{
		    				transfer = (IInventory) worldObj.getTileEntity(xCoord+dir.offsetX, yCoord+dir.offsetY, zCoord+dir.offsetZ);
		    			}
		    			catch(Exception e)
		    			{
		    				
		    			}
		    			if(slot!=null && transfer!= null)
			    		{	
		    				
			    			if(slot[1] == 0)
			    			{
			    				ItemStack temp = inventory[i].copy();
			    				temp.stackSize = this.numTransfered;
			    				transfer.setInventorySlotContents(slot[0], temp);
			    				transfer.markDirty();
			    				if(inventory[i].stackSize == 1)
			    				{
			    					inventory[i] = null;
			    					this.markDirty();
			    					lastDir[ix] = 0;
			    					return;
			    				}
			    				inventory[i].stackSize-=numTransfered;
			    				this.markDirty();
			    				lastDir[ix] = 0;
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
			    				if(inventory[i].stackSize == 1)
			    				{
			    					inventory[i] = null;
			    					this.markDirty();
			    					lastDir[ix] = 0;
			    					return;
			    				}
			    				inventory[i].stackSize-=numTransfered;
			    				this.markDirty();
			    				lastDir[ix] = 0;
			    				return;
			    				
			    			}
			    			
			    		}
		    			
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

}
