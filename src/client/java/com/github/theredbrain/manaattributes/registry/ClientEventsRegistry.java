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

	private static final Identifier[] STAMINA_TEXTURES = {
			ManaAttributes.identifier("textures/gui/sprites/hud/horizontal_mana_background_left_end.png"),
			ManaAttributes.identifier("textures/gui/sprites/hud/horizontal_mana_background_middle_segment.png"),
			ManaAttributes.identifier("textures/gui/sprites/hud/horizontal_mana_background_right_end.png"),
			ManaAttributes.identifier("textures/gui/sprites/hud/vertical_mana_background_top_end.png"),
			ManaAttributes.identifier("textures/gui/sprites/hud/vertical_mana_background_middle_segment.png"),
			ManaAttributes.identifier("textures/gui/sprites/hud/vertical_mana_background_bottom_end.png"),

			ManaAttributes.identifier("textures/gui/sprites/hud/horizontal_mana_progress_left_end.png"),
			ManaAttributes.identifier("textures/gui/sprites/hud/horizontal_mana_progress_middle_segment.png"),
			ManaAttributes.identifier("textures/gui/sprites/hud/horizontal_mana_progress_right_end.png"),
			ManaAttributes.identifier("textures/gui/sprites/hud/vertical_mana_progress_top_end.png"),
			ManaAttributes.identifier("textures/gui/sprites/hud/vertical_mana_progress_middle_segment.png"),
			ManaAttributes.identifier("textures/gui/sprites/hud/vertical_mana_progress_bottom_end.png"),

			ManaAttributes.identifier("textures/gui/sprites/hud/horizontal_mana_progress_decrease_animation_left_end.png"),
			ManaAttributes.identifier("textures/gui/sprites/hud/horizontal_mana_progress_decrease_animation_middle_segment.png"),
			ManaAttributes.identifier("textures/gui/sprites/hud/horizontal_mana_progress_decrease_animation_right_end.png"),
			ManaAttributes.identifier("textures/gui/sprites/hud/vertical_mana_progress_decrease_animation_top_end.png"),
			ManaAttributes.identifier("textures/gui/sprites/hud/vertical_mana_progress_decrease_animation_middle_segment.png"),
			ManaAttributes.identifier("textures/gui/sprites/hud/vertical_mana_progress_decrease_animation_bottom_end.png"),

			ManaAttributes.identifier("textures/gui/sprites/hud/horizontal_mana_progress_increase_value_left_end.png"),
			ManaAttributes.identifier("textures/gui/sprites/hud/horizontal_mana_progress_increase_value_middle_segment.png"),
			ManaAttributes.identifier("textures/gui/sprites/hud/horizontal_mana_progress_increase_value_right_end.png"),
			ManaAttributes.identifier("textures/gui/sprites/hud/vertical_mana_progress_increase_value_top_end.png"),
			ManaAttributes.identifier("textures/gui/sprites/hud/vertical_mana_progress_increase_value_middle_segment.png"),
			ManaAttributes.identifier("textures/gui/sprites/hud/vertical_mana_progress_increase_value_bottom_end.png"),

			ManaAttributes.identifier("textures/gui/sprites/hud/horizontal_mana_progress_increase_animation_left_end.png"),
			ManaAttributes.identifier("textures/gui/sprites/hud/horizontal_mana_progress_increase_animation_middle_segment.png"),
			ManaAttributes.identifier("textures/gui/sprites/hud/horizontal_mana_progress_increase_animation_right_end.png"),
			ManaAttributes.identifier("textures/gui/sprites/hud/vertical_mana_progress_increase_animation_top_end.png"),
			ManaAttributes.identifier("textures/gui/sprites/hud/vertical_mana_progress_increase_animation_middle_segment.png"),
			ManaAttributes.identifier("textures/gui/sprites/hud/vertical_mana_progress_increase_animation_bottom_end.png"),

			ManaAttributes.identifier("textures/gui/sprites/hud/horizontal_mana_reserved_left_end.png"),
			ManaAttributes.identifier("textures/gui/sprites/hud/horizontal_mana_reserved_middle_segment.png"),
			ManaAttributes.identifier("textures/gui/sprites/hud/horizontal_mana_reserved_right_end.png"),
			ManaAttributes.identifier("textures/gui/sprites/hud/vertical_mana_reserved_top_end.png"),
			ManaAttributes.identifier("textures/gui/sprites/hud/vertical_mana_reserved_middle_segment.png"),
			ManaAttributes.identifier("textures/gui/sprites/hud/vertical_mana_reserved_bottom_end.png"),

			ManaAttributes.identifier("textures/gui/sprites/hud/horizontal_mana_overlay.png"),
			ManaAttributes.identifier("textures/gui/sprites/hud/vertical_mana_overlay.png")
	};

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
						new double[]{-1, -1, 0, 0, 0, 0, 0, 0},
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
						clientConfig.positionSettings.is_centered,
						STAMINA_TEXTURES,
						clientConfig.fill_direction,
						clientConfig.textureSettings.backgroundTextureSettings.middle_segment_amounts,
						clientConfig.textureSettings.backgroundTextureSettings.horizontalTextureSettings.horizontal_left_end_width,
						clientConfig.textureSettings.backgroundTextureSettings.horizontalTextureSettings.horizontal_middle_segment_width,
						clientConfig.textureSettings.backgroundTextureSettings.horizontalTextureSettings.horizontal_right_end_width,
						clientConfig.textureSettings.backgroundTextureSettings.horizontalTextureSettings.horizontal_height,
						clientConfig.textureSettings.backgroundTextureSettings.verticalTextureSettings.vertical_width,
						clientConfig.textureSettings.backgroundTextureSettings.verticalTextureSettings.vertical_top_end_height,
						clientConfig.textureSettings.backgroundTextureSettings.verticalTextureSettings.vertical_middle_segment_height,
						clientConfig.textureSettings.backgroundTextureSettings.verticalTextureSettings.vertical_bottom_end_height,
						clientConfig.textureSettings.progressTextureSettings.offset_x,
						clientConfig.textureSettings.progressTextureSettings.offset_y,
						clientConfig.textureSettings.progressTextureSettings.middle_segment_amounts,
						clientConfig.textureSettings.progressTextureSettings.horizontalTextureSettings.horizontal_left_end_width,
						clientConfig.textureSettings.progressTextureSettings.horizontalTextureSettings.horizontal_middle_segment_width,
						clientConfig.textureSettings.progressTextureSettings.horizontalTextureSettings.horizontal_right_end_width,
						clientConfig.textureSettings.progressTextureSettings.horizontalTextureSettings.horizontal_height,
						clientConfig.textureSettings.progressTextureSettings.verticalTextureSettings.vertical_width,
						clientConfig.textureSettings.progressTextureSettings.verticalTextureSettings.vertical_top_end_height,
						clientConfig.textureSettings.progressTextureSettings.verticalTextureSettings.vertical_middle_segment_height,
						clientConfig.textureSettings.progressTextureSettings.verticalTextureSettings.vertical_bottom_end_height,
						clientConfig.textureSettings.reservedTextureSettings.offset_x,
						clientConfig.textureSettings.reservedTextureSettings.offset_y,
						clientConfig.textureSettings.reservedTextureSettings.middle_segment_amounts,
						clientConfig.textureSettings.reservedTextureSettings.horizontalTextureSettings.horizontal_left_end_width,
						clientConfig.textureSettings.reservedTextureSettings.horizontalTextureSettings.horizontal_middle_segment_width,
						clientConfig.textureSettings.reservedTextureSettings.horizontalTextureSettings.horizontal_right_end_width,
						clientConfig.textureSettings.reservedTextureSettings.horizontalTextureSettings.horizontal_height,
						clientConfig.textureSettings.reservedTextureSettings.verticalTextureSettings.vertical_width,
						clientConfig.textureSettings.reservedTextureSettings.verticalTextureSettings.vertical_top_end_height,
						clientConfig.textureSettings.reservedTextureSettings.verticalTextureSettings.vertical_middle_segment_height,
						clientConfig.textureSettings.reservedTextureSettings.verticalTextureSettings.vertical_bottom_end_height,
						clientConfig.show_current_value_overlay,
						clientConfig.textureSettings.overlayTextureSettings.offset_x,
						clientConfig.textureSettings.overlayTextureSettings.offset_y,
						clientConfig.textureSettings.overlayTextureSettings.horizontal_width,
						clientConfig.textureSettings.overlayTextureSettings.horizontal_height,
						clientConfig.textureSettings.overlayTextureSettings.vertical_width,
						clientConfig.textureSettings.overlayTextureSettings.vertical_height,
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
			if (identifier.equals(Identifier.of(RESOURCE_BAR_IDENTIFIER_STRING))) {
				ResourceBarAPIClient.clearCache(RESOURCE_BAR_IDENTIFIER_STRING, new double[]{-1, -1, 0, 0, 0, 0, 0, 0});
			}
		});
	}
}
