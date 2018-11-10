package com.sudwood.advancedutilities.tileentity;

import com.sudwood.advancedutilities.TransferHelper;

import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityRationedItemTube extends TileEntityItemTube
{
	@Override
	  public void pushItem()
    {
    	switch(worldObj.getBlockMetadata(xCoord, yCoord, zCoord))
    	{
    	case 1: // up
    		TransferHelper.pushItemRationed(this, xCoord, yCoord, zCoord, ForgeDirection.UP, worldObj, numTransfered);
	    	break;
    	case 0:// down
    		TransferHelper.pushItemRationed(this, xCoord, yCoord, zCoord, ForgeDirection.DOWN, worldObj, numTransfered);
	    	break;
    	case 3:// south
    		TransferHelper.pushItemRationed(this, xCoord, yCoord, zCoord, ForgeDirection.SOUTH, worldObj, numTransfered);
	    	break;
    	case 2:// north
    		TransferHelper.pushItemRationed(this, xCoord, yCoord, zCoord, ForgeDirection.NORTH, worldObj, numTransfered);
	    	break;
    	case 4:// west
    		TransferHelper.pushItemRationed(this, xCoord, yCoord, zCoord, ForgeDirection.WEST, worldObj, numTransfered);
	    	break;
    	case 5:// east
    		TransferHelper.pushItemRationed(this, xCoord, yCoord, zCoord, ForgeDirection.EAST, worldObj, numTransfered);
	    	break;
    	}
    }
}
