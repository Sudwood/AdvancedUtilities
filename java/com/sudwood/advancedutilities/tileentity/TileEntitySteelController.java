package com.sudwood.advancedutilities.tileentity;

import com.sudwood.advancedutilities.HelperLibrary;
import com.sudwood.advancedutilities.config.ServerOptions;
import com.sudwood.advancedutilities.fluids.AdvancedUtilitiesFluids;
import com.sudwood.advancedutilities.items.AdvancedUtilitiesItems;
import com.sudwood.advancedutilities.recipes.SteelOvenRecipes;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class TileEntitySteelController extends TileEntity implements ISidedInventory, IFluidHandler
{
	private ItemStack[] inventory = new ItemStack[6];
    public FluidTank tank = new FluidTank(128*FluidContainerRegistry.BUCKET_VOLUME);
    private static int smeltCost = 800*ServerOptions.steamCreationRate;
    private int costMod = 0;
    private int speedMult = 1;
    private int currentOutput = 0;
    public int progressTime;
    private int maxProgress = 600;
    public boolean isMulti = false;
    
	@Override
	public ItemStack getStackInSlot(int slot) 
	{
		return inventory[slot];
	}
	
	@Override
    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);
        tank.readFromNBT(tag);
        this.progressTime = tag.getInteger("progressTime");
        this.currentOutput = tag.getInteger("CurrentOutput");
        this.costMod = tag.getInteger("costMod");
        this.speedMult = tag.getInteger("speedMult");
        this.isMulti = tag.getBoolean("isMulti");
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
	public void updateBreak(int skip)
	{
		for(int i = 0; i < 27; i++)
		{
			if(i == skip)
				continue;
			if(getEntForNum(i, worldObj, xCoord, yCoord, zCoord)!= null)
			{
				getEntForNum(i, worldObj, xCoord, yCoord, zCoord).setPiece(0);
			}
		}
		this.isMulti = false;
	}
	
	public int getMaxProgressTime()
	{
		return this.maxProgress;
	}
	
	public TileEntitySteelOven getEntForNum(int num, World world, int x, int y, int z)
	 {
		 switch(num)
		 {
		 case 1:
			return (TileEntitySteelOven) world.getTileEntity(x+1, y+1, z+1);
		 case 2:
			 return (TileEntitySteelOven) world.getTileEntity(x+1, y+1, z);
		 case 3:
			 return (TileEntitySteelOven) world.getTileEntity(x+1, y+1, z-1);
		 case 4:
			 return (TileEntitySteelOven) world.getTileEntity(x, y+1, z+1);
		 case 5:
			 return (TileEntitySteelOven) world.getTileEntity(x, y+1, z);
		 case 6:
			 return (TileEntitySteelOven) world.getTileEntity(x, y+1, z-1);
		 case 7:
			 return (TileEntitySteelOven) world.getTileEntity(x-1, y+1, z+1);
		 case 8:
			 return (TileEntitySteelOven) world.getTileEntity(x-1, y+1, z);
		 case 9:
			 return (TileEntitySteelOven) world.getTileEntity(x-1, y+1, z-1);
		 case 10:
			 return (TileEntitySteelOven) world.getTileEntity(x+1, y, z+1);
		 case 11:
			 return (TileEntitySteelOven) world.getTileEntity(x+1, y, z);
		 case 12:
			 return (TileEntitySteelOven) world.getTileEntity(x+1, y, z-1);
		 case 13:
			 return (TileEntitySteelOven) world.getTileEntity(x, y, z+1);
		 case 14:
			 return (TileEntitySteelOven) world.getTileEntity(x, y, z-1);
		 case 15:
			 return (TileEntitySteelOven) world.getTileEntity(x-1, y, z+1);
		 case 16:
			 return (TileEntitySteelOven) world.getTileEntity(x-1, y, z);
		 case 17:
			 return (TileEntitySteelOven) world.getTileEntity(x-1, y, z-1);
		 case 18:
			 return (TileEntitySteelOven) world.getTileEntity(x+1, y-1, z+1);
		 case 19:
			 return (TileEntitySteelOven) world.getTileEntity(x+1, y-1, z);
		 case 20:
			 return (TileEntitySteelOven) world.getTileEntity(x+1, y-1, z-1);
		 case 21:
			 return (TileEntitySteelOven) world.getTileEntity(x, y-1, z+1);
		 case 22:
			 return (TileEntitySteelOven) world.getTileEntity(x, y-1, z);
		 case 23:
			 return (TileEntitySteelOven) world.getTileEntity(x, y-1, z-1);
		 case 24:
			 return (TileEntitySteelOven) world.getTileEntity(x-1, y-1, z+1);
		 case 25:
			 return (TileEntitySteelOven) world.getTileEntity(x-1, y-1, z);
		 case 26:
			 return (TileEntitySteelOven) world.getTileEntity(x-1, y-1, z-1);
		 }
		 return null;
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
        tag.setBoolean("isMulti", this.isMulti);
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
    		if(this.canSmelt())
    		{
    			this.progressTime++;
    			
	    			if(this.progressTime >= this.maxProgress / this.speedMult)
	    			{
	    				if(!worldObj.isRemote)
	    				{
	    					this.smeltItem();
	    				}
	    				this.progressTime = 0;
	    			}
    	    	
    		}
    		else
    		{
    			this.progressTime = 0;
    		}
    	
    }
    public int getProgressScaled(int num)
    {
    	return this.progressTime * num / (maxProgress*this.speedMult);
    }
    public boolean canSmelt()
    {
    	//System.out.println(SteelOvenRecipes.getSteelOvenResult(inventory[0], inventory[1], inventory[2], inventory[3], inventory[4]));
    	if(tank.getFluidAmount() < this.smeltCost)
    	{
    		return false;
    	}
    	for(int i = 0; i < 5; i++)
    	{
    		if(inventory[i] == null)
    			return false;
    	}
    	if(inventory[5] != null && inventory[5].stackSize >= 64)
    		return false;
    	if(SteelOvenRecipes.getSteelOvenResult(inventory[0], inventory[1], inventory[2], inventory[3], inventory[4])!= null)
    	{
    		return true;
    	}
    	return false;
    }
    public void smeltItem()
    {
    	if(this.canSmelt())
    	{
    	ItemStack itemstack = SteelOvenRecipes.getSteelOvenResult(inventory[0], inventory[1], inventory[2], inventory[3], inventory[4]);
    	int[] size = SteelOvenRecipes.getIngredientSize(inventory[0], inventory[1], inventory[2], inventory[3], inventory[4]);
		 if(itemstack!=null)
		 {
            if (this.inventory[5] == null)
            {
                this.inventory[5] = itemstack.copy();
            }
            else if (HelperLibrary.areItemStacksSameItemAndDamage(inventory[5], itemstack))
            {
                this.inventory[5].stackSize += itemstack.stackSize; // Forge BugFix: Results may have multiple items
            }

            inventory[0].stackSize-=size[0];

            if (this.inventory[0].stackSize <= 0)
            {
                this.inventory[0] = null;
            }
            inventory[1].stackSize-=size[0];

            if (this.inventory[1].stackSize <= 0)
            {
                this.inventory[1] = null;
            }
            inventory[2].stackSize-=size[0];

            if (this.inventory[2].stackSize <= 0)
            {
                this.inventory[2] = null;
            }
            inventory[3].stackSize-=size[0];

            if (this.inventory[3].stackSize <= 0)
            {
                this.inventory[3] = null;
            }
            inventory[4].stackSize-=size[0];

            if (this.inventory[4].stackSize <= 0)
            {
                this.inventory[4] = null;
            }
            this.drain(ForgeDirection.UNKNOWN, this.smeltCost + this.costMod, true);
		 }
    	}
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
		return "SteelOven";
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
	public void openInventory() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeInventory() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) 
	{
		switch(slot)
		{
		case 0:
			return SteelOvenRecipes.isIngr0(stack);
		case 1:
			return SteelOvenRecipes.isIngr1(stack);
		case 2:
			return SteelOvenRecipes.isIngr2(stack);
		case 3:
			return SteelOvenRecipes.isIngr3(stack);
		case 4:
			return SteelOvenRecipes.isIngr4(stack);
		}
		return false;
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
	    		return this.tank.getFluid().getFluid().getLocalizedName(this.tank.getFluid());
	    	else
	    		return "Empty";
	    }
	    
	    /* IFluidHandler */
	    public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
	    {
	        return true;
	    }
	    /**
	     * Returns true if automation can insert the given item in the given slot from the given side. Args: Slot, item,
	     * side
	     */
	    @Override
	    public boolean canInsertItem(int par1, ItemStack par2ItemStack, int par3)
	    {
	    	if(par1 == 5)
	    		return false;
	        return this.isItemValidForSlot(par1, par2ItemStack);
	    }

	    /**
	     * Returns true if automation can extract the given item in the given slot from the given side. Args: Slot, item,
	     * side
	     */
	    @Override
	    public boolean canExtractItem(int par1, ItemStack par2ItemStack, int par3)
	    {
	        return par1 == 5;
	    }

		@Override
		public int[] getAccessibleSlotsFromSide(int p_94128_1_) {
			// TODO Auto-generated method stub
			return new int[]{0, 1, 2, 3 , 4, 5};
		}

}
