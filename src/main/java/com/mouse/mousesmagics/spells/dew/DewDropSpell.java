package com.mouse.mousesmagics.spells.dew;

import com.mouse.mousesmagics.MousesMagics;
import com.mouse.mousesmagics.entity.spells.dew_drop.DewDrop;
import com.mouse.mousesmagics.entity.spells.drip.Drop;
import com.mouse.mousesmagics.registries.MMSchoolRegistries;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.spells.CastSource;
import io.redspace.ironsspellbooks.api.spells.CastType;
import io.redspace.ironsspellbooks.api.spells.SpellRarity;
import io.redspace.ironsspellbooks.api.util.Utils;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

import java.util.List;

public class DewDropSpell extends AbstractSpell {
    private final ResourceLocation spellId = ResourceLocation.fromNamespaceAndPath(MousesMagics.MOD_ID, "dew_drop");

    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(Component.translatable("ui.irons_spellbooks.damage", new Object[]{Utils.stringTruncation((double)this.getDamage(spellLevel, caster), 2)}));
    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.COMMON)
            .setSchoolResource(MMSchoolRegistries.DEW_RESOURCE)
            .setMaxLevel(10)
            .setCooldownSeconds(2)
            .build();

    public DewDropSpell() {
        this.manaCostPerLevel = 2;
        this.baseSpellPower = 10;
        this.spellPowerPerLevel = 1;
        this.castTime = 0;
        this.baseManaCost = 20;
    }

    public CastType getCastType() {
        return CastType.INSTANT;
    }

    public DefaultConfig getDefaultConfig() {
        return this.defaultConfig;
    }

    public ResourceLocation getSpellResource() {
        return this.spellId;
    }

    public void onCast(Level world, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        DewDrop dewdrop = new DewDrop(world, entity);
        dewdrop.setPos(entity.position().add((double)0.0F, (double)entity.getEyeHeight() - dewdrop.getBoundingBox().getYsize() * (double)0.5F, (double)0.0F));
        dewdrop.shoot(entity.getLookAngle());
        dewdrop.setDamage(this.getDamage(spellLevel, entity));
        world.addFreshEntity(dewdrop);
        super.onCast(world, spellLevel, entity, castSource, playerMagicData);
    }

    private float getDamage(int spellLevel, LivingEntity entity) {
        return this.getSpellPower(spellLevel, entity) * 0.5f;
    }
}
