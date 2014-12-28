package com.sudwood.advancedutilities.blocks;

import java.util.Random;

import com.sudwood.advancedutilities.AdvancedUtilities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;

public class BlockIngotBlock extends Block{

	protected int type;
	protected BlockIngotBlock(Material mat, int type) 
	{
		super(mat);
		this.type = type;
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
        	this.blockIcon = icon.registerIcon("advancedutilities:copperblock"+AdvancedUtilities.textureSize);
        	break;
        	
        case 1:
        	this.blockIcon = icon.registerIcon("advancedutilities:tinblock"+AdvancedUtilities.textureSize);
        	break;
        case 2:
        	this.blockIcon = icon.registerIcon("advancedutilities:bronzeblock"+AdvancedUtilities.textureSize);
        	break;
        case 3:
        	this.blockIcon = icon.registerIcon("advancedutilities:zincblock"+AdvancedUtilities.textureSize);
        	break;
        case 4:
        	this.blockIcon = icon.registerIcon("advancedutilities:brassblock"+AdvancedUtilities.textureSize);
        	break;
        case 5:
        	this.blockIcon = icon.registerIcon("advancedutilities:bronzemachine"+AdvancedUtilities.textureSize);
        	break;
        case 6:
        	this.blockIcon = icon.registerIcon("advancedutilities:silverblock"+AdvancedUtilities.textureSize);
        	break;
        case 7:
        	this.blockIcon = icon.registerIcon("advancedutilities:leadblock"+AdvancedUtilities.textureSize);
        	break;
        case 8:
        	this.blockIcon = icon.registerIcon("advancedutilities:aluminumblock"+AdvancedUtilities.textureSize);
        	break;
        case 9:
        	this.blockIcon = icon.registerIcon("advancedutilities:tungstenblock"+AdvancedUtilities.textureSize);
        	break;
        case 10:
        	this.blockIcon = icon.registerIcon("advancedutilities:platinumblock"+AdvancedUtilities.textureSize);
        	break;
        case 11:
        	this.blockIcon = icon.registerIcon("advancedutilities:iridiumblock"+AdvancedUtilities.textureSize);
        	break;
        case 12:
        	this.blockIcon = icon.registerIcon("advancedutilities:palidiumblock"+AdvancedUtilities.textureSize);
        	break;
        case 13:
        	this.blockIcon = icon.registerIcon("advancedutilities:steelblock"+AdvancedUtilities.textureSize);
        	break;
        case 14:
        	this.blockIcon = icon.registerIcon("advancedutilities:nickelblock"+AdvancedUtilities.textureSize);
        	break;
        }
    }
    
    public boolean isBeaconBase(IBlockAccess worldObj, int x, int y, int z, int beaconX, int beaconY, int beaconZ)
    {
        return true;
    }

}
