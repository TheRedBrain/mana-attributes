package com.github.theredbrain.manaattributes.world.item.enchantment;

import com.github.theredbrain.manaattributes.entity.ManaUsingEntity;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public record AddManaEnchantmentEntityEffect(LevelBasedValue amount) implements EnchantmentEntityEffect {
	public static final MapCodec<AddManaEnchantmentEntityEffect> CODEC = RecordCodecBuilder.mapCodec(
			instance -> instance.group(LevelBasedValue.CODEC.fieldOf("amount").forGetter(AddManaEnchantmentEntityEffect::amount)).apply(instance, AddManaEnchantmentEntityEffect::new)
	);

	@Override
	public void apply(ServerLevel serverLevel, int i, EnchantedItemInUse enchantedItemInUse, Entity entity, Vec3 vec3) {
		if (entity instanceof ManaUsingEntity manaUsingEntity) {
			manaUsingEntity.manaattributes$addMana(this.amount.calculate(i));
		}
	}

	@Override
	public MapCodec<AddManaEnchantmentEntityEffect> codec() {
		return CODEC;
	}
}
