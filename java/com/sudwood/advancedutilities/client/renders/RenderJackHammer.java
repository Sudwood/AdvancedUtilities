package com.sudwood.advancedutilities.client.renders;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import com.sudwood.advancedutilities.client.models.AdvancedModelBase;

public class RenderJackHammer implements IItemRenderer{

	AdvancedModelBase model;
	ResourceLocation texture;
	public RenderJackHammer(AdvancedModelBase mod, ResourceLocation res)
	{
		model = mod;
		texture = res;
		
	}
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		// TODO Auto-generated method stub
		switch(type)
		{
		case EQUIPPED: return true;
		
		case EQUIPPED_FIRST_PERSON: return true;
		
		default: return true;
		}
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {
		// TODO Auto-generated method stub
		
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
			GL11.glScalef(1.0F, 1.0F, 1.0F);
			GL11.glTranslatef((float)0.5F, (float) 0.2F, (float)0.5F);
			  GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
			  GL11.glTranslatef(-0.4F, -1.27F, 0.5F);      
			  
			  Minecraft.getMinecraft().renderEngine.bindTexture(texture);

			  model.render(0.0625F);  
			  GL11.glPopMatrix();
			  break;
		}
		case EQUIPPED: 
		{
			GL11.glPushMatrix();
			GL11.glScalef(2F, 2F, 2F);
			GL11.glTranslatef((float)0.5F, (float) 0.5F, (float)0.5F);
			  GL11.glRotatef(180F, -2.3F, 0.0F, 1.0F);
			  GL11.glTranslatef(-0.5F, -0.1F, 0.38F);      
			  
			  Minecraft.getMinecraft().renderEngine.bindTexture(texture);

			  model.render(0.0625F);  
			  GL11.glPopMatrix();
			  break;
		}
		
		case EQUIPPED_FIRST_PERSON:
		{
			GL11.glPushMatrix();
			  GL11.glScalef(1F, 1F, 1F);
			  GL11.glRotatef(180, 1F, -0F, 0.5F);
			  GL11.glTranslatef(0.5F, -1.7F, -0.3F);   
			  
			  
			  Minecraft.getMinecraft().renderEngine.bindTexture(texture);

			  model.render(0.0625F);  
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

			  model.render(0.0625F);  
			  GL11.glPopMatrix();
			  break;
		}
		}
		  
		
	}

}
