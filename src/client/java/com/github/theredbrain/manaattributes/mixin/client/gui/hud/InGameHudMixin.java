package com.github.theredbrain.manaattributes.mixin.client.gui.hud;

import com.github.theredbrain.manaattributes.ManaAttributes;
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

    @Shadow private int scaledWidth;

    @Shadow private int scaledHeight;

    @Shadow @Final private MinecraftClient client;

    @Shadow public abstract TextRenderer getTextRenderer();

    @Unique
    private static final Identifier BARS_TEXTURE = new Identifier("textures/gui/bars.png");

    @Inject(method = "renderStatusBars", at = @At("RETURN"))
    private void manaattributes$renderStatusBars(DrawContext context, CallbackInfo ci) {
        var clientConfig = ManaAttributesClient.clientConfig;
        if (clientConfig.show_mana_bar) {
            PlayerEntity playerEntity = this.getCameraPlayer();
            if (playerEntity != null) {
                int mana = MathHelper.ceil(((ManaUsingEntity) playerEntity).manaattributes$getMana());
                int maxMana = MathHelper.ceil(((ManaUsingEntity) playerEntity).manaattributes$getMaxMana());

                int attributeBarX = this.scaledWidth / 2 - 91;
                int attributeBarY = this.scaledHeight - clientConfig.mana_bar_y_offset;
                int attributeBarNumberX;
                int attributeBarNumberY;
                int normalizedManaRatio = (int) (((double) mana / Math.max(maxMana, 1)) * 182);

                if (maxMana > 0 && (normalizedManaRatio < 1 || clientConfig.show_full_mana_bar)) {
                    this.client.getProfiler().push("mana_bar");
                    context.drawTexture(BARS_TEXTURE, attributeBarX, attributeBarY - 18, 0, 10, 182, 5);
                    if (normalizedManaRatio > 0) {
                        context.drawTexture(BARS_TEXTURE, attributeBarX, attributeBarY - 18, 0, 15, normalizedManaRatio, 5);
                    }
                    if (clientConfig.show_mana_bar_number) {
                        this.client.getProfiler().swap("mana_bar_number");
                        String string = String.valueOf(mana);
                        attributeBarNumberX = (this.scaledWidth - this.getTextRenderer().getWidth(string)) / 2;
                        attributeBarNumberY = attributeBarY - 19;
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
