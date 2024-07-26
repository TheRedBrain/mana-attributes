package com.github.theredbrain.manaattributes.mixin.entity.player;

import com.github.theredbrain.manaattributes.entity.ManaUsingEntity;
import com.github.theredbrain.manaattributes.registry.GameRulesRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity implements ManaUsingEntity {

	protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	public float manaattributes$getRegeneratedMana() {
		return Math.max(this.manaattributes$getManaRegeneration(), (this.getServer() != null && this.getServer().getGameRules().getBoolean(GameRulesRegistry.NATURAL_MANA_REGENERATION) ? 1.0F : 0.0F));
	}
}
