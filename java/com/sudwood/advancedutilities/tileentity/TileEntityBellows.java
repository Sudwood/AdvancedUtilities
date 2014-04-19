package com.sudwood.advancedutilities.tileentity;

import net.minecraft.tileentity.TileEntity;

public class TileEntityBellows extends TileEntity
{
	private float yScale = 1;
	private boolean isGrowing = false;
	
	@Override
	public void updateEntity()
	{
		if(yScale >= 1 && isGrowing)
        {
        	isGrowing = false;
        }
        if(yScale <= 0.5 && !isGrowing)
        {
        	isGrowing = true;
        }
        if(isGrowing)
        {
        	yScale+=0.051;
        }
        else
        {
        	yScale-=0.051;
        }
	}
	
	public float getYScale()
    {
    	return yScale;
    }
}
