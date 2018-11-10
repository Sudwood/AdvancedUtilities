package com.sudwood.advancedutilities.tileentity;

import com.sudwood.advancedutilities.config.ServerOptions;
import com.sudwood.advancedutilities.fluids.AdvancedUtilitiesFluids;

import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;

public class TileEntityHPBoiler extends TileEntityBoiler
{
	public TileEntityHPBoiler()
	{
		super();
		fuelUseage = 10;
	}
	@Override
	public void makeSteam()
    {
    	this.fill(ForgeDirection.UNKNOWN, new FluidStack(AdvancedUtilitiesFluids.fluidSteam, ServerOptions.steamCreationRate*15*steamMulti), true);
    	this.waterTank.drain(ServerOptions.steamCreationRate*steamMulti, true);
    	worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }
	
}
