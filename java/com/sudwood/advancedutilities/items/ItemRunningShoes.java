package com.sudwood.advancedutilities.items;

import java.util.List;

import org.lwjgl.input.Keyboard;

import com.sudwood.advancedutilities.AdvancedUtilities;
import com.sudwood.advancedutilities.client.ClientRegistering;
import com.sudwood.advancedutilities.client.KeyHandler;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.nbt.NBTTagCompound;

public class ItemRunningShoes extends ItemArmor
{
	int type;
	public ItemRunningShoes(ArmorMaterial material, int render, int type) 
	{
		super(material, render, type);
		this.type = type;
		this.maxStackSize = 1;
		// TODO Auto-generated constructor stub
	}
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) 
	{
		par3List.add("Hold "+Keyboard.getKeyName(KeyHandler.run.getKeyCode())+" to run!");
	}
	@SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister icon)
    {
		this.itemIcon = icon.registerIcon("advancedutilities:copperingot");
    }
	
	@Override 
	 /**
     * Determines if the specific ItemStack can be placed in the specified armor slot.
     *
     * @param stack The ItemStack
     * @param armorType Armor slot ID: 0: Helmet, 1: Chest, 2: Legs, 3: Boots
     * @param entity The entity trying to equip the armor
     * @return True if the given ItemStack can be inserted in the slot
     */
    public boolean isValidArmor(ItemStack stack, int armorType, Entity entity)
    {
        if (armorType == 3)
        {
            return true;
        }

        return false;
    }
	
	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
    {
		return true;
    }
	public boolean isRepairable()
    {
		return true;
    }
	
	 public boolean isDamageable()
	 {
		 return true;
	 }
	 
	public boolean isBookEnchantable(ItemStack stack, ItemStack book)
    {
        return true;
    }
	
	public int getItemEnchantability()
    {
		 return 10;
    }
	public boolean isItemTool(ItemStack par1ItemStack)
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
	       if(itemStack != null && itemStack.getItem() instanceof ItemRunningShoes)
	       {
	    	 armorModel = ClientRegistering.shoesArmor;
	    	 armorModel.bipedHead.showModel = armorSlot == 0;
	    	 armorModel.bipedHeadwear.showModel = armorSlot == 0;
	    	 armorModel.bipedBody.showModel = false;
	    	 armorModel.bipedRightArm.showModel = false;
	    	 armorModel.bipedLeftArm.showModel = false;
	    	 armorModel.bipedRightLeg.showModel = true;
	    	 armorModel.bipedLeftLeg.showModel = true;

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
	    

	 	@Override
	 	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	 	{
	 		return AdvancedUtilities.MODID+":"+"textures/items/runningshoes.png";

 		}
}
