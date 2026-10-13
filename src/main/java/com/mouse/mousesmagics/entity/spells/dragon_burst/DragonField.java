/*package com.mouse.mousesmagics.entity.spells.dragon_burst;

import com.mouse.mousesmagics.registries.MMEntityRegistries;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.damage.ISSDamageTypes;
import io.redspace.ironsspellbooks.entity.spells.AoeEntity;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;

import java.util.Optional;

public class DragonField extends AoeEntity {

    private DamageSource damageSource;

    public DragonField(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public DragonField(Level level) {
        this(MMEntityRegistries.DRAGON_FIELD.get(), level);
    }

    @Override
    public void applyEffect(LivingEntity target) {
        if (damageSource == null) {
            damageSource = new DamageSource(DamageSources.getHolderFromResource(target, ISSDamageTypes.FIRE_FIELD), this, getOwner());
        }
        if (!DamageSources.isFriendlyFireBetween(this.getOwner(), target)) {
            DamageSources.ignoreNextKnockback(target);
            if (target.hurt(damageSource, getDamage())) {
            }
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (tickCount > getDelay()) {
            if (!level().isClientSide) {
                if (tickCount > duration + getDelay()) {
                    MagicManager.spawnParticles(level(), ParticleHelper.ENDER_SPARKS, getX(), getY() + 0.06, getZ(), 200, getRadius() * .7f, .2f, getRadius() * .7f, 0.2f, true);
                    discard();
                    return;
                }
                if (tickCount % reapplicationDelay == 0) {
                    checkHits();
                }
                if (tickCount % 5 == 0)
                    this.setRadius(getRadius() + radiusPerTick);
            } else {
                ambientParticles();
            }
        }
    }

    @Override
    public float getParticleCount() {
        return getRadius();
    }

    @Override
    public float getRadius() {
        return 3;
    }

    @Override
    protected float particleYOffset() {
        return .25f;
    }

    @Override
    protected float getParticleSpeedModifier() {
        return 1.4f;
    }

    @Override
    public Optional<ParticleOptions> getParticle() {
        return Optional.of(ParticleHelper.ENDER_SPARKS);
    }
}*/
