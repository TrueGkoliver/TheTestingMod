package com.gkoliver.thetestmod.common.nonidealstructures;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FakeMossBolder implements INonIdealStructure {
	private static Random rand = new Random();
	int radius;
	public FakeMossBolder() {
		radius = rand.nextInt(3)+1;
	}
	
	@SuppressWarnings("unused")
	@Override
	public void generate(World worldIn, BlockPos posIn) {
		while(true) {
	         label48: {
	            int i1 = this.radius;

	            for(int i = 0; i1 >= 0 && i < 3; ++i) {
	               int j = i1 + rand.nextInt(2);
	               int k = i1 + rand.nextInt(2);
	               int l = i1 + rand.nextInt(2);
	               float f = (float)(j + k + l) * 0.333F + 0.5F;
	               
	               
	               for(BlockPos blockpos : BlockPos.getAllInBoxMutable(posIn.add(-j, -k, -l), posIn.add(j, k, l))) {
	                  if (blockpos.distanceSq(posIn) <= (double)(f * f)) {
	                	  BlockState state = Blocks.COBBLESTONE.getDefaultState();
	                	  if (rand.nextBoolean()) {
	                		  state = Blocks.MOSSY_COBBLESTONE.getDefaultState();
		   	              }
	                	  worldIn.setBlockState(blockpos, state, 2);
	                  }
	               }

	               posIn = posIn.add(-(i1 + 1) + rand.nextInt(2 + i1 * 2), 0 - rand.nextInt(2), -(i1 + 1) + rand.nextInt(2 + i1 * 2));
	            }

	            return;
	         }
	      }
	}
	

}
