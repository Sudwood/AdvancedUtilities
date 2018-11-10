package com.sudwood.advancedutilities.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.sudwood.advancedutilities.AdvancedUtilities;
import com.sudwood.advancedutilities.tileentity.TileEntityTrash;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockTrash extends BlockContainer
{
	private final Random field_149933_a = new Random();
	private IIcon[] icons = new IIcon[3];

	protected BlockTrash(Material mat) 
	{
		super(mat);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int p_149915_2_) 
	{
		return new TileEntityTrash();
	}
	
	@SideOnly(Side.CLIENT)
	  public void registerBlockIcons(IIconRegister icon)
	  {
		 icons[0] =  icon.registerIcon("advancedutilities:trashblock");
	  }
	
	 @SideOnly(Side.CLIENT)
	    public IIcon getIcon(int side, int meta)
	    {
	       return icons[0];
	    }
	 @Override
	 public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	    {
	        if (par1World.isRemote)
	        {
	            return true;
	        }
	        else
	        {
	            TileEntity var10 = par1World.getTileEntity(par2, par3, par4);
	            if(var10 == null || par5EntityPlayer.isSneaking()){
	            	
	            	return false;
	            	}
	            if (var10 != null&& var10 instanceof TileEntityTrash)
	            {
	            	par5EntityPlayer.openGui(AdvancedUtilities.instance, AdvancedUtilities.trashGui, par1World, par2, par3, par4);
	            }

	            return true;
	        }
	    }

	public void breakBlock(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_, int p_149749_6_)
    {

        super.breakBlock(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_, p_149749_6_);
    }
	
}
