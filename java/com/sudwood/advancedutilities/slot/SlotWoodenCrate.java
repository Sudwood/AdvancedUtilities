package com.sudwood.advancedutilities.slot;

import com.sudwood.advancedutilities.tileentity.TileEntityWoodenCrate;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotWoodenCrate extends Slot
{
	TileEntityWoodenCrate tile;
	public SlotWoodenCrate(IInventory par1iInventory, int par2, int par3, int par4) 
	{
		super(par1iInventory, par2, par3, par4);
		tile = (TileEntityWoodenCrate) par1iInventory;
	}
	public boolean isItemValid(ItemStack stack)
    {
		if(tile.stackSize <= 0 && this.slotNumber == 0)
		return true;
		else if(tile.isItemValidForSlot(this.slotNumber, stack))
		{
			return true;
		}
		else
			return false;
    }
	public int getSlotStackLimit()
    {
        return 64;
    }

}
