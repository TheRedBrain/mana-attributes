package com.github.theredbrain.manaattributes.mixin.server.network;

import com.github.theredbrain.manaattributes.entity.ManaUsingEntity;
import com.mojang.authlib.GameProfile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.ServerStatHandler;
import net.minecraft.stat.Stats;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends PlayerEntity implements ManaUsingEntity {

	public ServerPlayerEntityMixin(World world, GameProfile profile) {
		super(world, profile);
	}

	@Shadow
	public abstract ServerStatHandler getStatHandler();

	@Inject(method = "onSpawn", at = @At("TAIL"))
	public void manaattributes$onSpawn(CallbackInfo ci) {
		this.manaattributes$setApplyOldMana(false);
		if (this.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(Stats.LEAVE_GAME)) <= 0) {
			this.manaattributes$setApplyMaxMana(true);
		}
	}

}
