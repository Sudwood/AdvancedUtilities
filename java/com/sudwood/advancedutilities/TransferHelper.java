package com.sudwood.advancedutilities;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.world.World;

public class TransferHelper 
{
	/**
	 * 
	 * @param x of the block being checked
	 * @param y of the block being checked
	 * @param z of the block being checked
	 * @param inventory the inventory that the stack is going into - ei the tile entity of the hopper
	 * @param worldObj the world of the block
	 * @return
	 */
	public static int[] getFirstStackThatFits(int x, int y, int z, ItemStack[] inventory, World worldObj, int numTransfered)
	{
		TileEntity tile = null;
		int[] slot = new int[4];
		try
		{
			tile = worldObj.getTileEntity(x, y, z);
		}
		catch(Exception e)
		{
			
		}
		if(tile != null && tile instanceof ISidedInventory)
		{
			int[] slots =  ((ISidedInventory) tile).getAccessibleSlotsFromSide(0);
			if(tile instanceof TileEntityFurnace)
				slots = new int[]{2};
			for(int i = 0; i < slots.length; i++)
			{
				if(((ISidedInventory)tile).getStackInSlot(slots[i]) != null)
				{
					for(int ix = 0; ix < inventory.length; ix++)
					{
						if(inventory[ix] == null)
						{
							slot[0] = slots[i];
							slot[1] = 0;
							slot[2] = ix;
							return slot;
						}
						if(((ISidedInventory)tile).getStackInSlot(slots[i]) != null && inventory[ix].isItemEqual(((ISidedInventory)tile).getStackInSlot(ix)))
						{
							if(((ISidedInventory)tile).getStackInSlot(slots[i]).stackSize + inventory[ix].stackSize < 64)
							{
								slot[0] = slots[i];
								slot[1] = 1;
								slot[2] = ix;
								slot[3] = 0;
								return slot;
							}
							else if(((ISidedInventory)tile).getStackInSlot(slots[i]).stackSize + inventory[ix].stackSize > 64)
							{
								slot[0] = slots[i];
								slot[1] = 1;
								slot[2] = ix;
								slot[3] = 64-inventory[ix].stackSize;
								return slot;
							}
						}
					}
				}
			}
			return null;
		}
		
		
		if(tile != null && tile instanceof IInventory)
		{
			for(int i = 0; i < ((IInventory)tile).getSizeInventory(); i++)
			{
				if(((IInventory)tile).getStackInSlot(i) != null)
				{
					for(int ix = 0; ix < inventory.length; ix++)
					{
						if(inventory[ix] == null)
						{
							slot[0] = i;
							slot[1] = 0;
							slot[2] = ix;
							return slot;
						}
						if(((IInventory)tile).getStackInSlot(i) != null && inventory[ix].isItemEqual(((IInventory)tile).getStackInSlot(i)))
						{
							if(numTransfered + inventory[ix].stackSize < 64)
							{
								slot[0] = i;
								slot[1] = 1;
								slot[2] = ix;
								slot[3] = 0;
								return slot;
							}
							else if(numTransfered + inventory[ix].stackSize > 64 && inventory[ix].stackSize!=64)
							{
								slot[0] = i;
								slot[1] = 1;
								slot[2] = ix;
								slot[3] = 64-inventory[ix].stackSize;
								return slot;
							}
						}
					}
				}
			}
			return null;
		}
		return null;
	}
	
	/**
	 * 
	 * @param x of the block being checked
	 * @param y of the block being checked
	 * @param z of the block being checked
	 * @param inventory the inventory that the stack is going into - ei the tile entity of the hopper
	 * @param worldObj the world of the block
	 * @param The tile entity calling this method for isitemvalidforslot checking
	 * @return
	 */
	public static int[] getFirstStackThatFits(int x, int y, int z, ItemStack[] inventory, World worldObj, int numTransfered, IInventory tileen)
	{
		TileEntity tile = null;
		int[] slot = new int[4];
		try
		{
			tile = worldObj.getTileEntity(x, y, z);
		}
		catch(Exception e)
		{
			
		}
		if(tile != null && tile instanceof ISidedInventory)
		{
			int[] slots =  ((ISidedInventory) tile).getAccessibleSlotsFromSide(0);
			if(tile instanceof TileEntityFurnace)
				slots = new int[]{2};
			for(int i = 0; i < slots.length; i++)
			{
				if(((ISidedInventory)tile).getStackInSlot(slots[i]) != null)
				{
					for(int ix = 0; ix < inventory.length; ix++)
					{
						if(inventory[ix] == null)
						{
							slot[0] = slots[i];
							slot[1] = 0;
							slot[2] = ix;
							return slot;
						}
						if(((ISidedInventory)tile).getStackInSlot(slots[i]) != null && inventory[ix] != null && inventory[ix].isItemEqual(((ISidedInventory)tile).getStackInSlot(ix)))
						{
							if(((ISidedInventory)tile).getStackInSlot(slots[i]).stackSize + inventory[ix].stackSize < 64)
							{
								slot[0] = slots[i];
								slot[1] = 1;
								slot[2] = ix;
								slot[3] = 0;
								return slot;
							}
							else if(((ISidedInventory)tile).getStackInSlot(slots[i]).stackSize + inventory[ix].stackSize > 64)
							{
								slot[0] = slots[i];
								slot[1] = 1;
								slot[2] = ix;
								slot[3] = 64-inventory[ix].stackSize;
								return slot;
							}
						}
					}
				}
			}
			return null;
		}
		
		
		if(tile != null && tile instanceof IInventory)
		{
			for(int i = 0; i < ((IInventory)tile).getSizeInventory(); i++)
			{
				if(((IInventory)tile).getStackInSlot(i) != null)
				{
					for(int ix = 0; ix < inventory.length; ix++)
					{
						if(inventory[ix] == null && tileen.isItemValidForSlot(ix, ((IInventory)tile).getStackInSlot(i)))
						{
							slot[0] = i;
							slot[1] = 0;
							slot[2] = ix;
							return slot;
						}
						if(((IInventory)tile).getStackInSlot(i) != null && inventory[ix]!=null && inventory[ix].isItemEqual(((IInventory)tile).getStackInSlot(i)) && tileen.isItemValidForSlot(ix, ((IInventory)tile).getStackInSlot(i)))
						{
							if(numTransfered + inventory[ix].stackSize < 64)
							{
								slot[0] = i;
								slot[1] = 1;
								slot[2] = ix;
								slot[3] = 0;
								return slot;
							}
							else if(numTransfered + inventory[ix].stackSize > 64 && inventory[ix].stackSize!=64)
							{
								slot[0] = i;
								slot[1] = 1;
								slot[2] = ix;
								slot[3] = 64-inventory[ix].stackSize;
								return slot;
							}
						}
					}
				}
			}
			return null;
		}
		return null;
	}
	
	
	
