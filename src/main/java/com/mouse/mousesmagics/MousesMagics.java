package com.mouse.mousesmagics;

import com.mouse.mousesmagics.registries.MMBlocks;
import com.mouse.mousesmagics.item.staffs.ceremonial_dagger.Ceremonial_DaggerRenderer;
import com.mouse.mousesmagics.item.staffs.crystal_wrench.Crystal_WrenchRenderer;
import com.mouse.mousesmagics.item.staffs.overgrown_sickle.OvergrownSickleRenderer;
import com.mouse.mousesmagics.registries.*;
import mod.azure.azurelib.common.render.item.AzItemRendererRegistry;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

@Mod(MousesMagics.MOD_ID)
public class MousesMagics {
    public static final String MOD_ID = "mousesmagics";
    public static final Logger LOGGER = LogUtils.getLogger();

    public MousesMagics(IEventBus modEventBus, ModContainer modContainer)
    {
        modEventBus.addListener(this::commonSetup);

        NeoForge.EVENT_BUS.register(this);

        MMCreativeModeTabs.register(modEventBus);

        MMItems.register(modEventBus);

        MMAttributeRegistries.register(modEventBus);

        MMEffectRegisteries.register(modEventBus);

        MMEntityRegistries.register(modEventBus);

        MMSchoolRegistries.register(modEventBus);

        MMParticleRegistries.register(modEventBus);

        MMBlocks.register(modEventBus);

        MMBlockEntityRegistries.register(modEventBus);

        SpellRegistries.register(modEventBus);
    }

    public static ResourceLocation namespacePath(@NotNull String path) {
        return ResourceLocation.fromNamespaceAndPath(MousesMagics.MOD_ID, path);
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {}

    private void commonSetup(final FMLCommonSetupEvent event) {}

    @EventBusSubscriber(value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {

            //Staves
            AzItemRendererRegistry.register(OvergrownSickleRenderer::new, MMItems.OVERGROWN_SICKLE.get());
            AzItemRendererRegistry.register(Ceremonial_DaggerRenderer::new, MMItems.CEREMONIAL_DAGGER.get());
            AzItemRendererRegistry.register(Crystal_WrenchRenderer::new, MMItems.CRYSTAL_WRENCH.get());
        }
    }
}
