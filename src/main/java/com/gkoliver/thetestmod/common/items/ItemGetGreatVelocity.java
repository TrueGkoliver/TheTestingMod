package com.gkoliver.thetestmod.common.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.StriderEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemGetGreatVelocity extends Item {
    public ItemGetGreatVelocity(Properties properties) {
        super(properties);
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if (entityIn.getLowestRidingEntity() instanceof StriderEntity){
            StriderEntity entity2 = (StriderEntity) entityIn.getLowestRidingEntity();
            System.out.println(entity2.getAIMoveSpeed());
            //System.out.println("X:");
            //System.out.println(entity2.getAIMoveSpeed());
            //System.out.println("Y:");
            //System.out.println(entity2.getMotion().getY());
            //System.out.println("Z:");
            //System.out.println(entity2.getMotion().getZ());
        }

        super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
    }
}
