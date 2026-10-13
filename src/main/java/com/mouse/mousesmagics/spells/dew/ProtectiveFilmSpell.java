package com.mouse.mousesmagics.spells.dew;

import com.mouse.mousesmagics.MousesMagics;
import com.mouse.mousesmagics.effect.CalmEffect;
import com.mouse.mousesmagics.registries.MMEffectRegisteries;
import com.mouse.mousesmagics.registries.MMSchoolRegistries;
import com.mouse.mousesmagics.utils.MMParticleHelper;
import io.redspace.ironsspellbooks.IronsSpellbooks;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.util.ModTags;
import io.redspace.ironsspellbooks.util.ParticleHelper;
import net.acetheeldritchking.aces_spell_utils.spells.ASSpellAnimations;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class ProtectiveFilmSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(MousesMagics.MOD_ID, "protective_film");

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(
                Component.translatable("ui.irons_spellbooks.effect_length", Utils.timeFromTicks(getDuration(spellLevel, caster), 1))
                );
    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.COMMON)
            .setSchoolResource(MMSchoolRegistries.DEW_RESOURCE)
            .setMaxLevel(10)
            .setCooldownSeconds(25)
            .build();

    public ProtectiveFilmSpell() {
        this.manaCostPerLevel = 7;
        this.baseSpellPower = 15;
        this.spellPowerPerLevel = 1;
        this.castTime = 0;
        this.baseManaCost = 25;
    }

    @Override
    public CastType getCastType() {
        return CastType.INSTANT;
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
    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        level.getEntitiesOfClass(LivingEntity.class, AABB.ofSize(entity.getBoundingBox().getCenter(), 6, 6, 6)).forEach(livingEntity -> {
            IronsSpellbooks.LOGGER.debug("cleanse: {}", livingEntity);
            if (Utils.shouldHealEntity(entity, livingEntity)) {
                livingEntity.addEffect(new MobEffectInstance(MMEffectRegisteries.DEW_FILM,
                        getDuration(spellLevel, entity), 0, false, false, true));
                MagicManager.spawnParticles(level, MMParticleHelper.DEW_DROP, livingEntity.getX(), livingEntity.getY() + 1.25, livingEntity.getZ(), 15, livingEntity.getBbWidth() * 0.5, livingEntity.getBbWidth() * 0.5, livingEntity.getBbWidth() * 0.5, 0, false);
            }
        });

        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
    }

    public int getDuration(int spellLevel, LivingEntity caster) {
        return (int) (getSpellPower(spellLevel, caster) * 20);
    }

    @Override
    public AnimationHolder getCastStartAnimation() {
        return ASSpellAnimations.ANIMATION_CLAP;
    }
}
