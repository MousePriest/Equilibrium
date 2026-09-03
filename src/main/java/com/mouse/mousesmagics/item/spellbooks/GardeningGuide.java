package com.mouse.mousesmagics.item.spellbooks;

import com.mouse.mousesmagics.registries.MMAttributeRegistries;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.item.SpellBook;
import io.redspace.ironsspellbooks.item.weapons.AttributeContainer;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class GardeningGuide extends SpellBook {

    public GardeningGuide() {
        super(12, ItemPropertiesHelper.equipment().fireResistant().stacksTo(1));

        withSpellbookAttributes(
                new AttributeContainer(MMAttributeRegistries.DEW_MAGIC_POWER, 0.10F, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                new AttributeContainer(AttributeRegistry.MAX_MANA, 200, AttributeModifier.Operation.ADD_VALUE)
        );
    }
}
