package com.mouse.mousesmagics.spells.dew;

import com.mouse.mousesmagics.MousesMagics;
import com.mouse.mousesmagics.registries.MMSchoolRegistries;
import com.mouse.mousesmagics.spells.MMSpellAnimations;
import com.mouse.mousesmagics.utils.MMParticleHelper;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.capabilities.magic.RecastInstance;
import io.redspace.ironsspellbooks.particle.TraceParticleOptions;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class PetalOnTheWindSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(MousesMagics.MOD_ID, "petal_on_the_wind");
    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.RARE)
            .setSchoolResource(MMSchoolRegistries.DEW_RESOURCE)
            .setMaxLevel(5)
            .setCooldownSeconds(20)
            .build();

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(Component.translatable("ui.irons_spellbooks.distance", Utils.stringTruncation(getDistance(spellLevel, caster), 1)));
    }

    public PetalOnTheWindSpell() {
        this.baseSpellPower = 12;
        this.spellPowerPerLevel = 2;
        this.baseManaCost = 30;
        this.manaCostPerLevel = 10;
        this.castTime = 10;
    }

    @Override
    public CastType getCastType() {
        return CastType.LONG;
    }

    @Override
    public DefaultConfig getDefaultConfig() {
        return defaultConfig;
    }

    @Override
    public ResourceLocation getSpellResource() {
        return spellId;
    }

    @Override
    public Optional<SoundEvent> getCastStartSound() {
        return Optional.empty();
    }

    @Override
    public Optional<SoundEvent> getCastFinishSound() {
        return Optional.of(SoundRegistry.BLOOD_STEP.get());
    }

    @Override
    public int getRecastCount(int spellLevel, @Nullable LivingEntity entity) {
        return spellLevel;
    }

    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        var recasts = playerMagicData.getPlayerRecasts();
        if (!recasts.hasRecastForSpell(getSpellId())) {
            recasts.addRecast(new RecastInstance(getSpellId(), spellLevel, getRecastCount(spellLevel, entity), 240, castSource, null), playerMagicData);
        }

        Vec3 forward = entity.getForward();
        Vec3 end = Utils.raycastForBlock(level, entity.getEyePosition(), entity.getEyePosition().add(forward.scale(getDistance(spellLevel, entity))), ClipContext.Fluid.NONE).getLocation();
        AABB hitbox = entity.getHitbox().expandTowards(end.subtract(entity.getEyePosition())).inflate(2);
        var targetableEntities = level.getEntities(entity, hitbox, e ->
                !e.isSpectator() &&
                        (e instanceof LivingEntity || e instanceof Projectile) &&
                        e.getBoundingBox().getCenter().subtract(entity.getBoundingBox().getCenter()).normalize().dot(entity.getForward()) >= .85);
        targetableEntities.sort(Comparator.comparingDouble(e -> e.distanceToSqr(entity)));
        Vec3 rayVector = end.subtract(entity.getEyePosition());
        Vec3 impulse = rayVector.scale(1 / 6f).add(0, 0.1, 0);
        entity.setDeltaMovement(entity.getDeltaMovement().scale(0.2).add(impulse));
        entity.hurtMarked = true;
        entity.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 30, 0, false, false, true));

        int trailParticles = 15;
        double speed = rayVector.length() / 12.0 * .75;
        for (int i = 0; i < trailParticles; i++) {
            Vec3 particleStart = entity.getBoundingBox().getCenter().add(Utils.getRandomVec3(1 + entity.getBbWidth()));
            Vec3 particleDew = particleStart.add(rayVector);
            MagicManager.spawnParticles(level, new TraceParticleOptions(Utils.v3f(particleDew), new Vector3f(1f, 1f, 1f)), particleStart.x, particleStart.y, particleStart.z, 1, 0, 0, 0, speed, false);
        }

        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
    }

    public float getDistance(int spellLevel, LivingEntity entity) {return getSpellPower(spellLevel, entity) * .4f;}

    @Override
    public AnimationHolder getCastStartAnimation() {return AnimationHolder.none();}

    @Override
    public AnimationHolder getCastFinishAnimation() {return MMSpellAnimations.DANCE_1;}
}
