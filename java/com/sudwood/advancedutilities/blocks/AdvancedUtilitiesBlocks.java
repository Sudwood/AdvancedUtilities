package com.sudwood.advancedutilities.blocks;

import com.sudwood.advancedutilities.AdvancedFuelHandler;
import com.sudwood.advancedutilities.AdvancedUtilities;
import com.sudwood.advancedutilities.NBTShapedOreRecipe;
import com.sudwood.advancedutilities.RecipePortaChest;
import com.sudwood.advancedutilities.fluids.BlockFluidOil;
import com.sudwood.advancedutilities.fluids.BlockFluidSteam;
import com.sudwood.advancedutilities.items.AdvancedUtilitiesItems;
import com.sudwood.advancedutilities.items.ItemIngot;
import com.sudwood.advancedutilities.items.ItemPlate;
import com.sudwood.advancedutilities.tileentity.TileEntityPortaChest;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class AdvancedUtilitiesBlocks 
{
    public static Block chunkLoader;
    public static Block blockOre;
    public static Block ingotBlock;
    public static Block compressedBlock;
    
    public static Block blockRubberLog;
    public static Block blockRubberLeaves;
    public static Block blockRubberPlanks;
    public static Block blockRubberSapling;
    
    public static Block blockKiln;
    public static Block blockSmeltry;
    public static Block blockStoneMill;
    public static Block blockWoodenCrate;
    public static Block blockCompressor;
    public static Block blockToolForge;
    public static Block blockArmorForge;
    
    public static Block steamBoiler;
    public static Block hpBoiler;
    public static Block machineBase;
    public static Block steamCrusher;
    public static Block steamFurnace;
    public static Block steamSmeltry;
    public static Block bellows;
    public static Block steamCompressor;
    public static Block itemTube;
    public static Block restrictedItemTube;
    public static Block magnetItemTube;
    public static Block splitterItemTube;
    public static Block steamCharger;
    public static Block fluidTube;
    public static Block splitterFluidTube;
    public static Block rationedItemTube;
    public static Block blockTank;
    public static Block blockSteelOven;
    public static Block blockSteelController;
    public static Block steamQuarry;
    public static Block quarryFrame;
    public static Block ironWire;
    public static Block copperWire;
    public static Block goldWire;
    public static Block steamTurbine;
    public static Block energyBattery;
    
    public static Block blockBreaker;
    public static Block blockPlacer;
    public static Block blockGrower;
    public static Block blockSpeedyGrowing;
    public static Block tomatoPlant;
    public static Block blockTrash;
    
    public static Block portaChestWood;
    public static Block portaChestBronze;
    public static Block portaChestGold;
    public static Block portaChestDiamond;
    public static Block portaChestPlatinum;
    
    
    public static Block dummyChunkChest;
    public static Block dummyTank;
    public static Block dummyChunkTank;
    
    public static Block elevator;

    
	public static void init()
	{
		chunkLoader = new BlockChunkLoader(Material.circuits, 0).setBlockName("ChunkLoader").setHardness(20F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedTab);
		dummyChunkChest = new BlockChunkLoader(Material.circuits, 1).setBlockName("DummyChunkChest").setHardness(20F).setResistance(100F);
		dummyTank = new BlockChunkLoader(Material.circuits, 2).setBlockName("DummyTank").setHardness(20F).setResistance(100F);
		dummyChunkTank = new BlockChunkLoader(Material.circuits, 3).setBlockName("DummyChunkTank").setHardness(20F).setResistance(100F);
		
		blockOre = new BlockOre(Material.rock).setBlockName("Ore").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedTab);
		
		ingotBlock = new BlockIngotBlock(Material.iron).setBlockName("IngotBlock").setHardness(2F).setResistance(6000000.0F).setCreativeTab(AdvancedUtilities.advancedTab);
		compressedBlock = new BlockCompressedBlock(Material.rock).setBlockName("CompressedBlock").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedTab);
		
		blockRubberLog = new BlockWood(Material.wood, 0).setBlockName("RubberLog").setHardness(2F).setStepSound(Block.soundTypeWood).setCreativeTab(AdvancedUtilities.advancedTab);
		blockRubberPlanks = new BlockWood(Material.wood, 1).setBlockName("RubberPlanks").setHardness(2F).setStepSound(Block.soundTypeWood).setCreativeTab(AdvancedUtilities.advancedTab);
		blockRubberSapling = new BlockRubberSapling().setBlockName("RubberSapling").setStepSound(Block.soundTypeGrass).setCreativeTab(AdvancedUtilities.advancedTab);
		blockRubberLeaves = new BlockAdvancedLeaf().setBlockName("RubberLeaves").setStepSound(Block.soundTypeGrass).setCreativeTab(AdvancedUtilities.advancedTab);
		
		blockKiln = new BlockKiln(Material.rock).setBlockName("Kiln").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedTab);
		blockSmeltry = new BlockSmeltry(Material.rock).setBlockName("Smeltry").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedTab);
		blockStoneMill = new BlockStoneMill(Material.rock).setBlockName("StoneMill").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedTab);
		blockCompressor = new BlockCompressorBlock(Material.rock).setBlockName("Compressor").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedTab);
		blockWoodenCrate = new BlockWoodenCrate(Material.wood).setBlockName("WoodenCrate").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedTab);
		blockToolForge = new BlockToolForge(Material.anvil).setBlockName("ToolForge").setHardness(2F).setStepSound(Block.soundTypeAnvil).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedTab);
		blockArmorForge = new BlockArmorForge(Material.anvil).setBlockName("ArmorForge").setHardness(2F).setStepSound(Block.soundTypeAnvil).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedTab);
		
		machineBase = new BlockMachineBase(Material.iron).setBlockName("MachineBase").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedBEMachinesTab);
		
		steamBoiler = new BlockSteamMachine(Material.iron, 0).setBlockName("SteamBoiler").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedBEMachinesTab);
		hpBoiler = new BlockSteamMachine(Material.iron, 7).setBlockName("HPBoiler").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedBEMachinesTab);
		steamCrusher = new BlockSteamMachine(Material.iron, 1).setBlockName("SteamCrusher").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedBEMachinesTab);
		steamFurnace = new BlockSteamMachine(Material.iron, 2).setBlockName("SteamFurnace").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedBEMachinesTab);
		steamSmeltry = new BlockSteamMachine(Material.iron, 3).setBlockName("SteamSmeltry").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedBEMachinesTab);
		bellows = new BlockSteamMachine(Material.iron, 4).setBlockName("Bellows").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedBEMachinesTab);
		steamCompressor = new BlockSteamMachine(Material.iron, 5).setBlockName("SteamCompressor").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedBEMachinesTab);
		itemTube = new BlockTube(Material.iron, 0).setBlockName("ItemTube").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedTab);
		magnetItemTube = new BlockTube(Material.iron, 5).setBlockName("MagnetItemTube").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedTab);
		restrictedItemTube = new BlockTube(Material.iron, 2).setBlockName("RestrictedItemTube").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedTab);
		splitterItemTube = new BlockTube(Material.iron, 3).setBlockName("SplitterItemTube").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedTab);
		steamCharger = new BlockSteamMachine(Material.iron, 6).setBlockName("SteamCharger").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedBEMachinesTab);
		fluidTube = new BlockTube(Material.iron, 1).setBlockName("FluidTube").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedTab);
		splitterFluidTube = new BlockTube(Material.iron, 4).setBlockName("SplitterFluidTube").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedTab);
		rationedItemTube = new BlockTube(Material.iron, 6).setBlockName("RationedItemTube").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedTab);
		blockSteelOven = new BlockSteelOven(Material.iron, 0).setBlockName("SteelOvenBlock").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedBEMachinesTab);
		blockSteelController = new BlockSteelOven(Material.iron, 1).setBlockName("SteelOvenController").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedBEMachinesTab);
		steamQuarry = new BlockSteamQuarry(Material.iron).setBlockName("SteamQuarry").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedBEMachinesTab);
		quarryFrame = new BlockQuarryFrame(Material.iron).setBlockName("QuarryFrame").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedBEMachinesTab);
		ironWire = new BlockWire(Material.iron, 1).setBlockName("IronWire").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedBEMachinesTab);
		copperWire = new BlockWire(Material.iron, 0).setBlockName("CopperWire").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedBEMachinesTab);
		goldWire = new BlockWire(Material.iron, 2).setBlockName("GoldWire").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedBEMachinesTab);
		steamTurbine = new BlockSteamTurbine(Material.iron).setBlockName("SteamTurbine").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedBEMachinesTab);
		energyBattery = new BlockBattery(Material.iron).setBlockName("EnergyBattery").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedBEMachinesTab);
		
		portaChestWood = new BlockPortaChest(Material.wood, 0).setBlockName("PortaChestWood").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedTab);
		portaChestBronze = new BlockPortaChest(Material.iron, 1).setBlockName("PortaChestBronze").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedTab);
		portaChestGold = new BlockPortaChest(Material.iron, 2).setBlockName("PortaChestGold").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedTab);
		portaChestDiamond = new BlockPortaChest(Material.iron, 3).setBlockName("PortaChestDiamond").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedTab);
		portaChestPlatinum = new BlockPortaChest(Material.iron, 4).setBlockName("PortaChestPlatinum").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedTab);
		
		blockBreaker = new BlockBlockBreaker(Material.piston).setBlockName("BlockBreaker").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedTab);
		blockPlacer = new BlockBlockPlacer(Material.rock).setBlockName("BlockPlacer").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedTab);
		blockGrower = new BlockGrowerBlock(Material.rock).setBlockName("BlockGrower").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedTab);
		blockSpeedyGrowing = new BlockSpeedyGrowing(Material.clay).setBlockName("SpeedyGrowing").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedTab);
		tomatoPlant = new BlockTomatoPlant(Material.plants).setBlockName("TomatoPlant").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedTab);
		blockTrash = new BlockTrash(Material.rock).setBlockName("TrashBlock").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedTab);
		
		
		
		blockTank = new BlockTank(Material.rock).setBlockName("BlockTank").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedTab);
		
		elevator = new BlockElevator(Material.glass).setBlockName("Elevator").setHardness(2F).setResistance(100F).setCreativeTab(AdvancedUtilities.advancedTab);
		registerBlocks();
		
	}
	
	public static void registerBlocks()
	{
		GameRegistry.registerBlock(chunkLoader, "chunkloader");
		GameRegistry.registerBlock(dummyChunkChest, "dummyChunkChest");
		GameRegistry.registerBlock(dummyTank, "dummyTank");
		GameRegistry.registerBlock(dummyChunkTank, "dummyChunkTank");
		
		GameRegistry.registerBlock(blockOre, ItemBlockOre.class, "ore");
		GameRegistry.registerBlock(ingotBlock, ItemBlockIngotBlock.class, "ingotblock");
		GameRegistry.registerBlock(compressedBlock, ItemBlockCompressedBlock.class, "compressedblock");
		
		GameRegistry.registerBlock(blockRubberLog, "rubberlog");
		GameRegistry.registerBlock(blockRubberPlanks, "rubberplanks");
		GameRegistry.registerBlock(blockRubberSapling, "rubbersapling");
		GameRegistry.registerBlock(blockRubberLeaves, "rubberleaves");
		
		GameRegistry.registerBlock(blockKiln, "kiln");
		GameRegistry.registerBlock(blockSmeltry, "smeltry");
		GameRegistry.registerBlock(blockStoneMill, "stonemill");
		GameRegistry.registerBlock(blockCompressor, "compressor");
		GameRegistry.registerBlock(blockWoodenCrate, ItemBlockCrate.class, "woodencrate");
		GameRegistry.registerBlock(blockToolForge, "toolforge");
		GameRegistry.registerBlock(blockArmorForge, "armorforge");
		
		GameRegistry.registerBlock(steamBoiler, "steamboiler");
		GameRegistry.registerBlock(hpBoiler, "hpboiler");
		GameRegistry.registerBlock(steamCrusher, "steamcrusher");
		GameRegistry.registerBlock(steamFurnace, "steamfurnace");
		GameRegistry.registerBlock(steamSmeltry, "steamsmeltry");
		GameRegistry.registerBlock(steamCompressor, "steamcompressor");
		GameRegistry.registerBlock(steamCharger, "steamcharger");
		
		GameRegistry.registerBlock(itemTube, "itemtube");
		GameRegistry.registerBlock(magnetItemTube, "magnetitemtube");
		GameRegistry.registerBlock(restrictedItemTube, "restricteditemtube");
		GameRegistry.registerBlock(splitterItemTube, "splitteritemtube");
		GameRegistry.registerBlock(fluidTube, "fluidtube");
		GameRegistry.registerBlock(splitterFluidTube, "splitterfluidtube");
		GameRegistry.registerBlock(rationedItemTube, "rationeditemtube");
		
		GameRegistry.registerBlock(bellows, "bellows");
		GameRegistry.registerBlock(machineBase, ItemBlockMachineBase.class, "machinebase");
		GameRegistry.registerBlock(blockTank, ItemBlockTank.class, "blocktank");
		GameRegistry.registerBlock(blockSteelOven, "blocksteeloveny");
		GameRegistry.registerBlock(blockSteelController, "blocksteelovencontroller");
		GameRegistry.registerBlock(steamQuarry, "steamQuarry");
		GameRegistry.registerBlock(quarryFrame, "quarryFrame");
		
		
		GameRegistry.registerBlock(ironWire, "ironWire");
		GameRegistry.registerBlock(copperWire, "copperWire");
		GameRegistry.registerBlock(goldWire, "goldWire");
		GameRegistry.registerBlock(steamTurbine, "steamTurbine");
		GameRegistry.registerBlock(energyBattery, "energyBattery");
		
		GameRegistry.registerBlock(portaChestWood,ItemBlockPortaChest.class, "portaChestWood");
		GameRegistry.registerBlock(portaChestBronze,ItemBlockPortaChest.class, "portaChestBronze");
		GameRegistry.registerBlock(portaChestGold,ItemBlockPortaChest.class, "portaChestGold");
		GameRegistry.registerBlock(portaChestDiamond,ItemBlockPortaChest.class, "portaChestDiamond");
		GameRegistry.registerBlock(portaChestPlatinum,ItemBlockPortaChest.class, "portaChestPlatinum");
		
		GameRegistry.registerBlock(blockBreaker, "blockbreaker");
		GameRegistry.registerBlock(blockPlacer, "blockplacer");
		GameRegistry.registerBlock(blockGrower, "blockgrower");
		GameRegistry.registerBlock(blockSpeedyGrowing, "speedygrowing");
		GameRegistry.registerBlock(tomatoPlant, "tomatoplant");
		GameRegistry.registerBlock(blockTrash, "blocktrash");
		
		GameRegistry.registerBlock(elevator, "elevaotr");
		
		
		OreDictionary.registerOre("treeWood", blockRubberLog);
		OreDictionary.registerOre("plankWood", blockRubberPlanks);
		OreDictionary.registerOre("treeLeaves", blockRubberLeaves);
		OreDictionary.registerOre("rubberWood", blockRubberLog);
		OreDictionary.registerOre("logWood", blockRubberLog);
		OreDictionary.registerOre("logRubber", blockRubberLog);
		OreDictionary.registerOre("rubberSapling", blockRubberSapling);
		OreDictionary.registerOre("treeSapling", Blocks.sapling);

		OreDictionary.registerOre("oreCopper", new ItemStack(blockOre, 1, BlockOre.COPPER));
		OreDictionary.registerOre("oreTin", new ItemStack(blockOre, 1, BlockOre.TIN));
		OreDictionary.registerOre("oreZinc", new ItemStack(blockOre, 1, BlockOre.ZINC));
		OreDictionary.registerOre("oreCoal", new ItemStack(Blocks.coal_ore, 1));
		OreDictionary.registerOre("oreSilver", new ItemStack(blockOre, 1, BlockOre.SILVER));
		OreDictionary.registerOre("oreLead", new ItemStack(blockOre, 1, BlockOre.LEAD));
		OreDictionary.registerOre("oreBauxite", new ItemStack(blockOre, 1, BlockOre.BAUXITE));
		OreDictionary.registerOre("oreAluminum", new ItemStack(blockOre, 1, BlockOre.BAUXITE));
		OreDictionary.registerOre("oreAluminium", new ItemStack(blockOre, 1, BlockOre.BAUXITE));
		OreDictionary.registerOre("oreTungsten", new ItemStack(blockOre, 1, BlockOre.TUNGSTEN));
		OreDictionary.registerOre("orePlatinum", new ItemStack(blockOre, 1, BlockOre.PLATINUM));
		OreDictionary.registerOre("oreNickel", new ItemStack(blockOre, 1, BlockOre.NICKEL));
		
		
		OreDictionary.registerOre("blockCopper", new ItemStack(ingotBlock, 1, BlockIngotBlock.COPPER));
		OreDictionary.registerOre("blockTin",  new ItemStack(ingotBlock, 1, BlockIngotBlock.TIN));
		OreDictionary.registerOre("blockBronze",  new ItemStack(ingotBlock, 1, BlockIngotBlock.BRONZE));
		OreDictionary.registerOre("blockZinc",  new ItemStack(ingotBlock, 1, BlockIngotBlock.ZINC));
		OreDictionary.registerOre("blockBrass",  new ItemStack(ingotBlock, 1, BlockIngotBlock.BRASS));
		OreDictionary.registerOre("blockSilver",  new ItemStack(ingotBlock, 1, BlockIngotBlock.SILVER));
		OreDictionary.registerOre("blockLead",  new ItemStack(ingotBlock, 1, BlockIngotBlock.LEAD));
		OreDictionary.registerOre("blockAluminum",  new ItemStack(ingotBlock, 1, BlockIngotBlock.ALUMINUM));
		OreDictionary.registerOre("blockAluminium",  new ItemStack(ingotBlock, 1, BlockIngotBlock.ALUMINUM));
		OreDictionary.registerOre("blockTungsten",  new ItemStack(ingotBlock, 1, BlockIngotBlock.TUNGSTEN));
		OreDictionary.registerOre("blockPlatinum",  new ItemStack(ingotBlock, 1, BlockIngotBlock.PLATINUM));
		OreDictionary.registerOre("blockIridium",  new ItemStack(ingotBlock, 1, BlockIngotBlock.IRIDIUM));
		OreDictionary.registerOre("blockPalidium",  new ItemStack(ingotBlock, 1, BlockIngotBlock.PALIDIUM));
		OreDictionary.registerOre("blockSteel",  new ItemStack(ingotBlock, 1, BlockIngotBlock.STEEL));
		OreDictionary.registerOre("blockNickel",  new ItemStack(ingotBlock, 1, BlockIngotBlock.NICKEL));
		OreDictionary.registerOre("blockIron",  new ItemStack(ingotBlock, 1, BlockIngotBlock.IRON));
		OreDictionary.registerOre("blockIron",  new ItemStack(Blocks.iron_block, 1));
		OreDictionary.registerOre("blockCoal", new ItemStack(Blocks.coal_block, 1));
		OreDictionary.registerOre("cobblestone", new ItemStack(Blocks.cobblestone, 1));
		OreDictionary.registerOre("sand", new ItemStack(Blocks.sand, 1));
		OreDictionary.registerOre("gravel", new ItemStack(Blocks.gravel, 1));
		OreDictionary.registerOre("stone", new ItemStack(Blocks.stone, 1));
		OreDictionary.registerOre("dirt", new ItemStack(Blocks.dirt, 1));
		OreDictionary.registerOre("treeSapling", new ItemStack(blockRubberSapling, 1));
		OreDictionary.registerOre("treeWood", new ItemStack(Blocks.log, 1,0));
		OreDictionary.registerOre("treeWood", new ItemStack(Blocks.log, 1,1));
		OreDictionary.registerOre("treeWood", new ItemStack(Blocks.log, 1,2));
		OreDictionary.registerOre("treeWood", new ItemStack(Blocks.log, 1,3));
		OreDictionary.registerOre("treeWood", new ItemStack(Blocks.log2, 1,0));
		OreDictionary.registerOre("treeWood", new ItemStack(Blocks.log2, 1,1));
		OreDictionary.registerOre("plankWoood", new ItemStack(Blocks.planks, 1,0));
		OreDictionary.registerOre("plankWoood", new ItemStack(Blocks.planks, 1,1));
		OreDictionary.registerOre("plankWoood", new ItemStack(Blocks.planks, 1,2));
		OreDictionary.registerOre("plankWoood", new ItemStack(Blocks.planks, 1,3));
		OreDictionary.registerOre("plankWoood", new ItemStack(Blocks.planks, 1,4));
		OreDictionary.registerOre("plankWoood", new ItemStack(Blocks.planks, 1,5));
		
		
		
	}
	
	public static void addRecipies()
	{
		GameRegistry.addSmelting(new ItemStack(blockOre, 1, BlockOre.COPPER), new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.COPPER),1);
		GameRegistry.addSmelting(new ItemStack(blockOre, 1, BlockOre.TIN), new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.TIN),1);
		GameRegistry.addSmelting(new ItemStack(blockOre, 1, BlockOre.ZINC), new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.ZINC), 1);
		GameRegistry.addSmelting(new ItemStack(blockOre, 1, BlockOre.SILVER), new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.SILVER),1);
		GameRegistry.addSmelting(new ItemStack(blockOre, 1, BlockOre.LEAD), new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.LEAD),1);
		GameRegistry.addSmelting(new ItemStack(blockOre, 1, BlockOre.BAUXITE), new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.ALUMINUM), 1);
		GameRegistry.addSmelting(new ItemStack(blockOre, 1, BlockOre.TUNGSTEN), new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.TUNGSTEN),1);
		GameRegistry.addSmelting(new ItemStack(blockOre, 1, BlockOre.PLATINUM), new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.PLATINUM),1);
		GameRegistry.addSmelting(new ItemStack(blockOre, 1, BlockOre.NICKEL), new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.NICKEL),1);
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(chunkLoader, 1), new Object[]{
			"CRC", "RDR", "CRC", 'C', "blockCoal", 'R', Blocks.redstone_block, 'D', "gemDiamond"
		}));
		
		GameRegistry.addRecipe(new ItemStack(Blocks.packed_ice, 9), new Object[]{
			"III", "III", "III", 'I', Blocks.ice
		});
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Blocks.mossy_cobblestone, 1), new Object[]{"cobblestone", "cropWheat", Items.water_bucket}));
		
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.mycelium, 1), new Object[]{Blocks.dirt, Blocks.red_mushroom});
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.mycelium, 1), new Object[]{Blocks.dirt, Blocks.brown_mushroom});
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Blocks.sapling, 1, 5), new Object[]{"treeSapling",  "dyeBlack"}));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Blocks.sapling, 1, 4), new Object[]{"treeSapling", "dyeOrange"}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.rail, 48), new Object[]{
			"I I", "IRI", "I I", 'I', "ingotBrass", 'R', new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 1)
		}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.golden_rail, 32), new Object[]{
			"I I", "IRI", "ISI", 'I', "ingotBronze", 'R', new ItemStack(AdvancedUtilitiesItems.toolPart, 1, 0), 'S', Items.redstone
		}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(blockBreaker, 1), new Object[]{
			"WWW", "CRC", "CIC", 'I', "ingotBronze", 'R', Items.redstone, 'C', Blocks.cobblestone, 'W', new ItemStack(Blocks.planks, 1, OreDictionary.WILDCARD_VALUE)
		}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.sand, 8, 1) , new Object[]{
			"SSS","SDS","SSS",Character.valueOf('S'), "sand", 'D', "dyeRed"
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.dirt, 8, 2) , new Object[]{
			"SSS","SDS","SSS",Character.valueOf('S'), "dirt", 'D', Items.water_bucket
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(blockKiln, 1), new Object[]{
			"S S", "SPS", "PCP", 'C', "blockCoal", 'P', "plankWood", 'S', "stone"
		}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(blockSmeltry, 1), new Object[]{
			"S S", "SCS", "CPC", 'P', "blockCoal", 'S', "stone", 'C', Blocks.clay
		}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(blockStoneMill, 1), new Object[]{
				"CSC", "SFS", "CSC", 'C', "cobblestone", 'S', "stone", 'F', Items.flint
			}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(blockCompressor, 1), new Object[]{
				"CSC", "S S", "CPC", 'C', "cobblestone", 'S', "stone", 'P', Blocks.piston
			}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(blockWoodenCrate, 1), new Object[]{
				"RWR", "WCW", "RWR", 'W', "logWood", 'R', new ItemStack(AdvancedUtilitiesItems.stoneRivets, 1), 'C', Blocks.chest
			}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(blockToolForge, 1), new Object[]{
			"   ", " IS", " A ", 'A', Blocks.anvil, 'I', "ingotIron", 'S', Items.stick
		}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(blockArmorForge, 1), new Object[]{
			"   ", " IS", " A ", 'A', Blocks.anvil, 'I', "ingotBronze", 'S', Items.stick
		}));
		GameRegistry.addShapelessRecipe(new ItemStack(restrictedItemTube, 1), new Object[]{itemTube, Items.redstone});
		GameRegistry.addShapelessRecipe(new ItemStack(rationedItemTube, 1), new Object[]{itemTube, new ItemStack(Items.dye, 1,4)});
		GameRegistry.addShapelessRecipe(new ItemStack(magnetItemTube, 1), new Object[]{itemTube, AdvancedUtilitiesItems.magnetAmulet});
		GameRegistry.addShapelessRecipe(new ItemStack(splitterItemTube, 1), new Object[]{itemTube});
		GameRegistry.addShapelessRecipe(new ItemStack(itemTube, 1), new Object[]{splitterItemTube});
		GameRegistry.addShapelessRecipe(new ItemStack(splitterFluidTube, 1), new Object[]{fluidTube});
		GameRegistry.addShapelessRecipe(new ItemStack(fluidTube, 1), new Object[]{splitterFluidTube});
		GameRegistry.addShapelessRecipe(new ItemStack(blockTank, 1), new Object[]{blockTank});
		GameRegistry.addShapelessRecipe(new ItemStack(blockRubberSapling, 1), new Object[]{blockRubberLeaves});
		GameRegistry.addShapelessRecipe(new ItemStack(blockRubberPlanks, 4), new Object[]{blockRubberLog});
		
		GameRegistry.addRecipe(new ItemStack(machineBase, 1, BlockMachineBase.BRONZEMACHINE), new Object[]{
			"RPR", "P P", "RPR", 'R', AdvancedUtilitiesItems.brassRivets, 'P', new ItemStack(AdvancedUtilitiesItems.plate, 1, 1)
		});
		
		GameRegistry.addRecipe(new ItemStack(machineBase, 1, BlockMachineBase.STEELMACHINE), new Object[]{
			"RPR", "P P", "RPR", 'R', AdvancedUtilitiesItems.steelRivets, 'P', new ItemStack(AdvancedUtilitiesItems.plate, 1, 4)
		});
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(hpBoiler, 1), new Object[]{
			"IBI", "WMW", "IBI", 'I', "ingotSteel", 'M', new ItemStack(machineBase, 1, BlockMachineBase.STEELMACHINE), 'B', Items.bucket, 'W', Items.water_bucket
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(blockSteelOven, 1), new Object[]{
			"III", "IMI", "III", 'I', "ingotBronze", 'M', new ItemStack(machineBase, 1, BlockMachineBase.BRONZEMACHINE)
		}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(blockSteelController, 1), new Object[]{
			"III", "IMI", "III", 'I', "ingotBrass", 'M', new ItemStack(machineBase, 1, BlockMachineBase.BRONZEMACHINE)
		}));
		
		GameRegistry.addRecipe(new ItemStack(steamQuarry, 1), new Object[]{
			"R R", "PMP", " P ",'M' , new ItemStack(machineBase, 1, BlockMachineBase.STEELMACHINE), 'R', AdvancedUtilitiesItems.steelRivets, 'P', new ItemStack(AdvancedUtilitiesItems.plate, 1, ItemPlate.STEELPLATE)
		});
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(quarryFrame, 64), new Object[]{
			"III", "III", "   ", 'I', "ingotSteel"
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(steamCharger, 1), new Object[]{
			" B ", " M ", " P ", 'P',  new ItemStack(AdvancedUtilitiesItems.plate, 1, 2), 'M', new ItemStack(machineBase, 1, BlockMachineBase.BRONZEMACHINE), 'B',  "blockBronze"
		}));
		GameRegistry.addRecipe(new ItemStack(itemTube, 16), new Object[]{
			" P ", "P P", " P ", 'P',  new ItemStack(AdvancedUtilitiesItems.plate, 1, 0)
		});
		
		GameRegistry.addRecipe(new ItemStack(ingotBlock, 1, BlockIngotBlock.COPPER), new Object[]{
			"III", "III", "III", 'I', new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.COPPER)
		});
		GameRegistry.addRecipe(new ItemStack(ingotBlock, 1, BlockIngotBlock.TIN), new Object[]{
			"III", "III", "III", 'I', new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.TIN)
		});
		GameRegistry.addRecipe(new ItemStack(ingotBlock, 1, BlockIngotBlock.ZINC), new Object[]{
			"III", "III", "III", 'I', new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.ZINC)
		});
		GameRegistry.addRecipe(new ItemStack(ingotBlock, 1, BlockIngotBlock.BRONZE), new Object[]{
			"III", "III", "III", 'I', new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.BRONZE)
		});
		GameRegistry.addRecipe(new ItemStack(ingotBlock, 1, BlockIngotBlock.BRASS), new Object[]{
			"III", "III", "III", 'I', new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.BRASS)
		});
		GameRegistry.addRecipe(new ItemStack(ingotBlock, 1, BlockIngotBlock.SILVER), new Object[]{
			"III", "III", "III", 'I', new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.SILVER)
		});
		GameRegistry.addRecipe(new ItemStack(ingotBlock, 1, BlockIngotBlock.LEAD), new Object[]{
			"III", "III", "III", 'I', new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.LEAD)
		});
		GameRegistry.addRecipe(new ItemStack(ingotBlock, 1, BlockIngotBlock.ALUMINUM), new Object[]{
			"III", "III", "III", 'I', new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.ALUMINUM)
		});
		GameRegistry.addRecipe(new ItemStack(ingotBlock, 1, BlockIngotBlock.TUNGSTEN), new Object[]{
			"III", "III", "III", 'I', new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.TUNGSTEN)
		});
		GameRegistry.addRecipe(new ItemStack(ingotBlock, 1, BlockIngotBlock.PLATINUM), new Object[]{
			"III", "III", "III", 'I', new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.PLATINUM)
		});
		GameRegistry.addRecipe(new ItemStack(ingotBlock, 1, BlockIngotBlock.IRIDIUM), new Object[]{
			"III", "III", "III", 'I', new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.IRIDIUM)
		});
		GameRegistry.addRecipe(new ItemStack(ingotBlock, 1, BlockIngotBlock.PALIDIUM), new Object[]{
			"III", "III", "III", 'I', new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.PALIDIUM)
		});
		GameRegistry.addRecipe(new ItemStack(ingotBlock, 1, BlockIngotBlock.STEEL), new Object[]{
			"III", "III", "III", 'I', new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.STEEL)
		});
		GameRegistry.addRecipe(new ItemStack(ingotBlock, 1, BlockIngotBlock.NICKEL), new Object[]{
			"III", "III", "III", 'I', new ItemStack(AdvancedUtilitiesItems.ingot, 1, ItemIngot.NICKEL)
		});
		
		GameRegistry.addShapelessRecipe(new ItemStack(compressedBlock, 1, BlockCompressedBlock.SINGLECHARCOAL), new Object[]{new ItemStack(Items.coal, 1, 1),new ItemStack(Items.coal, 1, 1),new ItemStack(Items.coal, 1, 1),new ItemStack(Items.coal, 1, 1),new ItemStack(Items.coal, 1, 1),new ItemStack(Items.coal, 1, 1),new ItemStack(Items.coal, 1, 1),new ItemStack(Items.coal, 1, 1),new ItemStack(Items.coal, 1, 1)});
		GameRegistry.addShapelessRecipe(new ItemStack(compressedBlock, 1, BlockCompressedBlock.DOUBLECHARCOAL), new Object[]{new ItemStack(compressedBlock, 1, BlockCompressedBlock.SINGLECHARCOAL),new ItemStack(compressedBlock, 1, BlockCompressedBlock.SINGLECHARCOAL),new ItemStack(compressedBlock, 1, BlockCompressedBlock.SINGLECHARCOAL),new ItemStack(compressedBlock, 1, BlockCompressedBlock.SINGLECHARCOAL),new ItemStack(compressedBlock, 1, BlockCompressedBlock.SINGLECHARCOAL),new ItemStack(compressedBlock, 1, BlockCompressedBlock.SINGLECHARCOAL),new ItemStack(compressedBlock, 1, BlockCompressedBlock.SINGLECHARCOAL),new ItemStack(compressedBlock, 1, BlockCompressedBlock.SINGLECHARCOAL),new ItemStack(compressedBlock, 1, BlockCompressedBlock.SINGLECHARCOAL)});

		GameRegistry.registerFuelHandler(new AdvancedFuelHandler());
		GameRegistry.addShapelessRecipe(new ItemStack(ingotBlock, 1, BlockIngotBlock.IRON), new Object[]{Blocks.iron_block, Blocks.glass});
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.iron_block, 1), new Object[]{new ItemStack(ingotBlock, 1, BlockIngotBlock.IRON), Blocks.dirt});
		
		GameRegistry.addShapelessRecipe(new ItemStack(AdvancedUtilitiesItems.ingot, 9, ItemIngot.COPPER), new Object[]{new ItemStack(ingotBlock, 1, BlockIngotBlock.COPPER)});
		GameRegistry.addShapelessRecipe(new ItemStack(AdvancedUtilitiesItems.ingot, 9, ItemIngot.TIN), new Object[]{new ItemStack(ingotBlock, 1, BlockIngotBlock.TIN)});
		GameRegistry.addShapelessRecipe(new ItemStack(AdvancedUtilitiesItems.ingot, 9, ItemIngot.BRONZE), new Object[]{new ItemStack(ingotBlock, 1, BlockIngotBlock.BRONZE)});
		GameRegistry.addShapelessRecipe(new ItemStack(AdvancedUtilitiesItems.ingot, 9, ItemIngot.ZINC), new Object[]{new ItemStack(ingotBlock, 1, BlockIngotBlock.ZINC)});
		GameRegistry.addShapelessRecipe(new ItemStack(AdvancedUtilitiesItems.ingot, 9, ItemIngot.BRASS), new Object[]{new ItemStack(ingotBlock, 1, BlockIngotBlock.BRASS)});
		GameRegistry.addShapelessRecipe(new ItemStack(AdvancedUtilitiesItems.ingot, 9, ItemIngot.SILVER), new Object[]{new ItemStack(ingotBlock, 1, BlockIngotBlock.SILVER)});
		GameRegistry.addShapelessRecipe(new ItemStack(AdvancedUtilitiesItems.ingot, 9, ItemIngot.LEAD), new Object[]{new ItemStack(ingotBlock, 1, BlockIngotBlock.LEAD)});
		GameRegistry.addShapelessRecipe(new ItemStack(AdvancedUtilitiesItems.ingot, 9, ItemIngot.ALUMINUM), new Object[]{new ItemStack(ingotBlock, 1, BlockIngotBlock.ALUMINUM)});
		GameRegistry.addShapelessRecipe(new ItemStack(AdvancedUtilitiesItems.ingot, 9, ItemIngot.TUNGSTEN), new Object[]{new ItemStack(ingotBlock, 1, BlockIngotBlock.TUNGSTEN)});
		GameRegistry.addShapelessRecipe(new ItemStack(AdvancedUtilitiesItems.ingot, 9, ItemIngot.PLATINUM), new Object[]{new ItemStack(ingotBlock, 1, BlockIngotBlock.PLATINUM)});
		GameRegistry.addShapelessRecipe(new ItemStack(AdvancedUtilitiesItems.ingot, 9, ItemIngot.IRIDIUM), new Object[]{new ItemStack(ingotBlock, 1, BlockIngotBlock.IRIDIUM)});
		GameRegistry.addShapelessRecipe(new ItemStack(AdvancedUtilitiesItems.ingot, 9, ItemIngot.PALIDIUM), new Object[]{new ItemStack(ingotBlock, 1, BlockIngotBlock.PALIDIUM)});
		GameRegistry.addShapelessRecipe(new ItemStack(AdvancedUtilitiesItems.ingot, 9, ItemIngot.STEEL), new Object[]{new ItemStack(ingotBlock, 1, BlockIngotBlock.STEEL)});
		GameRegistry.addShapelessRecipe(new ItemStack(AdvancedUtilitiesItems.ingot, 9, ItemIngot.NICKEL), new Object[]{new ItemStack(ingotBlock, 1, BlockIngotBlock.NICKEL)});
		
		GameRegistry.addRecipe(new ItemStack(steamBoiler, 1), new Object[]{
			" B ", "WMW", " B ", 'M',new ItemStack(machineBase, 1, BlockMachineBase.BRONZEMACHINE), 'B', Items.bucket, 'W', Items.water_bucket
		});
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(steamCrusher, 1), new Object[]{
			" I ", "RPR", " M ", 'M', new ItemStack(machineBase, 1, BlockMachineBase.BRONZEMACHINE), 'I', "blockIron", 'R', AdvancedUtilitiesItems.brassRivets, 'P', new ItemStack(AdvancedUtilitiesItems.plate, 1, 0)
		}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(steamFurnace, 1), new Object[]{
			" M ", "RPR", " C ", 'M', new ItemStack(machineBase, 1, BlockMachineBase.BRONZEMACHINE), 'C', "blockCoal", 'R', AdvancedUtilitiesItems.brassRivets, 'P', new ItemStack(AdvancedUtilitiesItems.plate, 1, 1)
		}));
		GameRegistry.addRecipe(new ItemStack(steamSmeltry, 1), new Object[]{
			"RPR", "CMC", " P ", 'M', new ItemStack(machineBase, 1, BlockMachineBase.BRONZEMACHINE), 'C', Blocks.clay, 'R', AdvancedUtilitiesItems.brassRivets, 'P', new ItemStack(AdvancedUtilitiesItems.plate, 1, 2)
		});
		GameRegistry.addRecipe(new ItemStack(bellows, 1), new Object[]{
			"RPR", "L L", "RPR", 'M', new ItemStack(machineBase, 1, BlockMachineBase.BRONZEMACHINE), 'L', Items.leather, 'R', AdvancedUtilitiesItems.brassRivets, 'P', new ItemStack(AdvancedUtilitiesItems.plate, 1, 1)
		});
		GameRegistry.addRecipe(new ItemStack(steamCompressor, 1), new Object[]{
			" P ", " M ", " P ", 'M', new ItemStack(machineBase, 1, BlockMachineBase.BRONZEMACHINE), 'P', new ItemStack(AdvancedUtilitiesItems.plate, 1, 1)
		});
		GameRegistry.addRecipe(new ItemStack(blockTank, 1) , new Object[]{
			"GGG","GMG","GGG",Character.valueOf('G'), Blocks.glass, 'M', new ItemStack(machineBase, 1, BlockMachineBase.BRONZEMACHINE)
		});
		GameRegistry.addRecipe(new ItemStack(fluidTube, 16) , new Object[]{
			" P ","P P"," P ",Character.valueOf('P'), new ItemStack(AdvancedUtilitiesItems.plate, 1, 1)
		});
		GameRegistry.addShapedRecipe(new ItemStack(blockTrash, 1) , new Object[]{
			"RSR","OCO","OOO",Character.valueOf('R'), Blocks.stone, Character.valueOf('S'), Items.stick, Character.valueOf('O'), Blocks.cobblestone, Character.valueOf('C'), Blocks.chest
		});
		GameRegistry.addShapedRecipe(new ItemStack(blockPlacer, 1) , new Object[]{
			"DRD","RDR","DRD",Character.valueOf('R'), Blocks.redstone_block, Character.valueOf('D'), Blocks.dispenser
		});
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(blockGrower, 1), new Object[]{
			"SRS","RDR","SRS", 'R', Blocks.redstone_block, 'S', "treeSapling", 'D', Blocks.dispenser
		}));
		GameRegistry.addRecipe(new ItemStack(elevator, 1) , new Object[]{
			"FFF","FMF","FFF",Character.valueOf('M'), new ItemStack(machineBase, 1), 'F',Items.feather
		});
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.chest, 4), new Object[]{
			"LLL", "L L", "LLL", 'L', "treeWood"
		}));
		
		registerPortaChestRecipes();
		
	}
	
	public static void registerPortaChestRecipes()
	{
		ItemStack woodChest = new ItemStack(portaChestWood,1,TileEntityPortaChest.WOOD);
		NBTTagCompound woodTag = new NBTTagCompound();
		woodTag.setInteger("chestType", TileEntityPortaChest.WOOD);
		woodChest.setTagCompound(woodTag);
		ShapedOreRecipe woodRecipe = new ShapedOreRecipe(woodChest, new Object[] {
				"LPL","PCP","LPL", 'L', "treeWood", 'C', Blocks.chest, 'P', "plankWood"
		});
		GameRegistry.addRecipe(woodRecipe);
		
		ItemStack bronzeChest = new ItemStack(portaChestBronze,1,TileEntityPortaChest.BRONZE);
		NBTTagCompound bronzeTag = new NBTTagCompound();
		bronzeTag.setInteger("chestType", TileEntityPortaChest.BRONZE);
		bronzeChest.setTagCompound(bronzeTag);
		ItemStack[] bronzeCrafting = new ItemStack[9];
		bronzeCrafting[0] = new ItemStack(AdvancedUtilitiesItems.plate,1,1);
		bronzeCrafting[1] = new ItemStack(AdvancedUtilitiesItems.plate,1,1);
		bronzeCrafting[2] = new ItemStack(AdvancedUtilitiesItems.plate,1,1);
		bronzeCrafting[3] = new ItemStack(AdvancedUtilitiesItems.plate,1,1);
		bronzeCrafting[4] = woodChest;
		bronzeCrafting[5] = new ItemStack(AdvancedUtilitiesItems.plate,1,1);
		bronzeCrafting[6] = new ItemStack(AdvancedUtilitiesItems.plate,1,1);
		bronzeCrafting[7] = new ItemStack(AdvancedUtilitiesItems.plate,1,1);
		bronzeCrafting[8] = new ItemStack(AdvancedUtilitiesItems.plate,1,1);
		RecipePortaChest bronzeRecipe = new RecipePortaChest(bronzeCrafting, bronzeChest);
		GameRegistry.addRecipe(bronzeRecipe);
		
		ItemStack goldChest = new ItemStack(portaChestGold,1,TileEntityPortaChest.GOLD);
		NBTTagCompound goldTag = new NBTTagCompound();
		goldTag.setInteger("chestType", TileEntityPortaChest.GOLD);
		goldChest.setTagCompound(goldTag);
		ItemStack[] goldCrafting = new ItemStack[9];
		goldCrafting[0] = new ItemStack(Items.gold_ingot, 1, 0);
		goldCrafting[1] = new ItemStack(Items.gold_ingot, 1, 0);
		goldCrafting[2] = new ItemStack(Items.gold_ingot, 1, 0);
		goldCrafting[3] = new ItemStack(Items.gold_ingot, 1, 0);
		goldCrafting[4] = bronzeChest;
		goldCrafting[5] = new ItemStack(Items.gold_ingot, 1, 0);
		goldCrafting[6] = new ItemStack(Items.gold_ingot, 1, 0);
		goldCrafting[7] = new ItemStack(Items.gold_ingot, 1, 0);
		goldCrafting[8] = new ItemStack(Items.gold_ingot, 1, 0);
		RecipePortaChest goldRecipe = new RecipePortaChest(goldCrafting, goldChest);
		/*NBTShapedOreRecipe gold2recipe = new NBTShapedOreRecipe(goldChest, new Object[] {
				"GGG","GBG","GGG", 'G', Items.gold_ingot, 'B', new ItemStack(portaChestBronze, 1, TileEntityPortaChest.BRONZE)
		});*/
		GameRegistry.addRecipe(goldRecipe);
		
		ItemStack diamondChest = new ItemStack(portaChestDiamond,1,TileEntityPortaChest.DIAMOND);
		NBTTagCompound diamondTag = new NBTTagCompound();
		diamondTag.setInteger("chestType", TileEntityPortaChest.DIAMOND);
		diamondChest.setTagCompound(diamondTag);
		ItemStack[] diamondCrafting = new ItemStack[9];
		diamondCrafting[0] = new ItemStack(Items.diamond, 1);
		diamondCrafting[1] = new ItemStack(Items.diamond, 1);
		diamondCrafting[2] = new ItemStack(Items.diamond, 1);
		diamondCrafting[3] = new ItemStack(Items.diamond, 1);
		diamondCrafting[4] = goldChest;
		diamondCrafting[5] = new ItemStack(Items.diamond, 1);
		diamondCrafting[6] = new ItemStack(Items.diamond, 1);
		diamondCrafting[7] = new ItemStack(Items.diamond, 1);
		diamondCrafting[8] = new ItemStack(Items.diamond, 1);
		RecipePortaChest diamondRecipe = new RecipePortaChest(diamondCrafting, diamondChest);
		GameRegistry.addRecipe(diamondRecipe);
		
		ItemStack platinumChest = new ItemStack(portaChestPlatinum,1, TileEntityPortaChest.PLATINUM);
		NBTTagCompound platinumTag = new NBTTagCompound();
		platinumTag.setInteger("chestType", TileEntityPortaChest.PLATINUM);
		platinumChest.setTagCompound(platinumTag);
		ItemStack[] platinumCrafting = new ItemStack[9];
		platinumCrafting[0] = new ItemStack(AdvancedUtilitiesItems.ingot, 1,ItemIngot.PLATINUM);
		platinumCrafting[1] = new ItemStack(AdvancedUtilitiesItems.ingot, 1,ItemIngot.PLATINUM);
		platinumCrafting[2] = new ItemStack(AdvancedUtilitiesItems.ingot, 1,ItemIngot.PLATINUM);
		platinumCrafting[3] = new ItemStack(AdvancedUtilitiesItems.ingot, 1,ItemIngot.PLATINUM);
		platinumCrafting[4] = diamondChest;
		platinumCrafting[5] = new ItemStack(AdvancedUtilitiesItems.ingot, 1,ItemIngot.PLATINUM);
		platinumCrafting[6] = new ItemStack(AdvancedUtilitiesItems.ingot, 1,ItemIngot.PLATINUM);
		platinumCrafting[7] = new ItemStack(AdvancedUtilitiesItems.ingot, 1,ItemIngot.PLATINUM);
		platinumCrafting[8] = new ItemStack(AdvancedUtilitiesItems.ingot, 1,ItemIngot.PLATINUM);
		RecipePortaChest platinumRecipe = new RecipePortaChest(platinumCrafting, platinumChest);
		GameRegistry.addRecipe(platinumRecipe);
	}
}
