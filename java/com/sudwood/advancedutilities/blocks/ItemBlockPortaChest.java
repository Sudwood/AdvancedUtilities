package com.sudwood.advancedutilities.blocks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lwjgl.input.Keyboard;

import com.sudwood.advancedutilities.AdvancedUtilities;
import com.sudwood.advancedutilities.tileentity.TileEntityPortaChest;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

public class ItemBlockPortaChest extends ItemBlock
{

	public ItemBlockPortaChest(Block block) 
	{
		super(block);
		this.maxStackSize = 1;
	}
	
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float px, float py, float pz)
    {
		if(player.isSneaking())
		{
			super.onItemUse(stack, player, world, x, y, z, side, px, py, pz);
			return true;
		}
		return false;
    }
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		if(!player.isSneaking())
		{
			if(stack.getTagCompound() ==null)
			{
				NBTTagCompound tag = new NBTTagCompound();
				tag.setInteger("chestType", stack.getItemDamage());
				stack.setTagCompound(tag);
			}
			player.playSound("random.chestopen", 15, 2);
			player.openGui(AdvancedUtilities.instance, AdvancedUtilities.portaChestItemGui, world, (int)player.posX, (int)player.posY, (int)player.posZ);
			return player.getCurrentEquippedItem();
		}
		
	    	//par3EntityPlayer.openGui(AdvancedUtilities.instance, AdvancedUtilities.bulletMagazineGui, par2World, (int) par3EntityPlayer.posX, (int) par3EntityPlayer.posY, (int) par3EntityPlayer.posZ);
	     return  player.getCurrentEquippedItem();
	}
	@Override
	public int getMaxItemUseDuration(ItemStack stack) 
	{
		return 68000; // return any value greater than zero
	}
	
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean bool) 
	{
		ItemStack[] inventory = new ItemStack[27];
		if(!Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindSneak.getKeyCode()))
		{
			list.add("<press "+Keyboard.getKeyName(Minecraft.getMinecraft().gameSettings.keyBindSneak.getKeyCode())+" for more information>");
		}
		else
		{
			HashMap<String, Integer> map = new HashMap();
			switch(stack.getItemDamage())
			{
			case TileEntityPortaChest.WOOD:
				
				break;
			case TileEntityPortaChest.BRONZE:
				inventory = new ItemStack[54];
				break;
			case TileEntityPortaChest.GOLD:
				inventory = new ItemStack[81];
				break;
			case TileEntityPortaChest.DIAMOND:
				inventory = new ItemStack[108];
				break;
			case TileEntityPortaChest.PLATINUM:
				inventory = new ItemStack[135];
				break;
			}
			NBTTagCompound tag = stack.getTagCompound();
			if(tag!=null)
			{
				NBTTagList nbttaglist = tag.getTagList("Items", 10);
				for (int i = 0; i < nbttaglist.tagCount(); ++i)
		        {
		            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
		            byte b0 = nbttagcompound1.getByte("Slot");
	
		            if (b0 >= 0 && b0 < inventory.length)
		            {
		                inventory[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
		            }
		        }
				for(int i = 0; i<inventory.length; i++)
				{
					if(inventory[i]!=null)
					{
						if(map.get(inventory[i].getDisplayName())!=null)
						{
							int temp = map.get(inventory[i].getDisplayName());
							temp+=inventory[i].stackSize;
							map.put(inventory[i].getDisplayName(),temp);
						}
						else
						{
							map.put(inventory[i].getDisplayName(), inventory[i].stackSize);
						}
					}
				}
				for(Map.Entry<String, Integer> entry: map.entrySet())
				{
					list.add(entry.getKey()+" : "+entry.getValue());
				}
			}
			
		}
	}
}
