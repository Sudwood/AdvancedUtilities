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

public class ItemBulletHead extends Item
{
	private String[] names = {"StoneHead", "LeadBullet","TungstenBullet"};
	private IIcon[] icons = new IIcon[3];
	public ItemBulletHead()
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
        int i = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, 2);
        return super.getUnlocalizedName() + "." + names[i];
    }
    
    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tabs, List list)
    {
        for (int i = 0; i < 3; ++i)
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
    	if(par1<icons.length)
        return icons[par1];
    	else return null;
    }
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister)
    {
    	this.icons[0] = par1IconRegister.registerIcon(AdvancedUtilities.MODID+":"+"stonehead");
    	this.icons[1] = par1IconRegister.registerIcon(AdvancedUtilities.MODID+":"+"leadbullet");
    	this.icons[2] = par1IconRegister.registerIcon(AdvancedUtilities.MODID+":"+"tungstenbullet");
    }
}
