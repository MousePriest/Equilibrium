package com.mouse.mousesmagics.registries;

import com.mouse.mousesmagics.MousesMagics;
import com.mouse.mousesmagics.effect.BlossomBulwarkEffect;
import com.mouse.mousesmagics.effect.CalmEffect;
import com.mouse.mousesmagics.effect.FlowerFoodEffect;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.acetheeldritchking.aces_spell_utils.registries.ASAttributeRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class MMEffectRegisteries {
    public static final DeferredRegister<MobEffect> MOB_EFFECT_DEFERRED_REGISTER = DeferredRegister.create(Registries.MOB_EFFECT, MousesMagics.MOD_ID);

    public static void register(IEventBus eventBus) {
        MOB_EFFECT_DEFERRED_REGISTER.register(eventBus);
    }

    public static final DeferredHolder<MobEffect, MobEffect> CALM = MOB_EFFECT_DEFERRED_REGISTER.register("calm", () -> new CalmEffect(MobEffectCategory.BENEFICIAL, 0xFFC0F0)
            .addAttributeModifier(AttributeRegistry.MANA_REGEN, MousesMagics.namespacePath("mobeffect_calm"), AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, level -> -CalmEffect.MANA_MAGNITUDE)
            .addAttributeModifier(MMAttributeRegistries.DEW_MAGIC_POWER, MousesMagics.namespacePath("mobeffect_calm"), CalmEffect.DEW_POWER_PER_LEVEL, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
            .addAttributeModifier(AttributeRegistry.SPELL_RESIST, MousesMagics.namespacePath("mobeffect_calm"), CalmEffect.SR_PER_LEVEL, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

    public static final DeferredHolder<MobEffect, MobEffect> FLOWER_FOOD = MOB_EFFECT_DEFERRED_REGISTER.register("flower_food", () -> new FlowerFoodEffect(MobEffectCategory.BENEFICIAL, 0xFFFFFF)
            .addAttributeModifier(AttributeRegistry.MANA_REGEN, MousesMagics.namespacePath("mobeffect_flower_food"), 0.2, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));


    public static final DeferredHolder<MobEffect, MobEffect> DEW_FILM = MOB_EFFECT_DEFERRED_REGISTER.register("dew_film", () -> new CalmEffect(MobEffectCategory.BENEFICIAL, 0xFFC0F0)
            .addAttributeModifier(Attributes.BURNING_TIME, MousesMagics.namespacePath("mobeffect_dew_film"), AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, level -> -1)
            .addAttributeModifier(Attributes.SAFE_FALL_DISTANCE, MousesMagics.namespacePath("mobeffect_dew_film"), 2, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
            .addAttributeModifier(Attributes.FALL_DAMAGE_MULTIPLIER, MousesMagics.namespacePath("mobeffect_dew_film"), AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, level -> -15)
            .addAttributeModifier(Attributes.JUMP_STRENGTH, MousesMagics.namespacePath("mobeffect_dew_film"), 0.3, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
            .addAttributeModifier(Attributes.STEP_HEIGHT, MousesMagics.namespacePath("mobeffect_dew_film"), 0.7, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

    public static final DeferredHolder<MobEffect, MobEffect> BLOSSOM_BULWARK = MOB_EFFECT_DEFERRED_REGISTER.register("blossom_bulwark", () -> new BlossomBulwarkEffect(MobEffectCategory.BENEFICIAL, 0xF477FF));
}
