package com.sudwood.advancedutilities;

import com.sudwood.advancedutilities.blocks.AdvancedUtilitiesBlocks;
import com.sudwood.advancedutilities.blocks.BlockIngotBlock;
import com.sudwood.advancedutilities.items.AdvancedUtilitiesItems;
import com.sudwood.advancedutilities.items.ItemIngot;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CompressRecipes 
{
	public static ItemStack getCompressResult(ItemStack stack)
	{
		if(HelperLibrary.isOreDicItem(stack, new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.ALUMINUM)))
		{
			return new ItemStack(AdvancedUtilitiesBlocks.ingotBlock, 1, BlockIngotBlock.ALUMINUM);
		}
		if(HelperLibrary.isOreDicItem(stack, new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.BRASS)))
		{
			return new ItemStack(AdvancedUtilitiesBlocks.ingotBlock, 1, BlockIngotBlock.BRASS);
		}
		if(HelperLibrary.isOreDicItem(stack, new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.BRONZE)))
		{
			return new ItemStack(AdvancedUtilitiesBlocks.ingotBlock, 1, BlockIngotBlock.BRONZE);
		}
		if(HelperLibrary.isOreDicItem(stack, new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.COPPER)))
		{
			return new ItemStack(AdvancedUtilitiesBlocks.ingotBlock, 1, BlockIngotBlock.COPPER);
		}
		if(HelperLibrary.isOreDicItem(stack, new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.IRIDIUM)))
		{
			return new ItemStack(AdvancedUtilitiesBlocks.ingotBlock, 1, BlockIngotBlock.IRIDIUM);
		}
		if(HelperLibrary.isOreDicItem(stack, new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.LEAD)))
		{
			return new ItemStack(AdvancedUtilitiesBlocks.ingotBlock, 1, BlockIngotBlock.LEAD);
		}
		if(HelperLibrary.isOreDicItem(stack, new ItemStack(Items.iron_ingot)))
		{
			return new ItemStack(AdvancedUtilitiesBlocks.ingotBlock, 1, BlockIngotBlock.IRON);
		}
		if(HelperLibrary.isOreDicItem(stack, new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.NICKEL)))
		{
			return new ItemStack(AdvancedUtilitiesBlocks.ingotBlock, 1, BlockIngotBlock.NICKEL);
		}
		if(HelperLibrary.isOreDicItem(stack, new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.PALIDIUM)))
		{
			return new ItemStack(AdvancedUtilitiesBlocks.ingotBlock, 1, BlockIngotBlock.PALIDIUM);
		}
		if(HelperLibrary.isOreDicItem(stack, new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.PLATINUM)))
		{
			return new ItemStack(AdvancedUtilitiesBlocks.ingotBlock, 1, BlockIngotBlock.PLATINUM);
		}
		if(HelperLibrary.isOreDicItem(stack, new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.SILVER)))
		{
			return new ItemStack(AdvancedUtilitiesBlocks.ingotBlock, 1, BlockIngotBlock.SILVER);
		}
		if(HelperLibrary.isOreDicItem(stack, new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.TIN)))
		{
			return new ItemStack(AdvancedUtilitiesBlocks.ingotBlock, 1, BlockIngotBlock.TIN);
		}
		if(HelperLibrary.isOreDicItem(stack, new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.STEEL)))
		{
			return new ItemStack(AdvancedUtilitiesBlocks.ingotBlock, 1, BlockIngotBlock.STEEL);
		}
		if(HelperLibrary.isOreDicItem(stack, new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.TUNGSTEN)))
		{
			return new ItemStack(AdvancedUtilitiesBlocks.ingotBlock, 1, BlockIngotBlock.TUNGSTEN);
		}
		if(HelperLibrary.isOreDicItem(stack, new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.ZINC)))
		{
			return new ItemStack(AdvancedUtilitiesBlocks.ingotBlock, 1, BlockIngotBlock.ZINC);
		}
		if(HelperLibrary.isOreDicItem(stack, new ItemStack(Items.gold_ingot, 1)))
		{
			return new ItemStack(Blocks.gold_block, 1);
		}
		if(HelperLibrary.areItemStacksSameItemAndDamage(stack, new ItemStack(Items.coal, 1, 0)))
		{
			return new ItemStack(Blocks.coal_block, 1);
		}
		if(HelperLibrary.areItemStacksSameItemAndDamage(stack, new ItemStack(Items.redstone, 1)))
		{
			return new ItemStack(Blocks.redstone_block, 1);
		}
		if(HelperLibrary.areItemStacksSameItemAndDamage(stack, new ItemStack(Items.dye, 1, 4)))
		{
			return new ItemStack(Blocks.lapis_block, 1);
		}
		return null;
	}
}
