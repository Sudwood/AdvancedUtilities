package com.sudwood.advancedutilities.client;

import com.sudwood.advancedutilities.AdvancedUtilities;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class SoundHandler 
{
	public static void playAtEntity(World world, Entity target, String soundName, float volume, float pitch)
	{
		world.playSoundAtEntity(target, AdvancedUtilities.MODID+":"+soundName, volume, pitch);
		
	}
	
	public static void playSoundEffect(World world, int x, int y, int z, String name, float volume, float pitch)
	{
		world.playSoundEffect((double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D, AdvancedUtilities.MODID+":"+name, volume, pitch);
	}
}
