package com.sudwood.advancedutilities.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;

import com.sudwood.advancedutilities.AdvancedUtilities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemUpgrade extends Item
{
	private IIcon[] icons = new IIcon[16];
	public ItemUpgrade()
	{
		this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.maxStackSize = 64;
	}
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) 
	{
		String type = "";
		switch(par1ItemStack.getItemDamage())
		{
		case 0:
			type =  "Aqua Affinity";
			break;
		case 1:
			type = "Efficiency Level 1";
			break;
		case 2:
			type = "Efficiency Level 2";
			break;
		case 3:
			type = "Efficiency Level 3";
			break;
		case 4:
			type = "Feather Falling Level 1";
			break;
		case 5:
			type = "Feather Falling Level 2";
			break;
		case 6:
			type = "Feather Falling Level 3";
			break;
		case 7:
			type = "Luck Level 1";
			break;
		case 8:
			type = "Luck Level 2";
			break;
		case 9:
			type = "Luck Level 3";
			break;
		case 10:
			type = "Protection Level 1";
			break;
		case 11:
			type = "Protection Level 2";
			break;
		case 12:
			type = "Protection Level 3";
			break;
		case 13:
			type = "Sharpness Level 1";
			break;
		case 14:
			type = "Sharpness Level 2";
			break;
		case 15:
			type = "Sharpness Level 3";
			break;
		}
		par3List.add(type);
	}
    
    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tabs, List list)
    {
        for (int i = 0; i < 16; ++i)
        {
            list.add(new ItemStack(item, 1, i));
            item.setCreativeTab(AdvancedUtilities.advancedBEToolsTab);
        }
    }
	
    public CreativeTabs[] getCreativeTabs()
    {
        return new CreativeTabs[]{ AdvancedUtilities.advancedBEToolsTab };
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int par1)
    {
        return icons[par1];
    }
    
    @SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses()
    {
        return true;
    }
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister)
    {
    	this.icons[0] = par1IconRegister.registerIcon(AdvancedUtilities.MODID+":"+"upgradeaa");
    	this.icons[1] = par1IconRegister.registerIcon(AdvancedUtilities.MODID+":"+"upgradeeff1");
    	this.icons[2] = par1IconRegister.registerIcon(AdvancedUtilities.MODID+":"+"upgradeeff2");
    	this.icons[3] = par1IconRegister.registerIcon(AdvancedUtilities.MODID+":"+"upgradeeff3");
    	this.icons[4] = par1IconRegister.registerIcon(AdvancedUtilities.MODID+":"+"upgradeff1");
    	this.icons[5] = par1IconRegister.registerIcon(AdvancedUtilities.MODID+":"+"upgradeff2");
    	this.icons[6] = par1IconRegister.registerIcon(AdvancedUtilities.MODID+":"+"upgradeff3");
    	this.icons[7] = par1IconRegister.registerIcon(AdvancedUtilities.MODID+":"+"upgradel1");
    	this.icons[8] = par1IconRegister.registerIcon(AdvancedUtilities.MODID+":"+"upgradel2");
    	this.icons[9] = par1IconRegister.registerIcon(AdvancedUtilities.MODID+":"+"upgradel3");
    	this.icons[10] = par1IconRegister.registerIcon(AdvancedUtilities.MODID+":"+"upgradepro1");
    	this.icons[11] = par1IconRegister.registerIcon(AdvancedUtilities.MODID+":"+"upgradepro2");
    	this.icons[12] = par1IconRegister.registerIcon(AdvancedUtilities.MODID+":"+"upgradepro3");
    	this.icons[13] = par1IconRegister.registerIcon(AdvancedUtilities.MODID+":"+"upgradesha1");
    	this.icons[14] = par1IconRegister.registerIcon(AdvancedUtilities.MODID+":"+"upgradesha2");
    	this.icons[15] = par1IconRegister.registerIcon(AdvancedUtilities.MODID+":"+"upgradesha3");
    }
}
