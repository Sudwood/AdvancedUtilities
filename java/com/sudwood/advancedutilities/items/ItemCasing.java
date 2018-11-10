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

public class ItemCasing extends Item
{
	private String[] names = {"StoneCasing", "BronzeCasing", "SteamCanister","SteelCasing"};
	private IIcon[] icons = new IIcon[4];
	public ItemCasing()
	{
		this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.maxStackSize = 64;
	}
	
	/**
     * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
     * different names based on their damage or NBT.
     */
    public String getUnlocalizedName(ItemStack par1ItemStack)
    {
        int i = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, 3 );
        return super.getUnlocalizedName() + "." + names[i];
    }
    
    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tabs, List list)
    {
        for (int i = 0; i < 4; ++i)
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
    public void registerIcons(IIconRegister par1IconRegister)
    {
    	this.icons[0] = par1IconRegister.registerIcon(AdvancedUtilities.MODID+":"+"stonecasing");
    	this.icons[1] = par1IconRegister.registerIcon(AdvancedUtilities.MODID+":"+"bronzebulletcase");
    	this.icons[2] = par1IconRegister.registerIcon(AdvancedUtilities.MODID+":"+"steamcanister");
    	this.icons[3] = par1IconRegister.registerIcon(AdvancedUtilities.MODID+":"+"steelbulletcase");
    }
}
