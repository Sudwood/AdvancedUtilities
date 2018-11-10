package com.sudwood.advancedutilities.items;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.stats.StatList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.world.BlockEvent;

import com.google.common.collect.Sets;
import com.sudwood.advancedutilities.AdvancedUtilities;
import com.sudwood.advancedutilities.HelperLibrary;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBETool extends ItemTool
{
	
	public int hamBreakRadius = 1;
    public int hamBreakDepth = 0;
    
    public int exBreakRadius = 1;
    public int exBreakDepth = 0;
	
	private IIcon[] icons = new IIcon[42];
	
	public static final int PICK = 0;
	public static final int SWORD = 1;
	public static final int SHOVEL = 2;
	public static final int AXE = 3;
	public static final int HAMMER = 4;
	public static final int EXCAVATOR = 5;
	
	private static final int WOODB = 100;
	private static final int IRONB = 190;
	private static final int BRONZEB = 350;
	private static final int STEELB = 800;
	
	private static final int IRONT = 210;
	private static final int BRONZET = 350;
	private static final int STEELT = 800;
	
	private static final int IRONHAM = 1100;
	private static final int BRONZEHAM = 1500;
	private static final int STEELHAM = 2800;
	
	private static final int IRONEX = 1100;
	private static final int BRONZEEX = 1500;
	private static final int STEELEX = 2800;
	
	private static final float BASEDIGSPEED = 10F;
	private static final float STEELDIGMODIFIER = 1.5F;
	private float DIGSPEED = 10F;
	private static final float BADSPEED = 3F;
	private static final float DEFAULTSPEED = 1.5F;
	
	 private static final Set field_150915_c = Sets.newHashSet(new Block[] {Blocks.cobblestone, Blocks.double_stone_slab, Blocks.stone_slab, Blocks.stone, Blocks.sandstone, Blocks.mossy_cobblestone, Blocks.iron_ore, Blocks.iron_block, Blocks.coal_ore, Blocks.gold_block, Blocks.gold_ore, Blocks.diamond_ore, Blocks.diamond_block, Blocks.ice, Blocks.netherrack, Blocks.lapis_ore, Blocks.lapis_block, Blocks.redstone_ore, Blocks.lit_redstone_ore, Blocks.rail, Blocks.detector_rail, Blocks.golden_rail, Blocks.activator_rail});
	
	private String[] names = {"IronPickaxe", "IronSword", "IronShovel", "IronAxe", 
			"BronzePickaxe", "BronzeSword", "BronzeShovel", "BronzeAxe",
			"BronzeHammer", "IronHammer", "BronzeExcavator", "IronExcavator", "SteelPickaxe",
			"SteelSword", "SteelShovel", "SteelAxe", "SteelHammer", "SteelExcavator"};
	
	public ItemBETool()
	{
		super(3, ToolMaterial.IRON, field_150915_c);
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
    	par3List.add("Attack: "+((tag.getInteger("Attack")+2*EnchantmentHelper.getEnchantmentLevel(Enchantment.sharpness.effectId, stack))/2)+" Hearts");
    	par3List.add("Level: "+tag.getInteger("ItemLevel"));
    	par3List.add("EXP: "+tag.getInteger("CurrentXP")+" / "+this.getMaxXPForType(tag.getInteger("Type")));
    	if(tag.getInteger("CurrentDamage")<=0)
    	{
    		par3List.add("BROKEN!");
    	}
    }
    
    public static int getMaxXPForType(int type)
    {
    	int temp = -1;
    	switch(type)
    	{
    	case PICK:
    		temp = 100;
    		break;
    	case SHOVEL:
    		temp = 100;
    		break;
    	case SWORD:
    		temp = 100;
    		break;
    	case AXE:
    		temp = 100;
    		break;
    	case HAMMER:
    		temp = 500;
    		break;
    	case EXCAVATOR:
    		temp = 500;
    		break;
    	}
    	return temp;
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
    		return false;
    	NBTTagCompound tag = itemStack.getTagCompound();
    	switch(tag.getInteger("Type"))
    	{
    	case PICK:
    		int templevel = tag.getInteger("ItemLevel");
    		if(templevel >= 15)
    		{
    			if((par1Block.isToolEffective("pickaxe", 3)&&par1Block.getHarvestTool(3)=="pickaxe")||par1Block == Blocks.obsidian | par1Block == Blocks.redstone_ore || par1Block == Blocks.lit_redstone_ore)
    			{
        			return true;
    			}
    			else if((par1Block.getMaterial()==Material.rock ||par1Block.getMaterial()==Material.anvil|| par1Block.getMaterial()==Material.iron) && par1Block.isToolEffective("pickaxe", 3) == false && par1Block.getHarvestTool(3)== null)
    			{
    				return true;
    			}
    			else return false;
    		}
    		else{
	    		if(par1Block.isToolEffective("pickaxe", 2)&&par1Block.getHarvestTool(2)=="pickaxe")
	    		{
	    			return true;
	    		}
	    		else if((par1Block.getMaterial()==Material.rock ||par1Block.getMaterial()==Material.anvil|| par1Block.getMaterial()==Material.iron) && par1Block.isToolEffective("pickaxe", 2) == false && par1Block.getHarvestTool(2)== null)
	    		{
	    			return true;
	    		}
    		}
    		break;
    	case HAMMER:
    		int templevel1 = tag.getInteger("ItemLevel");
    		if(templevel1 >= 10)
    		{
    			if((par1Block.isToolEffective("pickaxe", 3)&&par1Block.getHarvestTool(3)=="pickaxe")||par1Block == Blocks.obsidian | par1Block == Blocks.redstone_ore || par1Block == Blocks.lit_redstone_ore)
    			{
        			return true;
    			}
    			else if((par1Block.getMaterial()==Material.rock ||par1Block.getMaterial()==Material.anvil|| par1Block.getMaterial()==Material.iron) && par1Block.isToolEffective("pickaxe", 3) == false && par1Block.getHarvestTool(3)== null)
    			{
    				return true;
    			}
    			else return false;
    		}
    		else{
	    		if(par1Block.isToolEffective("pickaxe", 2)&&par1Block.getHarvestTool(2)=="pickaxe")
	    		{
	    			return true;
	    		}
	    		else if((par1Block.getMaterial()==Material.rock ||par1Block.getMaterial()==Material.anvil|| par1Block.getMaterial()==Material.iron) && par1Block.isToolEffective("pickaxe", 2) == false && par1Block.getHarvestTool(2)== null)
	    		{
	    			return true;
	    		}
    		}
    		break;
    	case SWORD:
    		if(par1Block.isToolEffective("sword", 2))
    			return true;
    		break;
    	case SHOVEL:
    		if(par1Block.isToolEffective("shovel", 2) || par1Block.getMaterial() == Material.grass || par1Block.getMaterial() == Material.ground)
    			return true;
    		break;
    	case EXCAVATOR:
    		if(par1Block.isToolEffective("shovel", 2) || par1Block.getMaterial() == Material.grass || par1Block.getMaterial() == Material.ground)
    			return true;
    		break;
    	case AXE:
    		if(par1Block.isToolEffective("axe", 2) || par1Block.getMaterial() == Material.wood)
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
    	if(itemstack.getTagCompound().getBoolean("isSteel"))
    	{
    		this.DIGSPEED = this.BASEDIGSPEED*this.STEELDIGMODIFIER;
    	}
    	else this.DIGSPEED = this.BASEDIGSPEED;
    	switch(itemstack.getTagCompound().getInteger("Type"))
    	{
    	case PICK:
    		int templevel = itemstack.getTagCompound().getInteger("ItemLevel");
    		if(templevel >= 15)
    		{
    			if((block.isToolEffective("pickaxe", 3)&&block.getHarvestTool(3)=="pickaxe")||block == Blocks.obsidian | block == Blocks.redstone_ore || block == Blocks.lit_redstone_ore)
    			{
    				return DIGSPEED+(DIGSPEED*(itemstack.getTagCompound().getInteger("ItemLevel")*0.01F));
    			}
    			else if((block.getMaterial()==Material.rock ||block.getMaterial()==Material.anvil|| block.getMaterial()==Material.iron) && block.isToolEffective("pickaxe", 3) == false && block.getHarvestTool(3)== null)
	    		{
	    			return DIGSPEED+(DIGSPEED*(itemstack.getTagCompound().getInteger("ItemLevel")*0.01F));
	    		}
    		}
    		else{
	    		if(block.isToolEffective("pickaxe", 2)&&block.getHarvestTool(2)=="pickaxe")
	    		{
	    			return DIGSPEED+(DIGSPEED*(itemstack.getTagCompound().getInteger("ItemLevel")*0.01F));
	    		}
	    		else if((block.getMaterial()==Material.rock ||block.getMaterial()==Material.anvil|| block.getMaterial()==Material.iron) && block.isToolEffective("pickaxe", 2) == false && block.getHarvestTool(2)== null)
	    		{
	    			return DIGSPEED+(DIGSPEED*(itemstack.getTagCompound().getInteger("ItemLevel")*0.01F));
	    		}
    		}
    		break;
    	case HAMMER:
    		int templevel1 = itemstack.getTagCompound().getInteger("ItemLevel");
    		if(templevel1 >= 10)
    		{
    			if((block.isToolEffective("pickaxe", 3)&&block.getHarvestTool(3)=="pickaxe")||block == Blocks.obsidian | block == Blocks.redstone_ore || block == Blocks.lit_redstone_ore)
    			{
    				return DIGSPEED+(DIGSPEED*(itemstack.getTagCompound().getInteger("ItemLevel")*0.01F));
    			}
    			else if((block.getMaterial()==Material.rock ||block.getMaterial()==Material.anvil|| block.getMaterial()==Material.iron) && block.isToolEffective("pickaxe", 3) == false && block.getHarvestTool(3)== null)
	    		{
	    			return DIGSPEED+(DIGSPEED*(itemstack.getTagCompound().getInteger("ItemLevel")*0.01F));
	    		}
    		}
    		else{
	    		if(block.isToolEffective("pickaxe", 2)&&block.getHarvestTool(2)=="pickaxe")
	    		{
	    			return DIGSPEED+(DIGSPEED*(itemstack.getTagCompound().getInteger("ItemLevel")*0.01F));
	    		}
	    		else if(((block.getMaterial()==Material.rock ||block.getMaterial()==Material.anvil|| block.getMaterial()==Material.iron) && block.isToolEffective("pickaxe", 2) == false && block.getHarvestTool(2)== null)&& block != Blocks.obsidian && block != Blocks.redstone_ore && block!=Blocks.lit_redstone_ore)
	    		{
	    			return DIGSPEED+(DIGSPEED*(itemstack.getTagCompound().getInteger("ItemLevel")*0.01F));
	    		}
    		}
    		break;
    	case EXCAVATOR:
    		if(block.isToolEffective("shovel", 2) || block.getMaterial() == Material.grass || block.getMaterial() == Material.ground)
    			return DIGSPEED+(DIGSPEED*(itemstack.getTagCompound().getInteger("ItemLevel")*0.01F));
    		break;
    	case SWORD:
    		if(block.isToolEffective("sword", 2) || block.getMaterial() == Material.cloth ||block.getMaterial() == Material.carpet|| block.getMaterial() == Material.web)
    			return DIGSPEED+(DIGSPEED*(itemstack.getTagCompound().getInteger("ItemLevel")*0.01F));
    		break;
    	case SHOVEL:
    		if(block.isToolEffective("shovel", 2) || block.getMaterial() == Material.grass || block.getMaterial() == Material.ground)
    			return DIGSPEED+(DIGSPEED*(itemstack.getTagCompound().getInteger("ItemLevel")*0.01F));
    		break;
    	case AXE:
    		if(block.isToolEffective("axe", 2) || block.getMaterial() == Material.wood)
    			return DIGSPEED+(DIGSPEED*(itemstack.getTagCompound().getInteger("ItemLevel")*0.01F));
    		break;
    	default:
    		return BADSPEED+(BADSPEED*(itemstack.getTagCompound().getInteger("ItemLevel")*0.01F));
    	}
    	return DEFAULTSPEED+(DEFAULTSPEED*(itemstack.getTagCompound().getInteger("ItemLevel")*0.01F));
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
	      		if(EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, par1ItemStack)>0)
				{
					Random rand = new Random();
					if(!(rand.nextInt(10) <=(0+EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, par1ItemStack))))
					{
						tag.setInteger("CurrentDamage", tag.getInteger("CurrentDamage")-1);
					}
				}
				else
				{
		      	 tag.setInteger("CurrentDamage", tag.getInteger("CurrentDamage")-1);
				}
		      	 int tempDam = 0;
		      	 if(tag.getInteger("ItemLevel")>= 5)
		      	 {
		      		 tempDam = tag.getInteger("ItemLevel")/5;
		      	 }
		      	int damage = tag.getInteger("Attack")+tempDam;
			     if(EnchantmentHelper.getEnchantmentLevel(Enchantment.sharpness.effectId, par1ItemStack) > 0)
			     {
			    	 damage+=EnchantmentHelper.getEnchantmentLevel(Enchantment.sharpness.effectId, par1ItemStack)*2;
			     }
		      	par2EntityLivingBase.attackEntityFrom(DamageSource.generic, damage);
		      	tag.setInteger("CurrentXP", tag.getInteger("CurrentXP")+1);
		      	if(tag.getInteger("CurrentXP") >= this.getMaxXPForType(tag.getInteger("Type")))
		      	{
		      		tag.setInteger("ItemLevel", tag.getInteger("ItemLevel")+1);
		      		tag.setInteger("CurrentXP", 0);
		      		par3EntityLivingBase.worldObj.playSoundAtEntity(par3EntityLivingBase, "minecraft:random.levelup", 1F, 1F);
		      		if(tag.getInteger("ItemLevel")/50 > 0 )
		      		{
		      			if(EnchantmentHelper.getEnchantmentLevel(Enchantment.looting.effectId, par1ItemStack)<=0)
		      			{
		      				par1ItemStack.addEnchantment(Enchantment.looting, tag.getInteger("ItemLevel")/50);
		      			}
		      			else if(EnchantmentHelper.getEnchantmentLevel(Enchantment.looting.effectId, par1ItemStack) < tag.getInteger("ItemLevel")/50)
		      			{
		      				Map map = EnchantmentHelper.getEnchantments(par1ItemStack);
	    					map.put(Enchantment.looting.effectId, tag.getInteger("ItemLevel")/50);
	    					EnchantmentHelper.setEnchantments(map, par1ItemStack);
		      			}
		      		}
		      	}
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
    			if(EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, par1ItemStack)>0)
				{
					Random rand = new Random();
					if(!(rand.nextInt(10) <=(0+EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, par1ItemStack))))
					{
						tag.setInteger("CurrentDamage", tag.getInteger("CurrentDamage")-2);
					}
				}
				else
				{
		      	 tag.setInteger("CurrentDamage", tag.getInteger("CurrentDamage")-2);
				}
		      	int damage = tag.getInteger("Attack");
			     if(EnchantmentHelper.getEnchantmentLevel(Enchantment.sharpness.effectId, par1ItemStack) > 0)
			     {
			    	 damage+=EnchantmentHelper.getEnchantmentLevel(Enchantment.sharpness.effectId, par1ItemStack)*2;
			     }
		      	par2EntityLivingBase.attackEntityFrom(DamageSource.generic, damage);
		      	if(tag.getInteger("CurrentDamage") <= 0)
		      	{
		      		par3EntityLivingBase.worldObj.playSoundAtEntity(par3EntityLivingBase, "minecraft:random.break", 1F, 1F);
		      	}
		      	 return false;
	      	 }
	        return false;
    	}
    }
    
    public boolean onBlockDestroyed(ItemStack stack, World world, Block block, int x, int y, int z, EntityLivingBase player)
    {
    	NBTTagCompound tag = stack.getTagCompound();
    	if(tag.getInteger("Type") == PICK)
    	{
			if(block.isToolEffective("pickaxe", 2) && tag.getInteger("CurrentDamage") >= 1)
	      	 {
				if(EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, stack)>0)
				{
					Random rand = new Random();
					if(!(rand.nextInt(10) <=(0+EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, stack))))
					{
						tag.setInteger("CurrentDamage", tag.getInteger("CurrentDamage")-1);
					}
				}
				else
				{
		      	 tag.setInteger("CurrentDamage", tag.getInteger("CurrentDamage")-1);
				}
		      	tag.setInteger("CurrentXP", tag.getInteger("CurrentXP")+1);
		      	if(tag.getInteger("CurrentXP") >= this.getMaxXPForType(tag.getInteger("Type")))
		      	{
		      		tag.setInteger("ItemLevel", tag.getInteger("ItemLevel")+1);
		      		tag.setInteger("CurrentXP", 0);
		      		player.worldObj.playSoundAtEntity(player, "minecraft:random.levelup", 1F, 1F);
		      		if(tag.getInteger("ItemLevel")/50 > 0 )
		      		{
		      			if(EnchantmentHelper.getEnchantmentLevel(Enchantment.fortune.effectId, stack)<=0)
		      			{
		      				stack.addEnchantment(Enchantment.fortune, tag.getInteger("ItemLevel")/50);
		      			}
		      			else if(EnchantmentHelper.getEnchantmentLevel(Enchantment.fortune.effectId, stack) < tag.getInteger("ItemLevel")/50)
		      			{
		      				Map map = EnchantmentHelper.getEnchantments(stack);
	    					map.put(Enchantment.fortune.effectId, tag.getInteger("ItemLevel")/50);
	    					EnchantmentHelper.setEnchantments(map, stack);
		      			}
		      		}
		      	}
		      	if(tag.getInteger("CurrentDamage") <= 0)
		      	{
		      		player.worldObj.playSoundAtEntity(player, "minecraft:random.break", 1F, 1F);
		      	}
		      	 return true;
	      	 }
	   		if(!block.isToolEffective("pickaxe", 2) && tag.getInteger("CurrentDamage") >= 2)
	      	 {
	   			if(EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, stack)>0)
				{
					Random rand = new Random();
					if(!(rand.nextInt(10) <=(0+EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, stack))))
					{
						tag.setInteger("CurrentDamage", tag.getInteger("CurrentDamage")-2);
					}
				}
				else
				{
		      	 tag.setInteger("CurrentDamage", tag.getInteger("CurrentDamage")-2);
				}
		      	if(tag.getInteger("CurrentDamage") <= 0)
		      	{
		      		player.worldObj.playSoundAtEntity(player, "minecraft:random.break", 1F, 1F);
		      	}
		      	 return true;
	      	 }
	        return false;
    	}
    	if(tag.getInteger("Type") == HAMMER)
    	{
    		if(block.isToolEffective("pickaxe", 2) && tag.getInteger("CurrentDamage") >= 1)
	      	 {
    			if(EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, stack)>0)
				{
					Random rand = new Random();
					if(!(rand.nextInt(10) <=(0+EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, stack))))
					{
						tag.setInteger("CurrentDamage", tag.getInteger("CurrentDamage")-1);
					}
				}
				else
				{
		      	 tag.setInteger("CurrentDamage", tag.getInteger("CurrentDamage")-1);
				}
		      	tag.setInteger("CurrentXP", tag.getInteger("CurrentXP")+1);
		      	if(tag.getInteger("CurrentXP") >= this.getMaxXPForType(tag.getInteger("Type")))
		      	{
		      		tag.setInteger("ItemLevel", tag.getInteger("ItemLevel")+1);
		      		tag.setInteger("CurrentXP", 0);
		      		player.worldObj.playSoundAtEntity(player, "minecraft:random.levelup", 1F, 1F);
		      		if(tag.getInteger("ItemLevel")/50 > 0 )
		      		{
		      			if(EnchantmentHelper.getEnchantmentLevel(Enchantment.fortune.effectId, stack)<=0)
		      			{
		      				stack.addEnchantment(Enchantment.fortune, tag.getInteger("ItemLevel")/50);
		      			}
		      			else if(EnchantmentHelper.getEnchantmentLevel(Enchantment.fortune.effectId, stack) < tag.getInteger("ItemLevel")/50)
		      			{
		      				Map map = EnchantmentHelper.getEnchantments(stack);
	    					map.put(Enchantment.fortune.effectId, tag.getInteger("ItemLevel")/50);
	    					EnchantmentHelper.setEnchantments(map, stack);
		      			}
		      		}
		      	}
		      	if(tag.getInteger("CurrentDamage") <= 0)
		      	{
		      		player.worldObj.playSoundAtEntity(player, "minecraft:random.break", 1F, 1F);
		      	}
		      	 return true;
	      	 }
	   		if(!block.isToolEffective("pickaxe", 2) && tag.getInteger("CurrentDamage") >= 2)
	      	 {
	   			if(EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, stack)>0)
				{
					Random rand = new Random();
					if(!(rand.nextInt(10) <=(0+EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, stack))))
					{
						tag.setInteger("CurrentDamage", tag.getInteger("CurrentDamage")-2);
					}
				}
				else
				{
		      	 tag.setInteger("CurrentDamage", tag.getInteger("CurrentDamage")-2);
				}
		      	if(tag.getInteger("CurrentDamage") <= 0)
		      	{
		      		player.worldObj.playSoundAtEntity(player, "minecraft:random.break", 1F, 1F);
		      	}
		      	 return true;
	      	 }
	        return false;
    	}
    	if(tag.getInteger("Type") == EXCAVATOR)
    	{
    		if(block.isToolEffective("shovel", 2) && tag.getInteger("CurrentDamage") >= 1)
	      	 {
    			if(EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, stack)>0)
				{
					Random rand = new Random();
					if(!(rand.nextInt(10) <=(0+EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, stack))))
					{
						tag.setInteger("CurrentDamage", tag.getInteger("CurrentDamage")-1);
					}
				}
				else
				{
		      	 tag.setInteger("CurrentDamage", tag.getInteger("CurrentDamage")-1);
				}
		      	tag.setInteger("CurrentXP", tag.getInteger("CurrentXP")+1);
		      	if(tag.getInteger("CurrentXP") >= this.getMaxXPForType(tag.getInteger("Type")))
		      	{
		      		tag.setInteger("ItemLevel", tag.getInteger("ItemLevel")+1);
		      		tag.setInteger("CurrentXP", 0);
		      		player.worldObj.playSoundAtEntity(player, "minecraft:random.levelup", 1F, 1F);
		      		if(tag.getInteger("ItemLevel")/50 > 0 )
		      		{
		      			if(EnchantmentHelper.getEnchantmentLevel(Enchantment.fortune.effectId, stack)<=0)
		      			{
		      				stack.addEnchantment(Enchantment.fortune, tag.getInteger("ItemLevel")/50);
		      			}
		      			else if(EnchantmentHelper.getEnchantmentLevel(Enchantment.fortune.effectId, stack) < tag.getInteger("ItemLevel")/50)
		      			{
		      				Map map = EnchantmentHelper.getEnchantments(stack);
	    					map.put(Enchantment.fortune.effectId, tag.getInteger("ItemLevel")/50);
	    					EnchantmentHelper.setEnchantments(map, stack);
		      			}
		      		}
		      	}
		      	if(tag.getInteger("CurrentDamage") <= 0)
		      	{
		      		player.worldObj.playSoundAtEntity(player, "minecraft:random.break", 1F, 1F);
		      	}
		      	 return true;
	      	 }
	   		if(!block.isToolEffective("shovel", 2) && tag.getInteger("CurrentDamage") >= 2)
	      	 {
	   			if(EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, stack)>0)
				{
					Random rand = new Random();
					if(!(rand.nextInt(10) <=(0+EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, stack))))
					{
						tag.setInteger("CurrentDamage", tag.getInteger("CurrentDamage")-2);
					}
				}
				else
				{
		      	 tag.setInteger("CurrentDamage", tag.getInteger("CurrentDamage")-2);
				}
	   			
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
    			if(EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, stack)>0)
				{
					Random rand = new Random();
					if(!(rand.nextInt(10) <=(0+EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, stack))))
					{
						tag.setInteger("CurrentDamage", tag.getInteger("CurrentDamage")-2);
					}
				}
				else
				{
		      	 tag.setInteger("CurrentDamage", tag.getInteger("CurrentDamage")-2);
				}
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
    			if(EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, stack)>0)
				{
					Random rand = new Random();
					if(!(rand.nextInt(10) <=(0+EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, stack))))
					{
						tag.setInteger("CurrentDamage", tag.getInteger("CurrentDamage")-1);
					}
				}
				else
				{
		      	 tag.setInteger("CurrentDamage", tag.getInteger("CurrentDamage")-1);
				}
		      	tag.setInteger("CurrentXP", tag.getInteger("CurrentXP")+1);
		      	if(tag.getInteger("CurrentXP") >= this.getMaxXPForType(tag.getInteger("Type")))
		      	{
		      		tag.setInteger("ItemLevel", tag.getInteger("ItemLevel")+1);
		      		tag.setInteger("CurrentXP", 0);
		      		player.worldObj.playSoundAtEntity(player, "minecraft:random.levelup", 1F, 1F);
		      		if(tag.getInteger("ItemLevel")/50 > 0 )
		      		{
		      			if(EnchantmentHelper.getEnchantmentLevel(Enchantment.fortune.effectId, stack)<=0)
		      			{
		      				stack.addEnchantment(Enchantment.fortune, tag.getInteger("ItemLevel")/50);
		      			}
		      			else if(EnchantmentHelper.getEnchantmentLevel(Enchantment.fortune.effectId, stack) < tag.getInteger("ItemLevel")/50)
		      			{
		      				Map map = EnchantmentHelper.getEnchantments(stack);
	    					map.put(Enchantment.fortune.effectId, tag.getInteger("ItemLevel")/50);
	    					EnchantmentHelper.setEnchantments(map, stack);
		      			}
		      		}
		      	}
		      	if(tag.getInteger("CurrentDamage") <= 0)
		      	{
		      		player.worldObj.playSoundAtEntity(player, "minecraft:random.break", 1F, 1F);
		      	}
		      	 return true;
	      	 }
    		if(!block.isToolEffective("shovel", 2) && tag.getInteger("CurrentDamage") >= 2)
	      	 {
    			if(EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, stack)>0)
				{
					Random rand = new Random();
					if(!(rand.nextInt(10) <=(0+EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, stack))))
					{
						tag.setInteger("CurrentDamage", tag.getInteger("CurrentDamage")-2);
					}
				}
				else
				{
		      	 tag.setInteger("CurrentDamage", tag.getInteger("CurrentDamage")-2);
				}
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
    		if(tag.getInteger("GoingDamage")>0 && tag.getInteger("CurrentDamage") >= tag.getInteger("GoingDamage"))
	      	 {
    			if(EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, stack)>0)
				{
					Random rand = new Random();
					if(!(rand.nextInt(10) <=(0+EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, stack))))
					{
						tag.setInteger("CurrentDamage", tag.getInteger("CurrentDamage")-tag.getInteger("GoingDamage"));
					}
				}
				else
				{
		      	 tag.setInteger("CurrentDamage", tag.getInteger("CurrentDamage")-tag.getInteger("GoingDamage"));
				}
		      	 tag.setInteger("CurrentDamage", tag.getInteger("CurrentDamage")-tag.getInteger("GoingDamage"));
		      	tag.setInteger("CurrentXP", tag.getInteger("CurrentXP")+tag.getInteger("GoingDamage"));
		      	tag.setInteger("GoingDamage", 0);
		      	if(tag.getInteger("CurrentXP") >= this.getMaxXPForType(tag.getInteger("Type")))
		      	{
		      		tag.setInteger("ItemLevel", tag.getInteger("ItemLevel")+1);
		      		tag.setInteger("CurrentXP", 0);
		      		player.worldObj.playSoundAtEntity(player, "minecraft:random.levelup", 1F, 1F);
		      		if(tag.getInteger("ItemLevel")/50 > 0 )
		      		{
		      			if(EnchantmentHelper.getEnchantmentLevel(Enchantment.fortune.effectId, stack)<=0)
		      			{
		      				stack.addEnchantment(Enchantment.fortune, tag.getInteger("ItemLevel")/50);
		      			}
		      			else if(EnchantmentHelper.getEnchantmentLevel(Enchantment.fortune.effectId, stack) < tag.getInteger("ItemLevel")/50)
		      			{
		      				Map map = EnchantmentHelper.getEnchantments(stack);
	    					map.put(Enchantment.fortune.effectId, tag.getInteger("ItemLevel")/50);
	    					EnchantmentHelper.setEnchantments(map, stack);
		      			}
		      		}
		      	}
		      	if(tag.getInteger("CurrentDamage") <= 0)
		      	{
		      		player.worldObj.playSoundAtEntity(player, "minecraft:random.break", 1F, 1F);
		      	}
		      	 return true;
	      	 }
    		else if(!block.isToolEffective("axe", 2) && tag.getInteger("CurrentDamage") >= 2)
	      	 {
    			if(EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, stack)>0)
				{
					Random rand = new Random();
					if(!(rand.nextInt(10) <=(0+EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, stack))))
					{
						tag.setInteger("CurrentDamage", tag.getInteger("CurrentDamage")-2);
					}
				}
				else
				{
		      	 tag.setInteger("CurrentDamage", tag.getInteger("CurrentDamage")-2);
				}
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
    public boolean onBlockStartBreak(ItemStack stack, int x, int y, int z, EntityPlayer player)
    {
    	if(stack.getTagCompound()==null)
		  {
			  stack.setTagCompound(new NBTTagCompound());
		  }
    	NBTTagCompound tag = stack.getTagCompound();
    	
    	if(tag.getInteger("Type")==SWORD)
    	{
	     	 if(tag.getInteger("CurrentDamage") >= 2)
	     	 {
		      	 return false;
	     	 }
	    	 else return true;
    	}
    	if(tag.getInteger("Type") == HAMMER)
    	{
    		if(tag.getInteger("CurrentDamage") >= 9)
    		{
    			Block block = player.worldObj.getBlock(x,y,z);
    	        int meta = player.worldObj.getBlockMetadata(x,y,z);
    	        
    	        if(block == null || !this.canHarvestBlock(block, stack))
    	            return super.onBlockStartBreak(stack, x,y,z, player);
    	        
    	        MovingObjectPosition mop = HelperLibrary.raytraceFromEntity(player.worldObj, player, false,  4.5d);
    	        if(mop == null)
    	            return super.onBlockStartBreak(stack, x,y,z, player);
    	        int sideHit = mop.sideHit;
    	        
    	        int xRange = hamBreakRadius;
    	        int yRange = hamBreakRadius;
    	        int zRange = hamBreakDepth;
    	        switch (sideHit) 
    	        {
    	            case 0:
    	            case 1:
    	                yRange = hamBreakDepth;
    	                zRange = hamBreakRadius;
    	                break;
    	            case 2:
    	            case 3:
    	                xRange = hamBreakRadius;
    	                zRange = hamBreakDepth;
    	                break;
    	            case 4:
    	            case 5:
    	                xRange = hamBreakDepth;
    	                zRange = hamBreakRadius;
    	                break;
    	        }
    	        
    	        for (int xPos = x - xRange; xPos <= x + xRange; xPos++)
    	            for (int yPos = y - yRange; yPos <= y + yRange; yPos++)
    	                for (int zPos = z - zRange; zPos <= z + zRange; zPos++) {
    	                    // don't break the originally already broken block, duh
    	                    if (xPos == x && yPos == y && zPos == z)
    	                    {
    	                    	continue;
    	                    }
    	                    	
    	                        breakExtraBlock(player.worldObj, xPos, yPos, zPos, sideHit, player, x,y,z);
    	                }
    	        
    			return false;
    		}
    		else
    		return true;
    	}
    	if(tag.getInteger("Type") == EXCAVATOR)
    	{
    		if(tag.getInteger("CurrentDamage") >= 9)
    		{
    			Block block = player.worldObj.getBlock(x,y,z);
    	        int meta = player.worldObj.getBlockMetadata(x,y,z);
    	        
    	        if(block == null || !this.canHarvestBlock(block, stack))
    	            return super.onBlockStartBreak(stack, x,y,z, player);
    	        
    	        MovingObjectPosition mop = HelperLibrary.raytraceFromEntity(player.worldObj, player, false,  4.5d);
    	        if(mop == null)
    	            return super.onBlockStartBreak(stack, x,y,z, player);
    	        int sideHit = mop.sideHit;
    	        
    	        int xRange = exBreakRadius;
    	        int yRange = exBreakRadius;
    	        int zRange = exBreakDepth;
    	        switch (sideHit) 
    	        {
    	            case 0:
    	            case 1:
    	                yRange = exBreakDepth;
    	                zRange = exBreakRadius;
    	                break;
    	            case 2:
    	            case 3:
    	                xRange = exBreakRadius;
    	                zRange = exBreakDepth;
    	                break;
    	            case 4:
    	            case 5:
    	                xRange = exBreakDepth;
    	                zRange = exBreakRadius;
    	                break;
    	        }
    	        
    	        for (int xPos = x - xRange; xPos <= x + xRange; xPos++)
    	            for (int yPos = y - yRange; yPos <= y + yRange; yPos++)
    	                for (int zPos = z - zRange; zPos <= z + zRange; zPos++) {
    	                    // don't break the originally already broken block, duh
    	                    if (xPos == x && yPos == y && zPos == z)
    	                    {
    	                    	continue;
    	                    }
    	                    	
    	                        breakExtraBlock(player.worldObj, xPos, yPos, zPos, sideHit, player, x,y,z);
    	                }
    	        
    			return false;
    		}
    		else
    		return true;
    	}
    	if(tag.getInteger("Type") == AXE)
    	{
    		 if (!stack.hasTagCompound())
    	           return true;

    	        World world = player.worldObj;
    	        final Block wood = world.getBlock(x, y, z);

    	        if (wood == null)
    	            return true;

    	        if (wood.isToolEffective("axe", 2) && wood.isWood(world, x, y, z) || wood.getMaterial() == Material.sponge)
    	        {
    	            if(detectTree(world, x,y,z, wood)) {
    	                NBTTagCompound tags = stack.getTagCompound().getCompoundTag("InfiTool");
    	                int meta = world.getBlockMetadata(x, y, z);
    	               int temp = breakTree(world, x, y, z, stack, tags, wood, meta, player);
    	            	  world.playSoundAtEntity(player, "minecraft:dig.wood", 1F, 1F);
    	                // custom block breaking code, don't call vanilla code
    	                return true;
    	            }
    	        }

    	        return false;
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
    	if(tag.getBoolean("hasCreated"))
    	{
    		return;
    	}
    	if(stack.getItemDamage() == 0 || stack.getItemDamage() == 3 || stack.getItemDamage() == 6 || stack.getItemDamage() == 9)
    	{
    		tag.setInteger("MaxDamage", IRONT+WOODB);
    		tag.setInteger("CurrentDamage", IRONT+WOODB);
    		tag.setBoolean("isSteel", false);
    	}
    	if(stack.getItemDamage() == 12 || stack.getItemDamage() == 15 || stack.getItemDamage() == 18 || stack.getItemDamage() == 21)
    	{
    		tag.setInteger("MaxDamage", BRONZET+WOODB);
    		tag.setInteger("CurrentDamage", BRONZET+WOODB);
    		tag.setBoolean("isSteel", false);
    	}
    	if(stack.getItemDamage() == 1 || stack.getItemDamage() == 4 || stack.getItemDamage() == 7 || stack.getItemDamage() == 10)
    	{
    		tag.setInteger("MaxDamage", IRONT+IRONB);
    		tag.setInteger("CurrentDamage", IRONT+IRONB);
    		tag.setBoolean("isSteel", false);
    	}
    	if(stack.getItemDamage() == 13 || stack.getItemDamage() == 16 || stack.getItemDamage() == 19 || stack.getItemDamage() == 22)
    	{
    		tag.setInteger("MaxDamage", BRONZET+IRONB);
    		tag.setInteger("CurrentDamage", BRONZET+IRONB);
    		tag.setBoolean("isSteel", false);
    	}
    	if(stack.getItemDamage() == 2 || stack.getItemDamage() == 5 || stack.getItemDamage() == 8 || stack.getItemDamage() == 11)
    	{
    		tag.setInteger("MaxDamage", IRONT+BRONZEB);
    		tag.setInteger("CurrentDamage", IRONT+BRONZEB);
    		tag.setBoolean("isSteel", false);
    	}
    	if(stack.getItemDamage() == 14 || stack.getItemDamage() == 17 || stack.getItemDamage() == 20 || stack.getItemDamage() == 23)
    	{
    		tag.setInteger("MaxDamage", BRONZET+BRONZEB);
    		tag.setInteger("CurrentDamage", BRONZET+BRONZEB);
    		tag.setBoolean("isSteel", false);
    	}
    	if(stack.getItemDamage() == 36 || stack.getItemDamage() == 37 || stack.getItemDamage() == 38 || stack.getItemDamage() == 39)
    	{
    		tag.setInteger("MaxDamage", STEELT+STEELB);
    		tag.setInteger("CurrentDamage", STEELT+STEELB);
    		tag.setBoolean("isSteel", true);
    	}
    	if(stack.getItemDamage() == 0 || stack.getItemDamage() == 1 || stack.getItemDamage() == 2 || stack.getItemDamage() == 12 || stack.getItemDamage() == 13 || stack.getItemDamage() == 14 || stack.getItemDamage() == 36)
    	{
    		tag.setInteger("Type", PICK);
    		if(tag.getBoolean("isSteel"))
    		{
    			tag.setInteger("Attack", 6);
    		}
    		else
    		tag.setInteger("Attack", 3);
    	}
    	if(stack.getItemDamage() == 3 || stack.getItemDamage() == 4 || stack.getItemDamage() == 5  || stack.getItemDamage() == 37)
    	{
    		tag.setInteger("Type", SWORD);
    		if(tag.getBoolean("isSteel"))
    		{
    			tag.setInteger("Attack", 16);
    		}
    		else
    		tag.setInteger("Attack", 6);
    	}
    	if(stack.getItemDamage() == 15 || stack.getItemDamage() == 16 || stack.getItemDamage() == 17)
    	{
    		tag.setInteger("Type", SWORD);
    		tag.setInteger("Attack", 8);
    	}
    	if(stack.getItemDamage() == 6 || stack.getItemDamage() == 7 || stack.getItemDamage() == 8 || stack.getItemDamage() == 18 || stack.getItemDamage() == 19 || stack.getItemDamage() == 20  || stack.getItemDamage() == 38)
    	{
    		tag.setInteger("Type", SHOVEL);
    		if(tag.getBoolean("isSteel"))
    		{
    			tag.setInteger("Attack", 6);
    		}
    		else
    		tag.setInteger("Attack", 3);
    	}
    	if(stack.getItemDamage() == 9 || stack.getItemDamage() == 10 || stack.getItemDamage() == 11 || stack.getItemDamage() == 21 || stack.getItemDamage() == 22 || stack.getItemDamage() == 23 || stack.getItemDamage() == 39)
    	{
    		tag.setInteger("Type", AXE);
    		if(tag.getBoolean("isSteel"))
    		{
    			tag.setInteger("Attack", 6);
    		}
    		else
    		tag.setInteger("Attack", 3);
    	}
    	
    	if(stack.getItemDamage() == 24)
    	{
    		tag.setInteger("MaxDamage", BRONZEHAM+WOODB);
    		tag.setInteger("CurrentDamage", BRONZEHAM+WOODB);
    		tag.setBoolean("isSteel", false);
    	}
    	if(stack.getItemDamage() == 25)
    	{
    		tag.setInteger("MaxDamage", BRONZEHAM+IRONB);
    		tag.setInteger("CurrentDamage", BRONZEHAM+IRONB);
    		tag.setBoolean("isSteel", false);
    	}
    	if(stack.getItemDamage() == 26)
    	{
    		tag.setInteger("MaxDamage", BRONZEHAM+BRONZEB);
    		tag.setInteger("CurrentDamage", BRONZEHAM+BRONZEB);
    		tag.setBoolean("isSteel", false);
    	}
    	if(stack.getItemDamage() == 27)
    	{
    		tag.setInteger("MaxDamage", IRONHAM+WOODB);
    		tag.setInteger("CurrentDamage", IRONHAM+WOODB);
    		tag.setBoolean("isSteel", false);
    	}
    	if(stack.getItemDamage() == 28)
    	{
    		tag.setInteger("MaxDamage", IRONHAM+BRONZEB);
    		tag.setInteger("CurrentDamage", IRONHAM+BRONZEB);
    		tag.setBoolean("isSteel", false);
    	}
    	if(stack.getItemDamage() == 29)
    	{
    		tag.setInteger("MaxDamage", IRONHAM+IRONB);
    		tag.setInteger("CurrentDamage", IRONHAM+IRONB);
    		tag.setBoolean("isSteel", false);
    	}
    	if(stack.getItemDamage() == 30)
    	{
    		tag.setInteger("MaxDamage", BRONZEEX+WOODB);
    		tag.setInteger("CurrentDamage", BRONZEEX+WOODB);
    		tag.setBoolean("isSteel", false);
    	}
    	if(stack.getItemDamage() == 31)
    	{
    		tag.setInteger("MaxDamage", BRONZEEX+IRONB);
    		tag.setInteger("CurrentDamage", BRONZEEX+IRONB);
    		tag.setBoolean("isSteel", false);
    	}
    	if(stack.getItemDamage() == 32)
    	{
    		tag.setInteger("MaxDamage", BRONZEEX+BRONZEB);
    		tag.setInteger("CurrentDamage", BRONZEEX+BRONZEB);
    		tag.setBoolean("isSteel", false);
    	}
    	if(stack.getItemDamage() == 33)
    	{
    		tag.setInteger("MaxDamage", IRONEX+WOODB);
    		tag.setInteger("CurrentDamage", IRONEX+WOODB);
    		tag.setBoolean("isSteel", false);
    	}
    	if(stack.getItemDamage() == 34)
    	{
    		tag.setInteger("MaxDamage", IRONEX+BRONZEB);
    		tag.setInteger("CurrentDamage", IRONEX+BRONZEB);
    		tag.setBoolean("isSteel", false);
    	}
    	if(stack.getItemDamage() == 35)
    	{
    		tag.setInteger("MaxDamage", IRONEX+IRONB);
    		tag.setInteger("CurrentDamage", IRONEX+IRONB);
    		tag.setBoolean("isSteel", false);
    	}
    	if(stack.getItemDamage() == 41)
    	{
    		tag.setInteger("MaxDamage",STEELEX+STEELB);
    		tag.setInteger("CurrentDamage", STEELEX+STEELB);
    		tag.setBoolean("isSteel", true);
    	}
    	if(stack.getItemDamage() == 40)
    	{
    		tag.setInteger("MaxDamage", STEELHAM+STEELB);
    		tag.setInteger("CurrentDamage", STEELHAM+STEELB);
    		tag.setBoolean("isSteel", true);
    	}
    	
    	if(stack.getItemDamage() == 24 || stack.getItemDamage() == 25 || stack.getItemDamage() == 26  || stack.getItemDamage() == 40)
    	{
    		tag.setInteger("Type", HAMMER);
    		tag.setInteger("Attack", 7);
    	}
    	if(stack.getItemDamage() == 27 || stack.getItemDamage() == 28 || stack.getItemDamage() == 29)
    	{
    		tag.setInteger("Type", HAMMER);
    		tag.setInteger("Attack", 7);
    	}
    	if(stack.getItemDamage() == 30 || stack.getItemDamage() == 31 || stack.getItemDamage() == 32  || stack.getItemDamage() == 41)
    	{
    		tag.setInteger("Type", EXCAVATOR);
    		if(tag.getBoolean("isSteel"))
    		{
    			tag.setInteger("Attack", 14);
    		}
    		else
    		tag.setInteger("Attack", 7);
    	}
    	if(stack.getItemDamage() == 33 || stack.getItemDamage() == 34 || stack.getItemDamage() == 35)
    	{
    		tag.setInteger("Type", EXCAVATOR);
    		if(tag.getBoolean("isSteel"))
    		{
    			tag.setInteger("Attack", 14);
    		}
    		else
    		tag.setInteger("Attack", 7);
    	}
    	
    	if(EnchantmentHelper.getEnchantmentLevel(Enchantment.sharpness.effectId, stack) == 1)
		{
    		tag.setInteger("Attack", tag.getInteger("Attack")+2);
		}
    	if(EnchantmentHelper.getEnchantmentLevel(Enchantment.sharpness.effectId, stack) == 2)
		{
    		tag.setInteger("Attack", tag.getInteger("Attack")+6);
		}
    	if(EnchantmentHelper.getEnchantmentLevel(Enchantment.sharpness.effectId, stack) == 3)
		{
    		tag.setInteger("Attack", tag.getInteger("Attack")+10);
		}
    	tag.setBoolean("checkCreated", true);
    }
	
	/**
     * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
     * different names based on their damage or NBT.
     */
    public String getUnlocalizedName(ItemStack par1ItemStack)
    {
        int i = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, 42);
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
        case 24:
        	return names[8];
        case 25:
        	return names[8];
        case 26:
        	return names[8];
        case 27:
        	return names[9];
        case 28:
        	return names[9];
        case 29:
        	return names[9];
        case 30:
        	return names[10];
        case 31:
        	return names[10];
        case 32:
        	return names[10];
        case 33:
        	return names[11];
        case 34:
        	return names[11];
        case 35:
        	return names[11];
        case 36:
        	return names[12];
        case 37:
        	return names[13];
        case 38:
        	return names[14];
        case 39:
        	return names[15];
        case 40:
        	return names[16];
        case 41:
        	return names[17];
        	
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
        for (int i = 0; i < 42; ++i)
        {
        	ItemStack temp = new ItemStack(item, 1, i);
        	this.checkCreated(temp);
            list.add(temp);
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
        this.icons[27] = par1IconRegister.registerIcon("advancedutilities:ironham");
        this.icons[28] = par1IconRegister.registerIcon("advancedutilities:ironhamirod");
        this.icons[29] = par1IconRegister.registerIcon("advancedutilities:ironhambrod");
        this.icons[33] = par1IconRegister.registerIcon("advancedutilities:ironexcavator");
        this.icons[34] = par1IconRegister.registerIcon("advancedutilities:ironexcavatorirod");
        this.icons[35] = par1IconRegister.registerIcon("advancedutilities:ironexcavatorbrod");
        
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
        this.icons[24] = par1IconRegister.registerIcon("advancedutilities:bronzeham");
        this.icons[25] = par1IconRegister.registerIcon("advancedutilities:bronzehamirod");
        this.icons[26] = par1IconRegister.registerIcon("advancedutilities:bronzehambrod");
        this.icons[30] = par1IconRegister.registerIcon("advancedutilities:bronzeexcavator");
        this.icons[31] = par1IconRegister.registerIcon("advancedutilities:bronzeexcavatorirod");
        this.icons[32] = par1IconRegister.registerIcon("advancedutilities:bronzeexcavatorbrod");
        
        this.icons[36] = par1IconRegister.registerIcon("advancedutilities:steelpicksrod");
        this.icons[37] = par1IconRegister.registerIcon("advancedutilities:steelswordsrod");
        this.icons[38] = par1IconRegister.registerIcon("advancedutilities:steelshovelsrod");
        this.icons[39] = par1IconRegister.registerIcon("advancedutilities:steelaxesrod");
        this.icons[40] = par1IconRegister.registerIcon("advancedutilities:steelhamsrod");
        this.icons[41] = par1IconRegister.registerIcon("advancedutilities:steelexcavatorsrod");
        
        
    }
	public void func_150999_a(World world, Block block, int x, int y, int z, EntityPlayer player)
    {
		NBTTagCompound tag = player.getCurrentEquippedItem().getTagCompound();
		tag.setInteger("CurrentDamage", tag.getInteger("CurrentDamage")-1);
		tag.setInteger("CurrentXP", tag.getInteger("CurrentXP")+1);
      	if(tag.getInteger("CurrentXP") >= this.getMaxXPForType(tag.getInteger("Type")))
      	{
      		tag.setInteger("ItemLevel", tag.getInteger("ItemLevel")+1);
      		tag.setInteger("CurrentXP", 0);
      		player.worldObj.playSoundAtEntity(player, "minecraft:random.levelup", 1F, 1F);
      	}
    }
	
	
	
	protected void breakExtraBlock(World world, int x, int y, int z, int sidehit, EntityPlayer player, int refX, int refY, int refZ) 
	{
        // prevent calling that stuff for air blocks, could lead to unexpected behaviour since it fires events
        if (world.isAirBlock(x, y, z))
            return;

        // check if the block can be broken, since extra block breaks shouldn't instantly break stuff like obsidian
        // or precious ores you can't harvest while mining stone
        Block block = world.getBlock(x, y, z);
        int meta = world.getBlockMetadata(x, y, z);

        // only effective materials
        if (!this.canHarvestBlock(block, player.getCurrentEquippedItem()))
            return;

        Block refBlock = world.getBlock(refX, refY, refZ);
        float refStrength = ForgeHooks.blockStrength(refBlock, player, world, refX, refY, refZ);
        float strength = ForgeHooks.blockStrength(block, player, world, x,y,z);

        // only harvestable blocks that aren't impossibly slow to harvest
        if (!this.canHarvestBlock(block, player.getCurrentEquippedItem()) || refStrength/strength > 10f)
            return;

        if (player.capabilities.isCreativeMode) {
            block.onBlockHarvested(world, x, y, z, meta, player);
            if (block.removedByPlayer(world, player, x, y, z, false))
                block.onBlockDestroyedByPlayer(world, x, y, z, meta);

            // send update to client
            if (!world.isRemote) {
                ((EntityPlayerMP)player).playerNetServerHandler.sendPacket(new S23PacketBlockChange(x, y, z, world));
            }
            return;
        }

        // callback to the tool the player uses. Called on both sides. This damages the tool n stuff.
        player.getCurrentEquippedItem().func_150999_a(world, block, x, y, z, player);

        // server sided handling
        if (!world.isRemote) {
            // serverside we reproduce ItemInWorldManager.tryHarvestBlock

            // ItemInWorldManager.removeBlock
            block.onBlockHarvested(world, x,y,z, meta, player);

            if(block.removedByPlayer(world, player, x,y,z, true)) // boolean is if block can be harvested, checked above
            {
                block.onBlockDestroyedByPlayer( world, x,y,z, meta);
                block.harvestBlock(world, player, x, y, z, meta);
            }

            // always send block update to client
            EntityPlayerMP mpPlayer = (EntityPlayerMP) player;
            mpPlayer.playerNetServerHandler.sendPacket(new S23PacketBlockChange(x, y, z, world));
        }
        // client sided handling
        else {
            PlayerControllerMP pcmp = Minecraft.getMinecraft().playerController;
            // clientside we do a "this clock has been clicked on long enough to be broken" call. This should not send any new packets
            // the code above, executed on the server, sends a block-updates that give us the correct state of the block we destroy.

            // following code can be found in PlayerControllerMP.onPlayerDestroyBlock
            world.playAuxSFX(2001, x, y, z, Block.getIdFromBlock(block) + (meta << 12));
            if(block.removedByPlayer(world, player, x,y,z, true))
            {
                block.onBlockDestroyedByPlayer(world, x,y,z, meta);
            }
            // callback to the tool
            ItemStack itemstack = player.getCurrentEquippedItem();
            if (itemstack != null)
            {
                itemstack.func_150999_a(world, block, x, y, z, player);

                if (itemstack.stackSize == 0)
                {
                    player.destroyCurrentEquippedItem();
                }
            }

            // send an update to the server, so we get an update back
                Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C07PacketPlayerDigging(2, x,y,z, Minecraft.getMinecraft().objectMouseOver.sideHit));
        }
	}
	 private boolean detectTree(World world, int x, int y, int z, Block wood)
	    {
	        int height = y;
	        boolean foundTop = false;
	        do
	        {
	            height++;
	            Block block = world.getBlock(x, height, z);
	            if (block != wood)
	            {
	                height--;
	                foundTop = true;
	            }
	        } while (!foundTop);

	        int numLeaves = 0;
	        if (height - y < 50)
	        {
	            for (int xPos = x - 1; xPos <= x + 1; xPos++)
	            {
	                for (int yPos = height - 1; yPos <= height + 1; yPos++)
	                {
	                    for (int zPos = z - 1; zPos <= z + 1; zPos++)
	                    {
	                        Block leaves = world.getBlock(xPos, yPos, zPos);
	                        if (leaves != null && leaves.isLeaves(world, xPos, yPos, zPos))
	                            numLeaves++;
	                    }
	                }
	            }
	        }

	        return numLeaves > 3;
	    }

	    private int breakTree (World world, int x, int y, int z, ItemStack stack, NBTTagCompound tags, Block bID, int meta, EntityPlayer player)
	    {
	    	int count = 0;
	        for (int xPos = x - 1; xPos <= x + 1; xPos++)
	        {
	            for (int yPos = y; yPos <= y + 1; yPos++)
	            {
	                for (int zPos = z - 1; zPos <= z + 1; zPos++)
	                {
	                    
	                        Block localBlock = world.getBlock(xPos, yPos, zPos);
	                        if (bID == localBlock)
	                        {
	                            int localMeta = world.getBlockMetadata(xPos, yPos, zPos);
	                            int hlvl = localBlock.getHarvestLevel(localMeta);
	                            float localHardness = localBlock == null ? Float.MAX_VALUE : localBlock.getBlockHardness(world, xPos, yPos, zPos);

	                            if (hlvl <= 2 && !(localHardness < 0))
	                            {
	                                boolean cancelHarvest = false;
	                                if (stack.getTagCompound().getInteger("CurrentDamage")<=0)
	                                       return 0;


	                                // send blockbreak event
	                                BlockEvent.BreakEvent event = new BlockEvent.BreakEvent(x, y, z, world, localBlock, localMeta, player);
	                                event.setCanceled(cancelHarvest);
	                                MinecraftForge.EVENT_BUS.post(event);
	                                cancelHarvest = event.isCanceled();

	                                if (cancelHarvest)
	                                {
	                                    breakTree(world, xPos, yPos, zPos, stack, tags, bID, meta, player);
	                                }
	                                else
	                                {
	                                    if (localBlock == bID && localMeta % 4 == meta % 4)
	                                    {
	                                        if (!player.capabilities.isCreativeMode)
	                                        {
	                                            localBlock.harvestBlock(world, player, x,y,z, localMeta);
	                                            if(stack.getTagCompound()!= null)
	                                            //stack.getTagCompound().setInteger("CurrentDamage", stack.getTagCompound().getInteger("CurrentDamage")-1);
	                                            stack.getTagCompound().setInteger("GoingDamage",  stack.getTagCompound().getInteger("goingDamage")+1);
	                                            
	                                        }

	                                        world.setBlockToAir(xPos, yPos, zPos);
	                                        if (!world.isRemote)
	                                            breakTree(world, xPos, yPos, zPos, stack, tags, bID, meta, player);
	                                    }
	                                }
	                            }
	                        }
	                    
	                }
	            }
	        }
	        onBlockDestroyed(stack, world, bID, x, y, z, player);
	        return count;
	    }
	    
	   /* @Override
	    public void onUpdate(ItemStack stack, World world, Entity ent, int huh, boolean wut) 
	    {
	    	if(stack.getTagCompound() == null)
	    	{
	    		stack.setTagCompound(new NBTTagCompound());
	    	}
	    	if(!stack.getTagCompound().getBoolean("hasCreated"))
	    	{
	    		this.checkCreated(stack);
	    	}
	    }*/
	    
	    
}
