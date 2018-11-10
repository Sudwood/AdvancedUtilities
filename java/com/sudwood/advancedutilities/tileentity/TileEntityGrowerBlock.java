package com.sudwood.advancedutilities.tileentity;

import java.util.UUID;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.event.entity.player.BonemealEvent;

import com.mojang.authlib.GameProfile;
import com.sudwood.advancedutilities.AdvancedUtilities;
import com.sudwood.advancedutilities.HelperLibrary;

import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityGrowerBlock extends TileEntity implements IInventory
{
	private ItemStack[] inventory = new ItemStack[1];
	public int storedBonemeal =0;
	@SideOnly(Side.SERVER)
	FakePlayer player1;
	
	public TileEntityGrowerBlock()
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
	public int getSizeInventory() {
		// TODO Auto-generated method stub
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		// TODO Auto-generated method stub
		return inventory[slot];
	}

	@Override
	public ItemStack decrStackSize(int i, int j) 
	{
		if (this.inventory[i] != null)
        {
            ItemStack itemstack;

            if (this.inventory[i].stackSize <= j)
            {
                itemstack = this.inventory[i];
                this.inventory[i] = null;
                return itemstack;
            }
            else
            {
                itemstack = this.inventory[i].splitStack(j);

                if (this.inventory[i].stackSize == 0)
                {
                    this.inventory[i] = null;
                }

                return itemstack;
            }
        }
        else
        {
            return null;
        }
	}
	
	@Override
	public void updateEntity()
    {
		if(!worldObj.isRemote)
		{
			if(inventory[0]!=null)
			{
				if(HelperLibrary.areItemStacksSameItemAndDamage(inventory[0], new ItemStack(Items.dye, 1, 15)))
				{
					this.storedBonemeal+=inventory[0].stackSize;
					this.decrStackSize(0, inventory[0].stackSize);
				}
			}
		}
		
		if(!worldObj.isRemote &&worldObj.getBlockPowerInput(xCoord, yCoord, zCoord)>0)
    	{
			switch(worldObj.getBlockMetadata(xCoord, yCoord, zCoord))
	    	{
	    	case 0: //up +y
	    		this.growPlant(xCoord, yCoord+1, zCoord, worldObj);
	    		break;
			case 1: //down -y
				this.growPlant(xCoord, yCoord-1, zCoord, worldObj);
	    		break;
			case 2: //North +z
				this.growPlant(xCoord, yCoord, zCoord+1, worldObj);
	    		break;
			case 3: //south -z 
				this.growPlant(xCoord, yCoord, zCoord-1, worldObj);
	    		break;
			case 4: //east +x
				this.growPlant(xCoord+1, yCoord, zCoord, worldObj);
	    		break;
			case 5: //west -x
				this.growPlant(xCoord-1, yCoord, zCoord, worldObj);
	    		break;
			}
    	}
    }

	@Override
	public ItemStack getStackInSlotOnClosing(int i) 
	{
		if (this.inventory[i] != null)
        {
            ItemStack itemstack = this.inventory[i];
            this.inventory[i] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) 
	{
		this.inventory[i] = itemstack;

        if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit())
        {
        	itemstack.stackSize = this.getInventoryStackLimit();
        }
	}

	@Override
	public String getInventoryName() {
		// TODO Auto-generated method stub
		return "BlockPlacer";
	}

	@Override
	public boolean hasCustomInventoryName() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int getInventoryStackLimit() {
		// TODO Auto-generated method stub
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void openInventory() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeInventory() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack item) 
	{
		return HelperLibrary.areItemStacksSameItemAndDamage(item, new ItemStack(Items.dye, 1, 15));
	}
	
	@Override
    public void readFromNBT(NBTTagCompound tag)
    {
		super.readFromNBT(tag);
		NBTTagList nbttaglist = tag.getTagList("Items", 10);
		this.inventory = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.inventory.length)
            {
                this.inventory[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
        this.storedBonemeal = tag.getInteger("storedbonemeal");
        
    }

    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
    	super.writeToNBT(tag);
    	NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.inventory.length; ++i)
        {
            if (this.inventory[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.inventory[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        tag.setTag("Items", nbttaglist);
        tag.setInteger("storedbonemeal", storedBonemeal);

    }
    
    private void growPlant(int x, int y, int z, World world)
    {
    	if(storedBonemeal<=0)
    	{
    		return;
    	}
    	if(world.getBlock(x, y, z) instanceof IGrowable &&!world.isRemote)
    	{
    		if(applyBonemeal(world, x, y, z))
    		{
    			this.storedBonemeal--;
    		}
    	}
    }
    
    public boolean applyBonemeal(World world, int x, int y, int z)
    {
        Block block = world.getBlock(x, y, z);

        if (block instanceof IGrowable)
        {
            IGrowable igrowable = (IGrowable)block;

            if (igrowable.func_149851_a(world, x, y, z, world.isRemote))
            {
                if (!world.isRemote)
                {
                    if (igrowable.func_149852_a(world, world.rand, x, y, z))
                    {
                        igrowable.func_149853_b(world, world.rand, x, y, z);
                        
                    }
                    
                }

                return true;
            }
        }

        return false;
    }

}
