package com.github.theredbrain.manaattributes.entity;

public interface ManaUsingEntity {

	int manaattributes$getManaTickTimer();

	void manaattributes$setManaTickTimer(int manaTickTimer);

	int manaattributes$getDepletedManaRegenerationDelayTimer();

	void manaattributes$setDepletedManaRegenerationDelayTimer(int depletedManaRegenerationDelayTimer);

	int manaattributes$getManaRegenerationDelayTimer();

	void manaattributes$setManaRegenerationDelayTimer(int manaRegenerationDelayTimer);

	boolean manaattributes$delayManaRegeneration();

	void manaattributes$setDelayManaRegeneration(boolean delayManaRegeneration);

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

	Float manaattributes$getOldMana();

	void manaattributes$setOldMana(Float mana);

	void manaattributes$setApplyOldMana(boolean applyOldMana);

	boolean manaattributes$applyOldMana();

	void manaattributes$setApplyMaxMana(boolean applyMaxMana);

	boolean manaattributes$applyMaxMana();
}
