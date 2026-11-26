package com.ls.straightwindcharges.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Resets the vanilla direction-derived acceleration at wind-charge creation. */
@Mixin(targets = "net.minecraft.world.entity.projectile.windcharge.AbstractWindCharge")
abstract class AbstractWindChargeMixin implements WindChargeMarker {
    @Inject(method = "<init>", at = @At("RETURN"))
    private void sw$clearAccelerationAfterWindChargeConstruction(CallbackInfo ci) {
        AbstractHurtingProjectilePowerAccessor accessor =
                (AbstractHurtingProjectilePowerAccessor) this;
        accessor.sw$setXPower(0.0D);
        accessor.sw$setYPower(0.0D);
        accessor.sw$setZPower(0.0D);
    }
}
