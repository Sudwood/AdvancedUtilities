package com.sudwood.advancedutilities.client.renders;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import com.sudwood.advancedutilities.client.models.ModelSmeltry;
import com.sudwood.advancedutilities.tileentity.TileEntitySmeltry;

public class RenderSmeltry extends TileEntitySpecialRenderer
{
	ModelSmeltry model = new ModelSmeltry();
	ResourceLocation texture = new ResourceLocation("advancedutilities", "textures/blocks/smeltry.png");

	private void adjustRotatePivotViaMeta(World world, int x, int y, int z) {
        int meta = world.getBlockMetadata(x, y, z);
        if(meta == 3)
        {
        	
        	GL11.glRotatef(meta * (-45), 1.0F, 800.0F, 1.0F);
        }
        if(meta == 2)
        {
        	GL11.glRotatef(meta * (-45), 1.0F, 800.0F, 1.0F);
        }
        if(meta == 4)
        {
        	
        	GL11.glRotatef(meta * (225),0.0F,10.0F, 0.0F);
        }
    }

    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) 
    {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        bindTexture(texture);
        GL11.glPushMatrix();
        //adjustRotatePivotViaMeta(te.getWorldObj(), te.xCoord, te.yCoord, te.zCoord);//rotation 1
        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
        adjustRotatePivotViaMeta(te.getWorldObj(), te.xCoord, te.yCoord, te.zCoord);//comment line "rotation 1" and uncomment this line if it doesnt work. 
        model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }
	
	

}
