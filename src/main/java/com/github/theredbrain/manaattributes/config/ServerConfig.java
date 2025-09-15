package com.github.theredbrain.manaattributes.config;

import com.github.theredbrain.manaattributes.ManaAttributes;
import me.fzzyhmstrs.fzzy_config.annotations.ConvertFrom;
import me.fzzyhmstrs.fzzy_config.config.Config;

@ConvertFrom(fileName = "server.json5", folder = "staminaattributes")
public class ServerConfig extends Config {

	public ServerConfig() {
		super(ManaAttributes.identifier("server"));
	}

	public float natural_mana_regeneration = 1.0F;
	public float natural_max_mana = 10.0F;
	public float natural_mana_regeneration_delay_threshold = 20.0F;
	public float natural_depleted_mana_regeneration_delay_threshold = 60.0F;
	public float natural_mana_tick_threshold = 20.0F;
	public float natural_reserved_mana = 0.0F;
}