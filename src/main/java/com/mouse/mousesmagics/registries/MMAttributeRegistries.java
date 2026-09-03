package com.mouse.mousesmagics.registries;

import com.mouse.mousesmagics.MousesMagics;
import io.redspace.ironsspellbooks.api.attribute.MagicPercentAttribute;
import io.redspace.ironsspellbooks.api.attribute.MagicRangedAttribute;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@EventBusSubscriber(modid = MousesMagics.MOD_ID)
public class MMAttributeRegistries {
    private static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(Registries.ATTRIBUTE, MousesMagics.MOD_ID);

    public static final DeferredHolder<Attribute, Attribute> DEW_MAGIC_RESIST = registerResistanceAttribute("dew");
    public static final DeferredHolder<Attribute, Attribute> DEW_MAGIC_POWER = registerPowerAttribute("dew");

    public static final DeferredHolder<Attribute, Attribute> HEX_MAGIC_RESIST = registerResistanceAttribute("hex");
    public static final DeferredHolder<Attribute, Attribute> HEX_MAGIC_POWER = registerPowerAttribute("hex");

    public static final DeferredHolder<Attribute, Attribute> CRYSTAL_MAGIC_RESIST = registerResistanceAttribute("crystal");
    public static final DeferredHolder<Attribute, Attribute> CRYSTAL_MAGIC_POWER = registerPowerAttribute("crystal");

    public static void register(IEventBus eventBus)
    {
        ATTRIBUTES.register(eventBus);
    }

    @SubscribeEvent
    public static void modifyEntityAttributes(EntityAttributeModificationEvent event)
    {
        event.getTypes().forEach(entityType ->
                ATTRIBUTES.getEntries().forEach(
                        attributeDeferredHolder -> event.add(entityType, attributeDeferredHolder
                        )));
    }

    // ;_;
    private static DeferredHolder<Attribute, Attribute> registerResistanceAttribute(String id)
    {
        return ATTRIBUTES.register(id + "_magic_resist", () ->
                (new MagicPercentAttribute("attribute.mousesmagics." + id + "_magic_resist",
                        1.0D, 0, 10).setSyncable(true)));
    }

    private static DeferredHolder<Attribute, Attribute> registerPowerAttribute(String id)
    {
        return ATTRIBUTES.register(id + "_spell_power", () ->
                (new MagicPercentAttribute("attribute.mousesmagics." + id + "_spell_power",
                        1.0D, 0, 10).setSyncable(true)));
    }
}
