package com.sudwood.advancedutilities.items;

import java.util.List;

import com.sudwood.advancedutilities.AdvancedUtilities;
import com.sudwood.advancedutilities.container.InventoryBag;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemBag extends Item
{
	IIcon[] icons = new IIcon[16];
	
	private String[] names = {"WhiteBag", "OrangeBag", "MagentaBag", "LightBlueBag", "YellowBag", "LimeGreenBag", "PinkBag", "GreyBag", "LightGreyBag", "CyanBag", "PurpleBag", "BlueBag", "BrownBag", "GreenBag", "RedBag", "BlackBag"};
	
	public ItemBag()
	{
		this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.maxStackSize = 1;
	}
	
	public String getUnlocalizedName(ItemStack par1ItemStack)
    {
        int i = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, 15);
        return super.getUnlocalizedName() + "." + names[i];
    }
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) 
    {
		InventoryBag bag = new InventoryBag(stack);
		if(bag.getStackInSlot(bag.INV_SIZE-1)!= null)
		{
			int total = bag.getTotalItemsWithInventoryItems(bag.getStackInSlot(27).getItem(), AdvancedUtilitiesItems.bag);
			par3List.add("Contains: "+total+" "+bag.getStackInSlot(bag.INV_SIZE-1).getDisplayName());
		}
    }
	
	@SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tabs, List list)
    {
        for (int i = 0; i < 16; ++i)
        {
            list.add(new ItemStack(item, 1, i));
            item.setCreativeTab(AdvancedUtilities.advancedBEToolsTab);
        }
    }
	public CreativeTabs[] getCreativeTabs()
    {
        return new CreativeTabs[]{ AdvancedUtilities.advancedBEToolsTab };
    }
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister)
    {
    	for(int i = 0; i < 16; i++)
    	{
    		icons[i] = par1IconRegister.registerIcon("advancedutilities:"+names[i].toLowerCase());
    	}
    }
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int dmg)
    {
        return icons[dmg];
    }
    
    @Override
	public int getMaxItemUseDuration(ItemStack stack) 
	{
		return 68000; // return any value greater than zero
	}
    
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
    	par3EntityPlayer.openGui(AdvancedUtilities.instance, AdvancedUtilities.bagGui, par2World, (int) par3EntityPlayer.posX, (int) par3EntityPlayer.posY, (int) par3EntityPlayer.posZ);
     return par1ItemStack;
    }
}
