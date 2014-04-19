package com.sudwood.advancedutilities.tileentity;

import com.sudwood.advancedutilities.AdvancedUtilities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeChunkManager.Ticket;
import net.minecraftforge.common.ForgeChunkManager.Type;

public class TileEntityChunkLoader extends TileEntity
{
	private boolean isGettingRedstone = false;
	private Ticket chunkTicket;
	public void setIsGettingRedstone(boolean bool)
	{
		isGettingRedstone = bool;
		System.out.println(bool);
	}
	public void readFromNBT(NBTTagCompound tag)
    {
		isGettingRedstone = tag.getBoolean("isGettingRedstone");
    }
	public void writeToNBT(NBTTagCompound tag)
    {
		tag.setBoolean("isGettingRedstone", isGettingRedstone);
    }
	
	 public void updateEntity() 
	 {
		 if(!isGettingRedstone)
		 {
			 unloadChunks();
		 }
		 else
		 {
			 loadChunks();
		 }
	 }
	
	public void loadChunks()
	{
		if(!worldObj.isRemote)
		{
			 while(chunkTicket == null)
			 {
				 chunkTicket = ForgeChunkManager.requestTicket(AdvancedUtilities.instance, worldObj, Type.NORMAL);
			 }
			 if(chunkTicket==null)
			 {
				 System.out.println("FuckingHell");
			 }
			 chunkTicket.getModData().setInteger("blockX", xCoord);
			 chunkTicket.getModData().setInteger("blockY", yCoord);
			 chunkTicket.getModData().setInteger("blockZ", zCoord);
			
			 ForgeChunkManager.forceChunk(chunkTicket, new ChunkCoordIntPair(xCoord>>4, zCoord >>4));
		}
	}
	 
	 public void unloadChunks()
	 {
		 ForgeChunkManager.unforceChunk(chunkTicket, new ChunkCoordIntPair(xCoord>>4, zCoord >>4));
	 }
	 public void loadTicket(Ticket ticket)
	 {
		 if (chunkTicket == null)
		 {
				chunkTicket = ticket;
		 }
		 ChunkCoordIntPair loadChunk = new ChunkCoordIntPair(xCoord >> 4, zCoord >> 4);
		 ForgeChunkManager.forceChunk(ticket, loadChunk);
	 }
}
