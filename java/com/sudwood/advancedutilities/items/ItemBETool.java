package com.sudwood.advancedutilities.items;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.sudwood.advancedutilities.AdvancedUtilities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBETool extends Item
{
	private IIcon[] icons = new IIcon[24];
	
	private static final int PICK = 0;
	private static final int SWORD = 1;
	private static final int SHOVEL = 2;
	private static final int AXE = 3;
	
	private static final int WOODB = 100;
	private static final int IRONB = 190;
	private static final int BRONZEB = 350;
	
	private static final int IRONT = 210;
	private static final int BRONZET = 350;
	
	private String[] names = {"IronPickaxe", "IronSword", "IronShovel", "IronAxe", "BronzePickaxe", "BronzeSword", "BronzeShovel", "BronzeAxe"};
	
	public ItemBETool()
	{
		this.setHasSubtypes(true);
        this.setMaxDamage(0);
	}
	
	public boolean isBookEnchantable(ItemStack stack, ItemStack book)
    {
        return true;
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
    	par3List.add("Damage: "+tag.getInteger("CurrentDamage")+" / "+tag.getInteger("MaxDamage"));
    	par3List.add("Attack: "+tag.getInteger("Attack")/2+" Hearts");
    	if(tag.getInteger("CurrentDamage")<=0)
    	{
    		par3List.add("BROKEN!");
    	}
    }
    
    public int getItemEnchantability()
    {
        return 9;
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
    	if(itemStack.getTagCompound() == null)
    		itemStack.setTagCompound(new NBTTagCompound());
    	NBTTagCompound tag = itemStack.getTagCompound();
    	switch(tag.getInteger("Type"))
    	{
    	case PICK:
    		if(par1Block.isToolEffective("pickaxe", 2))
    			return true;
    		break;
    	case SWORD:
    		if(par1Block.isToolEffective("sword", 2))
    			return true;
    		break;
    	case SHOVEL:
    		if(par1Block.isToolEffective("shovel", 2))
    			return true;
    		break;
    	case AXE:
    		if(par1Block.isToolEffective("axe", 2))
    			return true;
    		break;
    	}
    	
    	return false;
    }
    
    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
    {
    	if(stack.getTagCompound()== null)
    		stack.setTagCompound(new NBTTagCompound());
    	
    	NBTTagCompound tag = stack.getTagCompound();
    	int type = tag.getInteger("Type");
    	
    	if(type == SWORD)
    	{
		  	 if(tag.getInteger("CurrentDamage") >= 1)
		  	 {
		  	
		  	 return false;
		  	 }
		  	 else
		  	 {
		  		 
		  		 return true;
		  	 }
    	}
    	else
    	{
    		if(tag.getInteger("CurrentDamage") >= 2)
		  	 {
		  	
		  	 return false;
		  	 }
		  	 else
		  	 {
		  		 
		  		 return true;
		  	 }
    	}
    }
    
    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    @Override
    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.block;
    }

    /**
     * How long it takes to use or consume an item
     */
    @Override
    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 74800;
    }
    
    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
        return par1ItemStack;
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
    	switch(itemstack.getTagCompound().getInteger("Type"))
    	{
    	case PICK:
    		if(block.isToolEffective("pickaxe", 2))
    			return 10F;
    		break;
    	case SWORD:
    		if(block.isToolEffective("sword", 2))
    		return 10F;
    		break;
    	case SHOVEL:
    		if(block.isToolEffective("shovel", 2))
    			return 10F;
    		break;
    	case AXE:
    		if(block.isToolEffective("axe", 2))
    			return 10F;
    		break;
    	default:
    		return 3F;
    	}
    	return 1.5F;
    }
    
    /**
     * Current implementations of this method in child classes do not use the entry argument beside ev. They just raise
     * the damage on the stack.
     */
    @Override
    public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase)
    {
    	NBTTagCompound tag = par1ItemStack.getTagCompound();
    	if(tag.getInteger("Type") == SWORD)
    	{
	      	 if(tag.getInteger("CurrentDamage") >= 1)
	      	 {
		      	 tag.setInteger("CurrentDamage", tag.getInteger("CurrentDamage")-1);
		      	par2EntityLivingBase.attackEntityFrom(DamageSource.generic, tag.getInteger("Attack"));
		      	if(tag.getInteger("CurrentDamage") <= 0)
		      	{
		      		par3EntityLivingBase.worldObj.playSoundAtEntity(par3EntityLivingBase, "minecraft:random.break", 1F, 1F);
		      	}
		      	 return false;
	      	 }
	        return false;
    	}
    	else
    	{
    		if(tag.getInteger("CurrentDamage") >= 2)
	      	 {
		      	 tag.setInteger("CurrentDamage", tag.getInteger("CurrentDamage")-2);
		      	par2EntityLivingBase.attackEntityFrom(DamageSource.generic, tag.getInteger("Attack"));
		      	if(tag.getInteger("CurrentDamage") <= 0)
		      	{
		      		par3EntityLivingBase.worldObj.playSoundAtEntity(par3EntityLivingBase, "minecraft:random.break", 1F, 1F);
		      	}
		      	 return false;
	      	 }
	        return false;
    	}
    }
    
    public boolean onBlockDestroyed(ItemStack stack, World world, Block block, int p_150894_4_, int p_150894_5_, int p_150894_6_, EntityLivingBase player)
    {
    	NBTTagCompound tag = stack.getTagCompound();
    	if(tag.getInteger("Type") == PICK)
    	{
    		if(block.isToolEffective("pickaxe", 2) && tag.getInteger("CurrentDamage") >= 1)
	      	 {
		      	 tag.setInteger("CurrentDamage", tag.getInteger("CurrentDamage")-1);
		      	if(tag.getInteger("CurrentDamage") <= 0)
		      	{
		      		player.worldObj.playSoundAtEntity(player, "minecraft:random.break", 1F, 1F);
		      	}
		      	 return true;
	      	 }
   		if(!block.isToolEffective("pickaxe", 2) && tag.getInteger("CurrentDamage") >= 2)
	      	 {
		      	 tag.setInteger("CurrentDamage", tag.getInteger("CurrentDamage")-2);
		      	if(tag.getInteger("CurrentDamage") <= 0)
		      	{
		      		player.worldObj.playSoundAtEntity(player, "minecraft:random.break", 1F, 1F);
		      	}
		      	 return true;
	      	 }
	        return false;
    	}
    	if(tag.getInteger("Type") == SWORD)
    	{
    		if(tag.getInteger("CurrentDamage") >= 2)
	      	 {
		      	 tag.setInteger("CurrentDamage", tag.getInteger("CurrentDamage")-2);
		      	if(tag.getInteger("CurrentDamage") <= 0)
		      	{
		      		player.worldObj.playSoundAtEntity(player, "minecraft:random.break", 1F, 1F);
		      	}
		      	 return true;
	      	 }
	        return false;
    	}
    	if(tag.getInteger("Type") == SHOVEL)
    	{
    		if(block.isToolEffective("shovel", 2) && tag.getInteger("CurrentDamage") >= 1)
	      	 {
		      	 tag.setInteger("CurrentDamage", tag.getInteger("CurrentDamage")-1);
		      	if(tag.getInteger("CurrentDamage") <= 0)
		      	{
		      		player.worldObj.playSoundAtEntity(player, "minecraft:random.break", 1F, 1F);
		      	}
		      	 return true;
	      	 }
    		if(!block.isToolEffective("shovel", 2) && tag.getInteger("CurrentDamage") >= 2)
	      	 {
		      	 tag.setInteger("CurrentDamage", tag.getInteger("CurrentDamage")-2);
		      	if(tag.getInteger("CurrentDamage") <= 0)
		      	{
		      		player.worldObj.playSoundAtEntity(player, "minecraft:random.break", 1F, 1F);
		      	}
		      	 return true;
	      	 }
	        return false;
    	}
    	if(tag.getInteger("Type") == AXE)
    	{
    		if(block.isToolEffective("axe", 2) && tag.getInteger("CurrentDamage") >= 1)
	      	 {
		      	 tag.setInteger("CurrentDamage", tag.getInteger("CurrentDamage")-1);
		      	if(tag.getInteger("CurrentDamage") <= 0)
		      	{
		      		player.worldObj.playSoundAtEntity(player, "minecraft:random.break", 1F, 1F);
		      	}
		      	 return true;
	      	 }
    		if(!block.isToolEffective("axe", 2) && tag.getInteger("CurrentDamage") >= 2)
	      	 {
		      	 tag.setInteger("CurrentDamage", tag.getInteger("CurrentDamage")-2);
		      	if(tag.getInteger("CurrentDamage") <= 0)
		      	{
		      		player.worldObj.playSoundAtEntity(player, "minecraft:random.break", 1F, 1F);
		      	}
		      	 return true;
	      	 }
	        return false;
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
    	
    	if(tag.getInteger("Type")==SWORD)
    	{
	     	 if(tag.getInteger("CurrentDamage") >= 2)
	     	 {
		      	 return false;
	     	 }
	    	 else return true;
    	}
    	else
    	{
    		if(tag.getInteger("CurrentDamage") >= 1)
	     	 {
		      	 return false;
	     	 }
	    	 else return true;
    	}
    }
    
    /**
     * Called when item is crafted/smelted. Used only by maps so far.
     */
    public static void checkCreated(ItemStack stack) 
    {
    	if(stack.getTagCompound() == null)
    		stack.setTagCompound(new NBTTagCompound());
    	NBTTagCompound tag = stack.getTagCompound();
    	if(stack.getItemDamage() == 0 || stack.getItemDamage() == 3 || stack.getItemDamage() == 6 || stack.getItemDamage() == 9)
    	{
    		tag.setInteger("MaxDamage", IRONT+WOODB);
    		tag.setInteger("CurrentDamage", IRONT+WOODB);
    	}
    	if(stack.getItemDamage() == 12 || stack.getItemDamage() == 15 || stack.getItemDamage() == 18 || stack.getItemDamage() == 21)
    	{
    		tag.setInteger("MaxDamage", BRONZET+WOODB);
    		tag.setInteger("CurrentDamage", BRONZET+WOODB);
    	}
    	if(stack.getItemDamage() == 1 || stack.getItemDamage() == 4 || stack.getItemDamage() == 7 || stack.getItemDamage() == 10)
    	{
    		tag.setInteger("MaxDamage", IRONT+IRONB);
    		tag.setInteger("CurrentDamage", IRONT+IRONB);
    	}
    	if(stack.getItemDamage() == 13 || stack.getItemDamage() == 16 || stack.getItemDamage() == 19 || stack.getItemDamage() == 22)
    	{
    		tag.setInteger("MaxDamage", BRONZET+IRONB);
    		tag.setInteger("CurrentDamage", BRONZET+IRONB);
    	}
    	if(stack.getItemDamage() == 2 || stack.getItemDamage() == 5 || stack.getItemDamage() == 8 || stack.getItemDamage() == 11)
    	{
    		tag.setInteger("MaxDamage", IRONT+BRONZEB);
    		tag.setInteger("CurrentDamage", IRONT+BRONZEB);
    	}
    	if(stack.getItemDamage() == 14 || stack.getItemDamage() == 17 || stack.getItemDamage() == 20 || stack.getItemDamage() == 23)
    	{
    		tag.setInteger("MaxDamage", BRONZET+BRONZEB);
    		tag.setInteger("CurrentDamage", BRONZET+BRONZEB);
    	}
    	if(stack.getItemDamage() == 0 || stack.getItemDamage() == 1 || stack.getItemDamage() == 2 || stack.getItemDamage() == 12 || stack.getItemDamage() == 13 || stack.getItemDamage() == 14)
    	{
    		tag.setInteger("Type", PICK);
    		tag.setInteger("Attack", 3);
    	}
    	if(stack.getItemDamage() == 3 || stack.getItemDamage() == 4 || stack.getItemDamage() == 5 )
    	{
    		tag.setInteger("Type", SWORD);
    		tag.setInteger("Attack", 6);
    	}
    	if(stack.getItemDamage() == 15 || stack.getItemDamage() == 16 || stack.getItemDamage() == 17)
    	{
    		tag.setInteger("Type", SWORD);
    		tag.setInteger("Attack", 8);
    	}
    	if(stack.getItemDamage() == 6 || stack.getItemDamage() == 7 || stack.getItemDamage() == 8 || stack.getItemDamage() == 18 || stack.getItemDamage() == 19 || stack.getItemDamage() == 20)
    	{
    		tag.setInteger("Type", SHOVEL);
    		tag.setInteger("Attack", 3);
    	}
    	if(stack.getItemDamage() == 9 || stack.getItemDamage() == 10 || stack.getItemDamage() == 11 || stack.getItemDamage() == 21 || stack.getItemDamage() == 22 || stack.getItemDamage() == 23)
    	{
    		tag.setInteger("Type", AXE);
    		tag.setInteger("Attack", 3);
    	}
    }
	
	/**
     * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
     * different names based on their damage or NBT.
     */
    public String getUnlocalizedName(ItemStack par1ItemStack)
    {
        int i = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, 23);
        switch(i)
        {
        case 0:
        	return names[0];
        case 1:
        	return names[0];
        case 2:
        	return names[0];
        case 3:
        	return names[1];
        case 4:
        	return names[1];
        case 5:
        	return names[1];
        case 6:
        	return names[2];
        case 7:
        	return names[2];
        case 8:
        	return names[2];
        case 9:
        	return names[3];
        case 10:
        	return names[3];
        case 11:
        	return names[3];
        case 12:
        	return names[4];
        case 13:
        	return names[4];
        case 14:
        	return names[4];
        case 15:
        	return names[5];
        case 16:
        	return names[5];
        case 17:
        	return names[5];
        case 18:
        	return names[6];
        case 19:
        	return names[6];
        case 20:
        	return names[6];
        case 21:
        	return names[7];
        case 22:
        	return names[7];
        case 23:
        	return names[7];
        default: return "";
        }
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int par1)
    {
        return this.icons[par1];
    }
    
    public CreativeTabs[] getCreativeTabs()
    {
        return new CreativeTabs[]{ AdvancedUtilities.advancedBEToolsTab };
    }
    
    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tabs, List list)
    {
        for (int i = 0; i < 24; ++i)
        {
            list.add(new ItemStack(item, 1, i));
            item.setCreativeTab(AdvancedUtilities.advancedBEToolsTab);
        }
    }
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister)
    {
        this.icons[0] = Items.iron_pickaxe.getIconFromDamage(0);
        this.icons[1] = par1IconRegister.registerIcon("advancedutilities:ironpickirod");
        this.icons[2] = par1IconRegister.registerIcon("advancedutilities:ironpickbrod");
        this.icons[3] = Items.iron_sword.getIconFromDamage(0);
        this.icons[4] = par1IconRegister.registerIcon("advancedutilities:ironswordirod");
        this.icons[5] = par1IconRegister.registerIcon("advancedutilities:ironswordbrod");
        this.icons[6] = Items.iron_shovel.getIconFromDamage(0);
        this.icons[7] = par1IconRegister.registerIcon("advancedutilities:ironshovelirod");
        this.icons[8] = par1IconRegister.registerIcon("advancedutilities:ironshovelbrod");
        this.icons[9] = Items.iron_axe.getIconFromDamage(0);
        this.icons[10] = par1IconRegister.registerIcon("advancedutilities:ironaxeirod");
        this.icons[11] = par1IconRegister.registerIcon("advancedutilities:ironaxebrod");
        
        this.icons[12] = par1IconRegister.registerIcon("advancedutilities:bronzepick");
        this.icons[13] = par1IconRegister.registerIcon("advancedutilities:bronzepickirod");
        this.icons[14] = par1IconRegister.registerIcon("advancedutilities:bronzepickbrod");
        this.icons[15] = par1IconRegister.registerIcon("advancedutilities:bronzesword");
        this.icons[16] = par1IconRegister.registerIcon("advancedutilities:bronzeswordirod");
        this.icons[17] = par1IconRegister.registerIcon("advancedutilities:bronzeswordbrod");
        this.icons[18] = par1IconRegister.registerIcon("advancedutilities:bronzeshovel");
        this.icons[19] = par1IconRegister.registerIcon("advancedutilities:bronzeshovelirod");
        this.icons[20] = par1IconRegister.registerIcon("advancedutilities:bronzeshovelbrod");
        this.icons[21] = par1IconRegister.registerIcon("advancedutilities:bronzeaxe");
        this.icons[22] = par1IconRegister.registerIcon("advancedutilities:bronzeaxeirod");
        this.icons[23] = par1IconRegister.registerIcon("advancedutilities:bronzeaxebrod");
    }
}
