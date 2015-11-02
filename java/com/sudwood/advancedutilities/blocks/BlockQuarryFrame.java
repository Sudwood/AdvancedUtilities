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
import com.sudwood.advancedutilities.tileentity.TileEntityQuarryFrame;
import com.sudwood.advancedutilities.tileentity.TileEntitySteamCharger;
import com.sudwood.advancedutilities.tileentity.TileEntitySteamCompressor;
import com.sudwood.advancedutilities.tileentity.TileEntitySteamCrusher;
import com.sudwood.advancedutilities.tileentity.TileEntitySteamFurnace;
import com.sudwood.advancedutilities.tileentity.TileEntitySteamQuarry;
import com.sudwood.advancedutilities.tileentity.TileEntitySteamSmeltry;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockQuarryFrame extends BlockContainer
{
	private int type;
	private final Random field_149933_a = new Random();
	protected BlockQuarryFrame(Material mat) {
		super(mat);
		// TODO Auto-generated constructor stub
	}
	@Override
	public TileEntity createNewTileEntity(World var1, int var2) 
	{
		return new TileEntityQuarryFrame();
	}
	
	
	/**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
	@Override
    public void onBlockAdded(World world, int p_149726_2_, int p_149726_3_, int p_149726_4_)
    {
        super.onBlockAdded(world, p_149726_2_, p_149726_3_, p_149726_4_);
        if(!world.isRemote)
		{
	        TileEntityQuarryFrame tile = (TileEntityQuarryFrame) world.getTileEntity(p_149726_2_, p_149726_3_, p_149726_4_);
	        tile.onBlocksUpdated();
		}
    }
    
    public void sneakWrench(World world, int x, int y, int z, EntityPlayer player)
	{
		if(!world.isRemote)
		{

		world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(AdvancedUtilitiesBlocks.quarryFrame, 1)));
		this.breakBlock(world, x, y, z, this, world.getBlockMetadata(x, y, z));
		this.removedByPlayer(world, player, x, y, z);
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
        return ClientRegistering.quarryFrameId;
    }
	
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor) 
	{
		if(!world.isRemote)
		{
			TileEntityQuarryFrame tile = (TileEntityQuarryFrame) world.getTileEntity(x, y, z);
			tile.onBlocksUpdated();
		}
	}
	
	public boolean onBlockEventReceived(World world, int x, int y, int z, int id, int info)
    {
        super.onBlockEventReceived(world, x, y, z, id, info);
        if(world.isRemote)
        {
        	TileEntityQuarryFrame tile = (TileEntityQuarryFrame) world.getTileEntity(x, y, z);
        	if(id<tile.render.length)
        	tile.render[id] = info;
        }
        return true;
    }
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
		if(player.isSneaking())
		{
			this.sneakWrench(world, x, y, z, player);
			return true;
		}
		return false;
    }
	
	

}
