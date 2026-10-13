package com.mouse.mousesmagics.spells.dew;

import com.mouse.mousesmagics.MousesMagics;
import com.mouse.mousesmagics.effect.CalmEffect;
import com.mouse.mousesmagics.registries.MMEffectRegisteries;
import com.mouse.mousesmagics.registries.MMSchoolRegistries;
import com.mouse.mousesmagics.spells.MMSpellAnimations;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import net.acetheeldritchking.aces_spell_utils.spells.ASSpellAnimations;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

import java.util.List;

public class BlissSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(MousesMagics.MOD_ID, "bliss");

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(
                Component.translatable("ui.irons_spellbooks.effect_length", Utils.timeFromTicks(getDuration(spellLevel, caster), 1)),
                Component.translatable("attribute.modifier.plus.1", Utils.stringTruncation(getPercentDewPower(spellLevel, caster), 0), Component.translatable("attribute.mousesmagics.dew_spell_power")),
                Component.translatable("attribute.modifier.plus.1", Utils.stringTruncation(getPercentSR(spellLevel, caster), 0), Component.translatable("attribute.irons_spellbooks.spell_resist")),
                Component.translatable("attribute.modifier.take.1", Utils.stringTruncation(CalmEffect.MANA_MAGNITUDE * 100, 0), Component.translatable("attribute.irons_spellbooks.mana_regen")).withStyle(ChatFormatting.RED)
        );
    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.RARE)
            .setSchoolResource(MMSchoolRegistries.DEW_RESOURCE)
            .setMaxLevel(5)
            .setCooldownSeconds(60)
            .build();

    public BlissSpell() {
        this.manaCostPerLevel = 25;
        this.baseSpellPower = 15;
        this.spellPowerPerLevel = 1;
        this.castTime = 30;
        this.baseManaCost = 50;
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
    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {

        entity.addEffect(new MobEffectInstance(MMEffectRegisteries.CALM,
                getDuration(spellLevel, entity), spellLevel - 1, false, false, true));

        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
    }

    private float getPercentDewPower(int spellLevel, LivingEntity entity) {
        return spellLevel * CalmEffect.DEW_POWER_PER_LEVEL * 100;
    }

    private float getPercentSR(int spellLevel, LivingEntity entity) {
        return spellLevel * CalmEffect.SR_PER_LEVEL * 100;
    }

    public int getDuration(int spellLevel, LivingEntity caster) {
        return (int) (getSpellPower(spellLevel, caster) * 20);
    }

    @Override
    public AnimationHolder getCastStartAnimation() {
        return ASSpellAnimations.ANIMATION_SNOWGRAVE_STOP;
    }
}
