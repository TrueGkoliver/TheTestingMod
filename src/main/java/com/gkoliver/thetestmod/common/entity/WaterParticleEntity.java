package com.gkoliver.thetestmod.common.entity;

import com.gkoliver.thetestmod.common.packets.WaterParticleSpawnPacket;
import com.gkoliver.thetestmod.core.registry.TestEntities;

import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SSpawnObjectPacket;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class WaterParticleEntity extends Entity {
	double velocityDownwards;
	private Entity shooter;
	private double distAway;
	public WaterParticleEntity(World worldIn) {
		super(TestEntities.WATER_PARTICLE.get(), worldIn);
		
	}
	public WaterParticleEntity(EntityType<? extends WaterParticleEntity> entityTypeIn, World worldIn) {
		super(entityTypeIn, worldIn);
		
	}

	public static WaterParticleEntity createNewEntity(World world, double x, double y, double z, int speedX, int speedY, int speedZ) {
		WaterParticleEntity tbr = new WaterParticleEntity(world);
		tbr.setPosition(x, y, z);
		tbr.setMotion(speedX, speedY, speedZ);
		return tbr;
	}

	@Override
	public void tick() {
		if (this.getPosY() <= -40) {
			this.remove();
		}
		if (!this.isAirBorne) {
			this.setMotion(this.getMotion().add(0.0D, -0.04D, 0.0D));
			this.setPosition(this.getPosX() + this.getMotion().x, this.getPosY() + this.getMotion().y, this.getPosZ() + this.getMotion().z);
		}

		super.tick();
	}
	public static void createNewEntity(double x, double y, double z, double vX, double vZ) {}
	public static void createNewEntity(double x, double y, double z, double vX, double vY, double vZ) {}
	public static WaterParticleEntity createNewEntity(Entity entityIn, double vX, double vY, double vZ, double sizeAway) {
		//System.out.println("createNewEntity");
		WaterParticleEntity tbr = new WaterParticleEntity(entityIn.getEntityWorld());
		tbr.shooter = entityIn;
		tbr.distAway = sizeAway;
		//tbr.reAdjustLook(entityIn, sizeAway);
		tbr.reAdjustPosition(entityIn);
		tbr.setVelocity(vX, vY, vZ);
		entityIn.getEntityWorld().addEntity(tbr);
		return tbr;
	}
	public void reAdjustPosition(Entity entityIn) {
		this.setPosition(entityIn.getPosX(), entityIn.getPosY(), entityIn.getPosZ());
		this.rotationPitch = entityIn.rotationPitch;
		this.rotationYaw = entityIn.rotationYaw;
	}
	public void reAdjustLook(Entity entityIn, double sizeAway) {
		//System.out.println("reAdjustLook");
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
		int i = 0;
		return new WaterParticleSpawnPacket(this.shooter, distAway, this.getEntityId(), this.getUniqueID(), this.getPosX(), this.getPosY(), this.getPosZ(), this.rotationPitch, this.rotationYaw, this.getType(), 0, new Vector3d(0, 0, 0));
	}

}
