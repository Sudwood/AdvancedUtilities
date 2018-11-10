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
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.sudwood.advancedutilities.AdvancedUtilities;
import com.sudwood.advancedutilities.client.ClientRegistering;
import com.sudwood.advancedutilities.items.AdvancedUtilitiesItems;
import com.sudwood.advancedutilities.tileentity.TileEntityBellows;
import com.sudwood.advancedutilities.tileentity.TileEntityBoiler;
import com.sudwood.advancedutilities.tileentity.TileEntityHPBoiler;
import com.sudwood.advancedutilities.tileentity.TileEntitySteamCharger;
import com.sudwood.advancedutilities.tileentity.TileEntitySteamCompressor;
import com.sudwood.advancedutilities.tileentity.TileEntitySteamCrusher;
import com.sudwood.advancedutilities.tileentity.TileEntitySteamFurnace;
import com.sudwood.advancedutilities.tileentity.TileEntitySteamSmeltry;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSteamMachine extends BlockContainer
{
	private int type;
	private final Random field_149933_a = new Random();
	protected BlockSteamMachine(Material mat, int type) {
		super(mat);
		this.type = type;
		// TODO Auto-generated constructor stub
	}
	@Override
	public TileEntity createNewTileEntity(World var1, int var2) 
	{
		switch(type)
		{
		case 0:
			return new TileEntityBoiler();
		case 1:
			return new TileEntitySteamCrusher();
		case 2:
			return new TileEntitySteamFurnace();
		case 3:
			return new TileEntitySteamSmeltry();
		case 4:
			return new TileEntityBellows();
		case 5:
			return new TileEntitySteamCompressor();
		case 6:
			return new TileEntitySteamCharger();
		case 7:
			return new TileEntityHPBoiler();
		default:
				return null;
		}
	}
	
	public void breakBlock(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_, int p_149749_6_)
    {
        if (!false && type != 4 && type != 5)
        {
        	IInventory tileentityfurnace;
        	switch(type)
        	{
        	case 0:
        		tileentityfurnace = (TileEntityBoiler)p_149749_1_.getTileEntity(p_149749_2_, p_149749_3_, p_149749_4_);
        		break;
    		case 1:
    			tileentityfurnace = (TileEntitySteamCrusher)p_149749_1_.getTileEntity(p_149749_2_, p_149749_3_, p_149749_4_);
    			break;
    		case 2:
    			tileentityfurnace = (TileEntitySteamFurnace)p_149749_1_.getTileEntity(p_149749_2_, p_149749_3_, p_149749_4_);
    			break;
    		case 3:
    			tileentityfurnace = (TileEntitySteamSmeltry)p_149749_1_.getTileEntity(p_149749_2_, p_149749_3_, p_149749_4_);
    			break;
    		case 6:
    			tileentityfurnace = (TileEntitySteamCharger)p_149749_1_.getTileEntity(p_149749_2_, p_149749_3_, p_149749_4_);
    			break;
    		case 7:
    			tileentityfurnace = (TileEntityHPBoiler)p_149749_1_.getTileEntity(p_149749_2_, p_149749_3_, p_149749_4_);
    			break;

    			default:
    				tileentityfurnace = (TileEntitySteamSmeltry)p_149749_1_.getTileEntity(p_149749_2_, p_149749_3_, p_149749_4_);
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
	/**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
	@Override
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
    
    public void sneakWrench(World world, int x, int y, int z, EntityPlayer player)
	{
		if(!world.isRemote)
		{
			switch(type)
			{
			case 0:
				world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(AdvancedUtilitiesBlocks.steamBoiler, 1)));
				break;
			case 1:
				world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(AdvancedUtilitiesBlocks.steamCrusher, 1)));
				break;
			case 2:
				world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(AdvancedUtilitiesBlocks.steamFurnace, 1)));
				break;
			case 3:
				world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(AdvancedUtilitiesBlocks.steamSmeltry, 1)));
				break;
			case 4:
				world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(AdvancedUtilitiesBlocks.bellows, 1)));
				break;
			case 5:
				world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(AdvancedUtilitiesBlocks.steamCompressor, 1)));
				break;
			case 6:
				world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(AdvancedUtilitiesBlocks.steamCharger, 1)));
				break;
			case 7:
				world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(AdvancedUtilitiesBlocks.hpBoiler, 1)));
				break;
			}

			this.breakBlock(world, x, y, z, this, world.getBlockMetadata(x, y, z));
		}
		this.removedByPlayer(world, player, x, y, z);
	}
    
    /**
     * Called when the block is placed in the world.
     */
    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase p_149689_5_, ItemStack p_149689_6_)
    {
        int l = MathHelper.floor_double((double)(p_149689_5_.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l == 0)
        {
        	world.setBlockMetadataWithNotify(x, y, z, 2, 2);
        }

        if (l == 1)
        {
        	world.setBlockMetadataWithNotify(x, y, z, 5, 2);
        }

        if (l == 2)
        {
        	world.setBlockMetadataWithNotify(x, y, z, 3, 2);
        }

        if (l == 3)
        {
        	world.setBlockMetadataWithNotify(x, y, z, 4, 2);
        }
        
        if(type == 5)
        {
        	TileEntitySteamCompressor tile = (TileEntitySteamCompressor) world.getTileEntity(x, y, z);
        	tile.placedCheckMulti();
        }

    }
	
	/**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
    	if(player.isSneaking())
		{
			this.sneakWrench(world, x, y, z, player);
			return true;
		}
    	switch(type)
		{
		case 0:
			player.openGui(AdvancedUtilities.instance, AdvancedUtilities.steamBoilerGui, world, x, y, z);
			return true;
		case 1:
			player.openGui(AdvancedUtilities.instance, AdvancedUtilities.steamCrusherGui, world, x, y, z);
			return true;
		case 2:
			player.openGui(AdvancedUtilities.instance, AdvancedUtilities.steamFurnaceGui, world, x, y, z);
			return true;
		case 3:
			player.openGui(AdvancedUtilities.instance, AdvancedUtilities.steamSmeltryGui, world, x, y, z);
			return true;
		case 4:
			return false;
		case 5:
			player.addChatMessage(new ChatComponentText(((TileEntitySteamCompressor)world.getTileEntity(x, y, z)).getTankAmount()+" mB Steam"+"  MULT "+((TileEntitySteamCompressor)world.getTileEntity(x, y, z)).multiplier));
			return true;
		case 6:
			TileEntitySteamCharger tile = (TileEntitySteamCharger) world.getTileEntity(x, y, z);
			if(tile.canAddItem() && player.getCurrentEquippedItem() == null)
			{
				if(!world.isRemote)
				player.addChatMessage(new ChatComponentText(tile.getTankAmount()+" mB Steam"));
				return true;
			}
			if(tile.canAddItem() && (player.getCurrentEquippedItem().getItem() == AdvancedUtilitiesItems.pnumaticGun || player.getCurrentEquippedItem().getItem() == AdvancedUtilitiesItems.steamJetpack || player.getCurrentEquippedItem().getItem() == AdvancedUtilitiesItems.jackHammer || player.getCurrentEquippedItem().getItem() == AdvancedUtilitiesItems.steamLegs))
			{
				tile.addItem(player.getCurrentEquippedItem());
				player.setCurrentItemOrArmor(0, null);
				return true;
			}
			if(tile.canTakeItem() && player.getCurrentEquippedItem() == null)
			{
				player.setCurrentItemOrArmor(0, tile.takeItem());
				return true;
			}
			return true;
		case 7:
			player.openGui(AdvancedUtilities.instance, AdvancedUtilities.steamBoilerGui, world, x, y, z);
			return true;
		default:
				return false;
		}

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
		switch(type)
		{
		case 0:
			return ClientRegistering.boilerId;
		case 1:
			return ClientRegistering.steamCrusherId;
		case 2:
			return ClientRegistering.steamFurnaceId;
		case 3:
			return ClientRegistering.steamSmeltryId;
		case 4:
			return ClientRegistering.bellowsId;
		case 5:
			return ClientRegistering.steamCompressorId;
		case 6:
			return ClientRegistering.steamChargerId;
		case 7:
			return ClientRegistering.boilerId;
		}
        return ClientRegistering.kilnId;
    }
	
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor) 
	{
		switch(type)
		{
		case 0:
			((TileEntityBoiler)world.getTileEntity(x, y, z)).checkBellows();
			break;
		case 1:
			((TileEntitySteamCrusher)world.getTileEntity(x, y, z)).findInventory();
			break;
		case 2:
			((TileEntitySteamFurnace)world.getTileEntity(x, y, z)).findInventory();
			break;
		case 3:
			((TileEntitySteamSmeltry)world.getTileEntity(x, y, z)).findInventory();
			break;
		default:
				
		}
	}
	

}
