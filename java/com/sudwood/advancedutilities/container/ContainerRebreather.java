package com.sudwood.advancedutilities.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.sudwood.advancedutilities.items.ItemBulletBE;
import com.sudwood.advancedutilities.slot.SlotFoodSlot;

public class ContainerRebreather extends Container
{
	/** The Item Inventory for this Container */
	public final RebreatherInv inventory;
	
	private int sizeInventory = 0;
	public ContainerRebreather(EntityPlayer par1Player, InventoryPlayer inventoryPlayer, RebreatherInv RebreatherInv)
	{
		this.inventory = RebreatherInv;
		sizeInventory = inventory.getSizeInventory();
		int i;
		
		// ITEM INVENTORY - you'll need to adjust the slot locations to match your texture file
		// I have them set vertically in columns of 4 to the right of the player model
		for (i = 0; i < RebreatherInv.INV_SIZE/2; ++i)
		{
			this.addSlotToContainer(new SlotFoodSlot(this.inventory, i, 18, 5+18*i));
		}
		for (i = 0; i < RebreatherInv.INV_SIZE/2; ++i)
		{
			this.addSlotToContainer(new Slot(this.inventory, i+4, 142, 5+20*i));
		}
		// If you want, you can add ARMOR SLOTS here as well, but you need to
		// make a public version of SlotArmor. I won't be doing that in this tutorial.
		/*
		for (i = 0; i < 4; ++i)
		{
		// These are the standard positions for survival inventory layout
		this.addSlotToContainer(new SlotArmor(this.player, inventoryPlayer, inventoryPlayer.getSizeInventory() - 1 - i, 8, 8 + i * 18, i));
		}
		*/
		
		// PLAYER INVENTORY - uses default locations for standard inventory texture file
		for (i = 0; i < 3; ++i)
		{
			for (int j = 0; j < 9; ++j)
			{
				this.addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}
		
		// PLAYER ACTION BAR - uses default locations for standard action bar texture file
		for (i = 0; i < 9; ++i)
		{
			this.addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
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