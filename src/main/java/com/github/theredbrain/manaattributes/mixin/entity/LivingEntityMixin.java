package com.github.theredbrain.manaattributes.mixin.entity;

import com.github.theredbrain.manaattributes.ManaAttributes;
import com.github.theredbrain.manaattributes.entity.DataAttachmentHelper;
import com.github.theredbrain.manaattributes.entity.LivingEntityHelper;
import com.github.theredbrain.manaattributes.entity.ManaUsingEntity;
import net.minecraft.core.Holder;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.Level;
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
	private boolean delayManaTick = false;
	@Unique
	private boolean delayMaxValueApplication = false;
	@Unique
	private boolean delayedMaxValueApplication = false;

	public LivingEntityMixin(EntityType<?> type, Level world) {
		super(type, world);
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

	@Inject(method = "tick", at = @At("TAIL"))
	public void manaattributes$tick(CallbackInfo ci) {
		LivingEntityHelper.tick(((LivingEntity) (Object) this));
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
	public float manaattributes$getMana() {
		return DataAttachmentHelper.getMana((LivingEntity) (Object) this);
	}

	@Override
	public void manaattributes$addMana(float amount) {
		this.manaattributes$setMana(this.manaattributes$getMana() + amount);
		if (amount < 0) {
			this.manaRegenerationDelayTimer = this.manaattributes$getManaRegenerationDelayThreshold();
			this.manaTickTimer = 0;
		}
	}

	@Override
	public void manaattributes$setMana(float amount) {
		DataAttachmentHelper.setMana((LivingEntity) (Object) this, (float) Mth.clamp(amount, 0.0, this.manaattributes$getUnreservedMana()));
	}

	@Override
	public boolean manaattributes$delayManaTick() {
		return this.delayManaTick;
	}

	@Override
	public void manaattributes$setDelayManaTick(boolean delayManaTick) {
		this.delayManaTick = delayManaTick;
	}

	@Override
	public boolean manaattributes$delayMaxValueApplication() {
		return this.delayMaxValueApplication;
	}

	@Override
	public void manaattributes$setDelayMaxValueApplication(boolean delayMaxValueApplication) {
		this.delayMaxValueApplication = delayMaxValueApplication;
	}

	@Override
	public boolean manaattributes$delayedMaxValueApplication() {
		return this.delayedMaxValueApplication;
	}

	@Override
	public void manaattributes$setDelayedMaxValueApplication(boolean delayedMaxValueApplication) {
		this.delayedMaxValueApplication = delayedMaxValueApplication;
	}
}
