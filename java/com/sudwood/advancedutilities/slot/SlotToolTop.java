package com.sudwood.advancedutilities.slot;

import com.sudwood.advancedutilities.items.AdvancedUtilitiesItems;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotToolTop extends Slot
{

	public SlotToolTop(IInventory par1iInventory, int par2, int par3, int par4) {
		super(par1iInventory, par2, par3, par4);
		// TODO Auto-generated constructor stub
	}
	
	public boolean isItemValid(ItemStack stack)
    {
		if(stack.getItem() == AdvancedUtilitiesItems.toolBE|| stack.getItem() == AdvancedUtilitiesItems.pnumaticGun || stack.getItem() == AdvancedUtilitiesItems.jackHammer ||stack.isItemEqual(new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 2)) || stack.isItemEqual(new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 3)) || stack.isItemEqual(new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 4)) || stack.isItemEqual(new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 5)) || stack.isItemEqual(new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 6)) || stack.isItemEqual(new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 7)) || stack.isItemEqual(new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 8)) || stack.isItemEqual(new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 9)))
			return true;
        return false;
    }
	
	public int getSlotStackLimit()
    {
        return 1;
    }

}
