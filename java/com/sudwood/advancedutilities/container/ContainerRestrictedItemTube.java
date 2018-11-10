package com.sudwood.advancedutilities.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.sudwood.advancedutilities.recipes.CrushRecipes;
import com.sudwood.advancedutilities.tileentity.TileEntityRestrictedItemTube;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerRestrictedItemTube extends Container
{
    private TileEntityRestrictedItemTube tileFurnace;
    private int lastTankAmount;
    private int lastProgressTime;
    private int sizeInventory = 0;

    public ContainerRestrictedItemTube(InventoryPlayer par1InventoryPlayer, TileEntityRestrictedItemTube tile)
    {
        this.tileFurnace = tile;
        sizeInventory = tileFurnace.getSizeInventory();
        this.addSlotToContainer(new Slot(tile, 0, 10 + 0 * 18, 34));
        this.addSlotToContainer(new Slot(tile, 1, 10 + 1 * 18, 34));
        this.addSlotToContainer(new Slot(tile, 2, 10 + 2 * 18, 34));
        this.addSlotToContainer(new Slot(tile, 3, 10 + 3 * 18, 34));
        this.addSlotToContainer(new Slot(tile, 4, 10 + 4 * 18, 34));
        
        
        this.addSlotToContainer(new Slot(tile, 5, 109, 17));
        this.addSlotToContainer(new Slot(tile, 6, 109, 35));
        this.addSlotToContainer(new Slot(tile, 7, 109, 53));
        this.addSlotToContainer(new Slot(tile, 8, 127, 17));
        this.addSlotToContainer(new Slot(tile, 9, 127, 35));
        this.addSlotToContainer(new Slot(tile, 10, 127, 53));
        this.addSlotToContainer(new Slot(tile, 11, 145, 17));
        this.addSlotToContainer(new Slot(tile, 12, 145, 35));
        this.addSlotToContainer(new Slot(tile, 13, 145, 53));
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

    public void addCraftingToCrafters(ICrafting par1ICrafting)
    {
        super.addCraftingToCrafters(par1ICrafting);
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2)
    {
    }

    public boolean canInteractWith(EntityPlayer par1EntityPlayer)
    {
        return this.tileFurnace.isUseableByPlayer(par1EntityPlayer);
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
             else if (!tileFurnace.isItemValidForSlot(i, slot.getStack()))
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
}