	 /**
     * 
     * @param x of block
     * @param y of block
     * @param z of block
     * @param worldObj World of block
     * @param sideAttached for sidded checks
     * @param stack itemstack to check for space in
     * @return int of either empty space or space that can take the given stack
     */
    public static int[] checkSpace(int x, int y, int z, ItemStack stack, World worldObj, int sideAttached)
    {
    	int[] space = new int[3];
            try
            {
            TileEntity tempTile = worldObj.getTileEntity(x, y, z);
            if(tempTile!=null&&tempTile instanceof ISidedInventory && sideAttached != -1)
            {
            	int[] slots =  ((ISidedInventory) tempTile).getAccessibleSlotsFromSide(sideAttached);
            	for(int i = 0; i < slots.length; i++)
            	{
            		if(((ISidedInventory) tempTile).isItemValidForSlot(slots[i], stack) && ((ISidedInventory) tempTile).getStackInSlot(slots[i]) == null)
            		{
            			space[0] = slots[i];
            			space[1] = 0;
            			return space;
            		}
            		if(((ISidedInventory) tempTile).isItemValidForSlot(slots[i], stack) && ((ISidedInventory) tempTile).getStackInSlot(slots[i]).isItemEqual(stack))
            		{
            			if(((ISidedInventory) tempTile).getStackInSlot(slots[i]).stackSize + stack.stackSize <= ((ISidedInventory) tempTile).getInventoryStackLimit())
            			{
	            			space[0] = slots[i];
	            			space[1] = 1;
	            			return space;
            			}
            			if(((ISidedInventory) tempTile).getStackInSlot(slots[i]).stackSize + stack.stackSize > ((ISidedInventory) tempTile).getInventoryStackLimit())
            			{
            				space[0] = slots[i];
	            			space[1] = 1;
	            			space[2] = ((ISidedInventory) tempTile).getInventoryStackLimit() - ((ISidedInventory) tempTile).getStackInSlot(slots[i]).stackSize;
	            			return space;
            			}
            		}
            	}
            }
            if(tempTile!=null&&tempTile instanceof IInventory)
            {
            	for(int i = 0; i < ((IInventory) tempTile).getSizeInventory(); i++)
            	{
            		
            		if(((IInventory) tempTile).isItemValidForSlot(i, stack) && ((IInventory) tempTile).getStackInSlot(i) == null)
            		{
            			
            			space[0] = i;
            			space[1] = 0;
            			space[2] = stack.stackSize;
            			return space;
            		}
            		if(((IInventory) tempTile).isItemValidForSlot(i, stack) && ((IInventory) tempTile).getStackInSlot(i).isItemEqual(stack))
            		{
            			if(((IInventory) tempTile).getStackInSlot(i).stackSize + stack.stackSize <= ((IInventory) tempTile).getInventoryStackLimit())
            			{
            				
	            			space[0] = i;
	            			space[1] = 1;
	            			space[2] = stack.stackSize;
	            			return space;
            			}
            			if(((IInventory) tempTile).getStackInSlot(i).stackSize + stack.stackSize > ((IInventory) tempTile).getInventoryStackLimit() && ((IInventory) tempTile).getStackInSlot(i).stackSize < ((IInventory) tempTile).getInventoryStackLimit())
            			{
            				
            				space[0] = i;
	            			space[1] = 1;
	            			space[2] = ((IInventory) tempTile).getInventoryStackLimit() - ((IInventory) tempTile).getStackInSlot(i).stackSize;
	            			return space;
            			}
            		}
            	}
            }
            
            
            }
            catch(Exception e)
            {
                    e.printStackTrace();
            }
  
            return null;
    }
}