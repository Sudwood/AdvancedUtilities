package com.sudwood.advancedutilities.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotForged extends Slot
{

	public SlotForged(IInventory par1iInventory, int par2, int par3, int par4) 
	{
		super(par1iInventory, par2, par3, par4);
		// TODO Auto-generated constructor stub
	}
	public boolean isItemValid(ItemStack par1ItemStack)
    {
        return false;
    }
}
