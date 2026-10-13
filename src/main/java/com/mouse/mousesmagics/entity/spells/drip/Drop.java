package com.mouse.mousesmagics.entity.spells.drip;

import com.mouse.mousesmagics.registries.MMEntityRegistries;
import com.mouse.mousesmagics.registries.MMSoundRegistries;
import com.mouse.mousesmagics.registries.SpellRegistries;
import com.mouse.mousesmagics.utils.MMParticleHelper;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.spells.AbstractMagicProjectile;
import io.redspace.ironsspellbooks.network.SyncManaPacket;
import io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

public class Drop extends AbstractMagicProjectile implements GeoEntity {
    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);
    HashMap<UUID, Integer> victims;


    public Drop(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setNoGravity(true);
    }

    public Drop(Level pLevel, LivingEntity pShooter) {
        this(MMEntityRegistries.DROP.get(), pLevel);
        this.setOwner(pShooter);
    }

    public void shoot(Vec3 rotation, float innaccuracy) {
        Vec3 offset = Utils.getRandomVec3(1).normalize().scale(innaccuracy);
        super.shoot(rotation.add(offset));
    }

    @Override
    public void trailParticles() {}

    @Override
    public void impactParticles(double x, double y, double z) {
        MagicManager.spawnParticles(level(), MMParticleHelper.DEW_DROP, x, y, z, 5, .1, .1, .1, .5, true);
        MagicManager.spawnParticles(level, new BlastwaveParticleOptions(1f, 0.95f, 0.95f, 1.25f), getX(), getY() + 0.06, getZ(), 1, 0, 0, 0, 0, true);    }

    @Override
    public float getSpeed() {
        return 2f;
    }

    @Override
    protected void doImpactSound(Holder<SoundEvent> sound) {
        level.playSound(null, getX(), getY(), getZ(), sound, SoundSource.NEUTRAL, .8f, 1.35f + Utils.random.nextFloat() * .3f);
    }

    @Override
    public Optional<Holder<SoundEvent>> getImpactSound() {
        return Optional.of(MMSoundRegistries.DEW_CAST);
    }

    @Override
    protected void onHit(@NotNull HitResult hitResult) {
        if (!this.level.isClientSide) {
            impactParticles(xOld, yOld, zOld);
            getImpactSound().ifPresent(this::doImpactSound);
            float explosionRadius = getExplosionRadius();
            var entities = level.getEntities(this, this.getBoundingBox().inflate(explosionRadius));
            for (Entity entity : entities) {
                double distance = entity.distanceToSqr(hitResult.getLocation());
                if (distance < explosionRadius * explosionRadius && canHitEntity(entity)) {
                    DamageSources.applyDamage(entity, damage, SpellRegistries.DRIP.get().getDamageSource(this, getOwner()));
                    if(getOwner() instanceof LivingEntity owner){
                        var pmg = MagicData.getPlayerMagicData(owner);
                        pmg.addMana(10);
                        if (entity instanceof ServerPlayer serverPlayer) {
                            PacketDistributor.sendToPlayer(serverPlayer, new SyncManaPacket(pmg));
                        }
                    }
                }
            }
            this.discardHelper(hitResult);
        }
    }

    private final AnimationController<Drop> animationController = new AnimationController<>(this, "controller", 0, this::predicate);

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(animationController);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return geoCache;
    }

    private PlayState predicate(AnimationState<Drop> event) {
        // Sounds pretty cool!
        event.getController().setAnimation(RawAnimation.begin().then("animation.blank", Animation.LoopType.LOOP));

        return PlayState.CONTINUE;
    }
}
