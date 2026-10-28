package com.mouse.mousesmagics.registries;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Items;

public class MMFoods {
    private static FoodProperties.Builder stew(int nutrition) {
        return (new FoodProperties.Builder()).nutrition(nutrition).saturationModifier(0.6F).usingConvertsTo(Items.BOWL);
    }

    public static final FoodProperties ELDRITCH_ICE_LOLLY;

    static {
    ELDRITCH_ICE_LOLLY = (new FoodProperties.Builder()).nutrition(4).saturationModifier(1.2F).effect(new MobEffectInstance(MobEffects.REGENERATION, 60, 3), 0.75F).effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200, 0), 0.75F).effect(new MobEffectInstance(MobEffects.ABSORPTION, 200, 2), 0.75F).effect(new MobEffectInstance(MobEffects.DARKNESS, 6000, 1), 0.25F).alwaysEdible().build();
    }
}
