package com.sudwood.advancedutilities.entity.minecart;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.item.EntityMinecartChest;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeChunkManager.Ticket;
import net.minecraftforge.common.ForgeChunkManager.Type;

import com.sudwood.advancedutilities.AdvancedUtilities;
import com.sudwood.advancedutilities.AdvancedUtilitiesChunkLoadCallback;
import com.sudwood.advancedutilities.blocks.AdvancedUtilitiesBlocks;
import com.sudwood.advancedutilities.items.AdvancedUtilitiesItems;

public class EntitySpeedyChunkChestCart extends EntityMinecartChest implements IChunkCart
{
	private Ticket ticket;
	public EntitySpeedyChunkChestCart(World par1World) 
	{
		super(par1World);
		if(ticket == null)
        	ticket = ForgeChunkManager.requestTicket(AdvancedUtilities.instance, par1World, Type.ENTITY);
        ticket.getModData().setInteger("type", AdvancedUtilitiesChunkLoadCallback.ChunkLoaderCartID);
        ticket.bindEntity(this);
        ForgeChunkManager.forceChunk(ticket, new ChunkCoordIntPair(((int)this.posX) >> 4, ((int)this.posZ) >> 4));
	}
	@Override
    public Block func_145817_o()
    {
        return AdvancedUtilitiesBlocks.dummyChunkChest;
    }
	public EntitySpeedyChunkChestCart(World par1World, double par2, double par4, double par6)
    {
        super(par1World, par2, par4, par6);
        if(ticket == null)
        	ticket = ForgeChunkManager.requestTicket(AdvancedUtilities.instance, par1World, Type.ENTITY);
        ticket.getModData().setInteger("type", AdvancedUtilitiesChunkLoadCallback.ChunkLoaderCartID);
        ticket.bindEntity(this);
        ForgeChunkManager.forceChunk(ticket, new ChunkCoordIntPair(((int)this.posX) >> 4, ((int)this.posZ) >> 4));
    }

	@Override
    public float getMaxCartSpeedOnRail()
    {
        return 30.6f;
    }
    
	@Override
    public void killMinecart(DamageSource par1DamageSource)
    {
		this.unLoadChunk(((int)this.posX) >> 4,((int) this.posZ) >> 4);
        this.setDead();
        ItemStack itemstack = new ItemStack(AdvancedUtilitiesItems.speedyMinecart, 1);
        this.func_145778_a(Item.getItemFromBlock(Blocks.chest), 1, 0.0F);
        this.func_145778_a(Item.getItemFromBlock(AdvancedUtilitiesBlocks.chunkLoader), 1, 0.0F);
        this.entityDropItem(itemstack, 0.0F);
        for (int i = 0; i < this.getSizeInventory(); ++i)
        {
            ItemStack itemstack1 = this.getStackInSlot(i);

            if (itemstack1 != null)
            {
                float f = this.rand.nextFloat() * 0.8F + 0.1F;
                float f1 = this.rand.nextFloat() * 0.8F + 0.1F;
                float f2 = this.rand.nextFloat() * 0.8F + 0.1F;

                while (itemstack1.stackSize > 0)
                {
                    int j = this.rand.nextInt(21) + 10;

                    if (j > itemstack1.stackSize)
                    {
                        j = itemstack1.stackSize;
                    }

                    itemstack1.stackSize -= j;
                    EntityItem entityitem = new EntityItem(this.worldObj, this.posX + (double)f, this.posY + (double)f1, this.posZ + (double)f2, new ItemStack(itemstack1.getItem(), j, itemstack1.getItemDamage()));
                    float f3 = 0.05F;
                    entityitem.motionX = (double)((float)this.rand.nextGaussian() * f3);
                    entityitem.motionY = (double)((float)this.rand.nextGaussian() * f3 + 0.2F);
                    entityitem.motionZ = (double)((float)this.rand.nextGaussian() * f3);
                    this.worldObj.spawnEntityInWorld(entityitem);
                }
            }
        }
    }
    
    protected void func_145821_a(int p_145821_1_, int p_145821_2_, int p_145821_3_, double p_145821_4_, double p_145821_6_, Block p_145821_8_, int p_145821_9_)
    {
    	//if(p_145821_8_ == Blocks.golden_rail)
    		super.func_145821_a(p_145821_1_, p_145821_2_, p_145821_3_, p_145821_4_*4, p_145821_6_, p_145821_8_, p_145821_9_);
    	/*else
    		super.func_145821_a(p_145821_1_, p_145821_2_, p_145821_3_, p_145821_4_/4, p_145821_6_, p_145821_8_, p_145821_9_);*/
    }
    /**
     * Creates a new minecart of the specified type in the specified location in the given world. par0World - world to
     * create the minecart in, double par1,par3,par5 represent x,y,z respectively. int par7 specifies the type: 1 for
     * MinecartChest, 2 for MinecartFurnace, 3 for MinecartTNT, 4 for MinecartMobSpawner, 5 for MinecartHopper and 0 for
     * a standard empty minecart
     */
    public static EntityMinecart createMinecart(World par0World, double par1, double par3, double par5, int par7)
    {
    	return new EntitySpeedyChunkChestCart(par0World, par1, par3, par5);
    }

    public void loadChunk(int chunkX, int chunkZ)
    {
    	ForgeChunkManager.forceChunk(ticket, new ChunkCoordIntPair(chunkX, chunkZ));
    }
    public void unLoadChunk(int chunkX, int chunkZ)
    {
    	ForgeChunkManager.unforceChunk(ticket, new ChunkCoordIntPair(chunkX, chunkZ));
    }

    public void loadTicket(Ticket ticket)
	{
	   	if(this.ticket == null)
	   	{
	   		this.ticket = ticket;
	   		this.loadChunk(((int)this.posX) >> 4,((int) this.posZ) >> 4);
	   	}
	 }

}
