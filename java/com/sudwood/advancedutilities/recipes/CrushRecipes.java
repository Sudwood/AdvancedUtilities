package com.sudwood.advancedutilities.recipes;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.sudwood.advancedutilities.HelperLibrary;
import com.sudwood.advancedutilities.config.RecipeConfig;
import com.sudwood.advancedutilities.items.AdvancedUtilitiesItems;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class CrushRecipes 
{
	
	 public static final int ORE_TIN = OreDictionary.getOreID("oreTin");
	 public static final int ORE_COPPER = OreDictionary.getOreID("oreCopper");
	 public static final int ORE_ZINC = OreDictionary.getOreID("oreZinc");
	 public static final int ORE_SILVER = OreDictionary.getOreID("oreSilver");
	 public static final int ORE_LEAD = OreDictionary.getOreID("oreLead");
	 public static final int ORE_BAUXITE = OreDictionary.getOreID("oreBauxite");
	 public static final int ORE_ALUMINUM = OreDictionary.getOreID("oreAluminum");
	 public static final int ORE_TUNGSTEN = OreDictionary.getOreID("oreTungsten");
	 public static final int ORE_PLATINUM = OreDictionary.getOreID("orePlatinum");
	 public static final int ORE_IRON = OreDictionary.getOreID("oreIron");
	 public static final int ORE_GOLD = OreDictionary.getOreID("oreGold");
	 
	 public static final int ORE_DIAMOND = OreDictionary.getOreID("oreDiamond");
	 public static final int ORE_LAPIS = OreDictionary.getOreID("oreLapis");
	 public static final int ORE_REDSTONE = OreDictionary.getOreID("oreRedstone");
	 public static final int ORE_EMERALD = OreDictionary.getOreID("oreEmerald");
	 public static final int ORE_QUARTZ = OreDictionary.getOreID("oreQuartz");
	 public static final int ORE_COAL = OreDictionary.getOreID("oreCoal");
	 public static final int ORE_NICKEL = OreDictionary.getOreID("oreNickel");
	 
	 public static final int BLOCK_COAL = OreDictionary.getOreID("blockCoal");
	 
	 public static final int INGOT_TIN = OreDictionary.getOreID("ingotTin");
	 public static final int INGOT_COPPER = OreDictionary.getOreID("ingotCopper");
	 public static final int INGOT_ZINC = OreDictionary.getOreID("ingotZinc");
	 public static final int INGOT_BRONZE = OreDictionary.getOreID("ingotBronze");
	 public static final int INGOT_BRASS = OreDictionary.getOreID("ingotBrass");
	 public static final int INGOT_SILVER = OreDictionary.getOreID("ingotSilver");
	 public static final int INGOT_LEAD = OreDictionary.getOreID("ingotLead");
	 public static final int INGOT_ALUMINUM = OreDictionary.getOreID("ingotAluminum");
	 public static final int INGOT_TUNGSTEN = OreDictionary.getOreID("ingotTungsten");
	 public static final int INGOT_PLATINUM = OreDictionary.getOreID("ingotPlatinum");
	 public static final int INGOT_IRIDIUM = OreDictionary.getOreID("ingotIridium");
	 public static final int INGOT_PALIDIUM = OreDictionary.getOreID("ingotPalidium");
	 public static final int INGOT_STEEL = OreDictionary.getOreID("ingotSteel");
	 public static final int INGOT_NICKEL = OreDictionary.getOreID("ingotNickel");
	 public static final int INGOT_IRON = OreDictionary.getOreID("ingotIron");
	 public static final int INGOT_GOLD = OreDictionary.getOreID("ingotGold");
	 
	 public static final int GEM_DIAMOND = OreDictionary.getOreID("gemDiamond");
	 
	 public static final int PLANKS = OreDictionary.getOreID("plankWood");
	 public static final int COBBLESTONE = OreDictionary.getOreID("cobblestone");
	 public static final int SAND = OreDictionary.getOreID("sand");
	 public static final int GRAVEL = OreDictionary.getOreID("gravel");
	 public static final int COAL = OreDictionary.getOreID("coal");
	 public static final int WHEAT = OreDictionary.getOreID("cropWheat");
	 public static final int BONE = OreDictionary.getOreID("itemBone");
	 
	 private static Map<ItemStack, Object> map = new HashMap();
	 private static Map numResult = new HashMap();
	 private static Map<ItemStack, ItemStack> itemMap = new HashMap();
	 
	 public static void loadRecipes()
	 {
		 addCrushRecipe(COBBLESTONE, new ItemStack(Blocks.gravel, 1));
		 addCrushRecipe(GRAVEL, new ItemStack(Blocks.sand, 1));
		 addCrushRecipe(ORE_TIN, new ItemStack(AdvancedUtilitiesItems.dust, 2, 2));
		 addCrushRecipe(ORE_COPPER, new ItemStack(AdvancedUtilitiesItems.dust, 2, 0));
		 addCrushRecipe(ORE_ZINC, new ItemStack(AdvancedUtilitiesItems.dust, 2, 3));
		 addCrushRecipe(ORE_IRON, new ItemStack(AdvancedUtilitiesItems.dust, 2, 1));
		 addCrushRecipe(ORE_GOLD, new ItemStack(AdvancedUtilitiesItems.dust, 2, 6));
		 addCrushRecipe(ORE_DIAMOND, new ItemStack(AdvancedUtilitiesItems.dust, 2, 7));
		 addCrushRecipe(ORE_LAPIS, new ItemStack(Items.dye, 9, 4));
		 addCrushRecipe(ORE_REDSTONE, new ItemStack(Items.redstone, 12));
		 addCrushRecipe(ORE_EMERALD, new ItemStack(Items.emerald, 4));
		 addCrushRecipe(ORE_QUARTZ, new ItemStack(Items.quartz, 4));
		 addCrushRecipe(ORE_COAL, new ItemStack(Items.coal, 4, 0));
		 addCrushRecipe(INGOT_TIN, new ItemStack(AdvancedUtilitiesItems.dust, 1,2));
		 addCrushRecipe(INGOT_COPPER, new ItemStack(AdvancedUtilitiesItems.dust, 1, 0));
		 addCrushRecipe(INGOT_ZINC, new ItemStack(AdvancedUtilitiesItems.dust, 1, 3));
		 addCrushRecipe(INGOT_BRONZE, new ItemStack(AdvancedUtilitiesItems.dust, 1, 4));
		 addCrushRecipe(INGOT_BRASS, new ItemStack(AdvancedUtilitiesItems.dust, 1, 5));
		 addCrushRecipe(ORE_SILVER, new ItemStack(AdvancedUtilitiesItems.dust, 2, 9));
		 addCrushRecipe(INGOT_SILVER, new ItemStack(AdvancedUtilitiesItems.dust, 1, 9));
		 addCrushRecipe(ORE_LEAD, new ItemStack(AdvancedUtilitiesItems.dust, 2, 12));
		 addCrushRecipe(INGOT_LEAD, new ItemStack(AdvancedUtilitiesItems.dust, 1, 12));
		 addCrushRecipe(ORE_BAUXITE, new ItemStack(AdvancedUtilitiesItems.dust, 2, 11));
		 addCrushRecipe(ORE_ALUMINUM, new ItemStack(AdvancedUtilitiesItems.dust, 2, 11));
		 addCrushRecipe(INGOT_ALUMINUM, new ItemStack(AdvancedUtilitiesItems.dust, 1, 11));
		 addCrushRecipe(ORE_TUNGSTEN, new ItemStack(AdvancedUtilitiesItems.dust, 2, 13));
		 addCrushRecipe(INGOT_TUNGSTEN, new ItemStack(AdvancedUtilitiesItems.dust, 1, 13));
		 addCrushRecipe(ORE_PLATINUM, new ItemStack(AdvancedUtilitiesItems.dust, 2, 10));
		 addCrushRecipe(INGOT_PLATINUM, new ItemStack(AdvancedUtilitiesItems.dust, 1, 10));
		 addCrushRecipe(INGOT_IRIDIUM, new ItemStack(AdvancedUtilitiesItems.dust, 1, 14));
		 addCrushRecipe(INGOT_PALIDIUM, new ItemStack(AdvancedUtilitiesItems.dust, 1, 15));
		 addCrushRecipe(INGOT_STEEL, new ItemStack(AdvancedUtilitiesItems.dust, 1, 16));
		 addCrushRecipe(ORE_NICKEL, new ItemStack(AdvancedUtilitiesItems.dust, 2, 20));
		 addCrushRecipe(INGOT_NICKEL, new ItemStack(AdvancedUtilitiesItems.dust, 1, 20));
		 addCrushRecipe(PLANKS, new ItemStack(AdvancedUtilitiesItems.dust, 1, 19));
		 addCrushRecipe(INGOT_IRON, new ItemStack(AdvancedUtilitiesItems.dust, 1, 1));
		 addCrushRecipe(INGOT_GOLD, new ItemStack(AdvancedUtilitiesItems.dust, 1, 6));
		 addCrushRecipe(GEM_DIAMOND, new ItemStack(AdvancedUtilitiesItems.dust, 1, 7));
		 addCrushRecipe(COAL, new ItemStack(AdvancedUtilitiesItems.dust, 1, 8));
		 addCrushRecipe(BLOCK_COAL, new ItemStack(AdvancedUtilitiesItems.dust, 9, 8));
		 addCrushRecipe(WHEAT, new ItemStack(AdvancedUtilitiesItems.dust, 1, 18));
		 addCrushRecipe(BONE, new ItemStack(Items.dye, 5, 15));
	 }
	 
	 public static void addCrushRecipe(Object ingr, ItemStack result)
	 {
		 map.put(result, ingr);
		 numResult.put(ingr, result.stackSize);
		 if(OreDictionary.getOreName((Integer)ingr)!=null && OreDictionary.getOres(OreDictionary.getOreName((Integer)ingr))!=null && OreDictionary.getOres(OreDictionary.getOreName((Integer)ingr)).size() >0&& OreDictionary.getOres(OreDictionary.getOreName((Integer)ingr)).get(0)!=null)
		 {
			 ItemStack temp = OreDictionary.getOres(OreDictionary.getOreName((Integer)ingr)).get(0);
			 itemMap.put(result, temp);
		 }
	 }
	 
	 public static Map getItemMap()
	 {
		 return itemMap;
	 }
	 public static Map getRecipeMap()
	 {
		 return map;
	 }
	 public static Map getNumMap()
	 {
		 return numResult;
	 }
	public static ItemStack getCrushResult(ItemStack stack)
	{
		if(stack == null)
			return null;
		for(Entry<ItemStack, ItemStack> recipe: itemMap.entrySet())
		{
			if(HelperLibrary.isOreDicItem(stack, recipe.getValue()) || HelperLibrary.areItemStacksSameItemAndDamage(stack, recipe.getValue()))
			{
				if(stack.stackSize >= recipe.getValue().stackSize)
					return recipe.getKey().copy();
			}
		}
		return null;
	}
	public static boolean getCrushResultForAutomation(ItemStack stack)
	{
		if(stack == null)
			return false;
		for(Entry<ItemStack, ItemStack> recipe: itemMap.entrySet())
		{
			if(HelperLibrary.isOreDicItem(stack, recipe.getValue()) || HelperLibrary.areItemStacksSameItemAndDamage(stack, recipe.getValue()))
			{
				return true;
			}
		}
		return true;
	}
	
	public static int getIngredientSize(ItemStack stack)
	{
		if(stack == null)
			return 0;
		for(Entry<ItemStack, ItemStack> recipe: itemMap.entrySet())
		{
			if(HelperLibrary.isOreDicItem(stack, recipe.getValue()) || HelperLibrary.areItemStacksSameItemAndDamage(stack, recipe.getValue()))
			{
				if(stack.stackSize >= recipe.getValue().stackSize)
					return recipe.getValue().stackSize;
			}
		}
		return 0;
	}
	public static int getCrushResultNumber(ItemStack stack)
	{
		if(stack == null)
			return 0;
		int checkID = OreDictionary.getOreIDs(stack)[0];
		if(checkID == -1) 
			return 0;
		else if(numResult.containsKey(checkID))
		{
			return (Integer) numResult.get(checkID);
		}
		return 0;
	}
	
	public static void addCrushRecipe(String melt, int meltCount,int meltDam, boolean meltBlock, String result, int resultCount, int resultDam, boolean resultBlock)
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
			map.put(pmet3, OreDictionary.getOreID(pmet1));
			numResult.put(OreDictionary.getOreID(pmet1), pmet3.stackSize);
			itemMap.put(pmet3,pmet1);
		}
	}
	
	public static void registerCrushRecipesString()
	{
		if(RecipeConfig.CrushRecipes!=null && RecipeConfig.CrushRecipes.length >0)
		{
			for(int i = 0; i<RecipeConfig.CrushRecipes.length; i++)
			{
				String[] recipeArray = RecipeConfig.CrushRecipes[i].split(",");
				/*recipeArray[0]; //melt item
				recipeArray[1]; // melt size
				recipeArray[2]; // melt dam
				recipeArray[3]; //melt block
				recipeArray[4]; //mould item
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
				
				String result = recipeArray[4];
				int resultSize = Integer.parseInt(recipeArray[5]);
				int resultDam = Integer.parseInt(recipeArray[6]);
				Boolean resultBlock = Boolean.valueOf(recipeArray[7]);
				
				addCrushRecipe(melt, meltSize, meltDam, meltBlock,result, resultSize, resultDam, resultBlock);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
			}
		}
	}
	
	public static String[] getCrushString()
	{
		String[] result = new String[itemMap.size()];
		int count = 0;
		for(Map.Entry<ItemStack, ItemStack> recipe : itemMap.entrySet())
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
					"advancedutilities:ingot,1,6,false,advancedutilities:dust,1,12,false",
			        "advancedutilities:ingot,1,0,false,advancedutilities:dust,1,0,false",
			        "minecraft:gravel,1,0,true,minecraft:sand,1,0,true",
			        "advancedutilities:ore,1,0,true,advancedutilities:dust,2,0,false",
			        "advancedutilities:ingot,1,1,false,advancedutilities:dust,1,2,false",
			        "advancedutilities:ore,1,7,true,advancedutilities:dust,2,10,false",
			        "advancedutilities:ore,1,5,true,advancedutilities:dust,2,11,false",
			        "advancedutilities:ingot,1,7,false,advancedutilities:dust,1,11,false",
			        "minecraft:wheat,1,0,false,advancedutilities:dust,1,18,false",
			        "advancedutilities:ingot,1,5,false,advancedutilities:dust,1,9,false",
			        "advancedutilities:ingot,1,10,false,advancedutilities:dust,1,14,false",
			        "minecraft:gold_ore,1,0,true,advancedutilities:dust,2,6,false",
			        "advancedutilities:ore,1,4,true,advancedutilities:dust,2,12,false",
			        "advancedutilities:ore,1,6,true,advancedutilities:dust,2,13,false",
			        "minecraft:planks,1,32767,true,advancedutilities:dust,1,19,false",
			        "minecraft:quartz_ore,1,0,true,minecraft:quartz,4,0,false",
			        "minecraft:iron_ore,1,0,true,advancedutilities:dust,2,1,false",
			        "minecraft:redstone_ore,1,0,true,minecraft:redstone,12,0,false",
			        "minecraft:emerald_ore,1,0,true,minecraft:emerald,4,0,false",
			        "advancedutilities:ore,1,1,true,advancedutilities:dust,2,2,false",
			        "minecraft:diamond,1,0,false,advancedutilities:dust,1,7,false",
			        "advancedutilities:ingot,1,9,false,advancedutilities:dust,1,10,false",
			        "minecraft:coal,1,0,false,advancedutilities:dust,1,8,false",
			        "advancedutilities:ingot,1,3,false,advancedutilities:dust,1,3,false",
			        "advancedutilities:ingot,1,12,false,advancedutilities:dust,1,16,false",
			        "minecraft:iron_ingot,1,0,false,advancedutilities:dust,1,1,false",
			        "advancedutilities:ore,1,2,true,advancedutilities:dust,2,3,false",
			        "advancedutilities:ingot,1,4,false,advancedutilities:dust,1,5,false",
			        "advancedutilities:ingot,1,8,false,advancedutilities:dust,1,13,false",
			        "advancedutilities:ore,1,8,true,advancedutilities:dust,2,20,false",
			        "minecraft:cobblestone,1,0,true,minecraft:gravel,1,0,true",
			        "advancedutilities:ingot,1,2,false,advancedutilities:dust,1,4,false",
			        "advancedutilities:ore,1,5,true,advancedutilities:dust,2,11,false",
			        "minecraft:lapis_ore,1,0,true,minecraft:dye,9,4,false",
			        "advancedutilities:ingot,1,11,false,advancedutilities:dust,1,15,false",
			        "minecraft:gold_ingot,1,0,false,advancedutilities:dust,1,6,false",
			        "minecraft:bone,1,0,false,minecraft:dye,5,15,false",
			        "minecraft:coal_ore,1,0,true,minecraft:coal,4,0,false",
			        "minecraft:diamond_ore,1,0,true,advancedutilities:dust,2,7,false",
			        "advancedutilities:ore,1,3,true,advancedutilities:dust,2,9,false",
			        "minecraft:coal_block,1,0,true,advancedutilities:dust,9,8,false"
			};
}
