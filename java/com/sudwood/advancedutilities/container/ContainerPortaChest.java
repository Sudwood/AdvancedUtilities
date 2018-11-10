package com.sudwood.advancedutilities.container;

import com.sudwood.advancedutilities.tileentity.TileEntityPortaChest;

import invtweaks.api.container.ChestContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;


@ChestContainer(isLargeChest = true)
public class ContainerPortaChest extends Container
{
	private TileEntityPortaChest tile;
	private InventoryPortaChest item;
	private int sizeInventory;
	private boolean isBlock;
	public int chestType;
	public String name;
	public ContainerPortaChest(EntityPlayer player, InventoryPlayer pInv, IInventory inventory, boolean isB)
	{
		isBlock = isB;
		if(isBlock)
		{
			int slot= 0;
			int x = 8;
			int y = 14;
			int px = 8;
			int py = 84;
			int hotx = 8;
			int hoty = 142;
			int playerslot =9;
			tile = (TileEntityPortaChest)inventory;
			name = tile.getInventoryName();
			chestType = tile.getType();
			sizeInventory = tile.getSizeInventory();
			switch(tile.getType())
			{
			case TileEntityPortaChest.WOOD:
				for(int i =0; i < 3;i++)
				{
					for(int ix = 0; ix<9;ix++)
					{
						this.addSlotToContainer(new Slot(tile, slot, x + ix * 18, y + i * 18));
						slot++;
					}
				}
				//playerinv
				for (int j = 0; j < 3; ++j)
				{
					for (int l = 0; l < 9; ++l)
					{
						this.addSlotToContainer(new Slot(pInv, playerslot, px + l * 18, py + j * 18));
						playerslot++;
					}
				}
				
				// actionbar
				for (int k = 0; k < 9; ++k)
				{
					this.addSlotToContainer(new Slot(pInv, k, hotx + k * 18, hoty));
				}
			break;
			case TileEntityPortaChest.BRONZE:
					slot= 0;
					x = 12;
					y = 8;
					px = 12;
					py = 120;
					hotx = 12;
					hoty = 178;
					playerslot =9;
					for(int i =0; i < 6;i++)
					{
						for(int ix = 0; ix<9;ix++)
						{
							this.addSlotToContainer(new Slot(tile, slot, x + ix * 18, y + i * 18));
							slot++;
						}
					}
					//playerinv
					for (int j = 0; j < 3; ++j)
					{
						for (int l = 0; l < 9; ++l)
						{
							this.addSlotToContainer(new Slot(pInv, playerslot, px + l * 18, py + j * 18));
							playerslot++;
						}
					}
					
					// actionbar
					for (int k = 0; k < 9; ++k)
					{
						this.addSlotToContainer(new Slot(pInv, k, hotx + k * 18, hoty));
					}
			break;
			case TileEntityPortaChest.GOLD:
				slot= 0;
				x = 12;
				y = 8;
				px = 12;
				py = 174;
				hotx = 12;
				hoty = 232;
				playerslot =9;
				for(int i =0; i < 9;i++)
				{
					for(int ix = 0; ix<9;ix++)
					{
						this.addSlotToContainer(new Slot(tile, slot, x + ix * 18, y + i * 18));
						slot++;
					}
				}
				//playerinv
				for (int j = 0; j < 3; ++j)
				{
					for (int l = 0; l < 9; ++l)
					{
						this.addSlotToContainer(new Slot(pInv, playerslot,px + l * 18, py + j * 18));
						playerslot++;
					}
				}
				
				// actionbar
				for (int k = 0; k < 9; ++k)
				{
					this.addSlotToContainer(new Slot(pInv, k, hotx + k * 18, hoty));
				}
			break;
			case TileEntityPortaChest.DIAMOND:
				slot= 0;
				x = 12;
				y = 8;
				px = 39;
				py = 174;
				hotx = 39;
				hoty = 232;
				playerslot =9;
				for(int i =0; i < 9;i++)
				{
					for(int ix = 0; ix<12;ix++)
					{
						this.addSlotToContainer(new Slot(tile, slot, x + ix * 18, y + i * 18));
						slot++;
					}
				}
				//playerinv
				for (int j = 0; j < 3; ++j)
				{
					for (int l = 0; l < 9; ++l)
					{
						this.addSlotToContainer(new Slot(pInv,playerslot, px + l * 18, py + j * 18));
						playerslot++;
					}
				}
				
				// actionbar
				for (int k = 0; k < 9; ++k)
				{
					this.addSlotToContainer(new Slot(pInv, k, hotx + k * 18, hoty));
				}
			break;
			case TileEntityPortaChest.PLATINUM:
				slot= 0;
				x = 12;
				y = 8;
				px = 63;
				py = 174;
				hotx = 63;
				hoty = 232;
				playerslot =9;
				for(int i =0; i < 9;i++)
				{
					for(int ix = 0; ix<15;ix++)
					{
						this.addSlotToContainer(new Slot(tile, slot, x + ix * 18, y + i * 18));
						slot++;
					}
				}
				
				//playerinv
				for (int j = 0; j < 3; ++j)
				{
					for (int l = 0; l < 9; ++l)
					{
						this.addSlotToContainer(new Slot(pInv, playerslot, px + l * 18, py + j * 18));
						playerslot++;
					}
				}
				
				// actionbar
				for (int k = 0; k < 9; ++k)
				{
					this.addSlotToContainer(new Slot(pInv, k, hotx + k * 18, hoty));
				}
			break;
			}
		}
		if(!isBlock)
		{
			item = (InventoryPortaChest)inventory;
			sizeInventory = item.getSizeInventory();
			name = item.getInventoryName();
			chestType = item.chestType;
			int slot= 0;
			int x = 8;
			int y = 14;
			int px = 8;
			int py = 84;
			int hotx = 8;
			int hoty = 142;
			int playerslot =9;
			switch(item.chestType)
			{
			case InventoryPortaChest.WOOD:
				for(int i =0; i < 3;i++)
				{
					for(int ix = 0; ix<9;ix++)
					{
						this.addSlotToContainer(new Slot(item, slot, x + ix * 18, y + i * 18));
						slot++;
					}
				}
				//playerinv
				for (int j = 0; j < 3; ++j)
				{
					for (int l = 0; l < 9; ++l)
					{
						this.addSlotToContainer(new Slot(pInv, playerslot, px + l * 18, py + j * 18));
						playerslot++;
					}
				}
				
				// actionbar
				for (int k = 0; k < 9; ++k)
				{
					this.addSlotToContainer(new Slot(pInv, k, hotx + k * 18, hoty));
				}
			break;
			case InventoryPortaChest.BRONZE:
					slot= 0;
					x = 12;
					y = 8;
					px = 12;
					py = 120;
					hotx = 12;
					hoty = 178;
					playerslot =9;
					for(int i =0; i < 6;i++)
					{
						for(int ix = 0; ix<9;ix++)
						{
							this.addSlotToContainer(new Slot(item, slot, x + ix * 18, y + i * 18));
							slot++;
						}
					}
					//playerinv
					for (int j = 0; j < 3; ++j)
					{
						for (int l = 0; l < 9; ++l)
						{
							this.addSlotToContainer(new Slot(pInv, playerslot, px + l * 18, py + j * 18));
							playerslot++;
						}
					}
					
					// actionbar
					for (int k = 0; k < 9; ++k)
					{
						this.addSlotToContainer(new Slot(pInv, k, hotx + k * 18, hoty));
					}
			break;
			case InventoryPortaChest.GOLD:
				slot= 0;
				x = 12;
				y = 8;
				px = 12;
				py = 174;
				hotx = 12;
				hoty = 232;
				playerslot =9;
				for(int i =0; i < 9;i++)
				{
					for(int ix = 0; ix<9;ix++)
					{
						this.addSlotToContainer(new Slot(item, slot, x + ix * 18, y + i * 18));
						slot++;
					}
				}
				//playerinv
				for (int j = 0; j < 3; ++j)
				{
					for (int l = 0; l < 9; ++l)
					{
						this.addSlotToContainer(new Slot(pInv, playerslot, px + l * 18, py + j * 18));
						playerslot++;
					}
				}
				
				// actionbar
				for (int k = 0; k < 9; ++k)
				{
					this.addSlotToContainer(new Slot(pInv, k, hotx + k * 18, hoty));
				}
			break;
			case InventoryPortaChest.DIAMOND:
				slot= 0;
				x = 12;
				y = 8;
				px = 39;
				py = 174;
				hotx = 39;
				hoty = 232;
				playerslot =9;
				for(int i =0; i < 9;i++)
				{
					for(int ix = 0; ix<12;ix++)
					{
						this.addSlotToContainer(new Slot(item, slot, x + ix * 18, y + i * 18));
						slot++;
					}
				}
				//playerinv
				for (int j = 0; j < 3; ++j)
				{
					for (int l = 0; l < 9; ++l)
					{
						this.addSlotToContainer(new Slot(pInv, playerslot, px + l * 18, py + j * 18));
						playerslot++;
					}
				}
				
				// actionbar
				for (int k = 0; k < 9; ++k)
				{
					this.addSlotToContainer(new Slot(pInv, k, hotx + k * 18, hoty));
				}
			break;
			case InventoryPortaChest.PLATINUM:
				slot= 0;
				x = 12;
				y = 8;
				px = 63;
				py = 174;
				hotx = 63;
				hoty = 232;
				playerslot =9;
				for(int i =0; i < 9;i++)
				{
					for(int ix = 0; ix<15;ix++)
					{
						this.addSlotToContainer(new Slot(item, slot, x + ix * 18, y + i * 18));
						slot++;
					}
				}
				
				//playerinv
				for (int j = 0; j < 3; ++j)
				{
					for (int l = 0; l < 9; ++l)
					{
						this.addSlotToContainer(new Slot(pInv, playerslot,px + l * 18, py + j * 18));
						playerslot++;
					}
				}
				
				// actionbar
				for (int k = 0; k < 9; ++k)
				{
					this.addSlotToContainer(new Slot(pInv, k, hotx + k * 18, hoty));
				}
			break;
			}
		}
	}
	@Override
	public boolean canInteractWith(EntityPlayer player) 
	{
		return true;
	}
	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int i)
	{
		 ItemStack itemstack = null;
         Slot slot = (Slot) inventorySlots.get(i);
         if (slot != null && slot.getHasStack())
         {
             if(isBlock)
             {
            	 ItemStack itemstack1 = slot.getStack();
                 itemstack = itemstack1.copy();
                 if (i < sizeInventory)
                 {
                     if (!mergeItemStack(itemstack1, sizeInventory, inventorySlots.size(), true))
                     {
                         return null;
                     }
                 }
                 else if (!tile.isItemValidForSlot(i, slot.getStack()))
                 {
                     return null;
                 }
                 else if (!mergeItemStack(itemstack1, 0, sizeInventory, false))
                 {
                     return null;
                 }
                 if (itemstack1.stackSize == 0)
                 {
                     slot.putStack(null);
                 }
                 else
                 {
                     slot.onSlotChanged();
                 }
             }
             if(!isBlock)
             {
            	 ItemStack itemstack1 = slot.getStack();
                 itemstack = itemstack1.copy();
                 if (i < sizeInventory)
                 {
                     if (!mergeItemStack(itemstack1, sizeInventory, inventorySlots.size(), true))
                     {
                         return null;
                     }
                 }
                 else if (!item.isItemValidForSlot(i, slot.getStack()))
                 {
                     return null;
                 }
                 else if (!mergeItemStack(itemstack1, 0, sizeInventory, false))
                 {
                     return null;
                 }
                 if (itemstack1.stackSize == 0)
                 {
                     slot.putStack(null);
                 }
                 else
                 {
                     slot.onSlotChanged();
                 }
             }
         }
         return itemstack;
	}
		
	@Override
	public ItemStack slotClick(int slot, int button, int flag, EntityPlayer player) 
	{
		// this will prevent the player from interacting with the item that opened the inventory:
		if (!isBlock&&slot >= 0 && getSlot(slot) != null && getSlot(slot).getStack() == player.getHeldItem() &&player.getHeldItem()!=null) 
		{
			return null;
		}
		return super.slotClick(slot, button, flag, player);
	}
	@Override
	public void onContainerClosed(EntityPlayer player)
    {
			if(isBlock)
			{
				tile.closeInventory();
			}
			if(!isBlock)
			{
				item.closeInventory();
			}
		super.onContainerClosed(player);
    }
	  @ChestContainer.RowSizeCallback
	    public int getNumColumns() 
	  {
	        switch(chestType)
	        {
	        case TileEntityPortaChest.WOOD:
	        	return 9;
	        case TileEntityPortaChest.BRONZE:
	        	return 9;
	        case TileEntityPortaChest.GOLD:
	        	return 9;
	        case TileEntityPortaChest.DIAMOND:
	        	return 12;
	        case TileEntityPortaChest.PLATINUM:
	        	return 15;
	        }
	        return 0;
	}
}
