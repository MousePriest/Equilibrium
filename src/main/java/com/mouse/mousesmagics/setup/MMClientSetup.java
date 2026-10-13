package com.mouse.mousesmagics.setup;

import com.mouse.mousesmagics.MousesMagics;
import com.mouse.mousesmagics.entity.mobs.wizards.gardener.GardenerRenderer;
//import com.mouse.mousesmagics.entity.spells.defenestration.DefenestrationRenderer;
//import com.mouse.mousesmagics.entity.spells.dragon_burst.DragonBurstRenderer;
import com.mouse.mousesmagics.entity.spells.drip.DropRenderer;
import com.mouse.mousesmagics.entity.spells.floral_spear.FloralSpearRenderer;
import com.mouse.mousesmagics.particle.FadingFlowerParticle;
import com.mouse.mousesmagics.particle.SparkleParticle;
import com.mouse.mousesmagics.registries.MMEntityRegistries;
import com.mouse.mousesmagics.registries.MMParticleRegistries;
import com.mouse.mousesmagics.render.MMChargeSpellLayer;
import io.redspace.ironsspellbooks.render.SpellTargetingLayer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.NoopRenderer;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

@EventBusSubscriber(modid = MousesMagics.MOD_ID, value = Dist.CLIENT)
public class MMClientSetup {

    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.AddLayers event) {
        addLayerToPlayerSkin(event, PlayerSkin.Model.SLIM);
        addLayerToPlayerSkin(event, PlayerSkin.Model.WIDE);
        for (EntityType type : event.getEntityTypes()) {
            var renderer = event.getRenderer(type);
            if (renderer instanceof LivingEntityRenderer livingRenderer) {
                livingRenderer.addLayer(new SpellTargetingLayer.Vanilla<>(livingRenderer));
            }
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static void addLayerToPlayerSkin(EntityRenderersEvent.AddLayers event, PlayerSkin.Model skinName) {
        EntityRenderer<? extends Player> render = event.getSkin(skinName);
        if (render instanceof LivingEntityRenderer livingRenderer) {
            livingRenderer.addLayer(new MMChargeSpellLayer.Vanilla<>(livingRenderer));
        }
    }

    @SubscribeEvent
    public static void rendererRegister(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(MMEntityRegistries.GARDENER.get(), GardenerRenderer::new);
        //event.registerEntityRenderer(MMEntityRegistries.DEFENESTRATION_PROJECTILE.get(), DefenestrationRenderer::new);
        event.registerEntityRenderer(MMEntityRegistries.FLORAL_SPEAR_PROJECTILE.get(), FloralSpearRenderer::new);
        //event.registerEntityRenderer(MMEntityRegistries.DRAGON_BURST_PROJECTILE.get(), DragonBurstRenderer::new);
        //event.registerEntityRenderer(MMEntityRegistries.DRAGON_FIELD.get(), NoopRenderer::new);
        event.registerEntityRenderer(MMEntityRegistries.DROP.get(), DropRenderer::new);

        //    event.registerBlockEntityRenderer(MMBlockEntityRegistries.OVERGROWN_SICKLE_BLOCK.get(), OvergrownSickleBlockRenderer::new);
    }

    @SubscribeEvent
    public static void registerParticles(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(MMParticleRegistries.FADING_FLOWER_PARTICLE.get(), FadingFlowerParticle.Provider::new);
        event.registerSpriteSet(MMParticleRegistries.SPARKLE_PARTICLE.get(), SparkleParticle.Provider::new);
    }
}