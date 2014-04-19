package com.sudwood.advancedutilities.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;

import com.sudwood.advancedutilities.ExtendedPlayer;
import com.sudwood.advancedutilities.tileentity.TileEntitySteamCharger;

public class PacketSteamCharger extends AbstractPacket
{
	boolean isGun;
	boolean isJetpack;
	boolean isJackHammer;
	int x, y, z;
	public PacketSteamCharger(){

	}

	public PacketSteamCharger(boolean isGun, boolean isJetpack, boolean isJackHammer, int x, int y, int z){
		this.isGun = isGun;
		this.isJetpack = isJetpack;
		this.isJackHammer = isJackHammer;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) 
	{
		buffer.writeBoolean(isGun);
		buffer.writeBoolean(isJetpack);
		buffer.writeBoolean(isJackHammer);
		buffer.writeInt(x);
		buffer.writeInt(y);
		buffer.writeInt(z);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) 
	{
		isGun = buffer.readBoolean();
		isJetpack = buffer.readBoolean();
		isJackHammer = buffer.readBoolean();
		x = buffer.readInt();
		y = buffer.readInt();
		z = buffer.readInt();
	}

	@Override
	public void handleClientSide(EntityPlayer player) 
	{
		TileEntitySteamCharger tile = (TileEntitySteamCharger) player.worldObj.getTileEntity(x, y, z);
		tile.renderGun = isGun;
		tile.renderJetpack = isJetpack;
		tile.renderJackHammer = isJackHammer;
	}

	@Override
	public void handleServerSide(EntityPlayer player) 
	{
	}

}
