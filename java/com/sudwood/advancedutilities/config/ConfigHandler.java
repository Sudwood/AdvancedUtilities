package com.sudwood.advancedutilities.config;

import java.io.File;

import com.sudwood.advancedutilities.AdvancedUtilities;
import com.sudwood.advancedutilities.recipes.CompressRecipes;
import com.sudwood.advancedutilities.recipes.CrushRecipes;
import com.sudwood.advancedutilities.recipes.KilnRecipes;
import com.sudwood.advancedutilities.recipes.SmeltryRecipes;
import com.sudwood.advancedutilities.recipes.SteamFurnaceRecipes;
import com.sudwood.advancedutilities.recipes.SteelOvenRecipes;

import net.minecraftforge.common.config.Configuration;

public class ConfigHandler 
{
   public static void init(Configuration configFile) 
   {
      Configuration config = configFile;
      
      config.load();
      
    //  Peanut.PeanutId = config.get("Mobs", "Peanut Id", 30).getInt();
      HudOptions.displayArmorHud = config.get("Gui", "Enable Armor Hud: ", true).getBoolean(true);
      HudOptions.displayToolHud = config.get("Gui", "Enable Tool Hud: ", true).getBoolean(true);
      HudOptions.displaySteamHud = config.get("Gui", "Enable Steam Hud: ", true).getBoolean(true);
      HudOptions.displayFluidHud = config.get("Gui", "Enable Fluid Hud", true).getBoolean(true);
      HudOptions.displayBlockHud = config.get("Gui", "Enable Block Hud", true).getBoolean(true);
      HudOptions.displayJetpackParticles = config.get("Gui", "Enable Jetpack Particles", true).getBoolean(true);
      HudOptions.playJetpackSounds = config.get("Gui", "Enable Jetpack Sounds", true).getBoolean(true);
      HudOptions.playBeltSounds = config.get("Gui", "Enable Climbing Belt Sounds", true).getBoolean(true);
      HudOptions.dynamicToggleText = config.get("Gui", "Enable Dynamic Re-position of Item Toggle text", true).getBoolean(true);
      HudOptions.displayToggleText = config.get("Gui", "Enable Toggle text", true).getBoolean(true);
      HudOptions.magnetAmuletParticles = config.get("Gui", "Enable Void ring particles", true).getBoolean(true);
      HudOptions.fluidX = config.get("Gui", "Fluid X", 163).getInt();
      HudOptions.fluidY = config.get("Gui", "Fluid Y", 2).getInt();
      HudOptions.steamBarX = config.get("Gui", "Steam Bar X: ", 2).getInt();
      HudOptions.steamBarY = config.get("Gui", "Steam Bar Y: ", 2).getInt();
      HudOptions.steamStringX = config.get("Gui", "Steam String X: ", 15).getInt();
      HudOptions.steamStringY = config.get("Gui", "Steam String Y: ", 2).getInt();
      HudOptions.steamToolX = config.get("Gui", "Steam Tool X: ", 15).getInt();
      HudOptions.steamToolY = config.get("Gui", "Steam Tool Y: ", 12).getInt();
      HudOptions.steamJetPackTimeX = config.get("Gui", "Steam Jetpack Time X: ", 15).getInt();
      HudOptions.steamJetPackTimeY = config.get("Gui", "Steam Jetpack Time Y: ", 12).getInt();
      HudOptions.steamJetPackEnabledX = config.get("Gui", "Steam Jetpack Enabled X: ", 2).getInt();
      HudOptions.steamJetPackEnabledY = config.get("Gui", "Steam Jetpack Enabled Y: ", 35).getInt();
      config.setCategoryComment("Gui", "1080p settings for tool hud x: 278 y:8 1440p x: 261 y: -12");
      HudOptions.ArmorHudX = config.get("Gui", "Armor Hud X: ", 430).getInt();
      HudOptions.ArmorHudY = config.get("Gui", "Armor Hud Y: ", 180).getInt();
      HudOptions.ToolHudX = config.get("Gui", "Tool Hud X: ", 278).getInt();
      HudOptions.ToolHudY = config.get("Gui", "Tool Hud Y: ", 8).getInt();
      HudOptions.steamJetpackHoverEnabledX = config.get("Gui", "Steam Jetpack Hover Enabled X: ", 2).getInt();
      HudOptions.steamJetpackHoverEnabledY = config.get("Gui", "Steam Jetpack Hover Enabled Y: ", 45).getInt();
      HudOptions.baubleEnabledX = config.get("Gui", "Baubles Enabled X: ", 2).getInt();
      HudOptions.baubleEnabledY = config.get("Gui", "Baubles Enabled Y: ", 55).getInt();
      
     
      
      config.setCategoryComment("OreSpawnRate", "The default rates are for replacing all the ores in a modpack");
      ServerOptions.copperSpawnRate = config.get("OreSpawnRate", "Copper Spawn Rate: ", 28).getInt(); // 28 28 28 24 28 22 9 9 28 for modpack
      ServerOptions.tinSpawnRate = config.get("OreSpawnRate", "Tin Spawn Rate: ", 28).getInt();
      ServerOptions.zincSpawnRate = config.get("OreSpawnRate", "Zinc Spawn Rate: ", 28).getInt();
      ServerOptions.silverSpawnRate = config.get("OreSpawnRate", "Silver Spawn Rate: ", 24).getInt();
      ServerOptions.bauxiteSpawnRate = config.get("OreSpawnRate", "Bauxite Spawn Rate: ", 28).getInt();
      ServerOptions.leadSpawnRate = config.get("OreSpawnRate", "Lead Spawn Rate: ", 22).getInt();
      ServerOptions.tungstenSpawnRate = config.get("OreSpawnRate", "Tungsten Spawn Rate: ", 9).getInt();
      ServerOptions.platinumSpawnRate = config.get("OreSpawnRate", "Platinum Spawn Rate: ", 9).getInt();
      ServerOptions.nickelSpawnRate = config.get("OreSpawnRate", "Nickel Spawn Rate: ", 28).getInt();
      ServerOptions.spawnOil = config.getBoolean("Generate Oil", "Generate", true, "Set false to disable oil generation.");
      ServerOptions.spawnRubberTrees  = config.getBoolean("Generate Rubber Trees", "Generate", true, "Set false to disable rubber tree generation.");
      ServerOptions.steamCreationRate = config.get("MachineEnergy", "Steam Creation Rate: ", 5).getInt();
      
      AdvancedUtilities.steamName = config.get("Fluid", "Steam Name", "Steam").getString();
      config.setCategoryComment("SmeltryRecipes", "Accepts a String[] with each string being a recipe. Template is such modid:meltitemname,stacksize,stackdamage,isblock,modid:castitemname,stacksize,stackdamage,isblock,modid:resultitemname,stacksize,stackdamage,isblock");
      RecipeConfig.SmeltryRecipes = config.getStringList("Smeltry Recipes", "SmeltryRecipes", SmeltryRecipes.defaultRecipes, "Put Smeltry Recipes Here.");
      config.setCategoryComment("SteamFurnaceRecipes", "Accepts a String[] with each string being a recipe. Template is such modid:ingredient1itemname,stacksize,stackdamage,isblock,modid:ingredient2itemname,stacksize,stackdamage,isblock,modid:resultitemname,stacksize,stackdamage,isblock");
      RecipeConfig.SteamFurnaceRecipes = config.getStringList("Steam Furnace Recipes", "SteamFurnaceRecipes", SteamFurnaceRecipes.defaultRecipes, "Put Steam Furnace Recipes Here.");
      config.setCategoryComment("CompressRecipes", "Accepts a String[] with each string being a recipe. Template is such modid:ingredient1itemname,stacksize,stackdamage,isblock,modid:resultitemname,stacksize,stackdamage,isblock");
      RecipeConfig.CompressRecipes = config.getStringList("Compressor Recipes", "CompressRecipes", CompressRecipes.defaultRecipes, "Put Compressor Recipes Here.");
      
      config.setCategoryComment("KilnRecipes", "Accepts a String[] with each string being a recipe. Second item is used in bottom 3 slots. Template is such modid:Singleitemname,stacksize,stackdamage,isblock,modid:Trippleitemname,stacksize,stackdamage,isblock,modid:resultitemname,stacksize,stackdamage,isblock");
      RecipeConfig.KilnRecipes = config.getStringList("Kiln Recipes", "KilnRecipes", KilnRecipes.defaultRecipes, "Put Kiln Recipes Here.");
      
      config.setCategoryComment("CrushRecipes", "Accepts a String[] with each string being a recipe. Template is such modid:ingredient1itemname,stacksize,stackdamage,isblock,modid:resultitemname,stacksize,stackdamage,isblock");
      RecipeConfig.CrushRecipes = config.getStringList("Crusher Recipes", "CrushRecipes", CrushRecipes.defaultRecipes, "Put Crusher Recipes Here.");
      
      config.setCategoryComment("SteelOvenRecipes", "Accepts a String[] with each string being a recipe. Template is such modid:ingredient1itemname,stacksize,stackdamage,isblock,modid:ingredient2itemname,stacksize,stackdamage,isblock,modid:ingredient3itemname,stacksize,stackdamage,isblock,modid:ingredient4itemname,stacksize,stackdamage,isblock,modid:ingredient5itemname,stacksize,stackdamage,isblock,modid:resultitemname,stacksize,stackdamage,isblock");
      RecipeConfig.SteelOvenRecipes = config.getStringList("Steel Oven Recipes", "SteelOvenRecipes", SteelOvenRecipes.defaultRecipes, "Put Steel Oven Recipes Here.");
      config.save();
   }
}