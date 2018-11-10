package com.sudwood.advancedutilities;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

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
				if(((ISidedInventory)tile).getStackInSlot(slots[i]) != null  && ((ISidedInventory)tile).canExtractItem(i, ((ISidedInventory)tile).getStackInSlot(slots[i]), 0))
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
						if(((ISidedInventory)tile).getStackInSlot(slots[i]) != null && HelperLibrary.areItemStacksSameItemAndDamageAndNBT(inventory[ix],((ISidedInventory)tile).getStackInSlot(ix)))
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
						if(((IInventory)tile).getStackInSlot(i) != null && HelperLibrary.areItemStacksSameItemAndDamageAndNBT(inventory[ix],((IInventory)tile).getStackInSlot(ix)))
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
	 * @param the side that is being pulled from
	 * @return
	 */
	public static int[] getFirstStackThatFits(int x, int y, int z, ItemStack[] inventory, World worldObj, int numTransfered, int side)
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
			int[] slots =  ((ISidedInventory) tile).getAccessibleSlotsFromSide(side);
			if(tile instanceof TileEntityFurnace)
				slots = new int[]{2};
			for(int i = 0; i < slots.length; i++)
			{
				if(((ISidedInventory)tile).getStackInSlot(slots[i]) != null  && ((ISidedInventory)tile).canExtractItem(i, ((ISidedInventory)tile).getStackInSlot(slots[i]), side))
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
						if(((ISidedInventory)tile).getStackInSlot(slots[i]) != null && HelperLibrary.areItemStacksSameItemAndDamageAndNBT(inventory[ix],((ISidedInventory)tile).getStackInSlot(ix)))
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
						if(((IInventory)tile).getStackInSlot(i) != null && HelperLibrary.areItemStacksSameItemAndDamageAndNBT(inventory[ix],((IInventory)tile).getStackInSlot(ix)))
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
				if(((ISidedInventory)tile).getStackInSlot(slots[i]) != null && ((ISidedInventory)tile).canExtractItem(i, ((ISidedInventory)tile).getStackInSlot(slots[i]), 0))
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
						if(((ISidedInventory)tile).getStackInSlot(slots[i]) != null && inventory[ix] != null && HelperLibrary.areItemStacksSameItemAndDamageAndNBT(inventory[ix],((ISidedInventory)tile).getStackInSlot(ix)))
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
						if(((IInventory)tile).getStackInSlot(i) != null && inventory[ix]!=null && HelperLibrary.areItemStacksSameItemAndDamageAndNBT(inventory[ix],((IInventory)tile).getStackInSlot(ix)) && tileen.isItemValidForSlot(ix, ((IInventory)tile).getStackInSlot(i)))
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
	 * @param the side that is being pulled from
	 * @return
	 */
	public static int[] getFirstStackThatFits(int x, int y, int z, ItemStack[] inventory, World worldObj, int numTransfered, IInventory tileen, int side)
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
			int[] slots =  ((ISidedInventory) tile).getAccessibleSlotsFromSide(side);
			if(tile instanceof TileEntityFurnace)
				slots = new int[]{2};
			for(int i = 0; i < slots.length; i++)
			{
				if(((ISidedInventory)tile).getStackInSlot(slots[i]) != null && ((ISidedInventory)tile).canExtractItem(i, ((ISidedInventory)tile).getStackInSlot(slots[i]), side))
				{
					for(int ix = 0; ix < inventory.length; ix++)
					{
						if(inventory[ix] == null && tileen.isItemValidForSlot(ix, ((ISidedInventory)tile).getStackInSlot(slots[i])))
						{
							slot[0] = slots[i];
							slot[1] = 0;
							slot[2] = ix;
							return slot;
						}
						if(((ISidedInventory)tile).getStackInSlot(slots[i]) != null && inventory[ix] != null && HelperLibrary.areItemStacksSameItemAndDamageAndNBT(inventory[ix],((ISidedInventory)tile).getStackInSlot(ix)) && tileen.isItemValidForSlot(ix, ((ISidedInventory)tile).getStackInSlot(slots[i])))
						{
							if(((ISidedInventory)tile).getStackInSlot(slots[i]).stackSize + inventory[ix].stackSize < ((ISidedInventory)tile).getInventoryStackLimit() )
							{
								slot[0] = slots[i];
								slot[1] = 1;
								slot[2] = ix;
								slot[3] = 0;
								return slot;
							}
							else if(((ISidedInventory)tile).getStackInSlot(slots[i]).stackSize + inventory[ix].stackSize > ((ISidedInventory)tile).getInventoryStackLimit())
							{
								slot[0] = slots[i];
								slot[1] = 1;
								slot[2] = ix;
								slot[3] = ((ISidedInventory)tile).getInventoryStackLimit()-inventory[ix].stackSize;
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
						if(((IInventory)tile).getStackInSlot(i) != null && inventory[ix]!=null && HelperLibrary.areItemStacksSameItemAndDamageAndNBT(inventory[ix],((IInventory)tile).getStackInSlot(ix)) && tileen.isItemValidForSlot(ix, ((IInventory)tile).getStackInSlot(i)))
						{
							if(numTransfered + inventory[ix].stackSize < ((IInventory)tile).getInventoryStackLimit())
							{
								slot[0] = i;
								slot[1] = 1;
								slot[2] = ix;
								slot[3] = 0;
								return slot;
							}
							else if(numTransfered + inventory[ix].stackSize > ((IInventory)tile).getInventoryStackLimit() && inventory[ix].stackSize!=((IInventory)tile).getInventoryStackLimit())
							{
								slot[0] = i;
								slot[1] = 1;
								slot[2] = ix;
								slot[3] = ((IInventory)tile).getInventoryStackLimit()-inventory[ix].stackSize;
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
            		if(((ISidedInventory) tempTile).canInsertItem(slots[i], stack, sideAttached) && ((ISidedInventory) tempTile).getStackInSlot(slots[i]) == null)
            		{
            			space[0] = slots[i];
            			space[1] = 0;
            			return space;
            		}
            		if(((ISidedInventory) tempTile).canInsertItem(slots[i], stack, sideAttached) && HelperLibrary.areItemStacksSameItemAndDamageAndNBT(((ISidedInventory) tempTile).getStackInSlot(slots[i]), stack))
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
            		if(((IInventory) tempTile).isItemValidForSlot(i, stack) && HelperLibrary.areItemStacksSameItemAndDamageAndNBT(((IInventory) tempTile).getStackInSlot(i), stack))
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
    
    public static boolean canFitInInventory(ItemStack stack, IInventory inv)
    {
    	for(int i = 0; i < inv.getSizeInventory(); i++)
    	{
    		if(inv.getStackInSlot(i) == null && inv.isItemValidForSlot(i, stack))
    		{
    			return true;
    		}
    		else
    		{
    			if(HelperLibrary.areItemStacksSameItemAndDamageAndNBT(stack, inv.getStackInSlot(i)) && inv.isItemValidForSlot(i, stack))
    			{
    				if(stack.stackSize+inv.getStackInSlot(i).stackSize <= inv.getInventoryStackLimit())
    				{
    					return true;
    				}
    			}
    		}
    	}
    	return false;
    }
    
    public static int canFitInventory(ItemStack stack, IInventory inv)
    {
    	for(int i = 0; i < inv.getSizeInventory(); i++)
    	{
    		if(inv.getStackInSlot(i) == null && inv.isItemValidForSlot(i, stack))
    		{
    			return i;
    		}
    		else
    		{
    			if(HelperLibrary.areItemStacksSameItemAndDamageAndNBT(stack, inv.getStackInSlot(i)) && inv.isItemValidForSlot(i, stack))
    			{
    				if(stack.stackSize+inv.getStackInSlot(i).stackSize <= inv.getInventoryStackLimit())
    				{
    					return i;
    				}
    			}
    		}
    	}
    	return -1;
    }
    
    public static int canFitInventory(ItemStack stack, IInventory inv, int[] slots)
    {
    	for(int i = 0; i < slots.length; i++)
    	{
    		if(inv.getStackInSlot(slots[i]) == null && inv.isItemValidForSlot(slots[i], stack))
    		{
    			return slots[i];
    		}
    		else
    		{
    			if(HelperLibrary.areItemStacksSameItemAndDamageAndNBT(stack, inv.getStackInSlot(slots[i])) && inv.isItemValidForSlot(slots[i], stack))
    			{
    				if(stack.stackSize+inv.getStackInSlot(slots[i]).stackSize <= inv.getInventoryStackLimit())
    				{
    					return slots[i];
    				}
    			}
    		}
    	}
    	return -1;
    }
    
    public static boolean pullItem(IInventory inv, int x, int y, int z, ForgeDirection facing, World world, int numTransfered)
    {
    	TileEntity tile = world.getTileEntity(x+facing.offsetX, y+facing.offsetY, z+facing.offsetZ);
    	if(tile!=null)
    	{
    		int face = 0;
    		int oposite = 0;
    		if(facing == ForgeDirection.DOWN)
    		{
    			face = 0;
    			oposite = 1;
    		}
    		if(facing == ForgeDirection.UP)
    		{
    			face = 1;
    			oposite = 0;
    		}
    		if(facing == ForgeDirection.NORTH)
    		{
    			face = 2;
    			oposite = 3;
    		}
    		if(facing == ForgeDirection.SOUTH)
    		{
    			face = 3;
    			oposite = 2;
    		}
    		if(facing == ForgeDirection.WEST)
    		{
    			face = 4;
    			oposite = 5;
    		}
    		if(facing == ForgeDirection.EAST)
    		{
    			face = 5;
    			oposite = 4;
    		}
    		if(tile instanceof ISidedInventory)
    		{
    			ISidedInventory pull = (ISidedInventory) tile;
    			int[] slots = pull.getAccessibleSlotsFromSide(face);
    			for(int i = 0; i<slots.length; i++)
    			{
    				if(pull.getStackInSlot(slots[i])!=null)
    				{
	    				ItemStack item = pull.getStackInSlot(slots[i]).copy();
	    				if(item.stackSize > numTransfered)
	    				{
	    					item.stackSize = numTransfered;
	    				}
	    				int can = canFitInventory(item, inv);
	    				if(can>=0 && pull.canExtractItem(slots[i], item, face))
	    				{
	    					ItemStack temp = pull.getStackInSlot(slots[i]);
	    					if(inv.getStackInSlot(can) == null)
	    					{
	    						inv.setInventorySlotContents(can, item);
	    					}
	    					else
	    					{
	    						ItemStack temp2 = inv.getStackInSlot(can);
	    						temp2.stackSize+=item.stackSize;
	    						inv.setInventorySlotContents(can, temp2);
	    					}
	    					temp.stackSize-=item.stackSize;
	    					if(temp.stackSize<=0)
	    					{
	    						temp = null;
	    					}
	    					pull.setInventorySlotContents(slots[i], temp);
	    					return true;
	    				}
    				}
    					
    			}
    			
    		}
    		else if(tile instanceof IInventory)
    		{
    			IInventory pull = (IInventory) tile;
    			for(int i = 0; i < pull.getSizeInventory(); i++)
    			{
    				if(pull.getStackInSlot(i)!=null)
    				{
	    				ItemStack item = pull.getStackInSlot(i).copy();
	    				if(item.stackSize > numTransfered)
	    				{
	    					item.stackSize = numTransfered;
	    				}
	    				int can = canFitInventory(item, inv);
	    				if(can>=0)
	    				{
	    					ItemStack temp = pull.getStackInSlot(i);
	    					if(inv.getStackInSlot(can) == null)
	    					{
	    						inv.setInventorySlotContents(can, item);
	    					}
	    					else
	    					{
	    						ItemStack temp2 = inv.getStackInSlot(can);
	    						temp2.stackSize+=item.stackSize;
	    						inv.setInventorySlotContents(can, temp2);
	    					}
	    					temp.stackSize-=item.stackSize;
	    					if(temp.stackSize<=0)
	    					{
	    						temp = null;
	    					}
	    					pull.setInventorySlotContents(i, temp);
	    					return true;
	    				}
    				}
    			}
    		}
    	}
    	return false;
    }
    
    public static boolean pushItem(IInventory inv, int x, int y, int z, ForgeDirection facing, World world, int numTransfered)
    {
    	TileEntity tile = world.getTileEntity(x+facing.getOpposite().offsetX, y+facing.getOpposite().offsetY, z+facing.getOpposite().offsetZ);
    	if(tile!=null)
    	{
    		int face = 0;
    		int oposite = 0;
    		if(facing == ForgeDirection.DOWN)
    		{
    			face = 0;
    			oposite = 1;
    		}
    		if(facing == ForgeDirection.UP)
    		{
    			face = 1;
    			oposite = 0;
    		}
    		if(facing == ForgeDirection.NORTH)
    		{
    			face = 2;
    			oposite = 3;
    		}
    		if(facing == ForgeDirection.SOUTH)
    		{
    			face = 3;
    			oposite = 2;
    		}
    		if(facing == ForgeDirection.WEST)
    		{
    			face = 4;
    			oposite = 5;
    		}
    		if(facing == ForgeDirection.EAST)
    		{
    			face = 5;
    			oposite = 4;
    		}
    		if(tile instanceof ISidedInventory)
    		{
    			ISidedInventory push = (ISidedInventory) tile;
    			int[] slots = push.getAccessibleSlotsFromSide(oposite);
    				for(int ix = 0; ix<inv.getSizeInventory(); ix++)
    				{
    					if(inv.getStackInSlot(ix)!=null)
    					{
	    					ItemStack item = inv.getStackInSlot(ix).copy();
	    					if(item.stackSize > numTransfered)
	        				{
	        					item.stackSize = numTransfered;
	        				}
	    					int can = canFitInventory(item, push, slots);
	    					if(can>=0)
	    					{
	    						if(push.getStackInSlot(can) == null)
	    						{
	    							push.setInventorySlotContents(can, item);
	    						}
	    						else
	    						{
	    							ItemStack temp = push.getStackInSlot(can).copy();
	    							temp.stackSize+=item.stackSize;
	    							push.setInventorySlotContents(can, temp);
	    						}
	    						ItemStack temp = inv.getStackInSlot(ix).copy();
	    						temp.stackSize-=item.stackSize;
	    						if(temp.stackSize <=0)
	    						{
	    							temp = null;
	    						}
	    						inv.setInventorySlotContents(ix, temp);
	    						return true;
	    					}
    					}
    					
    				}
    				
    					
    			
    			
    		}
    		else if(tile instanceof IInventory)
    		{
    			IInventory push = (IInventory) tile;
    			for(int ix = 0; ix<inv.getSizeInventory(); ix++)
				{
    				if(inv.getStackInSlot(ix)!=null)
					{
						ItemStack item = inv.getStackInSlot(ix).copy();
						if(item.stackSize > numTransfered)
	    				{
	    					item.stackSize = numTransfered;
	    				}
						int can = canFitInventory(item, push);
						if(can>=0)
						{
							if(push.getStackInSlot(can) == null)
							{
								push.setInventorySlotContents(can, item);
							}
							else
							{
								ItemStack temp = push.getStackInSlot(can).copy();
								temp.stackSize+=item.stackSize;
								push.setInventorySlotContents(can, temp);
							}
							ItemStack temp = inv.getStackInSlot(ix).copy();
    						temp.stackSize-=item.stackSize;
    						if(temp.stackSize <=0)
    						{
    							temp = null;
    						}
    						inv.setInventorySlotContents(ix, temp);
							return true;
						}
					}
					
				}
    		}
    	}
    	return false;
    }
    
    public static boolean pushItemRationed(IInventory inv, int x, int y, int z, ForgeDirection facing, World world, int numTransfered)
    {
    	TileEntity tile = world.getTileEntity(x+facing.getOpposite().offsetX, y+facing.getOpposite().offsetY, z+facing.getOpposite().offsetZ);
    	if(tile!=null)
    	{
    		int face = 0;
    		int oposite = 0;
    		if(facing == ForgeDirection.DOWN)
    		{
    			face = 0;
    			oposite = 1;
    		}
    		if(facing == ForgeDirection.UP)
    		{
    			face = 1;
    			oposite = 0;
    		}
    		if(facing == ForgeDirection.NORTH)
    		{
    			face = 2;
    			oposite = 3;
    		}
    		if(facing == ForgeDirection.SOUTH)
    		{
    			face = 3;
    			oposite = 2;
    		}
    		if(facing == ForgeDirection.WEST)
    		{
    			face = 4;
    			oposite = 5;
    		}
    		if(facing == ForgeDirection.EAST)
    		{
    			face = 5;
    			oposite = 4;
    		}
    		if(tile instanceof ISidedInventory)
    		{
    			ISidedInventory push = (ISidedInventory) tile;
    			int[] slots = push.getAccessibleSlotsFromSide(oposite);
    				for(int ix = 0; ix<inv.getSizeInventory(); ix++)
    				{
    					if(inv.getStackInSlot(ix)!=null)
    					{
	    					ItemStack item = inv.getStackInSlot(ix).copy();
	    					if(item.stackSize > numTransfered)
	        				{
	        					item.stackSize = numTransfered;
	        				}
	    					int can = canFitInventoryRationed(item, push, slots);
	    					if(can==0 || can+numTransfered <= item.getMaxStackSize())
	    					{
	    						int slot = canFitInventory(item,push,slots);
	    						if(push.getStackInSlot(slot) == null)
	    						{
	    							push.setInventorySlotContents(slot, item);
	    						}
	    						else
	    						{
	    							ItemStack temp = push.getStackInSlot(slot).copy();
	    							temp.stackSize+=item.stackSize;
	    							push.setInventorySlotContents(slot, temp);
	    						}
	    						ItemStack temp = inv.getStackInSlot(ix).copy();
	    						temp.stackSize-=item.stackSize;
	    						if(temp.stackSize <=0)
	    						{
	    							temp = null;
	    						}
	    						inv.setInventorySlotContents(ix, temp);
	    						return true;
	    					}
    					}
    					
    				}
    				
    					
    			
    			
    		}
    		else if(tile instanceof IInventory)
    		{
    			IInventory push = (IInventory) tile;
    			for(int ix = 0; ix<inv.getSizeInventory(); ix++)
				{
    				if(inv.getStackInSlot(ix)!=null)
					{
						ItemStack item = inv.getStackInSlot(ix).copy();
						if(item.stackSize > numTransfered)
	    				{
	    					item.stackSize = numTransfered;
	    				}
						int can = canFitInInventoryRationed(item, push);
						if(can==0 || can+numTransfered <= item.getMaxStackSize())
						{
							int slot = canFitInventory(item,push);
							if(push.getStackInSlot(slot) == null)
							{
								push.setInventorySlotContents(slot, item);
							}
							else
							{
								ItemStack temp = push.getStackInSlot(slot).copy();
								temp.stackSize+=item.stackSize;
								push.setInventorySlotContents(slot, temp);
							}
							ItemStack temp = inv.getStackInSlot(ix).copy();
    						temp.stackSize-=item.stackSize;
    						if(temp.stackSize <=0)
    						{
    							temp = null;
    						}
    						inv.setInventorySlotContents(ix, temp);
							return true;
						}
					}
					
				}
    		}
    	}
    	return false;
    }
    
    public static int canFitInInventoryRationed(ItemStack stack, IInventory inv)
    {
    	boolean result =false;
    	int count = 0;
    	for(int i = 0; i < inv.getSizeInventory(); i++)
    	{
    			if(HelperLibrary.areItemStacksSameItemAndDamageAndNBT(stack, inv.getStackInSlot(i)) && inv.isItemValidForSlot(i, stack))
    			{
    					count+=inv.getStackInSlot(i).stackSize;
    			}
    		
    	}
    	return count;
    }
    
    public static int canFitInventoryRationed(ItemStack stack, IInventory inv, int[] slots)
    {
    	int count = 0;
    	for(int i = 0; i < slots.length; i++)
    	{
    			if(HelperLibrary.areItemStacksSameItemAndDamageAndNBT(stack, inv.getStackInSlot(slots[i])) && inv.isItemValidForSlot(slots[i], stack))
    			{
    				count+=inv.getStackInSlot(slots[i]).stackSize;
    			}
    	}
    	return count;
    }
    
}