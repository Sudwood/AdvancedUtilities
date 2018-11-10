package com.sudwood.advancedutilities.container;

import com.sudwood.advancedutilities.tileentity.TileEntityBoiler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerSteamBoiler extends Container
{
    private TileEntityBoiler tileFurnace;
    private int lastTankAmount;
    private int lastWaterAmount;
    private int lastBurnTime;
    private int lastItemBurnTime;
    private static final String __OBFID = "CL_00001748";
    private int sizeInventory = 0;
    public ContainerSteamBoiler(InventoryPlayer par1InventoryPlayer, TileEntityBoiler par2TileEntitySteamBoiler)
    {
        this.tileFurnace = par2TileEntitySteamBoiler;
        sizeInventory = tileFurnace.getSizeInventory();
        this.addSlotToContainer(new Slot(par2TileEntitySteamBoiler, 0, 56, 53));
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
        par1ICrafting.sendProgressBarUpdate(this, 1, this.tileFurnace.furnaceBurnTime);
        par1ICrafting.sendProgressBarUpdate(this, 2, this.tileFurnace.currentItemBurnTime);
        par1ICrafting.sendProgressBarUpdate(this, 3, this.tileFurnace.getTankAmount());
        par1ICrafting.sendProgressBarUpdate(this, 4, this.tileFurnace.getWaterTankAmount());
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

            if (this.lastBurnTime != this.tileFurnace.furnaceBurnTime)
            {
                icrafting.sendProgressBarUpdate(this, 1, this.tileFurnace.furnaceBurnTime);
            }

            if (this.lastItemBurnTime != this.tileFurnace.currentItemBurnTime)
            {
                icrafting.sendProgressBarUpdate(this, 2, this.tileFurnace.currentItemBurnTime);
            }
            if(this.lastTankAmount != this.tileFurnace.tank.getFluidAmount())
            {
            	icrafting.sendProgressBarUpdate(this, 3, this.tileFurnace.tank.getFluidAmount());
            }
            if(this.lastWaterAmount != this.tileFurnace.waterTank.getFluidAmount())
            {
            	icrafting.sendProgressBarUpdate(this, 4, this.tileFurnace.waterTank.getFluidAmount());
            }
        }

        this.lastBurnTime = this.tileFurnace.furnaceBurnTime;
        this.lastItemBurnTime = this.tileFurnace.currentItemBurnTime;
        this.lastTankAmount = this.tileFurnace.getTankAmount();
        this.lastWaterAmount = this.tileFurnace.getWaterTankAmount();
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2)
    {

        if (par1 == 1)
        {
            this.tileFurnace.furnaceBurnTime = par2;
        }

        if (par1 == 2)
        {
            this.tileFurnace.currentItemBurnTime = par2;
        }
        if(par1 == 3)
        {
        	this.tileFurnace.tank.setFluid(new FluidStack(FluidRegistry.getFluid("steam"), par2));
        }
        if(par1 == 4)
        {
        	this.tileFurnace.waterTank.setFluid(new FluidStack(FluidRegistry.WATER, par2));
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