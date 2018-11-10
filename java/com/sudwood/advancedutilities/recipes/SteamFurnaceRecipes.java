package com.sudwood.advancedutilities.recipes;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.sudwood.advancedutilities.HelperLibrary;
import com.sudwood.advancedutilities.blocks.AdvancedUtilitiesBlocks;
import com.sudwood.advancedutilities.blocks.BlockIngotBlock;
import com.sudwood.advancedutilities.config.RecipeConfig;
import com.sudwood.advancedutilities.items.AdvancedUtilitiesItems;
import com.sudwood.advancedutilities.items.ItemIngot;

import cpw.mods.fml.common.registry.GameData;
import cpw.mods.fml.common.registry.GameRegistry;

public class SteamFurnaceRecipes 
{
	
	private static Map<ItemStack, Map<ItemStack, ItemStack>> recipes = new HashMap<ItemStack, Map<ItemStack, ItemStack>>();
	public static void addFurnaceRecipe(ItemStack melt, ItemStack mould, ItemStack result)
	{
		Map<ItemStack, ItemStack> temp = new HashMap<ItemStack, ItemStack>();
		temp.put(melt, mould);
		recipes.put(result, temp);
	}
	
	public static ItemStack getFurnaceResult(ItemStack ingr1, ItemStack ingr2)
	{
		ItemStack result = null;
		if(ingr1 == null || ingr2 == null)
		{
			return null;
		}
			for(Map.Entry<ItemStack, Map<ItemStack, ItemStack>> recipe : recipes.entrySet())
			{
				
				for(Entry<ItemStack, ItemStack> ingr: recipe.getValue().entrySet())
				{
					if(HelperLibrary.isOreDicItem(ingr.getKey(), ingr1) ||  HelperLibrary.areItemStacksSameItemAndDamage(ingr.getKey(), ingr1))
					{

						if(HelperLibrary.isOreDicItem(ingr.getValue(), ingr2) ||  HelperLibrary.areItemStacksSameItemAndDamage(ingr.getValue(), ingr2) && ingr1.stackSize >= ingr.getKey().stackSize&& ingr2.stackSize >= ingr.getValue().stackSize)
						{
							return recipe.getKey().copy();
						}
					}
				}
				
		}
		return result;
	}
	public static Map getRecipes()
	{
		return recipes;
	}
	
	public static void removeSmeltryRecipe(ItemStack result)
	{
		if(recipes.containsKey(result))
		recipes.remove(result);
	}
	
