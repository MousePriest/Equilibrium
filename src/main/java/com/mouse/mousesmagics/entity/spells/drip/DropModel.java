package com.mouse.mousesmagics.entity.spells.drip;

import com.mouse.mousesmagics.MousesMagics;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class DropModel extends GeoModel<Drop> {

    @Override
    public ResourceLocation getModelResource(Drop animatable) {
        return ResourceLocation.fromNamespaceAndPath(MousesMagics.MOD_ID, "geo/entities/spells/drop.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Drop animatable) {
        return ResourceLocation.fromNamespaceAndPath(MousesMagics.MOD_ID, "textures/entity/drop/drop.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Drop animatable) {
        return ResourceLocation.fromNamespaceAndPath(MousesMagics.MOD_ID, "animations/entities/spells/blank.animation.json");
    }
}
