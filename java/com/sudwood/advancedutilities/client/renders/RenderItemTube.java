package com.sudwood.advancedutilities.client.renders;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;

import com.sudwood.advancedutilities.blocks.AdvancedUtilitiesBlocks;
import com.sudwood.advancedutilities.client.models.ModelTube;
import com.sudwood.advancedutilities.tileentity.TileEntityItemTube;

public class RenderItemTube extends TileEntitySpecialRenderer
{
	ModelTube model = new ModelTube();
	ResourceLocation texture = new ResourceLocation("advancedutilities","textures/blocks/itemtube.png");  
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
        bindTexture(texture);
        GL11.glPushMatrix();
        //adjustRotatePivotViaMeta(te.getWorldObj(), te.xCoord, te.yCoord, te.zCoord);//rotation 1
        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
        adjustRotatePivotViaMeta(te.getWorldObj(), te.xCoord, te.yCoord, te.zCoord);//comment line "rotation 1" and uncomment this line if it doesnt work. 
        model.renderBase((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        TileEntityItemTube tile = (TileEntityItemTube) te;
      //  model.render1((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
      //  model.render2((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
      //  model.render3((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
      //  model.render4((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        thisMeta = te.getWorldObj().getBlockMetadata(te.xCoord, te.yCoord, te.zCoord);
        if(thisMeta == 0 )
        {
	        if(te.getWorldObj().getBlock(te.xCoord+ForgeDirection.NORTH.offsetX, te.yCoord, te.zCoord+ForgeDirection.NORTH.offsetZ) == AdvancedUtilitiesBlocks.itemTube && te.getWorldObj().getBlockMetadata(te.xCoord+ForgeDirection.NORTH.offsetX, te.yCoord, te.zCoord+ForgeDirection.NORTH.offsetZ) == 2)
	        {
	        	model.render3((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
	        }
	        if(te.getWorldObj().getBlock(te.xCoord+ForgeDirection.SOUTH.offsetX, te.yCoord, te.zCoord+ForgeDirection.SOUTH.offsetZ) == AdvancedUtilitiesBlocks.itemTube && te.getWorldObj().getBlockMetadata(te.xCoord+ForgeDirection.SOUTH.offsetX, te.yCoord, te.zCoord+ForgeDirection.SOUTH.offsetZ) == 3)
	        {
	        	model.render4((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
	        }
	        if(te.getWorldObj().getBlock(te.xCoord+ForgeDirection.EAST.offsetX, te.yCoord, te.zCoord+ForgeDirection.EAST.offsetZ) == AdvancedUtilitiesBlocks.itemTube && te.getWorldObj().getBlockMetadata(te.xCoord+ForgeDirection.EAST.offsetX, te.yCoord, te.zCoord+ForgeDirection.EAST.offsetZ) == 5)
	        {
	        	model.render1((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
	        }
	        if(te.getWorldObj().getBlock(te.xCoord+ForgeDirection.WEST.offsetX, te.yCoord, te.zCoord+ForgeDirection.WEST.offsetZ) == AdvancedUtilitiesBlocks.itemTube && te.getWorldObj().getBlockMetadata(te.xCoord+ForgeDirection.WEST.offsetX, te.yCoord, te.zCoord+ForgeDirection.WEST.offsetZ) == 4)
	        {
	        	model.render2((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
	        }
        }
        if(thisMeta == 1 )
        {
	        if(te.getWorldObj().getBlock(te.xCoord+ForgeDirection.NORTH.offsetX, te.yCoord, te.zCoord+ForgeDirection.NORTH.offsetZ) == AdvancedUtilitiesBlocks.itemTube && te.getWorldObj().getBlockMetadata(te.xCoord+ForgeDirection.NORTH.offsetX, te.yCoord, te.zCoord+ForgeDirection.NORTH.offsetZ) == 2)
	        {
	        	model.render3((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
	        }
	        if(te.getWorldObj().getBlock(te.xCoord+ForgeDirection.SOUTH.offsetX, te.yCoord, te.zCoord+ForgeDirection.SOUTH.offsetZ) == AdvancedUtilitiesBlocks.itemTube && te.getWorldObj().getBlockMetadata(te.xCoord+ForgeDirection.SOUTH.offsetX, te.yCoord, te.zCoord+ForgeDirection.SOUTH.offsetZ) == 3)
	        {
	        	model.render4((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
	        }
	        if(te.getWorldObj().getBlock(te.xCoord+ForgeDirection.EAST.offsetX, te.yCoord, te.zCoord+ForgeDirection.EAST.offsetZ) == AdvancedUtilitiesBlocks.itemTube && te.getWorldObj().getBlockMetadata(te.xCoord+ForgeDirection.EAST.offsetX, te.yCoord, te.zCoord+ForgeDirection.EAST.offsetZ) == 5)
	        {
	        	model.render2((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
	        }
	        if(te.getWorldObj().getBlock(te.xCoord+ForgeDirection.WEST.offsetX, te.yCoord, te.zCoord+ForgeDirection.WEST.offsetZ) == AdvancedUtilitiesBlocks.itemTube && te.getWorldObj().getBlockMetadata(te.xCoord+ForgeDirection.WEST.offsetX, te.yCoord, te.zCoord+ForgeDirection.WEST.offsetZ) == 4)
	        {
	        	model.render1((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
	        }
        }
        if(thisMeta == 2)
        {
        	 if(te.getWorldObj().getBlock(te.xCoord+ForgeDirection.UP.offsetX, te.yCoord+ForgeDirection.UP.offsetY, te.zCoord+ForgeDirection.UP.offsetZ) == AdvancedUtilitiesBlocks.itemTube && te.getWorldObj().getBlockMetadata(te.xCoord+ForgeDirection.UP.offsetX, te.yCoord+ForgeDirection.UP.offsetY, te.zCoord+ForgeDirection.UP.offsetZ) == 1)
 	        {
 	        	model.render3((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
 	        }
 	        if(te.getWorldObj().getBlock(te.xCoord+ForgeDirection.DOWN.offsetX, te.yCoord+ForgeDirection.DOWN.offsetY, te.zCoord+ForgeDirection.DOWN.offsetZ) == AdvancedUtilitiesBlocks.itemTube && te.getWorldObj().getBlockMetadata(te.xCoord+ForgeDirection.DOWN.offsetX, te.yCoord+ForgeDirection.DOWN.offsetY, te.zCoord+ForgeDirection.DOWN.offsetZ) == 0)
 	        {
 	        	model.render4((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
 	        }
 	        if(te.getWorldObj().getBlock(te.xCoord+ForgeDirection.EAST.offsetX, te.yCoord, te.zCoord+ForgeDirection.EAST.offsetZ) == AdvancedUtilitiesBlocks.itemTube && te.getWorldObj().getBlockMetadata(te.xCoord+ForgeDirection.EAST.offsetX, te.yCoord, te.zCoord+ForgeDirection.EAST.offsetZ) == 5)
 	        {
 	        	model.render1((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
 	        }
 	        if(te.getWorldObj().getBlock(te.xCoord+ForgeDirection.WEST.offsetX, te.yCoord, te.zCoord+ForgeDirection.WEST.offsetZ) == AdvancedUtilitiesBlocks.itemTube && te.getWorldObj().getBlockMetadata(te.xCoord+ForgeDirection.WEST.offsetX, te.yCoord, te.zCoord+ForgeDirection.WEST.offsetZ) == 4)
 	        {
 	        	model.render2((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
 	        }
        }
        if(thisMeta == 3)
        {
        	 if(te.getWorldObj().getBlock(te.xCoord+ForgeDirection.UP.offsetX, te.yCoord+ForgeDirection.UP.offsetY, te.zCoord+ForgeDirection.UP.offsetZ) == AdvancedUtilitiesBlocks.itemTube && te.getWorldObj().getBlockMetadata(te.xCoord+ForgeDirection.UP.offsetX, te.yCoord+ForgeDirection.UP.offsetY, te.zCoord+ForgeDirection.UP.offsetZ) == 1)
 	        {
 	        	model.render1((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
 	        }
 	        if(te.getWorldObj().getBlock(te.xCoord+ForgeDirection.DOWN.offsetX, te.yCoord+ForgeDirection.DOWN.offsetY, te.zCoord+ForgeDirection.DOWN.offsetZ) == AdvancedUtilitiesBlocks.itemTube && te.getWorldObj().getBlockMetadata(te.xCoord+ForgeDirection.DOWN.offsetX, te.yCoord+ForgeDirection.DOWN.offsetY, te.zCoord+ForgeDirection.DOWN.offsetZ) == 0)
 	        {
 	        	model.render2((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
 	        }
 	        if(te.getWorldObj().getBlock(te.xCoord+ForgeDirection.EAST.offsetX, te.yCoord, te.zCoord+ForgeDirection.EAST.offsetZ) == AdvancedUtilitiesBlocks.itemTube && te.getWorldObj().getBlockMetadata(te.xCoord+ForgeDirection.EAST.offsetX, te.yCoord, te.zCoord+ForgeDirection.EAST.offsetZ) == 5)
 	        {
 	        	model.render3((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
 	        }
 	        if(te.getWorldObj().getBlock(te.xCoord+ForgeDirection.WEST.offsetX, te.yCoord, te.zCoord+ForgeDirection.WEST.offsetZ) == AdvancedUtilitiesBlocks.itemTube && te.getWorldObj().getBlockMetadata(te.xCoord+ForgeDirection.WEST.offsetX, te.yCoord, te.zCoord+ForgeDirection.WEST.offsetZ) == 4)
 	        {
 	        	model.render4((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
 	        }
        }
        if(thisMeta == 5)
        {
	        if(te.getWorldObj().getBlock(te.xCoord+ForgeDirection.NORTH.offsetX, te.yCoord, te.zCoord+ForgeDirection.NORTH.offsetZ) == AdvancedUtilitiesBlocks.itemTube && te.getWorldObj().getBlockMetadata(te.xCoord+ForgeDirection.NORTH.offsetX, te.yCoord, te.zCoord+ForgeDirection.NORTH.offsetZ) == 2)
	        {
	        	model.render3((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
	        }
	        if(te.getWorldObj().getBlock(te.xCoord+ForgeDirection.SOUTH.offsetX, te.yCoord, te.zCoord+ForgeDirection.SOUTH.offsetZ) == AdvancedUtilitiesBlocks.itemTube && te.getWorldObj().getBlockMetadata(te.xCoord+ForgeDirection.SOUTH.offsetX, te.yCoord, te.zCoord+ForgeDirection.SOUTH.offsetZ) == 3)
	        {
	        	model.render4((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
	        }
	        if(te.getWorldObj().getBlock(te.xCoord+ForgeDirection.UP.offsetX, te.yCoord+ForgeDirection.UP.offsetY, te.zCoord+ForgeDirection.UP.offsetZ) == AdvancedUtilitiesBlocks.itemTube && te.getWorldObj().getBlockMetadata(te.xCoord+ForgeDirection.UP.offsetX, te.yCoord+ForgeDirection.UP.offsetY, te.zCoord+ForgeDirection.UP.offsetZ) == 1)
 	        {
 	        	model.render1((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
 	        }
 	        if(te.getWorldObj().getBlock(te.xCoord+ForgeDirection.DOWN.offsetX, te.yCoord+ForgeDirection.DOWN.offsetY, te.zCoord+ForgeDirection.DOWN.offsetZ) == AdvancedUtilitiesBlocks.itemTube && te.getWorldObj().getBlockMetadata(te.xCoord+ForgeDirection.DOWN.offsetX, te.yCoord+ForgeDirection.DOWN.offsetY, te.zCoord+ForgeDirection.DOWN.offsetZ) == 0)
 	        {
 	        	model.render2((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
 	        }
        }
        if(thisMeta == 4)
        {
	        if(te.getWorldObj().getBlock(te.xCoord+ForgeDirection.NORTH.offsetX, te.yCoord, te.zCoord+ForgeDirection.NORTH.offsetZ) == AdvancedUtilitiesBlocks.itemTube && te.getWorldObj().getBlockMetadata(te.xCoord+ForgeDirection.NORTH.offsetX, te.yCoord, te.zCoord+ForgeDirection.NORTH.offsetZ) == 2)
	        {
	        	model.render4((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
	        }
	        if(te.getWorldObj().getBlock(te.xCoord+ForgeDirection.SOUTH.offsetX, te.yCoord, te.zCoord+ForgeDirection.SOUTH.offsetZ) == AdvancedUtilitiesBlocks.itemTube && te.getWorldObj().getBlockMetadata(te.xCoord+ForgeDirection.SOUTH.offsetX, te.yCoord, te.zCoord+ForgeDirection.SOUTH.offsetZ) == 3)
	        {
	        	model.render3((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
	        }
	        if(te.getWorldObj().getBlock(te.xCoord+ForgeDirection.UP.offsetX, te.yCoord+ForgeDirection.UP.offsetY, te.zCoord+ForgeDirection.UP.offsetZ) == AdvancedUtilitiesBlocks.itemTube && te.getWorldObj().getBlockMetadata(te.xCoord+ForgeDirection.UP.offsetX, te.yCoord+ForgeDirection.UP.offsetY, te.zCoord+ForgeDirection.UP.offsetZ) == 1)
 	        {
 	        	model.render1((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
 	        }
 	        if(te.getWorldObj().getBlock(te.xCoord+ForgeDirection.DOWN.offsetX, te.yCoord+ForgeDirection.DOWN.offsetY, te.zCoord+ForgeDirection.DOWN.offsetZ) == AdvancedUtilitiesBlocks.itemTube && te.getWorldObj().getBlockMetadata(te.xCoord+ForgeDirection.DOWN.offsetX, te.yCoord+ForgeDirection.DOWN.offsetY, te.zCoord+ForgeDirection.DOWN.offsetZ) == 0)
 	        {
 	        	model.render2((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
 	        }
        }
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }

}
