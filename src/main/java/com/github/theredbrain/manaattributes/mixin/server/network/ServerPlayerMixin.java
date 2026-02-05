package com.github.theredbrain.manaattributes.mixin.server.network;

import com.github.theredbrain.manaattributes.entity.ManaUsingEntity;
import com.mojang.authlib.GameProfile;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.ServerStatsCounter;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin extends Player implements ManaUsingEntity {

	public ServerPlayerMixin(Level world, GameProfile profile) {
		super(world, profile);
	}

	@Shadow
	public abstract ServerStatsCounter getStats();

	@Inject(method = "initInventoryMenu", at = @At("TAIL"))
	public void manaattributes$onSpawn(CallbackInfo ci) {
		if (this.getStats().getValue(Stats.CUSTOM.get(Stats.LEAVE_GAME)) <= 0) {
			this.manaattributes$setDelayMaxValueApplication(true);
		}
	}

}
