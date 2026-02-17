package com.github.theredbrain.manaattributes.registry;

import com.github.theredbrain.manaattributes.ManaAttributes;
import com.github.theredbrain.manaattributes.world.item.enchantment.AddManaEnchantmentEntityEffect;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;

public class EnchantmentEntityEffectRegistry {

	public static void init() {
	}

	private static <T extends EnchantmentEntityEffect> MapCodec<T> register(Identifier id, MapCodec<T> codec) {
		return Registry.register(BuiltInRegistries.ENCHANTMENT_ENTITY_EFFECT_TYPE, id, codec);
	}

	static {
		ManaAttributes.ADD_MANA = register(ManaAttributes.identifier("add_mana"), AddManaEnchantmentEntityEffect.CODEC);
	}

}
