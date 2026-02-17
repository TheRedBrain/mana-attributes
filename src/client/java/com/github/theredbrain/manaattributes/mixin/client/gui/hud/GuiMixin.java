package com.github.theredbrain.manaattributes.mixin.client.gui.hud;

import com.github.theredbrain.manaattributes.gui.hud.DuckGuiMixin;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.Gui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Environment(EnvType.CLIENT)
@Mixin(Gui.class)
public class GuiMixin implements DuckGuiMixin {

	@Shadow
	private int tickCount;

	@Unique
	private int displayMana;
	@Unique
	private int lastMana;
	@Unique
	private long lastManaTime;
	@Unique
	private long manaBlinkTime;

	@Override
	public int manaattributes$getDisplayMana() {
		return this.displayMana;
	}

	@Override
	public void manaattributes$setDisplayMana(int displayMana) {
		this.displayMana = displayMana;
	}

	@Override
	public int manaattributes$getLastMana() {
		return this.lastMana;
	}

	@Override
	public void manaattributes$setLastMana(int lastMana) {
		this.lastMana = lastMana;
	}

	@Override
	public long manaattributes$getLastManaTime() {
		return this.lastManaTime;
	}

	@Override
	public void manaattributes$setLastManaTime(long lastManaTime) {
		this.lastManaTime = lastManaTime;
	}

	@Override
	public long manaattributes$getManaIconBlinkTime() {
		return this.manaBlinkTime;
	}

	@Override
	public void manaattributes$setManaIconBlinkTime(long manaIconBlinkTime) {
		this.manaBlinkTime = manaIconBlinkTime;
	}

	@Override
	public int manaattributes$getTickCount() {
		return this.tickCount;
	}
}
