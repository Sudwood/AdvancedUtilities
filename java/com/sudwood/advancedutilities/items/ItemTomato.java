package com.sudwood.advancedutilities.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;

public class ItemTomato extends ItemFood
{
	public ItemTomato(int healAmount, float saturation, boolean wolf)
    {
        super(healAmount, saturation, wolf);
        this.setCreativeTab(CreativeTabs.tabFood);
    }
	@SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister icon)
    {
		this.itemIcon = icon.registerIcon("advancedutilities:copperingot");
    }
	
}
