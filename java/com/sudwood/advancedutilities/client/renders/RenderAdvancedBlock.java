package com.sudwood.advancedutilities.client.renders;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;

public class RenderAdvancedBlock extends Render
{
	public static RenderAdvancedBlock instance = new RenderAdvancedBlock();
	protected RenderBlocks blocks;
	@Override
	public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9) 
	{
		
	}
	
	public RenderAdvancedBlock()
	{
		this.blocks = field_147909_c;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity var1) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Why are you calling this?");
	}
	
	public void RenderBlock(RenderInformation info, IBlockAccess world, double x, double y, double z, int lX, int lY, int lZ, boolean renderLight)
	{
		float lightB = 0.5F;
		float lightT = 1.0F;
		float lightEW = 0.8F;
		float lightNS = 0.6F;
		
		Tessellator tess = Tessellator.instance;
		tess.startDrawingQuads();
		if(world == null)
		{
			renderLight = false;
		}
		float light = 0;
		if (renderLight) 
		{
			if (info.lightness < 0) 
			{
				light = info.base.getMixedBrightnessForBlock(world, (int) lX, (int) lY, (int) lZ);
				light = light + ((1.0f - light) * 0.4f);
			} 
			else
				light = info.lightness;
			int brightness = 0;
			if (info.brightness < 0)
				brightness = info.base.getMixedBrightnessForBlock(world, lX, lY, lZ);
			else
				brightness = info.brightness;
			tess.setBrightness(brightness);
			tess.setColorOpaque_F(lightB * light, lightB * light, lightB * light);
		} 
		else 
		{
			if (info.brightness >= 0)
				tess.setBrightness(info.brightness);
		}
		blocks.setRenderBounds(info.minX, info.minY, info.minZ, info.maxX, info.maxY, info.maxZ);
		
		if (info.rSide[0])
			blocks.renderFaceYNeg(info.base, x, y, z, info.getIconFromSide(0));

		if (renderLight)
			tess.setColorOpaque_F(lightT * light, lightT * light, lightT * light);

		if (info.rSide[1])
			blocks.renderFaceYPos(info.base, x, y, z, info.getIconFromSide(1));

		if (renderLight)
			tess.setColorOpaque_F(lightEW * light, lightEW * light, lightEW * light);

		if (info.rSide[2])
			blocks.renderFaceZNeg(info.base, x, y, z, info.getIconFromSide(2));

		if (renderLight)
			tess.setColorOpaque_F(lightEW * light, lightEW * light, lightEW * light);

		if (info.rSide[3])
			blocks.renderFaceZPos(info.base, x, y, z, info.getIconFromSide(3));

		if (renderLight)
			tess.setColorOpaque_F(lightNS * light, lightNS * light, lightNS * light);

		if (info.rSide[4])
			blocks.renderFaceXNeg(info.base, x, y, z, info.getIconFromSide(4));

		if (renderLight)
			tess.setColorOpaque_F(lightNS * light, lightNS * light, lightNS * light);

		if (info.rSide[5])
			blocks.renderFaceXPos(info.base, x, y, z, info.getIconFromSide(5));
		tess.draw();

	}
	
	public static class RenderInformation
	{
		public double minX, maxX, minY, maxY, minZ, maxZ;
		public Block base = Blocks.dirt;
		public IIcon icon = null;
		public IIcon[] icons = null;
		public boolean[] rSide = new boolean[6];
		public float lightness = -1F;
		public int brightness = -1;
		
		public RenderInformation()
		{
			setAllSidesToRender();
		}
		
		public RenderInformation(Block block, IIcon[] bIcons)
		{
			base = block;
			icons = bIcons;
		}
		public void rotateDrawing() {
			double sha = minX;
			minX = minZ;
			minZ = sha;

			sha = maxX;
			maxX = maxZ;
			maxZ = sha;
		}
		
		public void setAllSidesToRender()
		{
			for(int i =0; i < 6; i++)
			{
				rSide[i] = true;
			}
		}
		
		public void setOneSideToRender(int side)
		{
			for(int i =0; i < 6; i++)
			{
				rSide[i] = false;
			}
			rSide[side] = true;
		}
		
		public void setMinMax(double miX, double maX, double miY, double maY, double miZ, double maZ)
		{
			minX = miX;
			minY = miY;
			minZ = miZ;
			maxX = maX;
			maxY = maY;
			maxZ = maZ;
		}

		public void reverseX() {
			double man = minX;
			minX = 1 - maxX;
			maxX = 1 - man;
		}

		public void reverseZ() {
			double wod = minZ;
			minZ = 1 - maxZ;
			maxZ = 1 - wod;
		}
		
		public IIcon getIconFromSide(int side)
		{
			if(icon != null)
				return icon;
			if(icons == null || icons.length <=0)
			{
				return base.getBlockTextureFromSide(side);
			}
			else
			{
				if(side >= icons.length)
					side = 0;
				return icons[side];
			}
		}
		
	}

}
