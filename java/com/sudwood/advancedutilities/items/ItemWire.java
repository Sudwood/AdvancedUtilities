package com.sudwood.advancedutilities.items;

import java.util.List;

import com.sudwood.advancedutilities.AdvancedUtilities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemWire extends Item
{
	//private IIcon[] icons = new IIcon[18];
	private String[] names = {"Stone","Copper","Iron", "Gold"};
	
	public ItemWire()
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
        int i = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, 3);
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
            item.setCreativeTab(AdvancedUtilities.advancedBEMachinesTab);
        }
    }
	
    public CreativeTabs[] getCreativeTabs()
    {
        return new CreativeTabs[]{ AdvancedUtilities.advancedBEMachinesTab };
    }
    
	@SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int par1)
    {
        return this.itemIcon;
    }
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister)
    {
        this.itemIcon = par1IconRegister.registerIcon(AdvancedUtilities.MODID+":wire");
    }
	
	@SideOnly(Side.CLIENT)
	@Override
    public int getColorFromItemStack(ItemStack stack, int par2)
    {
        switch(stack.getItemDamage())
        {
        case 0:
        	return 11579568;
        case 1: // copper
        	return 16741657; 
        case 2: // iron
        	return 14860458;
        case 3: // gold
        	return 16777095;
        default: return 0;
        }
    }
	
}
