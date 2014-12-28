package com.sudwood.advancedutilities.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

import com.sudwood.advancedutilities.tileentity.TileEntitySpeedyGrowing;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSpeedyGrowing extends BlockContainer
{

	protected BlockSpeedyGrowing(Material p_i45394_1_) {
		super(p_i45394_1_);
		// TODO Auto-generated constructor stub
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		// TODO Auto-generated method stub
		return new TileEntitySpeedyGrowing();
	}
	
	public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plantable)
    {
		return true;
    }
	@SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister icon)
    {
		this.blockIcon = icon.registerIcon("advancedutilities:copper");
    }
	@Override
	public void updateTick(World worldObj, int xCoord, int yCoord, int zCoord, Random random)
	{
		if(worldObj.getBlock(xCoord, yCoord+1, zCoord)!= null)
		{
			Block block = worldObj.getBlock(xCoord, yCoord+1, zCoord);
			if(block.getTickRandomly())
			{
				block.updateTick(worldObj, xCoord, yCoord+1, zCoord, random);
			}
		}
	}

}
