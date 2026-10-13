package com.mouse.mousesmagics.registries;

import com.mouse.mousesmagics.MousesMagics;
import io.redspace.ironsspellbooks.item.armor.UpgradeOrbType;
import io.redspace.ironsspellbooks.registries.UpgradeOrbTypeRegistry;
import net.minecraft.resources.ResourceKey;

public class MMUpgradeOrbTypeRegistries {

    public static ResourceKey<UpgradeOrbType> DEW_SPELL_POWER = ResourceKey.create(UpgradeOrbTypeRegistry.UPGRADE_ORB_REGISTRY_KEY, MousesMagics.id("dew_power"));
}
