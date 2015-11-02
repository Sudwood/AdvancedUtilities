package com.sudwood.advancedutilities.blocks;


import com.sudwood.advancedutilities.AdvancedUtilities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class BlockWood extends Block
{
	protected int type;
	protected IIcon[] icons= new IIcon[2];
	protected BlockWood(Material mat, int type)
	{
		super(mat);
		this.type = type;
		
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean isWood(IBlockAccess world, int x, int y, int z)
	{
		return true;
	}
	@SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister icon)
    {
        switch(type)
        {
        case 0:
        	icons[0] =  icon.registerIcon("advancedutilities:rubberwoodside"+AdvancedUtilities.textureSize);
        	icons[1] =  icon.registerIcon("advancedutilities:rubberwoodtop"+AdvancedUtilities.textureSize);
        	break;
        
	    case 1:
	    	icons[0] =  icon.registerIcon("advancedutilities:rubberplanks"+AdvancedUtilities.textureSize);
	    	icons[1] =  icon.registerIcon("advancedutilities:rubberplanks"+AdvancedUtilities.textureSize);
	    	break;
	    }
    }
	
	public boolean canSustainLeaves(IBlockAccess world, int x, int y, int z)
    {
        return true;
    }
	
	/**
     * Gets the block's texture. Args: side, meta
     */
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
       if(side == 0 || side == 1)
       {
    	   return icons[1];
       }
       else return icons[0];
    }

}
