package com.gkoliver.thetestmod.common.waterhose.config;

import com.gkoliver.thetestmod.common.entity.WaterParticleEntity;

import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class DefaultConfiguration implements IWaterHoseType {
	public DefaultConfiguration() {
		Configurations.CONFIGS.put(this.getId(), this);
	}
	@Override
	public void doShoot(World worldIn, LivingEntity entityIn) {
		if (!worldIn.isRemote()) {
			//System.out.println("doShoot");
			double vX = entityIn.getLookVec().getX()*this.getVelocity();
			double vY = entityIn.getLookVec().getY()*this.getVelocity();
			double vZ = entityIn.getLookVec().getZ()*this.getVelocity();
			WaterParticleEntity.createNewEntity(entityIn, vX, vY, vZ, 0.5);
		}
	}

	@Override
	public double getVelocity() {
		return 3.5;
	}

	@Override
	public EWaterHoseType getType() {
		return EWaterHoseType.MEDIUM;
	}

	@Override
	public int getId() {
		return 0;
	}

}
