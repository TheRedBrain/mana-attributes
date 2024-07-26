package com.github.theredbrain.manaattributes.config;

import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;

@Config(
		name = "manaattributes"
)
public class ClientConfigWrapper extends PartitioningSerializer.GlobalData {
	@ConfigEntry.Category("client")
	@ConfigEntry.Gui.TransitiveObject
	public ClientConfig client = new ClientConfig();

	public ClientConfigWrapper() {
	}
}
