/*package com.mouse.mousesmagics.spells.fire;

import com.mouse.mousesmagics.MousesMagics;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.events.SpellHealEvent;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.MagicManager;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.NeoForge;

import java.util.List;

public class PhoenixRebirthSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(MousesMagics.MOD_ID, "phoenix_rebirth");
    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.UNCOMMON)
            .setSchoolResource(SchoolRegistry.FIRE_RESOURCE)
            .setMaxLevel(5)
            .setCooldownSeconds(30)
            .build();

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(Component.translatable("ui.irons_spellbooks.healing", Utils.stringTruncation(getSpellPower(spellLevel, caster), 2)));
    }

    public PhoenixRebirthSpell() {
        this.manaCostPerLevel = 7;
        this.baseSpellPower = 5;
        this.spellPowerPerLevel = 2;
        this.castTime = 1;
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
    public void onCast(Level world, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData)
    {
        float healAmount = entity.getMaxHealth() * entity.getHealth();
        NeoForge.EVENT_BUS.post(new SpellHealEvent(entity, entity, healAmount, getSchoolType()));
        entity.heal(healAmount);
        int count = 16;
        float radius = 1.25f;
        for (int i = 0; i < count; i++) {
            double x, z;
            double theta = Math.toRadians(360 / count) * i;
            x = Math.cos(theta) * radius;
            z = Math.sin(theta) * radius;
            MagicManager.spawnParticles(world, ParticleTypes.FLAME, entity.position().x + x, entity.position().y + 1, entity.position().z + z, 2, 0, 0, 0, 0.1, false);
            MagicManager.spawnParticles(world, ParticleTypes.ASH, entity.position().x + x, entity.position().y + 0.5, entity.position().z + z, 5, 0, 0, 0, 0.1, false);
        }
        super.onCast(world, spellLevel, entity, castSource, playerMagicData);
    }

    @Override
    public AnimationHolder getCastStartAnimation() {
        return SpellAnimations.TOUCH_GROUND_ANIMATION;
    }
}*/

