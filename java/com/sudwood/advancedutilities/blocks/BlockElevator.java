package com.sudwood.advancedutilities.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class BlockElevator extends Block{

	protected BlockElevator(Material mat) {
		super(mat);
		// TODO Auto-generated constructor stub
	}
	
	public boolean isOpaqueCube()
    {
        return false;
    }
	
	 public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
	    {
	        float f = 0.0625F;
	        return AxisAlignedBB.getBoundingBox(0, 0, 0, 0, 0, 0);
	    }

	 public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity ent)
	    {
	        ent.motionY = 5.0;
	    }
	 
	 @SideOnly(Side.CLIENT)
	    public void registerBlockIcons(IIconRegister p_149651_1_)
	    {
	        this.blockIcon = p_149651_1_.registerIcon("advancedutilities:bronzemachine");
	    }
}
