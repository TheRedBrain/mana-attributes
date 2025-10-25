package com.github.theredbrain.manaattributes.registry;

import com.github.theredbrain.manaattributes.ManaAttributes;
import com.github.theredbrain.manaattributes.ManaAttributesClient;
import com.github.theredbrain.manaattributes.config.ClientConfig;
import com.github.theredbrain.manaattributes.entity.ManaUsingEntity;
import com.github.theredbrain.resourcebarapi.ResourceBarAPI;
import com.github.theredbrain.resourcebarapi.ResourceBarAPIClient;
import me.fzzyhmstrs.fzzy_config.api.ConfigApi;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import org.apache.commons.lang3.tuple.MutablePair;

import java.util.ArrayList;

public class ClientEventsRegistry {
	private static final String RESOURCE_BAR_IDENTIFIER_STRING = ManaAttributes.MOD_ID + ":mana";
	private static final Identifier ICON_MANA_CONTAINER = ManaAttributes.identifier("hud/icon_mana_container");
	private static final Identifier ICON_MANA_FULL = ManaAttributes.identifier("hud/icon_mana_full");
	private static final Identifier ICON_MANA_HALF = ManaAttributes.identifier("hud/icon_mana_half");

	public static void initializeClientEvents() {
		HudElementRegistry.attachElementAfter(VanillaHudElements.HEALTH_BAR, ManaAttributes.identifier("mana"), ((matrixStack, delta) -> {
			MinecraftClient minecraftClient = MinecraftClient.getInstance();
			PlayerEntity playerEntity = minecraftClient.player;
			ClientConfig clientConfig = ManaAttributesClient.CLIENT_CONFIG;

			if (playerEntity != null && !minecraftClient.options.hudHidden) {
				double mana = MathHelper.ceil(((ManaUsingEntity) playerEntity).manaattributes$getMana());
				double maxMana = MathHelper.ceil(((ManaUsingEntity) playerEntity).manaattributes$getMaxMana());
				double unreservedMana = MathHelper.ceil(((ManaUsingEntity) playerEntity).manaattributes$getUnreservedMana());

				if (!playerEntity.isCreative() && maxMana > 0) {

					int u = playerEntity.getMaxAir();
					int v = Math.min(playerEntity.getAir(), u);
					int air_offset = clientConfig.dynamically_adjust_to_air_bar && playerEntity.isSubmergedIn(FluidTags.WATER) || v < u ? 10 : 0;
					int armor_offset = clientConfig.dynamically_adjust_to_armor_bar && playerEntity.getArmor() > 0 ? 10 : 0;

					MutablePair<Integer, Integer> originPos = ResourceBarAPIClient.getOriginPos(matrixStack, clientConfig.origin);

					if (clientConfig.mana_bar_display == ResourceBarAPI.ResourceBarDisplay.ICON && (mana < maxMana || clientConfig.show_full_mana_bar)) {
						ResourceBarAPIClient.drawIconResourceBar(
								minecraftClient,
								matrixStack,
								RESOURCE_BAR_IDENTIFIER_STRING,
								mana,
								maxMana,
								ICON_MANA_CONTAINER,
								ICON_MANA_FULL,
								ICON_MANA_HALF,
								new ArrayList<>(),// TODO reserved mana
								new ArrayList<>(),
								originPos.getLeft(),
								originPos.getRight(),
								clientConfig.iconBarSettings.offset_x.get(),
								clientConfig.iconBarSettings.offset_y.get() + air_offset + armor_offset,
								clientConfig.fill_direction,
								clientConfig.iconBarSettings.reverse_stack_direction.get(),
								clientConfig.iconBarSettings.max_icon_amount_per_bar.get()
						);
					} else if (clientConfig.mana_bar_display == ResourceBarAPI.ResourceBarDisplay.SMOOTH && (mana < maxMana || clientConfig.show_full_mana_bar)) {
						ResourceBarAPIClient.drawSmoothResourceBar(
								minecraftClient,
								matrixStack,
								RESOURCE_BAR_IDENTIFIER_STRING,
								new double[]{
										-1,
										-1,
										0,
										-91,
										-45,
										5,
										182,
										5,
										182,
										5,
										182,
										5,
										5,
										0,
										0
								},
								new Identifier[]{
										Identifier.of("manaattributes", "textures/gui/sprites/hud/horizontal_mana_background.png"),
										Identifier.of("manaattributes", "textures/gui/sprites/hud/horizontal_mana_progress_decrease_animation.png"),
										Identifier.of("manaattributes", "textures/gui/sprites/hud/horizontal_mana_progress_increase_animation.png"),
										Identifier.of("manaattributes", "textures/gui/sprites/hud/horizontal_mana_progress_increase_value.png"),
										Identifier.of("manaattributes", "textures/gui/sprites/hud/horizontal_mana_progress.png"),
										Identifier.of("manaattributes", "textures/gui/sprites/hud/horizontal_mana_reserved.png"),
										Identifier.of("manaattributes", "textures/gui/sprites/hud/horizontal_mana_overlay.png"),
										null
								},
								mana,
								maxMana,
								MathHelper.ceil(((ManaUsingEntity) playerEntity).manaattributes$getRegeneratedMana()),
								unreservedMana,
								originPos.getLeft(),
								originPos.getRight(),
								clientConfig.smoothBarSettings.positionSettings.offsets_x,
								clientConfig.smoothBarSettings.positionSettings.offsets_y,
								0,
								air_offset + armor_offset,
								clientConfig.fill_direction,
								clientConfig.smoothBarSettings.textureSettings.backgroundTextureSettings.texture_heights,
								clientConfig.smoothBarSettings.textureSettings.backgroundTextureSettings.texture_widths,
								clientConfig.smoothBarSettings.textureSettings.backgroundTextureSettings.texture_ids,
								clientConfig.smoothBarSettings.textureSettings.progressTextureSettings.offset_x,
								clientConfig.smoothBarSettings.textureSettings.progressTextureSettings.offset_y,
								clientConfig.smoothBarSettings.textureSettings.progressTextureSettings.texture_heights,
								clientConfig.smoothBarSettings.textureSettings.progressTextureSettings.texture_widths,
								clientConfig.smoothBarSettings.textureSettings.progressTextureSettings.progress_decrease_animation_texture_ids,
								clientConfig.smoothBarSettings.textureSettings.progressTextureSettings.progress_increase_animation_texture_ids,
								clientConfig.smoothBarSettings.textureSettings.progressTextureSettings.progress_increase_value_texture_ids,
								clientConfig.smoothBarSettings.textureSettings.progressTextureSettings.progress_texture_ids,
								clientConfig.smoothBarSettings.textureSettings.reservedTextureSettings.offset_x,
								clientConfig.smoothBarSettings.textureSettings.reservedTextureSettings.offset_y,
								clientConfig.smoothBarSettings.textureSettings.reservedTextureSettings.texture_heights,
								clientConfig.smoothBarSettings.textureSettings.reservedTextureSettings.texture_widths,
								clientConfig.smoothBarSettings.textureSettings.reservedTextureSettings.texture_ids,
								clientConfig.smoothBarSettings.show_current_value_overlay,
								clientConfig.smoothBarSettings.textureSettings.overlayTextureSettings.offset_x,
								clientConfig.smoothBarSettings.textureSettings.overlayTextureSettings.offset_y,
								clientConfig.smoothBarSettings.textureSettings.overlayTextureSettings.texture_heights,
								clientConfig.smoothBarSettings.textureSettings.overlayTextureSettings.texture_widths,
								clientConfig.smoothBarSettings.textureSettings.overlayTextureSettings.texture_ids,
								clientConfig.smoothBarSettings.show_icon,
								clientConfig.smoothBarSettings.iconTextureSettings.offset_x,
								clientConfig.smoothBarSettings.iconTextureSettings.offset_y,
								clientConfig.smoothBarSettings.iconTextureSettings.texture_heights,
								clientConfig.smoothBarSettings.iconTextureSettings.texture_widths,
								clientConfig.smoothBarSettings.iconTextureSettings.texture_ids,
								clientConfig.smoothBarSettings.enable_smooth_animation,
								clientConfig.smoothBarSettings.animationSettings.animation_interval,
								clientConfig.smoothBarSettings.animationSettings.max_value_change_is_animated
						);
					}
					if (clientConfig.numberSettings.show_number && (mana < maxMana || clientConfig.numberSettings.show_when_mana_full)) {
						ResourceBarAPIClient.drawResourceNumber(
								minecraftClient,
								minecraftClient.textRenderer,
								matrixStack,
								RESOURCE_BAR_IDENTIFIER_STRING,
								mana,
								maxMana,
								unreservedMana,
								originPos.getLeft(),
								originPos.getRight(),
								clientConfig.numberSettings.show_max_value,
								clientConfig.numberSettings.offset_x,
								clientConfig.numberSettings.offset_y + air_offset + armor_offset,
								clientConfig.numberSettings.color.toInt()
						);
					}
				}
			}
		}));
		ConfigApi.event().onUpdateClient((identifier, config) -> {
			if (identifier.equals(Identifier.of(ManaAttributes.MOD_ID, "client"))) {
				ResourceBarAPIClient.clearCache(
						RESOURCE_BAR_IDENTIFIER_STRING,
						new double[]{
								-1,
								-1,
								0,
								-91,
								-45,
								5,
								182,
								5,
								182,
								5,
								182,
								5,
								5,
								0,
								0
						},
						new Identifier[]{
								Identifier.of("manaattributes", "textures/gui/sprites/hud/horizontal_mana_background.png"),
								Identifier.of("manaattributes", "textures/gui/sprites/hud/horizontal_mana_progress_decrease_animation.png"),
								Identifier.of("manaattributes", "textures/gui/sprites/hud/horizontal_mana_progress_increase_animation.png"),
								Identifier.of("manaattributes", "textures/gui/sprites/hud/horizontal_mana_progress_increase_value.png"),
								Identifier.of("manaattributes", "textures/gui/sprites/hud/horizontal_mana_progress.png"),
								Identifier.of("manaattributes", "textures/gui/sprites/hud/horizontal_mana_reserved.png"),
								Identifier.of("manaattributes", "textures/gui/sprites/hud/horizontal_mana_overlay.png"),
								null
						}
				);
			}
		});
	}
}
