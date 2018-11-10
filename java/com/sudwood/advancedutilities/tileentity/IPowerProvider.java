package com.sudwood.advancedutilities.tileentity;

import net.minecraftforge.common.util.ForgeDirection;

public interface IPowerProvider 
{
	public void sendPower();
	public boolean addPower(int powerToAdd, ForgeDirection dir);
	public int getTransferAmount();
	public int getCurrentPower();
	public int getMaxPower();
}
