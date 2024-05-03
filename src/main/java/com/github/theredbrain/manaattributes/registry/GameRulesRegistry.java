package com.github.theredbrain.manaattributes.registry;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules;

public class GameRulesRegistry {
    public static final GameRules.Key<GameRules.BooleanRule> NATURAL_MANA_REGENERATION =
            GameRuleRegistry.register("naturalManaRegeneration", GameRules.Category.PLAYER, GameRuleFactory.createBooleanRule(true));
    public static void init() {}
}
