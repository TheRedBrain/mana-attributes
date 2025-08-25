package com.github.theredbrain.manaattributes.registry;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.fabricmc.fabric.api.gamerule.v1.rule.DoubleRule;
import net.minecraft.world.GameRules;

public class GameRulesRegistry {
	public static final GameRules.Key<DoubleRule> NATURAL_MANA_REGENERATION =
			GameRuleRegistry.register("naturalManaRegeneration", GameRules.Category.PLAYER, GameRuleFactory.createDoubleRule(0.0, -1024.0, 1024.0));

	public static final GameRules.Key<DoubleRule> NATURAL_MAXIMUM_MANA =
			GameRuleRegistry.register("naturalMaximumMana", GameRules.Category.PLAYER, GameRuleFactory.createDoubleRule(10.0, 0.0, 1024.0));

	public static void init() {
	}
}
