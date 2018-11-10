package com.sudwood.advancedutilities.client.gui;

import com.sudwood.advancedutilities.AdvancedUtilities;

import cpw.mods.fml.client.config.GuiConfig;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;

public class GuiConfigAdvanced extends GuiConfig
{
	public GuiConfigAdvanced(GuiScreen parent) 
    {
        super(parent, new ConfigElement(AdvancedUtilities.config.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(), AdvancedUtilities.MODID, false, false, GuiConfig.getAbridgedConfigPath(AdvancedUtilities.config.toString()));
    }
    
    @Override
    public void initGui()
    {
        // You can add buttons and initialize fields here
        super.initGui();
    }

    
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        // You can do things like create animations, draw additional elements, etc. here
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button)
    {
        // You can process any additional buttons you may have added here
        super.actionPerformed(button);
    }

}
