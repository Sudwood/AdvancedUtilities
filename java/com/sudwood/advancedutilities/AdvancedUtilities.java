package com.sudwood.advancedutilities;

import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.MinecraftForge;

import com.sudwood.advancedutilities.blocks.AdvancedUtilitiesBlocks;
import com.sudwood.advancedutilities.client.ClientRegistering;
import com.sudwood.advancedutilities.client.gui.GuiSteamBar;
import com.sudwood.advancedutilities.entity.EntitySpeedyChestcart;
import com.sudwood.advancedutilities.entity.EntitySpeedyMinecart;
import com.sudwood.advancedutilities.items.AdvancedUtilitiesItems;
import com.sudwood.advancedutilities.packets.PacketForge;
import com.sudwood.advancedutilities.packets.PacketJetpack;
import com.sudwood.advancedutilities.packets.PacketPipeline;
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
import com.sudwood.advancedutilities.tileentity.TileEntityToolForge;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = AdvancedUtilities.MODID, version = AdvancedUtilities.VERSION)
public class AdvancedUtilities
{
    public static final String MODID = "advancedutilities";
    public static final String VERSION = "0.1";
    public static final String textureSize = "";
    public static final PacketPipeline packetPipeline = new PacketPipeline();
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
    	AdvancedUtilitiesBlocks.init();
    	AdvancedUtilitiesItems.init();
    	AdvancedUtilitiesBlocks.addRecipies();
    	AdvancedUtilitiesItems.addRecipies();
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
    	//EntityRegistry.registerModEntity(EntityBullet.class, "EntityBullet", 0, this.instance, 20, 1, true);
    	//EntityRegistry.registerGlobalEntityID(EntitySpeedyMinecart.class, "EntitySpeedyMinecart", EntityRegistry.findGlobalUniqueEntityId());
    	EntityRegistry.registerModEntity(EntitySpeedyMinecart.class, "EntitySpeedyMinecart", 1, this.instance, 20, 1, true);
    	EntityRegistry.registerModEntity(EntitySpeedyChestcart.class, "EntitySpeedyChestcart", 0, this.instance, 20, 1, true);
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
		packetPipeline.initialise();
		packetPipeline.registerPacket(PacketForge.class);
		packetPipeline.registerPacket(SyncPlayerPropsPacket.class);
		packetPipeline.registerPacket(PacketJetpack.class);
		packetPipeline.registerPacket(PacketSteamCharger.class);
    }   
    
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    	ForgeChunkManager.setForcedChunkLoadingCallback(instance, new AdvancedUtilitiesChunkLoadCallback());
    	packetPipeline.postInitialise();
    	if(FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
		{
    		MinecraftForge.EVENT_BUS.register(new GuiSteamBar(Minecraft.getMinecraft()));
		}
    	
    }
    
}
