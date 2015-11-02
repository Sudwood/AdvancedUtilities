package com.sudwood.advancedutilities.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;

import com.sudwood.advancedutilities.AdvancedUtilities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemIngot extends Item
{
	public IIcon[] icons = new IIcon[14];
	public static final int COPPER = 0;
	public static final int TIN = 1;
	public static final int BRONZE = 2;
	public static final int ZINC = 3;
	public static final int BRASS = 4;
	public static final int SILVER = 5;
	public static final int LEAD = 6;
	public static final int ALUMINUM = 7;
	public static final int TUNGSTEN = 8;
	public static final int PLATINUM = 9;
	public static final int IRIDIUM = 10;
	public static final int PALIDIUM = 11;
	public static final int STEEL = 12;
	public static final int NICKEL = 13;
	private String[] names = {"CopperIngot","TinIngot","BronzeIngot","ZincIngot","BrassIngot","SilverIngot",
			"LeadIngot","AluminumIngot", "TungstenIngot", "PlatinumIngot","IridiumIngot", "PalidiumIngot","SteelIngot","NickelIngot"
	};
	public ItemIngot()
	{
		this.setHasSubtypes(true);
        this.setMaxDamage(0);
	}
	
	/**
     * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
     * different names based on their damage or NBT.
     */
    public String getUnlocalizedName(ItemStack par1ItemStack)
    {
        int i = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, 13);
        return super.getUnlocalizedName() + "." + names[i];
    }
    
    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tabs, List list)
    {
        for (int i = 0; i < 14; ++i)
        {
            list.add(new ItemStack(item, 1, i));
            item.setCreativeTab(AdvancedUtilities.advancedTab);
        }
    }
	
    public CreativeTabs[] getCreativeTabs()
    {
        return new CreativeTabs[]{ AdvancedUtilities.advancedTab };
    }
	
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int par1)
    {
    	return icons[par1];
    }
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister)
    {
			icons[0] = par1IconRegister.registerIcon("advancedutilities:copperingot"+AdvancedUtilities.textureSize);
			icons[1] = par1IconRegister.registerIcon("advancedutilities:tiningot"+AdvancedUtilities.textureSize);
			icons[2] = par1IconRegister.registerIcon("advancedutilities:bronzeingot"+AdvancedUtilities.textureSize);
			icons[3] = par1IconRegister.registerIcon("advancedutilities:zincingot"+AdvancedUtilities.textureSize);
			icons[4] = par1IconRegister.registerIcon("advancedutilities:brassingot"+AdvancedUtilities.textureSize);
			icons[5] = par1IconRegister.registerIcon("advancedutilities:silveringot"+AdvancedUtilities.textureSize);
			icons[6] = par1IconRegister.registerIcon("advancedutilities:leadingot"+AdvancedUtilities.textureSize);
			icons[7] = par1IconRegister.registerIcon("advancedutilities:aluminumingot"+AdvancedUtilities.textureSize);
			icons[8] = par1IconRegister.registerIcon("advancedutilities:tungsteningot"+AdvancedUtilities.textureSize);
			icons[9] = par1IconRegister.registerIcon("advancedutilities:platinumingot"+AdvancedUtilities.textureSize);
			icons[10] = par1IconRegister.registerIcon("advancedutilities:iridiumingot"+AdvancedUtilities.textureSize);
			icons[11] = par1IconRegister.registerIcon("advancedutilities:palidiumingot"+AdvancedUtilities.textureSize);
			icons[12] = par1IconRegister.registerIcon("advancedutilities:steelingot"+AdvancedUtilities.textureSize);
			icons[13] = par1IconRegister.registerIcon("advancedutilities:nickelingot");
       
    }
}
