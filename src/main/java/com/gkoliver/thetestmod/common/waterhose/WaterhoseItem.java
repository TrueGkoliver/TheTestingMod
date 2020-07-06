package com.gkoliver.thetestmod.common.waterhose;

import com.gkoliver.thetestmod.common.waterhose.config.Configurations;
import com.gkoliver.thetestmod.common.waterhose.config.IWaterHoseType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class WaterhoseItem extends Item {

	public WaterhoseItem(Properties properties) {
		super(properties);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
	}
	public static void setConfiguration(ItemStack stackIn, IWaterHoseType config) {
		int id = config.getId();
		CompoundNBT nbt = stackIn.getTag();
		if (nbt == null) { nbt = new CompoundNBT();}
		CompoundNBT data = nbt.getCompound("hoseData");
		if (data == null) { data = new CompoundNBT(); }
		data.putInt("configuration", id);
		nbt.put("hoseData", data);
		stackIn.setTag(nbt);
	}
	public static IWaterHoseType getConfiguration(ItemStack stackIn) {
		CompoundNBT nbt = stackIn.getTag();
		if (nbt == null) {
			nbt = new CompoundNBT();
			return Configurations.CONFIG_DEFAULT;
		}
		CompoundNBT data = nbt.getCompound("hoseData");
		if (data == null) { return Configurations.CONFIG_DEFAULT; }
		int configId = data.getInt("configuration");
		IWaterHoseType tbr = Configurations.getConfig(configId);
		if (tbr==null) { return Configurations.CONFIG_DEFAULT; }
		return tbr;
	}


	/**
	 * Sets the intensity
	 * @param stackIn Stack to set.
	 * @param intensity Sets the amount of particles per second. 0-100
	 */
	public static void setIntensity(ItemStack stackIn, int intensity) {
		CompoundNBT nbt = stackIn.getTag();
		if (nbt == null) { nbt = new CompoundNBT();}
		CompoundNBT data = nbt.getCompound("hoseData");
		if (data == null) { data = new CompoundNBT(); }
		data.putInt("intensity", intensity);
		nbt.put("hoseData", data);
		stackIn.setTag(nbt);
	}
	public static int getIntensity(ItemStack stackIn) {
		CompoundNBT nbt = stackIn.getTag();
		if (nbt == null) { return 0; }
		CompoundNBT data = nbt.getCompound("hoseData");
		if (data == null) { return 0; }
		return data.getInt("intensity");
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		if (playerIn.isCrouching()) {

		} else {
			getConfiguration(playerIn.getHeldItem(handIn)).doShoot(worldIn, playerIn);
			//Configurations.CONFIG_DEFAULT.doShoot(worldIn, playerIn);
		}

		return ActionResult.resultSuccess(playerIn.getHeldItem(handIn));
	}

}
