package com.sudwood.advancedutilities.tileentity;

import java.util.ArrayList;

import com.sudwood.advancedutilities.AdvancedUtilities;
import com.sudwood.advancedutilities.AdvancedUtilitiesChunkLoadCallback;
import com.sudwood.advancedutilities.TransferHelper;
import com.sudwood.advancedutilities.blocks.AdvancedUtilitiesBlocks;
import com.sudwood.advancedutilities.config.ServerOptions;
import com.sudwood.advancedutilities.fluids.AdvancedUtilitiesFluids;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeChunkManager.Ticket;
import net.minecraftforge.common.ForgeChunkManager.Type;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.oredict.OreDictionary;

public class TileEntitySteamQuarry extends TileEntity implements IInventory, IFluidHandler, ISteamTank
{
	 public FluidTank tank = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME*64);
	 private ItemStack[] inventory = new ItemStack[9];
	 private static int digCost = 50*(ServerOptions.steamCreationRate/5);
	 private int currentOutput = 0;
	 private int costMod = 0;
	 public int speedMult = 1;
	 public int progressTime;
	 private int[] digCoords = {0,0,0};
	 private int[] digChunk = {0,0};
	 private boolean Axis = false; // true x false z
	 public int xSize = 0;
	 public int zSize = 0;
	 public int xSign = 0;
	 public int zSign = 0;
	 public boolean isFortune3 = false;
	 public boolean isEff = false;
	 public boolean hasDetermined = false;
	 private boolean isLoadingMining = false;
	 public boolean shouldBeDigging = false;
	 
	 private Ticket chunkTicket;
	 private Ticket miningTicket;
	 
	 private Block outline = AdvancedUtilitiesBlocks.quarryFrame;
	    @Override
	    public void readFromNBT(NBTTagCompound tag)
	    {
	        super.readFromNBT(tag);
	        tank.readFromNBT(tag);
	        this.isEff = tag.getBoolean("iseff");
	        this.progressTime = tag.getInteger("progressTime");
	        this.currentOutput = tag.getInteger("CurrentOutput");
	        this.costMod = tag.getInteger("costMod");
	        this.speedMult = tag.getInteger("speedMult");
	        xSize = tag.getInteger("xSize");
	        zSize = tag.getInteger("zSize");
	        Axis = tag.getBoolean("Axis");
	        digCoords = tag.getIntArray("digCoords");
	        digChunk = tag.getIntArray("digChunk");
	        hasDetermined = tag.getBoolean("hasDetermined");
	        isLoadingMining = tag.getBoolean("isLoadingMining");
	        xSign = tag.getInteger("xSign");
	        zSign = tag.getInteger("zSign");
	        isFortune3 = tag.getBoolean("isFortune3");
	        shouldBeDigging = tag.getBoolean("shouldBeDigging");
	        NBTTagList nbttaglist = tag.getTagList("Items", 10);
	        this.inventory = new ItemStack[this.getSizeInventory()];

	        for (int i = 0; i < nbttaglist.tagCount(); ++i)
	        {
	            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
	            byte b0 = nbttagcompound1.getByte("Slot");

	            if (b0 >= 0 && b0 < this.inventory.length)
	            {
	                this.inventory[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
	            }
	        }
	    }

	    public String getOutline()
	    {
	    	return outline.getUnlocalizedName();
	    }
	    
	    public int[] getBoundary()
	    {
	    	int[] temp2 = {xSize,zSize,xSign,zSign};
	    	return temp2;
	    	
	    }
	    
	    public int getMaxProcessTime()
	    {
	    	return 10/this.speedMult;
	    }
	    
	    public int[] getDigCoords()
	    {
	    	return this.digCoords.clone();
	    }
	    
	    @Override
	    public void writeToNBT(NBTTagCompound tag)
	    {
	        super.writeToNBT(tag);
	        tank.writeToNBT(tag);
	        tag.setInteger("progressTime", progressTime);
	        tag.setInteger("CurrentOutput", currentOutput);
	        tag.setInteger("costMod", this.costMod);
	        tag.setInteger("speedMult", this.speedMult);
	        tag.setInteger("xSize", xSize);
	        tag.setInteger("zSize", zSize);
	        tag.setBoolean("Axis", Axis);
	        tag.setIntArray("digCoords", digCoords);
	        tag.setIntArray("digChunk", digChunk);
	        tag.setBoolean("hasDetermined", hasDetermined);
	        tag.setBoolean("isLoadingMining", isLoadingMining);
	        tag.setInteger("xSign", xSign);
	        tag.setInteger("zSign", zSign);
	        tag.setBoolean("isFortune3", isFortune3);
	        tag.setBoolean("shouldBeDigging", shouldBeDigging);
	        tag.setBoolean("iseff", isEff);
	        NBTTagList nbttaglist = new NBTTagList();

	        for (int i = 0; i < this.inventory.length; ++i)
	        {
	            if (this.inventory[i] != null)
	            {
	                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
	                nbttagcompound1.setByte("Slot", (byte)i);
	                this.inventory[i].writeToNBT(nbttagcompound1);
	                nbttaglist.appendTag(nbttagcompound1);
	            }
	        }

	        tag.setTag("Items", nbttaglist);

	    }
	    public void updateEntity()
	    {
	    	if((xCoord>>4) != (digCoords[0] >> 4) || (zCoord >>4) != (digCoords[2] >>4) && !isLoadingMining)
			{
				digChunk[0] = digCoords[0] >> 4;
				digChunk[1] = digCoords[2] >> 4;
				loadMining();
			}
			if((digChunk[0] != digCoords[0] >> 4 || digChunk[1] != digCoords[2] >>4) && isLoadingMining)
			{
				unloadMining();
				digChunk[0] = digCoords[0] >> 4;
				digChunk[1] = digCoords[2] >> 4;
				loadMining();
			}
	    	if(this.digCost > this.tank.getCapacity())
	    		this.digCost = this.tank.getCapacity();
	    		if(hasDetermined && canDig()&& shouldBeDigging)
	    		{
	    			this.progressTime++;
	    			
		    			if(this.progressTime >= 2 / this.speedMult)
		    			{
		    					this.dig();
		    				
		    				if(Axis)
		    				{
		    					digCoords[0]+=(1*xSign);
			    				if(xCoord+(xSize*xSign) +2*xSign == digCoords[0])
			    				{
			    					digCoords[0] = xCoord+(2*xSign);
			    					digCoords[2]+=(1*zSign);
			    				}
			    				if(zCoord+(zSize*zSign) +1*zSign == digCoords[2])
			    				{
			    					digCoords[0] = xCoord+(2*xSign);
			    					digCoords[1]-=1;
			    					digCoords[2] = zCoord+(1*zSign);
			    				}
		    				}
		    				if(!Axis)
		    				{
		    					digCoords[2]+=(1*zSign);
			    				if(zCoord+(zSize*zSign) +zSign == digCoords[2])
			    				{
			    					digCoords[2] = zCoord+(2*zSign);
			    					digCoords[0]+=(1*xSign);
			    				}
			    				if(xCoord+(xSize*xSign) == digCoords[0])
			    				{
			    					digCoords[2] = zCoord+(2*zSign);
			    					digCoords[1]-=1;
			    					digCoords[0] = xCoord+(1*xSign);
			    				}
		    				}
		    				if(digCoords[1] <=4)
		    				{
		    					shouldBeDigging = false;
		    				}
		    				
		    				this.progressTime = 0;
		    			}
	    	    	
	    		}
	    		else
	    		{
	    			this.progressTime = 0;
	    		}
	    		
	    		
	    	
	    }
	    
	    public void loadMining()
		 {
			 if(miningTicket == null)
			 {
				 miningTicket = ForgeChunkManager.requestTicket(AdvancedUtilities.instance, worldObj, Type.NORMAL);
			 }
			 if(miningTicket !=null)
			 {
				 miningTicket.getModData().setInteger("type", AdvancedUtilitiesChunkLoadCallback.ChunkLoaderQuarryDigID);
				 miningTicket.getModData().setInteger("blockX", xCoord);
				 miningTicket.getModData().setInteger("blocky", yCoord);
				 miningTicket.getModData().setInteger("blockz", zCoord);
			 isLoadingMining = true;
			 ForgeChunkManager.forceChunk(miningTicket, new ChunkCoordIntPair(digChunk[0], digChunk[1]));
			 }
		 }
		 public void loadMining(Ticket ticket)
		 {
			 if (miningTicket == null) {
					miningTicket = ticket;
				}
			 ChunkCoordIntPair loadChunk = new ChunkCoordIntPair(digChunk[0], digChunk[1]);
			 isLoadingMining = true;
			 ForgeChunkManager.forceChunk(ticket, loadChunk);
		 }
		 public void unloadMining()
		 {
			 ForgeChunkManager.unforceChunk(miningTicket, new ChunkCoordIntPair(digChunk[0], digChunk[1]));
			 isLoadingMining = false;
		 }
		 
		 public void loadChunks()
			{
				 if(chunkTicket == null)
				 {
					 chunkTicket = ForgeChunkManager.requestTicket(AdvancedUtilities.instance, worldObj, Type.NORMAL);
				 }
				 chunkTicket.getModData().setInteger("blockX", xCoord);
				 chunkTicket.getModData().setInteger("blocky", yCoord);
				 chunkTicket.getModData().setInteger("blockz", zCoord);
				 chunkTicket.getModData().setInteger("type", AdvancedUtilitiesChunkLoadCallback.ChunkLoaderQuarryID);
				
				 ForgeChunkManager.forceChunk(chunkTicket, new ChunkCoordIntPair(xCoord>>4, zCoord >>4));
			 }
			 
			 public void unloadChunks()
			 {
				 ForgeChunkManager.unforceChunk(chunkTicket, new ChunkCoordIntPair(xCoord>>4, zCoord >>4));
			 }
			 public void loadTicket(Ticket ticket)
			 {
				 if (chunkTicket == null) {
						chunkTicket = ticket;
					}
				 ChunkCoordIntPair loadChunk = new ChunkCoordIntPair(xCoord >> 4, zCoord >> 4);
				 ForgeChunkManager.forceChunk(ticket, loadChunk);
			 }
	    
	    public void dig()
	    {
	    	if(!isWrongBlock())
	    	{
	    		if(worldObj.getBlock(digCoords[0], digCoords[1], digCoords[2]) == Blocks.water || worldObj.getBlock(digCoords[0], digCoords[1], digCoords[2]) == Blocks.lava || worldObj.getBlock(digCoords[0], digCoords[1], digCoords[2]) == Blocks.flowing_lava ||  worldObj.getBlock(digCoords[0], digCoords[1], digCoords[2]) == Blocks.flowing_water)
	    		{
	    			worldObj.setBlock(digCoords[0], digCoords[1], digCoords[2], Blocks.dirt);
		    		this.tank.drain(this.digCost, true);
		    		worldObj.playSoundEffect((double)digCoords[0] + 0.5D, (double)digCoords[1] + 0.5D, (double)digCoords[2] + 0.5D, "dig.stone", 1.0F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
		    		return;
	    		}
	    		ArrayList list = null;
	    		if(!isEff)
	    		{
		    		if(isFortune3)
		    		{
		    			list = worldObj.getBlock(digCoords[0], digCoords[1], digCoords[2]).getDrops(worldObj, digCoords[0], digCoords[1], digCoords[2], worldObj.getBlockMetadata(digCoords[0], digCoords[1], digCoords[2]), 3);
		    		}
		    		else if(!isFortune3)
		    		{
		    			list = worldObj.getBlock(digCoords[0], digCoords[1], digCoords[2]).getDrops(worldObj, digCoords[0], digCoords[1], digCoords[2], worldObj.getBlockMetadata(digCoords[0], digCoords[1], digCoords[2]), 0);
		    		}
			    	if(list!= null && list.size() > 0)
			    	{
			    		for(int i = 0; i < list.size(); i++)
			    		{
			    			this.putItemInThisInventory((ItemStack)list.get(i));
			    		}
			    		if(!worldObj.isRemote)
			    		{
			    		worldObj.getBlock(digCoords[0], digCoords[1], digCoords[2]).breakBlock(worldObj, digCoords[0], digCoords[1], digCoords[2], worldObj.getBlock(digCoords[0], digCoords[1], digCoords[2]), worldObj.getBlockMetadata(digCoords[0], digCoords[1], digCoords[2]));
			    		worldObj.setBlockToAir(digCoords[0], digCoords[1], digCoords[2]);
			    		this.tank.drain(this.digCost+this.costMod, true);
			    		worldObj.playSoundEffect((double)digCoords[0] + 0.5D, (double)digCoords[1] + 0.5D, (double)digCoords[2] + 0.5D, "dig.stone", 1.0F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
			    		}
			    		if(worldObj.isRemote)
			    		{
			    			worldObj.spawnParticle("explode", digCoords[0], digCoords[1], digCoords[2], 1F, 1F, 1F);
			    		}
			    	}
	    		}
	    		if(isEff)
	    		{
	    			ItemStack temp = new ItemStack(worldObj.getBlock(digCoords[0], digCoords[1], digCoords[2]),1,worldObj.getBlockMetadata(digCoords[0], digCoords[1], digCoords[2]));
	    			int[] ids = OreDictionary.getOreIDs(temp);
	    			boolean shouldDig = false;
	    			if(ids!=null)
	    			{
		    			for(int i =0;i<ids.length;i++)
		    			{
		    				String name = OreDictionary.getOreName(ids[i]);
		    				if(name.substring(0, 3).equals("ore"))
		    				{
		    					shouldDig = true;
		    				}
		    			}
		    			if(shouldDig)
		    			{
		    				if(isFortune3)
				    		{
				    			list = worldObj.getBlock(digCoords[0], digCoords[1], digCoords[2]).getDrops(worldObj, digCoords[0], digCoords[1], digCoords[2], worldObj.getBlockMetadata(digCoords[0], digCoords[1], digCoords[2]), 3);
				    		}
				    		else if(!isFortune3)
				    		{
				    			list = worldObj.getBlock(digCoords[0], digCoords[1], digCoords[2]).getDrops(worldObj, digCoords[0], digCoords[1], digCoords[2], worldObj.getBlockMetadata(digCoords[0], digCoords[1], digCoords[2]), 0);
				    		}
					    	if(list!= null && list.size() > 0)
					    	{
					    		for(int i = 0; i < list.size(); i++)
					    		{
					    			this.putItemInThisInventory((ItemStack)list.get(i));
					    		}
					    		if(!worldObj.isRemote)
					    		{
					    		worldObj.getBlock(digCoords[0], digCoords[1], digCoords[2]).breakBlock(worldObj, digCoords[0], digCoords[1], digCoords[2], worldObj.getBlock(digCoords[0], digCoords[1], digCoords[2]), worldObj.getBlockMetadata(digCoords[0], digCoords[1], digCoords[2]));
					    		worldObj.setBlockToAir(digCoords[0], digCoords[1], digCoords[2]);
					    		this.tank.drain(this.digCost+this.costMod, true);
					    		worldObj.playSoundEffect((double)digCoords[0] + 0.5D, (double)digCoords[1] + 0.5D, (double)digCoords[2] + 0.5D, "dig.stone", 1.0F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
					    		}
					    		if(worldObj.isRemote)
					    		{
					    			worldObj.spawnParticle("explode", digCoords[0], digCoords[1], digCoords[2], 1F, 1F, 1F);
					    		}
					    	}
		    			}
	    			}
	    		}
	    	}
	    }
	    
	    public boolean isWrongBlock()
	    {
			if(worldObj.getBlock(digCoords[0], digCoords[1], digCoords[2]) != Blocks.chest)
	    	{
				if(worldObj.getBlock(digCoords[0], digCoords[1], digCoords[2]) != Blocks.bedrock)
		    	{
					if(worldObj.getBlock(digCoords[0], digCoords[1], digCoords[2]) != Blocks.command_block)
			    	{
						if(worldObj.getBlock(digCoords[0], digCoords[1], digCoords[2]) != Blocks.trapped_chest)
				    	{
							if(worldObj.getBlock(digCoords[0], digCoords[1], digCoords[2]) != Blocks.portal)
					    	{
								if(worldObj.getBlock(digCoords[0], digCoords[1], digCoords[2]) != Blocks.end_portal)
						    	{
									if(worldObj.getBlock(digCoords[0], digCoords[1], digCoords[2]) != Blocks.end_portal_frame)
							    	{
										if(worldObj.getBlock(digCoords[0], digCoords[1], digCoords[2]) != Blocks.ender_chest)
								    	{
											if(!(worldObj.getBlock(digCoords[0], digCoords[1], digCoords[2]) instanceof IInventory))
									    	{
												if(!(worldObj.getBlock(digCoords[0], digCoords[1], digCoords[2]) instanceof IFluidHandler))
    									    	{
													if(worldObj.getBlock(digCoords[0], digCoords[1], digCoords[2]) != AdvancedUtilitiesBlocks.quarryFrame)
											    	{
														return false;
											    	}
    									    	}
									    	}
								    	}
							    	}
						    	}
					    	}
				    	}
			    	}
		    	}
	    
	    	}
	    	return true;
	    }
	    
	    public boolean determineSize()
	    {
	    	xSize = 0;
	    	zSize = 0;
	    	xSign = 0;
	    	zSign = 0;
	    	int metadata = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
	    	ForgeDirection back = ForgeDirection.getOrientation(metadata^1);
	    	boolean xAxis = false;
	    	boolean isChecking = true;
	    	if(back.offsetX > 0)
	    		xSign = 1;
	    	if(back.offsetX < 0)
	    		xSign = -1;
	    	if(back.offsetZ > 0)
	    		zSign = 1;
	    	if(back.offsetZ < 0)
	    		zSign = -1;
	    	if(worldObj.getBlock(xCoord+back.offsetX, yCoord, zCoord) == outline)
	    	{
	    		xAxis = true;
	    	}
	    	if(worldObj.getBlock(xCoord, yCoord, zCoord+back.offsetZ) == outline)
	    	{
	    		xAxis = false;
	    	}
	    	if(worldObj.getBlock(xCoord, yCoord, zCoord+back.offsetZ) != outline && (worldObj.getBlock(xCoord+back.offsetX, yCoord, zCoord) != outline))
	    	{
	    		return false;
	    	}
	    	if(xAxis)
	    	{
	    		while(isChecking)
	    		{
	    			if(worldObj.getBlock(xCoord+back.offsetX+(xSize*xSign), yCoord, zCoord) == outline)
	    			{
	    				xSize+=1;
	    			}
	    			if(worldObj.getBlock(xCoord+back.offsetX+(xSize*xSign), yCoord, zCoord) != outline)
	    			{
	    				xSize-=1;
	    				isChecking = false;
	    			}
	    		}
	    		isChecking = true;
	    		if(worldObj.getBlock(xCoord+back.offsetX+(xSize*xSign), yCoord, zCoord+1) == outline)
	    		{
	    			zSign = 1;
	    		}
	    		else if(worldObj.getBlock(xCoord+back.offsetX+(xSize*xSign), yCoord, zCoord-1) == outline)
	    		{
	    			zSign = -1;
	    		}
	    		while(isChecking)
	    		{
	    			if(worldObj.getBlock(xCoord+back.offsetX+(xSize*xSign), yCoord, zCoord+(zSize*zSign)) == outline)
	    			{
	    				zSize+=1;
	    			}
	    			if(worldObj.getBlock(xCoord+back.offsetX+(xSize*xSign), yCoord, zCoord+(zSize*zSign)) != outline)
	    			{
	    				zSize-=1;
	    				isChecking = false;
	    			}
	    		}
	    		int tempx = 0;
	    		int tempz = 0;
	    		isChecking = true;
	    		while(isChecking)
	    		{
	    			if(worldObj.getBlock(xCoord+back.offsetX+(xSize*xSign)+(tempx*xSign), yCoord, zCoord+(zSize*zSign)) == outline)
	    			{
	    				tempx-=1;
	    			}
	    			if(worldObj.getBlock(xCoord+back.offsetX+(xSize*xSign)+(tempx*xSign), yCoord, zCoord+(zSize*zSign)) != outline)
	    			{
	    				tempx+=1;
	    				isChecking = false;
	    			}
	    		}
	    		isChecking = true;
	    		while(isChecking)
	    		{
	    			if(worldObj.getBlock(xCoord+back.offsetX+(xSize*xSign)+(tempx*xSign), yCoord, zCoord+(zSize*zSign)+(tempz*zSign)) == outline)
	    			{
	    				tempz-=1;
	    			}
	    			if(worldObj.getBlock(xCoord+back.offsetX+(xSize*xSign)+(tempx*xSign), yCoord, zCoord+(zSize*zSign)+(tempz*zSign)) != outline)
	    			{
	    				tempz+=1;
	    				isChecking = false;
	    			}
	    		}
	    		if((Math.abs((tempx)) == xSize) && (Math.abs(tempz) == zSize))
	    		{
	    			hasDetermined = true;
	    			digCoords[0] = (xCoord+2*xSign);
	    			digCoords[1] = yCoord-1;
	    			digCoords[2] = (zCoord+zSign);
	    			digChunk[0] = digCoords[0] >> 4;
	    			digChunk[1] = digCoords[2] >> 4;
	    			shouldBeDigging = true;
	    			return true;
	    		}
	    	
	    	}
	    	if(!xAxis)
	    	{
	    		
	    		while(isChecking)
	    		{
	    			if(worldObj.getBlock(xCoord, yCoord, zCoord+back.offsetZ+(zSize*zSign)) == outline)
	    			{
	    				zSize+=1;
	    			}
	    			if(worldObj.getBlock(xCoord, yCoord, zCoord+back.offsetZ+(zSize*zSign)) != outline)
	    			{
	    				zSize-=1;
	    				isChecking = false;
	    			}
	    		}
	    		isChecking = true;
	    		if(worldObj.getBlock(xCoord+1, yCoord, zCoord+back.offsetZ+(zSize*zSign)) == outline)
	    		{
	    			xSign = 1;
	    		}
	    		else if(worldObj.getBlock(xCoord-1, yCoord, zCoord+back.offsetZ+(zSize*zSign)) == outline)
	    		{
	    			xSign = -1;
	    		}
	    		while(isChecking)
	    		{
	    			if(worldObj.getBlock(xCoord+(xSize*xSign), yCoord, zCoord+back.offsetZ+(zSize*zSign)) == outline)
	    			{
	    				xSize+=1;
	    			}
	    			if(worldObj.getBlock(xCoord+(xSize*xSign), yCoord, zCoord+back.offsetZ+(zSize*zSign)) != outline)
	    			{
	    				xSize-=1;
	    				isChecking = false;
	    			}
	    		}
	    		int tempx = 0;
	    		int tempz = 0;
	    		isChecking = true;
	    		while(isChecking)
	    		{
	    			if(worldObj.getBlock(xCoord+(xSize*xSign), yCoord, zCoord+back.offsetZ+(zSize*zSign)+(tempz*zSign)) == outline)
	    			{
	    				tempz-=1;
	    			}
	    			if(worldObj.getBlock(xCoord+(xSize*xSign), yCoord, zCoord+back.offsetZ+(zSize*zSign)+(tempz*zSign)) != outline)
	    			{
	    				tempz+=1;
	    				isChecking = false;
	    			}
	    		}
	    		isChecking = true;
	    		while(isChecking)
	    		{
	    			if(worldObj.getBlock(xCoord+(xSize*xSign)+(tempx*xSign), yCoord, zCoord+(zSize*zSign)+(tempz*zSign)+back.offsetZ) == outline)
	    			{
	    				tempx-=1;
	    			}
	    			if(worldObj.getBlock(xCoord+(xSize*xSign)+(tempx*xSign), yCoord, zCoord+(zSize*zSign)+(tempz*zSign)+back.offsetZ) != outline)
	    			{
	    				tempx+=1;
	    				isChecking = false;
	    			}
	    		}
	    		if((Math.abs((tempx)) == xSize) && (Math.abs(tempz) == zSize))
	    		{
	    			hasDetermined = true;
	    			digCoords[0] = (xCoord+xSign);
	    			digCoords[1] = yCoord-1;
	    			digCoords[2] = (zCoord+2*zSign);
	    			digChunk[0] = digCoords[0] >> 4;
	    			digChunk[1] = digCoords[2] >> 4;
	    			shouldBeDigging = true;
	    			return true;
	    		}
	    	
	    	}
	    	return false;
	    }
	    
	    public boolean canDig()
	    {
	    	if(this.tank.getFluidAmount() >= (this.digCost)+this.costMod && inventory.length > 0 && (worldObj.getBlock(digCoords[0], digCoords[1], digCoords[2]) == Blocks.lava || worldObj.getBlock(digCoords[0], digCoords[1], digCoords[2]) == Blocks.water))
	    	{
	    		return true;
	    	}
	    	if(this.tank.getFluidAmount() >= (this.digCost)+this.costMod && inventory.length > 0)
	    	{
	    		ArrayList list = worldObj.getBlock(digCoords[0], digCoords[1], digCoords[2]).getDrops(worldObj, digCoords[0], digCoords[1], digCoords[2], worldObj.getBlockMetadata(digCoords[0], digCoords[1], digCoords[2]), 0);
	    		for(int i = 0; i < list.size(); i++)
	    		{
	    			int[] temp = TransferHelper.checkSpace(xCoord, yCoord, zCoord, (ItemStack)list.get(i), worldObj, 0);
	    			if(temp == null)
	    			{
	    				return false;
	    			}
	    		}
	    		return true;
	    	}
	    	return false;
	    }
	    
	    public boolean putItemInThisInventory(ItemStack stack)
	    {
	    	int[] slot = null;
	    	if(stack!=null)
	    	{
	    		slot = TransferHelper.checkSpace(xCoord, yCoord, zCoord, stack, worldObj, 0);
	    		if(slot!=null)
	    		{
		    		if(slot[1] == 0)
					{
						inventory[slot[0]] = new ItemStack(stack.getItem(), stack.stackSize, stack.getItemDamage());
						this.markDirty();
						return true;
					}
					if(slot[1] == 1)
					{
						inventory[slot[0]].stackSize += stack.stackSize;
						this.markDirty();
						return true;
					}
	    		}
	    	}
	    	return false;
	    	
	    }
	    
	    public int getProgressScaled(int num)
	    {
	    	return this.progressTime * num / (10/this.speedMult);
	    }
	    
	    public void findInventory()
	    {
	    	this.checkCompressor();
	    	/*switch(worldObj.getBlockMetadata(xCoord, yCoord, zCoord))
	    	{
	    	case 2: // north
	    		if(worldObj.getBlock(xCoord+ForgeDirection.NORTH.offsetX, yCoord+ForgeDirection.NORTH.offsetY, zCoord+ForgeDirection.NORTH.offsetZ) == Blocks.chest)
	    		{
	    			this.currentOutput = 2;
	    			return;
	    		}
	    		break;
	    	case 3: // south
	    		if(worldObj.getBlock(xCoord+ForgeDirection.EAST.offsetX, yCoord+ForgeDirection.EAST.offsetY, zCoord+ForgeDirection.EAST.offsetZ) == Blocks.chest)
	    		{
	    			this.currentOutput = 3;
	    			return;
	    		}
	    		break;
	    	case 4: // west
	    		if(worldObj.getBlock(xCoord+ForgeDirection.SOUTH.offsetX, yCoord+ForgeDirection.SOUTH.offsetY, zCoord+ForgeDirection.SOUTH.offsetZ)== Blocks.chest)
	    		{
		    		this.currentOutput = 5;
		    		return;
	    		}
	    		break;
	    	case 5: //east
	    		if(worldObj.getBlock(xCoord+ForgeDirection.WEST.offsetX, yCoord+ForgeDirection.WEST.offsetY, zCoord+ForgeDirection.WEST.offsetZ)== Blocks.chest)
	    		{
		    		this.currentOutput = 4;
		    		return;
	    		}
	    		break;
	    	}  	*/
	     	return;
	    }
	    
	    public void checkCompressor()
	    {
	    	if(worldObj.getBlock(xCoord, yCoord-1, zCoord) == AdvancedUtilitiesBlocks.steamCompressor)
	    	{
	    		TileEntitySteamCompressor tile = (TileEntitySteamCompressor) worldObj.getTileEntity(xCoord, yCoord-1, zCoord);
	    		int tempm = tile.multiplier;
	    		if(tempm > 10)
	    			tempm=10;
	    		this.speedMult = tempm;
	    		this.costMod = this.digCost*(tempm/2);
	    	}
	    	else
	    	{
	    		this.speedMult = 1;
	    		this.costMod = 0;
	    	}
	    }
	    
	    public void pushItem()
	    {
	    	int[] slot = null;
	    	int numTransfered = inventory[1].stackSize;
	    	for(int i = 1; i < inventory.length; i++)
	    	{
		    	if(inventory[i]!=null)
		    	{
		    		
		    		IInventory transfer = null;
		    		switch(currentOutput)
		    		{
		    		case 2: //North
		    			slot = TransferHelper.checkSpace(xCoord+ForgeDirection.NORTH.offsetX, yCoord+ForgeDirection.NORTH.offsetY, zCoord+ForgeDirection.NORTH.offsetZ, new ItemStack(inventory[i].getItem(), 1, inventory[i].getItemDamage()), worldObj, 3);
		    			
		    			try
		    			{
		    				transfer = (IInventory) worldObj.getTileEntity(xCoord+ForgeDirection.NORTH.offsetX, yCoord+ForgeDirection.NORTH.offsetY, zCoord+ForgeDirection.NORTH.offsetZ);
		    			}
		    			catch(Exception e)
		    			{
		    				
		    			}break;
		    		case 3: //south
		    			slot = TransferHelper.checkSpace(xCoord+ForgeDirection.SOUTH.offsetX, yCoord+ForgeDirection.SOUTH.offsetY, zCoord+ForgeDirection.SOUTH.offsetZ, new ItemStack(inventory[i].getItem(), 1, inventory[i].getItemDamage()), worldObj, 2);
		    			try
		    			{
		    				transfer = (IInventory) worldObj.getTileEntity(xCoord+ForgeDirection.SOUTH.offsetX, yCoord+ForgeDirection.SOUTH.offsetY, zCoord+ForgeDirection.SOUTH.offsetZ);
		    			}
		    			catch(Exception e)
		    			{
		    				
		    			}
		    			break;
		    		case 4: //west
		    			slot = TransferHelper.checkSpace(xCoord+ForgeDirection.WEST.offsetX, yCoord+ForgeDirection.WEST.offsetY, zCoord+ForgeDirection.WEST.offsetZ, new ItemStack(inventory[i].getItem(), 1, inventory[i].getItemDamage()), worldObj, 5);
		    			try
		    			{
		    				transfer = (IInventory) worldObj.getTileEntity(xCoord+ForgeDirection.WEST.offsetX, yCoord+ForgeDirection.WEST.offsetY, zCoord+ForgeDirection.WEST.offsetZ);
		    			}
		    			catch(Exception e)
		    			{
		    				
		    			}
		    			break;
		    		case 5: //east
		    			slot = TransferHelper.checkSpace(xCoord+ForgeDirection.EAST.offsetX, yCoord+ForgeDirection.EAST.offsetY, zCoord+ForgeDirection.EAST.offsetZ, new ItemStack(inventory[i].getItem(), 1, inventory[i].getItemDamage()), worldObj, 4);
		    			try
		    			{
		    				transfer = (IInventory) worldObj.getTileEntity(xCoord+ForgeDirection.EAST.offsetX, yCoord+ForgeDirection.EAST.offsetY, zCoord+ForgeDirection.EAST.offsetZ);
		    			}
		    			catch(Exception e)
		    			{
		    				
		    			}
		    			break;
		    		}
		    		if(slot!=null && transfer!= null)
		    		{	
		    			if(slot[1] == 0)
		    			{
		    				transfer.setInventorySlotContents(slot[0], new ItemStack(inventory[i].getItem(), numTransfered, inventory[i].getItemDamage()));
		    				transfer.markDirty();
		    				if(inventory[i].stackSize == numTransfered)
		    				{
		    					inventory[i] = null;
		    					this.markDirty();
		    					return;
		    				}
		    				inventory[i].stackSize-=numTransfered;
		    				this.markDirty();
		    				return;
		    			}
		    			if(slot[1] == 1 && transfer.getStackInSlot(slot[0]).stackSize < transfer.getInventoryStackLimit())
		    			{
			    				transfer.setInventorySlotContents(slot[0], new ItemStack(inventory[i].getItem(), transfer.getStackInSlot(slot[0]).stackSize+numTransfered, inventory[i].getItemDamage()));
			    				transfer.markDirty();
			    				if(inventory[i].stackSize == numTransfered)
			    				{
			    					inventory[i] = null;
			    					this.markDirty();
			    					return;
			    				}
			    				inventory[i].stackSize-=numTransfered;
			    				this.markDirty();
			    				return;
		    				
		    			}
		    			
		    		}
		    		
		    	}
	    	}
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
	    		return this.tank.getFluid().getFluid().getName();
	    	else
	    		return "Empty";
	    }
	    
	    /* IFluidHandler */
	    public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
	    {
	        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
	    }

	    public void openInventory() {}

	    public void closeInventory() {}

	    /**
	     * Returns true if automation can insert the given item in the given slot from the given side. Args: Slot, item,
	     * side
	     */
	    public boolean canInsertItem(int par1, ItemStack par2ItemStack, int par3)
	    {
	        return false;
	    }
	    
	    @Override
		public boolean isItemValidForSlot(int var1, ItemStack var2) {
			// TODO Auto-generated method stub
			return true;
		}

	    /**
	     * Returns true if automation can extract the given item in the given slot from the given side. Args: Slot, item,
	     * side
	     */
	    public boolean canExtractItem(int par1, ItemStack par2ItemStack, int par3)
	    {
	        return true;
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
		public int getSizeInventory() {
			// TODO Auto-generated method stub
			return inventory.length;
		}

		@Override
		public ItemStack getStackInSlot(int var1) {
			// TODO Auto-generated method stub
			return inventory[var1];
		}

		 /**
	     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
	     * new stack.
	     */
	    public ItemStack decrStackSize(int par1, int par2)
	    {
	        if (this.inventory[par1] != null)
	        {
	            ItemStack itemstack;

	            if (this.inventory[par1].stackSize <= par2)
	            {
	                itemstack = this.inventory[par1];
	                this.inventory[par1] = null;
	                return itemstack;
	            }
	            else
	            {
	                itemstack = this.inventory[par1].splitStack(par2);

	                if (this.inventory[par1].stackSize == 0)
	                {
	                    this.inventory[par1] = null;
	                }

	                return itemstack;
	            }
	        }
	        else
	        {
	            return null;
	        }
	    }

	    /**
	     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
	     * like when you close a workbench GUI.
	     */
	    public ItemStack getStackInSlotOnClosing(int par1)
	    {
	        if (this.inventory[par1] != null)
	        {
	            ItemStack itemstack = this.inventory[par1];
	            this.inventory[par1] = null;
	            return itemstack;
	        }
	        else
	        {
	            return null;
	        }
	    }

	    /**
	     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
	     */
	    public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
	    {
	        this.inventory[par1] = par2ItemStack;

	        if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
	        {
	            par2ItemStack.stackSize = this.getInventoryStackLimit();
	        }
	    }

		@Override
		public String getInventoryName() {
			// TODO Auto-generated method stub
			return "SteamQuarry";
		}

		@Override
		public boolean hasCustomInventoryName() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public int getInventoryStackLimit() {
			// TODO Auto-generated method stub
			return 64;
		}
}
