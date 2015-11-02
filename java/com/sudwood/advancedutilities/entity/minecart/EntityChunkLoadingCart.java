package com.sudwood.advancedutilities.entity.minecart;

import com.sudwood.advancedutilities.AdvancedUtilities;
import com.sudwood.advancedutilities.AdvancedUtilitiesChunkLoadCallback;
import com.sudwood.advancedutilities.blocks.AdvancedUtilitiesBlocks;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeChunkManager.Ticket;
import net.minecraftforge.common.ForgeChunkManager.Type;

public class EntityChunkLoadingCart extends EntityMinecart implements IChunkCart
{
    private static final String __OBFID = "CL_00001677";
    private Ticket ticket;
    public EntityChunkLoadingCart(World par1World)
    {
        super(par1World);
        if(ticket == null)
        	ticket = ForgeChunkManager.requestTicket(AdvancedUtilities.instance, par1World, Type.ENTITY);
        ticket.getModData().setInteger("type", AdvancedUtilitiesChunkLoadCallback.ChunkLoaderCartID);
        ticket.bindEntity(this);
        ForgeChunkManager.forceChunk(ticket, new ChunkCoordIntPair(((int)this.posX) >> 4, ((int)this.posZ) >> 4));
    }

    public EntityChunkLoadingCart(World par1World, double par2, double par4, double par6)
    {
        super(par1World, par2, par4, par6);
        if(ticket == null)
        	ticket = ForgeChunkManager.requestTicket(AdvancedUtilities.instance, par1World, Type.ENTITY);
        ticket.getModData().setInteger("type", AdvancedUtilitiesChunkLoadCallback.ChunkLoaderCartID);
        ticket.bindEntity(this);
        ForgeChunkManager.forceChunk(ticket, new ChunkCoordIntPair(((int)this.posX) >> 4, ((int)this.posZ) >> 4));
    }
    
    public void loadChunk(int chunkX, int chunkZ)
    {
    	ForgeChunkManager.forceChunk(ticket, new ChunkCoordIntPair(chunkX, chunkZ));
    }
    public void unLoadChunk(int chunkX, int chunkZ)
    {
    	ForgeChunkManager.unforceChunk(ticket, new ChunkCoordIntPair(chunkX, chunkZ));
    }
    
    @Override
    public Block func_145817_o()
    {
        return AdvancedUtilitiesBlocks.chunkLoader;
    }

    public void killMinecart(DamageSource par1DamageSource)
    {
        this.setDead();
        this.unLoadChunk(((int)this.posX) >> 4,((int) this.posZ) >> 4);
        ItemStack itemstack = new ItemStack(Items.minecart, 1);
        this.func_145778_a(Item.getItemFromBlock(AdvancedUtilitiesBlocks.chunkLoader), 1, 0.0F);
        this.entityDropItem(itemstack, 0.0F);
    }
    
    public void setDead()
    {
    	super.setDead();
    }
    
    
    public void loadTicket(Ticket ticket)
	 {
    	if(this.ticket == null)
    	{
    		this.ticket = ticket;
    		this.loadChunk(((int)this.posX) >> 4,((int) this.posZ) >> 4);
    	}
	 }
    
    /**
     * Creates a new minecart of the specified type in the specified location in the given world. par0World - world to
     * create the minecart in, double par1,par3,par5 represent x,y,z respectively. int par7 specifies the type: 1 for
     * MinecartChest, 2 for MinecartFurnace, 3 for MinecartTNT, 4 for MinecartMobSpawner, 5 for MinecartHopper and 0 for
     * a standard empty minecart
     */
    public static EntityMinecart createMinecart(World par0World, double par1, double par3, double par5, int par7)
    {
    	return new EntityChunkLoadingCart(par0World, par1, par3, par5);
    }
    
    @Override
    public void onUpdate()
    {
    	super.onUpdate();
    	//this.motionX += this.motionX * 4.05;
    }
    

    public int getMinecartType()
    {
        return 0;
    }
}