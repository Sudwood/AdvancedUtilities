package com.sudwood.advancedutilities.blocks;

import java.util.ArrayList;
import java.util.List;

import com.sudwood.advancedutilities.AdvancedUtilities;
import com.sudwood.advancedutilities.client.ClientRegistering;
import com.sudwood.advancedutilities.tileentity.TileEntityPortaChest;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockPortaChest extends BlockContainer
{
	private IIcon[] icons = new IIcon[5];
	int type;
	protected BlockPortaChest(Material mat, int ty) 
	{
		super(mat);
		type = ty;
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) 
	{
		// TODO Auto-generated method stub
		return new TileEntityPortaChest(type);
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
	@Override
	public boolean rotateBlock(World world, int x, int y, int z, ForgeDirection axis)
    {
		if(world.getBlockMetadata(x, y, z) < 5)
		{
			world.setBlockMetadataWithNotify(x, y, z, world.getBlockMetadata(x, y, z)+1, 3);
		}
		else
		{
			world.setBlockMetadataWithNotify(x, y, z, 2, 3);
		}
		return true;
    }
	 @Override
	  public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack stack)
	  {
	        int l = determineOrientation(world, x, y, z, player);
	        world.setBlockMetadataWithNotify(x, y, z, l, 2);
	        if(stack!=null)
	        {
	        	NBTTagCompound tag = stack.getTagCompound();
	        	if(tag!=null)
	        	{
	        		TileEntityPortaChest tile = (TileEntityPortaChest) world.getTileEntity(x, y, z);
	        		if(tile!=null)
	        		{
	        			tile.readFromNBTWithoutCoords(tag);	  
	        			tile.setType(stack.getItemDamage());
	        		}
	        	}
	        }
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
		return ClientRegistering.portaChestId;
    }
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
		if(player.isSneaking()&&player.getHeldItem() == null)
		{
			ItemStack temp = null;
			TileEntityPortaChest tile = (TileEntityPortaChest) world.getTileEntity(x, y, z);
			switch(tile.getType())
			{
			case TileEntityPortaChest.WOOD:
				temp  = new ItemStack(AdvancedUtilitiesBlocks.portaChestWood, 1,TileEntityPortaChest.WOOD);
				break;
			case TileEntityPortaChest.BRONZE:
				temp  = new ItemStack(AdvancedUtilitiesBlocks.portaChestBronze, 1,TileEntityPortaChest.BRONZE);
				break;
			case TileEntityPortaChest.GOLD:
				temp  = new ItemStack(AdvancedUtilitiesBlocks.portaChestGold, 1,TileEntityPortaChest.GOLD);
				break;
			case TileEntityPortaChest.DIAMOND:
				temp  = new ItemStack(AdvancedUtilitiesBlocks.portaChestDiamond, 1,TileEntityPortaChest.DIAMOND);
				break;
			case TileEntityPortaChest.PLATINUM:
				temp  = new ItemStack(AdvancedUtilitiesBlocks.portaChestPlatinum, 1,TileEntityPortaChest.PLATINUM);
				break;
			default:
				temp  = new ItemStack(AdvancedUtilitiesBlocks.portaChestWood, 1,TileEntityPortaChest.WOOD);
					break;
			}
			
			NBTTagCompound tag = new NBTTagCompound();
			tile.writeToNBTWithoutCoords(tag);
			temp.setTagCompound(tag);
			world.setBlockToAir(x, y, z);
			boolean put = player.inventory.addItemStackToInventory(temp);
			if(!put)
			{
				EntityItem item = new EntityItem(world);
				item.setEntityItemStack(temp);
				world.spawnEntityInWorld(item);
			}
			return true;
		}
		else
		{
			TileEntityPortaChest tile = (TileEntityPortaChest) world.getTileEntity(x, y, z);
			tile.openInventory();
			player.openGui(AdvancedUtilities.instance, AdvancedUtilities.portaChestBlockGui, world, x, y, z);
			return true;
		}
    }
	
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z, EntityPlayer player)
    {
		TileEntityPortaChest tile = (TileEntityPortaChest) world.getTileEntity(target.blockX, target.blockY, target.blockZ);
		ItemStack temp = null;
		switch(tile.getType())
		{
		case TileEntityPortaChest.WOOD:
			temp  = new ItemStack(AdvancedUtilitiesBlocks.portaChestWood, 1,TileEntityPortaChest.WOOD);
			break;
		case TileEntityPortaChest.BRONZE:
			temp  = new ItemStack(AdvancedUtilitiesBlocks.portaChestBronze, 1,TileEntityPortaChest.BRONZE);
			break;
		case TileEntityPortaChest.GOLD:
			temp  = new ItemStack(AdvancedUtilitiesBlocks.portaChestGold, 1,TileEntityPortaChest.GOLD);
			break;
		case TileEntityPortaChest.DIAMOND:
			temp  = new ItemStack(AdvancedUtilitiesBlocks.portaChestDiamond, 1,TileEntityPortaChest.DIAMOND);
			break;
		case TileEntityPortaChest.PLATINUM:
			temp  = new ItemStack(AdvancedUtilitiesBlocks.portaChestPlatinum, 1,TileEntityPortaChest.PLATINUM);
			break;
		default:
			temp  = new ItemStack(AdvancedUtilitiesBlocks.portaChestWood, 1,TileEntityPortaChest.WOOD);
				break;
		}
		
		NBTTagCompound tag = new NBTTagCompound();
		tile.writeToNBTWithoutCoords(tag);
		temp.setTagCompound(tag);
        return temp;
    }
	
	@Override
	  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
	  {
	      ArrayList<ItemStack> ret = super.getDrops(world, x, y, z, metadata, fortune);
	      TileEntityPortaChest tile = (TileEntityPortaChest) world.getTileEntity(x, y, z);
	      if (tile != null)
	      {
	    	  
	  		ItemStack temp = null;
	  		switch(tile.getType())
	  		{
	  		case TileEntityPortaChest.WOOD:
	  			temp  = new ItemStack(AdvancedUtilitiesBlocks.portaChestWood, 1,TileEntityPortaChest.WOOD);
	  			break;
	  		case TileEntityPortaChest.BRONZE:
	  			temp  = new ItemStack(AdvancedUtilitiesBlocks.portaChestBronze, 1,TileEntityPortaChest.BRONZE);
	  			break;
	  		case TileEntityPortaChest.GOLD:
	  			temp  = new ItemStack(AdvancedUtilitiesBlocks.portaChestGold, 1,TileEntityPortaChest.GOLD);
	  			break;
	  		case TileEntityPortaChest.DIAMOND:
	  			temp  = new ItemStack(AdvancedUtilitiesBlocks.portaChestDiamond, 1,TileEntityPortaChest.DIAMOND);
	  			break;
	  		case TileEntityPortaChest.PLATINUM:
	  			temp  = new ItemStack(AdvancedUtilitiesBlocks.portaChestPlatinum, 1,TileEntityPortaChest.PLATINUM);
	  			break;
	  		default:
	  			temp  = new ItemStack(AdvancedUtilitiesBlocks.portaChestWood, 1,TileEntityPortaChest.WOOD);
	  				break;
	  		}
	  		
	  		NBTTagCompound tag = new NBTTagCompound();
	  		tile.writeToNBTWithoutCoords(tag);
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
	  @Override
	public int getDamageValue(World world, int x, int y, int z)
    {
        return this.type;
    }
	  
  public int damageDropped(int p_149692_1_)
    {
        return type;
    }
  
  @SideOnly(Side.CLIENT)
  public void getSubBlocks(Item item, CreativeTabs tab, List list)
  {
      list.add(new ItemStack(item, 1, type));
  }
  
  @SideOnly(Side.CLIENT)
  public void registerBlockIcons(IIconRegister icon)
  {
	  icons[0] = icon.registerIcon(AdvancedUtilities.MODID+":chestbasewood");
	  icons[1] = icon.registerIcon(AdvancedUtilities.MODID+":chestbasebronze");
	  icons[2] = icon.registerIcon(AdvancedUtilities.MODID+":chestbasegold");
	  icons[3] = icon.registerIcon(AdvancedUtilities.MODID+":chestbasediamond");
	  icons[4] = icon.registerIcon(AdvancedUtilities.MODID+":chestbaseplatinum");
  }
  @SideOnly(Side.CLIENT)
  public IIcon getIcon(int p_149691_1_, int meta)
  {
	  return icons[type];
  }
}
