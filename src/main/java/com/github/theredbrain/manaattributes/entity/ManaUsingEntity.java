package com.github.theredbrain.manaattributes.entity;

public interface ManaUsingEntity {
	int manaattributes$getManaRegenerationDelayThreshold();

	int manaattributes$getDepletedManaRegenerationDelayThreshold();

	int manaattributes$getManaTickThreshold();

	float manaattributes$getRegeneratedMana();

	float manaattributes$getManaRegeneration();

	float manaattributes$getUnreservedMana();

	float manaattributes$getMaxMana();

	float manaattributes$getReservedMana();

	void manaattributes$addMana(float amount);

	float manaattributes$getMana();

	void manaattributes$setMana(float mana);

	void manaattributes$setApplyOldMana(boolean applyOldMana);

	void manaattributes$setApplyMaxMana(boolean applyMaxMana);
}
