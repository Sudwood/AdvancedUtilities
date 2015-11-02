package com.sudwood.advancedutilities.slot;

import com.sudwood.advancedutilities.items.AdvancedUtilitiesItems;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotWoodenCrate extends Slot
{

	public SlotWoodenCrate(IInventory par1iInventory, int par2, int par3, int par4) 
	{
		super(par1iInventory, par2, par3, par4);
		// TODO Auto-generated constructor stub
	}
	public boolean isItemValid(ItemStack stack)
    {
		return true;
    }
	public int getSlotStackLimit()
    {
        return 4096;
    }

}
