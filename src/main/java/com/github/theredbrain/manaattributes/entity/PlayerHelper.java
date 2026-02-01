package com.github.theredbrain.manaattributes.entity;

import com.github.theredbrain.manaattributes.ManaAttributes;
import com.google.common.collect.HashMultimap;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class PlayerHelper {

	public static HashMultimap<Holder<Attribute>, AttributeModifier> getNaturalManaModifiers() {
		HashMultimap<Holder<Attribute>, AttributeModifier> hashMultimap = HashMultimap.create();
		hashMultimap.put(ManaAttributes.MANA_REGENERATION, new AttributeModifier(ManaAttributes.identifier("natural_mana_regeneration_modifier"), ManaAttributes.SERVER_CONFIG.natural_mana_regeneration, AttributeModifier.Operation.ADD_VALUE));
		hashMultimap.put(ManaAttributes.MAX_MANA, new AttributeModifier(ManaAttributes.identifier("natural_max_mana_modifier"), ManaAttributes.SERVER_CONFIG.natural_max_mana, AttributeModifier.Operation.ADD_VALUE));
		hashMultimap.put(ManaAttributes.DEPLETED_MANA_REGENERATION_DELAY_THRESHOLD, new AttributeModifier(ManaAttributes.identifier("natural_depleted_mana_regeneration_delay_threshold_modifier"), ManaAttributes.SERVER_CONFIG.natural_depleted_mana_regeneration_delay_threshold, AttributeModifier.Operation.ADD_VALUE));
		hashMultimap.put(ManaAttributes.MANA_REGENERATION_DELAY_THRESHOLD, new AttributeModifier(ManaAttributes.identifier("natural_mana_regeneration_delay_threshold_modifier"), ManaAttributes.SERVER_CONFIG.natural_mana_regeneration_delay_threshold, AttributeModifier.Operation.ADD_VALUE));
		hashMultimap.put(ManaAttributes.MANA_TICK_THRESHOLD, new AttributeModifier(ManaAttributes.identifier("natural_mana_tick_threshold_modifier"), ManaAttributes.SERVER_CONFIG.natural_mana_tick_threshold, AttributeModifier.Operation.ADD_VALUE));
		hashMultimap.put(ManaAttributes.RESERVED_MANA, new AttributeModifier(ManaAttributes.identifier("natural_reserved_mana_modifier"), ManaAttributes.SERVER_CONFIG.natural_reserved_mana, AttributeModifier.Operation.ADD_VALUE));
		return hashMultimap;
	}
}
