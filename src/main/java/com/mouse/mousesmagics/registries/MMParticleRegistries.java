package com.mouse.mousesmagics.registries;

import com.mouse.mousesmagics.MousesMagics;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class MMParticleRegistries {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(Registries.PARTICLE_TYPE, MousesMagics.MOD_ID);

    public static void register(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }

    /* Copying Iron's guide

    To Create Particle:
    - textures + json
    - particle class
    - register it here
    - add it to particle helper
    - register it in client setup

     */

    public static final Supplier<SimpleParticleType> FADING_FLOWER_PARTICLE = PARTICLE_TYPES.register("fading_flower", () -> new SimpleParticleType(false));
    public static final Supplier<SimpleParticleType> SPARKLE_PARTICLE = PARTICLE_TYPES.register("sparkle", () -> new SimpleParticleType(false));
}
