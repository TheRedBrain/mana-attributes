package com.github.theredbrain.manaattributes.registry;

import com.github.theredbrain.manaattributes.ManaAttributes;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class EntityAttributesRegistry {

    public static final EntityAttribute MANA_REGENERATION = new ClampedEntityAttribute("attribute.name.generic.mana_regeneration", 0.0, -1024.0, 1024.0).setTracked(true);
    public static final EntityAttribute MAX_MANA = new ClampedEntityAttribute("attribute.name.generic.max_mana", 0.0, 0.0, 1024.0).setTracked(true);
    public static final EntityAttribute MANA_REGENERATION_DELAY_THRESHOLD = new ClampedEntityAttribute("attribute.name.generic.mana_regeneration_delay_threshold", 60.0, 0.0, 1024.0).setTracked(true);
    public static final EntityAttribute MANA_TICK_THRESHOLD = new ClampedEntityAttribute("attribute.name.generic.mana_tick_threshold", 20.0, 0.0, 1024.0).setTracked(true);

    public static void registerAttributes() {

        Registry.register(Registries.ATTRIBUTE, ManaAttributes.identifier("generic.mana_regeneration"), MANA_REGENERATION);
        Registry.register(Registries.ATTRIBUTE, ManaAttributes.identifier("generic.max_mana"), MAX_MANA);
        Registry.register(Registries.ATTRIBUTE, ManaAttributes.identifier("generic.mana_regeneration_delay_threshold"), MANA_REGENERATION_DELAY_THRESHOLD);
        Registry.register(Registries.ATTRIBUTE, ManaAttributes.identifier("generic.mana_tick_threshold"), MANA_TICK_THRESHOLD);
    }
}
