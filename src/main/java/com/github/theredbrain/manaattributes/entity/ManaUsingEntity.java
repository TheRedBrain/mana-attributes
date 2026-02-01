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

	float manaattributes$getMana();

	void manaattributes$addMana(float amount);

	void manaattributes$setMana(float amount);

	boolean manaattributes$delayMaxValueApplication();

	void manaattributes$setDelayMaxValueApplication(boolean delayMaxValueApplication);

	boolean manaattributes$delayedMaxValueApplication();

	void manaattributes$setDelayedMaxValueApplication(boolean delayedMaxValueApplication);
}
