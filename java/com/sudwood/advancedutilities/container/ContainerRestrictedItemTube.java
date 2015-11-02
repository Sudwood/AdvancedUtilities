package com.sudwood.advancedutilities.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.sudwood.advancedutilities.CrushRecipes;
import com.sudwood.advancedutilities.tileentity.TileEntityRestrictedItemTube;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerRestrictedItemTube extends Container
{
    private TileEntityRestrictedItemTube tileFurnace;
    private int lastTankAmount;
    private int lastProgressTime;

    public ContainerRestrictedItemTube(InventoryPlayer par1InventoryPlayer, TileEntityRestrictedItemTube tile)
    {
        this.tileFurnace = tile;
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
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(par2);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (par2 == 2)
            {
                if (!this.mergeItemStack(itemstack1, 3, 39, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (par2 != 1 && par2 != 0)
            {
                if (CrushRecipes.getCrushResult(itemstack1) != null)
                {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false))
                    {
                        return null;
                    }
                }
                
                else if (par2 >= 3 && par2 < 30)
                {
                    if (!this.mergeItemStack(itemstack1, 30, 39, false))
                    {
                        return null;
                    }
                }
                else if (par2 >= 30 && par2 < 39 && !this.mergeItemStack(itemstack1, 3, 30, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 3, 39, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
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
}