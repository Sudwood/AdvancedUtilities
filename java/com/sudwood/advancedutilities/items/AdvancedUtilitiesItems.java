package com.sudwood.advancedutilities.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;

import com.sudwood.advancedutilities.AdvancedUtilities;
import com.sudwood.advancedutilities.blocks.AdvancedUtilitiesBlocks;
import com.sudwood.advancedutilities.items.minecart.ItemChunkChestCart;
import com.sudwood.advancedutilities.items.minecart.ItemChunkLoadCart;
import com.sudwood.advancedutilities.items.minecart.ItemChunkTankCart;
import com.sudwood.advancedutilities.items.minecart.ItemCrowbar;
import com.sudwood.advancedutilities.items.minecart.ItemSpeedyChestcart;
import com.sudwood.advancedutilities.items.minecart.ItemSpeedyChunkChestCart;
import com.sudwood.advancedutilities.items.minecart.ItemSpeedyChunkTankCart;
import com.sudwood.advancedutilities.items.minecart.ItemSpeedyMinecart;
import com.sudwood.advancedutilities.items.minecart.ItemSpeedyTankCart;
import com.sudwood.advancedutilities.items.minecart.ItemTankCart;

import cpw.mods.fml.common.registry.GameRegistry;

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
	public static Item ingotNickel;
	
	public static Item rubber;
	public static Item glue;
	public static Item wire;
	public static Item toolPart;
	public static Item toolBE;
	public static Item cast;
	public static Item plate;
	public static Item brassRivets;
	public static Item stoneRivets;
	public static Item steelRivets;
	public static Item bronzeHelm;
	public static Item bronzeChest;
	public static Item bronzeLegs;
	public static Item bronzeBoots;
	public static Item dust;
	
	public static Item bag;
	
	public static Item pnumaticGun;
	public static Item bronzeBullet;
	public static Item steamJetpack;
	public static Item rebreather;
	public static Item itemCasing;
	public static Item itemBulletHead;
	public static Item jackHammer;
	public static Item bulletMagazine;
	public static Item upgrade;
	
	public static Item speedyMinecart;
	public static Item speedyChestcart;
	public static Item chunkLoadCart;
	public static Item speedyChunkLoadCart;
	public static Item chunkChestCart;
	public static Item speedyChunkChestCart;
	public static Item tankCart;
	public static Item speedyTankCart;
	public static Item chunkTankCart;
	public static Item speedyChunkTankCart;
	public static Item crowbar;
	public static Item bronzeWrench;
	public static Item runningShoes;
	
	public static Item itemTank;
	
	public static Item quickPotion;
	
	public static Item tomato;
	
	public static Item devTele;
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
		ingotNickel = new ItemIngot(13).setCreativeTab(AdvancedUtilities.advancedTab).setUnlocalizedName("NickelIngot");
		dust = new ItemDust().setUnlocalizedName("Dust");
		
		bag = new ItemBag().setUnlocalizedName("Bag");
		
		rubber = new ItemRubber(0).setUnlocalizedName("Rubber").setCreativeTab(AdvancedUtilities.advancedBEMachinesTab);
		glue = new ItemRubber(1).setUnlocalizedName("Glue").setCreativeTab(AdvancedUtilities.advancedBEMachinesTab);
		wire = new ItemWire().setUnlocalizedName("Wire");
		
		toolPart = new ItemToolPart().setUnlocalizedName("toolPart");
		toolBE = new ItemBETool().setUnlocalizedName("BETool");
		cast = new ItemCast().setUnlocalizedName("Cast");
		plate = new ItemPlate().setUnlocalizedName("Plate");
		brassRivets = new ItemRivets(0).setUnlocalizedName("BrassRivets").setCreativeTab(AdvancedUtilities.advancedTab);
		stoneRivets = new ItemRivets(1).setUnlocalizedName("StoneRivets").setCreativeTab(AdvancedUtilities.advancedTab);
		steelRivets = new ItemRivets(2).setUnlocalizedName("SteelRivets").setCreativeTab(AdvancedUtilities.advancedTab);
		
		ArmorMaterial armorBronze = EnumHelper.addArmorMaterial("BRONZEAU", 40, new int[]{2, 6, 5, 2}, 9);
		bronzeHelm = new ItemArmorBE(armorBronze, 0, 0).setUnlocalizedName("BronzeHelm").setCreativeTab(AdvancedUtilities.advancedBEToolsTab);
		bronzeChest = new ItemArmorBE(armorBronze, 0, 1).setUnlocalizedName("BronzeChest").setCreativeTab(AdvancedUtilities.advancedBEToolsTab);
		bronzeLegs = new ItemArmorBE(armorBronze, 0, 2).setUnlocalizedName("BronzeLegs").setCreativeTab(AdvancedUtilities.advancedBEToolsTab);
		bronzeBoots = new ItemArmorBE(armorBronze, 0, 3).setUnlocalizedName("BronzeBoots").setCreativeTab(AdvancedUtilities.advancedBEToolsTab);
		pnumaticGun = new ItemPnumaticGun().setUnlocalizedName("PnumaticGun").setCreativeTab(AdvancedUtilities.advancedBEToolsTab);
		bronzeBullet = new ItemBulletBE().setUnlocalizedName("BronzeBullet").setCreativeTab(AdvancedUtilities.advancedBEToolsTab);
		itemCasing = new ItemCasing().setUnlocalizedName("Casing");
		itemBulletHead = new ItemBulletHead().setUnlocalizedName("BulletHead");
		jackHammer = new ItemJackHammer().setUnlocalizedName("JackHammer").setCreativeTab(AdvancedUtilities.advancedBEToolsTab);
		bulletMagazine = new ItemMagazine().setUnlocalizedName("BulletMagazine").setCreativeTab(AdvancedUtilities.advancedBEToolsTab);
		upgrade = new ItemUpgrade().setUnlocalizedName("Upgrade");
		
		speedyMinecart = new ItemSpeedyMinecart(0).setUnlocalizedName("SpeedyMinecart").setCreativeTab(AdvancedUtilities.advancedTab);
		speedyChestcart = new ItemSpeedyChestcart(1).setUnlocalizedName("SpeedyChestcart").setCreativeTab(AdvancedUtilities.advancedTab);
		chunkLoadCart = new ItemChunkLoadCart(0, 0).setUnlocalizedName("ChunkLoadCart").setCreativeTab(AdvancedUtilities.advancedTab);
		speedyChunkLoadCart = new ItemChunkLoadCart(0, 1).setUnlocalizedName("SpeedyChunkLoadCart").setCreativeTab(AdvancedUtilities.advancedTab);
		chunkChestCart  = new ItemChunkChestCart(0).setUnlocalizedName("ChunkChestCart").setCreativeTab(AdvancedUtilities.advancedTab);
		speedyChunkChestCart  = new ItemSpeedyChunkChestCart(0).setUnlocalizedName("SpeedyChunkChestCart").setCreativeTab(AdvancedUtilities.advancedTab);
		tankCart = new ItemTankCart(0).setUnlocalizedName("TankCart").setCreativeTab(AdvancedUtilities.advancedTab);
		speedyTankCart = new ItemSpeedyTankCart(0).setUnlocalizedName("SpeedyTankCart").setCreativeTab(AdvancedUtilities.advancedTab);
		chunkTankCart = new ItemChunkTankCart(0).setUnlocalizedName("ChunkTankCart").setCreativeTab(AdvancedUtilities.advancedTab);
		speedyChunkTankCart = new ItemSpeedyChunkTankCart(0).setUnlocalizedName("SpeedyChunkTankCart").setCreativeTab(AdvancedUtilities.advancedTab);
		
		crowbar = new ItemCrowbar().setUnlocalizedName("Crowbar").setCreativeTab(AdvancedUtilities.advancedTab);
		bronzeWrench = new ItemWrench().setUnlocalizedName("BronzeWrench").setCreativeTab(AdvancedUtilities.advancedTab).setMaxDamage(1500).setMaxStackSize(1);
		
		quickPotion = new ItemQuickPotion(0, 0, false).setUnlocalizedName("QuickPotion").setCreativeTab(AdvancedUtilities.advancedTab);
		
		tomato = new ItemTomato(6, 6, false).setUnlocalizedName("Tomato").setCreativeTab(AdvancedUtilities.advancedTab).setCreativeTab(CreativeTabs.tabFood);
		
		ArmorMaterial armorSteamJetpack = EnumHelper.addArmorMaterial("SJETPACKAU", 0, new int[]{0, 0, 0, 0}, 10);
		steamJetpack = new ItemArmorSteamJetpack(armorSteamJetpack, 0, 1).setUnlocalizedName("SteamJetpack").setCreativeTab(AdvancedUtilities.advancedBEToolsTab);
		runningShoes = new ItemRunningShoes(armorSteamJetpack, 0, 3).setUnlocalizedName("RunningShoes").setCreativeTab(AdvancedUtilities.advancedBEToolsTab);
		rebreather = new ItemArmorRebreather(armorSteamJetpack, 0, 0).setUnlocalizedName("Rebreather").setCreativeTab(AdvancedUtilities.advancedBEToolsTab);
		
		devTele = new ItemDevTool().setUnlocalizedName("DevTele").setCreativeTab(AdvancedUtilities.advancedTab);
		
		registerItems();
	}
	
	public static void registerItems()
	{
		GameRegistry.registerItem(ingotCopper, "ingotcopper");
		GameRegistry.registerItem(ingotTin, "ingottin");
		GameRegistry.registerItem(ingotBronze, "ingotbronze");
		GameRegistry.registerItem(ingotZinc, "ingotzinc");
		GameRegistry.registerItem(ingotBrass, "ingotbrass");
		GameRegistry.registerItem(ingotSilver, "ingotsilver");
		GameRegistry.registerItem(ingotLead, "ingotlead");
		GameRegistry.registerItem(ingotAluminum, "ingotaluminum");
		GameRegistry.registerItem(ingotTungsten, "ingottungsten");
		GameRegistry.registerItem(ingotPlatinum, "ingotplatinum");
		GameRegistry.registerItem(ingotIridium, "ingotiridium");
		GameRegistry.registerItem(ingotPalidium, "ingotpalidium");
		GameRegistry.registerItem(ingotSteel, "ingotsteel");
		GameRegistry.registerItem(ingotNickel, "ingotnickel");
		
		GameRegistry.registerItem(dust, "dust");
		
		GameRegistry.registerItem(rubber, "rubber");
		GameRegistry.registerItem(glue, "glue");
		GameRegistry.registerItem(wire, "Wire");
		
		GameRegistry.registerItem(bag, "bag");
		
		GameRegistry.registerItem(toolPart, "toolpart");
		GameRegistry.registerItem(toolBE, "betool");
		GameRegistry.registerItem(cast, "cast");
		GameRegistry.registerItem(plate, "plate");
		GameRegistry.registerItem(brassRivets, "brassfittings");
		GameRegistry.registerItem(stoneRivets, "stonefittings");
		GameRegistry.registerItem(steelRivets, "steelrivets");
		
		GameRegistry.registerItem(bronzeHelm, "bronzehelm");
		GameRegistry.registerItem(bronzeChest, "bronzechest");
		GameRegistry.registerItem(bronzeLegs, "bronzelegs");
		GameRegistry.registerItem(bronzeBoots, "bronzeboots");
		GameRegistry.registerItem(pnumaticGun, "pnumaticgun");
		GameRegistry.registerItem(bronzeBullet, "bronzebullet");
		GameRegistry.registerItem(steamJetpack, "steamjetpack");
		GameRegistry.registerItem(rebreather, "rebreather");
		GameRegistry.registerItem(runningShoes, "runningshoes");
		GameRegistry.registerItem(itemCasing, "itemcasing");
		GameRegistry.registerItem(itemBulletHead, "itembullethead");
		GameRegistry.registerItem(jackHammer, "jackhammer");
		GameRegistry.registerItem(bulletMagazine, "bulletmagazine");
		GameRegistry.registerItem(upgrade, "upgrade");
		
		GameRegistry.registerItem(speedyMinecart, "speedyminecart");
		GameRegistry.registerItem(speedyChestcart, "speedychestcart");
		GameRegistry.registerItem(chunkLoadCart, "chunkLoadCart");
		GameRegistry.registerItem(speedyChunkLoadCart, "speedychunkLoadCart");
		GameRegistry.registerItem(chunkChestCart, "chunkchestcart");
		GameRegistry.registerItem(speedyChunkChestCart, "speedychunkchestcart");
		GameRegistry.registerItem(tankCart, "tankcart");
		GameRegistry.registerItem(speedyTankCart, "speedytankcart");
		GameRegistry.registerItem(chunkTankCart, "chunktankcart");
		GameRegistry.registerItem(speedyChunkTankCart, "speedychunktankcart");
		
		GameRegistry.registerItem(crowbar, "crowbar");
		GameRegistry.registerItem(bronzeWrench, "bronzewrench");
		
		GameRegistry.registerItem(quickPotion, "quickhealth");
		
		GameRegistry.registerItem(tomato, "tomato");
		
		GameRegistry.registerItem(devTele, "devTele");
		
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
		OreDictionary.registerOre("ingotNickel", ingotNickel);
		
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
		OreDictionary.registerOre("dustNickel", new ItemStack(dust, 1, 20));
		OreDictionary.registerOre("materialRubber", rubber);
		OreDictionary.registerOre("itemRubber", rubber);
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
		GameRegistry.addRecipe(new ItemStack(toolPart, 1, 10) , new Object[]{
			"XXX", "X X", "   ", Character.valueOf('X'), Blocks.stone});
		
		GameRegistry.addRecipe(new ItemStack(itemCasing, 1, 2) , new Object[]{
			"PPP","R R","PPP",Character.valueOf('P'), new ItemStack(plate, 1, 1), 'R', brassRivets
		});
		addUpgradeRecipes();
		GameRegistry.addRecipe(new ItemStack(runningShoes, 1) , new Object[]{
			"LS ","RLS","RLL", 'R', brassRivets, 'S', Items.string, 'L', Items.leather
		});
		GameRegistry.addRecipe(new ItemStack(rebreather, 1) , new Object[]{
			" H ","TGT"," I ", 'H', Items.leather_helmet, 'T', new ItemStack(itemCasing, 1, 2) , 'G', Blocks.glass, 'I', Items.iron_ingot
		});
		
		GameRegistry.addRecipe(new ItemStack(bulletMagazine, 1) , new Object[]{
			"PRP","P P","PRP",Character.valueOf('P'), new ItemStack(plate, 1, 0), 'R', brassRivets
		});
		
		addPotionRecipes();
		
		GameRegistry.addRecipe(new ItemStack(crowbar, 1) , new Object[]{
			"  I"," I ","I  ",'I', ingotBronze
		});
		GameRegistry.addRecipe(new ItemStack(crowbar, 1) , new Object[]{
			"I  "," I ","  I",'I', ingotBronze
		});
		
		GameRegistry.addRecipe(new ItemStack(bronzeWrench, 1) , new Object[]{
			"I I"," I "," I ",'I', ingotBronze
		});
		
		GameRegistry.addRecipe(new ItemStack(Items.saddle, 1) , new Object[]{
			"RLR","L L","   ",Character.valueOf('L'), Items.leather, 'R', brassRivets
		});
		
		GameRegistry.addShapelessRecipe(new ItemStack(Items.string, 4), new Object[]{new ItemStack(Blocks.wool, 1, OreDictionary.WILDCARD_VALUE)});
		GameRegistry.addShapelessRecipe(new ItemStack(glue, 1), new Object[]{new ItemStack(Items.water_bucket, 1), new ItemStack(dust, 1, 18)});
		GameRegistry.addShapelessRecipe(new ItemStack(Items.book, 1), new Object[]{new ItemStack(Items.paper, 1),new ItemStack(Items.paper, 1),new ItemStack(Items.paper, 1), new ItemStack(glue, 1)});
		GameRegistry.addShapelessRecipe(new ItemStack(Items.paper, 1), new Object[]{new ItemStack(dust, 1, 19),new ItemStack(dust, 1, 19),new ItemStack(dust, 1, 19), new ItemStack(Items.water_bucket, 1)});
		GameRegistry.addShapelessRecipe(new ItemStack(Items.paper, 3), new Object[]{new ItemStack(dust, 1, 19),new ItemStack(dust, 1, 19),new ItemStack(dust, 1, 19),new ItemStack(dust, 1, 19),new ItemStack(dust, 1, 19),new ItemStack(dust, 1, 19),new ItemStack(dust, 1, 19),new ItemStack(dust, 1, 19), new ItemStack(Items.water_bucket, 1)});
		
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
			"   ", "P P", "PPP", Character.valueOf('P'), ingotBronze
			});
		
		MinecraftForge.addGrassSeed(new ItemStack(Items.melon_seeds), 4);
		MinecraftForge.addGrassSeed(new ItemStack(Items.pumpkin_seeds), 4);
		MinecraftForge.addGrassSeed(new ItemStack(Items.carrot), 1);
		MinecraftForge.addGrassSeed(new ItemStack(Items.potato), 1);
		for(int i = 0; i < 16; i++)
		{
			GameRegistry.addRecipe(new ItemStack(bag, 1, i) , new Object[]{
				"LWL", "W W", "LWL", 'L', Items.leather, 'W', new ItemStack(Blocks.wool, 1, i)
				});
		}
		GameRegistry.addRecipe(new ItemStack(steamJetpack, 1) , new Object[]{
			"RLB", "C C", "BLR", 'C', new ItemStack(itemCasing, 1, 2), 'L', Items.leather, 'R', new ItemStack(toolPart, 1, 1), 'B', new ItemStack(toolPart, 1, 0)
			});
		GameRegistry.addShapelessRecipe(new ItemStack(speedyChestcart, 1), new Object[]{Blocks.chest, speedyMinecart});
		GameRegistry.addShapelessRecipe(new ItemStack(chunkLoadCart, 1), new Object[]{AdvancedUtilitiesBlocks.chunkLoader, Items.minecart});
		GameRegistry.addShapelessRecipe(new ItemStack(speedyChunkLoadCart, 1), new Object[]{AdvancedUtilitiesBlocks.chunkLoader, speedyMinecart});
		GameRegistry.addShapelessRecipe(new ItemStack(chunkChestCart, 1), new Object[]{Blocks.chest, Items.minecart, AdvancedUtilitiesBlocks.chunkLoader});
		GameRegistry.addShapelessRecipe(new ItemStack(speedyChunkChestCart, 1), new Object[]{Blocks.chest, speedyMinecart, AdvancedUtilitiesBlocks.chunkLoader});
		GameRegistry.addShapelessRecipe(new ItemStack(tankCart, 1), new Object[]{AdvancedUtilitiesBlocks.blockTank, Items.minecart});
		GameRegistry.addShapelessRecipe(new ItemStack(speedyTankCart, 1), new Object[]{AdvancedUtilitiesBlocks.blockTank, speedyMinecart});
		GameRegistry.addShapelessRecipe(new ItemStack(chunkTankCart, 1), new Object[]{AdvancedUtilitiesBlocks.blockTank, Items.minecart, AdvancedUtilitiesBlocks.chunkLoader});
		GameRegistry.addShapelessRecipe(new ItemStack(speedyChunkTankCart, 1), new Object[]{AdvancedUtilitiesBlocks.blockTank, speedyMinecart, AdvancedUtilitiesBlocks.chunkLoader});
		
		GameRegistry.addRecipe(new ItemStack(jackHammer, 1) , new Object[]{
			"CPC", "CMC", " B ", 'C', new ItemStack(itemCasing, 1, 2), 'P', new ItemStack(plate, 1, 0), 'M', AdvancedUtilitiesBlocks.bronzeMachineCase, 'B', new ItemStack(toolPart, 1, 0)
			});
		
		GameRegistry.addShapelessRecipe(new ItemStack(bronzeBullet, 1), new Object[]{
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
		GameRegistry.addSmelting(new ItemStack(dust, 1, 18), new ItemStack(Items.bread,  1), 1);
		GameRegistry.addSmelting(new ItemStack(dust, 1, 20), new ItemStack(ingotNickel,  1), 1);
		
		GameRegistry.addSmelting(new ItemStack(AdvancedUtilitiesBlocks.blockRubberLog,  1), new ItemStack(rubber, 2), 1);
		
		GameRegistry.addShapelessRecipe(new ItemStack(dust, 9, 17), new Object[]{
			new ItemStack(dust, 1, 7)
		});
		GameRegistry.addShapelessRecipe(new ItemStack(dust, 1, 7), new Object[]{
			new ItemStack(dust, 1, 17), new ItemStack(dust, 1, 17), new ItemStack(dust, 1, 17), new ItemStack(dust, 1, 17), new ItemStack(dust, 1, 17), new ItemStack(dust, 1, 17),new ItemStack(dust, 1, 17), new ItemStack(dust, 1, 17), new ItemStack(dust, 1, 17)
		});
		
	}
	
	public static void addUpgradeRecipes()
	{
		GameRegistry.addRecipe(new ItemStack(upgrade, 1, 0) , new Object[]{
			" I ","WWW"," I ",'I', Items.iron_ingot, 'W',Items.water_bucket
		});
		GameRegistry.addRecipe(new ItemStack(upgrade, 1, 1) , new Object[]{
			"RRR","RRR","RRR",'R', Blocks.redstone_block
		});
		GameRegistry.addRecipe(new ItemStack(upgrade, 1, 2) , new Object[]{
			" G ","UUU"," G ",'G', Items.gold_ingot, 'U', new ItemStack(upgrade, 1, 1)
		});
		GameRegistry.addRecipe(new ItemStack(upgrade, 1, 3) , new Object[]{
			"DDD","DUD","DDD",'D', Items.diamond, 'U', new ItemStack(upgrade, 1, 2)
		});
		GameRegistry.addRecipe(new ItemStack(upgrade, 1, 4) , new Object[]{
			"RRR","RRR","RRR",'R', AdvancedUtilitiesBlocks.blockAluminumBlock
		});
		GameRegistry.addRecipe(new ItemStack(upgrade, 1, 5) , new Object[]{
			"WWW","UUU","WWW",'W', new ItemStack(Blocks.wool, 1, OreDictionary.WILDCARD_VALUE), 'U', new ItemStack(upgrade, 1, 4)
		});
		GameRegistry.addRecipe(new ItemStack(upgrade, 1, 6) , new Object[]{
			"DDD","DUD","DDD",'D', Items.diamond, 'U', new ItemStack(upgrade, 1, 5)
		});
		GameRegistry.addRecipe(new ItemStack(upgrade, 1, 7) , new Object[]{
			"RRR","RRR","RRR",'R', Blocks.lapis_block
		});
		GameRegistry.addRecipe(new ItemStack(upgrade, 1, 8) , new Object[]{
			"WWW","UUU","WWW",'W', Blocks.lapis_block, 'U', new ItemStack(upgrade, 1, 7)
		});
		GameRegistry.addRecipe(new ItemStack(upgrade, 1, 9) , new Object[]{
			"DDD","DUD","DDD",'D', Items.diamond, 'U', new ItemStack(upgrade, 1, 8)
		});
		GameRegistry.addRecipe(new ItemStack(upgrade, 1, 10) , new Object[]{
			"RRR","RRR","RRR",'R', AdvancedUtilitiesBlocks.blockBronzeBlock
		});
		GameRegistry.addRecipe(new ItemStack(upgrade, 1, 10) , new Object[]{
			"RRR","RRR","RRR",'R', Blocks.iron_block
		});
		GameRegistry.addRecipe(new ItemStack(upgrade, 1, 11) , new Object[]{
			" I ","UUU"," I ",'I', Blocks.iron_block, 'U', new ItemStack(upgrade, 1, 10)
		});
		GameRegistry.addRecipe(new ItemStack(upgrade, 1, 11) , new Object[]{
			" I ","UUU"," I ",'I', AdvancedUtilitiesBlocks.blockBronzeBlock, 'U', new ItemStack(upgrade, 1, 10)
		});
		GameRegistry.addRecipe(new ItemStack(upgrade, 1, 12) , new Object[]{
			"DDD","DUD","DDD",'D', Items.diamond, 'U', new ItemStack(upgrade, 1, 11)
		});
		GameRegistry.addRecipe(new ItemStack(upgrade, 1, 13) , new Object[]{
			"RRR","RRR","RRR",'R', Blocks.quartz_block
		});
		GameRegistry.addRecipe(new ItemStack(upgrade, 1, 14) , new Object[]{
			"WWW","UUU","WWW",'W', Blocks.quartz_block, 'U', new ItemStack(upgrade, 1, 13)
		});
		GameRegistry.addRecipe(new ItemStack(upgrade, 1, 15) , new Object[]{
			"DDD","DUD","DDD",'D', Items.diamond, 'U', new ItemStack(upgrade, 1, 14)
		});
	}
	
	public static void addPotionRecipes()
	{
		GameRegistry.addShapelessRecipe(new ItemStack(quickPotion, 1, 2), new Object[]{
			new ItemStack(Items.potionitem ,1 , 0)
		});
		GameRegistry.addShapelessRecipe(new ItemStack(quickPotion, 1, 3), new Object[]{
			new ItemStack(Items.potionitem ,1 , 16)
		});
		GameRegistry.addShapelessRecipe(new ItemStack(Items.potionitem ,1 , 0), new Object[]{
			new ItemStack(quickPotion, 1, 2)
		});
		GameRegistry.addShapelessRecipe(new ItemStack(Items.potionitem ,1 , 16), new Object[]{
			new ItemStack(quickPotion, 1, 3)
		});
		GameRegistry.addShapelessRecipe(new ItemStack(quickPotion, 1, 3), new Object[]{
			new ItemStack(quickPotion, 1, 2), Items.nether_wart
		});
		GameRegistry.addShapelessRecipe(new ItemStack(quickPotion, 1, 4), new Object[]{
			new ItemStack(quickPotion, 1, 3), new ItemStack(dust, 1, 17)
		});
		GameRegistry.addShapelessRecipe(new ItemStack(quickPotion, 1, 4), new Object[]{
			new ItemStack(Items.potionitem, 1, 16), new ItemStack(dust, 1, 17)
		});
		GameRegistry.addShapelessRecipe(new ItemStack(quickPotion, 1, 0), new Object[]{
			new ItemStack(Items.potionitem, 1, 8261), new ItemStack(dust, 1, 17)
		});
		GameRegistry.addShapelessRecipe(new ItemStack(quickPotion, 1, 0), new Object[]{
			new ItemStack(quickPotion, 1, 4), new ItemStack(Items.speckled_melon, 1)
		});
		GameRegistry.addShapelessRecipe(new ItemStack(quickPotion, 1, 1), new Object[]{
			new ItemStack(Items.potionitem, 1, 8229), new ItemStack(dust, 1, 17)
		});
		GameRegistry.addShapelessRecipe(new ItemStack(quickPotion, 1, 1), new Object[]{
			new ItemStack(quickPotion, 1, 0), Items.glowstone_dust
		});
	}
	public void addMachineryRecipes()
	{
		
	}

}
