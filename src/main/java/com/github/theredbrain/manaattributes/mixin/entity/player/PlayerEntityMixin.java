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
		this.getAttributes().addTemporaryModifiers(getNaturalManaModifiers(this.getWorld()));
	}

	@Unique
	private HashMultimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> getNaturalManaModifiers(World world) {
		HashMultimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> hashMultimap = HashMultimap.create();
		hashMultimap.put(ManaAttributes.MANA_REGENERATION, new EntityAttributeModifier(ManaAttributes.identifier("natural_mana_regeneration_modifier"), world.getGameRules().get(GameRulesRegistry.NATURAL_MANA_REGENERATION).get(), EntityAttributeModifier.Operation.ADD_VALUE));
		hashMultimap.put(ManaAttributes.MAX_MANA, new EntityAttributeModifier(ManaAttributes.identifier("natural_maximum_mana_modifier"), world.getGameRules().get(GameRulesRegistry.NATURAL_MAXIMUM_MANA).get(), EntityAttributeModifier.Operation.ADD_VALUE));
		return hashMultimap;
	}
}
