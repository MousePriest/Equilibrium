package com.mouse.mousesmagics.item.staffs;

import com.mouse.mousesmagics.registries.MMAttributeRegistries;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.item.weapons.AttributeContainer;
import io.redspace.ironsspellbooks.item.weapons.IronsWeaponTier;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class MMStaffTiers implements IronsWeaponTier {

    //Dew Staffs
    public static MMStaffTiers OVERGROWN_SICKLE = new MMStaffTiers(3, -3,
            new AttributeContainer(MMAttributeRegistries.DEW_MAGIC_POWER, 0.15f,AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
            new AttributeContainer(AttributeRegistry.CASTING_MOVESPEED, .20, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
            new AttributeContainer(AttributeRegistry.SPELL_POWER, .05, AttributeModifier.Operation.ADD_MULTIPLIED_BASE)
    );

    //Hex Staffs
    public static MMStaffTiers CEREMONIAL_DAGGER = new MMStaffTiers(3, -3,
            new AttributeContainer(MMAttributeRegistries.HEX_MAGIC_POWER, 0.15f,AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

    //Crystal Staffs
    public static MMStaffTiers CRYSTAL_WRENCH = new MMStaffTiers(3, -3,
            new AttributeContainer(MMAttributeRegistries.CRYSTAL_MAGIC_POWER, 0.15f,AttributeModifier.Operation.ADD_MULTIPLIED_BASE));

    float damage;
    float speed;
    AttributeContainer[] attributeContainers;

    public MMStaffTiers(float damage, float speed, AttributeContainer... attributeContainers)
    {
        this.damage = damage;
        this.speed = speed;
        this.attributeContainers = attributeContainers;
    }

    @Override
    public float getAttackDamageBonus() {
        return damage;
    }

    @Override
    public float getSpeed() {
        return speed;
    }

    @Override
    public AttributeContainer[] getAdditionalAttributes() {
        return this.attributeContainers;
    }
}
