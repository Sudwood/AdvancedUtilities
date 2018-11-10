package com.sudwood.advancedutilities.tileentity;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.FakePlayerFactory;

import com.sudwood.advancedutilities.TransferHelper;

public class TileEntityBlockBreaker extends TileEntity
{
	 private ArrayList items = new ArrayList();
	 private NBTTagList tags = new NBTTagList();
	 private boolean isTreeFarm = false;
	 private boolean isEfficient = false;
	 public boolean isEfficient() {
		return isEfficient;
	}
	public void setEfficient(boolean isEfficient) {
		this.isEfficient = isEfficient;
	}

	public int multiplier = 0;
	 Random rand = new Random();
	FakePlayer player1;
	public TileEntityBlockBreaker()
	{
		try
		{
			if(worldObj instanceof WorldServer)
			{
				 player1 = FakePlayerFactory.getMinecraft((WorldServer)worldObj);
			}
		}
		catch(Exception e)
		{
			
		}
	}
	    @Override
	    public void readFromNBT(NBTTagCompound tag)
	    {
	    	tags = (NBTTagList) tag.getTag("tagslist");
	    	if(tags!=null)
	    	{
		    	for(int i =0; i<tags.tagCount(); i++)
		    	{
		    		ItemStack temp = new ItemStack(Items.stick);
		    		temp.readFromNBT(tags.getCompoundTagAt(i));
		    		items.add(temp);
		    	}
	    	}
	    	isTreeFarm=tag.getBoolean("treefarm");
	    	isEfficient = tag.getBoolean("isefficient");
	        super.readFromNBT(tag);
	    }

	    @Override
	    public void writeToNBT(NBTTagCompound tag)
	    {
	    	for(int i =0; i<items.size(); i++)
	    	{
	    		NBTTagCompound temp = new NBTTagCompound();
	    		((ItemStack)items.get(i)).writeToNBT(temp);
	    		tags.appendTag(temp);
	    	}
	    	tag.setTag("tagslist", tags);
	    	items.clear();
	    	tag.setBoolean("treefarm", isTreeFarm);
	    	tag.setBoolean("isefficient", isEfficient);
	        super.writeToNBT(tag);

	    }
	    
	    public void setTreeFarm(boolean bool)
	    {
	    	isTreeFarm = bool;
	    }
	    
	    public boolean getTreeFarm()
	    {
	    	return isTreeFarm;
	    }
	    
