package com.github.theredbrain.manaattributes.mixin.server;

import com.github.theredbrain.manaattributes.entity.ManaUsingEntity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerList.class)
public class PlayerListMixin {

	@Inject(method = "respawn", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerPlayer;setHealth(F)V"))
	protected void manaattributes$respawnPlayer(ServerPlayer player, boolean alive, Entity.RemovalReason removalReason, CallbackInfoReturnable<ServerPlayer> cir) {
		((ManaUsingEntity) player).manaattributes$setDelayMaxValueApplication(true);
	}

}
