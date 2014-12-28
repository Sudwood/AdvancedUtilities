package com.sudwood.advancedutilities.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityFluidTube extends TileEntity implements IFluidHandler, ISteamTank
{
	 public FluidTank tank = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME*16);
	 private int transferAmount = 1000;
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
	    
	    @Override
	    public void updateEntity()
	    {
	    	if(!worldObj.isRemote && !worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord))
	    	{
		    	if(tank.getFluidAmount() >= this.transferAmount)
		    	{
		    		pushFluid();
		    		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		    	}
		    	if(tank.getFluidAmount() + this.transferAmount <= tank.getCapacity())
		    	{
		    		pullFluid();
		    		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		    	}
		    	this.markDirty();
		    	
	    	}
	    }

	    /* IFluidHandler */
	    @Override
	    public int fill(ForgeDirection from, FluidStack resource, boolean doFill)
	    {
	        return tank.fill(resource, doFill);
	    }

	    @SideOnly(Side.CLIENT)
	    public int getFluidScaled(int p_145955_1_)
	    {
	        return this.tank.getFluidAmount() * p_145955_1_ / this.tank.getCapacity();
	    }
	    
	    public int getTankAmount()
	    {
	    	return tank.getFluidAmount();
	    }
	    
	    public String getFluidName()
	    {
	    	if(this.tank.getFluidAmount() > 0)
	    		return this.tank.getFluid().getFluid().getLocalizedName(tank.getFluid());
	    	else
	    		return "Empty";
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
	    	if(tank.getFluidAmount() <=0 || (fluid == tank.getFluid().getFluid() && tank.getFluidAmount()< tank.getCapacity()) )
	    		return true;
	    	else return false;
	    }

	    @Override
	    public boolean canDrain(ForgeDirection from, Fluid fluid)
	    {
	    	if(tank.getFluidAmount() >=0 || fluid == tank.getFluid().getFluid() )
	    		return true;
	    	else return false;
	    }

	    @Override
	    public FluidTankInfo[] getTankInfo(ForgeDirection from)
	    {
	        return new FluidTankInfo[] { tank.getInfo() };
	    }
	    
	    public void pullFluid()
	    {
	    	switch(worldObj.getBlockMetadata(xCoord, yCoord, zCoord))
	    	{
	    	case 1:
		    		try
		    		{
		    			if(!(worldObj.getTileEntity(xCoord, yCoord+ForgeDirection.UP.offsetY, zCoord) instanceof TileEntityFluidTube))
		    			{
		    				IFluidHandler tile = (IFluidHandler) worldObj.getTileEntity(xCoord, yCoord+ForgeDirection.UP.offsetY, zCoord);
		    				if(tile.getTankInfo(ForgeDirection.DOWN)[0].fluid.amount>0 && this.canFill(ForgeDirection.UP, tile.getTankInfo(ForgeDirection.DOWN)[0].fluid.getFluid()))
		    				{
		    					tank.fill(tile.drain(ForgeDirection.DOWN, new FluidStack(tile.getTankInfo(ForgeDirection.DOWN)[0].fluid.getFluid(), this.transferAmount), true), true);
		    				}
		    			}
		    		}
		    		catch(Exception e)
		    		{
		    			
		    		}
		    	
		    	break;
	    	case 0:
	    		try
	    		{
	    			if(!(worldObj.getTileEntity(xCoord, yCoord+ForgeDirection.DOWN.offsetY, zCoord) instanceof TileEntityFluidTube))
	    			{
	    				IFluidHandler tile = (IFluidHandler) worldObj.getTileEntity(xCoord, yCoord+ForgeDirection.DOWN.offsetY, zCoord);
	    				if(tile.getTankInfo(ForgeDirection.UP)[0].fluid.amount>0 && this.canFill(ForgeDirection.DOWN, tile.getTankInfo(ForgeDirection.UP)[0].fluid.getFluid()))
	    				{
	    					tank.fill(tile.drain(ForgeDirection.UP, new FluidStack(tile.getTankInfo(ForgeDirection.UP)[0].fluid.getFluid(), this.transferAmount), true), true);
	    				}
	    			}
	    		}
	    		catch(Exception e)
	    		{
	    			
	    		}
		    	break;
	    	case 3:
	    		try
	    		{
	    			if(!(worldObj.getTileEntity(xCoord+ForgeDirection.SOUTH.offsetX, yCoord+ForgeDirection.SOUTH.offsetY, zCoord+ForgeDirection.SOUTH.offsetZ) instanceof TileEntityFluidTube))
	    			{
	    				IFluidHandler tile = (IFluidHandler) worldObj.getTileEntity(xCoord+ForgeDirection.SOUTH.offsetX, yCoord+ForgeDirection.SOUTH.offsetY, zCoord+ForgeDirection.SOUTH.offsetZ);
	    				if(tile.getTankInfo(ForgeDirection.NORTH)[0].fluid.amount>0 && this.canFill(ForgeDirection.SOUTH, tile.getTankInfo(ForgeDirection.NORTH)[0].fluid.getFluid()))
	    				{
	    					tank.fill(tile.drain(ForgeDirection.NORTH, new FluidStack(tile.getTankInfo(ForgeDirection.NORTH)[0].fluid.getFluid(), this.transferAmount), true), true);
	    				}
	    			}
	    		}
	    		catch(Exception e)
	    		{
	    			
	    		}
		    	break;
	    	case 2:
	    		try
	    		{
	    			if(!(worldObj.getTileEntity(xCoord+ForgeDirection.NORTH.offsetX, yCoord+ForgeDirection.NORTH.offsetY, zCoord+ForgeDirection.NORTH.offsetZ) instanceof TileEntityFluidTube))
	    			{
	    				
	    				IFluidHandler tile = (IFluidHandler) worldObj.getTileEntity(xCoord+ForgeDirection.NORTH.offsetX, yCoord+ForgeDirection.NORTH.offsetY, zCoord+ForgeDirection.NORTH.offsetZ);
	    				if(tile.getTankInfo(ForgeDirection.SOUTH)[0].fluid.amount>0 && this.canFill(ForgeDirection.NORTH, tile.getTankInfo(ForgeDirection.SOUTH)[0].fluid.getFluid()))
	    				{
	    					tank.fill(tile.drain(ForgeDirection.SOUTH, new FluidStack(tile.getTankInfo(ForgeDirection.SOUTH)[0].fluid.getFluid(), this.transferAmount), true), true);
	    				}
	    			}
	    		}
	    		catch(Exception e)
	    		{
	    			
	    		}
		    	break;
	    	case 4:
	    		try
	    		{
	    			if(!(worldObj.getTileEntity(xCoord+ForgeDirection.WEST.offsetX, yCoord+ForgeDirection.WEST.offsetY, zCoord+ForgeDirection.WEST.offsetZ) instanceof TileEntityFluidTube))
	    			{
	    				IFluidHandler tile = (IFluidHandler) worldObj.getTileEntity(xCoord+ForgeDirection.WEST.offsetX, yCoord+ForgeDirection.WEST.offsetY, zCoord+ForgeDirection.WEST.offsetZ);
	    				if(tile.getTankInfo(ForgeDirection.EAST)[0].fluid.amount>0 && this.canFill(ForgeDirection.WEST, tile.getTankInfo(ForgeDirection.EAST)[0].fluid.getFluid()))
	    				{
	    					tank.fill(tile.drain(ForgeDirection.EAST, new FluidStack(tile.getTankInfo(ForgeDirection.EAST)[0].fluid.getFluid(), this.transferAmount), true), true);
	    				}
	    			}
	    		}
	    		catch(Exception e)
	    		{
	    			
	    		}
		    	break;
	    	case 5:
	    		try
	    		{
	    			if(!(worldObj.getTileEntity(xCoord+ForgeDirection.EAST.offsetX, yCoord+ForgeDirection.EAST.offsetY, zCoord+ForgeDirection.EAST.offsetZ) instanceof TileEntityFluidTube))
	    			{
	    				IFluidHandler tile = (IFluidHandler) worldObj.getTileEntity(xCoord+ForgeDirection.EAST.offsetX, yCoord+ForgeDirection.EAST.offsetY, zCoord+ForgeDirection.EAST.offsetZ);
	    				if(tile.getTankInfo(ForgeDirection.WEST)[0].fluid.amount>0  && this.canFill(ForgeDirection.WEST, tile.getTankInfo(ForgeDirection.EAST)[0].fluid.getFluid()))
	    				{
	    					tank.fill(tile.drain(ForgeDirection.WEST, new FluidStack(tile.getTankInfo(ForgeDirection.WEST)[0].fluid.getFluid(), this.transferAmount), true), true);
	    				}
	    			}
	    		}
	    		catch(Exception e)
	    		{
	    			
	    		}
		    	break;
	    	}
	    }
	    
	    
	    public void pushFluid()
	    {
	    	int sideAttached = worldObj.getBlockMetadata(xCoord, yCoord, zCoord) ^ 1;
		    		switch(worldObj.getBlockMetadata(xCoord, yCoord, zCoord))
		    		{
		    		case 1: //down
		    			try
		    			{
		    				IFluidHandler tile = (IFluidHandler) (worldObj.getTileEntity(xCoord, yCoord+ForgeDirection.DOWN.offsetY, zCoord));
		    				if(tile.canFill(ForgeDirection.UP, this.tank.getFluid().getFluid())   && this.canDrain(ForgeDirection.DOWN, tank.getFluid().getFluid()))
		    				{
		    					if(!(tile instanceof TileEntityTank) && tile.getTankInfo(ForgeDirection.UP)[0]!=null && tile.getTankInfo(ForgeDirection.UP)[0].fluid != null)
		    					{
		    						if(tile.getTankInfo(ForgeDirection.UP)[0].fluid.amount+this.transferAmount <= tile.getTankInfo(ForgeDirection.UP)[0].capacity)
		    						{
		    							tile.fill(ForgeDirection.UP, this.drain(ForgeDirection.DOWN, new FluidStack(tank.getFluid().getFluid(), this.transferAmount), true), true);
		    						}
		    					}
		    					else
		    						tile.fill(ForgeDirection.UP, this.drain(ForgeDirection.DOWN, new FluidStack(tank.getFluid().getFluid(), this.transferAmount), true), true);
		    				}
		    			}
		    			catch(Exception e)
		    			{
		    				
		    			}
		    			break;
		    		case 0: //up
		    			try
		    			{
		    				IFluidHandler tile = (IFluidHandler) (worldObj.getTileEntity(xCoord, yCoord+ForgeDirection.UP.offsetY, zCoord));
		    				if(tile.canFill(ForgeDirection.DOWN, this.tank.getFluid().getFluid())  && this.canDrain(ForgeDirection.UP, tank.getFluid().getFluid()))
		    				{
		    					if(!(tile instanceof TileEntityTank) && tile.getTankInfo(ForgeDirection.DOWN)[0]!=null && tile.getTankInfo(ForgeDirection.DOWN)[0].fluid != null)
		    					{
		    						if(tile.getTankInfo(ForgeDirection.DOWN)[0].fluid.amount+this.transferAmount <= tile.getTankInfo(ForgeDirection.DOWN)[0].capacity)
		    						{
		    							tile.fill(ForgeDirection.DOWN, this.drain(ForgeDirection.UP, new FluidStack(tank.getFluid().getFluid(), this.transferAmount), true), true);
		    						}
		    					}
		    					else
		    						tile.fill(ForgeDirection.DOWN, this.drain(ForgeDirection.UP, new FluidStack(tank.getFluid().getFluid(), this.transferAmount), true), true);
		    				}
		    			}
		    			catch(Exception e)
		    			{
		    				
		    			}
		    			break;
		    		case 3: //North
		    			
		    			try
		    			{
		    				IFluidHandler tile = (IFluidHandler) (worldObj.getTileEntity(xCoord+ForgeDirection.NORTH.offsetX, yCoord+ForgeDirection.NORTH.offsetY, zCoord+ForgeDirection.NORTH.offsetZ));
		    				if(tile.canFill(ForgeDirection.SOUTH, this.tank.getFluid().getFluid())  && this.canDrain(ForgeDirection.NORTH, tank.getFluid().getFluid()))
		    				{
		    					if(!(tile instanceof TileEntityTank) && tile.getTankInfo(ForgeDirection.SOUTH)[0]!=null && tile.getTankInfo(ForgeDirection.SOUTH)[0].fluid != null)
		    					{
		    						if(tile.getTankInfo(ForgeDirection.SOUTH)[0].fluid.amount+this.transferAmount <= tile.getTankInfo(ForgeDirection.SOUTH)[0].capacity)
		    						{
		    							tile.fill(ForgeDirection.SOUTH, this.drain(ForgeDirection.NORTH, new FluidStack(tank.getFluid().getFluid(), this.transferAmount), true), true);
		    						}
		    					}
		    					else
		    						tile.fill(ForgeDirection.SOUTH, this.drain(ForgeDirection.NORTH, new FluidStack(tank.getFluid().getFluid(), this.transferAmount), true), true);
		    				}
		    			}
		    			catch(Exception e)
		    			{
		    				
		    			}break;
		    		case 2: //south
		    			try
		    			{
		    				IFluidHandler tile = (IFluidHandler) (worldObj.getTileEntity(xCoord+ForgeDirection.SOUTH.offsetX, yCoord+ForgeDirection.SOUTH.offsetY, zCoord+ForgeDirection.SOUTH.offsetZ));
		    				if(tile.canFill(ForgeDirection.NORTH, this.tank.getFluid().getFluid()) && this.canDrain(ForgeDirection.SOUTH, tank.getFluid().getFluid()))
		    				{
		    					if(!(tile instanceof TileEntityTank) && tile.getTankInfo(ForgeDirection.NORTH)[0]!=null && tile.getTankInfo(ForgeDirection.NORTH)[0].fluid != null)
		    					{
		    						if(tile.getTankInfo(ForgeDirection.NORTH)[0].fluid.amount+this.transferAmount <= tile.getTankInfo(ForgeDirection.NORTH)[0].capacity)
		    						{
		    							tile.fill(ForgeDirection.NORTH, this.drain(ForgeDirection.SOUTH, new FluidStack(tank.getFluid().getFluid(), this.transferAmount), true), true);
		    						}
		    					}
		    					else
		    						tile.fill(ForgeDirection.NORTH, this.drain(ForgeDirection.SOUTH, new FluidStack(tank.getFluid().getFluid(), this.transferAmount), true), true);
		    				}
		    			}
		    			catch(Exception e)
		    			{
		    				
		    			}
		    			break;
		    		case 5: //west
		    			try
		    			{
		    				IFluidHandler tile = (IFluidHandler) (worldObj.getTileEntity(xCoord+ForgeDirection.WEST.offsetX, yCoord+ForgeDirection.WEST.offsetY, zCoord+ForgeDirection.WEST.offsetZ));
		    				if(tile.canFill(ForgeDirection.EAST, this.tank.getFluid().getFluid()) && this.canDrain(ForgeDirection.WEST, tank.getFluid().getFluid()))
		    				{
		    					if(!(tile instanceof TileEntityTank) && tile.getTankInfo(ForgeDirection.EAST)[0]!=null && tile.getTankInfo(ForgeDirection.EAST)[0].fluid != null)
		    					{
		    						if(tile.getTankInfo(ForgeDirection.EAST)[0].fluid.amount+this.transferAmount <= tile.getTankInfo(ForgeDirection.EAST)[0].capacity)
		    						{
		    							tile.fill(ForgeDirection.EAST, this.drain(ForgeDirection.WEST, new FluidStack(tank.getFluid().getFluid(), this.transferAmount), true), true);
		    						}
		    					}
		    					else
		    						tile.fill(ForgeDirection.EAST, this.drain(ForgeDirection.WEST, new FluidStack(tank.getFluid().getFluid(), this.transferAmount), true), true);
		    				}
		    			}
		    			catch(Exception e)
		    			{
		    				
		    			}
		    			break;
		    		case 4: //east
		    			try
		    			{
		    				IFluidHandler tile = (IFluidHandler) (worldObj.getTileEntity(xCoord+ForgeDirection.EAST.offsetX, yCoord+ForgeDirection.EAST.offsetY, zCoord+ForgeDirection.EAST.offsetZ));
		    				if(tile.canFill(ForgeDirection.WEST, this.tank.getFluid().getFluid())  && this.canDrain(ForgeDirection.EAST, tank.getFluid().getFluid()))
		    				{
		    					
		    					if(!(tile instanceof TileEntityTank) && tile.getTankInfo(ForgeDirection.WEST)[0]!=null && tile.getTankInfo(ForgeDirection.WEST)[0].fluid != null)
		    					{
		    						
		    						if(tile.getTankInfo(ForgeDirection.WEST)[0].fluid.amount+this.transferAmount <= tile.getTankInfo(ForgeDirection.WEST)[0].capacity)
		    						{
		    							tile.fill(ForgeDirection.WEST, this.drain(ForgeDirection.EAST, new FluidStack(tank.getFluid().getFluid(), this.transferAmount), true), true);
		    						}
		    					}
		    					else
		    						tile.fill(ForgeDirection.WEST, this.drain(ForgeDirection.EAST, new FluidStack(tank.getFluid().getFluid(), this.transferAmount), true), true);
		    				}
		    			}
		    			catch(Exception e)
		    			{
		    				
		    			}
		    			break;
		    		}
		    		
	    }
	    
	    public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
	    {
	        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
	    }
	    
}
