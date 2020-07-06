package com.gkoliver.thetestmod.core.event;

import com.gkoliver.thetestmod.TheTestMod;
import com.gkoliver.thetestmod.client.render.entity.WaterParticleRenderer;
import com.gkoliver.thetestmod.core.registry.TestEntities;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid=TheTestMod.MODID, bus=Bus.MOD, value=Dist.CLIENT)
public class ClientEvents {
	@SubscribeEvent
    public static void onSetup(final FMLClientSetupEvent event) {
		TheTestMod.LOGGER.debug("PLEASE LOG THIS I BEG");
		TheTestMod.LOGGER.debug("PLEASE LOG THIS I BEG");
		TheTestMod.LOGGER.debug("PLEASE LOG THIS I BEG");
		TheTestMod.LOGGER.debug("PLEASE LOG THIS I BEG");
		TheTestMod.LOGGER.debug("PLEASE LOG THIS I BEG");
		TheTestMod.LOGGER.debug("PLEASE LOG THIS I BEG");
		RenderingRegistry.registerEntityRenderingHandler(TestEntities.WATER_PARTICLE.get(), (dispatcher)->new WaterParticleRenderer(dispatcher));
	}
}
