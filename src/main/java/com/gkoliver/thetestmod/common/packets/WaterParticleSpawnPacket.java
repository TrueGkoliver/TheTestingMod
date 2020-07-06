package com.gkoliver.thetestmod.common.packets;

import com.gkoliver.thetestmod.common.entity.WaterParticleEntity;
import com.gkoliver.thetestmod.core.registry.TestEntities;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.MinecartTickableSound;
import net.minecraft.client.network.play.ClientPlayNetHandler;
import net.minecraft.client.network.play.IClientPlayNetHandler;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.item.*;
import net.minecraft.entity.item.minecart.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.*;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.PacketThreadUtil;
import net.minecraft.network.play.server.SSpawnObjectPacket;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.io.IOException;
import java.util.UUID;

public class WaterParticleSpawnPacket implements IPacket<IClientPlayNetHandler>  {
    private int entityId;
    private UUID uniqueId;
    private double x;
    private double y;
    private double z;
    private int speedX;
    private int speedY;
    private int speedZ;
    private int pitch;
    private int yaw;
    private EntityType<?> type;
    private int data;
    //private Entity shooter;
    //private double distAway;

    public WaterParticleSpawnPacket() {
    }

    public WaterParticleSpawnPacket(int p_i50777_1_, UUID p_i50777_2_, double p_i50777_3_, double p_i50777_5_, double p_i50777_7_, float p_i50777_9_, float p_i50777_10_, EntityType<?> p_i50777_11_, int p_i50777_12_, Vector3d p_i50777_13_) {
        this.entityId = p_i50777_1_;
        this.uniqueId = p_i50777_2_;
        this.x = p_i50777_3_;
        this.y = p_i50777_5_;
        this.z = p_i50777_7_;
        this.pitch = MathHelper.floor(p_i50777_9_ * 256.0F / 360.0F);
        this.yaw = MathHelper.floor(p_i50777_10_ * 256.0F / 360.0F);
        this.type = p_i50777_11_;
        this.data = p_i50777_12_;
        this.speedX = (int)(MathHelper.clamp(p_i50777_13_.x, -3.9D, 3.9D) * 8000.0D);
        this.speedY = (int)(MathHelper.clamp(p_i50777_13_.y, -3.9D, 3.9D) * 8000.0D);
        this.speedZ = (int)(MathHelper.clamp(p_i50777_13_.z, -3.9D, 3.9D) * 8000.0D);
    }

    public WaterParticleSpawnPacket(Entity shooterIn, double distAway, int entityId, UUID uuidIn, double x, double y, double z, float pitch, float yaw, EntityType<?> typeIn, int data, Vector3d speed) {
        this(entityId, uuidIn, x, y, z, pitch, yaw, typeIn, data, speed);
    }

    public WaterParticleSpawnPacket(Entity p_i50778_1_) {
        this(p_i50778_1_, 0);
    }

    public WaterParticleSpawnPacket(Entity entityIn, int typeIn) {
        this(entityIn.getEntityId(), entityIn.getUniqueID(), entityIn.getPosX(), entityIn.getPosY(), entityIn.getPosZ(), entityIn.rotationPitch, entityIn.rotationYaw, entityIn.getType(), typeIn, entityIn.getMotion());
    }

    public WaterParticleSpawnPacket(Entity p_i50779_1_, EntityType<?> p_i50779_2_, int p_i50779_3_, BlockPos p_i50779_4_) {
        this(p_i50779_1_.getEntityId(), p_i50779_1_.getUniqueID(), (double)p_i50779_4_.getX(), (double)p_i50779_4_.getY(), (double)p_i50779_4_.getZ(), p_i50779_1_.rotationPitch, p_i50779_1_.rotationYaw, p_i50779_2_, p_i50779_3_, p_i50779_1_.getMotion());
    }

