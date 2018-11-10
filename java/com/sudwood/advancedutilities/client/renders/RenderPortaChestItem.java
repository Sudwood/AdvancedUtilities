package com.sudwood.advancedutilities.client.renders;

import org.lwjgl.opengl.GL11;

import com.sudwood.advancedutilities.AdvancedUtilities;
import com.sudwood.advancedutilities.tileentity.TileEntityPortaChest;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class RenderPortaChestItem implements IItemRenderer
{

	TileEntity dummyTile;
	
	ResourceLocation objModelLoc;
	IModelCustom model;
	ResourceLocation wood;
	ResourceLocation bronze;
	ResourceLocation gold;
	ResourceLocation diamond;
	ResourceLocation platinum;
	ResourceLocation verticle;
	ResourceLocation horizontal;
	ResourceLocation latchtop;
	ResourceLocation latchbottom;
	public RenderPortaChestItem()
	{
		objModelLoc = new ResourceLocation(AdvancedUtilities.MODID, "models/chest/chest.obj");
		model = AdvancedModelLoader.loadModel(objModelLoc);
		wood  = new ResourceLocation(AdvancedUtilities.MODID, "models/chest/chestbasewood.png");
		bronze  = new ResourceLocation(AdvancedUtilities.MODID, "models/chest/chestbasebronze.png");
		gold = new ResourceLocation(AdvancedUtilities.MODID, "models/chest/chestbasegold.png");
		diamond = new ResourceLocation(AdvancedUtilities.MODID, "models/chest/chestbasediamond.png");
		platinum = new ResourceLocation(AdvancedUtilities.MODID, "models/chest/chestbaseplatinum.png");
		verticle = new ResourceLocation(AdvancedUtilities.MODID, "models/chest/chestverticle.png");
		horizontal = new ResourceLocation(AdvancedUtilities.MODID, "models/chest/chesthorizontal.png");
		latchtop = new ResourceLocation(AdvancedUtilities.MODID, "models/chest/chestlatchtop.png");
		latchbottom = new ResourceLocation(AdvancedUtilities.MODID, "models/chest/chestlatchbottom.png");
	}
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) 
	{
		// TODO Auto-generated method stub
		switch(type)
		{
		case EQUIPPED: return true;
		
		case EQUIPPED_FIRST_PERSON: return true;
		
		default: return true;
		}
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) 
	{
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) 
	{
		
		switch(type)
		{
		case INVENTORY:
		{
			GL11.glPushMatrix();
			GL11.glTranslatef((float)0.5F, (float) 0.5F, (float)0.5F);
			  GL11.glRotatef(180F, 0F, 150F, 1.0F);
			  GL11.glTranslatef(1.5F, -1.45F, 0.5F);      
				  GL11.glTranslated(-0.5F, 0.6F, 0.5F);
				  GL11.glScalef(0.0625F, 0.0625F, 0.0625F);

				  switch(item.getItemDamage())
					{
					case TileEntityPortaChest.WOOD:
						Minecraft.getMinecraft().getTextureManager().bindTexture(wood);
						break;
					case TileEntityPortaChest.BRONZE:
						Minecraft.getMinecraft().getTextureManager().bindTexture(bronze);
						break;
					case TileEntityPortaChest.GOLD:
						Minecraft.getMinecraft().getTextureManager().bindTexture(gold);
						break;
					case TileEntityPortaChest.DIAMOND:
						Minecraft.getMinecraft().getTextureManager().bindTexture(diamond);
						break;
					case TileEntityPortaChest.PLATINUM:
						Minecraft.getMinecraft().getTextureManager().bindTexture(platinum);
						break;
					}
				  model.renderPart("topmain");
					model.renderPart("bottommain");
					Minecraft.getMinecraft().getTextureManager().bindTexture(verticle);
					model.renderPart("topvert");
					model.renderPart("bottomvert");
					Minecraft.getMinecraft().getTextureManager().bindTexture(horizontal);
					model.renderPart("tophor");
					model.renderPart("bottomhor");
					Minecraft.getMinecraft().getTextureManager().bindTexture(latchtop);
					model.renderPart("latchtop");
					Minecraft.getMinecraft().getTextureManager().bindTexture(latchbottom);
					model.renderPart("latchbottom");
			  GL11.glPopMatrix();
			  break;
		}
		case EQUIPPED: 
		{
			GL11.glPushMatrix();
			GL11.glTranslatef((float)0.5F, (float) 0.5F, (float)0.5F);
			  GL11.glRotatef(180F, 0.0F, 50F, 1.0F);
			  GL11.glTranslatef(0.6F, -1.2F, -0.0F);      
			  
				  GL11.glTranslated(-0.5F, 0.7F, 0.5F);
				  GL11.glScalef(0.0625F, 0.0625F, 0.0625F);
				  switch(item.getItemDamage())
					{
					case TileEntityPortaChest.WOOD:
						Minecraft.getMinecraft().getTextureManager().bindTexture(wood);
						break;
					case TileEntityPortaChest.BRONZE:
						Minecraft.getMinecraft().getTextureManager().bindTexture(bronze);
						break;
					case TileEntityPortaChest.GOLD:
						Minecraft.getMinecraft().getTextureManager().bindTexture(gold);
						break;
					case TileEntityPortaChest.DIAMOND:
						Minecraft.getMinecraft().getTextureManager().bindTexture(diamond);
						break;
					case TileEntityPortaChest.PLATINUM:
						Minecraft.getMinecraft().getTextureManager().bindTexture(platinum);
						break;
					}
				  model.renderPart("topmain");
					model.renderPart("bottommain");
					Minecraft.getMinecraft().getTextureManager().bindTexture(verticle);
					model.renderPart("topvert");
					model.renderPart("bottomvert");
					Minecraft.getMinecraft().getTextureManager().bindTexture(horizontal);
					model.renderPart("tophor");
					model.renderPart("bottomhor");
					Minecraft.getMinecraft().getTextureManager().bindTexture(latchtop);
					model.renderPart("latchtop");
					Minecraft.getMinecraft().getTextureManager().bindTexture(latchbottom);
					model.renderPart("latchbottom");
			  GL11.glPopMatrix();
			  break;
		}
		
		case EQUIPPED_FIRST_PERSON:
		{
			GL11.glPushMatrix();
			  GL11.glScalef(1.2F, 1.2F, 1.2F);
			  GL11.glRotatef(90, 10F, 150F, 0F);
			  GL11.glTranslatef(0F, -1F, 4F);   
			  
			  
				  GL11.glTranslated(0F, 0.7F, 0.5F);
				  GL11.glScalef(0.0625F, 0.0625F, 0.0625F);
				  switch(item.getItemDamage())
					{
					case TileEntityPortaChest.WOOD:
						Minecraft.getMinecraft().getTextureManager().bindTexture(wood);
						break;
					case TileEntityPortaChest.BRONZE:
						Minecraft.getMinecraft().getTextureManager().bindTexture(bronze);
						break;
					case TileEntityPortaChest.GOLD:
						Minecraft.getMinecraft().getTextureManager().bindTexture(gold);
						break;
					case TileEntityPortaChest.DIAMOND:
						Minecraft.getMinecraft().getTextureManager().bindTexture(diamond);
						break;
					case TileEntityPortaChest.PLATINUM:
						Minecraft.getMinecraft().getTextureManager().bindTexture(platinum);
						break;
					}
				  model.renderPart("topmain");
					model.renderPart("bottommain");
					Minecraft.getMinecraft().getTextureManager().bindTexture(verticle);
					model.renderPart("topvert");
					model.renderPart("bottomvert");
					Minecraft.getMinecraft().getTextureManager().bindTexture(horizontal);
					model.renderPart("tophor");
					model.renderPart("bottomhor");
					Minecraft.getMinecraft().getTextureManager().bindTexture(latchtop);
					model.renderPart("latchtop");
					Minecraft.getMinecraft().getTextureManager().bindTexture(latchbottom);
					model.renderPart("latchbottom");
			  GL11.glPopMatrix();
			  break;
		}
		
		default: 
		{
			  GL11.glPushMatrix();
			  GL11.glScalef(1F, 2F, 1F);
			  GL11.glRotatef(0, 0.0F, 0.0F, 1F);
			  GL11.glTranslatef(0.0F, 0F, 0.0F);    
			  
				  GL11.glTranslated(-0.5F, 0.7F, 0.5F);
				  GL11.glScalef(0.0625F, 0.0625F, 0.0625F);
				  switch(item.getItemDamage())
					{
					case TileEntityPortaChest.WOOD:
						Minecraft.getMinecraft().getTextureManager().bindTexture(wood);
						break;
					case TileEntityPortaChest.BRONZE:
						Minecraft.getMinecraft().getTextureManager().bindTexture(bronze);
						break;
					case TileEntityPortaChest.GOLD:
						Minecraft.getMinecraft().getTextureManager().bindTexture(gold);
						break;
					case TileEntityPortaChest.DIAMOND:
						Minecraft.getMinecraft().getTextureManager().bindTexture(diamond);
						break;
					case TileEntityPortaChest.PLATINUM:
						Minecraft.getMinecraft().getTextureManager().bindTexture(platinum);
						break;
					}
				  model.renderPart("topmain");
					model.renderPart("bottommain");
					Minecraft.getMinecraft().getTextureManager().bindTexture(verticle);
					model.renderPart("topvert");
					model.renderPart("bottomvert");
					Minecraft.getMinecraft().getTextureManager().bindTexture(horizontal);
					model.renderPart("tophor");
					model.renderPart("bottomhor");
					Minecraft.getMinecraft().getTextureManager().bindTexture(latchtop);
					model.renderPart("latchtop");
					Minecraft.getMinecraft().getTextureManager().bindTexture(latchbottom);
					model.renderPart("latchbottom");
			  GL11.glPopMatrix();
			  break;
		}
		}
		  
		
	}

}
