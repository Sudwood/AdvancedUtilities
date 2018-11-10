package com.sudwood.advancedutilities.recipes;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.sudwood.advancedutilities.HelperLibrary;
import com.sudwood.advancedutilities.config.RecipeConfig;
import com.sudwood.advancedutilities.items.AdvancedUtilitiesItems;
import com.sudwood.advancedutilities.items.ItemDust;
import com.sudwood.advancedutilities.items.ItemIngot;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class SteelOvenRecipes 
{
	private static Map<ItemStack, Map<ItemStack, Map<ItemStack, Map<ItemStack, Map<ItemStack, ItemStack>>>>> recipes = new HashMap();
	
	public static void loadRecipes()
	{
		addRecipe(new ItemStack(AdvancedUtilitiesItems.dust, 1, ItemDust.IRON), new ItemStack(Blocks.sand, 1), new ItemStack(AdvancedUtilitiesItems.dust, 1, ItemDust.NICKEL),
				new ItemStack(Items.redstone, 1), new ItemStack(AdvancedUtilitiesItems.dust, 1, ItemDust.COAL), new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.STEEL));
	}
	
	/**
	 * 
	 * @param ingr1 - Steel Recipe: Iron dust
	 * @param ingr2 - Steel Recipe: Sand
	 * @param ingr3	 - Steel Recipe: Nickel Dust
	 * @param ingr4	 - Steel Recipe: Redstone dust
	 * @param ingr5 - Steel Recipe: Coal
	 * @param result
	 */
	public static void addRecipe(ItemStack ingr1, ItemStack ingr2, ItemStack ingr3, ItemStack ingr4, ItemStack ingr5, ItemStack result)
	{
		Map<ItemStack, ItemStack> ingr45 = new HashMap();
		ingr45.put(ingr4, ingr5);
		Map<ItemStack, Map<ItemStack, ItemStack>> ingre345 = new HashMap();
		ingre345.put(ingr3, ingr45);
		Map<ItemStack, Map<ItemStack, Map<ItemStack, ItemStack>>> ingre2345 = new HashMap();
		ingre2345.put(ingr2, ingre345);
		Map<ItemStack, Map<ItemStack, Map<ItemStack,Map<ItemStack, ItemStack>>>> ingre12345 = new HashMap();
		ingre12345.put(ingr1, ingre2345);
		recipes.put(result, ingre12345);
	}
	
	public static Map getRecipes()
	{
		return recipes;
	}
	
	public static int[] getIngredientSize(ItemStack ingr1, ItemStack ingr2, ItemStack ingr3, ItemStack ingr4, ItemStack ingr5)
	{
		int[] result = new int[5];
		for(Entry<ItemStack, Map<ItemStack, Map<ItemStack, Map<ItemStack, Map<ItemStack, ItemStack>>>>> recipe: recipes.entrySet())
		{
			for(Entry<ItemStack, Map<ItemStack, Map<ItemStack, Map<ItemStack, ItemStack>>>> ingr12345: recipe.getValue().entrySet())
			{
				for(Entry<ItemStack, Map<ItemStack, Map<ItemStack, ItemStack>>> ingr2345: ingr12345.getValue().entrySet())
				{
					for(Entry<ItemStack, Map<ItemStack, ItemStack>> ingr345: ingr2345.getValue().entrySet())
					{
						for(Entry<ItemStack,ItemStack> ingr45: ingr345.getValue().entrySet())
						{
							if((HelperLibrary.isOreDicItem(ingr45.getValue(), ingr5) || HelperLibrary.areItemStacksSameItemAndDamage(ingr45.getValue(), ingr5)) &&
									(HelperLibrary.isOreDicItem(ingr45.getKey(), ingr4) || HelperLibrary.areItemStacksSameItemAndDamage(ingr45.getKey(), ingr4))
									&& (HelperLibrary.isOreDicItem(ingr345.getKey(), ingr3) || HelperLibrary.areItemStacksSameItemAndDamage(ingr345.getKey(), ingr3))
									&& (HelperLibrary.isOreDicItem(ingr2345.getKey(), ingr2) || HelperLibrary.areItemStacksSameItemAndDamage(ingr2345.getKey(), ingr2))
									&& (HelperLibrary.isOreDicItem(ingr12345.getKey(), ingr1) || HelperLibrary.areItemStacksSameItemAndDamage(ingr12345.getKey(), ingr1))) 
							{
								result[0] = ingr12345.getKey().stackSize;
								result[1] = ingr2345.getKey().stackSize;
								result[2] = ingr345.getKey().stackSize;
								result[3] = ingr45.getKey().stackSize;
								result[4] = ingr45.getValue().stackSize;
							}
						}
					}
				}
			}
		}
		return result;
	}
	
	public static ItemStack getSteelOvenResult(ItemStack ingr1, ItemStack ingr2, ItemStack ingr3, ItemStack ingr4, ItemStack ingr5)
	{
		ItemStack result = null;
			for(Entry<ItemStack, Map<ItemStack, Map<ItemStack, Map<ItemStack, Map<ItemStack, ItemStack>>>>> recipe: recipes.entrySet())
			{
				for(Entry<ItemStack, Map<ItemStack, Map<ItemStack, Map<ItemStack, ItemStack>>>> ingr12345: recipe.getValue().entrySet())
				{
					for(Entry<ItemStack, Map<ItemStack, Map<ItemStack, ItemStack>>> ingr2345: ingr12345.getValue().entrySet())
					{
						for(Entry<ItemStack, Map<ItemStack, ItemStack>> ingr345: ingr2345.getValue().entrySet())
						{
							for(Entry<ItemStack,ItemStack> ingr45: ingr345.getValue().entrySet())
							{

								if((HelperLibrary.isOreDicItem(ingr45.getValue(), ingr5) || HelperLibrary.areItemStacksSameItemAndDamage(ingr45.getValue(), ingr5)) &&
										(HelperLibrary.isOreDicItem(ingr45.getKey(), ingr4) || HelperLibrary.areItemStacksSameItemAndDamage(ingr45.getKey(), ingr4))
										&& (HelperLibrary.isOreDicItem(ingr345.getKey(), ingr3) || HelperLibrary.areItemStacksSameItemAndDamage(ingr345.getKey(), ingr3))
										&& (HelperLibrary.isOreDicItem(ingr2345.getKey(), ingr2) || HelperLibrary.areItemStacksSameItemAndDamage(ingr2345.getKey(), ingr2))
										&& (HelperLibrary.isOreDicItem(ingr12345.getKey(), ingr1) || HelperLibrary.areItemStacksSameItemAndDamage(ingr12345.getKey(), ingr1))) 
								{
									if(ingr5.stackSize >= ingr45.getValue().stackSize && ingr4.stackSize >= ingr45.getKey().stackSize 
											 && ingr3.stackSize >= ingr345.getKey().stackSize && ingr2.stackSize >= ingr2345.getKey().stackSize
											 && ingr1.stackSize >= ingr12345.getKey().stackSize)
									{
									return recipe.getKey().copy();
									}
								}
							}
						}
					}
				}
			}
		
		return result;
	}
	
	public static boolean isIngr0(ItemStack ingr1)
	{
			for(Entry<ItemStack, Map<ItemStack, Map<ItemStack, Map<ItemStack, Map<ItemStack, ItemStack>>>>> recipe: recipes.entrySet())
			{
				for(Entry<ItemStack, Map<ItemStack, Map<ItemStack, Map<ItemStack, ItemStack>>>> ingr12345: recipe.getValue().entrySet())
				{
					for(Entry<ItemStack, Map<ItemStack, Map<ItemStack, ItemStack>>> ingr2345: ingr12345.getValue().entrySet())
					{
						for(Entry<ItemStack, Map<ItemStack, ItemStack>> ingr345: ingr2345.getValue().entrySet())
						{
							for(Entry<ItemStack,ItemStack> ingr45: ingr345.getValue().entrySet())
							{

								if (HelperLibrary.isOreDicItem(ingr12345.getKey(), ingr1) || HelperLibrary.areItemStacksSameItemAndDamage(ingr12345.getKey(), ingr1)) 
								{
									return true;
								}
							}
						}
					}
				}
			}
		
		return false;
	}
	public static boolean isIngr1(ItemStack ingr1)
	{
			for(Entry<ItemStack, Map<ItemStack, Map<ItemStack, Map<ItemStack, Map<ItemStack, ItemStack>>>>> recipe: recipes.entrySet())
			{
				for(Entry<ItemStack, Map<ItemStack, Map<ItemStack, Map<ItemStack, ItemStack>>>> ingr12345: recipe.getValue().entrySet())
				{
					for(Entry<ItemStack, Map<ItemStack, Map<ItemStack, ItemStack>>> ingr2345: ingr12345.getValue().entrySet())
					{
						for(Entry<ItemStack, Map<ItemStack, ItemStack>> ingr345: ingr2345.getValue().entrySet())
						{
							for(Entry<ItemStack,ItemStack> ingr45: ingr345.getValue().entrySet())
							{

								if (HelperLibrary.isOreDicItem(ingr2345.getKey(), ingr1) || HelperLibrary.areItemStacksSameItemAndDamage(ingr2345.getKey(), ingr1)) 
								{
									return true;
								}
							}
						}
					}
				}
			}
		
		return false;
	}
	public static boolean isIngr2(ItemStack ingr1)
	{
			for(Entry<ItemStack, Map<ItemStack, Map<ItemStack, Map<ItemStack, Map<ItemStack, ItemStack>>>>> recipe: recipes.entrySet())
			{
				for(Entry<ItemStack, Map<ItemStack, Map<ItemStack, Map<ItemStack, ItemStack>>>> ingr12345: recipe.getValue().entrySet())
				{
					for(Entry<ItemStack, Map<ItemStack, Map<ItemStack, ItemStack>>> ingr2345: ingr12345.getValue().entrySet())
					{
						for(Entry<ItemStack, Map<ItemStack, ItemStack>> ingr345: ingr2345.getValue().entrySet())
						{
							for(Entry<ItemStack,ItemStack> ingr45: ingr345.getValue().entrySet())
							{

								if (HelperLibrary.isOreDicItem(ingr345.getKey(), ingr1) || HelperLibrary.areItemStacksSameItemAndDamage(ingr345.getKey(), ingr1)) 
								{
									return true;
								}
							}
						}
					}
				}
			}
		
		return false;
	}
	public static boolean isIngr3(ItemStack ingr1)
	{
			for(Entry<ItemStack, Map<ItemStack, Map<ItemStack, Map<ItemStack, Map<ItemStack, ItemStack>>>>> recipe: recipes.entrySet())
			{
				for(Entry<ItemStack, Map<ItemStack, Map<ItemStack, Map<ItemStack, ItemStack>>>> ingr12345: recipe.getValue().entrySet())
				{
					for(Entry<ItemStack, Map<ItemStack, Map<ItemStack, ItemStack>>> ingr2345: ingr12345.getValue().entrySet())
					{
						for(Entry<ItemStack, Map<ItemStack, ItemStack>> ingr345: ingr2345.getValue().entrySet())
						{
							for(Entry<ItemStack,ItemStack> ingr45: ingr345.getValue().entrySet())
							{

								if (HelperLibrary.isOreDicItem(ingr45.getKey(), ingr1) || HelperLibrary.areItemStacksSameItemAndDamage(ingr45.getKey(), ingr1)) 
								{
									return true;
								}
							}
						}
					}
				}
			}
		
		return false;
	}
	public static boolean isIngr4(ItemStack ingr1)
	{
			for(Entry<ItemStack, Map<ItemStack, Map<ItemStack, Map<ItemStack, Map<ItemStack, ItemStack>>>>> recipe: recipes.entrySet())
			{
				for(Entry<ItemStack, Map<ItemStack, Map<ItemStack, Map<ItemStack, ItemStack>>>> ingr12345: recipe.getValue().entrySet())
				{
					for(Entry<ItemStack, Map<ItemStack, Map<ItemStack, ItemStack>>> ingr2345: ingr12345.getValue().entrySet())
					{
						for(Entry<ItemStack, Map<ItemStack, ItemStack>> ingr345: ingr2345.getValue().entrySet())
						{
							for(Entry<ItemStack,ItemStack> ingr45: ingr345.getValue().entrySet())
							{

								if (HelperLibrary.isOreDicItem(ingr45.getValue(), ingr1) || HelperLibrary.areItemStacksSameItemAndDamage(ingr45.getValue(), ingr1)) 
								{
									return true;
								}
							}
						}
					}
				}
			}
		
		return false;
	}
	
	public static void addSteelOvenRecipe(Object[] input)
	{
		Map<ItemStack, ItemStack> ingr45 = new HashMap();
		Map<ItemStack, Map<ItemStack, ItemStack>> ingr345 = new HashMap();
		Map<ItemStack, Map<ItemStack, Map<ItemStack, ItemStack>>> ingr2345 = new HashMap();
		Map<ItemStack, Map<ItemStack, Map<ItemStack,Map<ItemStack, ItemStack>>>> ingr12345 = new HashMap();
		Object item0; //0-3
		Object item1; //4-7
		Object item2; //8-11
		Object item3; //12-15
		Object item4; //16-19
		Object itemR; //20-23
		ItemStack stack0;
		ItemStack stack1;
		ItemStack stack2;
		ItemStack stack3;
		ItemStack stack4;
		ItemStack stackR;
		if((Boolean) input[3])
		{
			item0=HelperLibrary.getBlock((String)input[0]);
			stack0= new ItemStack((Block)item0, (Integer)input[1], (Integer)input[2]);
		}
		else
		{
			item0=HelperLibrary.getItem((String)input[0]);
			stack0= new ItemStack((Item)item0, (Integer)input[1], (Integer)input[2]);
		}
		if((Boolean) input[7])
		{
			item1=HelperLibrary.getBlock((String)input[4]);
			stack1= new ItemStack((Block)item1, (Integer)input[5], (Integer)input[6]);
		}
		else
		{
			item1=HelperLibrary.getItem((String)input[4]);
			stack1= new ItemStack((Item)item1, (Integer)input[5], (Integer)input[6]);
		}
		if((Boolean) input[11])
		{
			item2=HelperLibrary.getBlock((String)input[8]);
			stack2= new ItemStack((Block)item2, (Integer)input[9], (Integer)input[10]);
		}
		else
		{
			item2=HelperLibrary.getItem((String)input[8]);
			stack2= new ItemStack((Item)item2, (Integer)input[9], (Integer)input[10]);
		}
		if((Boolean) input[15])
		{
			item3=HelperLibrary.getBlock((String)input[12]);
			stack3= new ItemStack((Block)item3, (Integer)input[13], (Integer)input[14]);
		}
		else
		{
			item3=HelperLibrary.getItem((String)input[12]);
			stack3= new ItemStack((Item)item3, (Integer)input[13], (Integer)input[14]);
		}
		if((Boolean) input[19])
		{
			item4=HelperLibrary.getBlock((String)input[16]);
			stack4= new ItemStack((Block)item4, (Integer)input[17], (Integer)input[18]);
		}
		else
		{
			item4=HelperLibrary.getItem((String)input[16]);
			stack4= new ItemStack((Item)item4, (Integer)input[17], (Integer)input[18]);
		}
		if((Boolean) input[23])
		{
			itemR=HelperLibrary.getBlock((String)input[20]);
			stackR= new ItemStack((Block)itemR, (Integer)input[21], (Integer)input[22]);
		}
		else
		{
			itemR=HelperLibrary.getItem((String)input[20]);
			stackR= new ItemStack((Item)itemR, (Integer)input[21], (Integer)input[22]);
		}
		
		if(item0!=null&&item1!=null&&item2!=null&&item3!=null&&item4!=null&&itemR!=null &&stack0!=null&&stack1!=null&&stack2!=null&&stack3!=null&&stack4!=null&&stackR!=null)
		{
			ingr45.put(stack3, stack4);
			ingr345.put(stack2, ingr45);
			ingr2345.put(stack1, ingr345);
			ingr12345.put(stack0, ingr2345);
			recipes.put(stackR, ingr12345);
		}
	}
	
	public static void registerFurnaceRecipesString()
	{
		if(RecipeConfig.SteelOvenRecipes!=null && RecipeConfig.SteelOvenRecipes.length >0)
		{
			for(int i = 0; i<RecipeConfig.SteelOvenRecipes.length; i++)
			{
				String[] recipeArray = RecipeConfig.SteelOvenRecipes[i].split(",");
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
				Object[] recipe = new Object[recipeArray.length];
				recipe[0] = recipeArray[0];
				recipe[1] = Integer.parseInt(recipeArray[1]);
				recipe[2] = Integer.parseInt(recipeArray[2]);
				recipe[3] = Boolean.valueOf(recipeArray[3]);
				
				recipe[4] = recipeArray[4];
				recipe[5] = Integer.parseInt(recipeArray[5]);
				recipe[6] = Integer.parseInt(recipeArray[6]);
				recipe[7] = Boolean.valueOf(recipeArray[7]);
				
				recipe[8] = recipeArray[8];
				recipe[9] = Integer.parseInt(recipeArray[9]);
				recipe[10] = Integer.parseInt(recipeArray[10]);
				recipe[11] = Boolean.valueOf(recipeArray[11]);
				
				recipe[12] = recipeArray[12];
				recipe[13] = Integer.parseInt(recipeArray[13]);
				recipe[14] = Integer.parseInt(recipeArray[14]);
				recipe[15] = Boolean.valueOf(recipeArray[15]);
				
				recipe[16] = recipeArray[16];
				recipe[17] = Integer.parseInt(recipeArray[17]);
				recipe[18] = Integer.parseInt(recipeArray[18]);
				recipe[19] = Boolean.valueOf(recipeArray[19]);
				
				recipe[20] = recipeArray[20];
				recipe[21] = Integer.parseInt(recipeArray[21]);
				recipe[22] = Integer.parseInt(recipeArray[22]);
				recipe[23] = Boolean.valueOf(recipeArray[23]);
				
				
				addSteelOvenRecipe(recipe);
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
		for(Entry<ItemStack, Map<ItemStack, Map<ItemStack, Map<ItemStack, Map<ItemStack, ItemStack>>>>> recipe: recipes.entrySet())
		{
			for(Entry<ItemStack, Map<ItemStack, Map<ItemStack, Map<ItemStack, ItemStack>>>> ingr01234: recipe.getValue().entrySet())
			{
				for(Entry<ItemStack, Map<ItemStack, Map<ItemStack, ItemStack>>> ingr1234: ingr01234.getValue().entrySet())
				{
					for(Entry<ItemStack, Map<ItemStack, ItemStack>> ingr234: ingr1234.getValue().entrySet())
					{
						for(Entry<ItemStack,ItemStack> ingr34: ingr234.getValue().entrySet())
						{
							String meltName;
							String mouldName;
							String resultName;
							String ingr0Name;
							String ingr1Name;
							String ingr2Name;
							String ingr3Name;
							String ingr4Name;
							boolean is0Block = false;
							boolean is1Block = false;
							boolean is2Block = false;
							boolean is3Block = false;
							boolean is4Block = false;
							boolean isResultBlock = false;
							GameRegistry.UniqueIdentifier id0 = GameRegistry.findUniqueIdentifierFor(ingr01234.getKey().getItem());
							GameRegistry.UniqueIdentifier id1 = GameRegistry.findUniqueIdentifierFor(ingr1234.getKey().getItem());
							GameRegistry.UniqueIdentifier id2 = GameRegistry.findUniqueIdentifierFor(ingr234.getKey().getItem());
							GameRegistry.UniqueIdentifier id3 = GameRegistry.findUniqueIdentifierFor(ingr34.getKey().getItem());
							GameRegistry.UniqueIdentifier id4 = GameRegistry.findUniqueIdentifierFor(ingr34.getValue().getItem());
							ingr0Name = id0.toString();
							ingr1Name = id1.toString();
							ingr2Name = id2.toString();
							ingr3Name = id3.toString();
							ingr4Name = id4.toString();
							StringBuilder stringBuilder = new StringBuilder();
							GameRegistry.UniqueIdentifier idResult = GameRegistry.findUniqueIdentifierFor(recipe.getKey().getItem());
							resultName  = idResult.toString();
							if(ingr01234.getKey().getItem() instanceof ItemBlock)
							{
								is0Block = true;
							}
							if(ingr1234.getKey().getItem() instanceof ItemBlock)
							{
								is1Block = true;
							}
							if(ingr234.getKey().getItem() instanceof ItemBlock)
							{
								is2Block = true;
							}
							if(ingr34.getKey().getItem() instanceof ItemBlock)
							{
								is3Block = true;
							}
							if(ingr34.getValue().getItem() instanceof ItemBlock)
							{
								is4Block = true;
							}
							if(recipe.getKey().getItem() instanceof ItemBlock)
							{
								isResultBlock = true;
							}
							stringBuilder.append(ingr0Name+","+ingr01234.getKey().stackSize+","+ingr01234.getKey().getItemDamage()+","+is0Block);
							stringBuilder.append(","+ingr1Name+","+ingr1234.getKey().stackSize+","+ingr1234.getKey().getItemDamage()+","+is1Block);
							stringBuilder.append(","+ingr2Name+","+ingr234.getKey().stackSize+","+ingr234.getKey().getItemDamage()+","+is2Block);
							stringBuilder.append(","+ingr3Name+","+ingr34.getKey().stackSize+","+ingr34.getKey().getItemDamage()+","+is3Block);
							stringBuilder.append(","+ingr4Name+","+ingr34.getValue().stackSize+","+ingr34.getValue().getItemDamage()+","+is4Block);
							stringBuilder.append(","+resultName+","+recipe.getKey().stackSize+","+recipe.getKey().getItemDamage()+","+isResultBlock);
							result[count] = stringBuilder.toString();
							count++;
						}
					}
				}
			}
		}
		
		return result;
	}
	
	public static final String[] defaultRecipes = new String[]
			{
					"advancedutilities:dust,1,1,false,minecraft:sand,1,0,true,advancedutilities:dust,1,20,false,minecraft:redstone,1,0,false,advancedutilities:dust,1,8,false,advancedutilities:ingot,1,12,false"	
			};
}
