package com.sudwood.advancedutilities.items;

import java.util.List;

import com.sudwood.advancedutilities.AdvancedUtilities;
import com.sudwood.advancedutilities.client.ClientRegistering;
import com.sudwood.advancedutilities.client.SoundHandler;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidContainerRegistry;

public class ItemArmorSteamJetpack extends ItemArmor
{
	public static final int maxStorage = 32*FluidContainerRegistry.BUCKET_VOLUME;
	public static final int steamUse = 6;
	public static final int steamHover = 1;
	int type;
	public ItemArmorSteamJetpack(ArmorMaterial material, int render, int type) 
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
		NBTTagCompound tag = par1ItemStack.getTagCompound();
		if(tag.getInteger("maxTankAmount") == 0)
		{
			tag.setInteger("maxTankAmount", this.maxStorage);
		}
		par3List.add("Steam: "+tag.getInteger("tankAmount")+" / "+tag.getInteger("maxTankAmount")+" mB");
		par3List.add("Flight Time Remaining: "+ (tag.getInteger("tankAmount") / (20*this.steamUse))+ " s");
	}
	 	
	 	@Override
	 	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	 	{
	 		return AdvancedUtilities.MODID+":"+"textures/items/jetpack.png";

 		}
	 	
	 	public void onCreated(ItemStack stack, World world, EntityPlayer player) 
	 	{
	 		if(stack.getTagCompound() == null)
	 		{
	 			stack.setTagCompound(new NBTTagCompound());
	 		}
	 		NBTTagCompound tag = stack.getTagCompound();
	 		if(tag.getInteger("maxTankAmount") == 0)
	 		{
	 			tag.setInteger("maxTankAmount", maxStorage);
	 		}
	 	}
 		
	 	public boolean isBookEnchantable(ItemStack stack, ItemStack book)
	    {
	        return true;
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
 	    	ModelBiped armorModel = new ModelBiped();
 	       if(itemStack != null && itemStack.getItem() instanceof ItemArmorSteamJetpack)
 	       {
 	    	 armorModel = ClientRegistering.jetpackArmor;
 	    	 armorModel.bipedHead.showModel = armorSlot == 0;
 	    	 armorModel.bipedHeadwear.showModel = armorSlot == 0;
 	    	 armorModel.bipedBody.showModel = true;
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
 	    		if(itemStack.getTagCompound() == null)
 	    		{
 	    			itemStack.setTagCompound(new NBTTagCompound());
 	    		}
 	    		NBTTagCompound tag = itemStack.getTagCompound();
 	    		if(itemStack.getItem() == AdvancedUtilitiesItems.steamJetpack && tag.getInteger("tankAmount") >= this.steamHover && player.isSneaking())
 	    		{
 	    			player.fallDistance = 0F;
 	    		}
	 	    	if(itemStack.getItem() == AdvancedUtilitiesItems.steamJetpack && tag.getInteger("tankAmount") >= this.steamHover && player.isSneaking() && !player.onGround)
	 	    	{
	 	    		player.jumpMovementFactor = (float) 0.06;
	 	    		if(FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
	 	    			player.setVelocity(player.motionX,-0.14,player.motionZ);
	 	    		SoundHandler.playAtEntity(player.worldObj, player, "steamStream", 0.1F, 0.6F);
	 	    		if(!player.capabilities.isCreativeMode)
	 	    		tag.setInteger("tankAmount", tag.getInteger("tankAmount")-this.steamHover);
	 	    	}
 	    	}
 	    }
	
}
