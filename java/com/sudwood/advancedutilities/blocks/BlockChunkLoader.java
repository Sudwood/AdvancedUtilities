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
import net.minecraft.world.World;

public class BlockChunkLoader extends BlockContainer {

	public BlockChunkLoader(Material material) {
		super(material);
		// TODO Auto-generated constructor stub
	}
	
	@SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister icon)
    {
		this.blockIcon = icon.registerIcon("advancedutilities:chunkloader"+AdvancedUtilities.textureSize);
    }

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		// TODO Auto-generated method stub
		return new TileEntityChunkLoader();
	}
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor)
    {
		if(!world.isRemote)
		{
			((TileEntityChunkLoader) world.getTileEntity(x, y, z)).setIsGettingRedstone(world.isBlockIndirectlyGettingPowered(x, y, z));
		}
    }
	public void onBlockAdded(World world, int x, int y, int z) 
	{
		super.onBlockAdded(world, x, y, z);
		if(!world.isRemote)
		{
			TileEntityChunkLoader tile = (TileEntityChunkLoader) world.getTileEntity(x, y, z);
			tile.loadChunks();
		}
	}
	public void breakBlock(World world, int x, int y, int z, Block oldBlock, int oldMetadata)
    {
		if(!world.isRemote)
		{
			TileEntityChunkLoader tile = (TileEntityChunkLoader) world.getTileEntity(x, y, z);
			tile.unloadChunks();
		}
		super.breakBlock(world, x, y, z, oldBlock, oldMetadata);
    }
}
