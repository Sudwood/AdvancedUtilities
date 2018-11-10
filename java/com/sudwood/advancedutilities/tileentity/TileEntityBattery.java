package com.sudwood.advancedutilities.tileentity;

import java.util.ArrayList;

import com.sudwood.advancedutilities.HelperLibrary;
import com.sudwood.advancedutilities.blocks.AdvancedUtilitiesBlocks;

import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityBattery extends TileEntity implements IPowerReciever
{
	private int maxPower = 0;
	private int currentPower = 0;
	private int transferRate = 0;
	private int[] render = {0,0,0,0,0,0};
	private ArrayList powerClasses = new ArrayList();
	private boolean hasChecked = false;
	public TileEntityBattery(int rate)
	{
		this.transferRate = rate;
		this.maxPower = 5000*rate;
	}
	@Override
	public boolean addPower(int powerToAdd, ForgeDirection dir) {
		// TODO Auto-generated method stub
		if(powerToAdd+this.currentPower <= maxPower && dir!= ForgeDirection.UP && dir!=ForgeDirection.DOWN)
		{
			currentPower+=powerToAdd;
			this.sendPower();
			return true;
		}
		this.sendPower();
		return false;
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
    	worldObj.addBlockEvent(xCoord, yCoord, zCoord, AdvancedUtilitiesBlocks.energyBattery, id, info);
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
    }

	@Override
	public int getCurrentPower() 
	{
		// TODO Auto-generated method stub
		return currentPower;
	}

	@Override
	public int getMaxPower() 
	{
		// TODO Auto-generated method stub
		return maxPower;
	}

	@Override
	public void sendPower() 
	{
		if(currentPower >= transferRate)
		{
			int numsen = 0;
			int numnot = -1;
			if(render[HelperLibrary.getNumberFromForgeDirection(ForgeDirection.UP)]!= 0)
			{
				numsen++;
			}
			if(render[HelperLibrary.getNumberFromForgeDirection(ForgeDirection.DOWN)]!= 0)
			{
				numsen++;
			}
			if(render[HelperLibrary.getNumberFromForgeDirection(ForgeDirection.DOWN)]!= 0)
			{
					if(worldObj.getTileEntity(xCoord+ForgeDirection.DOWN.offsetX, yCoord+ForgeDirection.DOWN.offsetY, zCoord+ForgeDirection.DOWN.offsetZ) instanceof IWire)
					{
						IWire temp = (IWire)worldObj.getTileEntity(xCoord+ForgeDirection.DOWN.offsetX, yCoord+ForgeDirection.DOWN.offsetY, zCoord+ForgeDirection.DOWN.offsetZ);
						if(temp.getCurrentPower() + transferRate/numsen <= temp.getMaxPower())
						{
							temp.addPower(transferRate/numsen, ForgeDirection.DOWN.getOpposite());
							this.currentPower-=transferRate/numsen;
						}
					}
					else if(worldObj.getTileEntity(xCoord+ForgeDirection.DOWN.offsetX, yCoord+ForgeDirection.DOWN.offsetY, zCoord+ForgeDirection.DOWN.offsetZ) instanceof IPowerReciever)
					{
						IPowerReciever temp = (IPowerReciever)worldObj.getTileEntity(xCoord+ForgeDirection.DOWN.offsetX, yCoord+ForgeDirection.DOWN.offsetY, zCoord+ForgeDirection.DOWN.offsetZ);
						if(temp.getCurrentPower() + transferRate/numsen <= temp.getMaxPower())
						{
							temp.addPower(transferRate/numsen, ForgeDirection.DOWN.getOpposite());
							this.currentPower-=transferRate/numsen;
						}
					}
				
			}
			if(render[HelperLibrary.getNumberFromForgeDirection(ForgeDirection.UP)]!= 0)
			{
					if(worldObj.getTileEntity(xCoord+ForgeDirection.UP.offsetX, yCoord+ForgeDirection.UP.offsetY, zCoord+ForgeDirection.UP.offsetZ) instanceof IWire)
					{
						IWire temp = (IWire)worldObj.getTileEntity(xCoord+ForgeDirection.UP.offsetX, yCoord+ForgeDirection.UP.offsetY, zCoord+ForgeDirection.UP.offsetZ);
						if(temp.getCurrentPower() + transferRate/numsen <= temp.getMaxPower())
						{
							temp.addPower(transferRate/numsen, ForgeDirection.UP.getOpposite());
							this.currentPower-=transferRate/numsen;
						}
					}
					else if(worldObj.getTileEntity(xCoord+ForgeDirection.UP.offsetX, yCoord+ForgeDirection.UP.offsetY, zCoord+ForgeDirection.UP.offsetZ) instanceof IPowerReciever)
					{
						IPowerReciever temp = (IPowerReciever)worldObj.getTileEntity(xCoord+ForgeDirection.UP.offsetX, yCoord+ForgeDirection.UP.offsetY, zCoord+ForgeDirection.UP.offsetZ);
						if(temp.getCurrentPower() + transferRate/numsen <= temp.getMaxPower())
						{
							temp.addPower(transferRate/numsen, ForgeDirection.UP.getOpposite());
							this.currentPower-=transferRate/numsen;
						}
					}
				
			}
		}
		
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
		   tag.setInteger("rate", transferRate);
		   tag.setInteger("maxPower", maxPower);
		   this.onBlocksUpdated();
	   }
	   public void readSyncableDataFromNBT(NBTTagCompound tag)
	   {
		   render = tag.getIntArray("render");
		   this.currentPower = tag.getInteger("currentPower");
		   transferRate = tag.getInteger("rate");
		   maxPower = tag.getInteger("maxPower");
	   }
	   @Override
	   public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
	   {
		   super.onDataPacket(net, pkt);
	       readSyncableDataFromNBT(pkt.func_148857_g());
	   }

}
