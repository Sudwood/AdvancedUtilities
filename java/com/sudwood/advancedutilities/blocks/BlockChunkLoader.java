package com.sudwood.advancedutilities.blocks;

import com.sudwood.advancedutilities.AdvancedUtilities;
import com.sudwood.advancedutilities.tileentity.TileEntityChunkLoader;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockChunkLoader extends BlockContainer {

	private int type;
	private IIcon side = null;
	public BlockChunkLoader(Material material, int type) {
		super(material);
		this.type = type;
		// TODO Auto-generated constructor stub
	}
	
	@SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister icon)
    {
		if(type == 0)
			this.blockIcon = icon.registerIcon("advancedutilities:chunkloader"+AdvancedUtilities.textureSize);
		else if(type == 1)
		{
			side = icon.registerIcon("advancedutilities:dumychunkchestup");
			this.blockIcon = icon.registerIcon("advancedutilities:dumychunkchesttop");
		}
		else if(type == 2)
		{
			side = icon.registerIcon("advancedutilities:dumytank");
			this.blockIcon = icon.registerIcon("advancedutilities:dumytanktop");
		}
		else if(type == 3)
		{
			side = icon.registerIcon("advancedutilities:dumychunktankup");
			this.blockIcon = icon.registerIcon("advancedutilities:dumychunktanktop");
		}
    }

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		// TODO Auto-generated method stub
		if(type == 0)
		return new TileEntityChunkLoader();
		else
			return null;
	}
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor)
    {
		if(!world.isRemote && type == 0)
		{
			((TileEntityChunkLoader) world.getTileEntity(x, y, z)).setIsGettingRedstone(world.isBlockIndirectlyGettingPowered(x, y, z));
		}
    }
	public void onBlockAdded(World world, int x, int y, int z) 
	{
		super.onBlockAdded(world, x, y, z);
		if(!world.isRemote && type == 0)
		{
			TileEntityChunkLoader tile = (TileEntityChunkLoader) world.getTileEntity(x, y, z);
			tile.loadChunks();
		}
	}
	public void breakBlock(World world, int x, int y, int z, Block oldBlock, int oldMetadata)
    {
		if(!world.isRemote && type == 0)
		{
			TileEntityChunkLoader tile = (TileEntityChunkLoader) world.getTileEntity(x, y, z);
			tile.unloadChunks();
		}
		super.breakBlock(world, x, y, z, oldBlock, oldMetadata);
    }
	
	@Override
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int iside, int meta)
    {
		if(type == 0)
		return this.blockIcon;
		else
		{
			if(iside == 1)
				return this.blockIcon;
			else
				return side;
		}
		
    }
}
