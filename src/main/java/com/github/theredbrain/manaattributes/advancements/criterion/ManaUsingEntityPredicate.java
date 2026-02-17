package com.github.theredbrain.manaattributes.advancements.criterion;

import com.github.theredbrain.manaattributes.entity.ManaUsingEntity;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.criterion.EntitySubPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

public record ManaUsingEntityPredicate(MinMaxBounds.Ints mana_amount) implements EntitySubPredicate {
	public static final MapCodec<ManaUsingEntityPredicate> CODEC = RecordCodecBuilder.mapCodec(
			instance -> instance.group(MinMaxBounds.Ints.CODEC.optionalFieldOf("mana_amount", MinMaxBounds.Ints.ANY).forGetter(ManaUsingEntityPredicate::mana_amount))
					.apply(instance, ManaUsingEntityPredicate::new)
	);

	@Override
	public MapCodec<? extends EntitySubPredicate> codec() {
		return CODEC;
	}

	@Override
	public boolean matches(Entity entity, ServerLevel serverLevel, @Nullable Vec3 vec3) {
		if (entity instanceof ManaUsingEntity manaUsingEntity) {
			return this.mana_amount.matches(Mth.ceil(manaUsingEntity.manaattributes$getMana()));
		}
		return false;
	}
}