	public static void registerRecipies()
	{
		//addSmeltryRecipe("advancedutilities:ingot",1,2,false, "advancedutilities:cast",1,0,false, "advancedutilities:toolpart",1,0, false);
		addFurnaceRecipe(new ItemStack(AdvancedUtilitiesItems.ingot, 3, ItemIngot.COPPER), new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.TIN), new ItemStack(AdvancedUtilitiesItems.ingot, 3, ItemIngot.BRONZE));
		addFurnaceRecipe(new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.TIN), new ItemStack(AdvancedUtilitiesItems.ingot, 3, ItemIngot.COPPER), new ItemStack(AdvancedUtilitiesItems.ingot, 3, ItemIngot.BRONZE));
		addFurnaceRecipe(new ItemStack(AdvancedUtilitiesItems.ingot, 3, ItemIngot.COPPER), new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.ZINC), new ItemStack(AdvancedUtilitiesItems.ingot, 3, ItemIngot.BRASS));
		addFurnaceRecipe(new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.ZINC), new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.COPPER), new ItemStack(AdvancedUtilitiesItems.ingot, 3, ItemIngot.BRASS));
	}
	
	public static int getIngr1Size(ItemStack ingr1, ItemStack ingr2)
	{
		if(ingr1 == null || ingr2 == null)
		{
			return 0;
		}
			for(Map.Entry<ItemStack, Map<ItemStack, ItemStack>> recipe : recipes.entrySet())
			{
				
				for(Entry<ItemStack, ItemStack> ingr: recipe.getValue().entrySet())
				{
					if(HelperLibrary.isOreDicItem(ingr.getKey(), ingr1) ||  HelperLibrary.areItemStacksSameItemAndDamage(ingr.getKey(), ingr1))
					{

						if(HelperLibrary.isOreDicItem(ingr.getValue(), ingr2) ||  HelperLibrary.areItemStacksSameItemAndDamage(ingr.getValue(), ingr2) && ingr1.stackSize >= ingr.getKey().stackSize&& ingr2.stackSize >= ingr.getValue().stackSize)
						{
							return ingr.getKey().stackSize;
						}
					}
				}
				
		}
		return 0;
	}
	
	public static int getIngr2Size(ItemStack ingr1, ItemStack ingr2)
	{
		if(ingr1 == null || ingr2 == null)
		{
			return 0;
		}
			for(Map.Entry<ItemStack, Map<ItemStack, ItemStack>> recipe : recipes.entrySet())
			{
				
				for(Entry<ItemStack, ItemStack> ingr: recipe.getValue().entrySet())
				{
					if(HelperLibrary.isOreDicItem(ingr.getKey(), ingr1) ||  HelperLibrary.areItemStacksSameItemAndDamage(ingr.getKey(), ingr1))
					{

						if(HelperLibrary.isOreDicItem(ingr.getValue(), ingr2) ||  HelperLibrary.areItemStacksSameItemAndDamage(ingr.getValue(), ingr2) && ingr1.stackSize >= ingr.getKey().stackSize&& ingr2.stackSize >= ingr.getValue().stackSize)
						{
							return ingr.getValue().stackSize;
						}
					}
				}
				
		}
		return 0;
	}
	
	public static boolean isIngredient(ItemStack item)
	{
		boolean result = false;
		for(Map.Entry<ItemStack, Map<ItemStack, ItemStack>> recipe : recipes.entrySet())
		{
			
			for(Entry<ItemStack, ItemStack> ingr: recipe.getValue().entrySet())
			{
				if(HelperLibrary.isOreDicItem(item, ingr.getKey()) || HelperLibrary.areItemStacksSameItemAndDamage(item, ingr.getKey()))
				{
					result = true;
					return result;
				}
				if(HelperLibrary.isOreDicItem(item, ingr.getValue()) || HelperLibrary.areItemStacksSameItemAndDamage(item, ingr.getValue()))
				{
					result = true;
					return result;
				}
			}
		}
		return result;
	}
	public static boolean isIngredient1(ItemStack item)
	{
		boolean result = false;
		for(Map.Entry<ItemStack, Map<ItemStack, ItemStack>> recipe : recipes.entrySet())
		{
			
			for(Entry<ItemStack, ItemStack> ingr: recipe.getValue().entrySet())
			{
				if(HelperLibrary.isOreDicItem(item, ingr.getKey()) || HelperLibrary.areItemStacksSameItemAndDamage(item, ingr.getKey()))
				{
					result = true;
					return result;
				}
			}
		}
		return result;
	}
	public static boolean isIngredient2(ItemStack item)
	{
		boolean result = false;
		for(Map.Entry<ItemStack, Map<ItemStack, ItemStack>> recipe : recipes.entrySet())
		{
			
			for(Entry<ItemStack, ItemStack> ingr: recipe.getValue().entrySet())
			{
				if(HelperLibrary.isOreDicItem(item, ingr.getValue()) || HelperLibrary.areItemStacksSameItemAndDamage(item, ingr.getValue()))
				{
					result = true;
					return result;
				}
			}
		}
		return result;
	}
	
	public static int[] getIngredientSize(ItemStack ingr1, ItemStack ingr2)
	{
		int[] result = new int[2];
		if(ingr1 == null || ingr2 == null)
		{
			return result;
		}
			for(Map.Entry<ItemStack, Map<ItemStack, ItemStack>> recipe : recipes.entrySet())
			{
				
				for(Entry<ItemStack, ItemStack> ingr: recipe.getValue().entrySet())
				{
					if(HelperLibrary.isOreDicItem(ingr.getKey(), ingr1) ||  HelperLibrary.areItemStacksSameItemAndDamage(ingr.getKey(), ingr1))
					{

						if(HelperLibrary.isOreDicItem(ingr.getValue(), ingr2) ||  HelperLibrary.areItemStacksSameItemAndDamage(ingr.getValue(), ingr2) && ingr1.stackSize >= ingr.getKey().stackSize&& ingr2.stackSize >= ingr.getValue().stackSize)
						{
							result[0] = ingr.getKey().stackSize;
							result[1] = ingr.getValue().stackSize;
						}
					}
				}
				
		}
		return result;
	}
	
	public static void addSmeltryRecipe(String melt, int meltCount,int meltDam, boolean meltBlock, String mould, int mouldCount,int mouldDam, boolean mouldBlock, String result, int resultCount, int resultDam, boolean resultBlock)
	{
		Map<ItemStack, ItemStack> temp = new HashMap<ItemStack, ItemStack>();
		Object temp1;
		Object temp2;
		Object temp3;
		ItemStack pmet1;
		ItemStack pmet2;
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
		if(mouldBlock)
		{
			temp2=HelperLibrary.getBlock(mould);
			pmet2= new ItemStack((Block)temp2, mouldCount, mouldDam);
		}
		else
		{
			temp2 = HelperLibrary.getItem(mould);
			pmet2= new ItemStack((Item)temp2, mouldCount, mouldDam);
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
		
		if(temp1!=null && temp2!=null &&temp3 !=null &&pmet1!=null&&pmet2!=null&&pmet3!=null)
		{
			temp.put(pmet1, pmet2);
			recipes.put(pmet3,temp);
		}
	}
	
	public static void registerFurnaceRecipesString()
	{
		if(RecipeConfig.SteamFurnaceRecipes!=null && RecipeConfig.SteamFurnaceRecipes.length >0)
		{
			for(int i = 0; i<RecipeConfig.SteamFurnaceRecipes.length; i++)
			{
				String[] recipeArray = RecipeConfig.SteamFurnaceRecipes[i].split(",");
				/*recipeArray[0]; //melt item
				recipeArray[1]; // melt size
				recipeArray[2]; // melt dam
				recipeArray[3]; //melt block
				recipeArray[4]; //mould item
				recipeArray[5]; // mould size
				recipeArray[6]; // mould dam
				recipeArray[7]; // mould block
				recipeArray[8]; // result item
				recipeArray[9]; // result size
				recipeArray[10]; // resultdam
				recipeArray[11]; // result block*/
				try
				{
				String melt = recipeArray[0];
				int meltSize = Integer.parseInt(recipeArray[1]);
				int meltDam = Integer.parseInt(recipeArray[2]);
				Boolean meltBlock = Boolean.valueOf(recipeArray[3]);
				
				String mould = recipeArray[4];
				int mouldSize = Integer.parseInt(recipeArray[5]);
				int mouldDam = Integer.parseInt(recipeArray[6]);
				Boolean mouldBlock = Boolean.valueOf(recipeArray[7]);
				
				String result = recipeArray[8];
				int resultSize = Integer.parseInt(recipeArray[9]);
				int resultDam = Integer.parseInt(recipeArray[10]);
				Boolean resultBlock = Boolean.valueOf(recipeArray[11]);
				
				addSmeltryRecipe(melt, meltSize, meltDam, meltBlock, mould, mouldSize, mouldDam, mouldBlock, result, resultSize, resultDam, resultBlock);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
			}
		}
	}
	
	public static String[] getFurnaceString()
	{
		String[] result = new String[recipes.size()];
		int count = 0;
		for(Map.Entry<ItemStack, Map<ItemStack, ItemStack>> recipe : recipes.entrySet())
		{
			
			for(Entry<ItemStack, ItemStack> ingr: recipe.getValue().entrySet())
			{
				String meltName;
				String mouldName;
				String resultName;
				boolean isMeltBlock = false;
				boolean isMouldBlock = false;
				boolean isResultBlock = false;
				GameRegistry.UniqueIdentifier idMelt = GameRegistry.findUniqueIdentifierFor(ingr.getKey().getItem());
				meltName  = idMelt.toString();
				GameRegistry.UniqueIdentifier idMould = GameRegistry.findUniqueIdentifierFor(ingr.getValue().getItem());
				mouldName  = idMould.toString();
				GameRegistry.UniqueIdentifier idResult = GameRegistry.findUniqueIdentifierFor(recipe.getKey().getItem());
				resultName  = idResult.toString();
				if(ingr.getKey().getItem() instanceof ItemBlock)
				{
					isMeltBlock = true;
				}
				if(ingr.getValue().getItem() instanceof ItemBlock)
				{
					isMouldBlock = true;
				}
				if(recipe.getKey().getItem() instanceof ItemBlock)
				{
					isResultBlock = true;
				}
				
				String temp = meltName+","+ingr.getKey().stackSize+","+ingr.getKey().getItemDamage()+","+isMeltBlock+","+mouldName+","+ingr.getValue().stackSize+","+ingr.getValue().getItemDamage()+","+isMouldBlock+","+resultName+","+recipe.getKey().stackSize+","+recipe.getKey().getItemDamage()+","+isResultBlock;
				result[count] = temp;
				count++;
			}
		}
		
		return result;
	}
	
	
	
	public static final String[] defaultRecipes = new String[]
			{
					"advancedutilities:ingot,3,0,false,advancedutilities:ingot,1,1,false,advancedutilities:ingot,3,2,false",
			        "advancedutilities:ingot,3,0,false,advancedutilities:ingot,1,3,false,advancedutilities:ingot,3,4,false",
			        "advancedutilities:ingot,1,1,false,advancedutilities:ingot,3,0,false,advancedutilities:ingot,3,2,false",
			        "advancedutilities:ingot,1,3,false,advancedutilities:ingot,1,0,false,advancedutilities:ingot,3,4,false"
			};

}
	
	

