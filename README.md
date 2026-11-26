# Straight Windcharges

A Fabric mod for Minecraft 1.21.11 that makes Wind Charges travel on a straight trajectory at constant speed. It targets `AbstractWindCharge`, so both regular `WindCharge` and `BreezeWindCharge` receive the behavior. It does not add a timer, distance cap, or custom despawn rule.

## Build

Requirements: Java 21 (JDK), and `curl` or `wget` plus `unzip` the first time the included lightweight Gradle bootstrap is run. The bootstrap downloads the pinned Gradle 9.2.0 distribution to the normal Gradle user cache.

```sh
./gradlew build
```

The compiled mod JAR is written to `build/libs/straight-windcharges-1.0.0.jar`.

## Implementation

- `AbstractHurtingProjectileMixin` applies only when the projectile is an `AbstractWindCharge` (including its subclasses): it forces inertia to `1.0F`, disables shot inaccuracy, and clears the acceleration fields at construction and after shooting.
- `AbstractHurtingProjectilePowerAccessor` provides Mixin access to the vanilla acceleration fields.
- The mod metadata uses `environment: "*"`; the gameplay changes run on the logical server, including the integrated server.

The build uses Fabric Loom and Mojang Official Mappings. There is no Fabric API or NeoForge dependency.
