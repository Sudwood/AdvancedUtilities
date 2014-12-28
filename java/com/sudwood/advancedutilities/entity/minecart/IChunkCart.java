package com.sudwood.advancedutilities.entity.minecart;

import net.minecraftforge.common.ForgeChunkManager.Ticket;

public interface IChunkCart {
	 public void loadChunk(int chunkX, int chunkZ);
	 public void unLoadChunk(int chunkX, int chunkZ);
	 public void loadTicket(Ticket ticket);
}
