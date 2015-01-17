package com.sudwood.advancedutilities;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.sudwood.advancedutilities.blocks.AdvancedUtilitiesBlocks;
import com.sudwood.advancedutilities.items.AdvancedUtilitiesItems;

public class SmeltryRecipes 
{
	public static ItemStack getSmeltryResult(ItemStack melt, ItemStack mould, int level)
    {
    	if(melt == null || mould == null)
    		return null;
    	
    	if(melt.getItem() ==Item.getItemFromBlock(Blocks.clay))
    	{
    		if(mould.getItem() == Items.stick)
    		{
    			return new ItemStack(AdvancedUtilitiesItems.cast, 1, 0);
    		}
    		if(mould.getItem() == Items.stone_pickaxe)
    		{
    			return new ItemStack(AdvancedUtilitiesItems.cast, 1, 1);
    		}
    		if(mould.getItem() == Items.stone_sword)
    		{
    			return new ItemStack(AdvancedUtilitiesItems.cast, 1, 2);
    		}
    		if(mould.getItem() == Items.stone_shovel)
    		{
    			return new ItemStack(AdvancedUtilitiesItems.cast, 1, 3);
    		}
    		if(mould.getItem() == Items.stone_axe)
    		{
    			return new ItemStack(AdvancedUtilitiesItems.cast, 1, 4);
    		}
    		if(mould.isItemEqual(new ItemStack(AdvancedUtilitiesItems.plate, 1, 3)))
    		{
    			return new ItemStack(AdvancedUtilitiesItems.cast, 1, 5);
    		}
    		if(mould.isItemEqual(new ItemStack(AdvancedUtilitiesItems.stoneRivets, 1)))
    		{
    			return new ItemStack(AdvancedUtilitiesItems.cast, 1, 6);
    		}
    		if(mould.isItemEqual(new ItemStack(AdvancedUtilitiesItems.itemCasing, 1, 0)))
    		{
    			return new ItemStack(AdvancedUtilitiesItems.cast, 1, 8);
    		}
    		if(mould.isItemEqual(new ItemStack(AdvancedUtilitiesItems.itemBulletHead, 1, 0)))
    		{
    			return new ItemStack(AdvancedUtilitiesItems.cast, 1, 7);
    		}
    		if(mould.isItemEqual(new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 10)))
    		{
    			return new ItemStack(AdvancedUtilitiesItems.cast, 1, 9);
    		}
    	}
    	if(melt.getItem() == Items.iron_ingot)
    	{
    		if(mould.isItemEqual(new ItemStack(AdvancedUtilitiesItems.cast, 1, 0)))
    		{
    			return new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 1);
    		}
    		if(mould.isItemEqual(new ItemStack(AdvancedUtilitiesItems.cast, 1, 1)))
    		{
    			return new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 2);
    		}
    		if(mould.isItemEqual(new ItemStack(AdvancedUtilitiesItems.cast, 1, 2)))
    		{
    			return new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 3);
    		}
    		if(mould.isItemEqual(new ItemStack(AdvancedUtilitiesItems.cast, 1, 3)))
    		{
    			return new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 4);
    		}
    		if(mould.isItemEqual(new ItemStack(AdvancedUtilitiesItems.cast, 1, 4)))
    		{
    			return new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 5);
    		}
    		if(mould.isItemEqual(new ItemStack(AdvancedUtilitiesItems.cast, 1, 5)))
    		{
    			return new ItemStack(AdvancedUtilitiesItems.plate, 1, 0);
    		}
    		if(mould.isItemEqual(new ItemStack(AdvancedUtilitiesItems.cast, 1, 9)) && melt.stackSize >= 8)
    		{
    			return new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 12);
    		}
    	}
    	if(OreDictionary.getOreIDs(melt)!= null && OreDictionary.getOreIDs(melt).length > 0 && OreDictionary.getOreIDs(melt)[0] == OreDictionary.getOreIDs(new ItemStack(AdvancedUtilitiesItems.ingotBronze, 1))[0])
    	{
    		if(mould.isItemEqual(new ItemStack(AdvancedUtilitiesItems.cast, 1, 0)))
    		{
    			return new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 0);
    		}
    		if(mould.isItemEqual(new ItemStack(AdvancedUtilitiesItems.cast, 1, 1)))
    		{
    			return new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 6);
    		}
    		if(mould.isItemEqual(new ItemStack(AdvancedUtilitiesItems.cast, 1, 2)))
    		{
    			return new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 7);
    		}
    		if(mould.isItemEqual(new ItemStack(AdvancedUtilitiesItems.cast, 1, 3)))
    		{
    			return new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 8);
    		}
    		if(mould.isItemEqual(new ItemStack(AdvancedUtilitiesItems.cast, 1, 4)))
    		{
    			return new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 9);
    		}
    		if(mould.isItemEqual(new ItemStack(AdvancedUtilitiesItems.cast, 1, 5)))
    		{
    			return new ItemStack(AdvancedUtilitiesItems.plate, 1, 1);
    		}
    		if(mould.isItemEqual(new ItemStack(AdvancedUtilitiesItems.cast, 1, 8)))
    		{
    			return new ItemStack(AdvancedUtilitiesItems.itemCasing, 16, 1);
    		}
    		if(mould.isItemEqual(new ItemStack(AdvancedUtilitiesItems.cast, 1, 9)) && melt.stackSize >= 8)
    		{
    			return new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 11);
    		}
    	}
    	if(OreDictionary.getOreIDs(melt)!= null && OreDictionary.getOreIDs(melt).length > 0 && OreDictionary.getOreIDs(melt)[0] == OreDictionary.getOreIDs(new ItemStack(AdvancedUtilitiesItems.ingotBrass, 1))[0])
    	{
    		if(mould.isItemEqual(new ItemStack(AdvancedUtilitiesItems.cast, 1, 5)))
    		{
    			return new ItemStack(AdvancedUtilitiesItems.plate, 1, 2);
    		}
    		if(mould.isItemEqual(new ItemStack(AdvancedUtilitiesItems.cast, 1, 6)))
    		{
    			return new ItemStack(AdvancedUtilitiesItems.brassRivets, 1);
    		}
    	}
    	if(OreDictionary.getOreIDs(melt)!= null && OreDictionary.getOreIDs(melt).length > 0 && OreDictionary.getOreIDs(melt)[0] == OreDictionary.getOreIDs(new ItemStack(AdvancedUtilitiesItems.ingotLead, 1))[0])
    	{
    		if(mould.isItemEqual(new ItemStack(AdvancedUtilitiesItems.cast, 1, 7)))
    		{
    			return new ItemStack(AdvancedUtilitiesItems.itemBulletHead, 16, 1);
    		}
    	}
    	if(level >= 1)
    	{
    		if(HelperLibrary.isOreDicItem(melt, new ItemStack(AdvancedUtilitiesBlocks.blockSteelBlock, 1)))
    		{
    			if(mould.isItemEqual(new ItemStack(AdvancedUtilitiesItems.cast, 1, 5)))
        		{
        			return new ItemStack(AdvancedUtilitiesItems.plate, 1, 4);
        		}
    			if(mould.isItemEqual(new ItemStack(AdvancedUtilitiesItems.cast, 1, 6)))
        		{
        			return new ItemStack(AdvancedUtilitiesItems.steelRivets, 1);
        		}
    		}
    	}
    	return null;
    }
}
