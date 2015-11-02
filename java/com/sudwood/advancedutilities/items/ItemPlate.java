package com.sudwood.advancedutilities.items;

import java.util.List;

import com.sudwood.advancedutilities.AdvancedUtilities;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemPlate extends Item
{
	private IIcon[] icons = new IIcon[5];
	public static final int IRONPLATE = 0;
	public static final int BRONZEPLATE = 1;
	public static final int BRASSPLATE = 2;
	public static final int STONEPLATE = 3;
	public static final int STEELPLATE = 4;
	
	private String[] names = {"IronPlate", "BronzePlate", "BrassPlate", "StonePlate", "SteelPlate"};
	
	public ItemPlate()
	{
		this.setHasSubtypes(true);
        this.setMaxDamage(0);
	}
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister)
    {
			this.icons[0] = par1IconRegister.registerIcon(AdvancedUtilities.MODID+":ironplate");
			this.icons[1] = par1IconRegister.registerIcon(AdvancedUtilities.MODID+":bronzeplate");
			this.icons[2] = par1IconRegister.registerIcon(AdvancedUtilities.MODID+":brassplate");
			this.icons[3] = par1IconRegister.registerIcon(AdvancedUtilities.MODID+":stoneplate");
			this.icons[4] = par1IconRegister.registerIcon(AdvancedUtilities.MODID+":steelplate");
    }
	
	/**
     * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
     * different names based on their damage or NBT.
     */
    public String getUnlocalizedName(ItemStack par1ItemStack)
    {
        int i = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, 4);
        return super.getUnlocalizedName() + "." + names[i];
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int par1)
    {
        return this.icons[par1];
    }
    
    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tabs, List list)
    {
        for (int i = 0; i < 5; ++i)
        {
            list.add(new ItemStack(item, 1, i));
            item.setCreativeTab(AdvancedUtilities.advancedTab);
        }
    }
	
    public CreativeTabs[] getCreativeTabs()
    {
        return new CreativeTabs[]{ AdvancedUtilities.advancedTab };
    }
        
}

