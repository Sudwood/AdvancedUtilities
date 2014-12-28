package com.sudwood.advancedutilities.items;

import java.util.List;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import com.sudwood.advancedutilities.AdvancedUtilities;
import com.sudwood.advancedutilities.client.ClientRegistering;
import com.sudwood.advancedutilities.container.RebreatherInv;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemArmorRebreather extends ItemArmor
{

	int type;
	public ItemArmorRebreather(ArmorMaterial material, int render, int type) 
	{
		super(material, render, type);
		this.type = type;
		this.maxStackSize = 1;
		// TODO Auto-generated constructor stub
	}
	@SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister icon)
    {
		this.itemIcon = icon.registerIcon("advancedutilities:copperingot");
    }
	public int getItemEnchantability()
    {
		 return 10;
    }
	public boolean isItemTool(ItemStack par1ItemStack)
    {
        return true;
    }
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) 
	{
		if(par1ItemStack.getTagCompound() == null)
		{
			par1ItemStack.setTagCompound(new NBTTagCompound());
		}
		RebreatherInv inv = new RebreatherInv(par1ItemStack);
    	par3List.add(inv.getFoodTotal()+" Food");
		
	}
	 	
	 	@Override
	 	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	 	{
	 		return AdvancedUtilities.MODID+":"+"textures/items/rebreather.png";

 		}
	 	
	 	public void onCreated(ItemStack stack, World world, EntityPlayer player) 
	 	{
	 		if(stack.getTagCompound() == null)
	 		{
	 			stack.setTagCompound(new NBTTagCompound());
	 		}
	 		NBTTagCompound tag = stack.getTagCompound();
	 		
	 	}
 		
	 	public boolean isBookEnchantable(ItemStack stack, ItemStack book)
	    {
	        return true;
	    }
	 	
	 	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	    {
	 		if(par3EntityPlayer.isSneaking())
	 		{
		    	RebreatherInv inv = new RebreatherInv(par1ItemStack);
		    	NBTTagCompound tag = par1ItemStack.getTagCompound();
		    	par3EntityPlayer.openGui(AdvancedUtilities.instance, AdvancedUtilities.rebreatherGui, par2World, (int) par3EntityPlayer.posX, (int) par3EntityPlayer.posY, (int) par3EntityPlayer.posZ);
	 		}
	     
	     return par1ItemStack;
	    }
	 	
	 	@Override
		public int getMaxItemUseDuration(ItemStack stack) 
		{
			return 68000; // return any value greater than zero
		}
	 	
 		 /**
 	     * Override this method to have an item handle its own armor rendering.
 	     * 
 	     * @param  entityLiving  The entity wearing the armor 
 	     * @param  itemStack  The itemStack to render the model of 
 	     * @param  armorSlot  0=head, 1=torso, 2=legs, 3=feet
 	     * 
 	     * @return  A ModelBiped to render instead of the default
 	     */
 	    @SideOnly(Side.CLIENT)
 	    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot)
 	    {
 	    	ModelBiped armorModel = null;
 	       if(itemStack != null && itemStack.getItem() instanceof ItemArmorRebreather)
 	       {
 	    	 armorModel = ClientRegistering.rebreatherArmor;
 	    	 armorModel.bipedHead.showModel = false;
 	    	 armorModel.bipedHeadwear.showModel = true;
 	    	 armorModel.bipedBody.showModel = false;
 	    	 armorModel.bipedRightArm.showModel = false;
 	    	 armorModel.bipedLeftArm.showModel = false;
 	    	 armorModel.bipedRightLeg.showModel = false;
 	    	 armorModel.bipedLeftLeg.showModel = false;

 	    	 armorModel.isSneak = entityLiving.isSneaking();
 	    	 armorModel.isRiding = entityLiving.isRiding();
 	    	 armorModel.isChild = entityLiving.isChild();
 	    	 
 	    	 if(entityLiving instanceof EntityPlayer)
 	    	 {
 	    	 armorModel.aimedBow =((EntityPlayer)entityLiving).getItemInUseDuration() > 2;
 	    	 armorModel.heldItemRight = ((EntityPlayer)entityLiving).getCurrentEquippedItem() != null ? 1 :0;
 	    	 }
 	       }
 	       return armorModel;
 	    }
 	    
 	   /**
 	     * Called to tick armor in the armor slot. Override to do something
 	     *
 	     * @param world
 	     * @param player
 	     * @param itemStack
 	     */
 	    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
 	    {
 	    	if(itemStack!=null)
 	    	{
 	    		RebreatherInv inv = new RebreatherInv(itemStack);
 	    		if(player.getFoodStats().needFood())
 	    		{
 	    			for(int i = 0; i < inv.INV_SIZE/2; i++)
 	    			{
 	    				if(inv.getStackInSlot(i)!= null && inv.getStackInSlot(i).getItem() instanceof ItemFood)
 	    				{
	 	    				if(player.getFoodStats().getFoodLevel()+((ItemFood) inv.getStackInSlot(i).getItem()).func_150905_g(inv.getStackInSlot(i)) <= 20)
	 	    				{
	 	    					player.getFoodStats().setFoodLevel(player.getFoodStats().getFoodLevel()+((ItemFood) inv.getStackInSlot(i).getItem()).func_150905_g(inv.getStackInSlot(i)));
	 	    					player.getFoodStats().setFoodSaturationLevel(player.getFoodStats().getSaturationLevel()+((ItemFood) inv.getStackInSlot(i).getItem()).func_150906_h(inv.getStackInSlot(i)));
	 	    					inv.decrStackSize(i, 1);
	 	    				}
 	    				}
 	    			}
 	    		}
 	    		
 	    	}
 	    }
	
}
