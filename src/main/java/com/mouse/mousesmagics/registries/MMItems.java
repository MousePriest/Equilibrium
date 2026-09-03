package com.mouse.mousesmagics.registries;

import com.mouse.mousesmagics.MousesMagics;
import com.mouse.mousesmagics.item.armour.blossom_bandit.BlossomBanditArmourItem;
import com.mouse.mousesmagics.item.spellbooks.GardeningGuide;
import com.mouse.mousesmagics.item.staffs.MMStaffTiers;
import io.redspace.ironsspellbooks.api.item.weapons.ExtendedSwordItem;
import io.redspace.ironsspellbooks.item.weapons.StaffItem;
import io.redspace.ironsspellbooks.util.ItemPropertiesHelper;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.Rarity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;

public class MMItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MousesMagics.MOD_ID);

    public static void register(IEventBus eventBus) {ITEMS.register(eventBus);}

    //*DEW*

    //Dew focus
    public static final DeferredItem<Item> FRAGILE_DROPS = ITEMS.register("fragile_drops",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SERENE_SEED = ITEMS.register("serene_seed",
            () -> new ItemNameBlockItem(MMBlocks.DEW_CATCHER.get(), new Item.Properties()));

    //Dew staffs
    public static final DeferredHolder<Item, Item> OVERGROWN_SICKLE = ITEMS.registerItem("overgrownsickle", props ->
            new StaffItem(props.stacksTo(1).attributes(ExtendedSwordItem.createAttributes(MMStaffTiers.OVERGROWN_SICKLE))));

    //Dew books
    public static final DeferredHolder<Item, Item> GARDENING_GUIDE = ITEMS.register
            ("gardening_guide", GardeningGuide::new);
    public static final DeferredItem<Item> WATERING_INSTRUCTIONS = ITEMS.register("watering_instructions",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> PLANTING_INSTRUCTIONS = ITEMS.register("planting_instructions",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> HARVESTING_INSTRUCTIONS = ITEMS.register("harvesting_instructions",
            () -> new Item(new Item.Properties()));

    //Dew Armour
    public static final DeferredHolder<Item, Item> BLOSSOM_BANDIT_HELMET = ITEMS.registerItem("blossom_bandit_helmet",
            props -> new BlossomBanditArmourItem(ArmorItem.Type.HELMET, props.stacksTo(1).durability(ArmorItem.Type.HELMET.getDurability(37))));
    public static final DeferredHolder<Item, Item> BLOSSOM_BANDIT_CHESTPLATE = ITEMS.registerItem("blossom_bandit_chestplate",
            (props) -> new BlossomBanditArmourItem(ArmorItem.Type.CHESTPLATE, props.stacksTo(1).durability(ArmorItem.Type.CHESTPLATE.getDurability(37))));
    public static final DeferredHolder<Item, Item> BLOSSOM_BANDIT_LEGGINGS = ITEMS.registerItem("blossom_bandit_leggings",
            (props) -> new BlossomBanditArmourItem(ArmorItem.Type.LEGGINGS, props.stacksTo(1).durability(ArmorItem.Type.LEGGINGS.getDurability(37))));
    public static final DeferredHolder<Item, Item> BLOSSOM_BANDIT_BOOTS = ITEMS.registerItem("blossom_bandit_boots",
            (props) -> new BlossomBanditArmourItem(ArmorItem.Type.BOOTS, props.stacksTo(1).durability(ArmorItem.Type.BOOTS.getDurability(37))));

    //*HEX*

    //Hex focus
    public static final DeferredItem<Item> BOTTLE_OF_CURSES = ITEMS.register("bottle_of_curses",
            () -> new Item(new Item.Properties()));

    //Hex staffs
    public static final DeferredHolder<Item, Item> CEREMONIAL_DAGGER = ITEMS.register("ceremonial_dagger", () ->
            new StaffItem(ItemPropertiesHelper.equipment(1).attributes(ExtendedSwordItem.createAttributes(MMStaffTiers.CEREMONIAL_DAGGER))));

    //*CRYSTAL*

    //Crystal staffs
    public static final DeferredHolder<Item, Item> CRYSTAL_WRENCH = ITEMS.register("crystal_wrench", () ->
            new StaffItem(ItemPropertiesHelper.equipment(1).attributes(ExtendedSwordItem.createAttributes(MMStaffTiers.CRYSTAL_WRENCH))));

    //*FOOD*

    //Ace reference
    public static final DeferredItem<Item> ICE_LOLLY = ITEMS.register("ice_lolly",
            () -> new Item(new Item.Properties().rarity(Rarity.RARE).food(Foods.CHORUS_FRUIT).component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)));

    //*CURIOS*

    //Endless Apology

    //Wisp Cage

    //Reckless Vials

    //*ARTIFACTS*

    /* F______ H________

    -Ecliptic Greaves

    -M___ O_ M_____

    -F_______ C______ V___

     */

    //*MISC*

    //Plant-Be-Gone

    private static <T extends Item> DeferredHolder<Item, T> registerItem(String name, Function<Item.Properties, T> itemFactory) {
        return ITEMS.register(name, () -> itemFactory.apply(new Item.Properties()));
    }
}
