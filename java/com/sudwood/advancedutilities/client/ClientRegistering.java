package com.sudwood.advancedutilities.client;

import com.sudwood.advancedutilities.AdvancedUtilities;
import com.sudwood.advancedutilities.blocks.AdvancedUtilitiesBlocks;
import com.sudwood.advancedutilities.client.models.ModelBellows;
import com.sudwood.advancedutilities.client.models.ModelBoiler;
import com.sudwood.advancedutilities.client.models.ModelGun;
import com.sudwood.advancedutilities.client.models.ModelJackHammer;
import com.sudwood.advancedutilities.client.models.ModelJetpack;
import com.sudwood.advancedutilities.client.models.ModelJetpackArmor;
import com.sudwood.advancedutilities.client.models.ModelKilnHeld;
import com.sudwood.advancedutilities.client.models.ModelPowerLegs;
import com.sudwood.advancedutilities.client.models.ModelPowerLegsArmor;
import com.sudwood.advancedutilities.client.models.ModelQuarryFrame;
import com.sudwood.advancedutilities.client.models.ModelRebreather;
import com.sudwood.advancedutilities.client.models.ModelRebreatherArmor;
import com.sudwood.advancedutilities.client.models.ModelRunningShoes;
import com.sudwood.advancedutilities.client.models.ModelRunningShoesArmor;
import com.sudwood.advancedutilities.client.models.ModelSmeltryHeld;
import com.sudwood.advancedutilities.client.models.ModelSplitterTube;
import com.sudwood.advancedutilities.client.models.ModelSteamCharger;
import com.sudwood.advancedutilities.client.models.ModelSteamCompressor;
import com.sudwood.advancedutilities.client.models.ModelSteamCrusher;
import com.sudwood.advancedutilities.client.models.ModelSteamFurnace;
import com.sudwood.advancedutilities.client.models.ModelSteamQuarry;
import com.sudwood.advancedutilities.client.models.ModelSteamSmeltry;
import com.sudwood.advancedutilities.client.models.ModelTank;
import com.sudwood.advancedutilities.client.models.ModelTomato;
import com.sudwood.advancedutilities.client.models.ModelToolForge;
import com.sudwood.advancedutilities.client.models.ModelTube;
import com.sudwood.advancedutilities.client.renders.RenderArmorForge;
import com.sudwood.advancedutilities.client.renders.RenderBellows;
import com.sudwood.advancedutilities.client.renders.RenderBoiler;
import com.sudwood.advancedutilities.client.renders.RenderBullet;
import com.sudwood.advancedutilities.client.renders.RenderElevator;
import com.sudwood.advancedutilities.client.renders.RenderFluidTube;
import com.sudwood.advancedutilities.client.renders.RenderItem;
import com.sudwood.advancedutilities.client.renders.RenderItemTube;
import com.sudwood.advancedutilities.client.renders.RenderJackHammer;
import com.sudwood.advancedutilities.client.renders.RenderJetpackItem;
import com.sudwood.advancedutilities.client.renders.RenderKiln;
import com.sudwood.advancedutilities.client.renders.RenderMagnetTube;
import com.sudwood.advancedutilities.client.renders.RenderPortaChest;
import com.sudwood.advancedutilities.client.renders.RenderPortaChestItem;
import com.sudwood.advancedutilities.client.renders.RenderQuarryFrame;
import com.sudwood.advancedutilities.client.renders.RenderRationedItemTube;
import com.sudwood.advancedutilities.client.renders.RenderRebreather;
import com.sudwood.advancedutilities.client.renders.RenderRestrictedItemTube;
import com.sudwood.advancedutilities.client.renders.RenderRunningShoesItem;
import com.sudwood.advancedutilities.client.renders.RenderSmeltry;
import com.sudwood.advancedutilities.client.renders.RenderSpeedyMinecart;
import com.sudwood.advancedutilities.client.renders.RenderSplitterTube;
import com.sudwood.advancedutilities.client.renders.RenderSteamCharger;
import com.sudwood.advancedutilities.client.renders.RenderSteamCompressor;
import com.sudwood.advancedutilities.client.renders.RenderSteamCrusher;
import com.sudwood.advancedutilities.client.renders.RenderSteamFurnace;
import com.sudwood.advancedutilities.client.renders.RenderSteamQuarry;
import com.sudwood.advancedutilities.client.renders.RenderSteamSmeltry;
import com.sudwood.advancedutilities.client.renders.RenderTank;
import com.sudwood.advancedutilities.client.renders.RenderTankItem;
import com.sudwood.advancedutilities.client.renders.RenderTileEntityBase;
import com.sudwood.advancedutilities.client.renders.RenderTomato;
import com.sudwood.advancedutilities.client.renders.RenderTomatoPlant;
import com.sudwood.advancedutilities.client.renders.RenderToolForge;
import com.sudwood.advancedutilities.client.renders.RenderWire;
import com.sudwood.advancedutilities.entity.EntityBullet;
import com.sudwood.advancedutilities.entity.minecart.EntityChunkChestCart;
import com.sudwood.advancedutilities.entity.minecart.EntityChunkLoadingCart;
import com.sudwood.advancedutilities.entity.minecart.EntityChunkTankCart;
import com.sudwood.advancedutilities.entity.minecart.EntitySpeedyChestcart;
import com.sudwood.advancedutilities.entity.minecart.EntitySpeedyChunkChestCart;
import com.sudwood.advancedutilities.entity.minecart.EntitySpeedyChunkLoadCart;
import com.sudwood.advancedutilities.entity.minecart.EntitySpeedyChunkTankCart;
import com.sudwood.advancedutilities.entity.minecart.EntitySpeedyMinecart;
import com.sudwood.advancedutilities.entity.minecart.EntitySpeedyTankCart;
import com.sudwood.advancedutilities.entity.minecart.EntityTankCart;
import com.sudwood.advancedutilities.items.AdvancedUtilitiesItems;
import com.sudwood.advancedutilities.tileentity.TileEntityArmorForge;
import com.sudwood.advancedutilities.tileentity.TileEntityBellows;
import com.sudwood.advancedutilities.tileentity.TileEntityBoiler;
import com.sudwood.advancedutilities.tileentity.TileEntityElevator;
import com.sudwood.advancedutilities.tileentity.TileEntityFluidTube;
import com.sudwood.advancedutilities.tileentity.TileEntityHPBoiler;
import com.sudwood.advancedutilities.tileentity.TileEntityItemTube;
import com.sudwood.advancedutilities.tileentity.TileEntityKiln;
import com.sudwood.advancedutilities.tileentity.TileEntityMagnetTube;
import com.sudwood.advancedutilities.tileentity.TileEntityPortaChest;
import com.sudwood.advancedutilities.tileentity.TileEntityQuarryFrame;
import com.sudwood.advancedutilities.tileentity.TileEntityRationedItemTube;
import com.sudwood.advancedutilities.tileentity.TileEntityRestrictedItemTube;
import com.sudwood.advancedutilities.tileentity.TileEntitySmeltry;
import com.sudwood.advancedutilities.tileentity.TileEntitySplitterFluidTube;
import com.sudwood.advancedutilities.tileentity.TileEntitySplitterItemTube;
import com.sudwood.advancedutilities.tileentity.TileEntitySteamCharger;
import com.sudwood.advancedutilities.tileentity.TileEntitySteamCompressor;
import com.sudwood.advancedutilities.tileentity.TileEntitySteamCrusher;
import com.sudwood.advancedutilities.tileentity.TileEntitySteamFurnace;
import com.sudwood.advancedutilities.tileentity.TileEntitySteamQuarry;
import com.sudwood.advancedutilities.tileentity.TileEntitySteamSmeltry;
import com.sudwood.advancedutilities.tileentity.TileEntityTank;
import com.sudwood.advancedutilities.tileentity.TileEntityTomatoPlant;
import com.sudwood.advancedutilities.tileentity.TileEntityToolForge;
import com.sudwood.advancedutilities.tileentity.TileEntityWire;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.client.renderer.entity.RenderMinecart;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.MinecraftForgeClient;

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
	public static KeyHandler keyhandle;
	public static int tankId;
	public static int tomatoPlantId;
	public static int splitterTubeId;
	public static int steamQuarryId;
	public static int quarryFrameId;
	public static int wireId;
	public static int portaChestId;
	public static int elevatorId;
	
	public static final ModelJetpackArmor jetpackArmor = new ModelJetpackArmor(1.0F);
	public static final ModelRebreatherArmor rebreatherArmor = new ModelRebreatherArmor(1.0F);
	public static final ModelRunningShoesArmor shoesArmor = new ModelRunningShoesArmor(1.3F);
	public static final ModelPowerLegsArmor legsArmor = new ModelPowerLegsArmor(1.0F);
	
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
		tankId = RenderingRegistry.getNextAvailableRenderId();
		tomatoPlantId = RenderingRegistry.getNextAvailableRenderId();
		splitterTubeId = RenderingRegistry.getNextAvailableRenderId();
		steamQuarryId = RenderingRegistry.getNextAvailableRenderId();
		quarryFrameId = RenderingRegistry.getNextAvailableRenderId();
		wireId = RenderingRegistry.getNextAvailableRenderId();
		portaChestId = RenderingRegistry.getNextAvailableRenderId();
		elevatorId = RenderingRegistry.getNextAvailableRenderId();
		keyhandle = new KeyHandler();
		
		RenderingRegistry.registerEntityRenderingHandler(EntityBullet.class, new RenderBullet());
		RenderingRegistry.registerEntityRenderingHandler(EntitySpeedyMinecart.class, new RenderSpeedyMinecart());
		RenderingRegistry.registerEntityRenderingHandler(EntitySpeedyChestcart.class, new RenderSpeedyMinecart());
		RenderingRegistry.registerEntityRenderingHandler(EntityChunkLoadingCart.class, new RenderMinecart());
		RenderingRegistry.registerEntityRenderingHandler(EntitySpeedyChunkLoadCart.class, new RenderSpeedyMinecart());
		RenderingRegistry.registerEntityRenderingHandler(EntitySpeedyChunkChestCart.class, new RenderSpeedyMinecart());
		RenderingRegistry.registerEntityRenderingHandler(EntityChunkChestCart.class, new RenderMinecart());
		RenderingRegistry.registerEntityRenderingHandler(EntityTankCart.class, new RenderMinecart());
		RenderingRegistry.registerEntityRenderingHandler(EntitySpeedyTankCart.class, new RenderSpeedyMinecart());
		RenderingRegistry.registerEntityRenderingHandler(EntityChunkTankCart.class, new RenderMinecart());
		RenderingRegistry.registerEntityRenderingHandler(EntitySpeedyChunkTankCart.class, new RenderSpeedyMinecart());
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityKiln.class, new RenderKiln());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(AdvancedUtilitiesBlocks.blockKiln), new RenderTileEntityBase(new ModelKilnHeld(), new ResourceLocation("advancedutilities","textures/blocks/kiln.png")));
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySmeltry.class, new RenderSmeltry());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(AdvancedUtilitiesBlocks.blockSmeltry), new RenderTileEntityBase(new ModelSmeltryHeld(), new ResourceLocation("advancedutilities","textures/blocks/smeltry.png")));
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityToolForge.class, new RenderToolForge());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(AdvancedUtilitiesBlocks.blockToolForge), new RenderTileEntityBase(new ModelToolForge(), new ResourceLocation("advancedutilities","textures/blocks/toolforge.png")));
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityArmorForge.class, new RenderArmorForge());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(AdvancedUtilitiesBlocks.blockArmorForge), new RenderTileEntityBase(new ModelToolForge(), new ResourceLocation("advancedutilities","textures/blocks/armorforge.png")));
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBoiler.class, new RenderBoiler(0));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(AdvancedUtilitiesBlocks.steamBoiler), new RenderTileEntityBase(new ModelBoiler(), new ResourceLocation("advancedutilities","textures/blocks/boiler.png")));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHPBoiler.class, new RenderBoiler(1));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(AdvancedUtilitiesBlocks.hpBoiler), new RenderTileEntityBase(new ModelBoiler(), new ResourceLocation("advancedutilities","textures/blocks/hpboiler.png")));
		
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
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMagnetTube.class, new RenderMagnetTube());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(AdvancedUtilitiesBlocks.magnetItemTube), new RenderTileEntityBase(new ModelTube(), new ResourceLocation("advancedutilities","textures/blocks/magnetitemtube.png")));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRationedItemTube.class, new RenderRationedItemTube());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(AdvancedUtilitiesBlocks.rationedItemTube), new RenderTileEntityBase(new ModelTube(), new ResourceLocation("advancedutilities","textures/blocks/rationeditemtube.png")));
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySplitterItemTube.class, new RenderSplitterTube());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(AdvancedUtilitiesBlocks.splitterItemTube), new RenderTileEntityBase(new ModelSplitterTube(), new ResourceLocation("advancedutilities","textures/blocks/itemtube.png")));
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFluidTube.class, new RenderFluidTube());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(AdvancedUtilitiesBlocks.fluidTube), new RenderTileEntityBase(new ModelTube(), new ResourceLocation("advancedutilities","textures/blocks/fluidtube.png")));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySplitterFluidTube.class, new RenderSplitterTube());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(AdvancedUtilitiesBlocks.splitterFluidTube), new RenderTileEntityBase(new ModelSplitterTube(), new ResourceLocation("advancedutilities","textures/blocks/fluidtube.png")));
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySteamCharger.class, new RenderSteamCharger());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(AdvancedUtilitiesBlocks.steamCharger), new RenderTileEntityBase(new ModelSteamCharger(), new ResourceLocation("advancedutilities","textures/blocks/steamcharger.png")));
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTank.class, new RenderTank());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(AdvancedUtilitiesBlocks.blockTank), new RenderTileEntityBase(new ModelTank(), new ResourceLocation("advancedutilities","textures/blocks/tank.png")));
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTomatoPlant.class, new RenderTomatoPlant());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(AdvancedUtilitiesBlocks.tomatoPlant), new RenderTileEntityBase(new ModelTank(), new ResourceLocation("advancedutilities","textures/blocks/tomatoplant.png")));
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySteamQuarry.class, new RenderSteamQuarry());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(AdvancedUtilitiesBlocks.steamQuarry), new RenderTileEntityBase(new ModelSteamQuarry(), new ResourceLocation("advancedutilities","textures/blocks/steamquarry.png")));
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityQuarryFrame.class, new RenderQuarryFrame());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(AdvancedUtilitiesBlocks.quarryFrame), new RenderTileEntityBase(new ModelQuarryFrame(), new ResourceLocation("advancedutilities","textures/blocks/quarryframe.png")));
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPortaChest.class, new RenderPortaChest());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(AdvancedUtilitiesBlocks.portaChestWood), new RenderPortaChestItem());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(AdvancedUtilitiesBlocks.portaChestBronze), new RenderPortaChestItem());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(AdvancedUtilitiesBlocks.portaChestGold), new RenderPortaChestItem());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(AdvancedUtilitiesBlocks.portaChestDiamond), new RenderPortaChestItem());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(AdvancedUtilitiesBlocks.portaChestPlatinum), new RenderPortaChestItem());
		
		TileEntitySpecialRenderer eRender = new RenderElevator();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityElevator.class, eRender);
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(AdvancedUtilitiesBlocks.elevator), new RenderTileEntityBase(new ResourceLocation(AdvancedUtilities.MODID, "models/elevator/elevator.obj"), new ResourceLocation("advancedutilities","models/elevator/elevator.png")));
		
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWire.class, new RenderWire());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(AdvancedUtilitiesBlocks.ironWire), new RenderTileEntityBase(new ModelQuarryFrame(), new ResourceLocation("advancedutilities","textures/blocks/ironwire.png")));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(AdvancedUtilitiesBlocks.copperWire), new RenderTileEntityBase(new ModelQuarryFrame(), new ResourceLocation("advancedutilities","textures/blocks/copperwire.png")));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(AdvancedUtilitiesBlocks.goldWire), new RenderTileEntityBase(new ModelQuarryFrame(), new ResourceLocation("advancedutilities","textures/blocks/goldwire.png")));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(AdvancedUtilitiesBlocks.blockTank), new RenderTankItem(new ModelTank(), new ResourceLocation("advancedutilities","textures/blocks/tank.png")));
		
		MinecraftForgeClient.registerItemRenderer(AdvancedUtilitiesItems.pnumaticGun, new RenderItem(new ModelGun(), new ResourceLocation("advancedutilities","textures/items/gun.png")));
		MinecraftForgeClient.registerItemRenderer(AdvancedUtilitiesItems.jackHammer, new RenderJackHammer(new ModelJackHammer(), new ResourceLocation("advancedutilities","textures/items/jackhammer.png")));
		MinecraftForgeClient.registerItemRenderer(AdvancedUtilitiesItems.steamJetpack, new RenderJetpackItem(new ModelJetpack(), new ResourceLocation("advancedutilities","textures/items/jetpack.png")));
		MinecraftForgeClient.registerItemRenderer(AdvancedUtilitiesItems.rebreather, new RenderRebreather(new ModelRebreather(), new ResourceLocation("advancedutilities","textures/items/rebreather.png")));
		MinecraftForgeClient.registerItemRenderer(AdvancedUtilitiesItems.runningShoes, new RenderRunningShoesItem(new ModelRunningShoes(), new ResourceLocation("advancedutilities","textures/items/runningshoes.png")));
		MinecraftForgeClient.registerItemRenderer(AdvancedUtilitiesItems.steamLegs, new RenderRunningShoesItem(new ModelPowerLegs(), new ResourceLocation("advancedutilities","textures/items/powerlegs.png")));
		MinecraftForgeClient.registerItemRenderer(AdvancedUtilitiesItems.tomato, new RenderTomato(new ModelTomato(), new ResourceLocation("advancedutilities","textures/items/tomato.png")));
	}
}
