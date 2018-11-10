package com.sudwood.advancedutilities.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import com.sudwood.advancedutilities.tileentity.TileEntityWoodenCrate;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBlockCrate extends ItemBlock{

	public ItemBlockCrate(Block p_i45328_1_) {
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
				TileEntityWoodenCrate temp = new TileEntityWoodenCrate();
				temp.readInventory(tag);
				if(temp.checkStackSize() > 0)
				{
					list.add("Contains Items");
				}
			}
		}
    }

}
