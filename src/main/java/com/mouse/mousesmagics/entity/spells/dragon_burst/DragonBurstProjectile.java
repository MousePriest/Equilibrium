/*package com.mouse.mousesmagics.entity.spells.dragon_burst;

import com.mouse.mousesmagics.registries.MMEntityRegistries;
import com.mouse.mousesmagics.registries.SpellRegistries;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.spells.AbstractMagicProjectile;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

public class DragonBurstProjectile extends AbstractMagicProjectile implements GeoEntity {
    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);
    HashMap<UUID, Integer> victims;

    public DragonBurstProjectile(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.victims = new HashMap<>();
        this.setNoGravity(true);
    }

    public DragonBurstProjectile(Level level, LivingEntity shooter) {
        this(MMEntityRegistries.DRAGON_BURST_PROJECTILE.get(), level);
        setOwner(shooter);
    }

    @Override
    public void trailParticles() {
        var vec = getDeltaMovement();
        var length = vec.length();
        int count = (int) Math.min(20, Math.round(length) * 3) + 1;
        float f = (float) length / count;
        for (int i = 0; i < count; i++) {
            Vec3 random = Utils.getRandomVec3(0.02);
            Vec3 p = vec.scale(f * i);
            level().addParticle(ParticleHelper.ENDER_SPARKS, this.getX() + random.x + p.x, this.getY() + random.y + p.y, this.getZ() + random.z + p.z, random.x, random.y, random.z);
        }
    }


    public void impactParticles(double x, double y, double z) {
        MagicManager.spawnParticles(level(), ParticleHelper.ENDER_SPARKS, getX(), getY() + 0.06, getZ(), 200, getRadius() * .7f, .2f, getRadius() * .7f, 0.2f, true);
    }

    public float getSpeed() {
        return 1F;
    }

    public float getRadius() {
        return 3;
    }

    protected void onHit(@NotNull HitResult hitresult) {
        super.onHit(hitresult);
        createDragonField(Utils.moveToRelativeGroundLevel(level(), hitresult.getLocation(), 2, 6));
        float explosionRadius = getExplosionRadius();
        var entities = level().getEntities(this, this.getBoundingBox().inflate(explosionRadius));
        for (Entity entity : entities) {
            double distance = entity.distanceToSqr(hitresult.getLocation());
            if (distance < explosionRadius * explosionRadius && canHitEntity(entity)) {
                if (Utils.hasLineOfSight(level(), hitresult.getLocation(), entity.position().add(0, entity.getEyeHeight() * .5f, 0), true)) {
                    double p = (1 - Math.pow(Math.sqrt(distance) / (explosionRadius), 3));
                    float damage = (float) (this.damage * p);
                    DamageSources.applyDamage(entity, damage, SpellRegistries.DRAGON_BURST.get().getDamageSource(this, getOwner()));
                }
            }
        }
        this.discardHelper(hitresult);
    }

    public void createDragonField(Vec3 location) {
        if (!level().isClientSide) {
            DragonField dragonField = new DragonField(level());
            dragonField.setOwner(getOwner());
            dragonField.setDuration(200);
            dragonField.setDamage(aoeDamage);
            dragonField.setRadius(getExplosionRadius());
            dragonField.setCircular();
            dragonField.moveTo(location);
            level().addFreshEntity(dragonField);
        }
    }

    float aoeDamage;

    public void setAoeDamage(float damage) {
        this.aoeDamage = damage;
    }

    public float getAoeDamage() {
        return aoeDamage;
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putFloat("AoeDamage", aoeDamage);


    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.aoeDamage = tag.getFloat("AoeDamage");

    }

    @Override
    public Optional<Holder<SoundEvent>> getImpactSound() {
        return Optional.of(SoundRegistry.BLOOD_STEP);
    }

    //Animation
    private final AnimationController<DragonBurstProjectile> animationController = new AnimationController<>(this, "controller", 0, this::predicate);

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(animationController);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return geoCache;
    }

    private PlayState predicate(AnimationState<DragonBurstProjectile> event) {
        // Sounds pretty cool!
        event.getController().setAnimation(RawAnimation.begin().then("animation.blank", Animation.LoopType.LOOP));

        return PlayState.CONTINUE;
    }
}*/
