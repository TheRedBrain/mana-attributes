package com.github.theredbrain.manaattributes.mixin.entity.player;

import com.github.theredbrain.manaattributes.ManaAttributes;
import com.github.theredbrain.manaattributes.entity.ManaUsingEntity;
import com.github.theredbrain.manaattributes.entity.PlayerHelper;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public abstract class PlayerEntityMixin extends LivingEntity implements ManaUsingEntity {

	protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, Level world) {
		super(entityType, world);
	}

	@Inject(method = "createAttributes", at = @At("RETURN"))
	private static void manaattributes$createAttributes(CallbackInfoReturnable<AttributeSupplier.Builder> cir) {
		cir.getReturnValue()
				.add(ManaAttributes.MANA_REGENERATION_DELAY_THRESHOLD)
				.add(ManaAttributes.DEPLETED_MANA_REGENERATION_DELAY_THRESHOLD)
				.add(ManaAttributes.MANA_TICK_THRESHOLD)
		;
	}

	@Inject(method = "tick", at = @At("TAIL"))
	public void manaattributes$tick(CallbackInfo ci) {
		if (!this.level().isClientSide()) {
			this.getAttributes().addTransientAttributeModifiers(PlayerHelper.getNaturalManaModifiers());
		}
	}
}
