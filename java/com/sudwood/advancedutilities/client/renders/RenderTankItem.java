package com.sudwood.advancedutilities.client.renders;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import com.sudwood.advancedutilities.client.models.AdvancedModelBase;
import com.sudwood.advancedutilities.client.models.ModelTankFiller;
import com.sudwood.advancedutilities.tileentity.TileEntityTank;

public class RenderTankItem implements IItemRenderer{

	AdvancedModelBase model;
	ModelTankFiller fluid = new ModelTankFiller();
	ResourceLocation texture;
	private static final double H = 0.01;
	public RenderTankItem(AdvancedModelBase mod, ResourceLocation res)
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
			NBTTagCompound tag = item.getTagCompound();
			  if(tag!=null)
			  {
				  FluidStack temp = FluidStack.loadFluidStackFromNBT(tag);
				  if(temp!=null)
				  {
					  if(temp.amount > 0)
					  {
						  Tessellator tes = Tessellator.instance;
						  Minecraft.getMinecraft().renderEngine.bindTexture(Minecraft.getMinecraft().getTextureMapBlocks().locationBlocksTexture);
							  tes.startDrawingQuads();
						  final IIcon icon = temp.getFluid().getStillIcon();
						  int totalcolor = temp.getFluid().getColor();
						  Color colo = new Color(totalcolor);
							tes.setTextureUV(icon.getMinU(), icon.getMinV());
							tes.setColorRGBA(colo.getRed(), colo.getGreen(), colo.getBlue(), colo.getAlpha());
							double scaledY = (double)temp.amount / (double)64000;
							
						  renderCube(tes, 0 - H +0.125, 0 - H + 0.0625, 0 - H + 0.125, 0 + H + 0.9375, 0 + H + (0.9375*scaledY), 0 + H + 0.9375);
						  tes.draw();
					  }
				  }
			  }
			GL11.glPushMatrix();
			GL11.glTranslatef((float)0.5F, (float) 0.5F, (float)0.5F);
			  GL11.glScalef(1.35F, 1.2F, 1.35F);
			  GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
			  GL11.glTranslatef(-0.5F, -1.45F, 0.5F);      
			  
			  Minecraft.getMinecraft().renderEngine.bindTexture(texture);

			  model.render(0.0625F);  
			  GL11.glEnable(GL11.GL_BLEND);	
			  GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		      GL11.glColor4f(1, 1, 1, 1);
			  
