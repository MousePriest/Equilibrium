package com.mouse.mousesmagics.item.staffs.overgrown_sickle;

import com.mouse.mousesmagics.MousesMagics;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;

public class OvergrownSickleModel extends DefaultedItemGeoModel<OvergrownSickle> {
    public OvergrownSickleModel() {
        super(ResourceLocation.fromNamespaceAndPath(MousesMagics.MOD_ID, ""));
    }

    @Override
    public ResourceLocation getModelResource(OvergrownSickle animatable) {
        return ResourceLocation.fromNamespaceAndPath(MousesMagics.MOD_ID, "geo/staves/overgrownsickle.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(OvergrownSickle animatable) {
        return ResourceLocation.fromNamespaceAndPath(MousesMagics.MOD_ID, "textures/item/overgrown_sickle/overgrownsickle.png");
    }

    @Override
    public ResourceLocation getAnimationResource(OvergrownSickle animatable) {
        return ResourceLocation.fromNamespaceAndPath(MousesMagics.MOD_ID, "animations/entities/spells/blank.animation.json");
    }
}
