package com.sudwood.advancedutilities.tileentity;

import java.util.List;

import com.sudwood.advancedutilities.TransferHelper;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityMagnetTube extends TileEntityItemTube
{
	@Override
	public void pullItem()
	{
		switch(worldObj.getBlockMetadata(xCoord, yCoord, zCoord))
    	{
    	case 1: // up
    		doPullItem(1);
	    	break;
    	case 0:// down
    		doPullItem(0);
	    	break;
    	case 3:// south
    		doPullItem(3);
	    	break;
    	case 2:// north
    		doPullItem(2);
	    	break;
    	case 4:// west
    		doPullItem(4);
	    	break;
    	case 5:// east
    		doPullItem(5);
	    	break;
    	}
	}
	
	@Override
    public void updateEntity()
    {
    	if(checkPower)
    	{
	    	if(!worldObj.isRemote && !worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord))
	    	{
		    	if(inventory[0]!=null || inventory[1]!=null || inventory[2]!=null || inventory[3]!=null || inventory[4]!=null)
		    		pushItem();
		    	
		    	if((inventory[0] ==null || inventory[0].stackSize<64) || (inventory[1] ==null || inventory[1].stackSize<64) || (inventory[2] ==null || inventory[2].stackSize<64) || (inventory[3] ==null || inventory[3].stackSize<64) || (inventory[4] ==null || inventory[4].stackSize<64))
		    	{
		    		pullItem();
		    	}
	    	}
    	}
    	if(!checkPower)
    	{
    		if(!worldObj.isRemote)
	    	{
		    	if(inventory[0]!=null || inventory[1]!=null || inventory[2]!=null || inventory[3]!=null || inventory[4]!=null)
		    		pushItem();
		    	
		    	if((inventory[0] ==null || inventory[0].stackSize<64) || (inventory[1] ==null || inventory[1].stackSize<64) || (inventory[2] ==null || inventory[2].stackSize<64) || (inventory[3] ==null || inventory[3].stackSize<64) || (inventory[4] ==null || inventory[4].stackSize<64))
		    	{
		    		pullItem();
		    	}
	    	}
    	}
    }
	
	public void doPullItem(	int dir)
	{
		float distancex = 16;
		float distancez = 16;
        float distancey = 8;
        AxisAlignedBB bounding = null;
        switch(dir)
        {
        case 1: // up
        	bounding = AxisAlignedBB.getBoundingBox(xCoord-(distancex/2), yCoord, zCoord-(distancez/2), xCoord+(distancex), yCoord+(distancey), zCoord+(distancez));
	    	break;
    	case 0:// down
    		bounding = AxisAlignedBB.getBoundingBox(xCoord-(distancex/2), yCoord-(distancey), zCoord-(distancez/2), xCoord+(distancex), yCoord, zCoord+(distancez));
	    	break;
    	case 3:// south
    		bounding = AxisAlignedBB.getBoundingBox(xCoord-(distancex/2), yCoord, zCoord-(distancez/2), xCoord+(distancex), yCoord+(distancey), zCoord+(distancez));
	    	break;
    	case 2:// north
    		bounding = AxisAlignedBB.getBoundingBox(xCoord-(distancex/2), yCoord, zCoord-distancez, xCoord+(distancex), yCoord+(distancey), zCoord+(distancez/2));
	    	break;
    	case 4:// west
    		bounding = AxisAlignedBB.getBoundingBox(xCoord-distancex, yCoord, zCoord-(distancez/2), xCoord+(distancex/2), yCoord+(distancey), zCoord+(distancez));
	    	break;
    	case 5:// east
    		bounding = AxisAlignedBB.getBoundingBox(xCoord-(distancex/2), yCoord, zCoord-(distancez/2), xCoord+(distancex), yCoord+(distancey), zCoord+(distancez));
	    	break;
        }
        
		List<EntityItem> items = worldObj.getEntitiesWithinAABB(EntityItem.class, bounding);
        for (EntityItem item : items) 
        {
            int can = TransferHelper.canFitInventory(item.getEntityItem(), this);
            if(can>=0)
            {
            	if(inventory[can] == null)
            	{
            		inventory[can] = item.getEntityItem();
            	}
            	else
            	{
            		inventory[can].stackSize+=item.getEntityItem().stackSize;
            	}
            	item.setDead();
            }
        }
	}
}
