package com.sudwood.advancedutilities.items;

import com.sudwood.advancedutilities.AdvancedUtilities;
import com.sudwood.advancedutilities.blocks.AdvancedUtilitiesBlocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;

public class AdvancedUtilitiesItems 
{
	public static Item ingotCopper;
	public static Item ingotTin;
	public static Item ingotBronze;
	public static Item ingotZinc;
	public static Item ingotBrass;
	public static Item ingotSilver;
	public static Item ingotLead;
	public static Item ingotAluminum;
	public static Item ingotTungsten;
	public static Item ingotPlatinum;
	public static Item ingotIridium;
	public static Item ingotPalidium;
	public static Item ingotSteel;
	
	public static Item rubber;
	public static Item toolPart;
	public static Item toolBE;
	public static Item cast;
	public static Item plate;
	public static Item brassRivets;
	public static Item stoneRivets;
	public static Item bronzeHelm;
	public static Item bronzeChest;
	public static Item bronzeLegs;
	public static Item bronzeBoots;
	public static Item dust;
	
	public static Item pnumaticGun;
	public static Item bronzeBullet;
	public static Item steamJetpack;
	public static Item itemCasing;
	public static Item itemBulletHead;
	public static Item jackHammer;
	
	public static Item speedyMinecart;
	public static Item speedyChestcart;
	public static void init()
	{
		ingotCopper = new ItemIngot(0).setCreativeTab(AdvancedUtilities.advancedTab).setUnlocalizedName("CopperIngot");
		ingotTin = new ItemIngot(1).setCreativeTab(AdvancedUtilities.advancedTab).setUnlocalizedName("TinIngot");
		ingotBronze = new ItemIngot(2).setCreativeTab(AdvancedUtilities.advancedTab).setUnlocalizedName("BronzeIngot");
		ingotZinc = new ItemIngot(3).setCreativeTab(AdvancedUtilities.advancedTab).setUnlocalizedName("ZincIngot");
		ingotBrass = new ItemIngot(4).setCreativeTab(AdvancedUtilities.advancedTab).setUnlocalizedName("BrassIngot");
		ingotSilver = new ItemIngot(5).setCreativeTab(AdvancedUtilities.advancedTab).setUnlocalizedName("SilverIngot");
		ingotLead = new ItemIngot(6).setCreativeTab(AdvancedUtilities.advancedTab).setUnlocalizedName("LeadIngot");
		ingotAluminum = new ItemIngot(7).setCreativeTab(AdvancedUtilities.advancedTab).setUnlocalizedName("AluminumIngot");
		ingotTungsten = new ItemIngot(8).setCreativeTab(AdvancedUtilities.advancedTab).setUnlocalizedName("TungstenIngot");
		ingotPlatinum = new ItemIngot(9).setCreativeTab(AdvancedUtilities.advancedTab).setUnlocalizedName("PlatinumIngot");
		ingotIridium = new ItemIngot(10).setCreativeTab(AdvancedUtilities.advancedTab).setUnlocalizedName("IridiumIngot");
		ingotPalidium = new ItemIngot(11).setCreativeTab(AdvancedUtilities.advancedTab).setUnlocalizedName("PalidiumIngot");
		ingotSteel = new ItemIngot(12).setCreativeTab(AdvancedUtilities.advancedTab).setUnlocalizedName("SteelIngot");
		dust = new ItemDust().setUnlocalizedName("Dust");
		
		toolPart = new ItemToolPart().setUnlocalizedName("toolPart");
		toolBE = new ItemBETool().setUnlocalizedName("BETool");
		cast = new ItemCast().setUnlocalizedName("Cast");
		plate = new ItemPlate().setUnlocalizedName("Plate");
		brassRivets = new ItemRivets(0).setUnlocalizedName("BrassRivets").setCreativeTab(AdvancedUtilities.advancedTab);
		stoneRivets = new ItemRivets(1).setUnlocalizedName("StoneRivets").setCreativeTab(AdvancedUtilities.advancedTab);
		
		ArmorMaterial armorBronze = EnumHelper.addArmorMaterial("BRONZEAU", 1000, new int[]{3, 7, 6, 3}, 9);
		bronzeHelm = new ItemArmorBE(armorBronze, 0, 0).setUnlocalizedName("BronzeHelm").setCreativeTab(AdvancedUtilities.advancedBEToolsTab);
		bronzeChest = new ItemArmorBE(armorBronze, 0, 1).setUnlocalizedName("BronzeChest").setCreativeTab(AdvancedUtilities.advancedBEToolsTab);
		bronzeLegs = new ItemArmorBE(armorBronze, 0, 2).setUnlocalizedName("BronzeLegs").setCreativeTab(AdvancedUtilities.advancedBEToolsTab);
		bronzeBoots = new ItemArmorBE(armorBronze, 0, 3).setUnlocalizedName("BronzeBoots").setCreativeTab(AdvancedUtilities.advancedBEToolsTab);
		pnumaticGun = new ItemPnumaticGun().setUnlocalizedName("PnumaticGun").setCreativeTab(AdvancedUtilities.advancedBEToolsTab);
		bronzeBullet = new ItemBulletBE().setUnlocalizedName("BronzeBullet").setCreativeTab(AdvancedUtilities.advancedBEToolsTab);
		itemCasing = new ItemCasing().setUnlocalizedName("Casing");
		itemBulletHead = new ItemBulletHead().setUnlocalizedName("BulletHead");
		jackHammer = new ItemJackHammer().setUnlocalizedName("JackHammer").setCreativeTab(AdvancedUtilities.advancedBEToolsTab);
		
		speedyMinecart = new ItemSpeedyMinecart(0).setUnlocalizedName("SpeedyMinecart").setCreativeTab(AdvancedUtilities.advancedTab);
		speedyChestcart = new ItemSpeedyChestcart(1).setUnlocalizedName("SpeedyChestcart").setCreativeTab(AdvancedUtilities.advancedTab);
		
		ArmorMaterial armorSteamJetpack = EnumHelper.addArmorMaterial("SJETPACKAU", 0, new int[]{3, 7, 6, 3}, 10);
		steamJetpack = new ItemArmorSteamJetpack(armorSteamJetpack, 0, 1).setUnlocalizedName("SteamJetpack").setCreativeTab(AdvancedUtilities.advancedBEToolsTab);
		
		registerItems();
	}
	
