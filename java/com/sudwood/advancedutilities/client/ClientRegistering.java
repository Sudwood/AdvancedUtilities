package com.sudwood.advancedutilities.client;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.MinecraftForgeClient;

import com.sudwood.advancedutilities.blocks.AdvancedUtilitiesBlocks;
import com.sudwood.advancedutilities.client.models.ModelBellows;
import com.sudwood.advancedutilities.client.models.ModelBoiler;
import com.sudwood.advancedutilities.client.models.ModelGun;
import com.sudwood.advancedutilities.client.models.ModelJackHammer;
import com.sudwood.advancedutilities.client.models.ModelJetpack;
import com.sudwood.advancedutilities.client.models.ModelJetpackArmor;
import com.sudwood.advancedutilities.client.models.ModelKilnHeld;
import com.sudwood.advancedutilities.client.models.ModelSmeltryHeld;
import com.sudwood.advancedutilities.client.models.ModelSteamCharger;
import com.sudwood.advancedutilities.client.models.ModelSteamCompressor;
import com.sudwood.advancedutilities.client.models.ModelSteamCrusher;
import com.sudwood.advancedutilities.client.models.ModelSteamFurnace;
import com.sudwood.advancedutilities.client.models.ModelSteamSmeltry;
import com.sudwood.advancedutilities.client.models.ModelToolForge;
import com.sudwood.advancedutilities.client.models.ModelTube;
import com.sudwood.advancedutilities.client.renders.RenderArmorForge;
import com.sudwood.advancedutilities.client.renders.RenderBellows;
import com.sudwood.advancedutilities.client.renders.RenderBoiler;
import com.sudwood.advancedutilities.client.renders.RenderBullet;
import com.sudwood.advancedutilities.client.renders.RenderFluidTube;
import com.sudwood.advancedutilities.client.renders.RenderItem;
import com.sudwood.advancedutilities.client.renders.RenderItemTube;
import com.sudwood.advancedutilities.client.renders.RenderJackHammer;
import com.sudwood.advancedutilities.client.renders.RenderJetpackItem;
import com.sudwood.advancedutilities.client.renders.RenderKiln;
import com.sudwood.advancedutilities.client.renders.RenderRestrictedItemTube;
import com.sudwood.advancedutilities.client.renders.RenderSmeltry;
import com.sudwood.advancedutilities.client.renders.RenderSpeedyMinecart;
import com.sudwood.advancedutilities.client.renders.RenderSteamCharger;
import com.sudwood.advancedutilities.client.renders.RenderSteamCompressor;
import com.sudwood.advancedutilities.client.renders.RenderSteamCrusher;
import com.sudwood.advancedutilities.client.renders.RenderSteamFurnace;
import com.sudwood.advancedutilities.client.renders.RenderSteamSmeltry;
import com.sudwood.advancedutilities.client.renders.RenderTileEntityBase;
import com.sudwood.advancedutilities.client.renders.RenderToolForge;
import com.sudwood.advancedutilities.entity.EntityBullet;
import com.sudwood.advancedutilities.entity.EntitySpeedyChestcart;
import com.sudwood.advancedutilities.entity.EntitySpeedyMinecart;
import com.sudwood.advancedutilities.items.AdvancedUtilitiesItems;
import com.sudwood.advancedutilities.tileentity.TileEntityArmorForge;
import com.sudwood.advancedutilities.tileentity.TileEntityBellows;
import com.sudwood.advancedutilities.tileentity.TileEntityBoiler;
import com.sudwood.advancedutilities.tileentity.TileEntityFluidTube;
import com.sudwood.advancedutilities.tileentity.TileEntityItemTube;
import com.sudwood.advancedutilities.tileentity.TileEntityKiln;
import com.sudwood.advancedutilities.tileentity.TileEntityRestrictedItemTube;
import com.sudwood.advancedutilities.tileentity.TileEntitySmeltry;
import com.sudwood.advancedutilities.tileentity.TileEntitySteamCharger;
import com.sudwood.advancedutilities.tileentity.TileEntitySteamCompressor;
import com.sudwood.advancedutilities.tileentity.TileEntitySteamCrusher;
import com.sudwood.advancedutilities.tileentity.TileEntitySteamFurnace;
import com.sudwood.advancedutilities.tileentity.TileEntitySteamSmeltry;
import com.sudwood.advancedutilities.tileentity.TileEntityToolForge;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;

public class ClientRegistering 
{
	public static int kilnId;
	public static int smeltryId;
	public static int toolForgeId;
	public static int boilerId;
	public static int steamCrusherId;
	public static int steamFurnaceId;
	public static int steamSmeltryId;
	public static int bellowsId;
	public static int steamCompressorId;
	public static int tubeId;
	public static int steamChargerId;
	
	public static final ModelJetpackArmor jetpackArmor = new ModelJetpackArmor(1.0F);
	
