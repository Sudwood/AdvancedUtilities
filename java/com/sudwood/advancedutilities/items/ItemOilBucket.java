package com.sudwood.advancedutilities.items;

import com.sudwood.advancedutilities.AdvancedUtilities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBucket;

public class ItemOilBucket extends ItemBucket{

	public ItemOilBucket(Block block) {
		super(block);
		setCreativeTab(AdvancedUtilities.advancedTab);
		setUnlocalizedName("bucketOil");
		setContainerItem(Items.bucket);
		// TODO Auto-generated constructor stub
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister par1IconRegister){
		itemIcon = par1IconRegister.registerIcon(AdvancedUtilities.MODID + ":" + "bucketoil");
	}

}
