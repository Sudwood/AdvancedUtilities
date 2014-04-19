package com.sudwood.advancedutilities.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class TileEntitySteamBase extends TileEntity implements ISidedInventory, IFluidHandler
{
	 protected FluidTank tank = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME*64);

	    @Override
	    public void readFromNBT(NBTTagCompound tag)
	    {
	        super.readFromNBT(tag);
	    }

	    @Override
	    public void writeToNBT(NBTTagCompound tag)
	    {
	        super.writeToNBT(tag);
	    }

	    /* IFluidHandler */
	    @Override
	    public int fill(ForgeDirection from, FluidStack resource, boolean doFill)
	    {
	        return tank.fill(resource, doFill);
	    }

	    @Override
	    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain)
	    {
	        if (resource == null || !resource.isFluidEqual(tank.getFluid()))
	        {
	            return null;
	        }
	        return tank.drain(resource.amount, doDrain);
	    }

	    @Override
	    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain)
	    {
	        return tank.drain(maxDrain, doDrain);
	    }

	    @Override
	    public boolean canFill(ForgeDirection from, Fluid fluid)
	    {
	        return true;
	    }

	    @Override
	    public boolean canDrain(ForgeDirection from, Fluid fluid)
	    {
	        return true;
	    }

	    @Override
	    public FluidTankInfo[] getTankInfo(ForgeDirection from)
	    {
	        return new FluidTankInfo[] { tank.getInfo() };
	    }

		@Override
		public int getSizeInventory() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public ItemStack getStackInSlot(int var1) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public ItemStack decrStackSize(int var1, int var2) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public ItemStack getStackInSlotOnClosing(int var1) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setInventorySlotContents(int var1, ItemStack var2) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public String getInventoryName() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean hasCustomInventoryName() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public int getInventoryStackLimit() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public boolean isUseableByPlayer(EntityPlayer var1) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void openInventory() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void closeInventory() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean isItemValidForSlot(int var1, ItemStack var2) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public int[] getAccessibleSlotsFromSide(int var1) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean canInsertItem(int var1, ItemStack var2, int var3) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean canExtractItem(int var1, ItemStack var2, int var3) {
			// TODO Auto-generated method stub
			return false;
		}
}
