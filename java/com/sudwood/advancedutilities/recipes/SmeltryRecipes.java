package com.sudwood.advancedutilities.recipes;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.sudwood.advancedutilities.HelperLibrary;
import com.sudwood.advancedutilities.blocks.AdvancedUtilitiesBlocks;
import com.sudwood.advancedutilities.blocks.BlockIngotBlock;
import com.sudwood.advancedutilities.config.RecipeConfig;
import com.sudwood.advancedutilities.items.AdvancedUtilitiesItems;
import com.sudwood.advancedutilities.items.ItemIngot;

import cpw.mods.fml.common.registry.GameData;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class SmeltryRecipes 
{
	
	private static Map<ItemStack, Map<ItemStack, ItemStack>> recipes = new HashMap<ItemStack, Map<ItemStack, ItemStack>>();
	public static void addSmeltryRecipe(ItemStack melt, ItemStack mould, ItemStack result)
	{
		Map<ItemStack, ItemStack> temp = new HashMap<ItemStack, ItemStack>();
		temp.put(melt, mould);
		recipes.put(result, temp);
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
	
	public static void registerSmeltryRecipesString()
	{
		if(RecipeConfig.SmeltryRecipes!=null && RecipeConfig.SmeltryRecipes.length >0)
		{
			for(int i = 0; i<RecipeConfig.SmeltryRecipes.length; i++)
			{
				String[] recipeArray = RecipeConfig.SmeltryRecipes[i].split(",");
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
	
	public static String[] getSmeltryString()
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
	
	public static ItemStack getSmeltryResult(ItemStack melt, ItemStack mould, int level, boolean tres)
	{
		ItemStack result = null;
		if(melt == null || mould == null)
		{
			return null;
		}
			for(Map.Entry<ItemStack, Map<ItemStack, ItemStack>> recipe : recipes.entrySet())
			{
				
				for(Entry<ItemStack, ItemStack> ingr: recipe.getValue().entrySet())
				{
					if(HelperLibrary.isOreDicItem(ingr.getKey(), melt) ||  HelperLibrary.areItemStacksSameItemAndDamage(ingr.getKey(), melt))
					{
						if((HelperLibrary.isOreDicItem(melt, new ItemStack(AdvancedUtilitiesBlocks.ingotBlock, 1, BlockIngotBlock.STEEL))  
								|| HelperLibrary.isOreDicItem(melt, new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.STEEL)) 
								|| HelperLibrary.isOreDicItem(melt, new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.TUNGSTEN))
								|| HelperLibrary.areItemStacksSameItemAndDamage(melt, new ItemStack(AdvancedUtilitiesBlocks.ingotBlock, 1, BlockIngotBlock.STEEL))  
								|| HelperLibrary.areItemStacksSameItemAndDamage(melt, new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.STEEL)) 
								|| HelperLibrary.areItemStacksSameItemAndDamage(melt, new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.TUNGSTEN) ))&& level <1)
						{
							return null;
						}
						if(HelperLibrary.isOreDicItem(ingr.getValue(), mould) ||  HelperLibrary.areItemStacksSameItemAndDamage(ingr.getValue(), mould) && melt.stackSize >= ingr.getKey().stackSize)
						{
							return recipe.getKey().copy();
						}
					}
				}
				
		}
		return result;
	}
	
	public static boolean isMelt(ItemStack melt)
	{
		if(melt == null)
		{
			return false;
		}
		for(Map.Entry<ItemStack, Map<ItemStack, ItemStack>> recipe : recipes.entrySet())
		{
			
			for(Entry<ItemStack, ItemStack> ingr: recipe.getValue().entrySet())
			{
				if(HelperLibrary.isOreDicItem(ingr.getKey(), melt) ||  HelperLibrary.areItemStacksSameItemAndDamage(ingr.getKey(), melt))
				{
					
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean isMould(ItemStack mould)
	{
		if(mould == null)
		{
			return false;
		}
		for(Map.Entry<ItemStack, Map<ItemStack, ItemStack>> recipe : recipes.entrySet())
		{
			
			for(Entry<ItemStack, ItemStack> ingr: recipe.getValue().entrySet())
			{
				if(HelperLibrary.isOreDicItem(ingr.getValue(), mould) ||  HelperLibrary.areItemStacksSameItemAndDamage(ingr.getValue(), mould))
				{
					
					return true;
				}
			}
		}
		return false;
	}
	public static int[] getIngredientSize(ItemStack melt, ItemStack mould)
	{
		int[] result = new int[2];
		if(melt == null || mould == null)
		{
			return result;
		}
			for(Map.Entry<ItemStack, Map<ItemStack, ItemStack>> recipe : recipes.entrySet())
			{
				
				for(Entry<ItemStack, ItemStack> ingr: recipe.getValue().entrySet())
				{
					if(HelperLibrary.isOreDicItem(ingr.getKey(), melt) ||  HelperLibrary.areItemStacksSameItemAndDamage(ingr.getKey(), melt))
					{
						if(HelperLibrary.isOreDicItem(ingr.getValue(), mould) ||  HelperLibrary.areItemStacksSameItemAndDamage(ingr.getValue(), mould) && melt.stackSize >= ingr.getKey().stackSize)
						{
							result[0] = ingr.getKey().stackSize;
							result[1] = ingr.getValue().stackSize;
							return result;
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
		addSmeltryRecipe(new ItemStack(Blocks.clay, 1), new ItemStack(Items.stick, 1), new ItemStack(AdvancedUtilitiesItems.cast, 1, 0));
		addSmeltryRecipe(new ItemStack(Blocks.clay, 1), new ItemStack(Items.stone_pickaxe, 1), new ItemStack(AdvancedUtilitiesItems.cast, 1, 1));
		addSmeltryRecipe(new ItemStack(Blocks.clay, 1), new ItemStack(Items.stone_sword, 1), new ItemStack(AdvancedUtilitiesItems.cast, 1, 2));
		addSmeltryRecipe(new ItemStack(Blocks.clay, 1), new ItemStack(Items.stone_shovel, 1), new ItemStack(AdvancedUtilitiesItems.cast, 1, 3));
		addSmeltryRecipe(new ItemStack(Blocks.clay, 1), new ItemStack(Items.stone_axe, 1), new ItemStack(AdvancedUtilitiesItems.cast, 1, 4));
		addSmeltryRecipe(new ItemStack(Blocks.clay, 1), new ItemStack(AdvancedUtilitiesItems.plate, 1, 3), new ItemStack(AdvancedUtilitiesItems.cast, 1, 5));
		addSmeltryRecipe(new ItemStack(Blocks.clay, 1), new ItemStack(AdvancedUtilitiesItems.stoneRivets, 1), new ItemStack(AdvancedUtilitiesItems.cast, 1, 6));
		addSmeltryRecipe(new ItemStack(Blocks.clay, 1), new ItemStack(AdvancedUtilitiesItems.itemBulletHead, 1, 0), new ItemStack(AdvancedUtilitiesItems.cast, 1, 7));
		addSmeltryRecipe(new ItemStack(Blocks.clay, 1), new ItemStack(AdvancedUtilitiesItems.itemCasing, 1, 0), new ItemStack(AdvancedUtilitiesItems.cast, 1, 8));
		addSmeltryRecipe(new ItemStack(Blocks.clay, 1), new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 10), new ItemStack(AdvancedUtilitiesItems.cast, 1, 9));
		addSmeltryRecipe(new ItemStack(Blocks.clay, 1), new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 13), new ItemStack(AdvancedUtilitiesItems.cast, 1, 10));
		
		addSmeltryRecipe(new ItemStack(Items.iron_ingot, 1), new ItemStack(AdvancedUtilitiesItems.cast, 1, 0), new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 1));
		addSmeltryRecipe(new ItemStack(Items.iron_ingot, 1), new ItemStack(AdvancedUtilitiesItems.cast, 1, 1), new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 2));
		addSmeltryRecipe(new ItemStack(Items.iron_ingot, 1), new ItemStack(AdvancedUtilitiesItems.cast, 1, 2), new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 3));
		addSmeltryRecipe(new ItemStack(Items.iron_ingot, 1), new ItemStack(AdvancedUtilitiesItems.cast, 1, 3), new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 4));
		addSmeltryRecipe(new ItemStack(Items.iron_ingot, 1), new ItemStack(AdvancedUtilitiesItems.cast, 1, 4), new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 5));
		addSmeltryRecipe(new ItemStack(Items.iron_ingot, 1), new ItemStack(AdvancedUtilitiesItems.cast, 1, 5), new ItemStack(AdvancedUtilitiesItems.plate, 1, 0));
		addSmeltryRecipe(new ItemStack(Items.iron_ingot, 8), new ItemStack(AdvancedUtilitiesItems.cast, 1, 9), new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 12));
		addSmeltryRecipe(new ItemStack(Items.iron_ingot, 8), new ItemStack(AdvancedUtilitiesItems.cast, 1, 10), new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 15));
		
		addSmeltryRecipe(new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.BRONZE), new ItemStack(AdvancedUtilitiesItems.cast, 1, 0), new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 0));
		addSmeltryRecipe(new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.BRONZE), new ItemStack(AdvancedUtilitiesItems.cast, 1, 1), new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 6));
		addSmeltryRecipe(new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.BRONZE), new ItemStack(AdvancedUtilitiesItems.cast, 1, 2), new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 7));
		addSmeltryRecipe(new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.BRONZE), new ItemStack(AdvancedUtilitiesItems.cast, 1, 3), new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 8));
		addSmeltryRecipe(new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.BRONZE), new ItemStack(AdvancedUtilitiesItems.cast, 1, 4), new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 9));
		addSmeltryRecipe(new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.BRONZE), new ItemStack(AdvancedUtilitiesItems.cast, 1, 5), new ItemStack(AdvancedUtilitiesItems.plate, 1, 1));
		addSmeltryRecipe(new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.BRONZE), new ItemStack(AdvancedUtilitiesItems.cast, 1, 8), new ItemStack(AdvancedUtilitiesItems.itemCasing, 16, 1));
		addSmeltryRecipe(new ItemStack(AdvancedUtilitiesItems.ingot, 8, ItemIngot.BRONZE), new ItemStack(AdvancedUtilitiesItems.cast, 1, 9), new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 11));
		addSmeltryRecipe(new ItemStack(AdvancedUtilitiesItems.ingot, 8, ItemIngot.BRONZE), new ItemStack(AdvancedUtilitiesItems.cast, 1, 10), new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 14));
		
		addSmeltryRecipe(new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.BRASS), new ItemStack(AdvancedUtilitiesItems.cast, 1, 5), new ItemStack(AdvancedUtilitiesItems.plate, 1, 2));
		addSmeltryRecipe(new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.BRASS), new ItemStack(AdvancedUtilitiesItems.cast, 1, 6), new ItemStack(AdvancedUtilitiesItems.brassRivets, 1));
		
		addSmeltryRecipe(new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.LEAD), new ItemStack(AdvancedUtilitiesItems.cast, 1, 7), new ItemStack(AdvancedUtilitiesItems.itemBulletHead, 16,1));
		
		addSmeltryRecipe(new ItemStack(AdvancedUtilitiesBlocks.ingotBlock, 1, BlockIngotBlock.STEEL), new ItemStack(AdvancedUtilitiesItems.cast, 1, 5), new ItemStack(AdvancedUtilitiesItems.plate, 1,4));
		addSmeltryRecipe(new ItemStack(AdvancedUtilitiesBlocks.ingotBlock, 1, BlockIngotBlock.STEEL), new ItemStack(AdvancedUtilitiesItems.cast, 1, 6), new ItemStack(AdvancedUtilitiesItems.steelRivets, 1));
		addSmeltryRecipe(new ItemStack(AdvancedUtilitiesBlocks.ingotBlock, 1, BlockIngotBlock.STEEL), new ItemStack(AdvancedUtilitiesItems.cast, 1, 0), new ItemStack(AdvancedUtilitiesItems.toolPart, 1,16));
		addSmeltryRecipe(new ItemStack(AdvancedUtilitiesBlocks.ingotBlock, 1, BlockIngotBlock.STEEL), new ItemStack(AdvancedUtilitiesItems.cast, 1, 1), new ItemStack(AdvancedUtilitiesItems.toolPart, 1,17));
		addSmeltryRecipe(new ItemStack(AdvancedUtilitiesBlocks.ingotBlock, 1, BlockIngotBlock.STEEL), new ItemStack(AdvancedUtilitiesItems.cast, 1, 2), new ItemStack(AdvancedUtilitiesItems.toolPart, 1,18));
		addSmeltryRecipe(new ItemStack(AdvancedUtilitiesBlocks.ingotBlock, 1, BlockIngotBlock.STEEL), new ItemStack(AdvancedUtilitiesItems.cast, 1, 3), new ItemStack(AdvancedUtilitiesItems.toolPart, 1,19));
		addSmeltryRecipe(new ItemStack(AdvancedUtilitiesBlocks.ingotBlock, 1, BlockIngotBlock.STEEL), new ItemStack(AdvancedUtilitiesItems.cast, 1, 4), new ItemStack(AdvancedUtilitiesItems.toolPart, 1,20));
		addSmeltryRecipe(new ItemStack(AdvancedUtilitiesBlocks.ingotBlock, 8, BlockIngotBlock.STEEL), new ItemStack(AdvancedUtilitiesItems.cast, 1, 9), new ItemStack(AdvancedUtilitiesItems.toolPart, 1,21));
		addSmeltryRecipe(new ItemStack(AdvancedUtilitiesBlocks.ingotBlock, 8, BlockIngotBlock.STEEL), new ItemStack(AdvancedUtilitiesItems.cast, 1, 10), new ItemStack(AdvancedUtilitiesItems.toolPart, 1,22));
		
		addSmeltryRecipe(new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.TUNGSTEN), new ItemStack(AdvancedUtilitiesItems.cast, 1, 7), new ItemStack(AdvancedUtilitiesItems.itemBulletHead, 16,2));
		addSmeltryRecipe(new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.STEEL), new ItemStack(AdvancedUtilitiesItems.cast, 1, 8), new ItemStack(AdvancedUtilitiesItems.itemCasing, 16,3));
	}
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
    		if(mould.isItemEqual(new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 10)))//
    		{
    			return new ItemStack(AdvancedUtilitiesItems.cast, 1, 9);
    		}
    		if(mould.isItemEqual(new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 13)))
    		{
    			return new ItemStack(AdvancedUtilitiesItems.cast, 1, 10);
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
    		if(mould.isItemEqual(new ItemStack(AdvancedUtilitiesItems.cast, 1, 10)) && melt.stackSize >= 8)
    		{
    			return new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 15);
    		}
    	}
		
		
    	if(OreDictionary.getOreIDs(melt)!= null && OreDictionary.getOreIDs(melt).length > 0 && HelperLibrary.isOreDicItem(melt, new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.BRONZE)))
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
    		if(mould.isItemEqual(new ItemStack(AdvancedUtilitiesItems.cast, 1, 10)) && melt.stackSize >= 8)
    		{
    			return new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 14);
    		}
    	}
		
		if(OreDictionary.getOreIDs(melt)!= null && OreDictionary.getOreIDs(melt).length > 0 &&  HelperLibrary.isOreDicItem(melt, new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.BRASS)))
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
    	if(OreDictionary.getOreIDs(melt)!= null && OreDictionary.getOreIDs(melt).length > 0 &&  HelperLibrary.isOreDicItem(melt, new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.LEAD)))
    	{
    		if(mould.isItemEqual(new ItemStack(AdvancedUtilitiesItems.cast, 1, 7)))
    		{
    			return new ItemStack(AdvancedUtilitiesItems.itemBulletHead, 16, 1);
    		}
    	}
		
		if(HelperLibrary.isOreDicItem(melt, new ItemStack(AdvancedUtilitiesBlocks.ingotBlock, 1, BlockIngotBlock.STEEL)))
    		{
    			if(mould.isItemEqual(new ItemStack(AdvancedUtilitiesItems.cast, 1, 5)))
        		{
        			return new ItemStack(AdvancedUtilitiesItems.plate, 1, 4);
        		}
    			if(mould.isItemEqual(new ItemStack(AdvancedUtilitiesItems.cast, 1, 6)))
        		{
        			return new ItemStack(AdvancedUtilitiesItems.steelRivets, 1);
        			
        		}
    			if(mould.isItemEqual(new ItemStack(AdvancedUtilitiesItems.cast, 1, 0)))
        		{
        			return new ItemStack(AdvancedUtilitiesItems.toolPart, 1,16);
        		}
    			if(mould.isItemEqual(new ItemStack(AdvancedUtilitiesItems.cast, 1, 1)))
        		{
        			return new ItemStack(AdvancedUtilitiesItems.toolPart, 1,17);
        		}
    			if(mould.isItemEqual(new ItemStack(AdvancedUtilitiesItems.cast, 1, 2)))
        		{
        			return new ItemStack(AdvancedUtilitiesItems.toolPart, 1,18);
        		}
    			if(mould.isItemEqual(new ItemStack(AdvancedUtilitiesItems.cast, 1, 3)))
        		{
        			return new ItemStack(AdvancedUtilitiesItems.toolPart, 1,19);
        		}
    			if(mould.isItemEqual(new ItemStack(AdvancedUtilitiesItems.cast, 1, 4)))
        		{
        			return new ItemStack(AdvancedUtilitiesItems.toolPart, 1,20);
        		}
    		
    		}
			
			if(OreDictionary.getOreIDs(melt)!= null && OreDictionary.getOreIDs(melt).length > 0 &&  HelperLibrary.isOreDicItem(melt, new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.TUNGSTEN)))
        	{
        		if(mould.isItemEqual(new ItemStack(AdvancedUtilitiesItems.cast, 1, 7)))
        		{
        			return new ItemStack(AdvancedUtilitiesItems.itemBulletHead, 16, 2);
        		}
        	}
    		if(OreDictionary.getOreIDs(melt)!= null && OreDictionary.getOreIDs(melt).length > 0 &&  HelperLibrary.isOreDicItem(melt, new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.STEEL)))
        	{
    			if(mould.isItemEqual(new ItemStack(AdvancedUtilitiesItems.cast, 1, 8)))
        		{
        			return new ItemStack(AdvancedUtilitiesItems.itemCasing, 16, 3);
        		}
        	}
    	
    	if(level >= 1)
    	{
    		
    		
    		
    		if(HelperLibrary.isOreDicItem(melt, new ItemStack(AdvancedUtilitiesBlocks.ingotBlock, 1, BlockIngotBlock.STEEL)) && melt.stackSize>=8)
    		{
	    		if(mould.isItemEqual(new ItemStack(AdvancedUtilitiesItems.cast, 1, 9)))
	    		{
	    			return new ItemStack(AdvancedUtilitiesItems.toolPart, 1,21);
	    		}
				if(mould.isItemEqual(new ItemStack(AdvancedUtilitiesItems.cast, 1, 10)))
	    		{
	    			return new ItemStack(AdvancedUtilitiesItems.toolPart, 1,22);
	    		}	
    		}
    	}
    	return null;
    }
	public static final String[] defaultRecipes = new String[] {
			"minecraft:clay,1,0,true,advancedutilities:toolpart,1,13,false,advancedutilities:cast,1,10,false",
	        "advancedutilities:ingot,8,2,false,advancedutilities:cast,0,9,false,advancedutilities:toolpart,1,11,false",
	        "advancedutilities:ingotblock,8,12,true,advancedutilities:cast,0,9,false,advancedutilities:toolpart,1,21,false",
	        "advancedutilities:ingotblock,8,12,true,advancedutilities:cast,0,10,false,advancedutilities:toolpart,1,22,false",
	        "advancedutilities:ingot,1,4,false,advancedutilities:cast,0,5,false,advancedutilities:plate,1,2,false",
	        "advancedutilities:ingotblock,1,12,true,advancedutilities:cast,0,1,false,advancedutilities:toolpart,1,17,false",
	        "advancedutilities:ingotblock,1,12,true,advancedutilities:cast,0,3,false,advancedutilities:toolpart,1,19,false",
	        "advancedutilities:ingotblock,1,12,true,advancedutilities:cast,0,4,false,advancedutilities:toolpart,1,20,false",
	        "minecraft:clay,1,0,true,advancedutilities:stonefittings,1,0,false,advancedutilities:cast,1,6,false",
	        "minecraft:iron_ingot,8,0,false,advancedutilities:cast,0,9,false,advancedutilities:toolpart,1,12,false",
	        "advancedutilities:ingot,1,2,false,advancedutilities:cast,0,0,false,advancedutilities:toolpart,1,0,false",
	        "advancedutilities:ingot,8,2,false,advancedutilities:cast,0,10,false,advancedutilities:toolpart,1,14,false",
	        "advancedutilities:ingot,1,2,false,advancedutilities:cast,0,1,false,advancedutilities:toolpart,1,6,false",
	        "advancedutilities:ingot,1,12,false,advancedutilities:cast,0,8,false,advancedutilities:itemcasing,16,3,false",
	        "minecraft:clay,1,0,true,advancedutilities:itemcasing,1,0,false,advancedutilities:cast,1,8,false",
	        "advancedutilities:ingot,1,6,false,advancedutilities:cast,0,7,false,advancedutilities:itembullethead,16,1,false",
	        "advancedutilities:ingotblock,1,12,true,advancedutilities:cast,0,2,false,advancedutilities:toolpart,1,18,false",
	        "minecraft:clay,1,0,true,minecraft:stone_axe,1,0,false,advancedutilities:cast,1,4,false",
	        "advancedutilities:ingotblock,1,12,true,advancedutilities:cast,0,6,false,advancedutilities:steelrivets,1,0,false",
	        "advancedutilities:ingot,1,8,false,advancedutilities:cast,0,7,false,advancedutilities:itembullethead,16,2,false",
	        "minecraft:clay,1,0,true,minecraft:stone_shovel,1,0,false,advancedutilities:cast,1,3,false",
	        "minecraft:iron_ingot,8,0,false,advancedutilities:cast,0,10,false,advancedutilities:toolpart,1,15,false",
	        "minecraft:clay,1,0,true,minecraft:stick,1,0,false,advancedutilities:cast,1,0,false",
	        "advancedutilities:ingot,1,4,false,advancedutilities:cast,0,6,false,advancedutilities:brassfittings,1,0,false",
	        "minecraft:iron_ingot,1,0,false,advancedutilities:cast,0,1,false,advancedutilities:toolpart,1,2,false",
	        "advancedutilities:ingot,1,2,false,advancedutilities:cast,0,8,false,advancedutilities:itemcasing,16,1,false",
	        "minecraft:clay,1,0,true,advancedutilities:toolpart,1,10,false,advancedutilities:cast,1,9,false",
	        "advancedutilities:ingot,1,2,false,advancedutilities:cast,0,2,false,advancedutilities:toolpart,1,7,false",
	        "minecraft:iron_ingot,1,0,false,advancedutilities:cast,0,2,false,advancedutilities:toolpart,1,3,false",
	        "minecraft:iron_ingot,1,0,false,advancedutilities:cast,0,0,false,advancedutilities:toolpart,1,1,false",
	        "advancedutilities:ingotblock,1,12,true,advancedutilities:cast,0,5,false,advancedutilities:plate,1,4,false",
	        "minecraft:iron_ingot,1,0,false,advancedutilities:cast,0,3,false,advancedutilities:toolpart,1,4,false",
	        "advancedutilities:ingot,1,2,false,advancedutilities:cast,0,4,false,advancedutilities:toolpart,1,9,false",
	        "advancedutilities:ingot,1,2,false,advancedutilities:cast,0,5,false,advancedutilities:plate,1,1,false",
	        "advancedutilities:ingotblock,1,12,true,advancedutilities:cast,0,0,false,advancedutilities:toolpart,1,16,false",
	        "advancedutilities:ingot,1,2,false,advancedutilities:cast,0,3,false,advancedutilities:toolpart,1,8,false",
	        "minecraft:clay,1,0,true,advancedutilities:itembullethead,1,0,false,advancedutilities:cast,1,7,false",
	        "minecraft:iron_ingot,1,0,false,advancedutilities:cast,0,5,false,advancedutilities:plate,1,0,false",
	        "minecraft:clay,1,0,true,advancedutilities:plate,1,3,false,advancedutilities:cast,1,5,false",
	        "minecraft:clay,1,0,true,minecraft:stone_pickaxe,1,0,false,advancedutilities:cast,1,1,false",
	        "minecraft:clay,1,0,true,minecraft:stone_sword,1,0,false,advancedutilities:cast,1,2,false",
	        "minecraft:iron_ingot,1,0,false,advancedutilities:cast,0,4,false,advancedutilities:toolpart,1,5,false"};
	
	
}
	
	

