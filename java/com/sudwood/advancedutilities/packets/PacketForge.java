package com.sudwood.advancedutilities.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.sudwood.advancedutilities.tileentity.TileEntityArmorForge;
import com.sudwood.advancedutilities.tileentity.TileEntityToolForge;

public class PacketForge extends AbstractPacket
{
	int dimID, x, y, z, type;
	public PacketForge(){

	}

	public PacketForge(int dimension, int x, int y, int z, int type){
		this.dimID = dimension;
		this.x = x;
		this.y = y;
		this.z = z; 
		this.type = type;
	}
	
	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) 
	{
		buffer.writeInt(dimID);	
		buffer.writeInt(x);	
		buffer.writeInt(y);	
		buffer.writeInt(z);
		buffer.writeInt(type);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) 
	{
		dimID = buffer.readInt();
		x = buffer.readInt();
		y = buffer.readInt();
		z = buffer.readInt();
		type = buffer.readInt();
	}

	@Override
	public void handleClientSide(EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleServerSide(EntityPlayer player) 
	{
		World world = player.worldObj;
		TileEntity tile = world.getTileEntity(x, y, z);
		
		if(type == 0) // tool
		{
			((TileEntityToolForge)tile).startIsCrafting();
		}
		if(type == 1) // armor
		{
			((TileEntityArmorForge)tile).startIsCrafting();
		}
	}

}
