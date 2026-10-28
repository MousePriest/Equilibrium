package com.mouse.mousesmagics.registries;

import com.mouse.mousesmagics.MousesMagics;
import com.mouse.mousesmagics.entity.mobs.wizards.gardener.GardenerEntity;
//import com.mouse.mousesmagics.entity.spells.defenestration.DefenestrationProjectile;
//import com.mouse.mousesmagics.entity.spells.dragon_burst.DragonBurstProjectile;
//import com.mouse.mousesmagics.entity.spells.dragon_burst.DragonField;
import com.mouse.mousesmagics.entity.spells.drip.Drop;
import com.mouse.mousesmagics.entity.spells.floral_spear.FloralSpearProjectile;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class MMEntityRegistries {
    private static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(Registries.ENTITY_TYPE, MousesMagics.MOD_ID);

    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }

    //Spells

    //public static final DeferredHolder<EntityType<?>, EntityType<DefenestrationProjectile>> DEFENESTRATION_PROJECTILE =
    //        ENTITIES.register("defenestration", () -> EntityType.Builder.<DefenestrationProjectile>of(DefenestrationProjectile::new, MobCategory.MISC)
    //                .sized(.5f, .5f)
    //                .clientTrackingRange(64)
    //                .build(ResourceLocation.fromNamespaceAndPath(MousesMagics.MOD_ID, "defenestration").toString()));

    //public static final DeferredHolder<EntityType<?>, EntityType<DragonBurstProjectile>> DRAGON_BURST_PROJECTILE =
    //        ENTITIES.register("dragon_burst_projectile", () -> EntityType.Builder.<DragonBurstProjectile>of(DragonBurstProjectile::new, MobCategory.MISC)
    //                .sized(.5f, .5f)
    //                .clientTrackingRange(64)
    //                .build(ResourceLocation.fromNamespaceAndPath(MousesMagics.MOD_ID, "dragon_burst_projectile").toString()));

    //public static final DeferredHolder<EntityType<?>, EntityType<DragonField>> DRAGON_FIELD =
    //        ENTITIES.register("dragon_field", () -> EntityType.Builder.<DragonField>of(DragonField::new, MobCategory.MISC)
    //                .sized(4f, 1.2f)
    //                .clientTrackingRange(64)
    //                .build(ResourceLocation.fromNamespaceAndPath(MousesMagics.MOD_ID, "dragon_field").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<FloralSpearProjectile>> FLORAL_SPEAR_PROJECTILE =
            ENTITIES.register("floral_spear_projectile", () -> EntityType.Builder.<FloralSpearProjectile>of(FloralSpearProjectile::new, MobCategory.MISC)
                    .sized(.5f, .5f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MousesMagics.MOD_ID, "floral_spear_projectile").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<Drop>> DROP =
            ENTITIES.register("drop", () -> EntityType.Builder.<Drop>of(Drop::new, MobCategory.MISC)
                    .sized(.5f, .5f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MousesMagics.MOD_ID, "drop").toString()));


    //Mobs

    public static final DeferredHolder<EntityType<?>, EntityType<GardenerEntity>> GARDENER =
            ENTITIES.register("gardener", () -> EntityType.Builder.of(GardenerEntity::new, MobCategory.MONSTER)
                    .sized(.6f, 1.8f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(MousesMagics.MOD_ID, "gardener").toString()));

    }