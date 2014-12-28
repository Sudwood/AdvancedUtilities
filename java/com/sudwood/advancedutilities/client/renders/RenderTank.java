package com.sudwood.advancedutilities.client.renders;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidRegistry;

import org.lwjgl.opengl.GL11;

import com.sudwood.advancedutilities.client.models.ModelTank;
import com.sudwood.advancedutilities.client.models.ModelTankFiller;
import com.sudwood.advancedutilities.tileentity.TileEntityTank;

public class RenderTank extends TileEntitySpecialRenderer
{
	ModelTank model = new ModelTank();
	ModelTankFiller fluid = new ModelTankFiller();
	@Override
	public void renderTileEntityAt(TileEntity var1, double var2, double var4, double var6, float var8) 
	{
		  GL11.glPushMatrix();
			  GL11.glTranslatef((float)var2 + 0.5F, (float)var4 - 0.5F, (float)var6 + 0.5F);
			  GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
			  GL11.glTranslatef(0.0F, -2.0F, 0.0F);        
			  bindTexture(new ResourceLocation("advancedutilities","textures/blocks/tank.png"));   
			  model.render(0.0625F);  
			  GL11.glEnable(GL11.GL_BLEND);		
			  TileEntityTank tile = (TileEntityTank) var1;
			  if(tile.tank.getFluidAmount() > 0)
			  {
				  if(tile.tank.getFluid().getFluid() == FluidRegistry.LAVA)
				  {
					  bindTexture(new ResourceLocation("minecraft","textures/blocks/lava_still.png"));   
					  fluid.render(0.0625F, (int)((double)tile.tank.getFluidAmount() / tile.tank.getCapacity()*12));
				  }
				  if(tile.tank.getFluid().getFluid() == FluidRegistry.WATER)
				  {
					  bindTexture(new ResourceLocation("minecraft","textures/blocks/water_still.png"));   
					  fluid.render(0.0625F, (int)((double)tile.tank.getFluidAmount() / tile.tank.getCapacity()*12));
				  }
				  else
				  {
					  bindTexture(new ResourceLocation("advancedutilities","textures/blocks/steam_still.png"));   
					  fluid.render(0.0625F, (int)((double)tile.tank.getFluidAmount() / tile.tank.getCapacity()*12));
				  }
			  }

		  GL11.glPopMatrix();
	}

}
