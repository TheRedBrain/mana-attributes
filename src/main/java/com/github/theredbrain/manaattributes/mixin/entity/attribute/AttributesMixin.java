package com.github.theredbrain.manaattributes.mixin.entity.attribute;

import com.github.theredbrain.manaattributes.ManaAttributes;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Attributes.class)
public class AttributesMixin {
	static {
		ManaAttributes.MANA_REGENERATION = Registry.registerForHolder(BuiltInRegistries.ATTRIBUTE, ManaAttributes.identifier("mana_regeneration"), new RangedAttribute("attribute.name.mana_regeneration", 0.0, -1024.0, 1024.0).setSyncable(true));
		ManaAttributes.MAX_MANA = Registry.registerForHolder(BuiltInRegistries.ATTRIBUTE, ManaAttributes.identifier("max_mana"), new RangedAttribute("attribute.name.max_mana", 0.0, 0.0, 1024.0).setSyncable(true));
		ManaAttributes.MANA_REGENERATION_DELAY_THRESHOLD = Registry.registerForHolder(BuiltInRegistries.ATTRIBUTE, ManaAttributes.identifier("mana_regeneration_delay_threshold"), new RangedAttribute("attribute.name.mana_regeneration_delay_threshold", 20.0, 0.0, 1024.0).setSyncable(true));
		ManaAttributes.DEPLETED_MANA_REGENERATION_DELAY_THRESHOLD = Registry.registerForHolder(BuiltInRegistries.ATTRIBUTE, ManaAttributes.identifier("depleted_mana_regeneration_delay_threshold"), new RangedAttribute("attribute.name.depleted_mana_regeneration_delay_threshold", 60.0, 0.0, 1024.0).setSyncable(true));
		ManaAttributes.MANA_TICK_THRESHOLD = Registry.registerForHolder(BuiltInRegistries.ATTRIBUTE, ManaAttributes.identifier("mana_tick_threshold"), new RangedAttribute("attribute.name.mana_tick_threshold", 20.0, 0.0, 1024.0).setSyncable(true));
		ManaAttributes.RESERVED_MANA = Registry.registerForHolder(BuiltInRegistries.ATTRIBUTE, ManaAttributes.identifier("reserved_mana"), new RangedAttribute("attribute.name.reserved_mana", 0.0F, 0.0F, 100.0F).setSyncable(true));
	}
}
