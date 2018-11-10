package com.sudwood.advancedutilities;

import com.sudwood.advancedutilities.blocks.AdvancedUtilitiesBlocks;
import com.sudwood.advancedutilities.blocks.BlockCompressedBlock;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;

public class AdvancedFuelHandler implements IFuelHandler{

	@Override
	public int getBurnTime(ItemStack fuel) 
	{
		if(HelperLibrary.areItemStacksSameItemAndDamage(fuel, new ItemStack(AdvancedUtilitiesBlocks.compressedBlock, 1, BlockCompressedBlock.SINGLECHARCOAL)))
		{
			return 14400;
		}
		if(HelperLibrary.areItemStacksSameItemAndDamage(fuel, new ItemStack(AdvancedUtilitiesBlocks.compressedBlock, 1, BlockCompressedBlock.DOUBLECHARCOAL)))
		{
			return 129600;
		}
		return 0;
	}

}
