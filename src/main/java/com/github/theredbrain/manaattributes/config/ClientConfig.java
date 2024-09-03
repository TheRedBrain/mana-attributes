package com.github.theredbrain.manaattributes.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(
		name = "client"
)
public class ClientConfig implements ConfigData {
	@ConfigEntry.Gui.PrefixText
	public boolean show_mana_bar = true;
	public boolean show_full_mana_bar = true;
	public boolean dynamically_adjust_to_armor_bar = true;

	public int x_offset = -91;
	public int y_offset = -45;
	@ConfigEntry.Gui.EnumHandler(
			option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON
	)
	public FillDirection fill_direction = FillDirection.LEFT_TO_RIGHT;

	@ConfigEntry.Gui.PrefixText
	public int background_middle_segment_amount = 172;

	@ConfigEntry.Gui.PrefixText
	public int horizontal_background_left_end_width = 5;
	public int horizontal_background_middle_segment_width = 1;
	public int horizontal_background_right_end_width = 5;
	public int horizontal_background_height = 5;

	@ConfigEntry.Gui.PrefixText
	public int vertical_background_width = 5;
	@ConfigEntry.Gui.PrefixText
	public int vertical_background_top_end_height = 5;
	public int vertical_background_middle_segment_height = 1;
	public int vertical_background_bottom_end_height = 5;

	@ConfigEntry.Gui.PrefixText
	public int progress_offset_x = 0;
	public int progress_offset_y = 0;
	public int progress_middle_segment_amount = 172;

	@ConfigEntry.Gui.PrefixText
	public int horizontal_progress_left_end_width = 5;
	public int horizontal_progress_middle_segment_width = 1;
	public int horizontal_progress_right_end_width = 5;
	public int horizontal_progress_height = 5;

	@ConfigEntry.Gui.PrefixText
	public int vertical_progress_width = 5;
	@ConfigEntry.Gui.PrefixText
	public int vertical_progress_top_end_height = 5;
	public int vertical_progress_middle_segment_height = 1;
	public int vertical_progress_bottom_end_height = 5;

	@ConfigEntry.Gui.PrefixText
	public boolean show_current_value_overlay = false;

	public int overlay_offset_x = -2;
	public int overlay_offset_y = 0;

	@ConfigEntry.Gui.PrefixText
	public int horizontal_overlay_width = 5;
	public int horizontal_overlay_height = 5;

	@ConfigEntry.Gui.PrefixText
	public int vertical_overlay_width = 5;
	public int vertical_overlay_height = 5;

	@ConfigEntry.Gui.PrefixText
	public boolean enable_smooth_animation = true;
	public int animation_interval = 1;

	@ConfigEntry.Gui.PrefixText
	public boolean show_number = false;

	public int number_x_offset = 0;
	public int number_y_offset = -40;

	public int number_color = -6250336;


	public ClientConfig() {
	}

	public enum FillDirection {
		LEFT_TO_RIGHT,
		BOTTOM_TO_TOP,
		RIGHT_TO_LEFT,
		TOP_TO_BOTTOM;

		FillDirection() {
		}
	}
}
