package com.sudwood.advancedutilities.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.sudwood.advancedutilities.slot.SlotBonemeal;
import com.sudwood.advancedutilities.tileentity.TileEntityGrowerBlock;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerGrowerBlock extends Container
{
    private final IInventory tile;
    private static final String __OBFID = "CL_00001750";
    private int lastBonemeal = 0;
    private int sizeInventory = 0;
    public ContainerGrowerBlock(InventoryPlayer par1InventoryPlayer, IInventory par2IInventory)
    {
        this.tile = par2IInventory;
        sizeInventory = tile.getSizeInventory();
        par2IInventory.openInventory();
        byte b0 = 51;

            this.addSlotToContainer(new SlotBonemeal(par2IInventory, 0, 56 , 35));

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
        return this.tile.isUseableByPlayer(par1EntityPlayer);
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
             else if (!tile.isItemValidForSlot(i, slot.getStack()))
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
        this.tile.closeInventory();
    }
    
    public void addCraftingToCrafters(ICrafting par1ICrafting)
    {
        super.addCraftingToCrafters(par1ICrafting);
        par1ICrafting.sendProgressBarUpdate(this, 0, ((TileEntityGrowerBlock)tile).storedBonemeal);
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int var1 = 0; var1 < this.crafters.size(); ++var1)
        {
            ICrafting var2 = (ICrafting)this.crafters.get(var1);

            if (this.lastBonemeal != ((TileEntityGrowerBlock)tile).storedBonemeal)
            {
                var2.sendProgressBarUpdate(this, 0, ((TileEntityGrowerBlock)tile).storedBonemeal);
            }

        }

        this.lastBonemeal = ((TileEntityGrowerBlock)tile).storedBonemeal;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2)
    {
        if (par1 == 0)
        {
        	((TileEntityGrowerBlock)tile).storedBonemeal = par2;
        }

    }
}