package com.ls.straightwindcharges.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/** Access to AbstractHurtingProjectile's per-tick acceleration vector. */
@Mixin(targets = "net.minecraft.world.entity.projectile.AbstractHurtingProjectile")
public interface AbstractHurtingProjectilePowerAccessor {
    @Accessor("xPower")
    void sw$setXPower(double value);

    @Accessor("yPower")
    void sw$setYPower(double value);

    @Accessor("zPower")
    void sw$setZPower(double value);
}
