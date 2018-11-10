package com.sudwood.advancedutilities.tileentity;

import com.sudwood.advancedutilities.client.SoundHandler;
import com.sudwood.advancedutilities.fluids.AdvancedUtilitiesFluids;
import com.sudwood.advancedutilities.items.AdvancedUtilitiesItems;
import com.sudwood.advancedutilities.items.ItemArmorSteamJetpack;
import com.sudwood.advancedutilities.items.ItemJackHammer;
import com.sudwood.advancedutilities.items.ItemPnumaticGun;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class TileEntitySteamCharger extends TileEntity implements IInventory, IFluidHandler, ISteamTank
{
	 public FluidTank tank = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME*32);
	 public static final int fillAmount = 60;
	 public static int drainAmount = 60;
	 private ItemStack inventory;
	 private boolean isDone;
	 private boolean hasPlayed;
	 public boolean renderGun = false;
	 public boolean renderJetpack = false;
	 public boolean renderJackHammer = false;
	 public boolean renderLegs = false;
	 private boolean hasSent = false;
	    @Override
	    public void readFromNBT(NBTTagCompound tag)
	    {
	        super.readFromNBT(tag);
	        tank.readFromNBT(tag);
	        isDone = tag.getBoolean("isDone");
	        hasPlayed = tag.getBoolean("hasPlayed");
	        this.renderGun = tag.getBoolean("renderGun");
	        this.renderJackHammer = tag.getBoolean("renderJackHammer");
	        this.renderJetpack = tag.getBoolean("renderJetpack");
	        this.renderLegs = tag.getBoolean("renderLegs");
	        
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
	        tag.setBoolean("renderGun", renderGun);
	        tag.setBoolean("renderJackHammer", renderJackHammer);
	        tag.setBoolean("renderJetpack", renderJetpack);
	        tag.setBoolean("renderLegs", renderLegs);
	        NBTTagCompound tag2 = new NBTTagCompound();
	        if(inventory!=null)
	        	inventory.writeToNBT(tag2);
	        tag.setTag("inventory", tag2);
	    }
	    public void updateEntity()
	    {
	    	if(this.drainAmount > this.tank.getCapacity())
	    		this.drainAmount = this.tank.getCapacity();
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
            	this.renderGun = true;
            	worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        		this.markDirty();
            }
            if(isJetpack())
            {
            	this.renderJetpack = true;
            	worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        		this.markDirty();
            }
            if(isJackHammer())
            {
            	this.renderJackHammer = true;
            	worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        		this.markDirty();
            }
            if(isLegs())
            {
            	this.renderLegs = true;
            	worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        		this.markDirty();
            }
	    }
	    
	    public ItemStack takeItem()
	    {
	    	ItemStack temp = inventory.copy();
	    	inventory = null;
	    	isDone = false;
	    	hasPlayed = false;
	    	this.renderGun = false;
	    	this.renderJetpack = false;
	    	this.renderJackHammer = false;
	    	this.renderLegs = false;
	    	worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    		this.markDirty();

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
	    		if(inventory.getItem() == AdvancedUtilitiesItems.steamJetpack && tag.getInteger("maxTankAmount") == 0)
	    		{
	    			tag.setInteger("maxTankAmount", ItemArmorSteamJetpack.maxStorage);
	    		}
	    		if(inventory.getItem() == AdvancedUtilitiesItems.pnumaticGun && tag.getInteger("maxTankAmount") == 0)
	    		{
	    			tag.setInteger("maxTankAmount", ItemPnumaticGun.maxStorage);
	    		}
	    		if(inventory.getItem() == AdvancedUtilitiesItems.jackHammer && tag.getInteger("maxTankAmount") == 0)
	    		{
	    			tag.setInteger("maxTankAmount", ItemJackHammer.maxStorage);
	    		}
	    		if(tag.getInteger("tankAmount")<= tag.getInteger("maxTankAmount")-this.fillAmount && this.tank.getFluidAmount() >= this.fillAmount)
	    		{
	    			tag.setInteger("tankAmount", tag.getInteger("tankAmount")+this.fillAmount);
	    			this.drain(ForgeDirection.UNKNOWN, this.drainAmount, true);
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
	    public boolean isLegs()
	    {
	    	if(inventory!= null && inventory.getItem() == AdvancedUtilitiesItems.steamLegs)
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
	    		return this.tank.getFluid().getFluid().getLocalizedName(tank.getFluid());
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
		@Override
		public boolean isItemValidForSlot(int var1, ItemStack var2) {
			// TODO Auto-generated method stub
			return false;
		}
		@Override
		   public Packet getDescriptionPacket()
		   {
			   super.getDescriptionPacket();
		       NBTTagCompound syncData = new NBTTagCompound();
		       this.writeSyncableDataToNBT(syncData);
		       return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, syncData);
		   }
		   public void writeSyncableDataToNBT(NBTTagCompound tag)
		   {
			   tag.setBoolean("renderGun", renderGun);
		        tag.setBoolean("renderJackHammer", renderJackHammer);
		        tag.setBoolean("renderJetpack", renderJetpack);
		        tag.setBoolean("renderLegs", renderLegs);
		   }
		   public void readSyncableDataFromNBT(NBTTagCompound tag)
		   {
			   this.renderGun = tag.getBoolean("renderGun");
		        this.renderJackHammer = tag.getBoolean("renderJackHammer");
		        this.renderJetpack = tag.getBoolean("renderJetpack");
		        this.renderLegs = tag.getBoolean("renderLegs");
		   }
		   @Override
		   public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
		   {
			   super.onDataPacket(net, pkt);
		       readSyncableDataFromNBT(pkt.func_148857_g());
		   }
		   


}
