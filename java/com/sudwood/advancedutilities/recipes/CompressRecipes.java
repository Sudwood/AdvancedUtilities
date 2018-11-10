package com.sudwood.advancedutilities.recipes;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.sudwood.advancedutilities.HelperLibrary;
import com.sudwood.advancedutilities.blocks.AdvancedUtilitiesBlocks;
import com.sudwood.advancedutilities.blocks.BlockCompressedBlock;
import com.sudwood.advancedutilities.blocks.BlockIngotBlock;
import com.sudwood.advancedutilities.config.RecipeConfig;
import com.sudwood.advancedutilities.items.AdvancedUtilitiesItems;
import com.sudwood.advancedutilities.items.ItemIngot;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class CompressRecipes 
{
	private static Map<ItemStack, ItemStack> recipes = new HashMap();
	
	public static void registerRecipes()
	{
		addRecipe(new ItemStack(AdvancedUtilitiesBlocks.ingotBlock, 1, BlockIngotBlock.ALUMINUM), new ItemStack(AdvancedUtilitiesItems.ingot, 9, ItemIngot.ALUMINUM));
		addRecipe(new ItemStack(AdvancedUtilitiesBlocks.ingotBlock, 1, BlockIngotBlock.BRASS), new ItemStack(AdvancedUtilitiesItems.ingot, 9, ItemIngot.BRASS));
		addRecipe(new ItemStack(AdvancedUtilitiesBlocks.ingotBlock, 1, BlockIngotBlock.BRONZE), new ItemStack(AdvancedUtilitiesItems.ingot, 9, ItemIngot.BRONZE));
		addRecipe(new ItemStack(AdvancedUtilitiesBlocks.ingotBlock, 1, BlockIngotBlock.COPPER), new ItemStack(AdvancedUtilitiesItems.ingot, 9, ItemIngot.COPPER));
		addRecipe(new ItemStack(AdvancedUtilitiesBlocks.ingotBlock, 1, BlockIngotBlock.IRIDIUM), new ItemStack(AdvancedUtilitiesItems.ingot, 9, ItemIngot.IRIDIUM));
		addRecipe(new ItemStack(AdvancedUtilitiesBlocks.ingotBlock, 1, BlockIngotBlock.LEAD), new ItemStack(AdvancedUtilitiesItems.ingot, 9, ItemIngot.LEAD));
		addRecipe(new ItemStack(AdvancedUtilitiesBlocks.ingotBlock, 1, BlockIngotBlock.IRON), new ItemStack(Items.iron_ingot, 9));
		addRecipe(new ItemStack(AdvancedUtilitiesBlocks.ingotBlock, 1, BlockIngotBlock.NICKEL), new ItemStack(AdvancedUtilitiesItems.ingot, 9, ItemIngot.NICKEL));
		addRecipe(new ItemStack(AdvancedUtilitiesBlocks.ingotBlock, 1, BlockIngotBlock.PALIDIUM), new ItemStack(AdvancedUtilitiesItems.ingot, 9, ItemIngot.PALIDIUM));
		addRecipe(new ItemStack(AdvancedUtilitiesBlocks.ingotBlock, 1, BlockIngotBlock.PLATINUM), new ItemStack(AdvancedUtilitiesItems.ingot, 9, ItemIngot.PLATINUM));
		addRecipe(new ItemStack(AdvancedUtilitiesBlocks.ingotBlock, 1, BlockIngotBlock.SILVER), new ItemStack(AdvancedUtilitiesItems.ingot, 9, ItemIngot.SILVER));
		addRecipe(new ItemStack(AdvancedUtilitiesBlocks.ingotBlock, 1, BlockIngotBlock.TIN), new ItemStack(AdvancedUtilitiesItems.ingot, 9, ItemIngot.TIN));
		addRecipe(new ItemStack(AdvancedUtilitiesBlocks.ingotBlock, 1, BlockIngotBlock.STEEL), new ItemStack(AdvancedUtilitiesItems.ingot, 9, ItemIngot.STEEL));
		addRecipe(new ItemStack(AdvancedUtilitiesBlocks.ingotBlock, 1, BlockIngotBlock.TUNGSTEN), new ItemStack(AdvancedUtilitiesItems.ingot, 9, ItemIngot.TUNGSTEN));
		addRecipe(new ItemStack(AdvancedUtilitiesBlocks.ingotBlock, 1, BlockIngotBlock.ZINC), new ItemStack(AdvancedUtilitiesItems.ingot, 9, ItemIngot.ZINC));
		addRecipe(new ItemStack(Blocks.gold_block, 1), new ItemStack(Items.gold_ingot, 9));
		addRecipe(new ItemStack(Blocks.coal_block, 1), new ItemStack(Items.coal, 9, 0));
		addRecipe(new ItemStack(AdvancedUtilitiesBlocks.compressedBlock, 1, BlockCompressedBlock.SINGLECHARCOAL), new ItemStack(Items.coal, 9, 1));
		addRecipe(new ItemStack(AdvancedUtilitiesBlocks.compressedBlock, 1, BlockCompressedBlock.DOUBLECHARCOAL), new ItemStack(AdvancedUtilitiesBlocks.compressedBlock, 9, BlockCompressedBlock.SINGLECHARCOAL));
		addRecipe(new ItemStack(Blocks.redstone_block,1), new ItemStack(Items.redstone, 9));
		addRecipe(new ItemStack(Blocks.lapis_block,1), new ItemStack(Items.dye, 9, 4));
		
	}
	public static void addRecipe(ItemStack result, ItemStack ingr)
	{
		recipes.put(result, ingr);
	}
	
	public static Map getRecipes()
	{
		return recipes;
	}
	
	public static ItemStack getCompressResult(ItemStack stack)
	{
		if(stack == null)
			return null;
		for(Entry<ItemStack, ItemStack> recipe: recipes.entrySet())
		{
			if(HelperLibrary.isOreDicItem(stack, recipe.getValue()) || HelperLibrary.areItemStacksSameItemAndDamage(stack, recipe.getValue()))
			{
				if(stack.stackSize >= recipe.getValue().stackSize)
				{
					return recipe.getKey().copy();
				}
			}
		}
		return null;
	}
	
	public static boolean getCompressResultForAutomation(ItemStack stack)
	{
		if(stack == null)
			return false;
		for(Entry<ItemStack, ItemStack> recipe: recipes.entrySet())
		{
			if(HelperLibrary.isOreDicItem(stack, recipe.getValue()) || HelperLibrary.areItemStacksSameItemAndDamage(stack, recipe.getValue()))
			{

					return true;
			}
		}
		return false;
	}
	
	public static int getIngredientSize(ItemStack stack)
	{
		int result = 0;
		if(stack == null)
			return 0;
		for(Entry<ItemStack, ItemStack> recipe: recipes.entrySet())
		{
			if(HelperLibrary.isOreDicItem(stack, recipe.getValue()) || HelperLibrary.areItemStacksSameItemAndDamage(stack, recipe.getValue()))
			{
				if(stack.stackSize >= recipe.getValue().stackSize)
				{
					result = recipe.getValue().stackSize;
				}
			}
		}
		return result;
	}
	public static ItemStack getCompressResultold(ItemStack stack)
	{
		if(HelperLibrary.isOreDicItem(stack, new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.ALUMINUM)) || HelperLibrary.areItemStacksSameItemAndDamage(stack, new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.ALUMINUM)))
		{
			return new ItemStack(AdvancedUtilitiesBlocks.ingotBlock, 1, BlockIngotBlock.ALUMINUM);
		}
		if(HelperLibrary.isOreDicItem(stack, new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.BRASS)) || HelperLibrary.areItemStacksSameItemAndDamage(stack, new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.BRASS)))
		{
			return new ItemStack(AdvancedUtilitiesBlocks.ingotBlock, 1, BlockIngotBlock.BRASS);
		}
		if(HelperLibrary.isOreDicItem(stack, new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.BRONZE)) || HelperLibrary.areItemStacksSameItemAndDamage(stack, new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.BRONZE)))
		{
			return new ItemStack(AdvancedUtilitiesBlocks.ingotBlock, 1, BlockIngotBlock.BRONZE);
		}
		if(HelperLibrary.isOreDicItem(stack, new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.COPPER)) || HelperLibrary.areItemStacksSameItemAndDamage(stack, new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.COPPER)))
		{
			return new ItemStack(AdvancedUtilitiesBlocks.ingotBlock, 1, BlockIngotBlock.COPPER);
		}
		if(HelperLibrary.isOreDicItem(stack, new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.IRIDIUM)) || HelperLibrary.areItemStacksSameItemAndDamage(stack, new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.IRIDIUM)))
		{
			return new ItemStack(AdvancedUtilitiesBlocks.ingotBlock, 1, BlockIngotBlock.IRIDIUM);
		}
		if(HelperLibrary.isOreDicItem(stack, new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.LEAD)) || HelperLibrary.areItemStacksSameItemAndDamage(stack, new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.LEAD)))
		{
			return new ItemStack(AdvancedUtilitiesBlocks.ingotBlock, 1, BlockIngotBlock.LEAD);
		}
		if(HelperLibrary.isOreDicItem(stack, new ItemStack(Items.iron_ingot)))
		{
			return new ItemStack(AdvancedUtilitiesBlocks.ingotBlock, 1, BlockIngotBlock.IRON);
		}
		if(HelperLibrary.isOreDicItem(stack, new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.NICKEL)) || HelperLibrary.areItemStacksSameItemAndDamage(stack, new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.NICKEL)))
		{
			return new ItemStack(AdvancedUtilitiesBlocks.ingotBlock, 1, BlockIngotBlock.NICKEL);
		}
		if(HelperLibrary.isOreDicItem(stack, new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.PALIDIUM)) || HelperLibrary.areItemStacksSameItemAndDamage(stack, new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.PALIDIUM)))
		{
			return new ItemStack(AdvancedUtilitiesBlocks.ingotBlock, 1, BlockIngotBlock.PALIDIUM);
		}
		if(HelperLibrary.isOreDicItem(stack, new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.PLATINUM)) || HelperLibrary.areItemStacksSameItemAndDamage(stack, new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.PLATINUM)))
		{
			return new ItemStack(AdvancedUtilitiesBlocks.ingotBlock, 1, BlockIngotBlock.PLATINUM);
		}
		if(HelperLibrary.isOreDicItem(stack, new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.SILVER)) || HelperLibrary.areItemStacksSameItemAndDamage(stack, new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.SILVER)))
		{
			return new ItemStack(AdvancedUtilitiesBlocks.ingotBlock, 1, BlockIngotBlock.SILVER);
		}
		if(HelperLibrary.isOreDicItem(stack, new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.TIN)) || HelperLibrary.areItemStacksSameItemAndDamage(stack, new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.TIN)))
		{
			return new ItemStack(AdvancedUtilitiesBlocks.ingotBlock, 1, BlockIngotBlock.TIN);
		}
		if(HelperLibrary.isOreDicItem(stack, new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.STEEL)) || HelperLibrary.areItemStacksSameItemAndDamage(stack, new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.STEEL)))
		{
			return new ItemStack(AdvancedUtilitiesBlocks.ingotBlock, 1, BlockIngotBlock.STEEL);
		}
		if(HelperLibrary.isOreDicItem(stack, new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.TUNGSTEN)) || HelperLibrary.areItemStacksSameItemAndDamage(stack, new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.TUNGSTEN)))
		{
			return new ItemStack(AdvancedUtilitiesBlocks.ingotBlock, 1, BlockIngotBlock.TUNGSTEN);
		}
		if(HelperLibrary.isOreDicItem(stack, new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.ZINC)) || HelperLibrary.areItemStacksSameItemAndDamage(stack, new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.ZINC)))
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
		if(HelperLibrary.areItemStacksSameItemAndDamage(stack, new ItemStack(Items.coal, 1, 1)))
		{
			return new ItemStack(AdvancedUtilitiesBlocks.compressedBlock, 1, BlockCompressedBlock.SINGLECHARCOAL);
		}
		if(HelperLibrary.areItemStacksSameItemAndDamage(stack, new ItemStack(AdvancedUtilitiesBlocks.compressedBlock, 1, BlockCompressedBlock.SINGLECHARCOAL)))
		{
			return new ItemStack(AdvancedUtilitiesBlocks.compressedBlock, 1, BlockCompressedBlock.DOUBLECHARCOAL);
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
	
	public static void addCompressRecipe(String melt, int meltCount,int meltDam, boolean meltBlock, String result, int resultCount, int resultDam, boolean resultBlock)
	{
		Map<ItemStack, ItemStack> temp = new HashMap<ItemStack, ItemStack>();
		Object temp1;
		Object temp3;
		ItemStack pmet1;
		ItemStack pmet3;
		if(meltBlock)
		{
			temp1=HelperLibrary.getBlock(melt);
			pmet1= new ItemStack((Block)temp1, meltCount, meltDam);
		}
		else
		{
			temp1 = HelperLibrary.getItem(melt);
			pmet1= new ItemStack((Item)temp1, meltCount, meltDam);
		}

		if(resultBlock)
		{
			temp3 = HelperLibrary.getBlock(result);
			pmet3= new ItemStack((Block)temp3, resultCount, resultDam);
		}
		else
		{
			temp3 = HelperLibrary.getItem(result);
			pmet3= new ItemStack((Item)temp3, resultCount, resultDam);
		}
		
		if(temp1!=null &&temp3 !=null &&pmet1!=null&&pmet3!=null)
		{
			
			recipes.put(pmet3,pmet1);
		}
	}
	
	public static void registerCompressRecipesString()
	{
		if(RecipeConfig.CompressRecipes!=null && RecipeConfig.CompressRecipes.length >0)
		{
			for(int i = 0; i<RecipeConfig.CompressRecipes.length; i++)
			{
				String[] recipeArray = RecipeConfig.CompressRecipes[i].split(",");
				/*recipeArray[0]; //melt item
				recipeArray[1]; // melt size
				recipeArray[2]; // melt dam
				recipeArray[3]; //melt block
				recipeArray[8]; // result item
				recipeArray[9]; // result size
				recipeArray[10]; // resultdam
				recipeArray[11]; // result block*/
					if(recipeArray.length > 7)
					{
					String melt = recipeArray[0];
					int meltSize = Integer.parseInt(recipeArray[1]);
					int meltDam = Integer.parseInt(recipeArray[2]);
					Boolean meltBlock = Boolean.valueOf(recipeArray[3]);
					
					String result = recipeArray[4];
					int resultSize = Integer.parseInt(recipeArray[5]);
					int resultDam = Integer.parseInt(recipeArray[6]);
					Boolean resultBlock = Boolean.valueOf(recipeArray[7]);
					
					addCompressRecipe(melt, meltSize, meltDam, meltBlock,result, resultSize, resultDam, resultBlock);
					}

				
			}
		}
	}
	
	public static String[] getCompressString()
	{
		String[] result = new String[recipes.size()];
		int count = 0;
		for(Map.Entry<ItemStack, ItemStack> recipe : recipes.entrySet())
		{
				String meltName;
				String mouldName;
				String resultName;
				boolean isMeltBlock = false;
				boolean isMouldBlock = false;
				boolean isResultBlock = false;
				GameRegistry.UniqueIdentifier idMelt = GameRegistry.findUniqueIdentifierFor(recipe.getValue().getItem());
				meltName  = idMelt.toString();
				GameRegistry.UniqueIdentifier idResult = GameRegistry.findUniqueIdentifierFor(recipe.getKey().getItem());
				resultName  = idResult.toString();
				if(recipe.getValue().getItem() instanceof ItemBlock)
				{
					isMeltBlock = true;
				}
				if(recipe.getKey().getItem() instanceof ItemBlock)
				{
					isResultBlock = true;
				}
				
				String temp = meltName+","+recipe.getValue().stackSize+","+recipe.getValue().getItemDamage()+","+isMeltBlock+","+resultName+","+recipe.getKey().stackSize+","+recipe.getKey().getItemDamage()+","+isResultBlock;
				result[count] = temp;
				count++;
			
		}
		
		return result;
	}
	
	public static final String[] defaultRecipes = new String[]
			{
					"minecraft:coal,9,1,false,advancedutilities:compressedblock,1,0,true",
			        "advancedutilities:ingot,9,12,false,advancedutilities:ingotblock,1,12,true",
			        "advancedutilities:ingot,9,8,false,advancedutilities:ingotblock,1,8,true",
			        "advancedutilities:ingot,9,0,false,advancedutilities:ingotblock,1,0,true",
			        "advancedutilities:ingot,9,11,false,advancedutilities:ingotblock,1,11,true",
			        "minecraft:coal,9,0,false,minecraft:coal_block,1,0,true",
			        "advancedutilities:ingot,9,3,false,advancedutilities:ingotblock,1,3,true",
			        "advancedutilities:ingot,9,5,false,advancedutilities:ingotblock,1,5,true",
			        "advancedutilities:ingot,9,4,false,advancedutilities:ingotblock,1,4,true",
			        "advancedutilities:ingot,9,2,false,advancedutilities:ingotblock,1,2,true",
			        "advancedutilities:ingot,9,13,false,advancedutilities:ingotblock,1,13,true",
			        "minecraft:gold_ingot,9,0,false,minecraft:gold_block,1,0,true",
			        "advancedutilities:ingot,9,7,false,advancedutilities:ingotblock,1,7,true",
			        "minecraft:iron_ingot,9,0,false,advancedutilities:ingotblock,1,14,true",
			        "minecraft:redstone,9,0,false,minecraft:redstone_block,1,0,true",
			        "minecraft:dye,9,4,false,minecraft:lapis_block,1,0,true",
			        "advancedutilities:ingot,9,10,false,advancedutilities:ingotblock,1,10,true",
			        "advancedutilities:ingot,9,6,false,advancedutilities:ingotblock,1,6,true",
			        "advancedutilities:compressedblock,9,0,true,advancedutilities:compressedblock,1,1,true",
			        "advancedutilities:ingot,9,9,false,advancedutilities:ingotblock,1,9,true",
			        "advancedutilities:ingot,9,1,false,advancedutilities:ingotblock,1,1,true"
			};
}
