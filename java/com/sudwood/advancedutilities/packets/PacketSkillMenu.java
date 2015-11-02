package com.sudwood.advancedutilities.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

import com.sudwood.advancedutilities.ExtendedPlayer;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketSkillMenu implements IMessage
{
	byte[] skills = {0, 0, 0};
	byte levels;
	public PacketSkillMenu(){

	}

	public PacketSkillMenu(byte[] skills, byte levels)
	{
		this.skills = skills;
		this.levels = levels;
	}


	@Override
	public void fromBytes(ByteBuf buffer) 
	{
		buffer.readBytes(skills);
		levels = buffer.readByte();
	}

	@Override
	public void toBytes(ByteBuf buffer) 
	{
		buffer.writeBytes(skills);
		buffer.writeByte(levels);
	}
	
	public static class Handler implements IMessageHandler<PacketSkillMenu, IMessage> 
	{
        @Override
        public IMessage onMessage(PacketSkillMenu message, MessageContext ctx) 
        {
        	EntityPlayer player = ctx.getServerHandler().playerEntity;
        	ExtendedPlayer props = ExtendedPlayer.get(player);
        	if(player.experienceLevel >= message.levels)
        	{
        		if(message.skills[0] >= 0 && message.skills[0] <= 3 && message.skills[1] >= 0 && message.skills[1] <= 3 && message.skills[2] >= 0 && message.skills[2] <= 3)
        		{
        			props.skills = message.skills;
        			player.experienceLevel -= message.levels;
        			System.out.println(props.skills[0]+" speed");
        		}
        	}
        	props.saveProxyData(player);
    		props.loadProxyData(player);
    		return null;
        }
    }

}
