package com.gkoliver.thetestmod.core.registry;

import com.gkoliver.thetestmod.TheTestMod;
import com.gkoliver.thetestmod.common.items.RandomEatItem;
import com.gkoliver.thetestmod.common.items.StructureGeneratorItem;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TestItems {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TheTestMod.MODID);
	
	public static final RegistryObject<Item> ICE_SPIKE_CONFIG = ITEMS.register("ice_spike_config", ()->new StructureGeneratorItem(new Item.Properties().group(ItemGroup.MISC)));
	public static final RegistryObject<Item> RANDOM_FOOD = ITEMS.register("random_food", ()->new RandomEatItem(new Item.Properties().group(ItemGroup.FOOD).food(new Food.Builder().setAlwaysEdible().build())));
}
