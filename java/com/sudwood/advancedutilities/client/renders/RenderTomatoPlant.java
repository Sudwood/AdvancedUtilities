package com.sudwood.advancedutilities.client.renders;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.sudwood.advancedutilities.client.models.ModelTomatoPlant;

public class RenderTomatoPlant extends TileEntitySpecialRenderer
{
	ModelTomatoPlant model = new ModelTomatoPlant();
	@Override
	public void renderTileEntityAt(TileEntity var1, double var2, double var4,
			double var6, float var8) 
	{
		  GL11.glPushMatrix();
		  GL11.glTranslatef((float)var2 + 0.5F, (float)var4 - 0.5F, (float)var6 + 0.5F);
		  GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		  GL11.glTranslatef(0.0F, -2.0F, 0.0F);       
		  GL11.glScalef(0.5F, 1F, 0.5F);
		  bindTexture(new ResourceLocation("advancedutilities","textures/blocks/tomatoplant.png"));   
		  model.render(0.0625F, /*&var1.getWorldObj().getBlockMetadata(var1.xCoord, var1.yCoord, var1.zCoord)*/0);  
		  model.render(0.0625F, /*&var1.getWorldObj().getBlockMetadata(var1.xCoord, var1.yCoord, var1.zCoord)*/1);  
		  model.render(0.0625F, /*&var1.getWorldObj().getBlockMetadata(var1.xCoord, var1.yCoord, var1.zCoord)*/2);
		  model.render(0.0625F, /*&var1.getWorldObj().getBlockMetadata(var1.xCoord, var1.yCoord, var1.zCoord)*/3);  
		  model.render(0.0625F, /*&var1.getWorldObj().getBlockMetadata(var1.xCoord, var1.yCoord, var1.zCoord)*/4);  
		  GL11.glPopMatrix();
	}

}
