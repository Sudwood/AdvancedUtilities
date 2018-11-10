package com.sudwood.advancedutilities.slot;

import com.sudwood.advancedutilities.items.AdvancedUtilitiesItems;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotBulletOrMagazineSlot extends Slot
{

	public SlotBulletOrMagazineSlot(IInventory par1iInventory, int par2, int par3, int par4) 
	{
		super(par1iInventory, par2, par3, par4);
		// TODO Auto-generated constructor stub
	}
	public boolean isItemValid(ItemStack stack)
    {
		if(stack.getItem() == AdvancedUtilitiesItems.bronzeBullet || stack.getItem() == AdvancedUtilitiesItems.bulletMagazine || stack.getItem() == AdvancedUtilitiesItems.steelBullet)
			return true;
        return false;
    }
	public int getSlotStackLimit()
    {
        return 64;
    }

}
