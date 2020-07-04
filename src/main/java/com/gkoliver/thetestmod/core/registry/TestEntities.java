package com.gkoliver.thetestmod.core.registry;

import com.gkoliver.thetestmod.TheTestMod;
import com.gkoliver.thetestmod.common.entity.WaterParticleEntity;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TestEntities {
	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, TheTestMod.MODID);
	
	public static final RegistryObject<EntityType<WaterParticleEntity>> WATER_PARTICLE = ENTITIES.register("water_particle", ()->EntityType.Builder.<WaterParticleEntity>create(WaterParticleEntity::new, EntityClassification.MISC).build(new ResourceLocation(TheTestMod.MODID, "water_particle").toString()));
}
