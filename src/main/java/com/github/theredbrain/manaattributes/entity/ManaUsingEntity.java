package com.github.theredbrain.manaattributes.entity;

public interface ManaUsingEntity {
	int manaattributes$getManaRegenerationDelayThreshold();

	int manaattributes$getDepletedManaRegenerationDelayThreshold();

	int manaattributes$getManaTickThreshold();

	float manaattributes$getRegeneratedMana();

	float manaattributes$getManaRegeneration();

	float manaattributes$getMaxMana();

	void manaattributes$addMana(float amount);

	float manaattributes$getMana();

	void manaattributes$setMana(float mana);
}
