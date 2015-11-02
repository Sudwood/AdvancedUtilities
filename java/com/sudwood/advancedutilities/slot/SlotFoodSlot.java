package com.sudwood.advancedutilities.slot;


import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

public class SlotFoodSlot extends Slot
{

	public SlotFoodSlot(IInventory par1iInventory, int par2, int par3, int par4) 
	{
		super(par1iInventory, par2, par3, par4);
		// TODO Auto-generated constructor stub
	}
	public boolean isItemValid(ItemStack stack)
    {
		if(stack.getItem() instanceof ItemFood)
			return true;
        return false;
    }
	public int getSlotStackLimit()
    {
        return 64;
    }

}
