package com.sudwood.advancedutilities.blocks;

import com.sudwood.advancedutilities.client.ClientRegistering;
import com.sudwood.advancedutilities.tileentity.TileEntityQuarryFrame;
import com.sudwood.advancedutilities.tileentity.TileEntitySteamTurbine;
import com.sudwood.advancedutilities.tileentity.TileEntityWire;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class BlockWire extends BlockContainer
{
	private int type = 0;
	protected BlockWire(Material mat,int type) {
		super(mat);
		this.type = type;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int num) 
	{
		switch(type)
		{
		case 0:
			return new TileEntityWire(TileEntityWire.copperRate);
		case 1:
			return new TileEntityWire(TileEntityWire.ironRate);
		case 2:
			return new TileEntityWire(TileEntityWire.goldRate);
		}
		return new TileEntityWire(0);
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
		return ClientRegistering.wireId;
  }
	
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor) 
	{
		if(!world.isRemote)
		{
			TileEntityWire tile = (TileEntityWire) world.getTileEntity(x, y, z);
			tile.onBlocksUpdated();
		}
	}
	
	public boolean onBlockEventReceived(World world, int x, int y, int z, int id, int info)
    {
        super.onBlockEventReceived(world, x, y, z, id, info);
        if(world.isRemote)
        {
        	TileEntityWire tile = (TileEntityWire) world.getTileEntity(x, y, z);
        	if(id<tile.render.length)
        	tile.render[id] = info;
        }
        return true;
    }
	
	 public void sneakWrench(World world, int x, int y, int z, EntityPlayer player)
		{
			if(!world.isRemote)
			{
			switch(type)
			{
			case 0:
				world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(AdvancedUtilitiesBlocks.copperWire, 1)));
				this.breakBlock(world, x, y, z, this, world.getBlockMetadata(x, y, z));
				world.setBlockToAir(x, y, z);
				break;
			case 1:
				world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(AdvancedUtilitiesBlocks.ironWire, 1)));
				this.breakBlock(world, x, y, z, this, world.getBlockMetadata(x, y, z));
				world.setBlockToAir(x, y, z);
				break;
			case 2:
				world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(AdvancedUtilitiesBlocks.goldWire, 1)));
				this.breakBlock(world, x, y, z, this, world.getBlockMetadata(x, y, z));
				world.setBlockToAir(x, y, z);
				break;
			}
			
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
 	if(!world.isRemote)
 	{
 		TileEntityWire tile = (TileEntityWire) world.getTileEntity(x, y, z);
 			player.addChatMessage(new ChatComponentText(tile.getCurrentPower()+" / "+tile.getMaxPower()+" Power"));
 			return false;
 		}
 	return true;
 	
 }

}
