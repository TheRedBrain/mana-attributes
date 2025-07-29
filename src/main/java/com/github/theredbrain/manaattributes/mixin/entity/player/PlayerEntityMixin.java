package com.github.theredbrain.manaattributes.mixin.entity.player;

import com.github.theredbrain.manaattributes.ManaAttributes;
import com.github.theredbrain.manaattributes.entity.ManaUsingEntity;
import com.github.theredbrain.manaattributes.registry.GameRulesRegistry;
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
		if (this.getServer() != null && this.getServer().getGameRules().getBoolean(GameRulesRegistry.NATURAL_MANA_REGENERATION)) {
			this.getAttributes().addTemporaryModifiers(getNaturalManaRegenerationModifier());
		} else {
			this.getAttributes().removeModifiers(getNaturalManaRegenerationModifier());
		}
	}

	@Unique
	private HashMultimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> getNaturalManaRegenerationModifier() {
		HashMultimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> hashMultimap = HashMultimap.create();
		hashMultimap.put(ManaAttributes.MANA_REGENERATION, new EntityAttributeModifier(ManaAttributes.identifier("natural_mana_regeneration_modifier"), 1.0, EntityAttributeModifier.Operation.ADD_VALUE));
		return hashMultimap;
	}
}
