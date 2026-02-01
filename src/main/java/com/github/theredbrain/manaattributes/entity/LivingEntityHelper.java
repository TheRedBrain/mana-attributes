package com.github.theredbrain.manaattributes.entity;

import net.minecraft.world.entity.LivingEntity;

public class LivingEntityHelper {

	public static void tick(LivingEntity livingEntity) {

		if (!livingEntity.level().isClientSide()) {

			int manaTickTimer = ((ManaUsingEntity) livingEntity).manaattributes$getManaTickTimer();
			int depletedManaRegenerationDelayTimer = ((ManaUsingEntity) livingEntity).manaattributes$getDepletedManaRegenerationDelayTimer();
			int manaRegenerationDelayTimer = ((ManaUsingEntity) livingEntity).manaattributes$getManaRegenerationDelayTimer();
			boolean delayManaRegeneration = ((ManaUsingEntity) livingEntity).manaattributes$delayManaRegeneration();

			double mana = DataAttachmentHelper.getMana(livingEntity);

			manaTickTimer++;

			if (mana <= 0 && delayManaRegeneration) {
				depletedManaRegenerationDelayTimer = ((ManaUsingEntity) livingEntity).manaattributes$getDepletedManaRegenerationDelayThreshold();
				manaRegenerationDelayTimer = 0;
				delayManaRegeneration = false;
			}
			if (mana > 0 && !delayManaRegeneration) {
				delayManaRegeneration = true;
			}
			if (depletedManaRegenerationDelayTimer > ((ManaUsingEntity) livingEntity).manaattributes$getDepletedManaRegenerationDelayThreshold()) {
				depletedManaRegenerationDelayTimer--;
			}
			if (manaRegenerationDelayTimer > ((ManaUsingEntity) livingEntity).manaattributes$getManaRegenerationDelayThreshold()) {
				manaRegenerationDelayTimer--;
			}

			if (
					manaTickTimer >= ((ManaUsingEntity) livingEntity).manaattributes$getManaTickThreshold()
							&& manaRegenerationDelayTimer <= 0
							&& depletedManaRegenerationDelayTimer <= 0
			) {
				if (mana < ((ManaUsingEntity) livingEntity).manaattributes$getUnreservedMana() || ((ManaUsingEntity) livingEntity).manaattributes$getRegeneratedMana() < 0) {
					((ManaUsingEntity) livingEntity).manaattributes$addMana(((ManaUsingEntity) livingEntity).manaattributes$getRegeneratedMana());
				}
				if (mana > ((ManaUsingEntity) livingEntity).manaattributes$getUnreservedMana()) {
					DataAttachmentHelper.setMana(livingEntity, ((ManaUsingEntity) livingEntity).manaattributes$getUnreservedMana());
				}
				manaTickTimer = 0;
			}

			((ManaUsingEntity) livingEntity).manaattributes$setManaTickTimer(manaTickTimer);
			((ManaUsingEntity) livingEntity).manaattributes$setDepletedManaRegenerationDelayTimer(depletedManaRegenerationDelayTimer);
			((ManaUsingEntity) livingEntity).manaattributes$setManaRegenerationDelayTimer(manaRegenerationDelayTimer);
			((ManaUsingEntity) livingEntity).manaattributes$setDelayManaRegeneration(delayManaRegeneration);
		}
		if (((ManaUsingEntity) livingEntity).manaattributes$delayedMaxValueApplication()) {
			DataAttachmentHelper.setMana(livingEntity, ((ManaUsingEntity) livingEntity).manaattributes$getUnreservedMana());
			((ManaUsingEntity) livingEntity).manaattributes$setDelayedMaxValueApplication(false);
		}
		if (((ManaUsingEntity) livingEntity).manaattributes$delayMaxValueApplication()) {
			((ManaUsingEntity) livingEntity).manaattributes$setDelayedMaxValueApplication(true);
			((ManaUsingEntity) livingEntity).manaattributes$setDelayMaxValueApplication(false);
		}
	}
}
