package com.sudwood.advancedutilities.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import com.sudwood.advancedutilities.AdvancedUtilities;
import com.sudwood.advancedutilities.TransferHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBlockBreaker extends Block{

	private IIcon[] icons = new IIcon[6];
	protected BlockBlockBreaker(Material mat) 
	{
		super(mat);
	}
	    /**
	     * Returns the quantity of items to drop on block destruction.
	     */
    public int quantityDropped(Random p_149745_1_)
    {
       return 1;
    }
    
    /**
     * Called when the block is placed in the world.
     */
	 @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase p_149689_5_, ItemStack p_149689_6_)
    {
        int l = determineOrientation(world, x, y, z, p_149689_5_);
        world.setBlockMetadataWithNotify(x, y, z, l, 2);
        System.out.println(world.getBlockMetadata(x, y, z));
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
			world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(AdvancedUtilitiesBlocks.blockBreaker, 1)));
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
		return false;
    }
	public boolean isOpaqueCube()
    {
        return false;
    }
	@SideOnly(Side.CLIENT)
    public boolean isBlockNormalCube()
    {
		return false;
    }
	public boolean isNormalCube()
    {
        return false;
    }
	
	public boolean isBlockSolid(IBlockAccess p_149747_1_, int p_149747_2_, int p_149747_3_, int p_149747_4_, int p_149747_5_)
    {
        return false;
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
     * Determine if this block can make a redstone connection on the side provided,
     * Useful to control which sides are inputs and outputs for redstone wires.
     *
     * Side:
     *  -1: UP
     *   0: NORTH
     *   1: EAST
     *   2: SOUTH
     *   3: WEST
     *
     * @param world The current world
     * @param x X Position
     * @param y Y Position
     * @param z Z Position
     * @param side The side that is trying to make the connection
     * @return True to make the connection
     */
    public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z, int side)
    {
    	return true;
    }
    
    //world , thisx, thisy, thisz, neighboor
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) 
    {
    	if(world.getBlockPowerInput(x, y, z) > 0)
    	{
    		switch(world.getBlockMetadata(x, y, z))
    		{
    		case 0: //up
    			if(!world.isAirBlock(x, y+1, z) && world.getBlock(x, y+1, z).getDrops(world, x, y+1, z, world.getBlockMetadata(x, y+1, z), 0)!=null && !world.getBlock(x, y+1, z).getDrops(world, x, y+1, z, world.getBlockMetadata(x, y+1, z), 0).isEmpty())
    			{
    				ItemStack drops = world.getBlock(x, y+1, z).getDrops(world, x, y+1, z, world.getBlockMetadata(x, y+1, z), 0).get(0);
    				if(world.getBlock(x, y-1, z).hasTileEntity(world.getBlockMetadata(x, y-1, z)))
    				{
    					TileEntity tile = world.getTileEntity(x, y-1, z);
    					if(tile instanceof IInventory)
    					{
    						IInventory inv = (IInventory) tile;
    						int[] slot =  TransferHelper.checkSpace(x, y-1, z, drops, world, 1);
    						if(slot!=null)
    			    		{	
    			    			if(slot[1] == 0)
    			    			{
    			    				ItemStack temp = drops.copy();
    			    				inv.setInventorySlotContents(slot[0], temp);
    			    				inv.markDirty();
    			    			}
    			    			if(slot[1] == 1 && inv.getStackInSlot(slot[0]).stackSize < inv.getInventoryStackLimit())
    			    			{
    			    				ItemStack stack = drops.copy();
    			    				if(slot[2]!=0)
    			    					stack.stackSize = slot[2]+inv.getStackInSlot(slot[0]).stackSize;
    			    				else
    			    					stack.stackSize +=inv.getStackInSlot(slot[0]).stackSize;
    			    				
    			    				inv.setInventorySlotContents(slot[0], stack);
    			    				inv.markDirty();
    			    			}
    			    			world.setBlockToAir(x, y+1, z);
    			    		}
    					}
    				}
    				else
    				{
    					world.spawnEntityInWorld(new EntityItem(world, x, y-1, z, drops));
    					world.setBlockToAir(x, y+1, z);
    				}
    			}
    			break;
    		case 1: //down
    			if(!world.isAirBlock(x, y-1, z) && world.getBlock(x, y-1, z).getDrops(world, x, y-1, z, world.getBlockMetadata(x, y-1, z), 0)!=null && !world.getBlock(x, y-1, z).getDrops(world, x, y-1, z, world.getBlockMetadata(x, y-1, z), 0).isEmpty())
    			{
    				ItemStack drops = world.getBlock(x, y-1, z).getDrops(world, x, y-1, z, world.getBlockMetadata(x, y-1, z), 0).get(0);
    				if(world.getBlock(x, y+1, z).hasTileEntity(world.getBlockMetadata(x, y+1, z)))
    				{
    					TileEntity tile = world.getTileEntity(x, y+1, z);
    					if(tile instanceof IInventory)
    					{
    						IInventory inv = (IInventory) tile;
    						int[] slot =  TransferHelper.checkSpace(x, y+1, z, drops, world, 0);
    						if(slot!=null)
    			    		{	
    			    			if(slot[1] == 0)
    			    			{
    			    				ItemStack temp = drops.copy();
    			    				inv.setInventorySlotContents(slot[0], temp);
    			    				inv.markDirty();
    			    			}
    			    			if(slot[1] == 1 && inv.getStackInSlot(slot[0]).stackSize < inv.getInventoryStackLimit())
    			    			{
    			    				ItemStack stack = drops.copy();
    			    				if(slot[2]!=0)
    			    					stack.stackSize = slot[2]+inv.getStackInSlot(slot[0]).stackSize;
    			    				else
    			    					stack.stackSize +=inv.getStackInSlot(slot[0]).stackSize;
    			    				
    			    				inv.setInventorySlotContents(slot[0], stack);
    			    				inv.markDirty();
    			    			}
    			    			world.setBlockToAir(x, y-1, z);
    			    		}
    					}
    				}
    				else
    				{
    					world.spawnEntityInWorld(new EntityItem(world, x, y+1, z, drops));
    					world.setBlockToAir(x, y-1, z);
    				}
    			}
    			break;
    		case 2: //North
    			if(!world.isAirBlock(x, y, z+1) && world.getBlock(x, y, z+1).getDrops(world, x, y, z+1, world.getBlockMetadata(x, y, z+1), 0)!=null && !world.getBlock(x, y, z+1).getDrops(world, x, y, z+1, world.getBlockMetadata(x, y, z+1), 0).isEmpty())
    			{
    				ItemStack drops = world.getBlock(x, y, z+1).getDrops(world, x, y, z-1, world.getBlockMetadata(x, y, z+1), 0).get(0);
    				if(world.getBlock(x, y, z-1).hasTileEntity(world.getBlockMetadata(x, y, z-1)))
    				{
    					TileEntity tile = world.getTileEntity(x, y, z-1);
    					if(tile instanceof IInventory)
    					{
    						IInventory inv = (IInventory) tile;
    						int[] slot =  TransferHelper.checkSpace(x, y, z-1, drops, world, 3);
    						if(slot!=null)
    			    		{	
    			    			if(slot[1] == 0)
    			    			{
    			    				ItemStack temp = drops.copy();
    			    				inv.setInventorySlotContents(slot[0], temp);
    			    				inv.markDirty();
    			    			}
    			    			if(slot[1] == 1 && inv.getStackInSlot(slot[0]).stackSize < inv.getInventoryStackLimit())
    			    			{
    			    				ItemStack stack = drops.copy();
    			    				if(slot[2]!=0)
    			    					stack.stackSize = slot[2]+inv.getStackInSlot(slot[0]).stackSize;
    			    				else
    			    					stack.stackSize +=inv.getStackInSlot(slot[0]).stackSize;
    			    				
    			    				inv.setInventorySlotContents(slot[0], stack);
    			    				inv.markDirty();
    			    			}
    			    			world.setBlockToAir(x, y, z+1);
    			    		}
    					}
    				}
    				else
    				{
    					world.spawnEntityInWorld(new EntityItem(world, x, y+0.5, z-1, drops));
    					world.setBlockToAir(x, y, z+1);
    				}
    			}
    			break;
    		case 3: //south
    			if(!world.isAirBlock(x, y, z-1) && world.getBlock(x, y, z-1).getDrops(world, x, y, z-1, world.getBlockMetadata(x, y, z-1), 0)!=null && !world.getBlock(x, y, z-1).getDrops(world, x, y, z-1, world.getBlockMetadata(x, y, z-1), 0).isEmpty())
    			{
    				ItemStack drops = world.getBlock(x, y, z-1).getDrops(world, x, y, z-1, world.getBlockMetadata(x, y, z-1), 0).get(0);
    				if(world.getBlock(x, y, z+1).hasTileEntity(world.getBlockMetadata(x, y, z+1)))
    				{
    					TileEntity tile = world.getTileEntity(x, y, z+1);
    					if(tile instanceof IInventory)
    					{
    						IInventory inv = (IInventory) tile;
    						int[] slot =  TransferHelper.checkSpace(x, y, z+1, drops, world, 2);
    						if(slot!=null)
    			    		{	
    			    			if(slot[1] == 0)
    			    			{
    			    				ItemStack temp = drops.copy();
    			    				inv.setInventorySlotContents(slot[0], temp);
    			    				inv.markDirty();
    			    			}
    			    			if(slot[1] == 1 && inv.getStackInSlot(slot[0]).stackSize < inv.getInventoryStackLimit())
    			    			{
    			    				ItemStack stack = drops.copy();
    			    				if(slot[2]!=0)
    			    					stack.stackSize = slot[2]+inv.getStackInSlot(slot[0]).stackSize;
    			    				else
    			    					stack.stackSize +=inv.getStackInSlot(slot[0]).stackSize;
    			    				
    			    				inv.setInventorySlotContents(slot[0], stack);
    			    				inv.markDirty();
    			    			}
    			    			world.setBlockToAir(x, y, z-1);
    			    		}
    					}
    				}
    				else
    				{
    					world.spawnEntityInWorld(new EntityItem(world, x, y+0.5, z+1, drops));
    					world.setBlockToAir(x, y, z-1);
    				}
    			}
    			break;
    		case 4: //west
    			if(!world.isAirBlock(x+1, y, z) && world.getBlock(x+1, y, z).getDrops(world, x+1, y, z, world.getBlockMetadata(x+1, y, z), 0)!=null && !world.getBlock(x+1, y, z).getDrops(world, x+1, y, z, world.getBlockMetadata(x+1, y, z), 0).isEmpty())
    			{
    				ItemStack drops = world.getBlock(x+1, y, z).getDrops(world, x+1, y, z, world.getBlockMetadata(x+1, y, z), 0).get(0);
    				if(world.getBlock(x-1, y, z).hasTileEntity(world.getBlockMetadata(x-1, y, z)))
    				{
    					TileEntity tile = world.getTileEntity(x-1, y, z);
    					if(tile instanceof IInventory)
    					{
    						IInventory inv = (IInventory) tile;
    						int[] slot =  TransferHelper.checkSpace(x-1, y, z, drops, world, 5);
    						if(slot!=null)
    			    		{	
    			    			if(slot[1] == 0)
    			    			{
    			    				ItemStack temp = drops.copy();
    			    				inv.setInventorySlotContents(slot[0], temp);
    			    				inv.markDirty();
    			    			}
    			    			if(slot[1] == 1 && inv.getStackInSlot(slot[0]).stackSize < inv.getInventoryStackLimit())
    			    			{
    			    				ItemStack stack = drops.copy();
    			    				if(slot[2]!=0)
    			    					stack.stackSize = slot[2]+inv.getStackInSlot(slot[0]).stackSize;
    			    				else
    			    					stack.stackSize +=inv.getStackInSlot(slot[0]).stackSize;
    			    				
    			    				inv.setInventorySlotContents(slot[0], stack);
    			    				inv.markDirty();
    			    			}
    			    			world.setBlockToAir(x+1, y, z);
    			    		}
    					}
    				}
    				else
    				{
    					world.spawnEntityInWorld(new EntityItem(world, x-1.2, y+0.5, z+0.5, drops));
    					world.setBlockToAir(x+1, y, z);
    				}
    			}
    			break;
    		case 5: //east
    			if(!world.isAirBlock(x-1, y, z) && world.getBlock(x-1, y, z).getDrops(world, x-1, y, z, world.getBlockMetadata(x-1, y, z), 0)!=null && !world.getBlock(x-1, y, z).getDrops(world, x-1, y, z, world.getBlockMetadata(x-1, y, z), 0).isEmpty())
    			{
    				
    				ItemStack drops = world.getBlock(x-1, y, z).getDrops(world, x-1, y, z, world.getBlockMetadata(x-1, y, z), 0).get(0);
    				if(world.getBlock(x+1, y, z).hasTileEntity(world.getBlockMetadata(x+1, y, z)))
    				{
    					TileEntity tile = world.getTileEntity(x+1, y, z);
    					if(tile instanceof IInventory)
    					{
    						IInventory inv = (IInventory) tile;
    						int[] slot =  TransferHelper.checkSpace(x+1, y, z, drops, world, 4);
    						if(slot!=null)
    			    		{	
    			    			if(slot[1] == 0)
    			    			{
    			    				ItemStack temp = drops.copy();
    			    				inv.setInventorySlotContents(slot[0], temp);
    			    				inv.markDirty();
    			    			}
    			    			if(slot[1] == 1 && inv.getStackInSlot(slot[0]).stackSize < inv.getInventoryStackLimit())
    			    			{
    			    				ItemStack stack = drops.copy();
    			    				if(slot[2]!=0)
    			    					stack.stackSize = slot[2]+inv.getStackInSlot(slot[0]).stackSize;
    			    				else
    			    					stack.stackSize +=inv.getStackInSlot(slot[0]).stackSize;
    			    				
    			    				inv.setInventorySlotContents(slot[0], stack);
    			    				inv.markDirty();
    			    			}
    			    			world.setBlockToAir(x-1, y, z);
    			    		}
    					}
    				}
    				else
    				{
    					world.spawnEntityInWorld(new EntityItem(world, x+1.2, y+0.5, z+0.5, drops));
    					world.setBlockToAir(x-1, y, z);
    				}
    			}
    			break;
    		}
    	}
    }
    
    
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister icon)
    {
        this.icons[0] = icon.registerIcon(AdvancedUtilities.MODID+":blockbreakerfront");
        this.icons[1] = icon.registerIcon(AdvancedUtilities.MODID+":blockbreakersideup");
        this.icons[2] = icon.registerIcon(AdvancedUtilities.MODID+":blockbreakerback");
        this.icons[3] = icon.registerIcon(AdvancedUtilities.MODID+":blockbreakersidedown");
        this.icons[4] = icon.registerIcon(AdvancedUtilities.MODID+":blockbreakersideright");
        this.icons[5] = icon.registerIcon(AdvancedUtilities.MODID+":blockbreakersideleft");
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
    	switch(meta)
    	{
    	case 0: //up
    		switch(side)
    		{
    		case 0: // bottom
    			return icons[2];
    		case 1: // top
    			return icons[0];
    		case 2: // north
    			return icons[1];
    		case 3: /// south
    			return icons[1];
    		case 4:
    			return icons[1];
    		case 5:
    			return icons[1];
    		}
			break;
		case 1: //down
			switch(side)
    		{
    		case 0: // bottom
    			return icons[0];
    		case 1: // top
    			return icons[2];
    		case 2: // north
    			return icons[3];
    		case 3: // south
    			return icons[3];
    		case 4: // west
    			return icons[3];
    		case 5: //east
    			return icons[3];
    		}
			break;
		case 2: //North
			switch(side)
    		{
    		case 0: // bottom
    			return icons[3];
    		case 1: // top
    			return icons[3];
    		case 2: // north
    			return icons[2];
    		case 3: // south
    			return icons[0];
    		case 4: // west
    			return icons[4];
    		case 5: //east
    			return icons[4];
    		}
			break;
		case 3: //south
			switch(side)
    		{
    		case 0: // bottom
    			return icons[1];
    		case 1: // top
    			return icons[1];
    		case 2: // north
    			return icons[0];
    		case 3: // south
    			return icons[2];
    		case 4: // west
    			return icons[5];
    		case 5: //east
    			return icons[5];
    		}
			break;
		case 4: //east
			switch(side)
    		{
    		case 0: // bottom
    			return icons[4];
    		case 1: // top
    			return icons[4];
    		case 2: // north
    			return icons[4];
    		case 3: // south
    			return icons[4];
    		case 4: // west
    			return icons[2];
    		case 5: //east
    			return icons[0];
    		}
			break;
		case 5: //west
			switch(side)
    		{
    		case 0: // bottom
    			return icons[5];
    		case 1: // top
    			return icons[5];
    		case 2: // north
    			return icons[5];
    		case 3: // south
    			return icons[5];
    		case 4: // west
    			return icons[0];
    		case 5: //east
    			return icons[2];
    		}
			break;
		}
    	return icons[0];
    }
    
    public boolean isBeaconBase(IBlockAccess worldObj, int x, int y, int z, int beaconX, int beaconY, int beaconZ)
    {
        return true;
    }

}
