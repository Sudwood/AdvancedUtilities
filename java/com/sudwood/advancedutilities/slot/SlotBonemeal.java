package com.sudwood.advancedutilities.slot;

import com.sudwood.advancedutilities.HelperLibrary;
import com.sudwood.advancedutilities.items.AdvancedUtilitiesItems;

import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotBonemeal extends Slot
{

	public SlotBonemeal(IInventory par1iInventory, int par2, int par3, int par4) 
	{
		super(par1iInventory, par2, par3, par4);
		// TODO Auto-generated constructor stub
	}
	public boolean isItemValid(ItemStack stack)
    {
		if(HelperLibrary.areItemStacksSameItemAndDamage(stack, new ItemStack(Items.dye, 1, 15)))
			return true;
        return false;
    }
	public int getSlotStackLimit()
    {
        return 64;
    }

}
