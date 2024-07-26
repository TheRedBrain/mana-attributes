package com.github.theredbrain.manaattributes;

import com.github.theredbrain.manaattributes.registry.GameRulesRegistry;
import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ManaAttributes implements ModInitializer {
	public static final String MOD_ID = "manaattributes";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static EntityAttribute MANA_REGENERATION;
	public static EntityAttribute MAX_MANA;
	public static EntityAttribute MANA_REGENERATION_DELAY_THRESHOLD;
	public static EntityAttribute DEPLETED_MANA_REGENERATION_DELAY_THRESHOLD;
	public static EntityAttribute MANA_TICK_THRESHOLD;

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing mana!");

		GameRulesRegistry.init();
	}

	public static Identifier identifier(String path) {
		return new Identifier(MOD_ID, path);
	}
}