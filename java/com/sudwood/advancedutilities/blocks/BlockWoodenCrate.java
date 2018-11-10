package com.sudwood.advancedutilities.blocks;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.sudwood.advancedutilities.AdvancedUtilities;
import com.sudwood.advancedutilities.tileentity.TileEntityTank;
import com.sudwood.advancedutilities.tileentity.TileEntityWoodenCrate;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockWoodenCrate extends BlockContainer
{
	private final Random field_149933_a = new Random();
	private IIcon[] icons = new IIcon[3];

	protected BlockWoodenCrate(Material mat) 
	{
		super(mat);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int p_149915_2_) 
	{
		return new TileEntityWoodenCrate();
	}
	
	@SideOnly(Side.CLIENT)
	  public void registerBlockIcons(IIconRegister icon)
	  {
		 icons[0] =  icon.registerIcon("advancedutilities:woodcrate");
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

	            if (var10 != null&& var10 instanceof TileEntityWoodenCrate)
	            {
	            	par5EntityPlayer.openGui(AdvancedUtilities.instance, AdvancedUtilities.woodenCrateGui, par1World, par2, par3, par4);
	            }

	            return true;
	        }
	    }

	public void breakBlock(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_, int p_149749_6_)
    {
        super.breakBlock(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_, p_149749_6_);
    }
	
	@Override
	  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
	  {
	      ArrayList<ItemStack> ret = super.getDrops(world, x, y, z, metadata, fortune);
	      TileEntityWoodenCrate te = (TileEntityWoodenCrate)world.getTileEntity(x, y, z);
	      if (te != null)
	      {
	    	  NBTTagCompound tag = new NBTTagCompound();
	    	  te.writeInventory(tag);
	    	  ItemStack temp = new ItemStack(AdvancedUtilitiesBlocks.blockWoodenCrate,1);
	    	  temp.setTagCompound(tag);
	    	  ret.remove(0);
	    	  ret.add(temp);
	      }
	      return ret;
	  }
	  @Override
	  public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z, boolean willHarvest)
	  {
	      if (willHarvest) return true; //If it will harvest, delay deletion of the block until after getDrops
	      return super.removedByPlayer(world, player, x, y, z, willHarvest);
	  }
	  /**
	   * Called when the player destroys a block with an item that can harvest it. (i, j, k) are the coordinates of the
	   * block and l is the block's subtype/damage.
	   */
	  @Override
	  public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int meta)
	  {
	      super.harvestBlock(world, player, x, y, z, meta);
	      world.setBlockToAir(x, y, z);
	  }
	  
	  public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase placer, ItemStack stack) 
	  {
		  super.onBlockPlacedBy(world, x, y, z, placer, stack);
		  if(stack.getItem() != null)
		  {
			  NBTTagCompound tag = stack.getTagCompound();
			  TileEntityWoodenCrate te = (TileEntityWoodenCrate) world.getTileEntity(x, y, z);
			  if(tag!=null)
			  te.readInventory(tag);
		  }
	  }
	  
	  
	  public void sneakWrench(World world, int x, int y, int z, EntityPlayer player)
		{
			if(!world.isRemote)
			{
				this.harvestBlock(world, player, x,y,z, world.getBlockMetadata(x, y, z));
			}
			this.removedByPlayer(world, player, x, y, z, true);
		}
	
}
