package com.sudwood.advancedutilities.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import com.sudwood.advancedutilities.AdvancedUtilities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockOre extends Block
{
	private IIcon[] icons = new IIcon[9];
	
	public static int COPPER = 0;
	public static int TIN = 1;
	public static int ZINC = 2;
	public static int NICKEL = 8;
	public static int SILVER = 3;
	public static int LEAD = 4;
	public static int BAUXITE = 5;
	public static int TUNGSTEN = 6;
	public static int PLATINUM = 7;
	protected BlockOre(Material mat) 
	{
		super(mat);
		this.setHarvestLevel("pickaxe", 1, 0);
		this.setHarvestLevel("pickaxe", 1, 1);
		this.setHarvestLevel("pickaxe", 1, 2);
		this.setHarvestLevel("pickaxe", 1, 8);
		this.setHarvestLevel("pickaxe", 2, 3);
		this.setHarvestLevel("pickaxe", 2, 4);
		this.setHarvestLevel("pickaxe", 2, 5);
		this.setHarvestLevel("pickaxe", 3, 6);
		this.setHarvestLevel("pickaxe", 3, 7);
	}
	// public void setHarvestLevel(String toolClass, int level, int metadata)
	    /**
	     * Returns the quantity of items to drop on block destruction.
	     */
    public int quantityDropped(Random p_149745_1_)
    {
       return 1;
    }
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List subItems)
    {
    	for (int ix = 0; ix < 9; ix++) {
    		subItems.add(new ItemStack(this, 1, ix));
    		item.setCreativeTab(AdvancedUtilities.advancedTab);
    	}
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int meta)
    {
        return icons[meta];
    }
    
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister icon)
    {
        	icons[0] = icon.registerIcon("advancedutilities:copper"+AdvancedUtilities.textureSize);
        	icons[1] = icon.registerIcon("advancedutilities:tin"+AdvancedUtilities.textureSize);
        	icons[2] = icon.registerIcon("advancedutilities:zinc"+AdvancedUtilities.textureSize);
        	icons[3] = icon.registerIcon("advancedutilities:silver"+AdvancedUtilities.textureSize);
        	icons[4] = icon.registerIcon("advancedutilities:lead"+AdvancedUtilities.textureSize);
        	icons[5] = icon.registerIcon("advancedutilities:bauxite"+AdvancedUtilities.textureSize);
        	icons[6] = icon.registerIcon("advancedutilities:tungsten"+AdvancedUtilities.textureSize);
        	icons[7] = icon.registerIcon("advancedutilities:platinum"+AdvancedUtilities.textureSize);
        	icons[8] = icon.registerIcon("advancedutilities:nickel");
    }
    
    @Override
    public int damageDropped (int metadata) {
    	return metadata;
    }
    
    
}
