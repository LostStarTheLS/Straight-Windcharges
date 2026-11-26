package com.ls.straightwindcharges.mixin;

import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(AbstractHurtingProjectile.class)
public interface AbstractHurtingProjectilePowerAccessor {
    @Accessor("xPower")
    void sw$setXPower(double value);

    @Accessor("yPower")
    void sw$setYPower(double value);

    @Accessor("zPower")
    void sw$setZPower(double value);
}