	public static void registerItems()
	{
		GameRegistry.registerItem(ingotCopper, "advancedutilities:ingotcopper");
		GameRegistry.registerItem(ingotTin, "advancedutilities:ingottin");
		GameRegistry.registerItem(ingotBronze, "advancedutilities:ingotbronze");
		GameRegistry.registerItem(ingotZinc, "advancedutilities:ingotzinc");
		GameRegistry.registerItem(ingotBrass, "advancedutilities:ingotbrass");
		GameRegistry.registerItem(ingotSilver, "advancedutilities:ingotsilver");
		GameRegistry.registerItem(ingotLead, "advancedutilities:ingotlead");
		GameRegistry.registerItem(ingotAluminum, "advancedutilities:ingotaluminum");
		GameRegistry.registerItem(ingotTungsten, "advancedutilities:ingottungsten");
		GameRegistry.registerItem(ingotPlatinum, "advancedutilities:ingotplatinum");
		GameRegistry.registerItem(ingotIridium, "advancedutilities:ingotiridium");
		GameRegistry.registerItem(ingotPalidium, "advancedutilities:ingotpalidium");
		GameRegistry.registerItem(ingotSteel, "advancedutilities:ingotsteel");
		
		GameRegistry.registerItem(dust, "advancedutilities:dust");
		
		GameRegistry.registerItem(toolPart, "advancedutilities:toolpart");
		GameRegistry.registerItem(toolBE, "advancedutilities:betool");
		GameRegistry.registerItem(cast, "advancedutilities:cast");
		GameRegistry.registerItem(plate, "advancedutilities:plate");
		GameRegistry.registerItem(brassRivets, "advancedutilities:brassfittings");
		GameRegistry.registerItem(stoneRivets, "advancedutilities:stonefittings");
		
		GameRegistry.registerItem(bronzeHelm, "advancedutilities:bronzehelm");
		GameRegistry.registerItem(bronzeChest, "advancedutilities:bronzechest");
		GameRegistry.registerItem(bronzeLegs, "advancedutilities:bronzelegs");
		GameRegistry.registerItem(bronzeBoots, "advancedutilities:bronzeboots");
		GameRegistry.registerItem(pnumaticGun, "advancedutilities:pnumaticgun");
		GameRegistry.registerItem(bronzeBullet, "advancedutilities:bronzebullet");
		GameRegistry.registerItem(steamJetpack, "advancedutilities:steamjetpack");
		GameRegistry.registerItem(itemCasing, "advancedutilities:itemcasing");
		GameRegistry.registerItem(itemBulletHead, "advancedutilities:itembullethead");
		GameRegistry.registerItem(jackHammer, "advancedutilities:jackhammer");
		
		GameRegistry.registerItem(speedyMinecart, "advancedutilities:speedyminecart");
		GameRegistry.registerItem(speedyChestcart, "advancedutilities:speedychestcart");
		
		OreDictionary.registerOre("ingotCopper", ingotCopper);
		OreDictionary.registerOre("ingotTin", ingotTin);
		OreDictionary.registerOre("ingotBronze", ingotBronze);
		OreDictionary.registerOre("ingotZinc", ingotZinc);
		OreDictionary.registerOre("ingotBrass", ingotBrass);
		
		OreDictionary.registerOre("ingotSilver", ingotSilver);
		OreDictionary.registerOre("ingotLead", ingotLead);
		OreDictionary.registerOre("ingotAluminum", ingotAluminum);
		OreDictionary.registerOre("ingotTungsten", ingotTungsten);
		OreDictionary.registerOre("ingotPlatinum", ingotPlatinum);
		OreDictionary.registerOre("ingotIridium", ingotIridium);
		OreDictionary.registerOre("ingotPalidium", ingotPalidium);
		OreDictionary.registerOre("ingotSteel", ingotSteel);
		
		OreDictionary.registerOre("dustCopper", new ItemStack(dust, 1, 0));
		OreDictionary.registerOre("dustIron", new ItemStack(dust, 1, 1));
		OreDictionary.registerOre("dustTin", new ItemStack(dust, 1, 2));
		OreDictionary.registerOre("dustZinc", new ItemStack(dust, 1, 3));
		OreDictionary.registerOre("dustBronze", new ItemStack(dust, 1, 4));
		OreDictionary.registerOre("dustBrass", new ItemStack(dust, 1, 5));
		OreDictionary.registerOre("dustGold", new ItemStack(dust, 1, 6));
		OreDictionary.registerOre("dustDiamond", new ItemStack(dust, 1, 7));
		OreDictionary.registerOre("dustCoal", new ItemStack(dust, 1, 8));
		OreDictionary.registerOre("dustSilver", new ItemStack(dust, 1, 9));
		OreDictionary.registerOre("dustPlatinum", new ItemStack(dust, 1, 10));
		OreDictionary.registerOre("dustAluminum", new ItemStack(dust, 1, 11));
		OreDictionary.registerOre("dustLead", new ItemStack(dust, 1, 12));
		OreDictionary.registerOre("dustTungsten", new ItemStack(dust, 1, 13));
		OreDictionary.registerOre("dustIridium", new ItemStack(dust, 1, 14));
		OreDictionary.registerOre("dustPalidium", new ItemStack(dust, 1, 15));
	}
	
