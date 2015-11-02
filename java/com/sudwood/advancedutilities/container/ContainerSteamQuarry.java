package com.sudwood.advancedutilities.container;

import com.sudwood.advancedutilities.CrushRecipes;
import com.sudwood.advancedutilities.tileentity.TileEntitySteamQuarry;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerSteamQuarry extends Container
{
    private TileEntitySteamQuarry tileFurnace;
    private int lastTankAmount;
    private int lastProgressTime;

    public ContainerSteamQuarry(InventoryPlayer par1InventoryPlayer, TileEntitySteamQuarry par2TileEntitySteamBoiler)
    {
        this.tileFurnace = par2TileEntitySteamBoiler;
        this.addSlotToContainer(new Slot(par2TileEntitySteamBoiler, 0, 32, 16));
        this.addSlotToContainer(new Slot(par2TileEntitySteamBoiler, 1, 32, 34));
        this.addSlotToContainer(new Slot(par2TileEntitySteamBoiler, 2, 32, 52));
        this.addSlotToContainer(new Slot(par2TileEntitySteamBoiler, 3, 50, 16));
        this.addSlotToContainer(new Slot(par2TileEntitySteamBoiler, 4, 50, 34));
        this.addSlotToContainer(new Slot(par2TileEntitySteamBoiler, 5, 50, 52));
        this.addSlotToContainer(new Slot(par2TileEntitySteamBoiler, 6, 68, 16));
        this.addSlotToContainer(new Slot(par2TileEntitySteamBoiler, 7, 68, 34));
        this.addSlotToContainer(new Slot(par2TileEntitySteamBoiler, 8, 68, 52));
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
            else if (!this.mergeItemStack(itemstack1, 3, 2, false))
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