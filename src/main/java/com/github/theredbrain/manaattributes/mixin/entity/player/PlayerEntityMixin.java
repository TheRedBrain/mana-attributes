package com.github.theredbrain.manaattributes.mixin.entity.player;

import com.github.theredbrain.manaattributes.ManaAttributes;
import com.github.theredbrain.manaattributes.entity.ManaUsingEntity;
import com.google.common.collect.HashMultimap;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity implements ManaUsingEntity {

	protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
		super(entityType, world);
	}

	@Inject(method = "tick", at = @At("TAIL"))
	public void manaattributes$tick(CallbackInfo ci) {
		if (!this.getEntityWorld().isClient()) {
			this.getAttributes().addTemporaryModifiers(getNaturalManaModifiers());
		}
	}

	@Unique
	private HashMultimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> getNaturalManaModifiers() {
		HashMultimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> hashMultimap = HashMultimap.create();
		hashMultimap.put(ManaAttributes.MANA_REGENERATION, new EntityAttributeModifier(ManaAttributes.identifier("natural_mana_regeneration_modifier"), ManaAttributes.SERVER_CONFIG.natural_mana_regeneration, EntityAttributeModifier.Operation.ADD_VALUE));
		hashMultimap.put(ManaAttributes.MAX_MANA, new EntityAttributeModifier(ManaAttributes.identifier("natural_max_mana_modifier"), ManaAttributes.SERVER_CONFIG.natural_max_mana, EntityAttributeModifier.Operation.ADD_VALUE));
		hashMultimap.put(ManaAttributes.DEPLETED_MANA_REGENERATION_DELAY_THRESHOLD, new EntityAttributeModifier(ManaAttributes.identifier("natural_depleted_mana_regeneration_delay_threshold_modifier"), ManaAttributes.SERVER_CONFIG.natural_depleted_mana_regeneration_delay_threshold, EntityAttributeModifier.Operation.ADD_VALUE));
		hashMultimap.put(ManaAttributes.MANA_REGENERATION_DELAY_THRESHOLD, new EntityAttributeModifier(ManaAttributes.identifier("natural_mana_regeneration_delay_threshold_modifier"), ManaAttributes.SERVER_CONFIG.natural_mana_regeneration_delay_threshold, EntityAttributeModifier.Operation.ADD_VALUE));
		hashMultimap.put(ManaAttributes.MANA_TICK_THRESHOLD, new EntityAttributeModifier(ManaAttributes.identifier("natural_mana_tick_threshold_modifier"), ManaAttributes.SERVER_CONFIG.natural_mana_tick_threshold, EntityAttributeModifier.Operation.ADD_VALUE));
		hashMultimap.put(ManaAttributes.RESERVED_MANA, new EntityAttributeModifier(ManaAttributes.identifier("natural_reserved_mana_modifier"), ManaAttributes.SERVER_CONFIG.natural_reserved_mana, EntityAttributeModifier.Operation.ADD_VALUE));
		return hashMultimap;
	}
}
