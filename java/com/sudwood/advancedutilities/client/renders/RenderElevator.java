package com.sudwood.advancedutilities.client.renders;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;

import com.sudwood.advancedutilities.AdvancedUtilities;
import com.sudwood.advancedutilities.tileentity.TileEntityPortaChest;

public class RenderElevator extends TileEntitySpecialRenderer
{
	ResourceLocation objModelLoc;
	IModelCustom model;
	ResourceLocation texture;
	
	public RenderElevator()
	{
		objModelLoc = new ResourceLocation(AdvancedUtilities.MODID, "models/elevator/elevator.obj");
		model = AdvancedModelLoader.loadModel(objModelLoc);
		texture  = new ResourceLocation(AdvancedUtilities.MODID, "models/elevator/elevator.png");
	}
	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float flot) 
	{
		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5, y + 0.5, z + 0.5);
		GL11.glScalef(0.0625F, 0.0625F, 0.0625F);
		GL11.glPushMatrix();
		GL11.glRotatef(0, 0F, 1F, 0F);
			bindTexture(texture);
			model.renderPart("base");
			model.renderPart("middle");
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}

}