    /**
     * Reads the raw packet data from the data stream.
     */
    public void readPacketData(PacketBuffer buf) throws IOException {
        this.entityId = buf.readVarInt();
        this.uniqueId = buf.readUniqueId();
        this.type = Registry.ENTITY_TYPE.getByValue(buf.readVarInt());
        this.x = buf.readDouble();
        this.y = buf.readDouble();
        this.z = buf.readDouble();
        this.pitch = buf.readByte();
        this.yaw = buf.readByte();
        this.data = buf.readInt();
        this.speedX = buf.readShort();
        this.speedY = buf.readShort();
        this.speedZ = buf.readShort();
        //this.shooter = Minecraft.getInstance().world.getPlayerByUuid(buf.readUniqueId());
        //this.distAway = buf.readDouble();
    }

    /**
     * Writes the raw packet data to the data stream.
     */
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeVarInt(this.entityId);
        buf.writeUniqueId(this.uniqueId);
        buf.writeVarInt(Registry.ENTITY_TYPE.getId(this.type));
        buf.writeDouble(this.x);
        buf.writeDouble(this.y);
        buf.writeDouble(this.z);
        buf.writeByte(this.pitch);
        buf.writeByte(this.yaw);
        buf.writeInt(this.data);
        buf.writeShort(this.speedX);
        buf.writeShort(this.speedY);
        buf.writeShort(this.speedZ);
        //buf.writeUniqueId(this.shooter.getUniqueID());
        //buf.writeDouble(this.distAway);
    }

    /**
     * Passes this Packet on to the NetHandler for processing.
     */
    public void processPacket(IClientPlayNetHandler handler)
    {
        if (handler instanceof ClientPlayNetHandler) {
            handleSpawnObject((ClientPlayNetHandler) handler, this);
        }

    }

    public static void handleSpawnObject(ClientPlayNetHandler handlerIn, WaterParticleSpawnPacket packetIn) {
        PacketThreadUtil.checkThreadAndEnqueue(packetIn, handlerIn, Minecraft.getInstance());
        double d0 = packetIn.getX();
        double d1 = packetIn.getY();
        double d2 = packetIn.getZ();
        EntityType<?> entitytype = packetIn.getType();
        //Entity entityShooter = packetIn.getShooter();
        Entity entity;
        if (entitytype == TestEntities.WATER_PARTICLE.get()) {
            entity = WaterParticleEntity.createNewEntity(handlerIn.world, packetIn.x, packetIn.y, packetIn.z, packetIn.speedX, packetIn.speedY, packetIn.speedZ);
        } else {
            entity = null;
        }

        if (entity != null) {
            int i = packetIn.getEntityID();
            entity.setPacketCoordinates(d0, d1, d2);
            entity.moveForced(d0, d1, d2);
            entity.rotationPitch = (float)(packetIn.getPitch() * 360) / 256.0F;
            entity.rotationYaw = (float)(packetIn.getYaw() * 360) / 256.0F;
            entity.setEntityId(i);
            entity.setUniqueId(packetIn.getUniqueId());
            handlerIn.world.addEntity(i, entity);
        }

    }

    @OnlyIn(Dist.CLIENT)
    public int getEntityID() {
        return this.entityId;
    }

    @OnlyIn(Dist.CLIENT)
    public UUID getUniqueId() {
        return this.uniqueId;
    }

    @OnlyIn(Dist.CLIENT)
    public double getX() {
        return this.x;
    }

    @OnlyIn(Dist.CLIENT)
    public double getY() {
        return this.y;
    }

    @OnlyIn(Dist.CLIENT)
    public double getZ() {
        return this.z;
    }

    @OnlyIn(Dist.CLIENT)
    public double func_218693_g() {
        return (double)this.speedX / 8000.0D;
    }

    @OnlyIn(Dist.CLIENT)
    public double func_218695_h() {
        return (double)this.speedY / 8000.0D;
    }
    @OnlyIn(Dist.CLIENT)
    public double func_218692_i() {
        return (double)this.speedZ / 8000.0D;
    }

    @OnlyIn(Dist.CLIENT)
    public int getPitch() {
        return this.pitch;
    }

    @OnlyIn(Dist.CLIENT)
    public int getYaw() {
        return this.yaw;
    }

    @OnlyIn(Dist.CLIENT)
    public EntityType<?> getType() {
        return this.type;
    }

    @OnlyIn(Dist.CLIENT)
    public int getData() {
        return this.data;
    }
}
