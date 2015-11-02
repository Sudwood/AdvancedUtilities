package com.sudwood.advancedutilities.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import com.sudwood.advancedutilities.items.AdvancedUtilitiesItems;
import com.sudwood.advancedutilities.items.ItemQuickPotion;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketDrinkQuickPotion implements IMessage
{
	boolean doDrink;

	public PacketDrinkQuickPotion(){

	}

	public PacketDrinkQuickPotion(boolean doDrink){
		this.doDrink = doDrink;
	}

	@Override
	public void fromBytes(ByteBuf buf) 
	{
		doDrink = buf.readBoolean();
	}

	@Override
	public void toBytes(ByteBuf buf) 
	{
		buf.writeBoolean(doDrink);
	}
	
	public static class Handler implements IMessageHandler<PacketDrinkQuickPotion, IMessage> 
	{
        @Override
        public IMessage onMessage(PacketDrinkQuickPotion message, MessageContext ctx) 
        {
        	EntityPlayer player = ctx.getServerHandler().playerEntity;
        	if(player!=null && message.doDrink)
    		{
    			for(int i = 0; i < player.inventory.mainInventory.length; i++)
    			{
    				if(player.inventory.mainInventory[i]!=null && (player.inventory.mainInventory[i].isItemEqual(new ItemStack(AdvancedUtilitiesItems.quickPotion, 1, 0)) || player.inventory.mainInventory[i].isItemEqual(new ItemStack(AdvancedUtilitiesItems.quickPotion, 1, 1))))
    				{
    					player.addPotionEffect(ItemQuickPotion.getEffectForPotion(player.inventory.mainInventory[i]));
    					player.worldObj.playSoundAtEntity(player, "random.drink", 0.5F, player.worldObj.rand.nextFloat() * 0.1F + 0.9F);
    					if(player.inventory.mainInventory[i].stackSize > 1)
    						player.inventory.mainInventory[i].stackSize--;
    					else
    						player.inventory.mainInventory[i] = null;
    					return null;
    				}
    			}
    		}
        	
            return null; // no response in this case
        }
    }
}
