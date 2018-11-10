package com.sudwood.advancedutilities;

import com.sudwood.advancedutilities.blocks.AdvancedUtilitiesBlocks;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class RecipePortaChest implements IRecipe
{
	private ItemStack[] input;
	private ItemStack output;
	public RecipePortaChest(ItemStack[] in, ItemStack out)
	{
		input = in;
		output = out;
	}
	@Override
	public boolean matches(InventoryCrafting craft, World world) 
	{
		boolean isNBT = false;
		boolean isOther = false;
		if(input!=null&&input[4]!=null&&(input[4].getItem() == Item.getItemFromBlock(AdvancedUtilitiesBlocks.portaChestWood) || input[4].getItem() == Item.getItemFromBlock(AdvancedUtilitiesBlocks.portaChestBronze) 
				|| input[4].getItem() == Item.getItemFromBlock(AdvancedUtilitiesBlocks.portaChestGold) || input[4].getItem() == Item.getItemFromBlock(AdvancedUtilitiesBlocks.portaChestDiamond) 
						|| input[4].getItem() == Item.getItemFromBlock(AdvancedUtilitiesBlocks.portaChestPlatinum)))
		{
			if(craft.getStackInRowAndColumn(1, 1)!=null)
			{
				if(HelperLibrary.areItemStacksSameItemAndDamage(input[4], craft.getStackInRowAndColumn(1, 1)))
				{
					isNBT = true;
				}
			}
		}
		if(input!=null&&HelperLibrary.areItemStacksSameItemAndDamage(input[0], craft.getStackInRowAndColumn(0, 0))&&HelperLibrary.areItemStacksSameItemAndDamage(input[1], craft.getStackInRowAndColumn(0, 1))
				&&HelperLibrary.areItemStacksSameItemAndDamage(input[2], craft.getStackInRowAndColumn(0, 2))&&HelperLibrary.areItemStacksSameItemAndDamage(input[3], craft.getStackInRowAndColumn(1, 0))
				&&HelperLibrary.areItemStacksSameItemAndDamage(input[5], craft.getStackInRowAndColumn(1, 2))&&HelperLibrary.areItemStacksSameItemAndDamage(input[6], craft.getStackInRowAndColumn(2, 0))
				&&HelperLibrary.areItemStacksSameItemAndDamage(input[7], craft.getStackInRowAndColumn(2, 1))&&HelperLibrary.areItemStacksSameItemAndDamage(input[8], craft.getStackInRowAndColumn(2, 2)))
		{
			isOther=true;
		}
		return isNBT&&isOther;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting craft) 
	{
		if(craft.getStackInRowAndColumn(1,1)!=null)
		{
			NBTTagCompound craftTag = craft.getStackInRowAndColumn(1,1).getTagCompound();
			NBTTagCompound outTag = output.getTagCompound();
			if(craftTag!=null&&outTag!=null)
			{
				int tempType = outTag.getInteger("chestType");
				outTag = (NBTTagCompound) craftTag.copy();
				outTag.setInteger("chestType", tempType);
				output.setTagCompound(outTag);
			}
			
		}
		return output.copy();
	}

	@Override
	public int getRecipeSize() 
	{
		return 9;
	}

	@Override
	public ItemStack getRecipeOutput() 
	{
		return output.copy();
	}
	
	 public Object[] getInput()
	    {
	        return this.input;
	    }

}
