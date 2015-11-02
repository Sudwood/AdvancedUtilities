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
import net.minecraft.world.IBlockAccess;

import com.sudwood.advancedutilities.AdvancedUtilities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockIngotBlock extends Block{

	private IIcon[] icons = new IIcon[15];
	public static int COPPER = 0;
	public static int TIN = 1;
	public static int BRONZE = 2;
	public static int ZINC = 3;
	public static int BRASS = 4;
	public static int SILVER = 5;
	public static int LEAD = 6;
	public static int ALUMINUM = 7;
	public static int TUNGSTEN = 8;
	public static int PLATINUM = 9;
	public static int IRIDIUM = 10;
	public static int PALIDIUM = 11;
	public static int STEEL = 12;
	public static int NICKEL = 13;
	public static int IRON = 14;
	protected BlockIngotBlock(Material mat) 
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
    	for (int ix = 0; ix < 15; ix++) {
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
        	icons[0] = icon.registerIcon("advancedutilities:copperblock"+AdvancedUtilities.textureSize);
        	icons[1] = icon.registerIcon("advancedutilities:tinblock"+AdvancedUtilities.textureSize);
        	icons[2] = icon.registerIcon("advancedutilities:bronzeblock"+AdvancedUtilities.textureSize);
        	icons[3] = icon.registerIcon("advancedutilities:zincblock"+AdvancedUtilities.textureSize);
        	icons[4] = icon.registerIcon("advancedutilities:brassblock"+AdvancedUtilities.textureSize);
        	icons[5] = icon.registerIcon("advancedutilities:silverblock"+AdvancedUtilities.textureSize);
        	icons[6] = icon.registerIcon("advancedutilities:leadblock"+AdvancedUtilities.textureSize);
        	icons[7] = icon.registerIcon("advancedutilities:aluminumblock"+AdvancedUtilities.textureSize);
        	icons[8] = icon.registerIcon("advancedutilities:tungstenblock"+AdvancedUtilities.textureSize);
        	icons[9] = icon.registerIcon("advancedutilities:platinumblock"+AdvancedUtilities.textureSize);
        	icons[10] = icon.registerIcon("advancedutilities:iridiumblock"+AdvancedUtilities.textureSize);
        	icons[11] = icon.registerIcon("advancedutilities:palidiumblock"+AdvancedUtilities.textureSize);
        	icons[12] = icon.registerIcon("advancedutilities:steelblock"+AdvancedUtilities.textureSize);
        	icons[13] = icon.registerIcon("advancedutilities:nickelblock"+AdvancedUtilities.textureSize);
        	icons[14] = icon.registerIcon("advancedutilities:ironblock"+AdvancedUtilities.textureSize);
    }
    
    public boolean isBeaconBase(IBlockAccess worldObj, int x, int y, int z, int beaconX, int beaconY, int beaconZ)
    {
        return true;
    }

}
