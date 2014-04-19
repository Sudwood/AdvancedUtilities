package com.sudwood.advancedutilities.items;

import com.sudwood.advancedutilities.AdvancedUtilities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

public class ItemBulletBE extends Item
{
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister)
    {
		this.itemIcon = par1IconRegister.registerIcon(AdvancedUtilities.MODID+":"+"bullet");
    }
}
