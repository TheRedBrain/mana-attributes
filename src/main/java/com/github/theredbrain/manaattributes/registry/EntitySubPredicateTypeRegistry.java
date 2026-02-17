package com.github.theredbrain.manaattributes.registry;

import com.github.theredbrain.manaattributes.ManaAttributes;
import com.github.theredbrain.manaattributes.advancements.criterion.ManaUsingEntityPredicate;
import com.mojang.serialization.MapCodec;
import net.minecraft.advancements.criterion.EntitySubPredicate;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

public class EntitySubPredicateTypeRegistry {

	public static void init() {
	}

	private static <T extends EntitySubPredicate> MapCodec<T> register(Identifier id, MapCodec<T> mapCodec) {
		return Registry.register(
				BuiltInRegistries.ENTITY_SUB_PREDICATE_TYPE,
				id,
				mapCodec
		);
	}

	static {
		ManaAttributes.MANA_USING_ENTITY_PREDICATE = register(ManaAttributes.identifier("mana_using_entity"), ManaUsingEntityPredicate.CODEC);
	}
}
