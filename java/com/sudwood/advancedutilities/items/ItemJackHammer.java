package com.sudwood.advancedutilities.items;

import java.util.List;

import com.sudwood.advancedutilities.client.SoundHandler;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidContainerRegistry;

public class ItemJackHammer extends Item
{
	public static final int maxStorage = 64*FluidContainerRegistry.BUCKET_VOLUME;
	public static final int steamUse = 40;
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) 
	{
		if(par1ItemStack.getTagCompound() == null)
		{
			par1ItemStack.setTagCompound(new NBTTagCompound());
		}
		NBTTagCompound tag = par1ItemStack.getTagCompound();
		if(tag.getInteger("maxTankAmount") == 0)
		{
			tag.setInteger("maxTankAmount", this.maxStorage);
		}
		par3List.add("Steam: "+tag.getInteger("tankAmount")+" / "+tag.getInteger("maxTankAmount")+" mB");
		par3List.add("Blocks Remaining: "+ (tag.getInteger("tankAmount") / (this.steamUse)));
	}
	
	 /**
     * ItemStack sensitive version of {@link #canHarvestBlock(Block)} 
     * @param par1Block The block trying to harvest
     * @param itemStack The itemstack used to harvest the block
     * @return true if can harvest the block
     */
    @Override
    public boolean canHarvestBlock(Block par1Block, ItemStack itemStack)
    {
    	if(par1Block.isToolEffective("pickaxe", 2))
			return true;
    	else
    		return false;
    }
    
    public boolean isBookEnchantable(ItemStack stack, ItemStack book)
    {
        return true;
    }
    
    public int getItemEnchantability()
    {
        return 10;
    }
    
    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
    {
    	if(stack.getTagCompound()== null)
    		stack.setTagCompound(new NBTTagCompound());
    	
    	NBTTagCompound tag = stack.getTagCompound();
		if(tag.getInteger("tankAmount") >= 2*this.steamUse)
	  	 {
	  	
	  	 return false;
	  	 }
	  	 else
	  	 {
	  		 
	  		 return true;
	  	 }
    	
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
    	if(block.isToolEffective("pickaxe", 2))
			return 40F;
    	else return 10F;
    }
    
    /**
     * Current implementations of this method in child classes do not use the entry argument beside ev. They just raise
     * the damage on the stack.
     */
    @Override
    public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase player)
    {
    	NBTTagCompound tag = par1ItemStack.getTagCompound();
    	 if(tag.getInteger("tankAmount") >= 2*this.steamUse)
      	 {
	      	 tag.setInteger("tankAmount", tag.getInteger("tankAmount")-2*this.steamUse);
	      	par2EntityLivingBase.attackEntityFrom(DamageSource.generic, 8);
	      	if(tag.getInteger("tankAmount") <= 0)
	      	{
	      		SoundHandler.playAtEntity(player.worldObj, player, "steam",1F, 1F);
	      	}
	      	 return false;
      	 }
        return false;
    }
    public boolean onBlockDestroyed(ItemStack stack, World world, Block block, int p_150894_4_, int p_150894_5_, int p_150894_6_, EntityLivingBase player)
    {
    	NBTTagCompound tag = stack.getTagCompound();
    		if(block.isToolEffective("pickaxe", 2) && tag.getInteger("tankAmount") >= this.steamUse)
	      	 {
		      	 tag.setInteger("tankAmount", tag.getInteger("tankAmount")-this.steamUse);
		      	if(tag.getInteger("tankAmount") <= 0)
		      	{
		      		SoundHandler.playAtEntity(player.worldObj, player, "steam",1F, 1F);
		      	}
		      	 return true;
	      	 }
    		if(!block.isToolEffective("pickaxe", 2) && tag.getInteger("tankAmount") >= 2*this.steamUse)
	      	 {
		      	 tag.setInteger("tankAmount", tag.getInteger("tankAmount")-2*this.steamUse);
		      	if(tag.getInteger("tankAmount") <= 0)
		      	{
		      		SoundHandler.playAtEntity(player.worldObj, player, "steam",1F, 1F);
		      	}
		      	 return true;
	      	 }
	        return false;
    }
    
    /**
     * Called before a block is broken.  Return true to prevent default block harvesting.
     *
     * Note: In SMP, this is called on both client and server sides!
     *
     * @param itemstack The current ItemStack
     * @param X The X Position
     * @param Y The X Position
     * @param Z The X Position
     * @param player The Player that is wielding the item
     * @return True to prevent harvesting, false to continue as normal
     */
    @Override
    public boolean onBlockStartBreak(ItemStack itemstack, int X, int Y, int Z, EntityPlayer player)
    {
    	if(itemstack.getTagCompound()==null)
		  {
			  itemstack.setTagCompound(new NBTTagCompound());
		  }
	  	NBTTagCompound tag = itemstack.getTagCompound();
	  	
	  	if(player.worldObj.getBlock(X, Y, Z).isToolEffective("pickaxe", 2) && tag.getInteger("tankAmount") >= this.steamUse)
    	 {
	      	 return false;
    	 }
     	 if(!player.worldObj.getBlock(X, Y, Z).isToolEffective("pickaxe", 2) && tag.getInteger("tankAmount") >= 2*this.steamUse)
     	 {
	      	 return false;
     	 }
    	 else return true;
    }
}
