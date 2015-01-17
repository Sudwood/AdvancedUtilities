package com.sudwood.advancedutilities.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class TileEntitySteelOven extends TileEntity implements ISidedInventory, IFluidHandler
{
	private int piece;
	
	@Override
	public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);
       piece = tag.getInteger("piece");
    }
	@Override
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
       
        tag.setInteger("piece", piece);
    }
	
	public void setPiece(int num)
	{
		this.piece = num;
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		this.markDirty();
	}
	public int getPiece()
	{
		return this.piece;
	}
	
	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		// TODO Auto-generated method stub
		return this.getTileForNum().fill(from, resource, doFill);
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
		// TODO Auto-generated method stub
		return this.getTileForNum().drain(from, resource, doDrain);
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		// TODO Auto-generated method stub
		return this.getTileForNum().drain(from, maxDrain, doDrain);
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		// TODO Auto-generated method stub
		return this.getTileForNum().canFill(from, fluid);
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		// TODO Auto-generated method stub
		return this.getTileForNum().canDrain(from, fluid);
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		// TODO Auto-generated method stub
		return this.getTileForNum().getTankInfo(from);
	}

	@Override
	public int getSizeInventory() {
		// TODO Auto-generated method stub
		if(this.getTileForNum() != null)
		return this.getTileForNum().getSizeInventory();
		else return 0;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		// TODO Auto-generated method stub
		if(this.getTileForNum() != null)
		return this.getTileForNum().getStackInSlot(slot);
		else return null;
	}

	@Override
	public ItemStack decrStackSize(int p_70298_1_, int p_70298_2_) {
		// TODO Auto-generated method stub
		if(this.getTileForNum() != null)
		return this.getTileForNum().decrStackSize(p_70298_1_, p_70298_2_);
		else return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int p_70304_1_) {
		// TODO Auto-generated method stub
		if(this.getTileForNum() != null)
		return this.getTileForNum().getStackInSlotOnClosing(p_70304_1_);
		else return null;
	}

	@Override
	public void setInventorySlotContents(int p_70299_1_, ItemStack p_70299_2_) {
		// TODO Auto-generated method stub
		if(this.getTileForNum() != null)
		this.getTileForNum().setInventorySlotContents(p_70299_1_, p_70299_2_);
	}

	@Override
	public String getInventoryName() {
		// TODO Auto-generated method stub
		if(this.getTileForNum() != null)
		return this.getTileForNum().getInventoryName();
		else return "";
	}

	@Override
	public boolean hasCustomInventoryName() {
		// TODO Auto-generated method stub
		if(this.getTileForNum() != null)
		return this.getTileForNum().hasCustomInventoryName();
		else return false;
	}

	@Override
	public int getInventoryStackLimit() {
		// TODO Auto-generated method stub
		if(this.getTileForNum() != null)
		return this.getTileForNum().getInventoryStackLimit();
		else return 0;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
		// TODO Auto-generated method stub
		if(this.getTileForNum() != null)
		return this.getTileForNum().isUseableByPlayer(p_70300_1_);
		else return false;
	}

	@Override
	public void openInventory() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeInventory() {
		// TODO Auto-generated method stub
		
	}
	
	public void breakBlock()
	{
		if(this.getTileForNum() != null)
			this.getTileForNum().updateBreak(this.piece);
	}

	@Override
	public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
		// TODO Auto-generated method stub
		if(this.getTileForNum() != null)
		return this.getTileForNum().isItemValidForSlot(p_94041_1_, p_94041_2_);
		else return false;
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
	   tag.setInteger("piece", piece);
   }
   public void readSyncableDataFromNBT(NBTTagCompound tag)
   {
	   piece = tag.getInteger("piece");
   }
   @Override
   public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
   {
	   super.onDataPacket(net, pkt);
       readSyncableDataFromNBT(pkt.func_148857_g());
   }
   
   public int[] getCoordsForNum()
   {
	   int[] temp = new int[3];
	   switch(piece)
	   {
		 case 1:
			temp[0] = xCoord-1;
			temp[1] = yCoord-1;
			temp[2] = zCoord-1;
			break;
		 case 2:
			temp[0] = xCoord-1;
			temp[1] = yCoord-1;
			temp[2] = zCoord;
			break;
		 case 3:
			temp[0] = xCoord-1;
			temp[1] = yCoord-1;
			temp[2] = zCoord+1;
			break;
		 case 4:
			temp[0] = xCoord;
			temp[1] = yCoord-1;
			temp[2] = zCoord-1;
			break;
		 case 5:
			temp[0] = xCoord;
			temp[1] = yCoord-1;
			temp[2] = zCoord;
			break;
		 case 6:
			temp[0] = xCoord;
			temp[1] = yCoord-1;
			temp[2] = zCoord+1;
			break;
		 case 7:
			temp[0] = xCoord+1;
			temp[1] = yCoord-1;
			temp[2] = zCoord-1;
			break;
		 case 8:
			temp[0] = xCoord+1;
			temp[1] = yCoord-1;
			temp[2] = zCoord;
			break;
		 case 9:
			temp[0] = xCoord+1;
			temp[1] = yCoord-1;
			temp[2] = zCoord+1;
			break;
		 case 10:
			temp[0] = xCoord-1;
			temp[1] = yCoord;
			temp[2] = zCoord-1;
			break;
		 case 11:
			temp[0] = xCoord-1;
			temp[1] = yCoord;
			temp[2] = zCoord;
			break;
		 case 12:
			temp[0] = xCoord-1;
			temp[1] = yCoord;
			temp[2] = zCoord+1;
			break;
		 case 13:
			temp[0] = xCoord;
			temp[1] = yCoord;
			temp[2] = zCoord-1;
			break;
		 case 14:
			temp[0] = xCoord;
			temp[1] = yCoord;
			temp[2] = zCoord+1;
			break;
		 case 15:
			temp[0] = xCoord+1;
			temp[1] = yCoord;
			temp[2] = zCoord-1;
			break;
		 case 16:
			temp[0] = xCoord+1;
			temp[1] = yCoord;
			temp[2] = zCoord;
			break;
		 case 17:
			temp[0] = xCoord+1;
			temp[1] = yCoord;
			temp[2] = zCoord+1;
			break;
		 case 18:
			temp[0] = xCoord-1;
			temp[1] = yCoord+1;
			temp[2] = zCoord-1;
			break;
		 case 19:
			temp[0] = xCoord-1;
			temp[1] = yCoord+1;
			temp[2] = zCoord;
			break;
		 case 20:
			temp[0] = xCoord-1;
			temp[1] = yCoord+1;
			temp[2] = zCoord+1;
			break;
		 case 21:
			temp[0] = xCoord;
			temp[1] = yCoord+1;
			temp[2] = zCoord-1;
			break;
		 case 22:
			temp[0] = xCoord;
			temp[1] = yCoord+1;
			temp[2] = zCoord;
			break;
		 case 23:
			temp[0] = xCoord;
			temp[1] = yCoord+1;
			temp[2] = zCoord+1;
			break;
		 case 24:
			temp[0] = xCoord+1;
			temp[1] = yCoord+1;
			temp[2] = zCoord-1;
			break;
		 case 25:
			temp[0] = xCoord+1;
			temp[1] = yCoord+1;
			temp[2] = zCoord;
			break;
		 case 26:
			temp[0] = xCoord+1;
			temp[1] = yCoord+1;
			temp[2] = zCoord+1;
			break;
	   }
	   return temp;
   }
   
   public TileEntitySteelController getTileForNum()
   {
	   switch(piece)
	   {
		 case 1:
			return (TileEntitySteelController) worldObj.getTileEntity(xCoord-1, yCoord-1, zCoord-1);
		 case 2:
			 return (TileEntitySteelController) worldObj.getTileEntity(xCoord-1, yCoord-1, zCoord);
		 case 3:
			 return (TileEntitySteelController) worldObj.getTileEntity(xCoord-1, yCoord-1, zCoord+1);
		 case 4:
			 return (TileEntitySteelController) worldObj.getTileEntity(xCoord, yCoord-1, zCoord-1);
		 case 5:
			 return (TileEntitySteelController) worldObj.getTileEntity(xCoord, yCoord-1, zCoord);
		 case 6:
			 return (TileEntitySteelController) worldObj.getTileEntity(xCoord, yCoord-1, zCoord+1);
		 case 7:
			 return (TileEntitySteelController) worldObj.getTileEntity(xCoord+1, yCoord-1, zCoord-1);
		 case 8:
			 return (TileEntitySteelController) worldObj.getTileEntity(xCoord+1, yCoord-1, zCoord);
		 case 9:
			 return (TileEntitySteelController) worldObj.getTileEntity(xCoord+1, yCoord-1, zCoord+1);
		 case 10:
			 return (TileEntitySteelController) worldObj.getTileEntity(xCoord-1, yCoord, zCoord-1);
		 case 11:
			 return (TileEntitySteelController) worldObj.getTileEntity(xCoord-1, yCoord, zCoord);
		 case 12:
			 return (TileEntitySteelController) worldObj.getTileEntity(xCoord-1, yCoord, zCoord+1);
		 case 13:
			 return (TileEntitySteelController) worldObj.getTileEntity(xCoord, yCoord, zCoord-1);
		 case 14:
			 return (TileEntitySteelController) worldObj.getTileEntity(xCoord, yCoord, zCoord+1);
		 case 15:
			 return (TileEntitySteelController) worldObj.getTileEntity(xCoord+1, yCoord, zCoord-1);
		 case 16:
			 return (TileEntitySteelController) worldObj.getTileEntity(xCoord+1, yCoord, zCoord);
		 case 17:
			 return (TileEntitySteelController) worldObj.getTileEntity(xCoord+1, yCoord, zCoord+1);
		 case 18:
			 return (TileEntitySteelController) worldObj.getTileEntity(xCoord-1, yCoord+1, zCoord-1);
		 case 19:
			 return (TileEntitySteelController) worldObj.getTileEntity(xCoord-1, yCoord+1, zCoord);
		 case 20:
			 return (TileEntitySteelController) worldObj.getTileEntity(xCoord-1, yCoord+1, zCoord+1);
		 case 21:
			 return (TileEntitySteelController) worldObj.getTileEntity(xCoord, yCoord+1, zCoord-1);
		 case 22:
			 return (TileEntitySteelController) worldObj.getTileEntity(xCoord, yCoord+1, zCoord);
		 case 23:
			 return (TileEntitySteelController) worldObj.getTileEntity(xCoord, yCoord+1, zCoord+1);
		 case 24:
			 return (TileEntitySteelController) worldObj.getTileEntity(xCoord+1, yCoord+1, zCoord-1);
		 case 25:
			 return (TileEntitySteelController) worldObj.getTileEntity(xCoord+1, yCoord+1, zCoord);
		 case 26:
			 return (TileEntitySteelController) worldObj.getTileEntity(xCoord+1, yCoord+1, zCoord+1);
	   }
	   return null;
   }
	@Override
	public int[] getAccessibleSlotsFromSide(int p_94128_1_) {
		// TODO Auto-generated method stub
		if(this.getTileForNum()!= null)
		return this.getTileForNum().getAccessibleSlotsFromSide(p_94128_1_);
		else return new int[]{};
	}
	@Override
	public boolean canInsertItem(int par1, ItemStack par2ItemStack, int par3) 
	{
		if(this.getTileForNum()!= null)
		return this.getTileForNum().canInsertItem(par1, par2ItemStack, par3);
		else return false;
	}
	@Override
	public boolean canExtractItem(int par1, ItemStack par2ItemStack, int par3) 
	{
		if(this.getTileForNum()!= null)
		return this.getTileForNum().canExtractItem(par1, par2ItemStack, par3);
		else return false;
	}
}
