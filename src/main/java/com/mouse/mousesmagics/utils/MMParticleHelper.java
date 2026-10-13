package com.mouse.mousesmagics.utils;

import com.mouse.mousesmagics.registries.MMParticleRegistries;
import io.redspace.ironsspellbooks.particle.SparkParticleOptions;
import net.minecraft.core.particles.ParticleOptions;
import org.joml.Vector3f;

public class MMParticleHelper {

    public static final ParticleOptions FADING_FLOWER = MMParticleRegistries.FADING_FLOWER_PARTICLE.get();
    public static final ParticleOptions SPARKLE = MMParticleRegistries.SPARKLE_PARTICLE.get();
    public static final ParticleOptions DEW_DROP = new SparkParticleOptions(new Vector3f(1f, .9f, .9f));
}
