package com.mouse.mousesmagics.entity.spells.floral_spear;

import com.mouse.mousesmagics.registries.MMEntityRegistries;
import com.mouse.mousesmagics.registries.MMSoundRegistries;
import com.mouse.mousesmagics.registries.SpellRegistries;
import com.mouse.mousesmagics.utils.MMParticleHelper;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.spells.AbstractMagicProjectile;
import io.redspace.ironsspellbooks.entity.spells.magic_missile.MagicMissileProjectile;
import io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions;
import io.redspace.ironsspellbooks.registries.EntityRegistry;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class FloralSpearProjectile extends AbstractMagicProjectile {

    @Override
    public void trailParticles() {
        Vec3 vec3 = this.position().subtract(getDeltaMovement());
        level.addParticle(MMParticleHelper.FADING_FLOWER, vec3.x, vec3.y, vec3.z, 0, 0, 0);
    }

    @Override
    public void impactParticles(double x, double y, double z) {
        MagicManager.spawnParticles(level(), MMParticleHelper.FADING_FLOWER, x, y, z, 45, .1, .1, .1, 0.5, true);
        MagicManager.spawnParticles(level(), MMParticleHelper.FADING_FLOWER, x, y, z, 25, .1, .1, .1, 1.6, true);
        MagicManager.spawnParticles(level(), MMParticleHelper.DEW_DROP, x, y, z, 15, .1, .1, .1, 0.3, true);
        MagicManager.spawnParticles(level, new BlastwaveParticleOptions(1f, 0.95f, 0.95f, 5f), getX(), getY() + 0.06, getZ(), 1, 0, 0, 0, 0, true);
    }

    @Override
    public float getSpeed() {
        return 3f;
    }

    @Override
    public Optional<Holder<SoundEvent>> getImpactSound() {
        return Optional.empty();
    }

    public FloralSpearProjectile(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setNoGravity(true);
    }

    public FloralSpearProjectile(Level levelIn, LivingEntity shooter) {
        this(MMEntityRegistries.FLORAL_SPEAR_PROJECTILE.get(), levelIn);
        setOwner(shooter);
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {

    }

    @Override
    protected void onHitEntity(@NotNull EntityHitResult entityHitResult) {
        var entity = entityHitResult.getEntity();
        if (DamageSources.applyDamage(entity, damage, SpellRegistries.FLORAL_SPEAR.get().getDamageSource(this, getOwner())) && entity instanceof LivingEntity livingEntity)
            livingEntity.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 60, 0, false, false, true));
    }

    @Override
    protected void onHit(@NotNull HitResult pResult) {
        //irons_spellbooks.LOGGER.debug("Boom");

        if (!level().isClientSide) {
            this.playSound(MMSoundRegistries.DEW_CAST.value(), 6, .65f);}
        super.onHit(pResult);
        this.discardHelper(pResult);
    }

    public int getAge() {
        return tickCount;
    }
}
