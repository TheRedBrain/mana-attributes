package com.github.theredbrain.manaattributes;

import com.github.theredbrain.manaattributes.config.ClientConfig;
import com.github.theredbrain.manaattributes.registry.ClientEventsRegistry;
import net.fabricmc.api.ClientModInitializer;

public class ManaAttributesClient implements ClientModInitializer {
	public static ClientConfig CLIENT_CONFIG;

	@Override
	public void onInitializeClient() {
		ClientEventsRegistry.initializeClientEvents();
	}
}