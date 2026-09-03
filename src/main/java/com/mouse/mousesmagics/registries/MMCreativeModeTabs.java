package com.mouse.mousesmagics.registries;

import com.mouse.mousesmagics.MousesMagics;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class MMCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MousesMagics.MOD_ID);

    public static final Supplier<CreativeModeTab> MOUSES_MAGICS_TAB = CREATIVE_MODE_TAB.register("mouses_magics_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(MMItems.BOTTLE_OF_CURSES.get()))
                    .title(Component.translatable("creativetab.mousesmagics.mouses_magics"))
                    .displayItems((itemDisplayParameters, output) -> {
                        //Focuses
                        output.accept(MMItems.BOTTLE_OF_CURSES);
                        output.accept(MMItems.FRAGILE_DROPS);
                        //Blocks
                        output.accept(MMBlocks.BLOSSOM_CHALK);
                        //Misc
                        output.accept(MMItems.ICE_LOLLY);
                        //Staves
                        output.accept(MMItems.OVERGROWN_SICKLE.get());
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
