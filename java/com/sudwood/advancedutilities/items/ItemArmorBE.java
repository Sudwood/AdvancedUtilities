package com.sudwood.advancedutilities.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemArmorBE extends ItemArmor
{
	int type;
	public ItemArmorBE(ArmorMaterial material, int render, int type) 
	{
		super(material, render, type);
		this.type = type;
		// TODO Auto-generated constructor stub
	}
	
	 	@SideOnly(Side.CLIENT)
	    public void registerIcons(IIconRegister par1IconRegister)
	    {
	        if(type == 0)//helm
	        {
	        	this.itemIcon = par1IconRegister.registerIcon("advancedutilities:bronze_helm");
	        }
	        if(type == 1)//chest
	        {
	        	this.itemIcon = par1IconRegister.registerIcon("advancedutilities:bronze_chest");
	        }
	        if(type == 2)//legs
	        {
	        	this.itemIcon = par1IconRegister.registerIcon("advancedutilities:bronze_legs");
	        }
	        if(type == 3)//boots
	        {
	        	this.itemIcon = par1IconRegister.registerIcon("advancedutilities:bronze_boots");
	        }
	    }
	 	
	 	@Override
	 	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type){
	 		if(stack.getItem() == AdvancedUtilitiesItems.bronzeBoots || stack.getItem() == AdvancedUtilitiesItems.bronzeChest || stack.getItem() == AdvancedUtilitiesItems.bronzeHelm)
	 		{
	 		return "advancedutilities:textures/items/bronze_1.png";
	 		}
	 		if(stack.getItem() == AdvancedUtilitiesItems.bronzeLegs){
	 		return "advancedutilities:textures/items/bronze_2.png";
	 		}
	 		else return null;

	 		};
	
}
