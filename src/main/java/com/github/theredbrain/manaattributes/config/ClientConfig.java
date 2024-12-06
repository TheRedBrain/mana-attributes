package com.github.theredbrain.manaattributes.config;

import com.github.theredbrain.manaattributes.ManaAttributes;
import com.github.theredbrain.resourcebarapi.ResourceBarAPI;
import me.fzzyhmstrs.fzzy_config.annotations.Translation;
import me.fzzyhmstrs.fzzy_config.config.Config;
import me.fzzyhmstrs.fzzy_config.config.ConfigSection;
import me.fzzyhmstrs.fzzy_config.validation.collection.ValidatedMap;
import me.fzzyhmstrs.fzzy_config.validation.misc.ValidatedColor;
import me.fzzyhmstrs.fzzy_config.validation.number.ValidatedInt;

import java.util.HashMap;

public class ClientConfig extends Config {
	public ClientConfig() {
		super(ManaAttributes.identifier("client"));
	}

	public boolean show_mana_bar = true;
	public boolean show_full_mana_bar = true;
	public PositionSettings positionSettings = new PositionSettings();

	public static class PositionSettings extends ConfigSection {
		public ResourceBarAPI.ResourceBarOrigin origin = ResourceBarAPI.ResourceBarOrigin.BOTTOM_MIDDLE;
		public boolean dynamically_adjust_to_armor_bar = true;

		public boolean is_centered = false;


		public ValidatedMap<Integer, Integer> offsets_x = new ValidatedMap<>(new HashMap<>() {{
			put(0, -91);
		}}, new ValidatedInt(), new ValidatedInt());

		public ValidatedMap<Integer, Integer> offsets_y = new ValidatedMap<>(new HashMap<>() {{
			put(0, -45);
		}}, new ValidatedInt(), new ValidatedInt());
	}

	public ResourceBarAPI.ResourceBarFillDirection fill_direction = ResourceBarAPI.ResourceBarFillDirection.LEFT_TO_RIGHT;

	public boolean show_current_value_overlay = false;

	public TextureSettings textureSettings = new TextureSettings();

	public static class TextureSettings extends ConfigSection {

		public BackgroundTextureSettings backgroundTextureSettings = new BackgroundTextureSettings();

		@Translation(prefix = "manaattributes.client.texture_layer")
		public static class BackgroundTextureSettings extends ConfigSection {
			public ValidatedMap<Integer, Integer> middle_segment_amounts = new ValidatedMap<>(new HashMap<>() {{
				put(0, 172);
			}}, new ValidatedInt(), new ValidatedInt());

			public HorizontalTextureSettings horizontalTextureSettings = new HorizontalTextureSettings();

			@Translation(prefix = "manaattributes.client.texture_layer")
			public static class HorizontalTextureSettings extends ConfigSection {
				public int horizontal_left_end_width = 5;
				public int horizontal_middle_segment_width = 1;
				public int horizontal_right_end_width = 5;
				public int horizontal_height = 5;
			}

			public VerticalTextureSettings verticalTextureSettings = new VerticalTextureSettings();

			@Translation(prefix = "manaattributes.client.texture_layer")
			public static class VerticalTextureSettings extends ConfigSection {
				public int vertical_width = 5;
				public int vertical_top_end_height = 5;
				public int vertical_middle_segment_height = 1;
				public int vertical_bottom_end_height = 5;
			}
		}


		public ProgressTextureSettings progressTextureSettings = new ProgressTextureSettings();

		@Translation(prefix = "manaattributes.client.texture_layer")
		public static class ProgressTextureSettings extends ConfigSection {
			public int offset_x = 0;
			public int offset_y = 0;

			public ValidatedMap<Integer, Integer> middle_segment_amounts = new ValidatedMap<>(new HashMap<>() {{
				put(0, 172);
			}}, new ValidatedInt(), new ValidatedInt());

			public HorizontalTextureSettings horizontalTextureSettings = new HorizontalTextureSettings();

			@Translation(prefix = "manaattributes.client.texture_layer")
			public static class HorizontalTextureSettings extends ConfigSection {
				public int horizontal_left_end_width = 5;
				public int horizontal_middle_segment_width = 1;
				public int horizontal_right_end_width = 5;
				public int horizontal_height = 5;
			}

			public VerticalTextureSettings verticalTextureSettings = new VerticalTextureSettings();

			@Translation(prefix = "manaattributes.client.texture_layer")
			public static class VerticalTextureSettings extends ConfigSection {
				public int vertical_width = 5;
				public int vertical_top_end_height = 5;
				public int vertical_middle_segment_height = 1;
				public int vertical_bottom_end_height = 5;
			}
		}

		public ReservedTextureSettings reservedTextureSettings = new ReservedTextureSettings();

		@Translation(prefix = "manaattributes.client.texture_layer")
		public static class ReservedTextureSettings extends ConfigSection {
			public int offset_x = 0;
			public int offset_y = 0;

			public ValidatedMap<Integer, Integer> middle_segment_amounts = new ValidatedMap<>(new HashMap<>() {{
				put(0, 172);
			}}, new ValidatedInt(), new ValidatedInt());

			public HorizontalTextureSettings horizontalTextureSettings = new HorizontalTextureSettings();

			@Translation(prefix = "manaattributes.client.texture_layer")
			public static class HorizontalTextureSettings extends ConfigSection {
				public int horizontal_left_end_width = 5;
				public int horizontal_middle_segment_width = 1;
				public int horizontal_right_end_width = 5;
				public int horizontal_height = 5;
			}

			public VerticalTextureSettings verticalTextureSettings = new VerticalTextureSettings();

			@Translation(prefix = "manaattributes.client.texture_layer")
			public static class VerticalTextureSettings extends ConfigSection {
				public int vertical_width = 5;
				public int vertical_top_end_height = 5;
				public int vertical_middle_segment_height = 1;
				public int vertical_bottom_end_height = 5;
			}
		}

		public OverlayTextureSettings overlayTextureSettings = new OverlayTextureSettings();

		public static class OverlayTextureSettings extends ConfigSection {

			public int offset_x = -2;
			public int offset_y = 0;

			public int horizontal_width = 5;
			public int horizontal_height = 5;

			public int vertical_width = 5;
			public int vertical_height = 5;
		}
	}

	public boolean enable_smooth_animation = true;
	public AnimationsSettings animationSettings = new AnimationsSettings();

	public static class AnimationsSettings extends ConfigSection {
		public int animation_interval = 1;
		public boolean max_value_change_is_animated = false;
	}

	public boolean show_number = false;

	public NumberSettings numberSettings = new NumberSettings();

	public static class NumberSettings extends ConfigSection {
		public boolean show_max_value = false;
		public boolean show_when_mana_full = true;
		public int offset_x = 0;
		public int offset_y = -46;
		public ValidatedColor color = new ValidatedColor(150, 150, 150);
	}
}
