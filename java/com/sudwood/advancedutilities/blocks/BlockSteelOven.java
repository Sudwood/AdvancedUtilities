package com.sudwood.advancedutilities.blocks;

import java.util.List;

import com.sudwood.advancedutilities.AdvancedUtilities;
import com.sudwood.advancedutilities.HelperLibrary;
import com.sudwood.advancedutilities.tileentity.TileEntitySmeltry;
import com.sudwood.advancedutilities.tileentity.TileEntitySteelController;
import com.sudwood.advancedutilities.tileentity.TileEntitySteelOven;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSteelOven extends BlockContainer
{
	/*
	 * meta of 1 = top corners
	 * meta of 2 = top sides
	 * meta of 3 = middle top bottom blocks
	 * meta of 4 = middle furnace block
	 * meta of 5 = middle side
	 * meta of 6 = bottom corners
	 * meta of 7 = bottom sides
	 */
	IIcon icons[] = new IIcon[11];
	private int type;
	protected BlockSteelOven(Material mat, int type) {
		super(mat);
		this.type = type;
		// TODO Auto-generated constructor stub
	}


	@Override
	public TileEntity createNewTileEntity(World world, int stuff) {
		// TODO Auto-generated method stub
		if(type == 1)
		{
			return new TileEntitySteelController();
		}
		else
		return new TileEntitySteelOven();
		
	}
	public void breakBlock(World world, int x, int y, int z, Block block, int p_149749_6_)
	{
		if(type == 0)
		{
			((TileEntitySteelOven)world.getTileEntity(x, y, z)).breakBlock();
		}
        super.breakBlock(world, x, y, z, block, p_149749_6_);
    }
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side)
    {
		if(type == 1)
		{
			if(side == 0 || side == 1)
				return icons[0];
			return icons[1];
		}
		TileEntitySteelOven tile = (TileEntitySteelOven) world.getTileEntity(x, y, z);
		switch(tile.getPiece())
		{
		case 1:
			if( side == 0 || side == 1)
				return icons[3];
			if(side == 3)
				return icons[5];
			if(side == 2)
				return icons[4];
			if(side == 4)
				return icons[5];
			if(side == 5)
				return icons[4];
			 break;
		 case 2:
			 if(side == 1| side == 0)
				 return icons[8];
			 else return icons[9];
		 case 3:
			 if( side == 0 || side == 1)
					return icons[5];
				if(side == 3)
					return icons[5];
				if(side == 2)
					return icons[4];
				if(side == 4)
					return icons[4];
				if(side == 5)
					return icons[5];
			 break;
		 case 4:
			 if(side == 1| side == 0)
				 return icons[6];
			 else return icons[9];
		 case 5:
			 return icons[10];
		 case 6:
			 if(side == 1| side == 0)
				 return icons[9];
			 else return icons[9];
		 case 7:
			 if( side == 0 || side == 1)
					return icons[2];
				if(side == 3)
					return icons[4];
				if(side == 2)
					return icons[5];
				if(side == 4)
					return icons[5];
				if(side == 5)
					return icons[4];
			 break;
		 case 8:
			 if(side == 1| side == 0)
				 return icons[7];
			 else return icons[9];
		 case 9:
			 if( side == 0 || side == 1)
					return icons[4];
				if(side == 3)
					return icons[4];
				if(side == 2)
					return icons[5];
				if(side == 4)
					return icons[4];
				if(side == 5)
					return icons[5];
			 break;
		 case 10:
			 if( side == 0 || side == 1)
					return icons[8];
				if(side == 3)
					return icons[8];
				if(side == 2)
					return icons[8];
				if(side == 4)
					return icons[7];
				if(side == 5)
					return icons[7];
			 break;
		 case 11:
			return icons[1];
		 case 12:
			 if( side == 0 || side == 1)
					return icons[8];
				if(side == 3)
					return icons[7];
				if(side == 2)
					return icons[7];
				if(side == 4)
					return icons[8];
				if(side == 5)
					return icons[8];
			 break;
		 case 13:
			 return icons[1];
		 case 14:
			 return icons[1];
		 case 15:
			 if( side == 0 || side == 1)
					return icons[8];
				if(side == 3)
					return icons[7];
				if(side == 2)
					return icons[7];
				if(side == 4)
					return icons[8];
				if(side == 5)
					return icons[8];
			 break;
		 case 16:
			 return icons[1];
		 case 17:
			 if( side == 0 || side == 1)
					return icons[8];
				if(side == 3)
					return icons[8];
				if(side == 2)
					return icons[8];
				if(side == 4)
					return icons[7];
				if(side == 5)
					return icons[7];
			 break;
		 case 18:
			 if( side == 0 || side == 1)
					return icons[3];
				if(side == 3)
					return icons[3];
				if(side == 2)
					return icons[2];
				if(side == 4)
					return icons[3];
				if(side == 5)
					return icons[2];
			 break;
		 case 19:
			 if(side == 1| side == 0)
				 return icons[8];
			 else return icons[6];
		 case 20:
			 if( side == 0 || side == 1)
					return icons[5];
				if(side == 3)
					return icons[3];
				if(side == 2)
					return icons[2];
				if(side == 4)
					return icons[2];
				if(side == 5)
					return icons[3];
			 break;
		 case 21:
			 if(side == 1| side == 0)
				 return icons[6];
			 else return icons[6];
		 case 22:
			 return icons[10];
		 case 23:
			 if(side == 1| side == 0)
				 return icons[9];
			 else return icons[6];
		 case 24:
			 if( side == 0 || side == 1)
					return icons[2];
				if(side == 3)
					return icons[2];
				if(side == 2)
					return icons[3];
				if(side == 4)
					return icons[3];
				if(side == 5)
					return icons[2];
			 break;
		 case 25:
			 if(side == 1| side == 0)
				 return icons[7];
			 else return icons[6];
		 case 26:
			 if( side == 0 || side == 1)
					return icons[4];
				if(side == 3)
					return icons[2];
				if(side == 2)
					return icons[3];
				if(side == 4)
					return icons[2];
				if(side == 5)
					return icons[3];
			 break;
		}
		return icons[0];
    }
	@Override
	public IIcon getIcon(int side, int meta) {
		// TODO Auto-generated method stub
		if(type == 1)
		{
			if(side == 0 || side == 1)
				return icons[0];
			return icons[1];
		}
		if(meta == 3)
			return icons[10];
		switch(meta)
		{
		case 1:
			if( side == 0 || side == 1)
				return icons[2];
			if(side == 3)
				return icons[4];
			if(side == 2)
				return icons[5];
			if(side == 4)
				return icons[5];
			if(side == 5)
				return icons[4];
			break;
		case 2:
			break;
		case 4:
			break;
		case 5:
			break;
		case 6:
			if( side == 0 || side == 1)
				return icons[4];
			if(side == 3)
				return icons[2];
			if(side == 2)
				return icons[3];
			if(side == 4)
				return icons[3];
			if(side == 5)
				return icons[2];
			break;
		case 7:
			break;
		}
		return icons[0];
	}
	
	@SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
        this.icons[0] = p_149651_1_.registerIcon("advancedutilities:steelovenbase");
        this.icons[1] = p_149651_1_.registerIcon("advancedutilities:steelovencenter");
        this.icons[2] = p_149651_1_.registerIcon("advancedutilities:steelovenbottomleftcorner");
        this.icons[3] = p_149651_1_.registerIcon("advancedutilities:steelovenbottomrightcorner");
        this.icons[4] = p_149651_1_.registerIcon("advancedutilities:steeloventopleftcorner");
        this.icons[5] = p_149651_1_.registerIcon("advancedutilities:steeloventoprightcorner");
        this.icons[6] = p_149651_1_.registerIcon("advancedutilities:steelovenbottomside");
        this.icons[7] = p_149651_1_.registerIcon("advancedutilities:steelovenleftside");
        this.icons[8] = p_149651_1_.registerIcon("advancedutilities:steelovenrightside");
        this.icons[9] = p_149651_1_.registerIcon("advancedutilities:steeloventopside");
        this.icons[10] = p_149651_1_.registerIcon("advancedutilities:steeloventop");
    }
	
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor) 
	{
		if(type == 1)
		{
			TileEntitySteelController tile = (TileEntitySteelController) world.getTileEntity(x, y, z);
			if(!tile.isMulti)
			{
				this.makeMulti(world, x, y, z);
			}
		}
	}
	 
	 public void makeMulti(World world, int x, int y, int z)
	 {
		 if(type ==  1)
		 {
			 if(HelperLibrary.is3x3(AdvancedUtilitiesBlocks.blockSteelOven, x, y, z, world)) 
			 {
				 for(int i = 1; i < 27; i++)
				 {
					 TileEntitySteelOven tile = (TileEntitySteelOven) getEntForNum(i, world, x, y, z);
					 tile.setPiece(i);
					 updateEntForNum(i, world, x, y, z);
				 }
			 }
		 }
	 }
	 public void updateEntForNum(int num, World world, int x, int y, int z)
	 {
		 switch(num)
		 {
		 case 1:
			 world.markBlockForUpdate(x+1, y+1, z+1);
			 break;
		 case 2:
			  world.markBlockForUpdate(x+1, y+1, z);
			  break;
		 case 3:
			  world.markBlockForUpdate(x+1, y+1, z-1);
			  break;
		 case 4:
			  world.markBlockForUpdate(x, y+1, z+1);
			  break;
		 case 5:
			  world.markBlockForUpdate(x, y+1, z);
			  break;
		 case 6:
			  world.markBlockForUpdate(x, y+1, z-1);
			  break;
		 case 7:
			  world.markBlockForUpdate(x-1, y+1, z+1);
			  break;
		 case 8:
			  world.markBlockForUpdate(x-1, y+1, z);
			  break;
		 case 9:
			  world.markBlockForUpdate(x-1, y+1, z-1);
			  break;
		 case 10:
			  world.markBlockForUpdate(x+1, y, z+1);
			  break;
		 case 11:
			  world.markBlockForUpdate(x+1, y, z);
			  break;
		 case 12:
			  world.markBlockForUpdate(x+1, y, z-1);
			  break;
		 case 13:
			  world.markBlockForUpdate(x, y, z+1);
			  break;
		 case 14:
			  world.markBlockForUpdate(x, y, z-1);
			  break;
		 case 15:
			  world.markBlockForUpdate(x-1, y, z+1);
			  break;
		 case 16:
			  world.markBlockForUpdate(x-1, y, z);
			  break;
		 case 17:
			  world.markBlockForUpdate(x-1, y, z-1);
			  break;
		 case 18:
			  world.markBlockForUpdate(x+1, y-1, z+1);
			  break;
		 case 19:
			  world.markBlockForUpdate(x+1, y-1, z);
			  break;
		 case 20:
			  world.markBlockForUpdate(x+1, y-1, z-1);
			  break;
		 case 21:
			  world.markBlockForUpdate(x, y-1, z+1);
			  break;
		 case 22:
			  world.markBlockForUpdate(x, y-1, z);
			  break;
		 case 23:
			  world.markBlockForUpdate(x, y-1, z-1);
			  break;
		 case 24:
			  world.markBlockForUpdate(x-1, y-1, z+1);
			  break;
		 case 25:
			  world.markBlockForUpdate(x-1, y-1, z);
			  break;
		 case 26:
			  world.markBlockForUpdate(x-1, y-1, z-1);
			  break;
		 }
	 }
	 public TileEntity getEntForNum(int num, World world, int x, int y, int z)
	 {
		 switch(num)
		 {
		 case 1:
			return world.getTileEntity(x+1, y+1, z+1);
		 case 2:
			 return world.getTileEntity(x+1, y+1, z);
		 case 3:
			 return world.getTileEntity(x+1, y+1, z-1);
		 case 4:
			 return world.getTileEntity(x, y+1, z+1);
		 case 5:
			 return world.getTileEntity(x, y+1, z);
		 case 6:
			 return world.getTileEntity(x, y+1, z-1);
		 case 7:
			 return world.getTileEntity(x-1, y+1, z+1);
		 case 8:
			 return world.getTileEntity(x-1, y+1, z);
		 case 9:
			 return world.getTileEntity(x-1, y+1, z-1);
		 case 10:
			 return world.getTileEntity(x+1, y, z+1);
		 case 11:
			 return world.getTileEntity(x+1, y, z);
		 case 12:
			 return world.getTileEntity(x+1, y, z-1);
		 case 13:
			 return world.getTileEntity(x, y, z+1);
		 case 14:
			 return world.getTileEntity(x, y, z-1);
		 case 15:
			 return world.getTileEntity(x-1, y, z+1);
		 case 16:
			 return world.getTileEntity(x-1, y, z);
		 case 17:
			 return world.getTileEntity(x-1, y, z-1);
		 case 18:
			 return world.getTileEntity(x+1, y-1, z+1);
		 case 19:
			 return world.getTileEntity(x+1, y-1, z);
		 case 20:
			 return world.getTileEntity(x+1, y-1, z-1);
		 case 21:
			 return world.getTileEntity(x, y-1, z+1);
		 case 22:
			 return world.getTileEntity(x, y-1, z);
		 case 23:
			 return world.getTileEntity(x, y-1, z-1);
		 case 24:
			 return world.getTileEntity(x-1, y-1, z+1);
		 case 25:
			 return world.getTileEntity(x-1, y-1, z);
		 case 26:
			 return world.getTileEntity(x-1, y-1, z-1);
		 }
		 return null;
	 }
	 @Override
	 public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int px, float py, float pz, float par9)
	    {
		 if(type == 1)
		 {
		            TileEntity tile = world.getTileEntity(x, y, z);
		            if(tile == null || player.isSneaking()){
		            	
		            	return false;
		            	}

		            if (tile != null&& tile instanceof TileEntitySteelController && ((TileEntitySteelController) tile).isMulti)
		            {
		            	player.openGui(AdvancedUtilities.instance, AdvancedUtilities.steelOvenGui, world, x, y, z);
		            }

		            return true;
		 }
		 if(type == 0)
		 {
		            TileEntity tile = ((TileEntitySteelOven)world.getTileEntity(x, y, z)).getTileForNum();
		           
		            if(tile == null || player.isSneaking())
		            {
		            	return false;
		            }
		            if (tile != null&& tile instanceof TileEntitySteelController && ((TileEntitySteelOven)world.getTileEntity(x, y, z)).getPiece() != 0)
		            {
		            	player.openGui(AdvancedUtilities.instance, AdvancedUtilities.steelOvenGui, world, tile.xCoord, tile.yCoord, tile.zCoord);
		            }

		            return true;
		 }
		 return false;
	    }

}
