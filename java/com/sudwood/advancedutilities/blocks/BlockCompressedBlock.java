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
import net.minecraft.world.IBlockAccess;

public class BlockCompressedBlock extends Block
{
	private IIcon[] icons = new IIcon[2];
	public static int SINGLECHARCOAL = 0;
	public static int DOUBLECHARCOAL = 1;
	protected BlockCompressedBlock(Material mat) 
	{
		super(mat);
		// TODO Auto-generated constructor stub
	}
	 public boolean isOpaqueCube()
	    {
	        return false;
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
	    		item.setCreativeTab(AdvancedUtilities.advancedTab);
	    	}
	    }
	    
	    @SideOnly(Side.CLIENT)
	    public IIcon getIcon(int p_149691_1_, int meta)
	    {
	        return icons[meta];
	    }
	    
	    @Override
	    public int damageDropped (int metadata) {
	    	return metadata;
	    }
	    
	    @SideOnly(Side.CLIENT)
	    public void registerBlockIcons(IIconRegister icon)
	    {
	        	icons[0] = icon.registerIcon(AdvancedUtilities.MODID+":compressedcharcoal");
	        	icons[1] = icon.registerIcon(AdvancedUtilities.MODID+":doublecompressedcharcoal");
	    }
	    

}
