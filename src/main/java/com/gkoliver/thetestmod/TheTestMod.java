package com.gkoliver.thetestmod;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gkoliver.thetestmod.core.registry.TestItems;

import java.util.Random;
import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(TheTestMod.MODID)
@Mod.EventBusSubscriber(modid=TheTestMod.MODID)
public class TheTestMod
{
	public static final String MODID = "gkolivers_tests";
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public TheTestMod() {
    	IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        TestItems.ITEMS.register(eventBus);
        eventBus.addListener(this::onSetup);
    }
    
    public static void registerAllMobs() {

    	
    	for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
    		for (EntityType entity_type : ForgeRegistries.ENTITIES.getValues()) {
    			Random rand = new Random();
    	    	int type = rand.nextInt(3);
    	    	int weight = rand.nextInt(9)+1;
    	    	int min = rand.nextInt(5);
    	    	int max = min+rand.nextInt(19)+1;
    	    	EntityClassification classification = null;
    	    	if (type==0) {
    	    		classification = EntityClassification.AMBIENT;
    	    	}
    	    	else if (type==1) {
    	    		classification = EntityClassification.CREATURE;
    	    	}
    	    	else if (type==2) {
    	    		classification = EntityClassification.MISC;
    	    	}
    	    	else if (type==3) {
    	    		classification = EntityClassification.MONSTER;
    	    	}
    			if ((entity_type!=EntityType.WITHER&&entity_type!=EntityType.ENDER_DRAGON&&entity_type!=EntityType.PIG&&entity_type!=EntityType.COW&&entity_type!=EntityType.SHEEP)||rand.nextInt(100)==100) {
    				if (rand.nextInt(9)<=8) {
    					biome.addSpawn(classification, new Biome.SpawnListEntry(entity_type, weight, min, max));
    				}
    				
    			}
    		}
    	}
    }
    private void onSetup(final FMLCommonSetupEvent event) {
		TheTestMod.registerAllMobs();
	}

}
