package com.sudwood.advancedutilities.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import com.sudwood.advancedutilities.slot.SlotForged;
import com.sudwood.advancedutilities.tileentity.TileEntitySteamSmeltry;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerSteamSmeltry extends Container
{
    private TileEntitySteamSmeltry tileFurnace;
    private int lastTankAmount;
    private int lastProgressTime;
    private int sizeInventory = 0;
    private int lastSpeed;
    public ContainerSteamSmeltry(InventoryPlayer par1InventoryPlayer, TileEntitySteamSmeltry par2TileEntitySteamBoiler)
    {
        this.tileFurnace = par2TileEntitySteamBoiler;
        sizeInventory = tileFurnace.getSizeInventory();
        this.addSlotToContainer(new Slot(par2TileEntitySteamBoiler, 0, 81, 7));
        this.addSlotToContainer(new Slot(par2TileEntitySteamBoiler, 1, 81, 30));
        this.addSlotToContainer(new SlotForged(par2TileEntitySteamBoiler, 2, 81, 57));
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
        par1ICrafting.sendProgressBarUpdate(this, 2, this.tileFurnace.progressTime);
        par1ICrafting.sendProgressBarUpdate(this, 3, this.tileFurnace.getTankAmount());
        par1ICrafting.sendProgressBarUpdate(this, 4, this.tileFurnace.getSpeed());
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i)
        {
            ICrafting icrafting = (ICrafting)this.crafters.get(i);
            
            if(this.lastProgressTime != this.tileFurnace.progressTime)
            {
            	icrafting.sendProgressBarUpdate(this, 2, this.tileFurnace.progressTime);
            }
            
            if(this.lastTankAmount != this.tileFurnace.tank.getFluidAmount())
            {
            	icrafting.sendProgressBarUpdate(this, 3, this.tileFurnace.tank.getFluidAmount());
            }
            if(this.lastSpeed != this.tileFurnace.getSpeed())
            {
            	icrafting.sendProgressBarUpdate(this, 4, this.tileFurnace.getSpeed());
            }
        }

        this.lastProgressTime = this.tileFurnace.progressTime;
        this.lastTankAmount = this.tileFurnace.tank.getFluidAmount();
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2)
    {
    	if(par1 == 2)
        {
    		this.tileFurnace.progressTime = par2;
        }
        if(par1 == 3)
        {
        	this.tileFurnace.tank.setFluid(new FluidStack(FluidRegistry.getFluid("steam"), par2));
        }
        if(par1 == 4)
        {
    		this.tileFurnace.setSpeed(par2);
        }
        
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