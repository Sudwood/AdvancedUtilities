package com.sudwood.advancedutilities;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeChunkManager.Ticket;

import com.google.common.collect.Lists;
import com.sudwood.advancedutilities.blocks.AdvancedUtilitiesBlocks;
import com.sudwood.advancedutilities.entity.minecart.EntityChunkLoadingCart;
import com.sudwood.advancedutilities.entity.minecart.IChunkCart;
import com.sudwood.advancedutilities.tileentity.TileEntityChunkLoader;

public class AdvancedUtilitiesChunkLoadCallback implements ForgeChunkManager.OrderedLoadingCallback
{

	public static final int ChunkLoaderBlockID = 0;
	public static final int ChunkLoaderCartID = 1;
	@Override
	public void ticketsLoaded(List<Ticket> tickets, World world) 
	{
		
		for(Ticket ticket: tickets)
		{
			int type = ticket.getModData().getInteger("type");
			if(type == ChunkLoaderBlockID)
			{
				int blockX = ticket.getModData().getInteger("blockX");
				int blockY = ticket.getModData().getInteger("blockY");
				int blockZ = ticket.getModData().getInteger("blockZ");
				TileEntityChunkLoader tcl = (TileEntityChunkLoader) world.getTileEntity(blockX, blockY, blockZ);
				tcl.loadTicket(ticket);
			}
			else if(type == ChunkLoaderCartID)
			{
				IChunkCart entity = (IChunkCart) world.getEntityByID(ticket.getEntity().getEntityId());
				entity.loadTicket(ticket);
			}
		}
		
	}

	@Override
	public List<Ticket> ticketsLoaded(List<Ticket> tickets, World world, int maxTicketCount) 
	{
		List<Ticket> validTickets = Lists.newArrayList();
		for(Ticket ticket: tickets)
		{
			int type = ticket.getModData().getInteger("type");
			if(type == ChunkLoaderBlockID)
			{
				int blockX = ticket.getModData().getInteger("blockX");
				int blockY = ticket.getModData().getInteger("blockY");
				int blockZ = ticket.getModData().getInteger("blockZ");
				Block block = world.getBlock(blockX, blockY, blockZ);
				if(block == AdvancedUtilitiesBlocks.chunkLoader)
				{
					validTickets.add(ticket);
				}
			}
			if(type == ChunkLoaderCartID)
			{
				if(ticket.getEntity() instanceof IChunkCart)
				{
					validTickets.add(ticket);
				}
			}
		}
		return validTickets;
	}
	
}
