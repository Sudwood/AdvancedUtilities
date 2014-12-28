package com.sudwood.advancedutilities.items;

import java.util.List;

import javax.swing.Icon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class ItemDevTool extends Item {

	Integer x = 0;
	Integer y = 0;
	Integer z = 0;
	boolean state = false; // false for mark true for recall
	

	boolean isSet = false;

	Integer dimensionTravel = 0;
	int dimensionIn = 0;
	@SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister icon)
    {
		this.itemIcon = icon.registerIcon("advancedutilities:copperingot");
    }
	
	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
	{
		 if(par1ItemStack.getTagCompound()==null)
		  {
			  par1ItemStack.setTagCompound(new NBTTagCompound());
		  }
		  NBTTagCompound tag = par1ItemStack.getTagCompound();
		  dimensionTravel = tag.getInteger("Dimension");
		  x = tag.getInteger("posX");
			y = tag.getInteger("posY");
			z = tag.getInteger("posZ");
			 if (x!=null&&y!=null&&z!=null){
			 par3List.add("X: "+x.toString());
			 par3List.add("Y: "+y.toString());
			 par3List.add("Z: "+z.toString());
			 par3List.add("Dimension: " + dimensionTravel);

			 }
	}
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
		if (!par2World.isRemote)
		 {
		 if(par1ItemStack.getTagCompound() == null) par1ItemStack.setTagCompound(new NBTTagCompound());
		 
		 dimensionIn = par2World.provider.dimensionId;
	     NBTTagCompound tag = par1ItemStack.getTagCompound();
	
	     state = tag.getBoolean("state");
	     isSet = tag.getBoolean("isSet");
	     x = tag.getInteger("posX");
		 y = tag.getInteger("posY");
		 z = tag.getInteger("posZ");
	        
	      if (par3EntityPlayer.isSneaking()&&!par2World.isRemote)
	      {
	    	  if (state == false)
	    	  {
	    		  
	    		  par3EntityPlayer.addChatComponentMessage(new ChatComponentText("Recall Mode Activated"));
	    		  state = true;
	    		  tag.setBoolean("state", state);
	    		  return par1ItemStack;
	    	  }
	    	  if (state==true)
	    	  {
	    		  par3EntityPlayer.addChatComponentMessage(new ChatComponentText(("Mark Mode Activated")));
	    		  state = false;
	    		  tag.setBoolean("state", state);
	    		  return par1ItemStack;
	    	  }
	    	  
	      }
	      if (!par3EntityPlayer.isSneaking())
	      {
	    		  
	    	  if (state == false)
	    	  {
	    		  
	    		  if (isSet==true)
	    		  {
	    			  World otherWorld = MinecraftServer.getServer().worldServerForDimension(tag.getInteger("Dimension"));
	    			  this.x = (int)par3EntityPlayer.posX;
		    		  this.y = (int)par3EntityPlayer.posY;
		    		  this.z = (int)par3EntityPlayer.posZ;
	    		  }
	    		  if (isSet==false)
	    		  {
	    		  
	    			
	    		  this.x = (int)par3EntityPlayer.posX;
	    		  this.y = (int)par3EntityPlayer.posY;
	    		  this.z = (int)par3EntityPlayer.posZ;
	    		  isSet=true;
	    		  tag.setBoolean("isSet", isSet);
	    		  
	    		  }
	    		  
	    		  tag.setInteger("posX", x);
	    		  tag.setInteger("posY", y);
	    		  tag.setInteger("posZ", z);
	    		  tag.setInteger("Dimension", par2World.provider.dimensionId);
	    		  par3EntityPlayer.swingItem();
	    		  return par1ItemStack;
	    		  
	    	  }
	    	  if (state == true)
		    	 {
		    		 if (x==0&&y==0&&z==0)
		    		 {
		    			 return par1ItemStack;
		    		 }
		    		 par3EntityPlayer.setPositionAndUpdate(x, y, z);
		    		 par3EntityPlayer.fallDistance = 0;
		    		 par3EntityPlayer.swingItem();
		   		return par1ItemStack;
		    	 }
	    	 
	      }
	      }
	 
	 
	 
        return par1ItemStack;
    }
    }

