package com.sudwood.advancedutilities.items;

import com.sudwood.advancedutilities.AdvancedUtilities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

public class ItemIngot extends Item
{
	protected int type;
	public ItemIngot(int type)
	{
		this.type = type;
	}
	
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister)
    {
		switch(type)
		{
		case 0:
			 this.itemIcon = par1IconRegister.registerIcon("advancedutilities:copperingot"+AdvancedUtilities.textureSize);
			break;
		case 1:
			 this.itemIcon = par1IconRegister.registerIcon("advancedutilities:tiningot"+AdvancedUtilities.textureSize);
			break;
		case 2:
			 this.itemIcon = par1IconRegister.registerIcon("advancedutilities:bronzeingot"+AdvancedUtilities.textureSize);
			break;
		case 3:
			 this.itemIcon = par1IconRegister.registerIcon("advancedutilities:zincingot"+AdvancedUtilities.textureSize);
			break;
		case 4:
			 this.itemIcon = par1IconRegister.registerIcon("advancedutilities:brassingot"+AdvancedUtilities.textureSize);
			break;
		case 5:
			 this.itemIcon = par1IconRegister.registerIcon("advancedutilities:silveringot"+AdvancedUtilities.textureSize);
			break;
		case 6:
			 this.itemIcon = par1IconRegister.registerIcon("advancedutilities:leadingot"+AdvancedUtilities.textureSize);
			break;
		case 7:
			 this.itemIcon = par1IconRegister.registerIcon("advancedutilities:aluminumingot"+AdvancedUtilities.textureSize);
			break;
		case 8:
			 this.itemIcon = par1IconRegister.registerIcon("advancedutilities:tungsteningot"+AdvancedUtilities.textureSize);
			break;
		case 9:
			 this.itemIcon = par1IconRegister.registerIcon("advancedutilities:platinumingot"+AdvancedUtilities.textureSize);
			break;
		case 10:
			 this.itemIcon = par1IconRegister.registerIcon("advancedutilities:iridiumingot"+AdvancedUtilities.textureSize);
			break;
		case 11:
			 this.itemIcon = par1IconRegister.registerIcon("advancedutilities:palidiumingot"+AdvancedUtilities.textureSize);
			break;
		case 12:
			 this.itemIcon = par1IconRegister.registerIcon("advancedutilities:steelingot"+AdvancedUtilities.textureSize);
			break;
		}
       
    }
}
