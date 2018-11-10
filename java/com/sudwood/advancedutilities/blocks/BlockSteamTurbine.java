package com.sudwood.advancedutilities.blocks;

import com.sudwood.advancedutilities.tileentity.TileEntitySteamQuarry;
import com.sudwood.advancedutilities.tileentity.TileEntitySteamTurbine;

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

public class BlockSteamTurbine extends BlockContainer
{

	protected BlockSteamTurbine(Material mat) 
	{
		super(mat);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int num) 
	{
		return new TileEntitySteamTurbine(100);
	}

	@SideOnly(Side.CLIENT)
	  public void registerBlockIcons(IIconRegister icon)
	  {
		 this.blockIcon = icon.registerIcon("advancedutilities:bronzemachine");
	  }
	
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor) 
	{
		if(!world.isRemote)
		{
			((TileEntitySteamTurbine) world.getTileEntity(x, y, z)).onBlocksUpdated();
		}
	}
	
	 public void sneakWrench(World world, int x, int y, int z, EntityPlayer player)
		{
			if(!world.isRemote)
			{

			world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(AdvancedUtilitiesBlocks.steamTurbine, 1)));
			this.breakBlock(world, x, y, z, this, world.getBlockMetadata(x, y, z));
			this.removedByPlayer(world, player, x, y, z);
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
    		TileEntitySteamTurbine tile = (TileEntitySteamTurbine) world.getTileEntity(x, y, z);
    			player.addChatMessage(new ChatComponentText(tile.getCurrentPower()+" / "+tile.getMaxPower()+" Power"));
    			player.addChatMessage(new ChatComponentText(tile.tank.getFluidAmount()+" / "+tile.tank.getCapacity()+" Steam"));
    			return false;
    		}
    	return true;
    	
    }
}
