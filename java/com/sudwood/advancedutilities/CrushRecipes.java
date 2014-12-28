package com.sudwood.advancedutilities;

import com.sudwood.advancedutilities.items.AdvancedUtilitiesItems;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
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
	 
	 public static final int PLANKS = OreDictionary.getOreID("plankWood");
	public static ItemStack getCrushResult(ItemStack stack)
	{
		if(stack == null)
			return null;
		if(stack.getItem() == Item.getItemFromBlock(Blocks.cobblestone))
		{
			return new ItemStack(Blocks.gravel, 1);
		}
		if(stack.getItem() == Item.getItemFromBlock(Blocks.gravel))
		{
			return new ItemStack(Blocks.sand, 1);
		}
		int checkID = OreDictionary.getOreIDs(stack)[0];
		
		if(checkID == ORE_TIN)
		{
			return new ItemStack(AdvancedUtilitiesItems.dust, 2, 2);
		}
		if(checkID == ORE_COPPER)
		{
			return new ItemStack(AdvancedUtilitiesItems.dust, 2, 0);
		}
		if(checkID == ORE_ZINC)
		{
			return new ItemStack(AdvancedUtilitiesItems.dust, 2, 3);
		}
		if(checkID == ORE_IRON)
		{
			return new ItemStack(AdvancedUtilitiesItems.dust, 2, 1);
		}
		if(checkID == ORE_GOLD)
		{
			return new ItemStack(AdvancedUtilitiesItems.dust, 2, 6);
		}
		if(checkID == ORE_DIAMOND)
		{
			return new ItemStack(AdvancedUtilitiesItems.dust, 2, 7);
		}
		if(checkID == ORE_LAPIS)
		{
			return new ItemStack(Items.dye, 9, 4);
		}
		if(checkID == ORE_REDSTONE)
		{
			return new ItemStack(Items.redstone, 12);
		}
		if(checkID == ORE_EMERALD)
		{
			return new ItemStack(Items.emerald, 4);
		}
		if(checkID == ORE_QUARTZ)
		{
			return new ItemStack(Items.quartz, 4);
		}
		if(checkID == ORE_COAL)
		{
			return new ItemStack(Items.coal, 4);
		}
		if(checkID == INGOT_TIN)
		{
			return new ItemStack(AdvancedUtilitiesItems.dust, 1, 2);
		}
		if(checkID == INGOT_COPPER)
		{
			return new ItemStack(AdvancedUtilitiesItems.dust, 1, 0);
		}
		if(checkID == INGOT_ZINC)
		{
			return new ItemStack(AdvancedUtilitiesItems.dust, 1, 3);
		}
		if(checkID == INGOT_BRONZE)
		{
			return new ItemStack(AdvancedUtilitiesItems.dust, 1, 4);
		}
		if(checkID == INGOT_BRASS)
		{
			return new ItemStack(AdvancedUtilitiesItems.dust, 1, 5);
		}
		
		if(checkID == ORE_SILVER)
		{
			return new ItemStack(AdvancedUtilitiesItems.dust, 2, 9);
		}
		if(checkID == INGOT_SILVER)
		{
			return new ItemStack(AdvancedUtilitiesItems.dust, 1, 9);
		}
		if(checkID == ORE_LEAD)
		{
			return new ItemStack(AdvancedUtilitiesItems.dust, 2, 12);
		}
		if(checkID == INGOT_LEAD)
		{
			return new ItemStack(AdvancedUtilitiesItems.dust, 1, 12);
		}
		if(checkID == ORE_BAUXITE || checkID == ORE_ALUMINUM)
		{
			return new ItemStack(AdvancedUtilitiesItems.dust, 2, 11);
		}
		if(checkID == INGOT_ALUMINUM)
		{
			return new ItemStack(AdvancedUtilitiesItems.dust, 1, 11);
		}
		if(checkID == ORE_TUNGSTEN)
		{
			return new ItemStack(AdvancedUtilitiesItems.dust, 2, 13);
		}
		if(checkID == INGOT_TUNGSTEN)
		{
			return new ItemStack(AdvancedUtilitiesItems.dust, 1, 13);
		}
		if(checkID == ORE_PLATINUM)
		{
			return new ItemStack(AdvancedUtilitiesItems.dust, 2, 10);
		}
		if(checkID == INGOT_PLATINUM)
		{
			return new ItemStack(AdvancedUtilitiesItems.dust, 1, 10);
		}
		if(checkID == INGOT_IRIDIUM)
		{
			return new ItemStack(AdvancedUtilitiesItems.dust, 1, 14);
		}
		if(checkID == INGOT_PALIDIUM)
		{
			return new ItemStack(AdvancedUtilitiesItems.dust, 1, 15);
		}
		if(checkID == INGOT_STEEL)
		{
			return new ItemStack(AdvancedUtilitiesItems.dust, 1, 16);
		}
		if(checkID == ORE_NICKEL)
		{
			return new ItemStack(AdvancedUtilitiesItems.dust, 2, 20);
		}
		if(checkID == INGOT_NICKEL)
		{
			return new ItemStack(AdvancedUtilitiesItems.dust, 1, 20);
		}
		if(checkID == PLANKS)
		{
			return new ItemStack(AdvancedUtilitiesItems.dust, 1, 19);
		}
		if(stack.getItem() == Items.iron_ingot)
		{
			return new ItemStack(AdvancedUtilitiesItems.dust, 1, 1);
		}
		if(stack.getItem() == Items.gold_ingot)
		{
			return new ItemStack(AdvancedUtilitiesItems.dust, 1, 6);
		}
		if(stack.getItem() == Items.diamond)
		{
			return new ItemStack(AdvancedUtilitiesItems.dust, 1, 7);
		}
		if(stack.isItemEqual(new ItemStack(Items.coal, 1, 0)))
		{
			return new ItemStack(AdvancedUtilitiesItems.dust, 1, 8);
		}
		if(stack.getItem() == Item.getItemFromBlock(Blocks.coal_block))
		{
			return new ItemStack(AdvancedUtilitiesItems.dust, 9, 8);
		}
		if(stack.getItem() == Items.wheat)
		{
			return new ItemStack(AdvancedUtilitiesItems.dust, 1, 18);
		}
		if(checkID == -1) 
			return null;
		return null;
	}
}
