package com.sudwood.advancedutilities.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;

import com.sudwood.advancedutilities.AdvancedUtilities;
import com.sudwood.advancedutilities.fluids.BlockFluidSteam;
import com.sudwood.advancedutilities.items.AdvancedUtilitiesItems;
import com.sudwood.advancedutilities.items.ItemTank;

import cpw.mods.fml.common.registry.GameRegistry;

public class AdvancedUtilitiesBlocks 
{
    public static Block chunkLoader;
    public static Block blockCopperOre;
    public static Block blockTinOre;
    public static Block blockZincOre;
    public static Block blockSilverOre;
    public static Block blockLeadOre;
    public static Block blockBauxiteOre;
    public static Block blockTungstenOre;
    public static Block blockPlatinumOre;
    public static Block blockNickelOre;
    
    public static Block blockCopperBlock;
    public static Block blockTinBlock;
    public static Block blockBronzeBlock;
    public static Block blockZincBlock;
    public static Block blockBrassBlock;
    public static Block blockSilverBlock;
    public static Block blockLeadBlock;
    public static Block blockAluminumBlock;
    public static Block blockTungstenBlock;
    public static Block blockPlatinumBlock;
    public static Block blockIridiumBlock;
    public static Block blockPalidiumBlock;
    public static Block blockSteelBlock;
    public static Block blockNickelBlock;
    
    public static Block blockRubberLog;
    public static Block blockRubberLeaves;
    public static Block blockRubberPlanks;
    public static Block blockRubberSapling;
    
    public static Block blockKiln;
    public static Block blockSmeltry;
    public static Block blockToolForge;
    public static Block blockArmorForge;
    
    public static Fluid fluidSteam;
    public static Block blockFluidSteam;
    public static Block steamBoiler;
    public static Block bronzeMachineCase;
    public static Block steamCrusher;
    public static Block steamFurnace;
    public static Block steamSmeltry;
    public static Block bellows;
    public static Block steamCompressor;
    public static Block itemTube;
    public static Block restrictedItemTube;
    public static Block steamCharger;
    public static Block fluidTube;
    public static Block blockTank;
    
    public static Block blockBreaker;
    public static Block blockSpeedyGrowing;
    public static Block tomatoPlant;
    
