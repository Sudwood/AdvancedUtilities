package com.sudwood.advancedutilities.client.renders;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;

import com.sudwood.advancedutilities.client.models.AdvancedModelBase;

public class RenderTileEntityBase implements IItemRenderer
{

	TileEntity dummyTile;
	
	AdvancedModelBase model;
	ResourceLocation texture;
	IModelCustom cusmod;
	int typeRender;
	public RenderTileEntityBase(AdvancedModelBase mod, ResourceLocation res)
	{
		model = mod;
		texture = res;
		typeRender = 0;
	}
	public RenderTileEntityBase(ResourceLocation mod, ResourceLocation tex)
	{
		texture = tex;
		cusmod = AdvancedModelLoader.loadModel(mod);
		typeRender = 1;
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
			  GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
			  GL11.glTranslatef(-0.5F, -1.27F, 0.5F);      
			  
			  Minecraft.getMinecraft().renderEngine.bindTexture(texture);
			  if(typeRender == 0)
				  model.render(0.0625F);  
			  if(typeRender == 1)
			  {
				  GL11.glTranslated(-0.5F, 0.7F, 0.5F);
				  GL11.glScalef(0.0825F, 0.0825F, 0.0825F);
				  cusmod.renderAll();
			  }
			  GL11.glPopMatrix();
			  break;
		}
		case EQUIPPED: 
		{
			GL11.glPushMatrix();
			GL11.glTranslatef((float)0.5F, (float) 0.5F, (float)0.5F);
			  GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
			  GL11.glTranslatef(0.6F, -1.2F, -0.0F);      
			  
			  Minecraft.getMinecraft().renderEngine.bindTexture(texture);

			  if(typeRender == 0)
				  model.render(0.0625F);  
			  if(typeRender == 1)
			  {
				  GL11.glTranslated(-0.5F, 0.7F, 0.5F);
				  GL11.glScalef(0.0825F, 0.0825F, 0.0825F);
				  cusmod.renderAll();
			  }
			  GL11.glPopMatrix();
			  break;
		}
		
		case EQUIPPED_FIRST_PERSON:
		{
			GL11.glPushMatrix();
			  GL11.glScalef(1.2F, 1.2F, 1.2F);
			  GL11.glRotatef(180, 2F, -0F, 0.1F);
			  GL11.glTranslatef(1.5F, -1.2F, -0.3F);   
			  
			  
			  Minecraft.getMinecraft().renderEngine.bindTexture(texture);

			  if(typeRender == 0)
				  model.render(0.0625F);  
			  if(typeRender == 1)
			  {
				  GL11.glTranslated(0F, 0.7F, 0.5F);
				  GL11.glScalef(0.0825F, 0.0825F, 0.0825F);
				  cusmod.renderAll();
			  }
			  GL11.glPopMatrix();
			  break;
		}
		
		default: 
		{
			  GL11.glPushMatrix();
			  GL11.glScalef(1F, 2F, 1F);
			  GL11.glRotatef(0, 0.0F, 0.0F, 1F);
			  GL11.glTranslatef(0.0F, 0F, 0.0F);    
			  
			  Minecraft.getMinecraft().renderEngine.bindTexture(texture);

			  if(typeRender == 0)
				  model.render(0.0625F);  
			  if(typeRender == 1)
			  {
				  GL11.glTranslated(-0.5F, 0.7F, 0.5F);
				  GL11.glScalef(0.0825F, 0.0825F, 0.0825F);
				  cusmod.renderAll();
			  }
			  GL11.glPopMatrix();
			  break;
		}
		}
		  
		
	}

}
