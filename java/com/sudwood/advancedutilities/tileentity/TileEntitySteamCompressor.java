package com.sudwood.advancedutilities.tileentity;

import com.sudwood.advancedutilities.CrushRecipes;
import com.sudwood.advancedutilities.TransferHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class TileEntitySteamCompressor extends TileEntitySteamBase
{
	 public FluidTank tank = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME*10);
	 public static final int pushAmount = 1000;
	 public static final int useAmount = 100;
	    @Override
	    public void readFromNBT(NBTTagCompound tag)
	    {
	        super.readFromNBT(tag);
	        tank.readFromNBT(tag);

	    }

	    @Override
	    public void writeToNBT(NBTTagCompound tag)
	    {
	        super.writeToNBT(tag);
	        tank.writeToNBT(tag);



	    }
	    public void updateEntity()
	    {
	    	if(this.canPushSteam())
	    	{
	    		this.pushSteam();
	    	}
	    	
	    }
	    
	    public void pushSteam()
	    {
	    	if(worldObj.getBlock(xCoord, yCoord+1, zCoord)!=null)
	    	{
		    	try
		    	{
		    		TileEntity tile = worldObj.getTileEntity(xCoord, yCoord+1, zCoord);
		    		if(tile instanceof TileEntitySteamBase && this.tank.getFluidAmount() >= this.pushAmount + this.useAmount)
		    		{
		    			((TileEntitySteamBase)tile).fill(ForgeDirection.DOWN, new FluidStack(FluidRegistry.getFluid("steam"), this.pushAmount), true);
		    			this.drain(ForgeDirection.UNKNOWN, new FluidStack(FluidRegistry.getFluid("steam"), this.useAmount+this.useAmount), true);
		    		}
		    	}
		    	catch(Exception e)
		    	{
		    		
		    	}
	    	}
	    }
	    
	    public boolean canPushSteam()
	    {
	    	if(this.tank.getFluidAmount() >= this.pushAmount+this.useAmount)
	    	{
	    		return true;
	    	}
	    	else
	    		return false;
	    }
	    
	    

	    public int getTankAmount()
	    {
	    	return tank.getFluidAmount();
	    }
	    
	    public String getFluidName()
	    {
	    	if(this.tank.getFluidAmount() > 0)
	    		return this.tank.getFluid().getFluid().getLocalizedName();
	    	else
	    		return "Empty";
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
	    	if(fluid == FluidRegistry.getFluid("steam"))
	    		return true;
	    	else return false;
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


}
