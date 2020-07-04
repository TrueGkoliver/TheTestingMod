package com.gkoliver.thetestmod.common.nonidealstructures;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface INonIdealStructure {
	public void generate(World worldIn, BlockPos posIn);

}
