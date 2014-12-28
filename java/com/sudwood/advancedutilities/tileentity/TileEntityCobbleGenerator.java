package com.sudwood.advancedutilities.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class TileEntityCobbleGenerator extends TileEntity implements IInventory, IFluidHandler
{
	 protected FluidTank tank = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME*16);
	 protected FluidTank waterTank = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME*16);
	 private ItemStack inventory = null;
	 private ItemStack target = null;
	    @Override
	    public void readFromNBT(NBTTagCompound tag)
	    {
	        super.readFromNBT(tag);
	        tank.readFromNBT(tag);
	        waterTank.readFromNBT(tag);
	        NBTTagCompound nbttagcompound1 = (NBTTagCompound) tag.getTag("inventory");
            this.inventory = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            
            NBTTagCompound nbttagcompound2 = (NBTTagCompound) tag.getTag("target");
            this.target = ItemStack.loadItemStackFromNBT(nbttagcompound2);
	    }

	    @Override
	    public void writeToNBT(NBTTagCompound tag)
	    {
	        super.writeToNBT(tag);
	        tank.writeToNBT(tag);
	        waterTank.writeToNBT(tag);
	        NBTTagCompound tag2 = new NBTTagCompound();
	        if(inventory!=null)
	        	inventory.writeToNBT(tag2);
	        tag.setTag("inventory", tag2);
	        
	        NBTTagCompound tag3 = new NBTTagCompound();
	        if(target!=null)
	        	target.writeToNBT(tag3);
	        tag.setTag("target", tag3);
	    }
	    
	    public int getTankAmount()
	    {
	    	return tank.getFluidAmount();
	    }
	    public int getWaterTankAmount()
	    {
	    	return waterTank.getFluidAmount();
	    }
	    
	    public String getFluidName()
	    {
	    	if(this.tank.getFluidAmount() > 0)
	    		return this.tank.getFluid().getFluid().getLocalizedName();
	    	else
	    		return "Empty";
	    }
	    
	    public String getWaterName()
	    {
	    	if(this.waterTank.getFluidAmount() > 0)
	    		return this.waterTank.getFluid().getFluid().getLocalizedName();
	    	else
	    		return "Empty";
	    }
	    
	    @SideOnly(Side.CLIENT)
	    public int getFluidScaled(int p_145955_1_)
	    {
	        return this.tank.getFluidAmount() * p_145955_1_ / this.tank.getCapacity();
	    }
	    
	    @SideOnly(Side.CLIENT)
	    public int getWaterScaled(int p_145955_1_)
	    {
	        return this.waterTank.getFluidAmount() * p_145955_1_ / this.waterTank.getCapacity();
	    }
	    
	    public void updateEntity()
	    {
	    	if(this.target == null || this.target.getItem() == Item.getItemFromBlock(Blocks.cobblestone))
	    	{
	    		this.makeCobble();
	    	}
	    	if(this.target.getItem() == Item.getItemFromBlock(Blocks.stone))
	    	{
	    		this.makeStone();
	    	}
	    	if(this.target.getItem() == Item.getItemFromBlock(Blocks.obsidian))
	    	{
	    		this.makeObsidian();
	    	}
	    }
	    
	    private void makeCobble()
	    {
	    	if((this.inventory == null || (this.inventory.getItem() == Item.getItemFromBlock(Blocks.cobblestone) && this.inventory.stackSize < this.getInventoryStackLimit())) && this.waterTank.getFluidAmount() >= 1000 && this.tank.getFluidAmount() >= 1000)
	    	{
	    		if(this.inventory == null)
	    		{
	    			this.inventory = new ItemStack(Blocks.cobblestone, 1);
	    			return;
	    		}
	    		else
	    		{
	    			this.inventory.stackSize++;
	    		}
	    	}
	    }
	    private int stoneWaterUse = 5;
	    private void makeStone()
	    {
	    	if((this.inventory == null || (this.inventory.getItem() == Item.getItemFromBlock(Blocks.stone) && this.inventory.stackSize < this.getInventoryStackLimit())) && this.waterTank.getFluidAmount() >= stoneWaterUse && this.tank.getFluidAmount() >= 1000)
	    	{
	    		if(this.inventory == null)
	    		{
	    			this.inventory = new ItemStack(Blocks.stone, 1);
	    			this.drain(ForgeDirection.UNKNOWN, new FluidStack(FluidRegistry.WATER, this.stoneWaterUse), true);
	    			return;
	    		}
	    		else
	    		{
	    			this.inventory.stackSize++;
	    			this.drain(ForgeDirection.UNKNOWN, new FluidStack(FluidRegistry.WATER, this.stoneWaterUse), true);
	    		}
	    	}
	    }
	    private int obsidianLavaUse = 1000;
	    private void makeObsidian()
	    {
	    	if((this.inventory == null || (this.inventory.getItem() == Item.getItemFromBlock(Blocks.obsidian) && this.inventory.stackSize < this.getInventoryStackLimit())) && this.waterTank.getFluidAmount() >= 1000 && this.tank.getFluidAmount() >= this.obsidianLavaUse)
	    	{
	    		if(this.inventory == null)
	    		{
	    			this.inventory = new ItemStack(Blocks.obsidian, 1);
	    			this.drain(ForgeDirection.UNKNOWN, new FluidStack(FluidRegistry.LAVA, this.obsidianLavaUse), true);
	    			return;
	    		}
	    		else
	    		{
	    			this.inventory.stackSize++;
	    			this.drain(ForgeDirection.UNKNOWN, new FluidStack(FluidRegistry.LAVA, this.obsidianLavaUse), true);
	    		}
	    	}
	    }

	    /* IFluidHandler */
	    @Override
	    public int fill(ForgeDirection from, FluidStack resource, boolean doFill)
	    {
	       if(resource.getFluid() == FluidRegistry.LAVA)
	       {
	    	   return tank.fill(resource, doFill);
	       }
	       if(resource.getFluid() == FluidRegistry.WATER)
	       {
	    	   return waterTank.fill(resource, doFill);
	       }
	    return 0;
	       
	    }

	    @Override
	    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain)
	    {
	        if (resource == null || !resource.isFluidEqual(new FluidStack(FluidRegistry.LAVA ,1000)) || !resource.isFluidEqual(new FluidStack(FluidRegistry.WATER ,1000)))
	        {
	            return null;
	        }
	        if(resource.isFluidEqual(new FluidStack(FluidRegistry.LAVA ,1000)))
	        	return tank.drain(resource.amount, doDrain);
	        if(resource.isFluidEqual(new FluidStack(FluidRegistry.WATER ,1000)))
	        	return waterTank.drain(resource.amount, doDrain);
	        else
	        	return null;
	    }

	    @Override
	    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain)
	    {
	        return tank.drain(maxDrain, doDrain);
	    }

	    @Override
	    public boolean canFill(ForgeDirection from, Fluid fluid)
	    {
	    	if(fluid == FluidRegistry.LAVA || fluid == FluidRegistry.WATER)
	    		return true;
	    	else
	    		return false;
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
			return 1;
		}

		@Override
		public ItemStack getStackInSlot(int var1) {
			// TODO Auto-generated method stub
			return inventory;
		}

		@Override
		public ItemStack decrStackSize(int var1, int var2) {
			// TODO Auto-generated method stub
			inventory.stackSize-= var2;
			if(inventory.stackSize <= 0)
				inventory = null;
			return inventory;
				
		}

		@Override
		public ItemStack getStackInSlotOnClosing(int var1) {
			// TODO Auto-generated method stub
			return inventory;
		}

		@Override
		public void setInventorySlotContents(int var1, ItemStack var2) 
		{
			inventory = var2;
		}

		@Override
		public String getInventoryName() 
		{
			return "CobbleGen";
		}

		@Override
		public boolean hasCustomInventoryName() 
		{
			return false;
		}

		@Override
		public int getInventoryStackLimit() 
		{
			return 64;
		}

		@Override
		public boolean isUseableByPlayer(EntityPlayer var1) {
			// TODO Auto-generated method stub
			return true;
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
		public boolean isItemValidForSlot(int var1, ItemStack var2) 
		{
			// TODO Auto-generated method stub
			if(var2.getItem() == Item.getItemFromBlock(Blocks.cobblestone))
				return true;
			return false;
		}

}
