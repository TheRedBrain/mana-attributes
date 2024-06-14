package com.github.theredbrain.manaattributes.mixin.client.gui.hud;

import com.github.theredbrain.manaattributes.ManaAttributesClient;
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

    @Shadow protected abstract PlayerEntity getCameraPlayer();

    @Shadow @Final private MinecraftClient client;

    @Shadow public abstract TextRenderer getTextRenderer();

    @Unique
    private static final Identifier BOSS_BAR_BLUE_PROGRESS_TEXTURE = Identifier.ofVanilla("textures/gui/sprites/boss_bar/blue_progress.png");

    @Unique
    private static final Identifier BOSS_BAR_BLUE_BACKGROUND_TEXTURE = Identifier.ofVanilla("textures/gui/sprites/boss_bar/blue_background.png");

    @Inject(method = "renderStatusBars", at = @At("RETURN"))
    private void manaattributes$renderStatusBars(DrawContext context, CallbackInfo ci) {
        var clientConfig = ManaAttributesClient.clientConfig;
        if (clientConfig.show_mana_bar) {
            PlayerEntity playerEntity = this.getCameraPlayer();
            if (playerEntity != null) {
                int mana = MathHelper.ceil(((ManaUsingEntity) playerEntity).manaattributes$getMana());
                int maxMana = MathHelper.ceil(((ManaUsingEntity) playerEntity).manaattributes$getMaxMana());

                int attributeBarX = context.getScaledWindowWidth() / 2 + clientConfig.mana_bar_x_offset;
                int attributeBarY = context.getScaledWindowHeight() + clientConfig.mana_bar_y_offset - (clientConfig.dynamically_adjust_to_armor_bar && playerEntity.getArmor() > 0 ? 10 : 0);
                int mana_bar_additional_length = clientConfig.mana_bar_additional_length;
                int attributeBarNumberX;
                int attributeBarNumberY;
                int normalizedManaRatio = (int) (((double) mana / Math.max(maxMana, 1)) * (5 + clientConfig.mana_bar_additional_length + 5));

                if (maxMana > 0 && (mana < maxMana || clientConfig.show_full_mana_bar)) {
                    this.client.getProfiler().push("mana_bar");

                    // background
                    context.drawTexture(BOSS_BAR_BLUE_BACKGROUND_TEXTURE, attributeBarX, attributeBarY, 0, 0, 5, 5, 182, 5);
                    if (mana_bar_additional_length > 0) {
                        for (int i = 0; i < mana_bar_additional_length; i++) {
                            context.drawTexture(BOSS_BAR_BLUE_BACKGROUND_TEXTURE, attributeBarX + 5 + i, attributeBarY, 5, 0, 1, 5, 182, 5);
                        }
                    }
                    context.drawTexture(BOSS_BAR_BLUE_BACKGROUND_TEXTURE, attributeBarX + 5 + mana_bar_additional_length, attributeBarY, 177, 0, 5, 5, 182, 5);

                    // foreground
                    if (normalizedManaRatio > 0) {
                        context.drawTexture(BOSS_BAR_BLUE_PROGRESS_TEXTURE, attributeBarX, attributeBarY, 0, 0, Math.min(5, normalizedManaRatio), 5, 182, 5);
                        if (normalizedManaRatio > 5) {
                            if (mana_bar_additional_length > 0) {
                                for (int i = 5; i < Math.min(5 + mana_bar_additional_length, normalizedManaRatio); i++) {
                                    context.drawTexture(BOSS_BAR_BLUE_PROGRESS_TEXTURE, attributeBarX + i, attributeBarY, 5, 0, 1, 5, 182, 5);
                                }
                            }
                        }
                        if (normalizedManaRatio > (5 + mana_bar_additional_length)) {
                            context.drawTexture(BOSS_BAR_BLUE_PROGRESS_TEXTURE, attributeBarX + 5 + mana_bar_additional_length, attributeBarY, 177, 0, Math.min(5, normalizedManaRatio - 5 - mana_bar_additional_length), 5, 182, 5);
                        }
                    }

                    if (clientConfig.show_mana_bar_number) {
                        this.client.getProfiler().swap("mana_bar_number");
                        String string = String.valueOf(mana);
                        attributeBarNumberX = (context.getScaledWindowWidth() - this.getTextRenderer().getWidth(string)) / 2 + clientConfig.mana_bar_number_x_offset;
                        attributeBarNumberY = context.getScaledWindowHeight() + clientConfig.mana_bar_number_y_offset - (clientConfig.dynamically_adjust_to_armor_bar && playerEntity.getArmor() > 0 ? 10 : 0);
                        context.drawText(this.getTextRenderer(), string, attributeBarNumberX + 1, attributeBarNumberY, 0, false);
                        context.drawText(this.getTextRenderer(), string, attributeBarNumberX - 1, attributeBarNumberY, 0, false);
                        context.drawText(this.getTextRenderer(), string, attributeBarNumberX, attributeBarNumberY + 1, 0, false);
                        context.drawText(this.getTextRenderer(), string, attributeBarNumberX, attributeBarNumberY - 1, 0, false);
                        context.drawText(this.getTextRenderer(), string, attributeBarNumberX, attributeBarNumberY, clientConfig.mana_bar_number_color, true);
                    }
                }

                this.client.getProfiler().pop();
            }
        }
    }
}
