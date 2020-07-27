package com.gkoliver.thetestmod.common.potions;

import com.gkoliver.thetestmod.TheTestMod;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class StasisPotionEffect extends Effect {
    public StasisPotionEffect() {
        super(EffectType.NEUTRAL, 0x91d984);
    }

    @Override
    public void performEffect(LivingEntity entityLivingBaseIn, int amplifier) {
        entityLivingBaseIn.getPersistentData().putInt(TheTestMod.MODID+":falldamage", 0);

        super.performEffect(entityLivingBaseIn, amplifier);
    }
    public static void serializeEffects(LivingEntity entity) {
        entity.getPersistentData().putFloat("");
    }
    public static void deserializeEffects(LivingEntity entity) {}
    public static void onEffectEnd(LivingEntity entityIn) {
        if (!entityIn.isInWater()) {
            //entityIn.attackEntityFrom(DamageSource.)
        }
        entityIn.heal(0);
    }
}
