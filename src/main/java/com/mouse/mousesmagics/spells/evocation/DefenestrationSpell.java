/*package com.mouse.mousesmagics.spells.evocation;

import com.mouse.mousesmagics.MousesMagics;
import com.mouse.mousesmagics.entity.spells.defenestration.DefenestrationProjectile;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.Optional;

public class DefenestrationSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(MousesMagics.MOD_ID, "defenestration");

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(
                Component.translatable("ui.irons_spellbooks.strength", String.format("%s%%", (int) (getStrength(spellLevel, caster) * 100 / getStrength(1, null)))),
                Component.translatable("ui.irons_spellbooks.damage", Utils.stringTruncation(getSpellPower(spellLevel, caster), 1))
        );
    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.COMMON)
            .setSchoolResource(SchoolRegistry.EVOCATION_RESOURCE)
            .setMaxLevel(5)
            .setCooldownSeconds(20)
            .build();

    public DefenestrationSpell() {
        this.manaCostPerLevel = 3;
        this.baseSpellPower = 5;
        this.spellPowerPerLevel = 2;
        this.castTime = 15;
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
    public Optional<SoundEvent> getCastFinishSound() {
        return Optional.of(SoundRegistry.ABYSSAL_SHROUD.get());
    }

    @Override
    public void onCast(Level world, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        DefenestrationProjectile defenestrationProjectile = new DefenestrationProjectile(world, entity);
        defenestrationProjectile.setPos(entity.position().add(0, entity.getEyeHeight() - defenestrationProjectile.getBoundingBox().getYsize() * .5f, 0));
        defenestrationProjectile.shoot(entity.getLookAngle());
        defenestrationProjectile.setDamage(getDamage(spellLevel, entity));
            world.addFreshEntity(defenestrationProjectile);
            super.onCast(world, spellLevel, entity, castSource, playerMagicData);
    }

    public float getRange(int spellLevel, LivingEntity caster) {
        return 8;
    }

    public float getStrength(int spellLevel, LivingEntity caster) {
        return getSpellPower(spellLevel, caster) * .2f;
    }

    public float getDamage(int spellLevel, LivingEntity caster) {
        return getSpellPower(spellLevel, caster);
    }

    @Override
    public AnimationHolder getCastFinishAnimation() {
        return SpellAnimations.ONE_HANDED_VERTICAL_UPSWING_ANIMATION;
    }
}*/
