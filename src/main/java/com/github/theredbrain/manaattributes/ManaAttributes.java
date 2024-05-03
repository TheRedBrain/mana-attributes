package com.github.theredbrain.manaattributes;

import com.github.theredbrain.manaattributes.registry.EntityAttributesRegistry;
import com.github.theredbrain.manaattributes.registry.GameRulesRegistry;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ManaAttributes implements ModInitializer {
	public static final String MOD_ID = "manaattributes";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing mana!");

		EntityAttributesRegistry.registerAttributes();
		GameRulesRegistry.init();
	}

	public static Identifier identifier(String path) {
		return new Identifier(MOD_ID, path);
	}
}