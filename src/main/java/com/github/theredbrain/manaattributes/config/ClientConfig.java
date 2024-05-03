package com.github.theredbrain.manaattributes.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(
        name = "client"
)
public class ClientConfig implements ConfigData {
    public boolean show_mana_bar = true;
    public boolean show_full_mana_bar = true;
    public boolean show_mana_bar_number = true;
    public int mana_bar_number_color = -6250336;
    public int mana_bar_y_offset = 30;
    public ClientConfig() {

    }
}
