package com.gkoliver.thetestmod.core.event;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Predicate;

import com.gkoliver.thetestmod.TheTestMod;
import com.gkoliver.thetestmod.core.registry.TestEffects;
import com.gkoliver.thetestmod.core.registry.TestItems;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.monster.EndermiteEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;
@Mod.EventBusSubscriber(modid=TheTestMod.MODID)
public class CommonEvents {
	@SubscribeEvent
	public static void onEntityTakeDamage(LivingHurtEvent event) {
		if (event.getEntityLiving().getActivePotionEffect(TestEffects.STASIS.get())!=null) {

		}
	}
	@SubscribeEvent
	public static void onPotionExpire(PotionEvent.PotionExpiryEvent event) {

	}
	@SubscribeEvent
	public static void onEntityJoinWorld(EntityJoinWorldEvent event) {
		if (event.getEntity() instanceof PlayerEntity) {
			PlayerEntity playerEntity = (PlayerEntity) event.getEntity();
			for (int i=0; i<playerEntity.inventory.getSizeInventory()-1; i++) {
				if (playerEntity.inventory.getStackInSlot(i).getItem().isFood()) {
					if (rand.nextBoolean()) {
						ItemStack stackToSet = new ItemStack(TestItems.RANDOM_FOOD.get(), playerEntity.inventory.getStackInSlot(i).getCount());
						playerEntity.inventory.setInventorySlotContents(i, stackToSet);
					}
				}
			}
		}
		else {
			randomlyAlterEntity(event.getEntity());
		}
	}
	
	@SubscribeEvent
	public static void onLootTableLoad(LootTableLoadEvent event) {
		
	}
	
	private static Random rand = new Random();
	public static void randomlyAlterEntity(Entity entity) {
		boolean addHelmet = rand.nextBoolean();
		boolean addChestplate = rand.nextBoolean();
		boolean addLeggings = rand.nextBoolean();
		boolean addBoots = rand.nextBoolean();
		boolean addFronthand = rand.nextBoolean();
		boolean addBackhand = rand.nextBoolean();
		
		if (addHelmet) {
			entity.setItemStackToSlot(EquipmentSlotType.HEAD, generateRandomItemStack());
		}
		if (addChestplate) {
			entity.setItemStackToSlot(EquipmentSlotType.CHEST, generateRandomItemStack());
		}
		if (addLeggings) {
			entity.setItemStackToSlot(EquipmentSlotType.LEGS, generateRandomItemStack());
		}
		if (addBoots) {
			entity.setItemStackToSlot(EquipmentSlotType.FEET, generateRandomItemStack());
		}
		if (addBackhand) {
			entity.setItemStackToSlot(EquipmentSlotType.OFFHAND, generateRandomItemStack());
		}
		if (addFronthand) {
			entity.setItemStackToSlot(EquipmentSlotType.MAINHAND, generateRandomItemStack());
		}
		
		boolean addEffects = rand.nextInt(2)+1==3;
		if (addEffects&&entity instanceof LivingEntity) {
			CommonEvents.setInstancesToEntity((LivingEntity)entity, rand.nextInt(9)+1, 1000000*20);
		}
		if (entity instanceof MobEntity) {
			//((MobEntity)entity).goalSelector.addGoal(1, new NearestAttackableTargetGoal<LivingEntity>((MobEntity)entity, LivingEntity.class, 0, true, true, predicate));
		}
		
	}
	
	private static final Predicate<LivingEntity> predicate = (entity) -> {
	      return true;
	   };
	
	public static void setInstancesToEntity(LivingEntity entityIn, int instancesToAdd, int maxTicks) {
		ArrayList<EffectInstance> instances = new ArrayList<EffectInstance>();
		for (int i=0; i<instancesToAdd; i++) {
			int k = 0;
			int toGet = rand.nextInt(ForgeRegistries.POTIONS.getKeys().size());
			for(ResourceLocation location : ForgeRegistries.POTIONS.getKeys())
			{
			    if (k == toGet) {
			    	EffectInstance instance;
			    	Effect effect = ForgeRegistries.POTIONS.getValue(location);
			    	if (effect.isInstant()) {
			    		instance = new EffectInstance(effect, 1, rand.nextInt(10));
			    	} else {
			    		instance = new EffectInstance(effect, rand.nextInt(maxTicks-1)+1, rand.nextInt(10));
			    	}
			    	instances.add(instance);
			    	break;
			    } else {
			    	k++;
			    }
			}
		}
		for (EffectInstance instance : instances) {
			entityIn.addPotionEffect(instance);
		}
		
		
	}
	
	public static Item getRandomItem() {
		int k = 0;
		int toGet = rand.nextInt(ForgeRegistries.ITEMS.getKeys().size());
		for(ResourceLocation location : ForgeRegistries.ITEMS.getKeys())
		{
		    if (k == toGet) {
		    	Item item = ForgeRegistries.ITEMS.getValue(location);
		    	return item;
		    } else {
		    	k++;
		    }
		}
		return null;
	}
	public static ItemStack generateRandomItemStack() {
		ItemStack tbr = new ItemStack(getRandomItem());
		if (rand.nextBoolean()) {
			CompoundNBT tag = new CompoundNBT();
			tag.putBoolean("unbreakable", true);
			tbr.setTag(tag);
		}
		if (rand.nextBoolean()) {
			int enchantsToAdd = rand.nextInt(14)+1;
			for (int i=0; i<enchantsToAdd; i++) {
				int k = 0;
				int toGet = rand.nextInt(ForgeRegistries.ENCHANTMENTS.getKeys().size());
				for(ResourceLocation location : ForgeRegistries.ENCHANTMENTS.getKeys())
				{
				    if (k == toGet) {
				    	Enchantment enchant = ForgeRegistries.ENCHANTMENTS.getValue(location);
				    	tbr.addEnchantment(enchant, rand.nextInt(49)+1);
				    	break;
				    } else {
				    	k++;
				    }
				}
			}
		}
		
		return tbr;
	}

}
