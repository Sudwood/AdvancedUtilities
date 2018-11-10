package com.sudwood.advancedutilities.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;


public class ContainerVoidRing extends Container
{
	/** The Item Inventory for this Container */
	public final InventoryVoidRing inventory;
	private int sizeInventory = 0;
	
	public ContainerVoidRing(InventoryPlayer inventoryPlayer, InventoryVoidRing inventoryItem)
	{
		this.inventory = inventoryItem;
		sizeInventory = inventory.getSizeInventory();
		byte b0 = 51;
        int i;

        for (i = 0; i < inventoryItem.getSizeInventory(); ++i)
        {
            this.addSlotToContainer(new Slot(inventoryItem, i, 44 + i * 18, 20));
        }

        for (i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, i * 18 + b0));
            }
        }

        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 58 + b0));
        }
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		// be sure to return the inventory's isUseableByPlayer method
		// if you defined special behavior there:
		return inventory.isUseableByPlayer(player);
	}
	
	/**
	* Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
	* Only real change we make to this is to set needsUpdate to true at the end
	*/
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int i)
	{
		 ItemStack itemstack = null;
         Slot slot = (Slot) inventorySlots.get(i);
         if (slot != null && slot.getHasStack())
         {
             ItemStack itemstack1 = slot.getStack();
             itemstack = itemstack1.copy();
             if (i < sizeInventory)
             {
                 if (!mergeItemStack(itemstack1, sizeInventory, inventorySlots.size(), true))
                 {
                     return null;
                 }
             }
             else if (!inventory.isItemValidForSlot(i, slot.getStack()))
             {
                 return null;
             }
             else if (!mergeItemStack(itemstack1, 0, sizeInventory, false))
             {
                 return null;
             }
             if (itemstack1.stackSize == 0)
             {
                 slot.putStack(null);
             }
             else
             {
                 slot.onSlotChanged();
             }
         }
 return itemstack;
	}
		
		// NOTE: The following is necessary if you want to prevent the player from moving the item while the
		// inventory is open; if you don't and the player moves the item, the inventory will not be able to save properly
		@Override
		public ItemStack slotClick(int slot, int button, int flag, EntityPlayer player)
		{
			// this will prevent the player from interacting with the item that opened the inventory:
			if (slot >= 0 && getSlot(slot) != null && getSlot(slot).getStack() == player.getHeldItem()) 
			{
				return null;
			}
			return super.slotClick(slot, button, flag, player);
		}
		 
}