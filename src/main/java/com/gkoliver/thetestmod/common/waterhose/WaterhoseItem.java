package com.gkoliver.thetestmod.common.waterhose;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class WaterhoseItem extends Item {

	public WaterhoseItem(Properties properties) {
		super(properties);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		Configurations.CONFIG_DEFAULT.doShoot(worldIn, playerIn);
		return ActionResult.resultSuccess(playerIn.getHeldItem(handIn));
	}

}