	public static void registerRendering()
	{
		kilnId = RenderingRegistry.getNextAvailableRenderId();
		smeltryId = RenderingRegistry.getNextAvailableRenderId();
		toolForgeId = RenderingRegistry.getNextAvailableRenderId();
		boilerId = RenderingRegistry.getNextAvailableRenderId();
		steamCrusherId = RenderingRegistry.getNextAvailableRenderId();
		steamFurnaceId = RenderingRegistry.getNextAvailableRenderId();
		steamSmeltryId = RenderingRegistry.getNextAvailableRenderId();
		bellowsId = RenderingRegistry.getNextAvailableRenderId();
		steamCompressorId = RenderingRegistry.getNextAvailableRenderId();
		tubeId = RenderingRegistry.getNextAvailableRenderId();
		steamChargerId = RenderingRegistry.getNextAvailableRenderId();
		
		RenderingRegistry.registerEntityRenderingHandler(EntityBullet.class, new RenderBullet());
		RenderingRegistry.registerEntityRenderingHandler(EntitySpeedyMinecart.class, new RenderSpeedyMinecart());
		RenderingRegistry.registerEntityRenderingHandler(EntitySpeedyChestcart.class, new RenderSpeedyMinecart());
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityKiln.class, new RenderKiln());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(AdvancedUtilitiesBlocks.blockKiln), new RenderTileEntityBase(new ModelKilnHeld(), new ResourceLocation("advancedutilities","textures/blocks/kiln.png")));
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySmeltry.class, new RenderSmeltry());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(AdvancedUtilitiesBlocks.blockSmeltry), new RenderTileEntityBase(new ModelSmeltryHeld(), new ResourceLocation("advancedutilities","textures/blocks/smeltry.png")));
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityToolForge.class, new RenderToolForge());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(AdvancedUtilitiesBlocks.blockToolForge), new RenderTileEntityBase(new ModelToolForge(), new ResourceLocation("advancedutilities","textures/blocks/toolforge.png")));
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityArmorForge.class, new RenderArmorForge());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(AdvancedUtilitiesBlocks.blockArmorForge), new RenderTileEntityBase(new ModelToolForge(), new ResourceLocation("advancedutilities","textures/blocks/armorforge.png")));
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBoiler.class, new RenderBoiler());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(AdvancedUtilitiesBlocks.steamBoiler), new RenderTileEntityBase(new ModelBoiler(), new ResourceLocation("advancedutilities","textures/blocks/boiler.png")));
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySteamCrusher.class, new RenderSteamCrusher());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(AdvancedUtilitiesBlocks.steamCrusher), new RenderTileEntityBase(new ModelSteamCrusher(), new ResourceLocation("advancedutilities","textures/blocks/steamcrusher.png")));
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySteamFurnace.class, new RenderSteamFurnace());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(AdvancedUtilitiesBlocks.steamFurnace), new RenderTileEntityBase(new ModelSteamFurnace(), new ResourceLocation("advancedutilities","textures/blocks/steamfurnace.png")));
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySteamSmeltry.class, new RenderSteamSmeltry());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(AdvancedUtilitiesBlocks.steamSmeltry), new RenderTileEntityBase(new ModelSteamSmeltry(), new ResourceLocation("advancedutilities","textures/blocks/steamsmeltry.png")));

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBellows.class, new RenderBellows());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(AdvancedUtilitiesBlocks.bellows), new RenderTileEntityBase(new ModelBellows(), new ResourceLocation("advancedutilities","textures/blocks/bellows.png")));
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySteamCompressor.class, new RenderSteamCompressor());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(AdvancedUtilitiesBlocks.steamCompressor), new RenderTileEntityBase(new ModelSteamCompressor(), new ResourceLocation("advancedutilities","textures/blocks/steamcompressor.png")));
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityItemTube.class, new RenderItemTube());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(AdvancedUtilitiesBlocks.itemTube), new RenderTileEntityBase(new ModelTube(), new ResourceLocation("advancedutilities","textures/blocks/itemtube.png")));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRestrictedItemTube.class, new RenderRestrictedItemTube());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(AdvancedUtilitiesBlocks.restrictedItemTube), new RenderTileEntityBase(new ModelTube(), new ResourceLocation("advancedutilities","textures/blocks/restricteditemtube.png")));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFluidTube.class, new RenderFluidTube());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(AdvancedUtilitiesBlocks.fluidTube), new RenderTileEntityBase(new ModelTube(), new ResourceLocation("advancedutilities","textures/blocks/fluidtube.png")));
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySteamCharger.class, new RenderSteamCharger());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(AdvancedUtilitiesBlocks.steamCharger), new RenderTileEntityBase(new ModelSteamCharger(), new ResourceLocation("advancedutilities","textures/blocks/steamcharger.png")));
		
		MinecraftForgeClient.registerItemRenderer(AdvancedUtilitiesItems.pnumaticGun, new RenderItem(new ModelGun(), new ResourceLocation("advancedutilities","textures/items/gun.png")));
		MinecraftForgeClient.registerItemRenderer(AdvancedUtilitiesItems.jackHammer, new RenderJackHammer(new ModelJackHammer(), new ResourceLocation("advancedutilities","textures/items/jackhammer.png")));
		MinecraftForgeClient.registerItemRenderer(AdvancedUtilitiesItems.steamJetpack, new RenderJetpackItem(new ModelJetpack(), new ResourceLocation("advancedutilities","textures/items/jetpack.png")));
		
	}
}