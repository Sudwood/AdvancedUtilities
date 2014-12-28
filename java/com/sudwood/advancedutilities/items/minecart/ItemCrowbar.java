package com.sudwood.advancedutilities.items.minecart;

import java.util.List;

import com.sudwood.advancedutilities.AdvancedUtilities;
import com.sudwood.advancedutilities.entity.minecart.EntityChunkLoadingCart;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class ItemCrowbar extends Item
{
	
	public ItemCrowbar()
	{
		super();
		this.setMaxDamage(120);
		this.setMaxStackSize(1);
	}
	/**
     * allows items to add custom lines of information to the mouseover description
     */
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) 
    {
    	if(stack.getTagCompound() == null)
    		stack.setTagCompound(new NBTTagCompound());
    	NBTTagCompound tag = stack.getTagCompound();
    	par3List.add("Minecart: "+tag.getInteger("ID"));
    	par3List.add("Is Bound: "+tag.getBoolean("isSet"));
    }
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister)
    {
		this.itemIcon = par1IconRegister.registerIcon("advancedutilities:crowbar");
	}
    
    /**
     * Metadata-sensitive version of getStrVsBlock
     * @param itemstack The Item Stack
     * @param block The block the item is trying to break
     * @param metadata The items current metadata
     * @return The damage strength
     */
    public float getDigSpeed(ItemStack itemstack, Block block, int metadata)
    {
    	if(block == Blocks.activator_rail || block == Blocks.rail || block == Blocks.golden_rail || block == Blocks.detector_rail)
    	{
    		return 30F;
    	}
    	else
    		return 1.5F;
    }
    
    public boolean onBlockDestroyed(ItemStack stack, World world, Block block, int p_150894_4_, int p_150894_5_, int p_150894_6_, EntityLivingBase player)
    {
    	stack.damageItem(2, player);
    	return true;
    }
    
    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
    {
    	if(entity instanceof EntityChunkLoadingCart && player.isSneaking())
    	{
    		if(stack!=null && player!=null)
    		{
    			if(stack.getTagCompound()!=null)
    			{
    				NBTTagCompound tag = stack.getTagCompound();
    				if(!tag.getBoolean("isSet"))
    				{
    					tag.setInteger("ID", entity.getEntityId());
    					tag.setBoolean("isSet", true);
    				}
    				else if(tag.getBoolean("isSet"))
    				{
    					if(player.worldObj.getEntityByID(tag.getInteger("ID"))!=null)
    					{
    						entity.mountEntity(player.worldObj.getEntityByID(tag.getInteger("ID")));
    					}
    					tag.setBoolean("isSet", false);
    					tag.setInteger("ID", 0);
    				}
    			}
    		}
    		return true;
    	}
    	if(entity instanceof EntityMinecart)
    	{
    		entity.attackEntityFrom(DamageSource.generic, 40);
    		return false;
    	}
        return false;
    }

    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
    	if(par1ItemStack!=null && par3EntityPlayer != null && par3EntityPlayer.isSneaking())
    	{
    		if(par1ItemStack.getTagCompound()!=null)
    		{
    			NBTTagCompound tag = par1ItemStack.getTagCompound();
    			tag.setBoolean("isSet", false);
    			tag.setInteger("ID", 0);
    		}
    	}
        return par1ItemStack;
    }
    
}
