package com.mouse.mousesmagics.registries;

import com.mouse.mousesmagics.MousesMagics;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class MMCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MousesMagics.MOD_ID);

    public static final Supplier<CreativeModeTab> MOUSES_MAGICS_TAB = CREATIVE_MODE_TAB.register("mouses_magics_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(MMItems.SERENE_SEED.get()))
                    .title(Component.translatable("creativetab.mousesmagics.mouses_magics"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(MMItems.OVERGROWN_SICKLE.get());
                        output.accept((ItemLike) MMItems.GARDENING_GUIDE);
                        output.accept((ItemLike) MMItems.BLOSSOM_BANDIT_HELMET);
                        output.accept((ItemLike) MMItems.BLOSSOM_BANDIT_CHESTPLATE);
                        output.accept((ItemLike) MMItems.BLOSSOM_BANDIT_LEGGINGS);
                        output.accept((ItemLike) MMItems.BLOSSOM_BANDIT_BOOTS);
                        output.accept(MMItems.FRAGILE_DROPS);
                        output.accept(MMItems.SERENE_SEED);
                        output.accept(MMItems.DEW_RUNE);
                        output.accept((ItemLike) MMItems.DEW_UPGRADE_ORB);
                        output.accept(MMItems.HARVESTING_INSTRUCTIONS);
                        output.accept(MMItems.PLANTING_INSTRUCTIONS);
                        output.accept(MMItems.WATERING_INSTRUCTIONS);
                        output.accept(MMItems.FLORAL_SCENT);
                        output.accept(MMItems.ICE_LOLLY);
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
