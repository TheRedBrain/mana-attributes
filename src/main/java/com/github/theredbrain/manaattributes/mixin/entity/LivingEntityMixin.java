package com.github.theredbrain.manaattributes.mixin.entity;

import com.github.theredbrain.manaattributes.ManaAttributes;
import com.github.theredbrain.manaattributes.entity.LivingEntityHelper;
import com.github.theredbrain.manaattributes.entity.ManaUsingEntity;
import net.minecraft.core.Holder;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
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
	public abstract double getAttributeValue(Holder<Attribute> attribute);

	@Unique
	private int manaTickTimer = 0;
	@Unique
	private int depletedManaRegenerationDelayTimer = 0;
	@Unique
	private int manaRegenerationDelayTimer = 0;
	@Unique
	private boolean delayManaRegeneration = false;
	@Unique
	private Float oldMana = null;
	@Unique
	private boolean applyOldMana = true;
	@Unique
	private boolean applyMaxMana = false;

	@Unique
	private static final EntityDataAccessor<Float> MANA = SynchedEntityData.defineId(LivingEntity.class, EntityDataSerializers.FLOAT);

	public LivingEntityMixin(EntityType<?> type, Level world) {
		super(type, world);
	}

	@Inject(method = "defineSynchedData", at = @At("RETURN"))
	protected void manaattributes$initDataTracker(SynchedEntityData.Builder builder, CallbackInfo ci) {
		builder.define(MANA, 10.0F);
	}

	@Inject(method = "createLivingAttributes", at = @At("RETURN"))
	private static void manaattributes$createLivingAttributes(CallbackInfoReturnable<AttributeSupplier.Builder> cir) {
		cir.getReturnValue()
				.add(ManaAttributes.MANA_REGENERATION)
				.add(ManaAttributes.MAX_MANA)
				.add(ManaAttributes.MANA_REGENERATION_DELAY_THRESHOLD)
				.add(ManaAttributes.DEPLETED_MANA_REGENERATION_DELAY_THRESHOLD)
				.add(ManaAttributes.MANA_TICK_THRESHOLD)
				.add(ManaAttributes.RESERVED_MANA)
		;
	}

	@Inject(method = "readAdditionalSaveData", at = @At("HEAD"))
	public void manaattributes$readCustomData_head(ValueInput view, CallbackInfo ci) {
		float mana;
		if (view.contains("mana")) {
			mana = view.getFloatOr("mana", this.manaattributes$getMaxMana());
		} else {
			mana = Float.MIN_VALUE;
		}
		if (mana != Float.MIN_VALUE) {
			this.oldMana = mana;
		}
	}

	@Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
	public void manaattributes$readCustomData_tail(ValueInput view, CallbackInfo ci) {

		if (view.contains("mana")) {
			this.manaattributes$setMana(view.getFloatOr("mana", this.manaattributes$getMaxMana()));
		}

	}

	@Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
	public void manaattributes$writeCustomData(ValueOutput view, CallbackInfo ci) {

		view.putFloat("mana", this.manaattributes$getMana());

	}

	@Inject(method = "tick", at = @At("TAIL"))
	public void manaattributes$tick(CallbackInfo ci) {
		LivingEntityHelper.tick(((LivingEntity)(Object)this));
	}

	public int manaattributes$getManaTickTimer() {
		return this.manaTickTimer;
	}

	public void manaattributes$setManaTickTimer(int manaTickTimer) {
		this.manaTickTimer = manaTickTimer;
	}

	public int manaattributes$getDepletedManaRegenerationDelayTimer() {
		return this.depletedManaRegenerationDelayTimer;
	}

	public void manaattributes$setDepletedManaRegenerationDelayTimer(int depletedManaRegenerationDelayTimer) {
		this.depletedManaRegenerationDelayTimer = depletedManaRegenerationDelayTimer;
	}

	public int manaattributes$getManaRegenerationDelayTimer() {
		return this.manaRegenerationDelayTimer;
	}

	public void manaattributes$setManaRegenerationDelayTimer(int manaRegenerationDelayTimer) {
		this.manaRegenerationDelayTimer = manaRegenerationDelayTimer;
	}

	public boolean manaattributes$delayManaRegeneration() {
		return this.delayManaRegeneration;
	}

	public void manaattributes$setDelayManaRegeneration(boolean delayManaRegeneration) {
		this.delayManaRegeneration = delayManaRegeneration;
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
	public float manaattributes$getUnreservedMana() {
		return this.manaattributes$getMaxMana() - ((this.manaattributes$getMaxMana() * this.manaattributes$getReservedMana()) / 100);
	}

	@Override
	public float manaattributes$getMaxMana() {
		return (float) this.getAttributeValue(ManaAttributes.MAX_MANA);
	}

	@Override
	public float manaattributes$getReservedMana() {
		return (float) this.getAttributeValue(ManaAttributes.RESERVED_MANA);
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
		return this.entityData.get(MANA);
	}

	@Override
	public void manaattributes$setMana(float mana) {
		this.entityData.set(MANA, Mth.clamp(mana, 0, this.manaattributes$getUnreservedMana()));
	}

	public Float manaattributes$getOldMana() {
		return this.oldMana;
	}

	public void manaattributes$setOldMana(Float oldMana) {
		this.oldMana = oldMana;
	}

	@Override
	public boolean manaattributes$applyOldMana() {
		return this.applyOldMana;
	}

	@Override
	public void manaattributes$setApplyOldMana(boolean applyOldMana) {
		this.applyOldMana = applyOldMana;
	}

	@Override
	public boolean manaattributes$applyMaxMana() {
		return this.applyMaxMana;
	}

	@Override
	public void manaattributes$setApplyMaxMana(boolean applyMaxMana) {
		this.applyMaxMana = applyMaxMana;
	}
}
