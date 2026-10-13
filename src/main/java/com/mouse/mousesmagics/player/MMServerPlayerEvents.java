package com.mouse.mousesmagics.player;

import com.mouse.mousesmagics.effect.BlossomBulwarkEffect;
import com.mouse.mousesmagics.registries.MMEffectRegisteries;
import io.redspace.ironsspellbooks.api.entity.IMagicEntity;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

@EventBusSubscriber
public class MMServerPlayerEvents {

    @SubscribeEvent
    public static void onLivingIncomingDamage(LivingIncomingDamageEvent event) {
        var livingEntity = event.getEntity();
        if ((livingEntity instanceof ServerPlayer) || (livingEntity instanceof IMagicEntity)) {
            if (livingEntity.hasEffect(MMEffectRegisteries.BLOSSOM_BULWARK)) {
                if (BlossomBulwarkEffect.doEffect(livingEntity, event.getSource())) {
                    event.setCanceled(true);
                }
            }
        }
    }
}