    public static Block dummyChunkChest;
    public static Block dummyTank;
    public static Block dummyChunkTank;

    
	public static void init()
	{
		chunkLoader = new BlockChunkLoader(Material.circuits, 0).setBlockName("ChunkLoader").setHardness(20F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedTab);
		dummyChunkChest = new BlockChunkLoader(Material.circuits, 1).setBlockName("DummyChunkChest").setHardness(20F).setResistance(100F);
		dummyTank = new BlockChunkLoader(Material.circuits, 2).setBlockName("DummyTank").setHardness(20F).setResistance(100F);
		dummyChunkTank = new BlockChunkLoader(Material.circuits, 3).setBlockName("DummyChunkTank").setHardness(20F).setResistance(100F);
		
		blockCopperOre = new BlockOre(Material.rock, 0).setBlockName("CopperOre").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedTab);
		blockTinOre = new BlockOre(Material.rock, 1).setBlockName("TinOre").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedTab);
		blockZincOre = new BlockOre(Material.rock, 2).setBlockName("ZincOre").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedTab);
		blockSilverOre = new BlockOre(Material.rock, 3).setBlockName("SilverOre").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedTab);
		blockLeadOre = new BlockOre(Material.rock, 4).setBlockName("LeadOre").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedTab);
		blockBauxiteOre = new BlockOre(Material.rock, 5).setBlockName("BauxiteOre").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedTab);
		blockTungstenOre = new BlockOre(Material.rock, 6).setBlockName("TungstenOre").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedTab);
		blockPlatinumOre = new BlockOre(Material.rock, 7).setBlockName("PlatinumOre").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedTab);
		blockNickelOre = new BlockOre(Material.rock, 9).setBlockName("NickelOre").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedTab);
		
		blockCopperBlock = new BlockIngotBlock(Material.iron, 0).setBlockName("CopperBlock").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedTab);
		blockTinBlock = new BlockIngotBlock(Material.iron, 1).setBlockName("TinBlock").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedTab);
		blockBronzeBlock = new BlockIngotBlock(Material.iron, 2).setBlockName("BronzeBlock").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedTab);
		blockZincBlock = new BlockIngotBlock(Material.iron, 3).setBlockName("ZincBlock").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedTab);
		blockBrassBlock = new BlockIngotBlock(Material.iron, 4).setBlockName("BrassBlock").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedTab);
		blockSilverBlock = new BlockIngotBlock(Material.iron, 6).setBlockName("SilverBlock").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedTab);
		blockLeadBlock = new BlockIngotBlock(Material.iron, 7).setBlockName("LeadBlock").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedTab);
		blockAluminumBlock = new BlockIngotBlock(Material.iron, 8).setBlockName("AluminumBlock").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedTab);
		blockTungstenBlock = new BlockIngotBlock(Material.iron, 9).setBlockName("TungstenBlock").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedTab);
		blockPlatinumBlock = new BlockIngotBlock(Material.iron, 10).setBlockName("PlatinumBlock").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedTab);
		blockIridiumBlock = new BlockIngotBlock(Material.iron, 11).setBlockName("IridiumBlock").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedTab);
		blockPalidiumBlock = new BlockIngotBlock(Material.iron, 12).setBlockName("PalidiumBlock").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedTab);
		blockSteelBlock = new BlockIngotBlock(Material.iron, 13).setBlockName("SteelBlock").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedTab);
		blockNickelBlock = new BlockIngotBlock(Material.iron, 14).setBlockName("NickelBlock").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedTab);
		
		blockRubberLog = new BlockWood(Material.wood, 0).setBlockName("RubberLog").setHardness(2F).setStepSound(Block.soundTypeWood).setCreativeTab(AdvancedUtilities.advancedTab);
		blockRubberPlanks = new BlockWood(Material.wood, 1).setBlockName("RubberPlanks").setHardness(2F).setStepSound(Block.soundTypeWood).setCreativeTab(AdvancedUtilities.advancedTab);
		blockRubberSapling = new BlockRubberSapling().setBlockName("RubberSapling").setStepSound(Block.soundTypeGrass).setCreativeTab(AdvancedUtilities.advancedTab);
		blockRubberLeaves = new BlockAdvancedLeaf().setBlockName("RubberLeaves").setStepSound(Block.soundTypeGrass).setCreativeTab(AdvancedUtilities.advancedTab);
		
		blockKiln = new BlockKiln(Material.rock).setBlockName("Kiln").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedTab);
		blockSmeltry = new BlockSmeltry(Material.rock).setBlockName("Smeltry").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedTab);
		blockToolForge = new BlockToolForge(Material.anvil).setBlockName("ToolForge").setHardness(2F).setStepSound(Block.soundTypeAnvil).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedTab);
		blockArmorForge = new BlockArmorForge(Material.anvil).setBlockName("ArmorForge").setHardness(2F).setStepSound(Block.soundTypeAnvil).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedTab);
		
		bronzeMachineCase = new BlockIngotBlock(Material.iron, 5).setBlockName("BronzeMachine").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedBEMachinesTab);
		steamBoiler = new BlockSteamMachine(Material.iron, 0).setBlockName("SteamBoiler").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedBEMachinesTab);
		steamCrusher = new BlockSteamMachine(Material.iron, 1).setBlockName("SteamCrusher").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedBEMachinesTab);
		steamFurnace = new BlockSteamMachine(Material.iron, 2).setBlockName("SteamFurnace").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedBEMachinesTab);
		steamSmeltry = new BlockSteamMachine(Material.iron, 3).setBlockName("SteamSmeltry").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedBEMachinesTab);
		bellows = new BlockSteamMachine(Material.iron, 4).setBlockName("Bellows").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedBEMachinesTab);
		steamCompressor = new BlockSteamMachine(Material.iron, 5).setBlockName("SteamCompressor").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedBEMachinesTab);
		itemTube = new BlockTube(Material.iron, 0).setBlockName("ItemTube").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedTab);
		restrictedItemTube = new BlockTube(Material.iron, 2).setBlockName("RestrictedItemTube").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedTab);
		steamCharger = new BlockSteamMachine(Material.iron, 6).setBlockName("SteamCharger").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedBEMachinesTab);
		fluidTube = new BlockTube(Material.iron, 1).setBlockName("FluidTube").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedTab);
		
		blockBreaker = new BlockBlockBreaker(Material.piston).setBlockName("BlockBreaker").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedTab);
		blockSpeedyGrowing = new BlockSpeedyGrowing(Material.clay).setBlockName("SpeedyGrowing").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedTab);
		tomatoPlant = new BlockTomatoPlant(Material.plants).setBlockName("TomatoPlant").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedTab);
		
		fluidSteam = new Fluid(AdvancedUtilities.steamName).setDensity(103).setGaseous(true).setTemperature(373).setViscosity(108).setUnlocalizedName("Steam");
    	FluidRegistry.registerFluid(fluidSteam);
    	blockFluidSteam = new BlockFluidSteam(fluidSteam, Material.water).setBlockName("BlockFluidSteam");
		GameRegistry.registerBlock(blockFluidSteam, "blockfluidsteam");
		fluidSteam.setBlock(blockFluidSteam);
		blockTank = new BlockTank(Material.rock).setBlockName("BlockTank").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedTab);
		registerBlocks();
	}
	
	public static void registerBlocks()
	{
		GameRegistry.registerBlock(chunkLoader, "chunkloader");
		GameRegistry.registerBlock(dummyChunkChest, "dummyChunkChest");
		GameRegistry.registerBlock(dummyTank, "dummyTank");
		GameRegistry.registerBlock(dummyChunkTank, "dummyChunkTank");
		
		GameRegistry.registerBlock(blockCopperOre, "coperore");
		GameRegistry.registerBlock(blockTinOre, "tinore");
		GameRegistry.registerBlock(blockZincOre, "zincore");
		GameRegistry.registerBlock(blockSilverOre, "silverore");
		GameRegistry.registerBlock(blockLeadOre, "leadore");
		GameRegistry.registerBlock(blockBauxiteOre, "bauxiteore");
		GameRegistry.registerBlock(blockTungstenOre, "tungstenore");
		GameRegistry.registerBlock(blockPlatinumOre, "platinumore");
		GameRegistry.registerBlock(blockNickelOre, "nickelore");
		
		GameRegistry.registerBlock(blockCopperBlock, "coperblock");
		GameRegistry.registerBlock(blockTinBlock, "tinblock");
		GameRegistry.registerBlock(blockBronzeBlock, "bronzeblock");
		GameRegistry.registerBlock(blockZincBlock, "zincblock");
		GameRegistry.registerBlock(blockBrassBlock, "brassblock");
		GameRegistry.registerBlock(blockSilverBlock, "silverblock");
		GameRegistry.registerBlock(blockLeadBlock, "leadblock");
		GameRegistry.registerBlock(blockAluminumBlock, "aluminumblock");
		GameRegistry.registerBlock(blockTungstenBlock, "tungstenblock");
		GameRegistry.registerBlock(blockPlatinumBlock, "platinumblock");
		GameRegistry.registerBlock(blockIridiumBlock, "iridiumblock");
		GameRegistry.registerBlock(blockPalidiumBlock, "palidiumblock");
		GameRegistry.registerBlock(blockSteelBlock, "steelblock");
		GameRegistry.registerBlock(blockNickelBlock, "nickelblock");
		
		GameRegistry.registerBlock(blockRubberLog, "rubberlog");
		GameRegistry.registerBlock(blockRubberPlanks, "rubberplanks");
		GameRegistry.registerBlock(blockRubberSapling, "rubbersapling");
		GameRegistry.registerBlock(blockRubberLeaves, "rubberleaves");
		
		GameRegistry.registerBlock(blockKiln, "kiln");
		GameRegistry.registerBlock(blockSmeltry, "smeltry");
		GameRegistry.registerBlock(blockToolForge, "toolforge");
		GameRegistry.registerBlock(blockArmorForge, "armorforge");
		
		GameRegistry.registerBlock(steamBoiler, "steamboiler");
		GameRegistry.registerBlock(steamCrusher, "steamcrusher");
		GameRegistry.registerBlock(steamFurnace, "steamfurnace");
		GameRegistry.registerBlock(steamSmeltry, "steamsmeltry");
		GameRegistry.registerBlock(steamCompressor, "steamcompressor");
		GameRegistry.registerBlock(steamCharger, "steamcharger");
		GameRegistry.registerBlock(itemTube, "itemtube");
		GameRegistry.registerBlock(restrictedItemTube, "restricteditemtube");
		GameRegistry.registerBlock(fluidTube, "fluidtube");
		GameRegistry.registerBlock(bellows, "bellows");
		GameRegistry.registerBlock(bronzeMachineCase, "bronzemachinecase");
		GameRegistry.registerBlock(blockTank, ItemTank.class, "blocktank");
		
		GameRegistry.registerBlock(blockBreaker, "blockbreaker");
		GameRegistry.registerBlock(blockSpeedyGrowing, "speedygrowing");
		GameRegistry.registerBlock(tomatoPlant, "tomatoplant");
		
		
		OreDictionary.registerOre("treeWood", blockRubberLog);
		OreDictionary.registerOre("plankWood", blockRubberPlanks);
		OreDictionary.registerOre("treeLeaves", blockRubberLeaves);
		OreDictionary.registerOre("rubberWood", blockRubberLog);

		OreDictionary.registerOre("oreCopper", blockCopperOre);
		OreDictionary.registerOre("oreTin", blockTinOre);
		OreDictionary.registerOre("oreZinc", blockZincOre);
		OreDictionary.registerOre("oreCoal", Blocks.coal_ore);
		OreDictionary.registerOre("oreSilver", blockSilverOre);
		OreDictionary.registerOre("oreLead", blockLeadOre);
		OreDictionary.registerOre("oreBauxite", blockBauxiteOre);
		OreDictionary.registerOre("oreAluminum", blockBauxiteOre);
		OreDictionary.registerOre("oreTungsten", blockTungstenOre);
		OreDictionary.registerOre("orePlatinum", blockPlatinumOre);
		OreDictionary.registerOre("oreNickel", blockNickelOre);
		
		
		OreDictionary.registerOre("blockCopper", blockCopperBlock);
		OreDictionary.registerOre("blockTin", blockTinBlock);
		OreDictionary.registerOre("blockBronze", blockBronzeBlock);
		OreDictionary.registerOre("blockZinc", blockZincBlock);
		OreDictionary.registerOre("blockBrass", blockBrassBlock);
		OreDictionary.registerOre("blockSilver", blockSilverBlock);
		OreDictionary.registerOre("blockLead", blockLeadBlock);
		OreDictionary.registerOre("blockAluminum", blockAluminumBlock);
		OreDictionary.registerOre("blockTungsten", blockTungstenBlock);
		OreDictionary.registerOre("blockPlatinum", blockPlatinumBlock);
		OreDictionary.registerOre("blockIridium", blockIridiumBlock);
		OreDictionary.registerOre("blockPalidium", blockPalidiumBlock);
		OreDictionary.registerOre("blockSteel", blockSteelBlock);
		OreDictionary.registerOre("blockNickel", blockNickelBlock);
		
		
		
	}
	
	public static void addRecipies()
	{
		GameRegistry.addSmelting(blockCopperOre, new ItemStack(AdvancedUtilitiesItems.ingotCopper, 1),1);
		GameRegistry.addSmelting(blockTinOre, new ItemStack(AdvancedUtilitiesItems.ingotTin, 1),1);
		GameRegistry.addSmelting(blockZincOre, new ItemStack(AdvancedUtilitiesItems.ingotZinc, 1), 1);
		GameRegistry.addSmelting(blockSilverOre, new ItemStack(AdvancedUtilitiesItems.ingotSilver, 1),1);
		GameRegistry.addSmelting(blockLeadOre, new ItemStack(AdvancedUtilitiesItems.ingotLead, 1),1);
		GameRegistry.addSmelting(blockBauxiteOre, new ItemStack(AdvancedUtilitiesItems.ingotAluminum, 1), 1);
		GameRegistry.addSmelting(blockTungstenOre, new ItemStack(AdvancedUtilitiesItems.ingotTungsten, 1),1);
		GameRegistry.addSmelting(blockPlatinumOre, new ItemStack(AdvancedUtilitiesItems.ingotPlatinum, 1),1);
		GameRegistry.addSmelting(blockNickelOre, new ItemStack(AdvancedUtilitiesItems.ingotNickel, 1),1);
		
		GameRegistry.addRecipe(new ItemStack(chunkLoader, 1), new Object[]{
			"CRC", "RDR", "CRC", 'C', Blocks.coal_block, 'R', Blocks.redstone_block, 'D', Items.diamond
		});
		
		GameRegistry.addRecipe(new ItemStack(Blocks.packed_ice, 9), new Object[]{
			"III", "III", "III", 'I', Blocks.ice
		});
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.mossy_cobblestone, 1), new Object[]{Blocks.cobblestone, Items.wheat, Items.water_bucket});
		
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.mycelium, 1), new Object[]{Blocks.dirt, Blocks.red_mushroom});
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.mycelium, 1), new Object[]{Blocks.dirt, Blocks.brown_mushroom});
		
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.sapling, 1, 5), new Object[]{Blocks.sapling,  new ItemStack(Items.dye, 1, 0)});
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.sapling, 1, 4), new Object[]{Blocks.sapling, new ItemStack(Items.dye, 1, 14)});
		
		GameRegistry.addRecipe(new ItemStack(Blocks.rail, 48), new Object[]{
			"I I", "IRI", "I I", 'I', AdvancedUtilitiesItems.ingotBrass, 'R', new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 1)
		});
		GameRegistry.addRecipe(new ItemStack(Blocks.golden_rail, 32), new Object[]{
			"I I", "IRI", "ISI", 'I', AdvancedUtilitiesItems.ingotBronze, 'R', new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 0), 'S', Items.redstone
		});
		GameRegistry.addRecipe(new ItemStack(blockBreaker, 1), new Object[]{
			"WWW", "CRC", "CIC", 'I', AdvancedUtilitiesItems.ingotBronze, 'R', Items.redstone, 'C', Blocks.cobblestone, 'W', new ItemStack(Blocks.planks, 1, OreDictionary.WILDCARD_VALUE)
		});
		GameRegistry.addRecipe(new ItemStack(Blocks.sand, 8, 1) , new Object[]{
			"SSS","SDS","SSS",Character.valueOf('S'), Blocks.sand, 'D', new ItemStack(Items.dye, 1, 1)
		});
		
		GameRegistry.addRecipe(new ItemStack(Blocks.dirt, 8, 2) , new Object[]{
			"SSS","SDS","SSS",Character.valueOf('S'), Blocks.dirt, 'D', Items.water_bucket
		});
		
		GameRegistry.addRecipe(new ItemStack(blockKiln, 1), new Object[]{
			"S S", "SPS", "PCP", 'C', Blocks.coal_block, 'P', Blocks.planks, 'S', Blocks.stone
		});
		GameRegistry.addRecipe(new ItemStack(blockSmeltry, 1), new Object[]{
			"SSS", "SFS", "PCP", 'C', Blocks.coal_block, 'P', Blocks.planks, 'S', Blocks.stone, 'F', Blocks.furnace
		});
		GameRegistry.addRecipe(new ItemStack(blockToolForge, 1), new Object[]{
			"   ", " IS", " A ", 'A', Blocks.anvil, 'I', Items.iron_ingot, 'S', Items.stick
		});
		GameRegistry.addRecipe(new ItemStack(blockArmorForge, 1), new Object[]{
			"   ", " IS", " A ", 'A', Blocks.anvil, 'I', AdvancedUtilitiesItems.ingotBronze, 'S', Items.stick
		});
		GameRegistry.addShapelessRecipe(new ItemStack(restrictedItemTube, 1), new Object[]{itemTube, Items.redstone});
		
		GameRegistry.addShapelessRecipe(new ItemStack(blockRubberSapling, 1), new Object[]{blockRubberLeaves});
		GameRegistry.addShapelessRecipe(new ItemStack(blockRubberPlanks, 4), new Object[]{blockRubberLog});
		
		GameRegistry.addRecipe(new ItemStack(bronzeMachineCase, 1), new Object[]{
			"RPR", "P P", "RPR", 'R', AdvancedUtilitiesItems.brassRivets, 'P', new ItemStack(AdvancedUtilitiesItems.plate, 1, 1)
		});
		
		GameRegistry.addRecipe(new ItemStack(steamCharger, 1), new Object[]{
			" B ", " M ", " P ", 'P',  new ItemStack(AdvancedUtilitiesItems.plate, 1, 2), 'M', bronzeMachineCase, 'B', blockBronzeBlock
		});
		GameRegistry.addRecipe(new ItemStack(itemTube, 16), new Object[]{
			" P ", "P P", " P ", 'P',  new ItemStack(AdvancedUtilitiesItems.plate, 1, 0)
		});
		
		GameRegistry.addRecipe(new ItemStack(blockCopperBlock, 1), new Object[]{
			"III", "III", "III", 'I', AdvancedUtilitiesItems.ingotCopper
		});
		GameRegistry.addRecipe(new ItemStack(blockTinBlock, 1), new Object[]{
			"III", "III", "III", 'I', AdvancedUtilitiesItems.ingotTin
		});
		GameRegistry.addRecipe(new ItemStack(blockZincBlock, 1), new Object[]{
			"III", "III", "III", 'I', AdvancedUtilitiesItems.ingotZinc
		});
		GameRegistry.addRecipe(new ItemStack(blockBronzeBlock, 1), new Object[]{
			"III", "III", "III", 'I', AdvancedUtilitiesItems.ingotBronze
		});
		GameRegistry.addRecipe(new ItemStack(blockBrassBlock, 1), new Object[]{
			"III", "III", "III", 'I', AdvancedUtilitiesItems.ingotBrass
		});
		GameRegistry.addRecipe(new ItemStack(blockSilverBlock, 1), new Object[]{
			"III", "III", "III", 'I', AdvancedUtilitiesItems.ingotSilver
		});
		GameRegistry.addRecipe(new ItemStack(blockLeadBlock, 1), new Object[]{
			"III", "III", "III", 'I', AdvancedUtilitiesItems.ingotLead
		});
		GameRegistry.addRecipe(new ItemStack(blockAluminumBlock, 1), new Object[]{
			"III", "III", "III", 'I', AdvancedUtilitiesItems.ingotAluminum
		});
		GameRegistry.addRecipe(new ItemStack(blockTungstenBlock, 1), new Object[]{
			"III", "III", "III", 'I', AdvancedUtilitiesItems.ingotTungsten
		});
		GameRegistry.addRecipe(new ItemStack(blockPlatinumBlock, 1), new Object[]{
			"III", "III", "III", 'I', AdvancedUtilitiesItems.ingotPlatinum
		});
		GameRegistry.addRecipe(new ItemStack(blockIridiumBlock, 1), new Object[]{
			"III", "III", "III", 'I', AdvancedUtilitiesItems.ingotIridium
		});
		GameRegistry.addRecipe(new ItemStack(blockPalidiumBlock, 1), new Object[]{
			"III", "III", "III", 'I', AdvancedUtilitiesItems.ingotPalidium
		});
		GameRegistry.addRecipe(new ItemStack(blockSteelBlock, 1), new Object[]{
			"III", "III", "III", 'I', AdvancedUtilitiesItems.ingotSteel
		});
		GameRegistry.addRecipe(new ItemStack(blockNickelBlock, 1), new Object[]{
			"III", "III", "III", 'I', AdvancedUtilitiesItems.ingotNickel
		});
		
		GameRegistry.addShapelessRecipe(new ItemStack(AdvancedUtilitiesItems.ingotCopper, 9), new Object[]{blockCopperBlock});
		GameRegistry.addShapelessRecipe(new ItemStack(AdvancedUtilitiesItems.ingotTin, 9), new Object[]{blockTinBlock});
		GameRegistry.addShapelessRecipe(new ItemStack(AdvancedUtilitiesItems.ingotBronze, 9), new Object[]{blockBronzeBlock});
		GameRegistry.addShapelessRecipe(new ItemStack(AdvancedUtilitiesItems.ingotZinc, 9), new Object[]{blockZincBlock});
		GameRegistry.addShapelessRecipe(new ItemStack(AdvancedUtilitiesItems.ingotBrass, 9), new Object[]{blockBrassBlock});
		GameRegistry.addShapelessRecipe(new ItemStack(AdvancedUtilitiesItems.ingotSilver, 9), new Object[]{blockSilverBlock});
		GameRegistry.addShapelessRecipe(new ItemStack(AdvancedUtilitiesItems.ingotLead, 9), new Object[]{blockLeadBlock});
		GameRegistry.addShapelessRecipe(new ItemStack(AdvancedUtilitiesItems.ingotAluminum, 9), new Object[]{blockAluminumBlock});
		GameRegistry.addShapelessRecipe(new ItemStack(AdvancedUtilitiesItems.ingotTungsten, 9), new Object[]{blockTungstenBlock});
		GameRegistry.addShapelessRecipe(new ItemStack(AdvancedUtilitiesItems.ingotPlatinum, 9), new Object[]{blockPlatinumBlock});
		GameRegistry.addShapelessRecipe(new ItemStack(AdvancedUtilitiesItems.ingotIridium, 9), new Object[]{blockIridiumBlock});
		GameRegistry.addShapelessRecipe(new ItemStack(AdvancedUtilitiesItems.ingotPalidium, 9), new Object[]{blockPalidiumBlock});
		GameRegistry.addShapelessRecipe(new ItemStack(AdvancedUtilitiesItems.ingotSteel, 9), new Object[]{blockSteelBlock});
		GameRegistry.addShapelessRecipe(new ItemStack(AdvancedUtilitiesItems.ingotNickel, 9), new Object[]{blockNickelBlock});
		
		GameRegistry.addRecipe(new ItemStack(steamBoiler, 1), new Object[]{
			" B ", "WMW", " B ", 'M', bronzeMachineCase, 'B', Items.bucket, 'W', Items.water_bucket
		});
		GameRegistry.addRecipe(new ItemStack(steamCrusher, 1), new Object[]{
			" I ", "RPR", " M ", 'M', bronzeMachineCase, 'I', Blocks.iron_block, 'R', AdvancedUtilitiesItems.brassRivets, 'P', new ItemStack(AdvancedUtilitiesItems.plate, 1, 0)
		});
		GameRegistry.addRecipe(new ItemStack(steamFurnace, 1), new Object[]{
			" M ", "RPR", " C ", 'M', bronzeMachineCase, 'C', Blocks.coal_block, 'R', AdvancedUtilitiesItems.brassRivets, 'P', new ItemStack(AdvancedUtilitiesItems.plate, 1, 1)
		});
		GameRegistry.addRecipe(new ItemStack(steamSmeltry, 1), new Object[]{
			"RPR", "CMC", " P ", 'M', bronzeMachineCase, 'C', Blocks.clay, 'R', AdvancedUtilitiesItems.brassRivets, 'P', new ItemStack(AdvancedUtilitiesItems.plate, 1, 2)
		});
		GameRegistry.addRecipe(new ItemStack(bellows, 1), new Object[]{
			"RPR", "L L", "RPR", 'M', bronzeMachineCase, 'L', Items.leather, 'R', AdvancedUtilitiesItems.brassRivets, 'P', new ItemStack(AdvancedUtilitiesItems.plate, 1, 1)
		});
		GameRegistry.addRecipe(new ItemStack(steamCompressor, 1), new Object[]{
			" P ", " M ", " P ", 'M', bronzeMachineCase, 'P', new ItemStack(AdvancedUtilitiesItems.plate, 1, 1)
		});
		GameRegistry.addRecipe(new ItemStack(blockTank, 1) , new Object[]{
			"GGG","GMG","GGG",Character.valueOf('G'), Blocks.glass, 'M', bronzeMachineCase
		});
		GameRegistry.addRecipe(new ItemStack(fluidTube, 16) , new Object[]{
			" P ","P P"," P ",Character.valueOf('P'), new ItemStack(AdvancedUtilitiesItems.plate, 1, 1)
		});
		
	}
}
