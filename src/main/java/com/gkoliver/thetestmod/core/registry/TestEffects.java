package com.gkoliver.thetestmod.core.registry;

import com.gkoliver.thetestmod.TheTestMod;
import com.gkoliver.thetestmod.common.potions.StasisPotionEffect;
import net.minecraft.entity.EntityType;
import net.minecraft.potion.Effect;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TestEffects {
    public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, TheTestMod.MODID);

    public static RegistryObject<Effect> STASIS = EFFECTS.register("stasis" ()->new StasisPotionEffect(0));
}
