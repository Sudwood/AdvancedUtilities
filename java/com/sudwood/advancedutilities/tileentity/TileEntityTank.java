package com.sudwood.advancedutilities.tileentity;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

import com.sudwood.advancedutilities.blocks.AdvancedUtilitiesBlocks;
import com.sudwood.advancedutilities.blocks.BlockTank;

public class TileEntityTank extends TileEntity implements IFluidHandler, ISteamTank
{
	public FluidTank tank = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME*64);
	public int redstoneTime = 0;
	public int maxTime = 2;
 	@Override
    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);
        maxTime = tag.getInteger("maxTime");
        tank.readFromNBT(tag);
    }
    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
        tag.setInteger("maxTime", maxTime);
        tank.writeToNBT(tag);
    }
    
    public void increaseMaxTime()
    {
    	maxTime+=2;
    	if(maxTime>20)
    	{
    		maxTime=2;
    	}
    }
    
    public void redstone()
    {
    	if(redstoneTime > 0)
    	{
    		BlockTank block = (BlockTank) worldObj.getBlock(xCoord, yCoord, zCoord);
    		block.power = true;
    		notifyNeighbors(block);
    		redstoneTime--;
    		if(redstoneTime == 0)
    		{
    			block.power = false;
    			notifyNeighbors(block);
    		}
    		notifyNeighbors(block);
    	}
    }
    
    public void notifyNeighbors(Block block)
    {
    	worldObj.notifyBlocksOfNeighborChange(xCoord, yCoord, zCoord, block);
    	
    }
    
    public void updateEntity()
    {
    	redstone();
    	if(tank.getFluidAmount() > 0)
    	{
    		if(worldObj.getBlock(xCoord, yCoord-1, zCoord) == AdvancedUtilitiesBlocks.blockTank)
    		{
    			if(worldObj.getTileEntity(xCoord, yCoord-1, zCoord) != null)
    			{
    				TileEntityTank tile = (TileEntityTank) worldObj.getTileEntity(xCoord, yCoord-1, zCoord);
    				if(tile.tank.getFluidAmount() > 0)
    				{
    					if(tile.tank.getFluid().getFluid() == this.tank.getFluid().getFluid())
    					{
    						if(tile.tank.getFluidAmount() < tile.tank.getCapacity())
    						{
    							tile.fill(ForgeDirection.UP, this.drain(ForgeDirection.DOWN,tile.tank.getCapacity()-tile.tank.getFluidAmount(), true), true);
    							worldObj.markBlockForUpdate(xCoord, yCoord-1, zCoord);
    							worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    						}
    					}
    				}
    				else
    				{
    					tile.fill(ForgeDirection.UP, this.drain(ForgeDirection.DOWN,this.tank.getFluidAmount(), true), true);
    					worldObj.markBlockForUpdate(xCoord, yCoord-1, zCoord);
    					worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    				}
    			}
    			
    		}
    		/*if(worldObj.getBlock(xCoord, yCoord+1, zCoord) == AdvancedUtilitiesBlocks.blockTank)
    		{
	    		if(worldObj.getTileEntity(xCoord, yCoord+1, zCoord) != null)
				{
	    			TileEntityTank tile = (TileEntityTank) worldObj.getTileEntity(xCoord, yCoord+1, zCoord);
					if(this.tank.getFluidAmount()>= this.tank.getCapacity())
					{
						if(tile.tank.getFluidAmount() > 0)
						{
							if(tile.tank.getFluid().getFluid() == this.tank.getFluid().getFluid())
							{
								tile.fill(ForgeDirection.DOWN, this.drain(ForgeDirection.UP, tile.tank.getCapacity()-tile.tank.getFluidAmount(), true), true);
								worldObj.markBlockForUpdate(xCoord, yCoord+1, zCoord);
								worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
							}
						}
						else
						{
							tile.fill(ForgeDirection.DOWN, this.drain(ForgeDirection.UP, this.tank.getFluidAmount(), true), true);
							worldObj.markBlockForUpdate(xCoord, yCoord+1, zCoord);
							worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
						}
					}
				}
    		}*/
    		
    	}
    	if(worldObj.getBlock(xCoord, yCoord-1, zCoord) == Blocks.water)
		{
			if(this.tank.getFluidAmount() <= 0 || (this.tank.getFluid().getFluid()==FluidRegistry.WATER) && this.tank.getFluidAmount() < this.tank.getCapacity())
			{
				worldObj.setBlock(xCoord, yCoord-1, zCoord, Blocks.air);
				this.fill(ForgeDirection.SOUTH, new FluidStack(FluidRegistry.WATER, 1000), true);
			}
		}
    }
	
    @Override
    public boolean canUpdate()
    {
    	return true;
    }
    
	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) 
	{
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		if(resource== null)
		{
			return 0;
		}
		if(tank.getFluid() == null)
		{
			return tank.fill(resource, doFill);
		}
		if((resource.getFluid() == tank.getFluid().getFluid()) && tank.getFluidAmount() +resource.amount <= tank.getCapacity())
		{
			return tank.fill(resource, doFill);
		}
		else if(tank.getFluidAmount()+resource.amount >= tank.getCapacity())
		{
			if(worldObj.getBlock(xCoord, yCoord+1, zCoord)!= null && worldObj.getTileEntity(xCoord, yCoord+1, zCoord) != null)
			{
				if(worldObj.getTileEntity(xCoord, yCoord+1, zCoord) instanceof IFluidHandler)
				{
					if(((IFluidHandler)worldObj.getTileEntity(xCoord, yCoord+1, zCoord)).canFill(from, resource.getFluid()))
					{
						return ((IFluidHandler)worldObj.getTileEntity(xCoord, yCoord+1, zCoord)).fill(from, resource, doFill);
					}
				}
			}
		}
		return 0;
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) 
	{
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		if(resource.getFluid() == tank.getFluid().getFluid() && tank.getFluidAmount() >= resource.amount)
		{
			return tank.drain(resource.amount, doDrain);
		}
		return null;
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) 
	{
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		if(tank.getFluidAmount() >= maxDrain)
		{
			return tank.drain(maxDrain, doDrain);
		}
		return null;
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) 
	{
		if((tank.getFluidAmount() <= 0 || tank.getFluid().getFluid() == fluid) && tank.getFluidAmount() < tank.getCapacity())
		{
			return true;
		}
		else if(tank.getFluidAmount() >= tank.getCapacity())
		{
			if(worldObj.getBlock(xCoord, yCoord+1, zCoord)!= null && worldObj.getTileEntity(xCoord, yCoord+1, zCoord) != null)
			{
				if(worldObj.getTileEntity(xCoord, yCoord+1, zCoord) instanceof IFluidHandler && ((IFluidHandler)worldObj.getTileEntity(xCoord, yCoord+1, zCoord)).canFill(from, fluid))
				{
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) 
	{
		if(tank.getFluidAmount() > 0 && tank.getFluid().getFluid() == fluid)
		{
			return true;
		}
		return false;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) 
	{
		  return new FluidTankInfo[] { tank.getInfo() };
	}
	
	@Override
	public Packet getDescriptionPacket() 
	{
	    NBTTagCompound tagCompound = new NBTTagCompound();
	    writeToNBT(tagCompound);
	    return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tagCompound);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) 
	{
	    readFromNBT(pkt.func_148857_g());
	}


}
