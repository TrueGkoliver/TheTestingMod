package com.gkoliver.thetestmod.common.waterhose;

import com.gkoliver.thetestmod.common.entity.WaterParticleEntity;

import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class DefaultConfiguration implements IWaterHoseType {

	@Override
	public void doShoot(World worldIn, LivingEntity entityIn) {
		double vX = entityIn.getLookVec().getX()*this.getVelocity();
		double vY = entityIn.getLookVec().getY()*this.getVelocity();
		double vZ = entityIn.getLookVec().getZ()*this.getVelocity();
		WaterParticleEntity.createNewEntity(entityIn, vX, vY, vZ, 0.5);
	}

	@Override
	public double getVelocity() {
		return 3.5;
	}

	@Override
	public EWaterHoseType getType() {
		return EWaterHoseType.MEDIUM;
	}
	
}
