package com.github.theredbrain.manaattributes.entity;

import com.github.theredbrain.manaattributes.registry.DataAttachmentRegistry;
import net.minecraft.world.entity.LivingEntity;

public class DataAttachmentHelper {

	public static float getMana(LivingEntity livingEntity) {
		return livingEntity.getAttachedOrElse(DataAttachmentRegistry.MANA, 0.0F);
	}

	public static void setMana(LivingEntity livingEntity, double mana) {
		livingEntity.setAttached(DataAttachmentRegistry.MANA, (float) mana);
	}

}
