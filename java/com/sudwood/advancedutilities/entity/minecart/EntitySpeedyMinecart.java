package com.sudwood.advancedutilities.entity.minecart;

import com.sudwood.advancedutilities.blocks.AdvancedUtilitiesBlocks;
import com.sudwood.advancedutilities.items.AdvancedUtilitiesItems;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntitySpeedyMinecart extends EntityMinecart
{
    private static final String __OBFID = "CL_00001677";

    public EntitySpeedyMinecart(World par1World)
    {
        super(par1World);
    }

    public EntitySpeedyMinecart(World par1World, double par2, double par4, double par6)
    {
        super(par1World, par2, par4, par6);
    }
    
   /* @Override
    public Block func_145817_o()
    {
        return AdvancedUtilitiesBlocks.chunkLoader;
    }*/

    @Override
    public float getMaxCartSpeedOnRail()
    {
        return 30.6f;
    }
    
    public void killMinecart(DamageSource par1DamageSource)
    {
        this.setDead();
        ItemStack itemstack = new ItemStack(AdvancedUtilitiesItems.speedyMinecart, 1);

        this.entityDropItem(itemstack, 0.0F);
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
    	return new EntitySpeedyMinecart(par0World, par1, par3, par5);
    }
    
    @Override
    public void onUpdate()
    {
    	super.onUpdate();
    		
    	//this.motionX += this.motionX * 4.05;
    }
    
    /**
     * First layer of player interaction
     */
    public boolean interactFirst(EntityPlayer par1EntityPlayer)
    {
        if(net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.event.entity.minecart.MinecartInteractEvent(this, par1EntityPlayer))) return true;
        if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityPlayer && this.riddenByEntity != par1EntityPlayer)
        {
            return true;
        }
        else if (this.riddenByEntity != null && this.riddenByEntity != par1EntityPlayer)
        {
            return false;
        }
        else
        {
            if (!this.worldObj.isRemote)
            {
                par1EntityPlayer.mountEntity(this);
            }

            return true;
        }
    }

    public int getMinecartType()
    {
        return 0;
    }
}