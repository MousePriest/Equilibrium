package com.mouse.mousesmagics.utils;

import com.mouse.mousesmagics.MousesMagics;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class MMTags {
    //Focuses
    public static final TagKey<Item> DEW_FOCUS = ItemTags.create(ResourceLocation.parse(ResourceLocation.fromNamespaceAndPath(MousesMagics.MOD_ID, "dew_focus").toString()));
    public static final TagKey<Item> HEX_FOCUS = ItemTags.create(ResourceLocation.parse(ResourceLocation.fromNamespaceAndPath(MousesMagics.MOD_ID, "hex_focus").toString()));
    public static final TagKey<Item> CRYSTAL_FOCUS = ItemTags.create(ResourceLocation.parse(ResourceLocation.fromNamespaceAndPath(MousesMagics.MOD_ID, "crystal_focus").toString()));
}
