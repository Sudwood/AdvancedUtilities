package com.sudwood.advancedutilities.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

public class ItemBlockMachineBase extends ItemBlockWithMetadata
{
	private String[] names = {"BronzeMachine", "SteelMachine"};
	public ItemBlockMachineBase(Block block1) 
	{
		super(block1, block1);
		// TODO Auto-generated constructor stub
	}
	@Override
	public String getUnlocalizedName(ItemStack stack) 
	{
	   return names[stack.getItemDamage()];
	}

}
