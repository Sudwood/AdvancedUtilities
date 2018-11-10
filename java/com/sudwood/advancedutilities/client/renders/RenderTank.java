package com.sudwood.advancedutilities.client.renders;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import com.sudwood.advancedutilities.client.models.ModelTank;
import com.sudwood.advancedutilities.client.models.ModelTankFiller;
import com.sudwood.advancedutilities.tileentity.TileEntityTank;
import com.sun.prism.impl.VertexBuffer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidRegistry;

public class RenderTank extends TileEntitySpecialRenderer
{
	ModelTank model = new ModelTank();
	private static final double H = 0.01;
	ModelTankFiller fluid = new ModelTankFiller();
	@Override
	public void renderTileEntityAt(TileEntity var1, double x, double y, double z, float var8) 
	{
		TileEntityTank tile = (TileEntityTank) var1;
		Tessellator tes = Tessellator.instance;
		  if(tile.tank.getFluidAmount() > 0)
		  {
			  bindTexture(Minecraft.getMinecraft().getTextureMapBlocks().locationBlocksTexture);
			  tes.startDrawingQuads();
		  final IIcon icon = tile.tank.getFluid().getFluid().getStillIcon();
		  int totalcolor = tile.tank.getFluid().getFluid().getColor();
		  Color colo = new Color(totalcolor);
			tes.setTextureUV(icon.getMinU(), icon.getMinV());
			tes.setColorRGBA(colo.getRed(), colo.getGreen(), colo.getBlue(), colo.getAlpha());
			double scaledY = (double)tile.tank.getFluidAmount() / (double)tile.tank.getCapacity();
		  renderCube(tes, x - H +0.1875, y - H + 0.0625, z - H + 0.1875, x + H + 0.825, y + H + (scaledY*0.825), z + H + 0.825);
		  tes.draw();
		  }
		  GL11.glPushMatrix();
		  GL11.glTranslatef((float)x + 0.5F, (float)y - 0.5F, (float)z + 0.5F);
		  GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		  GL11.glTranslatef(0.0F, -2.0F, 0.0F);        
		  bindTexture(new ResourceLocation("advancedutilities","textures/blocks/tank.png"));   
		  model.render(0.0625F);  
		  GL11.glEnable(GL11.GL_BLEND);	
		  GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	      GL11.glColor4f(1, 1, 1, 1);
		  GL11.glDisable(GL11.GL_BLEND);
	  GL11.glPopMatrix();
		  
	}

	public static void renderCube(Tessellator tes, double x1, double y1, double z1, double x2, double y2, double z2) 
	{
		tes.addVertex(x1, y1, z1);
		tes.addVertex(x1, y2, z1);
		tes.addVertex(x2, y2, z1);
		tes.addVertex(x2, y1, z1);

		tes.addVertex(x1, y1, z2);
		tes.addVertex(x2, y1, z2);
		tes.addVertex(x2, y2, z2);
		tes.addVertex(x1, y2, z2);

		tes.addVertex(x1, y1, z1);
		tes.addVertex(x1, y1, z2);
		tes.addVertex(x1, y2, z2);
		tes.addVertex(x1, y2, z1);

		tes.addVertex(x2, y1, z1);
		tes.addVertex(x2, y2, z1);
		tes.addVertex(x2, y2, z2);
		tes.addVertex(x2, y1, z2);

		tes.addVertex(x1, y1, z1);
		tes.addVertex(x2, y1, z1);
		tes.addVertex(x2, y1, z2);
		tes.addVertex(x1, y1, z2);

		tes.addVertex(x1, y2, z1);
		tes.addVertex(x1, y2, z2);
		tes.addVertex(x2, y2, z2);
		tes.addVertex(x2, y2, z1);
	}

}
