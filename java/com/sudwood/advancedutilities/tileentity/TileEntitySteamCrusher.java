package com.sudwood.advancedutilities.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

import com.sudwood.advancedutilities.CrushRecipes;
import com.sudwood.advancedutilities.TransferHelper;
import com.sudwood.advancedutilities.blocks.AdvancedUtilitiesBlocks;
import com.sudwood.advancedutilities.config.ServerOptions;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntitySteamCrusher extends TileEntity implements ISidedInventory, IFluidHandler, ISteamTank
{
	 public FluidTank tank = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME*64);
	 private static final int[] slotsTop = new int[] {0};
	 private static final int[] slotsBottom = new int[] {};
	 private static final int[] slotsSides = new int[] {1};	 
	 private ItemStack[] inventory = new ItemStack[2];
	 private static int crushCost = 100*ServerOptions.steamCreationRate;
	 private int currentOutput = 0;
	 private int costMod = 0;
	 private int speedMult = 1;
	 public int progressTime;
	    @Override
	    public void readFromNBT(NBTTagCompound tag)
	    {
	        super.readFromNBT(tag);
	        tank.readFromNBT(tag);
	        this.progressTime = tag.getInteger("progressTime");
	        this.currentOutput = tag.getInteger("CurrentOutput");
	        this.costMod = tag.getInteger("costMod");
	        this.speedMult = tag.getInteger("speedMult");
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
	    }

	    public int getMaxProcessTime()
	    {
	    	return 160/this.speedMult;
	    }
	    
	    @Override
	    public void writeToNBT(NBTTagCompound tag)
	    {
	        super.writeToNBT(tag);
	        tank.writeToNBT(tag);
	        tag.setInteger("progressTime", progressTime);
	        tag.setInteger("CurrentOutput", currentOutput);
	        tag.setInteger("costMod", this.costMod);
	        tag.setInteger("speedMult", this.speedMult);
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

	    }
	    public void updateEntity()
	    {
	    	if(this.crushCost > this.tank.getCapacity())
	    		this.crushCost = this.tank.getCapacity();
	    		if(this.canCrush())
	    		{
	    			this.progressTime++;
	    			
		    			if(this.progressTime >= 160 / this.speedMult)
		    			{
		    				if(!worldObj.isRemote)
		    					this.crushItem();
		    				this.progressTime = 0;
		    			}
	    	    	
	    		}
	    		else
	    		{
	    			this.progressTime = 0;
	    		}
	    	
	    }
	    
	    public void crushItem()
	    {
	    	if(canCrush())
	    	{
		    	ItemStack output = CrushRecipes.getCrushResult(inventory[0]);
		    	if(inventory[1] == null)
		    	{
		    		inventory[1] = output;
		    		inventory[0].stackSize--;
		    		if(inventory[0].stackSize <= 0)
		    			inventory[0] = null;
		    		
		    		pushItem();
		    		this.drain(ForgeDirection.UNKNOWN, 500, true);
		    		return;
		    		
		    	}
		    	else if(inventory[1].stackSize + output.stackSize < 64)
		    	{
		    		this.setInventorySlotContents(1, new ItemStack(output.getItem(), output.stackSize+inventory[1].stackSize, output.getItemDamage()));
		    		inventory[0].stackSize--;
		    		if(inventory[0].stackSize <= 0)
		    			inventory[0] = null;
		    		
		    		pushItem();
		    		this.drain(ForgeDirection.UNKNOWN, 500, true);
		    		return;
		    	}
	    	}
	    }
	    
	    public boolean canCrush()
	    {
	    	if(this.tank.getFluidAmount() >= (FluidContainerRegistry.BUCKET_VOLUME/2)+this.costMod && inventory.length > 0)
	    	{
	    		if(inventory[0]!=null && CrushRecipes.getCrushResult(inventory[0])!=null)
	    		{
	    			if(inventory[1]== null)
	    				return true;
	    			ItemStack temp = CrushRecipes.getCrushResult(inventory[0]);
	    			
	    			if(inventory[1].isItemEqual(temp) && inventory[1].stackSize + temp.stackSize <= 64)
	    			{
	    				return true;
	    			}
	    			
	    		}
	    	}
	    	return false;
	    }
	    
	    public int getProgressScaled(int num)
	    {
	    	return this.progressTime * num / (160/this.speedMult);
	    }
	    
	    public void findInventory()
	    {
	    	this.checkCompressor();
	    	switch(worldObj.getBlockMetadata(xCoord, yCoord, zCoord))
	    	{
	    	case 2: // north
	    		if(worldObj.getBlock(xCoord+ForgeDirection.NORTH.offsetX, yCoord+ForgeDirection.NORTH.offsetY, zCoord+ForgeDirection.NORTH.offsetZ) == Blocks.chest)
	    		{
	    			this.currentOutput = 2;
	    			return;
	    		}
	    		break;
	    	case 3: // south
	    		if(worldObj.getBlock(xCoord+ForgeDirection.EAST.offsetX, yCoord+ForgeDirection.EAST.offsetY, zCoord+ForgeDirection.EAST.offsetZ) == Blocks.chest)
	    		{
	    			this.currentOutput = 3;
	    			return;
	    		}
	    		break;
	    	case 4: // west
	    		if(worldObj.getBlock(xCoord+ForgeDirection.SOUTH.offsetX, yCoord+ForgeDirection.SOUTH.offsetY, zCoord+ForgeDirection.SOUTH.offsetZ)== Blocks.chest)
	    		{
		    		this.currentOutput = 5;
		    		return;
	    		}
	    		break;
	    	case 5: //east
	    		if(worldObj.getBlock(xCoord+ForgeDirection.WEST.offsetX, yCoord+ForgeDirection.WEST.offsetY, zCoord+ForgeDirection.WEST.offsetZ)== Blocks.chest)
	    		{
		    		this.currentOutput = 4;
		    		return;
	    		}
	    		break;
	    	}  	
	     	return;
	    }
	    
	    public void checkCompressor()
	    {
	    	if(worldObj.getBlock(xCoord, yCoord-1, zCoord) == AdvancedUtilitiesBlocks.steamCompressor)
	    	{
	    		this.speedMult = 2;
	    		this.costMod = this.crushCost;
	    	}
	    	else
	    	{
	    		this.speedMult = 1;
	    		this.costMod = 0;
	    	}
	    }
	    
	    public void pushItem()
	    {
	    	int[] slot = null;
	    	int numTransfered = inventory[1].stackSize;
	    	for(int i = 1; i < inventory.length; i++)
	    	{
		    	if(inventory[i]!=null)
		    	{
		    		
		    		IInventory transfer = null;
		    		switch(currentOutput)
		    		{
		    		case 2: //North
		    			slot = TransferHelper.checkSpace(xCoord+ForgeDirection.NORTH.offsetX, yCoord+ForgeDirection.NORTH.offsetY, zCoord+ForgeDirection.NORTH.offsetZ, new ItemStack(inventory[i].getItem(), 1, inventory[i].getItemDamage()), worldObj, 3);
		    			
		    			try
		    			{
		    				transfer = (IInventory) worldObj.getTileEntity(xCoord+ForgeDirection.NORTH.offsetX, yCoord+ForgeDirection.NORTH.offsetY, zCoord+ForgeDirection.NORTH.offsetZ);
		    			}
		    			catch(Exception e)
		    			{
		    				
		    			}break;
		    		case 3: //south
		    			slot = TransferHelper.checkSpace(xCoord+ForgeDirection.SOUTH.offsetX, yCoord+ForgeDirection.SOUTH.offsetY, zCoord+ForgeDirection.SOUTH.offsetZ, new ItemStack(inventory[i].getItem(), 1, inventory[i].getItemDamage()), worldObj, 2);
		    			try
		    			{
		    				transfer = (IInventory) worldObj.getTileEntity(xCoord+ForgeDirection.SOUTH.offsetX, yCoord+ForgeDirection.SOUTH.offsetY, zCoord+ForgeDirection.SOUTH.offsetZ);
		    			}
		    			catch(Exception e)
		    			{
		    				
		    			}
		    			break;
		    		case 4: //west
		    			slot = TransferHelper.checkSpace(xCoord+ForgeDirection.WEST.offsetX, yCoord+ForgeDirection.WEST.offsetY, zCoord+ForgeDirection.WEST.offsetZ, new ItemStack(inventory[i].getItem(), 1, inventory[i].getItemDamage()), worldObj, 5);
		    			try
		    			{
		    				transfer = (IInventory) worldObj.getTileEntity(xCoord+ForgeDirection.WEST.offsetX, yCoord+ForgeDirection.WEST.offsetY, zCoord+ForgeDirection.WEST.offsetZ);
		    			}
		    			catch(Exception e)
		    			{
		    				
		    			}
		    			break;
		    		case 5: //east
		    			slot = TransferHelper.checkSpace(xCoord+ForgeDirection.EAST.offsetX, yCoord+ForgeDirection.EAST.offsetY, zCoord+ForgeDirection.EAST.offsetZ, new ItemStack(inventory[i].getItem(), 1, inventory[i].getItemDamage()), worldObj, 4);
		    			try
		    			{
		    				transfer = (IInventory) worldObj.getTileEntity(xCoord+ForgeDirection.EAST.offsetX, yCoord+ForgeDirection.EAST.offsetY, zCoord+ForgeDirection.EAST.offsetZ);
		    			}
		    			catch(Exception e)
		    			{
		    				
		    			}
		    			break;
		    		}
		    		if(slot!=null && transfer!= null)
		    		{	
		    			if(slot[1] == 0)
		    			{
		    				transfer.setInventorySlotContents(slot[0], new ItemStack(inventory[i].getItem(), numTransfered, inventory[i].getItemDamage()));
		    				transfer.markDirty();
		    				if(inventory[i].stackSize == numTransfered)
		    				{
		    					inventory[i] = null;
		    					this.markDirty();
		    					return;
		    				}
		    				inventory[i].stackSize-=numTransfered;
		    				this.markDirty();
		    				return;
		    			}
		    			if(slot[1] == 1 && transfer.getStackInSlot(slot[0]).stackSize < transfer.getInventoryStackLimit())
		    			{
			    				transfer.setInventorySlotContents(slot[0], new ItemStack(inventory[i].getItem(), transfer.getStackInSlot(slot[0]).stackSize+numTransfered, inventory[i].getItemDamage()));
			    				transfer.markDirty();
			    				if(inventory[i].stackSize == numTransfered)
			    				{
			    					inventory[i] = null;
			    					this.markDirty();
			    					return;
			    				}
			    				inventory[i].stackSize-=numTransfered;
			    				this.markDirty();
			    				return;
		    				
		    			}
		    			
		    		}
		    		
		    	}
	    	}
	    }

	    @SideOnly(Side.CLIENT)
	    public int getFluidScaled(int p_145955_1_)
	    {
	        return this.tank.getFluidAmount() * p_145955_1_ / this.tank.getCapacity();
	    }
	    
	    public int getTankAmount()
	    {
	    	return tank.getFluidAmount();
	    }
	    
	    public String getFluidName()
	    {
	    	if(this.tank.getFluidAmount() > 0)
	    		return this.tank.getFluid().getFluid().getLocalizedName();
	    	else
	    		return "Empty";
	    }
	    
	    /* IFluidHandler */
	    public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
	    {
	        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
	    }

	    public void openInventory() {}

	    public void closeInventory() {}

	    /**
	     * Returns an array containing the indices of the slots that can be accessed by automation on the given side of this
	     * block.
	     */
	    public int[] getAccessibleSlotsFromSide(int par1)
	    {
	        return new int[]{0,1};
	    }

	    /**
	     * Returns true if automation can insert the given item in the given slot from the given side. Args: Slot, item,
	     * side
	     */
	    @Override
	    public boolean canInsertItem(int par1, ItemStack par2ItemStack, int par3)
	    {
	        return par1 == 0;
	    }
	    
	    @Override
		public boolean isItemValidForSlot(int var1, ItemStack var2) {
			// TODO Auto-generated method stub
			if(var1 == 1)
				return false;
			else if(CrushRecipes.getCrushResult(var2)!= null && var1==0)
				return true;
			return false;
		}

	    /**
	     * Returns true if automation can extract the given item in the given slot from the given side. Args: Slot, item,
	     * side
	     */
	    public boolean canExtractItem(int par1, ItemStack par2ItemStack, int par3)
	    {
	        return par1==1;
	    }

	    @Override
	    public int fill(ForgeDirection from, FluidStack resource, boolean doFill)
	    {
	        return tank.fill(resource, doFill);
	    }

	    @Override
	    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain)
	    {
	        if (resource == null || !resource.isFluidEqual(tank.getFluid()))
	        {
	            return null;
	        }
	        return tank.drain(resource.amount, doDrain);
	    }

	    @Override
	    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain)
	    {
	        return tank.drain(maxDrain, doDrain);
	    }

	    @Override
	    public boolean canFill(ForgeDirection from, Fluid fluid)
	    {
	    	if((fluid == FluidRegistry.getFluid("Steam") || fluid == AdvancedUtilitiesBlocks.fluidSteam) && tank.getFluidAmount() < tank.getCapacity())
	    		return true;
	    	else return false;
	    }

	    @Override
	    public boolean canDrain(ForgeDirection from, Fluid fluid)
	    {
	        return true;
	    }

	    @Override
	    public FluidTankInfo[] getTankInfo(ForgeDirection from)
	    {
	        return new FluidTankInfo[] { tank.getInfo() };
	    }
		@Override
		public int getSizeInventory() {
			// TODO Auto-generated method stub
			return inventory.length;
		}

		@Override
		public ItemStack getStackInSlot(int var1) {
			// TODO Auto-generated method stub
			return inventory[var1];
		}

		 /**
	     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
	     * new stack.
	     */
	    public ItemStack decrStackSize(int par1, int par2)
	    {
	        if (this.inventory[par1] != null)
	        {
	            ItemStack itemstack;

	            if (this.inventory[par1].stackSize <= par2)
	            {
	                itemstack = this.inventory[par1];
	                this.inventory[par1] = null;
	                return itemstack;
	            }
	            else
	            {
	                itemstack = this.inventory[par1].splitStack(par2);

	                if (this.inventory[par1].stackSize == 0)
	                {
	                    this.inventory[par1] = null;
	                }

	                return itemstack;
	            }
	        }
	        else
	        {
	            return null;
	        }
	    }

	    /**
	     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
	     * like when you close a workbench GUI.
	     */
	    public ItemStack getStackInSlotOnClosing(int par1)
	    {
	        if (this.inventory[par1] != null)
	        {
	            ItemStack itemstack = this.inventory[par1];
	            this.inventory[par1] = null;
	            return itemstack;
	        }
	        else
	        {
	            return null;
	        }
	    }

	    /**
	     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
	     */
	    public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
	    {
	        this.inventory[par1] = par2ItemStack;

	        if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
	        {
	            par2ItemStack.stackSize = this.getInventoryStackLimit();
	        }
	    }

		@Override
		public String getInventoryName() {
			// TODO Auto-generated method stub
			return "SteamCrusher";
		}

		@Override
		public boolean hasCustomInventoryName() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public int getInventoryStackLimit() {
			// TODO Auto-generated method stub
			return 64;
		}
}
