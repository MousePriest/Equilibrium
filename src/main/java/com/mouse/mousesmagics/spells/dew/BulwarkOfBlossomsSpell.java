package com.mouse.mousesmagics.spells.dew;

import com.mouse.mousesmagics.MousesMagics;
import com.mouse.mousesmagics.effect.CalmEffect;
import com.mouse.mousesmagics.registries.MMEffectRegisteries;
import com.mouse.mousesmagics.registries.MMSchoolRegistries;
import com.mouse.mousesmagics.utils.MMParticleHelper;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import io.redspace.ironsspellbooks.particle.SwirlingParticleOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class BulwarkOfBlossomsSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(MousesMagics.MOD_ID, "bulwark_of_blossoms");

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(
                Component.translatable("ui.irons_spellbooks.effect_length", Utils.timeFromTicks(getDuration(spellLevel, caster), 1))
        );
    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.EPIC)
            .setSchoolResource(MMSchoolRegistries.DEW_RESOURCE)
            .setMaxLevel(1)
            .setCooldownSeconds(4)
            .build();

    public BulwarkOfBlossomsSpell() {
        this.manaCostPerLevel = 0;
        this.baseSpellPower = 17;
        this.spellPowerPerLevel = 1;
        this.castTime = 0;
        this.baseManaCost = 70;
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
    public void onCast(Level world, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        entity.addEffect(new MobEffectInstance(MMEffectRegisteries.BLOSSOM_BULWARK, (int) getDuration(spellLevel, entity), 0, false, false, true));
        MagicManager.spawnParticles(world, new SwirlingParticleOptions(MMParticleHelper.FADING_FLOWER, new Vec3(0, 0, 0), new Vec3(1, 0, 0),
                new Vec3(0, 0, 5), new Vec3(0.25, 0.25,2)), entity.getX(), entity.getY() + 1, entity.getZ(), 35, 0, 0.2, 0, 0.1, false);
        super.onCast(world, spellLevel, entity, castSource, playerMagicData);
    }

    public int getDuration(int spellLevel, LivingEntity caster) {
        return (int) (getSpellPower(spellLevel, caster) / 3);
    }

    @Override
    public AnimationHolder getCastStartAnimation() {
        return SpellAnimations.SLASH_ANIMATION;
    }
}

