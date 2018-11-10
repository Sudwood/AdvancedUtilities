package com.sudwood.advancedutilities.client.renders;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;

import com.sudwood.advancedutilities.AdvancedUtilities;
import com.sudwood.advancedutilities.tileentity.TileEntityPortaChest;

public class RenderPortaChest extends TileEntitySpecialRenderer
{
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
	
	public RenderPortaChest()
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
	
	private void adjustRotatePivotViaMeta(World world, int x, int y, int z) 
	{
        int meta = world.getBlockMetadata(x, y, z);
        
        if(meta == 5)
        {
        	GL11.glRotatef(meta * (-90), 1.0F, 800.0F, 1.0F);
        }
        if(meta == 4)
        {
        	GL11.glRotatef(meta * (22), 1.0F, 400.0F, 1.0F);
        }
        if(meta == 2)
        {
        	
        	GL11.glRotatef(meta * (180),0.0F,10.0F, 0.0F);
        }
        if(meta == 3)
        {
        	
        	GL11.glRotatef(meta * (180),0.0F,10.0F, 0.0F);
        }
	}
	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float flot) 
	{
		TileEntityPortaChest te = (TileEntityPortaChest) tile;
		switch(te.getType())
		{
		case TileEntityPortaChest.WOOD:
			bindTexture(wood);
			break;
		case TileEntityPortaChest.BRONZE:
			bindTexture(bronze);
			break;
		case TileEntityPortaChest.GOLD:
			bindTexture(gold);
			break;
		case TileEntityPortaChest.DIAMOND:
			bindTexture(diamond);
			break;
		case TileEntityPortaChest.PLATINUM:
			bindTexture(platinum);
			break;
		}
		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5, y + 0.5, z + 0.5);
		GL11.glScalef(0.0625F, 0.0625F, 0.0625F);
		GL11.glPushMatrix();
		GL11.glRotatef(0, 0F, 1F, 0F);
		adjustRotatePivotViaMeta(te.getWorldObj(), te.xCoord, te.yCoord, te.zCoord);
		if(!te.isOpen)
		{
			
			model.renderPart("topmain");
			model.renderPart("bottommain");
			bindTexture(verticle);
			model.renderPart("topvert");
			model.renderPart("bottomvert");
			bindTexture(horizontal);
			model.renderPart("tophor");
			model.renderPart("bottomhor");
			bindTexture(latchtop);
			model.renderPart("latchtop");
			bindTexture(latchbottom);
			model.renderPart("latchbottom");
		}
		if(te.isOpen)
		{
			model.renderPart("bottommain");
			bindTexture(verticle);
			model.renderPart("bottomvert");
			bindTexture(horizontal);
			model.renderPart("bottomhor");
			bindTexture(latchbottom);
			model.renderPart("latchbottom");
			GL11.glRotatef(45F, (float)(0.0625*25), 0, 0);
			GL11.glTranslated(0 , 0 + 5.5, 0 - 1.8);
			switch(te.getType())
			{
			case TileEntityPortaChest.WOOD:
				bindTexture(wood);
				break;
			case TileEntityPortaChest.BRONZE:
				bindTexture(bronze);
				break;
			case TileEntityPortaChest.GOLD:
				bindTexture(gold);
				break;
			case TileEntityPortaChest.DIAMOND:
				bindTexture(diamond);
				break;
			case TileEntityPortaChest.PLATINUM:
				bindTexture(platinum);
				break;
			}
			model.renderPart("topmain");
			bindTexture(verticle);
			model.renderPart("topvert");
			bindTexture(horizontal);
			model.renderPart("tophor");
			bindTexture(latchtop);
			model.renderPart("latchtop");
		}
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}
	
		
		 

}