	    public void updateEntity()
	    {
	    	
	    	if(!worldObj.isRemote &&worldObj.getBlockPowerInput(xCoord, yCoord, zCoord)>0)
	    	{
	    		switch(worldObj.getBlockMetadata(xCoord, yCoord, zCoord))
	    		{
	    		case 0: //up
	    			if(isTreeFarm&&!worldObj.isAirBlock(xCoord, yCoord+1, zCoord)&&(worldObj.getBlock(xCoord, yCoord+1, zCoord).getMaterial()==Material.wood ||worldObj.getBlock(xCoord, yCoord+1, zCoord).getMaterial()==Material.leaves || worldObj.getBlock(xCoord, yCoord+1, zCoord).getMaterial()==Material.sponge))
	    			{
	    				final Block wood = worldObj.getBlock(xCoord, yCoord+1, zCoord);
	    				if(wood.isWood(worldObj, xCoord, yCoord+1, zCoord)|| wood.getMaterial() == Material.sponge)
	    				{
	    					if(detectTree(worldObj, xCoord,yCoord+1,zCoord, wood)) {
	        	                int meta = worldObj.getBlockMetadata(xCoord, yCoord+1, zCoord);
	        	               int temp = breakTree(worldObj, xCoord, yCoord+1, zCoord, wood, meta, player1);
	        	            	   this.sendItems(worldObj, xCoord, yCoord-1, zCoord);
	        	            	   return;
	    					}
	    				}
	    				if(wood == Blocks.wheat||wood==Blocks.carrots||wood == Blocks.potatoes)
	    				{
	    					if(worldObj.getBlockMetadata(xCoord, yCoord+1, zCoord) == 7)
	    					{
	    						ArrayList<ItemStack> drops = worldObj.getBlock(xCoord, yCoord+1, zCoord).getDrops(worldObj, xCoord, yCoord+1, zCoord, worldObj.getBlockMetadata(xCoord, yCoord+1, zCoord), 0);
	    						this.breakBlocks(worldObj, xCoord, yCoord, zCoord, 0, 1, 0, drops);
	    					}
	    				}
	    			}
	    		else if(!worldObj.isAirBlock(xCoord, yCoord+1, zCoord) && worldObj.getBlock(xCoord, yCoord+1, zCoord).getDrops(worldObj, xCoord, yCoord+1, zCoord, worldObj.getBlockMetadata(xCoord, yCoord+1, zCoord), 0)!=null && !worldObj.getBlock(xCoord, yCoord+1, zCoord).getDrops(worldObj, xCoord, yCoord+1, zCoord, worldObj.getBlockMetadata(xCoord, yCoord+1, zCoord), 0).isEmpty())
	    			{
	    			ArrayList<ItemStack>  drops = worldObj.getBlock(xCoord, yCoord+1, zCoord).getDrops(worldObj, xCoord, yCoord+1, zCoord, worldObj.getBlockMetadata(xCoord, yCoord+1, zCoord), 0);
	    				if(isTreeFarm)
	    				{
	    					if(worldObj.getBlock(xCoord, yCoord+1, zCoord) instanceof BlockSapling)
	    					{
	    						break;
	    					}
	    				}
	    				this.breakBlocks(worldObj, xCoord, yCoord, zCoord, 0, 1, 0, drops);
	    			}
	    			break;
	    		case 1: //down
	    			if(isTreeFarm&&!worldObj.isAirBlock(xCoord, yCoord-1, zCoord)&&(worldObj.getBlock(xCoord, yCoord-1, zCoord).getMaterial()==Material.wood ||worldObj.getBlock(xCoord, yCoord-1, zCoord).getMaterial()==Material.leaves || worldObj.getBlock(xCoord, yCoord-1, zCoord).getMaterial()==Material.sponge))
	    			{
	    				final Block wood = worldObj.getBlock(xCoord, yCoord-1, zCoord);
	    				if(wood.isWood(worldObj, xCoord, yCoord-1, zCoord)|| wood.getMaterial() == Material.sponge)
	    				{
	    					if(detectTree(worldObj, xCoord,yCoord-1,zCoord, wood)) {
	        	                int meta = worldObj.getBlockMetadata(xCoord, yCoord-1, zCoord);
	        	               int temp = breakTree(worldObj, xCoord, yCoord-1, zCoord, wood, meta, player1);
	        	            	   this.sendItems(worldObj, xCoord, yCoord+1, zCoord);
	        	            	   return;
	    					}
	    				}
	    				if(wood == Blocks.wheat||wood==Blocks.carrots||wood == Blocks.potatoes)
	    				{
	    					if(worldObj.getBlockMetadata(xCoord, yCoord-1, zCoord) == 7)
	    					{
	    						ArrayList<ItemStack>  drops = worldObj.getBlock(xCoord, yCoord-1, zCoord).getDrops(worldObj, xCoord, yCoord-1, zCoord, worldObj.getBlockMetadata(xCoord, yCoord-1, zCoord), 0);
	    						this.breakBlocks(worldObj, xCoord, yCoord, zCoord, 0, -1, 0, drops);
	    					}
	    				}
	    			}
	    		else if(!worldObj.isAirBlock(xCoord, yCoord-1, zCoord) && worldObj.getBlock(xCoord, yCoord-1, zCoord).getDrops(worldObj, xCoord, yCoord-1, zCoord, worldObj.getBlockMetadata(xCoord, yCoord-1, zCoord), 0)!=null && !worldObj.getBlock(xCoord, yCoord-1, zCoord).getDrops(worldObj, xCoord, yCoord-1, zCoord, worldObj.getBlockMetadata(xCoord, yCoord-1, zCoord), 0).isEmpty())
	    			{
		    			if(isTreeFarm)
	    				{
	    					if(worldObj.getBlock(xCoord, yCoord-1, zCoord) instanceof BlockSapling)
	    					{
	    						break;
	    					}
	    				}
		    			ArrayList<ItemStack>  drops = worldObj.getBlock(xCoord, yCoord-1, zCoord).getDrops(worldObj, xCoord, yCoord-1, zCoord, worldObj.getBlockMetadata(xCoord, yCoord-1, zCoord), 0);
	    				this.breakBlocks(worldObj, xCoord, yCoord, zCoord, 0, -1, 0, drops);
	    			}
	    			break;
	    		case 2: //North
	    			if(isTreeFarm&&!worldObj.isAirBlock(xCoord, yCoord, zCoord+1)&&(worldObj.getBlock(xCoord, yCoord, zCoord+1).getMaterial()==Material.wood ||worldObj.getBlock(xCoord, yCoord, zCoord+1).getMaterial()==Material.leaves || worldObj.getBlock(xCoord, yCoord, zCoord+1).getMaterial()==Material.sponge))
	    			{
	    				final Block wood = worldObj.getBlock(xCoord, yCoord, zCoord+1);
	    				if(wood.isWood(worldObj, xCoord, yCoord, zCoord+1)|| wood.getMaterial() == Material.sponge)
	    				{
	    					if(detectTree(worldObj, xCoord,yCoord,zCoord+1, wood)) {
	        	                int meta = worldObj.getBlockMetadata(xCoord, yCoord, zCoord+1);
	        	               int temp = breakTree(worldObj, xCoord, yCoord, zCoord+1, wood, meta, player1);
	        	            	   this.sendItems(worldObj, xCoord, yCoord, zCoord-1);
	        	            	   return;
	    					}
	    				}
	    				if(wood == Blocks.wheat||wood==Blocks.carrots||wood == Blocks.potatoes)
	    				{
	    					if(worldObj.getBlockMetadata(xCoord, yCoord, zCoord+1) == 7)
	    					{
	    						ArrayList<ItemStack>  drops = worldObj.getBlock(xCoord, yCoord, zCoord+1).getDrops(worldObj, xCoord, yCoord, zCoord+1, worldObj.getBlockMetadata(xCoord, yCoord, zCoord+1), 0);
	    						this.breakBlocks(worldObj, xCoord, yCoord, zCoord, 0, 0, 1, drops);
	    					}
	    				}
	    			}
	    		else if(!worldObj.isAirBlock(xCoord, yCoord, zCoord+1) && worldObj.getBlock(xCoord, yCoord, zCoord+1).getDrops(worldObj, xCoord, yCoord, zCoord+1, worldObj.getBlockMetadata(xCoord, yCoord, zCoord+1), 0)!=null && !worldObj.getBlock(xCoord, yCoord, zCoord+1).getDrops(worldObj, xCoord, yCoord, zCoord+1, worldObj.getBlockMetadata(xCoord, yCoord, zCoord+1), 0).isEmpty())
	    			{
		    			if(isTreeFarm)
	    				{
	    					if(worldObj.getBlock(xCoord, yCoord, zCoord+1) instanceof BlockSapling)
	    					{
	    						break;
	    					}
	    				}
		    			ArrayList<ItemStack>  drops = worldObj.getBlock(xCoord, yCoord, zCoord+1).getDrops(worldObj, xCoord, yCoord, zCoord-1, worldObj.getBlockMetadata(xCoord, yCoord, zCoord+1), 0);
	    				this.breakBlocks(worldObj, xCoord, yCoord, zCoord, 0, 0, 1, drops);
	    			}
	    			break;
	    		case 3: //south
	    			if(isTreeFarm&&!worldObj.isAirBlock(xCoord, yCoord, zCoord-1)&&(worldObj.getBlock(xCoord, yCoord, zCoord-1).getMaterial()==Material.wood ||worldObj.getBlock(xCoord, yCoord, zCoord-1).getMaterial()==Material.leaves || worldObj.getBlock(xCoord, yCoord, zCoord-1).getMaterial()==Material.sponge))
	    			{
	    				final Block wood = worldObj.getBlock(xCoord, yCoord, zCoord-1);
	    				if(wood.isWood(worldObj, xCoord, yCoord, zCoord-1)|| wood.getMaterial() == Material.sponge)
	    				{
	    					if(detectTree(worldObj, xCoord,yCoord,zCoord-1, wood)) {
	        	                int meta = worldObj.getBlockMetadata(xCoord, yCoord, zCoord-1);
	        	               int temp = breakTree(worldObj, xCoord, yCoord, zCoord-1, wood, meta, player1);
	        	            	   this.sendItems(worldObj, xCoord, yCoord, zCoord+1);
	        	            	   return;
	    					}
	    					
	    				}
	    				if(wood == Blocks.wheat||wood==Blocks.carrots||wood == Blocks.potatoes)
	    				{
	    					if(worldObj.getBlockMetadata(xCoord, yCoord, zCoord-1) == 7)
	    					{
	    						ArrayList<ItemStack>  drops = worldObj.getBlock(xCoord, yCoord, zCoord-1).getDrops(worldObj, xCoord, yCoord, zCoord-1, worldObj.getBlockMetadata(xCoord, yCoord, zCoord-1), 0);
	    						this.breakBlocks(worldObj, xCoord, yCoord, zCoord, 0, 0, -1, drops);
	    					}
	    				}
	    			}
	    		else if(!worldObj.isAirBlock(xCoord, yCoord, zCoord-1) && worldObj.getBlock(xCoord, yCoord, zCoord-1).getDrops(worldObj, xCoord, yCoord, zCoord-1, worldObj.getBlockMetadata(xCoord, yCoord, zCoord-1), 0)!=null && !worldObj.getBlock(xCoord, yCoord, zCoord-1).getDrops(worldObj, xCoord, yCoord, zCoord-1, worldObj.getBlockMetadata(xCoord, yCoord, zCoord-1), 0).isEmpty())
	    			{
		    			if(isTreeFarm)
	    				{
	    					if(worldObj.getBlock(xCoord, yCoord, zCoord-1) instanceof BlockSapling)
	    					{
	    						break;
	    					}
	    					if(worldObj.getBlock(xCoord, yCoord, zCoord-1) == Blocks.wheat ||worldObj.getBlock(xCoord, yCoord, zCoord-1) == Blocks.potatoes || worldObj.getBlock(xCoord, yCoord, zCoord-1) == Blocks.carrots )
	    					{
	    						if(worldObj.getBlockMetadata(xCoord, yCoord, zCoord-1) <7)
	    						{
	    							break;
	    						}
	    					}
	    				}
		    			ArrayList<ItemStack>  drops = worldObj.getBlock(xCoord, yCoord, zCoord-1).getDrops(worldObj, xCoord, yCoord, zCoord-1, worldObj.getBlockMetadata(xCoord, yCoord, zCoord-1), 0);
	    				this.breakBlocks(worldObj, xCoord, yCoord, zCoord, 0, 0, -1, drops);
	    			}
	    			break;
	    		case 4: //west
	    			if(isTreeFarm&&!worldObj.isAirBlock(xCoord+1, yCoord, zCoord)&&(worldObj.getBlock(xCoord+1, yCoord, zCoord).getMaterial()==Material.wood ||worldObj.getBlock(xCoord+1, yCoord, zCoord).getMaterial()==Material.leaves || worldObj.getBlock(xCoord+1, yCoord, zCoord).getMaterial()==Material.sponge))
	    			{
	    				final Block wood = worldObj.getBlock(xCoord+1, yCoord, zCoord);
	    				if(wood.isWood(worldObj, xCoord+1, yCoord, zCoord)|| wood.getMaterial() == Material.sponge)
	    				{
	    					if(detectTree(worldObj, xCoord+1,yCoord,zCoord, wood)) {
	        	                int meta = worldObj.getBlockMetadata(xCoord+1, yCoord, zCoord);
	        	               int temp = breakTree(worldObj, xCoord+1, yCoord, zCoord, wood, meta, player1);
	        	            	   this.sendItems(worldObj, xCoord-1, yCoord, zCoord);
	        	            	   return;
	    					}
	    					
	    				}
	    				if(wood == Blocks.wheat||wood==Blocks.carrots||wood == Blocks.potatoes)
	    				{
	    					if(worldObj.getBlockMetadata(xCoord+1, yCoord, zCoord) == 7)
	    					{
	    						ArrayList<ItemStack>  drops = worldObj.getBlock(xCoord+1, yCoord, zCoord).getDrops(worldObj, xCoord+1, yCoord, zCoord-1, worldObj.getBlockMetadata(xCoord+1, yCoord, zCoord), 0);
	    						this.breakBlocks(worldObj, xCoord, yCoord, zCoord, 1, 0, 0, drops);
	    					}
	    				}
	    			}
	    		else if(!worldObj.isAirBlock(xCoord+1, yCoord, zCoord) && worldObj.getBlock(xCoord+1, yCoord, zCoord).getDrops(worldObj, xCoord+1, yCoord, zCoord, worldObj.getBlockMetadata(xCoord+1, yCoord, zCoord), 0)!=null && !worldObj.getBlock(xCoord+1, yCoord, zCoord).getDrops(worldObj, xCoord+1, yCoord, zCoord, worldObj.getBlockMetadata(xCoord+1, yCoord, zCoord), 0).isEmpty())
	    			{
		    			if(isTreeFarm)
	    				{
	    					if(worldObj.getBlock(xCoord+1, yCoord, zCoord) instanceof BlockSapling)
	    					{
	    						break;
	    					}
	    					if(worldObj.getBlock(xCoord+1, yCoord, zCoord) == Blocks.wheat ||worldObj.getBlock(xCoord+1, yCoord, zCoord) == Blocks.potatoes || worldObj.getBlock(xCoord+1, yCoord, zCoord) == Blocks.carrots )
	    					{
	    						if(worldObj.getBlockMetadata(xCoord+1, yCoord, zCoord) <7)
	    						{
	    							break;
	    						}
	    					}
	    				}
		    			ArrayList<ItemStack>  drops = worldObj.getBlock(xCoord+1, yCoord, zCoord).getDrops(worldObj, xCoord+1, yCoord, zCoord, worldObj.getBlockMetadata(xCoord+1, yCoord, zCoord), 0);
	    				this.breakBlocks(worldObj, xCoord, yCoord, zCoord, 1, 0, 0, drops);
	    			}
	    			break;
	    		case 5: //east
	    			if(isTreeFarm&&!worldObj.isAirBlock(xCoord-1, yCoord, zCoord)&&(worldObj.getBlock(xCoord-1, yCoord, zCoord).getMaterial()==Material.wood ||worldObj.getBlock(xCoord-1, yCoord, zCoord).getMaterial()==Material.leaves || worldObj.getBlock(xCoord-1, yCoord, zCoord).getMaterial()==Material.sponge))
	    			{
	    				final Block wood = worldObj.getBlock(xCoord-1, yCoord, zCoord);
	    				if(wood.isWood(worldObj, xCoord-1, yCoord, zCoord)|| wood.getMaterial() == Material.sponge)
	    				{
	    					if(detectTree(worldObj, xCoord-1,yCoord,zCoord, wood)) {
	        	                int meta = worldObj.getBlockMetadata(xCoord-1, yCoord, zCoord);
	        	               int temp = breakTree(worldObj, xCoord-1, yCoord, zCoord, wood, meta, player1);
	        	            	   this.sendItems(worldObj, xCoord+1, yCoord, zCoord);
	        	            	   return;
	    					}
	    				}
	    				if(wood == Blocks.wheat||wood==Blocks.carrots||wood == Blocks.potatoes)
	    				{
	    					if(worldObj.getBlockMetadata(xCoord-1, yCoord, zCoord) == 7)
	    					{
	    						ArrayList<ItemStack>  drops = worldObj.getBlock(xCoord-1, yCoord, zCoord).getDrops(worldObj, xCoord-1, yCoord, zCoord-1, worldObj.getBlockMetadata(xCoord-1, yCoord, zCoord), 0);
	    						this.breakBlocks(worldObj, xCoord, yCoord, zCoord, -1, 0, 0, drops);
	    					}
	    				}
	    			}
	    		else if(!worldObj.isAirBlock(xCoord-1, yCoord, zCoord) && worldObj.getBlock(xCoord-1, yCoord, zCoord).getDrops(worldObj, xCoord-1, yCoord, zCoord, worldObj.getBlockMetadata(xCoord-1, yCoord, zCoord), 0)!=null && !worldObj.getBlock(xCoord-1, yCoord, zCoord).getDrops(worldObj, xCoord-1, yCoord, zCoord, worldObj.getBlockMetadata(xCoord-1, yCoord, zCoord), 0).isEmpty())
	    			{
		    			if(isTreeFarm)
	    				{
	    					if(worldObj.getBlock(xCoord-1, yCoord, zCoord) instanceof BlockSapling)
	    					{
	    						break;
	    					}
	    					if(worldObj.getBlock(xCoord-1, yCoord, zCoord) == Blocks.wheat ||worldObj.getBlock(xCoord-1, yCoord, zCoord) == Blocks.potatoes || worldObj.getBlock(xCoord-1, yCoord, zCoord) == Blocks.carrots )
	    					{
	    						if(worldObj.getBlockMetadata(xCoord-1, yCoord, zCoord) <7)
	    						{
	    							break;
	    						}
	    					}
	    				}
		    			ArrayList<ItemStack>  drops = worldObj.getBlock(xCoord-1, yCoord, zCoord).getDrops(worldObj, xCoord-1, yCoord, zCoord, worldObj.getBlockMetadata(xCoord-1, yCoord, zCoord), 0);
	    				this.breakBlocks(worldObj, xCoord, yCoord, zCoord, -1, 0, 0, drops);
	    			}
	    			break;
	    		}
	    	}
	    }
	    
