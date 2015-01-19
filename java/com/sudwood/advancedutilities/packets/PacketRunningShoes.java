package com.sudwood.advancedutilities.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

import com.sudwood.advancedutilities.AdvancedUtilities;
import com.sudwood.advancedutilities.ExtendedPlayer;
import com.sudwood.advancedutilities.items.AdvancedUtilitiesItems;
import com.sudwood.advancedutilities.items.ItemQuickPotion;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketRunningShoes implements IMessage
{
	boolean isRunningShoes;
	public PacketRunningShoes(){

	}

	public PacketRunningShoes(boolean isRunningShoes){
		this.isRunningShoes = isRunningShoes;
	}

	@Override
	public void fromBytes(ByteBuf buffer) 
	{
		isRunningShoes = buffer.readBoolean();
	}

	@Override
	public void toBytes(ByteBuf buffer) 
	{
		buffer.writeBoolean(isRunningShoes);
	}
	
	public static class Handler implements IMessageHandler<PacketRunningShoes, IMessage> 
	{
        @Override
        public IMessage onMessage(PacketRunningShoes message, MessageContext ctx) 
        {
        	EntityPlayer player = ctx.getServerHandler().playerEntity;
        	ExtendedPlayer props = ExtendedPlayer.get(player);
    		props.isRunning = message.isRunningShoes;
    		props.saveProxyData(player);
    		props.loadProxyData(player);
    		return null;
        }
    }

}
