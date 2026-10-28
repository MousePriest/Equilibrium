package com.mouse.mousesmagics.entity.spells.dew_drop;

import com.mouse.mousesmagics.MousesMagics;
import com.mouse.mousesmagics.entity.spells.drip.Drop;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class DewDropModel extends GeoModel<Drop> {

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
