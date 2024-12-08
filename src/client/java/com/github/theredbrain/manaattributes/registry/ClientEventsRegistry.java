package com.github.theredbrain.manaattributes.registry;

import com.github.theredbrain.manaattributes.ManaAttributes;
import com.github.theredbrain.manaattributes.ManaAttributesClient;
import com.github.theredbrain.manaattributes.config.ClientConfig;
import com.github.theredbrain.manaattributes.entity.ManaUsingEntity;
import com.github.theredbrain.resourcebarapi.ResourceBarAPIClient;
import me.fzzyhmstrs.fzzy_config.api.ConfigApi;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class ClientEventsRegistry {
	private static final String RESOURCE_BAR_IDENTIFIER_STRING = ManaAttributes.MOD_ID + ":mana";

	public static void initializeClientEvents() {
		HudRenderCallback.EVENT.register((matrixStack, delta) -> {
			MinecraftClient minecraftClient = MinecraftClient.getInstance();
			PlayerEntity playerEntity = minecraftClient.player;
			ClientConfig clientConfig = ManaAttributesClient.CLIENT_CONFIG;

			if (playerEntity != null) {
				double mana = MathHelper.ceil(((ManaUsingEntity) playerEntity).manaattributes$getMana());
				double maxMana = MathHelper.ceil(((ManaUsingEntity) playerEntity).manaattributes$getMaxMana());

				ResourceBarAPIClient.drawResourceBar(
						minecraftClient,
						minecraftClient.textRenderer,
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
						clientConfig.show_mana_bar && maxMana > 0 && (mana < maxMana || clientConfig.show_full_mana_bar),
						mana,
						maxMana,
						MathHelper.ceil(((ManaUsingEntity) playerEntity).manaattributes$getRegeneratedMana()),
						MathHelper.ceil(((ManaUsingEntity) playerEntity).manaattributes$getUnreservedMana()),
						clientConfig.positionSettings.origin,
						clientConfig.positionSettings.offsets_x,
						clientConfig.positionSettings.offsets_y,
						0,
						(clientConfig.positionSettings.dynamically_adjust_to_armor_bar && playerEntity.getArmor() > 0) ? 10 : 0,
						clientConfig.fill_direction,
						clientConfig.textureSettings.backgroundTextureSettings.texture_heights,
						clientConfig.textureSettings.backgroundTextureSettings.texture_widths,
						clientConfig.textureSettings.backgroundTextureSettings.texture_ids,
						clientConfig.textureSettings.progressTextureSettings.offset_x,
						clientConfig.textureSettings.progressTextureSettings.offset_y,
						clientConfig.textureSettings.progressTextureSettings.texture_heights,
						clientConfig.textureSettings.progressTextureSettings.texture_widths,
						clientConfig.textureSettings.progressTextureSettings.progress_decrease_animation_texture_ids,
						clientConfig.textureSettings.progressTextureSettings.progress_increase_animation_texture_ids,
						clientConfig.textureSettings.progressTextureSettings.progress_increase_value_texture_ids,
						clientConfig.textureSettings.progressTextureSettings.progress_texture_ids,
						clientConfig.textureSettings.reservedTextureSettings.offset_x,
						clientConfig.textureSettings.reservedTextureSettings.offset_y,
						clientConfig.textureSettings.reservedTextureSettings.texture_heights,
						clientConfig.textureSettings.reservedTextureSettings.texture_widths,
						clientConfig.textureSettings.reservedTextureSettings.texture_ids,
						clientConfig.show_current_value_overlay,
						clientConfig.textureSettings.overlayTextureSettings.offset_x,
						clientConfig.textureSettings.overlayTextureSettings.offset_y,
						clientConfig.textureSettings.overlayTextureSettings.texture_heights,
						clientConfig.textureSettings.overlayTextureSettings.texture_widths,
						clientConfig.textureSettings.overlayTextureSettings.texture_ids,
						clientConfig.show_icon && maxMana > 0,
						clientConfig.iconTextureSettings.offset_x,
						clientConfig.iconTextureSettings.offset_y,
						clientConfig.iconTextureSettings.texture_heights,
						clientConfig.iconTextureSettings.texture_widths,
						clientConfig.iconTextureSettings.texture_ids,
						clientConfig.enable_smooth_animation,
						clientConfig.animationSettings.animation_interval,
						clientConfig.animationSettings.max_value_change_is_animated,
						clientConfig.show_number && maxMana > 0 && (mana < maxMana || clientConfig.numberSettings.show_when_mana_full),
						clientConfig.numberSettings.show_max_value,
						clientConfig.numberSettings.offset_x,
						clientConfig.numberSettings.offset_y - ((clientConfig.positionSettings.dynamically_adjust_to_armor_bar && playerEntity.getArmor() > 0) ? 10 : 0),
						clientConfig.numberSettings.color.toInt()
				);
			}
		});
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