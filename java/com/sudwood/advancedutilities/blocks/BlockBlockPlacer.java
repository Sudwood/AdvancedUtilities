package com.sudwood.advancedutilities.blocks;

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
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import com.sudwood.advancedutilities.AdvancedUtilities;
import com.sudwood.advancedutilities.tileentity.TileEntityBlockPlacer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBlockPlacer extends BlockContainer
{
	private IIcon[] icons = new IIcon[2];
	private final Random field_149933_a = new Random();
	protected BlockBlockPlacer(Material mat) 
	{
		super(mat);
		// TODO Auto-generated constructor stub
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) 
	{
		// TODO Auto-generated method stub
		return new TileEntityBlockPlacer();
	}
	
	 public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
	    {
	    	if(player.isSneaking())
			{
				this.sneakWrench(world, x, y, z, player);
				return true;
			}
	    	player.openGui(AdvancedUtilities.instance, AdvancedUtilities.blockPlacerGui, world, x, y, z);
			return true;
	    }
	 
	  public void sneakWrench(World world, int x, int y, int z, EntityPlayer player)
		{
			if(!world.isRemote)
			{

			world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(AdvancedUtilitiesBlocks.blockPlacer, 1)));
			this.breakBlock(world, x, y, z, this, world.getBlockMetadata(x, y, z));
			this.removedByPlayer(world, player, x, y, z);
			}
		}
	
	@Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase p_149689_5_, ItemStack p_149689_6_)
    {
        int l = determineOrientation(world, x, y, z, p_149689_5_);
        world.setBlockMetadataWithNotify(x, y, z, l, 2);
    }
	 
	 @Override
	public boolean rotateBlock(World world, int x, int y, int z, ForgeDirection axis)
    {
		if(world.getBlockMetadata(x, y, z) < 5)
		{
			world.setBlockMetadataWithNotify(x, y, z, world.getBlockMetadata(x, y, z)+1, 3);
		}
		else
		{
			world.setBlockMetadataWithNotify(x, y, z, 0, 3);
		}
		return true;
    }
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
	 public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z, int side)
	    {
	    	return true;
	    }
	 
	 @SideOnly(Side.CLIENT)
	    public void registerBlockIcons(IIconRegister icon)
	    {
	        this.icons[0] = icon.registerIcon(AdvancedUtilities.MODID+":blockplacerfront");
	        this.icons[1] = icon.registerIcon(AdvancedUtilities.MODID+":blockplacerside");
	        
	    }
	 
	 @SideOnly(Side.CLIENT)
	    public IIcon getIcon(int side, int meta)
	    {
	    	switch(meta)
	    	{
	    	case 0: //up
	    		switch(side)
	    		{
	    		case 1: // top
	    			return icons[0];
	    		default:
	    			return icons[1];
	    		}
			case 1: //down
				switch(side)
	    		{
	    		case 0: // bottom
	    			return icons[0];
	    		default:
	    			return icons[1];
	    		}
			case 2: //North
				switch(side)
	    		{
	    		case 3: // north
	    			return icons[0];
	    		default:
	    			return icons[1];
	    		}
			case 3: //south
				switch(side)
	    		{
	    		case 2: // south
	    			return icons[0];
	    		default:
	    			return icons[1];
	    		}
			case 4: //east
				switch(side)
	    		{
	    		case 5: // west
	    			return icons[0];
	    		default:
	    			return icons[1];
	    		}
			case 5: //west
				switch(side)
	    		{
	    		case 4: //east
	    			return icons[0];
	    		default:
	    			return icons[1];
	    		}
			}
	    	return icons[0];
	    }
	 
	 public void breakBlock(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_, int p_149749_6_)
	    {
			TileEntityBlockPlacer tileentityfurnace = (TileEntityBlockPlacer)p_149749_1_.getTileEntity(p_149749_2_, p_149749_3_, p_149749_4_);
	            

	            if (tileentityfurnace != null)
	            {
	                for (int i1 = 0; i1 < tileentityfurnace.getSizeInventory(); ++i1)
	                {
	                    ItemStack itemstack = tileentityfurnace.getStackInSlot(i1);

	                    if (itemstack != null)
	                    {
	                        float f = this.field_149933_a.nextFloat() * 0.8F + 0.1F;
	                        float f1 = this.field_149933_a.nextFloat() * 0.8F + 0.1F;
	                        float f2 = this.field_149933_a.nextFloat() * 0.8F + 0.1F;

	                        while (itemstack.stackSize > 0)
	                        {
	                            int j1 = this.field_149933_a.nextInt(21) + 10;

	                            if (j1 > itemstack.stackSize)
	                            {
	                                j1 = itemstack.stackSize;
	                            }

	                            itemstack.stackSize -= j1;
	                            EntityItem entityitem = new EntityItem(p_149749_1_, (double)((float)p_149749_2_ + f), (double)((float)p_149749_3_ + f1), (double)((float)p_149749_4_ + f2), new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));

	                            if (itemstack.hasTagCompound())
	                            {
	                                entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
	                            }

	                            float f3 = 0.05F;
	                            entityitem.motionX = (double)((float)this.field_149933_a.nextGaussian() * f3);
	                            entityitem.motionY = (double)((float)this.field_149933_a.nextGaussian() * f3 + 0.2F);
	                            entityitem.motionZ = (double)((float)this.field_149933_a.nextGaussian() * f3);
	                            p_149749_1_.spawnEntityInWorld(entityitem);
	                        }
	                    }
	                }

	                p_149749_1_.func_147453_f(p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_);
	            }
	            super.breakBlock(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_, p_149749_6_);
	        }

}
