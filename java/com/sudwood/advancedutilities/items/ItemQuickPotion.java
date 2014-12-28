package com.sudwood.advancedutilities.items;

import java.util.ArrayList;
import java.util.List;

import com.sudwood.advancedutilities.AdvancedUtilities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemQuickPotion extends ItemFood
{
	private IIcon[] icons = new IIcon[3];
	private String[] names = {"HealingPotion", "WaterBottle", "AwkwardPotion", "QuickPotion"};
	public ItemQuickPotion(int hungerAmount, float saturation, boolean wolfEat)
    {
		super(hungerAmount, saturation, wolfEat);
		this.setHasSubtypes(true);
		this.setAlwaysEdible();
    }
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) 
    {
		if(stack.getItemDamage() == 0)
		{
			par3List.add("Strength: 1");
		}
		else if(stack.getItemDamage() == 1)
		{
			par3List.add("Strength: 2");
		}
		else if(stack.getItemDamage() == 2 || stack.getItemDamage() == 3)
		{
			par3List.add("Used in quick brewing.");
		}
		else if(stack.getItemDamage() == 4)
		{
			par3List.add("Makes quick potions.");
		}
    }
	 @Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 1;
    }
	 
	public static boolean isSplash(int par0)
    {
        return false;
    }
	
	public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
		if(par1ItemStack.getItemDamage() == 0 || par1ItemStack.getItemDamage() == 1)
		{
	        --par1ItemStack.stackSize;
	        par3EntityPlayer.getFoodStats().func_151686_a(this, par1ItemStack);
	      //  par2World.playSoundAtEntity(par3EntityPlayer, "random.drink", 0.5F, par2World.rand.nextFloat() * 0.1F + 0.9F);
	        this.onFoodEaten(par1ItemStack, par2World, par3EntityPlayer);
		}
        return par1ItemStack;
    }
	
	protected void onFoodEaten(ItemStack stack, World par2World, EntityPlayer par3EntityPlayer)
    {
		if(stack.getItemDamage() == 0)
			par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.heal.id, 1, 1, false));
		else if(stack.getItemDamage() == 1)
			par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.heal.id, 1, 2, false));
    }
	
	public static PotionEffect getEffectForPotion(ItemStack stack)
	{
		PotionEffect result = null;
		if(stack.getItemDamage() == 0)
		{
			result = new PotionEffect(Potion.heal.id, 1, 1, false);
		}
		if(stack.getItemDamage() == 1)
		{
			result = new PotionEffect(Potion.heal.id, 1, 2, false);
		}
		return result;
	}
	
	 public EnumAction getItemUseAction(ItemStack par1ItemStack)
	 {
	        return EnumAction.drink;
	 }
	 public String getUnlocalizedName(ItemStack par1ItemStack)
	    {
	        int i = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, 1);
	        if(par1ItemStack.getItemDamage() == 2)
	        	return super.getUnlocalizedName() + "." + names[1];
	        if(par1ItemStack.getItemDamage() == 3)
	        	return super.getUnlocalizedName() + "." + names[2];
	        if(par1ItemStack.getItemDamage() == 4)
	        	return super.getUnlocalizedName() + "." + names[3];
	        else
	        {
	        	return super.getUnlocalizedName() + "." + names[0];
	        }
	    }
	    
	    /**
	     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
	     */
	    @SideOnly(Side.CLIENT)
	    public void getSubItems(Item item, CreativeTabs tabs, List list)
	    {
	        for (int i = 0; i < 5; ++i)
	        {
	            list.add(new ItemStack(item, 1, i));
	            item.setCreativeTab(AdvancedUtilities.advancedTab);
	        }
	    }
		
	    public CreativeTabs[] getCreativeTabs()
	    {
	        return new CreativeTabs[]{ AdvancedUtilities.advancedTab };
	    }
	    
		@SideOnly(Side.CLIENT)
	    public IIcon getIconFromDamage(int par1)
	    {
			if(par1 == 0 | par1 == 1)
			{
				return icons[0];
			}
			if(par1 == 4)
			{
				return icons[2];
			}
			else
				return icons[1];
	    }
		
		@SideOnly(Side.CLIENT)
	    public void registerIcons(IIconRegister par1IconRegister)
	    {
	        this.icons[0] = par1IconRegister.registerIcon(AdvancedUtilities.MODID+":quickhealthpotion");
	        this.icons[1] = par1IconRegister.registerIcon(AdvancedUtilities.MODID+":basepotion");
	        this.icons[2] = par1IconRegister.registerIcon(AdvancedUtilities.MODID+":quickpotion");
	    }
}
