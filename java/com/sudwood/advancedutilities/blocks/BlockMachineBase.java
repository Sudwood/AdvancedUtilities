package com.sudwood.advancedutilities.blocks;

import java.util.List;
import java.util.Random;

import com.sudwood.advancedutilities.AdvancedUtilities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class BlockMachineBase extends Block
{
	private IIcon[] icons = new IIcon[2];
	public static int BRONZEMACHINE = 0;
	public static int STEELMACHINE = 1;
	protected BlockMachineBase(Material mat) 
	{
		super(mat);
	}
	  public int quantityDropped(Random p_149745_1_)
	    {
	       return 1;
	    }
	    @Override
	    @SideOnly(Side.CLIENT)
	    public void getSubBlocks(Item item, CreativeTabs tab, List subItems)
	    {
	    	for (int ix = 0; ix < 2; ix++) {
	    		subItems.add(new ItemStack(this, 1, ix));
	    		item.setCreativeTab(AdvancedUtilities.advancedBEMachinesTab);
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
	        	icons[0] = icon.registerIcon("advancedutilities:bronzemachine");
	        	icons[1] = icon.registerIcon("advancedutilities:steelmachine");

	    }
	    
	    @Override
	    public int damageDropped (int metadata) {
	    	return metadata;
	    }
}
