package com.sudwood.advancedutilities.tileentity;

import net.minecraft.init.Blocks;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import com.sudwood.advancedutilities.blocks.AdvancedUtilitiesBlocks;
import com.sudwood.advancedutilities.config.ServerOptions;

public class TileEntityHPBoiler extends TileEntityBoiler
{
	public TileEntityHPBoiler()
	{
		super();
		fuelUseage = 5;
	}
	@Override
	public void makeSteam()
    {
    	this.fill(ForgeDirection.UNKNOWN, new FluidStack(AdvancedUtilitiesBlocks.fluidSteam, ServerOptions.steamCreationRate*15*steamMulti), true);
    	this.waterTank.drain(ServerOptions.steamCreationRate*steamMulti, true);
    	worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }
	
}
