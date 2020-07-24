package com.gkoliver.thetestmod.common.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.network.DebugPacketSender;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

public class HighlightItemBlock extends Item {
    public HighlightItemBlock(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        BlockPos pos = context.getPos();
        if (!context.getWorld().isRemote()) {
            DebugPacketSender.func_229752_a_((ServerWorld) context.getWorld(), pos, "this is a test.", -2147418368, 10000);
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.SUCCESS;
    }
}
