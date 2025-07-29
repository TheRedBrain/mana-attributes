package com.github.theredbrain.manaattributes;

import com.github.theredbrain.manaattributes.config.ClientConfig;
import com.github.theredbrain.manaattributes.registry.ClientEventsRegistry;
import com.github.theredbrain.resourcebarapi.ResourceBarAPI;
import com.mojang.blaze3d.systems.RenderSystem;
import me.fzzyhmstrs.fzzy_config.api.ConfigApiJava;
import me.fzzyhmstrs.fzzy_config.api.RegisterType;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;

public class ManaAttributesClient implements ClientModInitializer {
	public static ClientConfig CLIENT_CONFIG;

	@Override
	public void onInitializeClient() {
		CLIENT_CONFIG = ConfigApiJava.registerAndLoadConfig(ClientConfig::new, RegisterType.CLIENT);
		ClientEventsRegistry.initializeClientEvents();
	}

	public static void drawIconResourceBar(
			MinecraftClient client,
			DrawContext context,
			String identifier_string,
			double current_value,
			double max_value,
			double current_unreserved_value,
			ResourceBarAPI.ResourceBarOrigin resource_bar_origin,
			int offset_x,
			int offset_y,
			ResourceBarAPI.ResourceBarFillDirection resource_bar_fill_direction,
			Identifier container_texture_id,
			Identifier full_texture_id,
			Identifier half_texture_id
	) {

		int i = (int)(max_value + 0.5F) / 2;
		if (i != 0) {
			int j = (int) current_value;
			client.getProfiler().push(identifier_string);
			int k;
			int l;

			//region origin
			int originX;
			int originY;
			if (resource_bar_origin == ResourceBarAPI.ResourceBarOrigin.TOP_MIDDLE) {
				originX = context.getScaledWindowWidth() / 2;
				originY = 0;
			} else if (resource_bar_origin == ResourceBarAPI.ResourceBarOrigin.TOP_RIGHT) {
				originX = context.getScaledWindowWidth();
				originY = 0;
			} else if (resource_bar_origin == ResourceBarAPI.ResourceBarOrigin.MIDDLE_LEFT) {
				originX = 0;
				originY = context.getScaledWindowHeight() / 2;
			} else if (resource_bar_origin == ResourceBarAPI.ResourceBarOrigin.MIDDLE_MIDDLE) {
				originX = context.getScaledWindowWidth() / 2;
				originY = context.getScaledWindowHeight() / 2;
			} else if (resource_bar_origin == ResourceBarAPI.ResourceBarOrigin.MIDDLE_RIGHT) {
				originX = context.getScaledWindowWidth();
				originY = context.getScaledWindowHeight() / 2;
			} else if (resource_bar_origin == ResourceBarAPI.ResourceBarOrigin.BOTTOM_LEFT) {
				originX = 0;
				originY = context.getScaledWindowHeight();
			} else if (resource_bar_origin == ResourceBarAPI.ResourceBarOrigin.BOTTOM_MIDDLE) {
				originX = context.getScaledWindowWidth() / 2;
				originY = context.getScaledWindowHeight();
			} else if (resource_bar_origin == ResourceBarAPI.ResourceBarOrigin.BOTTOM_RIGHT) {
				originX = context.getScaledWindowWidth();
				originY = context.getScaledWindowHeight();
			} else {
				originX = 0;
				originY = 0;
			}
			//endregion origin

			k = originY + offset_y;
			l = originX + offset_x;

			int m = k;
			int n = 0;
			RenderSystem.enableBlend();

			while(i > 0) {
				int o = Math.min(i, 10);
				i -= o;

				for(int p = 0; p < o; ++p) {
					int q = l - p * 8 - 9;
					context.drawGuiTexture(container_texture_id, q, m, 9, 9);
					if (p * 2 + 1 + n < j) {
						context.drawGuiTexture(full_texture_id, q, m, 9, 9);
					}

					if (p * 2 + 1 + n == j) {
						context.drawGuiTexture(half_texture_id, q, m, 9, 9);
					}
				}

				m -= 10;
				n += 20;
			}

			RenderSystem.disableBlend();
			client.getProfiler().pop();
		}
	}

}