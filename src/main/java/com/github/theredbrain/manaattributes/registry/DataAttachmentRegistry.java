package com.github.theredbrain.manaattributes.registry;

import com.github.theredbrain.manaattributes.ManaAttributes;
import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.network.codec.ByteBufCodecs;

public class DataAttachmentRegistry {
	public static AttachmentType<Float> MANA;

	public static void init() {
	}

	static {
		MANA = AttachmentRegistry.create(ManaAttributes.identifier("mana"), builder -> builder
				.persistent(Codec.FLOAT)
				.syncWith(ByteBufCodecs.FLOAT, AttachmentSyncPredicate.all())
		);
	}
}
