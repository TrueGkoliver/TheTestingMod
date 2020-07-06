package com.gkoliver.thetestmod.common.waterhose.config;

import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public interface IWaterHoseType {
	public void doShoot(World worldIn, LivingEntity entityIn);
	public double getVelocity();
	public EWaterHoseType getType();
	public int getId();
}
