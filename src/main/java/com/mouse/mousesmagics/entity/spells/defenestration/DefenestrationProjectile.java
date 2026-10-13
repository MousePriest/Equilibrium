/*package com.mouse.mousesmagics.entity.spells.defenestration;

import com.mouse.mousesmagics.registries.MMEntityRegistries;
import com.mouse.mousesmagics.registries.SpellRegistries;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.spells.AbstractMagicProjectile;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

public class DefenestrationProjectile extends AbstractMagicProjectile implements GeoEntity {
    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);
    HashMap<UUID, Integer> victims;

    public DefenestrationProjectile(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.victims = new HashMap<>();
        this.setNoGravity(true);
    }

    public DefenestrationProjectile(Level level, LivingEntity shooter) {
        this(MMEntityRegistries.DEFENESTRATION_PROJECTILE.get(), level);
        setOwner(shooter);
    }

    public void trailParticles() {
    }

    public void impactParticles(double x, double y, double z) {
        MagicManager.spawnParticles(level(), ParticleHelper.BLOOD, x, y, z, 5, .1, .1, .1, .25, true);
    }

    public float getSpeed() {
        return 0.1F;
    }


    protected void onHitEntity(EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        DamageSources.applyDamage(entityHitResult.getEntity(), damage, SpellRegistries.DEFENESTRATION.get().getDamageSource(this, getOwner()));
        pierceOrDiscard();
    }

    protected void onHitBlock(@NotNull BlockHitResult blockHitResult) {
        super.onHitBlock(blockHitResult);
        this.discard();
    }

    @Override
    public Optional<Holder<SoundEvent>> getImpactSound() {
        return Optional.of(SoundRegistry.ICE_BLOCK_IMPACT);
    }

    //Animation
    private final AnimationController<DefenestrationProjectile> animationController = new AnimationController<>(this, "controller", 0, this::predicate);

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(animationController);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return geoCache;
    }

    private PlayState predicate(AnimationState<DefenestrationProjectile> event) {
        // Sounds pretty cool!
        event.getController().setAnimation(RawAnimation.begin().then("animation.blossoming_cuts_idle", Animation.LoopType.LOOP));

        return PlayState.CONTINUE;
    }
}*/
