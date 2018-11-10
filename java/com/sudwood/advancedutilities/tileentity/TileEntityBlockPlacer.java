package com.sudwood.advancedutilities.tileentity;

import java.util.UUID;

import com.mojang.authlib.GameProfile;
import com.sudwood.advancedutilities.AdvancedUtilities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayer;

public class TileEntityBlockPlacer extends TileEntity implements IInventory
{
	private ItemStack[] inventory = new ItemStack[9];
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
		if(!worldObj.isRemote &&worldObj.getBlockPowerInput(xCoord, yCoord, zCoord)>0)
    	{
			switch(worldObj.getBlockMetadata(xCoord, yCoord, zCoord))
	    	{
	    	case 0: //up +y
	    		this.placeBlock(xCoord, yCoord+1, zCoord, worldObj);
	    		break;
			case 1: //down -y
				this.placeBlock(xCoord, yCoord-1, zCoord, worldObj);
	    		break;
			case 2: //North +z
				this.placeBlock(xCoord, yCoord, zCoord+1, worldObj);
	    		break;
			case 3: //south -z 
				this.placeBlock(xCoord, yCoord, zCoord-1, worldObj);
	    		break;
			case 4: //east +x
				this.placeBlock(xCoord+1, yCoord, zCoord, worldObj);
	    		break;
			case 5: //west -x
				this.placeBlock(xCoord-1, yCoord, zCoord, worldObj);
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
	public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) 
	{
		return true;
	}
	
	@Override
    public void readFromNBT(NBTTagCompound tag)
    {
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
        super.readFromNBT(tag);
    }

    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
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
        super.writeToNBT(tag);

    }
    
    private void placeBlock(int x, int y, int z, World world)
    {
    	if(inventory[0]==null&&inventory[1]==null&&inventory[2]==null&&inventory[3]==null&&inventory[4]==null&&inventory[5]==null&&inventory[6]==null&&inventory[7]==null&&inventory[8]==null)
    	{
    		return;
    	}
    	if(world.isAirBlock(x, y, z)&&!world.isRemote)
    	{
    		for(int i = 0; i<inventory.length; i++)
    		{
    			if(inventory[i]!=null)
    			{
    				if(inventory[i].getItem() instanceof ItemBlock)
    				{
    					world.setBlock(x, y, z, ((ItemBlock)inventory[i].getItem()).field_150939_a, inventory[i].getItemDamage(), 3);
    					this.decrStackSize(i,1);
    					return;
    				}
    				if(inventory[i].getItem()==Items.wheat_seeds)
    				{
    					world.setBlock(x, y, z, Blocks.wheat, 0, 3);
    					this.decrStackSize(i,1);
    					return;
    				}
    				if(inventory[i].getItem()==Items.potato)
    				{
    					world.setBlock(x, y, z, Blocks.potatoes, 0, 3);
    					this.decrStackSize(i,1);
    					return;
    				}
    				if(inventory[i].getItem()==Items.carrot)
    				{
    					world.setBlock(x, y, z, Blocks.carrots, 0, 3);
    					this.decrStackSize(i,1);
    					return;
    				}
    			}
    		}
    	}
    }

}
