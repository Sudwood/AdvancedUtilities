package com.sudwood.advancedutilities.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import com.sudwood.advancedutilities.ExtendedPlayer;
import com.sudwood.advancedutilities.items.AdvancedUtilitiesItems;
import com.sudwood.advancedutilities.items.ItemQuickPotion;
import com.sudwood.advancedutilities.tileentity.TileEntitySteamCharger;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketSteamCharger implements IMessage
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

	public static class Handler implements IMessageHandler<PacketSteamCharger, IMessage> 
	{
        @Override
        public IMessage onMessage(PacketSteamCharger message, MessageContext ctx) 
        {
        	if(Minecraft.getMinecraft().thePlayer != null)
        	{
	        	EntityPlayer player = Minecraft.getMinecraft().thePlayer;
	        	TileEntitySteamCharger tile = (TileEntitySteamCharger) player.worldObj.getTileEntity(message.x, message.y, message.z);
	    		//tile.renderGun = message.isGun;
	    		tile.renderJetpack = message.isJetpack;
	    		tile.renderJackHammer = message.isJackHammer;
	    		tile.renderGun = message.isGun;
        	}
            return null; // no response in this case
        }
    }

	@Override
	public void fromBytes(ByteBuf buffer) 
	{
		isGun = buffer.readBoolean();
		isJetpack = buffer.readBoolean();
		isJackHammer = buffer.readBoolean();
		x = buffer.readInt();
		y = buffer.readInt();
		z = buffer.readInt();
	}

	@Override
	public void toBytes(ByteBuf buffer)
	{
		buffer.writeBoolean(isGun);
		buffer.writeBoolean(isJetpack);
		buffer.writeBoolean(isJackHammer);
		buffer.writeInt(x);
		buffer.writeInt(y);
		buffer.writeInt(z);
	}

}
