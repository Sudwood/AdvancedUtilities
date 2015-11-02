package com.sudwood.advancedutilities.entity.minecart;

import com.sudwood.advancedutilities.blocks.AdvancedUtilitiesBlocks;
import com.sudwood.advancedutilities.items.AdvancedUtilitiesItems;
import com.sudwood.advancedutilities.tileentity.TileEntityTank;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;

public class EntitySpeedyChunkTankCart extends EntityChunkLoadingCart
{
	public FluidTank tank = new FluidTank(FluidContainerRegistry.BUCKET_VOLUME*64);
	public EntitySpeedyChunkTankCart(World par1World) {
		super(par1World);
		// TODO Auto-generated constructor stub
	}
	public EntitySpeedyChunkTankCart(World par1World, double par2, double par4, double par6)
    {
		super(par1World, par2, par4, par6);
    }
	
	public void onUpdate()
    {
		super.onUpdate();
		if(this.motionX == 0 && this.motionY == 0 && this.motionZ == 0)
		{
			if(worldObj.getBlock((int)this.posX, (int)this.posY+1, (int)this.posZ) == AdvancedUtilitiesBlocks.blockTank)
			{
				TileEntityTank tile = (TileEntityTank) worldObj.getTileEntity((int)this.posX, (int)this.posY+1, (int)this.posZ);
				if( tile.tank.getFluidAmount() >= 100 && (this.tank.getFluidAmount()<= 0 || this.tank.getFluid().getFluid() == tile.tank.getFluid().getFluid()) && this.tank.getCapacity()-this.tank.getFluidAmount() >= 100)
				{
					this.tank.fill(tile.drain(ForgeDirection.DOWN, new FluidStack(tile.tank.getFluid().getFluid(), 100), true), true);
					if(tile.tank.getFluidAmount()<=0)
					{
						tile.redstoneTime = tile.maxTime;
					}
				}
				if(tile.tank.getFluidAmount()<=0 || (this.tank.getFluidAmount()> 0 && this.tank.getFluid().getFluid() != tile.tank.getFluid().getFluid()) || this.tank.getFluidAmount() >= this.tank.getCapacity())
				{
					tile.redstoneTime = tile.maxTime;
				}
			}

			if(worldObj.getBlock((int)this.posX, (int)this.posY-1, (int)this.posZ) == AdvancedUtilitiesBlocks.blockTank)
			{
				TileEntityTank tile = (TileEntityTank) worldObj.getTileEntity((int)this.posX, (int)this.posY-1, (int)this.posZ);
				if(this.tank.getFluidAmount() > 0 && (tile.tank.getFluidAmount() <= 0 || this.tank.getFluid().getFluid() == tile.tank.getFluid().getFluid()) && this.tank.getFluidAmount() >= 100 && tile.tank.getCapacity()-tile.tank.getFluidAmount() >= 100)
				{
					tile.tank.fill(tank.drain(100, true), true);
					if(this.tank.getFluidAmount()<=0)
					{
						tile.redstoneTime = tile.maxTime;
					}
					if(this.tank.getFluidAmount()<=0 || (tile.tank.getFluidAmount()> 0 && this.tank.getFluid().getFluid() != tile.tank.getFluid().getFluid()))
					{
						tile.redstoneTime = tile.maxTime;
					}
				}
				if(this.tank.getFluidAmount()<=0)
				{
					tile.redstoneTime = tile.maxTime;
				}
				if(this.tank.getFluidAmount()<=0 || (tile.tank.getFluidAmount()> 0 && this.tank.getFluid().getFluid() != tile.tank.getFluid().getFluid()) || tile.tank.getFluidAmount() >= tile.tank.getCapacity())
				{
					tile.redstoneTime = tile.maxTime;
				}
			}
		}
    }
	
