package com.github.theredbrain.manaattributes.mixin.entity.attribute;

import com.github.theredbrain.manaattributes.ManaAttributes;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(EntityAttributes.class)
public class EntityAttributesMixin {
	@Shadow
	private static EntityAttribute register(String id, EntityAttribute attribute) {
		throw new AssertionError();
	}

	static {
		ManaAttributes.MANA_REGENERATION = register(ManaAttributes.MOD_ID + ":generic.mana_regeneration", new ClampedEntityAttribute("attribute.name.generic.mana_regeneration", 0.0, -1024.0, 1024.0).setTracked(true));
		ManaAttributes.MAX_MANA = register(ManaAttributes.MOD_ID + ":generic.max_mana", new ClampedEntityAttribute("attribute.name.generic.max_mana", 0.0, 0.0, 1024.0).setTracked(true));
		ManaAttributes.MANA_REGENERATION_DELAY_THRESHOLD = register(ManaAttributes.MOD_ID + ":generic.mana_regeneration_delay_threshold", new ClampedEntityAttribute("attribute.name.generic.mana_regeneration_delay_threshold", 20.0, 0.0, 1024.0).setTracked(true));
		ManaAttributes.DEPLETED_MANA_REGENERATION_DELAY_THRESHOLD = register(ManaAttributes.MOD_ID + ":generic.depleted_mana_regeneration_delay_threshold", new ClampedEntityAttribute("attribute.name.generic.depleted_mana_regeneration_delay_threshold", 60.0, 0.0, 1024.0).setTracked(true));
		ManaAttributes.MANA_TICK_THRESHOLD = register(ManaAttributes.MOD_ID + ":generic.mana_tick_threshold", new ClampedEntityAttribute("attribute.name.generic.mana_tick_threshold", 20.0, 0.0, 1024.0).setTracked(true));
	}
}
