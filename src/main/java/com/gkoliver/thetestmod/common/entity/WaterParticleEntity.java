package com.gkoliver.thetestmod.common.entity;

import com.gkoliver.thetestmod.core.registry.TestEntities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;

public class WaterParticleEntity extends Entity {
	double velocityDownwards;
	public WaterParticleEntity(World worldIn) {
		super(TestEntities.WATER_PARTICLE.get(), worldIn);
	}
	public WaterParticleEntity(EntityType<? extends WaterParticleEntity> entityTypeIn, World worldIn) {
		super(entityTypeIn, worldIn);
	}
	@Override
	public void tick() {
		if (this.getPosY()<=-40) {
			this.remove();
		}
		if (!this.onGround) {
			this.addVelocity(0, 9.8/20, 0);
		}
		super.tick();
	}
	public static void createNewEntity(double x, double y, double z, double vX, double vZ) {}
	public static void createNewEntity(double x, double y, double z, double vX, double vY, double vZ) {}
	public static WaterParticleEntity createNewEntity(Entity entityIn, double vX, double vY, double vZ, double sizeAway) {
		WaterParticleEntity tbr = new WaterParticleEntity(entityIn.world);
		tbr.reAdjustLook(entityIn, sizeAway);
		tbr.setVelocity(vX, vY, vZ);
		return tbr;
	}
	public void reAdjustLook(Entity entityIn, double sizeAway) {
		this.setPosition(entityIn.getPosX(), entityIn.getPosY(), entityIn.getPosZ());
		double pitch = entityIn.rotationPitch;
		double yaw = entityIn.rotationYaw;
		
		//Horizontal total distance
		double a = Math.cos(Math.toRadians(pitch))*sizeAway;
		//Vertical total distance
		double b = Math.sin(Math.toRadians(pitch))*sizeAway;
		this.setPosition(this.getPosX(), this.getPosY()+b, this.getPosX());
		
		//X distance
		double c = Math.cos(Math.toRadians(yaw))*a;
		//Z distance
		double d = Math.sin(Math.toRadians(yaw))*a;
		this.setPosition(this.getPosX()+c, this.getPosY(), this.getPosZ()+d);
	}
	
	@Override
	protected void registerData() {}
	@Override
	protected void readAdditional(CompoundNBT compound) {}
	@Override
	protected void writeAdditional(CompoundNBT compound) {}

	@Override
	public IPacket<?> createSpawnPacket() {
		return null;
	}

}
