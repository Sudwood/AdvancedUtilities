package com.sudwood.advancedutilities.client.renders;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.sudwood.advancedutilities.client.models.ModelGun;
import com.sudwood.advancedutilities.client.models.ModelJackHammer;
import com.sudwood.advancedutilities.client.models.ModelJetpack;
import com.sudwood.advancedutilities.client.models.ModelSteamCharger;
import com.sudwood.advancedutilities.tileentity.TileEntitySteamCharger;

public class RenderSteamCharger extends TileEntitySpecialRenderer
{
	ModelSteamCharger model = new ModelSteamCharger();
	ModelGun modelGun = new ModelGun();
	ModelJetpack modelJetpack = new ModelJetpack();
	ModelJackHammer modelJackHammer = new ModelJackHammer();
	@Override
	public void renderTileEntityAt(TileEntity var1, double var2, double var4,
			double var6, float var8) 
	{
		  GL11.glPushMatrix();
		  GL11.glTranslatef((float)var2 + 0.5F, (float)var4 - 0.5F, (float)var6 + 0.5F);
		  GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		  GL11.glTranslatef(0.0F, -2.0F, 0.0F);        
		  bindTexture(new ResourceLocation("advancedutilities","textures/blocks/steamcharger.png"));   
		  model.render(0.0625F);  
		  GL11.glPushMatrix();
		  TileEntitySteamCharger tile = (TileEntitySteamCharger) var1;
		  if(tile.renderGun)
		  {
			  GL11.glTranslatef(0F, 0.6F, 0.6F);
			  GL11.glScalef(0.7F, 0.7F, 0.7F);
			  GL11.glRotatef(225F, 10F, 10F, -3F);
			  GL11.glRotatef(45F, 8F, -1.5F, 0.4F);
			  bindTexture(new ResourceLocation("advancedutilities","textures/items/gun.png"));
			  modelGun.render(0.0625F);
		  }
		  if(tile.renderJetpack)
		  {
			  GL11.glTranslatef(0F, 0.6F, 0.25F);
			  GL11.glScalef(0.7F, 0.7F, 0.7F);
			  GL11.glRotatef(225F, 11F, 4F, -1F);
			  GL11.glRotatef(45F, 20F, 22F, 10F);
			  bindTexture(new ResourceLocation("advancedutilities","textures/items/jetpack.png"));
			  modelJetpack.render(0.0625F);
		  }
		  if(tile.renderJackHammer)
		  {
			  GL11.glTranslatef(0F, 0.6F, 0.25F);
			  GL11.glScalef(0.5F, 0.5F, 0.5F);
			  GL11.glRotatef(225F, 11F, 4F, -1F);
			  GL11.glRotatef(45F, 20F, 22F, 10F);
			  bindTexture(new ResourceLocation("advancedutilities","textures/items/jackhammer.png"));
			  modelJackHammer.render(0.0625F);
		  }
		  GL11.glPopMatrix();
		  GL11.glPopMatrix();
		
	}

}
