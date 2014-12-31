package com.sudwood.advancedutilities;

import com.sudwood.advancedutilities.packets.SyncPlayerPropsPacket;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class ExtendedPlayer implements IExtendedEntityProperties
{
	/*
	Here I create a constant EXT_PROP_NAME for this class of properties. You need a unique name for every instance of IExtendedEntityProperties you make, and doing it at the top of each class as a constant makes
	it very easy to organize and avoid typos. It's easiest to keep the same constant name in every class, as it will be distinguished by the class name: ExtendedPlayer.EXT_PROP_NAME vs. ExtendedEntity.EXT_PROP_NAME
	
	Note that a single entity can have multiple extended properties, so each property should have a unique name. Try to come up with something more unique than the tutorial example.
	*/
	public final static String EXT_PROP_NAME = "ExtendedPlayer";
	
	private final EntityPlayer player;
	
	// Declare other variables you want to add here
	public boolean isJetpack;
	public boolean isRunning;
	public boolean toggleJetpack;
	public byte[] skills;
	
	/*
	The default constructor takes no arguments, but I put in the Entity so I can initialize the above variable 'player'
	
	Also, it's best to initialize any other variables you may have added, just like in any constructor.
	*/
	public ExtendedPlayer(EntityPlayer player)
	{
		this.player = player;
		isJetpack = false;
		isRunning = false;
		toggleJetpack = false;
		skills = new byte[]{0, 0, 0};
	}
	
	/**
	* Used to register these extended properties for the player during EntityConstructing event
	* This method is for convenience only; it will make your code look nicer
	*/
	public static final void register(EntityPlayer player)
	{
		player.registerExtendedProperties(ExtendedPlayer.EXT_PROP_NAME, new ExtendedPlayer(player));
	}
	
	/**
	* Returns ExtendedPlayer properties for player
	* This method is for convenience only; it will make your code look nicer
	*/
	public static final ExtendedPlayer get(EntityPlayer player)
	{
		return (ExtendedPlayer) player.getExtendedProperties(EXT_PROP_NAME);
	}
	
	
	// Save any custom data that needs saving here
	@Override
	public void saveNBTData(NBTTagCompound compound)
	{
		// We need to create a new tag compound that will save everything for our Extended Properties
		NBTTagCompound properties = new NBTTagCompound();
		
		properties.setBoolean("isJetpack", isJetpack);
		properties.setBoolean("isRunning", isRunning);
		properties.setBoolean("toggleJetpack", toggleJetpack);
		properties.setByteArray("skills", skills);
		
		/*
		Now add our custom tag to the player's tag with a unique name (our property's name). This will allow you to save multiple types of properties and distinguish between them. If you only have one type, it isn't as important, but it will still avoid conflicts between your tag names and vanilla tag names. For instance, if you add some "Items" tag, that will conflict with vanilla. Not good. So just use a unique tag name.
		*/
		compound.setTag(EXT_PROP_NAME, properties);
	}
	
	// Load whatever data you saved
	@Override
	public void loadNBTData(NBTTagCompound compound)
	{
		// Here we fetch the unique tag compound we set for this class of Extended Properties
		NBTTagCompound properties = (NBTTagCompound) compound.getTag(EXT_PROP_NAME);
		this.isJetpack = properties.getBoolean("isJetpack");
		this.isRunning = properties.getBoolean("isRunning");
		this.toggleJetpack = properties.getBoolean("toggleJetpack");
		this.skills = properties.getByteArray("skills");
	}
	
	private static final String getSaveKey(EntityPlayer player) 
	{
		// no longer a username field, so use the command sender name instead:
		return player.getCommandSenderName() + ":" + EXT_PROP_NAME;
	}

	public static final void loadProxyData(EntityPlayer player)
	{
		ExtendedPlayer playerData = ExtendedPlayer.get(player);
		NBTTagCompound savedData = ComonProxy.getEntityData(getSaveKey(player));
		if (savedData != null) { playerData.loadNBTData(savedData); }
		// we are replacing the entire sync() method with a single line; more on packets later
		// data can by synced just by sending the appropriate packet, as everything is handled internally by the packet class
		AdvancedUtilities.network.sendTo(new SyncPlayerPropsPacket(player), (EntityPlayerMP) player);
	}
	
	public static void saveProxyData(EntityPlayer player) 
	{
		ExtendedPlayer playerData = ExtendedPlayer.get(player);
		NBTTagCompound savedData = new NBTTagCompound();

		playerData.saveNBTData(savedData);
		// Note that we made the CommonProxy method storeEntityData static,
		// so now we don't need an instance of CommonProxy to use it! Great!
		ComonProxy.storeEntityData(getSaveKey(player), savedData);
	}

	/*
	I personally have yet to find a use for this method. If you know of any,
	please let me know and I'll add it in!
	*/
	@Override
	public void init(Entity entity, World world)
	{
		
	}
}
