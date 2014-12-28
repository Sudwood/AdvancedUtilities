package com.sudwood.advancedutilities.slot;

import com.sudwood.advancedutilities.items.AdvancedUtilitiesItems;

import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SlotToolRod extends Slot
{

	public SlotToolRod(IInventory par1iInventory, int par2, int par3, int par4) 
	{
		super(par1iInventory, par2, par3, par4);
		// TODO Auto-generated constructor stub
	}
	public boolean isItemValid(ItemStack stack)
    {
		if(stack.isItemEqual(new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 0)) || stack.isItemEqual(new ItemStack(AdvancedUtilitiesItems.itemCasing, 1, 2)) || stack.isItemEqual(new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 1)) || stack.getItem() == Items.stick || stack.getItem() == AdvancedUtilitiesItems.ingotBronze || stack.getItem() == Items.iron_ingot)
			return true;
        return false;
    }
	public int getSlotStackLimit()
    {
        return 64;
    }

}
