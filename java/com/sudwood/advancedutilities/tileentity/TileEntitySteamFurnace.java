package com.sudwood.advancedutilities.tileentity;

import com.sudwood.advancedutilities.HelperLibrary;
import com.sudwood.advancedutilities.TransferHelper;
import com.sudwood.advancedutilities.blocks.AdvancedUtilitiesBlocks;
import com.sudwood.advancedutilities.config.ServerOptions;
import com.sudwood.advancedutilities.fluids.AdvancedUtilitiesFluids;
import com.sudwood.advancedutilities.recipes.SteamFurnaceRecipes;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
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

public class TileEntitySteamFurnace extends TileEntity implements ISidedInventory, IFluidHandler, ISteamTank
{
	 public FluidTank tank = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME*64);
	 private static final int[] slotsTop = new int[] {0,1, 2, 3};
	 private static final int[] slotsBottom = new int[] {};
	 private static final int[] slotsSides = new int[] {2,3};	
	 private static int smeltCost = 24*ServerOptions.steamCreationRate;
	 private int costMod = 0;
	 private int speedMult = 1;
	 private ItemStack[] inventory = new ItemStack[4];
	 private int currentOutput = 0;
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
	    	if(this.smeltCost > this.tank.getCapacity())
	    		this.smeltCost = this.tank.getCapacity();
	    		if(this.canSmelt() == 1 || this.canSmelt() == 2)
	    		{
	    			this.progressTime++;
	    			
		    			if(this.progressTime >= 40 / this.speedMult)
		    			{
		    				if(!worldObj.isRemote)
		    				{
		    					this.smeltItem();
		    					this.pushItem();
		    				}
		    				this.progressTime = 0;
		    			}
	    	    	
	    		}
	    		else
	    		{
	    			this.progressTime = 0;
	    		}
	    	
	    }
	    
	    public int getMaxProcessTime()
	    {
	    	return 40/this.speedMult;
	    }
	    
	    public void smeltItem()
	    {
	    	if(canSmelt() == 1)
	    	{
	    		if(inventory[0]!=null)
	    		{
		    		 ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.inventory[0]);
		    		 if(itemstack!=null)
		    		 {
		    			 itemstack.stackSize = 1;
			             if (this.inventory[2] == null)
			             {
			                 this.inventory[2] = itemstack.copy();
			             }
			             else if (this.inventory[2].getItem() == itemstack.getItem())
			             {
			                 this.inventory[2].stackSize += itemstack.stackSize; // Forge BugFix: Results may have multiple items
			             }
		
			             --this.inventory[0].stackSize;
		
			             if (this.inventory[0].stackSize <= 0)
			             {
			                 this.inventory[0] = null;
			             }
		    		 }
	    		}
	    		if(inventory[1]!=null)
	    		{
		    		 ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.inventory[1]);
		    		
		    		 if(itemstack!= null)
		    		 {
		    			 itemstack.stackSize = 1;
			             if (this.inventory[3] == null)
			             {
			                 this.inventory[3] = itemstack.copy();
			             }
			             else if (this.inventory[3].getItem() == itemstack.getItem())
			             {
			                 this.inventory[3].stackSize += itemstack.stackSize; // Forge BugFix: Results may have multiple items
			             }
		
			             --this.inventory[1].stackSize;
		
			             if (this.inventory[1].stackSize <= 0)
			             {
			                 this.inventory[1] = null;
			             }
		    		 }
	    		}
	    		
		    	this.drain(ForgeDirection.UNKNOWN, this.smeltCost + this.costMod, true);
		    	return;
	    	}
	    	else if(canSmelt() == 2)
	    	{
	    		ItemStack output;

	    				output = SteamFurnaceRecipes.getFurnaceResult(inventory[0], inventory[1]);
	    				int[] ingrSize = SteamFurnaceRecipes.getIngredientSize(inventory[0], inventory[1]);
	    		    	if(inventory[2] == null)
	    			    {
	    			    		inventory[2] = output;
	    			    		inventory[0].stackSize-=ingrSize[0];
	    			    		if(inventory[0].stackSize <= 0)
	    			    			inventory[0] = null;
	    			    		inventory[1].stackSize-=ingrSize[1];
	    			    		if(inventory[1].stackSize <= 0)
	    			    			inventory[1] = null;
	    			    		this.drain(ForgeDirection.UNKNOWN, this.smeltCost + this.costMod, true);
	    			    		
	    			    }
	    		    	else if(inventory[2].stackSize + output.stackSize < 64)
	    		    	{
	    		    		inventory[2].stackSize+=output.stackSize;
	    		    		inventory[0].stackSize-=ingrSize[0];
	    		    		if(inventory[0].stackSize <= 0)
	    		    			inventory[0] = null;
	    		    		inventory[1].stackSize-=ingrSize[1];
				    		if(inventory[1].stackSize <= 0)
				    			inventory[1] = null;
	    		    		this.drain(ForgeDirection.UNKNOWN, this.smeltCost + this.costMod, true);
	    		    	}
	    				return;
	    	}
	    }
	    
	    public int canSmelt()
	    {
	    	if(this.tank.getFluidAmount() >= this.smeltCost + this.costMod && inventory.length > 0)
	    	{

	    		if(inventory[0]!=null && inventory[1]!=null)
	    		{
	    			ItemStack temp = SteamFurnaceRecipes.getFurnaceResult(inventory[0], inventory[1]);
	    			if(temp!=null)
	    			{
	    				return 2;
	    			}
	    		}	
	    		if(inventory[0]!=null && FurnaceRecipes.smelting().getSmeltingResult(inventory[0])!=null)
	    		{
	    			if(inventory[2]== null)
	    				return 1;
	    			ItemStack temp = FurnaceRecipes.smelting().getSmeltingResult(inventory[0]);
	    			
	    			if(inventory[2].isItemEqual(temp) && inventory[2].stackSize + temp.stackSize <= 64)
	    			{
	    				return 1;
	    			}
	    			
	    		}
	    		if(inventory[1]!=null && FurnaceRecipes.smelting().getSmeltingResult(inventory[1])!=null)
	    		{
	    			if(inventory[3]== null)
	    				return 1;
	    			ItemStack temp = FurnaceRecipes.smelting().getSmeltingResult(inventory[1]);
	    			
	    			if(inventory[3].isItemEqual(temp) && inventory[3].stackSize + temp.stackSize <= 64)
	    			{
	    				return 1;
	    			}
	    			
	    		}
	    		
	    	}
	    	return 0;
	    }
	    
	    public int getProgressScaled(int num)
	    {
	    	return this.progressTime * num / (40*this.speedMult);
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
	    		TileEntitySteamCompressor tile = (TileEntitySteamCompressor) worldObj.getTileEntity(xCoord, yCoord-1, zCoord);
	    		this.speedMult = tile.multiplier;
	    		this.costMod = this.smeltCost*(tile.multiplier/2);
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
	    	
	    	for(int i = 2; i < inventory.length; i++)
	    	{

		    	if(inventory[i]!=null)
		    	{
		    		int numTransfered = inventory[i].stackSize;
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
		    					
		    				}
		    				else
		    				{	
			    				inventory[i].stackSize-=numTransfered;
			    				this.markDirty();
		    				}
		    			}
		    			if(slot[1] == 1 && transfer.getStackInSlot(slot[0]).stackSize < transfer.getInventoryStackLimit())
		    			{
			    				transfer.setInventorySlotContents(slot[0], new ItemStack(inventory[i].getItem(), transfer.getStackInSlot(slot[0]).stackSize+numTransfered, inventory[i].getItemDamage()));
			    				transfer.markDirty();
			    				if(inventory[i].stackSize == numTransfered)
			    				{
			    					inventory[i] = null;
			    					this.markDirty();
			    					
			    				}
			    				else
			    				{	
				    				inventory[i].stackSize-=numTransfered;
				    				this.markDirty();
			    				}
		    				
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
	        return new int[]{0,1,2,3};
	    }

	    /**
	     * Returns true if automation can insert the given item in the given slot from the given side. Args: Slot, item,
	     * side
	     */
	    public boolean canInsertItem(int par1, ItemStack par2ItemStack, int par3)
	    {
	        return this.isItemValidForSlot(par1, par2ItemStack);
	    }

	    /**
	     * Returns true if automation can extract the given item in the given slot from the given side. Args: Slot, item,
	     * side
	     */
	    @Override
	    public boolean canExtractItem(int par1, ItemStack par2ItemStack, int par3)
	    {
	        return par1 == 2 || par1 == 3;
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
	    	if((fluid == FluidRegistry.getFluid("Steam") || fluid == AdvancedUtilitiesFluids.fluidSteam) && tank.getFluidAmount() < tank.getCapacity())
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
			return "SteamFurnace";
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

		@Override
		public boolean isItemValidForSlot(int slot, ItemStack stack) 
		{
			// TODO Auto-generated method stub
			boolean result = false;
			if(slot == 0)
			{
				return SteamFurnaceRecipes.isIngredient1(stack);
			}
			if(slot == 1)
			{
				return SteamFurnaceRecipes.isIngredient2(stack);
			}
			
			return result;
		}
}
