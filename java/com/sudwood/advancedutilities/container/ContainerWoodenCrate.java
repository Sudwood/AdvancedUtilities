package com.sudwood.advancedutilities.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.sudwood.advancedutilities.tileentity.TileEntityPortaChest;
import com.sudwood.advancedutilities.tileentity.TileEntityWoodenCrate;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import invtweaks.api.container.ChestContainer;

@ChestContainer(isLargeChest = true)
public class ContainerWoodenCrate extends Container
{
    private TileEntityWoodenCrate furnace;
    private int lastStackSize = 0;
    private int sizeInventory = 0;

    public ContainerWoodenCrate(InventoryPlayer par1InventoryPlayer, TileEntityWoodenCrate par2TileEntityFurnace)
    {
        this.furnace = par2TileEntityFurnace;
        this.sizeInventory = furnace.getSizeInventory();
        for(int i = 0; i < 14; i++)
        {
        this.addSlotToContainer(new Slot(par2TileEntityFurnace, i, 3+i*18, 5));
        }
        for(int i = 0; i < 14; i++)
        {
        	this.addSlotToContainer(new Slot(par2TileEntityFurnace, i+14, 3+i*18, 23));
        }
        for(int i = 0; i < 14; i++)
        {
        	this.addSlotToContainer(new Slot(par2TileEntityFurnace, i+28, 3+i*18, 41));
        }
        for(int i = 0; i < 14; i++)
        {
        	this.addSlotToContainer(new Slot(par2TileEntityFurnace, i+42, 3+i*18, 59));
        }
        this.addSlotToContainer(new Slot(par2TileEntityFurnace, 56, 219, 77));
        this.addSlotToContainer(new Slot(par2TileEntityFurnace, 57, 237, 77));
        this.addSlotToContainer(new Slot(par2TileEntityFurnace, 58, 219, 95));
        this.addSlotToContainer(new Slot(par2TileEntityFurnace, 59, 237, 95));
        this.addSlotToContainer(new Slot(par2TileEntityFurnace, 60, 219, 113));
        this.addSlotToContainer(new Slot(par2TileEntityFurnace, 61, 237, 113));
        this.addSlotToContainer(new Slot(par2TileEntityFurnace, 62, 219, 131));
        this.addSlotToContainer(new Slot(par2TileEntityFurnace, 63, 237, 131));
        int var3;

        for (var3 = 0; var3 < 3; ++var3)
        {
            for (int var4 = 0; var4 < 9; ++var4)
            {
                this.addSlotToContainer(new Slot(par1InventoryPlayer, var4 + var3 * 9 + 9, 48 + var4 * 18, 83 + var3 * 18));
            }
        }

        for (var3 = 0; var3 < 9; ++var3)
        {
            this.addSlotToContainer(new Slot(par1InventoryPlayer, var3, 48 + var3 * 18, 141));
        }
    }

    public void addCraftingToCrafters(ICrafting par1ICrafting)
    {
        super.addCraftingToCrafters(par1ICrafting);
        par1ICrafting.sendProgressBarUpdate(this, 0, this.furnace.stackSize);
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

            if (this.lastStackSize != this.furnace.stackSize)
            {
                var2.sendProgressBarUpdate(this, 0, this.furnace.stackSize);
            }
            
        }

    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2)
    {
        if (par1 == 0)
        {
        	this.furnace.stackSize = par2;
        }

    }

    public boolean canInteractWith(EntityPlayer par1EntityPlayer)
    {
        return this.furnace.isUseableByPlayer(par1EntityPlayer);
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
             else if (!furnace.isItemValidForSlot(i, slot.getStack()))
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
    @ChestContainer.RowSizeCallback
    public int getNumColumns() 
    {
        return 14;
  	}
}
