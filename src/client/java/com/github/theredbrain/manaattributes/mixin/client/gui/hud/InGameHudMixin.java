package com.github.theredbrain.manaattributes.mixin.client.gui.hud;

import com.github.theredbrain.manaattributes.ManaAttributes;
import com.github.theredbrain.manaattributes.ManaAttributesClient;
import com.github.theredbrain.manaattributes.config.ClientConfig;
import com.github.theredbrain.manaattributes.entity.ManaUsingEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(InGameHud.class)
public abstract class InGameHudMixin {

	@Shadow
	protected abstract PlayerEntity getCameraPlayer();

	@Shadow
	@Final
	private MinecraftClient client;

	@Shadow
	public abstract TextRenderer getTextRenderer();

	@Unique
	private static final Identifier[] MANA_TEXTURES = {
			ManaAttributes.identifier("textures/gui/sprites/hud/horizontal_mana_background.png"),
			ManaAttributes.identifier("textures/gui/sprites/hud/horizontal_mana_progress.png"),
			ManaAttributes.identifier("textures/gui/sprites/hud/horizontal_mana_overlay.png"),
			ManaAttributes.identifier("textures/gui/sprites/hud/vertical_mana_background.png"),
			ManaAttributes.identifier("textures/gui/sprites/hud/vertical_mana_progress.png"),
			ManaAttributes.identifier("textures/gui/sprites/hud/vertical_mana_overlay.png")
	};

	@Unique
	private int oldNormalizedManaRatio = -1;

	@Unique
	private int oldMaxMana = -1;

	@Unique
	private int manaBarAnimationCounter = 0;

	@Inject(method = "renderStatusBars", at = @At("RETURN"))
	private void manaattributes$renderStatusBars(DrawContext context, CallbackInfo ci) {
		if (ManaAttributesClient.clientConfig.show_mana_bar) {
			
			this.manaAttributes$renderManaBar(context, this.getCameraPlayer());
		}
	}

	@Unique
	private void manaAttributes$renderManaBar(DrawContext context, PlayerEntity player) {

		var clientConfig = ManaAttributesClient.clientConfig;

		int mana = MathHelper.ceil(((ManaUsingEntity) player).manaattributes$getMana());
		int maxMana = MathHelper.ceil(((ManaUsingEntity) player).manaattributes$getMaxMana());

		int manaBarX = context.getScaledWindowWidth() / 2 + clientConfig.x_offset;
		int manaBarY = context.getScaledWindowHeight() + clientConfig.y_offset;
		String manaBarNumberString = String.valueOf(mana);
		int manaBarNumberX = (context.getScaledWindowWidth() - this.getTextRenderer().getWidth(manaBarNumberString)) / 2 + clientConfig.number_x_offset + (clientConfig.dynamically_adjust_to_armor_bar && player.getArmor() > 0 ? 10 : 0);
		int manaBarNumberY = context.getScaledWindowHeight() / 2 + clientConfig.number_y_offset;

		if (maxMana > 0 && (mana < maxMana || clientConfig.show_full_mana_bar)) {

			this.client.getProfiler().push("mana");
			this.manaAttributes$drawEffectBuildUpElement(
					context,
					mana,
					maxMana,
					MathHelper.ceil(((ManaUsingEntity) player).manaattributes$getRegeneratedMana()),
					manaBarX,
					manaBarY,
					clientConfig.fill_direction,
					clientConfig.background_middle_segment_amount,
					clientConfig.horizontal_background_left_end_width,
					clientConfig.horizontal_background_middle_segment_width,
					clientConfig.horizontal_background_right_end_width,
					clientConfig.horizontal_background_height,
					clientConfig.vertical_background_width,
					clientConfig.vertical_background_top_end_height,
					clientConfig.vertical_background_middle_segment_height,
					clientConfig.vertical_background_bottom_end_height,
					clientConfig.progress_offset_x,
					clientConfig.progress_offset_y,
					clientConfig.progress_middle_segment_amount,
					clientConfig.horizontal_progress_left_end_width,
					clientConfig.horizontal_progress_middle_segment_width,
					clientConfig.horizontal_progress_right_end_width,
					clientConfig.horizontal_progress_height,
					clientConfig.vertical_progress_width,
					clientConfig.vertical_progress_top_end_height,
					clientConfig.vertical_progress_middle_segment_height,
					clientConfig.vertical_progress_bottom_end_height,
					clientConfig.show_current_value_overlay,
					clientConfig.overlay_offset_x,
					clientConfig.overlay_offset_y,
					clientConfig.horizontal_overlay_width,
					clientConfig.horizontal_overlay_height,
					clientConfig.vertical_overlay_width,
					clientConfig.vertical_overlay_height,
					clientConfig.enable_smooth_animation,
					clientConfig.animation_interval
			);

			if (clientConfig.show_number) {
				this.client.getProfiler().swap("mana_number");
				this.manaAttributes$drawEffectBuildUpNumber(
						context,
						manaBarNumberString,
						manaBarNumberX,
						manaBarNumberY,
						clientConfig.number_color
				);
			}
			this.client.getProfiler().pop();
		}
	}

