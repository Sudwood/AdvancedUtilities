package com.sudwood.advancedutilities.tileentity;

import java.util.ArrayList;

import com.sudwood.advancedutilities.HelperLibrary;
import com.sudwood.advancedutilities.blocks.AdvancedUtilitiesBlocks;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityWire extends TileEntity implements IWire
{
	public static final int copperRate = 50;
	public static final int ironRate = 15;
	public static final int goldRate = 100;
	private int transferRate;
	private Block thb = AdvancedUtilitiesBlocks.goldWire;
	private Block sqm = AdvancedUtilitiesBlocks.ironWire;
	private Block pwp = AdvancedUtilitiesBlocks.copperWire;
	public static ArrayList powerClasses = new ArrayList();
	public int[] render = new int[6];
	private boolean hasChecked = false;
	private int maxPower;
	private int currentPower = 0;
	private ForgeDirection addedDirection = ForgeDirection.UNKNOWN;
	
	public TileEntityWire(int rate)
	{
		transferRate = rate;
		maxPower = rate*250;
	}

	@Override
	public void sendPower() 
	{
		if(addedDirection != ForgeDirection.UNKNOWN && currentPower >= transferRate)
		{
			int numsen = 0;
			int numnot = -1;
			for(int i = 0; i < render.length; i++)
			{
				if(HelperLibrary.getForgeDirectionFromNumber(i) == addedDirection || render[i]== 0)
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
				if(HelperLibrary.getNumberFromForgeDirection(addedDirection) == ix)
				{
					
				}
				else
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
	public boolean addPower(int powerToAdd, ForgeDirection dir) 
	{
		if(this.currentPower+powerToAdd <= this.maxPower)
		{
			currentPower+=powerToAdd;
			addedDirection = dir;
			this.sendPower();
			return true;
		}
		else
		{
			this.sendPower();
			return false;
		}
			
	}

	@Override
	public int getTransferAmount() 
	{
		return this.transferRate;
	}

	@Override
	public int getCurrentPower() 
	{
		return this.currentPower;
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
    public boolean canUpdate()
    {
        return true;
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
    
    public static void registerRenderPowerTile(Class c)
    {
    	powerClasses.add(c);
    }
    
    public void onBlocksUpdated()
    {
    	this.checkRenderBlocks();
    	worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		this.markDirty();
    }
    
    public void makeUpdate(int id, int info)
    {
    	switch(transferRate)
    	{
    	case copperRate:
    		worldObj.addBlockEvent(xCoord, yCoord, zCoord, pwp, id, info);
    		break;
    	case ironRate:
    		worldObj.addBlockEvent(xCoord, yCoord, zCoord, sqm, id, info);
    		break;
    	case goldRate:
    		worldObj.addBlockEvent(xCoord, yCoord, zCoord, thb, id, info);
    		break;
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
		   tag.setInteger("addedDirection", HelperLibrary.getNumberFromForgeDirection(addedDirection));
		   this.onBlocksUpdated();
	   }
	   public void readSyncableDataFromNBT(NBTTagCompound tag)
	   {
		   render = tag.getIntArray("render");
		   transferRate = tag.getInteger("rate");
		   maxPower = tag.getInteger("maxPower");
		   this.currentPower = tag.getInteger("currentPower");
		   
		   this.addedDirection = HelperLibrary.getForgeDirectionFromNumber(tag.getInteger("addedDirection"));
	   }
	   @Override
	   public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
	   {
		   super.onDataPacket(net, pkt);
	       readSyncableDataFromNBT(pkt.func_148857_g());
	   }

	@Override
	public int getMaxPower() {
		// TODO Auto-generated method stub
		return this.maxPower;
	}
}
