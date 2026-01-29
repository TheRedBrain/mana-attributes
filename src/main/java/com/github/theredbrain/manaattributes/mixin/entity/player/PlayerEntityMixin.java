package com.github.theredbrain.manaattributes.mixin.entity.player;

import com.github.theredbrain.manaattributes.ManaAttributes;
import com.github.theredbrain.manaattributes.entity.ManaUsingEntity;
import com.google.common.collect.HashMultimap;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class PlayerEntityMixin extends LivingEntity implements ManaUsingEntity {

	protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, Level world) {
		super(entityType, world);
	}

	@Inject(method = "tick", at = @At("TAIL"))
	public void manaattributes$tick(CallbackInfo ci) {
		if (!this.level().isClientSide()) {
			this.getAttributes().addTransientAttributeModifiers(getNaturalManaModifiers());
		}
	}

	@Unique
	private HashMultimap<Holder<Attribute>, AttributeModifier> getNaturalManaModifiers() {
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
