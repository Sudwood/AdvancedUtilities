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

public class ItemToolPart extends Item
{
	private IIcon[] icons = new IIcon[13];
	
	private String[] names = {"BronzeRod","IronRod", "IronPickaxeHead", "IronSwordBlade", 
			"IronShovelHead","IronAxeHead", "BronzePickaxeHead", "BronzeSwordBlade", "BronzeShovelHead",
			"BronzeAxeHead", "StoneHammerHead", "BronzeHammerHead", "IronHammerHead"};
	
	public ItemToolPart()
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
        int i = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, 12);
        return super.getUnlocalizedName() + "." + names[i];
    }
    
    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tabs, List list)
    {
        for (int i = 0; i < 13; ++i)
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
        return this.icons[par1];
    }
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister)
    {
        this.icons[0] = par1IconRegister.registerIcon("advancedutilities:bronzerod");
        this.icons[1] = par1IconRegister.registerIcon("advancedutilities:ironrod");
        this.icons[2] = par1IconRegister.registerIcon("advancedutilities:ironpicktop");
        this.icons[3] = par1IconRegister.registerIcon("advancedutilities:ironswordtop");
        this.icons[4] = par1IconRegister.registerIcon("advancedutilities:ironshoveltop");
        this.icons[5] = par1IconRegister.registerIcon("advancedutilities:ironaxetop");
        this.icons[6] = par1IconRegister.registerIcon("advancedutilities:bronzepicktop");
        this.icons[7] = par1IconRegister.registerIcon("advancedutilities:bronzeswordtop");
        this.icons[8] = par1IconRegister.registerIcon("advancedutilities:bronzeshoveltop");
        this.icons[9] = par1IconRegister.registerIcon("advancedutilities:bronzeaxetop");
        this.icons[10] = par1IconRegister.registerIcon("advancedutilities:stonehammertop");
        this.icons[11] = par1IconRegister.registerIcon("advancedutilities:bronzehammertop");
        this.icons[12] = par1IconRegister.registerIcon("advancedutilities:ironhammertop");
    }
	
}