	 protected void func_145821_a(int p_145821_1_, int p_145821_2_, int p_145821_3_, double p_145821_4_, double p_145821_6_, Block p_145821_8_, int p_145821_9_)
	    {
	    	//if(p_145821_8_ == Blocks.golden_rail)
	    		super.func_145821_a(p_145821_1_, p_145821_2_, p_145821_3_, p_145821_4_*4, p_145821_6_, p_145821_8_, p_145821_9_);
	    	/*else
	    		super.func_145821_a(p_145821_1_, p_145821_2_, p_145821_3_, p_145821_4_/4, p_145821_6_, p_145821_8_, p_145821_9_);*/
	    }

	/**
     * Creates a new minecart of the specified type in the specified location in the given world. par0World - world to
     * create the minecart in, double par1,par3,par5 represent x,y,z respectively. int par7 specifies the type: 1 for
     * MinecartChest, 2 for MinecartFurnace, 3 for MinecartTNT, 4 for MinecartMobSpawner, 5 for MinecartHopper and 0 for
     * a standard empty minecart
     */
    public static EntityMinecart createMinecart(World par0World, double par1, double par3, double par5, int par7)
    {
    	return new EntitySpeedyChunkTankCart(par0World, par1, par3, par5);
    }
	@Override
    public Block func_145817_o()
    {
		return AdvancedUtilitiesBlocks.dummyChunkTank;
    }
	
	@Override
	public void readFromNBT(NBTTagCompound tag)
    {
		super.readFromNBT(tag);
		tank.readFromNBT(tag);
    }
	
	@Override
	public void writeToNBT(NBTTagCompound tag)
    {
		super.writeToNBT(tag);
		tank.writeToNBT(tag);
    }
	
	@Override
    public void killMinecart(DamageSource par1DamageSource)
    {
        this.setDead();
        ItemStack itemstack = new ItemStack(AdvancedUtilitiesItems.speedyMinecart, 1);
        this.func_145778_a(Item.getItemFromBlock(AdvancedUtilitiesBlocks.blockTank), 1, 0.0F);
        this.func_145778_a(Item.getItemFromBlock(AdvancedUtilitiesBlocks.chunkLoader), 1, 0.0F);
        this.entityDropItem(itemstack, 0.0F);
    }
	
	@Override
	public boolean interactFirst(EntityPlayer player)
    {
		if(!player.worldObj.isRemote)
		{
			if(player.getCurrentEquippedItem() != null)
			  {
				  if(FluidContainerRegistry.isFilledContainer(player.getCurrentEquippedItem()))
				  {
						  this.tank.fill(FluidContainerRegistry.getFluidForFilledItem(player.getCurrentEquippedItem()), true);
						  if(!player.capabilities.isCreativeMode)
						  {
							  player.inventory.consumeInventoryItem(player.getCurrentEquippedItem().getItem());
							  player.inventory.addItemStackToInventory(new ItemStack(Items.bucket, 1));
						  }
					  }
				  
				  if(FluidContainerRegistry.isEmptyContainer(player.getCurrentEquippedItem()))
				  {
						  if(this.tank.getFluidAmount() > 0 && this.tank.getFluid().getFluid()!= AdvancedUtilitiesBlocks.fluidSteam)
						  {
							  player.inventory.addItemStackToInventory(FluidContainerRegistry.fillFluidContainer(this.tank.drain(FluidContainerRegistry.BUCKET_VOLUME, true), player.getCurrentEquippedItem()));
							  if(!player.capabilities.isCreativeMode)
							  {
								  player.inventory.getCurrentItem().stackSize-=1;
								  if(player.inventory.getCurrentItem().stackSize <= 0)
								  {
									  player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
								  }
							  }
						  }
					  }
				  }
			if(tank.getFluidAmount() > 0)
				player.addChatComponentMessage(new ChatComponentText("Fluid: "+tank.getFluid().getFluid().getName()+" Amount: "+tank.getFluidAmount()+" mB"));
			else
				player.addChatComponentMessage(new ChatComponentText("Empty"));
		}
		return true;
    }
}
