package com.mouse.mousesmagics.registries;

import com.mouse.mousesmagics.MousesMagics;
import com.mouse.mousesmagics.spells.dew.*;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class SpellRegistries {
    public static final DeferredRegister<AbstractSpell> SPELLS =
            DeferredRegister.create(SpellRegistry.SPELL_REGISTRY_KEY, MousesMagics.MOD_ID);

    //I'm so cooked

    //-Dew-

    //Dew-Drop - Low Cooldown - Shoot a dew drop which boosts the amount of mana regen you have for a few seconds.
    public static final Supplier<AbstractSpell> DEW_DROP = registerSpell(new DewDropSpell());

    //Bliss - Long Cast - Grants you Calm. Calm lowers mana regen for boosted health regen, movement speed, and lower cooldowns + gravity.
    public static final Supplier<AbstractSpell> BLISS = registerSpell(new BlissSpell());

    //Floral Spear
    public static final Supplier<AbstractSpell> FLORAL_SPEAR = registerSpell(new FloralSpearSpell());

    //Petal On The Wind
    public static final Supplier<AbstractSpell> PETAL_ON_THE_WIND = registerSpell(new PetalOnTheWindSpell());

    //Sparkling Orbs
    //public static final Supplier<AbstractSpell> SPARKLING_ORBS = registerSpell(new SparklingOrbsSpell());

    //Flower Dance
    public static final Supplier<AbstractSpell> FLOWER_DANCE = registerSpell(new FlowerDanceSpell());

    //Sanctum
    //public static final Supplier<AbstractSpell> SANCTUM = registerSpell(new SanctumSpell());

    //Protective Film
    public static final Supplier<AbstractSpell> PROTECTIVE_FILM = registerSpell(new ProtectiveFilmSpell());

    //Bulwark Of Blossoms
    public static final Supplier<AbstractSpell> BULWARK_OF_BLOSSOMS = registerSpell(new BulwarkOfBlossomsSpell());

    //Drip
    public static final Supplier<AbstractSpell> DRIP = registerSpell(new DripSpell());

    //Harvest
    //public static final Supplier<AbstractSpell> HARVEST = registerSpell(new HarvestSpell());

    //Blessing Of Spirit
    public static final Supplier<AbstractSpell> BLESSING_OF_SPIRIT = registerSpell(new BlessingOfSpiritSpell());

    //Bloom
    //public static final Supplier<AbstractSpell> BLOOM = registerSpell(new BloomSpell());

    //***

    //-Hex- (Can't be arsed rn)

    //***

    //-Fire-

    //Phoenix Rebirth - Heal based on missing health.
    //public static final Supplier<AbstractSpell> PHOENIX_REBIRTH = registerSpell(new PhoenixRebirthSpell());

    //***

    //-Evocation-

    //Defenestration
    //public static final Supplier<AbstractSpell> DEFENESTRATION = registerSpell(new DefenestrationSpell());

    //***

    //-Ender-

    //Dragon Burst
    //public static final Supplier<AbstractSpell> DRAGON_BURST = registerSpell(new DragonBurstSpell());

    public static void register(IEventBus eventBus) { SPELLS.register(eventBus); }

    public static DeferredHolder<AbstractSpell, AbstractSpell> registerSpell(AbstractSpell spell) {
        return SPELLS.register(spell.getSpellName(), () -> spell);
    }
}
