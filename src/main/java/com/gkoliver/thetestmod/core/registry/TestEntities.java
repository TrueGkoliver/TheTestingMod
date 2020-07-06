package com.gkoliver.thetestmod.core.registry;

import com.gkoliver.thetestmod.TheTestMod;
import com.gkoliver.thetestmod.client.render.entity.WaterParticleRenderer;
import com.gkoliver.thetestmod.common.entity.WaterParticleEntity;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TestEntities {
	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, TheTestMod.MODID);
	
	public static final RegistryObject<EntityType<WaterParticleEntity>> WATER_PARTICLE = ENTITIES.register("water_particle", ()->EntityType.Builder.<WaterParticleEntity>create(WaterParticleEntity::new, EntityClassification.MISC)
			.size(0.2F, 0.2F).build(new ResourceLocation(TheTestMod.MODID, "water_particle").toString()));
	@OnlyIn(Dist.CLIENT)
	public static void clientSetup() {
		RenderingRegistry.registerEntityRenderingHandler(TestEntities.WATER_PARTICLE.get(), WaterParticleRenderer::new);
	}

}
