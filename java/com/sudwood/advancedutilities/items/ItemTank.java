package com.sudwood.advancedutilities.items;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import com.sudwood.advancedutilities.tileentity.TileEntityTank;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemTank extends ItemBlock{

	public ItemTank(Block block) {
		super(block);
		this.maxStackSize = 1;
		// TODO Auto-generated constructor stub
	}

	@Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) 
    {
		if(stack.getTagCompound() == null)
		   {
			   stack.setTagCompound(new NBTTagCompound());
		   }
		   NBTTagCompound tag = stack.getTagCompound();
		par3List.add("Fluid: " + tag.getString("fluid"));
		par3List.add("Amount: " + tag.getInteger("fluidamount")+" mB");
    }
	@SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister icon)
    {
		this.itemIcon = icon.registerIcon("advancedutilities:copperingot");
    }
	
	@Override
	public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
    {

       if (!world.setBlock(x, y, z, field_150939_a, metadata, 3))
       {
           return false;
       }

       if (world.getBlock(x, y, z) == field_150939_a)
       {
    	   if(world.getTileEntity(x, y, z)!=null)
    	   {
    		   if(world.getTileEntity(x, y, z) instanceof TileEntityTank)
    		   {
    			   TileEntityTank tile = (TileEntityTank) world.getTileEntity(x, y, z);
    			   if(stack.getTagCompound() != null)
    			   {
    				   NBTTagCompound tag = stack.getTagCompound();
    				   if(tag.getInteger("fluidamount") >0)
        			   tile.tank.setFluid(new FluidStack(FluidRegistry.getFluid(tag.getString("fluid")), tag.getInteger("fluidamount")));
    			   }
    			   
    		   }
    	   }
           field_150939_a.onBlockPlacedBy(world, x, y, z, player, stack);
           field_150939_a.onPostBlockPlaced(world, x, y, z, metadata);
       }

       return true;
    }
	
}
