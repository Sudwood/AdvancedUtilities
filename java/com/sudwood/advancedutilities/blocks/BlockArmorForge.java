package com.sudwood.advancedutilities.blocks;

import java.util.Random;

import com.sudwood.advancedutilities.AdvancedUtilities;
import com.sudwood.advancedutilities.client.ClientRegistering;
import com.sudwood.advancedutilities.tileentity.TileEntityArmorForge;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockArmorForge extends BlockContainer
{
	private final Random field_149933_a = new Random();
	protected BlockArmorForge(Material mat) {
		super(mat);
		
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		// TODO Auto-generated method stub
		return new TileEntityArmorForge();
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
	
	@SideOnly(Side.CLIENT)
	public int getRenderType()
    {
        return ClientRegistering.toolForgeId;
    }
	public void breakBlock(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_, int p_149749_6_)
    {
		TileEntityArmorForge tile = (TileEntityArmorForge) p_149749_1_.getTileEntity(p_149749_2_, p_149749_3_, p_149749_4_);
		 if (tile != null)
        {
            for (int i1 = 0; i1 < tile.getSizeInventory(); ++i1)
            {
                ItemStack itemstack = tile.getStackInSlot(i1);

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

	            if (var10 != null&& var10 instanceof TileEntityArmorForge)
	            {
	            	par5EntityPlayer.openGui(AdvancedUtilities.instance, AdvancedUtilities.armorForgeGui, par1World, par2, par3, par4);
	            }

	            return true;
	        }
	    }
	
	
}
