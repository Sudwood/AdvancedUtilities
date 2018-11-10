package com.sudwood.advancedutilities.fluids;

import com.sudwood.advancedutilities.AdvancedUtilities;
import com.sudwood.advancedutilities.BucketHandler;
import com.sudwood.advancedutilities.items.ItemOilBucket;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;

public class AdvancedUtilitiesFluids 
{
	public static Item bucketOil;
    public static Fluid fluidSteam;
    public static Fluid fluidOil;
    public static Block blockFluidSteam;
    public static Block blockFluidOil;
    
    public static void initFluids()
    {
    	fluidSteam = new Fluid(AdvancedUtilities.steamName).setDensity(103).setGaseous(true).setTemperature(373).setViscosity(108).setUnlocalizedName("Steam");
		fluidOil = new Fluid("Oil").setDensity(500).setGaseous(false).setTemperature(60).setViscosity(500).setUnlocalizedName("Oil");
		
    	FluidRegistry.registerFluid(fluidSteam);
    	FluidRegistry.registerFluid(fluidOil);
    	blockFluidOil = new BlockFluidOil(fluidOil, Material.water).setBlockName("BlockFluidOil");
    	blockFluidSteam = new BlockFluidSteam(fluidSteam, Material.water).setBlockName("BlockFluidSteam");
		GameRegistry.registerBlock(blockFluidSteam, "blockfluidsteam");
		GameRegistry.registerBlock(blockFluidOil, "blockfluidoil");
		fluidOil.setBlock(blockFluidOil);
		fluidSteam.setBlock(blockFluidSteam);
		bucketOil = new ItemOilBucket(blockFluidOil).setUnlocalizedName("BucketOil").setCreativeTab(CreativeTabs.tabMisc);
		GameRegistry.registerItem(bucketOil, "bucketoil");
		FluidContainerRegistry.registerFluidContainer(fluidOil, new ItemStack(bucketOil), new ItemStack(Items.bucket));
		BucketHandler.instance.buckets.put(blockFluidOil, bucketOil);
		
    }
}
