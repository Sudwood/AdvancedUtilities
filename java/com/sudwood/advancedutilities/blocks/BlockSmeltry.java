package com.sudwood.advancedutilities.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.sudwood.advancedutilities.AdvancedUtilities;
import com.sudwood.advancedutilities.client.ClientRegistering;
import com.sudwood.advancedutilities.tileentity.TileEntitySmeltry;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSmeltry extends BlockContainer
{
	private final Random field_149933_a = new Random();
	protected BlockSmeltry(Material mat) {
		super(mat);
		
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		// TODO Auto-generated method stub
		return new TileEntitySmeltry();
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
	
	public void breakBlock(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_, int p_149749_6_)
    {
        if (!false)
        {
            TileEntitySmeltry tileentityfurnace = (TileEntitySmeltry)p_149749_1_.getTileEntity(p_149749_2_, p_149749_3_, p_149749_4_);

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
        }

        super.breakBlock(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_, p_149749_6_);
    }
	@SideOnly(Side.CLIENT)
	public int getRenderType()
    {
        return ClientRegistering.smeltryId;
    }
	
	@SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random p_149734_5_)
    {
        
			int l = world.getBlockMetadata(x, y, z);
            
			if( ((TileEntitySmeltry)world.getTileEntity(x, y, z)).isBurning())
			{
	            float f = (float)x + 0.5F;
	            float f1 = (float)y + 0.0F + p_149734_5_.nextFloat() * 6.0F / 16.0F;
	            float f2 = (float)z + 0.5F;
	            float f4 = p_149734_5_.nextFloat() * 0.6F - 0.3F;
	            world.spawnParticle("smoke", (double)(f), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
	            world.spawnParticle("flame", (double)(f), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
	            world.spawnParticle("smoke", (double)(f-0.3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
	            world.spawnParticle("flame", (double)(f-0.3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
	            world.spawnParticle("smoke", (double)(f+0.3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
	            world.spawnParticle("flame", (double)(f+0.3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
	            world.spawnParticle("smoke", (double)(f), (double)f1, (double)(f2 + f4+0.3), 0.0D, 0.0D, 0.0D);
	            world.spawnParticle("flame", (double)(f), (double)f1, (double)(f2 + f4+0.3), 0.0D, 0.0D, 0.0D);
	            world.spawnParticle("smoke", (double)(f), (double)f1, (double)(f2 + f4-0.3), 0.0D, 0.0D, 0.0D);
	            world.spawnParticle("flame", (double)(f), (double)f1, (double)(f2 + f4-0.3), 0.0D, 0.0D, 0.0D);
	            
	            world.spawnParticle("smoke", (double)(f), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
	            world.spawnParticle("flame", (double)(f), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
	            world.spawnParticle("smoke", (double)(f-0.3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
	            world.spawnParticle("flame", (double)(f-0.3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
	            world.spawnParticle("smoke", (double)(f+0.3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
	            world.spawnParticle("flame", (double)(f+0.3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
	            world.spawnParticle("smoke", (double)(f), (double)f1, (double)(f2 + f4+0.3), 0.0D, 0.0D, 0.0D);
	            world.spawnParticle("flame", (double)(f), (double)f1, (double)(f2 + f4+0.3), 0.0D, 0.0D, 0.0D);
	            world.spawnParticle("smoke", (double)(f), (double)f1, (double)(f2 + f4-0.3), 0.0D, 0.0D, 0.0D);
	            world.spawnParticle("flame", (double)(f), (double)f1, (double)(f2 + f4-0.3), 0.0D, 0.0D, 0.0D);
			}
    }
	
    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World p_149726_1_, int p_149726_2_, int p_149726_3_, int p_149726_4_)
    {
        super.onBlockAdded(p_149726_1_, p_149726_2_, p_149726_3_, p_149726_4_);
        this.func_149930_e(p_149726_1_, p_149726_2_, p_149726_3_, p_149726_4_);
    }

    private void func_149930_e(World p_149930_1_, int p_149930_2_, int p_149930_3_, int p_149930_4_)
    {
        if (!p_149930_1_.isRemote)
        {
            Block block = p_149930_1_.getBlock(p_149930_2_, p_149930_3_, p_149930_4_ - 1);
            Block block1 = p_149930_1_.getBlock(p_149930_2_, p_149930_3_, p_149930_4_ + 1);
            Block block2 = p_149930_1_.getBlock(p_149930_2_ - 1, p_149930_3_, p_149930_4_);
            Block block3 = p_149930_1_.getBlock(p_149930_2_ + 1, p_149930_3_, p_149930_4_);
            byte b0 = 3;

            if (block.func_149730_j() && !block1.func_149730_j())
            {
                b0 = 3;
            }

            if (block1.func_149730_j() && !block.func_149730_j())
            {
                b0 = 2;
            }

            if (block2.func_149730_j() && !block3.func_149730_j())
            {
                b0 = 5;
            }

            if (block3.func_149730_j() && !block2.func_149730_j())
            {
                b0 = 4;
            }

            p_149930_1_.setBlockMetadataWithNotify(p_149930_2_, p_149930_3_, p_149930_4_, b0, 2);
        }
    }
    
    /**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_)
    {
        int l = MathHelper.floor_double((double)(p_149689_5_.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l == 0)
        {
            p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 2, 2);
        }

        if (l == 1)
        {
            p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 5, 2);
        }

        if (l == 2)
        {
            p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 3, 2);
        }

        if (l == 3)
        {
            p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 4, 2);
        }
    }
	
	 @SideOnly(Side.CLIENT)
	  public void registerBlockIcons(IIconRegister icon)
	  {
		 this.blockIcon = Blocks.stone.getIcon(0, 0);
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

	            if (var10 != null&& var10 instanceof TileEntitySmeltry)
	            {
	            	par5EntityPlayer.openGui(AdvancedUtilities.instance, AdvancedUtilities.smeltryGui, par1World, par2, par3, par4);
	            }

	            return true;
	        }
	    }
	
	
}
