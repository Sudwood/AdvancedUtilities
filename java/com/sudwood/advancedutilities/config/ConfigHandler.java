package com.sudwood.advancedutilities.config;

import java.io.File;

import com.sudwood.advancedutilities.AdvancedUtilities;

import net.minecraftforge.common.config.Configuration;

public class ConfigHandler 
{
   public static void init(File configFile) 
   {
      Configuration config = new Configuration(configFile);
      
      config.load();
      
    //  Peanut.PeanutId = config.get("Mobs", "Peanut Id", 30).getInt();
      HudOptions.displayArmorHud = config.get("Gui", "Enable Armor Hud: ", true).getBoolean(true);
      HudOptions.displayToolHud = config.get("Gui", "Enable Tool Hud: ", true).getBoolean(true);
      HudOptions.displaySteamHud = config.get("Gui", "Enable Steam Hud: ", true).getBoolean(true);
      HudOptions.displayFluidHud = config.get("Gui", "Enable Fluid Hud", true).getBoolean(true);
      HudOptions.displayBlockHud = config.get("Gui", "Enable Block Hud", true).getBoolean(true);
      
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
      HudOptions.ArmorHudX = config.get("Gui", "Armor Hud X: ", 430).getInt();
      HudOptions.ArmorHudY = config.get("Gui", "Armor Hud Y: ", 180).getInt();
      HudOptions.ToolHudX = config.get("Gui", "Tool Hud X: ", 278).getInt();
      HudOptions.ToolHudY = config.get("Gui", "Tool Hud Y: ", 8).getInt();
      
      ServerOptions.copperSpawnRate = config.get("OreSpawnRate", "Copper Spawn Rate: ", 28).getInt();
      ServerOptions.tinSpawnRate = config.get("OreSpawnRate", "Tin Spawn Rate: ", 28).getInt();
      ServerOptions.zincSpawnRate = config.get("OreSpawnRate", "Zinc Spawn Rate: ", 28).getInt();
      ServerOptions.silverSpawnRate = config.get("OreSpawnRate", "Silver Spawn Rate: ", 24).getInt();
      ServerOptions.bauxiteSpawnRate = config.get("OreSpawnRate", "Bauxite Spawn Rate: ", 28).getInt();
      ServerOptions.leadSpawnRate = config.get("OreSpawnRate", "Lead Spawn Rate: ", 22).getInt();
      ServerOptions.tungstenSpawnRate = config.get("OreSpawnRate", "Tungsten Spawn Rate: ", 9).getInt();
      ServerOptions.platinumSpawnRate = config.get("OreSpawnRate", "Platinum Spawn Rate: ", 9).getInt();
      ServerOptions.nickelSpawnRate = config.get("OreSpawnRate", "Nickel Spawn Rate: ", 28).getInt();
      
      ServerOptions.steamCreationRate = config.get("MachineEnergy", "Steam Creation Rate: ", 5).getInt();
      
      AdvancedUtilities.steamName = config.get("Fluid", "Steam Name", "Steam").getString();
      config.save();
   }
}