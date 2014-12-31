package com.sudwood.advancedutilities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public class HelperLibrary 
{
	/**
	 * Taken from Tinker's construct thank you very much!
	 * @param world
	 * @param player
	 * @param par3 false?
	 * @param range
	 * @return
	 */
	public static MovingObjectPosition raytraceFromEntity (World world, Entity player, boolean par3, double range)
    {
        float f = 1.0F;
        float f1 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * f;
        float f2 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * f;
        double d0 = player.prevPosX + (player.posX - player.prevPosX) * (double) f;
        double d1 = player.prevPosY + (player.posY - player.prevPosY) * (double) f;
        if (!world.isRemote && player instanceof EntityPlayer)
            d1 += 1.62D;
        double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * (double) f;
        Vec3 vec3 = Vec3.createVectorHelper(d0, d1, d2);
        float f3 = MathHelper.cos(-f2 * 0.017453292F - (float) Math.PI);
        float f4 = MathHelper.sin(-f2 * 0.017453292F - (float) Math.PI);
        float f5 = -MathHelper.cos(-f1 * 0.017453292F);
        float f6 = MathHelper.sin(-f1 * 0.017453292F);
        float f7 = f4 * f5;
        float f8 = f3 * f5;
        double d3 = range;
        if (player instanceof EntityPlayerMP)
        {
            d3 = ((EntityPlayerMP) player).theItemInWorldManager.getBlockReachDistance();
        }
        Vec3 vec31 = vec3.addVector((double) f7 * d3, (double) f6 * d3, (double) f8 * d3);
        return world.func_147447_a(vec3, vec31, par3, !par3, par3);
    }
	
	/**
	 * Returns true if the item is OreDictionary the same - false if not
	 * @param stack the item stack that is being checked
	 * @param oreDicID the OreDictionary ID of the Item that you are looking for
	 * @return
	 */
	public static boolean isOreDicItem(ItemStack stack, int oreDicID)
	{
		if(stack!=null && OreDictionary.getOreIDs(stack)!= null && OreDictionary.getOreIDs(stack).length > 0 && OreDictionary.getOreIDs(stack)[0] == oreDicID)
			return true;
		return false;
	}
	
	/**
	 * Returns true if the item is OreDictionary the same - false if not
	 * @param stack the item stack that is being checked
	 * @param compare the item stack of the Item that you are looking for
	 * @return
	 */
	public static boolean isOreDicItem(ItemStack stack, ItemStack compare)
	{
		if(stack!=null && OreDictionary.getOreIDs(stack)!= null && OreDictionary.getOreIDs(stack).length > 0)
		{
			if(compare!= null && OreDictionary.getOreIDs(compare)!= null && OreDictionary.getOreIDs(compare).length > 0)
			{
				if(OreDictionary.getOreIDs(stack)[0] == OreDictionary.getOreIDs(compare)[0])
				{
					return true;
				}
			}
		}
		return false;
	}
}
