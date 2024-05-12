package com.github.theredbrain.manaattributes.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(
        name = "client"
)
public class ClientConfig implements ConfigData {
    public boolean show_mana_bar = true;
    public boolean show_full_mana_bar = true;
    public boolean dynamically_adjust_to_armor_bar = true;
    public int mana_bar_additional_length = 172;
    public int mana_bar_x_offset = -91;
    public int mana_bar_y_offset = -45;
    public boolean show_mana_bar_number = true;
    public int mana_bar_number_color = -6250336;
    public int mana_bar_number_x_offset = 0;
    public int mana_bar_number_y_offset = -46;
    public ClientConfig() {

    }
}
