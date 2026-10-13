package com.mouse.mousesmagics.registries;

import com.mouse.mousesmagics.MousesMagics;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;

public class MMDamageTypeRegisteries {
    public static ResourceKey<DamageType> register(String name) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse(ResourceLocation.fromNamespaceAndPath(MousesMagics.MOD_ID, name).toString()));}

    // Dew
    public static final ResourceKey<DamageType> DEW_MAGIC = register("dew_magic");

}
