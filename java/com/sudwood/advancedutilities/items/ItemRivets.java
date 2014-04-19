package com.sudwood.advancedutilities.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

public class ItemRivets extends Item
{
	int type;
	public ItemRivets(int type)
	{
		this.type = type;
	}
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister)
    {
		switch(type)
		{
		case 0:
			this.itemIcon = par1IconRegister.registerIcon("advancedutilities:brassfittings");
			break;
		case 1:
			this.itemIcon = par1IconRegister.registerIcon("advancedutilities:stonefittings");
			break;
		}
        
    }
}
