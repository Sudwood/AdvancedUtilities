package com.sudwood.advancedutilities.tileentity;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

import com.sudwood.advancedutilities.blocks.AdvancedUtilitiesBlocks;

public class TileEntityQuarryFrame extends TileEntity
{
	
	private Block thb = AdvancedUtilitiesBlocks.quarryFrame;
	private Block sqm = AdvancedUtilitiesBlocks.steamQuarry;
	public int[] render = new int[6];
	private boolean hasChecked = false;
	
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
    
    public void onBlocksUpdated()
    {
    	if(worldObj.getBlock(xCoord, yCoord-1, zCoord) != thb && worldObj.getBlock(xCoord, yCoord-1, zCoord) != sqm)
    	{
    		render[0] = 0;
    		this.makeUpdate(0, 0);
    	}
    	if(worldObj.getBlock(xCoord, yCoord+1, zCoord) != thb && worldObj.getBlock(xCoord, yCoord+1, zCoord) != sqm)
    	{
    		render[1] = 0;
    		this.makeUpdate(1, 0);
    	}
    	if(worldObj.getBlock(xCoord, yCoord, zCoord-1) != thb && worldObj.getBlock(xCoord, yCoord, zCoord-1) != sqm)
    	{
    		render[2] = 0;
    		this.makeUpdate(2, 0);
    	}
    	if(worldObj.getBlock(xCoord, yCoord, zCoord+1) != thb && worldObj.getBlock(xCoord, yCoord, zCoord+1) != sqm)
    	{
    		render[3] = 0;
    		this.makeUpdate(3, 0);
    	}
    	if(worldObj.getBlock(xCoord-1, yCoord, zCoord) != thb && worldObj.getBlock(xCoord-1, yCoord, zCoord) != sqm)
    	{
    		render[4] = 0;
    		this.makeUpdate(4, 0);
    	}
    	if(worldObj.getBlock(xCoord+1, yCoord, zCoord) != thb && worldObj.getBlock(xCoord+1, yCoord, zCoord) != sqm)
    	{
    		render[5] = 0;
    		this.makeUpdate(5, 0);
    	}
    	if(worldObj.getBlock(xCoord, yCoord-1, zCoord) == thb || worldObj.getBlock(xCoord, yCoord-1, zCoord) == sqm)
    	{
    		render[0] = 1;
    		this.makeUpdate(0, 1);
    	}
    	if(worldObj.getBlock(xCoord, yCoord+1, zCoord) == thb || worldObj.getBlock(xCoord, yCoord+1, zCoord) == sqm)
    	{
    		render[1] = 1;
    		this.makeUpdate(1, 1);
    	}
    	if(worldObj.getBlock(xCoord, yCoord, zCoord-1) == thb || worldObj.getBlock(xCoord, yCoord, zCoord-1) == sqm)
    	{
    		render[2] = 1;
    		this.makeUpdate(2, 1);
    	}
    	if(worldObj.getBlock(xCoord, yCoord, zCoord+1) == thb || worldObj.getBlock(xCoord, yCoord, zCoord+1) == sqm)
    	{
    		render[3] = 1;
    		this.makeUpdate(3, 1);
    	}
    	if(worldObj.getBlock(xCoord-1, yCoord, zCoord) == thb || worldObj.getBlock(xCoord-1, yCoord, zCoord) == sqm)
    	{
    		render[4] = 1;
    		this.makeUpdate(4, 1);
    	}
    	if(worldObj.getBlock(xCoord+1, yCoord, zCoord) == thb || worldObj.getBlock(xCoord+1, yCoord, zCoord) == sqm)
    	{
    		render[5] = 1;
    		this.makeUpdate(5, 1);
    	}
    	worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		this.markDirty();
    }
    
    public void makeUpdate(int id, int info)
    {
    	worldObj.addBlockEvent(xCoord, yCoord, zCoord, thb, id, info);
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
		   this.onBlocksUpdated();
	   }
	   public void readSyncableDataFromNBT(NBTTagCompound tag)
	   {
		   tag.setIntArray("render", render);
	   }
	   @Override
	   public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
	   {
		   super.onDataPacket(net, pkt);
	       readSyncableDataFromNBT(pkt.func_148857_g());
	   }
}