			  GL11.glDisable(GL11.GL_BLEND);
			  GL11.glPopMatrix();
			  break;
		}
		case EQUIPPED: 
		{
			NBTTagCompound tag = item.getTagCompound();
			  if(tag!=null)
			  {
				  FluidStack temp = FluidStack.loadFluidStackFromNBT(tag);
				  if(temp!=null)
				  {
					  if(temp.amount > 0)
					  {
						  Tessellator tes = Tessellator.instance;
						  Minecraft.getMinecraft().renderEngine.bindTexture(Minecraft.getMinecraft().getTextureMapBlocks().locationBlocksTexture);
							  tes.startDrawingQuads();
						  final IIcon icon = temp.getFluid().getStillIcon();
						  int totalcolor = temp.getFluid().getColor();
						  Color colo = new Color(totalcolor);
							tes.setTextureUV(icon.getMinU(), icon.getMinV());
							tes.setColorRGBA(colo.getRed(), colo.getGreen(), colo.getBlue(), colo.getAlpha());
							double scaledY = (double)temp.amount / (double)64000;
							
						  renderCube(tes, 0 - H +0, 0 - H + 0.0625, 0 - H + 0.1875, 0 + H + 0.6375, 0 + H + (scaledY*0.825), 0 + H + 0.825);
						  tes.draw();
					  }
				  }
			  }
			GL11.glPushMatrix();
			GL11.glTranslatef((float)0.5F, (float) 0.5F, (float)0.5F);
			  GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
			  GL11.glTranslatef(0.2F, -1.0F, -0.0F);      
			  
			  Minecraft.getMinecraft().renderEngine.bindTexture(texture);

			  model.render(0.0625F);  
			  GL11.glEnable(GL11.GL_BLEND);	
			  GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		      GL11.glColor4f(1, 1, 1, 1);
		      
			  GL11.glDisable(GL11.GL_BLEND);
			  GL11.glPopMatrix();
			  break;
		}
		
		case EQUIPPED_FIRST_PERSON:
		{
			
			GL11.glPushMatrix();
			  GL11.glScalef(1.5F, 1.5F, 1.5F);
			  GL11.glRotatef(180, 1F, -0F, 0.1F);
			  GL11.glTranslatef(1.1F, -1.0F, -0.2F);   
			  
			  
			  Minecraft.getMinecraft().renderEngine.bindTexture(texture);

			  model.render(0.0625F);  
			  GL11.glEnable(GL11.GL_BLEND);	
			  GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		      GL11.glColor4f(1, 1, 1, 1);
		      
			  GL11.glDisable(GL11.GL_BLEND);  
			  GL11.glPopMatrix();
			  NBTTagCompound tag = item.getTagCompound();
			  if(tag!=null)
			  {
				  FluidStack temp = FluidStack.loadFluidStackFromNBT(tag);
				  if(temp!=null)
				  {
					  if(temp.amount > 0)
					  {
						  Tessellator tes = Tessellator.instance;
						  Minecraft.getMinecraft().renderEngine.bindTexture(Minecraft.getMinecraft().getTextureMapBlocks().locationBlocksTexture);
							  tes.startDrawingQuads();
						  final IIcon icon = temp.getFluid().getStillIcon();
						  int totalcolor = temp.getFluid().getColor();
						  Color colo = new Color(totalcolor);
							tes.setTextureUV(icon.getMinU(), icon.getMinV());
							tes.setColorRGBA(colo.getRed(), colo.getGreen(), colo.getBlue(), colo.getAlpha());
							double scaledY = (double)temp.amount / (double)64000;
						  renderCube(tes, 0 - H +0.1875, 0 - H + 0.0625, 0 - H + 0.1875, 0 + H + 0.825, 0 + H + (scaledY*0.825), 0 + H + 0.825);
						  tes.draw();
					  }
				  }
			  }
			  break;
		}
		
		default: 
		{
			NBTTagCompound tag = item.getTagCompound();
			  if(tag!=null)
			  {
				  FluidStack temp = FluidStack.loadFluidStackFromNBT(tag);
				  if(temp!=null)
				  {
					  if(temp.amount > 0)
					  {
						  Tessellator tes = Tessellator.instance;
						  Minecraft.getMinecraft().renderEngine.bindTexture(Minecraft.getMinecraft().getTextureMapBlocks().locationBlocksTexture);
							  tes.startDrawingQuads();
						  final IIcon icon = temp.getFluid().getStillIcon();
						  int totalcolor = temp.getFluid().getColor();
						  Color colo = new Color(totalcolor);
							tes.setTextureUV(icon.getMinU(), icon.getMinV());
							tes.setColorRGBA(colo.getRed(), colo.getGreen(), colo.getBlue(), colo.getAlpha());
							double scaledY = (double)temp.amount / (double)64000;
							
						  renderCube(tes, 0 - H +0.0625, 0 - H + 0.0625, 0 - H + 0.1875, 0 + H + 0.825, 0 + H + (scaledY*0.825), 0 + H + 0.825);
						  tes.draw();
					  }
				  }
			  }
			  GL11.glPushMatrix();
			  GL11.glScalef(1F, 2F, 1F);
			  GL11.glRotatef(0, 0.0F, 0.0F, 1F);
			  GL11.glTranslatef(0.0F, 0F, 0.0F);    
			  
			  Minecraft.getMinecraft().renderEngine.bindTexture(texture);

			  model.render(0.0625F);  
			  GL11.glEnable(GL11.GL_BLEND);	
			  GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		      GL11.glColor4f(1, 1, 1, 1);
		      
			  GL11.glDisable(GL11.GL_BLEND); 
			  GL11.glPopMatrix();
			  break;
		}
		}
		  
		
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
