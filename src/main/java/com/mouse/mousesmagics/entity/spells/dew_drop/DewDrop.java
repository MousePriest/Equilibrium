package com.mouse.mousesmagics.entity.spells.dew_drop;

import com.mouse.mousesmagics.entity.spells.drip.Drop;
import com.mouse.mousesmagics.registries.MMEffectRegisteries;
import com.mouse.mousesmagics.registries.MMEntityRegistries;
import com.mouse.mousesmagics.registries.MMSoundRegistries;
import com.mouse.mousesmagics.registries.SpellRegistries;
import com.mouse.mousesmagics.utils.MMParticleHelper;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.spells.AbstractMagicProjectile;
import io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions;
import io.redspace.ironsspellbooks.registries.EntityRegistry;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

public class DewDrop extends AbstractMagicProjectile implements GeoEntity {
    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);
    HashMap<UUID, Integer> victims;

    public DewDrop(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setNoGravity(true);
    }

    public DewDrop(Level pLevel, LivingEntity pShooter) {
        this(MMEntityRegistries.DROP.get(), pLevel);
        this.setOwner(pShooter);
    }

    @Override
    protected void onHitBlock(BlockHitResult blockHitResult) {
        super.onHitBlock(blockHitResult);
        discard();
    }

    @Override
    protected void onHitEntity(@NotNull EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        if(getOwner() instanceof LivingEntity owner){
            owner.addEffect(new MobEffectInstance(MMEffectRegisteries.FLOWER_FOOD, 200, 0, false, false));
            DamageSources.applyDamage(entityHitResult.getEntity(), getDamage(), SpellRegistries.DEW_DROP.get().getDamageSource(this, getOwner()));
            consumeEntityImpact(entityHitResult, true);
        }
    }

    @Override
    public void trailParticles() {}

    @Override
    public void impactParticles(double x, double y, double z) {
        MagicManager.spawnParticles(level(), MMParticleHelper.DEW_DROP, x, y, z, 5, .1, .1, .1, .5, true);
        MagicManager.spawnParticles(level, new BlastwaveParticleOptions(1f, 0.95f, 0.95f, 1.25f), getX(), getY() + 0.06, getZ(), 1, 0, 0, 0, 0, true);    }

    @Override
    public float getSpeed() {
        return 1.4f;
    }

    @Override
    protected void doImpactSound(Holder<SoundEvent> sound) {
        level.playSound(null, getX(), getY(), getZ(), sound, SoundSource.NEUTRAL, .8f, 1.35f + Utils.random.nextFloat() * .3f);
    }

    @Override
    public Optional<Holder<SoundEvent>> getImpactSound() {
        return Optional.of(MMSoundRegistries.DEW_CAST);
    }

    private final AnimationController<DewDrop> animationController = new AnimationController<>(this, "controller", 0, this::predicate);

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(animationController);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return geoCache;
    }

    private PlayState predicate(AnimationState<DewDrop> event) {
        // Sounds pretty cool!
        event.getController().setAnimation(RawAnimation.begin().then("animation.blank", Animation.LoopType.LOOP));

        return PlayState.CONTINUE;
    }
}
