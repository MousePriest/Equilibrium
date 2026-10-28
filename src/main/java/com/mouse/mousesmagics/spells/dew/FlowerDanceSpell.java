package com.mouse.mousesmagics.spells.dew;

import com.mouse.mousesmagics.MousesMagics;
import com.mouse.mousesmagics.registries.MMParticleRegistries;
import com.mouse.mousesmagics.registries.MMSchoolRegistries;
import com.mouse.mousesmagics.spells.MMSpellAnimations;
import com.mouse.mousesmagics.utils.MMParticleHelper;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.network.particles.ShockwaveParticlesPacket;
import io.redspace.ironsspellbooks.particle.BlastwaveParticleOptions;
import io.redspace.ironsspellbooks.registries.MobEffectRegistry;
import io.redspace.ironsspellbooks.registries.ParticleRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.List;

public class FlowerDanceSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(MousesMagics.MOD_ID, "flower_dance");

    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(
                Component.translatable("ui.irons_spellbooks.effect_length", Utils.timeFromTicks(getDuration(spellLevel, caster), 2)),
                Component.translatable("ui.irons_spellbooks.radius", Utils.stringTruncation(getRadius(spellLevel, caster), 2))
        );
    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.COMMON)
            .setSchoolResource(MMSchoolRegistries.DEW_RESOURCE)
            .setMaxLevel(7)
            .setCooldownSeconds(35)
            .build();

    public FlowerDanceSpell() {
        this.manaCostPerLevel = 5;
        this.baseSpellPower = 12;
        this.spellPowerPerLevel = 2;
        this.castTime = 10;
        this.baseManaCost = 30;
    }

    public CastType getCastType() {
        return CastType.LONG;
    }

    public DefaultConfig getDefaultConfig() {
        return this.defaultConfig;
    }

    public ResourceLocation getSpellResource() {
        return this.spellId;
    }

    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        float radius = getRadius(spellLevel, entity);
        MagicManager.spawnParticles(level, new BlastwaveParticleOptions(MMSchoolRegistries.DEW.get().getTargetingColor(), radius), entity.getX(), entity.getY() + .165f, entity.getZ(), 1, 0, 0, 0, 0, true);
        PacketDistributor.sendToPlayersTrackingEntityAndSelf(entity, new ShockwaveParticlesPacket(new Vec3(entity.getX(), entity.getY() + .165f, entity.getZ()), radius, MMParticleRegistries.FADING_FLOWER_PARTICLE.get()));
        level.getEntities(entity, entity.getBoundingBox().inflate(radius, 4, radius), (target) ->
                        !DamageSources.isFriendlyFireBetween(target, entity)
                                && Utils.hasLineOfSight(level, entity, target, true))
                .forEach(target -> {
                    if (target instanceof LivingEntity livingEntity && livingEntity.distanceToSqr(entity) < radius * radius) {
                        livingEntity.addEffect(new MobEffectInstance(MobEffects.LEVITATION, getDuration(spellLevel, entity), 0, false, false, true));
                        livingEntity.addEffect(new MobEffectInstance(MobEffectRegistry.REND, getDuration(spellLevel, entity), 0, false, false, true));
                        MagicManager.spawnParticles(level, MMParticleHelper.FADING_FLOWER, livingEntity.getX(), livingEntity.getY() + livingEntity.getBbHeight() * .5f, livingEntity.getZ(), 10, livingEntity.getBbWidth() * .5f, livingEntity.getBbHeight() * .5f, livingEntity.getBbWidth() * .5f, .03, false);
                        MagicManager.spawnParticles(level, MMParticleHelper.DEW_DROP, livingEntity.getX(), livingEntity.getY() + livingEntity.getBbHeight() * .5f, livingEntity.getZ(), 10, livingEntity.getBbWidth() * .5f, livingEntity.getBbHeight() * .5f, livingEntity.getBbWidth() * .5f, .03, false);
                    }
                });
        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
    }

    public float getRadius(int spellLevel, LivingEntity caster) {
        return 6 + spellLevel * .75f;
    }

    public int getDuration(int spellLevel, LivingEntity caster) {
        return (int) (getSpellPower(spellLevel, caster) * 3);
    }

    @Override
    public AnimationHolder getCastStartAnimation() {return SpellAnimations.STOMP;}

    @Override
    public AnimationHolder getCastFinishAnimation() {return AnimationHolder.none();}
}

