package com.sudwood.advancedutilities.blocks;

import java.util.ArrayList;

import com.sudwood.advancedutilities.client.ClientRegistering;
import com.sudwood.advancedutilities.fluids.AdvancedUtilitiesFluids;
import com.sudwood.advancedutilities.items.AdvancedUtilitiesItems;
import com.sudwood.advancedutilities.tileentity.TileEntityPortaChest;
import com.sudwood.advancedutilities.tileentity.TileEntityTank;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidContainerRegistry;

public class BlockTank extends BlockContainer
{

	public boolean power = false;
	protected BlockTank(Material p_i45386_1_) 
	{
		super(p_i45386_1_);
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2)
	{
		return new TileEntityTank();
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
		return ClientRegistering.tankId;
  }
  public boolean canProvidePower()
  {
      return power;
  }
  
  /**
   * If this returns true, then comparators facing away from this block will use the value from
   * getComparatorInputOverride instead of the actual redstone signal strength.
   */
  public boolean hasComparatorInputOverride()
  {
      return false;
  }

  /**
   * If hasComparatorInputOverride returns true, the return value from this is used instead of the redstone signal
   * strength when this block inputs to a comparator.
   */
  public int getComparatorInputOverride(World p_149736_1_, int p_149736_2_, int p_149736_3_, int p_149736_4_, int p_149736_5_)
  {
      return 0;
  }
  @Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z, EntityPlayer player)
  {
	  TileEntityTank tile = (TileEntityTank) world.getTileEntity(target.blockX, target.blockY, target.blockZ);
		ItemStack temp = null;
		if(tile!=null)
		{
			temp = new ItemStack(AdvancedUtilitiesBlocks.blockTank);
			NBTTagCompound tag = new NBTTagCompound();
			tile.tank.writeToNBT(tag);
			temp.setTagCompound(tag);
		}
		
      return temp;
  }
  
  public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
  {
	  if(player.getCurrentEquippedItem() != null)
	  {
		  if(player.isSneaking()&&player.getCurrentEquippedItem().getItem() == AdvancedUtilitiesItems.bronzeWrench)
			{
				this.sneakWrench(world, x, y, z, player);
				return true;
			}
		  if(FluidContainerRegistry.isFilledContainer(player.getCurrentEquippedItem()))
		  {
			  if(world.getTileEntity(x, y, z) != null)
			  {
				  TileEntityTank tile = (TileEntityTank) world.getTileEntity(x, y, z);
				  tile.fill(ForgeDirection.UNKNOWN, FluidContainerRegistry.getFluidForFilledItem(player.getCurrentEquippedItem()), true);
				  if(!player.capabilities.isCreativeMode)
				  {
					  player.inventory.mainInventory[player.inventory.currentItem] = new ItemStack(Items.bucket);
					 // player.inventory.consumeInventoryItem(player.getCurrentEquippedItem().getItem());
					 // player.inventory.addItemStackToInventory(new ItemStack(Items.bucket, 1));
				  }
				  world.markBlockForUpdate(x, y, z);
			  }
			  return true;
		  }
		  if(FluidContainerRegistry.isEmptyContainer(player.getCurrentEquippedItem()))
		  {
			  if(world.getTileEntity(x, y, z) != null)
			  {
				  TileEntityTank tile = (TileEntityTank) world.getTileEntity(x, y, z);
				  if(tile.tank.getFluidAmount() > 0 && tile.tank.getFluid().getFluid()!= AdvancedUtilitiesFluids.fluidSteam)
				  {
					  if(player.inventory.getCurrentItem().stackSize >1)
					  {
						  player.inventory.addItemStackToInventory(FluidContainerRegistry.fillFluidContainer(tile.drain(ForgeDirection.UNKNOWN, FluidContainerRegistry.BUCKET_VOLUME, true), player.getCurrentEquippedItem()));
						  if(!player.capabilities.isCreativeMode)
						  {
							  player.inventory.getCurrentItem().stackSize-=1;
							  if(player.inventory.getCurrentItem().stackSize <= 0)
							  {
								  player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
							  }
						  }
					  }
					  else
					  {
						  if(!player.capabilities.isCreativeMode)
							  player.inventory.mainInventory[player.inventory.currentItem] = FluidContainerRegistry.fillFluidContainer(tile.drain(ForgeDirection.UNKNOWN, FluidContainerRegistry.BUCKET_VOLUME, true), player.getCurrentEquippedItem());
						  else
						  {
							  tile.drain(ForgeDirection.UNKNOWN, FluidContainerRegistry.BUCKET_VOLUME, true);
						  }
					  }
					  
					  world.markBlockForUpdate(x, y, z);
				  }
			  }
			  return true;
		  }
		  if(player.getCurrentEquippedItem().getItem() == AdvancedUtilitiesItems.bronzeWrench)
		  {
			  TileEntityTank tile = (TileEntityTank) world.getTileEntity(x, y, z);
			  tile.increaseMaxTime();
			  if(!world.isRemote)
			  player.addChatComponentMessage(new ChatComponentText("Power Delay: "+tile.maxTime/2+" ticks."));
		  }
	  }
	  if(!world.isRemote)
	  {
		  if(((TileEntityTank)world.getTileEntity(x, y, z)).tank.getFluidAmount() > 0)
			  player.addChatComponentMessage(new ChatComponentText(((TileEntityTank)world.getTileEntity(x, y, z)).tank.getFluidAmount()+" mB " +((TileEntityTank)world.getTileEntity(x, y, z)).tank.getFluid().getFluid().getName()));
		  else
			  player.addChatComponentMessage(new ChatComponentText(((TileEntityTank)world.getTileEntity(x, y, z)).tank.getFluidAmount()+" Empty"));
		  world.markBlockForUpdate(x, y, z);
	  }
	  return true;
  }
  
  public int isProvidingWeakPower(IBlockAccess p_149709_1_, int p_149709_2_, int p_149709_3_, int p_149709_4_, int p_149709_5_)
  {
	  if(this.canProvidePower())
		  return 15;
	  else
		  return 0;
  }
  
/*  @Override
  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
  {
	  ArrayList<ItemStack> item = new ArrayList<ItemStack>();
	  ItemStack stack = new ItemStack(AdvancedUtilitiesBlocks.blockTank, 1);
	  if(stack.getTagCompound() == null)
	  {
		  stack.setTagCompound(new NBTTagCompound());
	  }
	  NBTTagCompound tag = stack.getTagCompound();
	  if(world.getTileEntity(x, y, z) != null)
	  {
		  if(world.getTileEntity(x, y, z) instanceof TileEntityTank)
		  {
			  TileEntityTank tile = (TileEntityTank) world.getTileEntity(x, y, z);
			  tile.tank.writeToNBT(tag);
			  stack.setTagCompound(tag);
			  
		  }
	  }
	  item.add(stack);
	  return item;
  }*/
  
  @Override
  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
  {
      ArrayList<ItemStack> ret = super.getDrops(world, x, y, z, metadata, fortune);
      TileEntityTank te = (TileEntityTank)world.getTileEntity(x, y, z);
      if (te != null && te.tank != null)
      {
    	  NBTTagCompound tag = new NBTTagCompound();
    	  te.tank.writeToNBT(tag);
    	  ItemStack temp = new ItemStack(AdvancedUtilitiesBlocks.blockTank ,1);
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
		  TileEntityTank te = (TileEntityTank) world.getTileEntity(x, y, z);
		  if(tag!=null)
		  te.tank.readFromNBT(tag);
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
