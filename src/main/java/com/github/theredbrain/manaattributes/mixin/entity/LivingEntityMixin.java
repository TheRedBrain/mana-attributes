package com.github.theredbrain.manaattributes.mixin.entity;

import com.github.theredbrain.manaattributes.ManaAttributes;
import com.github.theredbrain.manaattributes.entity.ManaUsingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements ManaUsingEntity {

	@Shadow
	public abstract double getAttributeValue(RegistryEntry<EntityAttribute> attribute);

	@Unique
	private int manaTickTimer = 0;
	@Unique
	private int depletedManaRegenerationDelayTimer = 0;
	@Unique
	private int manaRegenerationDelayTimer = 0;
	@Unique
	private boolean delayManaRegeneration = false;

	@Unique
	private static final TrackedData<Float> MANA = DataTracker.registerData(LivingEntity.class, TrackedDataHandlerRegistry.FLOAT);

	public LivingEntityMixin(EntityType<?> type, World world) {
		super(type, world);
	}

	@Inject(method = "initDataTracker", at = @At("RETURN"))
	protected void manaattributes$initDataTracker(DataTracker.Builder builder, CallbackInfo ci) {
		builder.add(MANA, 0.0F);
	}

	@Inject(method = "createLivingAttributes", at = @At("RETURN"))
	private static void manaattributes$createLivingAttributes(CallbackInfoReturnable<DefaultAttributeContainer.Builder> cir) {
		cir.getReturnValue()
				.add(ManaAttributes.MANA_REGENERATION)
				.add(ManaAttributes.MAX_MANA)
				.add(ManaAttributes.MANA_REGENERATION_DELAY_THRESHOLD)
				.add(ManaAttributes.DEPLETED_MANA_REGENERATION_DELAY_THRESHOLD)
				.add(ManaAttributes.MANA_TICK_THRESHOLD)
		;
	}

	@Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
	public void manaattributes$readCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci) {

		if (nbt.contains("mana", NbtElement.NUMBER_TYPE)) {
			this.manaattributes$setMana(nbt.getFloat("mana"));
		}

	}

	@Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
	public void manaattributes$writeCustomDataToNbt(NbtCompound nbt, CallbackInfo ci) {

		nbt.putFloat("mana", this.manaattributes$getMana());

	}

	@Inject(method = "tick", at = @At("TAIL"))
	public void manaattributes$tick(CallbackInfo ci) {
		if (!this.getWorld().isClient) {

			this.manaTickTimer++;

			if (this.manaattributes$getMana() <= 0 && this.delayManaRegeneration) {
				this.depletedManaRegenerationDelayTimer = 0;
				this.manaRegenerationDelayTimer = this.manaattributes$getManaRegenerationDelayThreshold();
				this.delayManaRegeneration = false;
			}
			if (this.manaattributes$getMana() > 0 && !this.delayManaRegeneration) {
				this.delayManaRegeneration = true;
			}
			if (this.depletedManaRegenerationDelayTimer <= this.manaattributes$getDepletedManaRegenerationDelayThreshold()) {
				this.depletedManaRegenerationDelayTimer++;
			}
			if (this.manaRegenerationDelayTimer <= this.manaattributes$getManaRegenerationDelayThreshold()) {
				this.manaRegenerationDelayTimer++;
			}

			if (
					this.manaTickTimer >= this.manaattributes$getManaTickThreshold()
							&& this.manaRegenerationDelayTimer >= this.manaattributes$getManaRegenerationDelayThreshold()
							&& this.depletedManaRegenerationDelayTimer >= this.manaattributes$getDepletedManaRegenerationDelayThreshold()
			) {
				if (this.manaattributes$getMana() < this.manaattributes$getMaxMana()) {
					((ManaUsingEntity) this).manaattributes$addMana(this.manaattributes$getRegeneratedMana());
				} else if (this.manaattributes$getMana() > this.manaattributes$getMaxMana()) {
					this.manaattributes$setMana(this.manaattributes$getMaxMana());
				}
				this.manaTickTimer = 0;
			}

		}
	}

	@Override
	public int manaattributes$getDepletedManaRegenerationDelayThreshold() {
		return (int) this.getAttributeValue(ManaAttributes.DEPLETED_MANA_REGENERATION_DELAY_THRESHOLD);
	}

	@Override
	public int manaattributes$getManaRegenerationDelayThreshold() {
		return (int) this.getAttributeValue(ManaAttributes.MANA_REGENERATION_DELAY_THRESHOLD);
	}

	@Override
	public int manaattributes$getManaTickThreshold() {
		return (int) this.getAttributeValue(ManaAttributes.MANA_TICK_THRESHOLD);
	}

	@Override
	public float manaattributes$getRegeneratedMana() {
		return this.manaattributes$getManaRegeneration();
	}

	@Override
	public float manaattributes$getManaRegeneration() {
		return (float) this.getAttributeValue(ManaAttributes.MANA_REGENERATION);
	}

	@Override
	public float manaattributes$getMaxMana() {
		return (float) this.getAttributeValue(ManaAttributes.MAX_MANA);
	}

	@Override
	public void manaattributes$addMana(float amount) {
		float f = this.manaattributes$getMana();
		this.manaattributes$setMana(f + amount);
		if (amount < 0) {
			this.manaRegenerationDelayTimer = 0;
			this.manaTickTimer = 0;
		}
	}

	@Override
	public float manaattributes$getMana() {
		return this.dataTracker.get(MANA);
	}

	@Override
	public void manaattributes$setMana(float mana) {
		this.dataTracker.set(MANA, MathHelper.clamp(mana, 0, this.manaattributes$getMaxMana()));
	}
}
