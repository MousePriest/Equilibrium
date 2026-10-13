/*package com.mouse.mousesmagics.spells.ender;

import com.mouse.mousesmagics.MousesMagics;
import com.mouse.mousesmagics.entity.spells.dragon_burst.DragonBurstProjectile;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.spells.CastSource;
import io.redspace.ironsspellbooks.api.spells.CastType;
import io.redspace.ironsspellbooks.api.spells.SpellRarity;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.RecastInstance;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class DragonBurstSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(MousesMagics.MOD_ID, "dragon_burst");

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(
                Component.translatable("ui.irons_spellbooks.damage", Utils.stringTruncation(getDamage(spellLevel, caster), 2)),
                Component.translatable("ui.irons_spellbooks.radius", getRadius(spellLevel, caster))
        );
    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.COMMON)
            .setSchoolResource(SchoolRegistry.ENDER_RESOURCE)
            .setMaxLevel(5)
            .setCooldownSeconds(40)
            .build();

    public DragonBurstSpell() {
        this.manaCostPerLevel = 15;
        this.baseSpellPower = 1;
        this.spellPowerPerLevel = 1;
        this.castTime = 20;
        this.baseManaCost = 100;
    }

    @Override
    public int getRecastCount(int spellLevel, @Nullable LivingEntity entity) {
        return 3;
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
        return Optional.of(SoundRegistry.MAGIC_ARROW_CHARGE.get());
    }

    @Override
    public void onCast(Level world, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        if (!playerMagicData.getPlayerRecasts().hasRecastForSpell(getSpellId())) {
            playerMagicData.getPlayerRecasts().addRecast(new RecastInstance(getSpellId(), spellLevel, getRecastCount(spellLevel, entity), 100, castSource, null), playerMagicData);
        }

        Vec3 origin = entity.getEyePosition();

        DragonBurstProjectile dragonBurst = new DragonBurstProjectile(world, entity);

        dragonBurst.setDamage(getDamage(spellLevel, entity));
        dragonBurst.setExplosionRadius(getRadius(spellLevel, entity));

        dragonBurst.setPos(origin.add(entity.getForward()).subtract(0, dragonBurst.getBbHeight() / 2, 0));
        dragonBurst.shoot(entity.getLookAngle());

        world.addFreshEntity(dragonBurst);
        super.onCast(world, spellLevel, entity, castSource, playerMagicData);
    }

    public float getDamage(int spellLevel, LivingEntity caster) {
        return this.getSpellPower(spellLevel, caster) * 5f;
    }

    public int getRadius(int spellLevel, LivingEntity caster) {
        return 3;
    }
}*/
