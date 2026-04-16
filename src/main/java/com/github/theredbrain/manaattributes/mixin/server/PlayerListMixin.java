package com.github.theredbrain.manaattributes.mixin.server;

import com.github.theredbrain.manaattributes.entity.ManaUsingEntity;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import net.minecraft.stats.Stats;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PlayerList.class)
public class PlayerListMixin {

	@WrapOperation(method = "placeNewPlayer", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerPlayer;initInventoryMenu()V"))
	protected void manaattributes$placeNewPlayer_wrap_initInventoryMenu(ServerPlayer instance, Operation<Void> original) {
		((ManaUsingEntity) instance).manaattributes$setDelayManaTick(true);
		if (instance.getStats().getValue(Stats.CUSTOM.get(Stats.LEAVE_GAME)) <= 0) {
			((ManaUsingEntity) instance).manaattributes$setDelayMaxValueApplication(true);
		}
		original.call(instance);
	}

	@WrapOperation(method = "respawn", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerPlayer;initInventoryMenu()V"))
	protected void manaattributes$respawn_wrap_initInventoryMenu(ServerPlayer instance, Operation<Void> original) {
		((ManaUsingEntity) instance).manaattributes$setDelayManaTick(true);
		((ManaUsingEntity) instance).manaattributes$setDelayMaxValueApplication(true);
		original.call(instance);
	}
}
