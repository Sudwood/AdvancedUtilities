package com.sudwood.advancedutilities.blocks;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.IFluidContainerItem;

public class ItemBlockTank extends ItemBlock implements IFluidContainerItem
{
	public ItemBlockTank(Block p_i45328_1_) {
		super(p_i45328_1_);
		// TODO Auto-generated constructor stub
	}
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) 
    {
		if(stack!=null)
		{
			NBTTagCompound tag = stack.getTagCompound();
			if(tag!=null)
			{
				FluidStack temp = FluidStack.loadFluidStackFromNBT(tag);
				if(temp!=null)
				{
					list.add("Fluid: "+temp.getLocalizedName());
					list.add("Amount: "+temp.amount+" mB");
				}
			}
		}
    }
	@Override
	public FluidStack getFluid(ItemStack container) 
	{
		if(container.getItem() instanceof ItemBlockTank)
		{
			FluidTank tank = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME*64);
			NBTTagCompound tag = container.getTagCompound();
			if(tag!=null)
			{
				tank.readFromNBT(tag);
				return tank.getFluid();
			}
		}
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getCapacity(ItemStack container) 
	{
		if(container.getItem() instanceof ItemBlockTank)
		{
			FluidTank tank = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME*64);
			return tank.getCapacity();
		}
		return 0;
	}
	@Override
	public int fill(ItemStack container, FluidStack resource, boolean doFill) {
		if(container.getItem() instanceof ItemBlockTank)
		{
			FluidTank tank = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME*64);
			NBTTagCompound tag = container.getTagCompound();
			if(tag!=null)
			{
				tank.readFromNBT(tag);
				int result = 0;
				if(resource.getFluid() == tank.getFluid().getFluid() && tank.getFluidAmount() >= resource.amount)
				{
				result = tank.fill(resource, doFill);
				tank.writeToNBT(tag);
				container.setTagCompound(tag);
				}
				
				return result;
			}
		}
		return 0;
	}
	@Override
	public FluidStack drain(ItemStack container, int maxDrain, boolean doDrain) {
		if(container.getItem() instanceof ItemBlockTank)
		{
			FluidTank tank = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME*64);
			NBTTagCompound tag = container.getTagCompound();
			if(tag!=null)
			{
				tank.readFromNBT(tag);
				FluidStack result = null;
				if(tank.getFluidAmount() >= maxDrain)
				{
				result = tank.drain(maxDrain, doDrain);
				tank.writeToNBT(tag);
				container.setTagCompound(tag);
				}
				
				return result;
			}
		}
		return null;
	}

}
