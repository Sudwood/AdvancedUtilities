package com.sudwood.advancedutilities.fluids;

import java.util.Random;

import com.sudwood.advancedutilities.AdvancedUtilities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockFluidOil extends BlockFluidClassic
{

    @SideOnly(Side.CLIENT)
    public IIcon stillIcon;
    @SideOnly(Side.CLIENT)
    public IIcon flowingIcon;
    
    public BlockFluidOil(Fluid fluid, Material material) {
            super(fluid, material);
            this.setCreativeTab(AdvancedUtilities.advancedTab);
           // setUnlocalizedName("yourFluid");
    }
    
    @Override

    public IIcon getIcon(int side, int meta) {

            return (side == 0 || side == 1)? stillIcon : flowingIcon;

    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister register)
    {
            stillIcon = register.registerIcon("advancedutilities:oil_still");
            flowingIcon = register.registerIcon("advancedutilities:oil_flow");
    }
    
    @Override

    public boolean canDisplace(IBlockAccess world, int x, int y, int z) {

            if (world.getBlock(x,  y,  z).getMaterial().isLiquid()) return false;

            return super.canDisplace(world, x, y, z);

    }

   

    @Override

    public boolean displaceIfPossible(World world, int x, int y, int z) {

            if (world.getBlock(x,  y,  z).getMaterial().isLiquid()) return false;

            return super.displaceIfPossible(world, x, y, z);

    }

    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity ent) 
    {
    	super.onEntityCollidedWithBlock(world, x, y, z, ent);
    	if(ent!=null && ent instanceof EntityLivingBase)
    	{
    		((EntityLivingBase)ent).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 200, 5));
    		((EntityLivingBase)ent).addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 200, 5));
    	}
    }
    
}