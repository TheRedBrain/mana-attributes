package com.github.theredbrain.manaattributes;

import com.github.theredbrain.manaattributes.config.ServerConfig;
import me.fzzyhmstrs.fzzy_config.api.ConfigApiJava;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.ai.attributes.Attribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ManaAttributes implements ModInitializer {
	public static final String MOD_ID = "manaattributes";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static ServerConfig SERVER_CONFIG;

	public static Holder<Attribute> MANA_REGENERATION;
	public static Holder<Attribute> MAX_MANA;
	public static Holder<Attribute> MANA_REGENERATION_DELAY_THRESHOLD;
	public static Holder<Attribute> DEPLETED_MANA_REGENERATION_DELAY_THRESHOLD;
	public static Holder<Attribute> MANA_TICK_THRESHOLD;
	public static Holder<Attribute> RESERVED_MANA;

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing mana!");
		SERVER_CONFIG = ConfigApiJava.registerAndLoadConfig(ServerConfig::new);
	}

	public static void info(String message) {
		LOGGER.info(message);
	}

	public static Identifier identifier(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}