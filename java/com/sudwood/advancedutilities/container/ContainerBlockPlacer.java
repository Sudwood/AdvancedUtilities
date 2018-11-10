package com.sudwood.advancedutilities.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerBlockPlacer extends Container
{
    private final IInventory field_94538_a;
    private static final String __OBFID = "CL_00001750";
    private int sizeInventory = 0;
    public ContainerBlockPlacer(InventoryPlayer par1InventoryPlayer, IInventory par2IInventory)
    {
    	
        this.field_94538_a = par2IInventory;
        sizeInventory = par2IInventory.getSizeInventory();
        par2IInventory.openInventory();
        byte b0 = 51;

            this.addSlotToContainer(new Slot(par2IInventory, 0, 62 , 16));
            this.addSlotToContainer(new Slot(par2IInventory, 1, 80 , 16));
            this.addSlotToContainer(new Slot(par2IInventory, 2, 98 , 16));
            this.addSlotToContainer(new Slot(par2IInventory, 3, 62 , 34));
            this.addSlotToContainer(new Slot(par2IInventory, 4, 80 , 34));
            this.addSlotToContainer(new Slot(par2IInventory, 5, 98 , 34));
            this.addSlotToContainer(new Slot(par2IInventory, 6, 62 , 52));
            this.addSlotToContainer(new Slot(par2IInventory, 7, 80 , 52));
            this.addSlotToContainer(new Slot(par2IInventory, 8, 98 , 52));
            // 62 16 +18X +18Y

            int i;

            for (i = 0; i < 3; ++i)
            {
                for (int j = 0; j < 9; ++j)
                {
                    this.addSlotToContainer(new Slot(par1InventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
                }
            }

            for (i = 0; i < 9; ++i)
            {
                this.addSlotToContainer(new Slot(par1InventoryPlayer, i, 8 + i * 18, 142));
            }
    }

    public boolean canInteractWith(EntityPlayer par1EntityPlayer)
    {
        return this.field_94538_a.isUseableByPlayer(par1EntityPlayer);
    }

    /**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
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
             else if (!field_94538_a.isItemValidForSlot(i, slot.getStack()))
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

    /**
     * Called when the container is closed.
     */
    public void onContainerClosed(EntityPlayer par1EntityPlayer)
    {
        super.onContainerClosed(par1EntityPlayer);
        this.field_94538_a.closeInventory();
    }
}