package com.sudwood.advancedutilities;

import java.util.HashMap;
import java.util.Map;

import com.sudwood.advancedutilities.items.AdvancedUtilitiesItems;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
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
	 
	 private static Map map = new HashMap();
	 private static Map numResult = new HashMap();
	 
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
	 }
	 
	 public static void addCrushRecipe(Object key, ItemStack value)
	 {
		 map.put(key, value);
		 numResult.put(key, value.stackSize);
	 }
	public static ItemStack getCrushResult(ItemStack stack)
	{
		if(stack == null)
			return null;
		int checkID = OreDictionary.getOreIDs(stack)[0];
		if(checkID == -1) 
			return null;
		else if(map.containsKey(checkID))
		{
			return (ItemStack) map.get(checkID);
		}
		return null;
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
}
