package com.sudwood.advancedutilities.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.sudwood.advancedutilities.AdvancedUtilities;
import com.sudwood.advancedutilities.client.ClientRegistering;
import com.sudwood.advancedutilities.tileentity.TileEntityFluidTube;
import com.sudwood.advancedutilities.tileentity.TileEntityItemTube;
import com.sudwood.advancedutilities.tileentity.TileEntityRestrictedItemTube;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockTube extends BlockContainer
{
	private int Type;
	 protected BlockTube(Material p_i45386_1_, int type) {
		super(p_i45386_1_);
		Type = type;
		// TODO Auto-generated constructor stub
	}

	/**
     * Called when the block is placed in the world.
     */
	 @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase p_149689_5_, ItemStack p_149689_6_)
    {
        int l = determineOrientation(world, x, y, z, p_149689_5_);
        world.setBlockMetadataWithNotify(x, y, z, l, 2);
    }
    
   @SideOnly(Side.CLIENT)
  public void registerBlockIcons(IIconRegister icon)
  {
	 this.blockIcon = icon.registerIcon("advancedutilities:bronzemachine");
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
	public boolean isOpaqueCube()
    {
        return false;
    }
	public int getRenderType()
    {
		return ClientRegistering.tubeId;
    }

    /**
     * gets the way this piston should face for that entity that placed it.
     */
    public static int determineOrientation(World p_150071_0_, int p_150071_1_, int p_150071_2_, int p_150071_3_, EntityLivingBase p_150071_4_)
    {
        if (MathHelper.abs((float)p_150071_4_.posX - (float)p_150071_1_) < 2.0F && MathHelper.abs((float)p_150071_4_.posZ - (float)p_150071_3_) < 2.0F)
        {
            double d0 = p_150071_4_.posY + 1.82D - (double)p_150071_4_.yOffset;

            if (d0 - (double)p_150071_2_ > 2.0D)
            {
                return 1;
            }

            if ((double)p_150071_2_ - d0 > 0.0D)
            {
                return 0;
            }
        }

        int l = MathHelper.floor_double((double)(p_150071_4_.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        return l == 0 ? 2 : (l == 1 ? 5 : (l == 2 ? 3 : (l == 3 ? 4 : 0)));
    }
    
    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
        float f = 0.0225F;
        if(world.getBlockMetadata(x, y, z) == 1 || world.getBlockMetadata(x, y, z) == 0)
        	return AxisAlignedBB.getAABBPool().getAABB((double)((float)x + f*15), (double)y, (double)((float)z + f*14), (double)((float)(x + 1) - f*14), (double)((float)(y + 1) - f), (double)((float)(z + 1) - f*14));
        if(world.getBlockMetadata(x, y, z) == 2 || world.getBlockMetadata(x, y, z) == 3)
        	return AxisAlignedBB.getAABBPool().getAABB((double)((float)x + f*15), (double)y+f*14, (double)z, (double)((float)(x + 1) - f*14), (double)((float)(y + 1) - f*14), (double)((float)(z + 1) - f));
        else
        	return AxisAlignedBB.getAABBPool().getAABB((double)x, (double)y+f*14, (double)((float)z + f*14), (double)((float)(x + 1) - f), (double)((float)(y + 1) - f*14), (double)((float)(z + 1) - f*14));
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
    {
    	float f = 0.0225F;
    	if(world.getBlockMetadata(x, y, z) == 0 || world.getBlockMetadata(x, y, z) == 1)
    	{
    		this.setBlockBounds(f*14, 0, f*14, f*30, 1, f*30);
    	}
    	if(world.getBlockMetadata(x, y, z) == 2 || world.getBlockMetadata(x, y, z) == 3)
    	{
    		this.setBlockBounds(f*14, f*14, 0, f*30, f*30, 1);
    	}
    	if(world.getBlockMetadata(x, y, z) == 4 || world.getBlockMetadata(x, y, z) == 5)
    	{
    		this.setBlockBounds(0, f*14, f*14, 1, f*30, f*30);
    	}
    }
    /**
     * Returns the bounding box of the wired rectangular prism to render.
     */
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z)
    {
        float f = 0.0225F;
        if(world.getBlockMetadata(x, y, z) == 1 || world.getBlockMetadata(x, y, z) == 0)
        	return AxisAlignedBB.getAABBPool().getAABB((double)((float)x + f*15), (double)y, (double)((float)z + f*14), (double)((float)(x + 1) - f*14), (double)((float)(y + 1) - f), (double)((float)(z + 1) - f*14));
        if(world.getBlockMetadata(x, y, z) == 2 || world.getBlockMetadata(x, y, z) == 3)
        	return AxisAlignedBB.getAABBPool().getAABB((double)((float)x + f*15), (double)y+f*14, (double)z, (double)((float)(x + 1) - f*14), (double)((float)(y + 1) - f*14), (double)((float)(z + 1) - f));
        else
        	return AxisAlignedBB.getAABBPool().getAABB((double)x, (double)y+f*14, (double)((float)z + f*14), (double)((float)(x + 1) - f), (double)((float)(y + 1) - f*14), (double)((float)(z + 1) - f*14));
    }

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		// TODO Auto-generated method stub
		if(Type == 0)
			return new TileEntityItemTube();
		if(Type == 1)
			return new TileEntityFluidTube();
		if(Type == 2)
			return new TileEntityRestrictedItemTube();
		return null;
	}
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
		if(Type == 0)
		{
			player.openGui(AdvancedUtilities.instance, AdvancedUtilities.itemTubeGui, world, x, y, z);
			return true;
		}
		if(Type == 1)
		{
			player.openGui(AdvancedUtilities.instance, AdvancedUtilities.fluidTubeGui, world, x, y, z);
			return true;
		}
		if(Type == 2)
		{
			player.openGui(AdvancedUtilities.instance, AdvancedUtilities.restrictedItemTubeGui, world, x, y, z);
			return true;
		}
		return false;
    }
}
