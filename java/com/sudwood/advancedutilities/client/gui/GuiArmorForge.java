package com.sudwood.advancedutilities.client.gui;

import java.util.Arrays;
import java.util.List;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.sudwood.advancedutilities.AdvancedUtilities;
import com.sudwood.advancedutilities.container.ContainerArmorForge;
import com.sudwood.advancedutilities.packets.PacketForge;
import com.sudwood.advancedutilities.tileentity.TileEntityArmorForge;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiArmorForge extends GuiContainer
{
    private static final ResourceLocation furnaceGuiTextures = new ResourceLocation("advancedutilities","textures/gui/toolforgegui.png");
    private TileEntityArmorForge tile;
    private static final String __OBFID = "CL_00000758";

    public GuiArmorForge(InventoryPlayer par1InventoryPlayer, TileEntityArmorForge par2TileEntityArmorForge)
    {
        super(new ContainerArmorForge(par1InventoryPlayer, par2TileEntityArmorForge));
        this.tile = par2TileEntityArmorForge;
    }
    
    @Override
    public void initGui()
    {
    super.initGui();
    this.buttonList.clear();

    int posX = (this.width - 256) / 2;
    int posY = (this.height - 180) / 2;

    this.buttonList.add(new GuiButton(0, posX+ 106, posY + 62, 36, 20, "Craft!"));
    }
    
    @Override
    public void actionPerformed(GuiButton button)
    {
	    if(button.id == 0)
	    {
	    	AdvancedUtilities.network.sendToServer(new PacketForge(tile.getWorldObj().provider.dimensionId, tile.xCoord, tile.yCoord, tile.zCoord, 1));
	    }
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_)
    {
        this.fontRendererObj.drawString("Armor Forge", this.xSize / 3 - this.fontRendererObj.getStringWidth("Tool Forge") / 2, 6, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 96 + 2, 4210752);
    }

    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(furnaceGuiTextures);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        int i1;
        
        i1 = this.tile.getCookProgressScaled(22);
        this.drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 + 1, 16);
    }
    
    @Override
    public void drawScreen(int par1, int par2, float par3)
    {
    	super.drawScreen(par1, par2, par3);
    	int var5 = (this.width - this.xSize) / 2;
        int var6 = (this.height - this.ySize) / 2;
        if(par1 >= 80+var5 && par2 >= 35+var6 && par1 <= 101+var5 && par2 <= 50+var6)
		{
			String[] text = {"Progress: " + tile.furnaceCookTime + " / 1200"};
			List temp = Arrays.asList(text);
			drawHoveringText(temp, par1, par2, fontRendererObj);
		}

        if(par1 >= 42+var5 && par2 >= 27+var6 && par1 <= 57+var5 && par2 <= 42+var6)
		{
			String[] text = {"Armor Plates / Armor to Upgrade"};
			List temp = Arrays.asList(text);
			drawHoveringText(temp, par1-40, par2-15, fontRendererObj);
		}
        if(par1 >= 42+var5 && par2 >= 45+var6 && par1 <= 57+var5 && par2 <= 60+var6)
		{
			String[] text = {"Armor Rivets / Upgrade Item"};
			List temp = Arrays.asList(text);
			drawHoveringText(temp, par1-40, par2-15, fontRendererObj);
		}
        
    }
}