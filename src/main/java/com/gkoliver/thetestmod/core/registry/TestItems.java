package com.gkoliver.thetestmod.core.registry;

import com.gkoliver.thetestmod.TheTestMod;
import com.gkoliver.thetestmod.common.items.*;
import com.gkoliver.thetestmod.common.waterhose.WaterhoseItem;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.WeightedRandom;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TestItems {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TheTestMod.MODID);
	
	public static final RegistryObject<Item> ICE_SPIKE_CONFIG = ITEMS.register("ice_spike_config", ()->new StructureGeneratorItem(new Item.Properties().group(ItemGroup.MISC)));
	public static final RegistryObject<Item> RANDOM_FOOD = ITEMS.register("random_food", ()->new RandomEatItem(new Item.Properties().group(ItemGroup.FOOD).food(new Food.Builder().setAlwaysEdible().build())));
	public static final RegistryObject<Item> HOSE = ITEMS.register("hose", ()->new WaterhoseItem(new Item.Properties().group(ItemGroup.MISC)));
	public static final RegistryObject<Item> HIGHLIGHT = ITEMS.register("highlight", ()->new HighlightItemBlock(new Item.Properties().group(ItemGroup.MISC)));
	public static final RegistryObject<Item> BIG_MINER = ITEMS.register("big_miner", ()->new BigMinerStuff(new Item.Properties().addToolType(ToolType.PICKAXE, 3).group(ItemGroup.TOOLS).maxStackSize(1)));
	public static final RegistryObject<Item> STRIDER_TESTER = ITEMS.register("getstridervelocity", ()->new ItemGetGreatVelocity(new Item.Properties().group(ItemGroup.MISC)));

}
