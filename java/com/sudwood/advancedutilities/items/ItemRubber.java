package com.sudwood.advancedutilities.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemRubber extends Item
{
	private int type;
	public ItemRubber(int type)
	{
		super();
		this.type = type;
		this.setMaxStackSize(64);
	}
	
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister)
    {
    	if(type == 0)
    		this.itemIcon = par1IconRegister.registerIcon("advancedutilities:rubberitem");
    	if(type == 1)
    		this.itemIcon = par1IconRegister.registerIcon("advancedutilities:glueball");
	}
    
}
