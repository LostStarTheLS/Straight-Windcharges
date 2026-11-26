package com.ls.straightwindcharges.mixin;

import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/** Keeps Wind Charges on their original velocity vector. */
@Mixin(AbstractHurtingProjectile.class)
abstract class AbstractHurtingProjectileMixin {
    @ModifyVariable(
            method = "shoot(DDDFF)V",
            at = @At("HEAD"),
            argsOnly = true,
            ordinal = 1
    )
    private float sw$removeWindChargeInaccuracy(float inaccuracy) {
        return (Object) this instanceof WindChargeMarker ? 0.0F : inaccuracy;
    }

    @Inject(method = "shoot(DDDFF)V", at = @At("RETURN"))
    private void sw$clearWindChargeAccelerationAfterShoot(
            double x, double y, double z, float velocity, float inaccuracy, CallbackInfo ci
    ) {
        if ((Object) this instanceof WindChargeMarker) {
            sw$clearAcceleration();
        }
    }

    @Inject(method = "getInertia()F", at = @At("RETURN"), cancellable = true)
    private void sw$useUnitWindChargeInertia(CallbackInfoReturnable<Float> cir) {
        if ((Object) this instanceof WindChargeMarker) {
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