	    private boolean checkGenerator(World worldObj,int xCoord, int yCoord, int zCoord)
	    {
	    	if(worldObj.getBlock(xCoord+1, yCoord, zCoord) == Blocks.water || worldObj.getBlock(xCoord+1, yCoord, zCoord) == Blocks.flowing_water || worldObj.getBlock(xCoord-1, yCoord, zCoord) == Blocks.water 
	    			|| worldObj.getBlock(xCoord-1, yCoord, zCoord) == Blocks.flowing_water || worldObj.getBlock(xCoord, yCoord, zCoord+1) == Blocks.water || worldObj.getBlock(xCoord, yCoord, zCoord+1) == Blocks.flowing_water 
	    			|| worldObj.getBlock(xCoord, yCoord, zCoord-1) == Blocks.water || worldObj.getBlock(xCoord, yCoord, zCoord-1) == Blocks.flowing_water)
	    	{
	    		if(worldObj.getBlock(xCoord+1, yCoord, zCoord) == Blocks.lava || worldObj.getBlock(xCoord+1, yCoord, zCoord) == Blocks.flowing_lava || worldObj.getBlock(xCoord-1, yCoord, zCoord) == Blocks.lava 
	    			|| worldObj.getBlock(xCoord-1, yCoord, zCoord) == Blocks.flowing_lava || worldObj.getBlock(xCoord, yCoord, zCoord+1) == Blocks.lava || worldObj.getBlock(xCoord, yCoord, zCoord+1) == Blocks.flowing_lava 
	    			|| worldObj.getBlock(xCoord, yCoord, zCoord-1) == Blocks.lava || worldObj.getBlock(xCoord, yCoord, zCoord-1) == Blocks.flowing_lava)
	    		{
	    			return true;
	    		}
	    	}
	    	return false;
	    }
	    private boolean detectTree(World world, int x, int y, int z, Block wood)
	    {
	        int height = y;
	        boolean foundTop = false;
	        do
	        {
	            height++;
	            Block block = world.getBlock(x, height, z);
	            if (block != wood)
	            {
	                height--;
	                foundTop = true;
	            }
	        } while (!foundTop);

	        int numLeaves = 0;
	        if (height - y < 50)
	        {
	            for (int xPos = x - 1; xPos <= x + 1; xPos++)
	            {
	                for (int yPos = height - 1; yPos <= height + 1; yPos++)
	                {
	                    for (int zPos = z - 1; zPos <= z + 1; zPos++)
	                    {
	                        Block leaves = world.getBlock(xPos, yPos, zPos);
	                        if (leaves != null && leaves.isLeaves(world, xPos, yPos, zPos))
	                            numLeaves++;
	                    }
	                }
	            }
	        }

	        return numLeaves > 3;
	    }
	    private int breakTree (World world, int x, int y, int z, Block bID, int meta, EntityPlayer player)
	    {
	    	int count = 0;
	        for (int xPos = x - 1; xPos <= x + 1; xPos++)
	        {
	            for (int yPos = y; yPos <= y + 1; yPos++)
	            {
	                for (int zPos = z - 1; zPos <= z + 1; zPos++)
	                {
	                    
	                        Block localBlock = world.getBlock(xPos, yPos, zPos);
	                        if (bID == localBlock||localBlock.getMaterial()==Material.leaves)
	                        {
	                            int localMeta = world.getBlockMetadata(xPos, yPos, zPos);
	                            int hlvl = localBlock.getHarvestLevel(localMeta);
	                           
	                            float localHardness = localBlock == null ? Float.MAX_VALUE : localBlock.getBlockHardness(world, xPos, yPos, zPos);
	                            
	                            if (hlvl <= 2 && !(localHardness < 0))
	                            {
	                                boolean cancelHarvest = false;


	                                // send blockbreak event
	                                if (cancelHarvest)
	                                {
	                                    breakTree(world, xPos, yPos, zPos, bID, meta, player);
	                                }
	                                else
	                                {
	                                    if ((localBlock == bID||localBlock.getMaterial()==Material.leaves) && localMeta % 4 == meta % 4)
	                                    {
	                                        ArrayList<ItemStack> temp3 = localBlock.getDrops(world, x, y, z, localMeta, 0);
	                                        for(int i = 0;i<temp3.size();i++)
	                                        {
	                                        	items.add(temp3.get(i));
	                                        }
	                                        world.setBlockToAir(xPos, yPos, zPos);
	                                        if (!world.isRemote)
	                                            breakTree(world, xPos, yPos, zPos, bID, meta, player);
	                                    }
	                                }
	                            }
	                        }
	                    
	                }
	            }
	        }
	        return count;
	    }
	    private void sendItems(World world, int x, int y, int z)
	    {
	    	if(world.getBlock(x, y, z).hasTileEntity(world.getBlockMetadata(x, y, z)))
			{
				TileEntity tile = world.getTileEntity(x, y, z);
				if(tile instanceof IInventory)
				{
					IInventory inv = (IInventory) tile;
					for(int i =0; i<items.size();i++)
					{
						ItemStack drops = (ItemStack) items.get(i);
						int[] slot =  TransferHelper.checkSpace(x, y, z, drops, world, drops.stackSize);
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
			    		}
					}
					items.clear();
				}
			}
	    	else
			{
	    		for(int i =0; i<items.size();i++)
				{
				ItemStack drops = (ItemStack) items.get(i);
				world.spawnEntityInWorld(new EntityItem(world, x+1.2, y+0.5, z+0.5, drops));
				}
				items.clear();
			}
	    }
	    
	    public void breakBlocks(World world, int x, int y, int z, int offsetX, int offsetY, int offsetZ, ArrayList<ItemStack> drops)
	    {
	    	if(world.getBlock(x-offsetX, y-offsetY, z-offsetZ).hasTileEntity(world.getBlockMetadata(x-offsetX, y-offsetY, z-offsetZ)))
			{
				TileEntity tile = world.getTileEntity(x-offsetX, y-offsetY, z-offsetZ);
				if(tile instanceof IInventory)
				{
					for(int i =0;i<drops.size();i++)
					{
						ItemStack temp = drops.get(i);
						IInventory inv = (IInventory) tile;
						int[] slot =  TransferHelper.checkSpace(x-offsetX, y-offsetY, z-offsetZ, temp, world, 1);
						if(slot!=null)
			    		{	
							if(this.checkGenerator(world, x+offsetX, y+offsetY, z+offsetZ))
							{
								if(this.isEfficient)
									temp.stackSize=64;
							}
			    			if(slot[1] == 0)
			    			{
			    				ItemStack temp2 = temp.copy();
			    				inv.setInventorySlotContents(slot[0], temp2);
			    				inv.markDirty();
			    			}
			    			if(slot[1] == 1 && inv.getStackInSlot(slot[0]).stackSize < inv.getInventoryStackLimit())
			    			{
			    				ItemStack stack = temp.copy();
			    				if(slot[2]!=0)
			    					stack.stackSize = slot[2]+inv.getStackInSlot(slot[0]).stackSize;
			    				else
			    					stack.stackSize +=inv.getStackInSlot(slot[0]).stackSize;
			    				
			    				inv.setInventorySlotContents(slot[0], stack);
			    				inv.markDirty();
			    			}
			    			
			    		}
					}
					if(!this.checkGenerator(world, x+offsetX, y+offsetY, z+offsetZ))
		    			world.setBlockToAir(x+offsetX, y+offsetY, z+offsetZ);
				}
			}
	    	else
			{
	    		for(int i =0; i<drops.size();i++)
				{
				ItemStack items = (ItemStack) drops.get(i);
				if(this.checkGenerator(world, x+offsetX, y+offsetY, z+offsetZ))
				{
					if(this.isEfficient)
						items.stackSize=64;
				}
				world.spawnEntityInWorld(new EntityItem(world, x-offsetX, y-offsetY, z-offsetZ, items));
				}
				items.clear();
				if(!this.checkGenerator(world, x+offsetX, y+offsetY, z+offsetZ))
	    			world.setBlockToAir(x+offsetX, y+offsetY, z+offsetZ);
			}
	    }


}
