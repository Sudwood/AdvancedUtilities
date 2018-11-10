package com.sudwood.advancedutilities.nei;

import com.sudwood.advancedutilities.AdvancedUtilities;
import com.sudwood.advancedutilities.client.gui.GuiKiln;
import com.sudwood.advancedutilities.client.gui.GuiSmeltry;
import com.sudwood.advancedutilities.client.gui.GuiSteamCrusher;
import com.sudwood.advancedutilities.client.gui.GuiSteelOven;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;

public class NEIAdvancedConfig implements IConfigureNEI
{

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "AdvancedUtilities Plugin";
	}

	@Override
	public String getVersion() {
		// TODO Auto-generated method stub
		return AdvancedUtilities.VERSION;
	}

	@Override
	public void loadConfig() 
	{
		API.registerRecipeHandler(new CrushRecipeHandler());
		API.registerUsageHandler(new CrushRecipeHandler());
		API.setGuiOffset(GuiSteamCrusher.class, 0, 0);
		API.registerRecipeHandler(new KilnRecipeHandler());
		API.registerUsageHandler(new KilnRecipeHandler());
		API.setGuiOffset(GuiKiln.class, 0, 0);
		API.registerRecipeHandler(new SmeltryRecipeHandler());
		API.registerUsageHandler(new SmeltryRecipeHandler());
		API.setGuiOffset(GuiSmeltry.class, 0, 0);
		
		API.registerRecipeHandler(new SteamFurnaceRecipeHandler());
		API.registerUsageHandler(new SteamFurnaceRecipeHandler());
		API.setGuiOffset(GuiSmeltry.class, 0, 0);
		
		API.registerRecipeHandler(new CompressRecipeHandler());
		API.registerUsageHandler(new CompressRecipeHandler());
		API.setGuiOffset(GuiSmeltry.class, 0, 0);
		
		API.registerRecipeHandler(new SteelOvenRecipeHandler());
		API.registerUsageHandler(new SteelOvenRecipeHandler());
		API.setGuiOffset(GuiSteelOven.class, 0, 0);
	}

}
