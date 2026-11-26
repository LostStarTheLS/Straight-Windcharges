package com.ls.straightwindcharges.mixin;

import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.AbstractWindCharge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Keeps Wind Charges (including Breeze Wind Charges) on their original
 * velocity vector: no inertia loss and no randomized inaccuracy/acceleration.
 */
@Mixin(AbstractHurtingProjectile.class)
abstract class AbstractHurtingProjectileMixin {
    /**
     * shoot() adds gaussian noise to its direction according to inaccuracy.
     * Suppress that argument for Wind Charges only; other hurting projectiles
     * keep their normal vanilla behavior.
     */
    @ModifyVariable(
            method = "shoot(DDDFF)V",
            at = @At("HEAD"),
            argsOnly = true,
            ordinal = 1
    )
    private float sw$removeWindChargeInaccuracy(float inaccuracy) {
        return (Object) this instanceof AbstractWindCharge ? 0.0F : inaccuracy;
    }

    /** Clear acceleration after every shot, including shots made through
     * Projectile.shootFromRotation (which delegates to this shoot method).
     */
    @Inject(method = "shoot(DDDFF)V", at = @At("RETURN"))
    private void sw$clearWindChargeAccelerationAfterShoot(
            double x, double y, double z, float velocity, float inaccuracy, CallbackInfo ci
    ) {
        if ((Object) this instanceof AbstractWindCharge) {
            sw$clearAcceleration();
        }
    }

    /** The vanilla inertia is 0.95 in air, which steadily slows projectiles. */
    @Inject(method = "getInertia()F", at = @At("RETURN"), cancellable = true)
    private void sw$useUnitWindChargeInertia(CallbackInfoReturnable<Float> cir) {
        if ((Object) this instanceof AbstractWindCharge) {
            cir.setReturnValue(1.0F);
        }
    }

    private void sw$clearAcceleration() {
        AbstractHurtingProjectilePowerAccessor accessor =
                (AbstractHurtingProjectilePowerAccessor) this;
        accessor.sw$setXPower(0.0D);
        accessor.sw$setYPower(0.0D);
        accessor.sw$setZPower(0.0D);
    }
}
