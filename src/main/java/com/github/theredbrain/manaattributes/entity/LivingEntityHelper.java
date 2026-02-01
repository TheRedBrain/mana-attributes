package com.github.theredbrain.manaattributes.entity;

import com.github.theredbrain.manaattributes.ManaAttributes;
import net.minecraft.world.entity.LivingEntity;

public class LivingEntityHelper {

	public static void tick(LivingEntity livingEntity) {

		if (!livingEntity.level().isClientSide()) {

			int manaTickTimer = ((ManaUsingEntity) livingEntity).manaattributes$getManaTickTimer();
			int depletedManaRegenerationDelayTimer = ((ManaUsingEntity) livingEntity).manaattributes$getDepletedManaRegenerationDelayTimer();
			int manaRegenerationDelayTimer = ((ManaUsingEntity) livingEntity).manaattributes$getManaRegenerationDelayTimer();
			boolean delayManaRegeneration = ((ManaUsingEntity) livingEntity).manaattributes$delayManaRegeneration();

			double mana = ((ManaUsingEntity) livingEntity).manaattributes$getMana();

			manaTickTimer++;

			if (mana <= 0 && delayManaRegeneration) {
				depletedManaRegenerationDelayTimer = 0;
				manaRegenerationDelayTimer = ((ManaUsingEntity)livingEntity).manaattributes$getManaRegenerationDelayThreshold();
				delayManaRegeneration = false;
			}
			if (mana > 0 && !delayManaRegeneration) {
				delayManaRegeneration = true;
			}
			if (depletedManaRegenerationDelayTimer <= ((ManaUsingEntity)livingEntity).manaattributes$getDepletedManaRegenerationDelayThreshold()) {
				depletedManaRegenerationDelayTimer++;
			}
			if (manaRegenerationDelayTimer <= ((ManaUsingEntity)livingEntity).manaattributes$getManaRegenerationDelayThreshold()) {
				manaRegenerationDelayTimer++;
			}

			if (
					manaTickTimer >= ((ManaUsingEntity)livingEntity).manaattributes$getManaTickThreshold()
							&& manaRegenerationDelayTimer >= ((ManaUsingEntity)livingEntity).manaattributes$getManaRegenerationDelayThreshold()
							&& depletedManaRegenerationDelayTimer >= ((ManaUsingEntity)livingEntity).manaattributes$getDepletedManaRegenerationDelayThreshold()
			) {
				if (mana < ((ManaUsingEntity)livingEntity).manaattributes$getUnreservedMana() || ((ManaUsingEntity)livingEntity).manaattributes$getRegeneratedMana() < 0) {
					((ManaUsingEntity) ((ManaUsingEntity)livingEntity)).manaattributes$addMana(((ManaUsingEntity)livingEntity).manaattributes$getRegeneratedMana());
				}
				if (mana > ((ManaUsingEntity)livingEntity).manaattributes$getUnreservedMana()) {
					((ManaUsingEntity) livingEntity).manaattributes$setMana(((ManaUsingEntity) livingEntity).manaattributes$getUnreservedMana());
				}
				manaTickTimer = 0;
			}

			((ManaUsingEntity) livingEntity).manaattributes$setManaTickTimer(manaTickTimer);
			((ManaUsingEntity) livingEntity).manaattributes$setDepletedManaRegenerationDelayTimer(depletedManaRegenerationDelayTimer);
			((ManaUsingEntity) livingEntity).manaattributes$setManaRegenerationDelayTimer(manaRegenerationDelayTimer);
			((ManaUsingEntity) livingEntity).manaattributes$setDelayManaRegeneration(delayManaRegeneration);
		}
		if (((ManaUsingEntity) livingEntity).manaattributes$applyOldMana()) {
			if (((ManaUsingEntity) livingEntity).manaattributes$applyMaxMana()) {
				((ManaUsingEntity) livingEntity).manaattributes$setOldMana(((ManaUsingEntity) livingEntity).manaattributes$getUnreservedMana());
				((ManaUsingEntity) livingEntity).manaattributes$setApplyMaxMana(false);
			}
			if (((ManaUsingEntity) livingEntity).manaattributes$getOldMana() != null) {
				((ManaUsingEntity) livingEntity).manaattributes$setMana(((ManaUsingEntity) livingEntity).manaattributes$getOldMana());
				((ManaUsingEntity) livingEntity).manaattributes$setOldMana(null);
			}
		} else {
			((ManaUsingEntity) livingEntity).manaattributes$setApplyOldMana(true);
		}
	}
}
