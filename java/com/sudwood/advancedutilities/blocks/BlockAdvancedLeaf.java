package com.sudwood.advancedutilities.blocks;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class BlockAdvancedLeaf extends BlockLeaves
{
	IIcon icons[] = new IIcon[2];
	@Override
	public IIcon getIcon(int var1, int var2) {
		// TODO Auto-generated method stub
		if(Minecraft.getMinecraft().isFancyGraphicsEnabled())
		{
			return icons[1];
		}
		else
			return icons[0];
	}

	@Override
	public String[] func_150125_e() {
		// TODO Auto-generated method stub
		return null;
	}
	
	 public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
	 {
	        return Item.getItemFromBlock(AdvancedUtilitiesBlocks.blockRubberSapling);
	 }
	 
	 public boolean isOpaqueCube()
	 {
	        return false;
	 }
	 
	 @SideOnly(Side.CLIENT)
	    public void registerBlockIcons(IIconRegister p_149651_1_)
	    {
	        this.icons[0] = p_149651_1_.registerIcon("advancedutilities:rubberleaves");
	        this.icons[1] = p_149651_1_.registerIcon("advancedutilities:rubberleavesfancy");
	    }
	 
	 /**
	     * Returns the quantity of items to drop on block destruction.
	     */
	    public int quantityDropped(Random p_149745_1_)
	    {
	        return p_149745_1_.nextInt(5) == 0 ? 1 : 0;
	    }

	 
	 @Override
	 @SideOnly(Side.CLIENT)
	    public boolean shouldSideBeRendered(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_)
	    {
	        return true;
	    }

}
