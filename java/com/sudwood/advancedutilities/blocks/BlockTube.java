package com.sudwood.advancedutilities.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.oredict.OreDictionary;

import com.sudwood.advancedutilities.AdvancedUtilities;
import com.sudwood.advancedutilities.client.ClientRegistering;
import com.sudwood.advancedutilities.items.AdvancedUtilitiesItems;
import com.sudwood.advancedutilities.tileentity.TileEntityFluidTube;
import com.sudwood.advancedutilities.tileentity.TileEntityItemTube;
import com.sudwood.advancedutilities.tileentity.TileEntityRestrictedItemTube;
import com.sudwood.advancedutilities.tileentity.TileEntitySplitterFluidTube;
import com.sudwood.advancedutilities.tileentity.TileEntitySplitterItemTube;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockTube extends BlockContainer
{
	private int Type;
	private final Random field_149933_a = new Random();

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
	@SideOnly(Side.CLIENT)
	public int getRenderType()
    {
		if(Type <= 2)
			return ClientRegistering.tubeId;
		else
			return ClientRegistering.splitterTubeId;
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
        	return AxisAlignedBB.getBoundingBox((double)((float)x + f*15), (double)y, (double)((float)z + f*14), (double)((float)(x + 1) - f*14), (double)((float)(y + 1) - f), (double)((float)(z + 1) - f*14));
        if(world.getBlockMetadata(x, y, z) == 2 || world.getBlockMetadata(x, y, z) == 3)
        	return AxisAlignedBB.getBoundingBox((double)((float)x + f*15), (double)y+f*14, (double)z, (double)((float)(x + 1) - f*14), (double)((float)(y + 1) - f*14), (double)((float)(z + 1) - f));
        else
        	return AxisAlignedBB.getBoundingBox((double)x, (double)y+f*14, (double)((float)z + f*14), (double)((float)(x + 1) - f), (double)((float)(y + 1) - f*14), (double)((float)(z + 1) - f*14));
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
        	return AxisAlignedBB.getBoundingBox((double)((float)x + f*15), (double)y, (double)((float)z + f*14), (double)((float)(x + 1) - f*14), (double)((float)(y + 1) - f), (double)((float)(z + 1) - f*14));
        if(world.getBlockMetadata(x, y, z) == 2 || world.getBlockMetadata(x, y, z) == 3)
        	return AxisAlignedBB.getBoundingBox((double)((float)x + f*15), (double)y+f*14, (double)z, (double)((float)(x + 1) - f*14), (double)((float)(y + 1) - f*14), (double)((float)(z + 1) - f));
        else
        	return AxisAlignedBB.getBoundingBox((double)x, (double)y+f*14, (double)((float)z + f*14), (double)((float)(x + 1) - f), (double)((float)(y + 1) - f*14), (double)((float)(z + 1) - f*14));
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
		if(Type == 3)
			return new TileEntitySplitterItemTube();
		if(Type == 4)
			return new TileEntitySplitterFluidTube();
		return null;
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
	
	public void sneakWrench(World world, int x, int y, int z, EntityPlayer player)
	{
		if(!world.isRemote)
		{
			if(Type == 0)
				world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(AdvancedUtilitiesBlocks.itemTube, 1)));
			if(Type == 1)
				world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(AdvancedUtilitiesBlocks.fluidTube, 1)));
			if(Type == 2)
				world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(AdvancedUtilitiesBlocks.restrictedItemTube, 1)));
			if(Type == 3)
				world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(AdvancedUtilitiesBlocks.splitterItemTube, 1)));
			if(Type == 4)
				world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(AdvancedUtilitiesBlocks.splitterFluidTube, 1)));
			this.breakBlock(world, x, y, z, this, world.getBlockMetadata(x, y, z));
		}
		this.removedByPlayer(world, player, x, y, z);
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
		if(player.isSneaking())
		{
			this.sneakWrench(world, x, y, z, player);
			return true;
		}
		if(Type == 0 && !player.isSneaking() && (player.getCurrentEquippedItem() == null || player.getCurrentEquippedItem().getItem() != AdvancedUtilitiesItems.bronzeWrench))
		{
			player.openGui(AdvancedUtilities.instance, AdvancedUtilities.itemTubeGui, world, x, y, z);
			return true;
		}
		if(Type == 1 && !player.isSneaking() && (player.getCurrentEquippedItem() == null || player.getCurrentEquippedItem().getItem() != AdvancedUtilitiesItems.bronzeWrench))
		{
			player.openGui(AdvancedUtilities.instance, AdvancedUtilities.fluidTubeGui, world, x, y, z);
			return true;
		}
		if(Type == 2 && !player.isSneaking() && (player.getCurrentEquippedItem() != null && OreDictionary.getOreIDs(player.getCurrentEquippedItem()).length > 0 && OreDictionary.getOreID("dyeBlack") == OreDictionary.getOreIDs(player.getCurrentEquippedItem())[0]))
		{
			TileEntityRestrictedItemTube tile = (TileEntityRestrictedItemTube) world.getTileEntity(x, y, z);
			tile.setBlacklist();
			if(!world.isRemote)
				player.addChatMessage(new ChatComponentText("Blacklist mode activated."));
			return false;
		}
		if(Type == 2 && !player.isSneaking() && (player.getCurrentEquippedItem() != null && OreDictionary.getOreIDs(player.getCurrentEquippedItem()).length > 0 && OreDictionary.getOreID("dyeWhite") == OreDictionary.getOreIDs(player.getCurrentEquippedItem())[0]))
		{
			TileEntityRestrictedItemTube tile = (TileEntityRestrictedItemTube) world.getTileEntity(x, y, z);
			tile.setWhitelist();
			if(!world.isRemote)
				player.addChatMessage(new ChatComponentText("Whitelist mode activated."));
			return false;
		}
		if(Type == 2 && !player.isSneaking() && (player.getCurrentEquippedItem() == null || player.getCurrentEquippedItem().getItem() != AdvancedUtilitiesItems.bronzeWrench))
		{
			player.openGui(AdvancedUtilities.instance, AdvancedUtilities.restrictedItemTubeGui, world, x, y, z);
			return true;
		}
		if(Type == 3 && !player.isSneaking() && (player.getCurrentEquippedItem() == null || player.getCurrentEquippedItem().getItem() != AdvancedUtilitiesItems.bronzeWrench))
		{
			player.openGui(AdvancedUtilities.instance, AdvancedUtilities.itemTubeGui, world, x, y, z);
			return true;
		}
		if(Type == 4 && !player.isSneaking() && (player.getCurrentEquippedItem() == null || player.getCurrentEquippedItem().getItem() != AdvancedUtilitiesItems.bronzeWrench))
		{
			player.openGui(AdvancedUtilities.instance, AdvancedUtilities.fluidTubeGui, world, x, y, z);
			return true;
		}
		return false;
    }
	
	public void breakBlock(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_, int p_149749_6_)
    {
        if (!false && Type != 1)
        {
        	IInventory tileentityfurnace;
        	switch(Type)
        	{
        	case 0:
        		tileentityfurnace = (TileEntityItemTube)p_149749_1_.getTileEntity(p_149749_2_, p_149749_3_, p_149749_4_);
        		break;
        	case 1:
        		return;
    		case 2:
    			tileentityfurnace = (TileEntityRestrictedItemTube)p_149749_1_.getTileEntity(p_149749_2_, p_149749_3_, p_149749_4_);
    			break;
    		case 3:
    			tileentityfurnace = (TileEntitySplitterItemTube)p_149749_1_.getTileEntity(p_149749_2_, p_149749_3_, p_149749_4_);
    			break;
    		case 4:
    			return;
    			

    			default:
    				tileentityfurnace = (TileEntityItemTube)p_149749_1_.getTileEntity(p_149749_2_, p_149749_3_, p_149749_4_);
    				break;
        	}
            

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
}
