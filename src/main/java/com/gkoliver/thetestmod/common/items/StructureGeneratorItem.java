package com.gkoliver.thetestmod.common.items;

import java.util.Random;
import java.util.function.Supplier;

import com.gkoliver.thetestmod.common.nonidealstructures.FakeEndCityStructure;
import com.gkoliver.thetestmod.common.nonidealstructures.FakeMossBolder;
import com.mojang.serialization.Dynamic;

import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.HugeFungusConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.server.ServerWorld;

public class StructureGeneratorItem extends Item {
	private final Supplier<ConfiguredFeature<HugeFungusConfig, ?>> field_235497_b_ = ()->{
		return Feature.field_236281_L_.withConfiguration(HugeFungusConfig.field_236301_d_);
	}; 
	private static final Random rand = new Random();
	public StructureGeneratorItem(Properties properties) {
		super(properties);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		if (worldIn instanceof ServerWorld) {
			FakeEndCityStructure structure = new FakeEndCityStructure();
			BlockPos posIn = new BlockPos(playerIn.getPosX(), playerIn.getPosYEye() - (double)0.1F, playerIn.getPosZ());
			if (!worldIn.isRemote()) {
				FakeMossBolder bolder = new FakeMossBolder();
				bolder.generate(worldIn, posIn);
			}
			playerIn.getCooldownTracker().setCooldown(this, 50);
			
			return ActionResult.resultSuccess(playerIn.getHeldItem(handIn));
		}
		
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}

}
