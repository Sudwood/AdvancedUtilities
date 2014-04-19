package com.sudwood.advancedutilities.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;

import com.sudwood.advancedutilities.AdvancedUtilities;
import com.sudwood.advancedutilities.client.SoundHandler;
import com.sudwood.advancedutilities.items.AdvancedUtilitiesItems;
import com.sudwood.advancedutilities.packets.PacketSteamCharger;

public class TileEntitySteamCharger extends TileEntitySteamBase implements IInventory
{
	 public FluidTank tank = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME*32);
	 public static final int fillAmount = 60;
	 private ItemStack inventory;
	 private boolean isDone;
	 private boolean hasPlayed;
	 public boolean renderGun = false;
	 public boolean renderJetpack = false;
	 public boolean renderJackHammer = false;
	 private boolean hasSent = false;
	    @Override
	    public void readFromNBT(NBTTagCompound tag)
	    {
	        super.readFromNBT(tag);
	        tank.readFromNBT(tag);
	        isDone = tag.getBoolean("isDone");
	        hasPlayed = tag.getBoolean("hasPlayed");
	        
	        NBTTagCompound nbttagcompound1 = (NBTTagCompound) tag.getTag("inventory");
            this.inventory = ItemStack.loadItemStackFromNBT(nbttagcompound1);
           
	    }
	    @Override
	    public void writeToNBT(NBTTagCompound tag)
	    {
	        super.writeToNBT(tag);
	        tank.writeToNBT(tag);
	        tag.setBoolean("isDone", isDone);
	        tag.setBoolean("hasPlayed", hasPlayed);
	        NBTTagCompound tag2 = new NBTTagCompound();
	        if(inventory!=null)
	        	inventory.writeToNBT(tag2);
	        tag.setTag("inventory", tag2);
	    }
	    public void updateEntity()
	    {
	    	if(isGun()&&!hasSent && this.hasWorldObj())
            {
	    		this.addItem(inventory);
	    		hasSent = true;
            }
            if(isJetpack()&&!hasSent && this.hasWorldObj())
            {
            	this.addItem(inventory);
            	hasSent = true;
            }
            if(isJackHammer()&&!hasSent && this.hasWorldObj())
            {
            	this.addItem(inventory);
            	hasSent = true;
            }
	    	fillItem();
	    	if(!isDone)
	    	{
	    		if(this.isDone())
	    		{
	    			isDone = true;
	    		}
	    	}
	    	if(isDone && !hasPlayed)
	    	{
	    		SoundHandler.playSoundEffect(worldObj, xCoord, yCoord, zCoord, "steam", 1F, 1F);
	    		hasPlayed = true;
	    	}
	    }
	    
	    public void addItem(ItemStack item)
	    {
	    	inventory = item.copy();
	    	if(isGun())
            {
            	AdvancedUtilities.packetPipeline.sendToAll(new PacketSteamCharger(true, false, false, xCoord, yCoord, zCoord));
            }
            if(isJetpack())
            {
            	AdvancedUtilities.packetPipeline.sendToAll(new PacketSteamCharger(false, true, false, xCoord, yCoord, zCoord));
            }
            if(isJackHammer())
            {
            	AdvancedUtilities.packetPipeline.sendToAll(new PacketSteamCharger(false, false, true, xCoord, yCoord, zCoord));
            }
	    }
	    
	    public ItemStack takeItem()
	    {
	    	ItemStack temp = inventory.copy();
	    	inventory = null;
	    	isDone = false;
	    	hasPlayed = false;
            AdvancedUtilities.packetPipeline.sendToAll(new PacketSteamCharger(false, false, false, xCoord, yCoord, zCoord));

	    	return temp;
	    }
	    
	    public void fillItem()
	    {
	    	if(canTakeItem())
	    	{
	    		if(inventory.getTagCompound() == null)
	    		{
	    			inventory.setTagCompound(new NBTTagCompound());
	    		}
	    		NBTTagCompound tag = inventory.getTagCompound();
	    		if(tag.getInteger("tankAmount")<= tag.getInteger("maxTankAmount")-this.fillAmount && this.tank.getFluidAmount() >= this.fillAmount)
	    		{
	    			tag.setInteger("tankAmount", tag.getInteger("tankAmount")+this.fillAmount);
	    			this.drain(ForgeDirection.UNKNOWN, this.fillAmount, true);
	    		}
	    	}
	    	
	    }
	    
	    public boolean isGun()
	    {
	    	if(inventory!= null && inventory.getItem() == AdvancedUtilitiesItems.pnumaticGun)
	    	{
	    		return true;
	    	}
	    	return false;
	    }
	    
	    public boolean isJackHammer()
	    {
	    	if(inventory!= null && inventory.getItem() == AdvancedUtilitiesItems.jackHammer)
	    	{
	    		return true;
	    	}
	    	return false;
	    }
	    
	    public boolean isJetpack()
	    {
	    	if(inventory!= null && inventory.getItem() == AdvancedUtilitiesItems.steamJetpack)
	    	{
	    		return true;
	    	}
	    	return false;
	    }
	    
	    public boolean isDone()
	    {
	    	if(inventory !=null)
	    	{
		    	if(inventory.getTagCompound() == null)
	    		{
	    			inventory.setTagCompound(new NBTTagCompound());
	    		}
	    		NBTTagCompound tag = inventory.getTagCompound();
	    		if(tag.getInteger("tankAmount")>= tag.getInteger("maxTankAmount")-this.fillAmount)
	    		{
	    			return true;
	    		}
	    	}
	    	return false;
	    }
	    
	    public boolean canAddItem()
	    {
	    	if(inventory == null)
	    		return true;
	    	else
	    	return false;
	    }
	    public boolean canTakeItem()
	    {
	    	if(inventory == null)
	    		return false;
	    	else
	    	return true;
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
	    	if(fluid == FluidRegistry.getFluid("steam"))
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
			return 1;
		}

		@Override
		public ItemStack getStackInSlot(int var1) {
			// TODO Auto-generated method stub
			return inventory;
		}

		@Override
		public ItemStack decrStackSize(int var1, int var2) {
			// TODO Auto-generated method stub
			inventory.stackSize-= var2;
			if(inventory.stackSize <= 0)
				inventory = null;
			return inventory;
				
		}

		@Override
		public ItemStack getStackInSlotOnClosing(int var1) {
			// TODO Auto-generated method stub
			return inventory;
		}

		@Override
		public void setInventorySlotContents(int var1, ItemStack var2) {
			// TODO Auto-generated method stub
			inventory = var2;
		}

		@Override
		public String getInventoryName() {
			// TODO Auto-generated method stub
			return "SteamCharger";
		}

		@Override
		public boolean hasCustomInventoryName() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public int getInventoryStackLimit() {
			// TODO Auto-generated method stub
			return 1;
		}

		@Override
		public boolean isUseableByPlayer(EntityPlayer var1) {
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


}