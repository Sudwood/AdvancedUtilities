package com.sudwood.advancedutilities.fluids;

import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.sound.SoundEvent;
import net.minecraftforge.fluids.Fluid;

public class FluidOil extends Fluid
{
	
	protected static int mapColor = 0x141414;
    protected static float overlayAlpha = 0.2F;
    protected static String emptySound = "minecraft:item.bucket.empty";
    protected static String fillSound = "minecraft:item.bucket.fill";
    protected static Material material = Material.water;
	public FluidOil(String fluidName)
	{
		super(fluidName);
		
	}
	 public int getColor()
    {
        return 0x141414;
    }
	

}