	public static void addRecipies()
	{
		GameRegistry.addRecipe(new ItemStack(plate, 1, 3) , new Object[]{
			"XXX", "XXX", "XXX", Character.valueOf('X'), Blocks.stone});
		GameRegistry.addRecipe(new ItemStack(stoneRivets, 1) , new Object[]{
			"X X", "   ", "  X", Character.valueOf('X'), Blocks.stone});
		GameRegistry.addRecipe(new ItemStack(itemBulletHead, 1, 0) , new Object[]{
			" X ", "X X", "   ", Character.valueOf('X'), Blocks.stone});
		GameRegistry.addRecipe(new ItemStack(itemCasing, 1, 0) , new Object[]{
			"X X", "X X", " X ", Character.valueOf('X'), Blocks.stone});
		
		GameRegistry.addRecipe(new ItemStack(itemCasing, 1, 2) , new Object[]{
			"PPP","R R","PPP",Character.valueOf('P'), new ItemStack(plate, 1, 1), 'R', brassRivets
		});
		
		GameRegistry.addRecipe(new ItemStack(Items.saddle, 1) , new Object[]{
			"RLR","L L","   ",Character.valueOf('L'), Items.leather, 'R', brassRivets
		});
		
		GameRegistry.addShapelessRecipe(new ItemStack(Items.string, 4), new Object[]{Blocks.wool});
		
		GameRegistry.addRecipe(new ItemStack(pnumaticGun, 1) , new Object[]{
			"IP ", " CI", "IPI", Character.valueOf('I'), new ItemStack(plate, 1, 0), Character.valueOf('P'), new ItemStack(plate, 1, 1), 'C', new ItemStack(itemCasing, 1, 2)
			});

		GameRegistry.addRecipe(new ItemStack(Items.iron_horse_armor, 1) , new Object[]{
			" RP", "PPR", "PPP", Character.valueOf('P'), new ItemStack(plate, 1, 0), 'R', brassRivets
			});
		GameRegistry.addRecipe(new ItemStack(Items.golden_horse_armor, 1) , new Object[]{
			" RP", "PPR", "PPP", Character.valueOf('P'), Items.gold_ingot, 'R', brassRivets
			});
		GameRegistry.addRecipe(new ItemStack(Items.diamond_horse_armor, 1) , new Object[]{
			" RP", "PPR", "PPP", Character.valueOf('P'), Items.diamond, 'R', brassRivets
			});
		
		GameRegistry.addRecipe(new ItemStack(speedyMinecart, 1) , new Object[]{
			"   ", "P R", "PPP", Character.valueOf('P'), ingotBronze
			});
		
		GameRegistry.addShapelessRecipe(new ItemStack(speedyChestcart, 1), new Object[]{Blocks.chest, speedyMinecart});
		MinecraftForge.addGrassSeed(new ItemStack(Items.melon_seeds), 4);
		MinecraftForge.addGrassSeed(new ItemStack(Items.pumpkin_seeds), 4);
		MinecraftForge.addGrassSeed(new ItemStack(Items.carrot), 1);
		MinecraftForge.addGrassSeed(new ItemStack(Items.potato), 1);
		
		GameRegistry.addRecipe(new ItemStack(steamJetpack, 1) , new Object[]{
			"RLB", "C C", "BLR", 'C', new ItemStack(itemCasing, 1, 2), 'L', Items.leather, 'R', new ItemStack(toolPart, 1, 1), 'B', new ItemStack(toolPart, 1, 0)
			});
		
		GameRegistry.addRecipe(new ItemStack(jackHammer, 1) , new Object[]{
			"CPC", "CMC", " B ", 'C', new ItemStack(itemCasing, 1, 2), 'P', new ItemStack(plate, 1, 0), 'M', AdvancedUtilitiesBlocks.bronzeMachineCase, 'B', new ItemStack(toolPart, 1, 0)
			});
		
		GameRegistry.addShapelessRecipe(new ItemStack(bronzeBullet, 32), new Object[]{
			new ItemStack(itemBulletHead, 1, 1), new ItemStack(itemCasing, 1 ,1)
		});
		
		GameRegistry.addSmelting(new ItemStack(dust, 1, 0), new ItemStack(ingotCopper,  1), 1);
		GameRegistry.addSmelting(new ItemStack(dust, 1, 1), new ItemStack(Items.iron_ingot,  1), 1);
		GameRegistry.addSmelting(new ItemStack(dust, 1, 2), new ItemStack(ingotTin,  1), 1);
		GameRegistry.addSmelting(new ItemStack(dust, 1, 3), new ItemStack(ingotZinc,  1), 1);
		GameRegistry.addSmelting(new ItemStack(dust, 1, 4), new ItemStack(ingotBronze,  1), 1);
		GameRegistry.addSmelting(new ItemStack(dust, 1, 5), new ItemStack(ingotBrass,  1), 1);
		GameRegistry.addSmelting(new ItemStack(dust, 1, 6), new ItemStack(Items.gold_ingot,  1), 1);
		GameRegistry.addSmelting(new ItemStack(dust, 1, 7), new ItemStack(Items.diamond,  1), 1);
		
		GameRegistry.addSmelting(new ItemStack(dust, 1, 9), new ItemStack(ingotSilver,  1), 1);
		GameRegistry.addSmelting(new ItemStack(dust, 1, 10), new ItemStack(ingotPlatinum,  1), 1);
		GameRegistry.addSmelting(new ItemStack(dust, 1, 11), new ItemStack(ingotAluminum,  1), 1);
		GameRegistry.addSmelting(new ItemStack(dust, 1, 12), new ItemStack(ingotLead,  1), 1);
		GameRegistry.addSmelting(new ItemStack(dust, 1, 13), new ItemStack(ingotTungsten,  1), 1);
		GameRegistry.addSmelting(new ItemStack(dust, 1, 14), new ItemStack(ingotIridium,  1), 1);
		GameRegistry.addSmelting(new ItemStack(dust, 1, 15), new ItemStack(ingotPalidium,  1), 1);
		
	}
}
