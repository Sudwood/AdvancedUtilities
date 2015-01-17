package com.sudwood.advancedutilities.client.renders;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;

import com.sudwood.advancedutilities.blocks.AdvancedUtilitiesBlocks;
import com.sudwood.advancedutilities.client.models.ModelSplitterTube;
import com.sudwood.advancedutilities.client.models.ModelTube;
import com.sudwood.advancedutilities.tileentity.TileEntityItemTube;
import com.sudwood.advancedutilities.tileentity.TileEntitySplitterFluidTube;
import com.sudwood.advancedutilities.tileentity.TileEntitySplitterItemTube;

public class RenderSplitterTube extends TileEntitySpecialRenderer
{
	ModelSplitterTube model = new ModelSplitterTube();
	ResourceLocation texture = new ResourceLocation("advancedutilities","textures/blocks/itemtube.png");  
	ResourceLocation texture2 = new ResourceLocation("advancedutilities","textures/blocks/fluidtube.png"); 
	int thisMeta = 0;
	
	private void adjustRotatePivotViaMeta(World world, int x, int y, int z) {
        int meta = world.getBlockMetadata(x, y, z);
        
        if(meta == 3)
        {
        	GL11.glRotatef(meta * (-90), 1.0F, 800.0F, 1.0F);
        }
        if(meta == 2)
        {
        	GL11.glRotatef(meta * (-90), 0.0F, 2.0F, 2.0F);
        	GL11.glTranslatef(0F, -1F, 1F);
        }
        if(meta == 4)
        {
        	
        	GL11.glRotatef(meta * (-225),80.0F,-80F, 1F);
        	GL11.glTranslatef(-1F, -1F, 0F);
        }
        if(meta == 3)
        {
        	
        	GL11.glRotatef(meta * (-90),0.0F,0.0F, -1.0F);
        	GL11.glTranslatef(-1F, -1F, 0F);
        }
        if(meta == 5)
        {
        	
        	GL11.glRotatef(meta * (-90),0.0F,0.0F, 1.0F);
        	GL11.glTranslatef(-1F, -1F, 0F);
        }
        if(meta == 0)
        {
        	
        	GL11.glRotatef(4 * (-225),0.0F,-0F, 1F);
        	GL11.glTranslatef(0F, -2F, 0F);
        }
    }

    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        if(te instanceof TileEntitySplitterItemTube)
        	bindTexture(texture);
        else if(te instanceof TileEntitySplitterFluidTube)
        	bindTexture(texture2);
        GL11.glPushMatrix();
        //adjustRotatePivotViaMeta(te.getWorldObj(), te.xCoord, te.yCoord, te.zCoord);//rotation 1
        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
        adjustRotatePivotViaMeta(te.getWorldObj(), te.xCoord, te.yCoord, te.zCoord);//comment line "rotation 1" and uncomment this line if it doesnt work. 
        model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
      //  model.render1((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
      //  model.render2((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
      //  model.render3((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
      //  model.render4((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }

}
