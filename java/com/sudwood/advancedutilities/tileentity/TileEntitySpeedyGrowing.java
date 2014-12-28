package com.sudwood.advancedutilities.tileentity;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.IPlantable;

public class TileEntitySpeedyGrowing extends TileEntity
{
	private int tickTime = 0;
	@Override
	public boolean canUpdate()
	{
		return true;
	}
	
	@Override
	public void updateEntity()
	{
		tickTime++;
		System.out.println(tickTime);
		if(tickTime == 200)
		{
			worldObj.getBlock(xCoord, yCoord, zCoord).updateTick(worldObj, xCoord, yCoord, zCoord, new Random());
			tickTime =0;
		}
	}
}
