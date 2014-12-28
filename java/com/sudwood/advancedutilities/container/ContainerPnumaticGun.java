package com.sudwood.advancedutilities.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.sudwood.advancedutilities.items.ItemBulletBE;
import com.sudwood.advancedutilities.items.ItemMagazine;
import com.sudwood.advancedutilities.slot.SlotBulletOrMagazineSlot;

public class ContainerPnumaticGun extends Container
{
	/** The Item Inventory for this Container */
	public final InventoryItem inventory;
	
	/** Using these will make transferStackInSlot easier to understand and implement
	* INV_START is the index of the first slot in the Player's Inventory, so our
	* InventoryItem's number of slots (e.g. 5 slots is array indices 0-4, so start at 5)
	* Notice how we don't have to remember how many slots we made? We can just use
	* InventoryItem.INV_SIZE and if we ever change it, the Container updates automatically. */
	private static final int INV_START = InventoryItem.INV_SIZE, INV_END = INV_START+26,
	HOTBAR_START = INV_END+1, HOTBAR_END = HOTBAR_START+8;
	
	// If you're planning to add armor slots, put those first like this:
	// ARMOR_START = InventoryItem.INV_SIZE, ARMOR_END = ARMOR_START+3,
	// INV_START = ARMOR_END+1, and then carry on like above.
	
	public ContainerPnumaticGun(EntityPlayer par1Player, InventoryPlayer inventoryPlayer, InventoryItem inventoryItem)
	{
		this.inventory = inventoryItem;
		
		int i;
		
		// ITEM INVENTORY - you'll need to adjust the slot locations to match your texture file
		// I have them set vertically in columns of 4 to the right of the player model
		for (i = 0; i < InventoryItem.INV_SIZE; ++i)
		{
			// You can make a custom Slot if you need different behavior,
			// such as only certain item types can be put into this slot
			// We made a custom slot to prevent our inventory-storing item
			// from being stored within itself, but if you want to allow that and
			// you followed my advice at the end of the above step, then you
			// could get away with using the vanilla Slot class
			this.addSlotToContainer(new SlotBulletOrMagazineSlot(this.inventory, i, 15+(18*i), 33));
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
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
	{
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(par2);
		
		if (slot != null && slot.getHasStack())
		{
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			
			// If item is in our custom Inventory or armor slot
			if (par2 < INV_START)
			{
				// try to place in player inventory / action bar
				if (!this.mergeItemStack(itemstack1, INV_START, HOTBAR_END + 1, true))
				{
					return null;
				}
				
				slot.onSlotChange(itemstack1, itemstack);
			}
			// Item is in inventory / hotbar, try to place in custom inventory or armor slots
			else
			{
				// Check that the item is the right type
				if (itemstack1.getItem() instanceof ItemBulletBE || itemstack1.getItem() instanceof ItemMagazine)
				{
					// Try to merge into your custom inventory slots
					// We use 'InventoryItem.INV_SIZE' instead of INV_START just in case
					// you also add armor or other custom slots
					if (!this.mergeItemStack(itemstack1, 0, InventoryItem.INV_SIZE, false))
					{
						return null;
					}
				}
				
				// item is in player's inventory, but not in action bar
				if (par2 >= INV_START && par2 < HOTBAR_START)
				{
					// place in action bar
					if (!this.mergeItemStack(itemstack1, HOTBAR_START, HOTBAR_END + 1, false))
					{
						return null;
					}
				}
				// item in action bar - place in player inventory
				else if (par2 >= HOTBAR_START && par2 < HOTBAR_END + 1)
				{
					if (!this.mergeItemStack(itemstack1, INV_START, INV_END + 1, false))
					{
						return null;
					}
				}
			}
			
			if (itemstack1.stackSize == 0)
			{
				slot.putStack((ItemStack) null);
			}
			else
			{
				slot.onSlotChanged();
			}
			
			if (itemstack1.stackSize == itemstack.stackSize)
			{
				return null;
			}
			
			slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
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