package com.gkoliver.thetestmod.common.items;

import java.util.ArrayList;
import java.util.Random;

import com.gkoliver.thetestmod.core.event.CommonEvents;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

public class RandomEatItem extends Item {

	public RandomEatItem(Properties properties) {
		super(properties);
	}
	private final Random rand = new Random();
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
		float saturation = rand.nextFloat();
		int hunger = rand.nextInt(20);
		int instancesToAdd = rand.nextInt(5);
		CommonEvents.setInstancesToEntity(entityLiving, instancesToAdd, 3600);
		if (entityLiving instanceof PlayerEntity) {
			((PlayerEntity) entityLiving).getFoodStats().addStats(hunger, saturation);
		}
		return stack;
		
	}

}
