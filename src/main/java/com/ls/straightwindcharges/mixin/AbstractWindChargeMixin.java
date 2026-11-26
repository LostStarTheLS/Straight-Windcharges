package com.ls.straightwindcharges.mixin;

import net.minecraft.world.entity.projectile.windcharge.AbstractWindCharge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractWindCharge.class)
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
