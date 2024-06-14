package com.github.theredbrain.manaattributes.mixin.entity.attribute;

import com.github.theredbrain.manaattributes.ManaAttributes;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(EntityAttributes.class)
public class EntityAttributesMixin {
    static {
        ManaAttributes.MANA_REGENERATION = Registry.registerReference(Registries.ATTRIBUTE, ManaAttributes.identifier("generic.mana_regeneration"), new ClampedEntityAttribute("attribute.name.generic.mana_regeneration", 0.0, -1024.0, 1024.0).setTracked(true));
        ManaAttributes.MAX_MANA = Registry.registerReference(Registries.ATTRIBUTE, ManaAttributes.identifier("generic.max_mana"), new ClampedEntityAttribute("attribute.name.generic.max_mana", 0.0, 0.0, 1024.0).setTracked(true));
        ManaAttributes.MANA_REGENERATION_DELAY_THRESHOLD = Registry.registerReference(Registries.ATTRIBUTE, ManaAttributes.identifier("generic.mana_regeneration_delay_threshold"), new ClampedEntityAttribute("attribute.name.generic.mana_regeneration_delay_threshold", 60.0, 0.0, 1024.0).setTracked(true));
        ManaAttributes.MANA_TICK_THRESHOLD = Registry.registerReference(Registries.ATTRIBUTE, ManaAttributes.identifier("generic.mana_tick_threshold"), new ClampedEntityAttribute("attribute.name.generic.mana_tick_threshold", 20.0, 0.0, 1024.0).setTracked(true));
    }
}
