package com.sudwood.advancedutilities;

import com.sudwood.advancedutilities.blocks.ItemBlockPortaChest;
import com.sudwood.advancedutilities.client.gui.GuiArmorForge;
import com.sudwood.advancedutilities.client.gui.GuiBag;
import com.sudwood.advancedutilities.client.gui.GuiBlockPlacer;
import com.sudwood.advancedutilities.client.gui.GuiBoiler;
import com.sudwood.advancedutilities.client.gui.GuiCompressor;
import com.sudwood.advancedutilities.client.gui.GuiFluidTube;
import com.sudwood.advancedutilities.client.gui.GuiGrowerBlock;
import com.sudwood.advancedutilities.client.gui.GuiItemTube;
import com.sudwood.advancedutilities.client.gui.GuiKiln;
import com.sudwood.advancedutilities.client.gui.GuiMagazine;
import com.sudwood.advancedutilities.client.gui.GuiPnumaticGun;
import com.sudwood.advancedutilities.client.gui.GuiPortaChest;
import com.sudwood.advancedutilities.client.gui.GuiRebreather;
import com.sudwood.advancedutilities.client.gui.GuiRestrictedItemTube;
import com.sudwood.advancedutilities.client.gui.GuiSkills;
import com.sudwood.advancedutilities.client.gui.GuiSmeltry;
import com.sudwood.advancedutilities.client.gui.GuiSteamCrusher;
import com.sudwood.advancedutilities.client.gui.GuiSteamFurnace;
import com.sudwood.advancedutilities.client.gui.GuiSteamQuarry;
import com.sudwood.advancedutilities.client.gui.GuiSteamSmeltry;
import com.sudwood.advancedutilities.client.gui.GuiSteelOven;
import com.sudwood.advancedutilities.client.gui.GuiStoneMill;
import com.sudwood.advancedutilities.client.gui.GuiToolForge;
import com.sudwood.advancedutilities.client.gui.GuiTrash;
import com.sudwood.advancedutilities.client.gui.GuiVoidRing;
import com.sudwood.advancedutilities.client.gui.GuiWoodenCrate;
import com.sudwood.advancedutilities.container.ContainerArmorForge;
import com.sudwood.advancedutilities.container.ContainerBag;
import com.sudwood.advancedutilities.container.ContainerBlockPlacer;
import com.sudwood.advancedutilities.container.ContainerCompressor;
import com.sudwood.advancedutilities.container.ContainerFluidTube;
import com.sudwood.advancedutilities.container.ContainerGrowerBlock;
import com.sudwood.advancedutilities.container.ContainerItemTube;
import com.sudwood.advancedutilities.container.ContainerKiln;
import com.sudwood.advancedutilities.container.ContainerMagazine;
import com.sudwood.advancedutilities.container.ContainerPnumaticGun;
import com.sudwood.advancedutilities.container.ContainerPortaChest;
import com.sudwood.advancedutilities.container.ContainerRebreather;
import com.sudwood.advancedutilities.container.ContainerRestrictedItemTube;
import com.sudwood.advancedutilities.container.ContainerSmeltry;
import com.sudwood.advancedutilities.container.ContainerSteamBoiler;
import com.sudwood.advancedutilities.container.ContainerSteamCrusher;
import com.sudwood.advancedutilities.container.ContainerSteamFurnace;
import com.sudwood.advancedutilities.container.ContainerSteamQuarry;
import com.sudwood.advancedutilities.container.ContainerSteamSmeltry;
import com.sudwood.advancedutilities.container.ContainerSteelOven;
import com.sudwood.advancedutilities.container.ContainerStoneMill;
import com.sudwood.advancedutilities.container.ContainerToolForge;
import com.sudwood.advancedutilities.container.ContainerTrash;
import com.sudwood.advancedutilities.container.ContainerVoidRing;
import com.sudwood.advancedutilities.container.ContainerWoodenCrate;
import com.sudwood.advancedutilities.container.InventoryBag;
import com.sudwood.advancedutilities.container.InventoryItem;
import com.sudwood.advancedutilities.container.InventoryPortaChest;
import com.sudwood.advancedutilities.container.InventoryVoidRing;
import com.sudwood.advancedutilities.container.RebreatherInv;
import com.sudwood.advancedutilities.items.AdvancedUtilitiesItems;
import com.sudwood.advancedutilities.items.ItemPnumaticGun;
import com.sudwood.advancedutilities.tileentity.TileEntityArmorForge;
import com.sudwood.advancedutilities.tileentity.TileEntityBlockPlacer;
import com.sudwood.advancedutilities.tileentity.TileEntityBoiler;
import com.sudwood.advancedutilities.tileentity.TileEntityCompressor;
import com.sudwood.advancedutilities.tileentity.TileEntityFluidTube;
import com.sudwood.advancedutilities.tileentity.TileEntityGrowerBlock;
import com.sudwood.advancedutilities.tileentity.TileEntityItemTube;
import com.sudwood.advancedutilities.tileentity.TileEntityKiln;
import com.sudwood.advancedutilities.tileentity.TileEntityPortaChest;
import com.sudwood.advancedutilities.tileentity.TileEntityRestrictedItemTube;
import com.sudwood.advancedutilities.tileentity.TileEntitySmeltry;
import com.sudwood.advancedutilities.tileentity.TileEntitySteamCrusher;
import com.sudwood.advancedutilities.tileentity.TileEntitySteamFurnace;
import com.sudwood.advancedutilities.tileentity.TileEntitySteamQuarry;
import com.sudwood.advancedutilities.tileentity.TileEntitySteamSmeltry;
import com.sudwood.advancedutilities.tileentity.TileEntitySteelController;
import com.sudwood.advancedutilities.tileentity.TileEntityStoneMill;
import com.sudwood.advancedutilities.tileentity.TileEntityToolForge;
import com.sudwood.advancedutilities.tileentity.TileEntityTrash;
import com.sudwood.advancedutilities.tileentity.TileEntityWoodenCrate;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler
{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) 
	{
		switch(ID)
		{
			case AdvancedUtilities.kilnGui:
				TileEntity tile = world.getTileEntity(x, y, z);
				if(tile instanceof TileEntityKiln)
				{
					return new ContainerKiln(player.inventory, (TileEntityKiln)tile);
				}
				
			case AdvancedUtilities.smeltryGui:
				TileEntity tile1 = world.getTileEntity(x, y, z);
				if(tile1 instanceof TileEntitySmeltry)
				{
					return new ContainerSmeltry(player.inventory, (TileEntitySmeltry)tile1);
				}
			case AdvancedUtilities.toolForgeGui:
				TileEntity tile11 = world.getTileEntity(x, y, z);
				if(tile11 instanceof TileEntityToolForge)
				{
					return new ContainerToolForge(player.inventory, (TileEntityToolForge)tile11);
				}
			case AdvancedUtilities.armorForgeGui:
				TileEntity tile111 = world.getTileEntity(x, y, z);
				if(tile111 instanceof TileEntityArmorForge)
				{
					return new ContainerArmorForge(player.inventory, (TileEntityArmorForge)tile111);
				}
			case AdvancedUtilities.steamBoilerGui:
				TileEntity tile1111 = world.getTileEntity(x, y, z);
				if(tile1111 instanceof TileEntityBoiler)
				{
					return new ContainerSteamBoiler(player.inventory, (TileEntityBoiler)tile1111);
				}
			case AdvancedUtilities.steamCrusherGui:
				TileEntity tile11111 = world.getTileEntity(x, y, z);
				if(tile11111 instanceof TileEntitySteamCrusher)
				{
					return new ContainerSteamCrusher(player.inventory, (TileEntitySteamCrusher)tile11111);
				}
			case AdvancedUtilities.steamFurnaceGui:
				TileEntity tile2 = world.getTileEntity(x, y, z);
				if(tile2 instanceof TileEntitySteamFurnace)
				{
					return new ContainerSteamFurnace(player.inventory, (TileEntitySteamFurnace)tile2);
				}
			case AdvancedUtilities.steamSmeltryGui:
				TileEntity tile21 = world.getTileEntity(x, y, z);
				if(tile21 instanceof TileEntitySteamSmeltry)
				{
					return new ContainerSteamSmeltry(player.inventory, (TileEntitySteamSmeltry)tile21);
				}
			case AdvancedUtilities.itemTubeGui:
				TileEntity tile211 = world.getTileEntity(x, y, z);
				if(tile211 instanceof TileEntityItemTube)
				{
					return new ContainerItemTube(player.inventory, (TileEntityItemTube)tile211);
				}
			case AdvancedUtilities.pnumaticGunGui:
				if(player.getCurrentEquippedItem().getItem() instanceof ItemPnumaticGun)
				{
					return new ContainerPnumaticGun(player, player.inventory, new InventoryItem(player.getCurrentEquippedItem()));
				}
			case AdvancedUtilities.fluidTubeGui:
				TileEntity tile2111 = world.getTileEntity(x, y, z);
				if(tile2111 instanceof TileEntityFluidTube)
				{
					return new ContainerFluidTube(player.inventory, (TileEntityFluidTube)tile2111);
				}
			case AdvancedUtilities.restrictedItemTubeGui:
				TileEntity tile21111 = world.getTileEntity(x, y, z);
				if(tile21111 instanceof TileEntityRestrictedItemTube)
				{
					return new ContainerRestrictedItemTube(player.inventory, (TileEntityRestrictedItemTube)tile21111);
				}
			case AdvancedUtilities.bulletMagazineGui:
				if(player.getCurrentEquippedItem().getItem() ==AdvancedUtilitiesItems.bulletMagazine)
				{
					return new ContainerMagazine(player, player.inventory, new InventoryItem(player.getCurrentEquippedItem()));
				}
			case AdvancedUtilities.bagGui:
				if(player.getCurrentEquippedItem().getItem() == AdvancedUtilitiesItems.bag)
				{
					return new ContainerBag(player, player.inventory, new InventoryBag(player.getCurrentEquippedItem()));
				}
			case AdvancedUtilities.skillsGui:
				return null;
				
			case AdvancedUtilities.rebreatherGui:
				if(player.getCurrentEquippedItem().getItem() == AdvancedUtilitiesItems.rebreather)
				{
					return new ContainerRebreather(player, player.inventory, new RebreatherInv(player.getCurrentEquippedItem()));
				}
			case AdvancedUtilities.steelOvenGui:
				TileEntity tile211111 = world.getTileEntity(x, y, z);
				if(tile211111 instanceof TileEntitySteelController)
				{
					return new ContainerSteelOven(player.inventory, (TileEntitySteelController)tile211111);
				}
			case AdvancedUtilities.steamQuaryGui:
				TileEntity tile221111 = world.getTileEntity(x, y, z);
				if(tile221111 instanceof TileEntitySteamQuarry)
				{
					return new ContainerSteamQuarry(player.inventory, (TileEntitySteamQuarry)tile221111);
				}
			case AdvancedUtilities.stoneMillGui:
				TileEntity tile222111 = world.getTileEntity(x, y, z);
				if(tile222111 instanceof TileEntityStoneMill)
				{
					return new ContainerStoneMill(player.inventory, (TileEntityStoneMill)tile222111);
				}
			case AdvancedUtilities.compressorGui:
				TileEntity tile222211 = world.getTileEntity(x, y, z);
				if(tile222211 instanceof TileEntityCompressor)
				{
					return new ContainerCompressor(player.inventory, (TileEntityCompressor)tile222211);
				}
			case AdvancedUtilities.woodenCrateGui:
				TileEntity tile222221 = world.getTileEntity(x, y, z);
				if(tile222221 instanceof TileEntityWoodenCrate)
				{
					return new ContainerWoodenCrate(player.inventory, (TileEntityWoodenCrate)tile222221);
				}
			case AdvancedUtilities.trashGui:
				TileEntity tile222222 = world.getTileEntity(x, y, z);
				if(tile222222 instanceof TileEntityTrash)
				{
					return new ContainerTrash(player.inventory, (TileEntityTrash)tile222222);
				}
			case AdvancedUtilities.blockPlacerGui:
				TileEntity tile3 = world.getTileEntity(x, y, z);
				if(tile3 instanceof TileEntityBlockPlacer)
				{
					return new ContainerBlockPlacer(player.inventory, (TileEntityBlockPlacer)tile3);
				}
			case AdvancedUtilities.blockGrowerGui:
				TileEntity tile31 = world.getTileEntity(x, y, z);
				if(tile31 instanceof TileEntityGrowerBlock)
				{
					return new ContainerGrowerBlock(player.inventory, (TileEntityGrowerBlock)tile31);
				}
			case AdvancedUtilities.portaChestBlockGui:
				TileEntity tile311 = world.getTileEntity(x, y, z);
				if(tile311 instanceof TileEntityPortaChest)
				{
					return new ContainerPortaChest(player, player.inventory, (TileEntityPortaChest)tile311, true);
				}
			case AdvancedUtilities.portaChestItemGui:
				if(player.getCurrentEquippedItem().getItem() instanceof ItemBlockPortaChest)
				{
					return new ContainerPortaChest(player, player.inventory, new InventoryPortaChest(player.getCurrentEquippedItem()), false);
				}
			case AdvancedUtilities.voidRingGui:
				if(player.getCurrentEquippedItem().getItem() == AdvancedUtilitiesItems.voidRing)
				{
					return new ContainerVoidRing(player.inventory, new InventoryVoidRing(player.getCurrentEquippedItem()));
				}
				
			default: return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) 
	{
		switch(ID)
		{
			case AdvancedUtilities.kilnGui:
				TileEntity tile = world.getTileEntity(x, y, z);
				if(tile instanceof TileEntityKiln)
				{
					return new GuiKiln(player.inventory, (TileEntityKiln)tile);
				}
			case AdvancedUtilities.smeltryGui:
				TileEntity tile1 = world.getTileEntity(x, y, z);
				if(tile1 instanceof TileEntitySmeltry)
				{
					return new GuiSmeltry(player.inventory, (TileEntitySmeltry)tile1);
				}
			case AdvancedUtilities.toolForgeGui:
				TileEntity tile11 = world.getTileEntity(x, y, z);
				if(tile11 instanceof TileEntityToolForge)
				{
					return new GuiToolForge(player.inventory, (TileEntityToolForge)tile11);
				}
			case AdvancedUtilities.armorForgeGui:
				TileEntity tile111 = world.getTileEntity(x, y, z);
				if(tile111 instanceof TileEntityArmorForge)
				{
					return new GuiArmorForge(player.inventory, (TileEntityArmorForge)tile111);
				}
			case AdvancedUtilities.steamBoilerGui:
				TileEntity tile1111 = world.getTileEntity(x, y, z);
				if(tile1111 instanceof TileEntityBoiler)
				{
					return new GuiBoiler(player.inventory, (TileEntityBoiler)tile1111);
				}
			case AdvancedUtilities.steamCrusherGui:
				TileEntity tile11111 = world.getTileEntity(x, y, z);
				if(tile11111 instanceof TileEntitySteamCrusher)
				{
					return new GuiSteamCrusher(player.inventory, (TileEntitySteamCrusher)tile11111);
				}
			case AdvancedUtilities.steamFurnaceGui:
				TileEntity tile2 = world.getTileEntity(x, y, z);
				if(tile2 instanceof TileEntitySteamFurnace)
				{
					return new GuiSteamFurnace(player.inventory, (TileEntitySteamFurnace)tile2);
				}
			case AdvancedUtilities.steamSmeltryGui:
				TileEntity tile21 = world.getTileEntity(x, y, z);
				if(tile21 instanceof TileEntitySteamSmeltry)
				{
					return new GuiSteamSmeltry(player.inventory, (TileEntitySteamSmeltry)tile21);
				}
			case AdvancedUtilities.itemTubeGui:
				TileEntity tile211 = world.getTileEntity(x, y, z);
				if(tile211 instanceof TileEntityItemTube)
				{
					return new GuiItemTube(player.inventory, (TileEntityItemTube)tile211);
				}
			case AdvancedUtilities.pnumaticGunGui:
				if(player.getCurrentEquippedItem().getItem() instanceof ItemPnumaticGun)
				{
					return new GuiPnumaticGun(new ContainerPnumaticGun(player, player.inventory, new InventoryItem(player.getCurrentEquippedItem())), player.getCurrentEquippedItem());
				}
			case AdvancedUtilities.fluidTubeGui:
				TileEntity tile2111 = world.getTileEntity(x, y, z);
				if(tile2111 instanceof TileEntityFluidTube)
				{
					return new GuiFluidTube(player.inventory, (TileEntityFluidTube)tile2111);
				}
			case AdvancedUtilities.restrictedItemTubeGui:
				TileEntity tile21111 = world.getTileEntity(x, y, z);
				if(tile21111 instanceof TileEntityRestrictedItemTube)
				{
					return new GuiRestrictedItemTube(player.inventory, (TileEntityRestrictedItemTube)tile21111);
				}
			case AdvancedUtilities.bulletMagazineGui:
				if(player.getCurrentEquippedItem().getItem() == AdvancedUtilitiesItems.bulletMagazine)
				{
					return new GuiMagazine(new ContainerMagazine(player, player.inventory, new InventoryItem(player.getCurrentEquippedItem())), player.getCurrentEquippedItem());
				}
			case AdvancedUtilities.bagGui:
				if(player.getCurrentEquippedItem().getItem() == AdvancedUtilitiesItems.bag)
				{
					return new GuiBag(new ContainerBag(player, player.inventory, new InventoryBag(player.getCurrentEquippedItem())), player.getCurrentEquippedItem());
				}
			case AdvancedUtilities.skillsGui:
				return new GuiSkills(Minecraft.getMinecraft());
			case AdvancedUtilities.rebreatherGui:
				if(player.getCurrentEquippedItem().getItem() == AdvancedUtilitiesItems.rebreather)
				{
					return new GuiRebreather(new ContainerRebreather(player, player.inventory, new RebreatherInv(player.getCurrentEquippedItem())), player.getCurrentEquippedItem());
				}
			case AdvancedUtilities.steelOvenGui:
				TileEntity tile211111 = world.getTileEntity(x, y, z);
				if(tile211111 instanceof TileEntitySteelController)
				{
					return new GuiSteelOven(player.inventory, (TileEntitySteelController)tile211111);
				}
			case AdvancedUtilities.steamQuaryGui:
				TileEntity tile221111 = world.getTileEntity(x, y, z);
				if(tile221111 instanceof TileEntitySteamQuarry)
				{
					return new GuiSteamQuarry(player.inventory, (TileEntitySteamQuarry)tile221111);
				}
			case AdvancedUtilities.stoneMillGui:
				TileEntity tile222111 = world.getTileEntity(x, y, z);
				if(tile222111 instanceof TileEntityStoneMill)
				{
					return new GuiStoneMill(player.inventory, (TileEntityStoneMill)tile222111);
				}
			case AdvancedUtilities.compressorGui:
				TileEntity tile222211 = world.getTileEntity(x, y, z);
				if(tile222211 instanceof TileEntityCompressor)
				{
					return new GuiCompressor(player.inventory, (TileEntityCompressor)tile222211);
				}
			case AdvancedUtilities.woodenCrateGui:
				TileEntity tile222221 = world.getTileEntity(x, y, z);
				if(tile222221 instanceof TileEntityWoodenCrate)
				{
					return new GuiWoodenCrate(player.inventory, (TileEntityWoodenCrate)tile222221);
				}
			case AdvancedUtilities.trashGui:
				TileEntity tile222222 = world.getTileEntity(x, y, z);
				if(tile222222 instanceof TileEntityTrash)
				{
					return new GuiTrash(player.inventory, (TileEntityTrash)tile222222);
				}
			case AdvancedUtilities.blockPlacerGui:
				TileEntity tile3 = world.getTileEntity(x, y, z);
				if(tile3 instanceof TileEntityBlockPlacer)
				{
					return new GuiBlockPlacer(player.inventory, (TileEntityBlockPlacer)tile3);
				}
			case AdvancedUtilities.blockGrowerGui:
				TileEntity tile31 = world.getTileEntity(x, y, z);
				if(tile31 instanceof TileEntityGrowerBlock)
				{
					return new GuiGrowerBlock(player.inventory, (TileEntityGrowerBlock)tile31);
				}
			case AdvancedUtilities.portaChestBlockGui:
				TileEntity tile311 = world.getTileEntity(x, y, z);
				if(tile311 instanceof TileEntityPortaChest)
				{
					return new GuiPortaChest(new ContainerPortaChest(player, player.inventory, (TileEntityPortaChest)tile311, true));
				}
			case AdvancedUtilities.portaChestItemGui:
				if(player.getCurrentEquippedItem().getItem() instanceof ItemBlockPortaChest)
				{
					return new GuiPortaChest(new ContainerPortaChest(player, player.inventory, new InventoryPortaChest(player.getCurrentEquippedItem()), false));
				}
			case AdvancedUtilities.voidRingGui:
				if(player.getCurrentEquippedItem().getItem() == AdvancedUtilitiesItems.voidRing)
				{
					return new GuiVoidRing(new ContainerVoidRing(player.inventory, new InventoryVoidRing(player.getCurrentEquippedItem())));
				}
			default: return null;
		}
	}

}
