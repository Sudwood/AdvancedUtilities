package com.sudwood.advancedutilities.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import com.sudwood.advancedutilities.AdvancedUtilities;
import com.sudwood.advancedutilities.ExtendedPlayer;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;

public class SyncPlayerPropsPacket implements IMessage
{
	// Previously, we've been writing each field in our properties one at a time,
	// but that is really annoying, and we've already done it in the save and load
	// NBT methods anyway, so here's a slick way to efficiently send all of your
	// extended data, and no matter how much you add or remove, you'll never have
	// to change the packet / synchronization of your data.
	
	// this will store our ExtendedPlayer data, allowing us to easily read and write
	private NBTTagCompound data;
	
	// The basic, no-argument constructor MUST be included to use the new automated handling
	public SyncPlayerPropsPacket() {}
	
	// We need to initialize our data, so provide a suitable constructor:
	public SyncPlayerPropsPacket(EntityPlayer player) 
	{
		// create a new tag compound
		data = new NBTTagCompound();
		// and save our player's data into it
		ExtendedPlayer.get(player).saveNBTData(data);
	}
	
	public static class Handler implements IMessageHandler<SyncPlayerPropsPacket, IMessage> 
	{
        @Override
        public IMessage onMessage(SyncPlayerPropsPacket message, MessageContext ctx) 
        {
        	EntityPlayer player = Minecraft.getMinecraft().thePlayer;
        	ExtendedPlayer.get(player).loadNBTData(message.data);
            return null; // no response in this case
        }
    }

	@Override
	public void fromBytes(ByteBuf buffer) 
	{
		data = ByteBufUtils.readTag(buffer);
	}

	@Override
	public void toBytes(ByteBuf buffer) 
	{
		ByteBufUtils.writeTag(buffer, data);
	}
}