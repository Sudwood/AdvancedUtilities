package com.sudwood.advancedutilities.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.sudwood.advancedutilities.items.AdvancedUtilitiesItems;
import com.sudwood.advancedutilities.items.ItemQuickPotion;
import com.sudwood.advancedutilities.tileentity.TileEntityArmorForge;
import com.sudwood.advancedutilities.tileentity.TileEntityToolForge;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketForge implements IMessage
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
	public void fromBytes(ByteBuf buffer) 
	{
		dimID = buffer.readInt();
		x = buffer.readInt();
		y = buffer.readInt();
		z = buffer.readInt();
		type = buffer.readInt();
		
	}

	@Override
	public void toBytes(ByteBuf buffer) 
	{
		buffer.writeInt(dimID);	
		buffer.writeInt(x);	
		buffer.writeInt(y);	
		buffer.writeInt(z);
		buffer.writeInt(type);
	}
	
	public static class Handler implements IMessageHandler<PacketForge, IMessage> {
        
        @Override
        public IMessage onMessage(PacketForge message, MessageContext ctx) 
        {
    		World world = ctx.getServerHandler().playerEntity.worldObj;
    		TileEntity tile = world.getTileEntity(message.x, message.y, message.z);
    		
    		if(message.type == 0) // tool
    		{
    			((TileEntityToolForge)tile).startIsCrafting();
    		}
    		if(message.type == 1) // armor
    		{
    			((TileEntityArmorForge)tile).startIsCrafting();
    		}
        	
            return null; // no response in this case
        }
    }

}
