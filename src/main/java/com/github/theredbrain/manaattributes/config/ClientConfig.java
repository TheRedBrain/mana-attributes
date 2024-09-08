package com.github.theredbrain.manaattributes.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(
		name = "client"
)
public class ClientConfig implements ConfigData {
	public boolean show_mana_bar = true;
	public boolean show_full_mana_bar = true;
	public boolean dynamically_adjust_to_armor_bar = true;

	@Comment("offset_x")
	public int offset_x = -91;
	@Comment("offset_y")
	public int offset_y = -45;
	@ConfigEntry.Gui.EnumHandler(
			option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON
	)
	@Comment("fill_direction")
	public FillDirection fill_direction = FillDirection.LEFT_TO_RIGHT;
	@ConfigEntry.Gui.EnumHandler(
			option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON
	)
	@Comment("origin")
	public Origin origin = Origin.BOTTOM_MIDDLE;

	@ConfigEntry.Gui.PrefixText
	@Comment("background_middle_segment_amount")
	public int background_middle_segment_amount = 172;

	@ConfigEntry.Gui.PrefixText
	@Comment("horizontal_background_left_end_width")
	public int horizontal_background_left_end_width = 5;
	@Comment("horizontal_background_middle_segment_width")
	public int horizontal_background_middle_segment_width = 1;
	@Comment("horizontal_background_right_end_width")
	public int horizontal_background_right_end_width = 5;
	@Comment("horizontal_background_height")
	public int horizontal_background_height = 5;

	@ConfigEntry.Gui.PrefixText
	@Comment("vertical_background_width")
	public int vertical_background_width = 5;
	@ConfigEntry.Gui.PrefixText
	@Comment("vertical_background_top_end_height")
	public int vertical_background_top_end_height = 5;
	@Comment("vertical_background_middle_segment_height")
	public int vertical_background_middle_segment_height = 1;
	@Comment("vertical_background_bottom_end_height")
	public int vertical_background_bottom_end_height = 5;

	@ConfigEntry.Gui.PrefixText
	@Comment("progress_offset_x")
	public int progress_offset_x = 0;
	@Comment("progress_offset_y")
	public int progress_offset_y = 0;
	@Comment("progress_middle_segment_amount")
	public int progress_middle_segment_amount = 172;

	@ConfigEntry.Gui.PrefixText
	@Comment("horizontal_progress_left_end_width")
	public int horizontal_progress_left_end_width = 5;
	@Comment("horizontal_progress_middle_segment_width")
	public int horizontal_progress_middle_segment_width = 1;
	@Comment("horizontal_progress_right_end_width")
	public int horizontal_progress_right_end_width = 5;
	@Comment("horizontal_progress_height")
	public int horizontal_progress_height = 5;

	@ConfigEntry.Gui.PrefixText
	@Comment("vertical_progress_width")
	public int vertical_progress_width = 5;
	@ConfigEntry.Gui.PrefixText
	@Comment("vertical_progress_top_end_height")
	public int vertical_progress_top_end_height = 5;
	@Comment("vertical_progress_middle_segment_height")
	public int vertical_progress_middle_segment_height = 1;
	@Comment("vertical_progress_bottom_end_height")
	public int vertical_progress_bottom_end_height = 5;

	@ConfigEntry.Gui.PrefixText
	@Comment("show_current_value_overlay")
	public boolean show_current_value_overlay = false;

	@Comment("overlay_offset_x")
	public int overlay_offset_x = -2;
	@Comment("overlay_offset_y")
	public int overlay_offset_y = 0;

	@ConfigEntry.Gui.PrefixText
	@Comment("horizontal_overlay_width")
	public int horizontal_overlay_width = 5;
	@Comment("horizontal_overlay_height")
	public int horizontal_overlay_height = 5;

	@ConfigEntry.Gui.PrefixText
	@Comment("vertical_overlay_width")
	public int vertical_overlay_width = 5;
	@Comment("vertical_overlay_height")
	public int vertical_overlay_height = 5;

	@ConfigEntry.Gui.PrefixText
	@Comment("enable_smooth_animation")
	public boolean enable_smooth_animation = true;
	@Comment("animation_interval")
	public int animation_interval = 1;
	@Comment("max_value_change_is_animated")
	public boolean max_value_change_is_animated = false;

	@ConfigEntry.Gui.PrefixText
	@Comment("show_number")
	public boolean show_number = false;

	@ConfigEntry.Gui.PrefixText
	@Comment("show_max_value")
	public boolean show_max_value = false;

	@Comment("number_offset_x")
	public int number_offset_x = 0;
	@Comment("number_offset_y")
	public int number_offset_y = -46;

	@Comment("number_color")
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

	public enum Origin {
		TOP_LEFT,
		TOP_MIDDLE,
		TOP_RIGHT,
		MIDDLE_LEFT,
		MIDDLE_MIDDLE,
		MIDDLE_RIGHT,
		BOTTOM_LEFT,
		BOTTOM_MIDDLE,
		BOTTOM_RIGHT;

		Origin() {
		}
	}
}
