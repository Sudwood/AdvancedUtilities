package com.sudwood.advancedutilities;
import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;
 
public class TreeGenerator implements IWorldGenerator {
       
	 
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
	                IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
	        switch(world.provider.dimensionId){
	                case -1: generateNether(world, random, chunkX * 16, chunkZ * 16);
	                case 0: generateSurface(world, random, chunkX * 16, chunkZ * 16);
	                case 1: generateEnd(world, random, chunkX * 16, chunkZ * 16);
	        }
	       
	}
	 
	private void generateEnd(World world, Random random, int x, int z) {
	       
	}
	 
	private void generateSurface(World world, Random random, int x, int z) {
	        for (int i = 0; i < 5; i++){
	                int Xcoord1 = x + random.nextInt(16); //where in chuck it generates
	                int Ycoord1 = random.nextInt(100); //how high it generates
	                int Zcoord1 = z + random.nextInt(16); //where in chunk it generates
	               
	                new WorldGenAdvancedTree(false, 7, 0, 0, false).generate(world, random, Xcoord1, Ycoord1, Zcoord1);
	               
	        }
	       
	}
	 
	private void generateNether(World world, Random random, int x, int z) {
	       
	}
}