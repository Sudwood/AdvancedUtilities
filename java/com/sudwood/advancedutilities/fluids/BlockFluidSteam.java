package com.sudwood.advancedutilities.fluids;

import java.util.Random;

import javax.swing.Icon;

import com.sudwood.advancedutilities.blocks.AdvancedUtilitiesBlocks;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFluidSteam extends BlockFluidClassic
{

    @SideOnly(Side.CLIENT)
    protected IIcon stillIcon;
    @SideOnly(Side.CLIENT)
    protected IIcon flowingIcon;
    
    public BlockFluidSteam(Fluid fluid, Material material) {
            super(fluid, material);
           // setUnlocalizedName("yourFluid");
    }
    
    @Override
    public IIcon getIcon(int side, int meta) {
            if(side == 0)
            {
            	return stillIcon;
            }
            else
            	return flowingIcon;
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister register)
    {
            stillIcon = register.registerIcon("advancedutilities:steam_still");
            flowingIcon = register.registerIcon("advancedutilities:steam_flow");
    }
    
    @Override
    public boolean canDisplace(IBlockAccess world, int x, int y, int z) {
            return false;
    }
    
    @Override
    public boolean displaceIfPossible(World world, int x, int y, int z) {
            return false;
    }
    @Override
    public void updateTick(World world, int x, int y, int z, Random rand)
    {
    	super.updateTick(world, x, y, z, rand);
    	if(world.getBlock(x, y+1, z).isAir(world, x, y, z))
    	{
    		if(y<250)
    			world.setBlock(x, y+1, z, AdvancedUtilitiesBlocks.blockFluidSteam);
    		world.setBlockToAir(x, y, z);
    	}
    }
    
}