	@Unique
	private void manaAttributes$drawEffectBuildUpElement(
			DrawContext context,
			int current_build_up,
			int max_build_up,
			int build_up_reduction,
			int build_up_element_x,
			int build_up_element_y,
			ClientConfig.FillDirection fill_direction,
			int background_additional_middle_segment_amount,
			int horizontal_background_left_end_width,
			int horizontal_background_middle_segment_width,
			int horizontal_background_right_end_width,
			int horizontal_background_height,
			int vertical_background_width,
			int vertical_background_top_end_height,
			int vertical_background_middle_segment_height,
			int vertical_background_bottom_end_height,
			int progress_offset_x,
			int progress_offset_y,
			int progress_additional_middle_segment_amount,
			int horizontal_progress_left_end_width,
			int horizontal_progress_middle_segment_width,
			int horizontal_progress_right_end_width,
			int horizontal_progress_height,
			int vertical_progress_width,
			int vertical_progress_top_end_height,
			int vertical_progress_middle_segment_height,
			int vertical_progress_bottom_end_height,
			boolean show_current_value_overlay,
			int overlay_offset_x,
			int overlay_offset_y,
			int horizontal_overlay_width,
			int horizontal_overlay_height,
			int vertical_overlay_width,
			int vertical_overlay_height,
			boolean enable_smooth_animation,
			int animation_interval
	) {

//		int backgroundBarLength;
		int progressBarLength;
		int backgroundTextureHeight;
		int backgroundTextureWidth;
		int backgroundMiddleSectionLength;
		int progressTextureHeight;
		int progressTextureWidth;
		int progressMiddleSectionLength;

		// region variable calculation
		if (fill_direction == ClientConfig.FillDirection.BOTTOM_TO_TOP || fill_direction == ClientConfig.FillDirection.TOP_TO_BOTTOM) {
			backgroundTextureHeight = vertical_background_top_end_height + vertical_background_middle_segment_height + vertical_background_bottom_end_height;
			backgroundTextureWidth = vertical_background_width;
			progressTextureHeight = vertical_progress_top_end_height + vertical_progress_middle_segment_height + vertical_progress_bottom_end_height;
			progressTextureWidth = vertical_progress_width;
			backgroundMiddleSectionLength = background_additional_middle_segment_amount * vertical_background_middle_segment_height;
			progressMiddleSectionLength = progress_additional_middle_segment_amount * vertical_progress_middle_segment_height;
//			backgroundBarLength = vertical_background_top_end_height + backgroundMiddleSectionLength + vertical_background_bottom_end_height;
			progressBarLength = vertical_progress_top_end_height + progressMiddleSectionLength + vertical_progress_bottom_end_height;
		} else {
			backgroundTextureHeight = horizontal_background_height;
			backgroundTextureWidth = horizontal_background_left_end_width + horizontal_background_middle_segment_width + horizontal_background_right_end_width;
			progressTextureHeight = horizontal_progress_height;
			progressTextureWidth = horizontal_progress_left_end_width + horizontal_progress_middle_segment_width + horizontal_progress_right_end_width;
			backgroundMiddleSectionLength = background_additional_middle_segment_amount * horizontal_background_middle_segment_width;
			progressMiddleSectionLength = progress_additional_middle_segment_amount * horizontal_progress_middle_segment_width;
//			backgroundBarLength = horizontal_background_left_end_width + backgroundMiddleSectionLength + horizontal_background_right_end_width;
			progressBarLength = horizontal_progress_left_end_width + progressMiddleSectionLength + horizontal_progress_right_end_width;
		}
		// endregion variable calculation

		int normalizedBuildUpRatio = (int) (((double) current_build_up / Math.max(max_build_up, 1)) * (progressBarLength));

		if (this.oldMaxMana != max_build_up) {
			this.oldMaxMana = max_build_up;
			this.oldNormalizedManaRatio = normalizedBuildUpRatio;
		}

		this.manaBarAnimationCounter = this.manaBarAnimationCounter + Math.max(1, build_up_reduction);

		if (this.oldNormalizedManaRatio != normalizedBuildUpRatio && this.manaBarAnimationCounter > Math.max(0, animation_interval)) {
			boolean reduceOldRatio = this.oldNormalizedManaRatio > normalizedBuildUpRatio;
			this.oldNormalizedManaRatio = this.oldNormalizedManaRatio + (reduceOldRatio ? -1 : 1);
			this.manaBarAnimationCounter = 0;
		}

		// region background
		// background
		if (fill_direction == ClientConfig.FillDirection.BOTTOM_TO_TOP || fill_direction == ClientConfig.FillDirection.TOP_TO_BOTTOM) {
			context.drawTexture(MANA_TEXTURES[3], build_up_element_x, build_up_element_y, 0, 0, backgroundTextureWidth, vertical_background_top_end_height, backgroundTextureWidth, backgroundTextureHeight);
			if (background_additional_middle_segment_amount > 0) {
				for (int i = 0; i < background_additional_middle_segment_amount; i++) {
					context.drawTexture(MANA_TEXTURES[3], build_up_element_x, build_up_element_y + vertical_background_top_end_height + (i * vertical_background_middle_segment_height), 0, vertical_background_top_end_height, backgroundTextureWidth, vertical_background_middle_segment_height, backgroundTextureWidth, backgroundTextureHeight);
				}
			}
			context.drawTexture(MANA_TEXTURES[3], build_up_element_x, build_up_element_y + vertical_background_top_end_height + backgroundMiddleSectionLength, 0, vertical_background_top_end_height + vertical_background_middle_segment_height, backgroundTextureWidth, vertical_background_bottom_end_height, backgroundTextureWidth, backgroundTextureHeight);
		} else {
			context.drawTexture(MANA_TEXTURES[0], build_up_element_x, build_up_element_y, 0, 0, horizontal_background_left_end_width, backgroundTextureHeight, backgroundTextureWidth, backgroundTextureHeight);
			if (background_additional_middle_segment_amount > 0) {
				for (int i = 0; i < background_additional_middle_segment_amount; i++) {
					context.drawTexture(MANA_TEXTURES[0], build_up_element_x + horizontal_background_left_end_width + (i * horizontal_background_middle_segment_width), build_up_element_y, horizontal_background_left_end_width, 0, horizontal_background_middle_segment_width, backgroundTextureHeight, backgroundTextureWidth, backgroundTextureHeight);
				}
			}
			context.drawTexture(MANA_TEXTURES[0], build_up_element_x + horizontal_background_left_end_width + backgroundMiddleSectionLength, build_up_element_y, horizontal_background_left_end_width + horizontal_background_middle_segment_width, 0, horizontal_progress_right_end_width, backgroundTextureHeight, backgroundTextureWidth, backgroundTextureHeight);
		}
		// endregion background

		// progress
		int displayRatio = enable_smooth_animation ? this.oldNormalizedManaRatio : normalizedBuildUpRatio;
		if (displayRatio > 0) {
			int ratioFirstPart;
			int ratioLastPart;
			int progressElementX = build_up_element_x + progress_offset_x;
			int progressElementY = build_up_element_y + progress_offset_y;

			if (fill_direction == ClientConfig.FillDirection.BOTTOM_TO_TOP) {
				// 1: bottom to top

				ratioFirstPart = Math.min(vertical_progress_bottom_end_height, displayRatio);
				ratioLastPart = Math.min(vertical_progress_top_end_height, displayRatio - vertical_progress_bottom_end_height - progressMiddleSectionLength);

				context.drawTexture(MANA_TEXTURES[4], progressElementX, progressElementY + progressBarLength - ratioFirstPart, 0, progressTextureHeight - ratioFirstPart, progressTextureWidth, ratioFirstPart, progressTextureWidth, progressTextureHeight);
				if (displayRatio > vertical_progress_bottom_end_height && background_additional_middle_segment_amount > 0) {
					boolean breakDisplay = false;
					for (int i = 0; i < progress_additional_middle_segment_amount; i++) {
						for (int j = 1; j <= vertical_progress_middle_segment_height; j++) {
							int currentTextureY = vertical_progress_bottom_end_height + (i * vertical_progress_middle_segment_height) + j;
							if (currentTextureY > displayRatio) {
								breakDisplay = true;
								break;
							}
							context.drawTexture(MANA_TEXTURES[4], progressElementX, progressElementY + progressBarLength - currentTextureY, 0, vertical_progress_top_end_height + vertical_progress_middle_segment_height - j, progressTextureWidth, 1, progressTextureWidth, progressTextureHeight);
						}
						if (breakDisplay) {
							break;
						}
					}

				}
				if (displayRatio > (vertical_progress_bottom_end_height + progressMiddleSectionLength)) {
					context.drawTexture(MANA_TEXTURES[4], progressElementX, progressElementY + vertical_progress_top_end_height - ratioLastPart, 0, vertical_progress_top_end_height - ratioLastPart, progressTextureWidth, ratioLastPart, progressTextureWidth, progressTextureHeight);
				}
			}
			else if (fill_direction == ClientConfig.FillDirection.RIGHT_TO_LEFT) {
				// 2: right to left

				ratioFirstPart = Math.min(horizontal_progress_right_end_width, displayRatio);
				ratioLastPart = Math.min(horizontal_progress_left_end_width, displayRatio - horizontal_progress_right_end_width - progressMiddleSectionLength);

				context.drawTexture(MANA_TEXTURES[1], progressElementX + progressBarLength - ratioFirstPart, progressElementY, progressTextureWidth - ratioFirstPart, 0, ratioFirstPart, progressTextureHeight, progressTextureWidth, progressTextureHeight);
				if (displayRatio > horizontal_progress_right_end_width && background_additional_middle_segment_amount > 0) {
					boolean breakDisplay = false;
					for (int i = 0; i < progress_additional_middle_segment_amount; i++) {
						for (int j = 1; j <= horizontal_progress_middle_segment_width; j++) {
							int currentTextureX = horizontal_progress_left_end_width + (i * horizontal_progress_middle_segment_width) + j;
							if (currentTextureX > displayRatio) {
								breakDisplay = true;
								break;
							}
							context.drawTexture(MANA_TEXTURES[1], progressElementX + progressBarLength - currentTextureX, progressElementY, horizontal_progress_left_end_width + horizontal_progress_middle_segment_width - j, 0, 1, progressTextureHeight, progressTextureWidth, progressTextureHeight);
						}
						if (breakDisplay) {
							break;
						}
					}

				}
				if (displayRatio > (horizontal_progress_right_end_width + progressMiddleSectionLength)) {
					context.drawTexture(MANA_TEXTURES[1], progressElementX + horizontal_progress_left_end_width - ratioLastPart, progressElementY, horizontal_progress_left_end_width - ratioLastPart, 0, ratioLastPart, progressTextureHeight, progressTextureWidth, progressTextureHeight);
				}
			}
			else if (fill_direction == ClientConfig.FillDirection.TOP_TO_BOTTOM) {
				// 3: top to bottom

				ratioFirstPart = Math.min(vertical_progress_top_end_height, displayRatio);
				ratioLastPart = Math.min(vertical_progress_bottom_end_height, displayRatio - vertical_progress_top_end_height - progressMiddleSectionLength);

				context.drawTexture(MANA_TEXTURES[4], progressElementX, progressElementY, 0, 0, progressTextureWidth, ratioFirstPart, progressTextureWidth, progressTextureHeight);
				if (displayRatio > vertical_progress_top_end_height && background_additional_middle_segment_amount > 0) {
					boolean breakDisplay = false;
					for (int i = 0; i < progress_additional_middle_segment_amount; i++) {
						for (int j = 0; j < vertical_progress_middle_segment_height; j++) {
							int currentTextureY = vertical_progress_top_end_height + (i * vertical_progress_middle_segment_height) + j;
							if (currentTextureY > displayRatio) {
								breakDisplay = true;
								break;
							}
							context.drawTexture(MANA_TEXTURES[4], progressElementX, progressElementY + currentTextureY, 0, vertical_progress_top_end_height + j, progressTextureWidth, 1, progressTextureWidth, progressTextureHeight);
						}
						if (breakDisplay) {
							break;
						}
					}
				}
				if (displayRatio > (vertical_progress_top_end_height + progressMiddleSectionLength)) {
					context.drawTexture(MANA_TEXTURES[4], progressElementX, progressElementY + vertical_progress_top_end_height + progressMiddleSectionLength, 0, vertical_progress_top_end_height + vertical_progress_middle_segment_height, progressTextureWidth, ratioLastPart, progressTextureWidth, progressTextureHeight);
				}
			}
			else {
				// 0: left to right

				ratioFirstPart = Math.min(horizontal_progress_left_end_width, displayRatio);
				ratioLastPart = Math.min(horizontal_progress_right_end_width, displayRatio - horizontal_progress_left_end_width - progressMiddleSectionLength);

				context.drawTexture(MANA_TEXTURES[1], progressElementX, progressElementY, 0, 0, ratioFirstPart, progressTextureHeight, progressTextureWidth, progressTextureHeight);
				if (displayRatio > horizontal_progress_left_end_width && background_additional_middle_segment_amount > 0) {
					boolean breakDisplay = false;
					for (int i = 0; i < progress_additional_middle_segment_amount; i++) {
						for (int j = 0; j < horizontal_progress_middle_segment_width; j++) {
							int currentTextureX = horizontal_progress_left_end_width + (i * horizontal_progress_middle_segment_width) + j;
							if (currentTextureX > displayRatio) {
								breakDisplay = true;
								break;
							}
							context.drawTexture(MANA_TEXTURES[1], progressElementX + currentTextureX, progressElementY, horizontal_progress_left_end_width + j, 0, 1, progressTextureHeight, progressTextureWidth, progressTextureHeight);
						}
						if (breakDisplay) {
							break;
						}
					}
				}
				if (displayRatio > (horizontal_progress_left_end_width + progressMiddleSectionLength)) {
					context.drawTexture(MANA_TEXTURES[1], progressElementX + horizontal_progress_left_end_width + progressMiddleSectionLength, progressElementY, horizontal_progress_left_end_width + horizontal_progress_middle_segment_width, 0, ratioLastPart, progressTextureHeight, progressTextureWidth, progressTextureHeight);
				}
			}

			// overlay
			if (show_current_value_overlay) {
				int overlayElementX = progressElementX + overlay_offset_x;
				int overlayElementY = progressElementY + overlay_offset_y;
				if (fill_direction == ClientConfig.FillDirection.BOTTOM_TO_TOP) {
					// 1: bottom to top
					if (current_build_up > 0 && current_build_up < max_build_up) {
						context.drawTexture(MANA_TEXTURES[5], overlayElementX, overlayElementY + progressBarLength - normalizedBuildUpRatio, 0, 0, vertical_overlay_width, vertical_overlay_height, vertical_overlay_width, horizontal_overlay_height);
					}
				} else if (fill_direction == ClientConfig.FillDirection.RIGHT_TO_LEFT) {
					// 2: right to left
					if (current_build_up > 0 && current_build_up < max_build_up) {
						context.drawTexture(MANA_TEXTURES[2], overlayElementX + progressBarLength - normalizedBuildUpRatio, overlayElementY, 0, 0, horizontal_overlay_width, horizontal_overlay_height, horizontal_overlay_width, horizontal_overlay_height);
					}
				} else if (fill_direction == ClientConfig.FillDirection.TOP_TO_BOTTOM) {
					// 3: top to bottom
					if (current_build_up > 0 && current_build_up < max_build_up) {
						context.drawTexture(MANA_TEXTURES[5], overlayElementX, overlayElementY + normalizedBuildUpRatio, 0, 0, vertical_overlay_width, vertical_overlay_height, vertical_overlay_width, horizontal_overlay_height);
					}
				} else {
					// 0: left to right
					if (current_build_up > 0 && current_build_up < max_build_up) {
						context.drawTexture(MANA_TEXTURES[2], overlayElementX + normalizedBuildUpRatio, overlayElementY, 0, 0, horizontal_overlay_width, horizontal_overlay_height, horizontal_overlay_width, horizontal_overlay_height);
					}
				}
			}
		}
	}

	@Unique
	private void manaAttributes$drawEffectBuildUpNumber(
			DrawContext context,
			String buildUpBarNumberString,
			int build_up_bar_number_x,
			int build_up_bar_number_y,
			int build_up_bar_number_color
	) {
		context.drawText(this.getTextRenderer(), buildUpBarNumberString, build_up_bar_number_x + 1, build_up_bar_number_y, 0, false);
		context.drawText(this.getTextRenderer(), buildUpBarNumberString, build_up_bar_number_x - 1, build_up_bar_number_y, 0, false);
		context.drawText(this.getTextRenderer(), buildUpBarNumberString, build_up_bar_number_x, build_up_bar_number_y + 1, 0, false);
		context.drawText(this.getTextRenderer(), buildUpBarNumberString, build_up_bar_number_x, build_up_bar_number_y - 1, 0, false);
		context.drawText(this.getTextRenderer(), buildUpBarNumberString, build_up_bar_number_x, build_up_bar_number_y, build_up_bar_number_color, false);
	}
}
