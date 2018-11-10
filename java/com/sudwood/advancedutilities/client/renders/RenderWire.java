package com.sudwood.advancedutilities.client.renders;

import org.lwjgl.opengl.GL11;

import com.sudwood.advancedutilities.client.models.ModelQuarryFrame;
import com.sudwood.advancedutilities.tileentity.TileEntityWire;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class RenderWire extends TileEntitySpecialRenderer
{
	ModelQuarryFrame model = new ModelQuarryFrame();
	ResourceLocation texture = new ResourceLocation("advancedutilities","textures/blocks/copperwire.png");  
	ResourceLocation texture2 = new ResourceLocation("advancedutilities","textures/blocks/ironwire.png");  
	ResourceLocation texture3 = new ResourceLocation("advancedutilities","textures/blocks/goldwire.png");  
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
        TileEntityWire tile = (TileEntityWire) te;
        if(tile.getTransferAmount() == tile.copperRate)
        	bindTexture(texture);
        if(tile.getTransferAmount() == tile.ironRate)
        	bindTexture(texture2);
        if(tile.getTransferAmount() == tile.goldRate)
        	bindTexture(texture3);
        GL11.glPushMatrix();
        //adjustRotatePivotViaMeta(te.getWorldObj(), te.xCoord, te.yCoord, te.zCoord);//rotation 1
        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
       // adjustRotatePivotViaMeta(te.getWorldObj(), te.xCoord, te.yCoord, te.zCoord);//comment line "rotation 1" and uncomment this line if it doesnt work. 
        model.renderbase(0.0625F);
      //  model.render1((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
      //  model.render2((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
      //  model.render3((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
      //  model.render4((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        if(tile.render[0] == 1)
        {
        	model.render0(0.0625F);
        }
        if(tile.render[1] == 1)
        {
        	model.render1(0.0625F);
        }
        if(tile.render[2] == 1)
        {
        	model.render2(0.0625F);
        }
        if(tile.render[3] == 1)
        {
        	model.render3(0.0625F);
        }
        if(tile.render[4] == 1)
        {
        	model.render4(0.0625F);
        }
        if(tile.render[5] == 1)
        {
        	model.render5(0.0625F);
        }
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }

}
