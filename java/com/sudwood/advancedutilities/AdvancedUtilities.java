package com.sudwood.advancedutilities;

import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import com.sudwood.advancedutilities.blocks.AdvancedUtilitiesBlocks;
import com.sudwood.advancedutilities.client.ClientRegistering;
import com.sudwood.advancedutilities.client.gui.GuiSteamBar;
import com.sudwood.advancedutilities.config.ConfigHandler;
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
import com.sudwood.advancedutilities.packets.PacketDrinkQuickPotion;
import com.sudwood.advancedutilities.packets.PacketForge;
import com.sudwood.advancedutilities.packets.PacketJetpack;
import com.sudwood.advancedutilities.packets.PacketRunningShoes;
import com.sudwood.advancedutilities.packets.PacketSteamCharger;
import com.sudwood.advancedutilities.packets.SyncPlayerPropsPacket;
import com.sudwood.advancedutilities.tileentity.TileEntityArmorForge;
import com.sudwood.advancedutilities.tileentity.TileEntityBellows;
import com.sudwood.advancedutilities.tileentity.TileEntityBoiler;
import com.sudwood.advancedutilities.tileentity.TileEntityChunkLoader;
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
import com.sudwood.advancedutilities.tileentity.TileEntityTank;
import com.sudwood.advancedutilities.tileentity.TileEntityToolForge;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = AdvancedUtilities.MODID, version = AdvancedUtilities.VERSION)
public class AdvancedUtilities
{
    public static final String MODID = "advancedutilities";
    public static final String VERSION = "A-0.477";
    public static final String textureSize = "";
    public static SimpleNetworkWrapper network;
    OreGenerator oregen = new OreGenerator();
    TreeGenerator treegen = new TreeGenerator();
    public static final int kilnGui = 0;
    public static final int smeltryGui = 1;
    public static final int toolForgeGui = 2;
    public static final int armorForgeGui = 3;
    public static final int steamBoilerGui = 4;
    public static final int steamCrusherGui = 5;
    public static final int steamFurnaceGui = 6;
    public static final int steamSmeltryGui = 7;
    public static final int itemTubeGui = 8;
    public static final int pnumaticGunGui = 9;
    public static final int fluidTubeGui = 10;
    public static final int restrictedItemTubeGui = 11;
    public static final int bulletMagazineGui = 12;
    public static final int bagGui = 13;
    public static final int skillsGui = 14;
    public static final int rebreatherGui = 15;
    
    public static String steamName = "Steam";
    
	@Instance(MODID)
    public static AdvancedUtilities instance;
    public static CreativeTabs advancedTab = new CreativeTabs("AdvancedUtilities")
    {
    	public Item getTabIconItem()
    	{
    		return Item.getItemFromBlock(AdvancedUtilitiesBlocks.blockKiln);
    	}
    };

    public static CreativeTabs advancedBEToolsTab = new CreativeTabs("AdvancedUtilitiesBETools")
    {
    	@SideOnly(Side.CLIENT)
        public ItemStack getIconItemStack()
        {
    		return new ItemStack(AdvancedUtilitiesItems.toolBE, 1, 14);
        }

    	@SideOnly(Side.CLIENT)
        public int func_151243_f()
        {
            return 14;
        }
		@Override
		public Item getTabIconItem() 
		{
			return AdvancedUtilitiesItems.toolBE;
		}
    };
    public static CreativeTabs advancedBEMachinesTab = new CreativeTabs("AdvancedUtilitiesBEMachines")
    {
    	@SideOnly(Side.CLIENT)
        public ItemStack getIconItemStack()
        {
    		return new ItemStack(AdvancedUtilitiesItems.dust, 1, 4);
        }

    	@SideOnly(Side.CLIENT)
        public int func_151243_f()
        {
            return 4;
        }
		@Override
		public Item getTabIconItem() 
		{
			return AdvancedUtilitiesItems.dust;
		}
    };
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
    
    	this.registerPackets();
    	AdvancedUtilitiesBlocks.init();
    	AdvancedUtilitiesItems.init();
    	AdvancedUtilitiesBlocks.addRecipies();
    	AdvancedUtilitiesItems.addRecipies();
    	this.registerEntities();
    	this.registerTiles();
    	ConfigHandler.init(event.getSuggestedConfigurationFile());

   		FMLCommonHandler.instance().bus().register(new AUFMLEventHandler());

