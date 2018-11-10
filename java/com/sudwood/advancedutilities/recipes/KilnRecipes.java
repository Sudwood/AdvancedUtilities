package com.sudwood.advancedutilities.recipes;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.sudwood.advancedutilities.HelperLibrary;
import com.sudwood.advancedutilities.config.RecipeConfig;
import com.sudwood.advancedutilities.items.AdvancedUtilitiesItems;
import com.sudwood.advancedutilities.items.ItemIngot;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class KilnRecipes 
{
	private static Map<ItemStack, Map<ItemStack, Map<ItemStack, Map<ItemStack, ItemStack>>>> recipes = new HashMap();
	
	public static void loadRecipes()
	{
		addRecipe(new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.TIN), new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.COPPER), new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.COPPER),
				new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.COPPER), new ItemStack(AdvancedUtilitiesItems.ingot, 2, ItemIngot.BRONZE));
		addRecipe(new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.ZINC), new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.COPPER), new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.COPPER),
				new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.COPPER), new ItemStack(AdvancedUtilitiesItems.ingot, 2, ItemIngot.BRASS));
	}
	
	public static void addRecipe(ItemStack ingr1, ItemStack ingr2, ItemStack ingr3, ItemStack ingr4, ItemStack result)
	{
		Map<ItemStack, ItemStack> ingre34 = new HashMap();
		ingre34.put(ingr3, ingr4);
		Map<ItemStack, Map<ItemStack, ItemStack>> ingre234 = new HashMap();
		ingre234.put(ingr2, ingre34);
		Map<ItemStack, Map<ItemStack, Map<ItemStack,ItemStack>>> ingre1234 = new HashMap();
		ingre1234.put(ingr1, ingre234);
		recipes.put(result, ingre1234);
	}
	
	public static Map getRecipes()
	{
		return recipes;
	}
	public static int[] getIngredientSize(ItemStack ingr1, ItemStack ingr2, ItemStack ingr3, ItemStack ingr4)
	{
		int[] result = new int[2];
		if((HelperLibrary.isOreDicItem(ingr2, ingr3) && HelperLibrary.isOreDicItem(ingr3, ingr4)) ||(HelperLibrary.areItemStacksSameItemAndDamage(ingr2, ingr3) && HelperLibrary.areItemStacksSameItemAndDamage(ingr3, ingr4)))
		{
			for(Entry<ItemStack, Map<ItemStack, Map<ItemStack, Map<ItemStack, ItemStack>>>> recipe: recipes.entrySet())
			{
				for(Entry<ItemStack, Map<ItemStack, Map<ItemStack, ItemStack>>> ingr1234: recipe.getValue().entrySet())
				{
					for(Entry<ItemStack, Map<ItemStack, ItemStack>> ingr234: ingr1234.getValue().entrySet())
					{
						for(Entry<ItemStack, ItemStack> ingr34: ingr234.getValue().entrySet())
						{
							if((HelperLibrary.isOreDicItem(ingr4, ingr34.getValue()) && HelperLibrary.isOreDicItem(ingr3, ingr34.getKey()) && HelperLibrary.isOreDicItem(ingr2, ingr234.getKey()))
									||(HelperLibrary.areItemStacksSameItemAndDamage(ingr4, ingr34.getValue()) && HelperLibrary.areItemStacksSameItemAndDamage(ingr3, ingr34.getKey()) && HelperLibrary.areItemStacksSameItemAndDamage(ingr2, ingr234.getKey())))
							{
								if(HelperLibrary.isOreDicItem(ingr1, ingr1234.getKey()) || HelperLibrary.areItemStacksSameItemAndDamage(ingr1, ingr1234.getKey()))
								{
									if(ingr1.stackSize >= ingr1234.getKey().stackSize && ingr2.stackSize>= ingr234.getKey().stackSize
											&& ingr3.stackSize>=ingr34.getKey().stackSize && ingr4.stackSize>=ingr34.getValue().stackSize)
									{
										result[0] = ingr1234.getKey().stackSize;
										result[1] = ingr34.getValue().stackSize;
									}
								}
							}
						}
					}
				}
			}
		}
		return result;
	}
	public static ItemStack getKilnResult(ItemStack ingr1, ItemStack ingr2, ItemStack ingr3, ItemStack ingr4)
	{
		ItemStack result = null;
		if((HelperLibrary.isOreDicItem(ingr2, ingr3) && HelperLibrary.isOreDicItem(ingr3, ingr4)) ||(HelperLibrary.areItemStacksSameItemAndDamage(ingr2, ingr3) && HelperLibrary.areItemStacksSameItemAndDamage(ingr3, ingr4)))
		{
			for(Entry<ItemStack, Map<ItemStack, Map<ItemStack, Map<ItemStack, ItemStack>>>> recipe: recipes.entrySet())
			{
				for(Entry<ItemStack, Map<ItemStack, Map<ItemStack, ItemStack>>> ingr1234: recipe.getValue().entrySet())
				{
					for(Entry<ItemStack, Map<ItemStack, ItemStack>> ingr234: ingr1234.getValue().entrySet())
					{
						for(Entry<ItemStack, ItemStack> ingr34: ingr234.getValue().entrySet())
						{
							if((HelperLibrary.isOreDicItem(ingr4, ingr34.getValue()) && HelperLibrary.isOreDicItem(ingr3, ingr34.getKey()) && HelperLibrary.isOreDicItem(ingr2, ingr234.getKey()))
									||(HelperLibrary.areItemStacksSameItemAndDamage(ingr4, ingr34.getValue()) && HelperLibrary.areItemStacksSameItemAndDamage(ingr3, ingr34.getKey()) && HelperLibrary.areItemStacksSameItemAndDamage(ingr2, ingr234.getKey())))
							{
								if(HelperLibrary.isOreDicItem(ingr1, ingr1234.getKey()) || HelperLibrary.areItemStacksSameItemAndDamage(ingr1, ingr1234.getKey()))
								{
									if(ingr1.stackSize >= ingr1234.getKey().stackSize && ingr2.stackSize>= ingr234.getKey().stackSize
											&& ingr3.stackSize>=ingr34.getKey().stackSize && ingr4.stackSize>=ingr34.getValue().stackSize)
									{
										result = recipe.getKey().copy();
									}
								}
							}
						}
					}
				}
			}
		}
		return result;
	}
	
	public static boolean getKilnIngr1(ItemStack ingr1)
	{
		for(Entry<ItemStack, Map<ItemStack, Map<ItemStack, Map<ItemStack, ItemStack>>>> recipe: recipes.entrySet())
			{
				for(Entry<ItemStack, Map<ItemStack, Map<ItemStack, ItemStack>>> ingr1234: recipe.getValue().entrySet())
				{
					if(HelperLibrary.areItemStacksSameItemAndDamage(ingr1, ingr1234.getKey()) || HelperLibrary.isOreDicItem(ingr1, ingr1234.getKey()))
					{
						return true;
					}
				}
			}
		
		return false;
	}
	public static boolean getKilnIngr2(ItemStack ingr1)
	{
		for(Entry<ItemStack, Map<ItemStack, Map<ItemStack, Map<ItemStack, ItemStack>>>> recipe: recipes.entrySet())
			{
				for(Entry<ItemStack, Map<ItemStack, Map<ItemStack, ItemStack>>> ingr1234: recipe.getValue().entrySet())
				{
					for(Entry<ItemStack, Map<ItemStack, ItemStack>> ingr234: ingr1234.getValue().entrySet())
					{
						if(HelperLibrary.areItemStacksSameItemAndDamage(ingr1, ingr234.getKey()) || HelperLibrary.isOreDicItem(ingr1, ingr234.getKey()))
						{
							return true;
						}
					}
					
				}
			}
		
		return false;
	}
	public static boolean getKilnIngr3(ItemStack ingr1)
	{
		for(Entry<ItemStack, Map<ItemStack, Map<ItemStack, Map<ItemStack, ItemStack>>>> recipe: recipes.entrySet())
			{
				for(Entry<ItemStack, Map<ItemStack, Map<ItemStack, ItemStack>>> ingr1234: recipe.getValue().entrySet())
				{
					for(Entry<ItemStack, Map<ItemStack, ItemStack>> ingr234: ingr1234.getValue().entrySet())
					{
						for(Entry<ItemStack, ItemStack> ingr34: ingr234.getValue().entrySet())
						{
							if(HelperLibrary.areItemStacksSameItemAndDamage(ingr1, ingr34.getKey()) || HelperLibrary.isOreDicItem(ingr1, ingr34.getKey()))
							{
								return true;
							}
						}
					}
					
				}
			}
		
		return false;
	}
	public static boolean getKilnIngr4(ItemStack ingr1)
	{
		for(Entry<ItemStack, Map<ItemStack, Map<ItemStack, Map<ItemStack, ItemStack>>>> recipe: recipes.entrySet())
			{
				for(Entry<ItemStack, Map<ItemStack, Map<ItemStack, ItemStack>>> ingr1234: recipe.getValue().entrySet())
				{
					for(Entry<ItemStack, Map<ItemStack, ItemStack>> ingr234: ingr1234.getValue().entrySet())
					{
						for(Entry<ItemStack, ItemStack> ingr34: ingr234.getValue().entrySet())
						{
							if(HelperLibrary.areItemStacksSameItemAndDamage(ingr1, ingr34.getValue()) || HelperLibrary.isOreDicItem(ingr1, ingr34.getValue()))
							{
								return true;
							}
						}
					}
				}
			}
		
		return false;
	}
	
	
	public static void addKilnRecipe(String single, int singleCount,int singleDam, boolean singleBlock, String tripple, int trippleCount,int trippleDam, boolean trippleBlock, String result, int resultCount, int resultDam, boolean resultBlock)
	{
		Map<ItemStack, Map<ItemStack, Map<ItemStack, ItemStack>>> map = new HashMap<ItemStack, Map<ItemStack, Map<ItemStack, ItemStack>>>();
		Map<ItemStack, Map<ItemStack, ItemStack>> map2 = new HashMap<ItemStack, Map<ItemStack, ItemStack>>();
		Map<ItemStack, ItemStack> map3 = new HashMap<ItemStack,ItemStack>();
		Object temp1;
		Object temp2;
		Object temp3;
		ItemStack pmet1;
		ItemStack pmet2;
		ItemStack pmet3;
		if(singleBlock)
		{
			temp1=HelperLibrary.getBlock(single);
			pmet1= new ItemStack((Block)temp1, singleCount, singleDam);
		}
		else
		{
			temp1 = HelperLibrary.getItem(single);
			pmet1= new ItemStack((Item)temp1, singleCount, singleDam);
		}
		if(trippleBlock)
		{
			temp2=HelperLibrary.getBlock(tripple);
			pmet2= new ItemStack((Block)temp2, trippleCount, trippleDam);
		}
		else
		{
			temp2 = HelperLibrary.getItem(tripple);
			pmet2= new ItemStack((Item)temp2, trippleCount, trippleDam);
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
			map3.put(pmet2.copy(), pmet2.copy());
			map2.put(pmet2.copy(), map3);
			map.put(pmet1.copy(), map2);
			recipes.put(pmet3, map);
		}
	}
	
	public static void registerKilnRecipesString()
	{
		if(RecipeConfig.KilnRecipes!=null && RecipeConfig.KilnRecipes.length >0)
		{
			for(int i = 0; i<RecipeConfig.KilnRecipes.length; i++)
			{
				String[] recipeArray = RecipeConfig.KilnRecipes[i].split(",");
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
				String single = recipeArray[0];
				int singleSize = Integer.parseInt(recipeArray[1]);
				int singleDam = Integer.parseInt(recipeArray[2]);
				Boolean singleBlock = Boolean.valueOf(recipeArray[3]);
				
				String tripple = recipeArray[4];
				int trippleSize = Integer.parseInt(recipeArray[5]);
				int trippleDam = Integer.parseInt(recipeArray[6]);
				Boolean trippleBlock = Boolean.valueOf(recipeArray[7]);
				
				String result = recipeArray[8];
				int resultSize = Integer.parseInt(recipeArray[9]);
				int resultDam = Integer.parseInt(recipeArray[10]);
				Boolean resultBlock = Boolean.valueOf(recipeArray[11]);
				
				addKilnRecipe(single, singleSize,singleDam, singleBlock, tripple, trippleSize,trippleDam, trippleBlock, result, resultSize, resultDam, resultBlock);
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
		for(Entry<ItemStack, Map<ItemStack, Map<ItemStack, Map<ItemStack, ItemStack>>>> recipe: recipes.entrySet())
		{
			for(Entry<ItemStack, Map<ItemStack, Map<ItemStack, ItemStack>>> ingr: recipe.getValue().entrySet())
			{
				for(Entry<ItemStack, Map<ItemStack, ItemStack>> ingr2: ingr.getValue().entrySet())
				{
					String singleName;
					String trippleName;
					String resultName;
					boolean isSingleBlock = false;
					boolean isTrippleBlock = false;
					boolean isResultBlock = false;
					GameRegistry.UniqueIdentifier idMelt = GameRegistry.findUniqueIdentifierFor(ingr.getKey().getItem());
					singleName  = idMelt.toString();
					GameRegistry.UniqueIdentifier idMould = GameRegistry.findUniqueIdentifierFor(ingr2.getKey().getItem());
					trippleName  = idMould.toString();
					GameRegistry.UniqueIdentifier idResult = GameRegistry.findUniqueIdentifierFor(recipe.getKey().getItem());
					resultName  = idResult.toString();
					if(ingr.getKey().getItem() instanceof ItemBlock)
					{
						isSingleBlock = true;
					}
					if(ingr2.getKey().getItem() instanceof ItemBlock)
					{
						isTrippleBlock = true;
					}
					if(recipe.getKey().getItem() instanceof ItemBlock)
					{
						isResultBlock = true;
					}
					
					String temp = singleName+","+ingr.getKey().stackSize+","+ingr.getKey().getItemDamage()+","+isSingleBlock+","+trippleName+","+ingr2.getKey().stackSize+","+ingr2.getKey().getItemDamage()+","+isTrippleBlock+","+resultName+","+recipe.getKey().stackSize+","+recipe.getKey().getItemDamage()+","+isResultBlock;
					result[count] = temp;
					count++;
				}
			}
		}
		
		return result;
	}
	
	public static final String[] defaultRecipes = new String[] 
	{
			"advancedutilities:ingot,1,1,false,advancedutilities:ingot,1,0,false,advancedutilities:ingot,2,2,false",
	        "advancedutilities:ingot,1,3,false,advancedutilities:ingot,1,0,false,advancedutilities:ingot,2,4,false"
	};
}
