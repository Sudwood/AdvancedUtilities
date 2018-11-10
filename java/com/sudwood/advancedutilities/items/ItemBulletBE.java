package com.sudwood.advancedutilities.items;

import java.util.List;

import com.sudwood.advancedutilities.AdvancedUtilities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;

public class ItemBulletBE extends Item
{
	
	private String[] names = {"BronzeBullet", "SteelBullet"};
	private IIcon[] icons = new IIcon[2];
	int type;
	public ItemBulletBE(int type)
	{
		this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.maxStackSize = 64;
        this.type = type;
	}
	
	/**
     * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
     * different names based on their damage or NBT.
     */
    public String getUnlocalizedName(ItemStack par1ItemStack)
    {
        return super.getUnlocalizedName() + "." + names[type];
    }
    
    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
	
    public CreativeTabs[] getCreativeTabs()
    {
        return new CreativeTabs[]{ AdvancedUtilities.advancedBEToolsTab };
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int par1)
    {
        return icons[type];
    }
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister)
    {
    	this.icons[0] = par1IconRegister.registerIcon(AdvancedUtilities.MODID+":"+"bullet");
    	this.icons[1] = par1IconRegister.registerIcon(AdvancedUtilities.MODID+":"+"bullettung");
    }
}