    	MinecraftForge.EVENT_BUS.register(new AUEventHandler());
    	
    }
    
    
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	
		GameRegistry.registerWorldGenerator(oregen, 0);
		GameRegistry.registerWorldGenerator(treegen, 0);
		if(FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
		{
			ClientRegistering.registerRendering();
		}
    }   
    
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    	ForgeChunkManager.setForcedChunkLoadingCallback(instance, new AdvancedUtilitiesChunkLoadCallback());
    	if(FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
		{
    		MinecraftForge.EVENT_BUS.register(new GuiSteamBar(Minecraft.getMinecraft()));
		}
    	
    }
    
    public void registerPackets()
    {
    	network = NetworkRegistry.INSTANCE.newSimpleChannel("AUMOD");
    	network.registerMessage(PacketDrinkQuickPotion.Handler.class, PacketDrinkQuickPotion.class, 0, Side.SERVER);
    	network.registerMessage(PacketForge.Handler.class, PacketForge.class, 1, Side.SERVER);
    	network.registerMessage(PacketJetpack.Handler.class, PacketJetpack.class, 2, Side.SERVER);
    	network.registerMessage(PacketRunningShoes.Handler.class, PacketRunningShoes.class, 3, Side.SERVER);
    	network.registerMessage(PacketSteamCharger.Handler.class, PacketSteamCharger.class, 4, Side.CLIENT);
    	network.registerMessage(SyncPlayerPropsPacket.Handler.class, SyncPlayerPropsPacket.class, 5, Side.CLIENT);
    }
    
    public void registerTiles()
    {
    	GameRegistry.registerTileEntity(TileEntityKiln.class, "AdvancedUtilities:TileEntityKiln");
    	GameRegistry.registerTileEntity(TileEntityChunkLoader.class, "AdvancedUtilities:TileEntityChunkLoader");
    	GameRegistry.registerTileEntity(TileEntitySmeltry.class, "AdvancedUtilities:TileEntitySmeltry");
    	GameRegistry.registerTileEntity(TileEntityToolForge.class, "AdvancedUtilities:TileEntityToolForge");
    	GameRegistry.registerTileEntity(TileEntityArmorForge.class, "AdvancedUtilities:TileEntityArmorForge");
    	GameRegistry.registerTileEntity(TileEntityBoiler.class, "AdvancedUtilities:TileEntityBoiler");
    	GameRegistry.registerTileEntity(TileEntitySteamCrusher.class, "AdvancedUtilities:TileEntitySteamCrusher");
    	GameRegistry.registerTileEntity(TileEntitySteamFurnace.class, "AdvancedUtilities:TileEntitySteamFurnace");
    	GameRegistry.registerTileEntity(TileEntitySteamSmeltry.class, "AdvancedUtilities:TileEntitySteamSmeltry");
    	GameRegistry.registerTileEntity(TileEntityBellows.class, "AdvancedUtilities:TileEntityBellows");
    	GameRegistry.registerTileEntity(TileEntitySteamCompressor.class, "AdvancedUtilities:TileEntitySteamCompressor");
    	GameRegistry.registerTileEntity(TileEntityItemTube.class, "AdvancedUtilities:TileEntityItemTube");
    	GameRegistry.registerTileEntity(TileEntitySteamCharger.class, "AdvancedUtilities:TileEntitySteamCharger");
    	GameRegistry.registerTileEntity(TileEntityFluidTube.class, "AdvancedUtilities:TileEntityFluidTube");
    	GameRegistry.registerTileEntity(TileEntityRestrictedItemTube.class, "AdvancedUtilities:TileEntityRestrictedItemTube");
    	GameRegistry.registerTileEntity(TileEntityTank.class, "AdvancedUtilities:TileEntityTank");
    }
    
    public void registerEntities()
    {
    	//EntityRegistry.registerModEntity(EntityBullet.class, "EntityBullet", 0, this.instance, 20, 1, true);
    	//EntityRegistry.registerGlobalEntityID(EntitySpeedyMinecart.class, "EntitySpeedyMinecart", EntityRegistry.findGlobalUniqueEntityId());
    	EntityRegistry.registerModEntity(EntitySpeedyMinecart.class, "EntitySpeedyMinecart", 1, this.instance, 20, 1, true);
    	EntityRegistry.registerModEntity(EntitySpeedyChestcart.class, "EntitySpeedyChestcart", 0, this.instance, 20, 1, true);
    	EntityRegistry.registerModEntity(EntityChunkLoadingCart.class, "EntityChunkLoadingCart", 2, this.instance, 20, 1, true);
    	EntityRegistry.registerModEntity(EntitySpeedyChunkLoadCart.class, "EntitySpeedyChunkLoadCart", 3, this.instance, 20, 1, true);
    	EntityRegistry.registerModEntity(EntityChunkChestCart.class, "EntityChunkChestCart", 4, this.instance, 20, 1, true);
    	EntityRegistry.registerModEntity(EntitySpeedyChunkChestCart.class, "EntitySpeedyChunkChestCart", 5, this.instance, 20, 1, true);
    	EntityRegistry.registerModEntity(EntityTankCart.class, "EntityTankCart", 6, this.instance, 20, 1, true);
    	EntityRegistry.registerModEntity(EntitySpeedyTankCart.class, "EntitySpeedyTankCart", 7, this.instance, 20, 1, true);
    	EntityRegistry.registerModEntity(EntityChunkTankCart.class, "EntityChunkTankCart", 8, this.instance, 20, 1, true);
    	EntityRegistry.registerModEntity(EntitySpeedyChunkTankCart.class, "EntitySpeedyChunkTankCart", 9, this.instance, 20, 1, true);
    }
    
}
