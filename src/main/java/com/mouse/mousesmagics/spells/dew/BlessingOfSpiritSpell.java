package com.mouse.mousesmagics.spells.dew;

import com.mouse.mousesmagics.MousesMagics;
import com.mouse.mousesmagics.registries.MMSchoolRegistries;
import io.redspace.ironsspellbooks.IronsSpellbooks;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.events.SpellHealEvent;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.spells.CastSource;
import io.redspace.ironsspellbooks.api.spells.CastType;
import io.redspace.ironsspellbooks.api.spells.SpellRarity;
import io.redspace.ironsspellbooks.api.util.RaycastBuilder;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.capabilities.magic.TargetEntityCastData;
import io.redspace.ironsspellbooks.network.SyncManaPacket;
import io.redspace.ironsspellbooks.network.particles.HealParticlesPacket;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.List;

public class BlessingOfSpiritSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(MousesMagics.MOD_ID, "blessing_of_spirit");

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(
                Component.translatable("ui.irons_spellbooks.healing", Utils.stringTruncation(getSpellPower(spellLevel, caster), 1))
        );
    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.COMMON)
            .setSchoolResource(MMSchoolRegistries.DEW_RESOURCE)
            .setMaxLevel(10)
            .setCooldownSeconds(10)
            .build();

    public BlessingOfSpiritSpell() {
        this.manaCostPerLevel = 5;
        this.baseSpellPower = 6;
        this.spellPowerPerLevel = 1;
        this.castTime = 30;
        this.baseManaCost = 10;

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
    public boolean checkPreCastConditions(Level level, int spellLevel, LivingEntity entity, MagicData playerMagicData) {
        return Utils.preCastTargetHelper(level, entity, playerMagicData, this, 64, .35f);
    }

    @Override
    public void onCast(Level world, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        if (playerMagicData.getAdditionalCastData() instanceof TargetEntityCastData healTargetingData) {
            var targetEntity = healTargetingData.getTarget((ServerLevel) world);
            if (targetEntity != null) {
                var pmg = MagicData.getPlayerMagicData(entity);
                pmg.addMana(getSpellPower(spellLevel, entity) * 5);
                if (entity instanceof ServerPlayer serverPlayer) {
                    PacketDistributor.sendToPlayer(serverPlayer, new SyncManaPacket(pmg));
                }
            }
        }

        super.onCast(world, spellLevel, entity, castSource, playerMagicData);
    }

    @Nullable
    private LivingEntity findTarget(LivingEntity caster) {
        var target = RaycastBuilder.begin(caster.level(), caster)
                .range(32)
                .checkForBlocks(true)
                .bbInflation(0.35f)
                .build();
        if (target instanceof EntityHitResult entityHit && entityHit.getEntity() instanceof LivingEntity livingTarget) {
            return livingTarget;
        } else {
            return null;
        }
    }

    @Override
    public Vector3f getTargetingColor() {
        return new Vector3f(1f, 0.8f, 0.8f);
    }
}
