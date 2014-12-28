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

public class ItemDust extends Item
{
	//private IIcon[] icons = new IIcon[18];
	private IIcon tinyDust;
	private String[] names = {"CopperDust","IronDust", "TinDust", "ZincDust", "BronzeDust",
			"BrassDust", "GoldDust", "DiamondDust", "CoalDust", "SilverDust", "PlatinumDust", 
			"AluminumDust", "LeadDust", "TungstenDust", "IridiumDust", "PalidiumDust", "SteelDust", 
			"TinyDiamondDust", "Flour", "SawDust", "NickelDust"};
	
	public ItemDust()
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
        int i = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, 20);
        return super.getUnlocalizedName() + "." + names[i];
    }
    
    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tabs, List list)
    {
        for (int i = 0; i < 21; ++i)
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
		if(par1!= 17)
			return this.itemIcon;
		else
        return this.tinyDust;
    }
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister)
    {
        this.itemIcon = par1IconRegister.registerIcon(AdvancedUtilities.MODID+":dust");
        this.tinyDust = par1IconRegister.registerIcon(AdvancedUtilities.MODID+":tinydust");
    }
	
	@SideOnly(Side.CLIENT)
	@Override
    public int getColorFromItemStack(ItemStack stack, int par2)
    {
        switch(stack.getItemDamage())
        {
        case 0: // copper
        	return 16741657;
        case 1: // iron
        	return 14860458; 
        case 2: // tin
        	return 15066597;
        case 3: // zinc
        	return 15725042;
        case 4: // bronze
        	return 11553034;
        case 5: // brass
        	return 13923865;
        case 6: // gold
        	return 16777095;
        case 7: // diamond
        	return 13101054;
        case 8: // coal
        	return 4210752;
        case 9: // silver
        	return 11776947;
        case 10: // platinum
        	return 9474194;
        case 11: // aluminum
        	return 11383485;
        case 12: // lead
        	return 6842441;
        case 13: // tungsten
        	return 3426607;
        case 14: // iridium
        	return 15658734;
        case 15: // palidium
        	return 13553885;
        case 16: // steel
        	return 6250335;
        case 17: // tiny diamond
        	return 13101054;
        case 18:
        	return 16775631;
        case 19:
        	return 8216100;
        case 20:
        	return 10138981;
        default: return 0;
        }
    }
	
}
