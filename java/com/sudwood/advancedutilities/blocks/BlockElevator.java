package com.sudwood.advancedutilities.blocks;

import java.util.List;

import com.sudwood.advancedutilities.AdvancedUtilities;
import com.sudwood.advancedutilities.tileentity.TileEntityElevator;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockElevator extends BlockContainer{

	private IIcon icon;
	protected BlockElevator(Material mat) {
		super(mat);
		// TODO Auto-generated constructor stub
	}
	
	public boolean isOpaqueCube()
    {
        return false;
    }
	
	public void addCollisionBoxesToList(World par1World, int par2, int par3, int par4, AxisAlignedBB par5AxisAlignedBB, List par6List, Entity ent)
    {
	if(ent instanceof EntityLiving || ent instanceof EntityItem || ent instanceof EntityPlayer)
	{
		this.setBlockBounds(0F,0F,0F,0F,0F,0F);
	}
	else
	{
		this.setBlockBounds(0F,0F,0F,1F,1F,1F);
	}
    super.addCollisionBoxesToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, ent);
    }
	
	@SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World p_149633_1_, int p_149633_2_, int p_149633_3_, int p_149633_4_)
    {
        float f = 0.0625F;
        return AxisAlignedBB.getBoundingBox(0, 0, 0, 1, 1, 1);
    }

	 public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity ent)
	    {
	        ent.motionY = 5.0;
	        ent.fallDistance = 0;
	    }
	 
	 @Override
	 public boolean hasTileEntity(int meta) {
	 	return true;
	 }

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		// TODO Auto-generated method stub
		TileEntityElevator tile = new TileEntityElevator();
		return tile;
		
	}
	
	@SideOnly(Side.CLIENT)
    public boolean isBlockNormalCube()
    {
		return false;
    }
	
	public boolean renderAsNormalBlock()
    {
        return false;
    }
	
	public boolean isNormalCube(IBlockAccess world, int x, int y, int z)
    {
		return false;
    }
	
	@SideOnly(Side.CLIENT)
	public int getRenderType()
    {
		return -1;
    }
	
	@SideOnly(Side.CLIENT)
	  public void registerBlockIcons(IIconRegister icon)
	  {
		this.icon = icon.registerIcon("advancedutilities:bronzeblock"+AdvancedUtilities.textureSize);
	  }
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int meta)
    {
        return icon;
    }
	 
	 
}
