package com.gkoliver.thetestmod.common.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;

import java.util.List;
import java.util.stream.Stream;

public class BigMinerStuff extends Item {
    public BigMinerStuff(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        BlockPos surroundBox1 = context.getPos().down().east().south();
        BlockPos surroundBox2 = context.getPos().up().west().north();
        Stream<BlockPos> poses = BlockPos.getAllInBox(surroundBox1, surroundBox2);
        int i = 50;
        poses.forEach((pos)->{
            if (pos!=context.getPos()) {
                context.getWorld().sendBlockBreakProgress(context.getPlayer().getEntityId(), context.getPos(), i);
            }
        });
        return ActionResultType.SUCCESS;
    }
}
