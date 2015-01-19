package com.sudwood.advancedutilities.blocks;

import java.util.Random;

import com.sudwood.advancedutilities.AdvancedUtilities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class BlockOre extends Block
{
	protected int type;
	protected BlockOre(Material mat, int type) 
	{
		super(mat);
		this.type = type;
		if(type == 0 || type == 1 || type == 2 || type == 9)
			this.setHarvestLevel("pickaxe", 1);
		if(type == 3 || type == 4 || type == 5)
			this.setHarvestLevel("pickaxe", 2);
		if(type == 6 || type == 7)
			this.setHarvestLevel("pickaxe", 3);
		// TODO Auto-generated constructor stub
	}
	    /**
	     * Returns the quantity of items to drop on block destruction.
	     */
    public int quantityDropped(Random p_149745_1_)
    {
       return 1;
    }

    
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister icon)
    {
        
        switch(type)
        {
        case 0:
        	this.blockIcon = icon.registerIcon("advancedutilities:copper"+AdvancedUtilities.textureSize);
        	break;
        	
        case 1:
        	this.blockIcon = icon.registerIcon("advancedutilities:tin"+AdvancedUtilities.textureSize);
        	break;
        case 2:
        	this.blockIcon = icon.registerIcon("advancedutilities:zinc"+AdvancedUtilities.textureSize);
        	break;
        case 3:
        	this.blockIcon = icon.registerIcon("advancedutilities:silver"+AdvancedUtilities.textureSize);
        	break;
        case 4:
        	this.blockIcon = icon.registerIcon("advancedutilities:lead"+AdvancedUtilities.textureSize);
        	break;
        case 5:
        	this.blockIcon = icon.registerIcon("advancedutilities:bauxite"+AdvancedUtilities.textureSize);
        	break;
        case 6:
        	this.blockIcon = icon.registerIcon("advancedutilities:tungsten"+AdvancedUtilities.textureSize);
        	break;
        case 7:
        	this.blockIcon = icon.registerIcon("advancedutilities:platinum"+AdvancedUtilities.textureSize);
        	break;
        case 8:
        	this.blockIcon = icon.registerIcon("advancedutilities:bronzemachine");
        	break;
        case 9:
        	this.blockIcon = icon.registerIcon("advancedutilities:nickel");
        	break;
        case 10:
        	this.blockIcon = icon.registerIcon("advancedutilities:steelmachine");
        	break;
        }
    }
    
    
}
