package com.mouse.mousesmagics.registries;

import com.mouse.mousesmagics.MousesMagics;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class MMSoundRegistries {
    private static final DeferredRegister<SoundEvent> SOUND_EVENT = DeferredRegister.create(Registries.SOUND_EVENT, MousesMagics.MOD_ID);

    //-o-|[ Spells

    // Dew Cast
    public static DeferredHolder<SoundEvent, SoundEvent> DEW_CAST = registerSoundEvent("dew_cast");

    private static DeferredHolder<SoundEvent, SoundEvent> registerSoundEvent(String name)
    {
        return SOUND_EVENT.register(name, () -> SoundEvent.createVariableRangeEvent
                (ResourceLocation.fromNamespaceAndPath(MousesMagics.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus)
    {
        SOUND_EVENT.register(eventBus);
    }
}
