package com.sudwood.advancedutilities;

import java.util.ArrayList;
import java.util.Iterator;

import com.sudwood.advancedutilities.blocks.ItemBlockPortaChest;
import com.sudwood.advancedutilities.tileentity.TileEntityPortaChest;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class NBTShapedOreRecipe extends ShapedOreRecipe
{
	private static final int MAX_CRAFT_GRID_WIDTH = 3;
    private static final int MAX_CRAFT_GRID_HEIGHT = 3;
    private int width = 3;
    private int height = 3;
    private ItemStack output = null;
    private Object[] input = null;

	public NBTShapedOreRecipe(ItemStack result, Object[] recipe) 
	{
		super(result, recipe);
		output = result;
		input = recipe;
	}
	@Override
	public ItemStack getCraftingResult(InventoryCrafting var1)
	{ 
		if(input!=null)
		{
			for(int i =0; i < input.length;i++)
			{
				if(input[i] instanceof ItemStack)
				{
					ItemStack temp = (ItemStack) input[i];
					if(temp.getItem() instanceof ItemBlockPortaChest && temp.getTagCompound()!=null)
					{
						if(output!=null)
						{
							int type = temp.getItemDamage();
							switch(type)
							{
							case TileEntityPortaChest.WOOD:
								type = TileEntityPortaChest.BRONZE;
								break;
							case TileEntityPortaChest.BRONZE:
								type = TileEntityPortaChest.GOLD;
								break;
							case TileEntityPortaChest.GOLD:
								type = TileEntityPortaChest.DIAMOND;
								break;
							case TileEntityPortaChest.DIAMOND:
								type = TileEntityPortaChest.PLATINUM;
								break;
							}
							NBTTagCompound tempTag2 = output.getTagCompound();
							temp.writeToNBT(tempTag2);
							tempTag2.setInteger("chestType", type);
							output.setTagCompound(tempTag2);
							output.setItemDamage(type);
							return output.copy();
						}
					}
				}
			}
		}
		return output.copy(); 
	}

	@SuppressWarnings("unused")
	private boolean checkMatch(InventoryCrafting inv, int startX, int startY, boolean mirror)
    {
        for (int x = 0; x < MAX_CRAFT_GRID_WIDTH; x++)
        {
            for (int y = 0; y < MAX_CRAFT_GRID_HEIGHT; y++)
            {
                int subX = x - startX;
                int subY = y - startY;
                Object target = null;

                if (subX >= 0 && subY >= 0 && subX < width && subY < height)
                {
                    if (mirror)
                    {
                        target = input[width - subX - 1 + subY * width];
                    }
                    else
                    {
                        target = input[subX + subY * width];
                    }
                }

                ItemStack slot = inv.getStackInRowAndColumn(x, y);

                if (target instanceof ItemStack)
                {
                    if (!HelperLibrary.areItemStacksSameItemAndDamage((ItemStack)target, slot))
                    {
                        return false;
                    }
                }
                else if (target instanceof ArrayList)
                {
                    boolean matched = false;

                    @SuppressWarnings("unchecked")
					Iterator<ItemStack> itr = ((ArrayList<ItemStack>)target).iterator();
                    while (itr.hasNext() && !matched)
                    {
                        matched = OreDictionary.itemMatches(itr.next(), slot, false);
                    }

                    if (!matched)
                    {
                        return false;
                    }
                }
                else if (target == null && slot != null)
                {
                    return false;
                }
            }
        }

        return true;
    }
}
