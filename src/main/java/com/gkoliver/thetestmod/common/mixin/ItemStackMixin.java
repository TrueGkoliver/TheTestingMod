package com.gkoliver.thetestmod.common.mixin;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.common.capabilities.CapabilityProvider;
import net.minecraftforge.common.extensions.IForgeItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;
import java.util.Random;

@Mixin(ItemStack.class)
public class ItemStackMixin {
    private int count;
    public boolean attemptDamageItem(int amount, Random rand, @Nullable ServerPlayerEntity damager) {
        return false;
    }
    //Thank you to the Forge project discord for aggressively telling me how to do this.
    //Note to self: Never ask another question to them again.
    @Inject(method = "shrink", at = @At("HEAD"), cancellable = true)
    public void check(CallbackInfo ci) {
        if (true) {
            System.out.println("This is an extremely boring check.");
            ci.cancel();
        }
        return;
    }
    /*public void shrink(int count) {
        if (1==1) {
            return;
        }
        else {
            this.count-=count;
        }

    }*/
}