package com.sudwood.advancedutilities.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;

import com.sudwood.advancedutilities.ExtendedPlayer;

public class PacketJetpack extends AbstractPacket
{
	boolean isJetpack;
	public PacketJetpack(){

	}

	public PacketJetpack(boolean isJetpack){
		this.isJetpack = isJetpack;
	}
	
	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) 
	{
		buffer.writeBoolean(isJetpack);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) 
	{
		isJetpack = buffer.readBoolean();
	}

	@Override
	public void handleClientSide(EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleServerSide(EntityPlayer player) 
	{
		ExtendedPlayer props = ExtendedPlayer.get(player);
		props.isJetpack = isJetpack;
		props.saveProxyData(player);
		props.loadProxyData(player);
	}

}
