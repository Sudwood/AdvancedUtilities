package com.sudwood.advancedutilities.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import com.sudwood.advancedutilities.ExtendedPlayer;
import com.sudwood.advancedutilities.items.AdvancedUtilitiesItems;
import com.sudwood.advancedutilities.items.ItemQuickPotion;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketJetpack implements IMessage
{
	boolean isJetpack;
	int type;
	public PacketJetpack(){

	}

	public PacketJetpack(boolean isJetpack, int type){
		this.isJetpack = isJetpack;
		this.type = type;
	}


	@Override
	public void fromBytes(ByteBuf buffer) 
	{
		isJetpack = buffer.readBoolean();
		type = buffer.readByte();
	}

	@Override
	public void toBytes(ByteBuf buffer) 
	{
		buffer.writeBoolean(isJetpack);
		buffer.writeByte(type);
	}
	
	public static class Handler implements IMessageHandler<PacketJetpack, IMessage> 
	{
        @Override
        public IMessage onMessage(PacketJetpack message, MessageContext ctx) 
        {
        	EntityPlayer player = ctx.getServerHandler().playerEntity;
        	ExtendedPlayer props = ExtendedPlayer.get(player);
    		if(message.type == 0)
    		{
    			props.isJetpack = message.isJetpack;
    		}
    		if(message.type == 1)
    		{
    			props.toggleJetpack = message.isJetpack;
    		}
    		props.saveProxyData(player);
    		props.loadProxyData(player);
    		return null;
        }
    }

}
