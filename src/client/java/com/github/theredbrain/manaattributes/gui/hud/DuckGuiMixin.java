package com.github.theredbrain.manaattributes.gui.hud;

public interface DuckGuiMixin {
	int manaattributes$getDisplayMana();

	void manaattributes$setDisplayMana(int displayMana);

	int manaattributes$getLastMana();

	void manaattributes$setLastMana(int lastMana);

	long manaattributes$getLastManaTime();

	void manaattributes$setLastManaTime(long lastManaTime);

	long manaattributes$getManaIconBlinkTime();

	void manaattributes$setManaIconBlinkTime(long manaIconBlinkTime);

	int manaattributes$getTickCount();
}
