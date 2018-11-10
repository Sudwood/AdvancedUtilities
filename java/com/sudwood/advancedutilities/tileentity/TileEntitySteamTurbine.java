package com.sudwood.advancedutilities.tileentity;

import java.util.ArrayList;

import com.sudwood.advancedutilities.HelperLibrary;
import com.sudwood.advancedutilities.blocks.AdvancedUtilitiesBlocks;
import com.sudwood.advancedutilities.fluids.AdvancedUtilitiesFluids;

import net.minecraft.nbt.NBTTagByte;
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

public class TileEntitySteamTurbine extends TileEntity implements IPowerProvider, ISteamTank, IFluidHandler
{
	private int transferRate;
	private int maxPower;
	private int currentPower = 0;
	private int spinUp = 0;
	private int[] render = {0,0,0,0,0,0};
	private ArrayList powerClasses = new ArrayList();
	private boolean hasChecked = false;
	public FluidTank tank = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME*64);
	public TileEntitySteamTurbine(int rate)
	{
		transferRate = rate;
		maxPower = 250*rate;
		this.registerRenderPowerTile(IWire.class);
		this.registerRenderPowerTile(IPowerReciever.class);
	}

	@Override
	public void sendPower() 
	{
		if(currentPower >= transferRate)
		{
			int numsen = 0;
			int numnot = -1;
			for(int i = 0; i < render.length; i++)
			{
				if(render[i]== 0)
				{
					numnot = i;
				}
				else
				{
					numsen++;
				}
			}
			for(int ix = 0; ix < render.length; ix++)
			{
				/*if(render[ix]== 0)
				{
					System.out.println(render[ix]+":"+ix);
				}
				else*/	
				{
					if(worldObj.getTileEntity(xCoord+HelperLibrary.getForgeDirectionFromNumber(ix).offsetX, yCoord+HelperLibrary.getForgeDirectionFromNumber(ix).offsetY, zCoord+HelperLibrary.getForgeDirectionFromNumber(ix).offsetZ) instanceof IWire)
					{
						IWire temp = (IWire)worldObj.getTileEntity(xCoord+HelperLibrary.getForgeDirectionFromNumber(ix).offsetX, yCoord+HelperLibrary.getForgeDirectionFromNumber(ix).offsetY, zCoord+HelperLibrary.getForgeDirectionFromNumber(ix).offsetZ);
						if(temp.getCurrentPower() + transferRate/*/numsen*/ <= temp.getMaxPower())
						{
							temp.addPower(transferRate/*/numsen*/, HelperLibrary.getForgeDirectionFromNumber(ix).getOpposite());
							this.currentPower-=transferRate/*/numsen*/;
						}
					}
					else if(worldObj.getTileEntity(xCoord+HelperLibrary.getForgeDirectionFromNumber(ix).offsetX, yCoord+HelperLibrary.getForgeDirectionFromNumber(ix).offsetY, zCoord+HelperLibrary.getForgeDirectionFromNumber(ix).offsetZ) instanceof IPowerReciever)
					{
						IPowerReciever temp = (IPowerReciever)worldObj.getTileEntity(xCoord+HelperLibrary.getForgeDirectionFromNumber(ix).offsetX, yCoord+HelperLibrary.getForgeDirectionFromNumber(ix).offsetY, zCoord+HelperLibrary.getForgeDirectionFromNumber(ix).offsetZ);
						if(temp.getCurrentPower() + transferRate/*/numsen*/ <= temp.getMaxPower())
						{
							temp.addPower(transferRate/*/numsen*/, HelperLibrary.getForgeDirectionFromNumber(ix).getOpposite());
							this.currentPower-=transferRate/*/numsen*/;
						}
					}
				}
			}
		}
	}

	@Override
	public boolean addPower(int powerToAdd, ForgeDirection dir) {
		// TODO Auto-generated method stub
		this.currentPower+=powerToAdd;
		return true;
	}
	
	@Override
    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);
        this.readSyncableDataFromNBT(tag);
       
    }
    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
        this.writeSyncableDataToNBT(tag);
    }

	@Override
	public void updateEntity()
    {
		if(!hasChecked && !worldObj.isRemote)
    	{
    			if(worldObj.getWorldInfo().getAdditionalProperty("AdvancedUtilities:WorldLoaded")!= null && ((NBTTagByte) worldObj.getWorldInfo().getAdditionalProperty("AdvancedUtilities:WorldLoaded")).func_150290_f() == (byte) 1)
    			{
    				this.onBlocksUpdated();
    				worldObj.getBlock(xCoord, yCoord, zCoord).onNeighborBlockChange(worldObj, xCoord, yCoord, zCoord, getBlockType());
    				hasChecked = true;
    			}
    	}
		if(this.tank.getFluidAmount() >= 10)
		{
			if(this.spinUp < 10)
			{
				tank.drain(10, true);
				spinUp++;
			}
			if(this.spinUp>= 10)
			{
				this.addPower(20, ForgeDirection.UNKNOWN);
				tank.drain(10, true);
				this.sendPower();
			}
		}
    }
	
	private void registerRenderPowerTile(Class c)
    {
    	powerClasses.add(c);
    }
	
	public void checkRenderBlocks()
    {
    	boolean temp0 = false;
    	boolean temp1 = false;
    	boolean temp2 = false;
    	boolean temp3 = false;
    	boolean temp4 = false;
    	boolean temp5 = false;
    	for(Object o : powerClasses)
    	{
    		if(o!=null)
    		{
    			if((o.getClass().isInstance(worldObj.getTileEntity(xCoord, yCoord-1, zCoord))))
    			{
    				temp0 = true;
    			}
    			if((o.getClass().isInstance(worldObj.getTileEntity(xCoord, yCoord+1, zCoord))))
    			{
    				temp1 = true;
    			}
    			if((o.getClass().isInstance(worldObj.getTileEntity(xCoord, yCoord, zCoord-1))))
    			{
    				temp2 = true;
    			}
    			if((o.getClass().isInstance(worldObj.getTileEntity(xCoord, yCoord, zCoord+1))))
    			{
    				temp3 = true;
    			}
    			if((o.getClass().isInstance(worldObj.getTileEntity(xCoord-1, yCoord, zCoord))))
    			{
    				temp4 = true;
    			}
    			if((o.getClass().isInstance(worldObj.getTileEntity(xCoord+1, yCoord, zCoord))))
    			{
    				temp5 = true;
    			}
    			
    		}
    	}
    	if(temp0)
    	{
    		render[0] = 1;
    		this.makeUpdate(0, 1);
    	}
    	if(!temp0)
    	{
    		render[0] = 0;
    		this.makeUpdate(0, 0);
    	}
    	if(temp1)
    	{
    		render[1] = 1;
    		this.makeUpdate(1, 1);
    	}
    	if(!temp1)
    	{
    		render[1] = 0;
    		this.makeUpdate(1, 0);
    	}
    	if(temp2)
    	{
    		render[2] = 1;
    		this.makeUpdate(2, 1);
    	}
    	if(!temp2)
    	{
    		render[2] = 0;
    		this.makeUpdate(2, 0);
    	}
    	if(temp3)
    	{
    		render[3] = 1;
    		this.makeUpdate(3, 1);
    	}
    	if(!temp3)
    	{
    		render[3] = 0;
    		this.makeUpdate(3, 0);
    	}
    	if(temp4)
    	{
    		render[4] = 1;
    		this.makeUpdate(4, 1);
    	}
    	if(!temp4)
    	{
    		render[4] = 0;
    		this.makeUpdate(4, 0);
    	}
    	if(temp5)
    	{
    		render[5] = 1;
    		this.makeUpdate(5, 1);
    	}
    	if(!temp5)
    	{
    		render[5] = 0;
    		this.makeUpdate(5, 0);
    	}
    }

	public void onBlocksUpdated()
    {
    	this.checkRenderBlocks();
    	worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		this.markDirty();
    }
	public void makeUpdate(int id, int info)
    {
    	worldObj.addBlockEvent(xCoord, yCoord, zCoord, AdvancedUtilitiesBlocks.steamTurbine, id, info);
    }
	
	@Override
	public int getTransferAmount() {
		// TODO Auto-generated method stub
		return this.transferRate;
	}

	@Override
	public int getCurrentPower() {
		// TODO Auto-generated method stub
		return this.currentPower;
	}

	@Override
	public int getMaxPower() {
		// TODO Auto-generated method stub
		return this.maxPower;
	}
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
    	if((fluid == FluidRegistry.getFluid("Steam") || fluid == AdvancedUtilitiesFluids.fluidSteam) && tank.getFluidAmount() < tank.getCapacity())
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
    
    @Override
	   public Packet getDescriptionPacket()
	   {
		   super.getDescriptionPacket();
	       NBTTagCompound syncData = new NBTTagCompound();
	       this.writeSyncableDataToNBT(syncData);
	       return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, syncData);
	   }
	   public void writeSyncableDataToNBT(NBTTagCompound tag)
	   {
		   tag.setIntArray("render", render);
		   tag.setInteger("currentPower", currentPower);
		   tag.setInteger("spinUp", spinUp);
		   tag.setInteger("rate", transferRate);
		   tag.setInteger("maxPower", maxPower);
		   tank.writeToNBT(tag);
		   this.onBlocksUpdated();
	   }
	   public void readSyncableDataFromNBT(NBTTagCompound tag)
	   {
		   render = tag.getIntArray("render");
		   this.spinUp = tag.getInteger("spinUp");
		   this.currentPower = tag.getInteger("currentPower");
		   transferRate = tag.getInteger("rate");
		   maxPower = tag.getInteger("maxPower");
		   tank.readFromNBT(tag);
	   }
	   @Override
	   public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
	   {
		   super.onDataPacket(net, pkt);
	       readSyncableDataFromNBT(pkt.func_148857_g());
	   }


}
